package com.paulandcode.dao;

import java.util.List;

import com.paulandcode.entity.RoleEntity;

/**
 * @author 黄建峰
 * @date 2017年10月18日 上午10:41:03
 */
public interface RoleDao {

	/**
	 * 创建角色
	 * @param role
	 * @return
	 */
	void insert(RoleEntity role);

	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	void update(RoleEntity role);

	/**
	 * 删除角色
	 * @param roleId
	 */
	void delete(Long roleId);

	/**
	 * 根据角色ID查找角色
	 * @param roleId
	 * @return
	 */
	RoleEntity queryObject(Long roleId);

	/**
	 * 查找所有角色
	 * @return
	 */
	List<RoleEntity> queryList();
}
