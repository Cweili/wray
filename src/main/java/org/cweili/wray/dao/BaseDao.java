/**
 * 
 */
package org.cweili.wray.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Cweili
 * @version 2013-4-3 上午11:27:37
 *
 */
public interface BaseDao<T> extends MongoRepository<T, String> {

}
