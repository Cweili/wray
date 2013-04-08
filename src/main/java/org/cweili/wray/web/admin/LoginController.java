package org.cweili.wray.web.admin;

import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.util.BlogView;
import org.cweili.wray.web.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:39:44
 * 
 */
@Controller
@Scope("prototype")
public final class LoginController extends BaseController {

	@RequestMapping(value = "/admin-login", method = RequestMethod.GET)
	public BlogView logIn() {
		BlogView v = new BlogView("login");
		v.add("err", false);
		return v;
	}

	@RequestMapping(value = "/admin-login", method = RequestMethod.POST)
	public BlogView doPost(WebRequest request, HttpServletResponse response) throws Exception {
		BlogView v = new BlogView("login");
		if (request.getParameter("username") != null && request.getParameter("password") != null
				&& request.getParameter("username").equals(blogConfig.get("adminName"))
				&& request.getParameter("password").equals(blogConfig.get("adminPwd"))) {
			// TODO cookie or session
			v.add("err", false);
			response.sendRedirect("/admin-dashboard.html");
		} else {
			if (request.getParameter("username") != null) {
				v.add("username", request.getParameter("username"));
			}
			v.add("err", true);
		}
		return v;
	}

	@RequestMapping("/admin-logout-{security}")
	public BlogView logOut(@PathVariable("security") String str) {
		// TODO Auto-generated method stub
		return null;
	}

}
