<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>在线办公OA主页</title>
	<link rel="icon" href="${pageContext.request.contextPath }/img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/themes/black/easyui.css">   
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/themes/IconExtension.css">   
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>   
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.easyui.min.js"></script>  
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/datagrid-detailview.js"></script>
	<script src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/china.js"></script>
     <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
	<script type="text/javascript">
		<!--菜单处理-->
		$(function(){
			$.ajax({ 
				url: "${pageContext.request.contextPath}/menu/getAllMenu.do", 
				type:'post',
				dataType:"json",
				success: function(data){
			   	 	 $.each(data,function(i,m){
						var title ="";
						$.each(m.menus,function(index1,second){
							//console.log(second);
							title += "<p style=\"text-align: center;\"><a onclick=\"addTabs('"+second.title+"','"+second.iconcls+"','"+second.href+"');\" href=\"#\" class=\"easyui-linkbutton\" data-options=\"iconCls:'"+second.iconcls+"'\">"+second.title+"</a></p>";
						})
						$('#aa').accordion('add', {
							iconCls:m.iconcls,
							title: m.title,
							content: title,
							selected: false,
						});
						
					}); 
				},//Ajax被拦截  请求失败后  跳转的 路径
				/*error:function(){
					location.href="${pageContext.request.contextPath}/login.jsp";
				
				}*/
				});
			});
			var addTabs = function(title,iconCls,href){
				var a = $('#tt').tabs('exists',title);
				if(a){
					var a = $('#tt').tabs('select',title);
				}else{
					$('#tt').tabs('add',{    
					    title:title, 
					    iconCls: iconCls,  
					    //content:'Tab Body',
					    href:"${pageContext.request.contextPath}/"+href,  
					    closable:true,    
					});  
				}		
		}
	
</script>

<!--EasyUI的主体框架  -->
</head>
<body class="easyui-layout">
    <div data-options="region:'north',split:true" style="height:60px;background-color:  ##333333">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >OA在线办公系统</div>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:${sessionScope.user.name}&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/user/logout" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
    </div>   
    <div data-options="region:'south',split:true" style="height: 40px;background: ##333333">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;OA在线办公</div>
    </div>   
       
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">  	  
    	<div id="aa" class="easyui-accordion" data-options="fit:true">
		</div>  
    </div>   
    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">   
		    <div id="main_tabs" title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(${pageContext.request.contextPath}/main/image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
		</div>  
    </div>   
</body> 
</html>