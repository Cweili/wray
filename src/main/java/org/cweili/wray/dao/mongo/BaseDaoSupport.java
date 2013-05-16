package org.cweili.wray.dao.mongo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * 基础 DAO 支持
 * 
 * @author Cweili
 * @version 2013-4-3 上午11:20:54
 * 
 */
public abstract class BaseDaoSupport {

	protected static final Log log = LogFactory.getLog(BaseDaoSupport.class);

	@Autowired
	protected MongoTemplate db;

	// @Autowired
	// protected GridFsTemplate gfs;
}
