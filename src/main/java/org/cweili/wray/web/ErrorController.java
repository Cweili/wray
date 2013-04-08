/**
 * 
 */
package org.cweili.wray.web;

import org.cweili.wray.util.BlogView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author cweili
 * @version 2012-9-5 下午9:31:12
 * 
 */
@Controller
@Scope("prototype")
public final class ErrorController extends BaseController {

	@RequestMapping(value = "/error")
	public BlogView error() {
		return new BlogView("error");
	}

}
