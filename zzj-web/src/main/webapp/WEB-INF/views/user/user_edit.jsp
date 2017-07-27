<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title> 
    <!--<link rel="stylesheet" type="text/css" href="../styles/admin-all.css" />-->
    <link rel="stylesheet" type="text/css" href="../styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="../styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="../styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/common.css"/>
    <!--<link rel="stylesheet" type="text/css" href="../styles/base.css"/>-->
    <script type="text/javascript" src="../scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-1.8.22.custom.min.js"></script>
    
    <!--<link rel="stylesheet" type="text/css" href="../styles/formui.css"/>-->
    <script type="text/javascript" src="../scripts/tb.js"></script>
    <script type="text/javascript">
    // 建立数组表示需提示的Input
     var checkInputs =    new Array()
    	// 设置标识符表示是否可提交表单
        $(function () {
            $(".datepicker").datepicker();
        })
     // 设置为同步的请求
        $.ajaxSetup({
    	    async : false  
    	});
        // 返回功能
		function back()
		{
    		// 得到isAdd
    		var isAdd=$("#isAdd").val();
    		if(isAdd != 0){
    			// 判断用户Id是否有输入值
    			if($("#userid").val() != ""){
        			// 得到刚添加的用户Id
        			var userId=$("#userid").val();
        			window.location.href="${pageContext.request.contextPath}/mstuser/list.htm?isAdd="+isAdd+"&userId="+userId+"";
    			}
    			// 若用户Id没有输入之，按照编辑操作处理（按照原条件查询）
    			else{
    				var keyPageNumber=$("#keypageNumber").val();
    				var keyUserId=$("#keyUserId").val();
            		var keyRoleId=$("#keyRoleId").val();
            		var keyUserName=$("#keyUserName").val();
            		var keyPhoneNumber=$("#keyPhoneNumber").val();
            		// 判断是否有查询条件
            		if(keyUserId != "" || keyRoleId != "" ||  keyUserName != "" || keyPhoneNumber !=""){
            			window.location.href="${pageContext.request.contextPath}/mstuser/list.htm?isAdd="+0+"&&keyUserId="+keyUserId+"&keyRoleId="+keyRoleId+"&keyUserName="+keyUserName+"&keyPhoneNumber="+keyPhoneNumber+"&keypageNumber="+keyPageNumber; 
            		}
            		else{
            			window.location.href="${pageContext.request.contextPath}/mstuser/list.htm"; 
        			}
    			}
    		}
    		else{
    			// 得到之前的查询条件
    			var keyPageNumber=$("#keypageNumber").val();
    			var keyUserId=$("#keyUserId").val();
        		var keyRoleId=$("#keyRoleId").val();
        		var keyUserName=$("#keyUserName").val();
        		var keyPhoneNumber=$("#keyPhoneNumber").val();
    			window.location.href="${pageContext.request.contextPath}/mstuser/list.htm?isAdd="+isAdd+"&&keyUserId="+keyUserId+"&keyRoleId="+keyRoleId+"&keyUserName="+keyUserName+"&keyPhoneNumber="+keyPhoneNumber+"&keypageNumber="+keyPageNumber;   			
    		}
			
		}
        // 保存功能
		function save()
		{
			var isAdd=$("#isAdd").val();
			roleIdCheck();
			userIdCheck();
			if(isAdd != 0){checkUserId()};
			
			checkPassword();
			checkPhonenumber();
			 
			if(isAdd != 0){
				if(1 == 1){
					var ck = true;
				}
				else {var ck = false;} 
				var f = checkPassword() && checkPhonenumber() && roleIdCheck() && userIdCheck() && ck;
			}
			else{var f = checkPassword() && checkPhonenumber() && roleIdCheck() && userIdCheck()};
			toFocus();
        	if(f){
        		//$("#editForm").submit();
        		// 利用Ajax将表单信息传回后台查编辑此用户
    			$.post("${pageContext.request.contextPath}/mstuser/editUser.htm",
    					$("#editForm").serialize(),
    					function(user){
    						//如果编辑成功
    				 		if(user == '1'){
    				 			// 显示提示Span
    				 			$("#editSp").removeAttr("style");
    				 		}
    						
    			  		});
        	}
			
		}
        // 修改按钮点击事件
		function uppw()
		{
			$("#editSp").removeAttr("style");
			$("#editSp").attr("style","display:none");
        	var oInput=$("#pw");
			oInput.removeAttr("disabled");
		}
		// 设置为同步的请求
        $.ajaxSetup({
    	    async : false  
    	});
        // 校验主键知否已经存在
        function checkUserId()
        {$("#editSp").removeAttr("style");
		$("#editSp").attr("style","display:none");
        	var flag = userIdCheck();
        	// 得到当前输入userId(用户账号)
        	var userId=$("#userid").val();
        	if(flag){
        		// 利用Ajax将用户账号传回后台查看此用户
    			$.post("${pageContext.request.contextPath}/mstuser/checkUserId.htm",
    					{"userId":userId},
    					function(user){
    						//如果Id已存在
    				 		if(user == 0){
    				 			// 显示提示Span
    				 			$("#userIdSpan").removeAttr("style");
    				 			$("#userid").removeAttr('class');
    				 			$("#userid").addClass('input-error');
    							/* $("#userid").focus(); */
    							checkInputs.push("#userid");	
    				 			// 将标识为设为false
    				 			return false;
    				 		}
    				 		else{
    				 			// 将
    				 			$("#userIdSpan").removeAttr("style");
    				 			$("#userIdSpan").attr("style","display:none;");
    				 			$("#uisp").removeAttr("style");
    		    			    $("#uisp").attr("style","display: none;");
    		    			    $("#userid").removeAttr('class');
    		    			    for(var i = 0; i < checkInputs.length; i++){
  						    	  if(checkInputs[i] == "#userid"){
  						    		  checkInputs.splice(i,1);
  						    	  }
  						      }
    				 			return true;
    				 		}
    						
    			  		});
        	}
        	else{return false;}
        	
        }
        // 角色check
        function roleIdCheck()
        {$("#editSp").removeAttr("style");
		$("#editSp").attr("style","display:none");
        	var roleId = $("#roleId").val();
        	if(roleId == ""){
        		$("#risp").removeAttr("style");
			    $("#risp").attr("style","display: inline;");
			    $("#roleId").removeAttr('class');
	 			$("#roleId").addClass('input-error');
				/* $("#roleId").focus(); */
				checkInputs.push("#roleId")
        		return false;
        	}
        	else{
        		$("#risp").removeAttr("style");
			    $("#risp").attr("style","display: none;");
			    $("#roleId").removeAttr('class');
			    for(var i = 0; i < checkInputs.length; i++){
			    	  if(checkInputs[i] == "#roleId"){
			    		  checkInputs.splice(i,1);
			    	  }
			      }
        		return true;
        	}
        }
     	// 账号check
        function userIdCheck()
        {$("#editSp").removeAttr("style");
		$("#editSp").attr("style","display:none");
        	$("#userIdSpan").removeAttr("style");
 			$("#userIdSpan").attr("style","display:none;");
     		if($("#userid").val() == ""){
     			
     			$("#uisp").removeAttr("style");
			    $("#uisp").attr("style","display: inline;");
			    $("#userid").removeAttr('class');
	 			$("#userid").addClass('input-error');
				/* $("#userid").focus(); */
				checkInputs.push("#userid")
        		return false;
     		}
     		else{
     			$("#uisp").removeAttr("style");
     			$("#userid").removeAttr('class');
			    $("#uisp").attr("style","display: none;");
			    for(var i = 0; i < checkInputs.length; i++){
			    	  if(checkInputs[i] == "#userid"){
			    		  checkInputs.splice(i,1);
			    	  }
			      }
        		return true;
     		}
        }
        // 校验手机号格式
			function checkPhonenumber()
			{$("#editSp").removeAttr("style");
			$("#editSp").attr("style","display:none");
				// 得到手机号
				var tel=$("#ph").val();
				// 判断是否为空
				if(tel != ""){
					// 定义正则表达式
					var check=/^[0-9]*$/;
					var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
					// 判断 如果格式正确
					if (reg.test(tel) && check.test(tel)) {
					      // 将提示信息隐藏
					      $("#tel").removeAttr("style");
					      $("#tel").attr("style","display: none;");
					      $("#ph").removeAttr('class');
					      for(var i = 0; i < checkInputs.length; i++){
					    	  if(checkInputs[i] == "#ph"){
					    		  checkInputs.splice(i,1);
					    	  }
					      }
					      return true;
					 }
					// 如果格式不正确
					else{
						// 显示提示信息
						$("#tel").removeAttr("style");
					    $("#tel").attr("style","display: inline;");
					    $("#ph").removeAttr('class');
			 			$("#ph").addClass('input-error');
						/* $("#ph").focus(); */
						checkInputs.push("#ph")
						return false;
					}
				}
				else{
					return true;
				}
			}
			// 校验密码格式
			function checkPassword()
			{$("#editSp").removeAttr("style");
			$("#editSp").attr("style","display:none");
				if($("#pwSp").val() != $("#pwSp1").val()){
					// 得到密码
					var pw=$("#pwSp").val();
					if(pw.length == 0){
						// 显示提示信息
						$("#pwspNull").removeAttr("style");
						$("#pwspNull").attr("style","display: inline;");
						$("#pwSp").removeAttr('class');
			 			$("#pwSp").addClass('input-error');
			 			/* $("#pwSp").focus(); */
			 			checkInputs.push("#pwSp")
						return false;
						
					}
					else{
						$("#pwspNull").removeAttr("style");
					      $("#pwspNull").attr("style","display: none;");
					      $("#pwSpNull").removeAttr('class');
						// 定义正则表达式
						var check=/^[A-Za-z0-9]*$/;
						// 判断 如果格式正确
						if (check.test(pw) && pw.length > 5 && pw.length < 13) {
						      // 将提示信息隐藏
						      $("#pwsp").removeAttr("style");
						      $("#pwsp").attr("style","display: none;");
						      $("#pwSp").removeAttr('class');
						      for(var i = 0; i < checkInputs.length; i++){
						    	  if(checkInputs[i] == "#pwSp"){
						    		  checkInputs.splice(i,1);
						    	  }
						      }
						      return true;
						 }
						// 如果格式不正确
						else{
							// 显示提示信息
							$("#pwsp").removeAttr("style");
							$("#pwsp").attr("style","display: inline;");
							$("#pwSp").removeAttr('class');
				 			$("#pwSp").addClass('input-error');
				 			/* $("#pwSp").focus(); */
				 			checkInputs.push("#pwSp")
							return false;
						}
					}
					
				}
				else{
					return true;
				}
			}
			// 密码更改事件
			function changePassword()
			{$("#editSp").removeAttr("style");
			$("#editSp").attr("style","display:none");
				// 得到新密码
				var password=$("#pw").val();
				// 将新密码赋予表示密码的Input
				$("#pwSp").val(password);
			}
			// 设置焦点方法
			function toFocus(){
				for(var i = 0; i < checkInputs.length; i++){
					if(checkInputs[i] != "#roleId"){
						$(""+checkInputs[i]+"").focus();
						break;
					}
				}
				
			}
    </script>
</head>
<body>
    <form id="editForm" action="${pageContext.request.contextPath}/mstuser/editUser.htm" method="post" onkeydown="if(event.keyCode==13){save();return false;}">
    <c:if test="${requestScope.user.isAdd != 0}">
    <div class="alert alert-heading"><h4>用户添加</h4></div>
    </c:if>
    <c:if test="${requestScope.user.isAdd == 0}">
    <div class="alert alert-heading"><h4>用户编辑</h4></div>    
    </c:if>
    <div  id="check_message" class='ul-error' style="margin-top:5px;margin-bottom:5px;">
    	<ul><li id="risp" style="display:none;">请输入用户角色。</li></ul>
    		<ul><li id="uisp" style="display:none;">请输入用户账号。</li></ul>
    		<ul><li id="userIdSpan" style="display:none;">此用户账号已经存在。</li></ul>
    		<ul><li id="pwspNull" style="display: none;">请输入用户密码。</li></ul>
    		<ul><li id="pwsp" style="display: none;">用户密码格式错误，请输入6-12位半角字母和数字。</li></ul>
    		<ul><li id="tel" style="display: none;">用户手机格式错误，请输入正确手机号码。</li></ul>
    </div>
    <div  id="edit_message" class='ul-info' style="margin-top:5px;margin-bottom:5px;">
    	<ul><li id="editSp" style="display:none;">保存成功。</li></ul>
    </div>
        <table class="tbform" style="margin:0px; padding:0px;">
		<thead>
            <tr>
                <td colspan="6" class="auto-style2">
                	&nbsp;用户基本信息
                	<input  name="keyPageNumber" id="keypageNumber" value="${keypageNumber}" type="hidden"/>
                	<input  name="keyUserId" id="keyUserId" value="${keyUserId}" type="hidden"/>
                	<input  name="keyRoleId" id="keyRoleId" value="${keyRoleId}" type="hidden"/>
                	<input  name="keyUserName" id="keyUserName" value="${keyUserName}" type="hidden"/>
                	<input  name="keyPhoneNumber" id="keyPhoneNumber" value="${keyPhoneNumber}" type="hidden"/>
                </td>
            </tr>
        </thead>
            <tbody>
                <tr>
                   <td class="tdl">用户角色<span style="color:red">*</span></td>                    
	                    <td class="td_detail" colspan="3">
	                        <select class="input-large" id="roleId" name="roleId" onchange="roleIdCheck();return false;">
	                        <c:if test="${requestScope.user.isAdd != 0}">
	                        <option value="">==请选择==</option> 
	                        </c:if>
	                        
	                        <c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="">
	                    		<c:if test="${mstCodeInfo.codeType=='mstUserRoleType'}">
	                    			<c:if test="${mstCodeInfo.code == user.roleId}">
	                    				<option value="${mstCodeInfo.code}" selected="selected">${mstCodeInfo.codeName}</option>
	                    			</c:if>
	                    			<c:if test="${mstCodeInfo.code != user.roleId}">
	                    				<option value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
	                    			</c:if>
	                    		</c:if>
	                    	</c:forEach>
	                       </select> 
	                       
	                    </td>
	                    <td class="tdl">用户账号
	                    	<c:if test="${requestScope.user.isAdd != 0}">
	                    	<span style="color:red">*</span>
	                    	</c:if>
	                    </td>  
	                    <c:if test="${requestScope.user.isAdd != 0}">                  
	                    <td class="td_detail" colspan="3"   >
	                    <input id="userid" name="userId" />	                    	
	                    </td>
                    </c:if>
                    <c:if test="${requestScope.user.isAdd == 0}">
                    	<td class="td_detail" colspan="3"  >
                    		<input id="userid" class="input-large" value="${user.userId }" disabled="disabled"/>                    		
	                        <input type="hidden" name="userId" value="${user.userId }"/>
	                    	
                    	</td>
                    </c:if>
                </tr>
                
                <tr>
					     
                    <td class="tdl">用户昵称
                    	<c:if test="${requestScope.user.isAdd != 0}">
                    		<input id="isAdd" value="1" type="hidden" name="isAdd"/>
                    		<input id="errorCount" value="${user.errorCount }" type="hidden" name="errorCount"/>
                    		<input id="createId" value="${userInfo.userId}" type="hidden" name="createId"/>
                    	</c:if>
                    	<c:if test="${requestScope.user.isAdd == 0}">
                    		<input id="isAdd" value="0" type="hidden" name="isAdd"/>
                    		<input id="updateId" value="${userInfo.userId}" type="hidden" name="updateId"/>
                    	</c:if>
                    </td>                    
                    <td class="td_detail" colspan="3">
                        <input class="input-large" value="${user.userName }" name="userName"/>
                    </td>
					<td class="tdl">用户密码<span style="color:red">*</span></td>
					<c:if test="${requestScope.user.isAdd == 0}">                    
                    <td class="td_detail" colspan="3">
                        <input id="pw" class="input-large"  disabled="disabled" name="password" />
						<input class="btn" type="button" value="修改" onclick="uppw();return false;"/>
						
						<input type="hidden" id="pwSp" name="password" value="${user.password }"/>
						<input type="hidden" id="pwSp1" name="password" value="${user.password }"/>
                    </td>
                    </c:if>
                    <c:if test="${requestScope.user.isAdd != 0}">                    
                    <td class="td_detail" colspan="3">
                        <input id="pwSp" class="input-large" name="password" />
                        
                    </td>
                    </c:if>
	                    
                </tr>
                <tr>
                    <td class="tdl">用户手机</td>                    
                    <td class="td_detail" colspan="3">
                        <input id="ph" class="input-large" value="${user.phoneNumber }" name="phoneNumber"/>
                        
                    </td>
                </tr>
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button class="btn btn-inverse no_repeat_submit" onclick="back();return false;">返回</button>
                            <button id="saveBt" class="btn btn-inverse no_repeat_submit"
							 onclick="save();return false">保存</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>

