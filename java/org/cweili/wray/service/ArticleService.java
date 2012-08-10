package org.cweili.wray.service;

import java.sql.SQLException;
import java.util.List;

import org.cweili.wray.domain.Article;

public interface ArticleService {
	
	public int getCountByTypeStatus(byte type, byte status);

	public List<Article> getArticlesByTypeStatus(byte type, byte status, int page, int limit);
	
	public Article getArticleById(long id);
	
	public Article getArticleByPermalink(String permalink);

	public long save(Article article) throws SQLException;
	
	public boolean update(Article article) throws SQLException;
	
	public boolean updateHitsCommentCount(Article article) throws SQLException;
	
	public boolean remove(Article article) throws SQLException;
	
	public boolean remove(List<Long> ids, byte type) throws SQLException;
	
	public List<Article>getTopCommentArticles(int num);
	
	public List<Article>getTopHitsArticles(int num);
	
	public void updateArticleCache();
	
	public void updateSidebarArticleCache();
}
