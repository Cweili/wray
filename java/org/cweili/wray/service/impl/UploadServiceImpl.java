package org.cweili.wray.service.impl;

import java.util.List;

import org.cweili.wray.domain.Upload;
import org.cweili.wray.service.UploadService;
import org.springframework.stereotype.Service;

@Service("uploadService")
public class UploadServiceImpl extends BaseService implements UploadService {

	@Override
	public Upload save(Upload upload) {
		return uploadDao.save(upload);
	}

	@Override
	public boolean remove(List<String> ids) {
		return uploadDao.remove(ids) > 0;
	}

	@Override
	public Upload getUploadById(String uploadId) {
		return uploadDao.getUploadById(uploadId);
	}

	@Override
	public List<Upload> getUploads(int page, int limit) {
		int start = (page - 1) * limit;
		return uploadDao.getUploads(start, limit);
	}

}
