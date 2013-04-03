package org.cweili.wray.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;
import org.cweili.wray.domain.Relationship;
import org.cweili.wray.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:22:11
 * 
 */
@Service("categoryService")
public class CategoryServiceImpl extends BaseService implements CategoryService {

	@Override
	public Item findById(String id) {
		if (categories == null) {
			updateCategoryCache();
		}
		for (Item item : categories) {
			if (item.getItemId().equals(id)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public Item findByPermalink(String permalink) {
		if (categories == null) {
			updateCategoryCache();
		}
		for (Item item : categories) {
			if (item.getPermalink().equals(permalink)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public List<Item> getCategories() {
		if (categories == null) {
			updateCategoryCache();
		}
		return categories;
	}

	@Override
	public List<Item> findByArticle(Article article) {
		List<Item> cats = new ArrayList<Item>();
		Item cat;
		for (Relationship relationship : relationshipDao.findByArticleId(article.getArticleId())) {
			cat = findById(relationship.getItemId());
			if (null != cat) {
				cats.add(cat);
			}
		}
		return cats;
	}

	@Override
	public void saveRelationshipWithArticle(Article article, List<Item> relatedItems) {
		List<Relationship> relationships = relationshipDao.findByArticleId(article.getArticleId());
		List<Item> old = itemDao.getItemsByRelationship(article.getArticleId());
		List<Long> relatedIds = new ArrayList<Long>();
		for (Item item : old) {
			if (!relatedItems.contains(item)) {
				item.setCount(item.getCount() - 1);
				itemDao.update(item);
			}
		}
		for (Item item : relatedItems) {
			if (!old.contains(item)) {
				item.setCount(item.getCount() + 1);
				itemDao.update(item);
			}
			relatedIds.add(item.getItemId());
		}
		relationshipDao.saveOrUpdate(article, relatedIds);
		updateCategoryCache();
		tags = itemDao.getItems(Item.TYPE_TAG, "count DESC");
	}

	@Override
	public long save(Item category) throws SQLException {
		long rs = itemDao.save(category);
		if (rs < 1) {
			throw new SQLException("Category save error");
		} else {
			updateCategoryCache();
		}
		return rs;
	}

	@Override
	public boolean update(Item category, boolean updateCache) throws SQLException {
		int rs = itemDao.update(category);
		if (rs < 1) {
			throw new SQLException("Category update error");
		} else {
			if (updateCache) {
				updateCategoryCache();
			}
		}
		return rs > 0;
	}

	@Override
	public boolean remove(Item category) throws SQLException {
		int rs = itemDao.remove(category);
		if (rs < 1) {
			throw new SQLException("Category remove error");
		} else {
			updateCategoryCache();
		}
		return rs > 0;
	}

	@Override
	public boolean remove(List<Long> ids) throws SQLException {
		int rs = itemDao.remove(ids);
		if (rs < 1) {
			throw new SQLException("Category remove error");
		} else {
			updateCategoryCache();
		}
		return rs > 0;
	}

	@Override
	public void updateCategoryCache() {
		categories = itemDao.getItems(Item.TYPE_CATEGORY, "item_order");

		// if(!categories.isEmpty()) {
		// Collections.sort(categories, new Comparator<Item>() {
		//
		// public int compare(Item i1, Item i2) {
		// return new Integer(i1.getItemOrder()).compareTo(new
		// Integer(i2.getItemOrder()));
		// }
		// });
		// }
	}

}
