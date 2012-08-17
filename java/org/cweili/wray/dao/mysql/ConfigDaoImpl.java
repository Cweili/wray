package org.cweili.wray.dao.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.dao.ConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:09:20
 *
 */
@Repository("configDao")
public class ConfigDaoImpl implements ConfigDao {

	/**
	 * 
	 */
	private Log log = LogFactory.getLog(ConfigDaoImpl.class);

	/**
	 * 
	 */
	@Autowired
	private JdbcTemplate db;
	
	/* (non-Javadoc)
	 * @see org.cweili.wray.dao.ConfigDao#getAll()
	 */
	@Override
	public Map<String, String> getAll() {
		final Map<String, String> map = new HashMap<String, String>();
		db.query("SELECT config_key, config_value FROM config", new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				map.put(rs.getString(1), rs.getString(2));
				log.info("Load config: " + rs.getString(1) + " => " + rs.getString(2));
			}
		});
		return map;
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.dao.ConfigDao#saveOrUpdate(java.lang.String, java.lang.String)
	 */
	@Override
	public int saveOrUpdate(String key, String value) {
//		int rs = db.queryForInt("SELECT COUNT(*) FROM config WHERE config_key=?", new Object[]{ key }, new int[]{ Types.VARCHAR });
//		if(rs > 0) {
//			rs = db.update("UPDATE config SET config_value=? WHERE config_key=?", new Object[]{ value, key }, new int[]{ Types.VARCHAR, Types.VARCHAR });
//			log.info("Update config: " + key + " => " + value);
//		} else {
			int rs = db.update("REPLACE INTO config (config_key, config_value) VALUES(?, ?)", new Object[]{ key, value }, new int[]{ Types.VARCHAR, Types.VARCHAR });
			log.info("Insert config: " + key + " => " + value);
//		}
		return rs;
	}

}
