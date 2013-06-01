package org.cweili.wray.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.BlogView;
import org.cweili.wray.entity.Article;
import org.cweili.wray.util.Function;
import org.cweili.wray.util.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * 搜索 Controller
 * 
 * @author Cweili
 * @version 2012-8-16 下午5:36:09
 * 
 */
@Controller
public final class SearchController extends BaseController {

	private static final String SEARCH_RESULT = "s-";
	private static final String SEARCH_KEYWORD = "k-";

	@RequestMapping("/search")
	public ResponseEntity<Object> searchPost(WebRequest request) throws URISyntaxException,
			NotFoundException {

		String searchId = cacheSearchResult(request, request.getParameter("k"));

		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI("search-" + searchId));

		return new ResponseEntity<Object>(header, HttpStatus.MOVED_PERMANENTLY);
	}

	@RequestMapping("/search-{searchid}")
	public BlogView search(WebRequest request, @PathVariable("searchid") String searchid)
			throws NotFoundException {
		return getSearchView(request, searchid, 1);
	}

	@RequestMapping("/search-{searchid}/page-{page}")
	public BlogView search(WebRequest request, @PathVariable("searchid") String searchid,
			@PathVariable("page") String page) throws NotFoundException {
		int p = Function.minimumPositiveInteger(page);
		return getSearchView(request, searchid, p);
	}

	private BlogView getSearchView(WebRequest request, String searchid, int page)
			throws NotFoundException {

		Object resultObject = request.getAttribute(SEARCH_RESULT + searchid,
				WebRequest.SCOPE_SESSION);

		Article[] articles;

		if (null != resultObject && resultObject instanceof Article[]) {
			articles = (Article[]) resultObject;
		} else {
			throw new NotFoundException();
		}

		String keyword = (String) request.getAttribute(SEARCH_KEYWORD + searchid,
				WebRequest.SCOPE_SESSION);

		int pageSize = blogConfig.getInt("pageSize");

		int end = pageSize * page - 1;
		end = end < articles.length ? end : articles.length;

		BlogView v = new BlogView("articles");
		v.add("title", blogConfig.get("searchLabel") + " \"" + keyword + '"');
		v.add("articles", Arrays.copyOfRange(articles, pageSize * (page - 1), end));
		addPaginator(v, articles.length, page);
		v.add("path", blogConfig.get("staticServePath") + "search-" + searchid + "/");

		return v;
	}

	private String cacheSearchResult(WebRequest request, String keyword) throws NotFoundException {
		keyword = Function.escapeHtml(keyword);
		if (StringUtils.isEmpty(keyword)) {
			throw new NotFoundException();
		}
		String searchId = Function.generateId();
		String[] keywords = searchService.segmentKeyword(keyword);
		request.setAttribute(SEARCH_RESULT + searchId, searchService.findByKeyword(keywords),
				WebRequest.SCOPE_SESSION);
		request.setAttribute(SEARCH_KEYWORD + searchId, keyword, WebRequest.SCOPE_SESSION);
		return searchId;
	}
}
