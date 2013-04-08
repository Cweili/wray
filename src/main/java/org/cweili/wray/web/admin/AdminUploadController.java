package org.cweili.wray.web.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.cweili.wray.domain.Upload;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.web.BaseController;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author cweili
 * @version 2013-3-13 下午2:21:39
 * 
 */
@Controller
@Scope("prototype")
public final class AdminUploadController extends BaseController {

	@RequestMapping(value = "/admin/upload-json", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody
	String uploadJson(HttpServletRequest request) {

		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("请选择文件。");
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> items = new ArrayList<FileItem>();
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}
		Iterator<FileItem> itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = itr.next();
			String filename = item.getName();
			if (!item.isFormField()) {
				// 检查文件大小 小于256MB
				if (item.getSize() > 268435456) {
					return getError("上传文件大小必须小于256MB。");
				}

				// 检查扩展名
				String fileExt = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
				if (!Upload.TYPE.containsKey(fileExt)) {
					return getError("上传文件扩展名是不允许的扩展名。");
				}

				byte[] content = new byte[(int) item.getSize()];
				try {
					item.getInputStream().read(content);
					item.getInputStream().close();
				} catch (IOException e) {
					log.error("InputStream Error.", e);
					return getError("文件保存错误");
				}

				String id = Function.generateId();
				uploadService.save(new Upload(id, filename, Upload.TYPE.get(fileExt), content));

				String filenameNew = filename.substring(0, filename.lastIndexOf("."));

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url",
						request.getContextPath() + "/upload/" + id + "/"
								+ Function.permalink(filenameNew) + "." + fileExt);
				obj.put("fileName", filename);
				return obj.toString();
			}
		}

		return getError("没有上传的文件");
	}

	@RequestMapping("/admin/upload")
	public BlogView uploadManager(WebRequest request) {
		BlogView v = new BlogView("upload-list");
		v.add("actionName", "附件管理");
		int page = 1;
		try {
			page = Integer.valueOf(request.getParameter("page") == null ? "1" : request
					.getParameter("page"));
		} catch (Exception e) {
			log.error(e.toString());
		}
		List<Upload> uploads = uploadService.getUploads(page, Constant.ADMIN_LIST_SIZE);
		v.add("uploads", uploads);
		addPaginator(v, uploadService.getCount(), page, Constant.ADMIN_LIST_SIZE);

		return v;
	}

	@RequestMapping(value = "/admin/upload-delete", method = RequestMethod.POST)
	public BlogView del(WebRequest request) {

		BlogView v = new BlogView("msg");
		v.add("redirect", "admin/upload/?page=" + request.getParameter("page"));

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

	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toString();
	}

}
