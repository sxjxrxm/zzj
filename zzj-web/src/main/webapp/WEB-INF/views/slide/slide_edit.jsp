<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title> 
    <!--<link rel="stylesheet" type="text/css" href="../styles/admin-all.css" />-->
    <link rel="stylesheet" type="text/css" href="../styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="../styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="../styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/edit-page.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/upload.css"/>
    <script type="text/javascript" src="../scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="../scripts/select-checkbox/jquery.multiselect.js"></script>
    <link rel="stylesheet" type="text/css" href="../styles/select-checkbox/jquery.multiselect.css"/>
    <script type="text/javascript" src="../scripts/tb.js"></script>
    <link rel="stylesheet" type="text/css" href="../styles/css/wangEditor.css"/>
	<script type="text/javascript" src="../scripts/wangEditor.js"></script>
	<!-- 图片上传 -->
    <script src="../scripts/ajaxfileupload.js" type="text/javascript"></script>    
	<script type="text/javascript" src="../scripts/tb.js"></script>
	<style>
    	.tdl{width: 100px;}
    	.td_detail{width: auto;}
	</style>
    <script type="text/javascript">
    // 定义变量表示最后上传成功的图片路径
    var realPath = "";
	$(function () {
        $("#btn_upload").click(function(){
        	$("#imgData").click();
        });
    })
	function preview(file)
	{	
		// 上传图片
		$.ajaxFileUpload({
            url: '${pageContext.request.contextPath}/slide/imgUpload.htm',
    		secureuri : false,// 安全协议
    		async : false,
    		fileElementId : 'imgData',
    		dataType:'json',
    		type : "POST",
    		success : function(data) {
    			
    			if (data.message == 'success')
    			{	
    				// 将图片上传地址保存到隐藏的input，此时保存的是临时文件
    				$("#bigIconImg").attr("src", data.url);
        			$("#bigIcon").val(data.path);
                    console.log(data);
                }
    			else
    			{
    				alert(data.message);
    			}
            },
            error: function (result, status, e)// 服务器响应失败处理函数
            {
                alert(e);
                return false;
            }
        })		
	}
	function del(){
		 var prevDiv = document.getElementById('preview');
       	prevDiv.innerHTML = ''; 
    }

	function back()
	{
		window.location.href="${pageContext.request.contextPath}/slide/list.htm"
	}
	// 图片链接改变事件
	function checkChange(isZzj) {
		if(isZzj == 0) {
			$("#fZzj").removeAttr("disabled");
			$("#zzjA").removeAttr("style");
			$("#zzjA").attr("style","display:none");
			$("#zzja").removeAttr("style");
			$("#zzja").attr("style","color:#808080");
			$("#tZzj").attr("disabled","disabled");
			$("#zzjNullSp").removeAttr("style");
			$("#zzjNullSp").attr("style","display:none");
			$("#tZzj").removeAttr("class");
	    }
		else {
			$("#fZzj").attr("disabled","disabled");
			$("#zzjA").removeAttr("style");
			$("#zzja").removeAttr("style");
			$("#zzja").attr("style","display:none");
			$("#urlSp").removeAttr("style");
			$("#urlSp").attr("style","display:none");
			$("#fZzj").removeAttr("class");
			$("#tZzj").removeAttr("disabled")
			$("#urlNullSp").removeAttr("style");
			$("#urlNullSp").attr("style","display:none");
			$("#fZzj").removeAttr("class");
		}
	}
	var flag = true ;
	var topicCheck = false;
	var imgCheck = false;
	var urlCheck = false;
	var zzjCheck = false;
	// 输入文字长度校验
	function checkLength() {
		// 得到轮播主题
		var topicName = $.trim($("#topicName").val());
		// 得到URL
		var url = $.trim($("#fZzj").val());
		// 得到图片地址
		var bigIcon = $("#bigIcon").val();
		// 得到选择资料的机能和主题编好
		var busiType = $("#busiType").val();
		var topicCd = $("#topicCd").val();
		var topicIsFocus = false;
		// 校验主题非空
/* 		if(topicName.length == 0) {
			$("#topicNullSp").removeAttr("style");
			$("#topicName").addClass('input-error');
			$("#topicName").focus();
			topicIsFocus = true;
			topicCheck = false;
			flag = false;
		} */
		$("#topicNullSp").removeAttr("style");
		$("#topicNullSp").attr("style","display:none");
		// 校验主题长度
		if(topicName.length > 100){
			$("#topicNameSp").removeAttr("style");
			$("#topicName").addClass('input-error');
			$("#topicName").focus();
			topicCheck = false;
			topicIsFocus = true;
			flag = false;
		}
		else{
			$("#topicNameSp").removeAttr("style");
			$("#topicNameSp").attr("style","display:none");
			$("#topicName").removeAttr("class");
			topicCheck = true;
		}

		//校验图片非空
		if (bigIcon.length == 0) {
			$("#imgNullSp").removeAttr("style");
			flag = false;
			imgCheck = false;
		}
		else {
			$("#imgNullSp").removeAttr("style");
			$("#imgNullSp").attr("style","display:none");
			imgCheck = true;
		}
		// 自定义图片链接校验
		if($("#fRadio").is(':checked')) {
			$("#zzjNullSp").removeAttr("style");
			$("#zzjNullSp").attr("style","display:none");
			$("#tZzj").removeAttr("class");
/* 			if(url.length == 0) {
 				$("#urlNullSp").removeAttr("style");
				$("#fZzj").addClass('input-error');
				if(!(topicIsFocus)){
					$("#fZzj").focus();
				}
				flag = false;
				urlCheck = false;
			} */
			if(url.length > 100){
				$("#urlSp").removeAttr("style");
				$("#fZzj").addClass('input-error');
				if(!(topicIsFocus)){
					$("#fZzj").focus();
				}
				flag = false;
				urlCheck = false;
			}
			else{
				$("#urlSp").removeAttr("style");
				$("#urlSp").attr("style","display:none");
				$("#fZzj").removeAttr("class");
				urlCheck = true;
			}	
		}
		// 资料选择校验
		else {
			$("#urlSp").removeAttr("style");
			$("#urlSp").attr("style","display:none");
			$("#fZzj").removeAttr("class");
			if(busiType.length !=0 && topicCd.length != 0){
				$("#zzjNullSp").removeAttr("style");
				$("#zzjNullSp").attr("style","display:none");
				$("#tZzj").removeAttr("class");
				zzjCheck = true;
			}
			else{
				$("#zzjNullSp").removeAttr("style");
				$("#tZzj").addClass('input-error');
				if(!(topicIsFocus)){
					$("#tZzj").focus();
				}
				flag = false;
				zzjCheck = false;
			}
		}
	}
	// 保存事件
    function save() {
    	checkLength();
		var slideCd = $("#slideCd").val();
		var topicName = $("#topicName").val();
		var isAdd = $("#isAdd").val();
		var param = "";
		var bigIcon = $("#bigIcon").val();
		if($("#fRadio").is(':checked')) {
	    	flag = topicCheck && imgCheck && urlCheck ;
			var url = $("#fZzj").val();
			param = "?isZzj=0&isAdd="+isAdd;
			if (flag) {
				// AJax将添加信息传到后台
				// 调用Ajax将该记录放入轮播表
				$.post("${pageContext.request.contextPath}/slide/editSlide.htm"+param,
				{
					slideCd:slideCd,
					topicName:topicName,
					bigIcon:bigIcon,
					url:url,					
				},
				function(result) {
					if(result == 1) {
						window.location.href="${pageContext.request.contextPath}/slide/list.htm"
					}
				});
			}
		}
		else if ($("#tRadio").is(':checked')) {
			flag = topicCheck && imgCheck && zzjCheck;
			var busiType = $("#busiType").val();
			var topicCd = $("#topicCd").val();
			param = "?isZzj=1&isAdd="+isAdd;
			if (flag) {
				// AJax将添加信息传到后台
				// 调用Ajax将该记录放入轮播表
				$.post("${pageContext.request.contextPath}/slide/editSlide.htm"+param,
				{
					slideCd:slideCd,
					topicName:topicName,
					bigIcon:bigIcon,
					busiType:busiType,
					topicCd:topicCd,
				},
				function(result) {
					if(result == 1) {
						window.location.href="${pageContext.request.contextPath}/slide/list.htm"
					}
				 });
			}
		}
	}
	function toSlideSearch() {
		// 得到当前页面已经添加的信息
		var isAdd =  $("#isAdd").val();
        if ($("#tRadio").is(':checked'))
		{
			document.forms[0].action = "${pageContext.request.contextPath}/slide/slideSearch.htm?isZzj=1&isAdd="+isAdd+"";
	  		document.forms[0].submit();
		}
	}
    </script>
</head>
<body>
<form id="form" action="" method="post" onkeydown="if(event.keyCode==13){save();return false;}">
    <div class="alert alert-heading">
	    <c:if test="${isAdd == 0}">
	    	<h4>轮播编辑</h4>
	    </c:if>
	    <c:if test="${isAdd == 1}">
	    	<h4>轮播添加</h4>
	    </c:if> 	   	   	
    </div>
    <div  id="edit_message" class='ul-error' style="margin-top:5px;margin-bottom:5px;">
    	<ul><li id="topicNameSp" style="display:none;">轮播主题最多输入100个文字。</li></ul>
    	<ul><li id="topicNullSp" style="display:none;">请输入轮播主题。</li></ul>
    	<ul><li id="imgNullSp" style="display:none;">请输入图片。</li></ul>
    	<ul><li id="urlSp" style="display:none;">图片链接最多输入100个文字。</li></ul>
    	<ul><li id="urlNullSp" style="display:none;">请输入图片链接。</li></ul>
    	<ul><li id="zzjNullSp" style="display:none;">请输入图片链接。</li></ul>
    </div>   
        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>
                    <td class="tdl">轮播主题</td>                    
                    <td class="td_detail" colspan="7">
	                    <input name="topicName"  id="topicName" class="input-large" style="width:800px"
	                    	<c:if test="${!empty topicName}">value="${topicName}"</c:if>
	                    	<c:if test="${empty topicName}">value="${slideShowInfo.topicName}"</c:if>
	                    />※最多输入100个字
                    </td>
				</tr>
				<tr>
					<td class="tdl">图片上传<span style="color: red">*</span></td>
					<td class="td_detail" colspan="7">     
						<div id="preview" style="width:320px;height:200px;">
							<img name="bigIconImg" id="bigIconImg" 
								<c:if test="${!empty bigIconUrl}">src="${bigIconUrl}"</c:if>
								<c:if test="${empty bigIconUrl}">src="${slideShowInfo.bigIconUrl}"</c:if> 
								class="slide_img"/>
							<input name="bigIcon" id="bigIcon" 
								<c:if test="${!empty bigIcon}">value="${bigIcon}"</c:if>
								<c:if test="${empty bigIcon}">value="${slideShowInfo.bigIcon}"</c:if>
								type="hidden" id="bigIcon"/> 
							<p class="detailDesc">&nbsp;&nbsp;图片尺寸：16 : 9&nbsp;&nbsp;&nbsp;&nbsp;图片大小：200K以内</p>
						</div>
						<div id="bt2">
							<span id="uploadImg2">
								<input type="file" id="imgData" name="imgData"  onchange="preview(this)"/>&nbsp;&nbsp;
								<input class="btn" type="button" value="上传" id="btn_upload" />													
							</span>					
						</div>
					</td>
				</tr>				
				<tr>
                    <td class="tdl">图片链接</td>                                  
                    <td class="td_detail" colspan="7">
                    	<c:if test="${isZzj == 0}">
		                    <input type="radio" id="tRadio" name = "isZzj" onclick="checkChange(1)" />找专家平台 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    	<a id="zzjA" href="#" onclick = "toSlideSearch();return false;" style="display:none;">资料选择</a>
		                        <span id="zzja" style="color:#808080;">资料选择</span>
                            <input name="tZzj" id="tZzj" class="input-large" style="width:625px" value="${fZzj}" readonly="readonly"/><br/>	
		                    <input type="radio" id="fRadio" name = "isZzj" onclick="checkChange(0)" checked="checked"/>自定义&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <input name="fZzj" id="fZzj" class="input-large" style="width:702px" value="${slideShowInfo.url}" />※最多输入100个字                		
                    	</c:if>
                        <c:if test="${isZzj == 1}">
                            <input type="radio" id="tRadio" name = "isZzj" onclick="checkChange(1)" checked="checked"/>找专家平台 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        		<a id="zzjA" href="#" onclick = "toSlideSearch();return false;">资料选择</a>
                        		<span id="zzja" style="color:#808080;display:none">资料选择</span>
                        		<input name="tZzj" id="tZzj" class="input-large" style="width:625px"
                        			<c:if test="${empty selectTopicName}">value="${tZzj}"</c:if>
									<c:if test="${!empty selectTopicName}">value="${selectTopicName}"</c:if>
                        			readonly="readonly"/><br/>
                            <input type="radio" id="fRadio" name = "isZzj" onclick="checkChange(0)" />自定义&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            	<input name="fZzj" id="fZzj" class="input-large" style="width:702px" disabled="disabled"/>※最多输入100个字                       	
                        </c:if>
                    </td>
				</tr>
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button class="btn btn-inverse no_repeat_submit" onclick="back();return false;">返回</button>
                            <button class="btn btn-inverse no_repeat_submit"
							onclick="save();return false;">保存</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <input name="isAdd" id="isAdd" value="${isAdd}" type="hidden"/>
		<input name="slideCd" id="slideCd"
			<c:if test="${!empty slideCd}">value="${slideCd}"</c:if>
			<c:if test="${empty slideCd}">value="${slideShowInfo.slideCd}"</c:if>
		type="hidden"/>  	
		<input name="busiType" id="busiType"  
			<c:if test="${!empty busiType}">value="${busiType}"</c:if>
			<c:if test="${empty busiType}">value="${slideShowInfo.busiType}"</c:if>
		type="hidden"/>
		<input name="topicCd" id="topicCd"
			<c:if test="${!empty topicCd}">value="${topicCd}"</c:if>
			<c:if test="${empty topicCd}">value="${slideShowInfo.topicCd}"</c:if>
		type="hidden"/> 	
    </form>
</body>
</html>
