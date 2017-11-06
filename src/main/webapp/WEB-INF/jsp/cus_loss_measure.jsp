<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	//查询指定id的销售机会
	$.post("${ctx}/customerLoss/findById.do?id=${param.id}", 
			{id : '${param.saleChanceId}'}, 
			function(result) {
					$("#customerName").val(result.customerName);
					$("#customerManager").val(result.customerManager);
					$("#lastOrderTime").val(result.lastOrderTime);
					$("#confirmLossTime").val(result.confirmLossTime);
					$("#lossReason").val(result.lossReason);
			}, 
			"json");
	
	/*展示数据的datagrid表格*/
	$("#datagrid").edatagrid({
		url:'${ctx}/customerLossMeasure/find.do?lossId=${param.id}',//只查询已分配咨询师的
		saveUrl:'${ctx}/customerLossMeasure/add.do?lossId=${param.id}',
		updateUrl:'${ctx}/customerLossMeasure/update.do?lossId=${param.id}',
		destroyUrl:'${ctx}/customerLossMeasure/delete.do',
		title:'开发计划项',
		singleSelect:true,
		toolbar:'#toolbar',
		rownumbers:true,
		fitColumns:true,
		columns:[[    
		     {field:'id',title:'编号',width:50,align:'center'},    
		     {field:'measure',title:'暂缓措施',width:500,align:'center',editor:{type:'validatebox',options:{required:true}}}  
		]]
	});
});

//更新确认流失
function confirmLoss(){
	 $.post("${ctx}/customerLoss/updateConfirmLoss.do?show='false'",
			 {id:'${param.id}'},
			 function(result){
				 if(result.status == Util.SUCCESS){
					 $.messager.alert("系统提示","执行成功！");
				 }else{
					 $.messager.alert("系统提示","执行失败！");
				 }
	 		},
	 		"json");
 }
</script>
</head>
<body>
	<!-- 营销机会信息面板  开始 -->
	<div id="p" class="easyui-panel" title="销售机会信息" style="width: 700px;height: 200px;padding: 10px">
	 	<table cellspacing="8px">
	   		<tr>
	   			<td>客户名称：</td>
	   			<td><input type="text" id="customerName" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>客户经理</td>
	   			<td><input type="text" id="customerManager"  readonly="readonly"/></td>
	   		</tr>
	   		<tr>
	   			<td>上次下单日期：</td>
	   			<td><input type="text" id="lastOrderTime" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   		</tr>
	   		<tr>
	   			<td>流失原因：</td>
	   			<td><input type="text" id="lossReason"/></td>
	   			<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td><input type="button" value="上传流失原因" onclick="saveLossReason()"/></td>
	   		</tr>
	   	</table>
	 </div>
	 <!-- 营销机会信息面板  结束  -->
	 
	<!-- 客户开发计划项table -->
	<table id="datagrid"></table>
	
	<!-- toolbar 开始 -->
	 <div id="toolbar">
	 	<c:if test="${param.show!='true' }">
		 	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#datagrid').edatagrid('addRow')">添加计划</a>
		 	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#datagrid').edatagrid('destroyRow')">删除计划</a>
		 	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#datagrid').edatagrid('saveRow');$('#datagrid').edatagrid('reload')">保存计划</a>
		 	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#datagrid').edatagrid('cancelRow')">撤销行</a>
		 	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-zzkf" plain="true" onclick="confirmLoss()">确认流失</a>
	 	</c:if>
	 </div>
	<!-- toolbar 结束 -->
	
	<script type="text/javascript">
		function saveLossReason(){
			var reason = $("#lossReason").val();
			options = {
					url : '${ctx}/customerLoss/update.do',
					data : {
						'id' : ${param.id},
						'lossReason' : reason
					},
					dataType : 'json',
					success : function(){
						$.messager.alert('提示','信息已保存'); 
					}
			}
			$.ajax(options);
		}
	</script>


</body>
</html>