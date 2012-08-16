package org.cweili.wray.service;

import java.sql.SQLException;
import java.util.List;

import org.cweili.wray.domain.Item;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:17:11
 *
 */
public interface LinkService {

	/**
	 * @param id
	 * @return
	 */
	public Item getLinkById(long id);
	
	/**
	 * @return
	 */
	public List<Item> getLinks();
	
	/**
	 * @param link
	 * @return
	 * @throws SQLException
	 */
	public long save(Item link) throws SQLException;
	
	/**
	 * @param link
	 * @return
	 * @throws SQLException
	 */
	public boolean update(Item link) throws SQLException;
	
	/**
	 * @param link
	 * @return
	 * @throws SQLException
	 */
	public boolean remove(Item link) throws SQLException;
	
	/**
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public boolean remove(List<Long> ids) throws SQLException;
	
	/**
	 * 
	 */
	public void updateLinkCache();
}
