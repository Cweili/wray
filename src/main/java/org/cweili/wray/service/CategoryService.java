package org.cweili.wray.service;

import java.util.List;

import org.cweili.wray.entity.Article;
import org.cweili.wray.entity.Item;

/**
 * 分类 Service
 * 
 * @author Cweili
 * @version 2013-4-3 下午2:04:55
 * 
 */
public interface CategoryService {

	public Item findById(String itemId);

	public Item findByPermalink(String permalink);

	public List<Item> getCategories();

	public List<Item> getAllCategories();

	public List<Item> findByArticle(Article article);

	public List<Item> getSelectedCategories(Article article);

	public void saveRelationshipWithArticle(Article article, List<Item> relatedItems);

	public Item save(Item category);

	public boolean switchStat(Item category);

	public boolean switchStat(List<String> ids);

	public void updateCategoryCache();
}
