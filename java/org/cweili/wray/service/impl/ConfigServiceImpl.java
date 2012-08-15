package org.cweili.wray.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.cweili.wray.service.ConfigService;
import org.springframework.stereotype.Service;

@Service("blogConfig")
public class ConfigServiceImpl extends BaseService implements ConfigService {
	
	@Override
	public String get(String key) {
		if(configMap == null) {
			UpdateConfigMap();
		}
		return configMap.get(key);
	}

	@Override
	public void UpdateConfigMap() {
		if(configMap != null) {
			configMap.clear();
		} else {
			configMap = new HashMap<String, String>();
		}
		configMap.putAll(configDao.getAll());
	}

	@Override
	public boolean saveOrUpdate(String key, String value) {
		return configDao.saveOrUpdate(key, value) > 0;
	}

	@Override
	public Map<String, String> getConfigMap() {
		if(configMap == null) {
			UpdateConfigMap();
		}
		return configMap;
	}
}
