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
		<a id="btn" href="javaScript:doSearch()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
		<a id="btn" href="javaScript:doReset()" class="easyui-linkbutton" style="float:right">清除</a>
		<br/>
		分配给 : <input class="easyui-combobox" id="assigner" name="assigner" data-options="panelHeight:'auto',editable:false,valueField:'trueName',textField:'trueName',url:'${ctx}/user/getTrueNameList.do'"/>
		<a href="javaScript:assign()" class="easyui-linkbutton">分配</a>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#datagrid").datagrid({
			url:'find.do?status=1',
			pagination:true,
			pageSize:20,
			fit:true,
			rownumbers:true,
			toolbar:'#tools',
			fitColumns:true,
			columns:[[
					  {field:'cb',checkbox:true,align:'center'},
					  {field:'id',title:'编号',width:80,align:'center'},    
					  {field:'serviceType',title:'服务类型',width:100,align:'center'},    
					  {field:'customer',title:'客户',width:80,align:'center'},    
					  {field:'serviceRequest',title:'服务请求',width:80,align:'center'},    
					  {field:'overview',title:'概要',width:100,align:'center'},    
					  {field:'createTime',title:'创建时间',width:100,align:'center'},    
			          	]]
		});
	})
	
	function doReset(){
		$("#id").textbox('clear');
		$("#customer").textbox('clear');
		$("#serviceRequest").textbox('clear');
		$("#serviceType").textbox('clear');
	}
	
	function doSearch(){
		var id = $("#id").val();
		var customer = $("#customer").val();
		var serviceRequest = $("#serviceRequest").val();
		var serviceType = $("#serviceType").val();
		$('#datagrid').datagrid('load',{
			id: id,
			customer: customer,
			serviceRequest: serviceRequest,
			serviceType: serviceType,
		});
	}
	
	function assign(){
		var ids = Util.getSelectionsIds("#datagrid");
		if(ids.length == 0){
			$.messager.alert("提示","至少选择一项");
			return;
		}
		var assigner = $("#assigner").val();
		if(assigner == ""){
			$.messager.alert("提示","未指定分配人");
			return;
		}
		$.messager.confirm("提示","确认分配?",function(result){
			if(result){
				$.post(
					'assign.do',
					{
					ids : ids,
					assigner : assigner,
					assignTime : Util.getCurrentDateTime()
					},
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
</script>
</html>