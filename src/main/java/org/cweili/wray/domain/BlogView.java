package org.cweili.wray.domain;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

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
	 * @param view
	 */
	public BlogView(View view) {
		super(view);
	}

	/**
	 * @param viewName
	 * @param model
	 */
	public BlogView(String viewName, Map<String, ?> model) {
		super(viewName, model);
	}

	/**
	 * @param viewName
	 * @param modelName
	 * @param modelObject
	 */
	public BlogView(String viewName, String modelName, Object modelObject) {
		super(viewName, modelName, modelObject);
	}

	/**
	 * @param view
	 * @param model
	 */
	public BlogView(View view, Map<String, ?> model) {
		super(view, model);
	}

	/**
	 * @param view
	 * @param modelName
	 * @param modelObject
	 */
	public BlogView(View view, String modelName, Object modelObject) {
		super(view, modelName, modelObject);
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
