<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
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
		<input class="easyui-textbox" data-options="prompt:'地点'" style="width:120px" id="address"></input>
		<input class="easyui-textbox" data-options="prompt:'概要'" style="width:120px" id="overview"></input>
		<a id="btn" href="javaScript:doSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
		<a id="btn" href="javaScript:doReset()" class="easyui-linkbutton" style="float:right">清除</a>
		<h4>来自客户&nbsp;&nbsp;:&nbsp;&nbsp;${param.customerName }</h4>
	</div>
	
<!-- 添加和修改的dialog 开始 -->
	<div class="easyui-dialog" id="dialog" title=" " buttons="#buttons" closed="true">
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id"/>
			<table>
				<tr>
					<td>时间：</td>
					<td><input type="text" id="time" name="time" class="easyui-datebox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>地点：</td>
					<td><input type="text" id="address" name="address" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td>概要：</td>
					<td><input type="text" id="overview" name="overview" class="easyui-validatebox" required="true"/><font color="red">*</font></td>
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
			url:'${ctx}/customerContact/find.do?customerId=${param.customerId}',
			fit:true,
			rownumbers:true,
			toolbar:'#tools',
			fitColumns:true,
			columns:[[
			          {field:'cb',checkbox:true,align:'center'},    
					  {field:'id',title:'编号',width:80,align:'center'},    
					  {field:'time',title:'日期',width:80,align:'center'},    
					  {field:'address',title:'往来地点',width:80,align:'center'},    
					  {field:'overview',title:'概要',width:100,align:'center'},    
			          	]]
		});
	})
</script>
<script type="text/javascript">
	function doSearch(){
		var id = $("#id").val();
		var address = $("#address").val();
		var overview = $("#overview").val();
		$('#datagrid').datagrid('load',{
			id: id,
			address: address,
			overview: overview,
		});
	}
	
	function doReset(){
		$("#id").textbox('clear');
		$("#time").textbox('clear');
		$("#overview").textbox('clear');
		$("#address").textbox('clear');
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
		urlUpdateOrAdd = "add.do?customerId="+${param.customerId};
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