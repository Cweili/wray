package org.cweili.wray.util;

import java.util.Calendar;

/**
 * Wray 常量
 * 
 * @author cweili
 * @version 2013-4-9 下午4:16:00
 * 
 */
public class Constant {

	/**
	 * 调试状态
	 */
	public static final boolean DEBUG = false;

	/**
	 * 默认字符集
	 */
	public static final String CHARSET = "UTF-8";

	/**
	 * 分页最大值
	 */
	public static final int MAX_PAGE_SIZE = Integer.MAX_VALUE;

	/**
	 * 客户端缓存时间
	 */
	public static final int CACHE_MAX_AGE = 31536000;

	/**
	 * 管理面板分页大小
	 */
	public static final int ADMIN_LIST_SIZE = 12;

	/**
	 * 当前年份
	 */
	public static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

	/**
	 * 当前月份
	 */
	public static final int CURRENT_MONTH = Calendar.getInstance().get(Calendar.MONTH) + 1;

	/**
	 * 验证码
	 */
	public static final String CAPTCHA = "captcha";

	/**
	 * 提交成功
	 */
	public static final String SUBMIT_SUCCESS = "success";

	/**
	 * 提交失败
	 */
	public static final String SUBMIT_FAILED = "";

	/**
	 * 登陆验证时间
	 */
	public static final String AUTHORITY_TIME = "wray_t";

	/**
	 * 登陆验证令牌
	 */
	public static final String AUTHORITY_TOKEN = "wray_k";

	/**
	 * COOKIE 保存用户名
	 */
	public static final String AUTHORITY_NAME = "wray_n";

	/**
	 * 危险 html 标签
	 */
	public static final String[] DANGEROUS_TAGS = new String[] { "applet", "body", "embed",
			"frame", "script", "frameset", "html", "iframe", "object", "meta", "style", "layer",
			"link", "ilayer", "form", "input", "textarea", "select", "button" };

	/**
	 * 皮肤界面 label 数组
	 */
	public static final String[] LABELS = new String[] { "firstPageLabel", "lastPageLabel",
			"previousPageLabel", "nextPageLabel", "sumLabel", "pageLabel", "noticeBoardLabel",
			"recentCommentsLabel", "mostCommentArticlesLabel", "mostViewCountArticlesLabel",
			"popTagsLabel", "archiveLabel", "linkLabel", "atomLabel", "homeLabel", "commentLabel",
			"moreLabel", "returnLabel", "tagLabel", "searchLabel", "viewLabel",
			"commentAuthorLabel", "commentEmailLabel", "commentLinkLabel", "submmitCommentLabel",
			"requiredErrorLabel", "inputErrorLabel", "maxlengthErrorLabel", "allTagsLabel" };

	/**
	 * 皮肤扩展名
	 */
	public static final String SKIN_EXT = ".ftl";

	/**
	 * 皮肤路径
	 */
	public static final String SKIN_PATH = "skin/";

	/**
	 * Wray 版本
	 */
	public static final String WRAY_VERSION = "0.1.0";

	/**
	 * 创建工具
	 */
	public static final String GENERATOR = "WRAY " + WRAY_VERSION;

}
