package com.paulandcode.entity;

import java.io.Serializable;

/**
 * 机构
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午10:09:34
 */
public class OrganizationEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 自增长主键
	 */
	private Long id;
	/**
	 * 机构名称
	 */
	private String name;
	/**
	 * 父编号
	 */
	private Long parentId;
	/**
	 * 父编号列表，如1/2/
	 */
	private String parentIds;
	/**
	 * 是否可用, 0:不可用, 1:可用, 默认1
	 */
	private Boolean available = Boolean.TRUE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public boolean isRootNode() {
		return parentId == 0;
	}

	public String makeSelfAsParentIds() {
		return getParentIds() + getId() + "/";
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
		OrganizationEntity other = (OrganizationEntity) obj;
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
		return "Organization [id=" + id + ", name=" + name + ", parentId=" + parentId + ", parentIds=" + parentIds
				+ ", available=" + available + "]";
	}
}