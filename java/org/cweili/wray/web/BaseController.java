package org.cweili.wray.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.service.ArticleService;
import org.cweili.wray.service.CategoryService;
import org.cweili.wray.service.ConfigService;
import org.cweili.wray.service.LinkService;
import org.cweili.wray.service.TagService;
import org.cweili.wray.service.UploadService;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:16
 * 
 */
public abstract class BaseController extends MultiActionController {

	protected Log log = LogFactory.getLog(BaseController.class);

	@Autowired
	protected ConfigService blogConfig;

	@Autowired
	protected ArticleService articleService;

	@Autowired
	protected CategoryService categoryService;

	@Autowired
	protected TagService tagService;

	@Autowired
	protected LinkService linkService;

	@Autowired
	protected UploadService uploadService;

	public abstract BlogView index(HttpServletRequest request, HttpServletResponse response);

	public abstract BlogView index(HttpServletRequest request, HttpServletResponse response, @PathVariable String str);

	/**
	 * @param v
	 * @param total
	 * @param current
	 */
	protected void addPaginator(BlogView v, int total, int current) {
		int limit = 10;
		try {
			limit = Integer.valueOf(blogConfig.get("limit"));
		} catch (Exception e) {
			log.error(e.toString());
		}
		Paginator pagination = new Paginator(total, limit, current);
		v.add("paginationOn", pagination.isPageBarOn());
		v.add("paginationPageNums", pagination.getPageList());
		v.add("paginationCurrentPageNum", current);
		v.add("paginationPreviousPageNum", pagination.getPrevious());
		v.add("paginationNextPageNum", pagination.getNext());
		v.add("paginationPageCount", pagination.getLast());
	}

	/**
	 * @param v
	 * @param total
	 * @param current
	 * @param limit
	 */
	protected void addPaginator(BlogView v, int total, int current, int limit) {
		Paginator pagination = new Paginator(total, limit, current);
		v.add("paginationOn", pagination.isPageBarOn());
		v.add("paginationPageNums", pagination.getPageList());
		v.add("paginationCurrentPageNum", current);
		v.add("paginationPreviousPageNum", pagination.getPrevious());
		v.add("paginationNextPageNum", pagination.getNext());
		v.add("paginationPageCount", pagination.getLast());
	}
}
