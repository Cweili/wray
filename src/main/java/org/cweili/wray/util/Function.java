package org.cweili.wray.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 公用方法
 * 
 * @author Cweili
 * @version 2013-4-27 下午8:13:33
 * 
 */
public class Function {

	private static final Log log = LogFactory.getLog(Function.class);

	private static final String CHARS = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz";

	private static long id = 0;

	/**
	 * 生成验证串
	 * 
	 * @param time
	 * @param name
	 * @param password
	 * @return
	 */
	public static String authorityToken(String time, String name, String password) {
		String sha256 = sha256(name + time + password);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sha256.length(); i += 16) {
			sb.append(encode(Long.parseLong(StringUtils.substring(sha256, i, i + 15), 16)));
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
				list.add(StringUtils.substring(f.getPath(), beginIndex + 1));
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
		char[] c = new char[11];
		long tmp = src;
		tmp = tmp > 0 ? tmp : -1 - tmp;
		int i = 11;
		while (tmp > 0 && i >= 0) {
			c[--i] = CHARS.charAt((int) tmp & 0x3f);
			tmp >>>= 6;
		}
		return new String(Arrays.copyOfRange(c, i, 11));
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
	 * 转换 html 字符并去除空格
	 * 
	 * @param input
	 * @return
	 */
	public static String escapeHtml(String input) {

		// input = StringUtils.replace(input, "&amp;", "&");
		// input = StringUtils.replace(input, "&", "&amp;");
		// input = StringUtils.replace(input, "\'", "&apos;");
		// input = StringUtils.replace(input, "\"", "&quot;");
		// input = StringUtils.replace(input, "<", "&lt;");
		// input = StringUtils.replace(input, ">", "&gt;");
		// input = StringUtils.replace(input, "«", "&laquo;");
		// input = StringUtils.replace(input, "»", "&raquo;");

		input = StringEscapeUtils.escapeHtml4(input);
		input = StringUtils.stripToEmpty(input);

		return input;
	}

	/**
	 * MD5
	 * 
	 * @param source
	 * @return
	 */
	public static String md5(String source) {
		String s = null;
		CharSequence hexDigits = "0123456789abcdef";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source.getBytes());
			byte[] digest = md.digest();
			char[] c = new char[32];
			for (int i = 0, k = 0; i < 16; ++i) {
				byte b = digest[i];
				c[k++] = hexDigits.charAt(b >>> 4 & 0xf);
				c[k++] = hexDigits.charAt(b & 0xf);
			}
			s = new String(c);

		} catch (Exception e) {
			log.error(e);
		}
		return s;
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
			for (int i = 0, k = 0; i < 32; ++i) {
				byte b = digest[i];
				c[k++] = hexDigits.charAt(b >>> 4 & 0xf);
				c[k++] = hexDigits.charAt(b & 0xf);
			}
			s = new String(c);

		} catch (Exception e) {
			log.error(e);
		}
		return s;
	}

	/**
	 * 取最小正整数
	 * 
	 * @param string
	 * @return
	 */
	public static int minimumPositiveInteger(String string) {
		return minimumInteger(string, 1);
	}

	/**
	 * 取最小整数
	 * 
	 * @param string
	 * @param min
	 *            最小整数值
	 * @return
	 */
	public static int minimumInteger(String string, int min) {
		int integer = defaultInteger(string, min);
		return integer >= min ? integer : min;
	}

	public static int defaultInteger(String string, int integer) {
		if (null != string) {
			try {
				integer = Integer.parseInt(string);
			} catch (Exception e) {
				log.error(e);
			}
		}
		return integer;
	}

	/**
	 * 将字符串转换成 permalink
	 * 
	 * @param permalink
	 * @return
	 */
	public static String permalink(String permalink) {
		permalink = permalink.replaceAll("[\\s\\p{Blank}\\p{Cntrl}\\p{Punct}]", "-");
		while (permalink.indexOf("--") > -1) {
			permalink = StringUtils.replace(permalink, "--", "-");
		}
		if (permalink.indexOf('-') == 0) {
			permalink = StringUtils.substring(permalink, 1);
		}
		if (!permalink.isEmpty() && permalink.lastIndexOf('-') == permalink.length() - 1) {
			permalink = StringUtils.left(permalink, permalink.length() - 1);
		}
		permalink = ChineseSpelling.getSpelling(permalink.toLowerCase().trim());
		if (permalink.length() > 32) {
			permalink = StringUtils.left(permalink, 32);
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
	public static long round(long a, long b) {
		return (((double) a / (double) b) > (a / b) ? a / b + 1 : a / b);
	}

	public static <K extends Object, V extends Number> List<Entry<K, V>> sortMapByValue(
			Map<K, V> map) {

		List<Entry<K, V>> list = new LinkedList<Entry<K, V>>(map.entrySet());

		Collections.sort(list, new Comparator<Entry<K, V>>() {

			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				if (e1.getValue().doubleValue() > e2.getValue().doubleValue()) {
					return -1;
				} else if (e1.getValue().doubleValue() < e2.getValue().doubleValue()) {
					return 1;
				}
				return 0;
			}

		});

		return list;

	}

	/**
	 * 去除 html 标签
	 * 
	 * @param input
	 * @return
	 */
	public static String stripTags(String input) {
		return escapeHtml(input.replaceAll("<[^>]+>", ""));
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

	public static String urlDecode(String input) {
		if (ServerDetector.getInstance().detect() == ServerDetector.TOMCAT) {
			try {
				input = new String(input.getBytes("ISO-8859-1"), Constant.CHARSET);
			} catch (UnsupportedEncodingException e) {
				input = "";
			}
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
			input = URLEncoder.encode(StringUtils.stripToEmpty(stripTags(input)).toLowerCase(),
					Constant.CHARSET);
		} catch (UnsupportedEncodingException e) {
			input = "";
		}
		return input;
	}

}
