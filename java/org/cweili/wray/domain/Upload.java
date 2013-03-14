package org.cweili.wray.domain;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Upload
 * 
 * @author Cweili
 * @version 2013-3-14 上午10:00:54
 * 
 */
@Document(collection = "upload")
public class Upload {

	@Id
	private String uploadId = "";
	private String uploadName = "";
	private String uploadExt = "";
	private int uploadSize = 0;
	private byte[] uploadFile = null;

	@Transient
	public static final HashMap<String, String> TYPE = new HashMap<String, String>();

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
		TYPE.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
		TYPE.put("prop", "text/plain");
		TYPE.put("qt", "video/quicktime");
		TYPE.put("rar", "application/x-rar-compressed");
		TYPE.put("rc", "text/plain");
		TYPE.put("rm", "audio/x-pn-realaudio");
		TYPE.put("rmvb", "audio/x-pn-realaudio");
		TYPE.put("rpm", "application/octet-stream");
		TYPE.put("rtf", "application/rtf");
		TYPE.put("sh", "text/plain");
		TYPE.put("swf", "application/x-shockwave-flash");
		TYPE.put("tar", "application/x-tar");
		TYPE.put("tgz", "application/x-compressed");
		TYPE.put("txt", "text/plain");
		TYPE.put("wav", "audio/x-wav");
		TYPE.put("wma", "audio/x-ms-wma");
		TYPE.put("wmv", "audio/x-ms-wmv");
		TYPE.put("wps", "application/vnd.ms-works");
		TYPE.put("xml", "text/xml");
		TYPE.put("z", "application/x-compress");
		TYPE.put("zip", "application/x-zip-compressed");
	}

	public Upload() {
		super();
	}

	@PersistenceConstructor
	public Upload(String uploadId, String uploadName, String uploadExt, int uploadSize, byte[] uploadFile) {
		super();
		this.uploadId = uploadId;
		this.uploadName = uploadName;
		this.uploadExt = uploadExt;
		this.uploadSize = uploadSize;
		this.uploadFile = uploadFile;
	}

	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	public String getUploadName() {
		return uploadName;
	}

	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}

	public String getUploadExt() {
		return uploadExt;
	}

	public void setUploadExt(String uploadExt) {
		this.uploadExt = uploadExt;
	}

	public int getUploadSize() {
		return uploadSize;
	}

	public void setUploadSize(int uploadSize) {
		this.uploadSize = uploadSize;
	}

	public byte[] getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(byte[] uploadFile) {
		this.uploadFile = uploadFile;
	}

}
