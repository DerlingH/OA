package com.hh.shiro.realms;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.hh.entity.User;
import com.hh.service.UserService;


//ֻ��֤����ֻ�̳������
public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	
	
	//˵���˾��Ǵ����ݿ����� Ȼ��ȶ� ����һ��info����
	//��֤�ķ���
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		System.out.println("���ҳ���õ���token [FirstRealm] doGetAuthenticationInfo ----------->"+token);
		
		//1. �� AuthenticationToken ת��Ϊ UsernamePasswordToken 
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//2. �� UsernamePasswordToken ������ȡ username
		String eamil= upToken.getUsername();
		
		//3. �������ݿ�ķ���, �����ݿ��в�ѯ username ��Ӧ���û���¼
		System.out.println("�����ݿ��л�ȡ username: " + eamil + " ����Ӧ���û���Ϣ.");
		
		User loginuser = new User();
		loginuser.setEmail(eamil);
		User user = userService.selectUserByObj(loginuser);
		System.out.println("------------------------>"+user);
		
		
		
		//4. ���û�������, ������׳� UnknownAccountException �쳣
		if(user == null){
			throw new UnknownAccountException("�û�������!");
		}
		Session session = SecurityUtils.getSubject().getSession();
	    session.setAttribute("user", user);
		
		
//		//5. �����û���Ϣ�����, �����Ƿ���Ҫ�׳������� AuthenticationException �쳣. 
//		if("monster".equals(username)){
//			throw new LockedAccountException("�û�������");
//		}
		
		//6. �����û������, ������ AuthenticationInfo ���󲢷���. ͨ��ʹ�õ�ʵ����Ϊ: SimpleAuthenticationInfo
		//������Ϣ�Ǵ����ݿ��л�ȡ��.
		//1). principal: ��֤��ʵ����Ϣ. ������ username, Ҳ���������ݱ��Ӧ���û���ʵ�������. 
		Object principal = user.getEmail();
		//2). credentials: ����. 
		Object credentials = user.getPassword(); //848d6bf0fabb0aea79ae806aeda3fb02
//		if("admin".equals(username)){
//			credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
//		}else if("user".equals(username)){
//			credentials = "098d2c478e9c11555ce2823231e02ec1";
//		}
		
		//3). realmName: ��ǰ realm ����� name. ���ø���� getName() ��������
		String realmName = getName();
		//4). ��ֵ.   ������ʹ��email��������ֵ
		ByteSource credentialsSalt = ByteSource.Util.bytes(eamil);
		
		SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
		info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		return info;
	}

	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("huanghao@zzu.com");;
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}

	//��Ȩ�ᱻ shiro �ص��ķ���
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		//1. �� PrincipalCollection ������ȡ��¼�û�����Ϣ
		Object principal = principals.getPrimaryPrincipal();
		
		//2. ���õ�¼���û�����Ϣ���û���ǰ�û��Ľ�ɫ��Ȩ��(������Ҫ��ѯ���ݿ�)
		Set<String> roles = new HashSet<>();
		roles.add("user");
		if("admin".equals(principal)){
			roles.add("admin");
		}
		
		//3. ���� SimpleAuthorizationInfo, �������� reles ����.
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		
		//4. ���� SimpleAuthorizationInfo ����. 
		return info;
	}
}
