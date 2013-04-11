/**
 * 
 */
package org.cweili.wray.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author cweili
 * @version 2013-4-8 下午12:13:56
 * 
 */
public class TestPath {

	private static final Log log = LogFactory.getLog(TestPath.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info(TestPath.class.getResource("/").toString().substring(6));
	}

}
