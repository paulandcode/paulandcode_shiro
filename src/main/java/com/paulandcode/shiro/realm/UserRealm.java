package com.paulandcode.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.kaptcha.Constants;
import com.paulandcode.entity.UserEntity;
import com.paulandcode.service.UserService;
import com.paulandcode.shiro.authc.CaptchaException;
import com.paulandcode.utils.ShiroUtils;

/**
 * 自定义Realm
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午10:49:44
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 授权(验证权限时调用)(若缓存中无AuthorizationInfo权限,则执行该方法)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = ((UserEntity) principals.getPrimaryPrincipal()).getUsername();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.getRoles(username));
		authorizationInfo.setStringPermissions(userService.getPermissions(username));
		return authorizationInfo;
	}

	/**
	 * 授权(验证权限时调用)(若缓存中有AuthorizationInfo权限,则执行该方法)
	 */
	@Override
	protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
		String username = ((UserEntity) principals.getPrimaryPrincipal()).getUsername();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.getRoles(username));
		authorizationInfo.setStringPermissions(userService.getPermissions(username));
		return authorizationInfo;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 系统生成的验证码
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		// 用户填写的验证码
		String captcha = ShiroUtils.getServletRequest().getParameter("captcha");
		if (!captcha.equalsIgnoreCase(kaptcha)) {
			// 验证码错误
			throw new CaptchaException();
		}
		
		UserEntity user = userService.queryByUsername((String) token.getPrincipal());
		if (user == null) {
			// 帐号不存在
			throw new UnknownAccountException();
		}
		if (Boolean.TRUE.equals(user.getLocked())) {
			// 帐号被锁定
			throw new LockedAccountException();
		}
		if(null == token.getCredentials()) {
			// 密码为空,则视为密码不正确
			throw new IncorrectCredentialsException();
		}

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		// 用户名，密码，salt=username+salt，realm name
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()), getName());

		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
