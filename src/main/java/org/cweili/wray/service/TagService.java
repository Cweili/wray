package org.cweili.wray.service;

import java.util.List;

import org.cweili.wray.domain.dto.Item;

/**
 * 标签 Service
 * 
 * @author Cweili
 * @version 2013-4-3 下午3:38:14
 * 
 */
public interface TagService {

	public Item findByName(String name);

	public Item findByPermalink(String name);

	public List<Item> getTags();

	public List<Item> getmostUsedTags(int size);

	public Item save(Item tag);

	public boolean remove(Item tag);

	public boolean remove(List<String> ids);

	public void updateTagCache();
}
