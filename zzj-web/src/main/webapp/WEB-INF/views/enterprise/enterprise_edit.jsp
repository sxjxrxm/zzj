<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title> 
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/edit-page.css"/>    
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
    	.tdl{width: 100px;}
    </style>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="../scripts/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.multiselect.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.core.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-checkbox/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/ChurAlert.min.js?skin=blue"></script>
    <script type="text/javascript" src="<%=path %>/scripts/tb.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-move-option.js"></script>

    <script type="text/javascript">
    
    	// 返回
	    function back() {
	    	document.forms[0].action = "<%=path %>/enterprise/edit.htm?add=back";
	  		document.forms[0].submit();
		}
    
   		// 保存
	    function save() {
	    	// 将右侧领域的内容赋值给隐藏的input：fieldNameForKeyWord，后台构造关键词使用	
	    	document.forms[0].action = "<%=path %>/enterprise/edit.htm?add=save";
	  		document.forms[0].submit();
		}
   		// 拒绝理由控制
	    $(function(){
	    	window.onload = function(){
	    					var r = document.getElementsByName("status");
	    					var s = document.getElementById("refuseMemo");
	    					r[0].onclick = function(){
	    						s.disabled = this.checked;
	    					}
	    					r[1].onclick = function(){
	    						s.disabled = !this.checked;
	    					}
	    					}
	    	$("input[name=status]").click(function(){
            	radioChange3();
            });
	    	})
   		// 领域遍历
	   	$(function(){
		   	       var numArr = []; // 定义一个空数组
		   	       var a = $("input[name='fieldCodeName']");// 获取所有文本框
		           
		           for (var i = 0; i < a.size(); i++) {
		               numArr.push(a.eq(i).val()); // 将文本框的值添加到数组中
		               $('#fieldCodeNameList').append(a.eq(i).val()+"\n");
		           }

	   		})
	   	// 选择拒绝理由与自定义拒绝理由切换
	    function refuseMemoTypeChange(){
	    	var val = $("input[name=refuseMemoType]:checked").attr("value");
	    	var r1 = $("#sel");
	    	var r2 = $("#refuseMemo2");
	    	switch (val) {
		        case "1":
		           	r1.removeAttr("disabled");
		           	r2.attr("disabled","disabled").val("");
		            break;
		        case "2":
		        	for (var i = 0; i < r1[0].options.length; i++) {
		        		r1[0].options[i].selected = false;
		            }
		        	r2.removeAttr("disabled");
		           	r1.attr("disabled","disabled");
		            break;
		        default:
		            break;
		        }
		    }
    </script>
</head>
<body>
    <form id="questionForm" method="post" onkeydown="if(event.keyCode==13){save();return false;}">
	    <div class="alert alert-heading"><h4>政企审核</h4></div>
	    <%@include file="../common/common_msg.jsp"%>
	    <div class="sub-title"><h5>用户基本信息</h5></div>
        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>                  
                    <td class="tdl">用户姓名</td>
                    <td class="td_detail" colspan="2">
                        <input class="span2" name="userName" value="${corpInfoList.userName}" disabled="disabled"/>
                    </td>
					<td class="tdl">头衔/职称</td>
					<td class="td_detail" colspan="2">
                        <input class="span2" name="rank" value="${corpInfoList.rank}" disabled="disabled"/>
					</td>
                </tr>           
                <tr>
                    <td class="tdl" rowspan="4">领域</td>
                    <td class="td_detail tbCenter" colspan="2" rowspan="4" style="width:80px;border-right-color:#fff;" >
                        <select class="selectArea" onchange="javascript:selectChange(this);" id="brand_sel" multiple="multiple" disabled="disabled">
	                    	<c:forEach items="${corpInfoList.fieldCd}" var="fieldCd">
								<c:forEach items="${techCodeInfos}" var="mstCodeInfo">
									<c:if test="${mstCodeInfo.code==fieldCd.techFieldCd}">
										<c:set value="${mstCodeInfo.code}" var="techFieldCode"></c:set>
										<c:set value="${mstCodeInfo.codeName}" var="techFieldCodeName"></c:set>
									</c:if>
								</c:forEach>
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
				    <td class="tdl" valign="top">单位性质</td>
					<td class="td_detail" colspan="2" valign="top">
						<select class="span2" name="corpType" id="corpType" disabled="disabled"> 
							<option value="">==请选择==</option>
							<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
								<c:if test="${mstCodeInfo.codeType=='corpType'}">
									<c:set var="selectNeedsType" value="${corpInfoList.corpType}" scope="page"></c:set>
									<c:set var="typeCode" value="${mstCodeInfo.code}" scope="page"></c:set>
									<option <c:if test="${fn:contains(selectNeedsType, typeCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
								</c:if>
							</c:forEach>				
						</select>
					</td>
                </tr>
                <tr><td colspan="2" rowspan="3"></td></tr>
                <tr></tr>
                <tr></tr>
                <tr>
                    <td class="tdl">工作单位</td>
                    <td class="td_detail" colspan="2">
                        <input class="span4" name="company" id="company" value="${corpInfoList.company}" disabled="disabled"/>
				    </td>
				    <td class="tdl">工作年限</td>
                    <td class="td_detail" colspan="2">                        
                    <select class="span2" disabled="disabled">
                        	<option value="">==请选择==</option>
                        	<c:set value="${corpInfoList.experience}" var="experience"></c:set>
                        	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
	                    		<c:if test="${fn:contains(mstCodeInfo.codeType,'workExperience')}">
	                    			<option value="${mstCodeInfo.code}" <c:if test="${mstCodeInfo.code eq experience}">selected</c:if>>${mstCodeInfo.codeName}</option>
	                    		</c:if>
	                    	</c:forEach> 
                   		</select>
                    </td>
                </tr>
                <tr>
				    <td class="tdl">常驻城市</td>
                    <td class="td_detail" colspan="8">                        
                    <select class="span3" name="cityP" id="cityP" disabled="disabled">
                        	<option value="">==请选择==</option>
                        	<c:set value="${corpInfoList.cityP}" var="cityP"></c:set>
                        	<c:forEach items="${corpInfoList.areaInfosCityP}" var="areaInfosCityP">
	                    		<option value="${areaInfosCityP.regionCode}" <c:if test="${areaInfosCityP.regionCode eq cityP}">selected</c:if>>${areaInfosCityP.regionName}</option>
	                    	</c:forEach> 
                   	</select>&nbsp;&nbsp;
                    <select class="span3" name="cityC" id="cityC" disabled="disabled">
                         	<option value="">==请选择==</option>
                        	<c:set value="${corpInfoList.cityC}" var="cityC"></c:set>
                        	<c:forEach items="${corpInfoList.areaInfosCityC}" var="areaInfosCityC">
	                    		<option value="${areaInfosCityC.regionCode}" <c:if test="${areaInfosCityC.regionCode eq cityC}">selected</c:if>>${areaInfosCityC.regionName}</option>
	                    	</c:forEach> 
                   	</select>
                    </td>
                </tr> 
                           
                <tr>
                    <td class="tdl">手机</td>
                    <td class="td_detail" colspan="2">
                        <input class="span2" name="phone" id="phone" value="${corpInfoList.phone}" disabled="disabled"/>
				    </td>

				    <td class="tdl">单位邮箱</td>
                    <td class="td_detail" colspan="2">
                        <input class="span3" name="email" id="email" value="${corpInfoList.email}" disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">政企介绍</td>
                    <td class="td_detail" colspan="8" >
                        <textarea class="span2" name="brief" id="brief" rows="2" cols="150" style="width:auto;border-radius: 0;" disabled="disabled">${corpInfoList.brief}</textarea>
                    </td>
                </tr>
                
                <tr>
	                <td class="tdl">审核结果<span class="red">*</span></td>
	                <td class="td_detail" colspan="8" id="statusSelect">
	                    <input name="status" type="radio" value="1" <c:if test="${corpInfoList.status == 1}">checked</c:if> <c:if test="${corpInfoList.status == 1}">disabled</c:if> />通过&nbsp;&nbsp;
	    			    <input name="status" type="radio" value="2" <c:if test="${corpInfoList.status == 2}">checked</c:if> <c:if test="${corpInfoList.status == 1}">disabled</c:if> />拒绝<br/>
		    			<input name="refuseMemoType" onclick="refuseMemoTypeChange()" type="radio"  id="refuseMemoType1" value="1" 
		    				<c:if test="${corpInfoList.status eq 2 and corpInfoList.refuseMemoType eq 1}">checked</c:if>
		    				<c:if test="${corpInfoList.status != 2}">disabled</c:if> />选择拒绝理由&nbsp;&nbsp;
		    			<select  class="dfinput" id="sel" <c:if test="${corpInfoList.status != 2 or corpInfoList.refuseMemoType != 1}">disabled</c:if> name="refuseMemo">
			                <option value="">==请选择==</option>
			                <c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
		                    	<c:if test="${mstCodeInfo.codeType=='refuseType'}">
		                    		<option value="${mstCodeInfo.code}" <c:if test="${corpInfoList.refuseMemo == mstCodeInfo.code}">selected</c:if>>${mstCodeInfo.codeName}</option>
		                    	</c:if>
		                    </c:forEach>
					    </select><br/>
					    <input name="refuseMemoType" onclick="refuseMemoTypeChange()" type="radio" id="refuseMemoType2" value="2" 
					    	<c:if test="${corpInfoList.status eq 2 and corpInfoList.refuseMemoType eq 2}">checked</c:if>
					    	<c:if test="${corpInfoList.status != 2}">disabled</c:if> />自定义拒绝理由&nbsp;&nbsp;
						<input name="refuseMemo" class="span6" id="refuseMemo2" maxlength="100" 
							<c:if test="${corpInfoList.status eq 2 and corpInfoList.refuseMemoType eq 2}">value="${corpInfoList.refuseMemo}"</c:if>
							<c:if test="${corpInfoList.status != 2 or corpInfoList.refuseMemoType != 2}">disabled</c:if>/>※最多输入100个字 
	                </td>
	            </tr>
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button class="btn btn-inverse no_repeat_submit" onclick="back(); return false;" >返回</button>
                            <button class="btn btn-inverse no_repeat_submit" onclick="save(); return false;" <c:if test="${corpInfoList.status == 1}">disabled</c:if> >保存</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
		<!--保存用户信息-->
		<input type="hidden" name="userId" value="${corpInfoList.userId}"/>
		<!-- 保存政企一览查询条件 -->
		<%-- <input type="hidden" name="userName" value="${enterpriseVO.userName}"/>
	<c:forEach items="${enterpriseVO.statusList}" var="statusList">
		<input type="hidden" name="statusList" value="${statusList}"/>
	</c:forEach>
	<c:forEach items="${enterpriseVO.techFieldTypeList}" var="techFieldTypeList">
		<input type="hidden" name="techFieldTypeList" value="${techFieldTypeList}"/>
	</c:forEach>
		<input type="hidden" name="corpType" value="${enterpriseVO.corpType}"/>
		<input type="hidden" name="cityP" value="${enterpriseVO.cityP}"/>
		<input type="hidden" name="cityC" value="${enterpriseVO.cityC}"/> --%>
		<input type="hidden" name="selectKey" value="${selectKey}"/>
		
		<input type="hidden" name="needsPageNo" value="${needsPageNo}"/>
		<input type="hidden" name="doSearch" value="${doSearch}"/>
    </form>
</body>
</html>
