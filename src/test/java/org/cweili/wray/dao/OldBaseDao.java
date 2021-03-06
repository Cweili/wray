package org.cweili.wray.dao;

/**
 * 
 * @author Cweili
 * @version 2012-8-16 下午5:07:27
 * 
 * @param <T>
 */
public interface OldBaseDao<T> {

	/**
	 * @param t
	 * @return
	 */
	public long save(final T t);

	/**
	 * @param t
	 * @return
	 */
	public int update(final T t);

	/**
	 * @param t
	 * @return
	 */
	public int remove(final T t);
}
