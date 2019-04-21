<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"%>
<html>
<!-- 领导审核请假请求 -->
<!-- 我的设计是这个地方只显示所有未审核的请求     全部的请假展示  在全部的请假jsp里面展示  还有审核人  这个地方就不写审核人了 -->
<body>
	<table id="examineleave_dg"></table>	
	<script>
$(function(){
	$('#examineleave_dg').datagrid({
    	url:'${pageContext.request.contextPath}/leave/selectallleave.do',    
   		columns:[[
   			{field:'leaveDate',title:'发起请假日期',width:100},   
        	{field:'leaveStarttime',title:'请假起始时间',width:100},    
        	{field:'leaveEndttime',title:'请假结束时间',width:100},
        	{field:'reason',title:'请假原因',width:100},
        	{field:'leaveUser',title:'申请人',width:100,
        		formatter : function(value, row, index) {
        			console.log("leaveUser---->"+value,row,index);
        			return row.leaveUser.name;
        		}
        	},
        	{field:'leaveStatus',title:'状态', width:80,
        		//这个函数太厉害了  可以判断值return  牛逼
				formatter : function(value, row, index) {
				//value 是当前的值    row是这一行的值    index是下标
					
					if (value == 0) {
						return "待审核";
					} else if (value == 1) {
						//return "<input value=" + value + row.id + " class='switchbutton'/>";
						return "通过";
					}else {
						return "未通过";
					}
				}
			},
			
    	]] ,
    	//pagination : true,
		height : '500',
		fitColumns : true,
		singleSelect : true,
		//view : detailview,
		//-----------按钮组------------------------------------------------------------------------------------------------
		toolbar : [ {
		
			iconCls : 'icon-edit',
			text : '审核',
			handler : function() {
				var as = $('#examineleave_dg').datagrid('getSelected');
				console.log("as----------->"+as.leaveUser.name);
				if (as == null || as == '') {
					$.messager.alert('警告', '请选中');
				} else {
					$('#examineleave_from_update').form('load', as);
					$('#leaveUsername').val(as.leaveUser.name);
					$('#examineleave_from_update').dialog({
						title : '审核请假',
						width : 400,
						height : 200,
						closed : false,
						modal : true,
						buttons : [ {
							text : '提交',
							handler : function() {
								$('#examineleave_from_update').form('submit', {
									url : "${pageContext.request.contextPath}/leave/update.do",
									success : function(data) {
										$('#examineleave_from_update').dialog({
											closed : true,
										});
										$('#examineleave_dg').datagrid('reload');
										$('examineleave_from_update').form('reset');
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
		}]

	
	});
});
</script>

<!-- -------------------------审核的表单-----------------------------------------------------  -->
<div id="dialog_leave_update" class="easyui-dialog"
	data-options="closed:true">
	<form id="examineleave_from_update" method="post" enctype="multipart/form-data">
		
		
		<div style="display:none">
			<label for="id">id&nbsp;&nbsp;&nbsp;:</label> <input class="easyui-validatebox"
				type="text" name="id" data-options="required:true" />
		</div>
		<div>
			<label for="name">申请人:</label> <input id="leaveUsername" class="easyui-validatebox" required ="required"
				type="text" name="leaveUser.name" data-options="required:true" editable="false"/>
		</div>
		<div>
			<label for="name">请假起始时间:</label> <input class="easyui-datebox" required ="required"
				type="text" name="leaveStarttime" data-options="required:true" disabled="true"/>
		</div>
		<div>
			<label for="name">请假结束时间:</label> <input class= "easyui-datebox" required ="required"
				type="text" name="leaveEndttime" data-options="required:true" disabled="true"/>
		</div>
		<div>
			<label for="name">请假原因:</label> <input class="easyui-validatebox"
				type="text" name="reason" data-options="required:true" editable="false"/>
		</div>
		<div>
			<label for="email">审核意见:</label> <select class="easyui-combobox"
				name="leaveStatus" style="width:200px;">
				<option value="0" ></option>
				<option value="1" >通过</option>
				<option value="2">不通过</option>
				
				
			</select>
		</div>
		
		<!-- <div>
			<label for="user">分配个人:</label> <input id="userwork" name="user.id" value="">  
		</div> -->
	</form>
</div>
<!-- --------------表单结束---------------------------------------------------------------------------------- -->




</body>



</html>


		
