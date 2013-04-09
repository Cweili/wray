package org.cweili.wray.interceptor;

import javax.servlet.http.Cookie;
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
public class LoginInterceptor extends BaseInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mv) throws Exception {
		// 保存登陆状态
		if (null != request.getAttribute(Constant.AUTHORITY_KEY)) {
			String time = Function.encode(Function.timestamp());
			String key = Function.authorityKey(time, blogConfig.get("adminName"),
					blogConfig.get("adminPwd"));
			request.getSession().setAttribute(Constant.AUTHORITY_KEY, key);
			if (request.getAttribute(Constant.AUTHORITY_KEY).equals(Constant.AUTHORITY_COOKIE)) {

				Cookie cookie = new Cookie(Constant.AUTHORITY_KEY, key + "|" + time);
				cookie.setDomain(request.getServerName());
				cookie.setPath(request.getContextPath() + "/");
				cookie.setMaxAge(31536000);
				response.addCookie(cookie);
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {

	}

}
