package com.paulandcode.shiro.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.paulandcode.entity.UserEntity;
import com.paulandcode.utils.Constants;

/**
 * 用户自定义过滤器
 * 
 * @author 黄建峰
 * @date 2017年10月19日 下午5:43:21
 */
public class UserFilter extends PathMatchingFilter {

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {

		UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
		request.setAttribute(Constants.CURRENT_USER, user);
		return true;
	}
}
