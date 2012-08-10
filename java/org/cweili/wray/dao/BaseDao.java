package org.cweili.wray.dao;


public interface BaseDao<T> {
	
	public long save(final T t);
	
	public int update(final T t);
	
	public int remove(final T t);
}
