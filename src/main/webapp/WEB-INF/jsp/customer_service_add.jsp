<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
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
	<form id='form' action="" method="post">
		<table>
			<tr>
				<td>服务类型&nbsp;:&nbsp;</td>
				<td><input id="serviceType" class="easyui-combobox" name="serviceType"   
    						data-options="valueField:'dataDicValue',limitToList:true,panelHeight:true,textField:'dataDicValue',url:'${ctx }/dataDic/getDataDicValueList.do?dataDicName=服务类型'" />
    			</td>
			</tr>
			<tr>
				<td>客户&nbsp;:&nbsp;</td>
				<td><input class="easyui-validatebox" required='true'  id="customer" name="customer"/>&nbsp;<font color="red">*</font>
    			</td>
			</tr>
			<tr>
				<td>请求&nbsp;:&nbsp;</td>
				<td><input class="easyui-validatebox" required='true'  id="serviceRequest" name="serviceRequest"/>&nbsp;<font color="red">*</font>
    			</td>
			</tr>
			<tr>
				<td>概要&nbsp;:&nbsp;</td>
				<td><input class="easyui-textbox" style="width:300px"  id="overview" name="overview"/>
    			</td>
			</tr>
			<tr>
				<td>创建人&nbsp;:&nbsp;</td>
				<td><input class="easyui-validatebox" type="text" readonly="true" id="createPeople" name="createPeople"/>
    			</td>
			</tr>
			<tr>
				<td>创建时间&nbsp;:&nbsp;</td>
				<td><input class="easyui-validatebox" type="text" readonly="true" id="createTime" name="createTime"/>
    			</td>
			</tr>
			<tr>
				<td><a class="easyui-linkbutton" href='javaScript:doSave()'>保存</a></td>
				<td><a class="easyui-linkbutton" href='javaScript:doReset()'>清除</a>
    			</td>
			</tr>
		</table>
		
    	
    	
    	
    	
    	
	</form>
	
</body>
	<script type="text/javascript">
		$("#createTime").val(Util.getCurrentDateTime());
		$("#createPeople").val(${user.name});
	</script>
	<script type="text/javascript">
	function doSave() {
		$('#form').form('submit', {    
		    url : '${ctx}/customerService/add.do',    
		    onSubmit: function(){    
		        // do some check    
		        if($("#serviceType").combobox("getValue") == "") {
		        	$.messager.alert("系统提示", "请选择服务类型");
		        	return false;
		        }
		        return $(this).form("validate");
		    },    
		    success:function(data){//正常返回ServerResponse
		    	var data = eval('(' + data + ')');
		    	if(data.status == Util.SUCCESS) {
		    		$.messager.alert("系统提示", data.msg);
		    	}
		    }    
		});  
	}
	
	function doReset(){
		$("#form").form('clear');
		$("#createTime").val(Util.getCurrentDateTime());
		$("#createPeople").val(${user.name});
	}
	</script>
</html>