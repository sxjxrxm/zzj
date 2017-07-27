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
    <link rel="stylesheet" type="text/css" href="../styles/select-checkbox/jquery.multiselect.css"/>
    <script type="text/javascript" src="../scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="../scripts/select-checkbox/jquery.multiselect.js"></script>   
    <script type="text/javascript" src="../scripts/tb.js"></script>
    <script type="text/javascript" src="../scripts/page.js"></script>
    <style>
    	.tb th, .tb td {text-align: center;}
    	.td_detail {text-align: left;}
		.t{
			padding-top: 20px;
			margin-top:10px;
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
            $("#sela").multiselect({
				noneSelectedText: "==请选择==",
				checkAllText: "全选",
				uncheckAllText: '全不选',
				selectedList:4
			}); 
            var key = '<%=request.getAttribute("keySlideResultVOs")%>';
            if(key != 'null' && key != ''){
            	createTable(eval(key));
            }
            
          	//重新加载tb.js使生成的table具有选中高亮和各行换色
  	 		jQuery.getScript("../scripts/tb.js", function(data, status, jqxhr) {});          
        })       
        // 保存,返回
		function save(back)
		{
			// 得到选中的checkBox
			var aCheckBox = $("input:checked");
			// 得到选中的checkBox代表的主题名称，机能分类，主题名称
			var zzjBusyType = $(aCheckBox[0]).parent().next().children("input").val();
			var zzjTopicCd = $(aCheckBox[0]).val();
			var zzjTopicName = $(aCheckBox[0]).parent().next().next().text();
			
			if (back != "back")
			{
				$("#zzjBusyType").val(zzjBusyType);
				$("#zzjTopicCd").val(zzjTopicCd);
				$("#zzjTopicName").val(zzjTopicName);
			}
			document.forms[0].action = "${pageContext.request.contextPath}/slide/edit.htm?";
	  		document.forms[0].submit();
		}
		// 查询功能
		function goPage(pageNumber) {
			// 得到机能
			var busi = $("#busi").val();
			// 得到主题
			var topic = $("#topic").val();
			// 得到领域
			var techField = $("#sela").val();
			// 将查询条件保存
			$("#keyBusi").val(busi);
			$("#keyTopic").val(topic);
			var techFields = "";
			if (techField != null) {
				for (var x=0; x<techField.length; x++) {
					techFields = techFields +"<input id='keyTechField' type='hidden' value='"+techField[x]+"'/>"
				}
			}
			$("#hiddens").append(techFields)
			$("#keyTechField").val(techField);
			// Ajax查询
			$.post("${pageContext.request.contextPath}/slide/selectBusis.htm?topic="+topic+"&techFields="+techField+"&busi="+busi+"&pageNumber="+pageNumber,
					"",
					function(result) {
						var busis=eval(result);
						// 利用查询结果生成列表
				 		createTable(busis);
			  		});			
		}
		// 根据查询所得到数据生成table
		function createTable(result) {
			var data = result['items'];
	        // 抓到table
	        var oTable = document.getElementById("list");
	        var rowNum=oTable.rows.length;
	        for (var rowIndex=1; rowIndex<rowNum; rowIndex++) {
	            oTable.deleteRow(rowIndex);
	            rowNum--;
	            rowIndex--;
	        }
	        if (data.length != 0) {
	        	// 生成其他tr
	            for (var i = 0 ; i < data.length ; i++){
	            	// 选择 
	            	var operate = "<td  id='topidCd'><input type='radio' name='radio' onchange='checkChange();return false;' value='"+data[i]['SlideResultId']+"'/><input type='hidden' value='"+data[i]['SlideResultBigIcon']+"'/><input type='hidden' value='"+data[i]['SlideResultBusi']+"'/></td>";
	            	// 机能
	            	// 抓到所有机能的Option
	            	var tbusis = $("#busi option");
	            	// 遍历option
	            	for (var j=0; j<tbusis.length; j++) {
	            		if($(tbusis[j]).val() == data[i]['SlideResultBusi']){
	            			var busi = "<td>"+$(tbusis[j]).text()+"<input value="+data[i]['SlideResultBusi']+" type='hidden'></td>";
	            		}
	            	}	
	            	// 轮播主题 
	            	var topic = "<td id='topicName' style='text-align:left;'>"+data[i]['SlideResultTopic']+"</td>";
	            	// 领域 
	            	var field = "";
	            	// 抓到隐藏的领域Input
	            	var techFields = $(".techFieldTypeCode");
	            	var rschFields = $(".rschFieldTypeCode");	            	
	            	if(data[i]['SlideResultBusi'] != '07'){
	            		// 遍历
		            	for (var k=0; k<data[i]['SlideResultfields'].length; k++) {
		            		for (var x=0; x<techFields.length;x++) {
								if(data[i]['SlideResultfields'][k]['techFieldCd'] == $(techFields[x]).val()){
									if (data[i]['SlideResultfields'][k]['rschFieldCd'] == ' ')
									{
										field = field +$(techFields[x]).next().val()+"|";
									}
									else
									{
										for(var y=0; y<rschFields.length; y++) {
											if (data[i]['SlideResultfields'][k]['rschFieldCd'] == $(rschFields[y]).val()) {
								            	field = field +$(techFields[x]).next().val()+"-&gt"+$(rschFields[y]).next().val()+"|";
											}
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
	            	if (data[i]['SlideResultPayment'] == 1) {
	            		var payMent = "<td>付费</td>";
	            	}
	            	else if (data[i]['SlideResultPayment'] == 0) {
	            		var payMent = "<td>免费</td>";
	            	}
	            	else{
	            		var payMent = "<td>-</td>"
	            	}
	            	$(oTable).append("<tr>"+operate+""+busi+""+topic+""+tField+""+payMent+"</tr>");
	            }
	            // 生成翻页
				$(oTable).append("<tr><input type='hidden' value='"+result['pageNo']+"' name='pageNo' id='pageNo'><input type='hidden' value='"+result['totalPageCount']+"' name='totalPageCount' id='totalPageCount'><th colspan='100' id='pagelink'>当前第"+result['pageNo']+"页/共"+result['totalPageCount']+"页&nbsp;&nbsp;共"+result['totalCount']+"条记录&nbsp;&nbsp;</tr>");
				pagging(result['pageNo'],result['totalPageCount']);
				
	  	 		jQuery.getScript("../scripts/tb.js", function(data, status, jqxhr) {});	        
	        }
	        else {
	        	$("#resultSp").removeAttr("style");
	        	$("#resultSp").attr("style","color:#ffcc33;display:inline;");
	        }	       
	     }
		
		// checkBox选中改变事件
		function checkChange(){
			// 得到选择的checkbox
			var aCheckBox = $("input:checked");
			if(aCheckBox.length != 1){
				$("#seleBt").removeAttr("disabled");
				$("#seleBt").attr("disabled","disabled");
			}
			else{
				$("#seleBt").removeAttr("disabled");
			}
		}
    </script>
</head>
<body>
<form id="form" action="" method="post">
    <div id="hiddens" class="alert alert-heading">
    	<h4>资料选择</h4>
    </div>
    <div class="sub-title">
        <h5>查询条件</h5>
    </div>
	<div>
        <table  class="tbform" style="margin:0px; padding:0px;">
            <tbody>
                <tr>                  
                    <td class="tdl">机能<span style="color:red">*</span></td>
                    <td class="td_detail">
                        <select class="input-large" id="busi">
                        	<c:forEach items="${codeInfos}" var="codeInfo" varStatus="">
                        		<c:if test="${codeInfo.code == keyBusi}">
                        			<option id="" value="${codeInfo.code }" selected="selected">${codeInfo.codeName }</option>
                        		</c:if>
                        		<c:if test="${codeInfo.code != keyBusi}">
                        			<option id="" value="${codeInfo.code }">${codeInfo.codeName }</option>
                        		</c:if>	               				
	            			</c:forEach>
                        </select>
                    </td>
                   <td class="tdl">主题</td>                    
                    <td class="td_detail" colspan="3">
                        <input class="input-large" id="topic" value="${keyTopic}"/>
                    </td>                   
                </tr>
                <tr>
				<td class="tdl">领域</td>
                    <td class="td_detail">						
						<select class="input-large" multiple="multiple" id="sela">  
	                        <c:forEach items="${techCodeInfos}" var="mstCodeInfo" varStatus="">
								<c:set var="keytechfield" value="${keyTechField}" scope="page"></c:set>
	                    		<c:set var="keyT" value="${fn:join(keytechfield, ',')} "  scope="page"></c:set>
                    			<c:set var="fieldCode" value="${mstCodeInfo.code}" scope="page"></c:set>
	                    		<option <c:if test="${fn:contains(keyT, fieldCode)}">selected</c:if> value="${mstCodeInfo.code}">${mstCodeInfo.codeName}</option>
	                    	</c:forEach>
                        </select>
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
        					<button class="btn btn-inverse no_repeat_submit" onclick="save('back');return false;">返回</button>
                            <button class="btn btn-inverse no_repeat_submit" onclick="save('');return false;" id="seleBt" disabled="disabled">选择</button>
                            <button class="btn btn-inverse no_repeat_submit" onclick="goPage(1);return false;">查询</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
      </div>
    <!--检索一览-->
	<div class="t">
	<table class="t" border="0" >
		<tbody>
			<tr  align="right">
            	<td colspan="8">
					<div class="sub-title"><h5>查询结果</h5></div>
            	</td>
        	</tr>
    	</tbody>
    </table>
    </div>
     <table class="tb" id="list">
         <tbody>
             <tr>
				<th class="thCss">选择</th>
                <th class="thCss">机能</th>
                <th class="thCss">主题</th>
                <th class="thCss">领域</th>
                <th class="thCss">费用</th>
             </tr>
         </tbody>
    </table>
    <input name="isAdd" id="isAdd" value='${isAdd}' type="hidden"/>
    <input name="topicName" id="topicName" value='${topicName}' type="hidden"/>
    <input name="bigIcon" id="bigIcon" value='${bigIcon}' type="hidden"/>
    <input name="slideCd" id="slideCd" value='${slideCd}' type="hidden"/>
    <input name="tZzj" id="tZzj" value='${tZzj}' type="hidden"/>
    <input name="fZzj" id="fZzj" value='${fZzj}' type="hidden"/>
    <input name="busiType" id="busiType" value='${busiType}' type="hidden"/>
    <input name="topicCd" id="topicCd" value='${topicCd}' type="hidden"/>
    <input name="isZzj" id="isZzj" value='${isZzj}' type="hidden"/>
    <input name="zzjBusyType" id="zzjBusyType" type="hidden"/>
    <input name="zzjTopicCd" id="zzjTopicCd" type="hidden"/>
    <input name="zzjTopicName" id="zzjTopicName" type="hidden"/>
</form>
</body>
</html>
