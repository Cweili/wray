package org.cweili.wray.domain.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

/**
 * 附件
 * 
 * @author Cweili
 * @version 2013-3-14 上午10:00:54
 * 
 */
public class Upload implements Serializable, Cloneable, Comparable<Upload> {

	private static final long serialVersionUID = -4128532409330876706L;

	/**
	 * 附件ID
	 */
	@Id
	private String uploadId;

	/**
	 * 长度
	 */
	private int length;

	/**
	 * MD5
	 */
	private String md5;

	/**
	 * 文件名
	 */
	private String filename;

	/**
	 * 文件类型
	 */
	private String contentType;

	/**
	 * 上传日期
	 */
	private Date uploadDate;

	@Transient
	private byte[] content;

	public static final Map<String, String> TYPE = new HashMap<String, String>(79);
	public static final Set<String> COMPRESSED = new HashSet<String>(27);

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
		this("", "", "", new byte[0]);
	}

	public Upload(String uploadId, int length, String md5, String filename, String contentType,
			Date uploadDate) {
		this(uploadId, length, md5, filename, contentType, uploadDate, new byte[0]);
	}

	public Upload(String uploadId, String filename, String contentType, byte[] content) {
		this(uploadId, 0, "", filename, contentType, new Date(), content);
	}

	public Upload(String uploadId, int length, String md5, String filename, String contentType,
			Date uploadDate, byte[] content) {
		this.uploadId = uploadId;
		this.length = length;
		this.md5 = md5;
		this.filename = filename;
		this.contentType = contentType;
		this.uploadDate = uploadDate;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Upload [uploadId=" + uploadId + ", length=" + length + ", filename=" + filename
				+ ", uploadDate=" + uploadDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uploadId == null) ? 0 : uploadId.hashCode());
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
		if (uploadId == null) {
			if (other.uploadId != null)
				return false;
		} else if (!uploadId.equals(other.uploadId))
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

	/**
	 * @return uploadId
	 */
	public String getUploadId() {
		return uploadId;
	}

	/**
	 * @param uploadId
	 *            要设置的 uploadId
	 */
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	/**
	 * @return length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 *            要设置的 length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * @param md5
	 *            要设置的 md5
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}

	/**
	 * @return filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 *            要设置的 filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            要设置的 contentType
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return uploadDate
	 */
	public Date getUploadDate() {
		return uploadDate;
	}

	/**
	 * @param uploadDate
	 *            要设置的 uploadDate
	 */
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
	 * @return content
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * @param content
	 *            要设置的 content
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

}
