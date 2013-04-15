package org.cweili.wray.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
	public Comment findById(String commentId) {
		return commentDao.findOne(commentId);
	}

	@Override
	public List<Comment> findByArticle(Article article) {
		return commentDao.findByArticleIdAndStat(article.getArticleId(), Comment.STAT_DISPLAY);
	}

	@Override
	public List<Comment> find(int page, int limit) {
		return dealCommentList(commentDao.findAll(new PageRequest(page - 1, limit)).getContent());
	}

	@Override
	public Comment save(Comment comment) {
		String author = Function.trimAndStripTags(comment.getAuthor());
		if ("".equals(author)) {
			return null;
		}
		String email = Function.trimAndStripTags(comment.getEmail());
		if ("".equals(email)) {
			return null;
		}
		String link = Function.trimAndStripTags(comment.getLink());
		String content = filterContent(comment.getContent());
		if ("".equals(content)) {
			return null;
		}

		comment.setAuthor(author);
		comment.setEmail(email);
		comment.setLink(link);
		comment.setContent(content);

		if ("".equals(comment.getCommentId())) {
			comment.setCommentId(Function.generateId());
		}

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
		return HtmlFixer.fix(StringUtils.replace(
				Function.stripTags(content, Constant.DANGEROUS_TAGS), "javascript:", ""));
	}

	private List<Comment> dealCommentList(List<Comment> before) {
		List<Comment> comments = new ArrayList<Comment>();
		for (Comment comment : before) {
			comment.setOrigin(IPSeeker.getInstance().getCountry(comment.getIp()) + " "
					+ IPSeeker.getInstance().getArea(comment.getIp()));
			comment.setContent(StringUtils.substring(Function.stripTags(comment.getContent()), 0,
					30) + " ...");
			Article article = articleDao.findOne(comment.getArticleId());
			if (null != article) {
				comment.setArtilceTitle(article.getTitle());
			}
			comments.add(comment);
		}
		return comments;
	}

}
