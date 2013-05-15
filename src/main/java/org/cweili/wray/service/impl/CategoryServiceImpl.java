package org.cweili.wray.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.cweili.wray.domain.dto.Article;
import org.cweili.wray.domain.dto.Item;
import org.cweili.wray.domain.dto.Relationship;
import org.cweili.wray.service.CategoryService;
import org.cweili.wray.util.Function;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cweili
 * @version 2013-4-3 下午3:40:51
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
	public List<Item> getAllCategories() {
		Order[] orders = new Order[] { new Order(Direction.DESC, "stat"),
				new Order(Direction.ASC, "itemOrder") };
		return itemDao.findByItemType(Item.TYPE_CATEGORY, new Sort(orders));
	}

	@Override
	public List<Item> findByArticle(Article article) {
		List<Item> cats = new ArrayList<Item>();
		if (null != article) {
			Item cat;
			for (Relationship relationship : relationshipDao
					.findByArticleId(article.getArticleId())) {
				cat = findById(relationship.getItemId());
				if (null != cat) {
					cats.add(cat);
				}
			}
		}
		return cats;
	}

	@Override
	public void saveRelationshipWithArticle(Article article, List<Item> relatedItems) {
		List<Relationship> relationships = relationshipDao.findByArticleId(article.getArticleId());
		List<Item> old = new ArrayList<Item>();
		Item itemOld;
		for (Relationship relationship : relationships) {
			itemOld = itemDao.findOne(relationship.getItemId());
			if (null != itemOld) {
				old.add(itemOld);
			}
		}
		for (Item item : old) {
			if (!relatedItems.contains(item)) {
				item.setCount(item.getCount() - 1);
				itemDao.save(item);
				relationshipDao.delete(relationshipDao.findByArticleIdAndItemId(
						article.getArticleId(), item.getItemId()));
			}
		}
		for (Item item : relatedItems) {
			if (!old.contains(item)) {
				item.setCount(item.getCount() + 1);
				itemDao.save(item);
				relationshipDao.save(new Relationship(Function.generateId(),
						article.getArticleId(), item.getItemId()));
			}
		}
		updateCategoryCache();
		tags = itemDao.findByItemTypeAndStat(Item.TYPE_TAG, Item.STAT_ON, new Sort(
				Sort.Direction.DESC, "count"));
	}

	@Override
	public Item save(Item item) {
		if ("".equals(item.getItemId())) {
			item.setItemId(Function.generateId());
		}
		Item itemNew = itemDao.save(item);
		if (null != itemNew) {
			updateCategoryCache();
		}
		return itemNew;
	}

	@Override
	public boolean switchStat(Item item) {
		item = itemDao.findOne(item.getItemId());
		if (null != item) {
			item.setStat(item.getStat() == Item.STAT_ON ? Item.STAT_OFF : Item.STAT_ON);
			Item itemNew = itemDao.save(item);
			if (null != itemNew) {
				updateCategoryCache();
			}
			return null != itemNew;
		}
		return false;
	}

	@Override
	public boolean switchStat(List<String> ids) {
		Iterable<Item> items = itemDao.findAll(ids);
		for (Item item : items) {
			item.setStat(item.getStat() == Item.STAT_ON ? Item.STAT_OFF : Item.STAT_ON);
		}
		items = itemDao.save(items);
		if (null != items) {
			updateCategoryCache();
		}
		return null != items;
	}

	@Override
	public List<Item> getSelectedCategories(Article article) {
		List<Item> selectedCategories = new ArrayList<Item>();
		List<Item> relatedCategories = findByArticle(article);
		List<Item> categories = getCategories();
		for (Item category : categories) {
			Item selectedCategory = new Item(category);
			if (relatedCategories.contains(category)) {
				selectedCategory.setStat(Item.STAT_SELECTED);
			}
			selectedCategories.add(selectedCategory);
		}
		return selectedCategories;
	}

	@Override
	public void updateCategoryCache() {
		categories = itemDao.findByItemTypeAndStat(Item.TYPE_CATEGORY, Item.STAT_ON, new Sort(
				Sort.Direction.ASC, "itemOrder"));
	}

}
