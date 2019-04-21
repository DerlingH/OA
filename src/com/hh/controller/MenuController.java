package com.hh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hh.entity.Menu;
import com.hh.service.MenuService;

//菜单 模块
@Service
@RequestMapping("menu")
public class MenuController {
	@Autowired
	private MenuService menuService;
	
	
	//获得所有的菜单模块
	@RequestMapping("getAllMenu")
	@ResponseBody
	public Object getAllMenu(HttpServletRequest req){
		List<Menu> menus = menuService.getAll();
		return menus;
	}
}
