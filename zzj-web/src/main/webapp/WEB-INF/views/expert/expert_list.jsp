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
    <!--<link rel="stylesheet" type="text/css" href="../Styles/admin-all.css" />-->
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/select-checkbox/style.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/select-checkbox/jquery.multiselect.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/chur.css" />
    <style>
    	td,th {text-align: center;}
    	.td_detail,.tdl {text-align: left;}
    </style>
    <!--<link rel="stylesheet" type="text/css" href="../Styles/base.css"/>-->
    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.multiselect.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.core.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/ChurAlert.min.js?skin=blue"></script>
    <script type="text/javascript" src="<%=path %>/scripts/tb.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-move-option.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/recommdtoTop.js"></script>
    <script type="text/javascript">
    var link = "<%=path %>/expert/list.htm";
	    function find_list(){
	    	$("input[name=doSearch]").val("1");
	    	$("input[name=pageNo]").val("1");
	    	document.forms[0].action = "<%=path %>/expert/list.htm";
	  		document.forms[0].submit();
		}
        function add(){
			document.forms[0].action = "<%=path %>/expert/edit.htm?isAdd=1&list=list";
	  		document.forms[0].submit();
		}
        function delExpert(userId,id){
        	document.forms[0].action = "<%=path %>/expert/del.htm?expertId="+id+"&userId="+userId;
	  		document.forms[0].submit();
		}
        function edit(id){
			document.forms[0].action = "<%=path %>/expert/edit.htm?isAdd=0&list=list&from=list&flag=2&expertId="+id;
	  		document.forms[0].submit();
		}
        function recommendList(){
        	document.forms[0].action = "<%=path %>/recommend/list.htm";
	  		document.forms[0].submit();
        }
        $(function () {
            var roleId = ${userInfo.roleId};// 获得当前用户的id，2：专家用户
            if (roleId == 2) {
            	$(".expertRole").attr("disabled","disabled");// 禁用筛选条件
            	$("#sela").empty(); // 领域复选框内容清空
            	$("#op .expertRole").attr("href","javascript:return false;").css("color","#6C7B8B");// 禁用推荐置顶及删除操作，仅保留编辑操作
            }
            if (roleId != 2) {// 非专家用户登录时启用复选框功能
            	$("#sela").multiselect({
    				noneSelectedText: "==请选择==",
    				checkAllText: "全选",
    				uncheckAllText: '全不选',
    				selectedList:6
    				});
            }
            
        })
        
        //获得选取复选框的值
		function showValues() {
			var oOption = $('#sela option:selected');
			multiValues = oOption.map(function() {
				return $(this).val();
			}).get().join(', ');
			alert(multiValues);
		}
        // 删除专家记录
		function del(userId,id) {
			$('body').alert({
				type : 'info',
				buttons : [ {
					id : 'yes',
					name : '确定',
					callback : function() {
						delExpert(userId,id);
					}
				}, {
					id : 'no',
					name : '取消',
					callback : function() {
					}
				} ]
			})
		}
        // userId：更新人id，  id：被更新人id
		// executeCode 操作分类： 1：置顶操作， 2：取消置顶操作， 3：推荐操作， 4：取消推荐操作
        // Code 操作分类： 1：置顶操作， 2：取消置顶操作， 3：推荐操作， 4：取消推荐操作
	    function recommend(userId, id) {
	    	// 清空错误信息
			$("#message").text("");
			$("#message").removeAttr("style");
			$("#message").attr("style", "display: none;");
			$("#DIVCSS5").text("");
            // 取得前回推荐语
			$.post("<%=path %>/recommend/recommendMsg.htm?", {
		  		busiType:"01",
		  		topicCd:id,
			  }, function (data) {				  
			  		if (data != "")
			  		{
			  			$("#DIVCSS5").text(data);
			  		}
			});
 	        $.dialog({
	            title: '添加推荐语',
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
	                	recommendExecute(userId, id, 3);
	                	if ($('#message', parent.document).text() != "")
	                	{
	                		return false;	                		
	                	}	                	
	                }
	            }],
	            cancel: true
	        }); 
	    }
	 	// 推荐置顶
        function recommendExecute(userId, id, executeCode) {
        	if (executeCode == 3)
        	{
            	$.post("<%=path %>/recommend/recommendExecute.htm?code=" + executeCode, {
            		updateId:userId,
            		busiType:"01",
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
            		busiType:"01",
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
        // 推荐状态变更
        function changeRecommend(code, obj) {
            var func = obj.attr("href");
            if (code == 3) {
            	obj.text("取消推荐");//.css("background", "yellow");
                func = func.replace("recommend", "cancleRecommend");
                obj.attr("href", func);
            }
            if (code == 4) {
            	obj.text("推荐");//.css("background", "none");
                func = func.replace("cancleRecommend", "recommend");
                obj.attr("href", func);
            }
        }
        // 置顶状态变更
        function changeToTop(code, obj) {
            var func = obj.attr("href");
            if (code == 1) 
            {
            	obj.text("取消置顶");//.css("background", "none");
                func = func.replace("toTop", "cancleToTop");
                obj.attr("href", func);
            }
            if (code == 2) 
            {
            	obj.text("置顶");//.css("background", "yellow");
                func = func.replace("cancleToTop", "toTop");
                obj.attr("href", func);
            } 
        }
     	// CSV功能
	    function CSV(){
	    	document.forms[0].action = "<%=path %>/expert/CSV.htm";
	  		document.forms[0].submit();
		}
     	
</script>
</head>
<body>
    <form id="form" action="" method="post" onkeydown="if(event.keyCode==13){find_list();return false;}">
    <div class="alert alert-heading"><h4>专家一览</h4></div>
    <%@include file="../common/common_msg.jsp"%>
        <div class="sub-title"><h5>查询条件</h5></div>
        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>
                   
                    <td class="tdl">专家姓名</td>
                    <td class="td_detail">
                        <input class="span2 expertRole" name="expertNameLike" value="${selectResource.expertName}"/>
                    </td>
                   
                    <td class="tdl">领域</td>
                    <td class="td_detail">
                        <select class="span6 expertRole" id ="sela" multiple="multiple" size="5" name="techField">
	                        <c:forEach items="${techCodeInfos}" var="mstCodeInfo" varStatus="">
								<c:set var="selectTechField" value="${selectResource.techField}" scope="page"></c:set>
                    			<c:set var="fieldCode" value="${mstCodeInfo.code}" scope="page"></c:set>
	                    		<option <c:if test="${fn:contains(selectTechField, fieldCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
	                    	</c:forEach>
                        </select>（可复选）
                    </td>
                </tr>
                <tr>
                    <td class="tdl">审核状态</td>
                    <td class="td_detail">
                    	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="">
                    		<c:if test="${mstCodeInfo.codeType=='auditStatus'}">
                    			<c:set var="selectStatus" value="${selectResource.auditStatus}" scope="page"></c:set>
                    			<c:set var="statusCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                    			<input class="expertRole" <c:if test="${fn:contains(selectStatus, statusCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="auditStatus"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    		</c:if>
                    	</c:forEach>
                    </td>

                    <td class="tdl">推荐置顶</td>
                    <td class="td_detail">
                        <c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                    		<c:if test="${mstCodeInfo.codeType=='recommendKbn'}">
                    			<c:set var="selectRecommendKbn" value="${selectResource.recommendKbn}" scope="page"></c:set>
                    			<c:set var="recommendCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                    			<input class="expertRole" <c:if test="${fn:contains(selectRecommendKbn, recommendCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="recommendKbn"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    		</c:if>
                    	</c:forEach>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button class="btn btn-inverse no_repeat_submit expertRole" onclick="add(); return false;">专家添加</button>
                            <button class="btn btn-inverse no_repeat_submit expertRole" onclick="recommendList(); return false;">推荐置顶一览</button>
                            <button class="btn btn-inverse no_repeat_submit" onclick="CSV(); return false;">EXCEL导出</button>
                            <button class="btn btn-inverse no_repeat_submit expertRole" onclick="find_list();return false;">查询</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        
    <!--检索一览-->
    <div class="sub-title"><h5>查询结果</h5></div>
     <table class="tb" id="list">
         <tbody>
             <tr>
                <th>操作 </th>
                <th>专家名称</th>
                <th>领域</th>
                <th>审核状态</th>
                <th>文章数 </th>
                <th>待审数 </th>
                <th>点赞数 </th>
                <th>收藏数 </th>
                <th>浏览数 </th>
                <th>下载数 </th>
            </tr>
            
            <c:forEach items="${resultList.items}" var="item" varStatus="status">
            	<tr>   
	                <td id="op">
	                <a href="javascript:edit('${item.expertId}');">编辑</a>
	                <!-- <a>审核</a> -->
	                <a class="expertRole" href="javascript:del('${userInfo.userId}','${item.expertId}');" style="color:#FF0000">删除</a><br/>
	                
	                <c:if test="${item.recommendKbn.size()==0 and item.status eq 1}">
	                	<a class="expertRole" href="javascript:recommend('${userInfo.userId}','${item.expertId}');" id="${item.expertId}_recommend">推荐</a>
	                	<a class="expertRole" href="javascript:toTop('${userInfo.userId}','${item.expertId}');" id="${item.expertId}_toTop">置顶</a>
	                </c:if>
	                <c:if test="${item.recommendKbn.size()==0 and item.status != 1}">
	                	<span style="color:#6C7B8B">推荐 </span>
		                <span style="color:#6C7B8B">置顶 </span>
	                </c:if>	                
	                <c:if test="${item.recommendKbn.size()==1}">
	                	<c:forEach items="${item.recommendKbn}" var="recommendKbn">
	                		<c:if test="${recommendKbn.recommendKbn==1 and item.status eq 1}">
	                			<a class="expertRole" href="javascript:cancleRecommend('${userInfo.userId}','${item.expertId}');" id="${item.expertId}_recommend">取消推荐</a>
	                			<a class="expertRole" href="javascript:toTop('${userInfo.userId}','${item.expertId}');" id="${item.expertId}_toTop">置顶</a>
	                		</c:if>
	                		<c:if test="${recommendKbn.recommendKbn==1 and item.status != 1}">
	                			<span style="color:#6C7B8B">取消推荐  </span>
		                		<span style="color:#6C7B8B">置顶 </span>
	                		</c:if>
	                		
	                		<c:if test="${recommendKbn.recommendKbn==2 and item.status eq 1}">
	                			<a class="expertRole" href="javascript:recommend('${userInfo.userId}','${item.expertId}');" id="${item.expertId}_recommend">推荐</a>
	                			<a class="expertRole" href="javascript:cancleToTop('${userInfo.userId}','${item.expertId}');" id="${item.expertId}_toTop">取消置顶</a>
	                		</c:if>
	                		<c:if test="${recommendKbn.recommendKbn==2 and item.status != 1}">
	                			<span style="color:#6C7B8B">推荐 </span>
		                		<span style="color:#6C7B8B">取消置顶 </span>
	                		</c:if>
	                	</c:forEach>
	                </c:if>	                
	                <c:if test="${item.recommendKbn.size()==2 and item.status eq 1}">
	                	<a class="expertRole" href="javascript:cancleRecommend('${userInfo.userId}','${item.expertId}');" id="${item.expertId}_recommend">取消推荐</a>
	                	<a class="expertRole" href="javascript:cancleToTop('${userInfo.userId}','${item.expertId}');" id="${item.expertId}_toTop">取消置顶</a>
	                </c:if>
	                <c:if test="${item.recommendKbn.size()==2 and item.status != 1}">
	                	<span style="color:#6C7B8B">取消推荐 </span>
		                <span style="color:#6C7B8B">取消置顶 </span>
	                </c:if>              
	                </td>
	                <td style="text-align:left;">${item.expertName}</td>
	                <td style="text-align:left;"><!-- 领域 -->
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
	                <td><!-- 审核状态 -->
	                	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                    		<c:if test="${mstCodeInfo.codeType=='auditStatus' && mstCodeInfo.code==item.status}">
                    			${mstCodeInfo.codeName}
                    		</c:if>
                    	</c:forEach>
	                </td>
	                <td style="text-align:right;">${item.articleCount}</td> <!--文章数 -->
	                <td style="text-align:right;">${item.pendingReviewArticleCount}</td><!-- 待审数 -->
	                <td style="text-align:right;">${item.userHandleInfo.likeCount}<c:if test="${empty item.userHandleInfo.likeCount}">0</c:if></td><!-- 点赞数  -->
	                <td style="text-align:right;">${item.userHandleInfo.collectCount}<c:if test="${empty item.userHandleInfo.collectCount}">0</c:if></td><!-- 收藏数 -->
	                <td style="text-align:right;">${item.userHandleInfo.scanCount}<c:if test="${empty item.userHandleInfo.scanCount}">0</c:if></td><!-- 浏览数 -->
	                <td style="text-align:right;">${item.userHandleInfo.downloadCount}<c:if test="${empty item.userHandleInfo.downloadCount}">0</c:if></td><!-- 下载数 -->
           	 	</tr>
            </c:forEach>
           	<c:if test="${doSearch=='1' and !empty resultList}">
            <tr>
            	<%@include file="../common/page.jsp" %>
            </tr>
            </c:if>
            </tbody>
    </table>
    <input type="hidden" value="${doSearch}" name="doSearch"/><!-- 记录一览页面是否执行了查询操作 -->
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
						<td class="tdl" style="color:#FF0000"><span class="red">*</span></td>
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
