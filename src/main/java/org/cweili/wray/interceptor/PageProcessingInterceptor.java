package org.cweili.wray.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.springframework.web.servlet.ModelAndView;

/**
 * Do Page Processing
 * 
 * @author cweili
 * @version 2012-8-16 下午5:15:25
 * 
 */
public class PageProcessingInterceptor extends BaseInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mv) throws Exception {
		// Map<String, Object> m = mv.getModel();
		// String title = blogConfig.get("BLOG_TITLE");
		// if(!m.get("title").equals("")) {
		// title = m.get("title") + " - " + title;
		// }
		if (null != mv) {
			String reqs = Function.requestScript(request);
			boolean isAdminPanel = Function.requestScript(request).substring(0, 7)
					.equals("/admin-");

			mv.addAllObjects(blogConfig.getConfigMap());

			String host = request.getServerName();
			if (request.getServerPort() != 80) {
				host += ":" + request.getServerPort();
			}
			String basePath = request.getScheme() + "://" + host + request.getContextPath() + "/";
			mv.addObject("staticServePath", basePath);
			blogConfig.getConfigMap().put("staticServePath", basePath);
			mv.addObject("year", Constant.CURRENT_YEAR);
			mv.addObject("wrayVersion", Constant.WRAY_VERSION);

			// Not Admin and Admin view
			if (!isAdminPanel) {

				List<Article> pageNavigations = articleService.findByTypeStatus(Article.TYPE_PAGE,
						Article.STAT_PUBLISHED, 1, 0);
				mv.addObject("pageNavigations", pageNavigations);
				mv.addObject("mostCommentArticles", articleService.getTopCommentArticles(Integer
						.valueOf(blogConfig.get("topCommentArticlesSize"))));
				mv.addObject("mostViewCountArticles", articleService.getTopHitsArticles(Integer
						.valueOf(blogConfig.get("topHitsArticlesSize"))));

				mv.addObject("categories", categoryService.getCategories());
				int mostUsedTagsSize = 20;
				try {
					mostUsedTagsSize = Integer.valueOf(blogConfig.get("mostUsedTagsSize"));
				} catch (Exception e) {
					log.error(e.toString());
				}
				mv.addObject(
						"mostUsedTags",
						tagService.getTags().subList(
								0,
								tagService.getTags().size() >= mostUsedTagsSize ? mostUsedTagsSize
										: tagService.getTags().size()));
				mv.addObject("links", linkService.getLinks());
			} else {
				mv.addObject("adminListSize", Constant.ADMIN_LIST_SIZE);
				int endIndex = reqs.indexOf(".", 7) > 0 ? reqs.indexOf(".", 7) : reqs.indexOf("/",
						7) > 0 ? reqs.indexOf("/", 7) : reqs.length();
				mv.addObject("adminAction", reqs.substring(7, endIndex));
				log.info("Admin Action: " + reqs.substring(7, endIndex));
			}

			// Skin Dir
			String skinDir = isAdminPanel ? "admin" : blogConfig.get("skinDir");
			mv.addObject("skinDir", Constant.SKIN_PATH + skinDir + "/");
			mv.setViewName(skinDir + "/" + mv.getViewName() + Constant.SKIN_EXT);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {

	}

}
