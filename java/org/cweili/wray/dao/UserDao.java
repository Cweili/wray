package org.cweili.wray.dao;

import java.util.List;
import java.util.Map;

import org.cweili.wray.domain.User;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:08:39
 *
 */
public interface UserDao extends BaseDao<User> {
	
	/**
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<User> getUsersWithLimit(int start, int limit);
	
	/**
	 * @return
	 */
	public Map<Long, User> getAllUsers();

	/**
	 * @param permalink
	 * @return
	 */
	public User getUserByPermalink(String permalink);
	
	/**
	 * @param name
	 * @return
	 */
	public User getUserByName(String name);
}
