package org.cweili.wray.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.cweili.wray.domain.Item;
import org.cweili.wray.service.TagService;

public class TagServiceImpl extends BaseService implements TagService {
	
	@Override
	public long getIdByName(String name) {
		if(tags == null) {
			updateTagCache();
		}
		for(int i = 0; i < tags.size(); ++i) {
			if(tags.get(i).getItemName().equals(name)) {
				return tags.get(i).getItemId();
			}
		}
		return 0;
	}

	@Override
	public List<Item> getTags() {
		if(tags == null) {
			updateTagCache();
		}
		return tags;
	}

	@Override
	public long save(Item tag) throws SQLException {
		long rs = itemDao.save(tag);
		if(rs < 1) {
			throw new SQLException("Tag save error");
		} else {
			updateTagCache();
		}
		return rs;
	}

	@Override
	public boolean update(Item tag) throws SQLException {
		int rs = itemDao.update(tag);
		if(rs < 1) {
			throw new SQLException("Tag update error");
		} else {
			updateTagCache();
		}
		return rs > 0;
	}

	@Override
	public boolean remove(Item tag) throws SQLException {
		int rs = itemDao.remove(tag);
		if(rs < 1) {
			throw new SQLException("Tag remove error");
		} else {
			updateTagCache();
		}
		return rs > 0;
	}

	@Override
	public boolean remove(List<Long> ids) throws SQLException {
		int rs = itemDao.remove(ids);
		if(rs < 1) {
			throw new SQLException("Tag remove error");
		} else {
			updateTagCache();
		}
		return rs > 0;
	}

	@Override
	public void updateTagCache() {
		tags = itemDao.getItems(Item.TYPE_TAG, "count DESC");
		
//		if(!tags.isEmpty()) {
//			Collections.sort(tags, new Comparator<Item>() {
//	
//				public int compare(Item i1, Item i2) {
//					return new Integer(i2.getCount()).compareTo(new Integer(i1.getCount()));
//				}
//			});
//		}
	}

}
