<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/common_res.jsp"%>
<title>编辑画面</title>
</head>
<body>
<%@include file="../common/common_msg.jsp"%>
<form action="/management/demo/save.htm" method="post">
a:
<input type="text" name="a" id="a" value="${demo.a }" />
<br>
b:
<input type="text" name="b" id="b" value="${demo.b }" />
<br>
c:
<input type="text" name="c" id="c" value="${demo.c }" />
<br>
d:
<input type="text" name="d" id="d" value="${demo.d }" />
<br>
e:
<input type="text" name="e" id="e" value="${demo.e }" />
<br>
f:
<input type="text" name="f" id="f" value="${demo.f }" />
<br>
<input type="hidden" name="isAdd" value="${demo.isAdd}">
<input type="submit" value="保存">
<input type="reset" value="重置">
</form>
</body>
</html>