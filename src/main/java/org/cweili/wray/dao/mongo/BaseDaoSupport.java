package org.cweili.wray.dao.mongo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * 
 * @author cweili
 * @version 2013-4-3 上午11:20:54
 * 
 */
public abstract class BaseDaoSupport {

	protected static Log log = LogFactory.getLog(BaseDaoSupport.class);

	@Autowired
	protected MongoTemplate db;
}
