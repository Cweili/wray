package org.cweili.wray.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constant {
	
	public static final int ADMIN_LIST_LIMIT = 12;
	
	public static final String CURRENT_YEAR = new SimpleDateFormat("yyyy").format(new Date());
	
	public static final String[] LABELS = new String[] { "firstPageLabel", "lastPageLabel",
		"previousPageLabel", "nextPageLabel", "sumLabel", "pageLabel", "noticeBoardLabel",
		"recentCommentsLabel", "mostCommentArticlesLabel", "mostViewCountArticlesLabel",
		"popTagsLabel", "archiveLabel", "linkLabel", "atomLabel", "homeLabel", "commentLabel",
		"moreLabel", "tagLabel", "viewLabel" };
	
	public static final String SKIN_PATH = "skin/";
	
	public static final String WRAY_VERSION = "0.1.0";
	
}	
