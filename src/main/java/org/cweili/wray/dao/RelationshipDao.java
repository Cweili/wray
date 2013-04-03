package org.cweili.wray.dao;

import java.util.List;

import org.cweili.wray.domain.Relationship;

/**
 * 
 * @author Cweili
 * @version 2013-4-3 上午11:20:43
 * 
 */
public interface RelationshipDao extends BaseDao<Relationship> {

	/**
	 * @param articleId
	 * @return
	 */
	public List<Relationship> findByArticleId(String articleId);

	/**
	 * @param itemId
	 * @return
	 */
	public List<Relationship> findByItemId(String itemId);

	/**
	 * @param articleId
	 * @param itemId
	 * @return
	 */
	public Relationship findByArticleIdAndItemId(String articleId, String itemId);

}
