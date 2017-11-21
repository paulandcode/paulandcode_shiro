package com.paulandcode.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.paulandcode.dao.ResourceDao;
import com.paulandcode.entity.ResourceEntity;
import com.paulandcode.service.ResourceService;
import com.paulandcode.utils.Query;

/**
 * 资源
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午10:44:59
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	@Override
	public void insert(ResourceEntity resource) {
		resourceDao.insert(resource);
	}

	@Override
	public void update(ResourceEntity resource) {
		String oldParentIds = resourceDao.queryObject(resource.getId()).getParentIds();
		resourceDao.updateSelf(resource);
		if (null != resource.getParentIds()) {
			Query query = new Query();
			query.put("oldParentIds", oldParentIds);
			query.put("newParentIds", resource.getParentIds());
			resourceDao.updateChildren(query);
		}
	}

	@Override
	public void delete(Long id) {
		resourceDao.delete(id);
	}

	@Override
	public ResourceEntity queryObject(Long id) {
		return resourceDao.queryObject(id);
	}

	@Override
	public List<ResourceEntity> queryList() {
		return resourceDao.queryList();
	}

	@Override
	public Set<String> queryPermissions(Set<Long> resourceIds) {
		Set<String> permissions = new HashSet<String>();
		for (Long resourceId : resourceIds) {
			ResourceEntity resource = queryObject(resourceId);
			if (resource != null && !StringUtils.isEmpty(resource.getPermission())) {
				permissions.add(resource.getPermission());
			}
		}
		return permissions;
	}

	@Override
	public List<ResourceEntity> getMenus(Set<String> permissions) {
		List<ResourceEntity> allResources = queryList();
		List<ResourceEntity> menus = new ArrayList<ResourceEntity>();
		for (ResourceEntity resource : allResources) {
			if (resource.isRootNode()) {
				continue;
			}
			if (resource.getType() != ResourceEntity.ResourceType.menu) {
				continue;
			}
			if (!hasPermission(permissions, resource)) {
				continue;
			}
			menus.add(resource);
		}
		return menus;
	}

	private boolean hasPermission(Set<String> permissions, ResourceEntity resource) {
		if (StringUtils.isEmpty(resource.getPermission())) {
			return true;
		}
		for (String permission : permissions) {
			WildcardPermission p1 = new WildcardPermission(permission);
			WildcardPermission p2 = new WildcardPermission(resource.getPermission());
			if (p1.implies(p2) || p2.implies(p1)) {
				return true;
			}
		}
		return false;
	}
}
