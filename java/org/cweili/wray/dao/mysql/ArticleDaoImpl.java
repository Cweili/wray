package org.cweili.wray.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.cweili.wray.dao.ArticleDao;
import org.cweili.wray.domain.Article;
import org.cweili.wray.util.Function;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("articleDao")
public class ArticleDaoImpl extends BaseDaoSupport<Article> implements ArticleDao {

	@Override
	public int getCountByTypeStatus(byte type, byte status) {
		int r = db.queryForInt("SELECT COUNT(*) FROM article WHERE is_page=? AND stat=?",
				new Object[] { type, status }, new int[] { Types.INTEGER, Types.INTEGER });
		log.info("Count article: " + r);
		return r;
	}

	@Override
	public List<Article> getArticles(byte type, byte status, int start, int limit) {
		final List<Article> list = new ArrayList<Article>();
		db.query("SELECT article_id, title, permalink, content, tag, create_time, stat, "
				+ "hits, comment_count, comment_status, is_page FROM article "
				+ "WHERE is_page=? AND stat=? ORDER BY article_id DESC LIMIT ?,?", new Object[] {
				type, status, start, limit }, new int[] { Types.INTEGER, Types.INTEGER,
				Types.INTEGER, Types.INTEGER }, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Article article = new Article(rs.getLong(1), rs.getString(2), rs.getString(3), rs
						.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getByte(7), rs
						.getInt(8), rs.getInt(9), rs.getByte(10), rs.getByte(11));
				list.add(article);
			}
		});
		log.info("Query article for type: " + type + " status: " + status);
		return list;
	}

	@Override
	public List<Article> getArticles(byte type, byte status, int start, int limit,
			String order) {
		final List<Article> list = new ArrayList<Article>();
		db.query("SELECT article_id, title, permalink, content, tag, create_time, stat, "
				+ "hits, comment_count, comment_status, is_page FROM article "
				+ "WHERE is_page=? AND stat=? ORDER BY " + order + " LIMIT ?,?", new Object[] {
				type, status, start, limit }, new int[] { Types.INTEGER, Types.INTEGER,
				Types.INTEGER, Types.INTEGER }, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Article article = new Article(rs.getLong(1), rs.getString(2), rs.getString(3), rs
						.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getByte(7), rs
						.getInt(8), rs.getInt(9), rs.getByte(10), rs.getByte(11));
				list.add(article);
			}
		});
		log.info("Query article for type: " + type + " status: " + status);
		return list;
	}

	@Override
	public List<Article> getMetas(byte type, byte status, String order) {
		final List<Article> list = new ArrayList<Article>();
		db.query("SELECT article_id, title, permalink, tag, create_time, stat, "
				+ "hits, comment_count, comment_status, is_page FROM article "
				+ "WHERE is_page=? AND stat=? ORDER BY " + order, new Object[] { type, status },
				new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER },
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Article article = new Article(rs.getLong("article_id"), rs
								.getString("title"), rs.getString("permalink"), "", rs
								.getString("tag"), rs.getTimestamp("create_time"), rs
								.getByte("stat"), rs.getInt("hits"), rs.getInt("comment_count"), rs
								.getByte("comment_status"), rs.getByte("is_page"));
						list.add(article);
					}
				});
		log.info("Query article for type: " + type + " status: " + status);
		return list;
	}

	@Override
	public List<Article> getMetas(byte type, byte status, int start, int limit,
			String order) {
		final List<Article> list = new ArrayList<Article>();
		db.query("SELECT article_id, title, permalink, tag, create_time, stat, "
				+ "hits, comment_count, comment_status, is_page FROM article "
				+ "WHERE is_page=? AND stat=? ORDER BY " + order + " LIMIT ?,?", new Object[] {
				type, status, start, limit }, new int[] { Types.INTEGER, Types.INTEGER,
				Types.INTEGER, Types.INTEGER }, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Article article = new Article(rs.getLong("article_id"), rs.getString("title"), rs
						.getString("permalink"), "", rs.getString("tag"), rs
						.getTimestamp("create_time"), rs.getByte("stat"), rs.getInt("hits"), rs
						.getInt("comment_count"), rs.getByte("comment_status"), rs
						.getByte("is_page"));
				list.add(article);
			}
		});
		log.info("Query article for type: " + type + " status: " + status);
		return list;
	}

	@Override
	public Article getArticleById(final long id) {
		return db
				.queryForObject(
						"SELECT article_id, title, permalink, content, tag, create_time, stat, "
								+ "hits, comment_count, comment_status, is_page FROM article "
								+ "WHERE article_id=? AND stat > 0 LIMIT 1",
						new Object[] { id }, new int[] { Types.BIGINT }, new RowMapper<Article>() {
							@Override
							public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
								return new Article(rs.getLong(1), rs.getString(2), rs.getString(3),
										rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs
												.getByte(7), rs.getInt(8), rs.getInt(9), rs
												.getByte(10), rs.getByte(11));
							}
						});
	}

	@Override
	public Article getArticleByPermalink(String permalink) {
		return db
				.queryForObject(
						"SELECT article_id, title, permalink, content, tag, create_time, stat, "
								+ "hits, comment_count, comment_status, is_page FROM article "
								+ "WHERE permalink=? AND stat > 0 LIMIT 1",
						new Object[] { permalink }, new int[] { Types.VARCHAR },
						new RowMapper<Article>() {
							@Override
							public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
								return new Article(rs.getLong(1), rs.getString(2), rs.getString(3),
										rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs
												.getByte(7), rs.getInt(8), rs.getInt(9), rs
												.getByte(10), rs.getByte(11));
							}
						});
	}

	@Override
	public long save(final Article t) {
		if (t.getArticleId() < 1) {
			t.setArticleId(Function.generateId());
		}
		if (db.update(
				"INSERT INTO article (article_id, title, permalink, content, tag, create_time, stat, "
						+ "hits, comment_count, comment_status, is_page) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setLong(1, t.getArticleId());
						ps.setString(2, t.getTitle());
						ps.setString(3, t.getPermalink());
						ps.setString(4, t.getContent());
						ps.setString(5, t.getTag());
						ps.setTimestamp(6, new Timestamp(t.getCreateTime().getTime()));
						ps.setInt(7, t.getStat());
						ps.setInt(8, 0);
						ps.setInt(9, 0);
						ps.setInt(10, t.getCommentStatus());
						ps.setInt(11, t.getIsPage());
					}
				}) > 0) {
			log.info("Save " + t.toString());
			return t.getArticleId();
		} else {
			log.info("Error saving article");
			return 0;
		}
	}

	@Override
	public int update(final Article t) {
		int r = db.update(
				"UPDATE article SET title=?, permalink=?, content=?, tag=?, create_time=?, stat=?, hits=?, "
						+ "comment_count=?, comment_status=?, is_page=? WHERE article_id=?",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, t.getTitle());
						ps.setString(2, t.getPermalink());
						ps.setString(3, t.getContent());
						ps.setString(4, t.getTag());
						ps.setTimestamp(5, new Timestamp(t.getCreateTime().getTime()));
						ps.setInt(6, t.getStat());
						ps.setInt(7, t.getHits());
						ps.setInt(8, t.getCommentCount());
						ps.setInt(9, t.getCommentStatus());
						ps.setInt(10, t.getIsPage());
						ps.setLong(11, t.getArticleId());
					}
				});
		log.info("Update " + t.toString());
		return r;
	}

	@Override
	public int remove(final Article t) {
		int r = db.update("UPDATE article SET stat=? WHERE article_id=?",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, Article.STAT_REMOVED);
						ps.setLong(2, t.getArticleId());
					}
				});
		log.info("Remove " + t.toString());
		return r;
	}

	@Override
	public int remove(final List<Long> ids, final byte type) {
		StringBuilder sql = new StringBuilder("UPDATE article SET stat=? WHERE article_id IN (");
		for (int i = 0; i < ids.size(); ++i) {
			if (i == 0) {
				sql.append("?");
			} else {
				sql.append(",?");
			}
		}
		sql.append(")");
		int r = db.update(sql.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setByte(1, type);
				for (int i = 0; i < ids.size(); ++i) {
					ps.setLong(i + 2, ids.get(i));
				}
			}
		});
		log.info("Remove" + r + "article(s)");
		return r;
	}

	@Override
	public int updateColumn(Article article, String col, int type, Object value) {
		int r = db.update("UPDATE article SET " + col + "=? WHERE article_id=?", new Object[] {
				value, article.getArticleId() }, new int[] { type, Types.BIGINT });
		log.info("Update article " + article.getArticleId() + " column " + col);
		return r;
	}

}
