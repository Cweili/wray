package org.cweili.wray.service;

import java.sql.SQLException;
import java.util.List;

import org.cweili.wray.domain.Item;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:17:31
 *
 */
public interface TagService {
	
	/**
	 * @param name
	 * @return
	 */
	public long getIdByName(String name);

	/**
	 * @return
	 */
	public List<Item> getTags();
	
	/**
	 * @param tag
	 * @return
	 * @throws SQLException
	 */
	public long save(Item tag) throws SQLException;
	
	/**
	 * @param tag
	 * @return
	 * @throws SQLException
	 */
	public boolean update(Item tag) throws SQLException;
	
	/**
	 * @param tag
	 * @return
	 * @throws SQLException
	 */
	public boolean remove(Item tag) throws SQLException;
	
	/**
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public boolean remove(List<Long> ids) throws SQLException;
	
	/**
	 * 
	 */
	public void updateTagCache();
}
