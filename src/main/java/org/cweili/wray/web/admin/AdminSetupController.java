package org.cweili.wray.web.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

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

	@RequestMapping(value = "/admin-setup-basic", method = RequestMethod.POST)
	public @ResponseBody
	String basic(WebRequest request) {

		blogConfig.saveRequest(request, new String[] { "blogTitle", "blogSubtitle", "metaKeywords",
				"metaDescription" }, new String[] { "blogHost", "noticeBoard", "attachHeader",
				"attachFooter", "attachStat" });
		return Constant.SUBMIT_SUCCESS;
	}

	@RequestMapping(value = "/admin-setup-account", method = RequestMethod.POST)
	public @ResponseBody
	String account(WebRequest request) {
		blogConfig.saveRequest(request, new String[] { "adminName", "adminNick", "adminEmail" }, ""
				.equals(request.getParameter("adminPwd")) ? new String[] {}
				: new String[] { "adminPwd" });
		return Constant.SUBMIT_SUCCESS;
	}

	@RequestMapping(value = "/admin-setup-skin", method = RequestMethod.POST)
	public @ResponseBody
	String skin(WebRequest request) {
		int limit = 10;
		int recentCommentsSize = 10;
		int topHitsArticlesSize = 10;
		int topCommentArticlesSize = 10;
		int mostUsedTagsSize = 30;
		try {
			limit = Integer.valueOf(request.getParameter("limit"));
			recentCommentsSize = Integer.valueOf(request.getParameter("recentCommentsSize"));
			topHitsArticlesSize = Integer.valueOf(request.getParameter("topHitsArticlesSize"));
			topCommentArticlesSize = Integer
					.valueOf(request.getParameter("topCommentArticlesSize"));
			mostUsedTagsSize = Integer.valueOf(request.getParameter("mostUsedTagsSize"));

		} catch (Exception e) {
			log.error(e.toString());
		}
		blogConfig.save(new Config("limit", limit + ""));
		blogConfig.save(new Config("recentCommentsSize", recentCommentsSize + ""));
		blogConfig.save(new Config("topHitsArticlesSize", topHitsArticlesSize + ""));
		blogConfig.save(new Config("topCommentArticlesSize", topCommentArticlesSize + ""));
		blogConfig.save(new Config("mostUsedTagsSize", mostUsedTagsSize + ""));
		blogConfig.saveRequest(request, Constant.LABELS, new String[] { "skinDir" });

		return Constant.SUBMIT_SUCCESS;
	}

	@RequestMapping(value = "/admin-setup-{type}", method = RequestMethod.GET)
	public BlogView doGet(@PathVariable("type") String type) {
		BlogView v = new BlogView("msg");
		if (TYPE.contains(type)) {
			v.setView("setup-" + type);
			if ("skin".equals(type)) {
				List<String> skinDirs = Function
						.dirList(getSkinPath((ServletRequestAttributes) RequestContextHolder
								.currentRequestAttributes()));
				skinDirs.remove("admin");
				v.add("skinDirs", skinDirs);
				v.add("currentSkinDir", blogConfig.get("skinDir"));
				v.add("labels", Arrays.asList(Constant.LABELS));
			}
		} else {
			v.add("err", "找不到页面");
		}
		return v;
	}

	private String getSkinPath(ServletRequestAttributes request) {
		StringBuilder sb = new StringBuilder(request.getRequest().getSession().getServletContext()
				.getRealPath("/"));
		if ('/' != sb.charAt(sb.length() - 1) && '\\' != sb.charAt(sb.length() - 1)) {
			sb.append('/');
		}
		sb.append(Constant.SKIN_PATH);
		return sb.toString();
	}
}
