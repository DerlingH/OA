package com.hh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hh.entity.User;
import com.hh.entity.UserQuery;
import com.hh.service.UserService;

//�û�ģ��
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userservice;

@RequestMapping("/login")
@ResponseBody
public Map<String, String> login(@RequestParam("email")String email,@RequestParam("password")String password){
	System.out.println("����˭���½��--->email:"+email+",password:"+password);
	
	Subject subject = SecurityUtils.getSubject();
	
	Map<String, String> map = new HashMap<String, String>();
	if (!subject.isAuthenticated()) {
    	// ���û����������װΪ UsernamePasswordToken ����
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        
        // rememberme��������ǰ̨��¼��ʱ�򣬴���һ�����ݣ���������ݣ�������token.setRememberMe(true);��
        // û��ֵ���Ͳ�����token.setRememberMe(true);,������ʵ���˼�ס�ҵĹ��ܡ���ϵͳ�У�ֻҪ�����á���ס�ҡ����Է��ʵ�ҳ�棬�����Է��ʡ�
//          token.setRememberMe(true);
        try {
        	// ִ�е�¼. 
            subject.login(token);
            map.put("code", "200");
            map.put("msg", "1");//��½�ɹ�
        }catch (UnknownAccountException e) {
        	System.out.println("------�û���������-----------"+e.getMessage());
        	map.put("code", "400");
            map.put("msg", "��~�û���������");
		}catch (IncorrectCredentialsException e) {
			System.out.println("---------�������-----------"+e.getMessage());
			map.put("code", "400");
            map.put("msg", "��~���������");
		}
        // ������֤ʱ�쳣�ĸ���. 
        catch (AuthenticationException e) {
        	System.out.println("--Controller���쳣��------��¼ʧ��--------"+e.getMessage());
        	map.put("code", "400");
            map.put("msg", "��¼ʧ��");
        }
    }else {
    	System.out.println("-----�Ѿ���֤----------");
    	map.put("code", "400");
        map.put("msg", "��¼ʧ��:���ѵ�¼��");
	}
	return map;
}
	
	
	
// ��¼
//	@RequestMapping("/login")
//	@ResponseBody
//	public Object login(String username, String password, HttpSession session) {
//		Map<String, String> map = new HashMap<String, String>();
//		User u = new User(null, null, null, null, username, null, null, null, null);
//		User user = userservice.selectUserByObj(u);
//		System.out.println("���յ��Ĳ���" + user + "�˺ţ�" + username + " " + password);
//		if (user == null) {
//
//			map.put("code", "200");
//			map.put("msg", "�˺Ŵ���");
//
//		} else {
//			// ��ѯ֮�� �ٱȽ�����
//			if (user.getPassword().equals(password)) {
//				// ��½�ɹ� ����session
//				session.setAttribute("user", user);
//				map.put("msg", "1");
//			} else {
//				map.put("msg", "�������");
//
//			}
//		}
//		return map;
//	}

	// ��֤����֤
	@RequestMapping("/checkCode")
	@ResponseBody
	public Object checkCode(String code, HttpSession session) {

		Map<String, String> map = new HashMap<String, String>();
		String realCode = (String) session.getAttribute("safeCode");
		if (code.equals("")) {
			map.put("code", "200");
			map.put("msg", "��������֤��");
		} else if (code.equals(realCode)) {
			map.put("code", "200");

			map.put("msg", "��֤����ȷ");

		} else {
			map.put("code", "200");
			map.put("msg", "��֤�����");

		}
		return map;

	}

	// չʾ��ǰ�ĵ�¼��Ա��
	@RequestMapping("/showone")
	@ResponseBody
	public Object showone( HttpSession session) {
		List<User> list = new ArrayList<User>();
		
		User loginuser = (User)session.getAttribute("user");
		System.out.println("�������ĸ�С������¼��---------->"+loginuser);
		
		if(loginuser!=null){
			User user = userservice.selectUserById(loginuser.getId());
			System.out.println("Ա����Ϣ" + user);
			list.add(user);
			return list;
		}else{
			return null;
		}
		
		
	}

	// չʾ����Ա��
	@RequestMapping("/showall")
	@ResponseBody
	public Object showall(UserQuery uq) {
		System.out.println(uq);
		List<User> list = userservice.selectUserByUQ(uq);
		System.out.println("���е�Ա����Ϣ" + list);
		return list;

	}

	// �޸ĵ�ǰԱ������Ϣ
	@RequestMapping("/update")
	@ResponseBody
	public void deleteuser(User enti) {

		System.out.println("��û�н��յ��������޸ĵ�user"+enti);
		userservice.updateNonEmptyUserById(enti);
		System.out.println("�޸ĳɹ�");
		

	}
	
	//ɾ��һ��Ա��
	@RequestMapping("/remove")
	@ResponseBody
	public void deleteuser(Integer ids) {

		System.out.println("��û�н��յ�������ɾ����id"+ids);
		userservice.deleteUserById(ids);
		
		System.out.println("ɾ���ɹ�");
		

	}
	
	//����һ��Ա��
	@RequestMapping("/add")
	@ResponseBody
	public void adduser(User user) {

		System.out.println("��û�н��յ���������User----->"+user);
		
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes(user.getEmail());
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
		
		
		
		user.setPassword((String)result);
		
		user.setJointime(new Date());
        userservice.insertUser(user);
		
		System.out.println("��ӳɹ�");
		

	}
	
	

}
