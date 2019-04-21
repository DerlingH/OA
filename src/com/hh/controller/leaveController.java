package com.hh.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hh.entity.Leave;
import com.hh.entity.User;
import com.hh.service.LeaveService;

//请假相关的Controller
@Controller
@RequestMapping("/leave")
public class leaveController {
	@Autowired
	private LeaveService leavservice;
	
	
	//展示跟当前用户相关的请假消息  当前是处于什么状态
	@RequestMapping("/selectloginleave.do")
	@ResponseBody
	public Object selectloginleave(HttpSession session){
		User loginuser =(User)session.getAttribute("user");
		if(loginuser!=null){
			List<Leave> list = leavservice.selectLeaveByUserId(loginuser.getId());
			System.out.println("当前登录用户的工作---------------->"+list);
			return list;
		}else{
			return null;
		}
	}

	//添加请假
	@RequestMapping("/add.do")
	@ResponseBody
	public void addleave(Leave leave,HttpSession session){
		System.out.println("前端传过来的leave---------->"+leave);
		leave.setLeaveDate(new Date());
		leave.setLeaveStatus(0);
		User loginuser =(User)session.getAttribute("user");
		
		User leaveuser = new User();
		leaveuser.setId(loginuser.getId());
		leave.setLeaveUser(leaveuser);
		
		leavservice.insertLeave(leave);
		System.out.println("----------------->请假请求发过去啦");
	}

	//展示所有的请假信息  详细到申请人 审核人
	@RequestMapping("/selectallleave")
	@ResponseBody
	public List<Leave> selectallleave(){
		
		
		List<Leave> list = leavservice.selectAllLeave();
		System.out.println("------所有的请假信息----------->"+list);
		return list;
	}
	

	//审核之后把修改请假的状态
	@RequestMapping("/update")
	@ResponseBody
	public void updateleave(Leave leave,HttpSession session){
		System.out.println("接收到的leave对象---------->"+leave);
		
		//把当前的审核人加上
		User loginuser =(User)session.getAttribute("user");
		User examineuser = new User();
		
		examineuser.setId(loginuser.getId());
		leave.setExamineUser(examineuser);
		
		leavservice.updateNonEmptyLeaveById(leave);
		System.out.println("更新成功");
		

	}


}
