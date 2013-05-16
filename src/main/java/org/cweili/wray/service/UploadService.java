package org.cweili.wray.service;

import java.util.List;

import org.cweili.wray.domain.Page;
import org.cweili.wray.domain.dto.Upload;

/**
 * 附件 Service
 * 
 * @author Cweili
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
	 * @param ids
	 * @return
	 */
	public boolean remove(List<String> ids);

	/**
	 * @return
	 */
	public int getCount();

	/**
	 * @param id
	 * @return
	 */
	public Upload getUploadById(String id);

	/**
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Upload> getUploads(int page, int size);
}
