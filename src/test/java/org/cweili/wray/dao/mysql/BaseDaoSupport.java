package org.cweili.wray.dao.mysql;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.dao.OldBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 
 * @author Cweili
 * @version 2012-8-16 下午5:09:00
 * 
 * @param <T>
 */
public abstract class BaseDaoSupport<T> implements OldBaseDao<T> {

	protected Log log = LogFactory.getLog(BaseDaoSupport.class);

	@Autowired
	protected JdbcTemplate db;
}
