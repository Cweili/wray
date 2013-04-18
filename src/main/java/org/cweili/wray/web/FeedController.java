package org.cweili.wray.web;

import java.util.Date;

import org.cweili.feed.atom.Atom;
import org.cweili.feed.rss.RSS;
import org.cweili.wray.util.Constant;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:47
 * 
 */
@Controller
@Scope("prototype")
public final class FeedController extends BaseController {

	@RequestMapping("/feed")
	public @ResponseBody
	String rss() {
		RSS rss = new RSS(blogConfig.get("blogTitle"), blogConfig.get("staticServePath"),
				blogConfig.get("staticServePath") + "atom", blogConfig.get("blogSubtitle"),
				Constant.GENERATOR, new Date());
		return rss.toString();
	}

	@RequestMapping("/atom")
	public @ResponseBody
	String atom() {
		Atom atom = new Atom(blogConfig.get("blogTitle"), blogConfig.get("blogSubtitle"),
				blogConfig.get("staticServePath"), blogConfig.get("adminNick"),
				blogConfig.get("staticServePath") + "atom", Constant.GENERATOR, new Date());
		return atom.toString();
	}

}
