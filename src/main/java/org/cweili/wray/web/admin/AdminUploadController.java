package org.cweili.wray.web.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.cweili.wray.domain.BlogView;
import org.cweili.wray.domain.Page;
import org.cweili.wray.domain.dto.Upload;
import org.cweili.wray.util.Constant;
import org.cweili.wray.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author cweili
 * @version 2013-3-13 下午2:21:39
 * 
 */
@Controller
public final class AdminUploadController extends BaseController {

	@RequestMapping("/admin-upload")
	public BlogView uploadManager(WebRequest request) {
		BlogView v = new BlogView("upload-list");
		v.add("actionName", "附件管理");
		int page = 1;
		try {
			page = Integer.valueOf(request.getParameter("page") == null ? "1" : request
					.getParameter("page"));
		} catch (Exception e) {
			log.error(e);
		}
		Page<Upload> uploads = uploadService.getUploads(page, Constant.ADMIN_LIST_SIZE);
		v.add("uploads", uploads.getContent());
		addPaginator(v, uploads);

		return v;
	}

	@RequestMapping(value = "/admin-upload-delete", method = RequestMethod.POST)
	public BlogView del(WebRequest request) {

		BlogView v = new BlogView("msg");
		v.add("redirect", "admin-upload?page=" + request.getParameter("page"));

		List<String> ids = new ArrayList<String>();
		if (request.getParameterValues("id") != null) {
			Collections.addAll(ids, request.getParameterValues("id"));
		}

		if (uploadService.remove(ids)) {
			v.add("err", "succ");
			v.add("msg", "附件删除成功");
			v.add("succ", "恭喜您，您选中的附件已成功删除。");
		} else {
			v.add("err", "数据库更新失败");
			v.add("msg", "附件删除失败");
		}

		return v;
	}

}
