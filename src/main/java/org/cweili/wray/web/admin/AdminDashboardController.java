package org.cweili.wray.web.admin;

import org.cweili.wray.util.BlogView;
import org.cweili.wray.web.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:39:07
 * 
 */
@Controller
@Scope("prototype")
public final class AdminDashboardController extends BaseController {

	@RequestMapping("/admin-dashboard")
	public BlogView index() {
		BlogView v = new BlogView("dashboard");
		return v;
	}

}
