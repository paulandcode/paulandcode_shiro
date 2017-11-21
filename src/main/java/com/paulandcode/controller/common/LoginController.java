package com.paulandcode.controller.common;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.paulandcode.shiro.authc.CaptchaException;
import com.paulandcode.utils.ShiroUtils;

/**
 * 登录
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午11:03:44
 */
@Controller
public class LoginController {

	@Autowired
	private Producer producer;

	@RequestMapping("login")
	public String showLoginForm(HttpServletRequest req, Model model) {
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		// 若已经登陆成功则重定向到主页面
		if (ShiroUtils.isLogin()) {
			return "redirect:/";
		}
		String error = null;
		if (CaptchaException.class.getName().equals(exceptionClassName)) {
			error = "验证码错误";
		} else if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户不存在";
		} else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
			error = "帐号被锁定";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "密码不正确";
		} else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
			error = "登录次数过多,请稍后再试";
		} else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
		}
		model.addAttribute("error", error);
		return "common/login";
	}

	@RequestMapping("getCaptcha")
	public void getCaptcha(HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}

}