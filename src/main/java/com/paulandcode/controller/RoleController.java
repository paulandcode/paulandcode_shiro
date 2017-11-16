package com.paulandcode.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paulandcode.entity.RoleEntity;
import com.paulandcode.service.ResourceService;
import com.paulandcode.service.RoleService;

/**
 * @author 黄建峰
 * @date 2017年10月18日 上午11:04:37
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;

	@RequiresPermissions("role:view")
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("roleList", roleService.queryList());
		return "role/list";
	}

	@RequiresPermissions("role:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateForm(Model model) {
		setCommonData(model);
		model.addAttribute("role", new RoleEntity());
		model.addAttribute("op", "新增");
		return "role/edit";
	}

	@RequiresPermissions("role:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(RoleEntity role, RedirectAttributes redirectAttributes) {
		roleService.insert(role);
		redirectAttributes.addFlashAttribute("msg", "新增成功");
		return "redirect:/role";
	}

	@RequiresPermissions("role:update")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		setCommonData(model);
		model.addAttribute("role", roleService.queryObject(id));
		model.addAttribute("op", "修改");
		return "role/edit";
	}

	@RequiresPermissions("role:update")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(RoleEntity role, RedirectAttributes redirectAttributes) {
		roleService.update(role);
		redirectAttributes.addFlashAttribute("msg", "修改成功");
		return "redirect:/role";
	}

	@RequiresPermissions("role:delete")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String showDeleteForm(@PathVariable("id") Long id, Model model) {
		setCommonData(model);
		model.addAttribute("role", roleService.queryObject(id));
		model.addAttribute("op", "删除");
		return "role/edit";
	}

	@RequiresPermissions("role:delete")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		roleService.delete(id);
		redirectAttributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/role";
	}

	private void setCommonData(Model model) {
		model.addAttribute("resourceList", resourceService.queryList());
	}
}