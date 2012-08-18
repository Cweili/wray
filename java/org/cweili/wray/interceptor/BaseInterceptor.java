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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:14:17
 *
 */
public abstract class BaseInterceptor implements HandlerInterceptor {
	
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
	
	@Override
	public abstract boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
	
	@Override
	public abstract void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception;

	@Override
	public abstract void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception;

}
