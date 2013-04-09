package org.cweili.wray.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Wray 公用函数
 * 
 * @author cweili
 * @version 2012-8-16 下午5:27:40
 * 
 */
public class Function {

	private static final Log log = LogFactory.getLog(Function.class);

	private static final CharSequence CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	private static long id = 0;

	// public static String cookieSecrityKey(String src) {
	// String sha256 = sha256(src);
	// StringBuilder sb = new StringBuilder();
	// for (int i = 0; i < sha256.length(); i += 16) {
	// sb.append(encode(Long.parseLong(sha256.substring(i, i + 15), 16)));
	// }
	// return sb.toString();
	// }

	public static String authorityKey(String time, String name, String password) {
		return sha256(name + time + password);
	}

	/**
	 * 将字符串还原为数值
	 * 
	 * @param src
	 * @return
	 */
	public static long decode(CharSequence src) {
		long out = 0;
		for (int i = 0; i < src.length(); ++i) {
			out *= CHARS.length();
			out += CHARS.toString().indexOf(src.charAt(i));
		}
		return out;
	}

	/**
	 * 取得路径下的文件夹列表
	 * 
	 * @param file
	 * @return
	 */
	public static List<String> dirList(String pathname) {
		File file = new File(pathname);
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

	/**
	 * 数值编码为字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String encode(long src) {
		StringBuilder sb = new StringBuilder();
		long tmp = src;
		while (tmp > 0) {
			sb.insert(0, CHARS.charAt((int) (tmp % CHARS.length())));
			tmp /= CHARS.length();
		}
		return sb.toString();
	}

	/**
	 * 创建实体唯一id
	 * 
	 * @return
	 */
	public synchronized static String generateId() {
		long time = timestamp() / 100;
		if (id < time) {
			id = time;
		} else {
			++id;
		}
		return encode(id);
	}

	/**
	 * SHA-256
	 * 
	 * @param source
	 * @return
	 */
	public static String sha256(String source) {
		String s = null;
		CharSequence hexDigits = "0123456789abcdef";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(source.getBytes());
			byte tmp[] = md.digest();
			char str[] = new char[64];
			int k = 0;
			for (int i = 0; i < 32; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits.charAt(byte0 >>> 4 & 0xf);
				str[k++] = hexDigits.charAt(byte0 & 0xf);
			}
			s = new String(str);

		} catch (Exception e) {
			log.error(e, e);
		}
		return s;
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

	/**
	 * 将字符串转换成 permalink
	 * 
	 * @param permalink
	 * @return
	 */
	public static String permalink(String permalink) {
		permalink = permalink.replaceAll("\\pP", "-").replaceAll("\\pM", "-")
				.replaceAll("\\pS", "-").replaceAll("\\pC", "-").replace(' ', '-');
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
	 * 求商并取整
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int round(int a, int b) {
		return (((double) a / (double) b) > (a / b) ? a / b + 1 : a / b);
	}

	/**
	 * 去除 html 标签
	 * 
	 * @param input
	 * @return
	 */
	public static String stripTags(String input) {
		return input.replaceAll("<[^>]+>", "");
	}

	public static String stripTags(String input, String[] tags) {
		StringBuilder sb = new StringBuilder("(");
		for (String tag : tags) {
			sb.append('|').append(tag);
		}
		sb.deleteCharAt(1);
		sb.append(')');
		return stripTag(input, sb.toString());
	}

	/**
	 * 去除指定 html 标签
	 * 
	 * @param input
	 * @param tag
	 * @return
	 */
	public static String stripTag(String input, String tag) {
		return input.replaceAll("<[//]?" + tag + "[^>]*>", "");
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
	 * 取得时间字符串
	 * 
	 * @return
	 */
	public static String timeString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
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
