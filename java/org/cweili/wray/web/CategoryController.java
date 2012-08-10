package org.cweili.wray.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.util.BlogView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class CategoryController extends BaseController {

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping("/category/{permalink}/*")
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String str) {
		// TODO Auto-generated method stub
		return null;
	}

}
