/**
 * 
 */
package org.cweili.wray.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * @author Cweili
 * @version 2013-4-3 上午11:27:37
 * 
 */
@NoRepositoryBean
public interface BaseDao<T> extends MongoRepository<T, String> {

}
