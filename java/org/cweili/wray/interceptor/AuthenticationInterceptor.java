package org.cweili.wray.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		boolean isAdminPanel = Function.requestScript(request).substring(0, 7).equals("/admin-");
		// FIXME continue...
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mv) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub

	}

}
