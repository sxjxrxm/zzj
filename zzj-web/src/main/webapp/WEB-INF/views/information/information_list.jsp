<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/select-checkbox/style.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/select-checkbox/jquery.multiselect.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/jquery.autocomplete.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/chur.css" />
    <style>
    	td,th {text-align: center;}
    	.td_detail,.tdl {text-align: left;}
    </style>
    <!--<link rel="stylesheet" type="text/css" href="<%=path %>/styles/base.css"/>-->
    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.multiselect.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.core.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/ChurAlert.min.js?skin=blue"></script>
        
    <script type="text/javascript" src="<%=path %>/scripts/jquery.autocomplete.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/tb.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-move-option.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/recommdtoTop.js"></script>
    
    <script type="text/javascript">
    var link = "<%=path %>/information/list.htm";
 	// CSV功能
    function CSV(){
    	document.forms[0].action = "<%=path %>/information/CSV.htm";
  		document.forms[0].submit();
	}
    function find_list(){
    	$("input[name=doSearch]").val("1");
    	$("input[name=pageNo]").val("1");
    	document.forms[0].action = "<%=path %>/information/list.htm";
  		document.forms[0].submit();
	}
    function add(){
		document.forms[0].action = "<%=path %>/information/edit.htm?isAddNews=1";
  		document.forms[0].submit();
	}
    function delExpert(userId,id){
    	document.forms[0].action = "<%=path %>/information/del.htm?newsCd="+id+"&userId="+userId;
  		document.forms[0].submit();
	}
    function edit(id){
		document.forms[0].action = "<%=path %>/information/edit.htm?isAddNews=0&newsCd="+id;
  		document.forms[0].submit();
	}
    function recommendList(){
    	document.forms[0].action = "<%=path %>/recommend/list.htm";
  		document.forms[0].submit();
    }
    $(function() {
    	$(".datepicker").datepicker();
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
        //作者输入框的禁用与可用的切换
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
    //获得选取复选框的值
    function showValues() {
        var oOption = $('#sela option:selected');
        multiValues = oOption.map(function() {
            return $(this).val();
        }).get().join(', ');
        alert(multiValues);
    }
 	// 删除记录
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
	// 推荐置顶及取消推荐置顶操作，该方法被select-move-option.js中的函数调用
    // userId：更新人id，  id：被更新人id
	// executeCode 操作分类： 1：置顶操作， 2：取消置顶操作， 3：推荐操作， 4：取消推荐操作
    // 推荐置顶
        function recommendExecute(userId, id, executeCode) {
        	if (executeCode == 3)
        	{
            	$.post("<%=path %>/recommend/recommendExecute.htm?code=" + executeCode, {
            		updateId:userId,
            		busiType:"02",
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
            		busiType:"02",
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
    <form id="form" action="" method="post"  onkeydown="if(event.keyCode==13){find_list();return false;}">
    <div class="alert alert-heading"><h4>知识一览</h4></div>
    <%@include file="../common/common_msg.jsp"%>
        <div class="sub-title"><h5>查询条件</h5></div>
        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>
                   
                    <td class="tdl">知识主题</td>
                    <td class="td_detail" >
                        <input class="span6 expertRole" name="newsNameLike" value="${selectResource.newsNameLike}"/>
                    </td>
                   
                    <td class="tdl">费用</td>
                    <td class="td_detail">
                        <c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                    		<c:if test="${mstCodeInfo.codeType=='paymentKbn'}">
                    			<c:set var="selectPaymentKbn" value="${selectResource.paymentKbnLike}" scope="page"></c:set>
                    			<c:set var="paymentCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                    			<input class="expertRole" <c:if test="${fn:contains(selectPaymentKbn, paymentCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="paymentKbnLike"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    		</c:if>
                    	</c:forEach>
                    </td>
                    
                </tr>
                <tr>
                    
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
                   
                    <td class="tdl">知识分类</td>
                    <td class="td_detail">
                        <select class="span6 expertRole" name="newsTypeLike">
                        	<option value="">==请选择==</option>
	                        <c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="">
	                    		<c:if test="${mstCodeInfo.codeType=='newsType'}">
	                    			<c:set var="selectNewsType" value="${selectResource.newsTypeLike}" scope="page"></c:set>
                    				<c:set var="newsTypeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
	                    			<option <c:if test="${fn:contains(selectNewsType, newsTypeCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
	                    		</c:if>
	                    	</c:forEach>
                        </select>
                    </td>
                    
                </tr>
                <tr>
                    <td class="tdl">来源</td>
                    <td class="td_detail" colspan="7">
                    	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                    		<c:if test="${mstCodeInfo.codeType=='sourceType'}">
                    			<c:set var="selectSourceType" value="${selectResource.sourceTypeLike}" scope="page"></c:set>
                    			<c:set var="sourceTypeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                    			<input class="expertRole" id="sourceType${sourceTypeCode}" <c:if test="${fn:contains(selectSourceType, sourceTypeCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="sourceTypeLike"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    		</c:if>
                    	</c:forEach>
    			        <input class="span2 expertRole" id="expertInput" 
	    			        <c:choose>
	                    		<c:when test="${fn:contains(selectSourceType, '2')}"></c:when>
	                    		<c:otherwise>disabled="disabled"</c:otherwise>
	                    	</c:choose>
     			        	value="${selectResource.expertName}" name="expertNameLike"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">推荐置顶</td>
                    <td class="td_detail">
                         <c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                    		<c:if test="${mstCodeInfo.codeType=='recommendKbn'}">
                    			<c:set var="selectRecommendKbn" value="${selectResource.recommendKbnLike}" scope="page"></c:set>
                    			<c:set var="recommendCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                    			<input class="expertRole" <c:if test="${fn:contains(selectRecommendKbn, recommendCode)}">checked</c:if> type="checkbox" value="${mstCodeInfo.code}" name="recommendKbnLike"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    		</c:if>
                    	</c:forEach>
                    </td>
                    <td class="tdl">创建日期</td>
                    <td class="td_detail">
                        <div class="input-prepend input-append">
                        	  <input class="input-small datepicker expertRole" name="startDate" id="startDate" value="<fmt:formatDate value='${selectResource.startDate}' pattern='yyyy-MM-dd' />"/><span class="add-on"><i class="icon-calendar datepicker"></i></span>
                        		&nbsp;&nbsp;~&nbsp;&nbsp;
                        	  <input class="input-small datepicker expertRole" name="endDate" id="endDate" value="<fmt:formatDate value='${selectResource.endDate}' pattern='yyyy-MM-dd' />"/><span class="add-on"><i class="icon-calendar datepicker"></i></span>
                        </div>
                    </td>
                </tr>                          
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button class="btn btn-inverse no_repeat_submit" onclick="add(); return false;">知识添加</button>
                            <button class="btn btn-inverse no_repeat_submit expertRole" onclick="recommendList(); return false;">推荐置顶一览</button>
                            <button class="btn btn-inverse no_repeat_submit" onclick="CSV(); return false;">EXCEL导出</button>
                            <button class="btn btn-inverse no_repeat_submit expertRole" onclick="find_list(); return false;">查询</button>
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
                <th style="width:200px;">操作</th>
                <th>知识主题</th>
                <th>费用</th>
                <th>领域</th>
                <th>知识分类</th>
                <th>创建时间</th>
                <th>收藏数</th>
                <th>浏览数</th>
                <th>下载数</th>
            </tr>
            <c:forEach items="${resultList.items}" var="item" varStatus="status">
            <tr>
                <td id="op">
                	<c:if test="${userInfo.roleId != 2 or item.status != 1}">
                		<a href="javascript:edit('${item.newsCd}');">编辑</a>
	                	<a href="javascript:del('${userInfo.userId}','${item.newsCd}');" style="color:#FF0000">删除</a><br/>
	                </c:if>	                
                	<c:if test="${userInfo.roleId eq 2 and item.status eq 1}">
	                	<span style="color:#6C7B8B">编辑 </span><!-- 审核通过的文章，禁止专家用户重新修改 -->
		                <span style="color:#6C7B8B">删除 </span><br/>
	                </c:if>	                
	                <c:if test="${item.recommendKbn.size()==0 and item.status eq 1}">
	                	<a class="expertRole" href="javascript:recommend('${userInfo.userId}','${item.newsCd}');" id="${item.newsCd}_recommend">推荐</a>
	                	<a class="expertRole" href="javascript:toTop('${userInfo.userId}','${item.newsCd}');" id="${item.newsCd}_toTop">置顶</a>
	                </c:if>
	                <c:if test="${item.recommendKbn.size()==0 and item.status != 1}">
	                	<span style="color:#6C7B8B">推荐 </span>
		                <span style="color:#6C7B8B">置顶 </span>
	                </c:if>                
	                <c:if test="${item.recommendKbn.size()==1}">
	                	<c:forEach items="${item.recommendKbn}" var="recommendKbn">
	                		<c:if test="${recommendKbn.recommendKbn==1 and item.status eq 1}">
	                			<a class="expertRole" href="javascript:cancleRecommend('${userInfo.userId}','${item.newsCd}');" id="${item.newsCd}_recommend">取消推荐</a>
	                			<a class="expertRole" href="javascript:toTop('${userInfo.userId}','${item.newsCd}');" id="${item.newsCd}_toTop">置顶</a>
	                		</c:if>
	                		<c:if test="${recommendKbn.recommendKbn==1 and item.status != 1}">
	                			<span style="color:#6C7B8B">取消推荐  </span>
		                		<span style="color:#6C7B8B">置顶 </span>
	                		</c:if>	                		
	                		<c:if test="${recommendKbn.recommendKbn==2 and item.status eq 1}">
	                			<a class="expertRole" href="javascript:recommend('${userInfo.userId}','${item.newsCd}');" id="${item.newsCd}_recommend">推荐</a>
	                			<a class="expertRole" href="javascript:cancleToTop('${userInfo.userId}','${item.newsCd}');" id="${item.newsCd}_toTop">取消置顶</a>
	                		</c:if>
	                		<c:if test="${recommendKbn.recommendKbn==2 and item.status != 1}">
	                			<span style="color:#6C7B8B">推荐 </span>
		                		<span style="color:#6C7B8B">取消置顶 </span>
	                		</c:if>
	                	</c:forEach>
	                </c:if>                
	                <c:if test="${item.recommendKbn.size()==2 and item.status eq 1}">
	                	<a class="expertRole" href="javascript:cancleRecommend('${userInfo.userId}','${item.newsCd}');" id="${item.newsCd}_recommend">取消推荐</a>
	                	<a class="expertRole" href="javascript:cancleToTop('${userInfo.userId}','${item.newsCd}');" id="${item.newsCd}_toTop">取消置顶</a>
	                </c:if>
	                <c:if test="${item.recommendKbn.size()==2 and item.status != 1}">
	                	<span style="color:#6C7B8B">取消推荐 </span>
		                <span style="color:#6C7B8B">取消置顶 </span>
	                </c:if>
	            </td>
                <td style="text-align:left;">${item.newsName}</td>
                <td><!-- 费用分类 -->
                	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                   		<c:if test="${mstCodeInfo.codeType=='paymentKbn' && mstCodeInfo.code==item.paymentKbn}">
                   			${mstCodeInfo.codeName}
                   		</c:if>
                   	</c:forEach>
                </td>
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
                <td><!-- 资讯分类 -->
                	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                   		<c:if test="${mstCodeInfo.codeType=='newsType' && mstCodeInfo.code==item.newsType}">
                   			${mstCodeInfo.codeName}
                   		</c:if>
                   	</c:forEach>
                </td>
                <td>
                	<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /><br/>
                	<fmt:formatDate value="${item.createTime}" pattern="HH:mm:ss" />
                </td>
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
