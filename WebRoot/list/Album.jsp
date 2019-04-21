
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

 <!-- 内部资料 -->
    <script>
    	var audio ='';
    	
    	 //展示所有
    	 $('#reegrid_audio').treegrid({    
			url:'${pageContext.request.contextPath}/album/getAll.do',    
			idField:'id',    
			treeField:'title',
			width:1580,
			height:750,
			fitColumns:true,    
			columns:[[    
			    {title:'专辑名称',field:'title',width:250},    
			    {field:'id',title:'Persons',width:60,align:'right',hidden:true},    
			    {field:'duration',title:'章节时长',width:250},    
			    {field:'size',title:'章节大小',width:250},
			    {field:'url',title:'下载路径',width:250}    
			]],
			//用户双击时候触发
			 onDblClickRow:function(){
			 	var root =  $('#reegrid_audio').treegrid('getSelected');
			 	console.log(root);
			  	if(typeof(root.children)=='undefined'&&audio==''){
			  		$("#dialog_audio_add").prop("src","${pageContext.request.contextPath}/"+root.url+"");
			  		$('#dialog_audio_add').dialog({    
					    title: '在线播放',    
					    width: 400,    
					    height: 80,    
					    closed: false,    
					    modal: true   
					});    
				 }
			 	/* console.log(this);
			 	$(this).append(audio); */
			  /*<audio controls="controls">
				  <source src="song.ogg" type="audio/ogg">
				  <source src="song.mp3" type="audio/mpeg">
				</audio> 
				autoplay autoplay 如果出现该属性，则音频在就绪后马上播放。*/
				
			},	 
			toolbar: [{
				iconCls: 'icon-edit',
				text:'专辑详情',
				handler: function(){
					var root =  $('#reegrid_audio').treegrid('getSelected');
					if(root!=null&&root!=''&&typeof(root.children)!='undefined'){
						$("#dialog_album_form img").prop("src","${pageContext.request.contextPath}"+"/img/"+root.coverImg);
						$('#dialog_album_form').form('load',root);
						$('#dialog_album_form').dialog({    
						    title: root.title+'详情',
						    width: 300,    
						    height: 500,    
						    closed: false,    
						    modal: true,
						    //href:'${pageContext.request.contextPath}/img/1.gif',
						});
					}else{
						$.messager.alert('您好','请选中专辑信息');
					}
				}
			},'-',{
				iconCls: 'icon-add',
				text:'添加专辑',
				handler: function(){
					$('#dialog_album_add_form').dialog({    
					    title: '添加专辑',    
					    width: 250,    
					    height: 280,    
					    closed: false,    
					    /* cache: false,    
					    href: 'get_content.php',    */ 
					    modal: true,
					    buttons:[{
							text:'添加',
							iconCls: 'icon-add',
							handler:function(){
								$('#dialog_album_add_form').form('submit', {
									url: "${pageContext.request.contextPath}/album/add.do",
									success: function(data){
										$('#dialog_album_add_form').dialog({
											closed:true,
										});
										$('#reegrid_audio').treegrid('reload');
										$('#dialog_album_add_form').form('reset');
										$.messager.show({
											title:'提示',
											msg:'添加成功',
											timeout:5000,
											showType:'slide'
										});

									}
								});
							}
						}],
					});  
				}
			},'-',{
			
			//添加章节
				iconCls: 'icon-add',
				text:'添加章节',
				handler: function(){
					$('#dialog_chapter_add_form').dialog({    
					    title: '添加章节',    
					    width: 250,    
					    height: 280,    
					    closed: false,    
					    /* cache: false,    
					    href: 'get_content.php',    */ 
					    modal: true,
					    buttons:[{
							text:'添加',
							iconCls: 'icon-add',
							handler:function(){
								$('#dialog_chapter_add_form').form('submit', {
									url: "${pageContext.request.contextPath}/chapter/addChapter.do",
									success: function(data){
										$('#dialog_chapter_add_form').dialog({
											closed:true,
										});
										$('#reegrid_audio').treegrid('reload');
										$('#dialog_chapter_add_form').form('reset');
										$.messager.show({
											title:'提示',
											msg:'添加成功',
											timeout:5000,
											showType:'slide'
										});
									}
								});
							}
						}],
					});
				}
			},'-',{
			
			//下载
				iconCls: 'icon-help',
				text:'下载音频',
				handler: function(){
					var root =  $('#reegrid_audio').treegrid('getSelected');
					if(root!=null&&root!=''&&typeof(root.children)=='undefined'){
						console.log(root.url);
						console.log(root.title);
						location.href="${pageContext.request.contextPath}/chapter/download.do?"+"url="+root.url;
						
				  	}else{
				  		 alert("请选中要下载的章节");
				  	}
						
				}
			},'-', {
			
			//导出相关
			
			iconCls : 'icon-help',
			text : "导出",
			//类型function，用于处理点击该图标以后的动作
			handler : function() {
			
			//点击之后自动调用 controller 开始下载
			location.href="${pageContext.request.contextPath}/poi/poiOutalbum"; 
			
			}
		}]
		});
		
    </script>
 
<!-- 添加章节 -->   
<div id="dialog_chapter_add" class="easyui-dialog" data-options="closed:true">

	<form id="dialog_chapter_add_form" method="post" enctype="multipart/form-data">   
	    <div>   
	        <label for="name">章节名称:</label>   
	        <input class="easyui-validatebox" type="text" name="title" data-options="required:true" />   
	    </div>   
	    <div>   
	        <label for="email">上传音频:</label>   
	        <input class="easyui-filebox" type="text" name="pic" data-options="required:true" />   
	    </div>
	    <div>   
	        <label for="email">添加时间:</label>   
	        <input class="easyui-datebox" type="text" name="pubDate" data-options="required:true" />   
	    </div>
	    <div>
	    	 <label for="email">所属专辑</label>
	    		<input name="album.id" class="easyui-combobox" data-options="    
			        valueField: 'id',    
			        textField: 'title',
			        required:true,    
			        url: '${pageContext.request.contextPath}/album/getAll.do',    
			        " 
			     /> 
	    </div>    
	</form>  
</div>  
    
    
    
<!--  -->
<div id="dialog_audio" style="display: none;">
	<audio id="dialog_audio_add" src="" autoplay="autoplay" controls="controls" loop="loop"></audio>
</div>  

<!-- 增加音频模块  --> 
<div id="dialog_album_add" class="easyui-dialog" data-options="closed:true">
	<form id="dialog_album_add_form" method="post" enctype="multipart/form-data">   
	    <div>   
	        <label for="name">专辑名称:</label>   
	        <input class="easyui-validatebox" type="text" name="title" data-options="required:true" />   
	    </div>   
	    <div>   
	        <label for="email">上传图片:</label>   
	        <input class="easyui-filebox" type="text" name="pic" data-options="required:true" />   
	    </div>
	    <div>   
	        <label for="email">作者:</label>   
	        <input class="easyui-validatebox" type="text" name="author" data-options="required:true" />   
	    </div>
	    <div>   
	        <label for="email">播音员:</label>   
	        <input class="easyui-validatebox" type="text" name="broadcaster" data-options="required:true" />   
	    </div> 
	     <div>   
	        <label for="email">星级:</label>   
	        <input class="easyui-numberspinner" type="text" name="score" data-options="required:true,max:5,min:1" />   
	    </div> 
	     <div>   
	        <label for="email">专辑数量:</label>   
	        <input class="easyui-numberspinner" type="text" name="count" data-options="required:true,min:0" />   
	    </div>
	    <div>   
	        <label for="email">专辑描述:</label>   
	        <input class="easyui-validatebox" type="text" name="desc" data-options="required:true" />   
	    </div>
	    <div>   
	        <label for="email">添加时间:</label>   
	        <input class="easyui-datebox" type="text" name="pubDate" data-options="required:true" />   
	    </div>    
	</form>  
</div>  



<!-- 表格  -->
<table id="reegrid_audio" style="width:600px;height:400px"></table> 

<!-- 专辑详情 -->
<div id="dialog_album" class="easyui-dialog" data-options="closed: true">
	<form id="dialog_album_form" method="post" >
		<table>
			<tr>
				<td>图像</td>
				<td><img name="coverImg" src=""/></td>
			</tr>
			<tr>
				<td>专辑标题</td>
				<td><input class="easyui-validatebox" type="text" name="title" data-options="required:true,readonly:true" /></td>
			</tr>
			<tr>
				<td>作者</td>
				<td><input class="easyui-validatebox" type="text" name="author" data-options="required:true,readonly:true" /></td>
			</tr>
			<tr>
				<td>播音</td>
				<td><input class="easyui-validatebox" type="text" name="broadcaster" data-options="required:true,readonly:true" /></td>
			</tr>
			<tr>
				<td>集数</td>
				<td><input class="easyui-validatebox" type="text" name="count" data-options="required:true,readonly:true" /></td>
			</tr>
			<tr>
				<td>星级</td>
				<td><input class="easyui-validatebox" type="text" name="score" data-options="required:true,readonly:true" /></td>
			</tr>
			<tr>
				<td>日期</td>
				<td><input class="easyui-validatebox" type="text" name="pubDate" data-options="required:true,readonly:true" /></td>
			</tr>
			<tr>
				<td>描述</td>
				<td><input class="easyui-validatebox" type="text" name="desc" data-options="required:true,readonly:true" /></td>
			</tr>
		</table>
	</form>    
</div>    