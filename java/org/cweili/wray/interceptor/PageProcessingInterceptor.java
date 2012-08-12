package org.cweili.wray.interceptor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.Function;
import org.springframework.web.servlet.ModelAndView;

public class PageProcessingInterceptor extends BaseInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
//		Map<String, Object> m = mv.getModel();
//		String title = blogConfig.get("BLOG_TITLE");
//		if(!m.get("title").equals("")) {
//			title = m.get("title") + " - " + title;
//		}
		String requestURI = request.getRequestURI().replaceFirst(request.getContextPath(), "").toLowerCase();
		boolean isAdminPanel = requestURI.substring(0, 7).equals("/admin-");
		
		mv.addAllObjects(blogConfig.getConfigMap());
		
		String host = request.getServerName();
		if(request.getServerPort() != 80) {
			host += ":" + request.getServerPort();
		}
		String basePath = request.getScheme() + "://" + host + request.getContextPath()+"/";
		mv.addObject("blogHost", basePath);
		mv.addObject("staticServePath", basePath);
		mv.addObject("year", Function.year());
		mv.addObject("wrayVersion", "0.0.1");
		
//		log.info("Page Processing: " + title);
		
		if(!isAdminPanel) {
		
			List<Article> pageNavigations = articleService.getArticlesByTypeStatus(Article.TYPE_PAGE, Article.STAT_PUBLISHED, 1, 10);
			mv.addObject("pageNavigations", pageNavigations);
			mv.addObject("mostCommentArticles", articleService.getTopCommentArticles(Integer.valueOf(blogConfig.get("topCommentArticlesSize"))));
			mv.addObject("mostViewCountArticles", articleService.getTopHitsArticles(Integer.valueOf(blogConfig.get("topHitsArticlesSize"))));
		
		}
		
		// Admin
		if(isAdminPanel) {
			int endIndex = requestURI.indexOf(".", 7) > 0 ? requestURI.indexOf(".", 7) : requestURI.indexOf("/", 7) > 0 ? requestURI.indexOf("/", 7) : requestURI.length();
			mv.addObject("adminAction", requestURI.substring(7, endIndex));
			log.info("Admin Action: " + requestURI.substring(7, endIndex));
		}
		
		// Skin Dir
		String skinDir = (isAdminPanel ? "admin" : blogConfig.get("skinDir")) + "/";
		mv.addObject("skinDir", "skin/" + skinDir);
		mv.setViewName(skinDir + mv.getViewName());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}



}
