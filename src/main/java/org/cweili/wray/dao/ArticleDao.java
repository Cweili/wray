package org.cweili.wray.dao;

import java.util.Date;
import java.util.List;

import org.cweili.wray.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

/**
 * 
 * @author cweili
 * @version 2013-4-3 上午11:19:43
 * 
 */
public interface ArticleDao extends BaseDao<Article> {

	public static final String VALUE_ISPAGE_STAT = "{ 'isPage': ?0, 'stat': ?1 }";

	public static final String FIELD_META = "{ 'title': 1, 'permalink': 1, 'tag': 1, "
			+ "'createTime': 1, 'stat': 1, 'hits': 1, 'commentCount': 1, "
			+ "'commentStatus': 1, 'isPage': 1 }";

	@Query(value = VALUE_ISPAGE_STAT, fields = FIELD_META)
	public List<Article> findMetaByIsPageAndStat(byte isPage, byte stat);

	/**
	 * @param isPage
	 * @param stat
	 * @return
	 */
	public List<Article> findByIsPageAndStat(byte isPage, byte stat);

	@Query(value = VALUE_ISPAGE_STAT, fields = FIELD_META)
	public Page<Article> findMetaByIsPageAndStat(byte isPage, byte stat, Pageable page);

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
	public Article findByPermalinkAndIsPageAndStat(String permalink, byte isPage, byte stat);

	public Page<Article> findByIsPageAndStatAndCreateTimeBetween(byte isPage, byte stat, Date from,
			Date to, Pageable page);

	public List<Article> findByStatAndTitleLike(byte stat, String title);

	public List<Article> findByStatAndContentLike(byte stat, String content);

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
