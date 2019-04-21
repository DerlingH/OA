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

//用户模块
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userservice;

@RequestMapping("/login")
@ResponseBody
public Map<String, String> login(@RequestParam("email")String email,@RequestParam("password")String password){
	System.out.println("看看谁想登陆？--->email:"+email+",password:"+password);
	
	Subject subject = SecurityUtils.getSubject();
	
	Map<String, String> map = new HashMap<String, String>();
	if (!subject.isAuthenticated()) {
    	// 把用户名和密码封装为 UsernamePasswordToken 对象
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        
        // rememberme，可以在前台登录的时候，传递一个数据，如果有数据，则设置token.setRememberMe(true);，
        // 没有值，就不设置token.setRememberMe(true);,这样就实现了记住我的功能。在系统中，只要是设置“记住我”可以访问的页面，都可以访问。
//          token.setRememberMe(true);
        try {
        	// 执行登录. 
            subject.login(token);
            map.put("code", "200");
            map.put("msg", "1");//登陆成功
        }catch (UnknownAccountException e) {
        	System.out.println("------用户名不存在-----------"+e.getMessage());
        	map.put("code", "400");
            map.put("msg", "亲~用户名不存在");
		}catch (IncorrectCredentialsException e) {
			System.out.println("---------密码错误-----------"+e.getMessage());
			map.put("code", "400");
            map.put("msg", "亲~密码输错啦");
		}
        // 所有认证时异常的父类. 
        catch (AuthenticationException e) {
        	System.out.println("--Controller层异常：------登录失败--------"+e.getMessage());
        	map.put("code", "400");
            map.put("msg", "登录失败");
        }
    }else {
    	System.out.println("-----已经认证----------");
    	map.put("code", "400");
        map.put("msg", "登录失败:您已登录！");
	}
	return map;
}
	
	
	
// 登录
//	@RequestMapping("/login")
//	@ResponseBody
//	public Object login(String username, String password, HttpSession session) {
//		Map<String, String> map = new HashMap<String, String>();
//		User u = new User(null, null, null, null, username, null, null, null, null);
//		User user = userservice.selectUserByObj(u);
//		System.out.println("接收到的参数" + user + "账号：" + username + " " + password);
//		if (user == null) {
//
//			map.put("code", "200");
//			map.put("msg", "账号错误");
//
//		} else {
//			// 查询之后 再比较密码
//			if (user.getPassword().equals(password)) {
//				// 登陆成功 放入session
//				session.setAttribute("user", user);
//				map.put("msg", "1");
//			} else {
//				map.put("msg", "密码错误");
//
//			}
//		}
//		return map;
//	}

	// 验证码验证
	@RequestMapping("/checkCode")
	@ResponseBody
	public Object checkCode(String code, HttpSession session) {

		Map<String, String> map = new HashMap<String, String>();
		String realCode = (String) session.getAttribute("safeCode");
		if (code.equals("")) {
			map.put("code", "200");
			map.put("msg", "请输入验证码");
		} else if (code.equals(realCode)) {
			map.put("code", "200");

			map.put("msg", "验证码正确");

		} else {
			map.put("code", "200");
			map.put("msg", "验证码错误");

		}
		return map;

	}

	// 展示当前的登录的员工
	@RequestMapping("/showone")
	@ResponseBody
	public Object showone( HttpSession session) {
		List<User> list = new ArrayList<User>();
		
		User loginuser = (User)session.getAttribute("user");
		System.out.println("看看是哪个小宝贝登录啦---------->"+loginuser);
		
		if(loginuser!=null){
			User user = userservice.selectUserById(loginuser.getId());
			System.out.println("员工信息" + user);
			list.add(user);
			return list;
		}else{
			return null;
		}
		
		
	}

	// 展示所有员工
	@RequestMapping("/showall")
	@ResponseBody
	public Object showall(UserQuery uq) {
		System.out.println(uq);
		List<User> list = userservice.selectUserByUQ(uq);
		System.out.println("所有的员工信息" + list);
		return list;

	}

	// 修改当前员工的信息
	@RequestMapping("/update")
	@ResponseBody
	public void deleteuser(User enti) {

		System.out.println("有没有接收到传过来修改的user"+enti);
		userservice.updateNonEmptyUserById(enti);
		System.out.println("修改成功");
		

	}
	
	//删除一个员工
	@RequestMapping("/remove")
	@ResponseBody
	public void deleteuser(Integer ids) {

		System.out.println("有没有接收到传过来删除的id"+ids);
		userservice.deleteUserById(ids);
		
		System.out.println("删除成功");
		

	}
	
	//增加一个员工
	@RequestMapping("/add")
	@ResponseBody
	public void adduser(User user) {

		System.out.println("有没有接收到传过来的User----->"+user);
		
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes(user.getEmail());
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
		
		
		
		user.setPassword((String)result);
		
		user.setJointime(new Date());
        userservice.insertUser(user);
		
		System.out.println("添加成功");
		

	}
	
	

}
