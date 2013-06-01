package org.cweili.wray.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.entity.Config;
import org.cweili.wray.util.ChineseSegment;
import org.cweili.wray.util.Constant;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
 * 初始化拦截器
 * 
 * @author Cweili
 * @version 2012-8-16 下午5:14:05
 * 
 */
public class InitInterceptor extends BaseInterceptor {

	private static boolean unInited = true;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {

		if (unInited) {

			// 初始化分词
			new Thread() {

				@Override
				public void run() {
					super.run();
					ChineseSegment.segmentToSet("");
				}

			}.start();

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
			}

			unInited = false;
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

	/**
	 * 导入默认配置
	 * 
	 * @throws IOException
	 */
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
