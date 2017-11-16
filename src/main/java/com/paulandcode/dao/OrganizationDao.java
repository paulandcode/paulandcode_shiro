package com.paulandcode.dao;

import java.util.List;

import com.paulandcode.entity.OrganizationEntity;
import com.paulandcode.utils.Query;

/**
 * @author 黄建峰
 * @date 2017年10月18日 上午10:40:17
 */
public interface OrganizationDao {

	/**
	 * 创建机构
	 * 
	 * @param organization
	 * @return
	 */
	void insert(OrganizationEntity organization);

	/**
	 * 更新机构本身
	 * 
	 * @param organization
	 * @return
	 */
	void updateSelf(OrganizationEntity organization);

	/**
	 * 更新子机构
	 * 
	 * @param query
	 * @return
	 */
	void updateChildren(Query query);

	/**
	 * 删除机构
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 根据id查找机构
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
	 * 只将源头机构自己移动到目标机构下
	 * 
	 * @param moveSelfQuery
	 */
	void moveSelf(Query moveSelfQuery);

	/**
	 * 将源机构下的所有子机构移动到目标机构下
	 * 
	 * @param moveOthersQuery
	 */
	void moveChildren(Query moveOthersQuery);
}
