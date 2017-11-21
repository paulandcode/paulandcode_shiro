package com.paulandcode.shiro.authc.credential;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * 登录尝试次数限制
 * 
 * @author 黄建峰
 * @date 2017年11月20日 下午3:09:57
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {
	/**
	 * 集群中可能会导致出现验证多过5次的现象，因为AtomicInteger只能保证单节点并发
	 */
	private Cache<String, AtomicInteger> passwordRetryCache;

	public RetryLimitCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (null == retryCount) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		int allowCount = 5;
		if (retryCount.incrementAndGet() > allowCount) {
			throw new ExcessiveAttemptsException();
		}
		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// clear retry data
			passwordRetryCache.remove(username);
		}
		return matches;
	}

}
