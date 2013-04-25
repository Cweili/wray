package org.cweili.wray.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Relationship;
import org.cweili.wray.service.ArticleService;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.util.HtmlFixer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cweili
 * @version 2013-4-3 下午3:41:01
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
			return articleDao.findMetaByIsPageAndStat(type, status).size();
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
						new PageRequest(page - 1, size, Sort.Direction.ASC, "hits")).getContent();
			}
		}
	}

	@Override
	public List<Article> findByRelationship(String itemId, byte type, byte status, int page,
			int size) {
		page = page > 0 ? page : 1;
		size = size > 0 ? size : 1;
		List<Relationship> relationships = relationshipDao.findByItemId(itemId);
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
		end = end < articles.size() ? end : articles.size();
		return articles.subList(size * (page - 1), end);
	}

	@Override
	public List<Article> findByMonth(Date month, int page, int size) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Article findById(String articleId) {
		Article article = articleDao.findOne(articleId);
		return article;
	}

	@Override
	public Article findByPermalink(String permalink, byte type) {
		Article article = articleDao.findByPermalinkAndIsPageAndStat(permalink, type,
				Article.STAT_PUBLISHED);
		return article;
	}

	@Override
	public Article save(Article article) {
		if ("".equals(article.getArticleId())) {
			article.setArticleId(Function.generateId());
		}
		Article articleNew = articleDao.save(article);
		if (null != articleNew && articleNew.getStat() == Article.STAT_PUBLISHED) {
			updateArticleCache();
			updateSidebarArticleCache();
		}
		return articleNew;
	}

	@Override
	public boolean updateHits(Article article) {
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
	public List<Article> getTopCommentArticles(int size) {
		if (size > 0 && topCommentArticlesSize != size) {
			topCommentArticlesSize = size;
			updateSidebarArticleCache();
		}
		return topCommentArticles;
	}

	@Override
	public List<Article> getTopHitsArticles(int size) {
		if (size > 0 && topHitsArticlesSize != size) {
			topHitsArticlesSize = size;
			updateSidebarArticleCache();
		}
		return topHitsArticles;
	}

	@Override
	public void updateArticleCache() {
		pages = articleDao.findMetaByIsPageAndStat(Article.TYPE_PAGE, Article.STAT_PUBLISHED,
				new PageRequest(0, Constant.MAX_PAGE, Sort.Direction.ASC, "hits")).getContent();
		publishedArticleCount = articleDao.findMetaByIsPageAndStat(Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED).size();
		rss = null;
		atom = null;
	}

	@Override
	public void updateSidebarArticleCache() {

		if (topCommentArticlesSize > 0) {
			topCommentArticles = articleDao
					.findMetaByIsPageAndStat(
							Article.TYPE_ARTICLE,
							Article.STAT_PUBLISHED,
							new PageRequest(0, topCommentArticlesSize, Sort.Direction.DESC,
									"commentCount")).getContent();
		}
		if (topHitsArticlesSize > 0) {
			topHitsArticles = articleDao.findMetaByIsPageAndStat(Article.TYPE_ARTICLE,
					Article.STAT_PUBLISHED,
					new PageRequest(0, topHitsArticlesSize, Sort.Direction.DESC, "hits"))
					.getContent();
		}
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
				new PageRequest(page - 1, size, Sort.Direction.DESC, "_id")).getContent();
	}

	/**
	 * @param list
	 * @return
	 */
	private List<Article> dealList(List<Article> list) {
		List<Article> articles = new ArrayList<Article>();
		for (Article article : list) {
			if (article.getContent().contains("<a name=\"more\"></a>")) {
				article.setContent(HtmlFixer.fix(StringUtils.substringBefore(article.getContent(),
						"<a name=\"more\"></a>")) + "<!--more-->");
			} else {
				if (article.getContent().length() >= 300) {
					article.setContent(HtmlFixer.substring(article.getContent(), 300)
							+ "<!--more-->");
				}
			}
			articles.add(article);
		}
		return articles;
	}

	@Override
	public void updateArchiveCache() {
		// TODO 自动生成的方法存根

	}

}
