package com.paulandcode.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.paulandcode.annotation.CurrentUser;
import com.paulandcode.entity.UserEntity;
import com.paulandcode.service.ResourceService;
import com.paulandcode.service.UserService;

/**
 * 主页
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午11:03:15
 */
@Controller
public class IndexController {

	@Autowired
	private ResourceService resourceService;
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index(@CurrentUser UserEntity loginUser, Model model) {
		((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		model.addAttribute("menus", resourceService.getMenus(userService.getPermissions(loginUser.getUsername())));
		return "common/index";
	}

	@RequestMapping("welcome")
	public String welcome(@CurrentUser UserEntity loginUser, Model model) {
		return "common/welcome";
	}

}