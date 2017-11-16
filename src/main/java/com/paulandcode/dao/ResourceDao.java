package com.paulandcode.dao;

import java.util.List;

import com.paulandcode.entity.ResourceEntity;
import com.paulandcode.utils.Query;

/**
 * @author 黄建峰
 * @date 2017年10月18日 上午10:40:49
 */
public interface ResourceDao {

	/**
	 * 创建资源
	 * 
	 * @param resource
	 * @return
	 */
	void insert(ResourceEntity resource);

	/**
	 * 更新资源本身
	 * 
	 * @param resource
	 */
	void updateSelf(ResourceEntity resource);

	/**
	 * 更新子资源
	 * 
	 * @param query
	 */
	void updateChildren(Query query);

	/**
	 * 删除资源
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 根据资源ID查找资源
	 * 
	 * @param id
	 * @return
	 */
	ResourceEntity queryObject(Long id);

	/**
	 * 查找所有资源
	 * 
	 * @return
	 */
	List<ResourceEntity> queryList();

}
