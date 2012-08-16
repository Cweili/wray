package org.cweili.wray.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Article Model
 * 
 * @author cweili
 * @version 2012-8-16 下午5:10:14
 *
 */
public class Article {

	private long articleId = 0;
	private String title = "";
	private String permalink = "";
	private String content = "";
	private String tag = "";
	private Date createTime = new Date();
	private byte stat = Article.STAT_PUBLISHED;
	private int hits = 0;
	private int commentCount = 0;
	private byte commentStatus = Article.COMMENT_ON;
	private byte isPage = Article.TYPE_ARTICLE;
	
	public static final byte STAT_PUBLISHED = 4;
	public static final byte STAT_DRAFT = 2;
	public static final byte STAT_RECYCLE = 1;
	public static final byte STAT_REMOVED = 0;
	
	public static final byte COMMENT_ON = 1;
	public static final byte COMMENT_OFF = 0;
	
	public static final byte TYPE_ARTICLE = 0;
	public static final byte TYPE_PAGE = 1;
	
	public Article(){
		super();
	}
	
	/**
	 * @param articleId
	 * @param title
	 * @param permalink
	 * @param content
	 * @param tag
	 * @param createTime
	 * @param stat
	 * @param hits
	 * @param commentCount
	 * @param commentStatus
	 * @param isPage
	 */
	public Article(long articleId,
			String title,
			String permalink,
			String content,
			String tag,
			Date createTime,
			byte stat,
			int hits,
			int commentCount,
			byte commentStatus,
			byte isPage) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.permalink = permalink;
		this.content = content;
		this.tag = tag;
		this.createTime = createTime;
		this.stat = stat;
		this.hits = hits;
		this.commentCount = commentCount;
		this.commentStatus = commentStatus;
		this.isPage = isPage;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (articleId ^ (articleId >>> 32));
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
		Article other = (Article) obj;
		if (articleId != other.articleId)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", title=" + title + ", permalink=" + permalink
				+ ", tag=" + tag + ", createTime=" + new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(createTime) + ", stat=" + stat
				+ ", isPage=" + isPage + "]";
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public byte getStat() {
		return stat;
	}

	public void setStat(byte stat) {
		this.stat = stat;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public byte getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(byte commentStatus) {
		this.commentStatus = commentStatus;
	}

	public byte getIsPage() {
		return isPage;
	}

	public void setIsPage(byte isPage) {
		this.isPage = isPage;
	}


}
