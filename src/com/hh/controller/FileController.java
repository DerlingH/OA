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
		
		//获得保存文件夹的真实路径
		String realPath = request.getServletContext().getRealPath("/upFile");
		//防止文件重名
		String temp = UUID.randomUUID().toString()+upFile.getOriginalFilename();
		
		//文件保存在服务器上的路径
		String filePath = realPath+"\\"+ temp;
		
		
		//保存文件
		try {
			upFile.transferTo(new java.io.File(filePath));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//设置一个文件名
		File filed = new File();
		filed.setFileName(upFile.getOriginalFilename());
		
		//设置文件大小
		String fileSize = String.format("%.2f", (double)(upFile.getSize()/1024));
		filed.setFileSize(fileSize+"K");
		//设置保存路径
		filed.setFileSavepath(temp);
		//设置上传的User的ID
		filed.setFileUploadtime(new Date());
		
		User loginUser = (User) session.getAttribute("user");
		if(loginUser!=null){
			filed.setUserid(loginUser.getId());
		}
		
		
		System.out.println("上传的file信息是-------------------->"+filed);
		
		int i = fileService.insertFile(filed);
		HashMap<String,String> map = new HashMap<String,String>();
		if (i==1) {
			map.put("code", "200");
			map.put("msg", "添加成功");
		}else {
			map.put("code", "400");
			map.put("msg", "添加失败");
		}
		return map;
		
		
	}
	
	//下载
	@RequestMapping("/downFile")
	public void downChapter(Integer fileId,HttpServletResponse resp,HttpServletRequest request){
		File filed = fileService.selectFileById(fileId);
		
		String savepath = filed.getFileSavepath();
		String name = filed.getFileName();
		
		String realPath = request.getServletContext().getRealPath("/upFile");
		String filePath = realPath + "/" + savepath;
		
		System.out.println("filePath："+filePath+",name:"+name);
		OutputStream out = null;
		
		try {
			//指定浏览器获取文件后，不解析，而是保存
			resp.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(name, "UTF-8"));
			//获得输出流
			out = resp.getOutputStream();
			//将文件拷贝到输出流中
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
			map.put("msg", "删除成功");
		}else {
			map.put("code", "400");
			map.put("msg", "删除失败");
		}
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
