<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title></title> 
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/chur.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/jquery.autocomplete.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/edit-page.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/upload.css"/>

    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery.autocomplete.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/bootstrap.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- 图片上传 -->
    <script type="text/javascript" src="<%=path %>/scripts/ajaxfileupload.js"></script> 
    <script type="text/javascript" src="<%=path %>/scripts/select-move-option.js"></script>
   
    <script type="text/javascript">
        var roleId = ${userInfo.roleId};
        $(function () {
        	$("#chargeInput").val(AngelMoney($("#chargeInput").val()));// 格式化金额
            $(".datepicker").datepicker();
        	
        	// 滚动事件
            $(window).scroll(function () {
                var offsetTop = menuYloc + $(window).scrollTop() + "px";
                //offsetTop = (offsetTop > editHeight ? editHeight : offsetTop) + "px";
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
            
            var newsStatus = $("#newsStatus").val();
            // 审核通过的专家文章不能编辑
            if(undefined != newsStatus){
                if (roleId == 2 && newsStatus == 1) {
                	$(".roleFlag").attr("disabled","disabled");
                }
                
        	}
            // 待审核的知识显示审核区域
            if(undefined != newsStatus){
                if (newsStatus == 1 || newsStatus =="" ) {
                	// 审核区域可见
                	$("#statusTr").hide();
            	}else{
            		$("#statusTr").show();
            	}
        	}else{
        		$("#statusTr").hide();
        	}

            // 专家名称的联想输入
            // 当页面从专家编辑页面跳过来时，sourceOwner的值为专家姓名，不能修改，所以不需要发送联想输入请求
            $.get("<%=path %>/expert/getExpertName.htm", {}, function (data) { 
    	       	$(".expertCdInput").autocomplete(data.split("    "),{minChars:0,max:100});
    	    });
            
            //sortMe(document.getElementById("brand_sel"));
            radioChange();
            $("input[name=paymentKbn]").click(function(){
            		radioChange();
            });
            // 审核状态
            radioChange3();
            $("input[name=status]").click(function(){
            		radioChange3();
            });
         	// 图片上传
	        $("#littleIconBtn").click(function(){
	        	$("#littleIconImgData").click();
	        });
	        $("#bigIconBtn").click(function(){
	        	$("#bigIconImgData").click();
	        });
	        $("#chapterBtn").click(function(){
	        	$("#chapter").click();
	        });
	        if (roleId == 2) {
            	$("#sel").attr("disabled","disabled");
            }
        })
        // 作者追加按钮的点击事件
        var ExpertCdCount = 1; //记录追加作者的数量
        function addExpertCd() {
        	ExpertCdCount = ExpertCdCount + 1;
        	if (ExpertCdCount >= 5) {
        		$("#addExpertCd").attr("disabled","disabled");
        	}
        	if (ExpertCdCount <= 5) {
        		$("#expertCd"+ExpertCdCount).css("display", "inline");
        	}
        }

        function back() {
        	var expertId = $("#expertId").val();
        	var linkFrom = $("#linkFrom").val();
        	if(linkFrom == 'expertEdit'){
        		$("input[name=isAdd]").val(0);
        		document.forms[0].action = "<%=path %>/expert/edit.htm?expertId="+expertId+"&newsEdit=newsEdit";
        	} else {
        		document.forms[0].action = "<%=path %>/information/list.htm?flag=0";
        	}
	  		document.forms[0].submit();
		}	
		function save(flag) {
			//TODO
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
	    	// 发送请求，flag=save（保存操作，更新审核状态），flag=submit（提交操作，不更新审核状态）
	    	document.forms[0].action = "<%=path %>/information/save.htm?flg=1&flag="+flag;
	  		document.forms[0].submit();
		}
		// 图片异步上传
		function ajaxFileUpload(id) {
		        $.ajaxFileUpload(
		            {
		                url: '${pageContext.request.contextPath}/information/upload.htm?id='+id,
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
		// pdf异步上传
		function ajaxFileUploadPdf(id) {
				changeFileName($("#"+id)[0],'pdf');//后缀名校验
				
		        $.ajaxFileUpload(
		            {
		                url: '${pageContext.request.contextPath}/information/uploadPdf.htm',
		        		secureuri : false,// 安全协议
		        		async : false,
		        		fileElementId : 'chapter',
		        		dataType:'json',
		        		type : "POST",
		        		success : function(data) {
		        			<%--地址存储——————切记修改！--%>
		        			// 将图片上传地址保存到隐藏的input，此时保存的是临时文件
		        			$(".showPdf").removeAttr("disabled").attr("onclick","javascript:showPdf('"+data.url+"');");
		        			$("#newsAddressUrl").val(data.url);
		        			$("#newsAddress").val(data.path);
		                    //console.log(data);
		                },
		                error: function (data, status, e)// 服务器响应失败处理函数
		                {
		                    alert(e);
		                }
		            }
		        )
		        return false;
		}
		// 删除图片
	    function delPic(id)
			{
	  	  		$.ajax({
							url:"${pageContext.request.contextPath}/information/delFile.htm?name=img&id="+id,
							data:{path:$("#"+id).val()},
							success:function(){
										$("#"+id+"Img").attr("src","");
										$("#"+id).removeAttr("value");
									}
						})
			}
		//设置为同步的请求
		$.ajaxSetup({
		    async : false  
		});
		// 删除pdf
	    function delPdf(id,ImgView)
		{
	  	  	$.ajax({
				url:"${pageContext.request.contextPath}/information/delFile.htm?name=pdf",
					data:{path:$("#newsAddress").val()},
					success:function() {
						delPdfExecute(id, ImgView);
				    }
			})
		}
		// pdf预览
		function showPdf(url){
			//alert(1);
			if (url == null || url == "" || url == undefined) {
				return;
			}
			window.open(url);
		}

		// 删除上传文件回调函数
		function delPdfExecute(id,ImgView) {
		    $("#newsAddress").val("");
			$(".showPdf").removeAttr("onclick").attr("disabled","disabled");
		    var obj = document.getElementById(id);
		    obj.outerHTML = obj.outerHTML;
		    if(ImgView == 'uploadFileName'){
		    	$("#uploadFileName").attr("value","");
		    	return;
		    }
		    var prevDiv = document.getElementById(ImgView);
		    prevDiv.innerHTML = '';
		}
		//审核状态radio切换
		function radioChange3() {
		    var statusSelect = $("#sel");
		    switch ($("input[name=status]:checked").attr("value")) {
		    case "0":
		        for (var i = 0; i < statusSelect[0].options.length; i++) {
		            statusSelect[0].options[i].selected = false;
		        }
		        statusSelect.attr("disabled", "disabled");
		        break;
		    case "1":
		        for (var i = 0; i < statusSelect[0].options.length; i++) {
		            statusSelect[0].options[i].selected = false;
		        }
		        statusSelect.attr("disabled", "disabled");
		        break;
		    case "2":
		        statusSelect.removeAttr("disabled");
		        break;
		    default:
		        break;
		    }
		}
	</script>
</head>
<body>
    <form id="newsForm" action="" enctype="multipart/form-data" method="post" onkeydown="if(event.keyCode==13){save('save');return false;}">
    		<div class="alert alert-heading"><h4>知识编辑</h4></div>
    		<%@include file="../common/common_msg.jsp"%>
        <!--<div class="sub-title"><h5>知识基本信息</h5></div>-->
        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>
                    <td class="tdl">知识主题<span class="red">*</span></td>
                    <td class="td_detail" colspan="3">
                        <input class="span6 roleFlag" name="newsName" id="newsName" value="${newsInfo.newsName}" maxlength="100"/>
                    </td>
                    <td class="tdl" style="width:50px;">知识ID</td>
                    <td class="td_detail" colspan="3">
                        <input class="span2" name="newsCd" id="newsCd" value="${newsInfo.newsCd}" maxlength="100" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">知识分类<span class="red">*</span></td>
  					<td class="td_detail" colspan="3">
  					     <select class="span6" name="newsType" id="newsType" <c:if test="${(!empty expertId and empty newsInfo.newsType) or userInfo.roleId eq 2}">disabled</c:if>>
                        	<option value="">==请选择==</option>
	                        <c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="">
	                    		<c:if test="${mstCodeInfo.codeType=='newsType'}">
	                    			<c:set var="selectNewsType" value="${newsInfo.newsType}" scope="page"></c:set>
                    				<c:set var="newsTypeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
	                    			<option <c:if test="${fn:contains(selectNewsType, newsTypeCode)}">selected</c:if>
	                    			 <c:if test="${!empty expertId and empty newsInfo.newsType and newsTypeCode eq 3}">selected</c:if>
	                    			 value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
	                    		</c:if>
	                    	</c:forEach>
                        </select>
  					</td>
                    <td class="tdl">知识标签<span class="red">*</span></td>
                    <td class="td_detail" colspan="7">
                        <input class="span2 roleFlag" name="newsNick" value="${newsInfo.newsNick}" maxlength="5" id="newsNick"/>※最多输入5汉字
                    </td>                   
                </tr>

                <tr>
                    <td class="tdl">领域<span class="red">*</span></td>
                    <td class="td_detail tbCenter"  style="width:80px;border-right-color:#fff;">
                    	 <select class="selectArea roleFlag" onchange="javascript:selectChange(this);" id="brand_sel" multiple="multiple">
                    	 	<!-- 遍历newsInfo.otherFieldCd，不属于该知识的领域 -->
	                    	 <c:forEach items="${newsInfo.otherFieldCd}" var="otherFieldCd">
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
				         <input type="button" class="btn roleFlag" id="brand_selBtn" disabled="disabled" value=" &gt;&gt; " onclick="javascript:rightMove();"/><br/>
				         <input type="button" class="btn roleFlag" id="choose_selBtn" disabled="disabled" value=" &lt;&lt; " onclick="javascript:leftMove();"/><br/>
			        </td>
				    <td class="td_detail tbCenter" style="width:80px;border-right-color:#fff; text-align:left;">
				      	<select class="selectArea roleFlag" onchange="javascript:selectChange(this);" name="fieldCdStr" id="choose_sel" multiple="multiple">
				      		<!-- 遍历newsInfo.fieldCd，属于该知识的领域 -->
				      		<c:forEach items="${newsInfo.fieldCd}" var="fieldCd">
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
				    <td class="td_detail" colspan="4"></td>
                </tr>

            		<tr>
                    <td class="tdl">知识介绍<span class="red">*</span></td>
                    <td class="td_detail" colspan="7">
                    	<div id="textEditorDiv" class="wxeditorarea">
                    		<textarea id="textEditor" name="newsBrief">${newsInfo.newsBrief}</textarea>
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
                    <td class="tdl">列表图片上传</td>
                    <td class="td_detail" colspan="7">
                    	<div id="preview">
							<img id="littleIconImg"  src="${newsInfo.littleIconUrl}" class="img"/>
							<input name="littleIcon" value="${newsInfo.littleIcon}" type="hidden" id="littleIcon"/>
							<p class="detailDesc">&nbsp;&nbsp;图片尺寸：192*148&nbsp;&nbsp;&nbsp;&nbsp;图片大小：200K以内</p>
						</div>
						<div id="bt">
							<span id="uploadImg">
								<input type="file" id="littleIconImgData" name="littleIconImgData" onchange="ajaxFileUpload('littleIcon')" />&nbsp;&nbsp;
								<input class="btn roleFlag" type="button" value="上传" id="littleIconBtn"/>
								<!-- <input class="btn" type="button" value="删除" onclick="delPic('littleIcon');return false;"/> -->
							</span>
						</div> 
                    </td>
                </tr>
                <!-- <tr>
                    <td class="tdl">详情图片上传</td>
                    <td class="td_detail" colspan="7">
                    	<div id="preview2">
							<img id="bigIconImg"  src="${newsInfo.bigIconUrl}" class="img"/>
							<input name="bigIcon" value="${newsInfo.bigIcon}" type="hidden" id="bigIcon"/>
							<p class="detailDesc">&nbsp;&nbsp;图片尺寸：120X360&nbsp;&nbsp;&nbsp;&nbsp;图片大小：3M以内</p>
						</div>
						<div id="bt2">
							<span id="uploadImg2">
								<input type="file" id="bigIconImgData" name="bigIconImgData" onchange="ajaxFileUpload('bigIcon')" />&nbsp;&nbsp;
								<input class="btn" type="button" value="上传" id="bigIconBtn"/>
								<input class="btn" type="button" value="删除" onclick="delPic('bigIcon');return false;"/>
							</span>
						</div> 
                    </td>
                </tr> -->
                <tr>
                    <td class="tdl" style="height:30px;width:50px;">资料上传</td>
                    <td class="td_detail" colspan="7">
                    		文件名：<input readonly="readonly" class="span4" id="uploadFileName" name="newsFile" value="${newsInfo.newsFile}"/>
                    		<input type="file" id="chapter" name="chapter" onchange="ajaxFileUploadPdf('chapter')" />
                    		<input name="newsAddress" value="${newsInfo.newsAddress}" type="hidden" id="newsAddress"/>
                    		<input name="newsAddressUrl" value="${newsInfo.newsAddressUrl}" type="hidden" id="newsAddressUrl"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<span id="chapterSpan">
                   			<input class="btn roleFlag" type="button" value="上传" id="chapterBtn"/>
                   			<input class="btn roleFlag" type="button" value="删除" onclick="javascript:delPdf('chapter','uploadFileName');"/>
                   			<input class="btn roleFlag showPdf" type="button" <c:if test="${!fn:contains(newsInfo.newsAddress, 'pdf')}">disabled="disabled"</c:if> value="预览" onclick="javascript:showPdf('${newsInfo.newsAddressUrl}');"/>
                   		</span>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">知识来源</td>
                    <td class="td_detail" colspan="7" id="sourceTypeTd">
                    	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                    		<c:if test="${mstCodeInfo.codeType=='sourceType'}">
                    			<c:set var="selectSourceType" value="${newsInfo.sourceType}" scope="page"></c:set>
                    			<c:set var="sourceTypeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                    			<input 
                    			<c:if test="${fn:contains(selectSourceType, sourceTypeCode)}">checked</c:if> 
                    			<c:if test="${!empty expertId and fn:contains(sourceTypeCode, '2')}">checked</c:if> 
                    			<c:if test="${!empty expertId}">disabled</c:if> 
                    			<c:if test="${sourceTypeCode eq 1 and empty selectSourceType}">checked</c:if>
                    			type="radio" value="${mstCodeInfo.code}" name="sourceType" id="sourceType_${mstCodeInfo.code}"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;
                    		</c:if>
                    	</c:forEach>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
  			            <input name="expertCdOrName" class="span2 expertCdInput" id="sourceOwner" 
  			            	<c:choose>
	                        	<c:when test="${!empty newsInfo.sourceOwnerName}">value="${newsInfo.sourceOwnerName}"</c:when>
	                        	<c:otherwise>value="${expertName}"</c:otherwise>
	                        </c:choose>
  			                <c:if test="${!empty expertId}">readonly="readonly"</c:if>/>
  			            <span id="expertCd">
  			            	<input name="expertCdOrName" class="span2 expertCdInput" value="${newsInfo.expertCd2Name}" <c:if test="${empty newsInfo.expertCd2}" >style="display:none;"</c:if> id="expertCd2"/>&nbsp;
  			            	<input name="expertCdOrName" class="span2 expertCdInput" value="${newsInfo.expertCd3Name}" <c:if test="${empty newsInfo.expertCd3}" >style="display:none;"</c:if> id="expertCd3"/>&nbsp;
  			            	<input name="expertCdOrName" class="span2 expertCdInput" value="${newsInfo.expertCd4Name}" <c:if test="${empty newsInfo.expertCd4}" >style="display:none;"</c:if> id="expertCd4"/>&nbsp;
  			            	<input name="expertCdOrName" class="span2 expertCdInput" value="${newsInfo.expertCd5Name}" <c:if test="${empty newsInfo.expertCd5}" >style="display:none;"</c:if> id="expertCd5"/>&nbsp;
  			            </span>
  			            <a class="btn roleFlag" onclick="addExpertCd();" id="addExpertCd" <c:if test="${!empty newsInfo.expertCd5}" >disabled</c:if>>作者追加</a>
  			            <c:if test="${!empty expertId}"> <input type="hidden" value="2" name="sourceType"/> </c:if>
                    </td>
                </tr>
                <tr>
                   <td class="tdl">知识费用</td>
                   <td class="td_detail" colspan="7">
                   	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                   		<c:if test="${mstCodeInfo.codeType=='paymentKbn'}">
                   			<c:set var="selectPaymentKbn" value="${newsInfo.paymentKbn}" scope="page"></c:set>
                   			<c:set var="paymentCode" value="${mstCodeInfo.code}" scope="page"></c:set>
                   			<input 
                   				<c:if test="${fn:contains(selectPaymentKbn, paymentCode)}">checked</c:if>
                   				<c:if test="${paymentCode eq 0 and empty selectPaymentKbn}">checked</c:if>
                   				<c:if test="${userInfo.roleId eq 2}">disabled</c:if>
                   			 type="radio" value="${mstCodeInfo.code}" name="paymentKbn"/>&nbsp;${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		</c:if>
                   	</c:forEach>
   			        <input name="price" value="${newsInfo.price}" class="span2" id="chargeInput" maxlength="6" 
   			        <c:if test="${userInfo.roleId eq 2}">disabled</c:if>
   			        onchange="this.value=AngelMoney(this.value)" style="text-align:right;"/>&nbsp;&nbsp;元
                   </td>
               </tr>
                <tr>
                    <td class="tdl">置顶</td>
                    <td class="td_detail" colspan="7">
                    	<c:set var="recommendKbn" value="${newsInfo.recommendKbn}" scope="page"></c:set>
                        <input <c:if test="${fn:contains(recommendKbn, '2')}">checked</c:if> <c:if test="${newsInfo.status != 1 || userInfo.roleId eq 2}">disabled="disabled"</c:if>
                        type="checkbox" name="toTopCode" value="2" id="toTop"/> <label class="checkbox-label" for="toTop">置顶</label>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">推荐</td>
                    <td class="td_detail" colspan="7">
                        <input <c:if test="${fn:contains(recommendKbn, '1')}">checked</c:if> <c:if test="${newsInfo.status != 1 || userInfo.roleId eq 2}">disabled="disabled"</c:if>
                         type="checkbox" name="recommend" value="1" id="recommend" onclick="radioChange4();"/> <label class="checkbox-label" for="recommend">推荐</label>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input class="span5" id="recommendInput" value="${newsInfo.recommendMsg}" name="recommendMsg" maxlength="100"
                        	<c:if test="${newsInfo.status != 1 || userInfo.roleId eq 2}">disabled="disabled"</c:if>
	                        <c:choose>
	                        	<c:when test="${fn:contains(recommendKbn, '1')}"></c:when>
	                        	<c:otherwise>disabled="false"</c:otherwise>
	                        </c:choose>
	                        onfocus="recommendInputCheck();"/>
                    </td>
                </tr>
                <tr id="statusTr" <c:if test="${(newsInfo.newsType != 3 or newsInfo.sourceType != 2) or userInfo.roleId eq 2}">style="display: none"</c:if>>
                    <td class="tdl">审核结果</td>
                    <td class="td_detail" colspan="7" id="statusSelect">
                        <input name="status" type="radio" value="0" <c:if test="${newsInfo.status == 0}">checked</c:if>
                          <c:if test="${userInfo.roleId eq 2}">disabled</c:if>/>审核中&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="status" type="radio" value="1" <c:if test="${newsInfo.status == 1}">checked</c:if>
                          <c:if test="${userInfo.roleId eq 2}">disabled</c:if>/>通过&nbsp;&nbsp;
    			    	<input name="status" type="radio" value="2" <c:if test="${newsInfo.status == 2}">checked</c:if>
    			    	  <c:if test="${userInfo.roleId eq 2}">disabled</c:if>/>拒绝&nbsp;&nbsp;&nbsp;&nbsp;
    			    <select  class="dfinput" id="sel"<c:if test="${newsInfo.status != 2}">disabled</c:if> name="refuseMemo">
	                	<option value="">==请选择==</option>
	                	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                    		<c:if test="${mstCodeInfo.codeType=='refuseType'}">
                    			<option value="${mstCodeInfo.code}" 
                    			<c:if test="${newsInfo.refuseMemo == mstCodeInfo.code}">selected</c:if>
                    			>${mstCodeInfo.codeName}</option>
                    		</c:if>
                    	</c:forEach>
					</select>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button class="btn btn-inverse no_repeat_submit" onclick="back(); return false;">返回</button>
                            <c:if test="${userInfo.roleId eq 2}">
                            	<button class="btn btn-inverse no_repeat_submit" id="saveBtn" onclick="save('submit'); return false;"
                            	<c:if test="${newsInfo.status eq 0 || newsInfo.status eq 1 || empty newsInfo.newsCd}">disabled</c:if>>提交</button>
                            </c:if>
                            <button class="btn btn-inverse no_repeat_submit" id="saveBtn" onclick="save('save'); return false;"
                            <c:if test="${newsInfo.status eq 1 && userInfo.roleId eq 2}">disabled</c:if>>保存</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
	<input type="hidden" value="${isAdd}" name="isAdd"/>
	<input type="hidden" value="${isAddNews}" name="isAddNews"/>
    <input type="hidden" value="${doSearch}" name="doSearch"/><!-- 记录一览页面是否执行了查询操作 -->
    <input type="hidden" value="${userInfo.userId}" name="updateId"/>
    <input type="hidden" value="${newsInfo.status}" name="newsStatus" id="newsStatus"/>
    <input type="hidden" value="" name="fieldNameForKeyWord" id="fieldNameForKeyWord"/><!-- 表单提交前设置值，保存领域名称，后台构造知识关键词使用 -->
    <!-- 知识一览页面回显使用的数据 -->
    <%-- <input type="hidden" value="${selectResource.newsNameLike}" name="newsNameLike"/>
    <input type="hidden" value="${selectResource.paymentKbnLike}" name="paymentKbnLike"/>
    <input type="hidden" value="${selectResource.techField}" name="techField"/>
    <input type="hidden" value="${selectResource.newsTypeLike}" name="newsTypeLike"/>
    <input type="hidden" value="${selectResource.sourceTypeLike}" name="sourceTypeLike"/>
    <input type="hidden" value="${selectResource.recommendKbnLike}" name="recommendKbnLike"/>
    <input type="hidden" value="${selectResource.startDate}" name="startDate"/>
    <input type="hidden" value="${selectResource.endDate}" name="endDate"/>
    <input type="hidden" value="${selectResource.pageNo}" name="pageNo"/> --%>
    <input type="hidden" value="${selectResourceNews}" name="selectResourceNews"/>
    
    <!-- 专家一览页面回显使用的数据 -->
    <%-- <input type="hidden" value="${selectResource.expertName}" name="expertNameLike"/>
    <input type="hidden" value="${selectResource.auditStatus}" name="auditStatus"/>
    <input type="hidden" value="${selectResource.recommendKbn}" name="recommendKbn"/>
    <input type="hidden" value="${selectResource.expertPageNo}" name="expertPageNo"/> --%>
    <input type="hidden" value="${selectResource}" name="selectResource"/>
    
    <input type="hidden" value="${linkFrom}" name="linkFrom" id="linkFrom"/>
    
    <c:if test="${(!empty expertId and empty newsInfo.newsType) or userInfo.roleId eq 2}"><input type="hidden" value="3" name="newsType"/></c:if>
    <input type="hidden" value="${expertName}" name="expertName" id="expertName"/>
    <input type="hidden" value="${expertId}" name="expertId" id="expertId"/>
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
    // 编辑器加载事件
    var editHeight = 0;
    var menuYloc = 0;
    ue.ready(function(){
        editHeight = $("#textEditorDiv").offset().top + $("#textEditorDiv").height() + 7;
        menuYloc = $("#textEditorDiv").offset().top + $(".editorSlide").height()/2 + 5;
    	// 设置浮动预览按钮
    	$(".editorSlide").offset({top:$("#textEditorDiv").offset().top - 10});
    	
    	// 设置专家审核通过后不可编辑
    	var newsStatus = $("#newsStatus").val();
    	if(undefined != newsStatus){
            if(roleId == 2 && newsStatus == 1) ue.setDisabled(true);
    	}
	});
    
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {

	    //判断路径   这里是config.json 中设置执行上传的action名称
	    if (action == 'uploadimage') {
	        return '<%=path%>/information/textEditorImg.htm';
	    } else if (action == 'uploadvideo') {
	        return '';
	    } else {
	        return this._bkGetActionUrl.call(this, action);
	    }
    }
</script>
</body>
</html>
