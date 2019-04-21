<%@page isELIgnored="false" pageEncoding="UTF-8"
	contentType="text/html; UTF-8"%>
<html>
<head>
<title>所有员工</title>
<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/common.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/login.css"
	type="text/css"></link>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/themes/black/easyui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui-lang-zh_CN.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/datagrid-detailview.js"></script>



</head>
<body>

<!-- <input type="text" ><input id="姓名" type="button" value="部门搜索" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --><div><input type="text" name="keyWord" id="keyWord"></div>
<table id="user_dg"></table>
<script>
$(function(){
	$('#user_dg').datagrid({
		 
    	url:'${pageContext.request.contextPath}/user/showall.do',    
   		columns:[[    
        	{field:'name',title:'姓名',width:100},    
        	{field:'email',title:'邮箱',width:100},    
        	{field:'hobbies',title:'爱好',width:100},
        	{field:'departmentId',title:'部门', width:80,
        	
        		//这个函数太厉害了  可以判断值return  牛逼
				formatter: function(value,row,index){
					/*  让我看看这三个参数都是啥 
					console.log("value"+value);
					console.log("row"+row);
					console.log("index"+index); */
					if (value==1){
						return "Java开发";
					} else if(value==2){
						return "前端部门";
					} else if(value==3){
						return "产品部门";
					} else{
						return "测试部门"
					}
				}
			}
        	
    	]] ,
    	//pagination : true,
		height : '500',
		fitColumns : true,
		//view : detailview,  
	}); 
		//搜索功能
	$('#keyWord').textbox({   
		buttonIcon:'icon-search',
    	buttonText:'Search',    
    	prompt:"员工姓名", 
    	iconAlign:'left',
    	onClickButton:function(){
    		var keyWord= $('#keyWord').val();
    		console.log(keyWord)
    		if(isNaN(parseInt(keyWord))){
					$("#user_dg").datagrid('load',{
						uqname:keyWord,//向后台传递name=name,value=keyWord
					});
				}else{
					$("#user_dg").datagrid('load',{
						id:keyWord,//向后台传递name=id,value=keyWord
    	
    				})
    			}	 
    	}     
	});

	
	
});
</script>
</body>

</html>


		
