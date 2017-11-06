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
	<table id="datagrid"></table>
	
	<!-- 工具栏 -->
	<div id="tools" class="easyui-toolbar">
		<a href="javascript:openAddDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
		<a href="javascript:doDelete()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
		<a class="easyui-linkbutton" href="javascript:openUpdateDialog()" iconCls="icon-edit">修改</a>
		<input class="easyui-textbox" data-options="prompt:'编号'" style="width:60px" id="id"></input>
		<input class="easyui-textbox" data-options="prompt:'用户名'" style="width:120px" id="name"></input>
		<input class="easyui-textbox" data-options="prompt:'姓名'" style="width:120px" id="trueName"></input>
		<input class="easyui-textbox" data-options="prompt:'邮件'" style="width:120px" id="email"></input>
		<input class="easyui-textbox" data-options="prompt:'电话'" style="width:120px" id="phone"></input>
		<input class="easyui-textbox" data-options="prompt:'角色'" style="width:120px" id="roleName"></input>
		<a id="btn" href="javaScript:doSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
		<a id="btn" href="javaScript:doReset()" class="easyui-linkbutton" style="float:right">清除</a>
	</div>
	
<!-- 添加和修改的dialog 开始 -->
	<div class="easyui-dialog" id="dialog" title=" " buttons="#buttons" closed="true">
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id"/>
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>密码：</td>
					<td><input type="text" id="password" name="password" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td>真实姓名：</td>
					<td><input type="text" id="trueName" name="trueName" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>邮箱：</td>
					<td><input type="text" id="email" name="email" class="easyui-validatebox" required="true" validType="email"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td><input type="text" id="phone" name="phone" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>用户角色：</td>
					<td>
						<select class="easyui-combobox" id="roleName" editable="false" name="roleName" style="width:160">
							<option></option>
							<option value="系统管理员">系统管理员</option>
							<option value="销售主管">销售主管</option>
							<option value="客户经理">客户经理</option>
							<option value="高管">高管</option>
						</select>
						<font color="red">*</font></td>
				</tr>
			</table>
		</form>
	</div>
<!-- 添加和修改的dialog 结束 -->
	
	<!-- dialog下面的按钮组 -->
	<div id="buttons">
		<a href="javaScript:doSave()" class="easyui-linkbutton">保存</a>
		<a href="javaScript:closeDialog()" class="easyui-linkbutton">关闭</a>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#datagrid").datagrid({
			url:'find.do',
			pagination:true,
			pageSize:20,
			fit:true,
			rownumbers:true,
			toolbar:'#tools',
			fitColumns:true,
			columns:[[
			          {field:'cb',checkbox:true,align:'center'},    
					  {field:'id',title:'编号',width:80,align:'center'},    
					  {field:'name',title:'用户名',width:100,align:'center'},    
					  {field:'password',title:'密码',width:80,align:'center'},    
					  {field:'trueName',title:'真实姓名',width:80,align:'center'},    
					  {field:'email',title:'邮件',width:100,align:'center'},    
					  {field:'phone',title:'联系电话',width:100,align:'center'},    
					  {field:'roleName',title:'角色',width:100,align:'center'} 
			          	]]
		});
	})
</script>
<script type="text/javascript">
	function doSearch(){
		var id = $("#id").val();
		var name = $("#name").val();
		var trueName = $("#trueName").val();
		var email = $("#email").val();
		var phone = $("#phone").val();
		var roleName = $("#roleName").val();
		$('#datagrid').datagrid('load',{
			id: id,
			name: name,
			trueName: trueName,
			email: email,
			phone: phone,
			roleName: roleName
		});
	}
	
	function doReset(){
		$("#id").textbox('clear');
		$("#name").textbox('clear');
		$("#trueName").textbox('clear');
		$("#email").textbox('clear');
		$("#phone").textbox('clear');
		$("#roleName").textbox('clear');
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