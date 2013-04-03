package org.cweili.wray.service.impl;

import java.util.List;

import org.cweili.wray.domain.Item;
import org.cweili.wray.service.TagService;
import org.cweili.wray.util.Function;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:23:30
 * 
 */
@Service("tagService")
public class TagServiceImpl extends BaseService implements TagService {

	@Override
	public Item findByName(String name) {
		if (tags == null) {
			updateTagCache();
		}
		for (Item item : tags) {
			if (item.getItemName().equals(name)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public Item findByPermalink(String name) {
		if (tags == null) {
			updateTagCache();
		}
		for (Item item : tags) {
			if (item.getPermalink().equals(name)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public List<Item> getTags() {
		if (tags == null) {
			updateTagCache();
		}
		return tags;
	}

	@Override
	public Item save(Item item) {
		if ("".equals(item.getItemId())) {
			item.setItemId(Function.generateId());
		}
		Item itemNew = itemDao.save(item);
		if (null != itemNew) {
			updateTagCache();
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
				updateTagCache();
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
			updateTagCache();
		}
		return null != items;
	}

	@Override
	public void updateTagCache() {
		tags = itemDao.findByItemTypeAndStat(Item.TYPE_TAG, Item.STAT_ON,
				new PageRequest(1, 65535, Sort.Direction.DESC, "count")).getContent();
	}

}
