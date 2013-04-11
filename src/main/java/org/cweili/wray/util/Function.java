package org.cweili.wray.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

	private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

	private static long id = 0;

	/**
	 * 生成验证串
	 * 
	 * @param time
	 * @param name
	 * @param password
	 * @return
	 */
	public static String authorityKey(String time, String name, String password) {
		String sha256 = sha256(name + time + password);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sha256.length(); i += 16) {
			sb.append(encode(Long.parseLong(sha256.substring(i, i + 15), 16)));
		}
		return sb.toString();
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
			out <<= 6;
			out += CHARS.indexOf(src.charAt(i));
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
		char[] c = new char[64];
		long tmp = src;
		int i = 64;
		while (tmp > 0 && i >= 0) {
			c[--i] = CHARS.charAt((int) tmp & 0x3f);
			tmp >>>= 6;
		}
		return new String(Arrays.copyOfRange(c, i, 64));
	}

	/**
	 * 创建实体唯一id
	 * 
	 * @return
	 */
	public synchronized static String generateId() {
		long time = timestamp() >>> 6;
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
			byte[] digest = md.digest();
			char[] c = new char[64];
			int k = 0;
			for (int i = 0; i < 32; i++) {
				byte b = digest[i];
				c[k++] = hexDigits.charAt(b >>> 4 & 0xf);
				c[k++] = hexDigits.charAt(b & 0xf);
			}
			s = new String(c);

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
			String p;
			if (uri.lastIndexOf("/") > uri.lastIndexOf("/page-") + 6) {
				p = uri.substring(uri.lastIndexOf("/page-") + 6, uri.lastIndexOf("/"));
			} else if (uri.lastIndexOf(".") > uri.lastIndexOf("/page-") + 6) {
				p = uri.substring(uri.lastIndexOf("/page-") + 6, uri.lastIndexOf("/"));
			} else {
				p = uri.substring(uri.lastIndexOf("/page-") + 6);
			}
			try {
				page = Integer.valueOf(p);
			} catch (Exception e) {
				log.error(e, e);
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
			permalink = StringUtils.replace(permalink, "--", "-");
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

		input = StringUtils.replace(input, "&amp;", "&");
		input = StringUtils.replace(input, "&", "&amp;");
		input = StringUtils.replace(input, "\'", "&apos;");
		input = StringUtils.replace(input, "\"", "&quot;");
		input = StringUtils.replace(input, "<", "&lt;");
		input = StringUtils.replace(input, ">", "&gt;");
		input = StringUtils.replace(input, "«", "&laquo;");
		input = StringUtils.replace(input, "&", "&amp;");
		input = StringUtils.replace(input, "»", "&raquo;");
		input = StringUtils.stripToEmpty(input);

		return input;
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
			input = URLEncoder.encode(stripTags(input).trim().toLowerCase(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			input = "";
		}
		return input;
	}

}
