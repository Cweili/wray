package org.cweili.wray.service.impl;

import java.util.List;

import org.cweili.wray.domain.dto.Item;
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
		return itemDao.findByItemNameAndItemTypeAndStat(name, Item.TYPE_TAG, Item.STAT_ON);
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
		return itemDao.findByPermalinkAndItemTypeAndStat(name, Item.TYPE_TAG, Item.STAT_ON);
	}

	@Override
	public List<Item> getTags(int page, int size) {
		page = page > 0 ? page : 1;
		size = size > 0 ? size : 1;
		return itemDao.findByItemTypeAndStat(Item.TYPE_TAG, Item.STAT_ON,
				new PageRequest(page - 1, size, Sort.Direction.DESC, "count")).getContent();
	}

	@Override
	public List<Item> getmostUsedTags(int size) {
		if (size >= 0 && mostUsedTagsSize != size) {
			mostUsedTagsSize = size;
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
		if (mostUsedTagsSize > 0) {
			tags = itemDao.findByItemTypeAndStat(Item.TYPE_TAG, Item.STAT_ON,
					new PageRequest(0, mostUsedTagsSize, Sort.Direction.DESC, "count"))
					.getContent();
		}
	}

}
