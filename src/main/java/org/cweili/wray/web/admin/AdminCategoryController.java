package org.cweili.wray.web.admin;

import java.util.ArrayList;
import java.util.Collections;
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

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:37:04
 * 
 */
@Controller
@Scope("prototype")
public final class AdminCategoryController extends BaseController {

	@Override
	@RequestMapping("/admin-category")
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("category-list");
		v.add("actionName", "分类");
		List<Item> items = categoryService.getCategories();
		v.add("items", items);
		return v;
	}

	@Override
	@RequestMapping(value = "/admin-category-edit-{categoryid}", method = RequestMethod.GET)
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String categoryid) {
		BlogView v = new BlogView("category-edit");
		v.add("actionName", "编辑分类");
		v.add("itemId", categoryid);
		Item category = categoryService.findById(categoryid);
		if (null != category) {
			v.add("itemName", category.getItemName());
			v.add("permalink", category.getPermalink());
			v.add("description", category.getDescription());
			v.add("itemOrder", category.getItemOrder());
			v.add("err", "");
		} else {
			v.add("err", "分类未找到");
		}
		return v;
	}

	@RequestMapping(value = "/admin-category-edit-{categoryid}", method = RequestMethod.POST)
	public BlogView editPost(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String categoryid) {
		BlogView v = new BlogView("msg");
		v.add("actionName", "编辑分类");
		v.add("redirect", "admin-category");
		Item category = categoryService.findById(categoryid);
		if (null != category) {
			category = getCategory(request, category);
			v.add("err", "succ");
			v.add("msg", "分类保存成功");
			v.add("succ", "恭喜您，您的分类已成功保存。");
			try {
				categoryService.save(category);
			} catch (Exception e) {
				v.setView("category-edit");
				v.add("itemId", categoryid);
				v.add("itemName", category.getItemName());
				v.add("description", category.getDescription());
				v.add("itemOrder", category.getItemOrder());
				v.add("err", "数据库更新失败");
			}
		} else {
			v.add("err", "分类未找到");
		}
		return v;
	}

	@RequestMapping(value = "/admin-category-add", method = RequestMethod.GET)
	public BlogView addGet(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("category-edit");
		v.add("actionName", "新增分类");
		v.add("itemOrder", 0);
		return v;
	}

	@RequestMapping(value = "/admin-category-add", method = RequestMethod.POST)
	public BlogView addPost(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("msg");
		v.add("actionName", "新增分类");
		Item category = getCategory(request, null);
		v.add("redirect", "admin-category");
		v.add("err", "succ");
		v.add("msg", "分类保存成功");
		v.add("succ", "恭喜您，您的分类已成功保存。");
		try {
			categoryService.save(category);
		} catch (Exception e) {
			v.setView("category-edit");
			v.add("itemName", category.getItemName());
			v.add("description", category.getDescription());
			v.add("itemOrder", category.getItemOrder());
			v.add("err", "数据库更新失败");
		}
		return v;
	}

	@RequestMapping(value = "/admin-category-manage", method = RequestMethod.POST)
	public BlogView manage(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("msg");
		v.add("err", "succ");
		v.add("msg", "分类更新成功");
		v.add("succ", "恭喜您，您的分类排序已成功更新，选中分类已删除。");
		v.add("redirect", "admin-category");

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

	private Item getCategory(HttpServletRequest request, Item ori) {
		String itemName = request.getParameter("itemName") != null ? request
				.getParameter("itemName") : "";
		String permalink = request.getParameter("permalink") != null ? request
				.getParameter("permalink") : "";
		String description = request.getParameter("description") != null ? request
				.getParameter("description") : "";
		byte itemOrder = 0;
		try {
			itemOrder = Byte.valueOf(request.getParameter("itemOrder"));
		} catch (Exception e) {
			log.error(e.toString());
		}

		String id = Function.generateId();
		itemName = Function.trimAndStripTags(itemName);
		itemName = "".equals(itemName) ? "未命名" + id : itemName;
		permalink = Function.permalink(permalink);
		permalink = "".equals(permalink) ? Function.permalink(itemName) + id : permalink;
		description = Function.trimAndStripTags(description);

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
