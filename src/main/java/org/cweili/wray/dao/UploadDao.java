package org.cweili.wray.dao;

import org.cweili.wray.domain.Upload;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Cweili
 * @version 2013-3-21 下午3:34:53
 * 
 */
public interface UploadDao extends CrudRepository<Upload, String> {

	public Iterable<Upload> findAll(int start, int limit);
}
