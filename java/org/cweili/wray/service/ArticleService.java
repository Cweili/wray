package org.cweili.wray.service;

import java.sql.SQLException;
import java.util.List;

import org.cweili.wray.domain.Article;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:15:49
 *
 */
public interface ArticleService {
	
	/**
	 * @param type
	 * @param status
	 * @return
	 */
	public int getCountByTypeStatus(byte type, byte status);

	/**
	 * @param type
	 * @param status
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Article> getArticlesByTypeStatus(byte type, byte status, int page, int limit);
	
	/**
	 * @param id
	 * @return
	 */
	public Article getArticleById(long id);
	
	/**
	 * @param permalink
	 * @return
	 */
	public Article getArticleByPermalink(String permalink);

	/**
	 * @param article
	 * @return
	 * @throws SQLException
	 */
	public long save(Article article) throws SQLException;
	
	/**
	 * @param article
	 * @return
	 * @throws SQLException
	 */
	public boolean update(Article article) throws SQLException;
	
	/**
	 * @param article
	 * @return
	 * @throws SQLException
	 */
	public boolean updateHits(Article article) throws SQLException;
	
	/**
	 * @param article
	 * @return
	 * @throws SQLException
	 */
	public boolean remove(Article article) throws SQLException;
	
	/**
	 * @param ids
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	public boolean remove(List<Long> ids, byte type) throws SQLException;
	
	/**
	 * @param num
	 * @return
	 */
	public List<Article>getTopCommentArticles(int num);
	
	/**
	 * @param num
	 * @return
	 */
	public List<Article>getTopHitsArticles(int num);
	
	/**
	 * 
	 */
	public void updateArticleCache();
	
	/**
	 * 
	 */
	public void updateSidebarArticleCache();
}