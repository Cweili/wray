/**
 * 
 */
package org.cweili.wray.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Cweili
 * @version 2013-4-8 下午12:13:56
 * 
 */
public class TestPath {

	private static final Log log = LogFactory.getLog(TestPath.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info(StringUtils.substring(TestPath.class.getResource("/").toString(), 6));
	}

}
