/**
 * 
 */
package org.cweili.wray.dao;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 * @author Cweili
 * @version 2013-4-3 上午11:27:37
 * 
 */
@NoRepositoryBean
public interface BaseDao<T> extends PagingAndSortingRepository<T, String> {

}
