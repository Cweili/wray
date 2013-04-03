package org.cweili.wray.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Relationship;
import org.cweili.wray.service.ArticleService;
import org.cweili.wray.util.Function;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:17:58
 * 
 */
@Service("articleService")
public class ArticleServiceImpl extends BaseService implements ArticleService {

	@Override
	public int countByTypeStatus(byte type, byte status) {

		if (type == Article.TYPE_ARTICLE && status == Article.STAT_PUBLISHED
				&& publishedArticleCount == 0) {
			updateArticleCache();
		} else if (type != Article.TYPE_ARTICLE || status != Article.STAT_PUBLISHED) {
			return (int) articleDao.countByIsPageAndStat(type, status);
		}
		return publishedArticleCount;
	}

	@Override
	public int countByRelationship(String itemId, byte type, byte status) {
		List<Relationship> relationships = relationshipDao.findByArticleId(itemId);
		List<Article> articles = new ArrayList<Article>();
		Article article;
		for (Relationship relationship : relationships) {
			article = articleDao.findOne(relationship.getArticleId());
			if (null != article && type == article.getIsPage() && status == article.getStat()) {
				articles.add(articleDao.findOne(relationship.getArticleId()));
			}
		}
		return articles.size();
	}

	@Override
	public List<Article> findByTypeStatus(byte type, byte status, int page, int size) {
		if (type == Article.TYPE_ARTICLE && status == Article.STAT_PUBLISHED) {
			return dealList(findByTypeStatusInDao(type, status, page, size));
		} else if (type == Article.TYPE_ARTICLE) {
			return findByTypeStatusInDao(type, status, page, size);
		} else {
			if (size < 1 && status == Article.STAT_PUBLISHED) {
				if (pages == null) {
					updateArticleCache();
					return pages;
				}
				return pages;
			} else {
				return articleDao.findByIsPageAndStat(type, status,
						new PageRequest(page, size, new Sort(Sort.Direction.ASC, "hits")))
						.getContent();
			}
		}
	}

	@Override
	public List<Article> findByRelationship(String itemId, byte type, byte status, int page,
			int size) {
		page = page > 0 ? page : 1;
		size = size > 0 ? size : 1;
		List<Relationship> relationships = relationshipDao.findByArticleId(itemId);
		List<Article> articles = new LinkedList<Article>();
		Article article;
		for (Relationship relationship : relationships) {
			article = articleDao.findOne(relationship.getArticleId());
			if (null != article && type == article.getIsPage() && status == article.getStat()) {
				articles.add(articleDao.findOne(relationship.getArticleId()));
			}
		}
		Collections.sort(articles);
		int end = size * page - 1;
		end = end < articles.size() ? end : articles.size() - 1;
		return articles.subList(size * (page - 1), end);
	}

	@Override
	public Article findById(String articleId) {
		return articleDao.findOne(articleId);
	}

	@Override
	public Article findByPermalink(String permalink, byte type) {
		return articleDao.findByPermalinkAndIsPage(permalink, type);
	}

	@Override
	public Article save(Article article) {
		Article articleNew = articleDao.save(article);
		if (null != articleNew && articleNew.getStat() == Article.STAT_PUBLISHED) {
			updateArticleCache();
			updateSidebarArticleCache();
		}
		return articleNew;
	}

	@Override
	public boolean updateHits(Article article) {
		article.setHits(article.getHits() + 1);
		Article articleNew = articleDao.save(article);
		return null != articleNew;
	}

	@Override
	public boolean remove(Article article) {
		article.setStat(Article.STAT_REMOVED);
		Article articleNew = articleDao.save(article);
		if (null != articleNew) {
			updateArticleCache();
			updateSidebarArticleCache();
		}
		return null != articleNew;
	}

	@Override
	public boolean updateStatus(List<String> ids, byte status) {
		Iterable<Article> articles = articleDao.findAll(ids);
		for (Article article : articles) {
			article.setStat(status);
		}
		articles = articleDao.save(articles);
		if (null != articles) {
			updateArticleCache();
			updateSidebarArticleCache();
		}
		return null != articles;
	}

	@Override
	public List<Article> getTopCommentArticles(int num) {
		if (topCommentArticlesSize != num) {
			topCommentArticlesSize = num;
			updateSidebarArticleCache();
		}
		return topCommentArticles;
	}

	@Override
	public List<Article> getTopHitsArticles(int num) {
		if (topHitsArticlesSize != num) {
			topHitsArticlesSize = num;
			updateSidebarArticleCache();
		}
		return topHitsArticles;
	}

	@Override
	public void updateArticleCache() {
		pages = articleDao.findByIsPageAndStat(Article.TYPE_PAGE, Article.STAT_PUBLISHED,
				new PageRequest(1, 65535, new Sort(Sort.Direction.ASC, "hits"))).getContent();
		publishedArticleCount = (int) articleDao.countByIsPageAndStat(Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED);
	}

	@Override
	public void updateSidebarArticleCache() {

		topCommentArticles = articleDao.findByIsPageAndStat(
				Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED,
				new PageRequest(1, topCommentArticlesSize, new Sort(Sort.Direction.DESC,
						"comment_count"))).getContent();
		topHitsArticles = articleDao.findByIsPageAndStat(Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED,
				new PageRequest(1, topHitsArticlesSize, new Sort(Sort.Direction.DESC, "hits")))
				.getContent();
	}

	/**
	 * @param type
	 * @param status
	 * @param page
	 * @param size
	 * @return
	 */
	private List<Article> findByTypeStatusInDao(byte type, byte status, int page, int size) {
		return articleDao.findByIsPageAndStat(type, status,
				new PageRequest(page, size, new Sort(Sort.Direction.DESC, "_id"))).getContent();
	}

	/**
	 * @param list
	 * @return
	 */
	private List<Article> dealList(List<Article> list) {
		Article a;
		for (int i = 0; i < list.size(); ++i) {
			a = list.get(i);
			if (a.getContent().contains("<a name=\"more\"></a>")) {
				a.setContent(Function.stripTags(a.getContent().substring(0,
						a.getContent().indexOf("<a name=\"more\"></a>") - 1))
						+ "<!--more-->");
				list.set(i, a);
			} else {
				a.setContent(Function.stripTags(a.getContent()));
				if (a.getContent().length() >= 300) {
					a.setContent(a.getContent().substring(0, 300) + "<!--more-->");
				}
			}
			list.set(i, a);
		}
		return list;
	}

}
