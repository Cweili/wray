package org.cweili.wray.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Upload;
import org.cweili.wray.util.BlogView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class UploadController extends BaseController {

	@Override
	public BlogView index(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	@RequestMapping("/upload/{id}.*")
	public BlogView index(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		Upload upload = uploadService.getUploadById(id);
		if (null != upload && upload.getUploadSize() > 0) {
			response.setContentType(Upload.TYPE.get(upload.getUploadExt()));
			response.setContentLength(upload.getUploadSize());
			try {
				OutputStream os = response.getOutputStream();
				os.write(upload.getUploadFile());
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
