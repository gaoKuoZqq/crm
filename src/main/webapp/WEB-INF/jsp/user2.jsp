<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<jsp:include page="common.jsp" />
<body>
	<table class="easyui-datagrid" id="datagrid"
	data-options="url:'find.do',pagination:true,fit:true,toolbar:'#tools',fitColumns:true">
		<thead>
			<tr>
				<th data-options="field:'cd',checkbox:true"/>
				<th data-options="field:'id'" style="width:200px">编码</th>
				<th data-options="field:'name'" style="width:200px">用户名</th>
				<th data-options="field:'trueName'" style="width:200px">姓名</th>
				<th data-options="field:'roleName'" style="width:200px">角色</th>
				<th data-options="field:'phone'" style="width:200px">电话</th>
				<th data-options="field:'email'" style="width:200px">邮箱</th>
				<th data-options="field:'password'" style="width:200px">密码</th>
			</tr>
		</thead>
	</table>
	
	<!-- 工具栏 -->
	<div id="tools" class="easyui-toolbar">
		<a href="javascript:openAddDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
		<a href="javascript:doDelete()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
		<a class="easyui-linkbutton" href="javascript:openUpdateDialog()" iconCls="icon-edit">修改</a>
		<input class="easyui-searchbox" data-options="prompt:'用户名',searcher:doSearch" style="width:200px"></input>
	</div>
	
	<!-- dialog,用于添加和修改 -->
	<div class="easyui-dialog" id="dialog" title=" " buttons="#buttons" closed="true">
		<form id="form" method="post">
			<input type="hidden" name="id" id="id"/>
			<table>
				<tr>
					<td style="width:40px;">用户名</td>
					<td style="width:150px;">
						<input name="name" class="easyui-validatebox" required="true" missingMessage="这是一个必填项" /><span style="color: red">*</span>
					</td>
					<td style="width:40px; padding-left:20px;">密码</td>
					<td style="width:150px;">
						<input name="password" class="easyui-validatebox" required="true" missingMessage="这是一个必填项" /><span style="color: red">*</span>
					</td>
				</tr>
				<tr>
					<td style="width:30px;">电话</td>
					<td style="width:150px;">
						<input name="phone" class="easyui-validatebox" required="true" missingMessage="这是一个必填项" /><span style="color: red">*</span>
					</td>
					<td style="width:40px; padding-left:20px;">角色</td>
					<td style="width:150px;">
						<select class="easyui-combobox" panelHeight="100px" name="roleName" id="roleName">
							<option>这个是角色1</option>
							<option>这个是角色2</option>
							<option>这个是角色3</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="width:30px;">姓名</td>
					<td style="width:150px;">
						<input name="trueName" class="easyui-validatebox" required="true" missingMessage="这是一个必填项" /><span style="color: red">*</span>
					</td>
					<td style="width:40px; padding-left:20px;">邮箱</td>
					<td style="width:150px;">
						<input name="email" class="easyui-validatebox" required="true" missingMessage="这是一个必填项" validType="true"/><span style="color: red">*</span>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- dialog下面的按钮组 -->
	<div id="buttons">
		<a href="javaScript:doSave()" class="easyui-linkbutton">保存</a>
		<a href="javaScript:closeDialog()" class="easyui-linkbutton">关闭</a>
	</div>
</body>
<script type="text/javascript">

</script>
<script type="text/javascript">
	function doSearch(value){
		$("#datagrid").datagrid("load",{name:value})
	}
	
	function doDelete(){
		var ids = Util.getSelectionsIds("#datagrid");
		if(ids.length == 0){
			$.messager.alert("提示","至少选择一项");
			return;
		}
		$.messager.confirm("提示","确认删除?",function(result){
			if(result){
				$.post(
					'delete.do',
					{ids : ids},
					function (news){
						$.messager.alert("提示",news.msg);
						if(news.status == Util.SUCCESS){
							$("#datagrid").datagrid("reload");
						}
					},
					"json"
				)
			}
		})
	}
	
	function closeDialog(){
		$("#dialog").dialog("close");
	}
	
	var urlUpdateOrAdd;
	
	function openAddDialog(){
		$("#form").form('clear');
		$("#dialog").dialog("open").dialog("setTitle","添加记录");
		urlUpdateOrAdd = "add.do"
	}
	
	function openUpdateDialog(){
		var selections = $("#datagrid").datagrid("getSelections");
		if(selections.length == 0){
			$.messager.alert("提示","没有被选择的记录");
			return;
		}
		var row = selections[0];
		$("#form").form("load",row);
		$("#dialog").dialog("open").dialog("setTitle","更新记录");
		urlUpdateOrAdd = "update.do"
	}
	
	function doSave(){
		$("#form").form("submit",{
			url : urlUpdateOrAdd,
			onSubmit : function (){
				if($("#roleName").combobox("getValue") == ""){
					$.messager.alert("提示","用户角色尚未选择");
					return false;
				}
				return $(this).form("validate");
			},
			success : function(news){
				var news = eval('(' + news + ')');
				$.messager.alert("提示",news.msg);
				if(news.status == Util.SUCCESS){
					$("#dialog").dialog('close');
					$("#datagrid").datagrid("reload");
				}
			}
		})
	}
</script>
</html>