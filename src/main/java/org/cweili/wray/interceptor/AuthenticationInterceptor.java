package org.cweili.wray.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * 身份验证拦截器
 * 
 * @author Cweili
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
		String token = findCookie(request, Constant.AUTHORITY_TOKEN);
		if (null != token) {
			String[] tokens = token.split("@");
			if (tokens.length == 2
					&& tokens[0].equals(Function.authorityToken(tokens[1],
							blogConfig.get("adminName"), blogConfig.get("adminPwd")))) {
				request.getSession().setAttribute(Constant.AUTHORITY_TOKEN, tokens[0]);
				request.getSession().setAttribute(Constant.AUTHORITY_TIME, tokens[1]);
				log.info(blogConfig.get("adminName") + " login by cookie successful.");
				return true;
			}
			log.error("Cookie login failed! Token: " + token);
		}

		response.sendRedirect("admin-login#" + StringUtils.substring(requestScript(request), 7));
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
