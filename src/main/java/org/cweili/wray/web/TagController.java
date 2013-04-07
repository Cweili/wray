package org.cweili.wray.web;

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
 * @version 2012-8-16 下午5:36:47
 * 
 */
@Controller
@Scope("prototype")
public final class TagController extends BaseController {

	@RequestMapping("/tag/{permalink}/")
	public BlogView permalink(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String permalink) throws NotFoundException {

		BlogView v = new BlogView("articles");
		v.add("path", "tag/" + permalink + "/");
		permalink = Function.urlDecode(permalink);
		Item item = tagService.findByName(permalink);
		if (null == item) {
			throw new NotFoundException();
		}
		v.add("item", item);
		v.add("articles", articleService.findByRelationship(item.getItemId(), Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED, 1, Integer.valueOf(blogConfig.get("limit"))));

		addPaginator(v, articleService.countByRelationship(item.getItemId(), Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED), 1);

		return v;
	}

	@RequestMapping("/tag/{permalink}/page-{page}/")
	public BlogView permalink(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String permalink, @PathVariable String page) throws NotFoundException {
		int p = 1;
		try {
			p = Integer.valueOf(page);
		} catch (Exception e) {
			log.error(e.toString());
		}
		BlogView v = new BlogView("articles");
		v.add("path", "tag/" + permalink + "/");
		permalink = Function.urlDecode(permalink);
		Item item = tagService.findByName(permalink);
		if (null == item) {
			throw new NotFoundException();
		}
		v.add("item", item);
		v.add("articles", articleService.findByRelationship(item.getItemId(), Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED, p, Integer.valueOf(blogConfig.get("limit"))));

		addPaginator(v, articleService.countByRelationship(item.getItemId(), Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED), p);

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
