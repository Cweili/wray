package org.cweili.wray.dao;

import java.util.List;
import java.util.Map;

import org.cweili.wray.domain.Item;

public interface ItemDao extends BaseDao<Item> {

	public Item getItemById(long id);
	
	public Item getItemByPermalink(String permalink, byte type);
	
	@Deprecated
	public Map<String, Item> getItemMap(byte type, String order);
	
	public List<Item> getItems(byte type, String order);
	
	public int remove(List<Long> ids);

}
