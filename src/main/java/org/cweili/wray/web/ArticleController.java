package org.cweili.wray.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@RequestMapping("/article/{permalink}/*")
	public BlogView permalink(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String permalink) throws Exception {

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

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String str) {
		return null;
	}

}
