package org.cweili.wray.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.util.BlogView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:47
 *
 */
@Controller
@Scope("prototype")
public final class TagController extends BaseController {

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping("/tag/{permalink}/*")
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String str) {
		// TODO Auto-generated method stub
		return null;
	}

}
