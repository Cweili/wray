package org.cweili.wray.web;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.cweili.wray.domain.BlogView;
import org.cweili.wray.domain.Page;
import org.cweili.wray.domain.dto.Article;
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
@RequestMapping("/archive")
public final class ArchiveController extends BaseController {

	@RequestMapping("/{year}-{month}")
	public BlogView archive(@PathVariable("year") String year, @PathVariable("month") String month) {
		return getArchiveView(year, month, 1);
	}

	@RequestMapping("/{year}-{month}/page-{page}")
	public BlogView archive(@PathVariable("year") String year, @PathVariable("month") String month,
			@PathVariable("page") String page) {
		return getArchiveView(year, month, Function.minimumPositiveInteger(page));
	}

	private BlogView getArchiveView(String year, String month, int page) {
		int y = Function.minimumInteger(year, Integer.valueOf(Constant.CURRENT_YEAR));
		int m = Function.minimumPositiveInteger(month) - 1;

		Calendar calendar = new GregorianCalendar();
		calendar.set(y, m, 1, 0, 0, 0);

		Page<Article> articles = articleService.findByMonth(calendar.getTime(), page,
				blogConfig.getInt("limit"));

		BlogView v = new BlogView("articles");
		v.add("title", year + " - " + month);
		v.add("articles", articles.getContent());

		addPaginator(v, articles);

		v.add("path", blogConfig.get("staticServePath") + "archive/" + year + "-" + month + "/");

		return v;
	}
}
