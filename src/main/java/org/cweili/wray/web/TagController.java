package org.cweili.wray.web;

import java.util.List;

import org.cweili.wray.domain.BlogView;
import org.cweili.wray.domain.Page;
import org.cweili.wray.domain.dto.Article;
import org.cweili.wray.domain.dto.Item;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.util.NotFoundException;
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
public final class TagController extends BaseController {

	@RequestMapping("/tag/{permalink}")
	public BlogView permalink(@PathVariable("permalink") String permalink) throws NotFoundException {
		return getTagView(permalink, 1);
	}

	@RequestMapping("/tag/{permalink}/page-{page:[\\d]+}")
	public BlogView permalink(@PathVariable("permalink") String permalink,
			@PathVariable("page") int page) throws NotFoundException {
		page = page > 0 ? page : 1;
		return getTagView(permalink, page);
	}

	@RequestMapping("/tags")
	public BlogView tags() {
		List<Item> tags = tagService.getTags(0, Constant.MAX_PAGE);
		BlogView v = new BlogView("tags");
		v.add("tags", tags);
		return v;
	}

	private BlogView getTagView(String permalink, int page) throws NotFoundException {
		BlogView v = new BlogView("item-articles");
		v.add("path", "tag/" + permalink);
		permalink = Function.urlDecode(permalink);
		Item item = tagService.findByName(permalink);
		if (null == item) {
			throw new NotFoundException();
		}
		v.add("item", item);

		Page<Article> articles = articleService.findByRelationship(item.getItemId(),
				Article.TYPE_ARTICLE, Article.STAT_PUBLISHED, page, blogConfig.getInt("pageSize"));
		v.add("articles", articles.getContent());

		addPaginator(v, articles);

		v.add("path", blogConfig.get("staticServePath") + "tag/" + Function.urlEncode(permalink)
				+ "/");

		return v;
	}

}
