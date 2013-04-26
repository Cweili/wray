package org.cweili.wray.web;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:09
 * 
 */
@Controller
public final class ArchiveController extends BaseController {

	@RequestMapping("/archive/{year}-{month}")
	public BlogView archive(@PathVariable("year") String year, @PathVariable("month") String month) {
		return getArchiveView(year, month, 1);
	}

	@RequestMapping("/archive/{year}-{month}/page-{page}")
	public BlogView archive(@PathVariable("year") String year, @PathVariable("month") String month,
			@PathVariable("page") String page) {
		return getArchiveView(year, month, Function.minimumPositiveInteger(page));
	}

	private BlogView getArchiveView(String year, String month, int page) {
		int y = Function.minimumInteger(year, Integer.valueOf(Constant.CURRENT_YEAR));
		int m = Function.minimumPositiveInteger(month) - 1;

		Calendar calendar = new GregorianCalendar();
		calendar.set(y, m, 1, 0, 0, 0);

		List<Article> articles = articleService.findByMonth(calendar.getTime(), page,
				blogConfig.getInt("limit"));

		BlogView v = new BlogView("articles");
		v.add("title", year + " - " + month);
		v.add("articles", articles);

		addPaginator(v, articleService.countByMonth(calendar.getTime()), page);

		v.add("path", blogConfig.get("StaticServePath") + "tag/" + year + "-" + month + "/");

		return v;
	}
}
