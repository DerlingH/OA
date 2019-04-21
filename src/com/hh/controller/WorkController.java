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

//������صĿ�����
@Controller
@RequestMapping("/work")
public class WorkController {
	@Autowired
	private WorkService workservice;
	
	
	
	//����չʾ��ǰ�û�δ��ɵĹ���  Ȼ����һ����ť  ���԰��Լ��Ĺ����������ύ
	//����չʾ��ʱ����һ������  ��δ��ɵ�������   ��ɵ�������  ��ťҲ��������һ�� ��������
	@RequestMapping("/showmework")
	@ResponseBody
	public Object showmework(HttpSession session){
		User loginuser =(User)session.getAttribute("user");
		if(loginuser!=null){
			List<Work> list = workservice.selectByuserId(loginuser.getId());
			System.out.println("��ǰ��¼�û��Ĺ���---------------->"+list);
			return list;
		}else{
			return null;
		}
		
	}
	
	//��һ��û����ɵ���Ŀ �޸ĳ����  ���� �����������ڵ�ʱ��  ����work��id�Լ����ڵ�״̬
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, String> updateuser(Work work){
		work.setEndtime(new Date());
		work.setStatus(1);
		System.out.println("�õ�Ҫ�ĵ�user����-------->"+work);
		workservice.updateNonEmptyWorkById(work);
		System.out.println("�޸ĳɹ�");
		
		
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("200", "ok");
		return map;
	}
	
	//չʾ���еĹ�������������˸���Ĳ���
	@RequestMapping("/showallwork")
	@ResponseBody
	public List<Work> showallwork(){
		List<Work> list = workservice.selectAllWork();
		System.out.println("-------����ȫ����list---------->"+list);
		return list;
	}
	
	//�������
	@RequestMapping("/add")
	@ResponseBody
	public void addwork(Work work){
		//������ķ���ʱ�������ȥ
		work.setStarttime(new Date());
		//���������״̬Ϊδ���
		work.setStatus(0);
		
		System.out.println("-------�쿴�µ���������---------->"+work);
		workservice.insertWork(work);
		System.out.println("��ӳɹ�");
	
	}
}
