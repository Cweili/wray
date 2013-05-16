package org.cweili.wray.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.dto.Item;
import org.cweili.wray.service.LinkService;
import org.cweili.wray.util.Function;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 链接 Service 实现
 * 
 * @author Cweili
 * @version 2013-4-3 下午3:40:41
 * 
 */
@Service("linkService")
public class LinkServiceImpl extends BaseService implements LinkService {

	@Override
	public Item findById(String itemId) {
		if (links == null) {
			updateLinkCache();
		}
		for (Item item : links) {
			if (item.getItemId().equals(itemId)) {
				return item;
			}
		}
		return itemDao.findOne(itemId);
	}

	@Override
	public List<Item> getLinks() {
		if (links == null) {
			updateLinkCache();
		}
		return links;
	}

	@Override
	public List<Item> getNavigators() {
		if (navigators == null) {
			updateNavigatorCache();
		}
		return navigators;
	}

	@Override
	public Item save(Item item) {
		if (StringUtils.isEmpty(item.getItemId())) {
			item.setItemId(Function.generateId());
		}
		Item itemNew = itemDao.save(item);
		if (null != itemNew) {
			if (Item.TYPE_LINK == itemNew.getItemType()) {
				updateLinkCache();
			} else {
				updateNavigatorCache();
			}
		}
		return itemNew;
	}

	@Override
	public boolean remove(Item item) {
		item = itemDao.findOne(item.getItemId());
		if (null != item) {
			item.setStat(Item.STAT_OFF);
			Item itemNew = itemDao.save(item);
			if (null != itemNew) {
				updateLinkCache();
			}
			return null != itemNew;
		}
		return false;
	}

	@Override
	public boolean remove(List<String> ids) {
		Iterable<Item> items = itemDao.findAll(ids);
		for (Item item : items) {
			item.setStat(Item.STAT_OFF);
		}
		items = itemDao.save(items);
		if (null != items) {
			updateLinkCache();
		}
		return null != items;
	}

	@Override
	public void updateLinkCache() {
		links = itemDao.findByItemTypeAndStat(Item.TYPE_LINK, Item.STAT_ON, new Sort(
				Sort.Direction.ASC, "itemOrder"));
	}

	@Override
	public void updateNavigatorCache() {
		navigators = itemDao.findByItemTypeAndStat(Item.TYPE_NAVIGATOR, Item.STAT_ON, new Sort(
				Sort.Direction.ASC, "itemOrder"));
	}

}
