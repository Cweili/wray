package org.cweili.wray.dao;

import org.cweili.wray.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author Cweili
 * @version 2013-4-3 上午11:19:55
 * 
 */
public interface CommentDao extends BaseDao<Comment> {

	/**
	 * @param articleId
	 * @param stat
	 * @return
	 */
	public Page<Comment> findByArticleIdAndStat(String articleId, byte stat, Pageable page);

}
