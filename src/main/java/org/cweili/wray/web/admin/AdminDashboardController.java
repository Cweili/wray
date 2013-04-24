package org.cweili.wray.web.admin;

import java.net.URI;
import java.net.URISyntaxException;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
import org.cweili.wray.web.BaseController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:39:07
 * 
 */
@Controller
public final class AdminDashboardController extends BaseController {

	@RequestMapping("/admin-dashboard")
	public BlogView dashboard() {
		BlogView v = new BlogView("dashboard");
		int recentCommentsSize = blogConfig.getInt("recentCommentsSize");
		if (recentCommentsSize > 0) {
			v.add("recentComments", commentService.getRecentComments(recentCommentsSize));
		}
		v.add("articles", articleService.findByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_DRAFT,
				1, Constant.ADMIN_LIST_SIZE));

		v.add("articlePublishCount",
				articleService.countByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_PUBLISHED));
		v.add("articleDraftCount",
				articleService.countByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_DRAFT));
		v.add("articleRecycleCount",
				articleService.countByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_RECYCLE));

		v.add("pagePublishCount",
				articleService.countByTypeStatus(Article.TYPE_PAGE, Article.STAT_PUBLISHED));
		v.add("pagePrivateCount",
				articleService.countByTypeStatus(Article.TYPE_PAGE, Article.STAT_PRIVATE));

		v.add("commentCount", commentService.count());
		return v;
	}

	@RequestMapping("/admin")
	public ResponseEntity<String> redirect() {
		HttpHeaders header = new HttpHeaders();
		try {
			header.setLocation(new URI("admin-dashboard"));
		} catch (URISyntaxException e) {
			log.error(e);
		}
		return new ResponseEntity<String>(header, HttpStatus.MOVED_PERMANENTLY);
	}

}
