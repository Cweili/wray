package org.cweili.wray.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.dao.ArticleDao;
import org.cweili.wray.dao.ConfigDao;
import org.cweili.wray.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {
	
	protected Log log = LogFactory.getLog(BaseService.class);

	@Autowired
	protected ArticleDao articleDao;
	
	@Autowired
	protected ConfigDao configDao;
	
	protected static List<Article> articles = new ArrayList<Article>();
	
	protected static List<Article> pages = new ArrayList<Article>();
	
	protected static List<Article> topCommentArticles = new ArrayList<Article>();

	protected static List<Article> topHitsArticles = new ArrayList<Article>();
	
	protected static int topCommentArticlesSize = 0;

	protected static int topHitsArticlesSize = 0;
	
	protected static int publishedArticleCount = 0;
	
}
