package org.cweili.wray.dao;

import java.util.List;

public interface RelationshipDao extends BaseDao<long[]> {

	public List<Long> getRelationship(Class<?> domain, long id);
	
	public int remove(Class<?> domain, List<Long> ids);
}
