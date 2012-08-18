package org.cweili.wray.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.dao.ArticleDao;
import org.cweili.wray.dao.ConfigDao;
import org.cweili.wray.dao.ItemDao;
import org.cweili.wray.dao.RelationshipDao;
import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:18:58
 *
 */
public abstract class BaseService {
	
	protected Log log = LogFactory.getLog(BaseService.class);

	@Autowired
	protected ConfigDao configDao;
	
	@Autowired
	protected ArticleDao articleDao;
	
	@Autowired
	protected ItemDao itemDao;
	
	@Autowired
	protected RelationshipDao relationshipDao;
	
	
	/**
	 * Config 缓存
	 */
	protected static Map<String, String> configMap = null;
	
//	protected static List<Article> articles = null;
	
	/**
	 * Article 缓存
	 */
	protected static List<Article> pages = null;
	
	/**
	 * 回复排行缓存
	 */
	protected static List<Article> topCommentArticles = null;

	/**
	 * 点击排行缓存
	 */
	protected static List<Article> topHitsArticles = null;
	
	/**
	 * 回复排行数
	 */
	protected static int topCommentArticlesSize = 0;

	/**
	 * 点击排行数
	 */
	protected static int topHitsArticlesSize = 0;
	
	/**
	 * 已发布 Article 数
	 */
	protected static int publishedArticleCount = 0;
	
	/**
	 * Category 缓存
	 */
	protected List<Item> categories = null;
	
	/**
	 * Tag 缓存
	 */
	protected List<Item> tags = null;
	
	/**
	 * Link 缓存
	 */
	protected List<Item> links = null;
	
}
