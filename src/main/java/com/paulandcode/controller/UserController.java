package com.paulandcode.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paulandcode.entity.UserEntity;
import com.paulandcode.service.OrganizationService;
import com.paulandcode.service.RoleService;
import com.paulandcode.service.UserService;

/**
 * @author 黄建峰
 * @date 2017年10月18日 上午11:04:54
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private RoleService roleService;

	@RequiresPermissions("user:view")
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("userList", userService.queryList());
		return "user/list";
	}

	@RequiresPermissions("user:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateForm(Model model) {
		setCommonData(model);
		model.addAttribute("user", new UserEntity());
		model.addAttribute("op", "新增");
		return "user/edit";
	}

	@RequiresPermissions("user:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(UserEntity user, RedirectAttributes redirectAttributes) {
		userService.insert(user);
		redirectAttributes.addFlashAttribute("msg", "新增成功");
		return "redirect:/user";
	}

	@RequiresPermissions("user:update")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		setCommonData(model);
		model.addAttribute("user", userService.queryObject(id));
		model.addAttribute("op", "修改");
		return "user/edit";
	}

	@RequiresPermissions("user:update")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(UserEntity user, RedirectAttributes redirectAttributes) {
		userService.update(user);
		redirectAttributes.addFlashAttribute("msg", "修改成功");
		return "redirect:/user";
	}

	@RequiresPermissions("user:delete")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String showDeleteForm(@PathVariable("id") Long id, Model model) {
		setCommonData(model);
		model.addAttribute("user", userService.queryObject(id));
		model.addAttribute("op", "删除");
		return "user/edit";
	}

	@RequiresPermissions("user:delete")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		userService.delete(id);
		redirectAttributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/user";
	}

	@RequiresPermissions("user:update")
	@RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
	public String showChangePasswordForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userService.queryObject(id));
		model.addAttribute("op", "修改密码");
		return "user/changePassword";
	}

	@RequiresPermissions("user:update")
	@RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
	public String changePassword(@PathVariable("id") Long id, String newPassword,
			RedirectAttributes redirectAttributes) {
		userService.changePassword(id, newPassword);
		redirectAttributes.addFlashAttribute("msg", "修改密码成功");
		return "redirect:/user";
	}

	private void setCommonData(Model model) {
		model.addAttribute("organizationList", organizationService.queryList());
		model.addAttribute("roleList", roleService.queryList());
	}
}
