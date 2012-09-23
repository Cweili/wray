/**
 * 
 */
package org.cweili.wray.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/* (non-Javadoc)
	 * @see org.cweili.wray.web.BaseController#index(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.web.BaseController#index(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/comment/{articleid}/", method = RequestMethod.POST)
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String articleid) {
		// TODO Auto-generated method stub
		return null;
	}

}
