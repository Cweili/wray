package org.cweili.wray.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.cweili.wray.domain.Item;
import org.cweili.wray.service.CategoryService;

public class CategoryServiceImpl extends BaseService implements CategoryService {

	@Override
	public Item getCategoryById(long id) {
		return itemDao.getItemById(id);
	}
	
	@Override
	public Item getCategoryByPermalink(String permalink) {
		if(categories == null) {
			updateCategoryCache();
		}
		return categoryMap.get(permalink);
	}

	@Override
	public List<Item> getCategories() {
		if(categories == null) {
			updateCategoryCache();
		}
		return categories;
	}

	@Override
	public long save(Item category) throws SQLException {
		long rs = itemDao.save(category);
		if(rs < 1) {
			throw new SQLException("Category save error");
		} else {
			updateCategoryCache();
		}
		return rs;
	}

	@Override
	public boolean update(Item category) throws SQLException {
		int rs = itemDao.update(category);
		if(rs < 1) {
			throw new SQLException("Category update error");
		} else {
			updateCategoryCache();
		}
		return rs > 0;
	}

	@Override
	public boolean remove(Item category) throws SQLException {
		int rs = itemDao.remove(category);
		if(rs < 1) {
			throw new SQLException("Category remove error");
		} else {
			updateCategoryCache();
		}
		return rs > 0;
	}

	@Override
	public boolean remove(List<Long> ids) throws SQLException {
		int rs = itemDao.remove(ids);
		if(rs < 1) {
			throw new SQLException("Category remove error");
		} else {
			updateCategoryCache();
		}
		return rs > 0;
	}

	@Override
	public void updateCategoryCache() {
		categories = itemDao.getItems(Item.TYPE_CATEGORY, "item_order");
		
		categoryMap.clear();
		
		for(int i = 0; i < categories.size(); ++i) {
			categoryMap.put(categories.get(i).getPermalink(), categories.get(i));
		}
		
//		if(!categories.isEmpty()) {
//			Collections.sort(categories, new Comparator<Item>() {
//	
//				public int compare(Item i1, Item i2) {
//					return new Integer(i1.getItemOrder()).compareTo(new Integer(i2.getItemOrder()));
//				}
//			});
//		}
	}

}
