package org.cweili.wray.domain.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 评论
 * 
 * @author Cweili
 * @version 2012-8-21 下午2:18:50
 * 
 */
@Document(collection = "comment")
public class Comment implements Serializable, Cloneable, Comparable<Comment> {

	private static final long serialVersionUID = -767060507781375509L;

	/**
	 * 评论ID
	 */
	@Id
	private String commentId;

	/**
	 * 文章ID
	 */
	@Indexed
	private String articleId;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * E-mail
	 */
	private String email;

	/**
	 * 链接
	 */
	private String link;

	/**
	 * IP
	 */
	private String ip;

	/**
	 * 发表时间
	 */
	private Date postDate;

	/**
	 * User-Agent
	 */
	private String agent;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 父评论
	 */
	private String parentId;

	/**
	 * 状态
	 */
	private byte stat;

	@Transient
	private String artilceTitle = "";
	@Transient
	private String origin = "";

	// 状态-已删除
	public static final byte STAT_REMOVED = 0;
	// 状态-屏蔽
	public static final byte STAT_BLOCK = 1;
	// 状态-显示
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

	/**
	 * @return commentId
	 */
	public String getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId
	 *            要设置的 commentId
	 */
	public void setCommentId(String commentId) {
		this.commentId = commentId;
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
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            要设置的 author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            要设置的 email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link
	 *            要设置的 link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            要设置的 ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return postDate
	 */
	public Date getPostDate() {
		return postDate;
	}

	/**
	 * @param postDate
	 *            要设置的 postDate
	 */
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	/**
	 * @return agent
	 */
	public String getAgent() {
		return agent;
	}

	/**
	 * @param agent
	 *            要设置的 agent
	 */
	public void setAgent(String agent) {
		this.agent = agent;
	}

	/**
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            要设置的 content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            要设置的 parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return stat
	 */
	public byte getStat() {
		return stat;
	}

	/**
	 * @param stat
	 *            要设置的 stat
	 */
	public void setStat(byte stat) {
		this.stat = stat;
	}

	/**
	 * @return artilceTitle
	 */
	public String getArtilceTitle() {
		return artilceTitle;
	}

	/**
	 * @param artilceTitle
	 *            要设置的 artilceTitle
	 */
	public void setArtilceTitle(String artilceTitle) {
		this.artilceTitle = artilceTitle;
	}

	/**
	 * @return origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin
	 *            要设置的 origin
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
