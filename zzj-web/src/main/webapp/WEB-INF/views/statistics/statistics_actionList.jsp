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
        var link = "<%=path %>/statistics/actionList.htm";
        $(function () {
            $(".datepicker").datepicker();
            $(".icon-calendar").datepicker();
            $("#sela").multiselect({
                noneSelectedText: "==请选择==",
                checkAllText: "全选",
                uncheckAllText: '全不选',
                selectedList: 6
            });
        })
        // 查询功能
	    function find_list(){
	    	document.forms[0].action = "<%=path %>/statistics/actionList.htm";
	  		document.forms[0].submit();
		}
        // CSV功能
	    function CSV(){
	    	document.forms[0].action = "<%=path %>/statistics/actionListCSV.htm";
	  		document.forms[0].submit();
		}
        // 行为详细
	    function handleDetail(busiType, topicCd,handleType){
	    	document.forms[0].action = "<%=path %>/statistics/actionDetail.htm?busiType="+busiType+"&topicCd="+topicCd+"&handleType="+handleType;
	    	document.forms[0].submit();
		}
    </script>
</head>
<body>
	<form id="form" action="" method="post" onkeydown="if(event.keyCode==13){find_list();return false;}">
		<div class="alert alert-heading">
			<h4>用户行为一览</h4>
		</div>
		<%@include file="../common/common_msg.jsp"%>
		<div class="sub-title">
			<h5>查询条件</h5>
		</div>
		<table class="tbform" style="margin: 0px; padding: 0px;">
			<tbody>
				<tr>
					<td class="tdl">机能分类<span style="color:red">*</span></td>
					<td class="td_detail">
						<select class="dfinput" name="selectKey.busiType"> 
							<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
								<c:if test="${mstCodeInfo.codeType=='busiType'}">
									<c:set var="selectBusiType" value="${selectKey.busiType}" scope="page"></c:set>
									<c:set var="typeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
									<option <c:if test="${fn:contains(selectBusiType, typeCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
								</c:if>
							</c:forEach>				
						</select>
					</td>
					<td class="tdl">资料主题</td>
					<td class="td_detail"><input class="span6" name="selectKey.topicName" value="${selectKey.topicName}"/></td>
				</tr>
				<tr>
                    <td class="tdl">领域</td>
                    <td class="td_detail">
                        <select class="span6" id ="sela" multiple="multiple" size="5" name="selectKey.field">
	                        <c:forEach items="${techCodeInfos}" var="mstCodeInfo" varStatus="">
								<c:set var="selectTechField" value="${selectKey.field}" scope="page"></c:set>
                    			<c:set var="fieldCode" value="${mstCodeInfo.code}" scope="page"></c:set>
	                    		<option <c:if test="${fn:contains(selectTechField, fieldCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
	                    	</c:forEach>
                        </select>（可复选）
                    </td>
					<td class="tdl">统计日期</td>
		            <td class="td_detail">
		                <div class="input-prepend input-append">
		                    <input class="input-small datepicker" name="selectKey.startDate" id="startDate" value="<fmt:formatDate value="${selectKey.startDate}" pattern="yyyy-MM-dd" />"/>
		                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
		                    &nbsp;&nbsp;~&nbsp;&nbsp;
		                    <input class="input-small datepicker" name="selectKey.endDate" id="endDate" value="<fmt:formatDate value="${selectKey.endDate}" pattern="yyyy-MM-dd" />"/>
		                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
		                </div>
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
					<th class="thCss">资料主题</th>
					<th class="thCss">领域</th>
					<th class="thCss">下载数</th>
					<th class="thCss">收藏数</th>
					<th class="thCss">分享数</th>
					<th class="thCss">点赞数</th>
					<th class="thCss">浏览数</th>
				</tr>
                <c:forEach items="${resultList.items}" var="item" varStatus="status">
            	<tr>   
	                <!-- 机能分类 -->
	                <td style='text-align:center;'>${item.busiTypeName}</td>
	                <!-- 资料主题 -->
	                <td style='text-align:left;'>${item.topicName}</td>
	                <!-- 领域 -->
	                <td  style="text-align:left;">
	                	<c:forEach items="${item.fieldCd}" var="fieldCd">
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
	                <!-- 下载数 -->
	                <td style='text-align:right;'>
	                    <c:if test="${item.downloadCount>0}">
	                	  <a href="javascript:handleDetail('${item.busiType}','${item.topicCd}','download');" id="${item.topicCd}_detail" >${item.downloadCount}</a>
                        </c:if>
                        <c:if test="${item.downloadCount==0}">0</c:if>
	                </td>
	                <!-- 收藏数 -->
	                <td style='text-align:right;'>
	                    <c:if test="${item.collectCount>0}">
	                	  <a href="javascript:handleDetail('${item.busiType}','${item.topicCd}','collect');" id="${item.topicCd}_detail" >${item.collectCount}</a>
                        </c:if>
                        <c:if test="${item.collectCount==0}">0</c:if>
	                </td>
	                <!-- 分享数 -->
	                <td style='text-align:right;'>
	                    <c:if test="${item.shareCount>0}">
	                	  <a href="javascript:handleDetail('${item.busiType}','${item.topicCd}','share');" id="${item.topicCd}_detail" >${item.shareCount}</a>
                        </c:if>
                        <c:if test="${item.shareCount==0}">0</c:if>
	                </td>
	                <!-- 点赞数 -->
	                <td style='text-align:right;'>
	                    <c:if test="${item.likeCount>0}">
	                	  <a href="javascript:handleDetail('${item.busiType}','${item.topicCd}','like');" id="${item.topicCd}_detail" >${item.likeCount}</a>
                        </c:if>
                        <c:if test="${item.likeCount==0}">0</c:if>
	                </td>
	                <!-- 浏览数 -->
	                <td style='text-align:right;'>
	                    <c:if test="${item.scanCount>0}">
	                	  <a href="javascript:handleDetail('${item.busiType}','${item.topicCd}','scan');" id="${item.topicCd}_detail" >${item.scanCount}</a>
                        </c:if>
                        <c:if test="${item.scanCount==0}">0</c:if>
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