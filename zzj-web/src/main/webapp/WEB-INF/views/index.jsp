<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>后台管理系统</title>
	<link rel="shortcut icon" href="favicon.ico" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/admin-all.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/base.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/chur.css" />
    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/index.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/ChurAlert.min.js?skin=blue"></script>
    <script type="text/javascript">
	function hidpic(){
		$(this).parent('.kidli').addClass('kidli-hover');
		$("#load_pic").hide();
		$("#Conframe").show();
	}
	 $(function () {
		$("#Conframe").hide();
     })
	 function reload(){
	 	history.go(0);
	 }
	 function editPassword(id) {  	
	    $.dialog({
	        title: '密码修改',
	        content: $('#popupForm').html(),
	        lock: true,
	        width: 330,
	        height: 130,
	        min: false,
	        max: false,
	        cancelVal: '关闭',
	        button: [{
	            id: 'chur',
	            name: '保存',
	            callback: function() {
	            	passwordExecute(id);
                	if ($('#message0', parent.document).text() != "")
                	{
                		return false;	                		
                	}	                	
                }
	        }],
	        cancel: true
	    });
	 }	 
	 function logout() {
         $('.warp').alert({
             type: 'logout',
             buttons: [{
                 id: 'yes',
                 name: '确定',
                 callback: function () {
                	 $.get("logout.htm",function(){
                		top.location = "login.htm";
                	});
                 }
             }, {
                 id: 'no',
                 name: '取消',
                 callback: function () {
                 }
             }]
         })
	}
	 
	// 设置为同步的请求
	$.ajaxSetup({
	    async : false  
	});

    // 密码变更
    function passwordExecute(userId) {
    	oldPassword = $('#oldPassword').val();
    	newPassword1 = $('#newPassword1').val();
    	newPassword2 = $('#newPassword2').val();
    	$.post("<%=path %>/mstuser/editPassword.htm?userId=" + userId, {
    		oldPassword:oldPassword,
    		newPassword1:newPassword1,
    		newPassword2:newPassword2,
    	}, function (data) {
    		if (data == 'success') {
				var msg = $('#message0', parent.document);
    			if(msg != null && msg != undefined) {
    				msg.text("");
    			}
    		}
    		else
    		{
    			$('#oldPassword', parent.document).removeAttr("style");
    			$('#newPassword1', parent.document).removeAttr("style");
    			$('#newPassword2', parent.document).removeAttr("style");
  				var flg = true;
				if (data.indexOf("旧密码") > -1)
				{
					var temp = $('#oldPassword', parent.document);
					if (flg) 
					{
						temp.focus();
						flg = false;
					}
					temp.attr("style", "background-color:#b94a48;");
				}
				if (data.indexOf("新密码") > -1)
				{
					var temp = $('#newPassword1', parent.document);
					if (flg) 
					{
						temp.focus();
						flg = false;
					}
					temp.attr("style", "background-color:#b94a48;");
				}
				if (data.indexOf("新密码确认") > -1)
				{
					var temp = $('#newPassword2', parent.document);
					if (flg) 
					{
						temp.focus();
						flg = false;
					}
					temp.attr("style", "background-color:#b94a48;");
				}
    			var arr = data.split("<br/>");    			
    			for (var i = 0; arr.length > 0 && i < arr.length; i++)
    			{
        			// 设置错误信息
        			var msg = $('#message' + i, parent.document);
        			if(msg != null && msg != undefined) {
        				msg.text(arr[i]);
        				msg.show();
        				msg.attr("style", "color:red;font-weight:bold;font-size:small;");
        			}
    			}
    		}
		});
    }
    </script>
</head>
<body>
    <div class="warp">
        <!--头部开始-->
        <div class="top_c">
            <div class="top-nav">欢迎登录系统，${userInfo.userName}！&nbsp;&nbsp;<a href="javascript:editPassword('${userInfo.userId}');">修改密码</a>&nbsp; | <a href="javascript:logout();">退出系统</a></div>
        </div>
        <!--头部结束-->
        <!--左边菜单开始-->
        <div class="left_c left">
            <h1>系统操作菜单</h1>
            <div class="acc">
            <c:forEach items="${screenList}" var="p" >
            <c:if test="${p.parentScreenId.trim() == ''}">
                <div>
            		<a class="one">${p.screenName}</a>
            		<ul class="kid">
		                <c:forEach items="${screenList}" var="screem" >
		                <c:if test="${screem.parentScreenId == p.screenId and screem.menuShowFlag == false}">
						<li class="kidli"><b class="tip"></b><a class="kida" target="Conframe" href="${screem.linkUrl}" onclick="hidpic()">${screem.screenName}</a></li>
		                </c:if>
		                </c:forEach>
	                </ul>
	            </div>
	        </c:if>
            </c:forEach>
          </div>
        </div>
        <!--左边菜单结束-->
        <!--右边框架开始-->
        <div class="right_c">
            <div class="nav-tip" onclick="javascript:void(0)">&nbsp;</div>

        </div>
        <div class="Conframe">
			<img src="images/back_show2.jpg" width="100%" height="100%" id="load_pic"/>  
            <iframe name="Conframe" id="Conframe"></iframe>
        </div>
        <!--右边框架结束-->
        <!--底部开始-->
        <div class="bottom_c">Copyright &copy;2016-2017 中国IT移动智库(找专家) 版权所有</div>
        <!--底部结束-->
    </div>
    <div id="popupFormDiv" style="display: none;">
		<form id="popupForm" action="" method="post">
			<div class="alert alert-heading">
				<h4 align="center">密码修改</h4>				
			</div>
			<table class="tbform" style="margin: 0px; padding: 0px;">
				<tbody>
					<tr><td class="tdl" colspan = '2'><label id="message0" style="display:none"/></td></tr>
					<tr><td class="tdl" colspan = '2'><label id="message1" style="display:none"/></td></tr>
					<tr><td class="tdl" colspan = '2'><label id="message2" style="display:none"/></td></tr>
					<tr>
						<td class="tdl">※密码长度为6-10个字符</td>
					</tr>
					<tr>
						<td class="tdl">旧密码<span style="color:#FF0000">*</span></td>
						<td class="td_detail" >
                            <input type="password" class="span2" name="oldPassword" id="oldPassword" value="${oldPassword}" maxlength="10"/>
                        </td>
					</tr>
					<tr>
						<td class="tdl">新密码<span style="color:#FF0000">*</span></td>
						<td class="td_detail" >
                            <input type="password" class="span2" name="newPassword1" id="newPassword1" value="${newPassword1}" maxlength="10"/>
                        </td>
					</tr>
					<tr>
						<td class="tdl">新密码确认<span style="color:#FF0000">*</span></td>
						<td class="td_detail" >
                            <input type="password" class="span2" name="newPassword2" id="newPassword2" value="${newPassword2}" maxlength="10"/>
                        </td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
