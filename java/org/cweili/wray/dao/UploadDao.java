package org.cweili.wray.dao;

import java.util.List;

import org.cweili.wray.domain.Upload;

/**
 * 
 * @author Cweili
 * @version 2013-3-21 下午3:34:53
 * 
 */
public interface UploadDao {

	/**
	 * @param id
	 * @return
	 */
	public Upload getUploadById(final String id);

	public List<Upload> getUploads(final int start, final int limit);

	public Upload save(final Upload upload);

	public int update(final Upload upload);

	public int remove(final Upload upload);

	public int remove(final List<String> ids);
}
