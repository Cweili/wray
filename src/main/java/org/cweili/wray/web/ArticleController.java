package org.cweili.wray.web;

import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Function;
import org.cweili.wray.util.NotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:09
 * 
 */
@Controller
@Scope("prototype")
public final class ArticleController extends BaseController {

	/**
	 * Article Handler
	 * 
	 * @param request
	 * @param response
	 * @param permalink
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping("/article/{permalink}")
	public BlogView article(@PathVariable("permalink") String permalink) throws NotFoundException {

		permalink = Function.urlDecode(permalink);
		Article article = articleService.findByPermalink(permalink, Article.TYPE_ARTICLE);
		if (null == article) {
			throw new NotFoundException();
		}
		List<Item> relatedCats = categoryService.findByArticle(article);
		BlogView v = new BlogView("article");
		v.add("article", article);
		v.add("relatedCats", relatedCats);
		v.add("commentList", commentService.getCommentsByArticle(article));
		article.setHits(article.getHits() + 1);
		articleService.updateHits(article);
		return v;
	}

	/**
	 * Page Handler
	 * 
	 * @param request
	 * @param response
	 * @param permalink
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping("/page/{permalink}")
	public BlogView page(@PathVariable("permalink") String permalink) throws NotFoundException {
		permalink = Function.urlDecode(permalink);
		Article page = articleService.findByPermalink(permalink, Article.TYPE_PAGE);
		if (null == page) {
			throw new NotFoundException();
		}
		BlogView v = new BlogView("page");
		v.add("article", page);
		return v;
	}

	@RequestMapping("/page-{page}")
	public BlogView index(@PathVariable("page") String page) {
		BlogView v = new BlogView("index");
		int p = 1;
		try {
			p = Integer.valueOf(page);
		} catch (Exception e) {
			log.error(e.toString());
		}

		List<Article> articles = articleService.findByTypeStatus(Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED, p, Integer.valueOf(blogConfig.get("limit")));
		v.add("articles", articles);

		addPaginator(v,
				articleService.countByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_PUBLISHED), p);

		v.add("path", "");

		return v;
	}

}
