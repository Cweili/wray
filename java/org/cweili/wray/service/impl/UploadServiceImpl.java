package org.cweili.wray.service.impl;

import org.bson.types.ObjectId;
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
	public Upload getUploadById(String uploadId) {
		ObjectId objectId = ObjectId.massageToObjectId(uploadId);
		if (null != objectId) {
			return uploadDao.findOne(ObjectId.massageToObjectId(uploadId));
		}
		return null;
	}

}
