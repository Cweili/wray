package org.cweili.wray.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * Authentication Interceptor
 * 
 * @author cweili
 * @version 2012-8-16 下午5:14:05
 * 
 */
public class AuthenticationInterceptor extends BaseInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// Session 登录
		if (null != WebUtils.getSessionAttribute(request, Constant.AUTHORITY_TOKEN)) {
			return true;
		}

		// Cookie 登录
		String key = findCookie(request, Constant.AUTHORITY_TOKEN);
		if (null != key) {
			String[] keys = key.split("@");
			if (keys.length == 2
					&& keys[0].equals(Function.authorityToken(keys[1], blogConfig.get("adminName"),
							blogConfig.get("adminPwd")))) {
				request.getSession().setAttribute(Constant.AUTHORITY_TOKEN, keys[0]);
				request.getSession().setAttribute(Constant.AUTHORITY_TIME, keys[1]);
				log.info(blogConfig.get("adminName") + " login by cookie successful.");
				return true;
			}
		}

		response.sendRedirect("admin-login#" + requestScript(request).substring(7));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mv) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {

	}

}
