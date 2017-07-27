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
	#uploadImg{ font-size:12px; overflow:hidden; position:absolute}
	#file{ position:absolute; z-index:100; margin-left:-180px; font-size:60px;opacity:0;filter:alpha(opacity=0); margin-top:-5px;}
	 #preview, .img, img
	 {
	 width:210px;
	 height:210px;
	 }
	 #preview
	 {
	border:1px solid #000;
	float:left;
	}
	.d
	{
		float:left;
		padding-left:52px;
	}
	
	#bt
	{
	padding-top:185px;	
	}
	
	#imgData {
    visibility:none;
    height:0;
    width:0;
}
	</style>
    <script type="text/javascript">
		 $(function () {
            $(".datepicker").datepicker();
            $("#btn_upload").click(function(){
            	$("#imgData").click();
            });
        })
        // 删除功能
        function deleteAd(){
			 // 得到欲删除记录主键
			 var commercialCd = $("#commercialCd").val();
			 // 使用aJax删除
			 $.post("${pageContext.request.contextPath}/ad/deleteAd.htm?commercialCd="+commercialCd+"",
						"",
						function(flag){
							if(flag != true){
								alert("阿斯达三大");
							}
				  		});
		 }
        // 图片上传功能
        function ajaxFileUpload() {
        $.ajaxFileUpload(
            {
                url: '${pageContext.request.contextPath}/ad/imgUpload.htm',
        		secureuri : false,// 安全协议
        		async : false,
        		fileElementId : 'imgData',
        		dataType:'json',
        		type : "POST",
        		success : function(data) {
                },
                error: function (data, status, e)// 服务器响应失败处理函数
                {
                    alert(e);
                    return false;
                }
            }
        )
    }
	// 保存事件
    function save()
		{
	    	// 先上传图片
			$.ajaxFileUpload(
            {
                url: '${pageContext.request.contextPath}/ad/imgUpload.htm',
        		secureuri : false,// 安全协议
        		async : false,
        		fileElementId : 'imgData',
        		dataType:'json',
        		type : "POST",
        		success : function(data) {
                    var name = $("#name").val();
    				var brief = $("#textEditor").val();
    				var userId = $("#userId").val();
    				var isAdd = $("#isAdd").val();
    				var commercialCd = $("#commercialCd").val();
    				//AJax将添加信息传到后台
    				// 调用Ajax将该记录放入轮播表
    				$.post("${pageContext.request.contextPath}/ad/editAd.htm?name="+name+"&brief="+brief+"&userId="+userId+"&isAdd="+isAdd+"&commercialCd="+commercialCd+"",
    						"",
    						function(flag){
    							if(flag != true){
    								alert("阿斯达三大");
    							}
    				  		});
                },
                error: function (data, status, e)// 服务器响应失败处理函数
                {
                    alert(e);
                    return false;
                }
            }
        )
		}
    // 删除图片
    function del()
	{
   	alert("ajax进来了");
 	  		$.ajax({
					url:"${pageContext.request.contextPath}/ad/delPic.htm",
					data:{sPath:$("#weixin_img").attr("src")},
					success:function(){
								$("#weixin_img").removeAttr("src");
								$("#qrcodeAddress").removeAttr("value");
							}
				})
	}
	function preview(file)
	 {
	 var prevDiv = document.getElementById('preview');
	 if (file.files && file.files[0])
	 {
	 var reader = new FileReader();
	 reader.onload = function(evt){
	 prevDiv.innerHTML = '<img src="' + evt.target.result + '" />';
	}  
	 reader.readAsDataURL(file.files[0]);
	}
	 else  
	 {
	 prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
	 }
	 }
	 function del(){
		 var prevDiv = document.getElementById('preview');
       	prevDiv.innerHTML = ''; 
       }

	function back()
	{
		// 得到之前的查询条件
		var keyBusi = $("#keyBusi").val();
       	var keyTopic = $("#keyTopic").val();
       	var inputs = $(".keyTechField");
       	var keyTechFields = new Array();
       	for(var i = 0; i < $(".keyTechField").length; i++){
       		if($($(".keyTechField")[i]).val() != "undefined"){
       			keyTechFields[i] = $($(".keyTechField")[i]).val();
       		}
       		else{
       			keyTechFields = null;
       		}
       	}
       	// 跳转
		window.location.href="${pageContext.request.contextPath}/slide/edit.htm?keyBusi="+keyBusi+"&keyTopic="+keyTopic+"&keyTechField="+keyTechFields+"";
	}
    </script>
</head>
<body>
    <form>
    <div class="alert alert-heading">
	    <c:if test="${commercialVO.isAdd == 0}">
	    	<h4>广告编辑</h4>
	    </c:if>
	    <c:if test="${commercialVO.isAdd == 1}">
	    	<h4>广告添加</h4>
	    </c:if>
    	<input id="userId" value="${userInfo.userId}" type="hidden"/>
    	<input id="isAdd" value="${commercialVO.isAdd}" type="hidden"/>
    	<input id="commercialCd" value="${commercialVO.commercialCd}" type="hidden"/>
    	<input id="keyTopic" value="${keyTopic}" type="hidden"/>
    	<input id="keyBusi" value="${keyBusi}" type="hidden"/>
    	<c:forEach items="${keyTechField}" var="techField" varStatus="s">
    		<input class="keyTechField" value="${keyTechField[s.count-1]}" type="hidden"/>
    	</c:forEach>
    </div>
        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>
                   
                    <td class="tdl">广告题目<span style="color:red">*</span></td>                    
                    <td class="td_detail" colspan="3">
                        <input class="input-large" style="width:800px" id="name" value="${commercialVO.commercialName}"/>
                    </td>
				</tr>
				<tr>
					<td class="tdl">广告简介</td>                    
                    <td class="td_detail" colspan="3">
                        <textarea id="textEditor" name="DIVCSS5" style="width:1000px;height:70px;">
                        ${commercialVO.commercialBrief}
					   </textarea> 
                    </td>
                </tr>
				<tr>
					<td class="tdl">图片上传<span style="color: red">*</span></td>
					<td class="td_detail" colspan="3">
						<div id="preview">
							<img id="weixin_img"  src="${commercialVO.iconAddress}" />
							<input name="qrcodeAddress" type="hidden" id="qrcodeAddress" value="${mstServiceInfoList.qrcodeAddress}"/>
						</div>
						<div id="bt">
							<span id="uploadImg">
								<input type="file" id="imgData" name="imgData"  onchange="preview(this)"/>
								<input class="btn" type="button" value="上传" id="btn_upload" />
								<!-- <input class="btn" type="button" value="删除" onclick="del();return false;" id="btn_del"/> -->
							</span>
						</div>
					</td>
				</tr>
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button class="btn btn-inverse no_repeat_submit" onclick="back();return false;">返回</button>
							<button class="btn btn-inverse no_repeat_submit" onclick="deleteAd();return false;">删除</button>
                            <button class="btn btn-inverse no_repeat_submit"
							onclick="save();return false;">保存</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>
