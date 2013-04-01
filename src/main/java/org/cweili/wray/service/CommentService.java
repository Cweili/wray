package org.cweili.wray.service;

import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Comment;

/**
 * 
 * @author cweili
 * @version 2012-11-25 下午3:10:59
 * 
 */
public interface CommentService {

	/**
	 * @param article
	 * @return
	 */
	public List<Comment> getCommentsByArticle(Article article);

	/**
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Comment> getComments(int page, int limit);

	/**
	 * @param comment
	 * @return
	 */
	public Comment save(Comment comment);

	/**
	 * @param ids
	 * @return
	 */
	public boolean remove(List<Long> ids);
}
