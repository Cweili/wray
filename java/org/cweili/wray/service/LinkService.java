package org.cweili.wray.service;

import java.sql.SQLException;
import java.util.List;

import org.cweili.wray.domain.Item;

public interface LinkService {

	public Item getLinkById(long id);
	
	public List<Item> getLinks();
	
	public long save(Item link) throws SQLException;
	
	public boolean update(Item link) throws SQLException;
	
	public boolean remove(Item link) throws SQLException;
	
	public boolean remove(List<Long> ids) throws SQLException;
	
	public void updateLinkCache();
}
