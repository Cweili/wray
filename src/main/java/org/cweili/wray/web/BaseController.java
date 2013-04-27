package org.cweili.wray.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.domain.BlogView;
import org.cweili.wray.domain.Page;
import org.cweili.wray.domain.Paginator;
import org.cweili.wray.service.ArticleService;
import org.cweili.wray.service.CategoryService;
import org.cweili.wray.service.CommentService;
import org.cweili.wray.service.ConfigService;
import org.cweili.wray.service.FeedService;
import org.cweili.wray.service.LinkService;
import org.cweili.wray.service.TagService;
import org.cweili.wray.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:36:16
 * 
 */
public abstract class BaseController extends MultiActionController {

	protected static final Log log = LogFactory.getLog(BaseController.class);

	@Autowired
	protected ConfigService blogConfig;

	@Autowired
	protected ArticleService articleService;

	@Autowired
	protected CommentService commentService;

	@Autowired
	protected CategoryService categoryService;

	@Autowired
	protected TagService tagService;

	@Autowired
	protected LinkService linkService;

	@Autowired
	protected UploadService uploadService;

	@Autowired
	protected FeedService feedService;

	/**
	 * @param v
	 * @param page
	 */
	protected void addPaginator(BlogView v, Page<?> page) {
		addPaginator(v, page.getTotal(), page.getNumber() + 1);
	}

	/**
	 * @param v
	 * @param total
	 * @param current
	 */
	protected void addPaginator(BlogView v, int total, int current) {
		int size = blogConfig.getInt("pageSize");
		Paginator pagination = new Paginator(total, size, current);
		v.add("paginationOn", pagination.isPageBarOn());
		v.add("paginationPageNums", pagination.getPageList());
		v.add("paginationCurrentPageNum", current);
		v.add("paginationPreviousPageNum", pagination.getPrevious());
		v.add("paginationNextPageNum", pagination.getNext());
		v.add("paginationPageCount", pagination.getLast());
	}

	/**
	 * @param message
	 * @return
	 */
	protected String multipartErrorMessage(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toString();
	}
}
