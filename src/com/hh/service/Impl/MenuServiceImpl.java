package com.hh.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.dao.MenuDao;
import com.hh.entity.Menu;
import com.hh.service.MenuService;





@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuDao menuDao;

	@Override
	public List<Menu> getAll() {
		return menuDao.selectAll();
	}
   

}