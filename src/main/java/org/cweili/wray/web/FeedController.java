package org.cweili.wray.web;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cweili.feed.atom.Atom;
import org.cweili.feed.rss.RSS;
import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;
import org.cweili.wray.util.Constant;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:47
 * 
 */
@Controller
@Scope("prototype")
public final class FeedController extends BaseController {

	@RequestMapping("/feed")
	public @ResponseBody
	String rss() {
		String path = blogConfig.get("staticServePath");
		String author = blogConfig.get("adminNick");
		RSS rss = new RSS(blogConfig.get("blogTitle"), path, path + "atom",
				blogConfig.get("blogSubtitle"), Constant.GENERATOR, new Date());
		List<Article> articles = articleService.findByTypeStatus(Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED, 1, 10);
		for (Article article : articles) {
			rss.addItem(article.getTitle(), path + "article/" + article.getPermalink(), author,
					article.getContent(), article.getCreateTime(), getCategories(article));
		}
		return rss.toString();
	}

	@RequestMapping("/atom")
	public @ResponseBody
	String atom() {
		String path = blogConfig.get("staticServePath");
		String author = blogConfig.get("adminNick");
		Atom atom = new Atom(blogConfig.get("blogTitle"), blogConfig.get("blogSubtitle"),
				blogConfig.get("staticServePath"), blogConfig.get("adminNick"),
				blogConfig.get("staticServePath") + "atom", Constant.GENERATOR, new Date());
		List<Article> articles = articleService.findByTypeStatus(Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED, 1, 10);
		for (Article article : articles) {
			atom.addEntry(article.getTitle(), path + "article/" + article.getPermalink(), author,
					article.getContent(), article.getCreateTime(), getCategories(article));
		}
		return atom.toString();
	}

	private String[] getCategories(Article article) {
		List<Item> categories = categoryService.findByArticle(article);
		String[] tags = StringUtils.split(article.getTag(), ',');
		String[] categoryStrings = Arrays.copyOf(tags, categories.size() + tags.length);

		for (int i = tags.length; i < categories.size() + tags.length; ++i) {
			categoryStrings[i] = categories.get(i - tags.length).getItemName();
		}

		return categoryStrings;
	}
}
