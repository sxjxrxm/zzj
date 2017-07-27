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
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/chur.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/edit-page.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/css/wangEditor.css"/>
    <style>
    	.tdl{width: 100px;}
    	.td_detail{width: auto;}
    </style>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.multiselect.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.core.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/ChurAlert.min.js?skin=blue"></script>
    <script type="text/javascript" src="<%=path %>/scripts/tb.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-move-option.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/wangEditor.js"></script>
    <script type="text/javascript">
	    // 技术领域变更
		function selectTechField(techFieldCd){
	    	document.forms[0].action = "<%=path %>/field/list.htm?techFieldCd=" + $(techFieldCd).val();
	  		document.forms[0].submit();
		}
	    function save() {
	    	$("#choose_sel option").attr("selected",true);
	    	document.forms[0].action = "<%=path %>/field/save.htm";
	  		document.forms[0].submit();
		}
		// 设置为同步的请求
		$.ajaxSetup({
		    async : false  
		});
	    function add(type) {
	    	// 清空错误信息
			$("#message").text("");
			$("#message").removeAttr("style");
			$("#message").attr("style", "display: none;");
			var title = "";
			var code = "";
			if (type == "techFieldType")
			{
				title = "技术领域";
				code = $("#techNo").val();
			}
			if (type == "rschFieldType")
			{
				title = "研究领域";
				code = $("#rschNo").val();
			}
			$("#code").text(code);
			var aOption = $("#codeType").find("option");
			// 为Select的option 生成选定项
			for(var i=0;i < aOption.length;i++) {
				if ($(aOption[i]).val() == type) {
					$(aOption[i]).attr("selected","selected");
				}
			}
	        $.dialog({
	            title: title,
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
	                	addExecute(type, code);
	                	if ($('#message', parent.document).text() != "")
	                	{
	                		return false;	                		
	                	}	
	                }
	            }],
	            cancel: true
	        });
	    }
	    // 执行
        function addExecute(type, code) {
        	var codeName = parent.document.getElementById("codeName").value;
        	$.post("<%=path %>/field/add.htm?", 
        	{
        		codeType:type,
        		code:code,
        		codeName:codeName,
        	}, function (data) {
        		if (data == 'success') {
        			var msg = $('#message', parent.document);
        			if(msg != null && msg != undefined) {
        				msg.text("");
        			}
        			document.forms[0].action = "<%=path %>/field/list.htm"
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
        			if (data.indexOf("项目名称") > -1)
    				{
    					var temp = $('#codeName', parent.document);
    					temp.focus();
    					temp.attr("style", "background-color:#b94a48;");
    				}
        		}
			});
	    }
    </script>
</head>
<body>
    <form onkeydown="if(event.keyCode==13){save();return false;}">
    	<div class="alert alert-heading" ><h4>领域配置</h4></div>
    	<%@include file="../common/common_msg.jsp"%>
		<table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>                 
					<td class="tdl">技术领域</td>
					<td class="td_detail">
						<select class="dfinput" name="techFieldCd" onchange="javascript:selectTechField(this);"> 
							<option value="">==请选择==</option>
							<c:forEach items="${techCodeInfos}" var="mstCodeInfo">
								<c:set var="selectTechFieldType" value="${techFieldCd}" scope="page"></c:set>
								<c:set var="typeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
								<option <c:if test="${fn:contains(selectTechFieldType, typeCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
							</c:forEach>				
						</select>
					</td>
                </tr>
				<tr>
                    <td class="tdl">研究领域</td>
                    <td class="td_detail tbCenter"  style="width:80px;border-right-color:#fff;">
                    	 <select class="selectArea" onchange="javascript:selectChange(this);" id="brand_sel" multiple="multiple" <c:if test="${empty techFieldCd}">disabled</c:if>>
                    	 	<c:forEach items="${fieldInfo.otherRschFieldInfo}" var="fieldCd">
	                    	 	<!-- 从session中取一级领域的值保存 -->
			                	<c:forEach items="${rschCodeInfos}" var="mstCodeInfo">
		                    		<c:if test="${mstCodeInfo.code==fieldCd.code}">
		                    			<c:set value="${mstCodeInfo.code}" var="rschFieldCode"></c:set>
		                    			<c:set value="${mstCodeInfo.codeName}" var="rschFieldCodeName"></c:set>
		                    			<option value="${rschFieldCode}">${rschFieldCodeName}</option>
		                    		</c:if>
		                    	</c:forEach>
	                    	</c:forEach>	                    	
					     </select>
				    </td>
				    <td class="td_detail tbCenter" style="width:50px;border-right-color:#fff;">
				         <input type="button" class="btn" id="brand_selBtn" disabled="disabled" value=" &gt;&gt; " onclick="javascript:rightMove();"/><br/>
				         <input type="button" class="btn" id="choose_selBtn" disabled="disabled" value=" &lt;&lt; " onclick="javascript:leftMove();"/><br/>
			        </td>
				    <td class="td_detail tbCenter" style="width:80px;border-right-color:#fff;">
				      	<select class="selectArea" name="fieldCd" onchange="javascript:selectChange(this);" id="choose_sel" multiple="multiple" <c:if test="${empty techFieldCd}">disabled</c:if>>
				      		<!-- 遍历fieldCd，属于该需求的领域 -->
				      		<c:forEach items="${fieldInfo.rschFieldInfo}" var="fieldCd">
				      			<!-- 从session中取一级领域的值保存 -->
			                	<c:forEach items="${rschCodeInfos}" var="mstCodeInfo">
		                    		<c:if test="${mstCodeInfo.code==fieldCd.rschFieldCd}">
		                    			<c:set value="${mstCodeInfo.code}" var="rschFieldCode"></c:set>
		                    			<c:set value="${mstCodeInfo.codeName}" var="rschFieldCodeName"></c:set>
		                    			<option value="${rschFieldCode}">${rschFieldCodeName}</option>
		                    		</c:if>
		                    	</c:forEach>		                    	
	                    	</c:forEach>
				      	</select>
				    </td>
				    <td class="td_detail" colspan="3"></td>
                </tr>
				<tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button onclick="add('techFieldType'); return false;" class="btn btn-inverse no_repeat_submit" >技术领域添加</button>
                            <button onclick="add('rschFieldType'); return false;" class="btn btn-inverse no_repeat_submit" >研究领域添加</button>
                            <button onclick="save(); return false;" class="btn btn-inverse no_repeat_submit" >保存</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <input type="hidden" value="${techNo}" name="techNo" id="techNo"/>
        <input type="hidden" value="${rschNo}" name="rschNo" id="rschNo"/>
    </form>
    <div id="popupFormDiv" style="display: none;">
		<form id="popupForm" action="" method="post">
			<div class="alert alert-heading">
				<h4>项目添加</h4>
			</div>
			<table class="tbform" style="margin: 0px; padding: 0px;">
				<tbody>
					<tr>
						<td class="tdl" colspan="4"><label id="message" style="display:none"></label></td>
					</tr>
					<tr>
	                    <td class="tdl" style="width:100px;height:30px;">类别</td>
	                    <td class="td_detail">
	                        <select class="input-large" id="codeType" disabled="disabled">                   	 
								<option value="techFieldType">技术领域</option>
								<option value="rschFieldType">研究领域</option>
		                    </select>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="tdl" style="width:100px;height:30px;">项目编码</td>                    
	                    <td class="td_detail" colspan="3">
	                        <label id="code"/>
	                    </td>
	                </tr>
					<tr>
						<td class="tdl" style="width:100px;height:30px;">项目名称<span style="color:red">*</span></td>                    
	                    <td class="td_detail" colspan="3">
	                        <input class="input-large" id="codeName"/>
	                    </td>				
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
