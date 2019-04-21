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

//�����ص�Controller
@Controller
@RequestMapping("/leave")
public class leaveController {
	@Autowired
	private LeaveService leavservice;
	
	
	//չʾ����ǰ�û���ص������Ϣ  ��ǰ�Ǵ���ʲô״̬
	@RequestMapping("/selectloginleave.do")
	@ResponseBody
	public Object selectloginleave(HttpSession session){
		User loginuser =(User)session.getAttribute("user");
		if(loginuser!=null){
			List<Leave> list = leavservice.selectLeaveByUserId(loginuser.getId());
			System.out.println("��ǰ��¼�û��Ĺ���---------------->"+list);
			return list;
		}else{
			return null;
		}
	}

	//������
	@RequestMapping("/add.do")
	@ResponseBody
	public void addleave(Leave leave,HttpSession session){
		System.out.println("ǰ�˴�������leave---------->"+leave);
		leave.setLeaveDate(new Date());
		leave.setLeaveStatus(0);
		User loginuser =(User)session.getAttribute("user");
		
		User leaveuser = new User();
		leaveuser.setId(loginuser.getId());
		leave.setLeaveUser(leaveuser);
		
		leavservice.insertLeave(leave);
		System.out.println("----------------->������󷢹�ȥ��");
	}

	//չʾ���е������Ϣ  ��ϸ�������� �����
	@RequestMapping("/selectallleave")
	@ResponseBody
	public List<Leave> selectallleave(){
		
		
		List<Leave> list = leavservice.selectAllLeave();
		System.out.println("------���е������Ϣ----------->"+list);
		return list;
	}
	

	//���֮����޸���ٵ�״̬
	@RequestMapping("/update")
	@ResponseBody
	public void updateleave(Leave leave,HttpSession session){
		System.out.println("���յ���leave����---------->"+leave);
		
		//�ѵ�ǰ������˼���
		User loginuser =(User)session.getAttribute("user");
		User examineuser = new User();
		
		examineuser.setId(loginuser.getId());
		leave.setExamineUser(examineuser);
		
		leavservice.updateNonEmptyLeaveById(leave);
		System.out.println("���³ɹ�");
		

	}


}
