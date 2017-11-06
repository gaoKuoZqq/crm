<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
</head>
<body>
	<form id="form" action="" method="post">
		用户名:<input class="easyui-validatebox" name="name"/>
		密码:<input class="easyui-validatebox" name="password"/>
		<a id="btn" href="javaScript:login()" class="easyui-linkbutton">登录</a> 
	</form>
</body>
<script type="text/javascript">
	function login(){
		$("#form").form("submit",{
			url : '${ctx}/user/checkLogin.do',
			onSubmit : function (){
				return $(this).form("validate");
			},
			success : function(news){
				var news = eval('(' + news + ')');
				if(news.status == Util.SUCCESS){
					window.location.href='${ctx}/index/index.do';
				}else{
					$.messager.alert("提示",news.msg);
				}
			}
		})
	}
</script>
</html>