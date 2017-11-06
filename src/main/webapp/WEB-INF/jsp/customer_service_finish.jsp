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
		<input class="easyui-textbox" data-options="prompt:'编号'" style="width:60px" id="id"></input>
		<input class="easyui-textbox" data-options="prompt:'类型'" style="width:60px" id="serviceType"></input>
		<input class="easyui-textbox" data-options="prompt:'客户'" style="width:120px" id="customer"></input>
		<input class="easyui-textbox" data-options="prompt:'请求'" style="width:120px" id="serviceRequest"></input>
		<a id="btn" href="javaScript:doReset()" class="easyui-linkbutton" style="float:right">清除</a>
		&nbsp;&nbsp;创建于:<input  id="startTime"  type= "text" class= "easyui-datebox" required ="required"> </input>--
		<input  id="endTime"  type= "text" class= "easyui-datebox" required ="required"> </input>
		<a id="btn" href="javaScript:doSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
	</div>
<script type="text/javascript">
	/* $("#endTime").datebox.defaults.formatter = function(date){
		return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
	} */

	$(function(){
		$("#datagrid").datagrid({
			url:'find.do?status=4',
			pagination:true,
			pageSize:20,
			fit:true,
			rownumbers:true,
			toolbar:'#tools',
			columns:[[
					  {field:'id',title:'编号',width:80,align:'center'},    
					  {field:'serviceType',title:'服务类型',width:100,align:'center'},    
					  {field:'customer',title:'客户',width:80,align:'center'},    
					  {field:'serviceRequest',title:'服务请求',width:80,align:'center'},    
					  {field:'overview',title:'概要',width:100,align:'center'}, 
					  {field:'createTime',title:'创建时间',width:100,align:'center'}, 
					  {field:'assigner',title:'负责人',width:100,align:'center'},
					  {field:'assignTime',title:'分配时间',width:100,align:'center'},
					  {field:'serviceDeal',title:'处理方式',width:100,align:'center'},
					  {field:'serviceDealTime',title:'处理时间',width:100,align:'center'},
					  {field:'serviceDealResult',title:'处理结果',width:100,align:'center'},
					  {field:'satisfy',title:'评价',width:100,align:'center'}
			          	]]
		});
	})
	
	function doReset(){
		$("#id").textbox('clear');
		$("#customer").textbox('clear');
		$("#serviceRequest").textbox('clear');
		$("#serviceType").textbox('clear');
		$("#startTime").databox('clear');
		$("#endTime").databox('clear');
	}
	
	function doSearch(){
		var id = $("#id").val();
		var customer = $("#customer").val();
		var serviceRequest = $("#serviceRequest").val();
		var serviceType = $("#serviceType").val();
		var endTime = $("#endTime").val();
		var startTime = $("#startTime").val();
		$('#datagrid').datagrid('load',{
			id: id,
			customer: customer,
			serviceRequest: serviceRequest,
			serviceType: serviceType,
			startTime : startTime,
			endTime : endTime
		});
	}
	
</script>
</html>