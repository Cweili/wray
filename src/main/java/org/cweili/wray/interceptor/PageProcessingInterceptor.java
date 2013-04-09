package org.cweili.wray.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
import org.springframework.web.context.request.ServletWebRequest;
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
		String host = request.getServerName();
		if (request.getServerPort() != 80) {
			host += ":" + request.getServerPort();
		}
		String basePath = request.getScheme() + "://" + host + request.getContextPath() + "/";
		blogConfig.getConfigMap().put("staticServePath", basePath);
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
			String reqs = requestScript(request);
			boolean isAdminPanel = isAdminPanel(request);

			mv.addAllObjects(blogConfig.getConfigMap());

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
				String adminAction = reqs.substring(7);
				mv.addObject("adminAction", adminAction);
				log.info("Admin Action: " + adminAction);
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

	@Override
	public boolean preHandle(ServletWebRequest request, Object handler) throws Exception {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void postHandle(ServletWebRequest request, Object handler, BlogView v) throws Exception {
		// TODO 自动生成的方法存根

	}

}
