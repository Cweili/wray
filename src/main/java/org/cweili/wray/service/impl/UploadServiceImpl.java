package org.cweili.wray.service.impl;

import java.util.ArrayList;
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
		long before = uploadDao.count();
		List<Upload> uploadList = new ArrayList<Upload>();
		for (String id : ids) {
			Upload upload = new Upload();
			upload.setId(id);
			uploadList.add(upload);
		}
		uploadDao.delete(uploadList);
		return uploadDao.count() < before;
	}

	@Override
	public Upload getUploadById(String id) {
		return uploadDao.findOne(id);
	}

	@Override
	public List<Upload> getUploads(int page, int limit) {
		int start = (page - 1) * limit;
		return (List<Upload>) uploadDao.findAll(start, limit);
	}

	@Override
	public int getCount() {
		return (int) uploadDao.count();
	}

}
