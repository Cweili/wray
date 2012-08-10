package org.cweili.wray.dao.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.dao.ConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

@Repository("configDao")
public class ConfigDaoImpl implements ConfigDao {

	private Log log = LogFactory.getLog(ConfigDaoImpl.class);

	@Autowired
	private JdbcTemplate db;
	
	@Override
	public String get(String key) {

		return null;
	}

	@Override
	public Map<String, String> getAll() {
		final Map<String, String> map = new HashMap<String, String>();
		db.query("SELECT * FROM config", new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				map.put(rs.getString(1), rs.getString(2));
				log.info("Load config: " + rs.getString(1) + " => " + rs.getString(2));
			}
		});
		return map;
	}

	@Override
	public int saveOrUpdate(String key, String value) {
		return 0;
	}

}
