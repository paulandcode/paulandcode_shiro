package com.paulandcode.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.paulandcode.entity.UserEntity;

/**
 * 密码加密工具类
 * 
 * @author 黄建峰
 * @date 2017年10月18日 上午10:33:49
 */
@Service
public class PasswordHelper {

	/**
	 *  随机数生成器
	 */
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	/**
	 *  散列算法
	 *  优先从配置文件中获取
	 */
	@Value("${password.algorithmName}") 
	private String algorithmName = "md5";
	/**
	 *  散列迭代次数
	 *  优先从配置文件中获取
	 */
	@Value("${password.hashIterations}") 
	private int hashIterations = 2;

	public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	public void encryptPassword(UserEntity user) {

		user.setSalt(randomNumberGenerator.nextBytes().toHex());

		// 16进制字符串加密用toHex()方法，base64加密用toBase64()方法
		String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();

		user.setPassword(newPassword);
	}
}
