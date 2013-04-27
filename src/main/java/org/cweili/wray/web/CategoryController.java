package org.cweili.wray.web;

import org.cweili.wray.domain.BlogView;
import org.cweili.wray.domain.Page;
import org.cweili.wray.domain.dto.Article;
import org.cweili.wray.domain.dto.Item;
import org.cweili.wray.util.Function;
import org.cweili.wray.util.NotFoundException;
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
@RequestMapping("/category")
public final class CategoryController extends BaseController {

	@RequestMapping("/{permalink}")
	public BlogView permalink(@PathVariable("permalink") String permalink) throws NotFoundException {
		return getCategoryView(permalink, 1);
	}

	@RequestMapping("/{permalink}/page-{page}")
	public BlogView permalink(@PathVariable("permalink") String permalink,
			@PathVariable("page") String page) throws NotFoundException {
		return getCategoryView(permalink, Function.minimumPositiveInteger(page));
	}

	private BlogView getCategoryView(String permalink, int page) throws NotFoundException {
		BlogView v = new BlogView("item-articles");
		v.add("path", "category/" + permalink);
		permalink = Function.urlDecode(permalink);

		Item item = categoryService.findByPermalink(permalink);
		if (null == item) {
			throw new NotFoundException();
		}
		v.add("item", item);

		Page<Article> articles = articleService.findByRelationship(item.getItemId(),
				Article.TYPE_ARTICLE, Article.STAT_PUBLISHED, page, blogConfig.getInt("limit"));
		v.add("articles", articles.getContent());

		addPaginator(v, articles);
		v.add("path",
				blogConfig.get("staticServePath") + "category/" + Function.urlEncode(permalink)
						+ "/");

		return v;
	}
}
