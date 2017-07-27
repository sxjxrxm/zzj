<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<%=path %>/styles/admin-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/styles/base.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/styles/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/styles/chur.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/styles/common.css" />
<script type="text/javascript" src="<%=path %>/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=path %>/scripts/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="<%=path %>/scripts/index.js"></script>
<script type="text/javascript" src="<%=path %>/scripts/chur-alert.1.0.js"></script>
<script type="text/javascript" src="<%=path %>/scripts/ChurAlert.min.js?skin=blue"></script>