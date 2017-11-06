<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<table id="datagrid"></table>
	
	<!-- 工具栏 -->
	<div id="tools" class="easyui-toolbar">
		<input class="easyui-textbox" data-options="prompt:'客户名'" style="width:60px" id="customerName"></input>
		<a id="btn" href="javaScript:doSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
	</div>
<script type="text/javascript">

	$(function(){
		$("#datagrid").datagrid({
			url:'find.do',
			pagination:true,
			pageSize:20,
			fit:true,
			rownumbers:true,
			toolbar:'#tools',
			fitColumns : true,
			columns:[[
					  {field:'customerName',title:'客户名称',width:1000,align:'center'},    
					  {field:'consumption',title:'服务类型',width:500,align:'center'},    
			          	]]
		});
	})
	
	function doReset(){
		$("#customerName").textbox('clear');
	}
	
	function doSearch(){
		var customerName = $("#customerName").val();
		$('#datagrid').datagrid('load',{
			customerName: customerName
		});
	}
	
</script>
</html>