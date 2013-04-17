/**
 * 
 */
package org.cweili.wray.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author cweili
 * @version 2013-3-28 下午4:29:04
 * 
 */
@Document(collection = "relationship")
@CompoundIndex(def = "{'articleId': 1, 'itemId': 1}", unique = true)
public class Relationship implements Serializable, Cloneable {

	private static final long serialVersionUID = -198625132517703178L;
	@Id
	private String id;
	@Indexed
	private String articleId;
	@Indexed
	private String itemId;

	public Relationship() {
		id = "";
		articleId = "";
		itemId = "";
	}

	public Relationship(String articleId, String itemId) {
		this.id = "";
		this.articleId = articleId;
		this.itemId = itemId;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}
