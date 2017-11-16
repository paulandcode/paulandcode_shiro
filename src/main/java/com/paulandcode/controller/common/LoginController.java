package com.paulandcode.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paulandcode.utils.ShiroUtils;

/**
 * @author 黄建峰
 * @date 2017年10月18日 上午11:03:44
 */
@Controller
public class LoginController {

	@RequestMapping(value = "/login")
	public String showLoginForm(HttpServletRequest req, Model model) {
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		// 若已经登陆成功则重定向到主页面
		if(ShiroUtils.isLogin()) {
			return "redirect:/";
		}
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户不存在";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "密码错误";
		} else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
		}
		model.addAttribute("error", error);
		return "common/login";
	}

}