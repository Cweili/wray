package org.cweili.wray.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User Model
 * 
 * @author cweili
 * @version 2012-8-16 下午5:12:51
 * 
 */
public class User {

	private long userId;
	private String username;
	private String passwd;
	private String nickname;
	private String permalink;
	private String email;
	private Date regtime;
	private byte usergroup;

	public static final byte GROUP_ADMIN = 9;
	public static final byte GROUP_EDITOR = 7;
	public static final byte GROUP_AUTHOR = 5;
	public static final byte GROUP_CONTRIBUTOR = 3;
	public static final byte GROUP_READER = 1;
	public static final byte GROUP_BLOCK = 0;

	public User() {
	}

	/**
	 * @param userId
	 * @param username
	 * @param passwd
	 * @param nickname
	 * @param permalink
	 * @param email
	 * @param regtime
	 * @param usergroup
	 */
	public User(long userId, String username, String passwd, String nickname, String permalink,
			String email, Date regtime, byte usergroup) {
		this.userId = userId;
		this.username = username;
		this.passwd = passwd;
		this.nickname = nickname;
		this.permalink = permalink;
		this.email = email;
		this.regtime = regtime;
		this.usergroup = usergroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		User other = (User) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", passwd=" + passwd
				+ ", nickname=" + nickname + ", permalink=" + permalink + ", email=" + email
				+ ", regtime=" + new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(regtime)
				+ ", usergroup=" + usergroup + "]";
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public byte getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(byte usergroup) {
		this.usergroup = usergroup;
	}

}