package org.cweili.wray.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;
import org.cweili.wray.util.BlogView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:47
 * 
 */
@Controller
@Scope("prototype")
public final class TagController extends BaseController {

	@Override
	@RequestMapping("/tag/{permalink}/")
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String permalink) {
		BlogView v = new BlogView("articles");
//		try {
//			permalink = URLDecoder.decode(permalink, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(permalink);
		Item item = tagService.getTagByName(permalink);
		v.add("item", item);
		v.add("articles", articleService.getArticlesByRelationship(item.getItemId(),
				Article.TYPE_ARTICLE, Article.STAT_PUBLISHED, 1,
				Integer.valueOf(blogConfig.get("limit"))));

		addPaginator(v, articleService.getCountByrelationship(item.getItemId(),
				Article.TYPE_ARTICLE, Article.STAT_PUBLISHED), 1);

		return v;
	}

	@RequestMapping("/tag/{permalink}/page-{page}/")
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String permalink, @PathVariable String page) {
		int p = 1;
		try {
			p = Integer.valueOf(page);
			permalink = URLDecoder.decode(permalink, "UTF-8");
		} catch (Exception e) {
			log.error(e.toString());
		}
		BlogView v = new BlogView("articles");
		Item item = tagService.getTagByName(permalink);
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
