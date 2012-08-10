package org.cweili.wray.web;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.BlogView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class ArticleController extends BaseController {
	
	@Override
	@RequestMapping("/article/{permalink}/*")
	public BlogView index(HttpServletRequest request, HttpServletResponse response, @PathVariable String permalink) {
		
		System.out.println(">>>>>>>>>" + permalink + "----------------------------------");
		BlogView v = new BlogView("article");
		Article article = articleService.getArticleByPermalink(permalink);
		v.add("article", article);
//		for(int i = 0; i < 100; ++i) {
//			articleService.save(new Article(0, "标题" + i, "permalink" + i, "内容" + i, "标签1,标签2,标签3,标签4", new Date(), Article.STAT_PUBLISHED, 0, 0, Article.COMMENT_ON, Article.TYPE_ARTICLE));
//		}
//		List<Article> list = articleService.getAll();
//		for(int i = 0; i < list.size(); ++i) {
//			log.info(list.get(i).toString());
//		}
		article.setHits(article.getHits() + 1);
		try {
			articleService.updateHitsCommentCount(article);
		} catch (SQLException e) {
			log.error(e.toString());
		}
		return v;
	}

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
