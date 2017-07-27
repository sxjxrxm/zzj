<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <!--<link rel="stylesheet" type="text/css" href="../Styles/admin-all.css" />-->
    <link rel="stylesheet" type="text/css" href="../styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="../styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="../styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/edit-page.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/upload.css"/>
    <script type="text/javascript" src="../scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-1.8.22.custom.min.js"></script>    
	<!-- 图片上传 -->
    <script src="../scripts/ajaxfileupload.js" type="text/javascript"></script> 
	<style>
	    .tdl{width: 100px;}
    	.td_detail{width: auto;}
	</style>
    <script type="text/javascript">
    function ajaxFileUpload() {
        $.ajaxFileUpload(
            {
                url: '${pageContext.request.contextPath}/customservice/upload.htm',
        		secureuri : false,// 安全协议
        		async : false,
        		fileElementId : 'imgData',
        		dataType:'json',
        		type : "POST",
        		success : function(data) {
        			<%--地址存储——————切记修改！--%>
        			if (data.message == 'success')
        			{		        			
    					$("#weixin_img").attr("src", data.url);
            			$("#qrcodeAddress").val(data.path);
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
    $(function () {    	
        $("#btn_upload").click(function(){
        	$("#imgData").click();
        });
    })
    
    // 保存事件
    function save()
	{
		document.forms[0].action = "<%=path%>/customservice/addEdit.htm";
		document.forms[0].submit();
	}
    // 删除图片
    function del()
		{
  	  		$.ajax({
						url:"${pageContext.request.contextPath}/customservice/delPic.htm",
						data:{sPath:$("#weixin_img").attr("src")},
						success:function(){
									$("#weixin_img").removeAttr("src");
									$("#qrcodeAddress").removeAttr("value");
								}
					})
		}

    </script>
</head>
<body>
	<form onkeydown="if(event.keyCode==13){save();return false;}">
		<div class="alert alert-heading">
			<h4>客服编辑</h4>
		</div>
	    <%@include file="../common/common_msg.jsp"%>
		<table class="tbform" style="margin: 0px; padding: 0px;">
			<thead>
				<tr>
					<td colspan="6" class="sub-title">&nbsp;客服基本信息</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="tdl">客服邮箱<span style="color: red">*</span></td>
					<td class="td_detail" colspan="7">
						<input class="span6" name="email" id="email" value="${mstServiceInfoList.email}" />※最多输入50个字						
					</td>
				</tr>
				<tr>
					<td class="tdl">客服电话<span style="color: red">*</span></td>
					<td class="td_detail" colspan="7">
						<input class="input-large" name="phoneNo" id="phoneNo" value="${mstServiceInfoList.phoneNo }" maxlength="11"/>※最多输入11个数字
					</td>
				</tr>
				<tr>
					<td class="tdl">微信公众号<span style="color: red">*</span></td>
					<td class="td_detail" colspan="7">
						<input class="span6" name="weChat" id="weChat" value="${mstServiceInfoList.weChat }" />※最多输入50个字
					</td>
				</tr>
				<tr>
					<td class="tdl">二维码<span style="color: red">*</span></td>
					<td class="td_detail" colspan="7">
						<div id="preview" style="width:200px;height:220px;">
							<img id="weixin_img"  src="${mstServiceInfoList.qrcodeAddressUrl}" class="QRCode_img"/>
							<input name="qrcodeAddress" type="hidden" id="qrcodeAddress" value="${mstServiceInfoList.qrcodeAddress}"/>
							<p class="detailDesc">&nbsp;&nbsp;客服二维码：宽高一致，不大于200&nbsp;&nbsp;&nbsp;&nbsp;图片大小：200K以内</p>
						</div>
						<div id="bt">
							<span id="uploadImg">
								<input type="file" id="imgData" name="imgData" onchange="ajaxFileUpload()" />
								<input class="btn" type="button" value="上传" id="btn_upload" />								
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="tdl">备注</td>
					<td class="td_detail" colspan="7">
						<div>※最多输入500个字</div>
						<textarea class="span9" name="memo" id="memo" style="width: 635px; height: 119px;">${mstServiceInfoList.memo}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="8" class="td-title">
						<div class="div_bottom_right">
							<button class="btn btn-inverse no_repeat_submit" onclick="save();return false;">保存</button>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>