package org.cweili.wray.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Upload
 * 
 * @author Cweili
 * @version 2013-3-14 上午10:00:54
 * 
 */
public class Upload implements Serializable, Cloneable, Comparable<Upload> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4128532409330876706L;
	private String id = "";
	private int length = 0;
	private String md5 = "";
	private String filename = "";
	private String contentType = "";
	private Date uploadDate = null;

	private byte[] content = null;

	public static final Map<String, String> TYPE = new HashMap<String, String>();
	public static final Set<String> COMPRESSED = new HashSet<String>();

	static {
		TYPE.put("", "text/html");
		TYPE.put("3gp", "video/3gpp");
		TYPE.put("7z", "application/x-compress");
		TYPE.put("apk", "application/vnd.Android.package-archive");
		TYPE.put("asf", "video/x-ms-asf");
		TYPE.put("avi", "video/x-msvideo");
		TYPE.put("bin", "application/octet-stream");
		TYPE.put("bmp", "image/bmp");
		TYPE.put("bz2", "application/x-bzip2");
		TYPE.put("c", "text/plain");
		TYPE.put("conf", "text/plain");
		TYPE.put("cpp", "text/plain");
		TYPE.put("deb", "application/octet-stream");
		TYPE.put("doc", "application/msword");
		TYPE.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		TYPE.put("dwg", "application/x-autocad");
		TYPE.put("dxf", "application/x-autocad");
		TYPE.put("xls", "application/vnd.ms-excel");
		TYPE.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		TYPE.put("exe", "application/octet-stream");
		TYPE.put("gif", "image/gif");
		TYPE.put("gtar", "application/x-gtar");
		TYPE.put("gz", "application/x-gzip");
		TYPE.put("gzip", "application/x-gzip");
		TYPE.put("h", "text/plain");
		TYPE.put("htm", "text/html");
		TYPE.put("html", "text/html");
		TYPE.put("ico", "application/octet-stream");
		TYPE.put("jar", "application/java-archive");
		TYPE.put("java", "text/plain");
		TYPE.put("jpeg", "image/jpeg");
		TYPE.put("jpg", "image/jpeg");
		TYPE.put("js", "application/x-javascript");
		TYPE.put("log", "text/plain");
		TYPE.put("m3u", "audio/x-mpegurl");
		TYPE.put("m4a", "audio/mp4a-latm");
		TYPE.put("m4b", "audio/mp4a-latm");
		TYPE.put("m4p", "audio/mp4a-latm");
		TYPE.put("m4u", "video/vnd.mpegurl");
		TYPE.put("m4v", "video/x-m4v");
		TYPE.put("mid", "audio/midi");
		TYPE.put("midi", "audio/midi");
		TYPE.put("mov", "video/quicktime");
		TYPE.put("mp2", "audio/x-mpeg");
		TYPE.put("mp3", "audio/x-mpeg");
		TYPE.put("mp4", "video/mp4");
		TYPE.put("mpc", "application/vnd.mpohun.certificate");
		TYPE.put("mpe", "video/mpeg");
		TYPE.put("mpeg", "video/mpeg");
		TYPE.put("mpg", "video/mpeg");
		TYPE.put("mpg4", "video/mp4");
		TYPE.put("mpga", "audio/mpeg");
		TYPE.put("msg", "application/vnd.ms-outlook");
		TYPE.put("ogg", "audio/ogg");
		TYPE.put("pdf", "application/pdf");
		TYPE.put("png", "image/png");
		TYPE.put("pps", "application/vnd.ms-powerpoint");
		TYPE.put("ppt", "application/vnd.ms-powerpoint");
		TYPE.put("pptx",
				"application/vnd.openxmlformats-officedocument.presentationml.presentation");
		TYPE.put("prop", "text/plain");
		TYPE.put("qt", "video/quicktime");
		TYPE.put("rar", "application/x-rar-compressed");
		TYPE.put("rc", "text/plain");
		TYPE.put("rm", "audio/x-pn-realaudio");
		TYPE.put("rmvb", "audio/x-pn-realaudio");
		TYPE.put("rpm", "application/octet-stream");
		TYPE.put("rtf", "application/rtf");
		TYPE.put("sh", "application/octet-stream");
		TYPE.put("swf", "application/x-shockwave-flash");
		TYPE.put("tar", "application/x-tar");
		TYPE.put("tgz", "application/x-gzip");
		TYPE.put("txt", "text/plain");
		TYPE.put("wav", "audio/x-wav");
		TYPE.put("wma", "audio/x-ms-wma");
		TYPE.put("wmv", "audio/x-ms-wmv");
		TYPE.put("wps", "application/vnd.ms-works");
		TYPE.put("xml", "text/xml");
		TYPE.put("z", "application/x-compress");
		TYPE.put("zip", "application/x-zip-compressed");

		COMPRESSED.add("video/3gpp");
		COMPRESSED.add("application/x-compress");
		COMPRESSED.add("application/vnd.Android.package-archive");
		COMPRESSED.add("video/x-ms-asf");
		COMPRESSED.add("video/x-msvideo");
		COMPRESSED.add("application/x-bzip2");
		COMPRESSED.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		COMPRESSED.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		COMPRESSED.add("application/vnd.openxmlformats-officedocument.presentationml.presentation");
		COMPRESSED.add("application/x-gtar");
		COMPRESSED.add("application/x-gzip");
		COMPRESSED.add("application/java-archive");
		COMPRESSED.add("audio/mp4a-latm");
		COMPRESSED.add("video/x-m4v");
		COMPRESSED.add("video/quicktime");
		COMPRESSED.add("audio/x-mpeg");
		COMPRESSED.add("video/mp4");
		COMPRESSED.add("video/mpeg");
		COMPRESSED.add("audio/mpeg");
		COMPRESSED.add("audio/ogg");
		COMPRESSED.add("application/x-rar-compressed");
		COMPRESSED.add("audio/x-pn-realaudio");
		COMPRESSED.add("application/x-tar");
		COMPRESSED.add("audio/x-ms-wma");
		COMPRESSED.add("audio/x-ms-wmv");
		COMPRESSED.add("application/x-compress");
		COMPRESSED.add("application/x-zip-compressed");
	}

	public Upload() {
		super();
	}

	public Upload(String id, int length, String md5, String filename, String contentType,
			Date uploadDate) {
		super();
		this.id = id;
		this.length = length;
		this.md5 = md5;
		this.filename = filename;
		this.contentType = contentType;
		this.uploadDate = uploadDate;
	}

	public Upload(String id, String filename, String contentType, byte[] content) {
		super();
		this.id = id;
		this.filename = filename;
		this.contentType = contentType;
		this.content = content;
	}

	public Upload(String id, int length, String md5, String filename, String contentType,
			Date uploadDate, byte[] content) {
		super();
		this.id = id;
		this.length = length;
		this.md5 = md5;
		this.filename = filename;
		this.contentType = contentType;
		this.uploadDate = uploadDate;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Upload [id=" + id + ", length=" + length + ", md5=" + md5 + ", filename="
				+ filename + ", uploadDate=" + uploadDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + length;
		result = prime * result + ((md5 == null) ? 0 : md5.hashCode());
		result = prime * result + ((uploadDate == null) ? 0 : uploadDate.hashCode());
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
		Upload other = (Upload) obj;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (length != other.length)
			return false;
		if (md5 == null) {
			if (other.md5 != null)
				return false;
		} else if (!md5.equals(other.md5))
			return false;
		if (uploadDate == null) {
			if (other.uploadDate != null)
				return false;
		} else if (!uploadDate.equals(other.uploadDate))
			return false;
		return true;
	}

	@Override
	public int compareTo(Upload o) {
		if (this.uploadDate.after(o.getUploadDate())) {
			return 1;
		} else if (this.uploadDate.before(o.getUploadDate())) {
			return -1;
		}
		return 0;
	}

}
