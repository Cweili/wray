package org.cweili.wray.web;

import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Function;
import org.cweili.wray.util.NotFoundException;
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
public final class ArticleController extends BaseController {

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
		v.add("commentList", commentService.findByArticle(article));
		article.setHits(article.getHits() + 1);
		articleService.updateHits(article);
		return v;
	}

	@RequestMapping("/page/{permalink}")
	public BlogView page(@PathVariable("permalink") String permalink) throws NotFoundException {
		permalink = Function.urlDecode(permalink);
		Article page = articleService.findByPermalink(permalink, Article.TYPE_PAGE);
		if (null == page) {
			throw new NotFoundException();
		}
		BlogView v = new BlogView("page");
		v.add("article", page);
		v.add("commentList", commentService.findByArticle(page));
		return v;
	}

	@RequestMapping("/")
	public BlogView index() {
		return getIndexView(1);
	}

	@RequestMapping("/page-{page}")
	public BlogView indexPage(@PathVariable("page") String page) {
		return getIndexView(Function.minimumPositiveInteger(page));
	}

	private BlogView getIndexView(int page) {
		BlogView v = new BlogView("index");

		List<Article> articles = articleService.findByTypeStatus(Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED, page, blogConfig.getInt("limit"));
		v.add("articles", articles);

		addPaginator(v,
				articleService.countByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_PUBLISHED),
				page);

		return v;
	}

}
