package org.cweili.wray.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Paginator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class IndexController extends BaseController {

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	@RequestMapping("/page-{page}/")
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String page) {
		BlogView v = new BlogView("index");
		int p = 1;
		try {
			p = Integer.valueOf(page);
		} catch(Exception e) {
			log.error(e.toString());
		}
		
//		for(int i = 0; i < 10000; ++i) {
//			articleService.save(new Article(0, "标题" + i, "permalink" + i,
//					"内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容" + i,
//					"标签1,标签2,标签3,标签4", new Date(), Article.STAT_PUBLISHED, 0, 0, Article.COMMENT_ON, Article.TYPE_ARTICLE));
//		}
		
		List<Article> articles = articleService.getArticlesByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_PUBLISHED, p, Integer.valueOf(blogConfig.get("limit")));
		v.add("articles", articles);
		
		Paginator pagination = new Paginator(articleService.getCountByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_PUBLISHED), Integer.valueOf(blogConfig.get("limit")), p);
		v.add("paginationOn", pagination.isPageBarOn());
		v.add("paginationPageNums", pagination.getPageList());
		v.add("paginationCurrentPageNum", p);
		v.add("paginationPreviousPageNum", pagination.getPrevious());
		v.add("paginationNextPageNum", pagination.getNext());
		v.add("paginationPageCount", pagination.getLast());
		
		v.add("path", "");
		
		return v;
	}

}
