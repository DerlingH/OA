<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"%>
<html>
<!-- 展示用户当前的 工作的一个jsp  用户可以通过一个按钮 提交当前的工作 -->
<body>
	<table id="mywork_dg"></table>	
	<script>
$(function(){
	$('#mywork_dg').datagrid({
    	url:'${pageContext.request.contextPath}/work/showmework.do',    
   		columns:[[
   			{field:'workname',title:'项目名',width:100},   
        	{field:'starttime',title:'项目起始时间',width:100},    
        	{field:'endtime',title:'项目完成时间',width:100},
        	{field:'workcontent',title:'项目描述',width:100},
        	{field:'status',title:'状态', width:80,
        		//这个函数太厉害了  可以判断值return  牛逼
				formatter : function(value, row, index) {
				//value 是当前的值    row是这一行的值    index是下标
					console.log(value,row,index);
					if (value == 0) {
						return "<input value=" + value + row.id + " class='switchbutton'/ >";
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
		onLoadSuccess : function(data) {
			console.log(data);
			var len = data.rows.length;
			$($('.switchbutton')).switchbutton({
				onText : '完成',
				offText : '未完成',
				onChange : function(checked) {
					console.log($(this).val());
					var status = $(this).val().charAt(0);
					var id = $(this).val().substr(1);
					console.log("id是什么"+id);
					console.log("status是什么"+status);
					$.ajax({
						url : "${pageContext.request.contextPath}/work/update.do",
						type : "post",
						data : "status=" + status + "&id=" + id,
						dataType : "json",
						success : function(data) {
							console.log("更改成功"+data);
							$('#mywork_dg').datagrid('reload');
						}
					});
				},
			});
		}

	
	});
});
</script>






</body>



</html>


		
