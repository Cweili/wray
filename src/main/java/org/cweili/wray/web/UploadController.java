package org.cweili.wray.web;

import org.cweili.wray.domain.Upload;
import org.cweili.wray.util.Function;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author cweili
 * @version 2013-4-8 下午3:53:51
 * 
 */
@Controller
@Scope("prototype")
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

}
