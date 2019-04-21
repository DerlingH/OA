<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"%>
<html>
<!-- 展示用户当前的 工作的一个jsp  用户可以通过一个按钮 提交当前的工作 -->
<body>
	<table id="allwork_dg"></table>	
	<script>
$(function(){
	$('#allwork_dg').datagrid({
    	url:'${pageContext.request.contextPath}/work/showallwork.do',    
   		columns:[[
   			{field:'workname',title:'项目名',width:100}, 
        	{field:'starttime',title:'项目起始时间',width:100},    
        	{field:'endtime',title:'项目完成时间',width:100,
        		formatter: function(value,row,index){
        			if(value != null){
	        			return value;
        			}else{
        				return "未完成";
        			}
        			
        		}
        	
        	},
        	{field:'workcontent',title:'项目描述',width:100},
        	/*为什么找不到这两个属性了？  */
        	{field:'dept',title:'负责部门',width:100,
        		formatter: function(value,row,index){
        			console.log(row.dept.deptname);
        			return row.dept.deptname;
        		
        		}
        	
        	},
        	{field:'user',title:'负责人',width:100,
        		formatter: function(value,row,index){
        			if(value!=null){
        				console.log(row.user.name);
        			return row.user.name;
        			}else{
        			
        			return "未指定";
        			}
        			
        		}
        	},
        	{field:'status',title:'状态', width:80,
        		//这个函数太厉害了  可以判断值return  牛逼
				formatter : function(value, row, index) {
				//value 是当前的值    row是这一行的值    index是下标
					//console.log(value,row,index);
					if (value == 0) {
						return "未完成";
					} else {
						//return "<input value=" + value + row.id + " class='switchbutton'/>";
						return "已完成";
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
			text : '发布工作',
			handler : function() {
				$('#work_ff').dialog({
					title : '又有活干咯',
					inonCls : 'icon-add',
					width : 300,
					height : 200,
					closed : false,
					modal : true,
					buttons : [ {
						text : '发布',
						handler : function() {
							$('#work_ff').form('submit', {
								url : '${pageContext.request.contextPath}/work/add.do',
								success : function(data) {
									$('#work_ff').dialog({
										closed : true,
									});
									/* 重新加载整个表格  */
									$('#allwork_dg').datagrid('reload');
									$('#work_ff').form('reset');
									$.messager.show({
										title : '提示',
										msg : '发布成功',
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
//-------按钮结束----------------------------------------------------------------------------------------------------------
	
	});
	
	/* $('#userwork').combobox({    
    url:'${pageContext.request.contextPath}/user/showall.do',    
    valueField:'id',    
    textField:'name'   
	});  */ 
});
</script>

<!-- -------------------------添加的表单-----------------------------------------------------  -->
<div id="dialog_work_add" class="easyui-dialog"
	data-options="closed:true">
	<form id="work_ff" method="post" enctype="multipart/form-data">
		<div>
			<label for="name">项目名称:</label> <input class="easyui-validatebox"
				type="text" name="workname" data-options="required:true" />
		</div>
		<div>
			<label for="name">项目说明:</label> <input class="easyui-validatebox"
				type="text" name="workcontent" data-options="required:true" />
		</div>
		<div>
			<label for="name">分配部门:</label> <select class="easyui-combobox"
				 name="dept.id"  style="width:200px;">
				 <option value="1">Java开发部门</option>
				 <option value="2">前端部门</option>
				 <option value="3">产品部门</option>
				 <option value="4">测试部门</option>
			</select>
		</div>
		<!-- <div>
			<label for="user">分配个人:</label> <input id="userwork" name="user.id" value="">  
		</div> -->
	</form>
</div>

<!-- ----------------添加表单结束-------------------------------------------------------------------------------------- -->


</body>



</html>


		
