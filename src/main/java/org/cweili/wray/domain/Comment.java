package org.cweili.wray.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author cweili
 * @version 2012-8-21 下午2:18:50
 * 
 */
@Document(collection = "comment")
public class Comment implements Serializable, Cloneable, Comparable<Comment> {

	@Transient
	private static final long serialVersionUID = -767060507781375509L;
	@Id
	private String id = "";
	@Indexed
	private long articleId = 0;
	private String author = "";
	private String email = "";
	private String link = "";
	private String ip = "";
	private Date postDate = new Date();
	private String agent = "";
	private String content = "";
	private long parrentId = 0;
	private byte stat = STAT_DISPLAY;

	public static final byte STAT_REMOVED = 0;
	public static final byte STAT_BLOCK = 1;
	public static final byte STAT_DISPLAY = 2;

	public Comment() {
	}

	/**
	 * @param id
	 * @param author
	 * @param email
	 * @param link
	 * @param ip
	 * @param postDate
	 * @param agent
	 * @param content
	 * @param parrentId
	 * @param stat
	 */
	@PersistenceConstructor
	public Comment(String id, long articleId, String author, String email, String link, String ip,
			Date postDate, String agent, String content, long parrentId, byte stat) {

		this.id = id;
		this.setArticleId(articleId);
		this.author = author;
		this.email = email;
		this.link = link;
		this.ip = ip;
		this.postDate = postDate;
		this.agent = agent;
		this.content = content;
		this.parrentId = parrentId;
		this.stat = stat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agent == null) ? 0 : agent.hashCode());
		result = prime * result + (int) (articleId ^ (articleId >>> 32));
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + (int) (parrentId ^ (parrentId >>> 32));
		result = prime * result + ((postDate == null) ? 0 : postDate.hashCode());
		result = prime * result + stat;
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
		Comment other = (Comment) obj;
		if (agent == null) {
			if (other.agent != null)
				return false;
		} else if (!agent.equals(other.agent))
			return false;
		if (articleId != other.articleId)
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (parrentId != other.parrentId)
			return false;
		if (postDate == null) {
			if (other.postDate != null)
				return false;
		} else if (!postDate.equals(other.postDate))
			return false;
		if (stat != other.stat)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", author=" + author + ", email=" + email + ", link=" + link
				+ ", ip=" + ip + ", agent=" + agent + ", content=" + content + "]";
	}

	@Override
	public int compareTo(Comment comment) {
		if (this.getPostDate().after(comment.getPostDate())) {
			return 1;
		} else if (this.getPostDate().before(comment.getPostDate())) {
			return -1;
		}
		return 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getParrentId() {
		return parrentId;
	}

	public void setParrentId(long parrentId) {
		this.parrentId = parrentId;
	}

	public byte getStat() {
		return stat;
	}

	public void setStat(byte stat) {
		this.stat = stat;
	}

}