package org.cweili.wray.dao;

import java.util.List;

import org.cweili.wray.domain.Article;

public interface ArticleDao extends BaseDao<Article>{
	
	public int getCountByTypeStatus(byte type, byte status);
	
	public List<Article> getArticlesByTypeStatus(byte type, byte status);

	public List<Article> getArticlesByTypeStatus(byte type, byte status, int start, int limit);
	
	public Article getArticleById(long id);
	
	public Article getArticleByPermalink(final String permalink);
	
	public int remove(final List<Long> ids, byte type);
	
	public int updateColumn(long id, String col, int type, Object value);
}
