package org.cweili.wray.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.service.ArticleService;
import org.cweili.wray.service.CategoryService;
import org.cweili.wray.service.ConfigService;
import org.cweili.wray.service.LinkService;
import org.cweili.wray.service.TagService;
import org.cweili.wray.util.BlogView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:14:17
 * 
 */
public abstract class BaseInterceptor extends HandlerInterceptorAdapter {

	protected Log log = LogFactory.getLog(BaseInterceptor.class);

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

	public boolean p1reHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		super.preHandle(request, response, handler);
		return preHandle(new ServletWebRequest(request, response), handler);
	}

	public abstract boolean preHandle(ServletWebRequest request, Object handler) throws Exception;

	public void po1stHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		postHandle(new ServletWebRequest(request, response), handler,
				new BlogView(modelAndView.getView(), modelAndView.getModel()));
	}

	public abstract void postHandle(ServletWebRequest request, Object handler, BlogView v)
			throws Exception;

	public void aft1erCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

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

}
