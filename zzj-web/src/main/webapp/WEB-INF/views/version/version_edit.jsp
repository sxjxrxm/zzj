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
    <!--<link rel="stylesheet" type="text/css" href="<%=path %>/styles/admin-all.css" />-->
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/upload.css"/>

    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-ui-1.8.22.custom.min.js"></script>    

    <script type="text/javascript" src="<%=path %>/scripts/tb.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/ajaxfileupload.js"></script> 
	<style>
	
	</style>
    <script type="text/javascript">
        $(function () {
        	// apk上传
	        $("#btn_upload").click(function(){
	        	$("#apkData").click();
	        });
        })
		function changeType(){
        	var val = $("#appType option:selected").val();
        	var type1 = $(".type1");
        	var type2 = $(".type2");
        	var span1 = $("#type1TD .redStar");
        	var span2 = $("#type2TD .redStar");
        	if (val == '') {
        		type1.attr("disabled","disabled").val("");
        		type2.attr("disabled","disabled").val("");
        		span1.text("");
        		span2.text("");
        		$("#saveBtn").attr("disabled","disabled");
        		$("#btn_upload").attr("disabled","disabled");
        	} else if (val == 1) {// 1：Android，2：IOS
        		document.forms[0].action = "<%=path %>/version/edit.htm";
    	  		document.forms[0].submit();
    	  		$("#saveBtn").removeAttr("disabled");
    	  		$("#btn_upload").removeAttr("disabled");
        	} else if (val == 2) {// 1：Android，2：IOS
        		document.forms[0].action = "<%=path %>/version/edit.htm";
    	  		document.forms[0].submit();
    	  		$("#saveBtn").removeAttr("disabled");
        	}
        }
        
     	// apk异步上传
		function ajaxFileUpload() {
     		if (!checkApkFileName()) {
     			return;
     		}
     		var versionNo = $("#versionNo").val();
     		if (versionNo == null || versionNo == '' || versionNo == undefined) {
     			alert("请先输入版本号");
     			$("#versionNo").focus();
     			return;
     		}
		        $.ajaxFileUpload(
		            {
		                url: '${pageContext.request.contextPath}/version/uploadApk.htm?versionNo='+versionNo,
		        		secureuri : false,// 安全协议
		        		async : false,
		        		fileElementId : 'apkData',
		        		dataType:'json',
		        		type : "POST",
		        		success : function(data) {
		        			if (data.path)
		        			{		        			
			        			$("#versionAddress1").val(data.path);
			        			$("#createTime").val(data.time);
			        			//$("#previewAvatar").attr("src", data.url);
			                    //console.log(data);
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
     	
     	// 校验文件后缀名
     	function checkApkFileName(){
     		var file = $("#apkData")[0];
    		//判断文件后缀名是否为apk格式
    	 	if (!/\.(apk)$/i.test(file.value)) {
    	       alert("文件类型必须是apk格式");
    	       file.outerHTML = file.outerHTML;
    	       return false;
    	 	}
    	 	return true;
     	}
     	
     	// 保存
     	function save(){
     		var val = $("#appType option:selected").val();
     		if (val == null || val == '' || val == undefined) {
     			return;
     		}
     		document.forms[0].action = "<%=path %>/version/save.htm";
	  		document.forms[0].submit();
     	}
    </script>
</head>
<body>
    <form onkeydown="if(event.keyCode==13){save();return false;}">
    <div class="alert alert-heading"><h4>版本编辑</h4></div>
    <%@include file="../common/common_msg.jsp"%>
    <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>
                    <td class="tdl">APP客户端</td>
                    <td class="td_detail">
    					    <select class="span3" id="appType" name="appType" onchange="changeType();">
    					    	<option value="">==请选择==</option>
		                		<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
	                    			<c:if test="${mstCodeInfo.codeType=='appType'}">
	                    				<option value="${mstCodeInfo.code}" <c:if test="${mstCodeInfo.code==versionInfo.appType}">selected</c:if>>${mstCodeInfo.codeName}</option>
	                    			</c:if>
	                    		</c:forEach>
                    		</select>
    				</td>
                </tr>
                <tr> 
                    <td class="tdl">版本号<span style="color: red">*</span></td>
                    <td class="td_detail" colspan="7">
                        <input class="span3 type1 type2" name="versionNo" id="versionNo" value="${versionInfo.versionNo}"<c:if test="${empty versionInfo.appType}">disabled="disabled"</c:if>/>
                    </td>
                </tr>
                <tr> 
                    <td class="tdl" style="height:30px;" id="type1TD">APK<span class="redStar" style="color: red"><c:if test="${versionInfo.appType eq 1}">*</c:if></span></td>
                    <td class="td_detail" colspan="7">
                        <input class="span8 type1" readonly="readonly" name="versionAddress1" id="versionAddress1"
                        	<c:if test="${empty versionInfo.appType or versionInfo.appType eq 2}">disabled="disabled"</c:if>
                        	<c:if test="${versionInfo.appType eq 1}">value="${versionInfo.versionAddress}"</c:if>/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input class="span2 type1" readonly="readonly" name="createTime" id="createTime"
                        	<c:if test="${empty versionInfo.appType or versionInfo.appType eq 2}">disabled="disabled"</c:if>
                        	<c:if test="${versionInfo.appType eq 1}">value='<fmt:formatDate value="${versionInfo.createTime}" pattern="yyyy-MM-dd" />'</c:if>/>&nbsp;&nbsp;&nbsp;&nbsp;
                        	<input type="file" id="apkData" name="apkData" onchange="ajaxFileUpload();"/>
                        	<input class="btn" type="button" value="上传" id="btn_upload"<c:if test="${empty versionInfo.appType or versionInfo.appType eq 2}">disabled="disabled"</c:if>/>
                    </td>
                </tr>
                <tr> 
                    <td class="tdl" id="type2TD">IOS链接<span class="redStar" style="color: red"><c:if test="${versionInfo.appType eq 2}">*</c:if></span></td>
                    <td class="td_detail" colspan="7">
                        <input class="span8 type2" name="versionAddress2" id="versionAddress2"
                        	<c:if test="${empty versionInfo.appType or versionInfo.appType eq 1}">disabled="disabled"</c:if>
                        	<c:if test="${versionInfo.appType eq 2}">value="${versionInfo.versionAddress}"</c:if>/>
                    </td>
                </tr>
                <tr> 
                    <td class="tdl">更新内容<span style="color: red">*</span></td>
                    <td class="td_detail" colspan="7">
                        <input class="span8 type1 type2" name="modifyMemo" id="modifyMemo" value="${versionInfo.modifyMemo}"<c:if test="${empty versionInfo.appType}">disabled="disabled"</c:if>/>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button onclick="save(); return false;" id="saveBtn" class="btn btn-inverse no_repeat_submit"<c:if test="${empty versionInfo.appType}">disabled="disabled"</c:if>>保存</button>
                        </div>
                    </td>
                </tr>
            </tbody>
    </table>
    </form>
</body>
</html>
