package org.cweili.wray.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Function;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:21
 * 
 */
@Controller
@Scope("prototype")
public final class CategoryController extends BaseController {

	@Override
	@RequestMapping("/category/{permalink}/")
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String permalink) {
		permalink = Function.urlDecode(permalink);
		BlogView v = new BlogView("articles");
		Item item = categoryService.getCategoryByPermalink(permalink);
		v.add("item", item);
		v.add("articles", articleService.getArticlesByRelationship(item.getItemId(),
				Article.TYPE_ARTICLE, Article.STAT_PUBLISHED, 1,
				Integer.valueOf(blogConfig.get("limit"))));

		addPaginator(v, articleService.getCountByrelationship(item.getItemId(),
				Article.TYPE_ARTICLE, Article.STAT_PUBLISHED), 1);

		return v;
	}

	@RequestMapping("/category/{permalink}/page-{page}/")
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String permalink, @PathVariable String page) {
		int p = 1;
		try {
			p = Integer.valueOf(page);
		} catch (Exception e) {
			log.error(e.toString());
		}
		permalink = Function.urlDecode(permalink);
		BlogView v = new BlogView("articles");
		Item item = categoryService.getCategoryByPermalink(permalink);
		v.add("item", item);
		v.add("articles", articleService.getArticlesByRelationship(item.getItemId(),
				Article.TYPE_ARTICLE, Article.STAT_PUBLISHED, p,
				Integer.valueOf(blogConfig.get("limit"))));

		addPaginator(v, articleService.getCountByrelationship(item.getItemId(),
				Article.TYPE_ARTICLE, Article.STAT_PUBLISHED), p);

		return v;
	}

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
