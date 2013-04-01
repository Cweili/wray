package org.cweili.wray.dao;

import java.util.List;

import org.cweili.wray.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 
 * @author cweili
 * @version 2012-8-21 下午4:07:20
 * 
 */
public interface CommentDao extends MongoRepository<Comment, Long> {

	public List<Comment> findByArticleIdAndStat(long articleId, byte stat);

}
