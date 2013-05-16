package org.cweili.wray.web.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.BlogView;
import org.cweili.wray.domain.Page;
import org.cweili.wray.domain.dto.Article;
import org.cweili.wray.domain.dto.Item;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.CutString;
import org.cweili.wray.util.Function;
import org.cweili.wray.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 文章管理 Controller
 * 
 * @author Cweili
 * @version 2012-8-16 下午5:36:57
 * 
 */
@Controller
public final class AdminArticleController extends BaseController {

	@RequestMapping("/admin-article-{status}")
	public BlogView articleList(WebRequest request, @PathVariable("status") String status) {
		BlogView v = new BlogView("article-list");
		byte stat = Article.STAT_PUBLISHED;
		String actionName = "已发布文章";
		int page = Function.minimumPositiveInteger(request.getParameter("page"));

		if ("draft".equals(status)) {
			stat = Article.STAT_DRAFT;
			actionName = "文章草稿";
		} else if ("recycle".equals(status)) {
			stat = Article.STAT_RECYCLE;
			actionName = "文章回收站";
		}
		v.add("actionName", actionName);

		Page<Article> articles = articleService.findByTypeStatus(Article.TYPE_ARTICLE, stat, page,
				Constant.ADMIN_LIST_SIZE);
		v.add("articles", articles.getContent());

		addPaginator(v, articles, Constant.ADMIN_LIST_SIZE);
		return v;
	}

	@RequestMapping(value = "/admin-article-add", method = RequestMethod.GET)
	public BlogView addGet() {
		BlogView v = new BlogView("article-edit");
		v.add("actionName", "新增文章");
		v.add("categories", categoryService.getCategories());
		v.add("article", new Article());
		return v;
	}

	@RequestMapping(value = "/admin-article-add", method = RequestMethod.POST)
	public @ResponseBody
	String addPost(WebRequest request) {
		try {
			Article article = getArticle(request, null);
			categoryService.saveRelationshipWithArticle(article, getRelatedItems(request));
			if (null != articleService.save(article)) {
				return "admin-article-edit-" + article.getArticleId();
			}
		} catch (Exception e) {
			return Constant.SUBMIT_FAILED;
		}
		return Constant.SUBMIT_FAILED;
	}

	@RequestMapping(value = "/admin-article-edit-{articleid}", method = RequestMethod.GET)
	public BlogView editGet(@PathVariable("articleid") String articleid) {
		BlogView v = new BlogView("article-edit");
		v.add("actionName", "编辑文章");
		v.add("articleId", articleid);
		Article article = articleService.findById(articleid);

		v.add("categories", categoryService.getSelectedCategories(article));

		if (null == article) {
			article = new Article();
		}
		v.add("article", article);
		return v;
	}

	@RequestMapping(value = "/admin-article-edit-{articleid}", method = RequestMethod.POST)
	public @ResponseBody
	String editPost(WebRequest request, @PathVariable("articleid") String articleid) {
		Article article = articleService.findById(articleid);
		try {
			article = getArticle(request, article);
			if (null == articleService.save(article)) {
				return Constant.SUBMIT_FAILED;
			}
		} catch (Exception e) {
			return Constant.SUBMIT_FAILED;
		}
		categoryService.saveRelationshipWithArticle(article, getRelatedItems(request));
		return Constant.SUBMIT_SUCCESS;
	}

	@RequestMapping(value = "/admin-article-delete-{status}", method = RequestMethod.POST)
	public BlogView del(WebRequest request, @PathVariable("status") String status) {
		BlogView v = new BlogView("msg");
		v.add("err", "succ");
		v.add("msg", "文章删除成功");
		v.add("succ", "恭喜您，您选中的文章已成功移入回收站。");
		v.add("redirect", "admin-article-" + status + "?page=" + request.getParameter("page"));

		byte type = Article.STAT_RECYCLE;
		if ("recycle".equals(status)) {
			type = Article.STAT_REMOVED;
			v.add("succ", "恭喜您，您选中的文章已成功删除。");
		}

		List<String> ids = new ArrayList<String>();
		if (request.getParameterValues("id") != null) {
			Collections.addAll(ids, request.getParameterValues("id"));
		}

		if (!articleService.updateStatus(ids, type)) {
			v.add("err", "数据库更新失败");
			v.add("msg", "文章删除失败");
		}
		return v;
	}

	private Article getArticle(WebRequest request, Article ori) {
		String title = StringUtils.trimToEmpty(request.getParameter("title"));
		String permalink = StringUtils.trimToEmpty(request.getParameter("permalink"));
		String tag = StringUtils.trimToEmpty(request.getParameter("tag"));
		String content = StringUtils.trimToEmpty(request.getParameter("content"));
		String s = StringUtils.trimToEmpty(request.getParameter("stat"));
		byte commentStatus = Article.COMMENT_OFF;
		if (request.getParameterValues("commentStatus") != null
				&& request.getParameterValues("commentStatus").length > 0) {
			commentStatus = Article.COMMENT_ON;
		}

		String id = Function.generateId();

		title = Function.escapeHtml(title);
		title = "".equals(title) ? Function.timeString() : title;
		permalink = Function.permalink(permalink);
		permalink = "".equals(permalink) ? Function.permalink(title) : permalink;
		tag = Function.stripTags(StringUtils.replaceEach(tag, new String[] { " ", "，" },
				new String[] { ",", "," }));
		StringBuilder tagSB = new StringBuilder("");
		for (String tagStr : tag.split(",")) {
			tagSB.append(CutString.substring(tagStr, 18));
			tagSB.append(',');
		}
		tagSB.deleteCharAt(tagSB.length() - 1);
		byte stat = Article.STAT_PUBLISHED;
		if ((Article.STAT_DRAFT + "").equals(s)) {
			stat = Article.STAT_DRAFT;
		} else if ((Article.STAT_RECYCLE + "").equals(s)) {
			stat = Article.STAT_RECYCLE;
		}
		if (ori != null) {
			ori.setTitle(title);
			ori.setPermalink(permalink);
			ori.setTag(tagSB.toString());
			ori.setContent(content);
			ori.setStat(stat);
			ori.setCommentStatus(commentStatus);
			return ori;
		}
		return new Article(id, title, permalink, content, tagSB.toString(), new Date(), stat, 0, 0,
				commentStatus, Article.TYPE_ARTICLE);
	}

	private List<Item> getRelatedItems(WebRequest request) {
		List<Item> relatedItems = new ArrayList<Item>();
		Item addItem;
		if (request.getParameterValues("category") != null) {
			for (String catStr : request.getParameterValues("category")) {
				addItem = categoryService.findById(catStr);
				if (null != addItem) {
					relatedItems.add(addItem);
				}
			}
		}
		if (request.getParameter("tag") != null) {
			String tag = request.getParameter("tag");
			if (!"".equals(tag)) {
				tag = Function.stripTags(tag.replaceAll(" ", ",").replaceAll("，", ","));
				for (String tagStr : tag.split(",")) {
					tagStr = CutString.substring(tagStr, 18);
					addItem = tagService.findByName(tagStr);
					if (null != addItem) {
						relatedItems.add(addItem);
					} else {
						String tagId = Function.generateId();
						addItem = new Item(tagId, tagStr, tagId, "", 0, (byte) 0, Item.TYPE_TAG,
								Item.STAT_ON);
						tagService.save(addItem);
						relatedItems.add(addItem);
					}
				}
			}
		}

		return relatedItems;
	}

}
