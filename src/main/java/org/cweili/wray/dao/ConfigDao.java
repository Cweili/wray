package org.cweili.wray.dao;

import org.cweili.wray.domain.Config;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:07:48
 * 
 */
public interface ConfigDao extends MongoRepository<Config, String> {

}
