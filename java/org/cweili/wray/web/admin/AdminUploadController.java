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

		int maxSize = 15728640;
		response.setContentType("text/html; charset=UTF-8");

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
				// 检查文件大小
				if (item.getSize() > maxSize) {
					v.add("content", getError("上传文件大小超过限制。"));
					return v;
				}
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

		// // 文件保存目录路径
		// String savePath =
		// request.getSession().getServletContext().getRealPath("/") +
		// Constant.UPLOAD_PATH;
		//
		// // 文件保存目录URL
		// String saveUrl = request.getContextPath() + "/" +
		// Constant.UPLOAD_PATH;
		//
		// // 定义允许上传的文件扩展名
		// HashMap<String, String> extMap = new HashMap<String, String>();
		// extMap.put("image", "gif,jpg,jpeg,png,bmp");
		// extMap.put("flash", "swf");
		// extMap.put("media",
		// "mp3,wav,wma,ogg,m4a,aac,mid,avi,mpg,asf,wmv,rm,rmvb,mp4");
		// extMap.put("file",
		// "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,rar,gz,bz2,7z,xz");
		//
		// // 最大文件大小
		// // long maxSize = 20000000;
		//
		// response.setContentType("text/html; charset=UTF-8");
		//
		// if (!ServletFileUpload.isMultipartContent(request)) {
		// v.add("content", getError("请选择文件。"));
		// return v;
		// }
		// // 检查目录
		// File uploadDir = new File(savePath);
		// if (!uploadDir.isDirectory()) {
		// v.add("content", getError("上传目录不存在。"));
		// return v;
		// }
		// // 检查目录写权限
		// if (!uploadDir.canWrite()) {
		// v.add("content", getError("上传目录没有写权限。"));
		// return v;
		// }
		//
		// String dirName = request.getParameter("dir");
		// if (dirName == null) {
		// dirName = "image";
		// }
		// if (!extMap.containsKey(dirName)) {
		// v.add("content", getError("目录名不正确。"));
		// return v;
		// }
		// // 创建文件夹
		// savePath += dirName + "/";
		// saveUrl += dirName + "/";
		// File saveDirFile = new File(savePath);
		// if (!saveDirFile.exists()) {
		// saveDirFile.mkdirs();
		// }
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		// String ymd = sdf.format(new Date());
		// savePath += ymd + "/";
		// saveUrl += ymd + "/";
		// File dirFile = new File(savePath);
		// if (!dirFile.exists()) {
		// dirFile.mkdirs();
		// }
		//
		// FileItemFactory factory = new DiskFileItemFactory();
		// ServletFileUpload upload = new ServletFileUpload(factory);
		// upload.setHeaderEncoding("UTF-8");
		// List items = new ArrayList();
		// try {
		// items = upload.parseRequest(request);
		// } catch (FileUploadException e1) {
		// e1.printStackTrace();
		// }
		// Iterator itr = items.iterator();
		// while (itr.hasNext()) {
		// FileItem item = (FileItem) itr.next();
		// String fileName = item.getName();
		// long fileSize = item.getSize();
		// if (!item.isFormField()) {
		// // 检查文件大小
		// if (item.getSize() > maxSize) {
		// v.add("content", getError("上传文件大小超过限制。"));
		// return v;
		// }
		// // 检查扩展名
		// String fileExt = fileName.substring(fileName.lastIndexOf(".") +
		// 1).toLowerCase();
		// if (!Arrays.<String>
		// asList(extMap.get(dirName).split(",")).contains(fileExt)) {
		// v.add("content", getError("上传文件扩展名是不允许的扩展名。\n只允许" +
		// extMap.get(dirName) + "格式。"));
		// return v;
		// }
		//
		// SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
		// String newFileName = df.format(new Date()) + "_" + new
		// Random().nextInt(1000) + "." + fileExt;
		// try {
		// File uploadedFile = new File(savePath, newFileName);
		// item.write(uploadedFile);
		// } catch (Exception e) {
		// v.add("content", getError("上传文件失败。"));
		// return v;
		// }
		//
		// JSONObject obj = new JSONObject();
		// obj.put("error", 0);
		// obj.put("url", saveUrl + newFileName);
		// obj.put("fileName", fileName);
		// v.add("content", obj.toJSONString());
		// }
		// }
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
