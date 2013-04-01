package org.cweili.wray.web;

import java.sql.SQLException;
import java.util.List;

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
 * @version 2012-8-16 下午5:36:09
 * 
 */
@Controller
@Scope("prototype")
public final class ArticleController extends BaseController {

	@Override
	@RequestMapping("/article/{permalink}/*")
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String permalink) {

		permalink = Function.urlDecode(permalink);
		BlogView v = new BlogView("article");
		Article article = articleService.getArticleByPermalink(permalink);
		List<Item> relatedCats = categoryService.getCategoriesByArticle(article);
		v.add("article", article);
		v.add("relatedCats", relatedCats);
		v.add("commentList", commentService.getCommentsByArticle(article));
		article.setHits(article.getHits() + 1);
		try {
			articleService.updateHits(article);
		} catch (SQLException e) {
			log.error(e.toString());
		}
		return v;
	}

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
