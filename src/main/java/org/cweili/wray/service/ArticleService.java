package org.cweili.wray.service;

import java.util.Date;
import java.util.List;

import org.cweili.wray.domain.Page;
import org.cweili.wray.entity.Article;

/**
 * 文章 Service
 * 
 * @author Cweili
 * @version 2013-4-3 下午12:52:59
 * 
 */
public interface ArticleService {

	public Page<Article> findByTypeStatus(byte type, byte status, int page, int size);

	public Page<Article> findByRelationship(String itemId, byte type, byte status, int page,
			int size);

	public Page<Article> findByMonth(Date month, int page, int size);

	public Article findById(String articleId);

	public Article findByPermalink(String permalink, byte type);

	public Article save(Article article);

	public boolean updateHit(Article article);

	public boolean remove(Article article);

	public boolean updateStatus(List<String> ids, byte status);

	public List<Article> getTopCommentArticles(int size);

	public List<Article> getTopHitArticles(int size);

	public List<Article> getArchive();

}