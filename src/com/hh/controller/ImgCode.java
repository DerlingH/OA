package com.hh.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hh.util.ValidateImageCodeUtils;

//验证码模块
@Controller
@RequestMapping("/imgCode")
public class ImgCode {
	
	@RequestMapping("/getImgCode")
	public void getImgCode(HttpServletResponse response,HttpSession session){
		 //生成验证码
		String code = ValidateImageCodeUtils.getSecurityCode();
		
		// 将验证码存入session中
		session.setAttribute("safeCode", code);

		// 调用工具类的方法自动生成一个验证码
	    BufferedImage bi = ValidateImageCodeUtils.createImage(code);

		// 3. 依靠response流，将图传回img的src
		// ImageIO.write(图，"JPG"，响应输出流)
	    
		ServletOutputStream os=null;
		try {
			os = response.getOutputStream();
			 ImageIO.write(bi, "JPG", os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	   		
		
		
	};
}
