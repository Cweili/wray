/**
 * 
 */
package org.cweili.wray.web;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.cweili.wray.util.Captcha;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author cweili
 * @version 2012-9-5 下午9:31:12
 * 
 */
@Controller
@Scope("prototype")
public final class CaptchaController extends BaseController {

	@RequestMapping("/captcha")
	public @ResponseBody
	BufferedImage captcha(WebRequest request) {

		int width = 200;
		int height = 50;

		try {
			width = Integer.valueOf(request.getParameter("width"));
		} catch (Exception e) {
			log.error(e);
		}
		try {
			height = Integer.valueOf(request.getParameter("height"));
		} catch (Exception e) {
			log.error(e);
		}
		String captcha = Captcha.getRandomString(6);
		request.setAttribute("captcha", captcha, WebRequest.SCOPE_SESSION);

		try {
			return Captcha.out(captcha, width, height);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
}
