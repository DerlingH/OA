package com.hh.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hh.entity.File;
import com.hh.entity.User;
import com.hh.service.FileService;

@Controller
@RequestMapping("/file")
public class FileController {
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/selectAllFile")
	@ResponseBody
	public List<File> selectAllFile(){
		List<File> list = fileService.selectFile();
		System.out.println("FileList---------->"+list);
		return list;
		
	}
	
	@RequestMapping("/addFile")
	@ResponseBody
	public Map<String, String> addAlbum(MultipartFile upFile,HttpServletRequest request,HttpSession session){
		System.out.println("upFile:"+upFile.getOriginalFilename());
		
		//��ñ����ļ��е���ʵ·��
		String realPath = request.getServletContext().getRealPath("/upFile");
		//��ֹ�ļ�����
		String temp = UUID.randomUUID().toString()+upFile.getOriginalFilename();
		
		//�ļ������ڷ������ϵ�·��
		String filePath = realPath+"\\"+ temp;
		
		
		//�����ļ�
		try {
			upFile.transferTo(new java.io.File(filePath));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//����һ���ļ���
		File filed = new File();
		filed.setFileName(upFile.getOriginalFilename());
		
		//�����ļ���С
		String fileSize = String.format("%.2f", (double)(upFile.getSize()/1024));
		filed.setFileSize(fileSize+"K");
		//���ñ���·��
		filed.setFileSavepath(temp);
		//�����ϴ���User��ID
		filed.setFileUploadtime(new Date());
		
		User loginUser = (User) session.getAttribute("user");
		if(loginUser!=null){
			filed.setUserid(loginUser.getId());
		}
		
		
		System.out.println("�ϴ���file��Ϣ��-------------------->"+filed);
		
		int i = fileService.insertFile(filed);
		HashMap<String,String> map = new HashMap<String,String>();
		if (i==1) {
			map.put("code", "200");
			map.put("msg", "��ӳɹ�");
		}else {
			map.put("code", "400");
			map.put("msg", "���ʧ��");
		}
		return map;
		
		
	}
	
	//����
	@RequestMapping("/downFile")
	public void downChapter(Integer fileId,HttpServletResponse resp,HttpServletRequest request){
		File filed = fileService.selectFileById(fileId);
		
		String savepath = filed.getFileSavepath();
		String name = filed.getFileName();
		
		String realPath = request.getServletContext().getRealPath("/upFile");
		String filePath = realPath + "/" + savepath;
		
		System.out.println("filePath��"+filePath+",name:"+name);
		OutputStream out = null;
		
		try {
			//ָ���������ȡ�ļ��󣬲����������Ǳ���
			resp.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(name, "UTF-8"));
			//��������
			out = resp.getOutputStream();
			//���ļ��������������
			FileUtils.copyFile(new java.io.File(filePath), out);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("/deleteFile")
	@ResponseBody
	public Map<String, String> deleteFile(Integer fileId){
		int i = fileService.deleteFileById(fileId);
		
		HashMap<String,String> map = new HashMap<String,String>();
		if (i==1) {
			map.put("code", "200");
			map.put("msg", "ɾ���ɹ�");
		}else {
			map.put("code", "400");
			map.put("msg", "ɾ��ʧ��");
		}
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
