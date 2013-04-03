package org.cweili.wray.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.BlogView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:32
 * 
 */
@Controller
@Scope("prototype")
public final class IndexController extends BaseController {

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	@RequestMapping("/page-{page}/")
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String page) {
		BlogView v = new BlogView("index");
		int p = 1;
		try {
			p = Integer.valueOf(page);
		} catch (Exception e) {
			log.error(e.toString());
		}

		// for(int i = 0; i < 10000; ++i) {
		// articleService.save(new Article(0, "标题" + i, "permalink" + i,
		// "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容"
		// + i,
		// "标签1,标签2,标签3,标签4", new Date(), Article.STAT_PUBLISHED, 0, 0,
		// Article.COMMENT_ON, Article.TYPE_ARTICLE));
		// }

		List<Article> articles = articleService.getArticlesByTypeStatus(Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED, p, Integer.valueOf(blogConfig.get("limit")));
		v.add("articles", articles);

		addPaginator(v,
				articleService.getCountByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_PUBLISHED),
				p);

		v.add("path", "");

		return v;
	}

}