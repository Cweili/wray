package org.cweili.wray.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Article Model
 * 
 * @author cweili
 * @version 2012-8-16 下午5:10:14
 * 
 */
@Document(collection = "articlecontent")
public class ArticleContent implements Serializable, Cloneable, Comparable<ArticleContent> {

	private static final long serialVersionUID = -6805966590592475180L;
	@Id
	private String articleId;
	private String content;

	public ArticleContent() {
		this("", "");
	}

	public ArticleContent(String articleId, String content) {
		this.articleId = articleId;
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articleId == null) ? 0 : articleId.hashCode());
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
		ArticleContent other = (ArticleContent) obj;
		if (articleId == null) {
			if (other.articleId != null)
				return false;
		} else if (!articleId.equals(other.articleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticleConetent [articleId=" + articleId + "]";
	}

	@Override
	public int compareTo(ArticleContent article) {
		return this.getArticleId().compareTo(article.getArticleId());
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
