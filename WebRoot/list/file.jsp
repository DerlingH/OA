<%@page isELIgnored="false" pageEncoding="UTF-8"
	contentType="text/html; UTF-8"%>
<html>

<body>
	<!-- 树形表格展示文件 -->
	<table id="datagrid_file"></table> 
	<!-- 文件按钮组 -->
	<div id="toolbar_file">
		<a href="#">添加文件</a>
		<a href="#">下载文件</a>
		<a href="#">删除文件</a>
	</div>
	<!-- 添加文件-添加按钮 -->
	<div id="toolbar_file_add">
		<a href="#">确定添加</a>
	</div>
	
	<!-- 添加文件 -->
	<div id="dialog_file_add">
		<form id="fm_file_add" action="${pageContext.request.contextPath }/file/addFile" method="post" enctype="multipart/form-data">
			<table ><!-- align="center" -->
				<tr>
					<td><input type="text" name="upFile" id="ipt_file"/></td>
				</tr>
			</table>
		</form>
	</div>
<!-- ---------分割------------------------------------------------------------------------------------ -->
	<script type="text/javascript">
		//添加文件框样式
		$("#ipt_file").filebox({
			width:200,
			buttonText:'选择文件',
		});
		
		
		//选中主体展示
		$('#datagrid_file').treegrid({    
		    url:'${pageContext.request.contextPath}/file/selectAllFile',    
		    idField:'id',    
		    treeField:'name',
		    columns:[[    
		    	{title:'文件名称',field:'fileName',width:200}, 
		        {field:'fileUploadtime',title:'创建时间',width:200},    
		        {field:'fileSize',title:'文件大小',width:200},    
		        {field:'fileSavepath',title:'下载路径',width:550}    
		    ]],
		    //选中自己的按钮组
		    toolbar:'#toolbar_file',
		    fitColumns : true
		}); 
		//添加文件dialog
		$("#dialog_file_add").dialog({
				title:'添加文件',
				width: 400,    
			   //height: 100,    
			    closed: true,    
			    modal: true,
			    buttons:'#toolbar_file_add',
			});
		
//-----------------------------文件按钮组---------------------------------------------------------------------
		$("#toolbar_file>a:contains(添加文件)").linkbutton({
			iconCls:'icon-edit',
			onClick:function(){
				$('#dialog_file_add').dialog({
					closed: false,
				});
			},
		});
		$("#toolbar_file>a:contains(下载文件)").linkbutton({
			iconCls:'icon-save',
			onClick:function(){
				var r = $('#datagrid_file').treegrid('getSelected');
				if(r == null){
					$.messager.show({
						title:'提示',
						msg:'请选择文件',
					});
				}else{
					window.location.href="${pageContext.request.contextPath}/file/downFile?fileId="+r.id;
				}
			},
		});
		$("#toolbar_file>a:contains(删除文件)").linkbutton({
			iconCls:'icon-remove',
			onClick:function(){
				var r = $('#datagrid_file').treegrid('getSelected');
				//获得文件在服务器中的路径
				if(r == null){
					$.messager.show({
						title:'提示',
						msg:'请选择文件',
					});
				}else{
					$.ajax({
						url:'${pageContext.request.contextPath}/file/deleteFile?fileId='+r.id,
						type:'post',
						dataType:'JSON',
						success:function(data){
							if(data.code==200){
								$('#datagrid_file').treegrid('reload');	// 重新载入所有行
								$.messager.show({
									title:'提示框',
									msg:data.msg,
								});
							}
						}
					});
				}
			},
		});
		
		//添加文件linkbutton
		$("#toolbar_file_add>a:contains(确定添加)").linkbutton({
			onClick:function(){
				$('#dialog_file_add').dialog({
					closed: true,
				});
				$("#fm_file_add").form('submit',{
					success:function(data){
						//清空表单内容
						$("#fm_file_add").form('clear');
						$("#dialog_file_add").dialog({
							closed:true,
						});
						$('#datagrid_file').treegrid('reload');	// 重新载入所有行
						$.messager.show({
							title:'提示框',
							msg:JSON.parse(data).msg,
						});
					}
				});
			},
		})
	</script>
</body>
</html>