package org.cweili.wray.web.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.util.BlogView;
import org.cweili.wray.web.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class AdminDashboardController extends BaseController {

	@Override
	@RequestMapping("/admin-dashboard")
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("dashboard");
		return v;
	}

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String str) {
		// TODO Auto-generated method stub
		return null;
	}

}
