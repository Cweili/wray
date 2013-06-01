package org.cweili.wray.service.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.Page;
import org.cweili.wray.entity.Article;
import org.cweili.wray.entity.Relationship;
import org.cweili.wray.service.ArticleService;
import org.cweili.wray.util.ChineseSegment;
import org.cweili.wray.util.Function;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 文章 Service 实现
 * 
 * @author Cweili
 * @version 2013-4-3 下午3:41:01
 * 
 */
@Service("articleService")
public class ArticleServiceImpl extends BaseService implements ArticleService {

	// @Override
	// public int countByTypeStatus(byte type, byte status) {
	//
	// if (type == Article.TYPE_ARTICLE && status == Article.STAT_PUBLISHED
	// && publishedArticleCount == 0) {
	// updateArticleCache();
	// } else if (type != Article.TYPE_ARTICLE || status !=
	// Article.STAT_PUBLISHED) {
	// return articleDao.findMetaByIsPageAndStat(type, status).size();
	// }
	// return (int) publishedArticleCount;
	// }
	//
	// @Override
	// public int countByRelationship(String itemId, byte type, byte status) {
	// List<Relationship> relationships = relationshipDao.findByItemId(itemId);
	// int count = 0;
	// Article article;
	// for (Relationship relationship : relationships) {
	// article = articleDao.findOne(relationship.getArticleId());
	// if (null != article && type == article.getIsPage() && status ==
	// article.getStat()) {
	// ++count;
	// }
	// }
	// return count;
	// }

	// @Override
	// public int countByMonth(Date month) {
	// Calendar toDate = new GregorianCalendar();
	// toDate.setTime(month);
	// toDate.set(Calendar.DATE, toDate.getActualMaximum(Calendar.DATE));
	// toDate.set(Calendar.HOUR_OF_DAY,
	// toDate.getActualMaximum(Calendar.HOUR_OF_DAY));
	// toDate.set(Calendar.MINUTE, toDate.getActualMaximum(Calendar.MINUTE));
	// toDate.set(Calendar.SECOND, toDate.getActualMaximum(Calendar.SECOND));
	// toDate.set(Calendar.MILLISECOND,
	// toDate.getActualMaximum(Calendar.MILLISECOND));
	//
	// return articleDao.findByArchive(month, toDate.getTime()).size();
	// }

	@Override
	public Page<Article> findByTypeStatus(byte type, byte status, int page, int size) {
		if (type == Article.TYPE_ARTICLE && status == Article.STAT_PUBLISHED) {

			Page<Article> pagedArticles = findByTypeStatusInDao(type, status, page, size);
			return new Page<Article>(dealArticleList(pagedArticles.getContent()), pagedArticles);

		} else if (type == Article.TYPE_ARTICLE) {

			return findByTypeStatusInDao(type, status, page, size);

		} else {
			if (size < 1 && status == Article.STAT_PUBLISHED) {
				if (pages == null) {
					updatePageCache();
					return pages;
				}
				return pages;
			} else {
				return new Page<Article>(articleDao.findByIsPageAndStat(type, status,
						new PageRequest(page - 1, size, Sort.Direction.ASC, "hit")));
			}
		}
	}

	@Override
	public Page<Article> findByRelationship(String itemId, byte type, byte status, int page,
			int size) {
		page = page > 0 ? page : 1;
		size = size > 0 ? size : 1;
		List<Relationship> relationships = relationshipDao.findByItemId(itemId);
		List<Article> articles = new LinkedList<Article>();
		Article article;
		for (Relationship relationship : relationships) {
			article = articleDao.findOne(relationship.getArticleId());
			if (null != article && type == article.getIsPage() && status == article.getStat()) {
				articles.add(dealArticleContent(articleDao.findOne(relationship.getArticleId())));
			}
		}
		Collections.sort(articles);
		int end = size * page - 1;
		end = end < articles.size() ? end : articles.size();
		Page<Article> pagedArticles = new Page<Article>(articles.subList(size * (page - 1), end),
				new PageRequest(page - 1, size), articles.size());
		return pagedArticles;
	}

	@Override
	public Page<Article> findByMonth(Date month, int page, int size) {
		Calendar toDate = new GregorianCalendar();
		toDate.setTime(month);
		toDate.set(Calendar.DATE, toDate.getActualMaximum(Calendar.DATE));
		toDate.set(Calendar.HOUR_OF_DAY, toDate.getActualMaximum(Calendar.HOUR_OF_DAY));
		toDate.set(Calendar.MINUTE, toDate.getActualMaximum(Calendar.MINUTE));
		toDate.set(Calendar.SECOND, toDate.getActualMaximum(Calendar.SECOND));
		toDate.set(Calendar.MILLISECOND, toDate.getActualMaximum(Calendar.MILLISECOND));

		Page<Article> pagedArticles = new Page<Article>(articleDao.findByArchive(month,
				toDate.getTime(), new PageRequest(page - 1, size, Sort.Direction.DESC, "_id")));

		return pagedArticles;
	}

	@Override
	public Article findById(String articleId) {
		Article article = articleDao.findOne(articleId);
		return article;
	}

	@Override
	public Article findByPermalink(String permalink, byte type) {
		Article article = articleDao.findByPermalinkAndIsPage(permalink, type);
		if (Article.STAT_REMOVED != article.getStat() && Article.STAT_RECYCLE != article.getStat()) {
			return article;
		} else {
			return null;
		}
	}

	@Override
	public Article save(Article article) {
		if (StringUtils.isEmpty(article.getArticleId())) {
			article.setArticleId(Function.generateId());
		}
		Set<String> keyword = ChineseSegment.segmentToSet(Function.stripTags(article.getTitle()
				+ article.getContent() + article.getTag()));
		article.setKeyword(keyword);
		Article articleNew = articleDao.save(article);
		updateCache(articleNew);
		return articleNew;
	}

	@Override
	public boolean updateHit(Article article) {
		Article articleNew = articleDao.save(article);
		return null != articleNew;
	}

	@Override
	public boolean remove(Article article) {
		article.setStat(Article.STAT_REMOVED);
		Article articleNew = articleDao.save(article);
		updateCache(articleNew);
		return null != articleNew;
	}

	@Override
	public boolean updateStatus(List<String> ids, byte status) {
		Iterable<Article> articles = articleDao.findAll(ids);
		for (Article article : articles) {
			article.setStat(status);
		}
		articles = articleDao.save(articles);
		updateCache(articles.iterator().next());
		return null != articles;
	}

	@Override
	public List<Article> getTopCommentArticles(int size) {
		if (size > 0 && topCommentArticlesSize != size) {
			topCommentArticlesSize = size;
			updateSidebarArticleCache();
		}
		return topCommentArticles.getContent();
	}

	@Override
	public List<Article> getTopHitArticles(int size) {
		if (size > 0 && topHitArticlesSize != size) {
			topHitArticlesSize = size;
			updateSidebarArticleCache();
		}
		return topHitArticles.getContent();
	}

	@Override
	public List<Article> getArchive() {
		if (null == archive) {
			updateArchiveCache();
		}
		return archive;
	}

	/**
	 * @param type
	 * @param status
	 * @param page
	 * @param size
	 * @return
	 */
	private Page<Article> findByTypeStatusInDao(byte type, byte status, int page, int size) {
		return new Page<Article>(articleDao.findByIsPageAndStat(type, status, new PageRequest(
				page - 1, size, Sort.Direction.DESC, "_id")));
	}

	private void updateCache(Article article) {
		if (null != article) {
			if (article.getIsPage() == Article.TYPE_ARTICLE) {
				updateSidebarArticleCache();
				updateArchiveCache();
				clearFeedCache();
			} else {
				updatePageCache();
			}
		}
	}

}
