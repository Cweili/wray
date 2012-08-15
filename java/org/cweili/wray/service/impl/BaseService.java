package org.cweili.wray.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.dao.ArticleDao;
import org.cweili.wray.dao.ConfigDao;
import org.cweili.wray.dao.ItemDao;
import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {
	
	protected Log log = LogFactory.getLog(BaseService.class);

	@Autowired
	protected ConfigDao configDao;
	
	@Autowired
	protected ArticleDao articleDao;
	
	@Autowired
	protected ItemDao itemDao;
	
	// Config 缓存
	
	protected static Map<String, String> configMap = new HashMap<String, String>();
	
	// Article 缓存
	
//	protected static List<Article> articles = null;
	
	protected static List<Article> pages = null;
	
	protected static List<Article> topCommentArticles = new ArrayList<Article>();

	protected static List<Article> topHitsArticles = new ArrayList<Article>();
	
	protected static int topCommentArticlesSize = 0;

	protected static int topHitsArticlesSize = 0;
	
	protected static int publishedArticleCount = 0;
	
	// Category 缓存
	
	protected List<Item> categories = null;
	
	// Tag 缓存
	
	protected List<Item> tags = null;
	
	// Link 缓存
	
	protected List<Item> links = null;
	
}
