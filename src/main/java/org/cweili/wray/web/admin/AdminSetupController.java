package org.cweili.wray.web.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Config;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.web.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:39:36
 * 
 */
@Controller
@Scope("prototype")
public final class AdminSetupController extends BaseController {

	private static final List<String> TYPE = new ArrayList<String>();

	static {
		TYPE.add("basic");
		TYPE.add("account");
		TYPE.add("skin");
	};

	@Override
	@RequestMapping(value = "/admin-setup-basic", method = RequestMethod.POST)
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {

		BlogView v = new BlogView("setup-basic");
		blogConfig.saveRequest(request, new String[] { "blogTitle", "blogSubtitle", "metaKeywords",
				"metaDescription" }, new String[] { "blogHost", "noticeBoard", "attachHeader",
				"attachFooter", "attachStat" });
		v.add("err", "succ");
		return v;
	}

	@RequestMapping(value = "/admin-setup-account", method = RequestMethod.POST)
	public BlogView account(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("setup-account");
		blogConfig.saveRequest(request, new String[] { "adminName", "adminNick", "adminEmail" }, ""
				.equals(request.getParameter("adminPwd")) ? new String[] {}
				: new String[] { "adminPwd" });
		v.add("err", "succ");
		return v;
	}

	@RequestMapping(value = "/admin-setup-skin", method = RequestMethod.POST)
	public BlogView skin(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("setup-skin");
		v.add("labels", Arrays.asList(Constant.LABELS));
		List<String> skinDirs = Function.dirList(new File(request.getSession().getServletContext()
				.getRealPath("/")
				+ Constant.SKIN_PATH));
		skinDirs.remove("admin");
		v.add("skinDirs", skinDirs);
		int limit = 10;
		int topHitsArticlesSize = 10;
		int topCommentArticlesSize = 10;
		try {
			limit = Integer.valueOf(request.getParameter("limit"));
			topHitsArticlesSize = Integer.valueOf(request.getParameter("topHitsArticlesSize"));
			topCommentArticlesSize = Integer
					.valueOf(request.getParameter("topCommentArticlesSize"));
		} catch (Exception e) {
			log.error(e.toString());
		}
		blogConfig.save(new Config("limit", limit + ""));
		blogConfig.save(new Config("topHitsArticlesSize", topHitsArticlesSize + ""));
		blogConfig.save(new Config("topCommentArticlesSize", topCommentArticlesSize + ""));
		blogConfig.saveRequest(request, Constant.LABELS, new String[] { "skinDir" });
		v.add("err", "succ");

		v.add("currentSkinDir", blogConfig.get("skinDir"));
		return v;
	}

	@Override
	@RequestMapping(value = "/admin-setup-{type}", method = RequestMethod.GET)
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String type) {
		BlogView v = new BlogView("msg");
		if (TYPE.contains(type)) {
			v.setView("setup-" + type);
			if ("skin".equals(type)) {
				List<String> skinDirs = Function.dirList(new File(request.getSession()
						.getServletContext().getRealPath("/")
						+ Constant.SKIN_PATH));
				skinDirs.remove("admin");
				v.add("skinDirs", skinDirs);
				v.add("currentSkinDir", blogConfig.get("skinDir"));
				v.add("labels", Arrays.asList(Constant.LABELS));
			}
			v.add("err", "");
		} else {
			v.add("err", "找不到页面");
		}
		return v;
	}
}
