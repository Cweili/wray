package org.cweili.wray.service;

import java.util.Date;
import java.util.List;

import org.cweili.wray.domain.Article;

/**
 * 
 * @author cweili
 * @version 2013-4-3 下午12:52:59
 * 
 */
public interface ArticleService {

	public int countByTypeStatus(byte type, byte status);

	public int countByRelationship(String itemId, byte type, byte status);

	public int countByMonth(Date month);

	public List<Article> findByTypeStatus(byte type, byte status, int page, int size);

	public List<Article> findByRelationship(String itemId, byte type, byte status, int page,
			int size);

	public List<Article> findByMonth(Date month, int page, int size);

	public Article findById(String articleId);

	public Article findByPermalink(String permalink, byte type);

	public Article save(Article article);

	public boolean updateHit(Article article);

	public boolean remove(Article article);

	public boolean updateStatus(List<String> ids, byte status);

	public List<Article> getTopCommentArticles(int size);

	public List<Article> getTopHitArticles(int size);

	public List<Article> getArchive();

	public void updateArticleCache();

	public void updateSidebarArticleCache();

	public void updateArchiveCache();
}