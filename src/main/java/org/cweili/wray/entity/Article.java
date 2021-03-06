package org.cweili.wray.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 文章
 * 
 * @author Cweili
 * @version 2012-8-16 下午5:10:14
 * 
 */
@Document(collection = "article")
@CompoundIndexes({
		@CompoundIndex(name = "index_stat_isPage", def = "{'stat': 1, 'isPage': 1}"),
		@CompoundIndex(name = "index_permalink_isPage", def = "{'permalink': 1, 'isPage': 1}", unique = true) })
public class Article implements Serializable, Cloneable, Comparable<Article> {

	private static final long serialVersionUID = 5085621145317473821L;

	/**
	 * 文章ID
	 */
	@Id
	private String articleId;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 永久链接
	 */
	private String permalink;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 标签
	 */
	private String tag;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 状态
	 */
	private byte stat;

	/**
	 * 点击数
	 */
	private int hit;

	/**
	 * 评论数
	 */
	private int commentCount;

	/**
	 * 评论开关
	 */
	private byte commentStatus;

	/**
	 * 是否为页面
	 */
	private byte isPage;

	/**
	 * 关键词集合
	 */
	@Indexed
	private Set<String> keyword;

	// 状态-已发布
	public static final byte STAT_PUBLISHED = 4;
	// 状态-私密
	public static final byte STAT_PRIVATE = 3;
	// 状态-草稿
	public static final byte STAT_DRAFT = 2;
	// 状态-回收站
	public static final byte STAT_RECYCLE = 1;
	// 状态-已删除
	public static final byte STAT_REMOVED = 0;

	// 评论-允许
	public static final byte COMMENT_ON = 1;
	// 评论-禁止
	public static final byte COMMENT_OFF = 0;

	// 类型-文章
	public static final byte TYPE_ARTICLE = 0;
	// 类型-页面
	public static final byte TYPE_PAGE = 1;

	public Article() {
		this("", "", "", "", "", new Date(), STAT_PUBLISHED, 0, 0, COMMENT_ON, TYPE_ARTICLE);
	}

	/**
	 * @param articleId
	 * @param title
	 * @param permalink
	 * @param content
	 * @param tag
	 * @param createTime
	 * @param stat
	 * @param hit
	 * @param commentCount
	 * @param commentStatus
	 * @param isPage
	 */
	public Article(String articleId, String title, String permalink, String content, String tag,
			Date createTime, byte stat, int hit, int commentCount, byte commentStatus, byte isPage) {
		this.articleId = articleId;
		this.title = title;
		this.permalink = permalink;
		this.content = content;
		this.tag = tag;
		this.createTime = createTime;
		this.stat = stat;
		this.hit = hit;
		this.commentCount = commentCount;
		this.commentStatus = commentStatus;
		this.isPage = isPage;

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
		Article other = (Article) obj;
		if (articleId == null) {
			if (other.articleId != null)
				return false;
		} else if (!articleId.equals(other.articleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", title=" + title + ", permalink=" + permalink
				+ ", tag=" + tag + ", createTime="
				+ new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(createTime) + ", stat=" + stat
				+ ", isPage=" + isPage + "]";
	}

	@Override
	public int compareTo(Article article) {
		if (this.getCreateTime().after(article.getCreateTime())) {
			return -1;
		} else if (this.getCreateTime().before(article.getCreateTime())) {
			return 1;
		}
		return 0;
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
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            要设置的 title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return permalink
	 */
	public String getPermalink() {
		return permalink;
	}

	/**
	 * @param permalink
	 *            要设置的 permalink
	 */
	public void setPermalink(String permalink) {
		this.permalink = permalink;
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
	 * @return tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag
	 *            要设置的 tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            要设置的 createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	 * @return hit
	 */
	public int getHit() {
		return hit;
	}

	/**
	 * @param hit
	 *            要设置的 hit
	 */
	public void setHit(int hit) {
		this.hit = hit;
	}

	/**
	 * @return commentCount
	 */
	public int getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount
	 *            要设置的 commentCount
	 */
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * @return commentStatus
	 */
	public byte getCommentStatus() {
		return commentStatus;
	}

	/**
	 * @param commentStatus
	 *            要设置的 commentStatus
	 */
	public void setCommentStatus(byte commentStatus) {
		this.commentStatus = commentStatus;
	}

	/**
	 * @return isPage
	 */
	public byte getIsPage() {
		return isPage;
	}

	/**
	 * @param isPage
	 *            要设置的 isPage
	 */
	public void setIsPage(byte isPage) {
		this.isPage = isPage;
	}

	/**
	 * @return keyword
	 */
	public Set<String> getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            要设置的 keyword
	 */
	public void setKeyword(Set<String> keyword) {
		this.keyword = keyword;
	}

}
