package org.cweili.wray.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.service.ArticleService;
import org.cweili.wray.service.CategoryService;
import org.cweili.wray.service.ConfigService;
import org.cweili.wray.service.LinkService;
import org.cweili.wray.service.TagService;
import org.cweili.wray.util.Function;
import org.springframework.beans.factory.annotation.Autowired;
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

	protected boolean isAdminPanel(HttpServletRequest request) {
		return Function.requestScript(request).length() > 7 ? Function.requestScript(request)
				.substring(0, 7).equals("/admin/") : false;
	}

}
