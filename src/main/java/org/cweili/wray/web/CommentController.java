/**
 * 
 */
package org.cweili.wray.web;

import java.net.URI;
import java.net.URISyntaxException;

import org.cweili.wray.domain.Comment;
import org.cweili.wray.util.Function;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @author cweili
 * @version 2012-9-5 下午9:31:12
 * 
 */
@Controller
@Scope("prototype")
public final class CommentController extends BaseController {

	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public ResponseEntity<String> index(@RequestParam("articleId") String articleId,
			@RequestParam("author") String author, @RequestHeader("User-Agent") String userAgent,
			@RequestParam("content") String content, @RequestParam("email") String email,
			@RequestParam("link") String link, @RequestParam("permalink") String permalink) {
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
		comment.setStat(Comment.STAT_DISPLAY);
		commentService.save(comment);
		HttpHeaders header = new HttpHeaders();

		try {
			header.setLocation(new URI(blogConfig.get("staticServePath") + "article/" + permalink
					+ "#comment-" + id));
		} catch (URISyntaxException e) {
			log.error(e, e);
		}

		return new ResponseEntity<String>(header, HttpStatus.MOVED_PERMANENTLY);
	}
}
