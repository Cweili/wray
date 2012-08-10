package org.cweili.wray.dao.mysql;

import java.util.Map;

import org.cweili.wray.dao.ItemDao;
import org.cweili.wray.domain.Item;

public class ItemDaoImpl extends BaseDaoSupport<Item> implements ItemDao {

	@Override
	public long save(Item t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Item t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Item t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Item getItemById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItemByPermalink(String permalink, byte type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Long, Item> getItemsByType(byte type) {
		// TODO Auto-generated method stub
		return null;
	}


}
