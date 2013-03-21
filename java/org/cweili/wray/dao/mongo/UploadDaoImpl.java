package org.cweili.wray.dao.mongo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.cweili.wray.dao.UploadDao;
import org.cweili.wray.domain.Upload;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Repository("uploadDao")
public class UploadDaoImpl extends BaseDaoSupport<Upload> implements UploadDao {

	private static final String UPLOAD_FILE_GRIDFS = "uploadfile";
	private static GridFS gfs;

	@Override
	public int update(Upload t) {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public Upload getUploadById(String id) {
		Upload upload = db.findById(id, Upload.class);
		setGfs();
		GridFSDBFile file = gfs.findOne(id.toString());
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			file.writeTo(os);
			upload.setUploadFile(os.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return upload;
	}

	@Override
	public List<Upload> getUploads(int start, int limit) {
		Query q = new Query();
		q.skip(start);
		q.limit(limit);
		return db.find(q, Upload.class);
	}

	@Override
	public Upload save(Upload upload) {
		if ("".equals(upload.getUploadId())) {
			upload.setUploadId(new ObjectId().toString());
		}
		db.save(upload);
		setGfs();
		GridFSInputFile file = gfs.createFile(upload.getUploadFile());
		file.setFilename(upload.getUploadId());
		file.save();
		return upload;
	}

	@Override
	public int remove(Upload upload) {
		db.remove(upload);
		setGfs();
		gfs.remove(upload.getUploadId());
		return 1;
	}

	@Override
	public int remove(List<String> ids) {
		for (String id : ids) {
			db.remove(new Query(Criteria.where("_id").is(id)), Upload.class);
			setGfs();
			gfs.remove(id);
		}
		return 1;
	}

	private void setGfs() {
		if (null == gfs) {
			gfs = new GridFS(db.getDb(), UPLOAD_FILE_GRIDFS);
		}
	}

}
