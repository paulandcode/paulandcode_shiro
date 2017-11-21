package com.paulandcode.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulandcode.dao.RoleDao;
import com.paulandcode.entity.RoleEntity;
import com.paulandcode.service.ResourceService;
import com.paulandcode.service.RoleService;

/**
 * 角色
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午10:45:46
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ResourceService resourceService;

	@Override
	public void insert(RoleEntity role) {
		roleDao.insert(role);
	}

	@Override
	public void update(RoleEntity role) {
		roleDao.update(role);
	}

	@Override
	public void delete(Long id) {
		roleDao.delete(id);
	}

	@Override
	public RoleEntity queryObject(Long id) {
		return roleDao.queryObject(id);
	}

	@Override
	public List<RoleEntity> queryList() {
		return roleDao.queryList();
	}

	@Override
	public Set<String> getRoles(Long... roleIds) {
		Set<String> roles = new HashSet<String>();
		for (Long roleId : roleIds) {
			RoleEntity role = queryObject(roleId);
			if (role != null) {
				roles.add(role.getRole());
			}
		}
		return roles;
	}

	@Override
	public Set<String> getPermissions(Long[] roleIds) {
		Set<Long> resourceIds = new HashSet<Long>();
		for (Long roleId : roleIds) {
			RoleEntity role = queryObject(roleId);
			if (role != null) {
				String resourceIdStrings = role.getResourceIds();
				if (StringUtils.isBlank(resourceIdStrings)) {
					continue;
				}
				String[] resourceIdStringss = resourceIdStrings.split(",");
				for (String resourceIdString : resourceIdStringss) {
					resourceIds.add(Long.valueOf(resourceIdString));
				}
			}
		}
		return resourceService.queryPermissions(resourceIds);
	}
}
