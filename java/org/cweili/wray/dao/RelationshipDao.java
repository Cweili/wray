package org.cweili.wray.dao;

import java.util.List;

/**
 *
 * @author Cweili
 * @version 2012-8-16 下午10:55:52
 *
 */
public interface RelationshipDao extends BaseDao<long[]> {
	
	/**
	 * @param object
	 * @param relatedIds
	 * @return
	 */
	public int[] saveOrUpdate(Object object, List<Long> relatedIds);
	
	/**
	 * @param object
	 * @return
	 */
	public List<Long> getRelatedIds(Object object);
	
}
