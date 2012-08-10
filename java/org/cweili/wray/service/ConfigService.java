package org.cweili.wray.service;

import java.util.Map;

public interface ConfigService {

	public String get(String key);
	
	public Map<String, String> getConfigMap();
	
	public void UpdateConfigMap();
	
	public boolean saveOrUpdate(String key, String value);
	
}
