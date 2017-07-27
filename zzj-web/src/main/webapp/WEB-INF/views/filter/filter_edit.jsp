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
    <!--<link rel="stylesheet" type="text/css" href="<%=path %>/styles/admin-all.css" />-->
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/edit-page.css"/>

    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-move-option.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/refuse.js"></script>

    <script type="text/javascript" src="<%=path %>/scripts/tb.js"></script>
    <script type="text/javascript">
	    function back(){
	    	document.forms[0].action = "<%=path %>/filter/list.htm";
	  		document.forms[0].submit();
		}
	    function save(){
	    	document.forms[0].action = "<%=path %>/filter/save.htm";
	  		document.forms[0].submit();
		}

    </script>
</head>
<body>
    <form onkeydown="if(event.keyCode==13){save();return false;}">
    <div class="alert alert-heading"><h4>${filterInfo.busiTypeName}筛选条件编辑</h4></div>
    <%@include file="../common/common_msg.jsp"%>
    <div class="sub-title"><h5>筛选条件</h5></div>

        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>
                    <td class="tdl">筛选条件1</td>
					<td class="td_detail">
						<select class="dfinput" name="screening01" id ="screening01" > 
							<option value="">==请选择==</option>
							<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
								<c:if test="${mstCodeInfo.codeType=='screeningType'}">
									<c:set var="selectScreeningType" value="${filterInfo.screening01}" scope="page"></c:set>
									<c:set var="typeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
									<option <c:if test="${fn:contains(selectScreeningType, typeCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
								</c:if>
							</c:forEach>				
						</select>
					</td>
                </tr>
                <tr>
                    <td class="tdl">筛选条件2</td>
					<td class="td_detail">
						<select class="dfinput" name="screening02" id ="screening02"> 
							<option value="">==请选择==</option>
							<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
								<c:if test="${mstCodeInfo.codeType=='screeningType'}">
									<c:set var="selectScreeningType" value="${filterInfo.screening02}" scope="page"></c:set>
									<c:set var="typeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
									<option <c:if test="${fn:contains(selectScreeningType, typeCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
								</c:if>
							</c:forEach>				
						</select>
					</td>
                </tr>
                <tr>
                    <td class="tdl">筛选条件3</td>
					<td class="td_detail">
						<select class="dfinput" name="screening03" id ="screening03"> 
							<option value="">==请选择==</option>
							<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
								<c:if test="${mstCodeInfo.codeType=='screeningType'}">
									<c:set var="selectScreeningType" value="${filterInfo.screening03}" scope="page"></c:set>
									<c:set var="typeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
									<option <c:if test="${fn:contains(selectScreeningType, typeCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
								</c:if>
							</c:forEach>				
						</select>
					</td>
                </tr>
                <tr>
                    <td class="tdl">筛选条件4</td>
					<td class="td_detail">
						<select class="dfinput" name="screening04" id ="screening04"> 
							<option value="">==请选择==</option>
							<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
								<c:if test="${mstCodeInfo.codeType=='screeningType'}">
									<c:set var="selectScreeningType" value="${filterInfo.screening04}" scope="page"></c:set>
									<c:set var="typeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
									<option <c:if test="${fn:contains(selectScreeningType, typeCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
								</c:if>
							</c:forEach>				
						</select>
					</td>
                </tr>           
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button onclick="back(); return false;" class="btn btn-inverse no_repeat_submit">返回</button>
                            <button onclick="save(); return false;" class="btn btn-inverse no_repeat_submit">保存</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <input type="hidden" name="busiType" value="${filterInfo.busiType}" />
        <input type="hidden" name="updateId" value="${filterInfo.updateId}" />
    </form>
</body>
</html>