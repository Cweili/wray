package org.cweili.wray.service;

import java.util.List;

import org.cweili.wray.entity.Item;

/**
 * 链接 Service
 * 
 * @author Cweili
 * @version 2013-4-3 下午3:36:41
 * 
 */
public interface LinkService {

	public Item findById(String itemId);

	public List<Item> getLinks();

	public List<Item> getNavigators();

	public Item save(Item link);

	public boolean remove(Item link);

	public boolean remove(List<String> ids);

	public void updateLinkCache();

	public void updateNavigatorCache();
}
