package com.paulandcode.entity;

import java.io.Serializable;

/**
 * 角色
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午10:11:29
 */
public class RoleEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 自增长主键
	 */
	private Long id;
	/**
	 * 角色名称
	 */
	private String role;
	/**
	 * 角色描述
	 */
	private String description;
	/**
	 * 拥有的资源,如11,21,31,41
	 */
	private String resourceIds;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
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
		RoleEntity other = (RoleEntity) obj;
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
		return "Role [id=" + id + ", role=" + role + ", description=" + description + ", resourceIds=" + resourceIds
				+ ", available=" + available + "]";
	}
}