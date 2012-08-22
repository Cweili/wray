package org.cweili.wray.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.cweili.wray.dao.CommentDao;
import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Comment;
import org.cweili.wray.util.Function;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;

/**
 * 
 * @author cweili
 * @version 2012-8-21 下午4:37:29
 *
 */
public class CommentDaoImpl extends BaseDaoSupport<Comment> implements CommentDao {

	/* (non-Javadoc)
	 * @see org.cweili.wray.dao.BaseDao#save(java.lang.Object)
	 */
	@Override
	public long save(final Comment t) {
		if (t.getCommentId() < 1) {
			t.setCommentId(Function.generateId());
		}
		if (db.update(
				"INSERT INTO comment (comment_id, article_id, author, email, link, ip, post_time, "
				+"agent, content, parrent_id, stat) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setLong(1, t.getCommentId());
						ps.setLong(2, t.getArticleId());
						ps.setString(3, t.getAuthor());
						ps.setString(4, t.getEmail());
						ps.setString(5, t.getLink());
						ps.setString(6, t.getIp());
						ps.setTimestamp(7, new Timestamp(t.getPostTime().getTime()));
						ps.setString(8, t.getAgent());
						ps.setString(9, t.getContent());
						ps.setLong(10, t.getStat());
						ps.setByte(11, t.getStat());
					}
				}) > 0) {
			log.info("Save " + t.toString());
			return t.getCommentId();
		} else {
			log.info("Error saving " + t.toString());
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.dao.BaseDao#update(java.lang.Object)
	 */
	@Override
	public int update(final Comment t) {
		int r = db.update(
				"UPDATE comment SET article_id=?, author=?, email=?, link=?, ip=?, post_time=?, "
				+"agent=?, content=?, parrent_id=?, stat=? WHERE comment_id=?",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setLong(1, t.getArticleId());
						ps.setString(2, t.getAuthor());
						ps.setString(3, t.getEmail());
						ps.setString(4, t.getLink());
						ps.setString(5, t.getIp());
						ps.setTimestamp(6, new Timestamp(t.getPostTime().getTime()));
						ps.setString(7, t.getAgent());
						ps.setString(8, t.getContent());
						ps.setLong(9, t.getStat());
						ps.setByte(10, t.getStat());
						ps.setLong(11, t.getCommentId());
					}
				});
		log.info("Update " + t.toString());
		return r;
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.dao.BaseDao#remove(java.lang.Object)
	 */
	@Override
	public int remove(final Comment t) {
		int r = db.update("UPDATE comment SET stat=? WHERE comment_id=?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setByte(1, Comment.STAT_REMOVED);
				ps.setLong(2, t.getCommentId());
			}
		});
		log.info("Update " + t.toString());
		return r;
	}
	
	/* (non-Javadoc)
	 * @see org.cweili.wray.dao.CommentDao#remove(java.util.List)
	 */
	@Override
	public int remove(final List<Long> ids) {
		StringBuilder sql = new StringBuilder("UPDATE comment SET stat=? WHERE comment_id IN (");
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
				ps.setByte(1, Comment.STAT_REMOVED);
				for (int i = 0; i < ids.size(); ++i) {
					ps.setLong(i + 2, ids.get(i));
				}
			}
		});
		log.info("Remove" + r + "item(s)");
		return r;
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.dao.CommentDao#getComments(byte, int, int, java.lang.String)
	 */
	@Override
	public List<Comment> getComments(byte status, int start, int limit, String order) {
		final List<Comment> list = new ArrayList<Comment>();
		db.query("SELECT comment_id, article_id, author, email, link, ip, post_time, agent, content, parrent_id, "
		+"stat FROM comment WHERE stat=? ORDER BY " + order + " LIMIT ?,?",
				new Object[] { status, start, limit }, new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER }, new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Comment comment = new Comment(rs.getLong(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs
								.getString(5), rs.getString(6), rs.getTimestamp(7), rs.getString(8), rs
								.getString(9), rs.getLong(10), rs.getByte(11));
						list.add(comment);
					}
				});
		log.info("Query comment for status: " + status);
		return list;
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.dao.CommentDao#getCommentsByArticle(org.cweili.wray.domain.Article)
	 */
	@Override
	public List<Comment> getCommentsByArticle(Article article, String order) {
		final List<Comment> list = new ArrayList<Comment>();
		db.query("SELECT comment_id, article_id, author, email, link, ip, post_time, agent, content, parrent_id, "
		+"stat FROM comment WHERE stat>0 and article_id=? ORDER BY " + order,
				new Object[] { article.getArticleId() }, new int[] { Types.BIGINT }, new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Comment comment = new Comment(rs.getLong(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs
								.getString(5), rs.getString(6), rs.getTimestamp(7), rs.getString(8), rs
								.getString(9), rs.getLong(10), rs.getByte(11));
						list.add(comment);
					}
				});
		log.info("Query comment for article: " + article.getArticleId());
		return list;
	}

}
