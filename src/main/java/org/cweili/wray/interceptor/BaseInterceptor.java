package org.cweili.wray.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.service.ArticleService;
import org.cweili.wray.service.CategoryService;
import org.cweili.wray.service.ConfigService;
import org.cweili.wray.service.LinkService;
import org.cweili.wray.service.TagService;
import org.cweili.wray.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:14:17
 * 
 */
public abstract class BaseInterceptor implements HandlerInterceptor {

	protected static final Log log = LogFactory.getLog(BaseInterceptor.class);

	@Autowired
	protected ConfigService blogConfig;

	@Autowired
	protected ArticleService articleService;

	@Autowired
	protected CategoryService categoryService;

	@Autowired
	protected TagService tagService;

	@Autowired
	protected LinkService linkService;

	/**
	 * 返回是否在管理员面板状态
	 * 
	 * @param request
	 * @return
	 */
	protected boolean isAdminPanel(HttpServletRequest request) {
		return requestScript(request).length() > 7 ? requestScript(request).substring(0, 7).equals(
				"/admin-") : false;
	}

	/**
	 * 取得请求脚本名
	 * 
	 * @param request
	 * @return
	 */
	protected String requestScript(HttpServletRequest request) {
		return request.getRequestURI().replaceFirst(request.getContextPath(), "");
	}

	protected String findCookie(HttpServletRequest request, String find) {
		if (null != request.getCookies()) {
			for (Cookie cookie : request.getCookies()) {
				if (find.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * @param request
	 * @return
	 */
	protected String getAuthoritySession(HttpServletRequest request) {
		if (null != request.getSession(false)
				&& null != request.getSession().getAttribute(Constant.AUTHORITY_KEY)) {
			return (String) request.getSession().getAttribute(Constant.AUTHORITY_KEY);
		}
		return null;
	}

}
