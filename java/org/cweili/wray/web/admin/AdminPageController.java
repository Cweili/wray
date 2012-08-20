package org.cweili.wray.web.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.web.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:39:25
 * 
 */
@Controller
@Scope("prototype")
public final class AdminPageController extends BaseController {

	@Override
	@RequestMapping("/admin-page-{status}")
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String status) {
		BlogView v = new BlogView("page-list");
		byte stat = Article.STAT_PUBLISHED;
		String actionName = "公开页面";
		int page = 1;
		try {
			page = Integer.valueOf(request.getParameter("page") == null ? "1" : request
					.getParameter("page"));
		} catch (Exception e) {
			log.error(e.toString());
		}
		if ("private".equals(status)) {
			stat = Article.STAT_DRAFT;
			actionName = "私密页面";
		} else if ("recycle".equals(status)) {
			stat = Article.STAT_RECYCLE;
			actionName = "页面回收站";
		}
		v.add("actionName", actionName);
		v.add("articles", articleService.getArticlesByTypeStatus(Article.TYPE_PAGE, stat, page,
				Constant.ADMIN_LIST_SIZE));

		addPaginator(v, articleService.getCountByTypeStatus(Article.TYPE_ARTICLE, stat), page,
				Constant.ADMIN_LIST_SIZE);
		return v;
	}

	@RequestMapping(value = "/admin-page-add", method = RequestMethod.GET)
	public BlogView addGet(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("page-edit");
		v.add("actionName", "新增页面");
		v.add("commentStatus", Article.COMMENT_ON);
		v.add("stat", Article.STAT_PUBLISHED);
		v.add("err", "");
		return v;
	}

	@RequestMapping(value = "/admin-page-add", method = RequestMethod.POST)
	public BlogView addPost(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("msg");
		v.add("actionName", "新增页面");
		Article article = getArticle(request, null);
		v.add("redirect", "admin-page-edit-" + article.getArticleId());
		v.add("err", "succ");
		v.add("msg", "页面保存成功");
		v.add("succ", "恭喜您，您的页面已成功保存。");
		try {
			articleService.save(article);
		} catch (Exception e) {
			v.setView("page-edit");
			v.add("title", article.getTitle());
			v.add("permalink", article.getPermalink());
			v.add("content", article.getContent());
			v.add("commentStatus", article.getCommentStatus());
			v.add("stat", article.getStat());
			v.add("err", "数据库更新失败");
			v.add("msg", "页面保存失败");
		}
		return v;
	}

	@RequestMapping(value = "/admin-page-edit-{articleid}", method = RequestMethod.GET)
	public BlogView editGet(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String articleid) {
		BlogView v = new BlogView("page-edit");
		v.add("actionName", "编辑页面");
		v.add("articleId", articleid);
		long id = 0;
		try {
			id = Long.valueOf(articleid);
		} catch (Exception e) {
			log.error(e.toString());
		}
		Article article = articleService.getArticleById(id);
		if (article != null) {
			v.add("title", article.getTitle());
			v.add("permalink", article.getPermalink());
			v.add("content", article.getContent());
			v.add("commentStatus", article.getCommentStatus());
			v.add("stat", article.getStat());
			v.add("err", "");
		} else {
			v.add("commentStatus", Article.COMMENT_ON);
			v.add("stat", Article.STAT_PUBLISHED);
			v.add("err", "页面未找到");
		}
		return v;
	}

	@RequestMapping(value = "/admin-page-edit-{articleid}", method = RequestMethod.POST)
	public BlogView editPost(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String articleid) {
		BlogView v = new BlogView("page-edit");
		v.add("actionName", "编辑页面");
		v.add("articleId", articleid);
		long id = 0;
		try {
			id = Long.valueOf(articleid);
		} catch (Exception e) {
			log.error(e.toString());
		}
		Article article = articleService.getArticleById(id);
		article = getArticle(request, article);
		v.add("title", article.getTitle());
		v.add("permalink", article.getPermalink());
		v.add("tag", article.getTag());
		v.add("content", article.getContent());
		v.add("commentStatus", article.getCommentStatus());
		v.add("stat", article.getStat());
		v.add("err", "succ");
		try {
			articleService.update(article);
		} catch (Exception e) {
			v.add("err", "数据库更新失败");
		}
		return v;
	}

	@RequestMapping(value = "/admin-page-manage-{status}", method = RequestMethod.POST)
	public BlogView manage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String status) {
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

		List<Long> ids = new ArrayList<Long>();
		if (request.getParameterValues("id") != null) {
			for (String idStr : request.getParameterValues("id")) {
				try {
					ids.add(Long.valueOf(idStr));
				} catch (Exception e) {
					log.error(e.toString());
				}
			}
		}

		int page = 1;
		try {
			page = Integer.valueOf(request.getParameter("page") == null ? "1" : request
					.getParameter("page"));
		} catch (Exception e) {
			log.error(e.toString());
		}

		byte stat = Article.STAT_PUBLISHED;
		if ("private".equals(status)) {
			stat = Article.STAT_DRAFT;
		} else if ("recycle".equals(status)) {
			stat = Article.STAT_RECYCLE;
		}

		boolean orderUpdated = false;

		List<Article> articles = articleService.getArticlesByTypeStatus(Article.TYPE_PAGE, stat,
				page, Constant.ADMIN_LIST_SIZE);
		int order = 0;
		Article article;

		try {
			for (int i = 0; i < articles.size(); ++i) {
				if (request.getParameter("order" + articles.get(i).getArticleId()) != null) {
					order = Integer.valueOf(request.getParameter("order"
							+ articles.get(i).getArticleId()));
					article = articles.get(i);
					if (article.getHits() != order) {
						orderUpdated = true;
						article.setHits(order);
						articleService.updateHits(article);
					}
				}
			}
			if (orderUpdated) {
				articleService.updateArticleCache();
			}
		} catch (SQLException se) {
			if (orderUpdated) {
				v.add("err", "数据库更新失败");
				v.add("msg", "页面排序失败");
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

		if (!ids.isEmpty()) {
			try {
				articleService.remove(ids, type);
			} catch (Exception e) {
				v.add("err", "数据库更新失败");
				v.add("msg", "页面删除失败");
			}
		}
		return v;
	}

	private Article getArticle(HttpServletRequest request, Article ori) {
		String title = request.getParameter("title") != null ? request.getParameter("title") : "";
		String permalink = request.getParameter("permalink") != null ? request
				.getParameter("permalink") : "";
		String content = request.getParameter("content") != null ? request.getParameter("content")
				: "";
		byte commentStatus = Article.COMMENT_OFF;
		if (request.getParameterValues("commentStatus") != null
				&& request.getParameterValues("commentStatus").length > 0) {
			commentStatus = Article.COMMENT_ON;
		}
		String s = request.getParameter("stat") != null ? request.getParameter("stat") : "";

		Long id = Function.generateId();

		title = Function.trimAndStripTags(title);
		title = "".equals(title) ? "未命名" + id : title;
		permalink = Function.url(permalink);
		permalink = "".equals(permalink) ? id.toString() : permalink;
		byte stat = Article.STAT_PUBLISHED;
		if ((Article.STAT_DRAFT + "").equals(s)) {
			stat = Article.STAT_DRAFT;
		} else if ((Article.STAT_RECYCLE + "").equals(s)) {
			stat = Article.STAT_RECYCLE;
		}
		if (ori != null) {
			ori.setTitle(title);
			ori.setPermalink(permalink);
			ori.setContent(content);
			ori.setStat(stat);
			ori.setCommentStatus(commentStatus);
			return ori;
		}
		return new Article(id, title, permalink, content, "", new Date(), stat, 0, 0,
				commentStatus, Article.TYPE_PAGE);
	}

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
