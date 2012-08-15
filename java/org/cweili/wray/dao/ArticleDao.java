package org.cweili.wray.dao;

import java.util.List;

import org.cweili.wray.domain.Article;

public interface ArticleDao extends BaseDao<Article>{
	
	public int getCountByTypeStatus(byte type, byte status);
	
	public List<Article> getArticles(byte type, byte status, int start, int limit);
	
	public List<Article> getArticles(byte type, byte status, int start, int limit, String order);
	
	public List<Article> getMetas(byte type, byte status, String order);
	
	public List<Article> getMetas(byte type, byte status, int start, int limit, String order);
	
	public Article getArticleById(long id);
	
	public Article getArticleByPermalink(String permalink);
	
	public int remove(List<Long> ids, byte type);
	
	public int updateColumn(Article article, String col, int type, Object value);
}
