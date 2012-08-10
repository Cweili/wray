package org.cweili.wray.service;

import java.util.List;

import org.cweili.wray.domain.Item;

public interface LinkService {

	public Item getLinkById(long id);
	
	public List<Item> getLinks(int page, int limit);
	
	public long save(final Item link);
	
	public int update(final Item link);
	
	public int remove(final Item link);
}
