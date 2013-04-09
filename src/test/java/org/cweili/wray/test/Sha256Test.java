/**
 * 
 */
package org.cweili.wray.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.util.Function;

/**
 * 
 * @author cweili
 * @version 2013-4-9 下午4:59:41
 * 
 */
public class Sha256Test {

	private static final Log log = LogFactory.getLog(Sha256Test.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "a";
		log.info(Function.sha256(s) + "////" + Function.sha256(s).length());
		// log.info(Function.cookieSecrityKey(s) + "////" +
		// Function.cookieSecrityKey(s).length());
	}

}
