package org.cweili.wray.web.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.bson.types.ObjectId;
import org.cweili.wray.domain.Upload;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
import org.cweili.wray.web.BaseController;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author cweili
 * @version 2013-3-13 下午2:21:39
 * 
 */
@Controller
@Scope("prototype")
public final class AdminUploadController extends BaseController {

	@Override
	@RequestMapping(value = "/admin-upload-json", method = RequestMethod.POST)
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {

		BlogView v = new BlogView("empty");
		response.setContentType("application/json; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			v.add("content", getError("请选择文件。"));
			return v;
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = new ArrayList();
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			long fileSize = item.getSize();
			if (!item.isFormField()) {
				// 检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
				if (!Upload.TYPE.containsKey(fileExt)) {
					v.add("content", getError("上传文件扩展名是不允许的扩展名。"));
					return v;
				}

				byte[] buffer = new byte[(int) item.getSize()];
				try {
					item.getInputStream().read(buffer);
					item.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
					v.add("content", getError("文件保存错误"));
					return v;
				}

				String uploadId = new ObjectId().toString();
				uploadService.save(new Upload(uploadId, fileName, fileExt, (int) item.getSize(), buffer));

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", request.getContextPath() + "/upload/" + uploadId + "." + fileExt);
				obj.put("fileName", fileName + "." + fileExt);
				v.add("content", obj.toJSONString());
			}
		}

		return v;
	}

	@RequestMapping("/admin-upload")
	public BlogView uploadManager(HttpServletRequest request, HttpServletResponse response) {
		BlogView v = new BlogView("upload-list");
		v.add("actionName", "附件管理");
		int page = 1;
		try {
			page = Integer.valueOf(request.getParameter("page") == null ? "1" : request.getParameter("page"));
		} catch (Exception e) {
			log.error(e.toString());
		}
		List<Upload> uploads = uploadService.getUploads(page, Constant.ADMIN_LIST_SIZE);
		v.add("uploads", uploads);

		return v;
	}

	@RequestMapping(value = "/admin-upload-delete", method = RequestMethod.POST)
	public BlogView del(HttpServletRequest request, HttpServletResponse response) {

		BlogView v = new BlogView("msg");
		v.add("err", "succ");
		v.add("msg", "附件删除成功");
		v.add("redirect", "admin-upload");

		v.add("succ", "恭喜您，您选中的附件已成功删除。");

		List<String> ids = new ArrayList<String>();
		if (request.getParameterValues("id") != null) {
			for (String id : request.getParameterValues("id")) {
				try {
					ids.add(id);
				} catch (Exception e) {
					log.error(e.toString());
				}
			}
		}

		try {
			uploadService.remove(ids);
		} catch (Exception e) {
			v.add("err", "数据库更新失败");
			v.add("msg", "附件删除失败");
		}
		return v;
	}

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response, @PathVariable String str) {
		return null;
	}

	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}

}
