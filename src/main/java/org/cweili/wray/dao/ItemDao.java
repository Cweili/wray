package org.cweili.wray.dao;

import java.util.List;
import java.util.Map;

import org.cweili.wray.domain.Item;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午4:55:07
 * 
 */
public interface ItemDao extends BaseDao<Item> {

	/**
	 * @param id
	 * @return
	 */
	public Item getItemById(long id);

	/**
	 * @param permalink
	 * @param type
	 * @return
	 */
	public Item getItemByPermalink(String permalink, byte type);

	/**
	 * @param type
	 * @param order
	 * @return
	 */
	@Deprecated
	public Map<String, Item> getItemMap(byte type, String order);

	/**
	 * @param type
	 * @param order
	 * @return
	 */
	public List<Item> getItems(byte type, String order);

	/**
	 * @param id
	 * @return
	 */
	public List<Item> getItemsByRelationship(long id);

	/**
	 * @param id
	 * @param type
	 * @param order
	 * @return
	 */
	public List<Item> getItemsByRelationship(long id, byte type, String order);

	/**
	 * @param ids
	 * @return
	 */
	public int remove(List<Long> ids);

}
