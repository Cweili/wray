/**
 * 
 */
package org.cweili.wray.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Comment;
import org.cweili.wray.util.BlogView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author cweili
 * @version 2012-9-5 下午9:31:12
 * 
 */
@Controller
@Scope("prototype")
public class CommentController extends BaseController {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.web.BaseController#index(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "/comment/", method = RequestMethod.POST)
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		Comment comment = new Comment();
		comment.setArticleId(Long.parseLong(request.getParameter("article")));
		comment.setAuthor(request.getParameter("author"));
		comment.setAgent(request.getHeader("User-Agent"));
		comment.setContent(request.getParameter("content"));
		comment.setEmail(request.getParameter("email"));
		comment.setIp(request.getRemoteAddr());
		comment.setLink(request.getParameter("link"));
		comment.setStat(Comment.STAT_DISPLAY);
		commentService.save(comment);
		try {
			response.sendRedirect("article/" + request.getParameter("permalink") + "/");
		} catch (IOException e) {
			log.error("Redirect error.", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.web.BaseController#index(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.String)
	 */
	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String articleid) {
		return null;
	}
}
