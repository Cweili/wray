package org.cweili.wray.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.cweili.wray.domain.Page;
import org.cweili.wray.domain.dto.Upload;
import org.cweili.wray.service.UploadService;
import org.cweili.wray.util.Zlib;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 附件 Service 实现
 * 
 * @author Cweili
 * @version 2013-5-16 下午5:33:00
 * 
 */
@Service("uploadService")
public class UploadServiceImpl extends BaseService implements UploadService {

	@Override
	public Upload save(Upload upload) {
		if (!Upload.COMPRESSED.contains(upload.getContentType())) {
			byte[] commpressed = Zlib.compress(upload.getContent());
			if (commpressed.length < upload.getContent().length) {
				upload.setContent(commpressed);
			}
		}
		return uploadDao.save(upload);
	}

	@Override
	public boolean remove(List<String> ids) {
		long before = uploadDao.count();
		List<Upload> uploadList = new ArrayList<Upload>();
		for (String id : ids) {
			Upload upload = new Upload();
			upload.setUploadId(id);
			uploadList.add(upload);
		}
		uploadDao.delete(uploadList);
		return uploadDao.count() < before;
	}

	@Override
	public Upload getUploadById(String id) {
		Upload upload = uploadDao.findOne(id);
		if (null != upload) {
			if (!Upload.COMPRESSED.contains(upload.getContentType())) {
				upload.setContent(Zlib.decompress(upload.getContent()));
				upload.setLength(upload.getContent().length);
			}
		}
		return upload;
	}

	@Override
	public Page<Upload> getUploads(int page, int size) {
		return new Page<Upload>(uploadDao.findAll(new PageRequest(page - 1, size,
				Sort.Direction.DESC, "_id")));
	}

	@Override
	public int getCount() {
		return (int) uploadDao.count();
	}

}
