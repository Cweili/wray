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
	 * @param domain
	 * @param id
	 * @return
	 */
	public List<Long> getIds(Class domain, long id);
	
}
