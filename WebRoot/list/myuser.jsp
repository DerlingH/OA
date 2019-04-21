<%@page isELIgnored="false" pageEncoding="UTF-8"
	contentType="text/html; UTF-8"%>
<html>

<body>

<!-- 个人信息 -->
<table id="myuser_dg"></table>
<script>
$(function(){
	$('#myuser_dg').datagrid({
		
    	url:'${pageContext.request.contextPath}/user/showone.do',    
   		columns:[[
   		
   			{field:'name',title:'姓名',width:100},    
        	{field:'email',title:'邮箱',width:100},    
        	{field:'jointime',title:'加入时间',width:100},
        	{field:'hobbies',title:'爱好',width:100},
        	{field:'phone',title:'电话',width:100},   
        	    
    	]] ,
    	
		height : '500',
		fitColumns : true,
		//view : detailview,
		toolbar : [  {
			iconCls : 'icon-edit',
			text : '修改',
			handler : function() {
				var as = $('#myuser_dg').datagrid('getSelected');
				console.log(as)
				if (as == null || as == '') {
					$.messager.alert('警告', '请选中');
				} else {
					$('#myuser_ff').form('load', as);
					console.log("66666")
					$('#myuser_ff').dialog({
						title : '修改',
						width : 400,
						height : 200,
						closed : false,
						modal : true,
						buttons : [ {
							text : '修改',
							handler : function() {
								console.log("77777")
								$('#myuser_from_ff').form('submit', {
									url : "${pageContext.request.contextPath}/user/update",
									success : function(data) {
										$('#myuser_ff').dialog({
											closed : true,
										});
										$('#myuser_dg').datagrid('reload');
										$('#myuser_from_ff').form('reset');
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
		}
		]
	}); 
});

/*修改按钮  */
</script>
<div id="myuser_ff" class="easyui-dialog"
	data-options="closed:true">
	<form id="myuser_from_ff" method="post" enctype="multipart/form-data">
		
		
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

		
	</form>
</div>
</body>

</html>


		
