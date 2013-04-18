package org.cweili.wray.web.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.Upload;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.cweili.wray.web.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author cweili
 * @version 2013-3-13 下午2:21:39
 * 
 */
@Controller
@Scope("prototype")
public final class AdminUploadController extends BaseController {

	@RequestMapping(value = "/admin-upload-json", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody
	String uploadJson(MultipartHttpServletRequest request) {

		Map<String, MultipartFile> fileMap = request.getFileMap();

		for (String filename : fileMap.keySet()) {
			MultipartFile file = fileMap.get(filename);

			filename = file.getOriginalFilename();

			// 检查文件大小 小于256MB
			if (file.getSize() > 268435456) {
				throw new MaxUploadSizeExceededException(file.getSize());
			}

			// 检查扩展名
			String[] filenameSplit = StringUtils.split(filename, '.');
			if (filenameSplit.length < 2) {
				return multipartErrorMessage("上传文件类型不被允许。");
			}
			String fileExt = filenameSplit[1].toLowerCase();
			if (!Upload.TYPE.containsKey(fileExt)) {
				return multipartErrorMessage("上传文件类型不被允许。");
			}

			String id = Function.generateId();
			try {
				byte[] content = file.getBytes();
				uploadService.save(new Upload(id, filename, Upload.TYPE.get(fileExt), content));
			} catch (IOException e) {
				return multipartErrorMessage("文件保存错误");
			}

			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url",
					blogConfig.get("staticServePath") + "upload/" + id + "/"
							+ Function.permalink(filenameSplit[0]) + "." + fileExt);
			obj.put("fileName", filename);
			return obj.toString();
		}

		return multipartErrorMessage("没有上传的文件");
	}

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
		List<Upload> uploads = uploadService.getUploads(page, Constant.ADMIN_LIST_SIZE);
		v.add("uploads", uploads);
		addPaginator(v, uploadService.getCount(), page, Constant.ADMIN_LIST_SIZE);

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
