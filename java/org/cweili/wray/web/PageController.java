package org.cweili.wray.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.BlogView;
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

	@Override
	@RequestMapping("/page/{permalink}/*")
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String permalink) {
		BlogView v = new BlogView("page");
		try {
			permalink = URLDecoder.decode(permalink, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Article page = articleService.getArticleByPermalink(permalink);
		v.add("article", page);
		return v;
	}

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
