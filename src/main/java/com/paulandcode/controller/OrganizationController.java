package com.paulandcode.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paulandcode.entity.OrganizationEntity;
import com.paulandcode.service.OrganizationService;

/**
 * @author 黄建峰
 * @date 2017年10月18日 上午11:04:01
 */
@Controller
@RequestMapping("organization")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	@RequiresPermissions("organization:view")
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "organization/index";
	}

	// value为权限值,logical有AND和OR,默认为AND
	@RequiresPermissions(value = { "organization:view", "test" }, logical = Logical.OR)
	@RequestMapping(value = "tree", method = RequestMethod.GET)
	public String showTree(Model model) {
		model.addAttribute("organizationList", organizationService.queryList());
		return "organization/tree";
	}

	@RequiresPermissions("organization:create")
	// 使用@PathVariable注解,可以直接获取url中的内容
	@RequestMapping(value = "{parentId}/appendChild", method = RequestMethod.GET)
	public String showAppendChildForm(@PathVariable("parentId") Long parentId, Model model) {
		OrganizationEntity parent = organizationService.queryObject(parentId);
		model.addAttribute("parent", parent);
		OrganizationEntity child = new OrganizationEntity();
		child.setParentId(parentId);
		child.setParentIds(parent.makeSelfAsParentIds());
		model.addAttribute("child", child);
		model.addAttribute("op", "新增");
		return "organization/appendChild";
	}

	@RequiresPermissions("organization:create")
	@RequestMapping(value = "{parentId}/appendChild", method = RequestMethod.POST)
	public String create(OrganizationEntity organization) {
		organizationService.insert(organization);
		return "redirect:/organization/success";
	}

	@RequiresPermissions("organization:update")
	// 此请求为GET方式,用于数据回显(例:当前端为POST请求,而后端只有GET对应的方法时,出现错误码:405 – Method Not
	// Allowed)
	@RequestMapping(value = "{id}/maintain", method = RequestMethod.GET)
	public String showMaintainForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("organization", organizationService.queryObject(id));
		return "organization/maintain";
	}

	@RequiresPermissions("organization:update")
	// 此请求为POST方式,用于更新数据库
	@RequestMapping(value = "{id}/update", method = RequestMethod.POST)
	public String update(OrganizationEntity organization, RedirectAttributes redirectAttributes) {
		organizationService.update(organization);
		redirectAttributes.addFlashAttribute("msg", "修改成功");
		return "redirect:/organization/success";
	}

	@RequiresPermissions("organization:delete")
	@RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
	// 若是重定向,则不能用model.addAttribute()方法,而是要用redirectAttributes.addFlashAttribute()方法
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		organizationService.delete(id);
		redirectAttributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/organization/success";
	}

	@RequiresPermissions("organization:update")
	@RequestMapping(value = "{sourceId}/move", method = RequestMethod.GET)
	public String showMoveForm(@PathVariable("sourceId") Long sourceId, Model model) {
		OrganizationEntity source = organizationService.queryObject(sourceId);
		model.addAttribute("source", source);
		model.addAttribute("targetList", organizationService.queryByExcludeId(source.getId()));
		return "organization/move";
	}

	@RequiresPermissions("organization:update")
	@RequestMapping(value = "{sourceId}/move", method = RequestMethod.POST)
	public String move(@PathVariable("sourceId") Long sourceId, @RequestParam("targetId") Long targetId,
			RedirectAttributes redirectAttributes) {
		OrganizationEntity source = organizationService.queryObject(sourceId);
		OrganizationEntity target = organizationService.queryObject(targetId);
		organizationService.move(source, target);
		redirectAttributes.addFlashAttribute("msg", "移动成功");
		return "redirect:/organization/success";
	}

	@RequiresPermissions("organization:view")
	@RequestMapping(value = "success", method = RequestMethod.GET)
	public String success() {
		return "organization/success";
	}

}