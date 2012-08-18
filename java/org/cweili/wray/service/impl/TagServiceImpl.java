package org.cweili.wray.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.cweili.wray.domain.Item;
import org.cweili.wray.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:23:30
 *
 */
@Service("tagService")
public class TagServiceImpl extends BaseService implements TagService {
	
	/* (non-Javadoc)
	 * @see org.cweili.wray.service.TagService#getIdByName(java.lang.String)
	 */
	@Override
	public long getIdByName(String name) {
		if(tags == null) {
			updateTagCache();
		}
		for(Item item : tags) {
			if(item.getItemName().equals(name)) {
				return item.getItemId();
			}
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.service.TagService#getTags()
	 */
	@Override
	public List<Item> getTags() {
		if(tags == null) {
			updateTagCache();
		}
		return tags;
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.service.TagService#save(org.cweili.wray.domain.Item)
	 */
	@Override
	public long save(Item tag, boolean updateCache) throws SQLException {
		long rs = itemDao.save(tag);
		if(rs < 1) {
			throw new SQLException("Tag save error");
		} else {
			if(updateCache) {
				updateTagCache();
			}
		}
		return rs;
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.service.TagService#update(org.cweili.wray.domain.Item)
	 */
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

	/* (non-Javadoc)
	 * @see org.cweili.wray.service.TagService#remove(org.cweili.wray.domain.Item)
	 */
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

	/* (non-Javadoc)
	 * @see org.cweili.wray.service.TagService#remove(java.util.List)
	 */
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

	/* (non-Javadoc)
	 * @see org.cweili.wray.service.TagService#updateTagCache()
	 */
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
