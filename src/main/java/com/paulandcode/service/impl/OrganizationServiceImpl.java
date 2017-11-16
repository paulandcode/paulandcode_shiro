package com.paulandcode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulandcode.dao.OrganizationDao;
import com.paulandcode.entity.OrganizationEntity;
import com.paulandcode.service.OrganizationService;
import com.paulandcode.utils.Query;

/**
 * @author 黄建峰
 * @date 2017年10月18日 上午10:44:41
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationDao organizationDao;

	@Override
	public void insert(OrganizationEntity organization) {
		organizationDao.insert(organization);
	}

	@Override
	public void update(OrganizationEntity organization) {
		String oldParentIds = organizationDao.queryObject(organization.getId()).getParentIds();
		organizationDao.updateSelf(organization);
		if(null != organization.getParentIds()) {
			Query query = new Query();
			query.put("oldParentIds", oldParentIds);
			query.put("newParentIds", organization.getParentIds());
			organizationDao.updateChildren(query);
		}
	}

	@Override
	public void delete(Long id) {
		organizationDao.delete(id);
	}

	@Override
	public OrganizationEntity queryObject(Long id) {
		return organizationDao.queryObject(id);
	}

	@Override
	public List<OrganizationEntity> queryList() {
		return organizationDao.queryList();
	}

	@Override
	public List<OrganizationEntity> queryByExcludeId(Long id) {
		return organizationDao.queryByExcludeId(id);
	}

	@Override
	public void move(OrganizationEntity source, OrganizationEntity target) {
		Query moveSelfQuery = new Query();
		moveSelfQuery.put("id", source.getId());
		moveSelfQuery.put("parentId", target.getId());
		moveSelfQuery.put("parentIds", target.makeSelfAsParentIds());
		organizationDao.moveSelf(moveSelfQuery);
		
		Query moveOthersQuery = new Query();
		moveOthersQuery.put("subResultParentIds", target.makeSelfAsParentIds() + source.getId() + "/");
		moveOthersQuery.put("sourceSelfAsParentIds", source.makeSelfAsParentIds().substring(0,source.makeSelfAsParentIds().length()));
		organizationDao.moveChildren(moveOthersQuery);
	}
}