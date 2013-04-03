/**
 * 
 */
package org.cweili.wray.service;

import java.util.List;

import org.cweili.wray.domain.Article;

/**
 * 
 * @author cweili
 * @version 2013-4-3 下午2:09:36
 * 
 */
public interface PageService {

	public int countByTypeStatus(byte type, byte status);

	public int countByRelationship(String itemId, byte type, byte status);

	public List<Article> findByTypeStatus(byte type, byte status, int page, int size);

	public List<Article> findByRelationship(String itemId, byte type, byte status, int page,
			int size);

	public Article findById(String articleId);

	public Article findByPermalink(String permalink, byte type);

	public Article save(Article article);

	public boolean updateHits(Article article);

	public boolean remove(Article article);

	public boolean updateStatus(List<String> ids, byte status);

	public List<Article> getTopCommentArticles(int num);

	public List<Article> getTopHitsArticles(int num);

	public void updateArticleCache();
}
