<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"%>
<html>
<!-- 展示用户请假的一个jsp  用户可以通过一个按钮发送请假请求 -->
<body>
	<table id="myleave_dg"></table>	
	<script>
$(function(){
	$('#myleave_dg').datagrid({
    	url:'${pageContext.request.contextPath}/leave/selectloginleave.do',    
   		columns:[[
   			{field:'leaveDate',title:'发起请假日期',width:100},   
        	{field:'leaveStarttime',title:'请假起始时间',width:100},    
        	{field:'leaveEndttime',title:'请假结束时间',width:100},
        	{field:'reason',title:'请假原因',width:100},
        	{field:'leaveStatus',title:'状态', width:80,
        		//这个函数太厉害了  可以判断值return  牛逼
				formatter : function(value, row, index) {
				//value 是当前的值    row是这一行的值    index是下标
					console.log("leave---->"+value,row,index);
					if (value == 0) {
						return "待审核";
					} else if (value == 1) {
						//return "<input value=" + value + row.id + " class='switchbutton'/>";
						return "通过";
					}else {
						return "未通过";
					}
				}
			}
    	]] ,
    	//pagination : true,
		height : '500',
		fitColumns : true,
		singleSelect : true,
		//view : detailview,
		//-----------按钮组------------------------------------------------------------------------------------------------
		toolbar : [ {
		
			/* 添加  */
			iconCls : 'icon-add',
			text : '发起请假',
			handler : function() {
				$('#leave_ff').dialog({
					title : '请假框',
					inonCls : 'icon-add',
					width : 300,
					height : 200,
					closed : false,
					modal : true,
					buttons : [ {
						text : '提交',
						handler : function() {
							$('#leave_ff').form('submit', {
								url : '${pageContext.request.contextPath}/leave/add.do',
								success : function(data) {
									$('#leave_ff').dialog({
										closed : true,
									});
									/* 重新加载整个表格  */
									$('#myleave_dg').datagrid('reload');
									$('#leave_ff').form('reset');
									$.messager.show({
										title : '提示',
										msg : '提交成功',
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
	]

	
	});
});
</script>

<!-- -------------------------添加的表单-----------------------------------------------------  -->
<div id="dialog_leave_add" class="easyui-dialog"
	data-options="closed:true">
	<form id="leave_ff" method="post" enctype="multipart/form-data">
		<div>
			<label for="name">请假起始时间:</label> <input class="easyui-datebox" required ="required"
				type="text" name="leaveStarttime" data-options="required:true" />
		</div>
		<div>
			<label for="name">请假结束时间:</label> <input class= "easyui-datebox" required ="required"
				type="text" name="leaveEndttime" data-options="required:true" />
		</div>
		<div>
			<label for="name">请假原因:</label> <input class="easyui-validatebox"
				type="text" name="reason" data-options="required:true" />
		</div>
		
		<!-- <div>
			<label for="user">分配个人:</label> <input id="userwork" name="user.id" value="">  
		</div> -->
	</form>
</div>
<!-- --------------表单结束---------------------------------------------------------------------------------- -->




</body>



</html>


		
