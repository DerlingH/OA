package com.hh.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hh.entity.Album;
import com.hh.service.AlbumService;



@Controller
@RequestMapping("/album")
public class AlbumController {
	@Autowired
	private AlbumService albumService;
	@RequestMapping("getAll")
	@ResponseBody
	public List<Album> getAll(){
		List<Album> list = albumService.getAll();
		return list;
	}
	@RequestMapping("add")
	@ResponseBody
	public Object add(Album a,MultipartFile pic,HttpServletRequest req){
		String realPath = req.getServletContext().getRealPath("/img");
		String headPic = UUID.randomUUID().toString()+pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf(".")); 
		String filePath = realPath+"/"+headPic;
		a.setCoverImg(headPic);
		a.setId(UUID.randomUUID().toString());
		albumService.add(a);
		Map<String,Object> map = new HashMap<>();
		map.put("code", "添加成功");
		try {
			pic.transferTo(new File(filePath));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
}
