package org.cweili.wray.web.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.BlogView;
import org.cweili.wray.domain.dto.Config;
import org.cweili.wray.domain.dto.Item;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 导航栏管理 Controller
 * 
 * @author Cweili
 * @version 2012-8-16 下午5:39:12
 * 
 */
@Controller
public final class AdminNavController extends BaseController {

	@RequestMapping(value = "/admin-nav-manage", method = RequestMethod.GET)
	public BlogView navList() {
		BlogView v = new BlogView("nav-list");
		v.add("actionName", "博客导航");
		List<Item> items = linkService.getNavigators();
		v.add("items", items);
		return v;
	}

	@RequestMapping(value = "/admin-nav-edit-{navid}", method = RequestMethod.GET)
	public BlogView editGet(@PathVariable("navid") String navid) {
		BlogView v = new BlogView("nav-edit");
		v.add("actionName", "编辑博客导航");
		v.add("itemId", navid);
		Item nav = linkService.findById(navid);
		if (nav == null) {
			nav = new Item();
		}
		v.add("nav", nav);
		return v;
	}

	@RequestMapping(value = "/admin-nav-edit-{navid}", method = RequestMethod.POST)
	public @ResponseBody
	String editPost(WebRequest request, @PathVariable("navid") String navid) {
		Item nav = linkService.findById(navid);
		if (null != nav) {
			nav = getLink(request, nav);
			try {
				if (null != linkService.save(nav)) {
					return Constant.SUBMIT_SUCCESS;
				}
			} catch (Exception e) {
				return Constant.SUBMIT_FAILED;
			}
		}
		return Constant.SUBMIT_FAILED;
	}

	@RequestMapping(value = "/admin-nav-add", method = RequestMethod.GET)
	public BlogView addGet() {
		BlogView v = new BlogView("nav-edit");
		v.add("actionName", "新增博客导航");
		v.add("nav", new Item());
		return v;
	}

	@RequestMapping(value = "/admin-nav-add", method = RequestMethod.POST)
	public @ResponseBody
	String addPost(WebRequest request) {
		Item nav = getLink(request, null);
		try {
			if (null != linkService.save(nav)) {
				return "admin-nav-edit-" + nav.getItemId();
			}
		} catch (Exception e) {
			return Constant.SUBMIT_FAILED;
		}
		return Constant.SUBMIT_FAILED;
	}

	@RequestMapping(value = "/admin-nav-manage", method = RequestMethod.POST)
	public BlogView manage(WebRequest request) {
		BlogView v = new BlogView("msg");
		v.add("err", "succ");
		v.add("msg", "导航更新成功");
		v.add("succ", "恭喜您，您的导航设置已成功更新，选中导航已删除。");
		v.add("redirect", "admin-nav-manage");

		blogConfig.save(new Config("navigatorSwitch", Function.defaultInteger(
				request.getParameter("navigatorSwitch"), 0)));

		List<String> ids = new ArrayList<String>();
		if (request.getParameterValues("id") != null) {
			Collections.addAll(ids, request.getParameterValues("id"));
		}

		List<Item> items = linkService.getLinks();
		byte order = 0;
		Item item;

		for (int i = 0; i < items.size(); ++i) {
			if (request.getParameter("order" + items.get(i).getItemId()) != null) {
				order = Byte.valueOf(request.getParameter("order" + items.get(i).getItemId()));
				item = items.get(i);
				if (item.getItemOrder() != order) {
					item.setItemOrder(order);
					linkService.save(item);
				}
			}
		}

		if (!ids.isEmpty() && !linkService.remove(ids)) {
			v.add("err", "数据库更新失败");
			v.add("msg", "导航删除失败");
		}
		return v;
	}

	private Item getLink(WebRequest request, Item ori) {
		if (request.getParameter("itemName") != null) {
			String itemName = request.getParameter("itemName");
			String description = StringUtils.trimToEmpty(request.getParameter("description"));
			byte itemOrder = 0;
			try {
				itemOrder = Byte.valueOf(request.getParameter("itemOrder"));
			} catch (Exception e) {
				log.error(e);
			}

			itemName = Function.escapeHtml(itemName);
			itemName = StringUtils.isEmpty(itemName) ? Function.timeString() : itemName;
			description = Function.escapeHtml(description);

			if (ori != null) {
				ori.setItemName(itemName);
				ori.setDescription(description);
				ori.setItemOrder(itemOrder);
				return ori;
			}
			String navId = Function.generateId();
			return new Item(navId, itemName, navId, description, 0, itemOrder, Item.TYPE_NAVIGATOR,
					Item.STAT_ON);
		}
		return null;
	}

}
