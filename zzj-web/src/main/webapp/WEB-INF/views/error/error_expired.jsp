<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<%@include file="../common/common_res.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=path %>/styles/error.css" />
<title>找专家后台管理系统 —— ${errMsg}</title>
<script type="text/javascript">
	function goLogin() {
		top.location.href = "<%=path %>/login.htm";
	}
</script>
</head>
<body style="background:#F4F3EF;">
	<div id="container">
        <p class="ir error-code error-500">500</p>
        <p class="not-found">${errMsg}</p>
        <br/><br/>
        <p class="link"><button class="btn btn-primary btn-large" onclick="goLogin();return false;">到登录页面</button>
    </div>
</body>
</html>