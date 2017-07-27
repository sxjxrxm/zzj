<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title> 
    <!--<link rel="stylesheet" type="text/css" href="../Styles/admin-all.css" />-->
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/select-checkbox/style.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/select-checkbox/jquery.multiselect.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/chur.css" />
    <style>
    	td,th {text-align: center;}
    	.td_detail,.tdl {text-align: left;}
    </style>
    <!--<link rel="stylesheet" type="text/css" href="../Styles/base.css"/>-->
    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.multiselect.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.core.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/ChurAlert.min.js?skin=blue"></script>
    <!--<link rel="stylesheet" type="text/css" href="../Styles/formui.css"/>-->
    <script type="text/javascript" src="<%=path %>/scripts/tb.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-move-option.js"></script>
    <script type="text/javascript">
	    function find_list(){
	    	document.forms[0].action = "<%=path %>/filter/list.htm";
	  		document.forms[0].submit();
		}
        function edit(id,name){
			document.forms[0].action = "<%=path %>/filter/edit.htm?busiType="+id+"&busiTypeName="+name;
	  		document.forms[0].submit();
		}
	</script>
</head>
<body>
    <form id="form" action="" method="post">     
	    <!--检索一览-->
	    <div class="alert alert-heading"><h4>筛选条件一览</h4></div>
	    <table class="tb" id="list">
	        <tbody>
	            <tr>
	                <th>操作 </th>
	                <th>机能</th>
	                <th>筛选类别1</th>
	                <th>筛选类别2</th>
	                <th>筛选类别3</th>
	                <th>筛选类别4</th>
	            </tr>            
	            <c:forEach items="${filterList}" var="item" varStatus="status">
	            	<tr>   
		                <td>
		                    <a href="javascript:edit('${item.busiType}','${item.busiTypeName}');">编辑 </a>
		                </td>
		                <td>${item.busiTypeName}</td>
		                <td>${item.name1}</td><!-- 筛选类别1 -->
		                <td>${item.name2}</td><!-- 筛选类别2 -->
		                <td>${item.name3}</td><!-- 筛选类别3  -->
		                <td>${item.name4}</td><!-- 筛选类别4 -->
	           	 	</tr>
	            </c:forEach>
	        </tbody>
	    </table>
    </form>
</body>
</html>
