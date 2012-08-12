package org.cweili.wray.web.admin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Function;
import org.cweili.wray.web.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("prototype")
public class AdminSetupController extends BaseController {

	@Override
	@RequestMapping(value="/admin-setup-basic", method = RequestMethod.POST)
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		
		BlogView v = new BlogView("setup-basic");
		if(saveConfig(request, new String[]{
				"blogTitle", "blogSubtitle", "metaKeywords", "metaDescription"
			}, new String[]{
				"attachHeader", "attachFooter", "attachStat"
			})) {
			v.add("err", "succ");
		} else {
			v.add("err", "数据库更新失败");
		}
		return v;
	}
	
	@RequestMapping(value="/admin-setup-account", method = RequestMethod.POST)
	public BlogView account(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("setup-account");
		if(saveConfig(request, new String[]{
				"adminName", "adminNick", "adminEmail"
			}, "".equals(request.getParameter("adminPwd")) ? new String[]{} : new String[]{
				"adminPwd"
			})) {
			v.add("err", "succ");
		} else {
			v.add("err", "数据库更新失败");
		}
		return v;
	}
	
	@RequestMapping(value="/admin-setup-skin", method = RequestMethod.POST)
	public BlogView skin(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("setup-account");
		v.add("err", "");
		return v;
	}

	@Override
	@RequestMapping(value="/admin-setup-{type}", method = RequestMethod.GET)
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String type) {
		BlogView v = new BlogView("msg");
		if("basic".equals(type) || "account".equals(type) || "skin".equals(type)) {
			v.setView("setup-" + type);
			v.add("err", "");
		} else {
			v.add("err", "找不到页面");
		}
		return v;
	}
	
	private boolean saveConfig(HttpServletRequest request, String[] nonHtmlArray, String[] htmlArray) {
		List<String> nonHtmlList = Arrays.asList(nonHtmlArray);
		List<String> htmlList = Arrays.asList(htmlArray);
		Map<String, String[]> map = request.getParameterMap();
		Map<String, String> values = new HashMap<String, String>();
		String key;
		for(Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			key = it.next();
			if(nonHtmlList.contains(key)) {
				values.put(key, Function.trimAndStripTags(map.get(key)[0]));
			} else if(htmlList.contains(key)) {
				values.put(key, map.get(key)[0].trim());
				
			}
		}
		byte failed = 0;
		for(Iterator<String> it = values.keySet().iterator(); it.hasNext();) {
			key = it.next();
			if(!blogConfig.saveOrUpdate(key, values.get(key))) {
				++failed;
			}
		}
		if(failed == 0) {
			blogConfig.UpdateConfigMap();
		}
		return failed == 0;
	}

}
