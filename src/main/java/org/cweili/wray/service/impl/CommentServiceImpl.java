package org.cweili.wray.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Comment;
import org.cweili.wray.service.CommentService;
import org.cweili.wray.util.Function;
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
	public List<Comment> getCommentsByArticle(Article article) {
		return commentDao.findByArticleIdAndStat(article.getArticleId(), Comment.STAT_DISPLAY);
	}

	@Override
	public List<Comment> getComments(int page, int limit) {
		return commentDao.findAll(new PageRequest(page, limit)).getContent();
	}

	@Override
	public Comment save(Comment comment) {
		if (0 == comment.getId()) {
			comment.setId(Function.generateId());
		}
		return commentDao.save(comment);
	}

	@Override
	public boolean remove(List<Long> ids) {
		long before = commentDao.count();
		List<Comment> comments = new ArrayList<Comment>();
		for (long id : ids) {
			Comment comment = new Comment();
			comment.setId(id);
			comments.add(comment);
		}
		commentDao.delete(comments);
		return commentDao.count() < before;
	}

}
