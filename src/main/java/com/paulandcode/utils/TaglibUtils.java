package com.paulandcode.utils;

import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.paulandcode.entity.OrganizationEntity;
import com.paulandcode.entity.ResourceEntity;
import com.paulandcode.entity.RoleEntity;
import com.paulandcode.service.OrganizationService;
import com.paulandcode.service.ResourceService;
import com.paulandcode.service.RoleService;

/**
 * fnc.tld标签的工具类
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午11:29:46
 */
public class TaglibUtils {

	private static OrganizationService organizationService;
	private static RoleService roleService;
	private static ResourceService resourceService;

	public static boolean in(String resourceIds, String resourceId) {
		if(StringUtils.isBlank(resourceIds) || StringUtils.isBlank(resourceId)) {
			return false;
		}
		String[] resourceIdArray = resourceIds.split(",");
		for(String subResourceId : resourceIdArray) {
			if(resourceId.equals(subResourceId)) {
				return true;
			}
		}
		return false;
	}

	public static String organizationName(Long organizationId) {
		OrganizationEntity organization = getOrganizationService().queryObject(organizationId);
		if (organization == null) {
			return "";
		}
		return organization.getName();
	}

	public static String organizationNames(Collection<Long> organizationIds) {
		if (CollectionUtils.isEmpty(organizationIds)) {
			return "";
		}

		StringBuilder s = new StringBuilder();
		for (Long organizationId : organizationIds) {
			OrganizationEntity organization = getOrganizationService().queryObject(organizationId);
			if (organization == null) {
				return "";
			}
			s.append(organization.getName());
			s.append(",");
		}

		if (s.length() > 0) {
			s.deleteCharAt(s.length() - 1);
		}

		return s.toString();
	}

	public static String roleName(Long roleId) {
		RoleEntity role = getRoleService().queryObject(roleId);
		if (role == null) {
			return "";
		}
		return role.getDescription();
	}

	public static String roleNames(String roleIdJsonString) {
		if (StringUtils.isBlank(roleIdJsonString)) {
			return "";
		}
		
		HashSet<Long> roleIds = new HashSet<Long>();
		String[] roleIdStrings = roleIdJsonString.split(",");
		for(String roleIdString : roleIdStrings) {
			roleIds.add(Long.valueOf(roleIdString));
		}
		
		StringBuilder s = new StringBuilder();
		for (Long roleId : roleIds) {
			RoleEntity role = getRoleService().queryObject(roleId);
			if (role == null) {
				return "";
			}
			s.append(role.getDescription());
			s.append(",");
		}

		if (s.length() > 0) {
			s.deleteCharAt(s.length() - 1);
		}

		return s.toString();
	}

	public static String resourceName(Long resourceId) {
		ResourceEntity resource = getResourceService().queryObject(resourceId);
		if (resource == null) {
			return "";
		}
		return resource.getName();
	}

	public static String resourceNames(String resourceIdJsonString) {
		if (StringUtils.isBlank(resourceIdJsonString)) {
			return "";
		}
		
		HashSet<Long> resourceIds = new HashSet<Long>();
		String[] resourceIdStrings = resourceIdJsonString.split(",");
		for(String resourceIdString : resourceIdStrings) {
			resourceIds.add(Long.valueOf(resourceIdString));
		}

		StringBuilder s = new StringBuilder();
		for (Long resourceId : resourceIds) {
			ResourceEntity resource = getResourceService().queryObject(resourceId);
			if (resource == null) {
				return "";
			}
			s.append(resource.getName());
			s.append(",");
		}

		if (s.length() > 0) {
			s.deleteCharAt(s.length() - 1);
		}

		return s.toString();
	}

	public static OrganizationService getOrganizationService() {
		if (organizationService == null) {
			organizationService = SpringUtils.getBean(OrganizationService.class);
		}
		return organizationService;
	}

	public static RoleService getRoleService() {
		if (roleService == null) {
			roleService = SpringUtils.getBean(RoleService.class);
		}
		return roleService;
	}

	public static ResourceService getResourceService() {
		if (resourceService == null) {
			resourceService = SpringUtils.getBean(ResourceService.class);
		}
		return resourceService;
	}
}
