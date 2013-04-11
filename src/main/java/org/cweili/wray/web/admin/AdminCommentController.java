package org.cweili.wray.web.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.Comment;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
import org.cweili.wray.web.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:57
 * 
 */
@Controller
@Scope("prototype")
public final class AdminCommentController extends BaseController {

	@RequestMapping("/admin-comment")
	public BlogView articleList(WebRequest request) {
		BlogView v = new BlogView("comment-list");
		String actionName = "评论管理";
		int page = 1;
		try {
			page = Integer.valueOf(request.getParameter("page") == null ? "1" : request
					.getParameter("page"));
		} catch (Exception e) {
			log.error(e.toString());
		}
		v.add("actionName", actionName);
		v.add("comments", commentService.find(page, Constant.ADMIN_LIST_SIZE));

		addPaginator(v, commentService.count(), page, Constant.ADMIN_LIST_SIZE);
		return v;
	}

	@RequestMapping(value = "/admin-comment-edit-{commentid}", method = RequestMethod.GET)
	public BlogView editGet(@PathVariable("commentid") String commentid) {
		BlogView v = new BlogView("comment-edit");
		v.add("actionName", "编辑评论");
		Comment comment = commentService.findById(commentid);
		if (null == comment) {
			comment = new Comment();
		}
		v.add("comment", comment);
		return v;
	}

	@RequestMapping(value = "/admin-comment-edit-{commentid}", method = RequestMethod.POST)
	public @ResponseBody
	String editPost(WebRequest request, @PathVariable("commentid") String commentid) {
		Comment comment = commentService.findById(commentid);
		comment = getComment(request, comment);

		if (null == commentService.save(comment)) {
			return Constant.SUBMIT_FAILED;
		}
		return Constant.SUBMIT_SUCCESS;
	}

	@RequestMapping(value = "/admin-comment-block", method = RequestMethod.POST)
	public BlogView del(WebRequest request) {
		BlogView v = new BlogView("msg");
		v.add("err", "succ");
		v.add("msg", "评论屏蔽成功");
		v.add("succ", "恭喜您，您选中的评论已成功屏蔽。");
		v.add("redirect", "admin-comment-" + "?page=" + request.getParameter("page"));

		List<String> ids = new ArrayList<String>();
		if (request.getParameterValues("id") != null) {
			Collections.addAll(ids, request.getParameterValues("id"));
		}

		return v;
	}

	private Comment getComment(WebRequest request, Comment ori) {
		String author = StringUtils.trimToEmpty(request.getParameter("author"));
		String email = StringUtils.trimToEmpty(request.getParameter("email"));
		String link = StringUtils.trimToEmpty(request.getParameter("link"));
		String content = StringUtils.trimToEmpty(request.getParameter("content"));
		String s = StringUtils.trimToEmpty(request.getParameter("stat"));

		byte stat = Comment.STAT_DISPLAY;
		if ((Comment.STAT_DISPLAY + "").equals(s)) {
			stat = Comment.STAT_DISPLAY;
		} else if ((Comment.STAT_BLOCK + "").equals(s)) {
			stat = Comment.STAT_BLOCK;
		}
		ori.setAuthor(author);
		ori.setEmail(email);
		ori.setLink(link);
		ori.setContent(content);
		ori.setStat(stat);

		return ori;
	}

}
