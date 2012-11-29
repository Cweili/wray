package org.cweili.wray.service;

import java.sql.SQLException;
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
	 * @throws SQLException
	 */
	public long save(Comment comment) throws SQLException;

	/**
	 * @param comment
	 * @return
	 * @throws SQLException
	 */
	public boolean update(Comment comment) throws SQLException;

	/**
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public boolean remove(List<Long> ids) throws SQLException;
}
