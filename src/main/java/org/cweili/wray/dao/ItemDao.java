package org.cweili.wray.dao;

import java.util.List;

import org.cweili.wray.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 项目 DAO
 * 
 * @author Cweili
 * @version 2013-4-3 上午11:20:33
 * 
 */
public interface ItemDao extends BaseDao<Item> {

	public Item findByItemNameAndItemTypeAndStat(String itemName, byte itemType, byte stat);

	public List<Item> findByItemType(byte itemType, Sort sort);

	public List<Item> findByItemTypeAndStat(byte itemType, byte stat, Sort sort);

	public Page<Item> findByItemTypeAndStat(byte itemType, byte stat, Pageable page);

	public Item findByPermalinkAndItemTypeAndStat(String permalink, byte itemType, byte stat);

	// /**
	// * @param id
	// * @return
	// */
	// public Item getItemById(long id);
	//
	// /**
	// * @param permalink
	// * @param type
	// * @return
	// */
	// public Item getItemByPermalink(String permalink, byte type);
	//
	// /**
	// * @param type
	// * @param order
	// * @return
	// */
	// @Deprecated
	// public Map<String, Item> getItemMap(byte type, String order);
	//
	// /**
	// * @param type
	// * @param order
	// * @return
	// */
	// public List<Item> getItems(byte type, String order);
	//
	// /**
	// * @param id
	// * @return
	// */
	// public List<Item> getItemsByRelationship(long id);
	//
	// /**
	// * @param id
	// * @param type
	// * @param order
	// * @return
	// */
	// public List<Item> getItemsByRelationship(long id, byte type, String
	// order);
	//
	// /**
	// * @param ids
	// * @return
	// */
	// public int remove(List<Long> ids);

}
