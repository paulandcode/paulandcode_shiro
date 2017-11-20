package com.paulandcode.entity;

import java.io.Serializable;

/**
 * 用户
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午10:14:33
 */
public class UserEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 自增长主键
	 */
	private Long id;
	/**
	 * 机构ID
	 */
	private Long organizationId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 加密密码的盐
	 */
	private String salt;
	/**
	 * 拥有的角色列表,如5,7
	 */
	private String roleIds;
	/**
	 * 账号是否锁定 0:未锁定 1:已锁定 默认0
	 */
	private Boolean locked = Boolean.FALSE;
	/**
	 * 是否可用 0:不可用 1:可用 默认1
	 */
	private Boolean available = Boolean.TRUE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getCredentialsSalt() {
		return username + salt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UserEntity other = (UserEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", organizationId=" + organizationId + ", username=" + username + ", password="
				+ password + ", salt=" + salt + ", roleIds=" + roleIds + ", locked=" + locked + ", available="
				+ available + "]";
	}

}