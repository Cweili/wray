package org.cweili.wray.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.dto.Config;
import org.cweili.wray.util.Constant;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
 * Initialization Interceptor
 * 
 * @author cweili
 * @version 2012-8-16 下午5:14:05
 * 
 */
public class InitInterceptor extends BaseInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		int configCount = blogConfig.getConfigMap().size();
		if (1 > configCount) {
			try {
				importConfig();
			} catch (Exception e) {
				response.getWriter().write(e.toString());
				response.getWriter().close();
				log.error(e, e);
				return false;
			}

			// 添加服务器路径
			String host = request.getServerName();
			if (request.getServerPort() != 80) {
				host += ":" + request.getServerPort();
			}
			String basePath = request.getScheme() + "://" + host + request.getContextPath() + "/";
			blogConfig.getConfigMap().put("staticServePath", basePath);
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mv) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {

	}

	private void importConfig() throws IOException {

		InputStream is = this.getClass().getResourceAsStream("/initdata.json");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int count;
		while ((count = is.read(buf, 0, 1024)) != -1) {
			bos.write(buf, 0, count);
		}
		is.close();

		List<Config> initConfig = JSON.parseArray(new String(bos.toByteArray(), Constant.CHARSET),
				Config.class);
		bos.close();
		blogConfig.save(initConfig);
		blogConfig.UpdateConfigMap();

	}
}
