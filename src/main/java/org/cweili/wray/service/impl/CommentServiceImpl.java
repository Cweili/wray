package org.cweili.wray.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cweili.ipseeker.IPSeeker;
import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Comment;
import org.cweili.wray.service.CommentService;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.util.HtmlFixer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cweili
 * @version 2012-11-25 下午3:23:57
 * 
 */
@Service("commentService")
public class CommentServiceImpl extends BaseService implements CommentService {

	@Override
	public int count() {
		return (int) commentDao.count();
	}

	@Override
	public Comment findById(String commentId) {
		return commentDao.findOne(commentId);
	}

	@Override
	public List<Comment> findByArticle(Article article) {
		return dealArticleCommentList(commentDao.findByArticleIdAndStat(article.getArticleId(),
				Comment.STAT_DISPLAY, new PageRequest(0, 65535, Sort.Direction.ASC, "_id"))
				.getContent());
	}

	@Override
	public List<Comment> find(int page, int limit) {
		return dealCommentList(commentDao.findAll(
				new PageRequest(page - 1, limit, Sort.Direction.DESC, "_id")).getContent());
	}

	@Override
	public Comment save(Comment comment) {
		String author = Function.trimAndStripTags(comment.getAuthor());
		if ("".equals(author)) {
			return null;
		}
		String email = Function.trimAndStripTags(comment.getEmail());
		if ("".equals(email)) {
			return null;
		}
		String link = Function.trimAndStripTags(comment.getLink());
		String content = filterContent(comment.getContent());
		if ("".equals(content)) {
			return null;
		}

		comment.setAuthor(author);
		comment.setEmail(email);
		comment.setLink(link);
		comment.setContent(content);

		if (!"".equals(comment.getCommentId())) {
			if (null == commentDao.findOne(comment.getCommentId())) {
				Article article = articleDao.findOne(comment.getArticleId());
				if (null != article) {
					article.setCommentCount(article.getCommentCount() + 1);
					articleDao.save(article);
				} else {
					return null;
				}
			}
		} else {
			comment.setCommentId(Function.generateId());
		}

		return commentDao.save(comment);
	}

	@Override
	public boolean remove(List<String> ids) {
		boolean result = false;
		for (String id : ids) {
			Comment comment = commentDao.findOne(id);
			if (null != comment) {
				comment.setStat(Comment.STAT_BLOCK);
				commentDao.save(comment);
				result = true;
			}
		}
		return result;
	}

	private String filterContent(String content) {
		return HtmlFixer.fix(StringUtils.replace(
				Function.stripTags(content, Constant.DANGEROUS_TAGS), "javascript:", ""));
	}

	private List<Comment> dealArticleCommentList(List<Comment> before) {
		List<Comment> comments = new ArrayList<Comment>();
		Comment commentNew;
		for (Comment comment : before) {
			commentNew = new Comment(comment.getCommentId(), comment.getArticleId(),
					comment.getAuthor(), Function.md5(comment.getEmail()), comment.getLink(),
					comment.getIp(), comment.getPostDate(), comment.getAgent(),
					comment.getContent(), comment.getParentId(), comment.getStat());
			comments.add(commentNew);
		}
		return comments;
	}

	private List<Comment> dealCommentList(List<Comment> before) {
		List<Comment> comments = new ArrayList<Comment>();
		for (Comment comment : before) {
			String area = IPSeeker.getInstance().getArea(comment.getIp());
			if (!"数据库损坏".equals(area)) {
				StringBuilder sb = new StringBuilder(IPSeeker.getInstance().getCountry(
						comment.getIp()));
				if (!" CZ88.NET".equals(area)) {
					sb.append(" ").append(area);
					log.error(area);
				}
				comment.setOrigin(sb.toString());
			}
			comment.setContent(StringUtils.substring(Function.stripTags(comment.getContent()), 0,
					16) + " ...");
			Article article = articleDao.findOne(comment.getArticleId());
			if (null != article) {
				comment.setArtilceTitle(article.getTitle());
			}
			comments.add(comment);
		}
		return comments;
	}
}
