package com.paulandcode.service;

import java.util.List;
import java.util.Set;

import com.paulandcode.entity.UserEntity;

/**
 * @author 黄建峰
 * @date 2017年10月18日 上午10:36:35
 */
public interface UserService {

	/**
	 * 创建用户
	 * @param user
	 * @return
	 */
	void insert(UserEntity user);

	/**
	 * 根据用户更新用户
	 * @param user
	 * @return
	 */
	void update(UserEntity user);

	/**
	 * 根据用户ID删除用户
	 * @param userId
	 */
	void delete(Long userId);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	void changePassword(Long userId, String newPassword);

	/**
	 * 根据用户ID查找用户
	 * @param userId
	 * @return
	 */
	UserEntity queryObject(Long userId);

	/**
	 * 查找所有用户
	 * @return
	 */
	List<UserEntity> queryList();

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	UserEntity queryByUsername(String username);

	/**
	 * 根据用户名查找其角色
	 * 
	 * @param username
	 * @return
	 */
	Set<String> getRoles(String username);

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param username
	 * @return
	 */
	Set<String> getPermissions(String username);

}
