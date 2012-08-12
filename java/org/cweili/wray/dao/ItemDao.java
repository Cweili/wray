package org.cweili.wray.dao;

import java.util.List;
import java.util.Map;

import org.cweili.wray.domain.Item;

public interface ItemDao extends BaseDao<Item> {

	public Item getItemById(long id);
	
	public Item getItemByPermalink(String permalink, byte type);
	
	public Map<Long, Item> getItemMapByType(byte type);
	
	public List<Item> getItemsByType(byte type);
	
	public int remove(List<Long> ids);

}
