package org.cweili.wray.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
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

	private static final long serialVersionUID = -767060507781375509L;
	@Id
	private String commentId = "";
	@Indexed
	private String articleId = "";
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
	 * @param commentId
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
	public Comment(String commentId, String articleId, String author, String email, String link,
			String ip, Date postDate, String agent, String content, long parrentId, byte stat) {

		this.commentId = commentId;
		this.articleId = articleId;
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
		result = prime * result + ((commentId == null) ? 0 : commentId.hashCode());
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
		if (commentId == null) {
			if (other.commentId != null)
				return false;
		} else if (!commentId.equals(other.commentId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", author=" + author + ", email=" + email
				+ ", ip=" + ip + ", content=" + content + "]";
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

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
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
