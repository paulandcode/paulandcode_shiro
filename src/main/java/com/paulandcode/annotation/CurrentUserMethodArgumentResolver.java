package com.paulandcode.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.paulandcode.entity.UserEntity;
import com.paulandcode.service.UserService;

/**
 * 增加方法注入，将含有 @CurrentUser注解的方法参数注入当前登录用户
 * 
 * @author 黄建峰
 * @date 2017年11月6日 下午4:30:34
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

	// 此处没有用到该bean,因为在用户自定义过滤器UserFilter中已经存储了当前User对象
	@SuppressWarnings("unused")
	private UserService userService;

	/**
	 * 将spring-mcv.xml中<mvc:argument-resolvers>里的userService赋值给该变量
	 * 
	 * @param userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @param parameter
	 *            方法参数
	 * @return 返回false则不会进入到resolveArgument方法,返回true则会
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// Class.isAssignableFrom(Class clz)方法 与 instanceof 关键字的区别:
		// Class.isAssignableFrom()是判断一个类Class1和另一个类Class2是否相同或是另一个类的(直接或间接的)子类或(直接或间接的)实现.
		// instanceof 是判断一个对象实例是否是一个类或其(直接或间接的)子类或其(直接或间接的)实现.
		// 判断parameter与User.class的关系,以及是否带有@CurrentUser注解
		return parameter.getParameterType().isAssignableFrom(UserEntity.class)
				&& parameter.hasParameterAnnotation(CurrentUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
		return webRequest.getAttribute(currentUserAnnotation.value(), NativeWebRequest.SCOPE_REQUEST);
	}
}
