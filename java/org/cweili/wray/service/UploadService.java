package org.cweili.wray.service;

import org.cweili.wray.domain.Upload;

/**
 * 
 * @author cweili
 * @version 2013-3-14 下午2:45:33
 * 
 */
public interface UploadService {

	/**
	 * @param upload
	 * @return
	 */
	public Upload save(Upload upload);

	/**
	 * @param uploadId
	 * @return
	 */
	public Upload getUploadById(String uploadId);
}
