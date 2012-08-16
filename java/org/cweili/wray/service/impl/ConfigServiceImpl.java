package org.cweili.wray.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.cweili.wray.service.ConfigService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:22:46
 *
 */
@Service("blogConfig")
public class ConfigServiceImpl extends BaseService implements ConfigService {
	
	/* (non-Javadoc)
	 * @see org.cweili.wray.service.ConfigService#get(java.lang.String)
	 */
	@Override
	public String get(String key) {
		if(configMap == null) {
			UpdateConfigMap();
		}
		return configMap.get(key);
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.service.ConfigService#UpdateConfigMap()
	 */
	@Override
	public void UpdateConfigMap() {
		if(configMap != null) {
			configMap.clear();
		} else {
			configMap = new HashMap<String, String>();
		}
		configMap.putAll(configDao.getAll());
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.service.ConfigService#saveOrUpdate(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean saveOrUpdate(String key, String value) {
		return configDao.saveOrUpdate(key, value) > 0;
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.service.ConfigService#getConfigMap()
	 */
	@Override
	public Map<String, String> getConfigMap() {
		if(configMap == null) {
			UpdateConfigMap();
		}
		return configMap;
	}
}
