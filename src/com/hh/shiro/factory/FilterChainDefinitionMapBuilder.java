package com.hh.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		
//		1). anon ���Ա���������
//    	2). authc ������֤(����¼)��ſ��ܷ��ʵ�ҳ��. 
//		3). logout �ǳ�.
//    	4). roles ��ɫ������
    	
		map.put("/img/**","anon");
		map.put("/imgs/**","anon");
		map.put("/css/**","anon");
		map.put("/frame/**","anon");
		map.put("/js/**","anon");
		map.put("/themes/**","anon");
		
		
		//��֤�����
		
		map.put("/imgCode/getImgCode","anon");
		map.put("/user/checkCode","anon");
		map.put("/user/login","anon");
		map.put("/login.jsp", "anon");
		
		
		
		
		map.put("/user/login", "anon");
		map.put("/user/logout", "logout");
//		map.put("/user.jsp", "authc,roles[user]");
//		map.put("/admin.jsp", "authc,roles[admin]");
//		map.put("/list.jsp", "user");
		
		map.put("/**", "authc");
		
		return map;
	}
	
}
