package org.cweili.wray.dao.mongo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.bson.types.ObjectId;
import org.cweili.wray.dao.UploadDao;
import org.cweili.wray.domain.Upload;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Repository("uploadDao")
public class UploadDaoImpl extends BaseDaoSupport implements UploadDao {

	private static final String UPLOAD_FILE_GRIDFS = "upload";
	private static GridFS gfs;

	@Override
	public Iterable<Upload> findAll(int start, int limit) {
		Query q = new Query();
		q.skip(start);
		q.limit(limit);
		return db.find(q, Upload.class, UPLOAD_FILE_GRIDFS + ".files");
	}

	@Override
	public long count() {
		return db.count(new Query(), UPLOAD_FILE_GRIDFS + ".files");
	}

	@Override
	public void delete(String id) {
		setGfs();
		gfs.remove(new ObjectId(id));
	}

	@Override
	public void delete(Upload upload) {
		setGfs();
		gfs.remove(new ObjectId(upload.getId()));
	}

	@Override
	public void delete(Iterable<? extends Upload> uploadList) {
		setGfs();
		for (Upload upload : uploadList) {
			gfs.remove(new ObjectId(upload.getId()));
		}
	}

	@Override
	public void deleteAll() {
		// TODO 自动生成的方法存根

	}

	@Override
	public boolean exists(String arg0) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public Iterable<Upload> findAll() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Iterable<Upload> findAll(Iterable<String> arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Upload findOne(String id) {
		setGfs();
		// GridFSDBFile file = gfs.find(new ObjectId(id));
		GridFSDBFile file = gfs.findOne(new BasicDBObject("_id", id));
		if (null == file) {
			return new Upload();
		}
		Upload upload = new Upload(id, (int) file.getLength(), file.getMD5(), file.getFilename(),
				file.getContentType(), file.getUploadDate());
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			file.writeTo(os);
			upload.setContent(os.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return upload;
	}

	@Override
	public <S extends Upload> S save(S upload) {
		if ("".equals(upload.getId())) {
			upload.setId(new ObjectId().toString());
		}
		setGfs();
		GridFSInputFile file = gfs.createFile(upload.getContent());
		file.setFilename(upload.getFilename());
		file.setContentType(upload.getContentType());
		file.setId(upload.getId());
		file.save();
		return upload;
	}

	@Override
	public <S extends Upload> Iterable<S> save(Iterable<S> arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	private void setGfs() {
		if (null == gfs) {
			gfs = new GridFS(db.getDb(), UPLOAD_FILE_GRIDFS);
		}
	}

}
