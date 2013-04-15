package org.cweili.wray.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.springframework.web.servlet.ModelAndView;

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
		if (null != getAuthoritySession(request)) {
			return true;
		}

		// Cookie 登录
		String key = findCookie(request, Constant.AUTHORITY_KEY);
		if (null != key) {
			String[] keys = key.split("@");
			if (keys.length == 2
					&& keys[0].equals(Function.authorityKey(keys[1], blogConfig.get("adminName"),
							blogConfig.get("adminPwd")))) {
				request.getSession().setAttribute(Constant.AUTHORITY_KEY, keys[0]);
				log.info(blogConfig.get("adminName") + " login by cookie successful.");
				return true;
			}
		}

		response.sendRedirect("admin-login");
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
