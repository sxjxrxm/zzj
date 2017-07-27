<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title> 
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/edit-page.css"/>    
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/select-checkbox/style.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/select-checkbox/jquery.multiselect.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/chur.css" />
    <style>
    	.tdl{width: 100px;}
    	.td_detail{width: auto;}
    	th {text-align: center;}
    </style>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="../scripts/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.multiselect.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.core.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/ChurAlert.min.js?skin=blue"></script>
    <script type="text/javascript" src="<%=path %>/scripts/tb.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-move-option.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/refuse.js"></script>
    <script type="text/javascript">
        var link = "<%=path %>/demand/answerPage.htm";
	    function back() {
	    	document.forms[0].action = "<%=path %>/demand/list.htm?flag=0";
	  		document.forms[0].submit();
		}
	    function save() {
	    	$("#choose_sel option").attr("selected", true);
	    	document.forms[0].action = "<%=path %>/demand/save.htm?flag=1";
	  		document.forms[0].submit();
		}
	    // 删除功能
		function delAnswer(needsCd, answerNo)
		{
			$('body').alert({
				type : 'info',
				buttons : [ {
					id : 'yes',
					name : '确定',
					callback : function() {
						document.forms[0].action = "<%=path %>/demand/delAnswer.htm?needsCd="+needsCd + "&answerNo="+answerNo;
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
	    // 设置为同步的请求
        $.ajaxSetup({
		    async : false  
		});
	    function answer(id, answerNo, content) {
	    	// 清空错误信息
			$("#message").text("");
			$("#message").removeAttr("style");
			$("#message").attr("style", "display: none;");
			// 编辑
	    	if (content != "")
	        {
	    		$("#DIVCSS5").text(content);
	        }	    	
	        $.dialog({
	            title: '添加回答',
	            content: $('#popupForm').html(),
	            lock: true,
	            width: 630,
	            height: 230,
	            min: false,
	            max: false,
	            cancelVal: '关闭',
	            button: [{
	                id: 'chur',
	                name: '保存',
	                callback: function() {
	                	answerExecute(id, answerNo);
	                	if ($('#message', parent.document).text() != "")
	                	{
	                		return false;	                		
	                	}	
	                }
	            }],
	            cancel: true
	        });
	    }
	    // 回答执行
        function answerExecute(id, answerNo) {
        	var content = parent.document.getElementById("DIVCSS5").value;
        	$.post("<%=path %>/demand/answer.htm?", 
        	{
        		needsCd:id,
        		answerNo:answerNo,
        		content:content,
        	}, function (data) {
        		if (data == 'success') {
        			var msg = $('#message', parent.document);
        			if(msg != null && msg != undefined) {
        				msg.text("");
        			}
			    	$("#choose_sel option").attr("selected", true);
        			document.forms[0].action = "<%=path %>/demand/answerPage.htm?needsCd="+id;
        	  		document.forms[0].submit();
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
    </script>
</head>
<body>
    <form id="form" action="" method="post" onkeydown="if(event.keyCode==13){save();return false;}">
	    <div class="alert alert-heading"><h4>需求的编辑•审核</h4></div>
	    <%@include file="../common/common_msg.jsp"%>
	    <div class="sub-title"><h5>需求基本信息</h5></div>
        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>                  
                    <td class="tdl">需求主题<span class="red">*</span></td>
                    <td class="td_detail" colspan="2">
                        <input class="span4" name="needsInfo.needsName" id="needsName" value="${needsInfo.needsName}" <c:if test="${needsInfo.status == 2}">disabled</c:if>/>
                    </td>
					<td class="tdl">需求分类<span class="red">*</span></td>
					<td class="td_detail" colspan="2">
						<select class="dfinput" name="needsInfo.needsType" id="needsType" <c:if test="${needsInfo.status == 2}">disabled</c:if>> 
							<option value="">==请选择==</option>
							<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
								<c:if test="${mstCodeInfo.codeType=='needsType'}">
									<c:set var="selectNeedsType" value="${needsInfo.needsType}" scope="page"></c:set>
									<c:set var="typeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
									<option <c:if test="${fn:contains(selectNeedsType, typeCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
								</c:if>
							</c:forEach>				
						</select>
					</td>
                </tr>           
                <tr>
                    <td class="tdl">领域<span class="red">*</span></td>
                    <td class="td_detail tbCenter"  style="width:80px;border-right-color:#fff;">
                    	 <select class="selectArea" onchange="javascript:selectChange(this);" id="brand_sel" multiple="multiple" <c:if test="${needsInfo.status == 2}">disabled</c:if>>
                    	 	<!-- 遍历otherFieldCd，不属于该需求的领域 -->
	                    	 <c:forEach items="${needsInfo.otherFieldCd}" var="otherFieldCd">
	                    	 	<!-- 从session中取一级领域的值保存 -->
			                	<c:forEach items="${techCodeInfos}" var="mstCodeInfo">
		                    		<c:if test="${mstCodeInfo.code==otherFieldCd.techFieldCd}">
		                    			<c:set value="${mstCodeInfo.code}" var="techFieldCode"></c:set>
		                    			<c:set value="${mstCodeInfo.codeName}" var="techFieldCodeName"></c:set>
		                    		</c:if>
		                    	</c:forEach>
		                    	<!-- 从session中取二级领域的值，并与一级领域合并保存 -->
		                    	<c:forEach items="${rschCodeInfos}" var="mstCodeInfo">
		                    		<c:if test="${mstCodeInfo.code==otherFieldCd.rschFieldCd}">
		                    			<c:set value="${mstCodeInfo.code}" var="rschFieldCode"></c:set>
		                    			<c:set value="${mstCodeInfo.codeName}" var="rschFieldCodeName"></c:set>
		                    		</c:if>
		                    	</c:forEach>
		                    	<!-- 使用临时保存的值 -->
		                    	<c:if test="${otherFieldCd.rschFieldCd != ' '}">
		                    		<option value="${techFieldCode}-${rschFieldCode}">${techFieldCodeName}-&gt;${rschFieldCodeName}</option>
		                    	</c:if>
		                    	<c:if test="${otherFieldCd.rschFieldCd == ' '}">
		                    		<option value="${techFieldCode}- ">${techFieldCodeName}</option>
		                    	</c:if>
	                    	</c:forEach>
					     </select>
				    </td>
				    <td class="td_detail tbCenter" style="width:50px;border-right-color:#fff;">
				         <input type="button" class="btn" id="brand_selBtn" disabled="disabled" value=" &gt;&gt; " onclick="javascript:rightMove();"/><br/>
				         <input type="button" class="btn" id="choose_selBtn" disabled="disabled" value=" &lt;&lt; " onclick="javascript:leftMove();"/><br/>
			        </td>
				    <td class="td_detail tbCenter" style="width:80px;border-right-color:#fff;">
				      	<select class="selectArea" onchange="javascript:selectChange(this);" name="fieldCd" id="choose_sel" multiple="multiple" <c:if test="${needsInfo.status == 2}">disabled</c:if>>
				      		<!-- 遍历fieldCd，属于该需求的领域 -->
				      		<c:forEach items="${needsInfo.fieldCd}" var="fieldCd">
				      			<!-- 从session中取一级领域的值保存 -->
			                	<c:forEach items="${techCodeInfos}" var="mstCodeInfo">
		                    		<c:if test="${mstCodeInfo.code==fieldCd.techFieldCd}">
		                    			<c:set value="${mstCodeInfo.code}" var="techFieldCode"></c:set>
		                    			<c:set value="${mstCodeInfo.codeName}" var="techFieldCodeName"></c:set>
		                    		</c:if>
		                    	</c:forEach>
		                    	<!-- 从session中取二级领域的值，并与一级领域合并保存 -->
		                    	<c:forEach items="${rschCodeInfos}" var="mstCodeInfo">
		                    		<c:if test="${mstCodeInfo.code==fieldCd.rschFieldCd}">
		                    			<c:set value="${mstCodeInfo.code}" var="rschFieldCode"></c:set>
		                    			<c:set value="${mstCodeInfo.codeName}" var="rschFieldCodeName"></c:set>
		                    		</c:if>
		                    	</c:forEach>
		                    	<!-- 使用临时保存的值 -->
		                    	<c:if test="${fieldCd.rschFieldCd != ' '}">
		                    		<option value="${techFieldCode}-${rschFieldCode}">${techFieldCodeName}-&gt;${rschFieldCodeName}</option>
		                    	</c:if>
		                    	<c:if test="${fieldCd.rschFieldCd == ' '}">
		                    		<option value="${techFieldCode}- ">${techFieldCodeName}</option>
		                    	</c:if>
	                    	</c:forEach>
				      	</select>
				    </td>
				    <td class="td_detail" colspan="3"></td>
                </tr>
                <tr>
                    <td class="tdl">需求内容<span class="red">*</span></td>
                    <td class="td_detail" colspan="7" >
                        <textarea name="needsInfo.needsContent" id="needsContent" rows="5" cols="170" style="text-align:left;width:auto;border-radius: 0;" <c:if test="${needsInfo.status == 2}">disabled</c:if>>${needsInfo.needsContent}</textarea>
                    </td>
                </tr>
                <tr>
	                <td class="tdl">审核结果</td>
	                <td class="td_detail" colspan="7" id="statusSelect">
	                    <input name="needsInfo.status" type="radio" value="1" <c:if test="${needsInfo.status == 1}">checked</c:if> <c:if test="${needsInfo.status == 2}">disabled</c:if>/>通过&nbsp;&nbsp;
	    			    <input name="needsInfo.status" type="radio" value="2" <c:if test="${needsInfo.status == 2}">checked</c:if> <c:if test="${needsInfo.status == 1}">disabled</c:if>/>拒绝&nbsp;&nbsp;&nbsp;&nbsp;
		    			<select  class="dfinput" id="sel" <c:if test="${needsInfo.status == 1 or needsInfo.status == 2}">disabled</c:if> name="needsInfo.refuseMemo" >
			                <option value="">==请选择==</option>
			                <c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
		                    	<c:if test="${mstCodeInfo.codeType=='refuseType'}">
		                    		<option value="${mstCodeInfo.code}" <c:if test="${needsInfo.refuseMemo == mstCodeInfo.code}">selected</c:if>>${mstCodeInfo.codeName}</option>
		                    	</c:if>
		                    </c:forEach>
					    </select>
	                </td>
	            </tr>
                <c:if test="${userInfo.roleId eq '1' or userInfo.roleId eq '3'}">
	                <tr>
	                    <td class="tdl">置顶</td>
	                    <td class="td_detail" colspan="7">
	                    	<c:set var="recommendKbn" value="${needsInfo.recommendKbn}" scope="page"></c:set>
	                        <input <c:if test="${fn:contains(recommendKbn, '2')}">checked</c:if> type="checkbox" name="toTopCode" value="2" id="toTop" 
	                          <c:if test="${needsInfo.status != 1}">disabled</c:if>/>
	                        <label class="checkbox-label" for="toTop">置顶</label>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="tdl">推荐</td>
	                    <td class="td_detail" colspan="7">
	                        <input <c:if test="${fn:contains(recommendKbn, '1')}">checked</c:if> type="checkbox" name="recommend" value="1" id="recommend" onclick="radioChange4();" 
	                          <c:if test="${needsInfo.status != 1}">disabled</c:if>/>
	                        <label class="checkbox-label" for="recommend">推荐</label>&nbsp;&nbsp;&nbsp;&nbsp;
	                        <input class="span5" id="recommendInput" value="${needsInfo.recommendMsg}" name="needsInfo.recommendMsg" maxlength="100"
		                        <c:choose>
		                        	<c:when test="${fn:contains(recommendKbn, '1')}"></c:when>
		                        	<c:otherwise>disabled="false"</c:otherwise>
		                        </c:choose>
		                        onfocus="recommendInputCheck();" <c:if test="${needsInfo.status == 2}">disabled</c:if>/>
	                    </td>
	                </tr>
                </c:if>
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button onclick="back(); return false;" class="btn btn-inverse no_repeat_submit">返回</button>
                            <button onclick="save(); return false;" class="btn btn-inverse no_repeat_submit" <c:if test="${needsInfo.status == 2}">disabled</c:if>>保存</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
       <div style='display:<c:if test="${needsInfo.status != '1'}">none</c:if>'>
	   <table class="tbform" style="margin:0px; padding:0px;">
	       <tr>
	       	   <td class="sub-title"><h5>回答一览</h5></td>
               <td colspan="8" class="td-title">
                   <div class="div_bottom_right">
                       <button class="btn btn-inverse no_repeat_submit" onclick="javascript:answer('${needsInfo.needsCd}', '', '');return false;">回答</button>
                   </div>
               </td>
            </tr>
        </table>
        <table class="tb" id="list"> 
         <tbody>
             <tr class="treven">
                <th class="thCss">操作 </th>
                <th class="thCss">回答内容</th>
                <th class="thCss">回答者</th>
                <th class="thCss">回答日</th>
            </tr>
            <c:forEach items="${resultList.items}" var="answer">
            <tr>
                <td style='text-align:center;'>
	                <a href="javascript:answer('${needsInfo.needsCd}','${answer.answerNo}','${answer.answerContent}');">编辑 </a>
	                <a href="javascript:delAnswer('${needsInfo.needsCd}','${answer.answerNo}');" style="color:#FF0000">删除 </a>
                </td>
                <td>${answer.answerContent}</td>
                <td>${answer.createName}</td>
                <td style='text-align:center;'><fmt:formatDate value="${answer.createTime}" pattern="yyyy-MM-dd" /></td>
            </tr>
            </c:forEach>
            <tr style='display:<c:if test="${resultList.items == null || resultList.items.size() == 0}">none</c:if>'>
                <%@include file="../common/page.jsp" %>
            </tr>
         </tbody>
      </table>
    </div>
	<!-- 页面回显使用的数据 -->
	<input type="hidden" value="${needsInfo.needsCd}" name="needsInfo.needsCd"/>
	<%-- <input type="hidden" value="${selectKey.needsName}" name="selectKey.needsName"/>
	<input type="hidden" value="${selectKey.answerStatus}" name="selectKey.answerStatus"/>
	<input type="hidden" value="${selectKey.field}" name="selectKey.field"/>
	<input type="hidden" value="${selectKey.needsType}" name="selectKey.needsType"/>
	<input type="hidden" value="${selectKey.sDate}" name="selectKey.sDate"/>
    <input type="hidden" value="${selectKey.eDate}" name="selectKey.eDate"/>
	<input type="hidden" value="${selectKey.auditStatus}" name="selectKey.auditStatus"/>
	<input type="hidden" value="${selectKey.recommendKbn}" name="selectKey.recommendKbn"/> --%>
	<input type="hidden" value="${selectKey}" name="selectKey"/>
	<input type="hidden" value="${needsPageNo}" name="needsPageNo"/>
    </form>
    <div id="popupFormDiv" style="display: none;">
		<form id="popupForm" action="" method="post">
			<div class="alert alert-heading">
				<h4>回答编辑</h4>
			</div>
			<table class="tbform" style="margin: 0px; padding: 0px;">
				<tbody>
					<tr>
						<td class="tdl" colspan="4"><label id="message" style="display:none"/></td>
					</tr>
					<tr>
						<td class="tdl">回答内容</td>
						<td class="tdl" style="color:#FF0000"><span class="red">*</span></td>
						<td class="td_detail" colspan="3">
						    <textarea name="DIVCSS5" id="DIVCSS5" style="width: 560px; height: 155px;"></textarea>
                        </td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
