package org.cweili.wray.dao;

import java.util.List;
import java.util.Map;

import org.cweili.wray.domain.User;

public interface UserDao extends BaseDao<User> {
	
	public List<User> getUsersWithLimit(int start, int limit);
	
	public Map<Long, User> getAllUsers();

	public User getUserByPermalink(String permalink);
	
	public User getUserByName(String name);
}
