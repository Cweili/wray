package org.cweili.wray.service;

import java.util.Map;

import org.cweili.wray.domain.Config;
import org.springframework.web.context.request.WebRequest;

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
	 * @param config
	 */
	public void save(Config config);

	/**
	 * @param request
	 * @param nonHtmlArray
	 * @param htmlArray
	 */
	public void saveRequest(WebRequest request, String[] nonHtmlArray, String[] htmlArray);

}
