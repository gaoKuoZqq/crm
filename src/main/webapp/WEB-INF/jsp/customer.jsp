<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="common.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
	<table id="datagrid"></table>

	<!-- 工具栏 -->
	<div id="tools" class="easyui-toolbar">
		<a href="javascript:openAddDialog()" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">添加</a> <a
			href="javascript:doDelete()" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a> <a
			class="easyui-linkbutton" href="javascript:openUpdateDialog()"
			iconCls="icon-edit">修改</a> <input class="easyui-textbox"
			data-options="prompt:'编号'" style="width: 60px" id="id"></input> <input
			class="easyui-textbox" data-options="prompt:'用户名'"
			style="width: 120px" id="name"></input> <a id="btn"
			href="javaScript:doSearch()" class="easyui-linkbutton"
			data-options="iconCls:'icon-search'">搜索</a>
			<a href="javaScript:openLinkmanTab()" class="easyui-linkbutton">客户联系人</a>
			<a href="javaScript:openContactTab()" class="easyui-linkbutton">交往记录管理</a>
			<a href="javaScript:openOrderDialog()" class="easyui-linkbutton">历史订单查看</a>
			 <a id="btn"
			href="javaScript:doReset()" class="easyui-linkbutton"
			style="float: right">清除</a>
	</div>
	
	<script type="text/javascript">
		function getFirstSelectedRow(){
			var selections = $("#datagrid").datagrid("getSelections");
			if(selections.length == 0){
				$.messager.alert("提示","没有被选择的记录");
				return;
			}
			var row = selections[0];
			return row;
		}
		function openLinkmanTab(){
			var row = getFirstSelectedRow();
			window.parent.openTab(row.name + '联系人',"${ctx}/customerLinkman/index.do?customerId="+row.id+"&customerName="+row.name+"",'icon-khkfjh');
		}
		
		function openContactTab(){
			var row = getFirstSelectedRow();
			window.parent.openTab(row.name + '往来',"${ctx}/customerContact/index.do?customerId="+row.id+"&customerName="+row.name+"",'icon-khkfjh');
		}
	</script>
	
	<!-- 添加和修改的dialog 开始 -->
	<div class="easyui-dialog" id="dialog" title=" " buttons="#buttons"
		closed="true">
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id" />
			<table cellspacing="8px">
				<tr>
					<td>客户名称：</td>
					<td><input typr="text" id="name" name="name"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>地区：</td>
					<td><select type="text" id="region" name="region"
						class="easyui-combobox" panelHeight="auto" editable="false"
						required="true" style="width: 160px">
							<option value="北京">北京</option>
							<option value="上海">上海</option>
							<option value="南京">南京</option>
							<option value="广州">广州</option>
							<lect>
							<font color="red">*</font></td>
				</tr>
				<tr>
					<td>客户经理：</td>
					<td><input type="text" id="managerName" name="managerName"
						class="easyui-combobox"
						data-options="panelHeight:'auto',editable:false,valueField:'trueName',textField:'trueName',url:'${ctx}/user/getTrueNameList.do'" /><font
						color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>客户等级：</td>
					<td><input type="text" id="level" name="level"
						class="easyui-combobox"
						data-options="panelHeight:'auto',required:true,editable:false,valueField:'dataDicValue',textField:'dataDicValue',url:'${ctx}/dataDic/getDataDicValueList.do?dataDicName=客户等级'" /><font
						color="red">*</font></td>
				</tr>
				<tr>
					<td>客户满意度：</td>
					<td><select type="text" id="satisfy" name="satisfy"
						class="easyui-combobox" panelHeight="auto" editable="false"
						required="true" style="width: 160px">
							<!-- <option value="">请选择...</option> -->
							<option value="☆">☆</option>
							<option value="☆☆">☆☆</option>
							<option value="☆☆☆">☆☆☆</option>
							<option value="☆☆☆☆">☆☆☆☆</option>
							<option value="☆☆☆☆☆">☆☆☆☆☆</option>
							<lect>
							<font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>客户信用度：</td>
					<td><select type="text" id="credit" name="credit"
						class="easyui-combobox" panelHeight="auto" editable="false"
						required="true" style="width: 160px">
							<!-- <option value="">请选择...</option> -->
							<option value="☆">☆</option>
							<option value="☆☆">☆☆</option>
							<option value="☆☆☆">☆☆☆</option>
							<option value="☆☆☆☆">☆☆☆☆</option>
							<option value="☆☆☆☆☆">☆☆☆☆☆</option>
							<lect>
							<font color="red">*</font></td>
				</tr>
				<tr>
					<td>邮政编码：</td>
					<td><input type="text" id="postCode" name="postCode"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>联系电话：</td>
					<td><input typr="text" id="phone" name="phone"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td>传真：</td>
					<td><input type="text" id="fax" name="fax"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>网址：</td>
					<td><input typr="text" id="webSite" name="webSite"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td>客户地址：</td>
					<td colspan="4"><input type="text" id="address" name="address"
						style="width: 420px" class="easyui-validatebox" required="true" /><font
						color="red">*</font></td>
				</tr>
				<tr>
					<td>营业执照注册号：</td>
					<td><input type="text" id="licenceNo" name="licenceNo"
						class="easyui-validatebox" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>法人：</td>
					<td><input typr="text" id="legalPerson" name="legalPerson"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td>注册资金(万元)：</td>
					<td><input type="text" id="bankroll" name="bankroll"
						class="easyui-validatebox" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>年营业额(万元)：</td>
					<td><input typr="text" id="turnover" name="turnover"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td>开户银行：</td>
					<td><input type="text" id="bankName" name="bankName"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>开户账户：</td>
					<td><input typr="text" id="bankAccount" name="bankAccount"
						class="easyui-validatebox" required="true" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td>地税登记号：</td>
					<td><input type="text" id="localTaxNo" name="localTaxNo"
						class="easyui-validatebox" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>国税登记号：</td>
					<td><input typr="text" id="nationalTaxNo" name="nationalTaxNo"
						class="easyui-validatebox" /></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 添加和修改的dialog 结束 -->

	<!-- dialog下面的按钮组 -->
	<div id="buttons">
		<a href="javaScript:doSave()" class="easyui-linkbutton">保存</a> <a
			href="javaScript:closeDialog()" class="easyui-linkbutton">关闭</a>
	</div>
	
	<!-- 订单的panel 开始 -->
	<div id="order_dialog">
		<div id="order_datagrid"></div>
	</div>
	<!-- 订单的panel 结束 -->
	
	<!-- 订单item的panel 开始 -->
	<div id="order_item_dialog">
		<div id="order_item_datagrid"></div>
	</div>
	<!-- 订单的panel 结束 -->
</body>
<script type="text/javascript">

	$("#order_item_dialog").dialog({
		title : '订单分项',
		width : 300,
		height : 300,
		closed : true
	});

	$("#order_item_datagrid").datagrid({
		url : '${ctx}/orderItem/find.do',
		fit : true,
		fitColumns : true,
		columns : [[{
			field : 'productName',
			title : '商品名称',
			align : 'center'
					},{
			field : 'productNum',
			title : '商品数量',
			align : 'center'
					},{
			field : 'unit',
			title : '单位',
			align : 'center'
					},{
			field : 'price',
			title : '价格',
			align : 'center'
					},{
			field : 'sum',
			title : '总金额',
			align : 'center'
					}
		            ]]
	});
	function openOrderItemDialog(order_id){
		$("#order_item_dialog").dialog("open");
		$('#order_item_datagrid').datagrid('load', {
			orderId : order_id
		});
	}
	$("#order_dialog").dialog({
		title : '订单',
		width : 400,
		height : 300,
		closed : true
	});
	
	$("#order_datagrid").datagrid({
		url : '${ctx}/customerOrder/find.do',
		fit : true,
		fitColumns : true,
		columns : [[{
			field : 'orderNo',
			title : '订单编号',
			align : 'center'
					},{
			field : 'orderDate',
			title : '订购日期',
			align : 'center'
					},{
			field : 'address',
			title : '收货地址',
			align : 'center'
					},
					{
			field : 'status',
			title : '状态',
			align : 'center',
			formatter : function(status){
				if(status == 0){
					return '未回款';
				}else{
					return '已回款';
				}
			}
					},{
			field : 'id',
			title : '操作',
			align : 'center',
			formatter : function(value){
				return "<a href='javaScript:openOrderItemDialog("+value+")'>查看详情</a>"
			}
					}
		            ]]
	});
	
	function openOrderDialog(){
		var selections = $("#datagrid").datagrid("getSelections");
		if (selections.length == 0) {
			$.messager.alert("提示", "没有被选择的记录");
			return;
		}
		var row = selections[0];
		$("#order_dialog").dialog("open");
		$('#order_datagrid').datagrid('load', {
			customerId : row.id
		});
	}
	$(function() {
		$("#datagrid").datagrid({
			url : 'find.do',
			pagination : true,
			pageSize:20,
			fit : true,
			rownumbers : true,
			toolbar : '#tools',
			columns : [ [ {
				field : 'cb',
				checkbox : true,
				align : 'center'
			}, {
				field : 'id',
				title : '编号',
				align : 'center'
			}, {
				field : 'num',
				title : '客户编号',
				align : 'center'
			}, {
				field : 'name',
				title : '客户姓名',
				align : 'center'
			}, {
				field : 'region',
				title : '客户地区',
				align : 'center'
			}, {
				field : 'managerName',
				title : '客户经理姓名',
				align : 'center'
			}, {
				field : 'level',
				title : '客户等级',
				align : 'center'
			}, {
				field : 'satisfy',
				title : '满意度',
				align : 'center'
			}, {
				field : 'credit',
				title : '信誉',
				align : 'center'
			}, {
				field : 'address',
				title : '地址',
				align : 'center'
			}, {
				field : 'phone',
				title : '电话',
				align : 'center'
			}, {
				field : 'webSite',
				title : '网址',
				align : 'center'
			}, {
				field : 'licenceNo',
				title : '营业执照',
				align : 'center'
			}, {
				field : 'legalPerson',
				title : '法人',
				align : 'center'
			}, {
				field : 'bankroll',
				title : '注册资金',
				align : 'center'
			}, {
				field : 'turnover',
				title : '年营业额',
				align : 'center'
			}, {
				field : 'bankName',
				title : '开户银行',
				align : 'center'
			}, {
				field : 'bankAccount',
				title : '开户账号',
				align : 'center'
			}, ] ]
		});
	})
</script>
<script type="text/javascript">
	function doSearch() {
		var id = $("#id").val();
		var name = $("#name").val();
		$('#datagrid').datagrid('load', {
			id : id,
			name : name
		});
	}

	function doReset() {
		$("#id").textbox('clear');
		$("#name").textbox('clear');
	}

	function doDelete() {
		var ids = Util.getSelectionsIds("#datagrid");
		if (ids.length == 0) {
			$.messager.alert("提示", "至少选择一项");
			return;
		}
		$.messager.confirm("提示", "确认删除?", function(result) {
			if (result) {
				$.post('delete.do', {
					ids : ids
				}, function(news) {
					$.messager.alert("提示", news.msg);
					if (news.status == Util.SUCCESS) {
						$("#datagrid").datagrid("reload");
					}
				}, "json")
			}
		})
	}

	function closeDialog() {
		$("#dialog").dialog("close");
	}

	var urlUpdateOrAdd;

	function openAddDialog() {
		$("#form").form('clear');
		$("#dialog").dialog("open").dialog("setTitle", "添加记录");
		urlUpdateOrAdd = "add.do"
	}

	function openUpdateDialog() {
		var selections = $("#datagrid").datagrid("getSelections");
		if (selections.length == 0) {
			$.messager.alert("提示", "没有被选择的记录");
			return;
		}
		var row = selections[0];
		$("#form").form("load", row);
		$("#dialog").dialog("open").dialog("setTitle", "更新记录");
		urlUpdateOrAdd = "update.do"
	}

	function doSave() {
		$("#form").form("submit", {
			url : urlUpdateOrAdd,
			/* onSubmit : function() {
				if ($("#roleName").combobox("getValue") == "") {
					$.messager.alert("提示", "用户角色尚未选择");
					return false;
				}
				return $(this).form("validate");
			}, */
			success : function(news) {
				var news = eval('(' + news + ')');
				$.messager.alert("提示", news.msg);
				if (news.status == Util.SUCCESS) {
					$("#dialog").dialog('close');
					$("#datagrid").datagrid("reload");
				}
			}
		})
	}
</script>
</html>