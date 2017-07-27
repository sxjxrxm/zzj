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
    <title>e视频一览</title>
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
    <script type="text/javascript" src="<%=path %>/scripts/select-move-option.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/recommdtoTop.js"></script>
    
    
    <script type="text/javascript">
        var link = "<%=path %>/video/list.htm";
        $(function () {
            $(".datepicker").datepicker();
            
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
        // 推荐置顶一览
        function recommendList(){
	    	document.forms[0].action = "<%=path %>/recommend/list.htm";
	  		document.forms[0].submit();
	    }
        // 视频添加
        function add(){
			document.forms[0].action = "<%=path %>/video/editVideo.htm?isAdd=1";
	  		document.forms[0].submit();
		}
	    // 编辑功能
	    function editVideo(videoCd){
			document.forms[0].action = "<%=path %>/video/editVideo.htm?isAdd=0&videoCd="+videoCd;
	  		document.forms[0].submit();
		}
	    // 删除功能
		function delVideo(videoCd)
		{
			$('body').alert({
				type : 'info',
				buttons : [ {
					id : 'yes',
					name : '确定',
					callback : function() {
						document.forms[0].action = "<%=path %>/video/del.htm?videoCd="+videoCd;
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
	    // 编辑功能
	    function editLive(liveCd){
			document.forms[0].action = "<%=path %>/video/editLive.htm?isAdd=0&live=live&liveCd="+liveCd;
	  		document.forms[0].submit();
		}
	    // 获得选取复选框的值
		function showValues() {
			var oOption = $('#sela option:selected');
			multiValues = oOption.map(function() {
				return $(this).val();
			}).get().join(', ');
			alert(multiValues);
		}
        // CSV功能
	    function CSV(){
	    	document.forms[0].action = "<%=path %>/video/CSV.htm";
	  		document.forms[0].submit();
		}

		// userId：更新人id，  id：被更新人id
		// executeCode 操作分类： 1：置顶操作， 2：取消置顶操作， 3：推荐操作， 4：取消推荐操作
        // Code 操作分类： 1：置顶操作， 2：取消置顶操作， 3：推荐操作， 4：取消推荐操作
	    function recommend(userId, id) {
	    	// 清空错误信息
			$("#message").text("");
			$("#message").removeAttr("style");
			$("#message").attr("style", "display: none;");
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
		// 视频与直播区分，1：视频，2：直播
		var video_type = $("#video_type").val();
	 	// 推荐置顶
        function recommendExecute(userId, id, executeCode) {
        	if (executeCode == 3)
        	{
            	$.post("<%=path %>/recommend/recommendExecute.htm?code=" + executeCode, {
            		updateId:userId,
            		busiType:"03",
            		topicCd:id,
            		videoType:video_type,
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
            		busiType:"03",
            		topicCd:id,
            		videoType:video_type,
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
        // 根据视频分类确定推荐置顶筛选能否使用
        function controlRecommendTd(obj){
        	var checkBox = $("#recommendTd").children();
        	var videoType = $(obj).val();
        	if (videoType == 2) {
        		checkBox.removeAttr("checked").attr("disabled","disabled");
        	} else {
        		checkBox.removeAttr("disabled");
        	}
        }
    </script>
</head>
<body>
	<form id="form" action="" method="post" onkeydown="if(event.keyCode==13){find_list();return false;}">
		<div class="alert alert-heading">
			<h4>e视频一览</h4>
		</div>
		<%@include file="../common/common_msg.jsp"%>
		<div class="sub-title">
			<h5>查询条件</h5>
		</div>
		<table class="tbform" style="margin: 0px; padding: 0px;">
			<tbody>
				<tr>
					<td class="tdl">e视频主题</td>
					<td class="td_detail"><input class="span6" name="selectKey.videoName" value="${selectKey.videoName}"/></td>
                    <td class="tdl">费用</td>
                    <td class="td_detail">
                        <c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                    		<c:if test="${mstCodeInfo.codeType=='paymentKbn'}">
                    			<c:set var="selectPaymentKbn" value="${selectKey.paymentKbnDisp}" scope="page"></c:set>
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
	                    		<c:if test="${mstCodeInfo.codeType=='techFieldType'}">
	                    			<c:set var="selectTechField" value="${selectKey.field}" scope="page"></c:set>
                    				<c:set var="fieldCode" value="${mstCodeInfo.code}" scope="page"></c:set>
	                    			<option <c:if test="${fn:contains(selectTechField, fieldCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
	                    		</c:if>
	                    	</c:forEach>
                        </select>（可复选）
                    </td>
                    <td class="tdl">推荐置顶</td>
                    <td class="td_detail" id="recommendTd">
                        <c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                    		<c:if test="${mstCodeInfo.codeType=='recommendKbn'}">
                    			<c:set var="selectRecommendKbn" value="${selectKey.recommendKbn}" scope="page"></c:set>
                    			<c:set var="recommendCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                    			<input 
                    			<c:if test="${fn:contains(selectRecommendKbn, recommendCode)}">checked</c:if> 
                    			<c:if test="${selectKey.videoType eq 2}">disabled</c:if>
                    			type="checkbox" value="${mstCodeInfo.code}" name="selectKey.recommendKbn"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    		</c:if>
                    	</c:forEach>
                    </td>
				</tr>
				<tr>
					<td class="tdl">创建日期</td>
		            <td class="td_detail">
		                <div class="input-prepend input-append">
		                    <input class="input-small datepicker" id="sDate" name="selectKey.sDate" value="<fmt:formatDate value="${selectKey.sDate}" pattern="yyyy-MM-dd" />"/>
		                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
		                    &nbsp;&nbsp;~&nbsp;&nbsp;
		                    <input class="input-small datepicker" id="eDate" name="selectKey.eDate" value="<fmt:formatDate value="${selectKey.eDate}" pattern="yyyy-MM-dd" />"/>
		                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
		                </div>
		            </td>
					<td class="tdl">e视频分类</td>
					<td class="td_detail">
						<select class="dfinput" name="selectKey.videoType" onchange="controlRecommendTd(this);"> 
							<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
								<c:if test="${mstCodeInfo.codeType=='videoType'}">
									<c:set var="selectVideoType" value="${selectKey.videoType}" scope="page"></c:set>
									<c:set var="typeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
									<option <c:if test="${fn:contains(selectVideoType, typeCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
								</c:if>
							</c:forEach>				
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="8" class="td-title">
						<div class="div_bottom_right">
							<button class="btn btn-inverse no_repeat_submit"
								onclick="add(); return false;">视频添加</button>
							<button class="btn btn-inverse no_repeat_submit"
								onclick="recommendList(); return false;">推荐置顶一览</button>
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
					<th class="thCss">e视频主题</th>
					<th class="thCss">费用</th>
					<th class="thCss">领域</th>
					<th class="thCss">收藏数</th>
					<th class="thCss">播放次数</th>
					<th class="thCss">点赞数</th>
				</tr>
                <c:forEach items="${resultList.items}" var="item" varStatus="status">
            	<tr> 
            		<c:if test="${fn:contains(selectKey.videoType,'1')}"> <!-- 视频 -->
		                <td><a href="javascript:editVideo('${item.videoCd}');">编辑</a>
		                <a href="javascript:delVideo('${item.videoCd}');" style="color:#FF0000">删除</a><br/>
		                <c:if test="${item.recommend.size()==0}">
		                	<a class="expertRole" href="javascript:recommend('${userInfo.userId}','${item.videoCd}');" id="${item.videoCd}_recommend">推荐</a>
		                	<a class="expertRole" href="javascript:toTop('${userInfo.userId}','${item.videoCd}');" id="${item.videoCd}_toTop">置顶</a>
		                </c:if>
		                <c:if test="${item.recommend.size()==1}">
		                	<c:forEach items="${item.recommend}" var="recommendKbn">
		                		<c:if test="${recommendKbn.recommendKbn==1 }">
		                			<a class="expertRole" href="javascript:cancleRecommend('${userInfo.userId}','${item.videoCd}');" id="${item.videoCd}_recommend">取消推荐</a>
		                			<a class="expertRole" href="javascript:toTop('${userInfo.userId}','${item.videoCd}');" id="${item.videoCd}_toTop">置顶</a>
		                		</c:if>
		                		<c:if test="${recommendKbn.recommendKbn==2 }">
		                			<a class="expertRole" href="javascript:recommend('${userInfo.userId}','${item.videoCd}');" id="${item.videoCd}_recommend">推荐</a>
		                			<a class="expertRole" href="javascript:cancleToTop('${userInfo.userId}','${item.videoCd}');" id="${item.videoCd}_toTop">取消置顶</a>
		                		</c:if>
		                	</c:forEach>
		                </c:if>
		                <c:if test="${item.recommend.size()==2}">
		                	<a class="expertRole" href="javascript:cancleRecommend('${userInfo.userId}','${item.videoCd}');" id="${item.videoCd}_recommend">取消推荐</a>
		                	<a class="expertRole" href="javascript:cancleToTop('${userInfo.userId}','${item.videoCd}');" id="${item.videoCd}_toTop">取消置顶</a>
		                </c:if>
		                </td>
		                <!-- e视频主题 -->
		                <td style='text-align:left;'>${item.videoName}</td>
	                </c:if> 
	                
            		<c:if test="${selectKey.videoType eq 2}"> <!-- 直播 -->
		                <td>
		                	<a href="javascript:editLive('${item.liveCd}');">编辑</a>
		                <c:if test="${item.startFlag eq 0 or item.invalidFlag eq 1}"><!-- 未开始或已过期  -->
		                	<a href="javascript:delVideo('${item.liveCd}');" style="color:#FF0000">删除</a><br/>
		                </c:if>
		                <c:if test="${item.startFlag eq 1 and item.invalidFlag eq 0}"><!-- 已开始且未过期 -->
		                	<span style="color:#6C7B8B">删除</span>	<br/>
		                </c:if>
		                </td>
		                <!-- 直播主题 -->
		                <td style='text-align:left;'>${item.liveName}</td>
	                </c:if> 
	                
	                <!-- 费用 -->
	                <td>${item.paymentKbnDisp}</td>
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
	                <td style="text-align:right;">${item.collectCount}<c:if test="${empty item.collectCount}">0</c:if></td><!-- 收藏数 -->
	                <td style="text-align:right;">${item.scanCount}<c:if test="${empty item.scanCount}">0</c:if></td><!-- 播放次数 -->
	               	<td style="text-align:right;">${item.likeCount}<c:if test="${empty item.likeCount}">0</c:if></td><!-- 点赞数 -->
           	 	</tr>
              </c:forEach>
            <tr style='display:<c:if test="${resultList.items == null || resultList.items.size() == 0}">none</c:if>'>
                <%@include file="../common/page.jsp" %>
            </tr>
			</tbody>
		</table>
		<input type="hidden" value="${doSearch}" name="doSearch"/>
		<input type="hidden" value="${selectKey.videoType}" name="selectKey.videoType" id="video_type"/>
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