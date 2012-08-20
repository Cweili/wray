package org.cweili.wray.service;

import java.util.Map;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:16:53
 * 
 */
public interface ConfigService {

	/**
	 * @param key
	 * @return
	 */
	public String get(String key);

	/**
	 * @return
	 */
	public Map<String, String> getConfigMap();

	/**
	 * 
	 */
	public void UpdateConfigMap();

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean saveOrUpdate(String key, String value);

}
