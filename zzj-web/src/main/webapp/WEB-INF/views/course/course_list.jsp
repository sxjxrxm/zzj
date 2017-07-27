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
    <title>e课堂一览</title>
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
    <script type="text/javascript" src="../scripts/cookie.js"></script>
    <script type="text/javascript">
        var link = "<%=path %>/course/list.htm";
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
	    function find_list() {
	    	document.forms[0].action = link;
	    	document.forms[0].target = "_self";
	  		document.forms[0].submit();
		}
        // 添加课堂
        function add() {
    		document.forms[0].action = "<%=path %>/course/edit.htm?isAdd=1&flag=2";
    		document.forms[0].target = "_self";
      		document.forms[0].submit();
    	}
	    // 编辑功能
	    function edit(courseCd) {
			document.forms[0].action = "<%=path %>/course/edit.htm?flag=2&courseCd="+courseCd;
			document.forms[0].target = "_self";
	  		document.forms[0].submit();
		}
	    // 删除功能
		function del(courseCd)
		{
			$('body').alert({
				type : 'info',
				buttons : [ {
					id : 'yes',
					name : '确定',
					callback : function() {
						document.forms[0].action = "<%=path %>/course/del.htm?courseCd="+courseCd;
						document.forms[0].target = "_self";
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
	    // 推荐置顶一览
		function recom() {
			document.forms[0].action = "<%=path %>/recommend/list.htm";
			document.forms[0].target = "_self";
			document.forms[0].submit();
		}
        // CSV功能
	    function CSV() {
	    	document.forms[0].action = "<%=path %>/course/CSV.htm";
	    	document.forms[0].target = "_self";
	  		document.forms[0].submit();
		}
        // 推荐置顶
        function recommendExecute(userId, id, executeCode) {
        	if (executeCode == 3)
        	{
            	$.post("<%=path %>/recommend/recommendExecute.htm?code=" + executeCode, {
            		updateId:userId,
            		busiType:"04",
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
            		busiType:"04",
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
        function closeCourse(courseCd) {
			$('body').alert({
				type : 'info',
				title: '关闭课堂提示',
				content: '你确定要关闭这个课堂吗？',
				buttons : [ {
					id : 'yes',
					name : '确定',
					callback : function() {
						document.forms[0].action = "<%=path %>/course/close.htm?courseCd=" + courseCd;
						document.forms[0].target = "_self";
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
        function enterCourse(courseCd) {
			if (getCookie(courseCd)) {
				//alert("窗口已打开");
				$('body').alert({
					type : 'openCourse',
					title: '进入课堂提示',
					content: '当前课堂已在浏览器打开，请勿重复进入课堂！',
					buttons : [ {
						id : 'yes',
						name : '确定',
						callback : function() {
						}
					}]
				})
			} else {
				document.forms[0].action = "<%=path %>/course/enterCourse.htm?courseCd="+courseCd;
				document.forms[0].target = "_blank";
				setCookie(courseCd,courseCd);
		  		document.forms[0].submit();
			}
		}
     
    </script>
</head>
<body>
	<form id="form" action="" method="post"  onkeydown="if(event.keyCode==13){find_list();return false;}">
		<div class="alert alert-heading">
			<h4>e课堂一览</h4>
		</div>
		<%@include file="../common/common_msg.jsp"%>
		<div class="sub-title">
			<h5>查询条件</h5>
		</div>
		<table class="tbform" style="margin: 0px; padding: 0px;">
			<tbody>
				<tr>
					<td class="tdl">e课堂主题</td>
					<td class="td_detail"><input class="span6" name="selectKey.courseName" value="${selectKey.courseName}"/></td>
                    <td class="tdl">费用</td>
                    <td class="td_detail">
                        <c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                    		<c:if test="${mstCodeInfo.codeType=='paymentKbn'}">
                    			<c:set var="selectPaymentKbn" value="${selectKey.paymentKbn}" scope="page"></c:set>
                    			<c:set var="paymentCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                    			<input <c:if test="${fn:contains(selectPaymentKbn, paymentCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="selectKey.paymentKbn"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
                    <td class="tdl">e课堂日期</td>
		            <td class="td_detail">
		                <div class="input-prepend input-append">
		                    <input class="input-small datepicker" id="sDate" name="selectKey.sDate" value="<fmt:formatDate value="${selectKey.sDate}" pattern="yyyy-MM-dd" />"/>
		                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
		                    &nbsp;&nbsp;~&nbsp;&nbsp;
		                    <input class="input-small datepicker" id="eDate" name="selectKey.eDate" value="<fmt:formatDate value="${selectKey.eDate}" pattern="yyyy-MM-dd" />"/>
		                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
		                </div>
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
                    <td class="tdl">课堂分类</td>
                    <td class="td_detail">
                        <c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                    		<c:if test="${mstCodeInfo.codeType=='courseType'}">
                    			<c:set var="selectCourseType" value="${selectKey.courseType}" scope="page"></c:set>
                    			<c:set var="courseTypeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                    			<input <c:if test="${fn:contains(selectCourseType, courseTypeCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="selectKey.courseType"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    		</c:if>
                    	</c:forEach>
                    </td>
				</tr>
				<tr>
					<td colspan="8" class="td-title">
						<div class="div_bottom_right">
							<button class="btn btn-inverse no_repeat_submit"
								onclick="add(); return false;">课堂添加</button>
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
					<th class="thCss">e课堂主题</th>
					<th class="thCss">课堂分类</th>
					<th class="thCss">费用</th>
					<th class="thCss">领域</th>
					<th class="thCss">e课堂日期</th>
					<th class="thCss">参与人数</th>
				</tr>
                <c:forEach items="${resultList.items}" var="item" varStatus="status">
            	<tr>   
	                <td><a href="javascript:enterCourse('${item.courseCd}');" >进入课堂</a>
	                <a href="javascript:edit('${item.courseCd}');">编辑</a>
	                <a href="javascript:del('${item.courseCd}');" style="color:#FF0000">删除</a><br/>
	                <c:if test="${item.courseType==0}">
	                    <a href="javascript:closeCourse('${item.courseCd}');">关闭课堂</a>
	                    <input type="hidden" value="${item.roomId}" name="${item.courseCd}_roomId"/>
	                </c:if>
	                <c:if test="${item.courseType==1}">
	                    <span style="color:#6C7B8B">关闭课堂 </span>	
	                </c:if>
	                <c:if test="${item.recommend.size()==0}">
	                  	<a href="javascript:recommend('${userInfo.userId}','${item.courseCd}');" id="${item.courseCd}_recommend" >推荐</a>
	                	<a href="javascript:toTop('${userInfo.userId}','${item.courseCd}');" id="${item.courseCd}_toTop" >置顶</a>	       
	                </c:if>
	                <c:if test="${item.recommend.size()==1}">
	                	<c:forEach items="${item.recommend}" var="recommendKbn">
	                		<c:if test="${recommendKbn.recommendKbn==1 }">
	                		  	<a href="javascript:cancleRecommend('${userInfo.userId}','${item.courseCd}');" id="${item.courseCd}_recommend" >取消推荐</a>
	                			<a href="javascript:toTop('${userInfo.userId}','${item.courseCd}');" id="${item.courseCd}_toTop" >置顶</a>	                		  
	                		</c:if>
	                		<c:if test="${recommendKbn.recommendKbn==2 }">
	                		  	<a href="javascript:recommend('${userInfo.userId}','${item.courseCd}');" id="${item.courseCd}_recommend" >推荐</a>
	                			<a href="javascript:cancleToTop('${userInfo.userId}','${item.courseCd}');" id="${item.courseCd}_toTop" >取消置顶</a>	                		
	                		</c:if>
	                	</c:forEach>
	                </c:if>
	                <c:if test="${item.recommend.size()==2}">
	                  	<a href="javascript:cancleRecommend('${userInfo.userId}','${item.courseCd}');" id="${item.courseCd}_recommend" >取消推荐</a>
	                	<a href="javascript:cancleToTop('${userInfo.userId}','${item.courseCd}');" id="${item.courseCd}_toTop" >取消置顶</a>
	                </c:if>
	                </td>
	                <!-- e课堂主题 -->
	                <td style='text-align:left;'>${item.courseName}</td>
	                <!-- 课堂分类 -->
	                <td>${item.courseTypeDisp}</td>
	                <!-- 费用 -->
	                <td>${item.paymentType}</td>
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
	                <!-- e课堂日期 -->
	                <td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd" /></td>
	                <!-- 参与人数 -->
	                <td style='text-align:right;'>${item.scanCount}</td>
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