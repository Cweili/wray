package org.cweili.wray.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.cweili.wray.dao.ConfigDao;
import org.cweili.wray.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blogConfig")
public class ConfigServiceImpl extends BaseService implements ConfigService {
	
	@Autowired
	private ConfigDao configDao;
	
	private static Map<String, String> configMap = new HashMap<String, String>();
	
	@Override
	public String get(String key) {
		if(configMap.isEmpty()) {
			UpdateConfigMap();
		}
		return configMap.get(key);
	}

	@Override
	public void UpdateConfigMap() {
		configMap.clear();
		configMap.putAll(configDao.getAll());
	}

	@Override
	public boolean saveOrUpdate(String key, String value) {
		return configDao.saveOrUpdate(key, value) > 0;
	}

	@Override
	public Map<String, String> getConfigMap() {
		if(configMap.isEmpty()) {
			UpdateConfigMap();
		}
		return configMap;
	}
}
