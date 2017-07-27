<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <!--<link rel="stylesheet" type="text/css" href="../styles/admin-all.css" />-->
    <link rel="stylesheet" type="text/css" href="../styles/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/ui-lightness/jquery-ui-1.8.22.custom.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/select-checkbox/style.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/select-checkbox/jquery.multiselect.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/chur.css" />
    <style>
    	td, th 
    {text-align: center;}.td_detail, .tdl {text-align: left;}
    </style>
    <!--<link rel="stylesheet" type="text/css" href="../styles/base.css"/>-->
    <script type="text/javascript" src="../scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="../scripts/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="../scripts/select-checkbox/jquery.multiselect.js"></script>
    <script type="text/javascript" src="../scripts/select-checkbox/jquery.ui.core.js"></script>
    <script type="text/javascript" src="../scripts/select-checkbox/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="../scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="../scripts/ChurAlert.min.js?skin=blue"></script>
    <!--<link rel="stylesheet" type="text/css" href="../Styles/formui.css"/>-->
    <script type="text/javascript" src="../scripts/tb.js"></script>
    <script type="text/javascript" src="../scripts/select-move-option.js"></script>
    <script type="text/javascript">
    var link = "<%=path %>/enterprise/list.htm";
        $(function () {
            $(".datepicker").datepicker();
            //$('#list').hide();
            //$('#find').click(function () {
            //    $('#list').show();
            //})
            $("#sela").multiselect({
                noneSelectedText: "==请选择==",
                checkAllText: "全选",
                uncheckAllText: '全不选',
                selectedList: 6
            });
            $("#sela1").multiselect({
                noneSelectedText: "==请选择==",
                checkAllText: "全选",
                uncheckAllText: '全不选',
                selectedList: 6
            });
        })
        
		// 查询	
		 function find_e_List(){
	        	document.forms[0].action = link;
	      		document.forms[0].submit();
			}
		// CSV功能
		 function CSV(){
		    document.forms[0].action = "<%=path %>/enterprise/CSV.htm";
		  	document.forms[0].submit();
			}
		// 审核功能
		  function edit(userId){
				document.forms[0].action = "<%=path %>/enterprise/edit.htm?userId="+userId+"&add=edit";
		  		document.forms[0].submit();
			}
		// 城市信息级联修改
		function changeArea(){
				$.get("<%=path %>/enterprise/getCityC.htm", {cityPCode : $('#cityP option:selected').val()}, function (data) { creatCityCOptions(data); });
			}
		function creatCityCOptions(CityCList){
				var cityCSelect = $("#cityC");
				if (CityCList.length > 0) {
					cityCSelect.empty(); 
					for(var i = 0; i < CityCList.length; i++){
						cityCSelect.append("<option value="+CityCList[i].regionCode+">"+CityCList[i].regionName+"</option>");
					}
				} else {
					cityCSelect.empty();
					cityCSelect.append("<option value=''>==请选择==</option>");
				}
			}
    </script>
</head>
<body>
	<form id="questionForm" method="post" onkeydown="if(event.keyCode==13){find_e_List();return false;}">
		<div class="alert alert-heading">
			<h4>政企一览</h4>
		</div>
	    <%@include file="../common/common_msg.jsp"%>
		<div class="sub-title">
			<h5>查询条件</h5>
		</div>
		<table class="tbform" style="margin: 0px; padding: 0px;">
			<tbody>
				<tr>
					<td class="tdl">用户名称</td>
					<td class="td_detail"><input class="span6" name="userName" value="${info.userName}" /></td>
					<td class="tdl">审核状态</td>
					<td class="td_detail">
						<c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="">
							<c:if test="${mstCodeInfo.codeType=='auditStatus'}">
								<c:set var="selectStatus" value="${info.statusList}" scope="page"></c:set>
								<c:set var="statusCode" value="${mstCodeInfo.code}" scope="page"></c:set>
								<input <c:if test="${fn:contains(selectStatus, statusCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="statusList" />
									&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;
                    		</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="tdl">领域</td>
					<td class="td_detail">
						<select class="span6" id="sela" multiple="multiple" size="5" name="techFieldTypeList">
							<c:forEach items="${techCodeInfos}" var="mstCodeInfo" varStatus="">
								<c:set var="selectTechField" value="${info.techFieldTypeList}" scope="page"></c:set>
								<c:set var="fieldCode1" value="${mstCodeInfo.code}" scope="page"></c:set>
								<option <c:if test="${fn:contains(selectTechField, fieldCode1)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
							</c:forEach>
						</select>（可复选）
					</td>
					<td class="tdl">单位性质</td>
					<td class="td_detail">
						<select class="span3" name="corpType">
                        	<option value="">==请选择==</option>
							<c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="">
								<c:if test="${mstCodeInfo.codeType=='corpType'}">
									<c:set var="selectTechField" value="${info.corpType}" scope="page"></c:set>
									<c:set var="fieldCode2" value="${mstCodeInfo.code}" scope="page"></c:set>
									<option <c:if test="${fn:contains(selectTechField, fieldCode2)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
                <tr>
                    <td class="tdl">常驻城市</td>
                    <td class="td_detail" colspan="7">
                        <select class="span3" name="cityP" id="cityP" onchange="changeArea()">
                        	<option value="">==请选择==</option>
                        	<c:set value="${info.cityP}" var="cityP"></c:set>
                        	<c:forEach items="${info.areaInfosCityP}" var="areaInfosCityP">
	                    		<option value="${areaInfosCityP.regionCode}" <c:if test="${areaInfosCityP.regionCode eq cityP}">selected</c:if>>${areaInfosCityP.regionName}</option>
	                    	</c:forEach> 
                   		</select>&nbsp;&nbsp;
                        <select class="span3" name="cityC" id="cityC">
                        	<option value="">==请选择==</option>
                        	<c:set value="${info.cityC}" var="cityC"></c:set>
                        	<c:forEach items="${info.areaInfosCityC}" var="areaInfosCityC">
	                    		<option value="${areaInfosCityC.regionCode}" <c:if test="${areaInfosCityC.regionCode eq cityC}">selected</c:if>>${areaInfosCityC.regionName}</option>
	                    	</c:forEach> 
                   		</select>
                    </td>
                </tr>
				<tr>
					<td colspan="8" class="td-title">
						<div class="div_bottom_right">
							<button class="btn btn-inverse no_repeat_submit" onclick="CSV(); return false;">EXCEL导出</button>
							<button class="btn btn-inverse no_repeat_submit" onclick="find_e_List(); return false;">查询</button>
						</div>
					</td>
				</tr>
			</tbody>
		</table>

		<!--检索一览-->
		<div class="sub-title" id="listDiv">
			<h5>查询结果</h5>
		</div>
		<table class="tb" id="listTable">
			<tbody>
				<tr>
					<th>操作</th>
					<th>用户名称</th>
					<th>领域</th>
					<th>单位性质</th>
					<th>工作单位</th>
					<th>职务/职称</th>
					<th>审核状态</th>
				</tr>
				<c:forEach var="enterpriseList" items="${resultList.items}">
					<tr>
						<!-- 操作 -->
						<td>
							<a href="javascript:edit('${enterpriseList.userId}');">编辑</a>&nbsp;
						</td>
						<!-- 名称 -->
						<td style="text-align: left;">${enterpriseList.userName}</td>
						<!-- 领域 --> 
						<td style="text-align: left;">
							<c:forEach items="${enterpriseList.fieldCd}" var="fieldCd">
			                	<c:forEach items="${techCodeInfos}" var="mstCodeInfo">
		                    		<c:if test="${mstCodeInfo.codeType=='techFieldType' && mstCodeInfo.code==fieldCd.techFieldCd}">
		                    			${mstCodeInfo.codeName}
		                    		</c:if>
		                    	</c:forEach>
		                    	<c:forEach items="${rschCodeInfos}" var="mstCodeInfo">
		                    		<c:if test="${mstCodeInfo.codeType=='rschFieldType' && mstCodeInfo.code==fieldCd.rschFieldCd}">
		                    			-&gt;${mstCodeInfo.codeName}
		                    		</c:if>
		                    	</c:forEach>|
                    		</c:forEach>
						</td>
						<td style="text-align: left;">
						<!--单位性质--> 
								<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
									<c:if test="${mstCodeInfo.codeType=='corpType' && mstCodeInfo.code==enterpriseList.corpType}">
	                    				${mstCodeInfo.codeName}
	                    			</c:if>
								</c:forEach>

						</td>
						<!--工作单位--> 
						<td style="text-align: left;">${enterpriseList.company}</td>
						<!--职务/职称--> 
						<td style="text-align: left;">${enterpriseList.rank}</td>
						
						<!-- 审核状态 -->
						<td>
								<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
									<c:if test="${mstCodeInfo.codeType=='auditStatus' && mstCodeInfo.code==enterpriseList.status}">
	                    				${mstCodeInfo.codeName}
	                    			</c:if>
								</c:forEach>
						</td>
					</tr>
				</c:forEach>
			<!-- 翻页 -->
            <tr style='display:<c:if test="${resultList.items == null || resultList.items.size() == 0}">none</c:if>'>
                <%@include file="../common/page.jsp" %>
            </tr>
			</tbody>
		</table>
		<input type="hidden" value="${doSearch}" name="doSearch"/>
		<input type="hidden" value="${needsPageNo}" name="needsPageNo"/>
	</form>
</body>
</html>
