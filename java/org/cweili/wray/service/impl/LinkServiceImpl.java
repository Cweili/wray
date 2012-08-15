package org.cweili.wray.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.cweili.wray.domain.Item;
import org.cweili.wray.service.LinkService;
import org.springframework.stereotype.Service;

@Service("linkService")
public class LinkServiceImpl extends BaseService implements LinkService {
	
	@Override
	public Item getLinkById(long id) {
		if(links == null) {
			updateLinkCache();
		}
		for(int i = 0; i < links.size(); ++i) {
			if(links.get(i).getItemId() == id) {
				return links.get(i);
			}
		}
		return itemDao.getItemById(id);
	}

	@Override
	public List<Item> getLinks() {
		if(links == null) {
			updateLinkCache();
		}
		return links;
	}

	@Override
	public long save(Item link) throws SQLException {
		long rs = itemDao.save(link);
		if(rs < 1) {
			throw new SQLException("Link save error");
		} else {
			updateLinkCache();
		}
		return rs;
	}

	@Override
	public boolean update(Item link) throws SQLException {
		int rs = itemDao.update(link);
		if(rs < 1) {
			throw new SQLException("Link update error");
		} else {
			updateLinkCache();
		}
		return rs > 0;
	}

	@Override
	public boolean remove(Item link) throws SQLException {
		int rs = itemDao.remove(link);
		if(rs < 1) {
			throw new SQLException("Link remove error");
		} else {
			updateLinkCache();
		}
		return rs > 0;
	}
	
	@Override
	public boolean remove(List<Long> ids) throws SQLException {
		int rs = itemDao.remove(ids);
		if(rs < 1) {
			throw new SQLException("Link remove error");
		} else {
			updateLinkCache();
		}
		return rs > 0;
	}

	@Override
	public void updateLinkCache() {
		links = itemDao.getItems(Item.TYPE_LINK, "item_order");
		if(!links.isEmpty()) {
			Collections.sort(links, new Comparator<Item>() {
	
				public int compare(Item i1, Item i2) {
					return new Integer(i1.getItemOrder()).compareTo(new Integer(i2.getItemOrder()));
				}
			});
		}
	}

}
