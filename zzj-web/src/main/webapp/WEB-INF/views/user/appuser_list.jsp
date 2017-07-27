<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title> 
    <link rel="stylesheet" type="text/css" href="../styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="../styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="../styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/common.css"/>
	<link rel="stylesheet" type="text/css" href="../styles/chur.css"/>
    <script type="text/javascript" src="../scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-1.8.22.custom.min.js"></script>   
    <script type="text/javascript" src="../scripts/tb.js"></script>
	<script type="text/javascript" src="../scripts/chur-alert.1.0.js"></script>
	<link rel="stylesheet" type="text/css" href="../styles/chur.css" />
	<style>
    	.tb th, .tb td {text-align: center;}
    	.td_detail {text-align: left;}
	</style>
	<script type="text/javascript">
	//定义标识位表示输入电话号码格式是否正确
	var flag=true;
	// 校验手机号格式
	function checkPhonenumber()
	{
		// 得到手机号
		var tel=$("#ph").val();
		// 定义正则表达式
		var check=/^[0-9]*$/;
		var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
		// 判断 如果格式正确
		if ((reg.test(tel) && check.test(tel)) || tel == "") {
		      // 改变标识位
		      flag = true;
		      // 将提示信息隐藏
		   // 将提示信息隐藏
		      $("#check_message").removeAttr("style");
		      $("#check_message").attr("style","display: none;");
		      $("#ph").attr("class","input-large");
		 }
		// 如果格式不正确
		else{
			// 改变标识位
			flag = false;
			// 显示提示信息
			$("#check_message").removeAttr("style");
			$("#check_message").removeAttr("class");
			$("#check_message").html("<ul><li>请输入正确的手机号码。</li></ul>");
			$("#check_message").attr("class","ul-error")
		    $("#check_message").attr("style","margin-top:5px;margin-bottom:5px;");
			$("#ph").addClass('input-error');
			$("#ph").focus();
		}
	}

    // 解锁button点击事件
	function deleteuser(oA)
	{
		 $('body').alert({ type: 'info', buttons: [{ id: 'yes', name: '确定', callback: function () {  deleteUser(oA)} }, { id: 'no', name: '取消', callback: function () { } }] }) 
	}
    // 删除功能
	function deleteUser(oA)
	{
		$("#deleteSp").removeAttr("style");
		$("#deleteSp").attr("style","display:none");
		// 得到欲删除用户账号
		var userId=$(oA).next().val();
		 // 利用Ajax将用户账号传回后台删除此用户
		$.post("${pageContext.request.contextPath}/appuser/deleteUser.htm",
				{"userId":userId},
				function(flag) {
					// 如果更新成功
			 		if(flag==1) {
			 			// 将该条数据删除状态显示效果更改
			 			$(oA).parent().parent().children("#deleteFlag").html("不可用");
			 			// 将当前Td内容改变
			 			$(oA).parent().html("<span style='color:#808080'>删除</span>");
			 			// 提示
			 			$("#check_message").removeAttr("style");
		            	$("#check_message").removeAttr("class");
						$("#check_message").html("<ul><li>删除成功。</li></ul>");
						$("#check_message").attr("class","ul-info");
					    $("#check_message").attr("style","margin-top:5px;margin-bottom:5px;");
			 		}
		  		});  
	}
	// 用户查询功能
	function select(pageNo) {
		$("#deleteSp").removeAttr("style");
		$("#deleteSp").attr("style","display:none");
		if(flag){
			// Ajax查询
			 $.post("${pageContext.request.contextPath}/appuser/searchUsers.htm?pageNumber="+pageNo,
					$("#userForm").serialize(),
					function(users){
				 		var userList=eval(users);
				 		// 利用查询结果生成列表
				 		createTable(userList);
			  		}); 
		}	
	}
	/**
	*根据查询所得到数据生成table
	*/
	function createTable(userList){
		var data = userList['items'];
        // 抓到table
        var oTable = document.getElementById("listTable");
        var rowNum=oTable.rows.length;
        for(var rowIndex=1;rowIndex<rowNum;rowIndex++){
            oTable.deleteRow(rowIndex);
            rowNum--;
            rowIndex--;
        }
        if(data != undefined){
        	// 生成其他tr
            for( var i = 0 ; i < data.length ; i++){
            	if(data.length > i){
            	// 定义字段分别表示用户锁定状态，删除状态，角色名
            	var unlock="";
            	var deleteFlag="";
            	var roleName="";
            	// 得到用户能否操作状态                  
            	if(data[i]['deleteFlag']==0){operate="<td><a id='js' href='#' onclick='deleteuser(this);return false;' style='color:red'>删除</a><input type='hidden' value='"+data[i]['userId']+"'/></td>"}
            	else{operate="<td><span style='color:#808080'>删除</span></td>"};
            	// 得到删除状态
            	if(data[i]['deleteFlag']==0){deleteFlag="可用"}
            	else{deleteFlag="不可用"};
            	// 得到用户角色名
            	var aOption=$("option");
            	for(var x=0;x<aOption.length;x++)
            		{
            			if(data[i]['roleId']==$(aOption[x]).val())
            				{roleName=$(aOption[x]).text()}
            		}
            	// 得到用户昵称
            	var userName="<td style='text-align:left;'>"+data[i]['userName']+"</td>";
            	
            	$(oTable).append("<tr>"+operate+"<td style='text-align:left;'>"+roleName+"</td><td style='text-align:left;'>"+data[i]['userId']+"</td style='text-align:left;'>"+userName+"<td>"+data[i]['phoneNumber']+"</td><td style='text-align:left;' id='deleteFlag'>"+deleteFlag+"</td></tr>");            
            	}
            }
            // 生成翻页
            $(oTable).append("<tr><input type='hidden' value='"+userList['pageNo']+"' name='pageNo' id='pageNo'><input type='hidden' value='"+userList['totalPageCount']+"' name='totalPageCount' id='totalPageCount'><th colspan='100' id='pagelink'>当前第"+userList['pageNo']+"页/共"+userList['totalPageCount']+"页&nbsp;&nbsp;共"+userList['totalCount']+"条记录&nbsp;&nbsp;</tr>");
            creteFy(userList['pageNo'],userList['totalPageCount']);
            //重新加载tb.js使生成的table具有选中高亮和各行换色
            jQuery.getScript("../scripts/tb.js", function(data, status, jqxhr) {});
            $("#check_message").removeAttr("style");
		      $("#check_message").attr("style","display: none;");
        }
        else{
        	$("#check_message").removeAttr("style");
        	$("#check_message").removeAttr("class");
			$("#check_message").html("<ul><li>没有检索到数据。</li></ul>");
			$("#check_message").attr("class","ul-warning");
		    $("#check_message").attr("style","margin-top:5px;margin-bottom:5px;");
        }
       
        }
	function creteFy(pageNo,totalPageCount){
		/* initPage();// 初始化参数 */
		if(pageNo > 1){
			$("#pagelink").append("<a onclick='select(1);return false;'>首页</a>&nbsp;");
			$("#pagelink").append("<a onclick='select("+(pageNo-1)+");return false;'>上一页</a>&nbsp;");
		}
		for (var i = 1; i <= totalPageCount; i++) {
			if (i == pageNo) {
				$("#pagelink").append("<a class='current'>"+i+"</a>&nbsp;");
			}else{
				$("#pagelink").append("<a onclick='select("+i+");return false;'>"+i+"</a>&nbsp;");
			}
		}
		if(pageNo < totalPageCount){
			$("#pagelink").append("<a onclick='select("+(pageNo-1+2)+");return false;'>下一页</a>&nbsp;");
			$("#pagelink").append("<a onclick='select("+totalPageCount+");return false;'>尾页</a>");
		}
	}
</script>
</head>
<body>
    <form id="userForm" onkeydown="if(event.keyCode==13){select(1);return false;}">
	    <div class="alert alert-heading"><h4>前台用户一览</h4></div>
	    <div  id="check_message" style="margin-top:5px;margin-bottom:5px;display:none;"></div>
        <div class="sub-title"><h5>查询条件</h5></div>
        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>                   
                    <td class="tdl">用户角色</td>
                    <td class="td_detail">
                        <select class="input-large" name="roleId">
                        	<option value="">==请选择==</option>                        	 
                        <c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="">
	                    		<c:if test="${mstCodeInfo.codeType == 'appUserRoleType'}">
			                        	<option value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
	                    		</c:if>
	                    	</c:forEach>
                        </select>
                    </td>
					<td class="tdl">用户手机</td>
                    <td class="td_detail">
                        <input class="input-large" name="phoneNumber" id="ph" onblur="checkPhonenumber();return false;"/>
                        <span id="tel" style="display: none;">*请输入正确的手机号码。</span>
                    </td>
                    <td class="tdl">用户昵称</td>
                    <td class="td_detail">
                        <input class="input-large" name="userName"/>
                    </td>
                </tr>               
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button class="btn btn-inverse no_repeat_submit" onclick="select(1);return false;">查询</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>        
    <!--检索一览-->
    <div class="sub-title"><h5>查询结果</h5></div>
      <table class="tb" id="listTable">
         <tbody>
             <tr>
                <th>操作</th>
                <th>用户角色</th>
                <th>用户账号</th>
                <th>用户昵称</th>
                <th>用户手机</th>
                <th>状态</th>
            </tr>
            </tbody>
    </table>
    </form>
</body>
</html>