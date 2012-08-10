package org.cweili.wray.util;

import org.springframework.web.servlet.ModelAndView;

public class BlogView extends ModelAndView {
	
	public BlogView() {
		super();
	}
	
	public BlogView(String viewName) {
		super();
		setView(viewName);
	}

	public BlogView add(String attributeName, Object attributeValue) {
		super.addObject(attributeName, attributeValue);
		return this;
	}
	
	public void setView(String viewName) {
		super.setViewName(viewName + ".ftl");
	}
}
