package org.cweili.wray.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.cweili.ipseeker.IPSeeker;
import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Comment;
import org.cweili.wray.service.CommentService;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.util.HtmlFixer;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cweili
 * @version 2012-11-25 下午3:23:57
 * 
 */
@Service("commentService")
public class CommentServiceImpl extends BaseService implements CommentService {

	@Override
	public int count() {
		return (int) commentDao.count();
	}

	@Override
	public List<Comment> getCommentsByArticle(Article article) {
		return seekIp(commentDao.findByArticleIdAndStat(article.getArticleId(),
				Comment.STAT_DISPLAY));
	}

	@Override
	public List<Comment> getComments(int page, int limit) {
		return seekIp(commentDao.findAll(new PageRequest(page - 1, limit)).getContent());
	}

	@Override
	public Comment save(Comment comment) {
		if ("".equals(comment.getCommentId())) {
			comment.setCommentId(Function.generateId());
		}
		comment.setContent(filterContent(comment.getContent()));
		return commentDao.save(comment);
	}

	@Override
	public boolean remove(List<String> ids) {
		boolean result = false;
		for (String id : ids) {
			Comment comment = commentDao.findOne(id);
			if (null != comment) {
				comment.setStat(Comment.STAT_BLOCK);
				commentDao.save(comment);
				result = true;
			}
		}
		return result;
	}

	private String filterContent(String content) {
		return HtmlFixer.fix(Function.stripTags(content, Constant.DANGEROUS_TAGS).replace(
				"javascript:", ""));
	}

	private List<Comment> seekIp(List<Comment> before) {
		List<Comment> comments = new ArrayList<Comment>();
		for (Comment comment : before) {
			comment.setOrigin(IPSeeker.getInstance().getCountry(comment.getIp()) + " "
					+ IPSeeker.getInstance().getArea(comment.getIp()));
			comments.add(comment);
		}
		return comments;
	}

}
