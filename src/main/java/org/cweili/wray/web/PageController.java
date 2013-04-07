package org.cweili.wray.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Function;
import org.cweili.wray.util.NotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:39
 * 
 */
@Controller
@Scope("prototype")
public final class PageController extends BaseController {

	@RequestMapping("/page/{permalink}/*")
	public BlogView permalink(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String permalink) throws NotFoundException {
		permalink = Function.urlDecode(permalink);
		Article page = articleService.findByPermalink(permalink, Article.TYPE_PAGE);
		if (null == page) {
			throw new NotFoundException();
		}
		BlogView v = new BlogView("page");
		v.add("article", page);
		return v;
	}

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String str) {
		return null;
	}

}
