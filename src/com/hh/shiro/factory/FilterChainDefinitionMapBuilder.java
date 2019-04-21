package com.hh.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		
//		1). anon 可以被匿名访问
//    	2). authc 必须认证(即登录)后才可能访问的页面. 
//		3). logout 登出.
//    	4). roles 角色过滤器
    	
		map.put("/img/**","anon");
		map.put("/imgs/**","anon");
		map.put("/css/**","anon");
		map.put("/frame/**","anon");
		map.put("/js/**","anon");
		map.put("/themes/**","anon");
		
		
		//验证码相关
		
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
