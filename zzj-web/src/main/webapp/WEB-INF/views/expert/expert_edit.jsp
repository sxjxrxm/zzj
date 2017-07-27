<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/style.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/jquery.autocomplete.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/edit-page.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/upload.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/chur.css" />
	

    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery.autocomplete.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/select-move-option.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/bootstrap.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/scripts/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/ChurAlert.min.js?skin=blue"></script>
    <!-- 图片上传 -->
    <script type="text/javascript" src="<%=path %>/scripts/ajaxfileupload.js"></script> 
	<!-- 修改弹窗画面遮罩区域及弹窗位置 -->
	<style>
		.alert-modal{height:300%}
		.chur-alert{position:absolute;left:50%;top:200%}
	</style>
    <script type="text/javascript" src="<%=path %>/scripts/tb.js"></script>
    <script type="text/javascript">
	    var link = "<%=path %>/expert/edit.htm?edit=edit&news=news";
		var roleId = ${userInfo.roleId};// 获得当前用户的id，2：专家用户
		
	    $(function(){

	    	// 更新专家背景图片
	    	var expert_head_backimg = "${expertInfo.backgroundAdressUrl}";
	    	var expert_information_head_w=$("#expert_bg_img");
	    	if(expert_head_backimg != '') {
	    		expert_information_head_w.attr("src", expert_head_backimg);
	    	}
	    	
	    	//sortMe(document.getElementById("brand_sel"));
            if (roleId != 2) {
		    	// 专家职称/头衔的联想输入
		        $.get("<%=path %>/expert/getRankName.htm", {}, function (data) { 
		        	$(".rankInput").autocomplete(data.split("    "),{minChars:0,max:100});
		        });
		    	// 图片上传
		        $("#btn_upload").click(function(){
		        	$("#imgData").click();
		        });
		    	// 图片上传
		        $("#btn_back_upload").click(function(){
		        	$("#backimgData").click();
		        });
		        //radioChange3();
	            $("input[name=status]").click(function(){
	            	radioChange3();
	            });
            } else {// 专家用户不能修改专家基本信息，只能修改文章
            	$(".expertRole").attr("disabled","disabled");// 禁止修改信息
            	$(".expertRole2").attr("readonly","readonly");// 禁止修改信息
            }
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
	    });
	    
	    function back(){
	    	document.forms[0].action = "<%=path %>/expert/list.htm?edit=edit&flag=0";
	  		document.forms[0].submit();
		}
	    function save(num){ //num 1：专家用户进行保存操作、其他用户保存操作， 2：专家用户进行提交操作
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
	    	$("#fieldNameForKeyWord").attr("value",fieldName);
	    	// 将城市信息的内容赋值给隐藏的input：cityNameForKeyWord，后台构造关键词使用
	    	var cityName = $("#cityP option:selected").text() + $("#cityC option:selected").text();
	    	$("#cityNameForKeyWord").attr("value",cityName);
	    	// 发送请求
	    	document.forms[0].action = "<%=path %>/expert/save.htm?flag=1";
	  		document.forms[0].submit();
		}
	    // 城市信息级联修改
		function changeArea(){
			$.get("<%=path %>/expert/getCityC.htm", {cityPCode : $('#cityP option:selected').val()}, function (data) { creatCityCOptions(data); });
		}
		function creatCityCOptions(CityCList){
			var cityCSelect = $("#cityC");
			if (CityCList.length > 0) {
				cityCSelect.empty();
				for(var i = 0; i < CityCList.length; i++){
					cityCSelect.append("<option value="+CityCList[i].regionCode+">"+CityCList[i].regionName+"</option>");
				}
			} else {
				cityCSelect.empty();
				cityCSelect.append("<option value=''>==请选择==</option>");
			}
		}
		//删除文章信息Btn
		function delArticle(userId,id) {
			$('body').alert({
				type : 'info',
				buttons : [ {
					id : 'yes',
					name : '确定',
					callback : function() {
						delExpertArticle(userId,id);
					}
				}, {
					id : 'no',
					name : '取消',
					callback : function() {
					}
				} ]
			})
		}
		// 执行文章删除操作
		function delExpertArticle(userId,id){
			document.forms[0].action = "<%=path %>/expert/delExpertArticle.htm?expertId="+userId+"&newsCd="+id;
	  		document.forms[0].submit();
		}
		// 编辑文章
		function editArticle(userId,id) {
			$("#isAddNews").val("0");
			document.forms[0].action = "<%=path %>/information/edit.htm?newsCd="+id+"&linkFrom=expertEdit";
	  		document.forms[0].submit();
		}
		// 文章添加
		function addNews(){
			$("#isAddNews").val("1");
			document.forms[0].action = "<%=path %>/information/edit.htm?linkFrom=expertEdit";
	  		document.forms[0].submit();
		}
		// 图片异步上传
		function ajaxFileUpload() {
		        $.ajaxFileUpload(
		            {
		                url: '${pageContext.request.contextPath}/expert/upload.htm',
		        		secureuri : false,// 安全协议
		        		async : false,
		        		fileElementId : 'imgData',
		        		dataType:'json',
		        		type : "POST",
		        		success : function(data) {
		        			<%--地址存储——————切记修改！--%>
		        			if (data.message == 'success')
		        			{		        			
			        			// 将图片上传地址保存到隐藏的input，此时保存的是临时文件
			        			$("#avatorAddress_img").attr("src", data.url);
			        			$("#previewAvatar").attr("src", data.url);
			        			$("#avatorAddress").val(data.path);
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
		// 背景图片异步上传
		function ajaxBackFileUpload() {
		        $.ajaxFileUpload(
		            {
		                url: '${pageContext.request.contextPath}/expert/uploadBack.htm',
		        		secureuri : false,// 安全协议
		        		async : false,
		        		fileElementId : 'backimgData',
		        		dataType:'json',
		        		type : "POST",
		        		success : function(data) {
		        			<%--地址存储——————切记修改！--%>
		        			if (data.message == 'success')
		        			{		        			
			        			// 将图片上传地址保存到隐藏的input，此时保存的是临时文件
			        			$("#expert_bg_img").attr("src", data.url);
			        			$("#backgroundAdress").val(data.path);
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
		// 删除图片
	    function delPic()
		{
	  	  		$.ajax({
							url:"${pageContext.request.contextPath}/expert/delPic.htm",
							data:{path:$("#avatorAddress").val()},
							success:function() {
								$("#avatorAddress_img").attr("src","");
								$("#avatorAddress").removeAttr("value");
							}
						})
		}
	    //设置为同步的请求
	    $.ajaxSetup({
	        async : false  
	    });
	    var recommendInputMsg = "请输入您的推荐语";
	    //推荐checkbox切换
	    function radioChange4() {
	      var recommendInput = $("#recommendInput");
	      var recommend = $("input[name=recommend]");
	      if (recommend.attr("checked")) {
	    	  if ($("#expertId").val() != "")
	    	  {
	    	      $.post("${pageContext.request.contextPath}/recommend/recommendMsg.htm?", {
	    		  		busiType:"01",
	    		  		topicCd:$("#expertId").val(),
	    		  }, function (data) {				  
	    		  		if (data != "")
	    		  		{
	    		  			recommendInputMsg = data;
	    		  		}        		
	    		  });
	    	  }
	          recommendInput.removeAttr("disabled");
	          if (recommendInput.attr("value") == "") {
	              recommendInput.attr("value", recommendInputMsg).css("color", "#aaa");
	          }
	      } else {
	      	if (recommendInput.attr("value") == recommendInputMsg) {
	      		recommendInput.attr("value", "");
	      	}
	      	recommendInput.css("color", "#aaa");
	          recommendInput.attr("disabled", "disabled");
	      }
	    }
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
<form id="expertForm" action="" method="post" enctype="multipart/form-data" onkeydown="if(event.keyCode==13){save();return false;}">
    <div class="alert alert-heading"><h4>专家的编辑•审核</h4></div>
    <%@include file="../common/common_msg.jsp"%>
    <div class="sub-title"><h5>专家基本信息</h5></div>

        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>
                    <td class="tdl" style="width:50px;">专家姓名<span class="red">*</span></td>
                    <td class="td_detail" colspan="3">
                        <input class="span4 expertRole2" name="expertName" id="expertName" value="${expertInfo.expertName}" maxlength="20"/>
                    </td>
                    <td class="tdl" style="width:50px;">专家ID</td>
                    <td class="td_detail" colspan="3">
                        <input class="span4 expertRole2" name="expertId" id="expertId" value="${expertInfo.expertId}" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">头衔/职称1<span class="red">*</span></td>
                    <td class="td_detail" colspan="3">
                    	<input class="span5 rankInput expertRole" id="rank" name="rank" value="${expertInfo.rank}" maxlength="100"/>
                        <!-- <select class="span3" name="rank">
                        	<option value="">==请选择==</option>
                        	<c:set value="${expertInfo.rank}" var="rank"></c:set>
                        	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
	                    		<c:if test="${fn:contains(mstCodeInfo.codeType,'rankType')}">
	                    			<option value="${mstCodeInfo.code}" <c:if test="${fn:contains(mstCodeInfo.code,rank) and isAdd == 0}">selected</c:if>>${mstCodeInfo.codeName}</option>
	                    		</c:if>
	                    	</c:forEach> 
                   		</select>-->
                    </td>
                    <td class="tdl">头衔/职称2</td>
                    <td class="td_detail" colspan="3">
                        <input class="span5 rankInput expertRole" id="rank2" name="rank2" value="${expertInfo.rank2}" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">头衔/职称3</td>
                    <td class="td_detail" colspan="3">
                        <input class="span5 rankInput expertRole" id="rank3" name="rank3" value="${expertInfo.rank3}" maxlength="100"/>
                    </td>
                    <td class="tdl">头衔/职称4</td>
                    <td class="td_detail" colspan="3">
                         <input class="span5 rankInput expertRole" id="rank4" name="rank4" value="${expertInfo.rank4}" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">领域<span class="red">*</span></td>
                    <td class="td_detail tbCenter"  style="width:80px;border-right-color:#fff;">
                    	 <select class="selectArea expertRole" onchange="javascript:selectChange(this);" id="brand_sel" multiple="multiple">
                    	 	<!-- 遍历expertInfo.otherFieldCd，不属于该专家的领域 -->
	                    	 <c:forEach items="${expertInfo.otherFieldCd}" var="otherFieldCd">
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
				         <input type="button" class="btn expertRole" id="brand_selBtn" disabled="disabled" value=" &gt;&gt; " onclick="javascript:rightMove();"/><br/>
				         <input type="button" class="btn expertRole" id="choose_selBtn" disabled="disabled" value=" &lt;&lt; " onclick="javascript:leftMove();"/><br/>
			        </td>
				    <td class="td_detail tbCenter" style="width:80px;border-right-color:#fff;">
				      	<select class="selectArea expertRole" onchange="javascript:selectChange(this);" name="fieldCdStr" id="choose_sel" multiple="multiple">
				      		<!-- 遍历expertInfo.fieldCd，属于该专家的领域 -->
				      		<c:forEach items="${expertInfo.fieldCd}" var="fieldCd">
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
                    
                    <td class="tdl">工作年限</td>
                    <td class="td_detail" colspan="3">
                        <select class="span3 expertRole" name="experience" id="experience">
                        	<option value="">==请选择==</option>
                        	<c:set value="${expertInfo.experience}" var="experience"></c:set>
                        	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
	                    		<c:if test="${fn:contains(mstCodeInfo.codeType,'workExperience')}">
	                    			<option value="${mstCodeInfo.code}" <c:if test="${mstCodeInfo.code eq experience}">selected</c:if>>${mstCodeInfo.codeName}</option>
	                    		</c:if>
	                    	</c:forEach> 
                   		</select>
                    </td>
                    <td class="tdl">工作单位</td>
                    <td class="td_detail" colspan="3">
                         <input class="span5 expertRole" name="company" id="company" value="${expertInfo.company}" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">常驻城市</td>
                    <td class="td_detail" colspan="7">
                        <select class="span2 expertRole" name="cityP" id="cityP" onchange="changeArea()">
                        	<option value="">==请选择==</option>
                        	<c:set value="${expertInfo.cityP}" var="cityP"></c:set>
                        	<c:forEach items="${expertInfo.areaInfosCityP}" var="areaInfosCityP">
	                    		<option value="${areaInfosCityP.regionCode}" <c:if test="${areaInfosCityP.regionCode eq cityP}">selected</c:if>>${areaInfosCityP.regionName}</option>
	                    	</c:forEach> 
                   		</select>&nbsp;&nbsp;
                        <select class="span2 expertRole" name="cityC" id="cityC">
                         	<option value="">==请选择==</option>
                        	<c:set value="${expertInfo.cityC}" var="cityC"></c:set>
                        	<c:forEach items="${expertInfo.areaInfosCityC}" var="areaInfosCityC">
	                    		<option value="${areaInfosCityC.regionCode}" <c:if test="${areaInfosCityC.regionCode eq cityC}">selected</c:if>>${areaInfosCityC.regionName}</option>
	                    	</c:forEach> 
                   		</select>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">手机<span class="red">*</span></td>
                    <td class="td_detail" colspan="3">
                        <input class="span2 expertRole" name="phone" value="${expertInfo.phone}" id="phoneInput" maxlength="11"
                        onkeyup="checkPhoneNum()" onchange="checkPhoneNum()"/>
                    </td>
                    <td class="tdl">邮箱</td>
                    <td class="td_detail" colspan="3">
                        <input class="span2 expertRole" id="email" name="email" value="${expertInfo.email}" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">专家介绍<span class="red">*</span></td>
                    <td class="td_detail" colspan="7">
                    	<div id="textEditorDiv" class="wxeditorarea expertRole">
                    		<textarea id="expertBrief" name="expertBrief">${expertInfo.expertBrief}</textarea>
	                    	<div class="editorSlide">
								<ul>
									<li>
										<i class="imb-preview"></i>
										<div class="icon-cover cover-preview"></div>
									</li>
								</ul>
							</div>
                    	</div>
                        <!-- <textarea class="span2 expertRole" name="expertBrief" id="expertBrief" rows="15" cols="150" style="width:auto;border-radius: 0;">${expertInfo.expertBrief}</textarea> -->
                    </td>
                </tr>
                <tr>
                    <td class="tdl">专家背景图片</td>
                    <td class="td_detail" colspan="3">
                    	<div class="expert_information_head">
                    	    <div class="expert_background"><img id="expert_bg_img" src="<%=path %>/images/expert_information_img.png" style="height:206px;width:100%;z-index=-1;" /></div>
						    <div class="expert_information_title"></div>
						    <div class="expert_head_bg" ></div>
						    <div class="expert_head"><img id="previewAvatar" src="${expertInfo.avatorAddressUrl}" /></div>
							<input name="backgroundAdress" value="${expertInfo.backgroundAdress}" type="hidden" id="backgroundAdress"/>
						</div>
						<div id="bt2" style="display:block; padding:2px; height:35px;">
							<p class="detailDesc">图片尺寸：720*412&nbsp;&nbsp;&nbsp;&nbsp;图片大小：200K以内&nbsp;
							<span id="uploadImg2" style="width:80px;float:right;" >
								<input type="file" id="backimgData" name="backimgData" onchange="ajaxBackFileUpload()" />
								<input class="btn expertRole" type="button" value="上传" id="btn_back_upload"/>
							</span></p>
						</div> 
                    </td>
                    <td class="tdl">专家头像</td>
                    <td class="td_detail" colspan="3">
                    	<div id="preview" style="width:120px;">
							<img id="avatorAddress_img"  src="${expertInfo.avatorAddressUrl}" class="avator_img"/>
							<input name="avatorAddress" value="${expertInfo.avatorAddress}" type="hidden" id="avatorAddress"/>
							<p class="detailDesc">图片尺寸：140*140&nbsp;&nbsp;&nbsp;&nbsp;图片大小：200K以内&nbsp;</p>
						</div>
						<div id="bt">
							<span id="uploadImg">
								<input type="file" id="imgData" name="imgData" onchange="ajaxFileUpload()" />
								<input class="btn expertRole" type="button" value="上传" id="btn_upload"/>
								<!-- <input class="btn" type="button" value="删除" onclick="delPic();return false;"/> -->
							</span>
						</div> 
                    </td>
                </tr>
                
	                <tr>
	                    <td class="tdl">置顶</td>
	                    <td class="td_detail" colspan="7">
	                    	<c:set var="recommendKbn" value="${expertInfo.recommendKbn}" scope="page"></c:set>
	                        <input <c:if test="${fn:contains(recommendKbn, '2')}">checked</c:if> <c:if test="${expertInfo.status != 1}">disabled="disabled"</c:if> type="checkbox" name="toTopCode" value="2" id="toTop"  class="expertRole statusFlag"/> <label class="checkbox-label expertRole" for="toTop">置顶</label>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="tdl">推荐</td>
	                    <td class="td_detail" colspan="7">
	                        <input <c:if test="${fn:contains(recommendKbn, '1')}">checked</c:if> <c:if test="${expertInfo.status != 1}">disabled="disabled"</c:if> type="checkbox" name="recommend" value="1" id="recommend" class="expertRole statusFlag" onclick="radioChange4();"/> <label class="checkbox-label expertRole" for="recommend">推荐</label>&nbsp;&nbsp;&nbsp;&nbsp;
	                        <input class="span5 expertRole statusFlagMsg" id="recommendInput" value="${expertInfo.recommendMsg}" name="recommendMsg" maxlength="100"
		                        <c:choose>
		                        	<c:when test="${fn:contains(recommendKbn, '1')}"></c:when>
		                        	<c:otherwise>disabled="false"</c:otherwise>
		                        </c:choose>
		                         <c:if test="${expertInfo.status != 1}">disabled="disabled"</c:if>
		                        onfocus="recommendInputCheck();"/>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="tdl">审核结果</td>
	                    <td class="td_detail" colspan="7" id="statusSelect">
	                        <input name="status" type="radio" class="expertRole" value="1" <c:if test="${expertInfo.status eq 1}">checked</c:if> <c:if test="${expertInfo.status eq 1 and (fn:contains(infoMsg, '成功') or isAdd eq 0)}">disabled</c:if>/>通过&nbsp;&nbsp;
	    			    	<input name="status" type="radio" class="expertRole" value="2" <c:if test="${expertInfo.status eq 2}">checked</c:if> <c:if test="${expertInfo.status eq 1 and (fn:contains(infoMsg, '成功') or isAdd eq 0)}">disabled</c:if>/>拒绝<br/>
	    			    	<c:if test="${expertInfo.status eq 1 or fn:contains(infoMsg, '成功')}"><!-- 状态为1时，由于radio按钮禁用，后台接受为空值，所以此处使用隐藏input给其赋值 -->
	    			    		<input type="hidden" value="1" name="status"/>
	    			    	</c:if>
	    			    <input name="refuseMemoType" onclick="refuseMemoTypeChange()" type="radio" class="expertRole" id="refuseMemoType1" value="1" <c:if test="${expertInfo.status eq 2 and expertInfo.refuseMemoType eq 1}">checked</c:if> <c:if test="${expertInfo.status != 2}">disabled</c:if>/>选择拒绝理由&nbsp;&nbsp;
	    			    <select  class="dfinput expertRole" id="sel" <c:if test="${expertInfo.status != 2 or expertInfo.refuseMemoType != 1}">disabled</c:if> name="refuseMemo">
		                	<option value="">==请选择==</option>
		                	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
	                    		<c:if test="${mstCodeInfo.codeType=='refuseType'}">
	                    			<option value="${mstCodeInfo.code}" <c:if test="${expertInfo.refuseMemo == mstCodeInfo.code}">selected</c:if>>${mstCodeInfo.codeName}</option>
	                    		</c:if>
	                    	</c:forEach>
						</select><br/>
						<input name="refuseMemoType" onclick="refuseMemoTypeChange()" type="radio" class="expertRole" id="refuseMemoType2" value="2" <c:if test="${expertInfo.status eq 2 and expertInfo.refuseMemoType eq 2}">checked</c:if> <c:if test="${expertInfo.status != 2}">disabled</c:if>/>自定义拒绝理由&nbsp;&nbsp;
						<input name="refuseMemo" class="span6 expertRole" id="refuseMemo2" maxlength="100" 
							<c:if test="${expertInfo.status eq 2 and expertInfo.refuseMemoType eq 2}">value="${expertInfo.refuseMemo}"</c:if>
							<c:if test="${expertInfo.status != 2 or expertInfo.refuseMemoType != 2}">disabled</c:if>/>※最多输入100个字 
	                    </td>
	                </tr>
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button onclick="back(); return false;" class="btn btn-inverse no_repeat_submit">返回</button>
                            <button onclick="save(); return false;" id="saveBtn" class="btn btn-inverse no_repeat_submit expertRole">保存</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    <input type="hidden" value="${isAdd}" name="isAdd"/>
    <input type="hidden" value="${doSearch}" name="doSearch"/><!-- 记录一览页面是否执行了查询操作 -->
    <input type="hidden" value="${userInfo.userId}" name="updateId"/>
    <input type="hidden" value="" name="fieldNameForKeyWord" id="fieldNameForKeyWord"/><!-- 表单提交前设置值，保存领域名称，后台构造专家关键词使用 -->
    <input type="hidden" value="" name="cityNameForKeyWord" id="cityNameForKeyWord"/><!-- 表单提交前设置值，保存城市名称，后台构造专家关键词使用 -->
    <!-- 页面回显使用的数据 -->
   <%--  <input type="hidden" value="${selectResource.expertName}" name="expertNameLike"/>
    <input type="hidden" value="${selectResource.techField}" name="techField"/>
    <input type="hidden" value="${selectResource.auditStatus}" name="auditStatus"/>
    <input type="hidden" value="${selectResource.recommendKbn}" name="recommendKbn"/> --%>
    <input type="hidden" value="${selectResource}" name="selectResource"/>
    <input type="hidden" value="${expertPageNo}" name="expertPageNo"/>


    <!--检索一览-->
	 <table class="tb" id="list">
	 <!--
	 <div><h5>文章状态&nbsp;&nbsp;&nbsp;
	 <input  name = "Fruit"  type = "radio"  value = ""  />全部 &nbsp;&nbsp;
	 <input  name = "Fruit"  type = "radio"  value = ""  />已审核&nbsp;&nbsp;
	 <input  name = "Fruit"  type = "radio"  value = ""  />待审核</h5> 
    </div>
    <c:if test="${expertInfo.status != '1'}">none</c:if>
    -->
    </table>

<div style='display:<c:if test="${expertInfo.status != '1'}">none</c:if>'>
	<div class="sub-title"><h5>专家文章一览</h5></div>
		<table class="tbform" style="margin:0px; padding:0px;">
             <tr>
                 <td colspan="8" class="td-title">
                     <div class="div_bottom_right">
                         <a class="btn btn-inverse no_repeat_submit" href="javascript:addNews();">文章添加 </a>
                     </div>
                 </td>
             </tr>
        </table>
     <table class="tb" id="list"> 
         <tbody>
             <tr>
                <th style="text-align:center;">操作 </th>
                <th style="text-align:center;">文章主题</th>
                <!-- <th style="text-align:center;">文章简介</th> -->
                <th style="text-align:center;">文章状态</th>
                <th style="text-align:center;">费用（元）</th>
            </tr>
            <c:forEach items="${resultList.items}" var="news">
            <tr>
                <td style="text-align:center;">
	                <a href="javascript:editArticle('${news.sourceOwner}','${news.newsCd}');">编辑 </a>
	                <a href="javascript:delArticle('${news.sourceOwner}','${news.newsCd}');" style="color:#FF0000">删除 </a><br/>
                </td>
                <td>${news.newsName}</td>
                <!-- <td>${news.newsBrief}</td> -->
                <td style="text-align:center;">
                   	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo">
                   		<c:if test="${mstCodeInfo.codeType=='auditStatus' && mstCodeInfo.code==news.status}">
                   			${mstCodeInfo.codeName}
                   		</c:if>
                   	</c:forEach>
                   	<c:if test="${empty news.status}">待审核</c:if>
                </td> 
                <td style="text-align:right;"><fmt:formatNumber value="${news.price}" type="currency" pattern="#,#00.00#"/><c:if test="${empty news.price}">0.00</c:if></td>
            </tr>
            </c:forEach>
            <tr style='display:<c:if test="${resultList.items == null || resultList.items.size() == 0}">none</c:if>'>
                <%@include file="../common/page.jsp" %>
            </tr>
         </tbody>
    </table>

</div>
    <input type="hidden" value="${resultList.pageNo}" name="pageNo"/>
    <input type="hidden" value="" name="isAddNews" id="isAddNews"/>
    <input type="hidden" value="${showNews}" name="showNews" id="showNews"/>
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
    ue = new UE.ui.Editor({
        toolbars: [["undo","redo","bold","italic","forecolor","backcolor","paragraph","fontfamily","fontsize",
                    "autotypeset",'insertorderedlist',"lineheight","removeformat","justifyleft",
                    "justifycenter","justifyright",'justifyjustify', "indent",'source']]
        ,initialFrameHeight:500
    });
    ue.render("expertBrief");
	
    // 编辑器加载事件
    var editHeight = 0;
    var menuYloc = 0;
    ue.ready(function(){
        editHeight = $("#textEditorDiv").offset().top + $("#textEditorDiv").height() + 7;
        menuYloc = $("#textEditorDiv").offset().top + $(".editorSlide").height()/2 + 5;
    	// 设置浮动预览按钮
    	$(".editorSlide").offset({top:$("#textEditorDiv").offset().top - 10});
        if(roleId == 2) ue.setDisabled(true);
	});
</script>
</body>
</html>
