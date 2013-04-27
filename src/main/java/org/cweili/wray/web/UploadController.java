package org.cweili.wray.web;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.dto.Upload;
import org.cweili.wray.util.Function;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author cweili
 * @version 2013-4-8 下午3:53:51
 * 
 */
@Controller
public class UploadController extends BaseController {

	@RequestMapping("/upload/{id}/*")
	public ResponseEntity<byte[]> upload(@PathVariable("id") String id) {
		Upload upload = uploadService.getUploadById(id);
		if (null != upload && upload.getLength() > 0) {
			HttpHeaders header = new HttpHeaders();
			String[] type = upload.getContentType().split("/");
			header.setContentType(new MediaType(type[0], type[1]));
			header.setContentLength(upload.getLength());
			header.set("Content-Disposition",
					"attachment; filename=" + Function.urlEncode(upload.getFilename()));
			header.set("Accept-Ranges", "bytes");

			return new ResponseEntity<byte[]>(upload.getContent(), header, HttpStatus.OK);

		}
		return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/upload-json", method = RequestMethod.POST)
	public @ResponseBody
	String uploadJson(MultipartHttpServletRequest request) {
		String authorityToken = StringUtils.stripToEmpty(request.getParameter("a"));
		String authorityTime = StringUtils.stripToEmpty(request.getParameter("t"));
		if (!authorityToken.equals(Function.authorityToken(authorityTime,
				blogConfig.get("adminName"), blogConfig.get("adminPwd")))) {
			return multipartErrorMessage("没有权限");
		}

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
}
