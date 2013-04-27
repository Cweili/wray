package org.cweili.wray.web.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.BlogView;
import org.cweili.wray.domain.dto.Config;
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

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:39:36
 * 
 */
@Controller
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

		int feedSize = Function.minimumInteger(request.getParameter("feedSize"), 10);

		if (blogConfig.getInt("feedSize") != feedSize) {
			blogConfig.save(new Config("feedSize", feedSize));
			feedService.clearFeedCache();
		}

		blogConfig.saveRequest(request, new String[] { "blogTitle", "blogSubtitle", "metaKeywords",
				"metaDescription" }, new String[] { "blogHost", "noticeBoard", "attachHeader",
				"attachFooter", "attachStat" });
		return Constant.SUBMIT_SUCCESS;
	}

	@RequestMapping(value = "/admin-setup-account", method = RequestMethod.POST)
	public @ResponseBody
	String account(WebRequest request) {
		String[] adminPwd = StringUtils.isBlank(request.getParameter("adminPwd")) ? new String[] {}
				: new String[] { "adminPwd" };
		blogConfig.saveRequest(request, new String[] { "adminName", "adminNick", "adminEmail" },
				adminPwd);
		return Constant.SUBMIT_SUCCESS;
	}

	@RequestMapping(value = "/admin-setup-skin", method = RequestMethod.POST)
	public @ResponseBody
	String skin(WebRequest request) {
		int pageSize = Function.minimumInteger(request.getParameter("pageSize"), 10);
		int recentCommentsSize = Function.minimumInteger(
				request.getParameter("recentCommentsSize"), 10);
		int topHitArticlesSize = Function.minimumInteger(
				request.getParameter("topHitArticlesSize"), 10);
		int topCommentArticlesSize = Function.minimumInteger(
				request.getParameter("topCommentArticlesSize"), 10);
		int mostUsedTagsSize = Function
				.minimumInteger(request.getParameter("mostUsedTagsSize"), 30);

		blogConfig.save(new Config("pageSize", pageSize));
		blogConfig.save(new Config("recentCommentsSize", recentCommentsSize));
		blogConfig.save(new Config("topHitArticlesSize", topHitArticlesSize));
		blogConfig.save(new Config("topCommentArticlesSize", topCommentArticlesSize));
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
