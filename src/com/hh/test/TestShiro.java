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
		//������ȫ����������
		IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//������ȫ����������   ��֤  ��Ȩ
		SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
		//������ȫ������
		SecurityUtils.setSecurityManager(securityManager);
		
		//��ȡ����
		Subject subject = SecurityUtils.getSubject();
		//��֤
		AuthenticationToken token = new UsernamePasswordToken("zhangsan", "123456");
		
		
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
