package org.cweili.wray.service;

import java.sql.SQLException;
import java.util.List;

import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:16:29
 * 
 */
public interface CategoryService {

	/**
	 * @param id
	 * @return
	 */
	public Item getCategoryById(long id);

	/**
	 * @param permalink
	 * @return
	 */
	public Item getCategoryByPermalink(String permalink);

	/**
	 * @return
	 */
	public List<Item> getCategories();

	/**
	 * @param article
	 * @return
	 */
	public List<Long> getRelatedIdsByArticle(Article article);

	/**
	 * @param article
	 * @return
	 */
	public List<Item> getCategoriesByArticle(Article article);

	/**
	 * @param article
	 * @param relatedItems
	 */
	public void saveRelationshipWithArticle(Article article, List<Item> relatedItems);

	/**
	 * @param category
	 * @return
	 * @throws SQLException
	 */
	public long save(Item category) throws SQLException;

	/**
	 * @param category
	 * @return
	 * @throws SQLException
	 */
	public boolean update(Item category, boolean updateCache) throws SQLException;

	/**
	 * @param category
	 * @return
	 * @throws SQLException
	 */
	public boolean remove(Item category) throws SQLException;

	/**
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public boolean remove(List<Long> ids) throws SQLException;

	/**
	 * 
	 */
	public void updateCategoryCache();
}