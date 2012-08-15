package org.cweili.wray.service;

import java.sql.SQLException;
import java.util.List;

import org.cweili.wray.domain.Item;

public interface TagService {
	
	public long getIdByName(String name);

	public List<Item> getTags();
	
	public long save(Item tag) throws SQLException;
	
	public boolean update(Item tag) throws SQLException;
	
	public boolean remove(Item tag) throws SQLException;
	
	public boolean remove(List<Long> ids) throws SQLException;
	
	public void updateTagCache();
}
