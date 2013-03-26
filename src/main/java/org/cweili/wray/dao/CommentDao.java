package org.cweili.wray.dao;

import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Comment;

/**
 * 
 * @author cweili
 * @version 2012-8-21 下午4:07:20
 *
 */
public interface CommentDao extends BaseDao<Comment> {
	
	public List<Comment> getComments(byte status, int start, int limit, String order);

	public List<Comment> getCommentsByArticle(Article article, String order);
	
	public int remove(List<Long> ids);
	
}
