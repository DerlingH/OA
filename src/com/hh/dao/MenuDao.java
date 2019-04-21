package com.hh.dao;

import java.util.List;

import com.hh.entity.Menu;

public interface MenuDao{
	
	//查询所有的菜单
	List<Menu> selectAll();
}