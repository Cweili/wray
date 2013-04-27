package org.cweili.wray.web.admin;

import java.net.URI;
import java.net.URISyntaxException;

import org.cweili.wray.domain.BlogView;
import org.cweili.wray.domain.dto.Article;
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
		v.add("articles",
				articleService.findByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_DRAFT, 1,
						Constant.ADMIN_LIST_SIZE).getContent());

		v.add("articlePublishCount",
				articleService.findByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_PUBLISHED, 1,
						Constant.MAX_PAGE).getTotalElements());
		v.add("articleDraftCount",
				articleService.findByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_DRAFT, 1,
						Constant.MAX_PAGE).getTotalElements());
		v.add("articleRecycleCount",
				articleService.findByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_RECYCLE, 1,
						Constant.MAX_PAGE).getTotalElements());

		v.add("pagePublishCount",
				articleService.findByTypeStatus(Article.TYPE_PAGE, Article.STAT_PUBLISHED, 1,
						Constant.MAX_PAGE).getTotalElements());
		v.add("pagePrivateCount",
				articleService.findByTypeStatus(Article.TYPE_PAGE, Article.STAT_PRIVATE, 1,
						Constant.MAX_PAGE).getTotalElements());

		v.add("commentCount", commentService.count());
		return v;
	}

	@RequestMapping("/admin")
	public ResponseEntity<Object> redirect() {
		HttpHeaders header = new HttpHeaders();
		try {
			header.setLocation(new URI("admin-dashboard"));
		} catch (URISyntaxException e) {
			log.error(e);
		}
		return new ResponseEntity<Object>(header, HttpStatus.MOVED_PERMANENTLY);
	}

}
