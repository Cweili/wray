package org.cweili.wray.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.dao.ArticleDao;
import org.cweili.wray.dao.CommentDao;
import org.cweili.wray.dao.ConfigDao;
import org.cweili.wray.dao.ItemDao;
import org.cweili.wray.dao.RelationshipDao;
import org.cweili.wray.dao.UploadDao;
import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Comment;
import org.cweili.wray.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author cweili
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
	protected static List<Article> pages = null;

	/**
	 * 最新评论
	 */
	protected static List<Comment> recentComments = null;

	/**
	 * 回复排行缓存
	 */
	protected static List<Article> topCommentArticles = null;

	/**
	 * 点击排行缓存
	 */
	protected static List<Article> topHitsArticles = null;

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
	protected static int topHitsArticlesSize = 0;

	/**
	 * 热门标签数
	 */
	protected static int mostUsedTagsSize = 0;

	/**
	 * 已发布 Article 数
	 */
	protected static int publishedArticleCount = 0;

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
	 * Archive 缓存
	 */
	protected static List<Map.Entry<String, String>> archive = null;

	/**
	 * RSS 缓存
	 */
	protected static String rss = null;

	/**
	 * Atom 缓存
	 */
	protected static String atom = null;

}
