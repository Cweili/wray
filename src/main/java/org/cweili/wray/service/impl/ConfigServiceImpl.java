package org.cweili.wray.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.domain.Config;
import org.cweili.wray.service.ConfigService;
import org.cweili.wray.util.Function;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:22:46
 * 
 */
@Service("blogConfig")
public class ConfigServiceImpl extends BaseService implements ConfigService {

	private static Log log = LogFactory.getLog(ConfigServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.ConfigService#get(java.lang.String)
	 */
	@Override
	public String get(String key) {
		String value = getConfigMap().get(key);
		log.info("Find Config: " + key + " => " + value);
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.ConfigService#saveOrUpdate(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void save(Config config) {
		log.info("Save " + config);
		configDao.save(config);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.ConfigService#getConfigMap()
	 */
	@Override
	public Map<String, String> getConfigMap() {
		if (configMap == null) {
			UpdateConfigMap();
		}
		return configMap;
	}

	@Override
	public void saveRequest(HttpServletRequest request, String[] nonHtmlArray, String[] htmlArray) {
		List<String> nonHtmlList = Arrays.asList(nonHtmlArray);
		List<String> htmlList = Arrays.asList(htmlArray);
		Map<String, String[]> map = request.getParameterMap();
		Map<String, String> values = new HashMap<String, String>();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			if (nonHtmlList.contains(entry.getKey())) {
				values.put(entry.getKey(), Function.trimAndStripTags(entry.getValue()[0]));
			} else if (htmlList.contains(entry.getKey())) {
				values.put(entry.getKey(), entry.getValue()[0].trim());
			}
		}
		for (Map.Entry<String, String> entry : values.entrySet()) {
			save(new Config(entry.getKey(), entry.getValue()));
		}
		UpdateConfigMap();

	}

	private void UpdateConfigMap() {
		if (configMap != null) {
			configMap.clear();
		} else {
			configMap = new HashMap<String, String>();
		}
		for (Config config : configDao.findAll()) {
			log.info("Find " + config);
			configMap.put(config.getKey(), config.getValue());
		}
	}
}
