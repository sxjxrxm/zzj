<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<script type="text/javascript" src="<%=path %>/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function () {
	top.location = "login.htm";
});
</script>
<title>跳转中</title>
</head>
<body>

</body>
</html>