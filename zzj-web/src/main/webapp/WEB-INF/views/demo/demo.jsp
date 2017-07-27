<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/common_res.jsp"%>
<title>内容画面</title>
</head>
<body>
<%@include file="../common/common_msg.jsp"%>
<form action="/management/demo/add.htm" method="post" name="demoForm">
a:
<input type="text" name="a" value="${demo.a }" readonly/>
<br>
b:
<input type="text" name="b" value="${demo.b }" readonly/>
<br>
c:
<input type="text" name="c" value="${demo.c }" readonly/>
<br>
d:
<input type="text" name="d" value="${demo.d }" readonly/>
<br>
e:
<input type="text" name="e" value="${demo.e }" readonly/>
<br>
f:
<input type="text" name="f" value="${demo.f }" readonly/>
<br>
<input type="hidden" name="isAdd" value="1" readonly/>
<input type="submit" value="追加">
<input type="button" value="编辑" onclick="javascript:document.demoForm.action='/management/demo/edit.htm?isAdd=0';document.demoForm.submit();">
</form>
</body>
</html>