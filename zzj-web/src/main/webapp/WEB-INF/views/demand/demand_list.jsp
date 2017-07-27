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
    <title>需求一览</title>
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
    <script type="text/javascript" src="../scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="../scripts/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="../scripts/select-checkbox/jquery.multiselect.js"></script>
    <script type="text/javascript" src="../scripts/select-checkbox/jquery.ui.core.js"></script>
    <script type="text/javascript" src="../scripts/select-checkbox/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="../scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="../scripts/ChurAlert.min.js?skin=blue"></script>
    <script type="text/javascript" src="../scripts/tb.js"></script>
    <script type="text/javascript" src="../scripts/recommdtoTop.js"></script>
    <script type="text/javascript">
        var link = "<%=path %>/demand/list.htm";
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
	    	document.forms[0].action = link;
	  		document.forms[0].submit();
		}
	    // 编辑功能
	    function edit(needsCd){
			document.forms[0].action = "<%=path %>/demand/edit.htm?flag=2&needsCd="+needsCd;
	  		document.forms[0].submit();
		}
	    function recommendList(){
	    	document.forms[0].action = "<%=path %>/recommend/list.htm";
	  		document.forms[0].submit();
	    }
	    // 删除功能
		function del(needsCd)
		{
			$('body').alert({
				type : 'info',
				buttons : [ {
					id : 'yes',
					name : '确定',
					callback : function() {
						document.forms[0].action = "<%=path %>/demand/del.htm?needsCd="+needsCd;
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
	    // 获得选取复选框的值
		function showValues() {
			var oOption = $('#sela option:selected');
			multiValues = oOption.map(function() {
				return $(this).val();
			}).get().join(', ');
			alert(multiValues);
		}
		function recom(){
			document.forms[0].action = "<%=path %>/recommend/list.htm";
			document.forms[0].submit();
		}
        // CSV功能
	    function CSV(){
	    	document.forms[0].action = "<%=path %>/demand/CSV.htm";
	  		document.forms[0].submit();
		}

        // 推荐置顶
        function recommendExecute(userId, id, executeCode) {
        	if (executeCode == 3)
        	{
            	$.post("<%=path %>/recommend/recommendExecute.htm?code=" + executeCode, {
            		updateId:userId,
            		busiType:"06",
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
            		busiType:"06",
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
    </script>
</head>
<body>
	<form id="form" action="" method="post" onkeydown="if(event.keyCode==13){find_list();return false;}">
		<div class="alert alert-heading">
			<h4>需求一览</h4>
		</div>
		<%@include file="../common/common_msg.jsp"%>
		<div class="sub-title">
			<h5>查询条件</h5>
		</div>
		<table class="tbform" style="margin: 0px; padding: 0px;">
			<tbody>
				<tr>
					<td class="tdl">需求主题</td>
					<td class="td_detail"><input class="span6" name="selectKey.needsName" value="${selectKey.needsName}"/></td>
					<td class="tdl">回答状态</td>
					<td class="td_detail">
						<c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="">
						    <c:if test="${mstCodeInfo.codeType=='answerStatus'}">
						    <c:set var="selectAnswerStatus" value="${selectKey.answerStatus}" scope="page"></c:set>
						    <c:set var="answerCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                    		<input <c:if test="${fn:contains(selectAnswerStatus, answerCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="selectKey.answerStatus"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    </c:if>
						</c:forEach>
                    </td>
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
					<td class="tdl">需求分类</td>
					<td class="td_detail">
						<select class="dfinput" name="selectKey.needsType"> 
							<option value="">==请选择==</option>
							<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
								<c:if test="${mstCodeInfo.codeType=='needsType'}">
									<c:set var="selectNeedsType" value="${selectKey.needsType}" scope="page"></c:set>
									<c:set var="typeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
									<option <c:if test="${fn:contains(selectNeedsType, typeCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
								</c:if>
							</c:forEach>				
						</select>
					</td>
				</tr>
				<tr>
					<td class="tdl">提出日期</td>
		            <td class="td_detail">
		                <div class="input-prepend input-append">
		                    <input class="input-small datepicker" id="sDate" name="selectKey.sDate" value="<fmt:formatDate value="${selectKey.sDate}" pattern="yyyy-MM-dd" />"/>
		                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
		                    &nbsp;&nbsp;~&nbsp;&nbsp;
		                    <input class="input-small datepicker" id="eDate" name="selectKey.eDate" value="<fmt:formatDate value="${selectKey.eDate}" pattern="yyyy-MM-dd" />"/>
		                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
		                </div>
		            </td>
                    <td class="tdl">审核状态</td>
                    <td class="td_detail">
                    	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="">
                    		<c:if test="${mstCodeInfo.codeType=='auditStatus'}">
                    			<c:set var="selectStatus" value="${selectKey.auditStatus}" scope="page"></c:set>
                    			<c:set var="statusCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                    			<input <c:if test="${fn:contains(selectStatus, statusCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="selectKey.auditStatus"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    		</c:if>
                    	</c:forEach>
                    </td>
				</tr>
				<tr>
                    <td class="tdl">推荐置顶</td>
                    <td class="td_detail">
                        <c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                    		<c:if test="${mstCodeInfo.codeType=='recommendKbn'}">
                    			<c:set var="selectRecommendKbn" value="${selectKey.recommendKbn}" scope="page"></c:set>
                    			<c:set var="recommendCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                    			<input <c:if test="${fn:contains(selectRecommendKbn, recommendCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="selectKey.recommendKbn"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    		</c:if>
                    	</c:forEach>
                    </td>
				</tr>
				<tr>
					<td colspan="8" class="td-title">
						<div class="div_bottom_right">
							<button class="btn btn-inverse no_repeat_submit"
								onclick="recom(); return false;">推荐置顶一览</button>
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
					<th class="thCss">操作</th>
					<th class="thCss">需求主题</th>
					<th class="thCss">回答状态</th>
					<th class="thCss">领域</th>
					<th class="thCss">需求分类</th>
					<th class="thCss">需求时间</th>
					<th class="thCss">审核状态</th>
				</tr>
                <c:forEach items="${resultList.items}" var="item" varStatus="status">
            	<tr>   
	                <td><a href="javascript:edit('${item.needsCd}');">编辑</a>
	                <a href="javascript:del('${item.needsCd}');" style="color:#FF0000">删除</a><br/>
	                <c:if test="${item.recommend.size()==0}">
	                  <c:if test="${item.status == 1}">
	                  	<a href="javascript:recommend('${userInfo.userId}','${item.needsCd}');" id="${item.needsCd}_recommend" >推荐</a>
	                	<a href="javascript:toTop('${userInfo.userId}','${item.needsCd}');" id="${item.needsCd}_toTop" >置顶</a>	       
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
	                		  	<a href="javascript:cancleRecommend('${userInfo.userId}','${item.needsCd}');" id="${item.needsCd}_recommend" >取消推荐</a>
	                			<a href="javascript:toTop('${userInfo.userId}','${item.needsCd}');" id="${item.needsCd}_toTop" >置顶</a>	                		  
	                		  </c:if>
		                	  <c:if test="${item.status == 0 or item.status == 2}">
		                	  	<span style="color:#6C7B8B">取消推荐 </span>
		                		<span style="color:#6C7B8B">置顶</span>	                		  
		                	  </c:if>
	                		</c:if>
	                		<c:if test="${recommendKbn.recommendKbn==2 }">
	                		  <c:if test="${item.status == 1}">
	                		  	<a href="javascript:recommend('${userInfo.userId}','${item.needsCd}');" id="${item.needsCd}_recommend" >推荐</a>
	                			<a href="javascript:cancleToTop('${userInfo.userId}','${item.needsCd}');" id="${item.needsCd}_toTop" >取消置顶</a>	                		
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
	                  	<a href="javascript:cancleRecommend('${userInfo.userId}','${item.needsCd}');" id="${item.needsCd}_recommend" >取消推荐</a>
	                	<a href="javascript:cancleToTop('${userInfo.userId}','${item.needsCd}');" id="${item.needsCd}_toTop" >取消置顶</a>
	                  </c:if>
	                  <c:if test="${item.status == 0 or item.status == 2}">
		                <span style="color:#6C7B8B">取消推荐 </span>
		                <span style="color:#6C7B8B">取消置顶</span>		                		  
		              </c:if>
	                </c:if>
	                </td>
	                <!-- 需求主题 -->
	                <td style='text-align:left;'>${item.needsName}</td>
	                <!-- 回答状态 -->
	                <td>${item.answerDisp}</td>
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
	                <!-- 需求分类 -->
	                <td>${item.needsTypeDisp}</td>
	                <!-- 需求时间 -->
	                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
	                <!-- 审核状态 -->
                    <td>${item.statusDisp}</td>
           	 	</tr>
              </c:forEach>
            <tr style='display:<c:if test="${resultList.items == null || resultList.items.size() == 0}">none</c:if>'>
                <%@include file="../common/page.jsp" %>
            </tr>
			</tbody>
		</table>
		<input type="hidden" value="${doSearch}" name="doSearch"/>
	</form>
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