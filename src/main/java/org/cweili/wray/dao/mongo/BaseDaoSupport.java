package org.cweili.wray.dao.mongo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class BaseDaoSupport<T> {

	protected Log log = LogFactory.getLog(BaseDaoSupport.class);

	@Autowired
	protected MongoTemplate db;
}
