package com.hh.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.hh.entity.User;
import com.hh.entity.Work;
import com.hh.service.WorkService;

//工作相关的控制器
@Controller
@RequestMapping("/work")
public class WorkController {
	@Autowired
	private WorkService workservice;
	
	
	
	//是想展示当前用户未完成的工作  然后有一个按钮  可以把自己的工作做完了提交
	//另外展示的时候做一个排序  让未完成的在上面   完成的在下面  按钮也可以设置一下 嘻嘻完美
	@RequestMapping("/showmework")
	@ResponseBody
	public Object showmework(HttpSession session){
		User loginuser =(User)session.getAttribute("user");
		if(loginuser!=null){
			List<Work> list = workservice.selectByuserId(loginuser.getId());
			System.out.println("当前登录用户的工作---------------->"+list);
			return list;
		}else{
			return null;
		}
		
	}
	
	//把一个没有完成的项目 修改成完成  并且 给他加上现在的时间  接收work的id以及现在的状态
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, String> updateuser(Work work){
		work.setEndtime(new Date());
		work.setStatus(1);
		System.out.println("拿到要改的user了吗-------->"+work);
		workservice.updateNonEmptyWorkById(work);
		System.out.println("修改成功");
		
		
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("200", "ok");
		return map;
	}
	
	//展示所有的工作包括负责的人负责的部门
	@RequestMapping("/showallwork")
	@ResponseBody
	public List<Work> showallwork(){
		List<Work> list = workservice.selectAllWork();
		System.out.println("-------工作全部的list---------->"+list);
		return list;
	}
	
	//添加任务
	@RequestMapping("/add")
	@ResponseBody
	public void addwork(Work work){
		//把任务的发布时间添加上去
		work.setStarttime(new Date());
		//设置任务的状态为未完成
		work.setStatus(0);
		
		System.out.println("-------快看新的任务来了---------->"+work);
		workservice.insertWork(work);
		System.out.println("添加成功");
	
	}
}
