/**
 * 
 */
package org.cweili.wray.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 关系
 * 
 * @author Cweili
 * @version 2013-3-28 下午4:29:04
 * 
 */
@Document(collection = "relationship")
@CompoundIndexes({ @CompoundIndex(name = "index_article_item", def = "{'articleId': 1, 'itemId': 1}", unique = true) })
public class Relationship implements Serializable, Cloneable {

	private static final long serialVersionUID = -198625132517703178L;

	/**
	 * 唯一ID
	 */
	@Id
	private String id;

	/**
	 * 文章ID
	 */
	@Indexed
	private String articleId;

	/**
	 * 项目ID
	 */
	@Indexed
	private String itemId;

	public Relationship() {
		this("", "");
	}

	public Relationship(String articleId, String itemId) {
		this("", articleId, itemId);
	}

	public Relationship(String id, String articleId, String itemId) {
		this.id = id;
		this.articleId = articleId;
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "Relationship: " + articleId + " => " + itemId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articleId == null) ? 0 : articleId.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relationship other = (Relationship) obj;
		if (articleId == null) {
			if (other.articleId != null)
				return false;
		} else if (!articleId.equals(other.articleId))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		return true;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return articleId
	 */
	public String getArticleId() {
		return articleId;
	}

	/**
	 * @param articleId
	 *            要设置的 articleId
	 */
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	/**
	 * @return itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            要设置的 itemId
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}
