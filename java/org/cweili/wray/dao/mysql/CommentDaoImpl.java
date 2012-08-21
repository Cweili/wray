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
				"INSERT INTO comment (comment_id, author, email, link, ip, post_time, "
				+"agent, content, parrent_id, stat) VALUES (?,?,?,?,?,?,?,?,?,?)",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setLong(1, t.getCommentId());
						ps.setString(2, t.getAuthor());
						ps.setString(3, t.getEmail());
						ps.setString(4, t.getLink());
						ps.setString(5, t.getIp());
						ps.setTimestamp(6, new Timestamp(t.getPostTime().getTime()));
						ps.setString(7, t.getAgent());
						ps.setString(8, t.getContent());
						ps.setLong(9, t.getStat());
						ps.setByte(10, t.getStat());
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
				"UPDATE comment SET comment_id=?, author=?, email=?, link=?, ip=?, post_time=?, "
				+"agent=?, content=?, parrent_id=?, stat=? WHERE comment_id=?",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, t.getAuthor());
						ps.setString(2, t.getEmail());
						ps.setString(3, t.getLink());
						ps.setString(4, t.getIp());
						ps.setTimestamp(5, new Timestamp(t.getPostTime().getTime()));
						ps.setString(6, t.getAgent());
						ps.setString(7, t.getContent());
						ps.setLong(8, t.getStat());
						ps.setByte(9, t.getStat());
						ps.setLong(10, t.getCommentId());
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
		db.query("SELECT comment_id, author, email, link, ip, post_time, agent, content, parrent_id, "
		+"stat FROM comment WHERE stat=? ORDER BY " + order + " LIMIT ?,?",
				new Object[] { status, start, limit }, new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER }, new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Comment comment = new Comment(rs.getLong(1), rs.getString(2), rs.getString(3), rs
								.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getString(7), rs
								.getString(8), rs.getLong(9), rs.getByte(10));
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
		db.query("SELECT comment_id, author, email, link, ip, post_time, agent, content, parrent_id, "
		+"stat FROM comment WHERE stat=? ORDER BY " + order,
				new Object[] { status, start, limit }, new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER }, new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Comment comment = new Comment(rs.getLong(1), rs.getString(2), rs.getString(3), rs
								.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getString(7), rs
								.getString(8), rs.getLong(9), rs.getByte(10));
						list.add(comment);
					}
				});
		log.info("Query comment for status: " + status);
		return list;
	}

}
