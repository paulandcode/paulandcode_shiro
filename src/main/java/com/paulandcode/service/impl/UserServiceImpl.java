package com.paulandcode.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulandcode.dao.UserDao;
import com.paulandcode.entity.UserEntity;
import com.paulandcode.service.RoleService;
import com.paulandcode.service.UserService;
import com.paulandcode.utils.PasswordHelper;

/**
 * 用户
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午10:46:08
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordHelper passwordHelper;
	@Autowired
	private RoleService roleService;

	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	@Override
	public void insert(UserEntity user) {
		// 加密密码
		passwordHelper.encryptPassword(user);
		userDao.insert(user);
	}

	@Override
	public void update(UserEntity user) {
		userDao.update(user);
	}

	@Override
	public void delete(Long userId) {
		userDao.delete(userId);
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	@Override
	public void changePassword(Long userId, String newPassword) {
		UserEntity user = userDao.queryObject(userId);
		user.setPassword(newPassword);
		passwordHelper.encryptPassword(user);
		userDao.update(user);
	}

	@Override
	public UserEntity queryObject(Long userId) {
		return userDao.queryObject(userId);
	}

	@Override
	public List<UserEntity> queryList() {
		return userDao.queryList();
	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	@Override
	public UserEntity queryByUsername(String username) {
		return userDao.queryByUsername(username);
	}

	/**
	 * 根据用户名查找其角色
	 * 
	 * @param username
	 * @return
	 */
	@Override
	public Set<String> getRoles(String username) {
		UserEntity user = queryByUsername(username);
		if (user == null) {
			return Collections.emptySet();
		}
		String[] roleIdStrings = user.getRoleIds().split(",");
		Long[] roleIds = new Long[roleIdStrings.length];
		for (int i = 0; i < roleIdStrings.length; i++) {
			roleIds[i] = Long.valueOf(roleIdStrings[i]);
		}
		return roleService.getRoles(roleIds);
	}

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param username
	 * @return
	 */
	@Override
	public Set<String> getPermissions(String username) {
		UserEntity user = queryByUsername(username);
		if (user == null) {
			return Collections.emptySet();
		}
		String roleIdStrings = user.getRoleIds();
		if (StringUtils.isBlank(roleIdStrings)) {
			return new HashSet<String>();
		}
		String[] roleIdStringss = roleIdStrings.split(",");
		Long[] roleIds = new Long[roleIdStringss.length];
		for (int i = 0; i < roleIdStringss.length; i++) {
			roleIds[i] = Long.valueOf(roleIdStringss[i]);
		}
		return roleService.getPermissions(roleIds);
	}
}