package com.paulandcode.service;

import java.util.List;

import com.paulandcode.entity.OrganizationEntity;

/**
 * @author 黄建峰
 * @date 2017年10月18日 上午10:38:25
 */
public interface OrganizationService {

	/**
	 * 创建机构
	 * 
	 * @param organization
	 * @return
	 */
	void insert(OrganizationEntity organization);

	/**
	 * 更新机构
	 * 
	 * @param organization
	 * @return
	 */
	void update(OrganizationEntity organization);

	/**
	 * 删除机构
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 根据机构ID查找机构
	 * 
	 * @param id
	 * @return
	 */
	OrganizationEntity queryObject(Long id);

	/**
	 * 查找所有机构
	 * 
	 * @return
	 */
	List<OrganizationEntity> queryList();

	/**
	 * 查找除已给机构(包括此机构的所有子机构)的所有机构
	 * 
	 * @param id
	 * @return
	 */
	List<OrganizationEntity> queryByExcludeId(Long id);

	/**
	 * 将源机构及其下所有子机构移动到目标机构下
	 * 
	 * @param source
	 * @param target
	 */
	void move(OrganizationEntity source, OrganizationEntity target);
}
