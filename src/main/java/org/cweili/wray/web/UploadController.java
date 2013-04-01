package org.cweili.wray.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cweili.wray.domain.Upload;
import org.cweili.wray.util.BlogView;
import org.cweili.wray.util.Function;
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
	@RequestMapping("/upload/{id}/*")
	public BlogView index(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String id) {
		Upload upload = uploadService.getUploadById(id);
		if (null != upload && upload.getLength() > 0) {
			response.setContentType(upload.getContentType());
			response.setContentLength(upload.getLength());
			response.setHeader("Content-Disposition",
					"attachment; filename=" + Function.urlEncode(upload.getFilename()));
			response.setHeader("Accept-Ranges", "bytes");
			try {
				OutputStream os = response.getOutputStream();
				os.write(upload.getContent());
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
