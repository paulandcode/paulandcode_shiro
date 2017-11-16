package com.paulandcode.service;

import java.util.List;
import java.util.Set;

import com.paulandcode.entity.RoleEntity;

/**
 * @author 黄建峰
 * @date 2017年10月18日 上午10:38:53
 */
public interface RoleService {

	/**
	 * 创建角色
	 * 
	 * @param role
	 * @return
	 */
	void insert(RoleEntity role);

	/**
	 * 更新角色
	 * 
	 * @param role
	 * @return
	 */
	void update(RoleEntity role);

	/**
	 * 删除角色
	 * 
	 * @param roleId
	 */
	void delete(Long roleId);

	/**
	 * 根据角色ID查找角色
	 * 
	 * @param roleId
	 * @return
	 */
	RoleEntity queryObject(Long roleId);

	/**
	 * 查找所有角色
	 * 
	 * @return
	 */
	List<RoleEntity> queryList();

	/**
	 * 根据角色编号得到角色标识符列表
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<String> getRoles(Long... roleIds);

	/**
	 * 根据角色编号得到权限字符串列表
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<String> getPermissions(Long[] roleIds);
}
