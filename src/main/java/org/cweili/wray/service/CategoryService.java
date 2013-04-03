package org.cweili.wray.service;

import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;

/**
 * 
 * @author cweili
 * @version 2013-4-3 下午2:04:55
 * 
 */
public interface CategoryService {

	public Item findById(String itemId);

	public Item findByPermalink(String permalink);

	public List<Item> getCategories();

	public List<Item> findByArticle(Article article);

	public void saveRelationshipWithArticle(Article article, List<Item> relatedItems);

	public Item save(Item category, boolean updateCache);

	public boolean remove(Item category);

	public boolean remove(List<String> ids);

	public void updateCategoryCache();
}
