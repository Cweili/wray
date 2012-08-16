package org.cweili.wray.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.service.ArticleService;
import org.cweili.wray.service.CategoryService;
import org.cweili.wray.service.ConfigService;
import org.cweili.wray.service.LinkService;
import org.cweili.wray.util.BlogView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:16
 *
 */
public abstract class BaseController extends MultiActionController {
	
	protected Log log = LogFactory.getLog(BaseController.class);
	
	@Autowired
	protected ConfigService blogConfig;
	
	@Autowired
	protected ArticleService articleService;
	
	@Autowired
	protected CategoryService categoryService;
	
	@Autowired
	protected LinkService linkService;
	
	public abstract BlogView index(HttpServletRequest request,
			HttpServletResponse response);
	
	public abstract BlogView index(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String str);
}
