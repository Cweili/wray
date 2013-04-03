package org.cweili.wray.dao.mongo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.cweili.wray.dao.UploadDao;
import org.cweili.wray.domain.Upload;
import org.cweili.wray.util.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		gfs.remove(new BasicDBObject("_id", upload.getUploadId()));
		log.info("Delete upload " + upload.getUploadId());
	}

	@Override
	public void delete(Iterable<? extends Upload> uploadList) {
		setGfs();
		for (Upload upload : uploadList) {
			gfs.remove(new BasicDBObject("_id", upload.getUploadId()));
			log.info("Delete upload " + upload.getUploadId());
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
		if ("".equals(upload.getUploadId())) {
			upload.setUploadId(Function.generateId());
		}
		setGfs();
		GridFSInputFile file = gfs.createFile(upload.getContent());
		file.setFilename(upload.getFilename());
		file.setContentType(upload.getContentType());
		file.setId(upload.getUploadId());
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

	@Override
	public Iterable<Upload> findAll(Sort arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Page<Upload> findAll(Pageable page) {
		Query q = new Query();
		q.with(page.getSort());
		q.skip(page.getOffset());
		q.limit(page.getPageSize());
		log.info("Find upload from " + page.getOffset() + " to " + page.getOffset());
		return new PageImpl<Upload>(db.find(q, Upload.class, UPLOAD_FILE_GRIDFS + ".files"));
	}

}
