<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ｅ问一览</title>
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
    <!--<link rel="stylesheet" type="text/css" href="../styles/base.css"/>-->
    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.multiselect.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.core.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/ChurAlert.min.js?skin=blue"></script>
    <script type="text/javascript" src="<%=path %>/scripts/tb.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/recommdtoTop.js"></script>
    <script type="text/javascript">
        var link = "<%=path %>/question/list.htm";
        $(function () {
            $(".datepicker").datepicker();
            $("#sela").multiselect({
                noneSelectedText: "==请选择==",
                checkAllText: "全选",
                uncheckAllText: '全不选',
                selectedList: 6
            });
        })
	    // 编辑功能
	    function edit(questionCd) {
			document.forms[0].action = "<%=path %>/question/edit.htm?flag=2&questionCd="+questionCd;
	  		document.forms[0].submit();
		}
		function recom(){
			document.forms[0].action = "<%=path %>/recommend/list.htm";
			document.forms[0].submit();
		}
	    // 删除功能
		function del(questionCd)
		{
			$('body').alert({
				type : 'info',
				buttons : [ {
					id : 'yes',
					name : '确定',
					callback : function() {
						document.forms[0].action = "<%=path %>/question/update.htm?questionCd="+questionCd;
				  		document.forms[0].submit();
					}
				}, {
					id : 'no',
					name : '取消',
					callback : function() {
					}
				} ]
			})
		}
        // 推荐置顶
        function recommendExecute(userId, id, executeCode) {
        	if (executeCode == 3)
        	{
            	$.post("<%=path %>/recommend/recommendExecute.htm?code=" + executeCode, {
            		updateId:userId,
            		busiType:"05",
            		topicCd:id,
            		recommendMemo:parent.document.getElementById("DIVCSS5").value,
            	}, function (data) {
            		if (data == 'success') {
                		changeRecommend(executeCode, $("#"+id+"_recommend"));
            			var msg = $('#message', parent.document);
            			if(msg != null && msg != undefined) {
            				msg.text("");
            			}
            		}
            		else
            		{
            			// 设置错误信息
            			var msg = $('#message', parent.document);
            			if(msg != null && msg != undefined) {
            				msg.text(data);
            				msg.show();
            				msg.attr("style", "color:red;font-weight:bold;font-size:small;");
            			}
            			var errItem = $('#DIVCSS5', parent.document);
            			errItem.focus();
            		}
    			});
        	}
        	else
        	{
            	$.post("<%=path %>/recommend/recommendExecute.htm?code=" + executeCode, {
            		updateId:userId,
            		busiType:"05",
            		topicCd:id,
            	}, function (data) {
            		if (data == 'success') {
            			if (executeCode < 3) {
            				changeToTop(executeCode, $("#"+id+"_toTop"));
            			} else {
            				changeRecommend(executeCode, $("#"+id+"_recommend"));
            			}
            		}
              		else
            		{
            			alert(data);
            		}
    			});
        	}
        }
		// 查询	
		function find_q_List(){
	        	document.forms[0].action = link;
	      		document.forms[0].submit();
		}
		// CSV功能
		function CSV() {
		    document.forms[0].action = "<%=path %>/question/CSV.htm";
		  	document.forms[0].submit();
		}
		
    </script>
</head>
<body>
	<form id="questionForm" method="post"  onkeydown="if(event.keyCode==13){find_q_List();return false;}">
		<!--控制页面无数据显示-->
		<input type = "hidden" value="true" name="isShow"/>
		<div class="alert alert-heading">
			<h4>e问一览</h4>
		</div>
		<%@include file="../common/common_msg.jsp"%>
		<div class="sub-title">
			<h5>查询条件</h5>
		</div>
		<table class="tbform" style="margin: 0px; padding: 0px;">
			<tbody>
				<tr>
					<td class="tdl">e问主题</td>
					<td class="td_detail"><input class="span6" name="questionName" value="${info.questionName}" /></td>
					<td class="tdl">回答状态</td>
					<td class="td_detail">
						<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
							<c:if test="${mstCodeInfo.codeType=='answerStatus'}">
								<c:set var="selectStatus" value="${info.answerStuts}" scope="page"></c:set>
								<c:set var="statusCode" value="${mstCodeInfo.code}" scope="page"></c:set>
								<input <c:if test="${fn:contains(selectStatus, statusCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="answerStuts" />
									&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="tdl">领域</td>
					<td class="td_detail">
						<select class="span6" id="sela" multiple="multiple" size="5" name="techFieldTypeList">
							<c:forEach items="${techCodeInfos}" var="mstCodeInfo" varStatus="">
								<c:set var="selectTechField" value="${info.techFieldTypeList}" scope="page"></c:set>
								<c:set var="fieldCode" value="${mstCodeInfo.code}" scope="page"></c:set>
								<option <c:if test="${fn:contains(selectTechField, fieldCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
							</c:forEach>
						</select>（可复选）
					</td>
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
					<td class="tdl">提出日期</td>
					<td class="td_detail">
						<div class="input-prepend input-append">
							<input class="input-small datepicker" id="sDate" name="sDate" value="<fmt:formatDate value="${info.sDate}" pattern="yyyy-MM-dd" />" />
							<span class="add-on"><i class="icon-calendar datepicker"></i></span> &nbsp;&nbsp;~&nbsp;&nbsp; 
							<input class="input-small datepicker" id="eDate" name="eDate" value="<fmt:formatDate value="${info.eDate}" pattern="yyyy-MM-dd" />" />
							<span class="add-on"><i class="icon-calendar datepicker"></i></span>
						</div>
					</td>
					<td class="tdl">推荐置顶</td>
					<td class="td_detail">
               			<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
							<c:if test="${mstCodeInfo.codeType=='recommendKbn'}">
								<c:set var="selectRecommendKbn" value="${info.recommendKbnlist}" scope="page"></c:set>
								<c:set var="recommendCode" value="${mstCodeInfo.code}" scope="page"></c:set>
								<input <c:if test="${fn:contains(selectRecommendKbn, recommendCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="recommendKbnlist" />
                    				&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    		</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td colspan="8" class="td-title">
						<div class="div_bottom_right">
							<button class="btn btn-inverse no_repeat_submit" onclick="recom(); return false;">推荐置顶一览</button>
							<button class="btn btn-inverse no_repeat_submit" onclick="CSV(); return false;">EXCEL导出</button>
							<button class="btn btn-inverse no_repeat_submit" onclick="find_q_List(); return false;">查询</button>
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
					<th>e问主题</th>
					<th>回答状态</th>
					<th>领域</th>
					<th>e问时间</th>
					<th>审核状态</th>
				</tr>
				<c:forEach items="${resultList.items}" var="item" varStatus="status">
					<tr>
						<!-- 操作 -->
		                <td><a href="javascript:edit('${item.questionCd}');">编辑</a>
		                <a href="javascript:del('${item.questionCd}');" style="color:#FF0000">删除</a><br/>
		                <c:if test="${item.recommend.size()==0}">
		                  <c:if test="${item.status == 1}">
		                  	<a href="javascript:recommend('${userInfo.userId}','${item.questionCd}');" id="${item.questionCd}_recommend" >推荐</a>
		                	<a href="javascript:toTop('${userInfo.userId}','${item.questionCd}');" id="${item.questionCd}_toTop" >置顶</a>		                  
		                  </c:if>
		                  <c:if test="${item.status == 0 or item.status == 2}">
		                  	<span style="color:#6C7B8B">推荐 </span>
		                	<span style="color:#6C7B8B">置顶</span>		                  		                  
		                  </c:if>		                
		                </c:if>
		                <c:if test="${item.recommend.size()==1}">
		                	<c:forEach items="${item.recommend}" var="recommendKbn">
		                		<c:if test="${recommendKbn.recommendKbn==1 }">
		                		  <c:if test="${item.status == 1}">
		                		  	<a href="javascript:cancleRecommend('${userInfo.userId}','${item.questionCd}');" id="${item.questionCd}_recommend" >取消推荐</a>
		                			<a href="javascript:toTop('${userInfo.userId}','${item.questionCd}');" id="${item.questionCd}_toTop" >置顶</a>		                		  
		                		  </c:if>
		                		  <c:if test="${item.status == 0 or item.status == 2}">
		                		  	<span style="color:#6C7B8B">取消推荐 </span>
		                			<span style="color:#6C7B8B">置顶</span>	                		  
		                		  </c:if>
		                		</c:if>
		                		<c:if test="${recommendKbn.recommendKbn==2 }">
		                		  <c:if test="${item.status == 1}">
		                		  	<a href="javascript:recommend('${userInfo.userId}','${item.questionCd}');" id="${item.questionCd}_recommend" >推荐</a>
		                			<a href="javascript:cancleToTop('${userInfo.userId}','${item.questionCd}');" id="${item.questionCd}_toTop" >取消置顶</a>
		                		  </c:if>
		                		  <c:if test="${item.status == 0 or item.status == 2}">
		                		  	<span style="color:#6C7B8B">推荐 </span>
		                			<span style="color:#6C7B8B">取消置顶</span>		                		  
		                		  </c:if>
		                		</c:if>
		                	</c:forEach>
		                </c:if>
		                <c:if test="${item.recommend.size()==2}">
		                  <c:if test="${item.status == 1}">
		                  	<a href="javascript:cancleRecommend('${userInfo.userId}','${item.questionCd}');" id="${item.questionCd}_recommend" >取消推荐</a>
		                	<a href="javascript:cancleToTop('${userInfo.userId}','${item.questionCd}');" id="${item.questionCd}_toTop" >取消置顶</a>
		                  </c:if>
		                  <c:if test="${item.status == 0 or item.status == 2}">
		                    <span style="color:#6C7B8B">取消推荐 </span>
		                	<span style="color:#6C7B8B">取消置顶</span>		                		  
		                  </c:if>
		                </c:if>
		                </td>
						<!-- 主题 -->
						<td style='text-align:left;'>${item.questionName}</td>
						<!-- 回答状态 --> 
						<td>${item.answerStr}</td>
						<!-- 领域 --> 
						<td style="text-align: left;">
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
						<!-- 提出时间 --> <%-- ${item.createTime}  --%>
						<td>
							<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />
						</td>
						<!-- 审核状态 -->
						<td>${item.statusStr}</td>
					</tr>
				</c:forEach>
			<!-- 翻页 -->
            <tr style='display:<c:if test="${resultList.items == null || resultList.items.size() == 0}">none</c:if>'>
                <%@include file="../common/page.jsp" %>
            </tr>
			</tbody>
		</table>
	</form>
	<!-- 推荐语弹窗 -->
	<div id="popupFormDiv" style="display: none;">
		<form id="popupForm" action="" method="post">
			<div class="alert alert-heading">
				<h4>推荐语编辑</h4>
			</div>
			<table class="tbform" style="margin: 0px; padding: 0px;">
				<tbody>
					<tr>
						<td class="tdl" colspan="4"><label id="message" style="display:none"></label></td>
					</tr>
					<tr>
						<td class="tdl">推荐语</td>
						<td class="td_detail">
						    <textarea name="DIVCSS5" id="DIVCSS5" style="width: 550px; height: 155px;"></textarea>
                        </td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
