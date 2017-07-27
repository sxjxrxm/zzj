<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>找专家后台管理系统</title>
	<link rel="shortcut icon" href="favicon.ico" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/base.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/admin-all.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/bootstrap.min.css" />
    <script type="text/javascript" src="<%=path %>/scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery.spritely-0.6.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path %>/styles/login.css" />
    <script type="text/javascript">
    	$(function (){
    		//防止页面连环嵌套
    		if(top.location!==self.location){
   			 top.location.href=self.location.href;
   			}
    	})
		function checkreg() {
            if ($('#uid').val() == "" || $('#pwd').val() == "") { 
            	$('.tip').html('用户名或密码不可为空！');
            	return false;
            }
            if ($('#code').val() == "") { 
            	$('.tip').html('验证码不可为空！');
            	return false;
            }
            return true;
		}
		function chageCode(){
	        $('#codeImage').attr('src','<%=path %>/login/authCode.htm?abc='+Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
	    }
    </script>
</head>
<body>
    <div class="row-fluid">
		<form action="<%=path %>/login/check.htm" id="loginUser" onsubmit="return checkreg()" method="post">
	     <h1>找专家后台管理系统</h1>
	     <table class="tbform" style="margin: 0px; padding: 0px;">
			<tbody>
		        <tr>
		            <td class="tdl"><label>用户名：</label></td>
		            <td class="td_detail"><input type="text" maxlength="20" id="uid" name="userid" value="${userid}"/></td>
		         </tr>
		        <tr>
		            <td class="tdl"><label>密&nbsp;&nbsp;&nbsp;码：</label></td>
		            <td class="td_detail">
		            	<input type="password" maxlength="10" id="pwd" name="password" value="${password}"/>
		      		</td>
		        </tr>
		        <tr>
		            <td class="tdl"><label>验证码：</label></td>
		            <td class="td_detail">
		            	<input type="text" id="code" maxlength="4" class="code" value="" name="authCode"/>
		            	<img src="<%=path %>/login/authCode.htm" id="codeImage" onclick="chageCode()" style="cursor:pointer;" class="imgcode" /><a onclick="chageCode();return false;">换一张</a>	            		        
		            </td>
		        </tr>
			</tbody>
		</table>
		<p class="tip">&nbsp;${lockMsg}${authCodeMsg}</p>
		<hr />
        <input type="submit" value=" 登 录 " class="btn btn-primary btn-large login" />
        </form>
    </div>
</body>
</html>