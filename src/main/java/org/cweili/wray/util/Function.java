package org.cweili.wray.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Wray 公用函数
 * 
 * @author cweili
 * @version 2012-8-16 下午5:27:40
 * 
 */
public class Function {

	private static final CharSequence CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public static String cleanUrl(String url) {
		return url.replace("%", "-");
	}

	public static long decodeId(String id) {
		long out = 0;
		for (int i = id.length() - 1; i >= 0; --i) {
			out *= CHARS.length();
			out += CHARS.toString().indexOf(id.charAt(i));
		}
		return out;
	}

	/**
	 * 取得路径下的文件夹列表
	 * 
	 * @param file
	 * @return
	 */
	public static List<String> dirList(File file) {
		File[] files = file.listFiles();
		List<String> list = new ArrayList<String>();
		for (File f : files) {
			if (f.isDirectory()) {
				int beginIndex = f.getPath().lastIndexOf('/');
				beginIndex = beginIndex > -1 ? beginIndex : f.getPath().lastIndexOf('\\');
				list.add(f.getPath().substring(beginIndex + 1));
			}
		}
		return list;
	}

	private static long id = 0;

	/**
	 * 创建实体唯一id
	 * 
	 * @return
	 */
	public synchronized static long generateId() {
		long time = timestamp();
		if (id < time) {
			id = time;
		} else {
			++id;
		}
		return id;
	}

	/**
	 * 取得当前页码
	 * 
	 * @param uri
	 * @return
	 */
	public static int page(String uri) {
		int page = 1;
		if (uri.contains("/page-")) {
			String p = uri.substring(uri.lastIndexOf("/page-") + 6, uri.lastIndexOf("/"));
			try {
				page = Integer.valueOf(p);
			} catch (Exception e) {
			}
		}
		return page;
	}

	public static String permalink(String permalink) {
		permalink = permalink.replaceAll("\\pP", "-").replaceAll("\\pM", "-")
				.replaceAll("\\pS", "-").replaceAll("\\pC", "-");
		while (permalink.indexOf("--") > -1) {
			permalink = permalink.replace("--", "-");
		}
		if (permalink.indexOf('-') == 0) {
			permalink = permalink.substring(1);
		}
		if (!permalink.isEmpty() && permalink.lastIndexOf('-') == permalink.length() - 1) {
			permalink = permalink.substring(0, permalink.length() - 1);
		}
		permalink = new ChineseSpelling(permalink.toLowerCase().trim()).getSpelling();
		if (permalink.length() > 32) {
			permalink = permalink.substring(0, 32);
		}
		return permalink;
	}

	/**
	 * 取得请求脚本名
	 * 
	 * @param request
	 * @return
	 */
	public static String requestScript(HttpServletRequest request) {
		return request.getRequestURI().replaceFirst(request.getContextPath(), "").toLowerCase();
	}

	/**
	 * 求商并取整
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int round(int a, int b) {
		return (((double) a / (double) b) > (a / b) ? a / b + 1 : a / b);
	}

	public static String shortId(long id) {
		StringBuilder sb = new StringBuilder();
		long ori = id;
		while (ori > 0) {
			sb.append(CHARS.charAt((int) (ori % CHARS.length())));
			ori /= CHARS.length();
		}
		return sb.toString();
	}

	/**
	 * 去除 html 标签
	 * 
	 * @param input
	 * @return
	 */
	public static String stripTags(String input) {
		return input
				.replaceAll("<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>", "")
				.replaceAll("<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>", "")
				.replaceAll("<[^>]+>", "").replaceAll("<[^>]+", "");
	}

	/**
	 * 取得时间字符串
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String timeString(long timestamp) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
	}

	/**
	 * 取得时间戳
	 * 
	 * @return
	 */
	public static long timestamp() {
		return System.currentTimeMillis();
	}

	/**
	 * 转换 html 字符并去除空格
	 * 
	 * @param input
	 * @return
	 */
	public static String trimAndStripTags(String input) {
		return input.replace("&amp;", "&").replace("&", "&amp;").replace("\'", "&apos;")
				.replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;")
				.replace("«", "&laquo;").replace("»", "&raquo;").trim();
	}

	public static String urlDecode(String input) {
		try {
			input = new String(input.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			input = "";
		}
		return input;
	}

	/**
	 * url编码
	 * 
	 * @param input
	 * @return
	 */
	public static String urlEncode(String input) {
		try {
			input = URLEncoder.encode(Function.stripTags(input).trim().toLowerCase(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			input = "";
		}
		return input;
	}

}
