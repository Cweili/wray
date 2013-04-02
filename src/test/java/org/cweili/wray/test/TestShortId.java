/**
 * 
 */
package org.cweili.wray.test;

import org.cweili.wray.util.Function;

/**
 * 
 * @author Cweili
 * @version 2013-4-1 上午10:19:25
 * 
 */
public class TestShortId {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long id = Function.generateId();
		System.out.println(id + "\n" + Function.shortId(id) + "\n"
				+ Function.decodeId(Function.shortId(id)));
	}

}
