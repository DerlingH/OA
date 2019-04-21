package com.hh.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hh.entity.Leave;
import com.hh.entity.User;
import com.hh.service.LeaveService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class Testleave {
	@Autowired
	private LeaveService leaveservice;
	
	
	//测试一下拿到当前用户的假期处理
	@Test
	public void test1(){
		List<Leave> list = leaveservice.selectLeaveByUserId(1);
		System.out.println(list);
	}
	
	
	//测试一下插入
	@Test
	public void test2(){
		Leave leave = new Leave();
		leave.setLeaveDate(new Date());
		leave.setLeaveStatus(0);
	
		
		User leaveuser = new User();
		leaveuser.setId(1);
		leave.setLeaveUser(leaveuser);
		
		User examineleaveuser = new User();
		leave.setExamineUser(examineleaveuser);
		
		
		leaveservice.insertLeave(leave);
		
	}
	

	//测试一下  查询所有假期
	@Test
	public void test3(){
		List<Leave> list = leaveservice.selectAllLeave();
		System.out.println(list);
	}
	
	//测试一下  更新
	@Test
	public void test4(){
		User leaveuser = new User();
		User examineUser = new User();
		examineUser.setId(2);
		leaveuser.setId(2);
		Leave leave = new Leave(); 
		leave.setId(7);
		leave.setLeaveUser(leaveuser);
		leave.setLeaveStatus(3);
		leave.setExamineUser(examineUser);
		leaveservice.updateNonEmptyLeaveById(leave);
		
	}
}
