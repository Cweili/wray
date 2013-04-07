/**
 * 
 */
package org.cweili.wray.util;

/**
 * 
 * @author cweili
 * @version 2013-4-7 下午3:47:17
 * 
 */
public class NotFoundException extends Exception {

	private static final long serialVersionUID = -8087530739459125746L;

	@Override
	public String getLocalizedMessage() {
		return this.toString();
	}

	@Override
	public String toString() {
		return "内容未找到";
	}

}
