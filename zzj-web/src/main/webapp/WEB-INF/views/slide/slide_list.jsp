<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title> 
    <link rel="stylesheet" type="text/css" href="../styles/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="../styles/ui-lightness/jquery-ui-1.8.22.custom.css" />
    <link rel="stylesheet" type="text/css" href="../styles/superTables.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/common.css"/>
    <!--<link rel="stylesheet" type="text/css" href="../styles/base.css"/>-->
    <script type="text/javascript" src="../scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-1.8.22.custom.min.js"></script>
    
    <!--<link rel="stylesheet" type="text/css" href="../styles/formui.css"/>-->
    <script type="text/javascript" src="../scripts/tb.js"></script>
    <style>
    	.tb th, .tb td {text-align: center;}
    	.td_detail {text-align: left;}
		#preview
	    {
	       float:left;
	       position:absolute;
		   margin-left:1110px;
	    }
	    .t{
			border-collapse: collapse;
		    border-spacing: 2px;
		    white-space: nowrap;
		    border-radius: 3px;
		    width: 99%;
		    margin-bottom: 8px;
		}
	</style>
    <script type="text/javascript">
        // 设置为同步的请求
	    $.ajaxSetup({
		    async : false  
		});
		function up()
		{
			$("#savebt").removeAttr("disabled");

			var oCheckBox=$("input:checked").eq(0);
			var otr=oCheckBox.parent().parent();
			var ohtml=otr.html();

			var ptr=otr.prev();
			var phtml=ptr.html();
			if(otr[0].rowIndex==3)
			{$("#upbt").attr("disabled", true);}
			if(otr[0].rowIndex>2)
			{
			otr.html(phtml);
			ptr.html(ohtml);
			ptr.children(0).children(0).attr("checked", true);
			otr.children(0).children(0).removeAttr("checked");
			$("#dwbt").removeAttr("disabled");}
		}
		function down()
		{
			$("#savebt").removeAttr("disabled");
			var maxRowIndex=$(".checkbox").length+1;
			var oCheckBox=$("input:checked").eq(0);

			var otr=oCheckBox.parent().parent();
			var ohtml=otr.html();

			var ntr=otr.next();
			var nhtml=ntr.html();
			if(otr[0].rowIndex==maxRowIndex-1)
			{$("#dwbt").attr("disabled", true);}
			if(otr[0].rowIndex<maxRowIndex)
			{
			otr.html(nhtml);
			ntr.html(ohtml);
			ntr.children(0).children(0).attr("checked", true);
			otr.children(0).children(0).removeAttr("checked");
			$("#upbt").removeAttr("disabled");}
		}
		function moveCheck()
		{
			var oCheckBox=$(".checkbox:checked").eq(0);
			var otr=oCheckBox.parent().parent();
			if($("input:checked").length != 0){
				$("#deletebt").removeAttr("disabled");
			}
			else{
				$("#deletebt").attr("disabled", true);
			}
			var aCheckBox=$(".checkbox:checked");
			if (aCheckBox.length==1)
			{
				$("#upbt").removeAttr("disabled");
				$("#dwbt").removeAttr("disabled");
			}
			else
			{
				$("#upbt").attr("disabled", true);
				$("#dwbt").attr("disabled", true);
			}
			if (otr[0].rowIndex==2)
			{
				$("#upbt").attr("disabled", true);
			}
			if (otr[0].rowIndex==$(".checkbox").length+1)
			{
				$("#dwbt").attr("disabled", true);
			}			
			if ($('#checkall').is(':checked'))
			{
				$('#checkall').removeAttr("checked");
			}
			if ($(".checkbox").length-$("input:checked").length==0)
			{
				$("#checkall").attr("checked", true)
			}

		}
		// 编辑:0，新规：1
		function edit(isAdd, slideCd)
		{
			var aCheckBox = $(".checkbox");
			var slideNum = $("#slideNum").val();
			if (aCheckBox.length >= slideNum && isAdd == 1) {
				$("#fullSp").removeAttr("style");
			}
			else {
				$("#fullSp").removeAttr("style");
				$("#fullSp").attr("style","display:none");
				slideCd = $.trim(slideCd);
				window.location.href="${pageContext.request.contextPath}/slide/edit.htm?isAdd="+isAdd+"&slideCd="+slideCd;
			}			
		}
		function check()
		{
			var f = true;
			var hasChk = $('#checkall').is(':checked');
			$(".checkbox").each(function() {  
            $(this).attr("checked", hasChk); 
			}); 
			$("#upbt").attr("disabled", true);
			$("#dwbt").attr("disabled", true);
			
			if(hasChk){
				$("#deletebt").removeAttr("disabled");
			}
			else{
				$("#deletebt").removeAttr("disabled");
				$("#deletebt").attr("disabled","disabled");
			}
		}
		// 保存排序功能
		function saveSortNo(isDele)
		{
			// 将删除成功提示隐藏
			$("#deleteSp").removeAttr("style");
			$("#deleteSp").attr("style","display:none");
			// 将保存成功提示隐藏
			$("#saveSp").removeAttr("style");
			$("#saveSp").attr("style","display:none");
			
			// 得到一组代表排序的Input
			var aInput = $("input[name = topicCd]");
			// 定义变量表示受影响条目数
			var x = 0;			
			// 遍历
			for(var i = 0; i < aInput.length; i ++)
			{
				// 得到当前Input的rowIndex
				var rowIndex = $(aInput[i]).parent().parent()[0].rowIndex;
				// 根据rowIndex计算当前记录的新sortNo
				var sortNo = rowIndex - 1;
				// 得到当前记录主键
				var slideCd = $(aInput[i]).next().next().val();
				var topicCd = $(aInput[i]).val();
				var busiType = $(aInput[i]).next().val();
				// 利用Ajax将新sortNo和当前记录主键传回后台进行更新
				$.post("${pageContext.request.contextPath}/slide/saveSortNo.htm?sortNo="+sortNo+"&slideCd="+slideCd,
						"",
						function(flag) {
							// 如果更新成功
					 		if (flag==1) {					 			
					 			x = x+1;
					 			if (x == aInput.length) {
					 				if(isDele == 0) {
										// 显示提示Span
							 			$("#saveSp").removeAttr("style");
									}
								}
					 		}
				  		});
			}
		}
		// 删除功能
		function deleteSlides()
		{	
			// 将删除成功提示隐藏
			$("#deleteSp").removeAttr("style");
			$("#deleteSp").attr("style","display:none");
			// 将保存成功提示隐藏
			$("#saveSp").removeAttr("style");
			$("#saveSp").attr("style","display:none");
			// 得到选中的checkBox
			var aCheckBox = $("input:checked");
			// 定义变量表示欲删除条目数和受影响条目数
			var x = 0;
			var y = 0;
			// 遍历
			for(var i = 0; i < aCheckBox.length; i++) {
				if ($(aCheckBox[i]).attr("id") != "checkall") {
					y++;
					// 得到当前checkBox代表的主键
					var slideCd = $(aCheckBox[i]).next().next().next();
					var topicCd = $(aCheckBox[i]).next();
					var busiType = $(aCheckBox[i]).next().next();
					// 得到操作人id
					var updateId = $("#updateId").val();
					// 利用Ajax将当前记录主键传回后台,删除对应
					/* $.post("${pageContext.request.contextPath}/slide/deleteSlides.htm?topicCd="+topicCd.val()+"&busiType="+busiType.val()+"&updateId="+updateId+"", */
					$.post("${pageContext.request.contextPath}/slide/deleteSlides.htm?slideCd="+slideCd.val()+"",
							"",
							function(flag) {
								// 如果更新成功
						 		if(flag==1) {					 			
						 			x = x+1;
						 			
						 		}
					  		});
				}
			}
			var sus = false;
			if (x == aCheckBox.length || x == aCheckBox.length-1) {
				for(var i = 0; i < aCheckBox.length; i++) {
					if ($(aCheckBox[i]).attr("id") != "checkall") {
						// 将当前列移除
						$(aCheckBox[i]).next().parent().parent().remove()
					}
					}
			}
			// 保存当前序号
				saveSortNo(1);
			// 显示提示Span
 			$("#deleteSp").removeAttr("style");
 			$("#fullSp").removeAttr("style");
 			$("#fullSp").attr("style","display:none");
 			$("#checkall").removeAttr("checked");
 			$("#deletebt").removeAttr("disabled");
 			$("#deletebt").attr("disabled","disabled")
			var aTr = $("tr");
			for(var i = 0; i < aTr.length; i++) {
				$(aTr[i]).removeAttr("class");
			}
			//重新加载tb.js使生成的table具有选中高亮和各行换色
            jQuery.getScript("../scripts/tb.js", function(data, status, jqxhr) {});			
		}
    </script>
</head>
<body>
    <form>
    <div class="alert alert-heading">
    	<h4>轮播一览</h4>    	
    	<input id="updateId" value='${userInfo.userId}' type='hidden'/>
    	<input id="slideNum" value='${slideNum}' type='hidden'/>
    </div>
    <div id="edit_message" class='ul-info' style="margin-top:5px;margin-bottom:5px;">
    	<ul><li id="saveSp" style="display:none;">保存成功。</li></ul>
    	<ul><li id="deleteSp" style="display:none;">删除成功。</li></ul>
    </div>
    <div id="full_message" class='ul-error' style="margin-top:5px;margin-bottom:5px;">
    	<ul><li id="fullSp" style="display:none;">轮播数已达到上限(${slideNum}个)。</li></ul>
    </div>
	<div id="table">
     <table class="tb" id="list" >
         <tbody>
			 <tr>
				<th colspan="7" style="text-align: left;">
					<input type="checkbox" id="checkall" onchange="check();return false;"/>
				</th>
			 </tr>
             <tr>
                <th>选择</th>
                <th>操作</th>
                <th>轮播主题</th>
                <th>机能</th>
                <th>设置者</th>
				<th>设置日</th>
            </tr>
            <c:forEach items="${slideVOs}" var="slideVO" varStatus="">
            	<tr id="tr">
	            	<!--选择-->
	                <td id="td">
	                	<input id="ckb"  class="checkbox" type="checkbox" onchange="moveCheck();return false;"/>
	                	<input id="topicCd" name="topicCd" value="${slideVO.topicCd }" type="hidden"/>
	                	<input id="busiType" name="busiType" value="${slideVO.busiType }" type="hidden"/>
	                	<input id="slideCd" name="slideCd" value="${slideVO.slideCd }" type="hidden"/>
	                </td>
	                <!--操作-->
	                <td><a href="#" onclick = "edit(0, '${slideVO.slideCd }');return false;">编辑</a><input id="slideCd" value="${slideVO.slideCd }"type="hidden"/></td>
	                <!--主题 -->
	                <td style="text-align: left;">${slideVO.topicName }</td>                  
	                <!--机能 -->
	                <c:if test="${!empty slideVO.busiType}">
		                <c:forEach items="${codeInfos}" var="codeInfo" varStatus="">
		                	<c:if test="${codeInfo.code == slideVO.busiType}">
		                    	<td style="text-align: left;">${codeInfo.codeName }</td>
		               		</c:if>
		            	</c:forEach>
	            	</c:if>
	            	<c:if test="${slideVO.busiType  ==  07 || empty slideVO.busiType}">
	                	<td style="text-align: left;">其他</td>
	                </c:if>
	            	<!--设置者 -->
	            	<td style="text-align: left;">${slideVO.updateName }</td>
					<!--设置日 -->
	                <td>
	                	<fmt:formatDate value="${slideVO.updateTime }" pattern="yyyy-MM-dd" />
	                </td>
                </tr>
	        </c:forEach>
	    </tbody>
	</table>
	<table class="t">
		<tbody>
	        <tr>
                <td colspan="8" class="td-title">
                    	<div class="div_bottom_left">
	                    	<input type="button" id="upbt" class="btn" value="↑" onclick="up();return false;" disabled="disabled"/>
	                    	<input type="button" id="dwbt" class="btn" value="↓" onclick="down();return false;" disabled="disabled"/>
                    	</div>
						<div class="div_bottom_right" style="float:right">
                            <button class="btn btn-inverse no_repeat_submit" onclick="deleteSlides(); return false;" disabled="disabled" id="deletebt">轮播删除</button>
							<button class="btn btn-inverse no_repeat_submit" onclick="edit(1, '');return false;"  id="editbt">轮播添加</button>
							<button class="btn btn-inverse no_repeat_submit" onclick="saveSortNo(0);return false;" disabled="disabled" id="savebt">排序保存</button>
						</div>						
                </td>
            </tr>                
		</tbody>
	</table>
	</div>
   </form>
</body>
</html>
