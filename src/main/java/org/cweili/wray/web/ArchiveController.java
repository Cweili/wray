package org.cweili.wray.web;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.util.BlogView;
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

		int y = 2013;
		int m = 1;
		try {
			y = Integer.valueOf(year);
		} catch (Exception e) {
			log.error(e);
		}
		try {
			m = Integer.valueOf(month);
		} catch (Exception e) {
			log.error(e);
		}

		Calendar calendar = new GregorianCalendar();
		calendar.set(y, m, 1, 0, 0, 0);

		List<Article> articles = articleService.findByMonth(calendar.getTime(), 1,
				blogConfig.getInt("limit"));

		BlogView v = new BlogView("archive-articles");
		v.add("articles", articles);

		return v;
	}
}
