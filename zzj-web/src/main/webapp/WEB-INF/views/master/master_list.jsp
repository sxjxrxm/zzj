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
    <script type="text/javascript" src="/management/scripts/chur-alert.1.0.js"></script>
    <script type="text/javascript" src="/management/scripts/ChurAlert.min.js?skin=blue"></script>    
    <script type="text/javascript" src="/management/scripts/code_edit_pop.js"></script>
	<link rel="stylesheet" type="text/css" href="../styles/chur.css" />
    <style>
    	.tb th, .tb td {text-align: center;}
    	.td_detail {text-align: left;}
		.t{padding-top: 20px;}
		.dela{color:red;}
	</style>
    <script type="text/javascript">
	    // 设置为同步的请求
	    $.ajaxSetup({
		    async : false  
		});
    	// 定义标识位表示是否可提交
    	var flag = true;
    	var isKey = false;
        // 检查主键是否重复功能
        function checkKey()
        { 	
        	// 得到联合主键值
        	var code = $(parent.document.getElementById("code")).val();
        	// 得到codeType和codeTypeName
        	var codeTypeInput = parent.document.getElementById("codeType");
        	var index = codeTypeInput.selectedIndex; // 选中索引
        	var codeType = codeTypeInput.options[index].value; // 选中值
        	if(code != "" && codeType != ""){
        		// AJax查重
            	$.post("${pageContext.request.contextPath}/master/checkKey.htm?code="+code+"&codeType="+codeType+"",
           				"",
           			function(result) {
           			 	if(result == 1) {
           			 		flag = false;
           			 		isKey = false;
           			 		// 提示
           			 		$(parent.document.getElementById("keySpan")).removeAttr("style");
           			 		$(parent.document.getElementById("keySpan")).attr("style","color:red;font-weight:bold;font-size:small;");		
                			$(parent.document.getElementById("idSpan")).removeAttr("style");
                			$(parent.document.getElementById("idSpan")).attr("style","display:none");
                			$(parent.document.getElementById("code")).attr('style',"border-color:#000;background-color:#b94a48;color:#fff;");
                			$(parent.document.getElementById("codeType")).attr('style',"border-color:#000;background-color:#b94a48;color:#fff;");
                			$(parent.document.getElementById("codeType")).focus();
                			$(parent.document.getElementById("code")).focus();           			 			
           			 	}
           		 		else {
           		 			 isKey = true;
           		 			$(parent.document.getElementById("keySpan")).removeAttr("style");
                			$(parent.document.getElementById("keySpan")).attr("style","display:none");
                			$(parent.document.getElementById("code")).removeAttr("style");
                			$(parent.document.getElementById("codeType")).removeAttr("style");               				
           		 		}
           			}); 
        	}       	
        }
        // 编辑页面弹出事件
		function editCodePop(oEdit,isAdd) {
        	if(isAdd == 1) {
        		$("#editTitle").html("项目添加");
        	}
			$("#editSp").removeAttr("style");
			$("#editSp").attr("style","display:none");
			$("#deleteSp").removeAttr("style");
			$("#deleteSp").attr("style","display:none"); 
        	if(oEdit == 'add') {
        		// 将联合主键取消不可编辑
				$("#codeType").removeAttr("disabled");
				$("#code").removeAttr("disabled");
				var codeType = $("#codeTypeSl").val();
				if (codeType != "")
				{
					var aOption = $("#codeType").find("option");
					// 为Select的option 生成选定项
					for(var i=0;i < aOption.length;i++) {
						if ($(aOption[i]).val() == codeType) {
							$(aOption[i]).attr("selected","selected");
						}
					}
				}
        	}
            $.dialog({
                    title: '',
                    content: $('#popupForm').html(),
                    lock: true,
                    width: 300,
                    height: 180,
                    min:false,
                    max:false,
                    cancelVal: '关闭',
                    button: [{
                        id: 'chur',
                        name: '保存',
                        callback: function () {
                        	if(isAdd == 1) {
                        		checkKey();
                            	var isNull = checkNull();
                            	if(isNull) {
                           			if(isKey) {
                              		    editCode(oEdit,isAdd);
                              		}
                           			else{return false;}                            		
                            	}
                            	else{return false;}
                        	}
                        	else{
                            	var isNull = checkNull();
                            	if(isNull){
                              	    editCode(oEdit,isAdd);                            		
                            	}
                            	else{return false;}
                        	}
                        }
                    }],
                    cancel: true
            });
	}
    // 编辑点击事件 0 编辑；1 添加
    function editCodeBt(oEdit,isAdd)
    {
    	// 得到欲编辑code主键
		var codeType=$(oEdit).next().next().val();
		var code=$(oEdit).next().next().next().val();
		// Ajax查询出此条code
		$.post("${pageContext.request.contextPath}/master/editCodeSearch.htm?codeType="+codeType+"&code="+code+"",
				"",
				function(result) {
			
					var resultCode = eval(result)
					// 根据返回结果为弹出form赋值
					// 得到codeType的select的所有option
					var aOption=$("#codeType").find("option");
					// 为Select的option 生成选定项
					for(var i=0;i<aOption.length;i++){
						if($(aOption[i]).val() == resultCode.codeType && $(aOption[i]).text() == resultCode.codeTypeName){
							$(aOption[i]).attr("selected","selected");
						}
					}				
					$("#editTitle").html("项目编辑");
					// 将联合主键设置为不可编辑
					$("#codeType").attr("disabled","disabled");
					$("#code").attr("disabled","disabled");
					// 弹出编辑窗口
					editCodePop(oEdit,isAdd);
					// 为code的Input赋值
					var codeInput = parent.document.getElementById("code");
					$(codeInput).val(resultCode.code);
					// 为codeName的Input赋值
					var codeNameInput = parent.document.getElementById("codeName");
					$(codeNameInput).val(resultCode.codeName);
					// 为DeleteFlag赋值
					$("#deleteFlag").val(resultCode.deleteFlag);					
		  		}); 
    }
    /**
	*根据查询所得到数据生成table
	*/
	function createTable(data){
        // 抓到table
        var oTable = document.getElementById("listTable");
        var rowNum=oTable.rows.length;
        for(var rowIndex=1;rowIndex<rowNum;rowIndex++){
            oTable.deleteRow(rowIndex);
            rowNum--;
            rowIndex--;
        }
        if(data.length != 0){
        	$("#resultSp").removeAttr("style");
        	$("#resultSp").attr("style","display:none");
        	// 生成其他tr
            for( var i = 0 ; i < data.length ; i++){
            	if(data.length > i){
            	// 定义字段分别表示用户锁定状态，删除状态，角色名
            	var unlock="";
            	var deleteFlag="";
            	var codeTypeName="";
            	// 得到项目能否操作状态                  
            	if(data[i]['deleteFlag']==0){operate="<td><a href='#' onclick='editCodeBt(this,0);return false;'>编辑 </a><a id='js' href='#' onclick='deletecode(this);return false;' style='color:red'>删除</a><input type='hidden' value='"+data[i]['codeType']+"'/><input type='hidden' value='"+data[i]['code']+"'/></td>"}
            	else{operate="<td><span style='color:#808080'>编辑 </span><span style='color:#808080'>删除</span></td>"};
            	// 得到删除状态
            	if(data[i]['deleteFlag']==0){deleteFlag="可用"}
            	else{deleteFlag="不可用"};
            	// 得到信息类别名
            	var aOption=$("option");
            	for(var x=0;x<aOption.length;x++)
            		{
            			if(data[i]['codeType']==$(aOption[x]).val())
            				{codeTypeName=$(aOption[x]).text()}
            		}
            	// 得到项目编号
            	var code="<td style='text-align:center;'>"+data[i]['code']+"</td>";
            	
            	$(oTable).append("<tr>"+operate+"<td style='text-align:left;'>"+codeTypeName+"</td>"+code+"<td style='text-align:left;' id='codeName'>"+data[i]['codeName']+"</td><td style='text-align:left;' id='deleteFlag'>"+deleteFlag+"</td></tr>");            
            	}
            }
        }
        else{
        	$("#resultSp").removeAttr("style");
        }
        //重新加载tb.js使生成的table具有选中高亮和各行换色
	    jQuery.getScript("../scripts/tb.js", function(data, status, jqxhr) {});
    }
    // Code编辑功能
    function editCode(oEdit,isAdd)
    {
        	// 得到要传的值
        	// 得到codeType和codeTypeName
        	var codeTypeInput = parent.document.getElementById("codeType");
        	var index = codeTypeInput.selectedIndex; // 选中索引
        	var codeTypeName = codeTypeInput.options[index].text; // 选中文本
        	var codeType = codeTypeInput.options[index].value; // 选中值
        	// 得到code
        	var code = $(parent.document.getElementById("code")).val();
        	// 得到codeName
        	var codeName = $(parent.document.getElementById("codeName")).val();
        		// Ajax将弹出Form属性传到Controller
       		 	$.post("${pageContext.request.contextPath}/master/editCode.htm?isAdd="+isAdd,
       		 		{
       		 			code:code,
       		 			codeName:codeName,
       		 		    codeType:codeType,
       		 			codeTypeName:codeTypeName,
            	    },
       				function(result) {
       			 		if(result == 1){       			 		
       			 			// 将isAdd重置
       			 			$("#isAdd").val(1);
       			 			// gen
       			 			if(isAdd == 0) {
       			 			// 改变编辑行显示效果
           			 			$(oEdit).parent().parent().children("#codeName").html(codeName);
       			 			}
       			 			else {
       			 				selectmstCode(codeType);
       			 				// 将类别select选中
       			 				var aOption = $("option");
       			 				for(var i = 0; i < aOption.length; i++) {
       			 					$(aOption[i]).removeAttr("selected");
       			 					if ($(aOption[i]).val() == codeType) {
       			 						$(aOption[i]).attr("selected","selected");
       			 					}
       			 				}
       			 			}
           			 		$("#editSp").removeAttr("style");
       			 		return true;
       			 		}
       		 			else{
       		 				return false;
       		 			}
       		  		});       		 
    }  		
    function checkNull(){
    	// 得到code
    	var code = $(parent.document.getElementById("code")).val();
    	// 得到codeName
    	var codeName = $(parent.document.getElementById("codeName")).val();
    	// 得到codeType和codeTypeName
    	var codeTypeInput = parent.document.getElementById("codeType");
    	var index = codeTypeInput.selectedIndex; // 选中索引
    	var codeTypeName = codeTypeInput.options[index].text; // 选中文本
    	var codeType = codeTypeInput.options[index].value; // 选中值
    	// 判断是否有未填
    	if(code != "" && codeType != "" && codeName != ""){
    		$(parent.document.getElementById("idSpan")).removeAttr("style");
       		$(parent.document.getElementById("nameSpan")).removeAttr("style");
       		$(parent.document.getElementById("idSpan")).attr("style","display:none");
       		$(parent.document.getElementById("nameSpan")).attr("style","display:none");
       		if($(parent.document.getElementById("code")).attr('style') != "border-color:#000;background-color:#b94a48;color:#fff;"){
				$(parent.document.getElementById("code")).removeAttr("style");
			}
       		$(parent.document.getElementById("codeName")).removeAttr("style");
    		flag = true;
    		return true;}
    	else{
    		if(code == ""){
    			$(parent.document.getElementById("idSpan")).removeAttr("style");
    			$(parent.document.getElementById("idSpan")).attr("style","color:red;font-weight:bold;font-size:small;");
    			$(parent.document.getElementById("code")).attr('style',"border-color:#000;background-color:#b94a48;color:#fff;");
    			$(parent.document.getElementById("code")).focus();
    		}
    		else{
    			$(parent.document.getElementById("idSpan")).removeAttr("style");
    			$(parent.document.getElementById("idSpan")).attr("style","display:none");
    			if($(parent.document.getElementById("code")).attr('style') != "border-color:#000;background-color:#b94a48;color:#fff;"){
    				$(parent.document.getElementById("code")).removeAttr("style");
    			}
    			
    		}
    		if(codeName == ""){
    			$(parent.document.getElementById("nameSpan")).removeAttr("style");
    			$(parent.document.getElementById("nameSpan")).attr("style","color:red;font-weight:bold;font-size:small;");
    			$(parent.document.getElementById("codeName")).attr('style',"border-color:#000;background-color:#b94a48;color:#fff;");
    			if(!$(parent.document.getElementById("code")).is(":focus")){
    				$(parent.document.getElementById("codeName")).focus();
    			}
    			
    		}
    		else{
    			$(parent.document.getElementById("nameSpan")).removeAttr("style");
    			$(parent.document.getElementById("nameSpan")).attr("style","display:none");
    			$(parent.document.getElementById("codeName")).removeAttr("style");
    		}
    		flag = false;
    		return false;
    	} 
    }
    // 弹出窗code改变事件
    function codeChange(oInput){
		$("#codeIp").val($(oInput).val());
    }
    function codeTypeChange(oSelect){
    	$("#codeTypeIp").val($(oSelect).val());
    	$("#codeTypeNameIp").val($(oSelect).find("option:selected").text());
    	checkKey();
    }
    function codeNameChange(oInput){
    	$("#codeNameIp").val($(oInput).val());
    }
    // 删除点击事件
	function deletecode(oA)
	{
		$("#editSp").removeAttr("style");
		$("#editSp").attr("style","display:none"); 
		$('body').alert({ type: 'info', buttons: [{ id: 'yes', name: '确定', callback: function () {  deleteCode(oA)} }, { id: 'no', name: '取消', callback: function () { } }] }) 
	}
    // 删除功能
	function deleteCode(oA)
	{
		// 得到欲删除code主键
		var codeType=$(oA).next().val();
		var code=$(oA).next().next().val();
		 // 利用Ajax将用户账号传回后台删除此项目
		$.post("${pageContext.request.contextPath}/master/deleteCode.htm?codeType="+codeType+"&code="+code+"",
				"",
				function(result){
					// 如果更新成功
			 		if(result==1){
			 			// 将该条数据删除状态显示效果更改
			 			$(oA).parent().parent().children("#deleteFlag").html("不可用");
			 			// 将当前Td内容改变
			 			$(oA).parent().html("<span style='color:#808080'>编辑 </span><span style='color:#808080'>删除</span>");
			 			// 显示提示
			 			$("#deleteSp").removeAttr("style");
			 		}
		  		});  
	}
    function selectmstCode(type){
        	$("#editSp").removeAttr("style");
			$("#editSp").attr("style","display:none");
			$("#deleteSp").removeAttr("style");
			$("#deleteSp").attr("style","display:none"); 
        	if(type == ''){
        		// 得到要查询的codeType
            	var codeType=$("#codeTypeSl").val();
        	}
        	else{codeType = type};
        	// Ajax查询
        	$.post("${pageContext.request.contextPath}/master/searchCodes.htm",
        			{"codeType":codeType},
					function(codes){
				 		var codeList=eval(codes);
				 		// 利用查询结果生成列表
				 		createTable(codeList);
			  		}); 
    }        
    </script>
</head>
<body>
    <form>
    <div class="alert alert-heading"><h4>基础信息一览</h4></div>
    <div  id="edit_message" class='ul-info' style="margin-top:5px;margin-bottom:5px;">
    	<ul>
    		<li id="editSp" style="display:none;">保存成功。</li>
    		<li id="deleteSp" style="display:none;">删除成功。</li>
    	</ul>
    </div>
    <div  id="edit_erro" class='ul-info' style="margin-top:5px;margin-bottom:5px;">
    	<ul>
    		<li id="resultSp" style="display:none;">没有检索到数据。</li>
    	</ul>
    </div>
        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>
                   
                    <td class="tdl" style="width:110px">类别</td>
                    <td class="td_detail">
                        <select id="codeTypeSl" class="input-large" onchange="javascript:selectmstCode('');return false;">
                        	<option value="">==请选择==</option>                        	 
                        	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="s">
	                        	<c:if test="${mstCodeInfos[s.count].codeType != mstCodeInfos[s.count-1].codeType}">
	                        		<option value="${mstCodeInfo.codeType}">${mstCodeInfo.codeTypeName}</option>
	                        	</c:if>
	                    	</c:forEach>
                        </select>
                    </td>
                </tr>
            </tbody>
        </table>
        
    <!--检索一览-->
	<div class="t">
					<div class="sub-title">
						<h5>项目一览</h5>
                        <input id="isAdd" value="1" type="hidden"/>
                        <input id="deleteFlag"  value="0" type="hidden"/>
					</div>
                        <div class="div_bottom_right">
                            <button class="btn btn-inverse no_repeat_submit" onclick="editCodePop('add', 1);return false;">项目添加</button>
                        </div>
                    <!--</td>
                </tr>
    </table>  -->
</div>
     <table  class="tb" id="listTable">
         <tbody>
             <tr>
                <th>操作</th>
                <th>类别</th>
                <th>项目编码</th>
                <th>项目名称</th>
                <th>状态</th>
            </tr>
            </tbody>
    </table>
    </form>
    
	 <div id="popupFormDiv" style="display:none;" >
    <form id="popupForm" action="" method="post">


		<div class="alert alert-heading" id = "editTitle"><h4>项目添加</h4></div>
		<div  id="check_message" class='ul-error' style="margin-top:5px;margin-bottom:5px;overflow-y:auto;max-height:74px;margin-bottom:5px;margin-top:-5px;padding:0px;padding-left:15px;">
    		<ul><li id="keySpan" style="display:none;color:red;font-weight:bold;font-size:small;">此分类下此编码已存在。</li></ul>
    		<ul><li id="idSpan" style="display:none;color:red;font-weight:bold;font-size:small;">请输入项目编码。</li></ul>
    		<ul><li id="nameSpan" style="display:none;color:red;font-weight:bold;font-size:small;">请输入项目名称。</li></ul>
    </div>
        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>
                   <td class="tdl">类别</td>
                    <td class="td_detail">
                        <select class="input-large"  id="codeType" onchange="codeTypeChange(this);return false;">                   	 
                        	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="s">
	                        	<c:if test="${mstCodeInfos[s.count].codeType != mstCodeInfos[s.count-1].codeType}">
	                        		<option value="${mstCodeInfo.codeType}">${mstCodeInfo.codeTypeName}</option>
	                        	</c:if>
	                    	</c:forEach>
	                    </select>
                    </td>
                </tr>
                <tr>
                    <td class="tdl">项目编码<span style="color:red">*</span></td>                    
                    <td class="td_detail" colspan="3">
                        <input class="input-large"  id="code" onchange="codeChange(this);return false;" onblur="window.parent.frames.checkKey();return false;"/>
                    </td>
                </tr>
				<tr>
					<td class="tdl">项目名称<span style="color:red">*</span></td>                    
                    <td class="td_detail" colspan="3">
                        <input class="input-large"  id="codeName" onchange="codeNameChange(this);return false;"/>
                    </td>
					
				</tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>