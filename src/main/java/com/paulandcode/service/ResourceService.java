package com.paulandcode.service;

import java.util.List;
import java.util.Set;

import com.paulandcode.entity.ResourceEntity;

/**
 * 资源
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午10:38:33
 */
public interface ResourceService {

	/**
	 * 创建资源
	 * 
	 * @param resource
	 * @return
	 */
	void insert(ResourceEntity resource);

	/**
	 * 更新资源
	 * 
	 * @param resource
	 * @return
	 */
	void update(ResourceEntity resource);

	/**
	 * 删除资源
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 通过资源ID查找资源
	 * 
	 * @param id
	 * @return
	 */
	ResourceEntity queryObject(Long id);

	/**
	 * 查找所有资源
	 * 
	 * @return
	 */
	List<ResourceEntity> queryList();

	/**
	 * 得到资源对应的权限字符串
	 * 
	 * @param ids
	 * @return
	 */
	Set<String> queryPermissions(Set<Long> ids);

	/**
	 * 根据用户权限得到菜单
	 * 
	 * @param permissions
	 * @return
	 */
	List<ResourceEntity> getMenus(Set<String> permissions);
}
