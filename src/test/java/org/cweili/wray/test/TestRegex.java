/**
 * 
 */
package org.cweili.wray.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;

/**
 * 
 * @author cweili
 * @version 2013-4-7 下午7:29:37
 * 
 */
public class TestRegex {

	private static final Log log = LogFactory.getLog(TestRegex.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String html = "<script type=\"text/javascript\">alert();</script>";
		log.info(html.replaceAll("<[//]?" + "script" + "[^>]*>", ""));
		log.info(Function.stripTags(html, Constant.DANGEROUS_TAGS));
	}
}
