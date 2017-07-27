<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    	.tb th, .tb td {text-align: center;}
    	.td_detail {text-align: left;}
		#preview
	 	{
			float:left;
			position:absolute;
			margin-left:1110px;
		}
		#table
		{
			float:left;
		}
		.t{
			margin-top:10px;
			border-collapse: collapse;
		    border-spacing: 2px;
		    white-space: nowrap;
		    border-radius: 3px;
		    width: 99%;
		    margin-bottom: 8px;
		}
		#bttb{
			border-collapse: collapse;
		    border-spacing: 2px;
		    white-space: nowrap;
		    border-radius: 3px;
		    width: 99%;
		    margin-bottom: 8px;
		}
	</style>
	 <script type="text/javascript">
		$(function () {
            $(".datepicker").datepicker();
            $("#sela").multiselect({
        				noneSelectedText: "==请选择==",
        				checkAllText: "全选",
        				uncheckAllText: '全不选',
        				selectedList:4
   					});
   			});
        $(function () {
            $(".datepicker").datepicker();
        })
        // 设置为同步的请求
	    $.ajaxSetup({
		    async : false  
		});
		function add()
		{
			window.location.href="../Template/推荐添加.html";
		}
		function up()
		{
			$("#savebt").removeAttr("disabled");
			var oCheckBox=$("input:checked").eq(1);

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
			var oCheckBox=$("input:checked").eq(1);

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
		
				var aCheckBox=$(".checkbox:checked");
				if($("input:checked").length != 1 ){
					$("#delbt").removeAttr("disabled");
				}
				else{
					$("#delbt").attr("disabled","disabled");
				}
				if(aCheckBox.length==1)
				{
					$("#upbt").removeAttr("disabled");
					$("#dwbt").removeAttr("disabled");
				}
				else
				{
					$("#upbt").attr("disabled", true);
					$("#dwbt").attr("disabled", true);
				}
			if(otr[0].rowIndex==2)
			{$("#upbt").attr("disabled", true);}
			if(otr[0].rowIndex==$(".checkbox").length+1  )
			{$("#dwbt").attr("disabled", true);}
			if(($('#checkall').is(':checked')))
			{$('#checkall').removeAttr("checked");}
			if($(".checkbox").length-$("input:checked").length==-1)
			{$("#checkall").attr("checked", true)}

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
			
			if($("input:checked").length > 2 || $("input:checked").length == 1){
				$("#delbt").removeAttr("disabled");
				$("#delbt").attr("disabled",!(hasChk));
			}

		}
		function removeCheck()
		{
			var aCheck = $("input:checked");
			for(var i = 1; i < aCheck.length; i++){
				$(aCheck[i]).removeAttr("checked");
			}
		}
		// 查询方法
		function selectBusis()
		{
			removeCheck();
			$("#upbt").attr("disabled", true);
			$("#dwbt").attr("disabled", true);
			$("#editSp").removeAttr("style");
			$("#editSp").attr("style","display:none");
			$("#saveSp").removeAttr("style");
			$("#saveSp").attr("style","display:none");
			// 得到查询条件
			var busi = $("#busi").val();
			$("#busiType").val(busi);
			var recommendKbn = $("input:checked").eq(0).val();
			$("#kbn").val(recommendKbn);
			// 利用Ajax将新sortNo和当前记录主键传回后台进行更新
			$.post("${pageContext.request.contextPath}/recommend/selectRecommends.htm?busi="+busi+"&recommendKbn="+recommendKbn+"",
					"",
					function(result){
				var busis=eval(result);
						// 利用查询结果生成列表
				 		createTable(busis);
			  		});
			
		}
		/**
		*根据查询所得到数据生成table
		*/
		function createTable(data){
			// 得到配置表
			var mstCodeInfos = $("#mstCodeInfos").val();
	        // 抓到table
	        var oTable = document.getElementById("list");
	        var rowNum=oTable.rows.length;
	        for(var rowIndex=2;rowIndex<rowNum;rowIndex++){
	            oTable.deleteRow(rowIndex);
	            rowNum--;
	            rowIndex--;
	        }
	        if(data.length != 0){

				// 将排序和保存排序按钮显示
				$("#bttb").removeAttr("style");
	        	$("#seleSp").removeAttr("style");
	        	$("#seleSp").attr("style","display:none");
	        	// 生成其他tr
	            for( var i = 0 ; i < data.length ; i++){
	            	// 选择 
	            	var operate = "<td  id='topidCd'><input type='checkbox' class='checkbox' onchange='moveCheck();return false;' value='"+data[i]['topicCd']+"'/></td>";

	            	// 机能
	            	// 抓到所有机能的Option
	            	var tbusis = $("#busi option");
	            	// 遍历option
	            	for(var j=0; j<tbusis.length; j++){
	            		if($(tbusis[j]).val() == data[i]['busiType']){
	            			var busi = "<td>"+$(tbusis[j]).text()+"</td>";
	            		}
	            	}
	            	
	            	// ID
	            	var ID = "<td id='ID' style='text-align:left;'>"+data[i]['topicCd']+"</td>" 
	                // 内容名称
	                var topic = "<td id='topicName' style='text-align:left;'>"+data[i]['RecommendInfoTopic']+"</td>";

	            	// 领域 
	            	var field = "";
	            	// 抓到隐藏的领域Input
	            	var techFields = $(".techFieldTypeCode");
	            	var rschFields = $(".rschFieldTypeCode");	            	
	            	if(data[i]['busiType'] != '07') {
	            		// 遍历
		            	for(var k=0; k<data[i]['RecommendInfoResultfields'].length;k++){
		            		for(var x=0; x<techFields.length;x++){
								if(data[i]['RecommendInfoResultfields'][k]['techFieldCd'] == $(techFields[x]).val()){
									for(var y=0; y<rschFields.length; y++){
										if(data[i]['RecommendInfoResultfields'][k]['rschFieldCd'] == $(rschFields[y]).val()){
							            	field = field +$(techFields[x]).next().val()+"-&gt"+$(rschFields[y]).next().val()+"|";
										}
									}
								}
							}
		            	}
		            	var tField = "<td style='text-align:left;'>"+field+"</td>";
	            	}
	            	else{
	            		var tField = "<td>-</td>"
	            	}
	            	
	            	// 费用
	            	if (data[i]['RecommendInfoPayment'] == 1) {
	            		var payMent = "<td>付费</td>";
	            	}
	            	else if (data[i]['RecommendInfoPayment'] == 0){ 
	            		var payMent = "<td>免费</td>";
	            	}
	            	else{
	            		var payMent = "<td>-</td>"
	            	}
	            	
	                // 推荐者
	                var updateName = "<td style='text-align:left;'>"+data[i]['ResultUpdateName']+"</td>"
					// 推荐日
					var updateTime = "<td>"+data[i]['ResultUpdateTime']+"</td>"
	                	            	
	            	$(oTable).append("<tr name='attr'>"+operate+""+busi+""+ID+""+topic+""+tField+""+payMent+""+updateName+""+updateTime+"</tr>");
	            }
 	            //重新加载tb.js使生成的table具有选中高亮和各行换色
                jQuery.getScript("../scripts/tb.js", function(data, status, jqxhr) {});
		}
	        else{
	        	$("#bttb").removeAttr("style");
	        	$("#bttb").attr("style","display:none");
	        	$("#seleSp").removeAttr("style");
	        }
	       
	        }
	        // 批量取消功能
	        function delRecommends(){
	        	$("#upbt").attr("disabled", true);
				$("#dwbt").attr("disabled", true);
	        	// 设置标识位表示取消成功状态
				var isSuss = true;
	        	// 得到要取消的推荐置顶的checkbox
	        	var ids = $(".checkbox:checked");
	        	// 便利
	        	for(var i = 0; i < ids.length; i++){
	        		// 得到当前checkbox代表的主键
	        		var id = ids.eq(i).val();
	        		id = encodeURI(encodeURI(id));
	        		// 得到当前记录机能
	        		var busiType = $("#busiType").val();
	        		// 得到当前要取消的状态
	        		var kbn = $("#kbn").val();
	        		// 得到当前登陆用户
	        		var updateId = $("#updateId").val();
	        		// 调用Ajax改变该条记录deleteFlag属性
	        		$.post("${pageContext.request.contextPath}/recommend/delRecommends.htm?topicCd="+id+"&kbn="+kbn+"&updateId="+updateId+"&busiType="+busiType+"",
							"",
							function(flag){
								if(flag != 1){
									// 保存成功则将该条数据移除显示结果
									isSuss = false;
								}
					  		});
	        	}
	        	if(isSuss){
	        		$("#editSp").removeAttr("style");
	        		$("#saveSp").removeAttr("style");
					$("#saveSp").attr("style","display:none");
					var length = $(".checkbox:checked").length;
					for(var x = 0; x<length; x++){
						$($(".checkbox:checked")[0]).parent().parent().remove();
					}
					var aTr = $("tr[name='attr']");
					 for(var i = 0; i < aTr.length; i++){
						$(aTr[i]).removeAttr("class");
					} 
					//重新加载tb.js使生成的table具有选中高亮和各行换色
		            jQuery.getScript("../scripts/tb.js", function(data, status, jqxhr) {});
					// 保存新显示顺序
		            saveSortNo(1);
				}
	        }
	        // 保存排序方法
	        function saveSortNo(isDele){
	        	$("#savebt").attr("disabled","disabled");
	        	removeCheck();
	        	$("#upbt").attr("disabled", true);
				$("#dwbt").attr("disabled", true);
	        	// 设置标识位表示取消成功状态
				var isSuss = true;
	        	// 得到所有checkbox
	        	var aCheckBox = $(".checkbox");
	        	// 遍历
	        	for(var i = 0; i < aCheckBox.length; i++){
	        		// 得到当前checkbox代表的主键
	        		var id = aCheckBox.eq(i).val();
					id = encodeURI(encodeURI(id));
	        		// 得到当前记录机能
	        		var busiType = $("#busiType").val();
	        		// 得到当前要取消的状态
	        		var kbn = $("#kbn").val();
	        		// 得到当前登陆用户
	        		var updateId = $("#updateId").val();
	        		// 得到当前记录的显示顺序
	        		var sortNo = aCheckBox.eq(i).parent().parent()[0].rowIndex-1;
	        		// 调用Ajax改变该条记录deleteFlag属性
	        		$.post("${pageContext.request.contextPath}/recommend/saveSortNo.htm?topicCd="+id+"&kbn="+kbn+"&updateId="+updateId+"&busiType="+busiType+"&sortNo="+sortNo+"",
							"",
							function(flag){
								if(flag != 1){
									// 保存成功则将该条数据移除显示结果
									isSuss = false;
								}
					  		}); 
	        	}
	        	if(isSuss){
	        		if(isDele == 0){
	        			$("#saveSp").removeAttr("style");
						$("#editSp").removeAttr("style");
						$("#editSp").attr("style","display:none");
	        		}
				} 
	        }
	        function tiaozhuan(){	        	
	        	 window.location.href= document.referrer; 
	        }
    </script>	
</head>
<body>
    <form onkeydown="if(event.keyCode==13){selectBusis();return false;}">
    <div class="alert alert-heading"><h4>推荐置顶一览</h4></div>
    <div  id="edit_message" class='ul-info' style="margin-top:5px;margin-bottom:5px;">
    	<ul><li id="saveSp" style="display:none;">保存成功。</li></ul>
    	<ul><li id="editSp" style="display:none;">取消成功。</li></ul>
    </div>
    <div  id="sele_message" class='ul-info' style="margin-top:5px;margin-bottom:5px;">
    	<ul><li id="seleSp" style="display:none;">没有检索到数据。</li></ul>
    </div>
        <div class="sub-title">
        	<h5>查询条件</h5>
        	<input id="kbn" type="hidden"/>
        	<input id="busiType" type="hidden"/>
        	<input id="updateId" value='${userInfo.userId}' type="hidden"/>
        </div>
        <table class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>                  
                    <td class="tdl">机能<span style="color:red">*</span></td>
                    <td class="td_detail">
                        <select class="input-large" id="busi">
	                        <c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="">
				               <c:if test="${mstCodeInfo.codeType == 'busiType'}">
				               		<option value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
				               </c:if>
				            </c:forEach> 
                        </select>
                    </td>                  
                    <td class="tdl">推荐置顶</td>
                    <td class="td_detail" >
                    	<c:forEach items="${mstCodeInfos}" var="mstCodeInfo" varStatus="s">
			               <c:if test="${mstCodeInfo.codeType == 'recommendKbn'}">
			               		<input type="radio" name="user_state" <c:if test="${mstCodeInfos[s.count-2].codeType != 'recommendKbn'}">checked="checked"</c:if>  value="${mstCodeInfo.code}" />${mstCodeInfo.codeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			               </c:if>
			            </c:forEach> 
                    </td>
                </tr>               
                <tr>
                    <td colspan="8" class="td-title">
                        <div class="div_bottom_right">
                        	<c:forEach items="${techCodeInfos}" var="mstCodeInfo">
								<input type="hidden" class="techFieldTypeCode" value="${mstCodeInfo.code}"/>
				     			<input type="hidden" class="techFieldTypeCodeName" value="${mstCodeInfo.codeName}"/>
        					</c:forEach>
        					<c:forEach items="${rschCodeInfos}" var="mstCodeInfo">
								<input type="hidden" class="rschFieldTypeCode" value="${mstCodeInfo.code}"/>
			        			<input type="hidden" class="rschFieldTypeCodeName" value="${mstCodeInfo.codeName}"/>
        					</c:forEach>
                        	<c:if test="${isIndex == 'false'}">
                        		<button class="btn btn-inverse no_repeat_submit" onclick="tiaozhuan();return false;">返回</button>
                        	</c:if>
                            <button class="btn btn-inverse no_repeat_submit" onclick="selectBusis();return false;">查询</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>        
	    <!--检索一览-->
		<div class="t" id="table" >
			<table class="t" border="0" >
				<tbody>
					<tr align="right">
		                <td colspan="8">
							<div class="sub-title"><h5>查询结果</h5></div>
							<div class="div_bottom_right">
									<button id="delbt" class="btn btn-inverse no_repeat_submit" onclick="delRecommends();return false;" disabled="disabled">批量取消</button>
							</div>
		                </td>
		            </tr>
		         </tbody>
		     </table>
		     <table class="tb" id="list">
		         <tbody>
				 	<tr>
						<th colspan="8" style="text-align: left;">
							<input type="checkbox" id="checkall" onchange="check();return false;"/>
							<span class="red">*</span>
						</th>
					</tr>
		            <tr>
		                <th>选择</th>
		                <th>机能</th>
		                <th>ID</th>
		                <th>内容名称</th>
		                <th>领域</th>		                
		                <th>费用</th>
		                <th>推荐者</th>
						<th>推荐日</th>
		            </tr>
		        </tbody>
		    </table>
		</div>
    </form>
    <table id="bttb" style="display:none">
		<tbody> 
	        <tr>
                    <td colspan="8" class="td-title">
                    	<div class="div_bottom_left">
	                    	<input type="button" id="upbt" class="btn" value="↑" onclick="up();return false;" disabled="disabled"/>
	                    	<input type="button" id="dwbt" class="btn" value="↓" onclick="down();return false;" disabled="disabled"/>
                    	</div>
						<div class="div_bottom_right" style="float:right">
							<button class="btn btn-inverse no_repeat_submit" onclick="saveSortNo(0);return false;" disabled="disabled" id="savebt">排序保存</button>
						</div>						
                    </td>
                </tr>                
		</tbody>
	</table>
</body>
</html>