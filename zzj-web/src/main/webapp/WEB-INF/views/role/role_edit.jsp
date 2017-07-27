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
	<link rel="stylesheet" type="text/css" href="../styles/chur.css"/>
    <script type="text/javascript" src="../scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="../scripts/tb.js"></script>
	<script type="text/javascript" src="../scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="/management/scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="/management/scripts/ChurAlert.min.js?skin=blue"></script>    
    <script type="text/javascript" src="/management/scripts/code_edit_pop.js"></script>
<script type="text/javascript">
        $(function () {
            $(".datepicker").datepicker();
        })
        // 设置为同步的请求
    $.ajaxSetup({
	    async : false  
	});
    
		function save()
		{$("#editSp").removeAttr("style");
		$("#editSp").attr("style","display:none");
			// 得到要修改的角色Id
			var roleId = $("#roleId").val();
			// 得到当前选中Input
			var aCheckBox = $(".checkbox:checked");
			// 得到有页面选中的父节点
			// 定义数组表示父节点
			var parentId = new Array();
			for(var i = 0; i < aCheckBox.length; i++){
				var f = true;
				for(var j = i+1; j < aCheckBox.length+1; j++){
					var x = $(aCheckBox[i]).parent().prev().children().children().val();
					var y = $(aCheckBox[j]).parent().prev().children().children().val();
					if(x == y){
						f = false;
					}
				}
				if(f == true){
					parentId.push(x);
				}
			}
			// 定义变量表示成功条数
			var x = 0;
			// 先调用Ajax删除所有记录
			$.post("${pageContext.request.contextPath}/role/deleteRoles.htm?roleId="+roleId+"",
							"",
							function(flag){
					  		});
			// 遍历aCheckbox
			for(var i = 0; i < aCheckBox.length; i++){
				// 得到当前checkbox代表的页面Id
				var screenId = $(aCheckBox[i]).val();
				// 调用Ajax
				if(screenId != ''){
					 $.post("${pageContext.request.contextPath}/role/saveRoles.htm?roleId="+roleId+"&screenId="+screenId+"",
							"",
							function(flag){
								if(flag == 1){									
									x++;
								}
					  		});
				}
			}
			for(var i = 0; i < parentId.length; i++){
				// 得到当前checkbox代表的页面Id
				var screenId = parentId[i];
				// 调用Ajax
				if(screenId != ''){
					 $.post("${pageContext.request.contextPath}/role/saveRoles.htm?roleId="+roleId+"&screenId="+screenId+"",
							"",
							function(flag){
								if(flag == 1){									
									x++;
								}
					  		});
				}
			}
			if(x == aCheckBox.length + parentId.length){
				$("#editSp").removeAttr("style");
			}
			
		}
		function sele() {
			$("#editSp").removeAttr("style");
			$("#editSp").attr("style","display:none");
			// 得到当前选择的角色Id
			var roleId=$("#RoleId").val();
			$("#roleId").val(roleId);
				$("#resultDiv").removeAttr("style");
				// aJax查询对应的
				$.post("${pageContext.request.contextPath}/role/editSearch.htm?roleId="+roleId+"",
						"",
						function(result){
							var permissions = eval(result);
							// 用查询结果数据生成表单
							createTable(permissions);
				  		});
		}
		/**
		*根据查询所得到数据生成table
		*/
		function createTable(data){
			// 得到全部页面
			var allScreens = data['allScreens'];
			// 得到格式化的全部后台界面代码
			var fmtAllScreens = data['fmtAllScreens'];
			// 得到页面拥有权限的页面合集
			var trueScreens = data['trueScreens'];
	         // 抓到table
	        var oTable = document.getElementById("list");
	        var rowNum=oTable.rows.length;
	        for(var rowIndex=0;rowIndex<rowNum;rowIndex++){
	            oTable.deleteRow(rowIndex);
	            rowNum--;
	            rowIndex--;
	        }
	        // 遍历fmtAllScreens
	        for(var i = 0;i < fmtAllScreens.length; i ++){
	        	// 生成tr
	        	var oTr="";
	        	var oTd="";
	        	var oInput="";
				var count = 0;
	        	for(var j = 1; j < fmtAllScreens[i].length; j++){
    				// 定义选中状态,选中个数
	        		// 遍历所有页面
	        		for(var k = 0;k < allScreens.length; k++){
	        			if(allScreens[k]['screenId'] == fmtAllScreens[i][j]){	        				
	        				// 判断选中
    						var checked = "";
	        				// 遍历用户拥有权限的页面集合
	        				for(var n = 0; n < trueScreens.length; n++){
	        					if(trueScreens[n]["screenId"] == fmtAllScreens[i][j]){ checked = "checked='checked'";count++};
	        					if(trueScreens[n]["screenId"] != fmtAllScreens[i][j]){flag = false};
		        				
	        				}
	        				oInput = oInput+"<input type='checkbox' class='checkbox' onchange='checkchange(this);return false;' value='"+fmtAllScreens[i][j]+"'"+checked+"/>"+allScreens[k]['screenName']+"&nbsp;&nbsp;";
	        			}
		        	}
	        		for(var k = 0;k < allScreens.length; k++){

        			if(allScreens[k]['screenId'] == fmtAllScreens[i][0]){
        				oTd="<td>"+oInput+"</td>";
        				var checkAll = "";
        				if(count == fmtAllScreens[i].length-1){checkAll = "checked='checked'"}
			        	oTr="<td style='width:155px;'><div style='padding-left:30px;'><input type='checkbox' id='checkall' onchange='check(this);return false;' value='"+allScreens[k]['screenId']+"'"+checkAll+"/>"+allScreens[k]['screenName']+"</div>"+oTd+"</td>";
	        		
        			}
	        		}
	        	}
	        	$(oTable).append("<tr>"+oTr+"</tr>");
	        }
	        $(oTable).append("<tr><td colspan='8' class='td-title'><div class='div_bottom_right'><button id='savebt' onclick='save();return false;' class='btn btn-inverse no_repeat_submit'>保存</button></div></td></tr>")
	     }
				
		
		function check(oCheckAll)
		{
			var hasChk = $(oCheckAll).is(':checked');
			$(oCheckAll).parent().parent().next().children("input").each(function() {  
            $(this).attr("checked", hasChk); 
			}); 

		}
		function checkchange(oCheckBox)
		{
			if($(oCheckBox).parent().prev().children().children("input").is(':checked')){
				$(oCheckBox).parent().prev().children().children("input").removeAttr("checked");
			}
			if($(oCheckBox).parent().children("input").length == $(oCheckBox).parent().children("input:checked").length)
			{$(oCheckBox).parent().prev().children().children("input").attr("checked", true);}
		}
		function add()
		{
			$("#savebt").removeAttr("disabled");
		}
		function hideResult(){
			$("#resultDiv").removeAttr("style");
			$("#resultDiv").attr("style","display:none");
		}
    </script>
</head>
<body>
    <form onkeydown="if(event.keyCode==13){sele();return false;}">
    		<div class="alert alert-heading"><h4>页面权限管理</h4></div>
    		<div  id="edit_message" class='ul-info' style="margin-top:5px;margin-bottom:5px;">
    	<ul><li id="editSp" style="display:none;">保存成功</li></ul>
    </div>
			<div class="sub-title">
				<h5>查询条件</h5>
				<input id="roleId" type="hidden"/>
			</div>
			<table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>                 
                    <td class="tdl">角色</td>
                    <td class="td_detail">
                        <select class="input-large"  name="roleId" id="RoleId" onchange = "hideResult();return false;">                     	 
                        	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="s">
	                        	<c:if test="${mstCodeInfo.codeType=='mstUserRoleType'}">
	                        		<option value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
	                        	</c:if>
	                    	</c:forEach>
                        </select>
                    </td>                    
                </tr>
				<tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                            <button id="selbt" class="btn btn-inverse no_repeat_submit"  onclick="sele();return false;">查询</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <div id="resultDiv">
        <div class="sub-title" style="display:none" ><h5>查询结果</h5></div>
	       <table class="tbform" style="margin:0px; padding:0px;" id='list'>
	           <tbody>
				</tbody>
	       </table>
		</div>
    </form>
</body>
</html>
