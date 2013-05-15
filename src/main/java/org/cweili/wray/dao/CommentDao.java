package org.cweili.wray.dao;

import java.util.List;

import org.cweili.wray.domain.dto.Comment;
import org.springframework.data.domain.Sort;

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
	public List<Comment> findByArticleIdAndStat(String articleId, byte stat, Sort sort);

}
