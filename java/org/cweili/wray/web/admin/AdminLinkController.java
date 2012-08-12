package org.cweili.wray.web.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Item;
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
public class AdminLinkController extends BaseController {

	@Override
	@RequestMapping(value="/admin-link-edit-{linkid}", method = RequestMethod.GET)
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String linkid) {
		BlogView v = new BlogView("link-edit");
		v.add("actionName", "编辑博客链接");
		v.add("itemId", linkid);
		long id = 0;
		try {
			id = Long.valueOf(linkid);
		} catch(Exception e) {
			log.error(e.toString());
		}
		Item link = linkService.getLinkById(id);
		if(link != null) {
			v.add("itemName", link.getItemName());
			v.add("description", link.getDescription());
			v.add("itemOrder", link.getItemOrder());
			v.add("err", "");
		} else {
			v.add("err", "链接未找到");
		}
		return v;
	}
	
	@RequestMapping(value="/admin-link-edit-{linkid}", method = RequestMethod.POST)
	public BlogView editPost(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String linkid) {
		BlogView v = new BlogView("msg");
		v.add("actionName", "编辑博客链接");
		v.add("redirect", "admin-link");
		long id = 0;
		try {
			id = Long.valueOf(linkid);
		} catch(Exception e) {
			log.error(e.toString());
		}
		Item link = linkService.getLinkById(id);
		link = getLink(request, link);
		v.add("err", "succ");
		v.add("msg", "链接保存成功");
		v.add("succ", "恭喜您，您的链接已成功保存。");
		try {
			linkService.update(link);
		} catch(Exception e) {
			v.setView("link-edit");
			v.add("itemId", linkid);
			v.add("itemName", link.getItemName());
			v.add("description", link.getDescription());
			v.add("itemOrder", link.getItemOrder());
			v.add("err", "数据库更新失败");
		}
		return v;
	}
	
	@RequestMapping(value="/admin-link-add", method = RequestMethod.GET)
	public BlogView addGet(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("link-edit");
		v.add("actionName", "新增博客链接");
		v.add("itemOrder", 0);
		return v;
	}
	
	@RequestMapping(value="/admin-link-add", method = RequestMethod.POST)
	public BlogView addPost(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("msg");
		v.add("actionName", "新增博客链接");
		Item link = getLink(request, null);
		v.add("redirect", "admin-link");
		v.add("err", "succ");
		v.add("msg", "链接保存成功");
		v.add("succ", "恭喜您，您的链接已成功保存。");
		try {
			linkService.save(link);
		} catch(Exception e) {
			v.setView("link-edit");
			v.add("itemName", link.getItemName());
			v.add("description", link.getDescription());
			v.add("itemOrder", link.getItemOrder());
			v.add("err", "数据库更新失败");
		}
		return v;
	}
	
	@Override
	@RequestMapping("/admin-link")
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("link-list");
		v.add("actionName", "博客链接");
		List<Item> links = linkService.getLinks();
		v.add("links", links);
		return v;
	}
	
	private Item getLink(HttpServletRequest request, Item ori) {
		String itemName = request.getParameter("itemName") != null ? request.getParameter("itemName") : "";
		String description = request.getParameter("description") != null ? request.getParameter("description") : "";
		int itemOrder = 0;
		try {
			itemOrder = Integer.valueOf(request.getParameter("itemOrder"));
		} catch (Exception e) {
			log.error(e.toString());
		}
		
		Long id = Function.generateId();
		itemName = Function.trimAndStripTags(itemName);
		itemName = "".equals(itemName) ? "无标题" + id : itemName;
		description = Function.trimAndStripTags(description);
		
		if(ori != null) {
			ori.setItemName(itemName);
			ori.setDescription(description);
			ori.setItemOrder(itemOrder);
			return ori;
		}
		return new Item(id, itemName, "", description, 0, itemOrder, Item.TYPE_LINK, 0, Item.STAT_ON);
	}

}
