/**
 * 
 */
package org.cweili.wray.web;

import java.net.URI;
import java.net.URISyntaxException;

import org.cweili.wray.domain.dto.Article;
import org.cweili.wray.domain.dto.Comment;
import org.cweili.wray.util.Captcha;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.util.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author cweili
 * @version 2012-9-5 下午9:31:12
 * 
 */
@Controller
public final class CommentController extends BaseController {

	@RequestMapping(value = "/comment-{commentid}")
	public ResponseEntity<String> get(@PathVariable String commentid) throws NotFoundException {
		Article article = commentService.findArticleByCommentId(commentid);
		if (null == article) {
			throw new NotFoundException();
		}
		HttpHeaders header = new HttpHeaders();
		try {
			if (Article.TYPE_ARTICLE == article.getIsPage()) {
				header.setLocation(new URI("article/" + article.getPermalink() + "#comment-"
						+ commentid));
			} else {
				header.setLocation(new URI("page/" + article.getPermalink() + "#comment-"
						+ commentid));
			}
		} catch (URISyntaxException e) {
			log.error(e);
			try {
				header.setLocation(new URI(blogConfig.get("staticServePath")));
			} catch (URISyntaxException e1) {
			}
		}
		return new ResponseEntity<String>(header, HttpStatus.MOVED_PERMANENTLY);
	}

	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public @ResponseBody
	String post(WebRequest request, @RequestParam("articleId") String articleId,
			@RequestParam("author") String author, @RequestHeader("User-Agent") String userAgent,
			@RequestParam("content") String content, @RequestParam("email") String email,
			@RequestParam("link") String link, @RequestParam("parentId") String parentId,
			@RequestParam("permalink") String permalink,
			@RequestParam(Constant.CAPTCHA) String captcha) {
		if (!captcha.toUpperCase().equals(
				request.getAttribute(Constant.CAPTCHA, WebRequest.SCOPE_SESSION))) {
			request.setAttribute(Constant.CAPTCHA, Captcha.getRandomString(6),
					WebRequest.SCOPE_SESSION);
			return Constant.CAPTCHA;
		}
		request.setAttribute(Constant.CAPTCHA, Captcha.getRandomString(6), WebRequest.SCOPE_SESSION);
		Article article = articleService.findById(articleId);
		if (null == article && "".equals(content)) {
			request.setAttribute(Constant.CAPTCHA, Captcha.getRandomString(6),
					WebRequest.SCOPE_SESSION);
			return "error";
		}
		String id = Function.generateId();
		Comment comment = new Comment();
		comment.setCommentId(id);
		comment.setArticleId(articleId);
		comment.setAuthor(author);
		comment.setAgent(userAgent);
		comment.setContent(content);
		comment.setEmail(email);
		comment.setIp(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest().getRemoteAddr());
		comment.setLink(link);
		comment.setParentId(parentId);
		comment.setStat(Comment.STAT_DISPLAY);
		commentService.save(comment);

		return "comment-" + id;
	}

}
