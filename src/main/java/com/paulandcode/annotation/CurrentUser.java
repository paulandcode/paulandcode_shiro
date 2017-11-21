package com.paulandcode.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.paulandcode.utils.Constants;

/**
 * 在Controller的方法参数中使用此注解，该方法在映射时会注入当前登录的User对象
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午10:55:23
 */
// 可用在方法的参数上
@Target({ ElementType.PARAMETER })
// 运行时有效
@Retention(RetentionPolicy.RUNTIME)
/*
 * Documented注解表明这个注解应该被
 * javadoc工具记录.默认情况下,javadoc是不包括注解的.但如果声明注解时指定了 @Documented,则它会被
 * javadoc之类的工具处理,所以注解类型信息也会被包括在生成的文档中
 */
@Documented
public @interface CurrentUser {

	/**
	 * 当前用户在request中的名字
	 * 
	 * @return
	 */
	String value() default Constants.CURRENT_USER;

}
