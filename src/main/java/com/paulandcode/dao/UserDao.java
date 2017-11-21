package com.paulandcode.dao;

import java.util.List;

import com.paulandcode.entity.UserEntity;

/**
 * 用户
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午10:41:34
 */
public interface UserDao {

	/**
	 * 创建用户
	 * 
	 * @param user
	 * @return
	 */
	void insert(UserEntity user);

	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return
	 */
	void update(UserEntity user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 根据用户ID查找用户
	 * 
	 * @param id
	 * @return
	 */
	UserEntity queryObject(Long id);

	/**
	 * 查找所有用户
	 * 
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

}
