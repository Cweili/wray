package org.cweili.wray.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Comment;
import org.cweili.wray.service.CommentService;

/**
 * 
 * @author cweili
 * @version 2012-11-25 下午3:23:57
 * 
 */
public class CommentServiceImpl extends BaseService implements CommentService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cweili.wray.service.CommentService#getCommentsByArticle(org.cweili
	 * .wray.domain.Article)
	 */
	@Override
	public List<Comment> getCommentsByArticle(Article article) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.CommentService#getComments(int, int)
	 */
	@Override
	public List<Comment> getComments(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cweili.wray.service.CommentService#save(org.cweili.wray.domain.Comment
	 * )
	 */
	@Override
	public long save(Comment comment) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cweili.wray.service.CommentService#update(org.cweili.wray.domain.
	 * Comment)
	 */
	@Override
	public boolean update(Comment comment) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.CommentService#remove(java.util.List)
	 */
	@Override
	public boolean remove(List<Long> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
