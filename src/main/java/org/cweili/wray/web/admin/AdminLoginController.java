package org.cweili.wray.web.admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.web.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public BlogView logIn(HttpServletRequest request) {
		BlogView v = new BlogView("login");
		String username = findCookie(request, Constant.AUTHORITY_COOKIE);
		if (null != username) {
			v.addObject("username", username);
		}
		v.add("err", false);
		return v;
	}

	@RequestMapping(value = "/admin-login", method = RequestMethod.POST)
	public @ResponseBody
	String logIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (null != request.getParameter("username") && null != request.getParameter("password")
				&& null != request.getParameter("hash")
				&& request.getParameter("username").equals(blogConfig.get("adminName"))) {
			String password = Function.sha256(blogConfig.get("adminPwd")
					+ request.getParameter("hash"));
			if (password.equals(request.getParameter("password"))) {

				// 保存登陆状态
				String time = Function.encode(Function.timestamp());
				String key = Function.authorityKey(time, blogConfig.get("adminName"),
						blogConfig.get("adminPwd"));
				request.getSession().setAttribute(Constant.AUTHORITY_KEY, key);

				// Cookie 保存用户名
				Cookie cookie = new Cookie(Constant.AUTHORITY_COOKIE, blogConfig.get("adminName"));
				cookie.setDomain(request.getServerName());
				cookie.setPath(request.getContextPath() + "/");
				cookie.setMaxAge(31536000);
				response.addCookie(cookie);

				if (null != request.getParameterValues("rememberme")
						&& "true".equals(request.getParameterValues("rememberme")[0])) {

					// Cookie 设置验证串
					cookie = new Cookie(Constant.AUTHORITY_KEY, key + "@" + time);
					cookie.setDomain(request.getServerName());
					cookie.setPath(request.getContextPath() + "/");
					cookie.setMaxAge(31536000);
					response.addCookie(cookie);
				}
				log.info(blogConfig.get("adminName") + " login successful.");

				return Constant.SUBMIT_SUCCESS;
			}
		}

		return Constant.SUBMIT_FAILED;
	}

	@RequestMapping("/admin-logout-{authority}")
	public BlogView logOut(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("authority") String authority) {
		BlogView v = new BlogView("msg");
		v.add("actionName", "管理员退出");
		String sessionAuthority = "";
		if (null != request.getSession(false)
				&& null != request.getSession().getAttribute(Constant.AUTHORITY_KEY)) {
			sessionAuthority = (String) request.getSession().getAttribute(Constant.AUTHORITY_KEY);
		}
		if (sessionAuthority.equals(authority)) {

			request.getSession().removeAttribute(Constant.AUTHORITY_KEY);

			Cookie cookie = new Cookie(Constant.AUTHORITY_KEY, null);
			cookie.setDomain(request.getServerName());
			cookie.setPath(request.getContextPath() + "/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);

			log.info(blogConfig.get("adminName") + " logout successful.");

			v.add("redirect", "admin-login");
			v.add("err", "succ");
			v.add("msg", "退出成功");
			v.add("succ", "退出成功，再见 " + blogConfig.get("adminNick"));
		} else {
			v.add("err", "退出失败");
		}
		return v;
	}

	private String findCookie(HttpServletRequest request, String find) {
		if (null != request.getCookies()) {
			for (Cookie cookie : request.getCookies()) {
				if (find.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

}
