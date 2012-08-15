package org.cweili.wray.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cweili.wray.dao.ItemDao;
import org.cweili.wray.domain.Item;
import org.cweili.wray.util.Function;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("itemDao")
public class ItemDaoImpl extends BaseDaoSupport<Item> implements ItemDao {

	@Override
	public long save(final Item t) {
		if(t.getItemId() < 1) {
			t.setItemId(Function.generateId());
		}
		if (db.update(
				"INSERT INTO item (item_id, item_name, permalink, description, count, item_order, item_type,"
				+ " parrent_id, stat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setLong(1, t.getItemId());
						ps.setString(2, t.getItemName());
						ps.setString(3, t.getPermalink());
						ps.setString(4, t.getDescription());
						ps.setInt(5, t.getCount());
						ps.setInt(6, t.getItemOrder());
						ps.setByte(7, t.getItemType());
						ps.setLong(8, t.getParrentId());
						ps.setByte(9, t.getStat());
					}
				}) > 0) {
			log.info("Save " + t.toString());
			return t.getItemId();
		} else {
			log.info("Error saving item");
			return 0;
		}
	}

	@Override
	public int update(final Item t) {
		int r = db
				.update("UPDATE item SET item_name=?, permalink=?, description=?, count=?, item_order=?,"
						+ " item_type=?, parrent_id=? WHERE item_id=?",
						new PreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps) throws SQLException {
								ps.setString(1, t.getItemName());
								ps.setString(2, t.getPermalink());
								ps.setString(3, t.getDescription());
								ps.setInt(4, t.getCount());
								ps.setInt(5, t.getItemOrder());
								ps.setByte(6, t.getItemType());
								ps.setLong(7, t.getParrentId());
								ps.setLong(8, t.getItemId());
							}
						});
		log.info("Update " + t.toString());
		return r;
	}

	@Override
	public int remove(final Item t) {
		int r = db
				.update("UPDATE item SET stat=? WHERE item_id=?",
						new PreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps) throws SQLException {
								ps.setByte(1, Item.STAT_OFF);
								ps.setLong(2, t.getItemId());
							}
						});
		log.info("Update " + t.toString());
		return r;
	}
	
	@Override
	public int remove(final List<Long> ids) {
		StringBuilder sql = new StringBuilder("UPDATE article SET stat=? WHERE article_id IN (");
		for(int i = 0; i < ids.size(); ++i) {
			if(i == 0) {
				sql.append("?");
			} else {
				sql.append(",?");
			}
		}
		sql.append(")");
		int r = db.update(sql.toString(),
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setByte(1, Item.STAT_OFF);
						for(int i = 0; i < ids.size(); ++i) {
							ps.setLong(i + 2, ids.get(i));
						}
					}
		});
		log.info("Remove" + r + "item(s)");
		return r;
	}

	@Override
	public Item getItemById(long id) {
		return db.queryForObject("SELECT item_id, item_name, permalink, description, count, item_order, item_type,"
				+ " parrent_id, stat FROM item WHERE item_id=? AND stat > 0 LIMIT 1",
				new Object[] { id }, new int[] { Types.BIGINT }, new RowMapper<Item>() {
					@Override
					public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Item(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getInt(5), rs.getByte(6), rs.getByte(7), rs.getLong(8), rs.getByte(9));
					}
		});
	}

	@Override
	public Item getItemByPermalink(String permalink, byte type) {
		return db.queryForObject("SELECT item_id, item_name, permalink, description, count, item_order, item_type,"
				+ " parrent_id, stat FROM item WHERE permalink=? AND stat > 0 LIMIT 1",
				new Object[] { permalink }, new int[] { Types.VARCHAR }, new RowMapper<Item>() {
					@Override
					public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Item(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getInt(5), rs.getByte(6), rs.getByte(7), rs.getLong(8), rs.getByte(9));
					}
		});
	}

	@Override
	@Deprecated
	public Map<String, Item> getItemMap(byte type, String order) {
		final Map<String, Item> map = new HashMap<String, Item>();
		db.query("SELECT item_id, item_name, permalink, description, count, item_order, item_type,"
				+ " parrent_id, stat FROM item WHERE item_type=? AND stat>0 ORDER BY " + order,
				new Object[] { type }, new int[] { Types.INTEGER,
						Types.INTEGER}, new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Item item = new Item(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getInt(5), rs.getByte(6), rs.getByte(7), rs.getLong(8), rs.getByte(9));
						map.put(item.getPermalink(), item);
					}
				});
		return map;
	}

	@Override
	public List<Item> getItems(byte type, String order) {
		final List<Item> list = new ArrayList<Item>();
		db.query("SELECT item_id, item_name, permalink, description, count, item_order, item_type,"
				+ " parrent_id, stat FROM item WHERE item_type=? AND stat>0 ORDER BY " + order,
				new Object[] { type }, new int[] { Types.INTEGER }, new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Item item = new Item(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getInt(5), rs.getByte(6), rs.getByte(7), rs.getLong(8), rs.getByte(9));
						list.add(item);
					}
				});
		log.info("Query item for type: " + type);
		return list;
	}

}
