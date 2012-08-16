package org.cweili.wray.web.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.util.Paginator;
import org.cweili.wray.web.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:57
 *
 */
@Controller
@Scope("prototype")
public final class AdminArticleController extends BaseController {
	
	@Override
	@RequestMapping("/admin-article-{status}")
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String status) {
		BlogView v = new BlogView("article-list");
		byte stat = Article.STAT_PUBLISHED;
		String actionName = "已发布文章";
		int page = 1;
		try {
			page = Integer.valueOf(request.getParameter("page") == null ? "1" : request.getParameter("page"));
		} catch(Exception e) {
			log.error(e.toString());
		}
		if("draft".equals(status)) {
			stat = Article.STAT_DRAFT;
			actionName = "文章草稿";
		} else if("recycle".equals(status)) {
			stat = Article.STAT_RECYCLE;
			actionName = "文章回收站";
		}
		v.add("actionName", actionName);
		v.add("articles", articleService.getArticlesByTypeStatus(Article.TYPE_ARTICLE, stat, page, Constant.ADMIN_LIST_SIZE));
		
		Paginator pagination = new Paginator(articleService.getCountByTypeStatus(Article.TYPE_ARTICLE, stat), Constant.ADMIN_LIST_SIZE, page);
		v.add("paginationOn", pagination.isPageBarOn());
		v.add("paginationPageNums", pagination.getPageList());
		v.add("paginationCurrentPageNum", page);
		v.add("paginationPreviousPageNum", pagination.getPrevious());
		v.add("paginationNextPageNum", pagination.getNext());
		v.add("paginationPageCount", pagination.getLast());
		return v;
	}
	
	@RequestMapping(value="/admin-article-add", method = RequestMethod.GET)
	public BlogView addGet(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("article-edit");
		v.add("actionName", "新增文章");
		v.add("commentStatus", Article.COMMENT_ON);
		v.add("stat", Article.STAT_PUBLISHED);
		v.add("err", "");
		return v;
	}
	
	@RequestMapping(value="/admin-article-add", method = RequestMethod.POST)
	public BlogView addPost(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("msg");
		v.add("actionName", "新增文章");
		Article article = getArticle(request, null);
		v.add("redirect", "admin-article-edit-"+article.getArticleId());
		v.add("err", "succ");
		v.add("msg", "文章保存成功");
		v.add("succ", "恭喜您，您的文章已成功保存。");
		try {
			articleService.save(article);
		} catch(Exception e) {
			v.setView("article-edit");
			v.add("title", article.getTitle());
			v.add("permalink", article.getPermalink());
			v.add("tag", article.getTag());
			v.add("content", article.getContent());
			v.add("commentStatus", article.getCommentStatus());
			v.add("stat", article.getStat());
			v.add("err", "数据库更新失败");
		}
		return v;
	}

	@RequestMapping(value="/admin-article-edit-{articleid}", method = RequestMethod.GET)
	public BlogView editGet(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String articleid) {
		BlogView v = new BlogView("article-edit");
		v.add("actionName", "编辑文章");
		v.add("articleId", articleid);
		long id = 0;
		try {
			id = Long.valueOf(articleid);
		} catch(Exception e) {
			log.error(e.toString());
		}
		Article article = articleService.getArticleById(id);
		if(article != null) {
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
	
	@RequestMapping(value="/admin-article-edit-{articleid}", method = RequestMethod.POST)
	public BlogView editPost(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String articleid) {
		BlogView v = new BlogView("article-edit");
		v.add("actionName", "编辑文章");
		v.add("articleId", articleid);
		long id = 0;
		try {
			id = Long.valueOf(articleid);
		} catch(Exception e) {
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
		String err = "succ";
		try {
			articleService.update(article);
		} catch(Exception e) {
			err = "数据库更新失败";
		}
		v.add("err", err);
		return v;
	}
	
	@RequestMapping(value="/admin-article-delete-{status}", method = RequestMethod.POST)
	public BlogView del(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String status) {
		BlogView v = new BlogView("msg");
		v.add("err", "succ");
		v.add("msg", "文章删除成功");
		v.add("succ", "恭喜您，您选中的文章已成功移入回收站。");
		v.add("redirect", "admin-article-"+status);
		
		byte type = Article.STAT_RECYCLE;
		if("recycle".equals(status)) {
			type = Article.STAT_REMOVED;
			v.add("succ", "恭喜您，您选中的文章已成功删除。");
		}
		
		List<Long> ids = new ArrayList<Long>();
		Long id;
		try {
			if(request.getParameterValues("id") != null && request.getParameterValues("id").length > 0) {
				for(int i = 0; i < request.getParameterValues("id").length; ++i) {
					id = Long.valueOf(request.getParameterValues("id")[i]);
					ids.add(id);
				}
			}
		} catch(Exception e) {
			log.error(e.toString());
		}
		
		try {
			articleService.remove(ids, type);
		} catch (Exception e) {
			v.add("err", "数据库更新失败");
			v.add("msg", "文章删除失败");
		}
		return v;
	}
	
	private Article getArticle(HttpServletRequest request, Article ori) {
		String title = request.getParameter("title") != null ? request.getParameter("title") : "";
		String permalink = request.getParameter("permalink") != null ? request.getParameter("permalink") : "";
		String tag = request.getParameter("tag") != null ? request.getParameter("tag") : "";
		String content = request.getParameter("content") != null ? request.getParameter("content"): "";
		byte commentStatus = Article.COMMENT_OFF;
		if(request.getParameterValues("commentStatus") != null && request.getParameterValues("commentStatus").length > 0) {
			commentStatus = Article.COMMENT_ON;
		}
		String s = request.getParameter("stat") != null ? request.getParameter("stat") : "";
		
		Long id = Function.generateId();
		
		title = Function.trimAndStripTags(title);
		title = "".equals(title) ? "无标题" + id : title;
		permalink = Function.stripTags(permalink).trim().toLowerCase();
		permalink = "".equals(permalink) ? id.toString() : permalink;
		tag = Function.stripTags(tag.replaceAll(" ", ",").replaceAll("，", ","));
		byte stat = Article.STAT_PUBLISHED;
		if((Article.STAT_DRAFT + "").equals(s)) {
			stat = Article.STAT_DRAFT;
		} else if((Article.STAT_RECYCLE + "").equals(s)) {
			stat = Article.STAT_RECYCLE;
		}
		if(ori != null) {
			ori.setTitle(title);
			ori.setPermalink(permalink);
			ori.setTag(tag);
			ori.setContent(content);
			ori.setStat(stat);
			ori.setCommentStatus(commentStatus);
			return ori;
		}
		return new Article(id, title, permalink, content, tag, new Date(), stat, 0, 0, commentStatus, Article.TYPE_ARTICLE);
	}

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
