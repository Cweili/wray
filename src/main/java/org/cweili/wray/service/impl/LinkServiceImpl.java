package org.cweili.wray.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.cweili.wray.domain.Item;
import org.cweili.wray.service.LinkService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:23:06
 * 
 */
@Service("linkService")
public class LinkServiceImpl extends BaseService implements LinkService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.LinkService#getLinkById(long)
	 */
	@Override
	public Item getLinkById(long id) {
		if (links == null) {
			updateLinkCache();
		}
		for (Item item : links) {
			if (item.getItemId() == id) {
				return item;
			}
		}
		return itemDao.getItemById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.LinkService#getLinks()
	 */
	@Override
	public List<Item> getLinks() {
		if (links == null) {
			updateLinkCache();
		}
		return links;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cweili.wray.service.LinkService#save(org.cweili.wray.domain.Item)
	 */
	@Override
	public long save(Item link) throws SQLException {
		long rs = itemDao.save(link);
		if (rs < 1) {
			throw new SQLException("Link save error");
		} else {
			updateLinkCache();
		}
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cweili.wray.service.LinkService#update(org.cweili.wray.domain.Item)
	 */
	@Override
	public boolean update(Item link, boolean updateCache) throws SQLException {
		int rs = itemDao.update(link);
		if (rs < 1) {
			throw new SQLException("Link update error");
		} else {
			if (updateCache) {
				updateLinkCache();
			}
		}
		return rs > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cweili.wray.service.LinkService#remove(org.cweili.wray.domain.Item)
	 */
	@Override
	public boolean remove(Item link) throws SQLException {
		int rs = itemDao.remove(link);
		if (rs < 1) {
			throw new SQLException("Link remove error");
		} else {
			updateLinkCache();
		}
		return rs > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.LinkService#remove(java.util.List)
	 */
	@Override
	public boolean remove(List<Long> ids) throws SQLException {
		int rs = itemDao.remove(ids);
		if (rs < 1) {
			throw new SQLException("Link remove error");
		} else {
			updateLinkCache();
		}
		return rs > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cweili.wray.service.LinkService#updateLinkCache()
	 */
	@Override
	public void updateLinkCache() {
		links = itemDao.getItems(Item.TYPE_LINK, "item_order");
		if (!links.isEmpty()) {
			Collections.sort(links, new Comparator<Item>() {

				public int compare(Item i1, Item i2) {
					return new Integer(i1.getItemOrder()).compareTo(new Integer(i2.getItemOrder()));
				}
			});
		}
	}

}
