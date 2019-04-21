package com.hh.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class TestShiro {
	public static void main(String[] args) {
		//创建安全管理器工厂
		IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//创建安全管理器对象   认证  授权
		SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
		//依赖安全管理器
		SecurityUtils.setSecurityManager(securityManager);
		
		//获取主体
		Subject subject = SecurityUtils.getSubject();
		//认证
		AuthenticationToken token = new UsernamePasswordToken("zhangsan", "123456");
		
		
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
