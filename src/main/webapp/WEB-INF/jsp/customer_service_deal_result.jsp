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
		<a href="javaScript:openDialog()" class="easyui-linkbutton">提交反馈</a>
	</div>
	
<!-- 添加和修改的dialog 开始 -->
	<div class="easyui-dialog" id="dialog" title=" " buttons="#buttons" closed="true">
		<form action="" id="form" method="post">
			<input type="hidden" id="dealId" name="id"/>
			<table>
				<tr>
					<td>服务类型：</td>
					<td><input readOnly='true' id="serviceType" name="serviceType" class="easyui-validatebox" required="true"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>客户：</td>
					<td><input readOnly='true' id="customer" name="customer" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>概要：</td>
					<td colspan='4'>
						<input readOnly='true' id="overview" name="overview" class="easyui-validatebox" required="true" style="width:400px;"/>
					</td>
				</tr>
				<tr>
					<td>服务请求：</td>
					<td colspan='4'>
						<input readOnly='true' id="serviceRequest" name="serviceRequest" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<td>创建人：</td>
					<td>
						<input readOnly='true' id="createPeople" name="createPeople" class="easyui-validatebox" required="true"/>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>创建日期：</td>
					<td>
						<input readOnly='true' id="createTime" name="createTime" class="easyui-validatebox" required="true"/></td>
					</td>
				</tr>
				<tr>
					<td>负责人：</td>
					<td>
						<input readOnly='true' id="assigner" name="assigner" class="easyui-validatebox" required="true"/>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>分配日期：</td>
					<td>
						<input readOnly='true' id="assignTime" name="assignTime" class="easyui-validatebox" required="true"/></td>
					</td>
				</tr>
				<tr>
					<td>服务处理:</td>
					<td colspan='4'>
					<input id="service_deal" name="serviceDeal" class="easyui-textbox" data-options="multiline:true" style="width:400px;"/> 
					</td>
				</tr>
				<tr>
					<td>处理结果反馈:</td>
					<td colspan='2'>
						<input id="service_deal_result" class="easyui-textbox" data-options="multiline:true" style="width:400px;"/> 
					</td>
					<td colspan='2'>
						<select id="satisfy" class="easyui-combobox"
							name="satisfy">
								<option>☆</option>
								<option>☆☆</option>
								<option>☆☆☆</option>
								<option>☆☆☆☆</option>
								<option>☆☆☆☆☆</option>
						</select>
					</td>
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

	function doSave(){
		var serviceDealResult = $("#service_deal_result").val();
		var satisfy = $("#satisfy").val();
		if(serviceDealResult == "" || satisfy == ""){
			alert("请完整填写表单");
			return;
		}
		options = {
				url : '${ctx}/customerService/update.do',
				data : {
					id : $("#dealId").val(),
					serviceDealResult : serviceDealResult,
					satisfy : satisfy,
					status : 4
				},
				dataType : 'json',
				success : function(news){
					alert(news.msg);
					if(news.status == Util.SUCCESS){
						$("#dialog").dialog('close');
						$("#datagrid").datagrid("reload");
					}
				}
		}
		$.ajax(options);
	}
	
	function closeDialog(){
		$("#dialog").dialog("close");
	}
	
	function openDialog(){
		var selections = $("#datagrid").datagrid("getSelections");
		if(selections.length == 0){
			$.messager.alert("提示","没有被选择的记录");
			return;
		}
		var row = selections[0];
		$("#form").form("load",row);
		$("#dialog").dialog("open").dialog("setTitle","处理");
	}
	
	$(function(){
		$("#datagrid").datagrid({
			url:'find.do?status=3',
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
					  {field:'serviceDeal',title:'处理方式',width:100,align:'center'},
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
	
</script>
</html>