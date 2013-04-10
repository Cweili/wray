package org.cweili.wray.web.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.CutString;
import org.cweili.wray.util.Function;
import org.cweili.wray.web.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:57
 * 
 */
@Controller
@Scope("prototype")
public final class AdminCommentController extends BaseController {

	@RequestMapping("/admin-comment")
	public BlogView articleList(WebRequest request) {
		BlogView v = new BlogView("comment-list");
		byte stat = Article.STAT_PUBLISHED;
		String actionName = "评论管理";
		int page = 1;
		try {
			page = Integer.valueOf(request.getParameter("page") == null ? "1" : request
					.getParameter("page"));
		} catch (Exception e) {
			log.error(e.toString());
		}
		v.add("actionName", actionName);
		v.add("comments", commentService.getComments(page, Constant.ADMIN_LIST_SIZE));

		addPaginator(v, commentService.count(), page, Constant.ADMIN_LIST_SIZE);
		return v;
	}

	@RequestMapping(value = "/admin-comment-edit-{commentid}", method = RequestMethod.GET)
	public BlogView editGet(@PathVariable("commentid") String commentid) {
		BlogView v = new BlogView("article-edit");
		v.add("actionName", "编辑文章");
		v.add("commentId", commentid);
		Article article = articleService.findById(commentid);

		if (article != null) {
			v.add("title", article.getTitle());
			v.add("permalink", article.getPermalink());
			v.add("tag", article.getTag());
			v.add("content", article.getContent());
			v.add("commentStatus", article.getCommentStatus());
			v.add("stat", article.getStat());
			v.add("err", "");
		} else {
			v.add("commentStatus", Article.COMMENT_ON);
			v.add("stat", Article.STAT_PUBLISHED);
			v.add("err", "文章未找到");
		}
		return v;
	}

	@RequestMapping(value = "/admin-comment-edit-{articleid}", method = RequestMethod.POST)
	public @ResponseBody
	String editPost(WebRequest request, @PathVariable("articleid") String articleid) {
		Article article = articleService.findById(articleid);
		article = getArticle(request, article);
		if (null == articleService.save(article)) {
			return Constant.SUBMIT_FAILED;
		}
		categoryService.saveRelationshipWithArticle(article, getRelatedItems(request));
		return Constant.SUBMIT_SUCCESS;
	}

	@RequestMapping(value = "/admin-comment-block", method = RequestMethod.POST)
	public BlogView del(WebRequest request) {
		BlogView v = new BlogView("msg");
		v.add("err", "succ");
		v.add("msg", "文章删除成功");
		v.add("succ", "恭喜您，您选中的文章已成功移入回收站。");
		v.add("redirect", "admin-article-" + "?page=" + request.getParameter("page"));

		List<String> ids = new ArrayList<String>();
		if (request.getParameterValues("id") != null) {
			Collections.addAll(ids, request.getParameterValues("id"));
		}

		return v;
	}

	private Article getArticle(WebRequest request, Article ori) {
		String title = request.getParameter("title") != null ? request.getParameter("title") : "";
		String permalink = request.getParameter("permalink") != null ? request
				.getParameter("permalink") : "";
		String tag = request.getParameter("tag") != null ? request.getParameter("tag") : "";
		String content = request.getParameter("content") != null ? request.getParameter("content")
				: "";
		byte commentStatus = Article.COMMENT_OFF;
		if (request.getParameterValues("commentStatus") != null
				&& request.getParameterValues("commentStatus").length > 0) {
			commentStatus = Article.COMMENT_ON;
		}
		String s = request.getParameter("stat") != null ? request.getParameter("stat") : "";

		String id = Function.generateId();

		title = Function.trimAndStripTags(title);
		title = "".equals(title) ? Function.timeString() : title;
		permalink = Function.permalink(permalink);
		permalink = "".equals(permalink) ? Function.permalink(title) : permalink;
		tag = Function.stripTags(tag.replaceAll(" ", ",").replaceAll("，", ","));
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
						addItem = new Item(Function.generateId(), tagStr, "", "", 0, (byte) 0,
								Item.TYPE_TAG, 0L, Item.STAT_ON);
						tagService.save(addItem);
						relatedItems.add(addItem);
					}
				}
			}
		}

		return relatedItems;
	}

}
