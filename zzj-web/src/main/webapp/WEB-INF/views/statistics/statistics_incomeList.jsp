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
    <title>用户行为一览</title>
    <link rel="stylesheet" type="text/css" href="../styles/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/ui-lightness/jquery-ui-1.8.22.custom.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/select-checkbox/style.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/jquery.autocomplete.css"/>
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
    <script type="text/javascript" src="../scripts/jquery.autocomplete.min.js"></script>
    <script type="text/javascript">
        var link = "<%=path %>/statistics/incomeList.htm";
        $(function () {
            $(".datepicker").datepicker();
            $(".icon-calendar").datepicker();
            //资料来源输入框的禁用与可用的切换
            $("#sourceType2").change(function() {
                var expert_check = $(this).attr("checked");
                if (expert_check == "checked") {
                	$.get("<%=path %>/expert/getExpertName.htm", {}, function (data) { 
        	        	$("#expertInput").autocomplete(data.split("    "),{minChars:0,max:100});
        	        });
                    $("#expertInput").removeAttr("disabled").focus();
                } else {
                    $("#expertInput").attr("disabled", true);
                }
            });
        })
        // 查询功能
	    function find_list(){
	    	document.forms[0].action = "<%=path %>/statistics/incomeList.htm";
	  		document.forms[0].submit();
		}
        // CSV功能
	    function CSV(){
	    	document.forms[0].action = "<%=path %>/statistics/incomeListCSV.htm";
	  		document.forms[0].submit();
		}
        // 行为详细
	    function incomeDetail(busiType,topicCd){
	    	document.forms[0].action = "<%=path %>/statistics/incomeDetail.htm?busiType="+busiType+"&topicCd="+topicCd;
	    	document.forms[0].submit();
		}
    </script>
</head>
<body>
	<form id="form" action="" method="post" onkeydown="if(event.keyCode==13){find_list();return false;}">
		<div class="alert alert-heading">
			<h4>收入一览</h4>
		</div>
		<%@include file="../common/common_msg.jsp"%>
		<div class="sub-title">
			<h5>查询条件</h5>
		</div>
		<table class="tbform" style="margin: 0px; padding: 0px;">
			<tbody>
				<tr>
					<td class="tdl">统计日期<span style="color:red">*</span></td>
		            <td class="td_detail">
		                <div class="input-prepend input-append">
		                    <input class="input-small datepicker" name="selectKey.startDate" id="startDate" value="<fmt:formatDate value="${selectKey.startDate}" pattern="yyyy-MM-dd" />"/>
		                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
		                    &nbsp;&nbsp;~&nbsp;&nbsp;
		                    <input class="input-small datepicker" name="selectKey.endDate" id="endDate" value="<fmt:formatDate value="${selectKey.endDate}" pattern="yyyy-MM-dd" />"/>
		                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
		                </div>
		            </td>
					<td class="tdl">机能分类</td>
					<td class="td_detail">
						<select class="dfinput" name="selectKey.busiType"> 
							<option value="">==请选择==</option>
							<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
								<c:if test="${mstCodeInfo.codeType=='busiType'}">
									<c:set var="selectBusiType" value="${selectKey.busiType}" scope="page"></c:set>
									<c:set var="typeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
									<option <c:if test="${fn:contains(selectBusiType, typeCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
								</c:if>
							</c:forEach>				
						</select>
					</td>
				</tr>
				<tr>
                    <td class="tdl">资料来源</td>
					<td colspan="4" class="td_detail">
						<c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="">
						    <c:if test="${mstCodeInfo.codeType=='sourceType'}">
						    <c:set var="selectSourceType" value="${selectKey.sourceType}" scope="page"></c:set>
						    <c:set var="sourceTypeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                    		<input id="sourceType${sourceTypeCode}" <c:if test="${fn:contains(selectSourceType, sourceTypeCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="selectKey.sourceType"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    </c:if>
						</c:forEach>
    			        <input class="span2" id="expertInput" 
	    			        <c:choose>
	                    		<c:when test="${fn:contains(selectSourceType, '2')}"></c:when>
	                    		<c:otherwise>disabled="disabled"</c:otherwise>
	                    	</c:choose>
     			        	value="${selectKey.expertName}" name="selectKey.expertName"/>			            
                    </td>
				</tr>
				<tr>
					<td colspan="4" class="td-title">
						<div class="div_bottom_right">
							<button class="btn btn-inverse no_repeat_submit"
								onclick="CSV(); return false;">EXCEL导出</button>
							<button class="btn btn-inverse no_repeat_submit"
								onclick="find_list(); return false;">查询</button>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<!--检索一览-->
		<div class="sub-title">
			<h5>查询结果</h5>
		</div>
		<table class="tb" id="list" >
			<tbody>
				<tr class="treven">
					<th class="thCss">机能分类</th>
					<th class="thCss">内容分类</th>
					<th class="thCss">资料主题</th>
					<th class="thCss">资料来源</th>
					<th class="thCss">收入金额(元)</th>
				</tr>
                <c:forEach items="${resultList.items}" var="item" varStatus="status">
            	<tr>   
	                <!-- 机能分类 -->
	                <td style='text-align:left;'>${item.busiTypeName}</td>
	                <!-- 内容分类 -->
	                <td style='text-align:left;'>${item.contentName}</td>
	                <!-- 资料主题 -->
	                <td style='text-align:left;'>${item.topicName}</td>
	                <!-- 资料来源 -->
	                <td style='text-align:left;'>
	                  <c:if test="${!empty item.sourceOwner}">${item.sourceOwner}</c:if>
	                  <c:if test="${empty item.sourceOwner}">找专家平台</c:if>
	                </td>
	                <!-- 收入 -->
	                <td style='text-align:right;'>
	                    <c:if test="${item.priceSell>0}">
	                	  <a href="javascript:incomeDetail('${item.busiType}','${item.topicCd}');" id="${item.topicCd}_detail" >${item.priceSell}</a>
                        </c:if>
                        <c:if test="${item.priceSell==0}">0</c:if>
	                </td>
           	 	</tr>
              </c:forEach>
            <tr style='display:<c:if test="${resultList.items == null || resultList.items.size() == 0}">none</c:if>'>
                <%@include file="../common/page.jsp" %>
            </tr>
			</tbody>
		</table>
		<input type="hidden" value="${doSearch}" name="doSearch"/>
	</form>
</body>
</html>