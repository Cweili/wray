//package org.cweili.wray.dao.mysql;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.sql.Types;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.cweili.wray.dao.UserDao;
//import org.cweili.wray.domain.User;
//import org.cweili.wray.util.Function;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.PreparedStatementSetter;
//import org.springframework.jdbc.core.RowCallbackHandler;
//
///**
// * 
// * @author cweili
// * @version 2012-8-16 下午5:09:46
// * 
// */
//public class UserDaoImpl extends BaseDaoSupport<User> implements UserDao {
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.cweili.wray.dao.BaseDao#save(java.lang.Object)
//	 */
//	@Override
//	public long save(final User t) {
//		t.setUserId(Function.generateId());
//		if (db.update(
//				"INSERT INTO user (user_id, username, passwd, nickname, permalink, email, regtime, usergroup) "
//						+ "VALUES (?,?,?,?,?,?,?,?)", new PreparedStatementSetter() {
//					public void setValues(PreparedStatement ps) throws SQLException {
//						ps.setLong(1, t.getUserId());
//						ps.setString(2, t.getUsername());
//						ps.setString(3, t.getPasswd());
//						ps.setString(4, t.getNickname());
//						ps.setString(5, t.getPermalink());
//						ps.setString(6, t.getEmail());
//						ps.setTimestamp(7, new Timestamp(t.getRegtime().getTime()));
//						ps.setInt(8, t.getUsergroup());
//					}
//				}) > 0) {
//			log.info("Save user " + t.toString());
//			return t.getUserId();
//		} else {
//			log.info("Error Saving user");
//			return 0;
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.cweili.wray.dao.BaseDao#update(java.lang.Object)
//	 */
//	@Override
//	public int update(final User t) {
//		int r = db.update(
//				"UPDATE user SET (name, passwd, nickname, permalink, email, regtime, usergroup) "
//						+ "VALUES (?,?,?,?,?,?,?,?) WHERE user_id=?",
//				new PreparedStatementSetter() {
//					public void setValues(PreparedStatement ps) throws SQLException {
//						ps.setString(1, t.getUsername());
//						ps.setString(2, t.getPasswd());
//						ps.setString(3, t.getNickname());
//						ps.setString(4, t.getPermalink());
//						ps.setString(5, t.getEmail());
//						ps.setTimestamp(6, new Timestamp(t.getRegtime().getTime()));
//						ps.setInt(7, t.getUsergroup());
//						ps.setLong(8, t.getUserId());
//					}
//				});
//		log.info("Update " + r + "user: " + t.toString());
//		return r;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.cweili.wray.dao.BaseDao#remove(java.lang.Object)
//	 */
//	@Override
//	public int remove(final User t) {
//		int r = db.update("UPDATE user SET (usergroup) VALUES (?) WHERE user_id=?",
//				new PreparedStatementSetter() {
//					public void setValues(PreparedStatement ps) throws SQLException {
//						ps.setInt(1, User.GROUP_BLOCK);
//						ps.setLong(2, t.getUserId());
//					}
//				});
//		log.info("Remove " + r + "article: " + t.toString());
//		return r;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.cweili.wray.dao.UserDao#getUsersWithLimit(int, int)
//	 */
//	@Override
//	public List<User> getUsersWithLimit(int start, int limit) {
//		final List<User> list = new ArrayList<User>();
//		db.query("SELECT * FROM user WHERE usergroup>0 LIMIT ?,?", new Object[] { start, limit },
//				new int[] { Types.INTEGER, Types.INTEGER }, new RowCallbackHandler() {
//
//					public void processRow(ResultSet rs) throws SQLException {
//						User user = new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs
//								.getString(4), rs.getString(5), rs.getString(6),
//								rs.getTimestamp(7), rs.getByte(8));
//						list.add(user);
//					}
//				});
//		return list;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.cweili.wray.dao.UserDao#getAllUsers()
//	 */
//	@Override
//	public Map<Long, User> getAllUsers() {
//		final Map<Long, User> map = new HashMap<Long, User>();
//		db.query("SELECT * FROM user WHERE usergroup>0", new RowCallbackHandler() {
//
//			public void processRow(ResultSet rs) throws SQLException {
//				User user = new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs
//						.getString(4), rs.getString(5), rs.getString(6), rs.getTimestamp(7), rs
//						.getByte(8));
//				map.put(rs.getLong(1), user);
//			}
//		});
//		return map;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.cweili.wray.dao.UserDao#getUserByPermalink(java.lang.String)
//	 */
//	@Override
//	public User getUserByPermalink(String permalink) {
//		return db.queryForObject(
//				"SELECT * FROM article WHERE permalink=? AND usergroup > 0 LIMIT 1",
//				new Object[] { permalink }, new int[] { Types.VARCHAR },
//				new BeanPropertyRowMapper<User>(User.class));
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.cweili.wray.dao.UserDao#getUserByName(java.lang.String)
//	 */
//	@Override
//	public User getUserByName(String name) {
//		return db.queryForObject("SELECT * FROM article WHERE name=? AND usergroup > 0 LIMIT 1",
//				new Object[] { name }, new int[] { Types.VARCHAR },
//				new BeanPropertyRowMapper<User>(User.class));
//	}
//
// }
