package org.cweili.wray.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.dao.ArticleDao;
import org.cweili.wray.dao.CommentDao;
import org.cweili.wray.dao.ConfigDao;
import org.cweili.wray.dao.ItemDao;
import org.cweili.wray.dao.RelationshipDao;
import org.cweili.wray.dao.UploadDao;
import org.cweili.wray.domain.Page;
import org.cweili.wray.domain.dto.Article;
import org.cweili.wray.domain.dto.Comment;
import org.cweili.wray.domain.dto.Item;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.HtmlFixer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * 基础 Service
 * 
 * @author Cweili
 * @version 2012-8-16 下午5:18:58
 * 
 */
public abstract class BaseService {

	protected static final Log log = LogFactory.getLog(BaseService.class);

	@Autowired
	protected ConfigDao configDao;

	@Autowired
	protected ArticleDao articleDao;

	@Autowired
	protected CommentDao commentDao;

	@Autowired
	protected ItemDao itemDao;

	@Autowired
	protected RelationshipDao relationshipDao;

	@Autowired
	protected UploadDao uploadDao;

	/**
	 * Config 缓存
	 */
	protected static Map<String, String> configMap = null;

	// protected static List<Article> articles = null;

	/**
	 * 页面缓存
	 */
	protected static Page<Article> pages = null;

	/**
	 * 最新评论
	 */
	protected static List<Comment> recentComments = null;

	/**
	 * 回复排行缓存
	 */
	protected static Page<Article> topCommentArticles = null;

	/**
	 * 点击排行缓存
	 */
	protected static Page<Article> topHitArticles = null;

	/**
	 * 最新评论数
	 */
	protected static int recentCommentsSize = 0;

	/**
	 * 回复排行数
	 */
	protected static int topCommentArticlesSize = 0;

	/**
	 * 点击排行数
	 */
	protected static int topHitArticlesSize = 0;

	/**
	 * 热门标签数
	 */
	protected static int mostUsedTagsSize = 0;

	/**
	 * Category 缓存
	 */
	protected static List<Item> categories = null;

	/**
	 * Tag 缓存
	 */
	protected static List<Item> tags = null;

	/**
	 * Link 缓存
	 */
	protected static List<Item> links = null;

	/**
	 * Navigator 缓存
	 */
	protected static List<Item> navigators = null;

	/**
	 * Archive 缓存
	 */
	protected static List<Article> archive = null;

	/**
	 * RSS 缓存
	 */
	protected static String rss = null;

	/**
	 * Atom 缓存
	 */
	protected static String atom = null;

	protected void updatePageCache() {
		pages = new Page<Article>(articleDao.findMetaByIsPageAndStat(Article.TYPE_PAGE,
				Article.STAT_PUBLISHED, new PageRequest(0, Constant.MAX_PAGE_SIZE,
						Sort.Direction.ASC, "hit")));
	}

	protected void updateSidebarArticleCache() {

		if (topCommentArticlesSize > 0) {
			topCommentArticles = new Page<Article>(articleDao.findMetaByIsPageAndStat(
					Article.TYPE_ARTICLE, Article.STAT_PUBLISHED, new PageRequest(0,
							topCommentArticlesSize, Sort.Direction.DESC, "commentCount")));
		}
		if (topHitArticlesSize > 0) {
			topHitArticles = new Page<Article>(articleDao.findMetaByIsPageAndStat(
					Article.TYPE_ARTICLE, Article.STAT_PUBLISHED, new PageRequest(0,
							topHitArticlesSize, Sort.Direction.DESC, "hit")));
		}
	}

	protected void updateArchiveCache() {
		if (null == archive) {
			archive = new LinkedList<Article>();
		} else {
			archive.clear();
		}
		HashMap<Date, Integer> months = new HashMap<Date, Integer>();
		Calendar calendar = Calendar.getInstance();
		for (Article article : articleDao.findByIsPageAndStat(Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED)) {
			calendar.setTime(article.getCreateTime());
			calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
			calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
			calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
			if (null != months.get(calendar.getTime())) {
				months.put(calendar.getTime(), months.get(calendar.getTime()) + 1);
			} else {
				months.put(calendar.getTime(), 1);
			}
		}
		for (Entry<Date, Integer> month : months.entrySet()) {
			Article article = new Article();
			article.setCreateTime(month.getKey());
			article.setHit(month.getValue());
			archive.add(article);
		}

		Collections.sort(archive);

	}

	protected void clearFeedCache() {
		rss = null;
		atom = null;
	}

	protected List<Article> dealArticleList(List<Article> list) {
		List<Article> articles = new ArrayList<Article>();
		for (Article article : list) {
			dealArticleContent(article);
			articles.add(article);
		}
		return articles;
	}

	protected Article dealArticleContent(Article article) {
		if (article.getContent().contains("<a name=\"more\"></a>")) {
			article.setContent(HtmlFixer.fix(StringUtils.substringBefore(article.getContent(),
					"<a name=\"more\"></a>")) + "<!--more-->");
		} else {
			if (article.getContent().length() >= 300) {
				article.setContent(HtmlFixer.substring(article.getContent(), 300) + "<!--more-->");
			}
		}
		return article;
	}

}
