package org.cweili.wray.web.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.Item;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.web.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:37:04
 * 
 */
@Controller
@Scope("prototype")
public final class AdminCategoryController extends BaseController {

	@RequestMapping("/admin-category")
	public BlogView categoryList() {
		BlogView v = new BlogView("category-list");
		v.add("actionName", "分类");
		List<Item> items = categoryService.getCategories();
		v.add("items", items);
		return v;
	}

	@RequestMapping(value = "/admin-category-edit-{categoryid}", method = RequestMethod.GET)
	public BlogView editGet(@PathVariable("categoryid") String categoryid) {
		BlogView v = new BlogView("category-edit");
		v.add("actionName", "编辑分类");
		Item category = categoryService.findById(categoryid);
		if (null == category) {
			category = new Item();
		}
		v.add("category", category);
		return v;
	}

	@RequestMapping(value = "/admin-category-edit-{categoryid}", method = RequestMethod.POST)
	public @ResponseBody
	String editPost(WebRequest request, @PathVariable("categoryid") String categoryid) {
		Item category = categoryService.findById(categoryid);
		if (null != category) {
			category = getCategory(request, category);
			if (null != categoryService.save(category)) {
				return Constant.SUBMIT_SUCCESS;
			}
		}
		return Constant.SUBMIT_FAILED;
	}

	@RequestMapping(value = "/admin-category-add", method = RequestMethod.GET)
	public BlogView addGet() {
		BlogView v = new BlogView("category-edit");
		v.add("actionName", "新增分类");
		v.add("category", new Item());
		return v;
	}

	@RequestMapping(value = "/admin-category-add", method = RequestMethod.POST)
	public @ResponseBody
	String addPost(WebRequest request) {
		Item category = getCategory(request, null);
		if (null != categoryService.save(category)) {
			return "admin-category-edit-" + category.getItemId();
		}
		return Constant.SUBMIT_FAILED;
	}

	@RequestMapping(value = "/admin-category-manage", method = RequestMethod.POST)
	public BlogView manage(WebRequest request) {
		BlogView v = new BlogView("msg");
		v.add("err", "succ");
		v.add("msg", "分类更新成功");
		v.add("succ", "恭喜您，您的分类排序已成功更新，选中分类已删除。");
		v.add("redirect", "admin-category/");

		List<String> ids = new ArrayList<String>();
		if (request.getParameterValues("id") != null) {
			Collections.addAll(ids, request.getParameterValues("id"));
		}

		List<Item> items = categoryService.getCategories();
		byte order = 0;
		Item item;

		for (int i = 0; i < items.size(); ++i) {
			if (request.getParameter("order" + items.get(i).getItemId()) != null) {
				order = Byte.valueOf(request.getParameter("order" + items.get(i).getItemId()));
				item = items.get(i);
				if (item.getItemOrder() != order) {
					item.setItemOrder(order);
					categoryService.save(item);
				}
			}
		}

		if (!ids.isEmpty() && !categoryService.remove(ids)) {
			v.add("err", "数据库更新失败");
			v.add("msg", "链接删除失败");
		}
		return v;
	}

	private Item getCategory(WebRequest request, Item ori) {
		String itemName = StringUtils.trimToEmpty(request.getParameter("itemName"));
		String permalink = StringUtils.trimToEmpty(request.getParameter("permalink"));
		String description = StringUtils.trimToEmpty(request.getParameter("description"));
		byte itemOrder = 0;
		try {
			itemOrder = Byte.valueOf(request.getParameter("itemOrder"));
		} catch (Exception e) {
			log.error(e);
		}

		String id = Function.generateId();
		itemName = Function.htmlSpecialChars(itemName);
		itemName = "".equals(itemName) ? Function.timeString() : itemName;
		permalink = Function.permalink(permalink);
		permalink = "".equals(permalink) ? Function.permalink(itemName) : permalink;
		description = Function.htmlSpecialChars(description);

		if (ori != null) {
			ori.setItemName(itemName);
			ori.setPermalink(permalink);
			ori.setDescription(description);
			ori.setItemOrder(itemOrder);
			return ori;
		}
		return new Item(id, itemName, permalink, description, 0, itemOrder, Item.TYPE_CATEGORY, 0,
				Item.STAT_ON);
	}

}
