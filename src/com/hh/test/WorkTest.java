package com.hh.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hh.entity.Dept;
import com.hh.entity.Work;
import com.hh.service.WorkService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class WorkTest {
	@Autowired
	private WorkService ws;
	
	
	
	@Test
	public void test1(){
		List<Work> list = ws.selectWork();
		System.out.println(list);
	}
	
	@Test
	public void test2(){
		List<Work> list = ws.selectByuserId(1);
		System.out.println(list);
	}
	
	@Test
	public void test3(){
		List<Work> list = ws.selectAllWork();
		System.out.println(list);
	}
	
	
	//测试添加工作
	@Test
	public void test4(){
		Work work = new Work();
		Dept dept = new Dept();
		dept.setId(1);
		
		
		work.setWorkname("吃饭");
		work.setWorkcontent("吃好点");
		work.setDept(dept);
		
		
		int i = ws.insertWork(work);
		System.out.println(i);
	}
}
