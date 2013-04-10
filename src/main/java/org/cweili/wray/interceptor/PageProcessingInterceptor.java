package org.cweili.wray.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.Constant;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面处理
 * 
 * @author cweili
 * @version 2012-8-16 下午5:15:25
 * 
 */
public class PageProcessingInterceptor extends BaseInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// 添加服务器路径
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

				// 添加页面
				List<Article> pageNavigations = articleService.findByTypeStatus(Article.TYPE_PAGE,
						Article.STAT_PUBLISHED, 1, 0);
				mv.addObject("pageNavigations", pageNavigations);

				// 添加侧栏
				int topCommentArticlesSize = 0;
				int topHitsArticlesSize = 0;
				int mostUsedTagsSize = 0;

				topCommentArticlesSize = Integer.valueOf(blogConfig.get("topCommentArticlesSize"));
				topHitsArticlesSize = Integer.valueOf(blogConfig.get("topHitsArticlesSize"));
				mostUsedTagsSize = Integer.valueOf(blogConfig.get("mostUsedTagsSize"));
				if (topCommentArticlesSize > 0) {
					mv.addObject("mostCommentArticles",
							articleService.getTopCommentArticles(topCommentArticlesSize));
				}
				if (topHitsArticlesSize > 0) {
					mv.addObject("mostViewCountArticles",
							articleService.getTopHitsArticles(topHitsArticlesSize));
				}
				if (mostUsedTagsSize > 0) {
					mv.addObject("mostUsedTags", tagService.getmostUsedTags(mostUsedTagsSize));
				}

				// 添加分类和链接
				mv.addObject("categories", categoryService.getCategories());
				mv.addObject("links", linkService.getLinks());
			} else {

				// 添加验证
				if (null != getAuthoritySession(request)) {
					mv.addObject("authority", getAuthoritySession(request));
				}

				// 添加列表大小设置
				mv.addObject("adminListSize", Constant.ADMIN_LIST_SIZE);

				// 添加管理员动作
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
}
