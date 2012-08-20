package org.cweili.wray.dao;

import java.util.List;

import org.cweili.wray.domain.Article;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午4:55:00
 * 
 */
public interface ArticleDao extends BaseDao<Article> {

	/**
	 * @param type
	 * @param status
	 * @return
	 */
	public int getCountByTypeStatus(byte type, byte status);

	/**
	 * @param id
	 * @param type
	 * @param status
	 * @return
	 */
	public int getCountByRelationship(long id, byte type, byte status);

	/**
	 * @param type
	 * @param status
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Article> getArticles(byte type, byte status, int start, int limit);

	/**
	 * @param type
	 * @param status
	 * @param start
	 * @param limit
	 * @param order
	 * @return
	 */
	public List<Article> getArticles(byte type, byte status, int start, int limit, String order);

	/**
	 * @param type
	 * @param status
	 * @param order
	 * @return
	 */
	public List<Article> getMetas(byte type, byte status, String order);

	/**
	 * @param type
	 * @param status
	 * @param start
	 * @param limit
	 * @param order
	 * @return
	 */
	public List<Article> getMetas(byte type, byte status, int start, int limit, String order);

	/**
	 * @param id
	 * @param start
	 * @param limit
	 * @param order
	 * @return
	 */
	public List<Article> getMetasByRelationship(long id, byte type, byte status, int start,
			int limit, String order);

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
	 * @param ids
	 * @param type
	 * @return
	 */
	public int remove(List<Long> ids, byte type);

	/**
	 * @param article
	 * @param col
	 * @param type
	 * @param value
	 * @return
	 */
	public int updateColumn(Article article, String col, int type, Object value);
}
