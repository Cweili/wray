package org.cweili.wray.service;

import java.util.List;

import org.cweili.wray.domain.Page;
import org.cweili.wray.entity.Article;
import org.cweili.wray.entity.Comment;

/**
 * 评论 Service
 * 
 * @author Cweili
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
	 * @param size
	 * @return
	 */
	public Page<Comment> find(int page, int size);

	/**
	 * @param comment
	 * @return
	 */
	public Comment save(Comment comment);

	/**
	 * @param ids
	 * @return
	 */
	public boolean switchStat(List<String> ids);

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
