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
	 * @return
	 */
	public int count();

	/**
	 * @param commentId
	 * @return
	 */
	public Comment findById(String commentId);

	/**
	 * @param article
	 * @return
	 */
	public List<Comment> findByArticle(Article article);

	/**
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Comment> find(int page, int limit);

	/**
	 * @param comment
	 * @return
	 */
	public Comment save(Comment comment);

	/**
	 * @param ids
	 * @return
	 */
	public boolean remove(List<String> ids);

	/**
	 * @param commentId
	 * @return
	 */
	public Article findArticleByCommentId(String commentId);

	/**
	 * @param size
	 * @return
	 */
	public List<Comment> getRecentComments(int size);

	/**
	 * 
	 */
	public void updateRecentComments();
}
