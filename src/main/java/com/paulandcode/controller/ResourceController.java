package com.paulandcode.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paulandcode.entity.ResourceEntity;
import com.paulandcode.service.ResourceService;

/**
 * @author 黄建峰
 * @date 2017年10月18日 上午11:04:20
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	// @ModelAttribute这个注解可以用在方法参数上,或是方法上
	// 	(1)用在方法上:被@ModelAttribute注解的方法会在每一个被@RequestMapping注解的controller方法之前执行
	// 		1>若方法上注解有value值,例:@ModelAttribute("types"),则要有返回类型,例:(ResourceEntity.ResourceType[]),不需要通过model.addAttribute()方法来添加属性,jsp页面通过${types}取值
	// 		2>若方法上注解无value值,例:@ModelAttribute,且无返回值类型,则要通过model.addAttribute()方法来添加属性,例model.addAttribute("types",
	// 		  ResourceEntity.ResourceType.values()),jsp页面通过${types}取值
	// 		3>若方法上注解无value值,但是有返回值类型,例:ResourceEntity.ResourceType[],则也不需要通过model.addAttribute()方法来添加属性,jsp页面通过${resourceTypeList}取值
	// 	(2)用在方法参数上:一个用在方法参数上的@ModelAttribute注解指示了参数应该从模型（这里所说的“模型”指
	// 	   Model）中获取。如果模型中不存在，参数会首先被实例化，然后添加到模型中。一旦模型中存在，这个参数的字段会被所有的名字匹配的请求参数所填充。这在
	// 	   Spring MVC 中叫做数据绑定，它能够把你从要对每一个字段进行类型转换的繁重体力劳动中解救出来，是非常有用的机制。
	@ModelAttribute("types")
	public ResourceEntity.ResourceType[] resourceTypes() {
		return ResourceEntity.ResourceType.values();
	}

	@RequiresPermissions("resource:view")
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("resourceList", resourceService.queryList());
		return "resource/list";
	}

	@RequiresPermissions("resource:create")
	@RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
	public String showAppendChildForm(@PathVariable("parentId") Long parentId, Model model) {
		ResourceEntity parent = resourceService.queryObject(parentId);
		model.addAttribute("parent", parent);
		ResourceEntity child = new ResourceEntity();
		child.setParentId(parentId);
		child.setParentIds(parent.makeSelfAsParentIds());
		model.addAttribute("resource", child);
		model.addAttribute("op", "新增子节点");
		return "resource/edit";
	}

	@RequiresPermissions("resource:create")
	@RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.POST)
	public String create(ResourceEntity resource, RedirectAttributes redirectAttributes) {
		resourceService.insert(resource);
		redirectAttributes.addFlashAttribute("msg", "新增子节点成功");
		return "redirect:/resource";
	}

	@RequiresPermissions("resource:update")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("resource", resourceService.queryObject(id));
		model.addAttribute("op", "修改");
		return "resource/edit";
	}

	@RequiresPermissions("resource:update")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(ResourceEntity resource, RedirectAttributes redirectAttributes) {
		resourceService.update(resource);
		redirectAttributes.addFlashAttribute("msg", "修改成功");
		return "redirect:/resource";
	}

	@RequiresPermissions("resource:delete")
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		resourceService.delete(id);
		redirectAttributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/resource";
	}

}
