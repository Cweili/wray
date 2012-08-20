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

	/**
	 * url编码
	 * 
	 * @param input
	 * @return
	 */
	public static String url(String input) {
		try {
			input = URLEncoder.encode(Function.stripTags(input).trim().toLowerCase(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			input = "";
		}
		return input;
	}

}
