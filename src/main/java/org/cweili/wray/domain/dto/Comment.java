package org.cweili.wray.domain.dto;

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

	private static final long serialVersionUID = -767060507781375509L;
	@Id
	private String commentId;
	@Indexed
	private String articleId;
	private String author;
	private String email;
	private String link;
	private String ip;
	private Date postDate;
	private String agent;
	private String content;
	private String parentId;
	private byte stat;

	@Transient
	private String artilceTitle = "";
	@Transient
	private String origin = "";

	public static final byte STAT_REMOVED = 0;
	public static final byte STAT_BLOCK = 1;
	public static final byte STAT_DISPLAY = 2;

	public Comment() {
		this("", "", "", "", "", "", new Date(), "", "", "", STAT_DISPLAY);
	}

	public <S extends Comment> Comment(S comment) {
		this.commentId = comment.getCommentId();
		this.articleId = comment.getArticleId();
		this.author = comment.getAuthor();
		this.email = comment.getEmail();
		this.link = comment.getLink();
		this.ip = comment.getLink();
		this.postDate = comment.getPostDate();
		this.agent = comment.getAgent();
		this.content = comment.getContent();
		this.parentId = comment.getParentId();
		this.stat = comment.getStat();
		this.artilceTitle = "";
		this.origin = "";
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
	 * @param parentId
	 * @param stat
	 */
	@PersistenceConstructor
	public Comment(String commentId, String articleId, String author, String email, String link,
			String ip, Date postDate, String agent, String content, String parentId, byte stat) {

		this.commentId = commentId;
		this.articleId = articleId;
		this.author = author;
		this.email = email;
		this.link = link;
		this.ip = ip;
		this.postDate = postDate;
		this.agent = agent;
		this.content = content;
		this.parentId = parentId;
		this.stat = stat;
		this.artilceTitle = "";
		this.origin = "";
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public byte getStat() {
		return stat;
	}

	public void setStat(byte stat) {
		this.stat = stat;
	}

	public String getArtilceTitle() {
		return artilceTitle;
	}

	public void setArtilceTitle(String artilceTitle) {
		this.artilceTitle = artilceTitle;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}