<%@page isELIgnored="false" pageEncoding="UTF-8"
	contentType="text/html; UTF-8"%>
<html>

<body>
	<table id="adminuser_dg"></table>	
	<script>
$(function(){
	$('#adminuser_dg').datagrid({
		 
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
		singleSelect : true,
		//view : detailview,
		
//-----------------------按钮组----------------------------------------------------------
		toolbar : [ {
		
			/* 添加  */
			iconCls : 'icon-add',
			text : '添加',
			handler : function() {
				$('#adminuser_ff').dialog({
					title : '添加',
					inonCls : 'icon-add',
					width : 300,
					height : 200,
					closed : false,
					modal : true,
					buttons : [ {
						text : '添加',
						handler : function() {
							$('#adminuser_ff').form('submit', {
								url : "${pageContext.request.contextPath}/user/add.do",
								success : function(data) {
									$('#adminuser_ff').dialog({
										closed : true,
									});
									/* 重新加载整个表格  */
									$('#adminuser_dg').datagrid('reload');
									$('#adminuser_ff').form('reset');
									$.messager.show({
										title : '提示',
										msg : '添加成功',
										timeout : 5000,
										showType : 'slide'
									});
								}
							});
						}
					} ],
				});
			}
		}, '-', {
			iconCls : 'icon-edit',
			text : '修改',
			handler : function() {
				var as = $('#adminuser_dg').datagrid('getSelected');
				if (as == null || as == '') {
					$.messager.alert('警告', '请选中');
				} else {
					$('#adminuser_from_update').form('load', as);
					$('#adminuser_from_update').dialog({
						title : '修改',
						width : 400,
						height : 200,
						closed : false,
						modal : true,
						buttons : [ {
							text : '修改',
							handler : function() {
								$('#adminuser_from_update').form('submit', {
									url : "${pageContext.request.contextPath}/user/update.do",
									success : function(data) {
										$('#adminuser_from_update').dialog({
											closed : true,
										});
										$('#adminuser_dg').datagrid('reload');
										$('#adminuser_from_update').form('reset');
										$.messager.show({
											title : '提示',
											msg : '修改成功',
											timeout : 5000,
											showType : 'slide'
										});
									}
								});
							}
						} ],
					});


				}
			}
		}, '-', {
			iconCls : 'icon-help',
			text : '删除',
			handler : function() {
				var as = $('#adminuser_dg').datagrid('getSelections');
				if (as == null || as == '') {
					$.messager.alert('警告', '请选中');
				} else {
					console.log(as);
					//1.获得要发送的id参数
					var param = "";
					for (var i = 0; i < as.length; i++) {
						if (i == 0) {
							param = param + "ids=" + as[i].id;
						} else {
							param = param + "&ids=" + as[i].id;
						}
					}
					//2.发送ajax请求
					$.ajax({
						url : '${pageContext.request.contextPath}/user/remove.do',
						type : 'post',
						data : param,
						dataType : "text",
						success : function(data) {
							$('#adminuser_dg').datagrid('reload');
							$.messager.show({
								title : '提示',
								msg : '删除成功',
								timeout : 5000,
								showType : 'slide'
							});

						},
					});
				}
				;
			}
		}]
//-----------------------按钮组结束--------------------------------------------------------------
		
		
		
		
			
		
	}); 
	
//-----------------------------搜索功能------------------------------------------------
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
//-------------------------------搜索功能结束-------------------------------------------------------------
	
	
});
</script>

<!-- -------------------------添加的表单-----------------------------------------------------  -->
<div id="dialog_adminuser_add" class="easyui-dialog"
	data-options="closed:true">
	<form id="adminuser_ff" method="post" enctype="multipart/form-data">
		<div>
			<label for="name">姓名:</label> <input class="easyui-validatebox"
				type="text" name="name" data-options="required:true" />
		</div>
		<div>
			<label for="name">手机号:</label> <input class="easyui-validatebox"
				type="text" name="phone" data-options="required:true" />
		</div>
		<div>
			<label for="name">email:</label> <input class="easyui-validatebox"
				type="text" name="email" data-options="required:true" />
		</div>
		<div>
			<label for="name">爱好:</label> <input class="easyui-validatebox"
				type="text" name="hobbies" data-options="required:true" />
		</div>
		<div>
			<label for="email">所属部门:</label> <select class="easyui-combobox"
				name="departmentId" style="width:200px;">
				<option value="1">Java开发部门</option>
				<option value="2">前端部门</option>
				<option value="3">产品部门</option>
				<option value="4">测试部门</option>
				
			</select>
		</div>
	
	</form>
</div>
<!-- ----------------------------修改的表单---------------------------------------------------------------- -->

<div id="dialog_adminuser_update" class="easyui-dialog"
	data-options="closed:true">
	<form id="adminuser_from_update" method="post" enctype="multipart/form-data">
		
		
		<div style="display:none">
			<label for="id">id&nbsp;&nbsp;&nbsp;:</label> <input class="easyui-validatebox"
				type="text" name="id" data-options="required:true" />
		</div>
		<div>
			<label for="name">姓名:</label> <input class="easyui-validatebox"
				type="text" name="name" data-options="required:true" />
		</div>
		<div>
			<label for="name">头像:</label> <input class="easyui-filebox"
				type="text" name="pic" data-options="required:false" />
		</div>
		
		<div>
			<label for="name">电话:</label> <input class="easyui-validatebox"
				type="text" name="phone" data-options="required:true" />
		</div>
		<div>
			<label for="name">爱好:</label> <input class="easyui-validatebox"
				type="text" name="hobbies" data-options="required:true" />
		</div>
		<div>
			<label for="email">所属部门:</label> <select class="easyui-combobox"
				name="proportion" style="width:200px;">
				<option value="1">Java开发部门</option>
				<option value="2">前端部门</option>
				<option value="3">产品部门</option>
				<option value="4">测试部门</option>
				
			</select>
		</div>
		
	</form>
</div>






</body>



</html>


		
