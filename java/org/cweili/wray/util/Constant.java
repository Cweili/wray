package org.cweili.wray.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Wray 常量
 * 
 * @author cweili
 * @version 2012-8-16 下午5:25:21
 * 
 */
public class Constant {

	/**
	 * 管理面板分页大小
	 */
	public static final int ADMIN_LIST_SIZE = 12;

	/**
	 * 当前年份
	 */
	public static final String CURRENT_YEAR = new SimpleDateFormat("yyyy").format(new Date());

	/**
	 * 皮肤界面 label 数组
	 */
	public static final String[] LABELS = new String[] { "firstPageLabel", "lastPageLabel",
			"previousPageLabel", "nextPageLabel", "sumLabel", "pageLabel", "noticeBoardLabel",
			"recentCommentsLabel", "mostCommentArticlesLabel", "mostViewCountArticlesLabel",
			"popTagsLabel", "archiveLabel", "linkLabel", "atomLabel", "homeLabel", "commentLabel",
			"moreLabel", "tagLabel", "viewLabel" };

	/**
	 * 皮肤扩展名
	 */
	public static final String SKIN_EXT = ".ftl";

	/**
	 * 皮肤目录
	 */
	public static final String SKIN_PATH = "skin/";

	/**
	 * Wray 版本
	 */
	public static final String WRAY_VERSION = "0.1.0";

}
