package org.cweili.wray.dao.mongo;

import java.util.List;

import org.bson.types.ObjectId;
import org.cweili.wray.dao.UploadDao;
import org.cweili.wray.domain.Upload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("uploadDao")
public class UploadDaoImpl extends BaseDaoSupport<Upload> implements UploadDao, MongoRepository<Upload, ObjectId> {

	@Override
	public Page<Upload> findAll(Pageable arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public long count() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public void delete(ObjectId arg0) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void delete(Upload arg0) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void delete(Iterable<? extends Upload> arg0) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void deleteAll() {
		// TODO 自动生成的方法存根

	}

	@Override
	public boolean exists(ObjectId arg0) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public Iterable<Upload> findAll(Iterable<ObjectId> arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Upload findOne(ObjectId id) {
		return db.findById(id, Upload.class);
	}

	@Override
	public <S extends Upload> S save(S upload) {
		if ("".equals(upload.getUploadId())) {
			upload.setUploadId(new ObjectId().toString());
		}
		db.save(upload);
		return upload;
	}

	@Override
	public List<Upload> findAll() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<Upload> findAll(Sort arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public <S extends Upload> List<S> save(Iterable<S> arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

}
