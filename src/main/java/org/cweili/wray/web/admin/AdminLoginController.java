package org.cweili.wray.web.admin;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.BlogView;
import org.cweili.wray.util.Captcha;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

/**
 * 管理登陆 Controller
 * 
 * @author Cweili
 * @version 2012-8-16 下午5:39:44
 * 
 */
@Controller
public final class AdminLoginController extends BaseController {

	@RequestMapping(value = "/admin-login", method = RequestMethod.GET)
	public BlogView logIn() {
		BlogView v = new BlogView("login");
		String username = "";
		ServletRequestAttributes req = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		if (null != WebUtils.getCookie(req.getRequest(), Constant.AUTHORITY_NAME)) {
			username = StringUtils.stripToEmpty(WebUtils.getCookie(req.getRequest(),
					Constant.AUTHORITY_NAME).getValue());
			try {
				username = URLDecoder.decode(username, Constant.CHARSET);
			} catch (Exception e) {
				log.error(e);
			}
		}

		v.addObject("username", username);
		v.add("err", false);
		return v;
	}

	@RequestMapping(value = "/admin-login", method = RequestMethod.POST)
	public @ResponseBody
	String logIn(WebRequest request, HttpServletResponse response) throws Exception {

		ServletRequestAttributes req = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();

		if (!StringUtils.stripToEmpty(request.getParameter(Constant.CAPTCHA)).toUpperCase()
				.equals(request.getAttribute(Constant.CAPTCHA, WebRequest.SCOPE_SESSION))) {
			request.setAttribute(Constant.CAPTCHA, Captcha.getRandomString(6),
					WebRequest.SCOPE_SESSION);
			return Constant.CAPTCHA;
		}

		if (null != request.getParameter("username") && null != request.getParameter("password")
				&& null != request.getParameter("hash")
				&& request.getParameter("username").equals(blogConfig.get("adminName"))) {
			String password = Function.sha256(blogConfig.get("adminPwd")
					+ request.getParameter("hash"));
			if (password.equals(request.getParameter("password"))) {

				// 保存登陆状态
				String time = Function.encode(Function.timestamp());
				String token = Function.authorityToken(time, blogConfig.get("adminName"),
						blogConfig.get("adminPwd"));
				WebUtils.setSessionAttribute(req.getRequest(), Constant.AUTHORITY_TIME, time);
				WebUtils.setSessionAttribute(req.getRequest(), Constant.AUTHORITY_TOKEN, token);

				// Cookie 保存用户名
				CookieGenerator cookie = new CookieGenerator();
				cookie.setCookieName(Constant.AUTHORITY_NAME);
				cookie.setCookiePath(request.getContextPath() + "/");
				cookie.setCookieMaxAge(Constant.CACHE_MAX_AGE);
				cookie.addCookie(response,
						URLEncoder.encode(blogConfig.get("adminName"), Constant.CHARSET));

				if (null != request.getParameterValues("rememberme")
						&& "true".equals(request.getParameterValues("rememberme")[0])) {

					// Cookie 设置验证串
					cookie.setCookieName(Constant.AUTHORITY_TOKEN);
					cookie.addCookie(response, token + "@" + time);
				}
				log.info(blogConfig.get("adminName") + " login successful.");

				return Constant.SUBMIT_SUCCESS;
			}
		}

		request.setAttribute(Constant.CAPTCHA, Captcha.getRandomString(6), WebRequest.SCOPE_SESSION);
		return Constant.SUBMIT_FAILED;
	}

	@RequestMapping("/admin-logout-{authority}")
	public BlogView logOut(HttpServletResponse response, @PathVariable("authority") String authority) {
		BlogView v = new BlogView("msg");
		v.add("actionName", "管理员退出");
		ServletRequestAttributes req = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		String sessionAuthority = StringUtils.stripToEmpty((String) WebUtils.getSessionAttribute(
				req.getRequest(), Constant.AUTHORITY_TOKEN));

		if (sessionAuthority.equals(authority)) {

			WebUtils.setSessionAttribute(req.getRequest(), Constant.AUTHORITY_TOKEN, null);

			CookieGenerator cookie = new CookieGenerator();
			cookie.setCookieName(Constant.AUTHORITY_TOKEN);
			cookie.setCookieDomain(req.getRequest().getServerName());
			cookie.setCookiePath(req.getRequest().getContextPath() + "/");
			cookie.setCookieMaxAge(0);
			cookie.addCookie(response, null);

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

}
