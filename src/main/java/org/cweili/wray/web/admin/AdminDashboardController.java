package org.cweili.wray.web.admin;

import java.net.URI;
import java.net.URISyntaxException;

import org.cweili.wray.util.BlogView;
import org.cweili.wray.web.BaseController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:39:07
 * 
 */
@Controller
public final class AdminDashboardController extends BaseController {

	@RequestMapping("/admin")
	public ResponseEntity<String> redirect() {
		HttpHeaders header = new HttpHeaders();
		try {
			header.setLocation(new URI("admin-dashboard"));
		} catch (URISyntaxException e) {
			log.error(e);
		}
		return new ResponseEntity<String>(header, HttpStatus.MOVED_PERMANENTLY);
	}

	@RequestMapping("/admin-dashboard")
	public BlogView dashboard() {
		BlogView v = new BlogView("dashboard");
		return v;
	}

}
