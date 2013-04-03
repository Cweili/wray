package org.cweili.wray.dao;

import org.cweili.wray.domain.Upload;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 * @author cweili
 * @version 2013-3-21 下午3:34:53
 * 
 */
public interface UploadDao extends PagingAndSortingRepository<Upload, String> {

}
