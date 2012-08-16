package org.cweili.wray.dao;

import java.util.List;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:08:16
 *
 */
public interface RelationshipDao extends BaseDao<long[]> {

	/**
	 * @param domain
	 * @param ids
	 * @return
	 */
	public int remove(Class<?> domain, List<Long> ids);
}
