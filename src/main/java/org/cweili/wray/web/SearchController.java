package org.cweili.wray.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.cweili.wray.domain.BlogView;
import org.cweili.wray.domain.dto.Article;
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
 * 
 * @author cweili
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

		return new ResponseEntity<Object>(header, HttpStatus.MOVED_TEMPORARILY);
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

		Object obj = request.getAttribute(SEARCH_RESULT + searchid, WebRequest.SCOPE_SESSION);
		String keyword = (String) request.getAttribute(SEARCH_KEYWORD + searchid,
				WebRequest.SCOPE_SESSION);

		List<Article> articles;

		if (null != obj && obj instanceof List<?>) {
			articles = (List<Article>) obj;
		} else {
			throw new NotFoundException();
		}

		int pageSize = blogConfig.getInt("limit");

		int end = pageSize * page - 1;
		end = end < articles.size() ? end : articles.size();

		BlogView v = new BlogView("articles");
		v.add("title", blogConfig.get("searchLabel") + " \"" + keyword + '"');
		v.add("articles", articles.subList(pageSize * (page - 1), end));
		addPaginator(v, articles.size(), page);

		return v;
	}

	private String cacheSearchResult(WebRequest request, String keyword) throws NotFoundException {
		keyword = Function.escapeHtml(keyword);
		if ("".equals(keyword)) {
			throw new NotFoundException();
		}
		String searchId = Function.generateId();
		request.setAttribute(SEARCH_RESULT + searchId, articleService.findByKeyword(keyword),
				WebRequest.SCOPE_SESSION);
		request.setAttribute(SEARCH_KEYWORD + searchId, keyword, WebRequest.SCOPE_SESSION);
		return searchId;
	}
}
