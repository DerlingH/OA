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

//��֤��ģ��
@Controller
@RequestMapping("/imgCode")
public class ImgCode {
	
	@RequestMapping("/getImgCode")
	public void getImgCode(HttpServletResponse response,HttpSession session){
		 //������֤��
		String code = ValidateImageCodeUtils.getSecurityCode();
		
		// ����֤�����session��
		session.setAttribute("safeCode", code);

		// ���ù�����ķ����Զ�����һ����֤��
	    BufferedImage bi = ValidateImageCodeUtils.createImage(code);

		// 3. ����response������ͼ����img��src
		// ImageIO.write(ͼ��"JPG"����Ӧ�����)
	    
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
