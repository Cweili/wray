package org.cweili.wray.web.admin;

import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
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
public final class AdminLoginController extends BaseController {

	@RequestMapping(value = "/admin-login", method = RequestMethod.GET)
	public BlogView logIn() {
		BlogView v = new BlogView("login");
		v.add("err", false);
		return v;
	}

	@RequestMapping(value = "/admin-login", method = RequestMethod.POST)
	public BlogView doPost(WebRequest request) throws Exception {
		if (request.getParameter("username") != null && request.getParameter("password") != null
				&& request.getParameter("username").equals(blogConfig.get("adminName"))
				&& request.getParameter("password").equals(blogConfig.get("adminPwd"))) {

			if (null != request.getParameterValues("rememberme")
					&& "true".equals(request.getParameterValues("rememberme")[0])) {
				request.setAttribute(Constant.AUTHORITY_KEY, Constant.AUTHORITY_COOKIE,
						WebRequest.SCOPE_REQUEST);
			} else {
				request.setAttribute(Constant.AUTHORITY_KEY, !Constant.AUTHORITY_COOKIE,
						WebRequest.SCOPE_REQUEST);
			}

			BlogView v = new BlogView("msg");
			v.add("actionName", "管理员登陆");
			v.add("redirect", "admin-dashboard");
			v.add("err", "succ");
			v.add("msg", "登陆成功");
			v.add("succ", "登陆成功，欢迎您 " + blogConfig.get("adminNick"));
			return v;

		} else {

			BlogView v = new BlogView("login");
			if (request.getParameter("username") != null) {
				v.add("username", request.getParameter("username"));
			}
			v.add("err", true);
			return v;
		}
	}

	@RequestMapping("/admin-logout-{authority}")
	public BlogView logOut(WebRequest request, @PathVariable("authority") String authority) {
		BlogView v = new BlogView("msg");
		v.add("actionName", "管理员退出");
		if (null != request.getAttribute(Constant.AUTHORITY_KEY, WebRequest.SCOPE_REQUEST)
				&& authority.equals(request.getAttribute(Constant.AUTHORITY_KEY,
						WebRequest.SCOPE_REQUEST))) {
			v.add("redirect", "admin-login");
			v.add("err", "succ");
			v.add("msg", "退出成功");
			v.add("succ", "退出成功，再见 " + blogConfig.get("adminNick"));
		} else {
			v.add("err", "退出失败");
		}
		return v;
	}

}
