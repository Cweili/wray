package org.cweili.wray.dao;

import org.cweili.wray.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author cweili
 * @version 2013-4-3 上午11:19:43
 * 
 */
public interface ArticleDao extends BaseDao<Article> {

	/**
	 * @param isPage
	 * @param stat
	 * @return
	 */
	public long countByIsPageAndStat(byte isPage, byte stat);

	/**
	 * @param isPage
	 * @param stat
	 * @param page
	 * @return
	 */
	public Page<Article> findByIsPageAndStat(byte isPage, byte stat, Pageable page);

	/**
	 * @param permalink
	 * @param isPage
	 * @return
	 */
	public Article findByPermalinkAndIsPage(String permalink, byte isPage);

	// /**
	// * @param type
	// * @param status
	// * @return
	// */
	// public int getCountByTypeStatus(byte type, byte status);
	//
	// /**
	// * @param id
	// * @param type
	// * @param status
	// * @return
	// */
	// public int getCountByRelationship(long id, byte type, byte status);
	//
	// /**
	// * @param type
	// * @param status
	// * @param start
	// * @param limit
	// * @return
	// */
	// public List<Article> getArticles(byte type, byte status, int start, int
	// limit);
	//
	// /**
	// * @param type
	// * @param status
	// * @param start
	// * @param limit
	// * @param order
	// * @return
	// */
	// public List<Article> getArticles(byte type, byte status, int start, int
	// limit, String order);
	//
	// /**
	// * @param type
	// * @param status
	// * @param order
	// * @return
	// */
	// public List<Article> getMetas(byte type, byte status, String order);
	//
	// /**
	// * @param type
	// * @param status
	// * @param start
	// * @param limit
	// * @param order
	// * @return
	// */
	// public List<Article> getMetas(byte type, byte status, int start, int
	// limit, String order);
	//
	// /**
	// * @param id
	// * @param start
	// * @param limit
	// * @param order
	// * @return
	// */
	// public List<Article> getMetasByRelationship(long id, byte type, byte
	// status, int start,
	// int limit, String order);
	//
	// /**
	// * @param id
	// * @return
	// */
	// public Article getArticleById(long id);
	//
	// /**
	// * @param permalink
	// * @return
	// */
	// public Article getArticleByPermalink(String permalink);
	//
	// /**
	// * @param ids
	// * @param type
	// * @return
	// */
	// public int remove(List<Long> ids, byte type);
	//
	// /**
	// * @param article
	// * @param col
	// * @param type
	// * @param value
	// * @return
	// */
	// public int updateColumn(Article article, String col, int type, Object
	// value);
}
