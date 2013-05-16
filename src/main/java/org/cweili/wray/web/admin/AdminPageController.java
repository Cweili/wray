package org.cweili.wray.web.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.BlogView;
import org.cweili.wray.domain.Page;
import org.cweili.wray.domain.dto.Article;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 页面管理 Controller
 * 
 * @author Cweili
 * @version 2012-8-16 下午5:39:25
 * 
 */
@Controller
public final class AdminPageController extends BaseController {

	@RequestMapping("/admin-page-{status}")
	public BlogView pageList(WebRequest request, @PathVariable("status") String status) {
		BlogView v = new BlogView("page-list");
		byte stat = Article.STAT_PUBLISHED;
		String actionName = "公开页面";
		int page = Function.minimumPositiveInteger(request.getParameter("page"));
		if ("private".equals(status)) {
			stat = Article.STAT_PRIVATE;
			actionName = "私密页面";
		} else if ("recycle".equals(status)) {
			stat = Article.STAT_RECYCLE;
			actionName = "页面回收站";
		}
		v.add("actionName", actionName);

		Page<Article> articles = articleService.findByTypeStatus(Article.TYPE_PAGE, stat, page,
				Constant.ADMIN_LIST_SIZE);
		v.add("articles", articles.getContent());

		addPaginator(v, articles, Constant.ADMIN_LIST_SIZE);
		return v;
	}

	@RequestMapping(value = "/admin-page-add", method = RequestMethod.GET)
	public BlogView addGet() {
		BlogView v = new BlogView("page-edit");
		v.add("actionName", "新增页面");
		v.add("article", new Article());
		return v;
	}

	@RequestMapping(value = "/admin-page-add", method = RequestMethod.POST)
	public @ResponseBody
	String addPost(WebRequest request) {
		Article article = getArticle(request, null);
		try {
			if (null == articleService.save(article)) {
				return Constant.SUBMIT_FAILED;
			}
		} catch (Exception e) {
			return Constant.SUBMIT_FAILED;
		}
		return "admin-page-edit-" + article.getArticleId();
	}

	@RequestMapping(value = "/admin-page-edit-{articleid}", method = RequestMethod.GET)
	public BlogView editGet(@PathVariable("articleid") String articleid) {
		BlogView v = new BlogView("page-edit");
		v.add("actionName", "编辑页面");
		v.add("articleId", articleid);
		Article article = articleService.findById(articleid);
		if (article == null) {
			article = new Article();
		}
		v.add("article", article);
		return v;
	}

	@RequestMapping(value = "/admin-page-edit-{articleid}", method = RequestMethod.POST)
	public @ResponseBody
	String editPost(WebRequest request, @PathVariable("articleid") String articleid) {
		Article article = articleService.findById(articleid);
		article = getArticle(request, article);
		try {
			if (null == articleService.save(article)) {
				return Constant.SUBMIT_FAILED;
			}
		} catch (Exception e) {
			return Constant.SUBMIT_FAILED;
		}
		return Constant.SUBMIT_SUCCESS;
	}

	@RequestMapping(value = "/admin-page-manage-{status}", method = RequestMethod.POST)
	public BlogView manage(WebRequest request, @PathVariable("status") String status) {
		BlogView v = new BlogView("msg");
		v.add("err", "succ");
		v.add("msg", "页面更新成功");
		v.add("succ", "恭喜您，您的页面排序已成功更新，选中页面已成功移入回收站。");
		v.add("redirect", "admin-page-" + status);

		byte type = Article.STAT_RECYCLE;
		if ("recycle".equals(status)) {
			type = Article.STAT_REMOVED;
			v.add("succ", "恭喜您，您的页面排序已成功更新，选中页面已成功删除。");
		}

		List<String> ids = new ArrayList<String>();
		if (request.getParameterValues("id") != null) {
			Collections.addAll(ids, request.getParameterValues("id"));
		}

		int page = Function.minimumPositiveInteger(request.getParameter("page"));

		byte stat = Article.STAT_PUBLISHED;
		if ("private".equals(status)) {
			stat = Article.STAT_PRIVATE;
		} else if ("recycle".equals(status)) {
			stat = Article.STAT_RECYCLE;
		}

		List<Article> articles = articleService.findByTypeStatus(Article.TYPE_PAGE, stat, page,
				Constant.ADMIN_LIST_SIZE).getContent();
		int order = 0;

		for (Article article : articles) {
			if (request.getParameter("order" + article.getArticleId()) != null) {
				order = Integer.valueOf(request.getParameter("order" + article.getArticleId()));
				if (article.getHit() != order) {
					article.setHit(order);
					articleService.updateHit(article);
				}
			}
		}

		if (!ids.isEmpty() && !articleService.updateStatus(ids, type)) {
			v.add("err", "数据库更新失败");
			v.add("msg", "页面删除失败");
		}
		return v;
	}

	private Article getArticle(WebRequest request, Article ori) {
		String title = StringUtils.trimToEmpty(request.getParameter("title"));
		String permalink = StringUtils.trimToEmpty(request.getParameter("permalink"));
		String tag = StringUtils.trimToEmpty(request.getParameter("tag"));
		String content = StringUtils.trimToEmpty(request.getParameter("content"));
		byte commentStatus = Article.COMMENT_OFF;
		if (request.getParameterValues("commentStatus") != null
				&& request.getParameterValues("commentStatus").length > 0) {
			commentStatus = Article.COMMENT_ON;
		}
		String s = StringUtils.trimToEmpty(request.getParameter("stat"));

		String id = Function.generateId();

		title = Function.escapeHtml(title);
		title = "".equals(title) ? Function.timeString() : title;
		permalink = Function.permalink(permalink);
		permalink = "".equals(permalink) ? Function.permalink(title) : permalink;
		tag = Function.stripTags(StringUtils.replaceEach(tag, new String[] { " ", "，" },
				new String[] { ",", "," }));
		byte stat = Article.STAT_PUBLISHED;
		if ((Article.STAT_PRIVATE + "").equals(s)) {
			stat = Article.STAT_PRIVATE;
		} else if ((Article.STAT_RECYCLE + "").equals(s)) {
			stat = Article.STAT_RECYCLE;
		}
		if (ori != null) {
			ori.setTitle(title);
			ori.setPermalink(permalink);
			ori.setContent(content);
			ori.setTag(tag);
			ori.setStat(stat);
			ori.setCommentStatus(commentStatus);
			return ori;
		}
		return new Article(id, title, permalink, content, tag, new Date(), stat, 0, 0,
				commentStatus, Article.TYPE_PAGE);
	}

}
