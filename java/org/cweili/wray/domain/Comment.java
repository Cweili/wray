package org.cweili.wray.domain;

import java.util.Date;

/**
 * 
 * @author cweili
 * @version 2012-8-21 下午2:18:50
 * 
 */
public class Comment {

	private long commentId = 0;
	private String author = "";
	private String email = "";
	private String link = "";
	private String ip = "";
	private Date postTime = new Date();
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
	 * @param postTime
	 * @param agent
	 * @param content
	 * @param parrentId
	 * @param stat
	 */
	public Comment(long commentId, String author, String email, String link, String ip,
			Date postTime, String agent, String content, long parrentId, byte stat) {

		this.commentId = commentId;
		this.author = author;
		this.email = email;
		this.link = link;
		this.ip = ip;
		this.postTime = postTime;
		this.agent = agent;
		this.content = content;
		this.parrentId = parrentId;
		this.stat = stat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (commentId ^ (commentId >>> 32));
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
		if (commentId != other.commentId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", author=" + author + ", email=" + email
				+ ", link=" + link + ", ip=" + ip + ", agent=" + agent + ", content=" + content
				+ "]";
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
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

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
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
