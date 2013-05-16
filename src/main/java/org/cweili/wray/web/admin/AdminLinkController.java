package org.cweili.wray.web.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.BlogView;
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
 * 链接管理 Controller
 * 
 * @author Cweili
 * @version 2012-8-16 下午5:39:12
 * 
 */
@Controller
public final class AdminLinkController extends BaseController {

	@RequestMapping(value = "/admin-link-manage", method = RequestMethod.GET)
	public BlogView linkList() {
		BlogView v = new BlogView("link-list");
		v.add("actionName", "博客链接");
		List<Item> items = linkService.getLinks();
		v.add("items", items);
		return v;
	}

	@RequestMapping(value = "/admin-link-edit-{linkid}", method = RequestMethod.GET)
	public BlogView editGet(@PathVariable("linkid") String linkid) {
		BlogView v = new BlogView("link-edit");
		v.add("actionName", "编辑博客链接");
		v.add("itemId", linkid);
		Item link = linkService.findById(linkid);
		if (link == null) {
			link = new Item();
		}
		v.add("link", link);
		return v;
	}

	@RequestMapping(value = "/admin-link-edit-{linkid}", method = RequestMethod.POST)
	public @ResponseBody
	String editPost(WebRequest request, @PathVariable("linkid") String linkid) {
		Item link = linkService.findById(linkid);
		if (null != link) {
			link = getLink(request, link);
			try {
				if (null != linkService.save(link)) {
					return Constant.SUBMIT_SUCCESS;
				}
			} catch (Exception e) {
				return Constant.SUBMIT_FAILED;
			}
		}
		return Constant.SUBMIT_FAILED;
	}

	@RequestMapping(value = "/admin-link-add", method = RequestMethod.GET)
	public BlogView addGet() {
		BlogView v = new BlogView("link-edit");
		v.add("actionName", "新增博客链接");
		v.add("link", new Item());
		return v;
	}

	@RequestMapping(value = "/admin-link-add", method = RequestMethod.POST)
	public @ResponseBody
	String addPost(WebRequest request) {
		Item link = getLink(request, null);
		try {
			if (null != linkService.save(link)) {
				return "admin-link-edit-" + link.getItemId();
			}
		} catch (Exception e) {
			return Constant.SUBMIT_FAILED;
		}
		return Constant.SUBMIT_FAILED;
	}

	@RequestMapping(value = "/admin-link-manage", method = RequestMethod.POST)
	public BlogView manage(WebRequest request) {
		BlogView v = new BlogView("msg");
		v.add("err", "succ");
		v.add("msg", "链接更新成功");
		v.add("succ", "恭喜您，您的链接排序已成功更新，选中链接已删除。");
		v.add("redirect", "admin-link-manage");

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
			v.add("msg", "链接删除失败");
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
			String linkId = Function.generateId();
			return new Item(linkId, itemName, linkId, description, 0, itemOrder, Item.TYPE_LINK,
					Item.STAT_ON);
		}
		return null;
	}

}
