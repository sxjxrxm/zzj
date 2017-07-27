<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="../styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="../styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="../styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/chur.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/edit-page.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/upload.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/jquery-ui-timepicker-addon.css" />
    <link rel="stylesheet" type="text/css" href="../styles/jquery.autocomplete.css"/>
    <style>
    	.tdl{width: 100px;}
    	.td_detail{width: auto;}
    </style>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="../scripts/chur-alert.1.0.js"></script>
	<script type="text/javascript" src="../scripts/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-timepicker-addon.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-timepicker-zh-CN.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/bootstrap.min.js"></script>
    <script type="text/javascript" src="../scripts/select-move-option.js"></script>
    <script type="text/javascript" src="../scripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="../scripts/jquery.autocomplete.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">
	    $(function () {
            
	        $(".datepicker").datetimepicker({
	        	defaultDate: $('.ui_timepicker').val(),  
            	dateFormat: "yy-mm-dd",  
            	timeFormat: 'HH:mm',  
            	stepHour: 1,  
            	stepMinute: 1
	        })
	        
	        // 格式化金额
	        $("#chargeInput").val(AngelMoney($("#chargeInput").val()));
         	// 图片上传
	        $("#littleIconBtn").click(function(){
	        	$("#littleIconImgData").click();
	        });
	        $("#bigIconBtn").click(function(){
	        	$("#bigIconImgData").click();
	        });
	        
        	// 滚动事件
            $(window).scroll(function () {
                var offsetTop = menuYloc + $(window).scrollTop();
                offsetTop = (offsetTop > editHeight ? editHeight : offsetTop) + "px";
                $(".editorSlide").animate({ top: offsetTop}, { duration: 600, queue: false });  
            });

            // 预览按钮操作
            $(".imb-preview").click(function(){
              var html = ue.getAllHtml();
  	          if(html){
		         $('#viewModal .device-content').html(ue.getContent());
		         $('#viewModal').modal("show");
		      }else{
		         alert("没有预览内容");
  		      }
            });
            
	        // 费用控制
	        radioChange();
            $("input[name=paymentKbn]").click(function(){
                radioChange();
            });
        	$.get("<%=path %>/expert/getExpertName.htm", {}, function (data) { 
	        	$(".expertCdInput").autocomplete(data.split("    "),{minChars:0,max:100});
	        });
	    })
	    function back() {
	    	document.forms[0].action = "<%=path %>/course/list.htm?flag=0";
	  		document.forms[0].submit();
		}
	    function save() {
	    	$("#choose_sel option").attr("selected",true);
	    	var price = $("#chargeInput").val();
	    	price = price.replace(",","");
	    	$("#chargeInput").val(price); 
	    	
	    	// 将右侧领域的所有option设置为选中，随form表单提交
	    	// 将右侧领域的内容赋值给隐藏的input：fieldNameForKeyWord，后台构造关键词使用
	    	var fieldName = new Array();
	    	$("#choose_sel option").each(function () {
	    		var txt = $(this).text();
	    		if(txt != ''){
	    			fieldName.push(txt);
	    		}
	    	});   	   		    	
	    	$("#fieldNameForKeyWord").attr("value",fieldName);
	    	
	    	document.forms[0].action = "<%=path %>/course/save.htm?flag=1";
	  		document.forms[0].submit();
		}
	    // 图片异步上传
		function ajaxFileUpload(id) {
		    $.ajaxFileUpload({
		                url: '${pageContext.request.contextPath}/course/upload.htm?id='+id,
		        		secureuri : false,// 安全协议
		        		async : false,
		        		fileElementId : id+'ImgData',
		        		dataType:'json',
		        		type : "POST",
		        		success : function(data) {
		        			<%--地址存储——————切记修改！--%>
		        			if (data.message == 'success')
		        			{		        			
		        				// 将图片上传地址保存到隐藏的input，此时保存的是临时文件
			        			$("#"+id+"Img").attr("src", data.url);
			        			$("#"+id).val(data.path);
			                    console.log(data);
			                    $("#"+id+"Url").val(data.url);
			                }
		        			else
		        			{
		        				alert(data.message);
		        			}
		                },
		                error: function (data, status, e)// 服务器响应失败处理函数
		                {
		                    alert(e);
		                }
		            }
		     )
		     return false;
		 }
    </script>
</head>
<body>
    <form id="form" action="" method="post" onkeydown="if(event.keyCode==13){save();return false;}">
	    <div class="alert alert-heading"><h4>e课堂编辑</h4></div>
	    <%@include file="../common/common_msg.jsp"%>
        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>                  
                    <td class="tdl">e课堂主题<span class="red">*</span></td>
                    <td class="td_detail" colspan="7">
                        <input class="span8" name="courseInfo.courseName" id="courseName" value="${courseInfo.courseName}" maxlength="30"/>※最多输入30个字
                    </td>
                </tr>           
                <tr>
                    <td class="tdl">领域<span class="red">*</span></td>
                    <td class="td_detail tbCenter"  style="width:80px;border-right-color:#fff;">
                    	 <select class="selectArea" onchange="javascript:selectChange(this);" id="brand_sel" multiple="multiple" >
                    	 	<!-- 遍历otherFieldCd，不属于该e课堂的领域 -->
	                    	 <c:forEach items="${courseInfo.otherFieldCd}" var="otherFieldCd">
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
				      	<select class="selectArea" onchange="javascript:selectChange(this);" name="fieldCd" id="choose_sel" multiple="multiple" >
				      		<!-- 遍历fieldCd，属于该e课堂的领域 -->
				      		<c:forEach items="${courseInfo.fieldCd}" var="fieldCd">
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
                    <td class="tdl">主讲人<span class="red">*</span></td>
                    <td class="td_detail" colspan="7">
                        <input class="span8 expertCdInput" name="courseInfo.speaker" id="speaker" value="${courseInfo.speaker}" maxlength="100" />※最多输入100个字
                    </td>
                </tr> 
                <tr>                  
                    <td class="tdl">开讲时间<span class="red">*</span></td>
                    <td class="td_detail" colspan="7">
	                    <div class="input-prepend input-append">
	                        <input class="input-small datepicker" name="courseInfo.startTime" id="startTime" value="<fmt:formatDate value="${courseInfo.startTime}" pattern="yyyy-MM-dd HH:mm" />"/>
			                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
			                    &nbsp;&nbsp;~&nbsp;&nbsp;
			                <input class="input-small datepicker" name="courseInfo.endTime" id="endTime" value="<fmt:formatDate value="${courseInfo.endTime}" pattern="yyyy-MM-dd HH:mm" />"/>
			                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
	                    </div>
                    </td>
                </tr> 
                <tr>
                    <td class="tdl">e课堂介绍<span class="red">*</span></td>
                    <td class="td_detail" colspan="7">
                    	<div id="textEditorDiv" class="wxeditorarea">
                    		<textarea id="textEditor" name="courseInfo.courseBrief">${courseInfo.courseBrief}</textarea>
	                    	<div class="editorSlide">
								<ul>
									<li>
										<i class="imb-preview"></i>
										<div class="icon-cover cover-preview"></div>
									</li>
								</ul>
							</div>
                    	</div>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">列表图片上传<span class="red">*</span></td>
                    <td class="td_detail" colspan="7">
                    	<div id="preview">
							<img id="littleIconImg" name="littleIconImg" src="${courseInfo.littleIconUrl}" class="img" />
							<input name="littleIcon" value="${courseInfo.littleIcon}" type="hidden" id="littleIcon"/>
							<input type="hidden" value="${courseInfo.littleIconUrl}" name="littleIconUrl" id="littleIconUrl"/>
						    <p class="detailDesc">&nbsp;&nbsp;图片尺寸：192*148&nbsp;&nbsp;&nbsp;&nbsp;图片大小：200K以内</p>
						</div>
						<div id="bt">
							<span id="uploadImg">
								<input type="file" id="littleIconImgData" name="littleIconImgData" onchange="ajaxFileUpload('littleIcon')" />&nbsp;&nbsp;
								<input class="btn" type="button" value="上传" id="littleIconBtn"/>
							</span>
						</div> 
                    </td>
                </tr>
                <!--<tr>
                    <td class="tdl">详情图片上传<span class="red">*</span></td>
                    <td class="td_detail" colspan="7">
                    	<div id="preview2">
							<img id="bigIconImg" name="bigIconImg" src="${courseInfo.bigIconUrl}" class="img"/>
							<input name="bigIcon" value="${courseInfo.bigIcon}" type="hidden" id="bigIcon"/>
							<input type="hidden" value="${courseInfo.bigIconUrl}" name="bigIconUrl" id="bigIconUrl"/>
							<p class="detailDesc">&nbsp;&nbsp;图片尺寸：120X360&nbsp;&nbsp;&nbsp;&nbsp;图片大小：3M以内</p>
						</div>
						<div id="bt2">
							<span id="uploadImg2">
								<input type="file" id="bigIconImgData" name="bigIconImgData" onchange="ajaxFileUpload('bigIcon')" />&nbsp;&nbsp;
								<input class="btn" type="button" value="上传" id="bigIconBtn"/>
							</span>
						</div> 
                    </td>
                </tr>-->
                <tr>
                   <td class="tdl">e课堂费用</td>
                   <td class="td_detail" colspan="7">
                   	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                   		<c:if test="${mstCodeInfo.codeType=='paymentKbn'}">
                   			<c:set var="selectPaymentKbn" value="${courseInfo.paymentKbn}" scope="page"></c:set>
                   			<c:set var="paymentCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                   			<input 
                   				<c:if test="${fn:contains(selectPaymentKbn, paymentCode)}">checked</c:if>
                   				<c:if test="${paymentCode eq 0 and empty selectPaymentKbn}">checked</c:if>
                   			 type="radio" value="${mstCodeInfo.code}" name="paymentKbn"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		</c:if>
                   	</c:forEach>
   			        <input style='text-align:right;' name="price" value="${courseInfo.price}" class="span2" id="chargeInput" maxlength="6" onchange="this.value=AngelMoney(this.value)"/>&nbsp;&nbsp;元
                   </td>
                </tr>
                <c:if test="${userInfo.roleId eq '1' or userInfo.roleId eq '3'}">
	                <tr>
	                    <td class="tdl">置顶</td>
	                    <td class="td_detail" colspan="7">
	                    	<c:set var="recommendKbn" value="${courseInfo.recommendKbn}" scope="page"></c:set>
	                        <input <c:if test="${fn:contains(recommendKbn, '2')}">checked</c:if> type="checkbox" name="toTopCode" value="2" id="toTop" />
	                        <label class="checkbox-label" for="toTop">置顶</label>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="tdl">推荐</td>
	                    <td class="td_detail" colspan="7">
	                        <input <c:if test="${fn:contains(recommendKbn, '1')}">checked</c:if> type="checkbox" name="recommend" value="1" id="recommend" onclick="radioChange4();" />
	                        <label class="checkbox-label" for="recommend">推荐</label>&nbsp;&nbsp;&nbsp;&nbsp;
	                        <input class="span5" id="recommendInput" value="${courseInfo.recommendMsg}" name="recommendMsg" maxlength="100"
		                        <c:choose>
		                        	<c:when test="${fn:contains(recommendKbn, '1')}"></c:when>
		                        	<c:otherwise>disabled="false"</c:otherwise>
		                        </c:choose>
		                        onfocus="recommendInputCheck();" />※最多输入100个字
	                    </td>
	                </tr>
                </c:if>
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button onclick="back(); return false;" class="btn btn-inverse no_repeat_submit">返回</button>
                            <button onclick="save(); return false;" class="btn btn-inverse no_repeat_submit">保存</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <input type="hidden" value="" name="fieldNameForKeyWord" id="fieldNameForKeyWord"/>
		<input type="hidden" value="${courseInfo.courseCd}" name="courseInfo.courseCd"/>
		<input type="hidden" value="${courseInfo.roomId}" name="courseInfo.roomId"/>
		<input type="hidden" value="${courseInfo.speakerOld}" name="courseInfo.speakerOld"/>
		<!-- 页面回显使用的数据 -->
		<%-- <input type="hidden" value="${selectKey.courseName}" name="selectKey.courseName"/>
		<input type="hidden" value="${selectKey.field}" name="selectKey.field"/>
		<input type="hidden" value="${selectKey.paymentKbn}" name="selectKey.paymentKbn"/>
		<input type="hidden" value="${selectKey.sDate}" name="selectKey.sDate"/>
	    <input type="hidden" value="${selectKey.eDate}" name="selectKey.eDate"/>
		<input type="hidden" value="${selectKey.recommendKbn}" name="selectKey.recommendKbn"/>
		<input type="hidden" value="${selectKey.courseType}" name="selectKey.courseType"/>
	    <input type="hidden" value="${selectKey.pageNo}" name="pageNo"/> --%>
	    <input type="hidden" value="${selectKey}" name="selectKey"/>
	    <input type="hidden" value="${isAdd}" name="isAdd"/>
		<input type="hidden" value="${doSearch}" name="doSearch"/>
	</form>
    <!-- 预览窗体 -->
    <div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
		<div class="modal-dialog modal-sm">
			<div class="ios-device">
				<div class="device-top">
					<div class="ico ico-1 circle"></div>
					<div class="ico ico-2"></div>
					<div class="ico ico-3 circle"></div>
				</div>
				<div class="device-content"></div>
				<div class="device-footer">
					<div class="circle"></div>
				</div>
			</div>
		</div>
	</div>	
	<script type="text/javascript">
    ue = new UE.ui.Editor();
    ue.render("textEditor");
    
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {

	    //判断路径   这里是config.json 中设置执行上传的action名称
	    if (action == 'uploadimage') {
	        return '<%=path%>/course/textEditorImg.htm';
	    } else if (action == 'uploadvideo') {
	        return '';
	    } else {
	        return this._bkGetActionUrl.call(this, action);
	    }
    }

    // 编辑器加载事件
    var editHeight = 0;
    var menuYloc = 0;
    ue.ready(function(){
        editHeight = $("#textEditorDiv").offset().top + $("#textEditorDiv").height() + 7;
        menuYloc = $("#textEditorDiv").offset().top + $(".editorSlide").height()/2 + 5;
    	// 设置浮动预览按钮
    	$(".editorSlide").offset({top:$("#textEditorDiv").offset().top - 10});
	});
	</script>
</body>
</html>
