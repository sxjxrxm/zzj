<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>用户行为详细</title>
    <link rel="stylesheet" type="text/css" href="../styles/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/ui-lightness/jquery-ui-1.8.22.custom.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/select-checkbox/style.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/select-checkbox/jquery.multiselect.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/chur.css" />
    <style>
    	td,th {text-align: center;}
    	.td_detail,.tdl {text-align: left;}
    </style>
    <script type="text/javascript" src="../scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="../scripts/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="../scripts/select-checkbox/jquery.multiselect.js"></script>
    <script type="text/javascript" src="../scripts/select-checkbox/jquery.ui.core.js"></script>
    <script type="text/javascript" src="../scripts/select-checkbox/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="../scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="../scripts/ChurAlert.min.js?skin=blue"></script>
    <script type="text/javascript" src="../scripts/tb.js"></script>
    <script type="text/javascript" src="../scripts/select-move-option.js"></script>
    <script type="text/javascript">
        var link = "<%=path %>/statistics/incomeDetail.htm";
        // 返回
    	function back() {
	    	document.forms[0].action = "<%=path %>/statistics/incomeList.htm";
	  		document.forms[0].submit();
		}
        // CSV功能
	    function CSV(){
	    	document.forms[0].action = "<%=path %>/statistics/incomeDetailCSV.htm";
	  		document.forms[0].submit();
		}
    </script>
</head>
<body>
	<form id="form" action="" method="post">
		<div class="alert alert-heading">
			<h4>收入详细</h4>
		</div>
		<table class="tb" id="list" >
			<tbody>
				<tr class="treven">
					<th class="thCss">机能分类</th>
					<th class="thCss">资料主题</th>
					<th class="thCss">用户昵称</th>
					<th class="thCss">购买金额(元)</th>
					<th class="thCss">购买时间</th>
				</tr>
                <c:forEach items="${resultList.items}" var="item" varStatus="status">
            	<tr>   
	                <!-- 机能分类 -->
	                <td style='text-align:center;'>${item.busiTypeName}</td>
	                <!-- 资料主题 -->
	                <td style='text-align:left;'>${item.topicName}</td>
	                <!-- 用户昵称 -->
	                <td style='text-align:left;'>${item.userName}</td>
	                <!-- 购买金额 -->
	                <td style='text-align:right;'>${item.priceSell}</td>
	                <!-- 购买时间 -->
	                <td style='text-align:center;'><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
           	 	</tr>
              </c:forEach>
              <tr style='display:<c:if test="${resultList.items == null || resultList.items.size() == 0}">none</c:if>'>
                <%@include file="../common/page.jsp" %>
              </tr>
			</tbody>
		</table>
	    <table class="tbform" style="margin:0px; padding:0px;">
			<tbody>	
		      <tr>
                  <td class="td-title">
                      <div class="div_bottom_right">
                          <button onclick="back(); return false;" class="btn btn-inverse no_repeat_submit">返回</button>
                          <button onclick="CSV(); return false;" class="btn btn-inverse no_repeat_submit">EXCEL导出</button>
                      </div>
                  </td>
              </tr>
			</tbody>
		</table>
		<!-- 页面回显使用的数据 -->
		<input type="hidden" value="${selectKey.busiType}" name="selectKey.busiType"/>
		<input type="hidden" value="${selectKey.startDate}" name="selectKey.startDate"/>
	    <input type="hidden" value="${selectKey.endDate}" name="selectKey.endDate"/>
	    <input type="hidden" value="${selectKey.sourceType}" name="selectKey.sourceType"/>
	    <input type="hidden" value="${selectKey.expertName}" name="selectKey.expertName"/>
	    <input type="hidden" value="${busiType}" name="busiType"/>
	    <input type="hidden" value="${topicCd}" name="topicCd"/>
		<input type="hidden" value="${listPageNo}" name="listPageNo"/>
	</form>
</body>
</html>