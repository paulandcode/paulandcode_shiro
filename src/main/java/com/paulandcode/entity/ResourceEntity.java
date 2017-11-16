package com.paulandcode.entity;

import java.io.Serializable;

/**
 * 资源
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午10:10:53
 */
public class ResourceEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 自增长主键
	 */
	private Long id;
	/**
	 * 资源名称
	 */
	private String name;
	/**
	 * 资源类型
	 */
	private ResourceType type = ResourceType.menu;
	/**
	 * 资源路径
	 */
	private String url;
	/**
	 * 权限字符串
	 */
	private String permission;
	/**
	 * 父编号
	 */
	private Long parentId;
	/**
	 * 父编号列表
	 */
	private String parentIds;
	/**
	 * 是否可用 0:不可用 1:可用 默认1
	 */
	private Boolean available = Boolean.TRUE;

	public static enum ResourceType {
		// 菜单和按钮
		menu("菜单"), button("按钮");

		private final String info;

		private ResourceType(String info) {
			this.info = info;
		}

		public String getInfo() {
			return info;
		}
	}

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

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
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
		ResourceEntity other = (ResourceEntity) obj;
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
		return "Resource [id=" + id + ", name=" + name + ", type=" + type + ", url=" + url + ", permission="
				+ permission + ", parentId=" + parentId + ", parentIds=" + parentIds + ", available=" + available + "]";
	}
}