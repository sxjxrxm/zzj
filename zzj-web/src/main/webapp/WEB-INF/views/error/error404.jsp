<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<%@include file="../common/common_res.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=path %>/styles/error.css" />
	<title>找专家后台管理系统 —— 找不到页面</title>
	<script type="text/javascript">
		function index() {
			top.location.href = "<%=path %>/index.htm";
		}
	</script>
</head>
<body style="background:#F4F3EF;">
	<div id="container">
        <p class="ir error-code error-404">404</p>
        <p class="not-found">您访问的页面找不到，请与管理员联系</p>
        <br/><br/>
        <p class="link"><button class="btn btn-warning btn-large" onclick="index();return false;">首页</button>&nbsp;&nbsp;
        <button class="btn btn-warning btn-large" onclick="window.history.go(-1);">返回</button></p>
    </div>
</body>
</html>