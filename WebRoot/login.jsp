
<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<title>OA办公系统</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/themes/black/easyui.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
        $(function(){
        	//点击图片后触发的事件 即点击图片重新发送请求 就可以换一个验证码
        	$("#captchaImage").click(function(){
        		//alert();
        		$(this).prop("src","${pageContext.request.contextPath}/imgCode/getImgCode?time"+new Date().getTime())
        	});
        	
        	//当离开验证码输入框时 触发事件 判断验证码是否正确
        	$("#enCode").blur(function(){
        		//alert();
        		//采用post 发送ajax请求
        		$.post("${pageContext.request.contextPath}/user/checkCode",{"code":$(this).val()},function(data){
        			console.log(data);
        			$("#messspan").html("<font>"+data.msg+"</font>")
        		},"JSON");
        	});
        	
        	
        	//点击提交按钮之后
        	$("#login").click(function(){
        		//alert();
         		$('#loginForm').form('submit', {    
				    url:"${pageContext.request.contextPath}/user/login",    
				    //发送请求之前会执行的代码
				    onSubmit: function(){    

				       /*  var isValid = $(this).form('validate');
				        return isValid; */
				        return true;
				    },    
				    success:function(data){  
				    var data = eval('(' + data + ')');  
				    console.log(data)
				    console.log(data.msg)
				    
				    if(data.msg!=1){
				    	$("#messspan").html("<font>"+data.msg+"</font>")
				    }
				    
				  
				    //alert(data.msg);
				    //判断一下  登陆的结果是什么 成功就跳转  不成功就显示 哪里出错
				    
				    if(data.msg==1){
				    	console.log('要跳转啦 ');
					    //成功就跳转路径
					    location.href="${pageContext.request.contextPath}/main.jsp";
				    }else{
				     	//转化json变成 js 不成功就把消息显示再界面
				   		 var data = eval('(' + data + ')');  
				        $("#messspan").html("<font>"+data.msg+"</font>")
				    }
				
				   
				    }    
				});
			});
        
        })
	</script>
</head>
<body>
		
		<div class="login">
			<form id="loginForm" method="post" >
				<table >
					<tbody>
						<tr><h2 align="center"><span >在线OA办公系统</span></h2></tr>
						<tr >
							<td width="190"  rowspan="2" align="center" valign="bottom">
								<img src="${pageContext.request.contextPath }/imgs/oalogo.jpg" height="100" width="100" />
							</td>
							<th>
								用户名:
							</th>
							<td>
								<input class="easyui-validatebox" type="text" name="email" data-options="required:true" />
							</td>
					  </tr>
					  <tr>
							<th>
								密&nbsp;&nbsp;&nbsp;码:
							</th>
							<td>
								<input class="easyui-validatebox" type="password" maxlength="6" name="password" data-options="required:true,validType:'maxLength[6]'"/>
							</td>
					  </tr>

						<tr>
							<td>&nbsp;</td>
							<th>验证码:</th>
							<td>
								<input type="text" id="enCode" name="enCode" class="easyui-validatebox" size="8px" data-options="required:true,validType:'maxLength[6]'"/>
								<img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/imgCode/getImgCode" title="点击更换验证码"/>
							</td>
						</tr>
					<tr>
						<td>&nbsp;</td>
						<th><span id="messspan" style="color: red"></span></th>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<th>&nbsp;</th>
						<td>
							<input type="button" class="homeButton"><input id="login" type="button" class="loginButton" value="登录">
						</td>
					</tr>
				</tbody></table>
				<div class="powered">ZZU © 2018-2019.</div>
				<div class="link">
					<a href="http://www.oahh.com/">OA在线办公首页</a> |
					<a href="http://www.oahh.com/">交流论坛</a> |
					<a href="">关于我们</a> |
					<a href="">联系我们</a> |
					<a href="">授权查询</a>
				</div>
			</form>
		</div>
</body>
</html>