package com.paulandcode.utils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;

import com.paulandcode.entity.UserEntity;

/**
 * Shiro工具类
 * 
 * @author 黄建峰
 * @date 2017年11月10日 下午2:32:41
 */
public class ShiroUtils {

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static UserEntity getUserEntity() {
		return (UserEntity) SecurityUtils.getSubject().getPrincipal();
	}

	public static Long getUserId() {
		return getUserEntity().getId();
	}

	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}

	public static String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}
	
	public static ServletRequest getServletRequest() {
		return ((WebSubject)getSubject()).getServletRequest();
	}
	
	public static ServletResponse getServletResponse() {
		return ((WebSubject)getSubject()).getServletResponse();
	}

}
