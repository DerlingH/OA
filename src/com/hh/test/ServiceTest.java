package com.hh.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hh.entity.Menu;
import com.hh.entity.User;
import com.hh.entity.UserQuery;
import com.hh.service.MenuService;
import com.hh.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class ServiceTest {
	@Autowired
	public UserService userService;
	
	@Autowired
	public MenuService msi; 
	
	@Test
	public void test1(){
		List<User> list = userService.selectUser();
		System.out.println(list);
	}
	@Test
	public void test2(){
		System.out.println(userService);
	}
	
	@Test
	public void test3(){
		List<Menu> list = msi.getAll();
		System.out.println(list);
	}
	
	@Test
	public void test4(){
		String username = "666@qq.com";
		User u = new User(null, null, null, null, username, null, null, null, null);
		User user = userService.selectUserByObj(u);
		System.out.println(user);
	}
	
	//ģ����ѯ�û�
	@Test
	public void test5(){
		UserQuery uq = new UserQuery("��", null);
		List<User> list = userService.selectUserByUQ(uq);
		System.out.println(list);
	}
	
	//ʱ��
	@Test
	public void test6(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
		
	}

}
