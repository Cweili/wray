package org.cweili.wray.util;

import org.springframework.web.servlet.ModelAndView;

/**
 * 继承 ModelAndView 方便以后扩展
 * 
 * @author cweili
 * @version 2012-8-16 下午5:23:55
 * 
 */
public class BlogView extends ModelAndView {

	/**
	 * 
	 */
	public BlogView() {
		super();
	}

	/**
	 * @param viewName
	 */
	public BlogView(String viewName) {
		super();
		setView(viewName);
	}

	/**
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	public BlogView add(String attributeName, Object attributeValue) {
		super.addObject(attributeName, attributeValue);
		return this;
	}

	/**
	 * @param viewName
	 */
	public void setView(String viewName) {
		super.setViewName(viewName);
	}
}
