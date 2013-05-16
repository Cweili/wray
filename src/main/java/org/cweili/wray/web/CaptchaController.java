package org.cweili.wray.web;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.cweili.wray.util.Captcha;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * 验证码 Controller
 * 
 * @author Cweili
 * @version 2012-9-5 下午9:31:12
 * 
 */
@Controller
public final class CaptchaController extends BaseController {

	@RequestMapping(value = "/captcha", produces = "image/gif")
	public ResponseEntity<BufferedImage> captcha(WebRequest request) {

		int width = Function.defaultInteger(request.getParameter("width"), 200);
		int height = Function.defaultInteger(request.getParameter("height"), 50);

		String captcha = Captcha.getRandomString(6);
		request.setAttribute(Constant.CAPTCHA, captcha, WebRequest.SCOPE_SESSION);

		BufferedImage bi;
		try {
			bi = Captcha.out(captcha, width, height);
		} catch (IOException e) {
			log.error(e);
			return new ResponseEntity<BufferedImage>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		HttpHeaders header = new HttpHeaders();
		header.setCacheControl("no-cache");
		header.setPragma("no-cache");
		header.setExpires(0);

		return new ResponseEntity<BufferedImage>(bi, header, HttpStatus.OK);
	}
}
