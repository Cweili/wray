package org.cweili.wray.web;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cweili.feed.atom.Atom;
import org.cweili.feed.rss.RSS;
import org.cweili.wray.entity.Article;
import org.cweili.wray.entity.Item;
import org.cweili.wray.util.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Feed Controller
 * 
 * @author Cweili
 * @version 2012-8-16 下午5:36:47
 * 
 */
@Controller
public final class FeedController extends BaseController {

	@RequestMapping(value = "/feed", produces = "text/xml;charset=UTF-8")
	public @ResponseBody
	String rss() {
		if (null != feedService.getRss()) {
			return feedService.getRss();
		} else {
			String path = blogConfig.get("staticServePath");
			String author = blogConfig.get("adminNick");
			RSS rss = new RSS(blogConfig.get("blogTitle"), path, path + "atom",
					blogConfig.get("blogSubtitle"), Constant.GENERATOR, new Date());

			int feedSize = blogConfig.getInt("feedSize");
			if (feedSize > 0) {
				List<Article> articles = articleService.findByTypeStatus(Article.TYPE_ARTICLE,
						Article.STAT_PUBLISHED, 1, feedSize).getContent();
				for (Article article : articles) {
					rss.addItem(article.getTitle(), path + "article/" + article.getPermalink(),
							author, article.getContent(), article.getCreateTime(),
							getCategories(article));
				}
			}

			synchronized (feedService) {
				feedService.setRss(rss);
			}
			return feedService.getRss();
		}
	}

	@RequestMapping(value = "/atom", produces = "text/xml;charset=UTF-8")
	public @ResponseBody
	String atom() {
		if (null != feedService.getAtom()) {
			return feedService.getAtom();
		} else {
			String path = blogConfig.get("staticServePath");
			String author = blogConfig.get("adminNick");
			Atom atom = new Atom(blogConfig.get("blogTitle"), blogConfig.get("blogSubtitle"),
					blogConfig.get("staticServePath"), blogConfig.get("adminNick"),
					blogConfig.get("staticServePath") + "atom", Constant.GENERATOR, new Date());

			int feedSize = blogConfig.getInt("feedSize");
			if (feedSize > 0) {
				List<Article> articles = articleService.findByTypeStatus(Article.TYPE_ARTICLE,
						Article.STAT_PUBLISHED, 1, feedSize).getContent();
				for (Article article : articles) {
					atom.addEntry(article.getTitle(), path + "article/" + article.getPermalink(),
							author, article.getContent(), article.getCreateTime(),
							getCategories(article));
				}
			}

			synchronized (feedService) {
				feedService.setAtom(atom);
			}
			return feedService.getAtom();
		}
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
