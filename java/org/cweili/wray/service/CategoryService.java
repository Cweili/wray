package org.cweili.wray.service;

import java.sql.SQLException;
import java.util.List;

import org.cweili.wray.domain.Item;

public interface CategoryService {

	public Item getCategoryById(long id);
	
	public Item getCategoryByPermalink(String permalink);

	public List<Item> getCategories();
	
	public long save(Item category) throws SQLException;
	
	public boolean update(Item category) throws SQLException;
	
	public boolean remove(Item category) throws SQLException;
	
	public boolean remove(List<Long> ids) throws SQLException;
	
	public void updateCategoryCache();
}
