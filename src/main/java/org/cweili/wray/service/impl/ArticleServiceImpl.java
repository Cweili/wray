package org.cweili.wray.service.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.service.ArticleService;
import org.cweili.wray.util.Function;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:17:58
 * 
 */
@Service("articleService")
public class ArticleServiceImpl extends BaseService implements ArticleService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.ArticleService#getCountByTypeStatus(byte,
	 * byte)
	 */
	@Override
	public int getCountByTypeStatus(byte type, byte status) {

		if (type == Article.TYPE_ARTICLE && status == Article.STAT_PUBLISHED
				&& publishedArticleCount == 0) {
			updateArticleCache();
		} else if (type != Article.TYPE_ARTICLE || status != Article.STAT_PUBLISHED) {
			return articleDao.getCountByTypeStatus(type, status);
		}
		return publishedArticleCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.ArticleService#getCountByrelationship(long,
	 * byte, byte)
	 */
	@Override
	public int getCountByrelationship(long id, byte type, byte status) {
		return articleDao.getCountByRelationship(id, type, status);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.ArticleService#getArticlesByTypeStatus(byte,
	 * byte, int, int)
	 */
	@Override
	public List<Article> getArticlesByTypeStatus(byte type, byte status, int page, int limit) {
		int start = (page - 1) * limit;
		// List<Article> list;

		// switch (status) {
		// case Article.STAT_PUBLISHED:
		// if(type == Article.TYPE_ARTICLE) {
		// if (articles == null) {
		// updateArticleCache();
		// }
		// if(!articles.isEmpty()) {
		// start = start < articles.size() ? start : articles.size() - 1;
		// limit = (start + limit) < articles.size() ? (start + limit) :
		// articles.size();
		// return dealList(articles.subList(start, limit));
		// } else {
		// return articles;
		// }
		// } else {
		// if (pages == null) {
		// updateArticleCache();
		// }
		// if(!pages.isEmpty()) {
		// start = start < pages.size() ? start : pages.size() - 1;
		// limit = (start + limit) < pages.size() ? (start + limit) :
		// pages.size();
		// return dealList(pages.subList(start, limit));
		// } else {
		// return pages;
		// }
		// }
		// default:
		// list = articleDao.getArticlesByTypeStatus(type, status);
		// }
		// if(!list.isEmpty()) {
		// Collections.reverse(list);
		// start = start < list.size() ? start : list.size() - 1;
		// limit = (start + limit) < list.size() ? (start + limit) :
		// list.size();
		// return dealList(list.subList(start, limit));
		// } else {
		// return list;
		// }
		if (type == Article.TYPE_ARTICLE && status == Article.STAT_PUBLISHED) {
			return dealList(articleDao.getArticles(type, status, start, limit));
		} else if (type == Article.TYPE_ARTICLE) {
			return articleDao.getMetas(type, status, start, limit, "article_id DESC");
		} else {
			if (limit < 1 && status == Article.STAT_PUBLISHED) {
				if (pages == null) {
					updateArticleCache();
					return pages;
				}
				return pages;
			} else {
				return articleDao.getMetas(type, status, start, limit, "hits");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cweili.wray.service.ArticleService#getArticlesByRelationship(long,
	 * byte, byte, int, int)
	 */
	@Override
	public List<Article> getArticlesByRelationship(long id, byte type, byte status, int page,
			int limit) {
		int start = (page - 1) * limit;
		return articleDao.getMetasByRelationship(id, type, status, start, limit, "article_id DESC");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.ArticleService#getArticleById(long)
	 */
	@Override
	public Article getArticleById(long id) {
		return articleDao.getArticleById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cweili.wray.service.ArticleService#getArticleByPermalink(java.lang
	 * .String)
	 */
	@Override
	public Article getArticleByPermalink(String Permalink) {
		return articleDao.getArticleByPermalink(Permalink);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cweili.wray.service.ArticleService#save(org.cweili.wray.domain.Article
	 * )
	 */
	@Override
	public long save(Article article) throws SQLException {
		long rs = articleDao.save(article);
		if (rs < 1) {
			throw new SQLException("Article save error");
		} else if (article.getStat() == Article.STAT_PUBLISHED) {
			updateArticleCache();
			updateSidebarArticleCache();
		}
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cweili.wray.service.ArticleService#update(org.cweili.wray.domain.
	 * Article)
	 */
	@Override
	public boolean update(Article article) throws SQLException {
		int rs = articleDao.update(article);
		if (rs == 0) {
			throw new SQLException("Article update error");
		} else {
			updateArticleCache();
			updateSidebarArticleCache();
		}
		return rs > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cweili.wray.service.ArticleService#updateHits(org.cweili.wray.domain
	 * .Article)
	 */
	@Override
	public boolean updateHits(Article article) throws SQLException {
		int rs = articleDao.updateColumn(article, "hits", Types.INTEGER, article.getHits());
		if (rs == 0) {
			throw new SQLException("Article update error");
		}
		return rs > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cweili.wray.service.ArticleService#remove(org.cweili.wray.domain.
	 * Article)
	 */
	@Override
	public boolean remove(Article article) throws SQLException {
		int rs = articleDao.remove(article);
		if (rs == 0) {
			throw new SQLException("Article remove error");
		} else {
			updateArticleCache();
			updateSidebarArticleCache();
		}
		return rs > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.ArticleService#remove(java.util.List, byte)
	 */
	@Override
	public boolean remove(List<Long> ids, byte type) throws SQLException {
		int rs = articleDao.remove(ids, type);
		if (rs == 0) {
			throw new SQLException("Article remove error");
		} else {
			updateArticleCache();
			updateSidebarArticleCache();
		}
		return 0 > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.ArticleService#getTopCommentArticles(int)
	 */
	@Override
	public List<Article> getTopCommentArticles(int num) {
		if (topCommentArticlesSize != num) {
			topCommentArticlesSize = num;
			log.info("fffffffffffff");
			updateSidebarArticleCache();
		}
		return topCommentArticles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.ArticleService#getTopHitsArticles(int)
	 */
	@Override
	public List<Article> getTopHitsArticles(int num) {
		if (topHitsArticlesSize != num) {
			topHitsArticlesSize = num;
			log.info("ddddddddddddddddddd");
			updateSidebarArticleCache();
		}
		return topHitsArticles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.ArticleService#updateArticleCache()
	 */
	@Override
	public void updateArticleCache() {
		// articles = articleDao.getArticlesByTypeStatus(Article.TYPE_ARTICLE,
		// Article.STAT_PUBLISHED);
		// pages = articleDao.getArticlesByTypeStatus(Article.TYPE_PAGE,
		// Article.STAT_PUBLISHED);

		// if(!articles.isEmpty()) {
		// Collections.sort(articles, new Comparator<Article>() {
		//
		// public int compare(Article a1, Article a2) {
		// return new Long(a2.getArticleId()).compareTo(new
		// Long(a1.getArticleId()));
		// }
		// });
		// //Collections.reverse(articles);
		// }
		// if(!pages.isEmpty()) {
		// Collections.sort(pages, new Comparator<Article>() {
		//
		// public int compare(Article a1, Article a2) {
		// return new Integer(a2.getHits()).compareTo(new
		// Integer(a1.getHits()));
		// }
		// });
		// }

		pages = articleDao.getMetas(Article.TYPE_PAGE, Article.STAT_PUBLISHED, "hits");

		publishedArticleCount = articleDao.getCountByTypeStatus(Article.TYPE_ARTICLE,
				Article.STAT_PUBLISHED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.ArticleService#updateSidebarArticleCache()
	 */
	@Override
	public void updateSidebarArticleCache() {
		// if (articles == null) {
		// updateArticleCache();
		// }
		// List<Article> tmp;
		// if (!articles.isEmpty() && topCommentArticlesSize > 0) {
		// tmp = new ArrayList<Article>();
		// tmp.addAll(articles);
		// Collections.sort(tmp, new Comparator<Article>() {
		//
		// public int compare(Article a1, Article a2) {
		// if (a1.getCommentCount() > a2.getCommentCount())
		// return 1;
		// else
		// return 0;
		// }
		// });
		// topCommentArticles = new ArrayList<Article>(tmp.subList(0,
		// (topCommentArticlesSize - 1) < tmp
		// .size() ? (topCommentArticlesSize - 1) : tmp.size()));
		//
		// }
		//
		// if (!articles.isEmpty() && topHitsArticlesSize > 0) {
		// tmp = new ArrayList<Article>();
		// tmp.addAll(articles);
		// Collections.sort(tmp, new Comparator<Article>() {
		//
		// public int compare(Article a1, Article a2) {
		// if (a1.getHits() > a2.getHits())
		// return 1;
		// else
		// return 0;
		// }
		// });
		// topHitsArticles = new ArrayList<Article>(tmp.subList(0,
		// (topHitsArticlesSize - 1) < tmp
		// .size() ? (topHitsArticlesSize - 1) : tmp.size()));
		// }

		topCommentArticles = articleDao.getMetas(Article.TYPE_ARTICLE, Article.STAT_PUBLISHED, 0,
				topCommentArticlesSize, "comment_count DESC");
		topHitsArticles = articleDao.getMetas(Article.TYPE_ARTICLE, Article.STAT_PUBLISHED, 0,
				topHitsArticlesSize, "hits DESC");
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
