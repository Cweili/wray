package org.cweili.wray.web;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.cweili.wray.domain.BlogView;
import org.cweili.wray.domain.Page;
import org.cweili.wray.domain.dto.Article;
import org.cweili.wray.util.Constant;
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

	@RequestMapping("/{year:[\\d]+}-{month:[\\d]+}")
	public BlogView archive(@PathVariable("year") int year, @PathVariable("month") int month) {
		return getArchiveView(year, month, 1);
	}

	@RequestMapping("/{year:[\\d]+}-{month:[\\d]+}/page-{page:[\\d]+}")
	public BlogView archive(@PathVariable("year") int year, @PathVariable("month") int month,
			@PathVariable("page") int page) {
		page = page > 0 ? page : 1;
		return getArchiveView(year, month, page);
	}

	private BlogView getArchiveView(int year, int month, int page) {

		if (year < 1970) {
			year = Constant.CURRENT_YEAR;
		}

		if (month < 1 || month > 12) {
			month = Constant.CURRENT_MONTH;
		}

		Calendar calendar = new GregorianCalendar(year, month - 1, 1);
		Page<Article> articles = articleService.findByMonth(calendar.getTime(), page,
				blogConfig.getInt("pageSize"));

		BlogView v = new BlogView("articles");
		String archiveTime = year + "-" + (month < 10 ? "0" : "") + month;
		v.add("title", archiveTime);
		v.add("articles", articles.getContent());

		addPaginator(v, articles);

		v.add("path", blogConfig.get("staticServePath") + "archive/" + archiveTime + "/");

		return v;
	}
}
