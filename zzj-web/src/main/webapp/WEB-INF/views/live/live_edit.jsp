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

    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="../scripts/chur-alert.1.0.js"></script>
	<script type="text/javascript" src="../scripts/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-timepicker-addon.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-timepicker-zh-CN.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/bootstrap.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="../scripts/select-move-option.js"></script>
    <!-- 图片上传 -->
    <script type="text/javascript" src="../scripts/ajaxfileupload.js"></script>
    <script type="text/javascript">
        $(function () {
        	$("#chargeInput").val(AngelMoney($("#chargeInput").val()));// 格式化金额
	        
	        $(".datepicker").datetimepicker({
	        	defaultDate: $('.ui_timepicker').val(),  
            	dateFormat: "yy-mm-dd",  
            	timeFormat: 'HH:mm',  
            	stepHour: 1,  
            	stepMinute: 1
	        })
	        
	        radioChange();
            $("input[name=paymentKbn]").click(function(){
            		radioChange();
            });
            
        	// 设置浮动预览按钮
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
            
	     	// 图片上传
	        $("#littleIconBtn").click(function(){
	        	$("#littleIconImgData").click();
	        });
	        $("#bigIconBtn").click(function(){
	        	$("#bigIconImgData").click();
	        });
	        
            radioChange();
            $("input[name=cost]").click(function(){
            		radioChange();
            });
            publishedDisabled();
        })
        
        // 根据发布状态控制页面活性
        function publishedDisabled(){
        	var publishedFlag = $('input[name=publishedDeleteFlag]').val();
        	if (publishedFlag != '' && publishedFlag == 0) {
        		$(".published").attr("disabled","disabled");
        	}
        }
        
        function back(){
        	document.forms[0].action = "<%=path %>/video/list.htm?flag=0";
	  		document.forms[0].submit();
		}
		function saveLive()
		{
			//$("#save").val('1');
			$("#choose_sel option").attr("selected",true);// 将右侧领域的所有option设置为选中，随form表单提交
	    	// 将右侧领域的内容赋值给隐藏的input：fieldNameForKeyWord，后台构造关键词使用
	    	var fieldName = new Array();
	    	$("#choose_sel option").each(function () {
	    		var txt = $(this).text();
	    		if(txt != ''){
	    			fieldName.push(txt);
	    		}
	    	});
	    	
	    	var price = $("#chargeInput").val().replace(",","");
	    	$("#chargeInput").val(price);
	    	
	    	$("#fieldNameForKeyWord").attr("value",fieldName);
			document.forms[0].action = "<%=path %>/live/save.htm";
	  		document.forms[0].submit();
		} 
		// 图片异步上传
		function ajaxFileUpload(id) {
		        $.ajaxFileUpload(
		            {
		                url: '${pageContext.request.contextPath}/video/upload.htm?id='+id,
		        		secureuri : false,// 安全协议
		        		async : false,
		        		fileElementId : id+'ImgData',
		        		dataType:'json',
		        		type : "POST",
		        		success : function(data) {
		        			if (data.message == 'success')
		        			{		        			
		        				// 将图片上传地址保存到隐藏的input，此时保存的是临时文件
			        			$("#"+id+"Img").attr("src", data.url);
			        			$("#"+id).val(data.path);
			                    console.log(data);
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
		// 发布
		function publish() {
			document.forms[0].action = "<%=path %>/live/publish.htm";
	  		document.forms[0].submit();
		}
		// 复制推流地址到剪切板
		function copy(id){
			obj = $("#"+id)[0];
			obj.select(); //选择对象 
		    document.execCommand("Copy"); //执行浏览器复制命令
		}
    </script>
</head>
<body>
    <form action="" onkeydown="if(event.keyCode==13){saveLive();return false;}">
    	<div class="alert alert-heading"><h4>直播编辑</h4></div>
    	<%@include file="../common/common_msg.jsp"%>
        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>
                    <td class="tdl">直播主题<span class="red">*</span></td>
                    <td class="td_detail" colspan="7">
                        <input class="span8 published" name="liveInfo.liveName" id="liveName" maxlength="30" value="${liveInfo.liveName}"/>※最多输入30个字
                    </td>  
                </tr>
                <tr>
                    <td class="tdl">领域<span class="red">*</span></td>
                    <td class="td_detail tbCenter"  style="width:80px;border-right-color:#fff;">
                    	 <select class="selectArea published" onchange="javascript:selectChange(this);" id="brand_sel" multiple="multiple">
                    	 	<!-- 遍历otherFieldCd，不属于该e视频的领域 -->
	                    	 <c:forEach items="${liveInfo.otherFieldCd}" var="otherFieldCd">
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
				         <input type="button" class="btn published" id="brand_selBtn" disabled="disabled" value=" &gt;&gt; " onclick="javascript:rightMove();"/><br/>
				         <input type="button" class="btn published" id="choose_selBtn" disabled="disabled" value=" &lt;&lt; " onclick="javascript:leftMove();"/><br/>
			        </td>
				    <td class="td_detail tbCenter" style="width:80px;border-right-color:#fff;">
				      	<select class="selectArea published" onchange="javascript:selectChange(this);" name="fieldCd" id="choose_sel" multiple="multiple">
				      		<!-- 遍历fieldCd，属于该e视频的领域 -->
				      		<c:forEach items="${liveInfo.fieldCd}" var="fieldCd">
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
                    <td class="tdl">直播介绍<span class="red">*</span></td>
                    <td class="td_detail" colspan="7">
                    	<div id="textEditorDiv" class="wxeditorarea">
                    		<textarea id="textEditor" class="published" name="liveInfo.liveBrief">${liveInfo.liveBrief}</textarea>
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
					<td class="tdl" style="width:70px;">直播时间<span class="red">*</span></td>
		            <td class="td_detail" colspan="3">
		                <div class="input-prepend input-append">
		                    <input class="input-small datepicker published" id="startTime" name="liveInfo.startTime" value="<fmt:formatDate value="${liveInfo.startTime}" pattern="yyyy-MM-dd HH:mm:00" />"/>
		                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
		                    &nbsp;&nbsp;~&nbsp;&nbsp;
		                    <input class="input-small datepicker published" id="endTime" name="liveInfo.endTime" value="<fmt:formatDate value="${liveInfo.endTime}" pattern="yyyy-MM-dd HH:mm:00" />"/>
		                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
		                </div>
		            </td>
		            <td class="tdl" style="width:70px;">直播过期时间<span class="red">*</span></td>
		            <td class="td_detail" colspan="3">
		                <div class="input-prepend input-append">
		                    <input class="input-small datepicker published" id="invalidDate" name="liveInfo.invalidDate" value="<fmt:formatDate value="${liveInfo.invalidDate}" pattern="yyyy-MM-dd HH:mm:00" />"/>
		                    <span class="add-on"><i class="icon-calendar datepicker"></i></span>
		                </div>
		            </td>
		        </tr>
                <tr>
                    <td class="tdl">列表图片上传</td>
                    <td class="td_detail" colspan="7">
                    	<div id="preview">
							<img id="littleIconImg"  src="${liveInfo.littleIconUrl}" class="img"/>
							<input name="littleIcon" value="${liveInfo.littleIcon}" type="hidden" id="littleIcon"/>
							<p class="detailDesc">&nbsp;&nbsp;图片尺寸：192*148&nbsp;&nbsp;&nbsp;&nbsp;图片大小：200K以内</p>
						</div>
						<div id="bt">
							<span id="uploadImg">
								<input type="file" id="littleIconImgData" name="littleIconImgData" onchange="ajaxFileUpload('littleIcon')" />&nbsp;&nbsp;
								<input class="btn published" type="button" value="上传" id="littleIconBtn"/>
								<!-- <input class="btn" type="button" value="删除" onclick="delPic('littleIcon');return false;"/> -->
							</span>
						</div> 
                    </td>
                </tr>
                <tr>
                    <td class="tdl">详情图片上传</td>
                    <td class="td_detail" colspan="7">
                    	<div id="preview2">
							<img id="bigIconImg"  src="${liveInfo.bigIconUrl}" class="bigIcon_img"/>
							<input name="bigIcon" value="${liveInfo.bigIcon}" type="hidden" id="bigIcon"/>
							<p class="detailDesc">&nbsp;&nbsp;图片尺寸：720*412&nbsp;&nbsp;&nbsp;&nbsp;图片大小：200K以内</p>
						</div>
						<div id="bt2">
							<span id="uploadImg2">
								<input type="file" id="bigIconImgData" name="bigIconImgData" onchange="ajaxFileUpload('bigIcon')" />&nbsp;&nbsp;
								<input class="btn published" type="button" value="上传" id="bigIconBtn"/>
								<!-- <input class="btn" type="button" value="删除" onclick="delPic('bigIcon');return false;"/> -->
							</span>
						</div> 
                    </td>
                </tr>  
                <!--              
                <tr>
                    <td class="tdl"><button id="getPullAddressBtn" class="btn btn-inverse no_repeat_submit" onclick="getPullAddress(); return false;">推流地址生成</button></td>
                </tr>
                -->
                <tr>
                    <td class="tdl">直播推流地址</td>
                    <td class="td_detail" colspan="7">
                        <input class="span10" name="liveInfo.pushAddress" id="pushAddress" value="${liveInfo.pushAddress}" readonly="readonly"/>
                        <input class="btn" type="button" onclick="copy('pushAddress');" value="复制"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">播放地址(RTMP)</td>
                    <td class="td_detail" colspan="7">
                        <input class="span8" name="liveInfo.rtmpAddress" id="rtmpAddress" value="${liveInfo.rtmpAddress}" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">播放地址(FLV)</td>
                    <td class="td_detail" colspan="7">
                        <input class="span8" name="liveInfo.flvAddress" id="flvAddress" value="${liveInfo.flvAddress}" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">播放地址(HLS)</td>
                    <td class="td_detail" colspan="7">
                        <input class="span8" name="liveInfo.hlsAddress" id="hlsAddress" value="${liveInfo.hlsAddress}" readonly="readonly"/>
                    </td>
                </tr>
				<tr>
                    <td class="tdl">直播费用</td>
                    <td class="td_detail" colspan="7">
                    	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
	                   		<c:if test="${mstCodeInfo.codeType=='paymentKbn'}">
	                   			<c:set var="selectPaymentKbn" value="${liveInfo.paymentKbn}" scope="page"></c:set>
	                   			<c:set var="paymentCode" value="${mstCodeInfo.code}" scope="page"></c:set>
	                   			<input 
	                   				<c:if test="${fn:contains(selectPaymentKbn, paymentCode)}">checked</c:if>
	                   				<c:if test="${paymentCode eq 0 and empty selectPaymentKbn}">checked</c:if>
	                   			 type="radio" value="${mstCodeInfo.code}" name="paymentKbn" class="published"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                   		</c:if>
	                   	</c:forEach>
    			        <input name="liveInfo.price" value="${liveInfo.price}" class="span2 published" id="chargeInput" maxlength="6" onchange="this.value=AngelMoney(this.value)"  style="text-align:right;"/>&nbsp;元
                    </td>
                </tr>
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button class="btn btn-inverse no_repeat_submit" onclick="back(); return false;">返回</button>
                            <button class="btn btn-inverse no_repeat_submit" id="save" onclick="saveLive(); return false;" <c:if test="${liveInfo.deleteFlag eq 0}">disabled="disabled"</c:if>>保存</button>
                            <button class="btn btn-inverse no_repeat_submit" onclick="publish(); return false;" <c:if test="${liveInfo.deleteFlag != 2}">disabled="disabled"</c:if>>发布</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <!-- 页面回显使用的数据 -->
		<input type="hidden" value="${liveInfo.liveCd}" name="liveInfo.liveCd"/>
		<input type="hidden" value="${liveInfo.roomId}" name="liveInfo.roomId"/>
		<%-- <input type="hidden" value="${selectKey.videoName}" name="selectKey.videoName"/>
		<input type="hidden" value="${selectKey.field}" name="selectKey.field"/>
		<input type="hidden" value="2" name="selectKey.videoType"/>
		<input type="hidden" value="${selectKey.sDate}" name="selectKey.sDate"/>
	    <input type="hidden" value="${selectKey.eDate}" name="selectKey.eDate"/>
		<input type="hidden" value="${selectKey.paymentKbnDisp}" name="selectKey.paymentKbnDisp"/>
		<input type="hidden" value="${selectKey.recommendKbn}" name="selectKey.recommendKbn"/> --%>
		<input type="hidden" value="2" name="selectKey.videoType"/>
		<input type="hidden" value="${selectKey}" name="selectKey"/>
		<input type="hidden" value="0" name="backFlag"/>
		<input type="hidden" value="1" name="saveFlag"/>
		<input type="hidden" value="${videoPageNo}" name="videoPageNo"/>
		<input type="hidden" value="${liveInfo.deleteFlag}" name="publishedDeleteFlag"/>
		<input type="hidden" value="" name="fieldNameForKeyWord" id="fieldNameForKeyWord"/><!-- 表单提交前设置值，保存领域名称，后台构造知识关键词使用 -->
		<!-- <input type="hidden" value="" name="save" id="save"/> -->
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
	        return '<%=path%>/video/textEditorImg.htm';
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
    	var publishedFlag = $('input[name=publishedDeleteFlag]').val();
    	if(publishedFlag != '' && publishedFlag == 0) ue.setDisabled(true);
	});
	</script>
</body>
</html>
