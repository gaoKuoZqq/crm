<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="common.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	$(function() {
		$("#datagrid").datagrid({
			url : 'find.do',
			pagination : true,
			pageSize:20,
			fit : true,
			rownumbers : true,
			toolbar : '#tools',
			fitColumns : true,
			columns : [[ 
				 {
				field : 'id',
				title : 'id',
				align : 'center',
				width : 500
			}, {
				field : 'customerNo',
				title : '客户编号',
				align : 'center',
				width : 500
			}, {
				field : 'customerName',
				title : '客户名称',
				align : 'center',
				width : 500
			}, {
				field : 'customerManager',
				title : '客户经理',
				align : 'center',
				width : 500
			}, {
				field : 'lastOrderTime',
				title : '上次下单日期',
				align : 'center',
				width : 500
			}, {
				field : 'confirmLossTime',
				title : '确认流失日期',
				align : 'center',
				width : 500
			}, {
				field : 'lossReason',
				title : '流失原因',
				align : 'center',
				width : 500
			}, {
				field : 'status',
				title : '操作',
				align : 'center',
				width : 500,
				formatter : function(value,row){
			    	 if(value==1){
						 return "已确认流失";
					 }else{
						 return "<a href='javaScript:openCusLossMeasureTab("+row.id+")'>暂缓流失</a>";
					 }
			     }
			}
			]]
		});
	});
	function openCusLossMeasureTab(id){
		 window.parent.openTab('暂缓流失','${ctx}/customerLossMeasure/index.do?id='+id,'icon-khkfjh');
	}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
	<table id="datagrid"></table>

	<!-- 工具栏 -->
	<div id="tools" class="easyui-toolbar">
		 <input class="easyui-textbox"
			data-options="prompt:'编号'" style="width: 60px" id="id"></input> <input
			class="easyui-textbox" data-options="prompt:'用户名'"
			style="width: 120px" id="name"></input> <a id="btn"
			href="javaScript:doSearch()" class="easyui-linkbutton"
			data-options="iconCls:'icon-search'">搜索</a> <a id="btn"
			href="javaScript:doReset()" class="easyui-linkbutton"
			style="float: right">清除</a>
	</div>

</body>

<script type="text/javascript">
	function doSearch() {
		var id = $("#id").val();
		var name = $("#name").val();
		$('#datagrid').datagrid('load', {
			id : id,
			customerName : name
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