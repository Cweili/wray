package org.cweili.wray.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cweili.ipseeker.IPSeeker;
import org.cweili.wray.domain.Page;
import org.cweili.wray.domain.dto.Article;
import org.cweili.wray.domain.dto.Comment;
import org.cweili.wray.service.CommentService;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.CutString;
import org.cweili.wray.util.Function;
import org.cweili.wray.util.HtmlFixer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 评论 Service 实现
 * 
 * @author Cweili
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
		return commentDao.findByArticleIdAndStat(article.getArticleId(), Comment.STAT_DISPLAY,
				new Sort(Sort.Direction.ASC, "_id"));
	}

	@Override
	public Page<Comment> find(int page, int size) {
		Page<Comment> pagedComments = new Page<Comment>(commentDao.findAll(new PageRequest(
				page - 1, size, Sort.Direction.DESC, "_id")));
		return new Page<Comment>(dealCommentList(pagedComments.getContent()), pagedComments);
	}

	@Override
	public Comment save(Comment comment) {
		String author = Function.escapeHtml(comment.getAuthor());
		if (StringUtils.isEmpty(author)) {
			return null;
		}
		String email = Function.escapeHtml(comment.getEmail());
		if (StringUtils.isEmpty(email)) {
			return null;
		}
		String link = Function.escapeHtml(comment.getLink());
		String content = filterContent(comment.getContent());
		if (StringUtils.isEmpty(content)) {
			return null;
		}

		comment.setAuthor(author);
		comment.setEmail(email);
		comment.setLink(link);
		comment.setContent(content);

		if (!StringUtils.isEmpty(comment.getCommentId())) {
			if (null == commentDao.findOne(comment.getCommentId())) {
				Article article = articleDao.findOne(comment.getArticleId());
				if (null != article && Article.COMMENT_ON == article.getCommentStatus()) {
					article.setCommentCount(article.getCommentCount() + 1);
					articleDao.save(article);
				} else {
					return null;
				}
			}
		} else {
			comment.setCommentId(Function.generateId());
		}

		Comment commentNew = commentDao.save(comment);

		if (null != commentNew) {
			updateRecentComments();
		}
		return commentNew;
	}

	@Override
	public boolean switchStat(List<String> ids) {
		boolean result = false;
		for (String id : ids) {
			Comment comment = commentDao.findOne(id);
			if (null != comment) {
				comment.setStat(comment.getStat() == Comment.STAT_DISPLAY ? Comment.STAT_BLOCK
						: Comment.STAT_DISPLAY);
				commentDao.save(comment);
				result = true;
			}
		}
		return result;
	}

	@Override
	public Article findArticleByCommentId(String commentId) {
		Comment comment = findById(commentId);
		if (null != comment) {
			return articleDao.findOne(comment.getArticleId());
		} else {
			return null;
		}
	}

	@Override
	public List<Comment> getRecentComments(int size) {
		if (size >= 0 && recentCommentsSize != size) {
			recentCommentsSize = size;
			updateRecentComments();
		}
		return recentComments;
	}

	@Override
	public void updateRecentComments() {
		if (recentCommentsSize > 0) {
			if (null != recentComments) {
				recentComments.clear();
			} else {
				recentComments = new ArrayList<Comment>(recentCommentsSize);
			}
			Comment commentNew;
			for (Comment comment : commentDao.findAll(
					new PageRequest(0, recentCommentsSize, Sort.Direction.DESC, "_id"))
					.getContent()) {
				commentNew = new Comment(comment);
				commentNew.setContent(CutString.substring(
						Function.stripTags(commentNew.getContent()), 50)
						+ " ...");
				recentComments.add(commentNew);
			}
		}
	}

	private String filterContent(String content) {
		return HtmlFixer.fix(StringUtils.replace(
				Function.stripTags(content, Constant.DANGEROUS_TAGS), "javascript:", ""));
	}

	private List<Comment> dealCommentList(List<Comment> before) {
		List<Comment> comments = new ArrayList<Comment>(before.size());
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
			comment.setContent(CutString.substring(Function.stripTags(comment.getContent()), 16)
					+ " ...");
			Article article = articleDao.findOne(comment.getArticleId());
			if (null != article) {
				comment.setArtilceTitle(article.getTitle());
			}
			comments.add(comment);
		}
		return comments;
	}

}
