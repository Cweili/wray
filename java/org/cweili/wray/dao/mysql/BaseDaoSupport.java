package org.cweili.wray.dao.mysql;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseDaoSupport<T> implements BaseDao<T> {
	
	protected Log log = LogFactory.getLog(BaseDaoSupport.class);

	@Autowired
	protected JdbcTemplate db;
}
