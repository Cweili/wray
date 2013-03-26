package org.cweili.wray.dao;

import java.util.Map;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:07:48
 * 
 */
public interface ConfigDao {

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public int saveOrUpdate(String key, String value);

	/**
	 * @return
	 */
	public Map<String, String> getAll();
}
