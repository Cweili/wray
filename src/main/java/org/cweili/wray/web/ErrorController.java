package org.cweili.wray.web;

import org.cweili.wray.domain.BlogView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 错误 Controller
 * 
 * @author Cweili
 * @version 2012-9-5 下午9:31:12
 * 
 */
@Controller
public final class ErrorController extends BaseController {

	@RequestMapping(value = "/error")
	public BlogView error() {
		return new BlogView("error");
	}

	@RequestMapping(value = "/error/MaxUploadSizeExceeded", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String maxUploadSizeExceede() {
		return multipartErrorMessage("上传文件大小必须小于256MB。");
	}

}
