package org.cweili.wray.dao;

import java.util.Map;

public interface ConfigDao {

	public String get(String key);
	
	public int saveOrUpdate(String key, String value);
	
	public Map<String, String> getAll();
}
