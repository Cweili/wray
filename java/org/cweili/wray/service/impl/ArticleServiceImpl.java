package org.cweili.wray.service.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.service.ArticleService;
import org.cweili.wray.util.Function;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("articleService")
@Scope("prototype")
public class ArticleServiceImpl extends BaseService implements ArticleService {

	@Override
	public int getCountByTypeStatus(byte type, byte status) {

		if (type == Article.TYPE_ARTICLE && status == Article.STAT_PUBLISHED
				&& publishedArticleCount == 0) {
			log.info("bbbbbbbbbbbbbbbbb");
			updateArticleCache();
		} else if (type != Article.TYPE_ARTICLE || status != Article.STAT_PUBLISHED) {
			log.info("gggggggggggggggggggggggggggg");
			return articleDao.getCountByTypeStatus(type, status);
		}
		return publishedArticleCount;
	}

	@Override
	public List<Article> getArticlesByTypeStatus(byte type, byte status, int page, int limit) {
		int start = (page - 1) * limit;
		List<Article> list;

		switch (status) {
		case Article.STAT_PUBLISHED:
			switch (type) {
			case Article.TYPE_ARTICLE:
				if (articles.isEmpty()) {
					log.info("aaaaaaaaaaaaaaaaaaaa》》》》》》》》》》");
					updateArticleCache();
					log.info("《《《《《《《《《《《《aaaaaaaaaaaaaaaaaaaa");
				}
				if(!articles.isEmpty()) {
					start = start < articles.size() ? start : articles.size() - 1;
					limit = (start + limit) < articles.size() ? (start + limit) : articles.size();
					return dealList(articles.subList(start, limit));
				} else {
					return articles;
				}
			default:
				if (pages.isEmpty()) {
					log.info("dddddddddddddddaaaaaaaaaaaaaaaaaaaa》》》》》》》");
					updateArticleCache();
					log.info("《《《《《《《《《dddddddddddddddaaaaaaaaaaaaaaaaaaaa");
				}
				if(!pages.isEmpty()) {
					start = start < pages.size() ? start : pages.size() - 1;
					limit = (start + limit) < pages.size() ? (start + limit) : pages.size();
					return dealList(pages.subList(start, limit));
				} else {
					return pages;
				}
			}
		default:
			list = articleDao.getArticlesByTypeStatus(type, status);
		}
		if(!list.isEmpty()) {
			Collections.reverse(list);
			start = start < list.size() ? start : list.size() - 1;
			limit = (start + limit) < list.size() ? (start + limit) : list.size();
			return dealList(list.subList(start, limit));
		} else {
			return list;
		}
	}

	@Override
	public Article getArticleById(long id) {
		return articleDao.getArticleById(id);
	}

	@Override
	public Article getArticleByPermalink(String Permalink) {
		return articleDao.getArticleByPermalink(Permalink);
	}

	@Override
	public long save(Article article) throws SQLException {
		long rs = articleDao.save(article);
		if(rs == 0) {
			throw new SQLException("Article save error");
		} else if(article.getStat() == Article.STAT_PUBLISHED) {
			updateArticleCache();
			updateSidebarArticleCache();
		}
		return rs;
	}

	@Override
	public boolean update(Article article) throws SQLException {
		int rs = articleDao.update(article);
		if(rs == 0) {
			throw new SQLException("Article update error");
		} else {
			updateArticleCache();
			updateSidebarArticleCache();
		}
		return rs > 0;
	}
	
	@Override
	public boolean updateHitsCommentCount(Article article) throws SQLException {
		int rs = articleDao.update(article);
		if(rs == 0) {
			throw new SQLException("Article update error");
		}
		return rs > 0;
	}
	
	@Override
	public boolean remove(Article article) throws SQLException {
		int rs = articleDao.remove(article);
		if(rs == 0) {
			throw new SQLException("Article remove error");
		} else {
			updateArticleCache();
			updateSidebarArticleCache();
		}
		return rs > 0;
	}
	
	@Override
	public boolean remove(List<Long> ids, byte type) throws SQLException {
		int rs = articleDao.remove(ids, type);
		if(rs == 0) {
			throw new SQLException("Article remove error");
		} else {
			updateArticleCache();
			updateSidebarArticleCache();
		}
		return 0 > 0;
	}

	@Override
	public List<Article> getTopCommentArticles(int num) {
		if (topCommentArticlesSize != num) {
			topCommentArticlesSize = num;
			log.info("fffffffffffff");
			updateSidebarArticleCache();
		}
		return topCommentArticles;
	}

	@Override
	public List<Article> getTopHitsArticles(int num) {
		if (topHitsArticlesSize != num) {
			topHitsArticlesSize = num;
			log.info("ddddddddddddddddddd");
			updateSidebarArticleCache();
		}
		return topHitsArticles;
	}

	@Override
	public void updateArticleCache() {
		articles = articleDao.getArticlesByTypeStatus(Article.TYPE_ARTICLE, Article.STAT_PUBLISHED);
		pages = articleDao.getArticlesByTypeStatus(Article.TYPE_PAGE, Article.STAT_PUBLISHED);

		if(!articles.isEmpty()) {
			Collections.sort(articles, new Comparator<Article>() {
	
				public int compare(Article a1, Article a2) {
					return new Long(a2.getArticleId()).compareTo(new Long(a1.getArticleId()));
				}
			});
			//Collections.reverse(articles);
		}
		if(!pages.isEmpty()) {
			Collections.sort(pages, new Comparator<Article>() {
	
				public int compare(Article a1, Article a2) {
					return new Integer(a2.getHits()).compareTo(new Integer(a1.getHits()));
				}
			});
		}
		publishedArticleCount = articleDao.getCountByTypeStatus(Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED);

	}

	@Override
	public void updateSidebarArticleCache() {
		if (articles.isEmpty()) {
			updateArticleCache();
		}
		List<Article> tmp;
		if (!articles.isEmpty() && topCommentArticlesSize > 0) {
			tmp = new ArrayList<Article>();
			tmp.addAll(articles);
			Collections.sort(tmp, new Comparator<Article>() {

				public int compare(Article a1, Article a2) {
					if (a1.getCommentCount() > a2.getCommentCount())
						return 1;
					else
						return 0;
				}
			});
			topCommentArticles = new ArrayList<Article>(tmp.subList(0, (topCommentArticlesSize - 1) < tmp
					.size() ? (topCommentArticlesSize - 1) : tmp.size()));
		}

		if (!articles.isEmpty() && topHitsArticlesSize > 0) {
			tmp = new ArrayList<Article>();
			tmp.addAll(articles);
			Collections.sort(tmp, new Comparator<Article>() {

				public int compare(Article a1, Article a2) {
					if (a1.getHits() > a2.getHits())
						return 1;
					else
						return 0;
				}
			});
			topHitsArticles = new ArrayList<Article>(tmp.subList(0, (topHitsArticlesSize - 1) < tmp
					.size() ? (topHitsArticlesSize - 1) : tmp.size()));
		}
	}
	
	private List<Article> dealList(List<Article> list) {
		Article a;
		for(int i = 0; i < list.size(); ++i) {
			a = list.get(i);
			if(a.getContent().contains("<a name=\"more\"></a>")) {
				a.setContent(Function.stripTags(a.getContent().substring(0, a.getContent().indexOf("<a name=\"more\"></a>") - 1)) + "<!--more-->");
				list.set(i, a);
			} else {
				a.setContent(Function.stripTags(a.getContent()));
				if(a.getContent().length() >= 300) {
					a.setContent(a.getContent().substring(0, 300) + "<!--more-->");
				}
			}
			list.set(i, a);
		}
		return list;
	}

}
