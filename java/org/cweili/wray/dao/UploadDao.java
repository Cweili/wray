package org.cweili.wray.dao;

import org.bson.types.ObjectId;
import org.cweili.wray.domain.Upload;

public interface UploadDao {

	public Upload findOne(ObjectId id);

	public <S extends Upload> S save(S upload);

}
