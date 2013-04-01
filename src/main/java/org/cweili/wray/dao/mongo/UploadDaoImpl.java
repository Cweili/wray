package org.cweili.wray.dao.mongo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.cweili.wray.dao.UploadDao;
import org.cweili.wray.domain.Upload;
import org.cweili.wray.util.Function;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Repository("uploadDao")
public class UploadDaoImpl extends BaseDaoSupport implements UploadDao {

	private static final String UPLOAD_FILE_GRIDFS = "upload";
	private static Log log = LogFactory.getLog(UploadDaoImpl.class);
	private static GridFS gfs;

	@Override
	public Iterable<Upload> findAll(int start, int limit) {
		Query q = new Query();
		q.skip(start);
		q.limit(limit);
		log.info("Find upload from " + start + " to " + limit);
		return db.find(q, Upload.class, UPLOAD_FILE_GRIDFS + ".files");
	}

	@Override
	public long count() {
		long count = db.count(new Query(), UPLOAD_FILE_GRIDFS + ".files");
		log.info("Count upload: " + count);
		return count;
	}

	@Override
	public void delete(String id) {
		setGfs();
		gfs.remove(new ObjectId(id));
		log.info("Delete upload " + id);
	}

	@Override
	public void delete(Upload upload) {
		setGfs();
		gfs.remove(new ObjectId("" + upload.getId()));
		log.info("Delete upload " + upload.getId());
	}

	@Override
	public void delete(Iterable<? extends Upload> uploadList) {
		setGfs();
		for (Upload upload : uploadList) {
			gfs.remove(new ObjectId("" + upload.getId()));
			log.info("Delete upload " + upload.getId());
		}
	}

	@Override
	public void deleteAll() {
	}

	@Override
	public boolean exists(String id) {
		return false;
	}

	@Override
	public Iterable<Upload> findAll() {
		return null;
	}

	@Override
	public Iterable<Upload> findAll(Iterable<String> ids) {
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
			log.error("Write upload content error.", e);
		}
		log.info("Find upload " + upload.getFilename());
		return upload;
	}

	@Override
	public <S extends Upload> S save(S upload) {
		if ("".equals(upload.getId())) {
			upload.setId(Function.shortId(Function.generateId()));
		}
		setGfs();
		GridFSInputFile file = gfs.createFile(upload.getContent());
		file.setFilename(upload.getFilename());
		file.setContentType(upload.getContentType());
		file.setId(upload.getId());
		file.save();
		log.info("Save upload " + upload.getFilename());
		return upload;
	}

	@Override
	public <S extends Upload> Iterable<S> save(Iterable<S> arg0) {
		return null;
	}

	private void setGfs() {
		if (null == gfs) {
			gfs = new GridFS(db.getDb(), UPLOAD_FILE_GRIDFS);
		}
	}

}
