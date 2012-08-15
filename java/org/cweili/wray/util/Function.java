package org.cweili.wray.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Function {
	
	public static List<String> dirList(File file) {
		File[] files = file.listFiles();
		List<String> list = new ArrayList<String>();
		for (File f : files) {
			if (f.isDirectory()) {
				list.add(f.getPath().substring(f.getPath().lastIndexOf('/') + 1));
			}
		}
		return list;
	}

	private static long id = 0;
	public synchronized static long generateId() {
		long time = timestamp();
		if(id < time) {
			id = time;
		} else {
			++id;
		}
		return id;
	}
	
	public static int page(String uri) {
		int page = 1;
		try {
			if(uri.contains("/page-")) {
				String p = uri.substring(uri.lastIndexOf("/page-") + 6, uri.lastIndexOf("/"));
				page = Integer.valueOf(p);
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return page;
	}
	
	public static String requestScript(HttpServletRequest request) {
		return request.getRequestURI().replaceFirst(request.getContextPath(), "").toLowerCase();
	}
	
	public static int round(int a, int b) {
		return (((double) a / (double) b ) > ( a / b ) ? a / b + 1 : a / b);
	}
	
	public static String stripTags(String input) {
		return input.replaceAll("<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>", "")
				.replaceAll("<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>", "")
				.replaceAll("<[^>]+>", "")
				.replaceAll("<[^>]+", "");
	}
	
	public static String timeString(long timestamp) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
	}
	
	public static long timestamp() {
		return System.currentTimeMillis();
	}
	
	public static String trimAndStripTags(String input) {
		return input.replace("&amp;", "&").replace("&", "&amp;").replace("\'", "&apos;").replace("\"", "&quot;")
				.replace("<", "&lt;").replace(">", "&gt;").replace("«", "&laquo;").replace("»", "&raquo;").trim();
	}
	
}
