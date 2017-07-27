    	// 定义标识位表示是否可提交
    	var flag = true;
        $(function () {
            $(".datepicker").datepicker();
        })
        // 检查主键是否重复功能
        function checkKey()
        {
        	// 得到联合主键值
        	var code = $("#codeIp").val();
        	var codeType = $("#codeTypeIp").val();
        	// AJax查重
        	$.post("${pageContext.request.contextPath}/master/checkKey.htm?code="+code+"&codeType="+codeType+"",
       				"",
       				function(result){
       			 		if(result == 1){
       			 			flag = false;
       			 			// 提示
       			 			$("#keySpan").removeAttr("style");
       			 			$("#keySpan").attr("style","color:red");
       			 			
       			 		}
       		 			else{
       		 				flag = true;
       		 			$("#keySpan").removeAttr("style");
   			 			$("#keySpan").attr("style","display:none");
       		 			}
       		  		}); 
        }
        
        // 编辑页面弹出事件
		function editCodePop(oEdit) {
                $.dialog({
                    title: '',
                    content: $('#popupForm').html(),
                    lock: true,
                    width: 300,
                    height: 150,
                    min:false,
                    max:false,
                    cancelVal: '关闭',
                    button: [{
                        id: 'chur',
                        name: '保存',
                        callback: function () {
                        	editCode(oEdit);
                        }
                    }],
                    cancel: true /*为true等价于function(){}*/
                });
				}
    //编辑点击事件 
    function editCodeBt(oEdit)
    {
    	// 得到欲编辑code主键
		var codeType=$(oEdit).next().next().val();
		var code=$(oEdit).next().next().next().val();
		// Ajax查询出此条code
		$.post("${pageContext.request.contextPath}/master/editCodeSearch.htm?codeType="+codeType+"&code="+code+"",
				"",
				function(result){
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
					// 弹出编辑窗口
					editCodePop(oEdit);
					// 为code的Input赋值
					$("#code").val(resultCode.code);
					$("#codeIp").val(resultCode.code);
					// 为codeName的Input赋值
					$("#codeName").val(resultCode.codeName);
					$("#codeNameIp").val(resultCode.codeName);
					// 为isAdd的Input赋值
					$("#isAdd").val(0);
					$("#deleteFlag").val(resultCode.deleteFlag);
					$("#codeTypeIp").val(resultCode.codeType);
					$("#codeTypeNameIp").val(resultCode.codeTypeName);
					// 将联合主键设置为不可编辑
					$("#codeType").attr("disabled","disabled");
					$("#code").attr("disabled","disabled");
					
					
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
        	// 生成其他tr
            for( var i = 0 ; i < 10 ; i++){
            	if(data.length > i){
            	// 定义字段分别表示用户锁定状态，删除状态，角色名
            	var unlock="";
            	var deleteFlag="";
            	var codeTypeName="";
            	// 得到项目能否操作状态                  
            	if(data[i]['deleteFlag']==0){operate="<td><a href='#' onclick='editCodeBt(this);return false;'>编辑 </a><a id='js' href='#' onclick='deletecode(this);return false;' style='color:red'>删除</a><input type='hidden' value='"+data[i]['codeType']+"'/><input type='hidden' value='"+data[i]['code']+"'/></td>"}
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
            	/* else{
            		$(oTable).append("<tr><td></td><td></td><td></td><td></td><td></td></tr>"); 
            	} */
            }
            // 生成翻页
            $(oTable).append("<tr><th colspan='100' id='pagelink'>当前第5页/共55页&nbsp;&nbsp;共650条记录&nbsp;&nbsp;<a>首页</a>&nbsp;<a>上一页</a>&nbsp;<a class='current'>1</a>&nbsp;<a>2</a>&nbsp;<a>3</a>&nbsp;<a>4</a>&nbsp;<a>5</a>&nbsp;<a class='navpage'>...</a>&nbsp;<a>55</a>&nbsp;<a>下一页</a>&nbsp;<a>尾页</a></th></tr>");
        }
        else{
        	$("#resultSp").removeAttr("style");
        	$("#resultSp").attr("style","color:#ffcc33;display:inline;");
        }
       
        }
    // Code编辑功能
    function editCode(oEdit)
    {
    	if(flag){
        	// 得到要传的值
        	var isAdd = $("#isAdd").val();
        	var code = $("#codeIp").val();
        	var codeType = $("#codeTypeIp").val();
        	var codeName = $("#codeNameIp").val();
        	var codeTypeName = $("#codeTypeNameIp").val();
        	var deleteFlag = $("#deleteFlag").val();
        	// 判断是否有未填
        	if(code != "" && codeType != "" && codeName != ""){
        		// Ajax将弹出Form属性传到Controller
       		 	$.post("${pageContext.request.contextPath}/master/editCode.htm?code="+code+"&isAdd="+isAdd+"&codeType="+codeType+"&codeName="+codeName+"&codeTypeName="+codeTypeName+"&deleteFlag="+deleteFlag+"",
       				"",
       				function(result){
       			 		if(result == 1){
       			 			// gen
       			 			if(isAdd == 0){
       			 			// 改变编辑行显示效果
           			 			$(oEdit).parent().parent().children("#codeName").html(codeName);
       			 			}
       			 			else{
       			 				selectmstCode();
       			 			}
       			 		return true;
       			 		}
       		 			else{
       		 				return false;
       		 			}
       		  		}); 
        	}
        	else{
        		alert("请填写全部")
        	}
    		
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
				function(flag){
					// 如果更新成功
			 		if(flag==1){
			 			// 将该条数据删除状态显示效果更改
			 			$(oA).parent().parent().children("#deleteFlag").html("不可用");
			 			// 将当前Td内容改变
			 			$(oA).parent().html("<span style='color:#808080'>编辑 </span><span style='color:#808080'>删除</span>");
			 		}
		  		});  
	}
        function selectmstCode(){
        	// 得到要查询的codeType
        	var codeType=$("#codeTypeSl").val();
        	// Ajax查询
        	$.post("${pageContext.request.contextPath}/master/searchCodes.htm",
        			{"codeType":codeType},
					function(codes){
				 		var codeList=eval(codes);
				 		// 利用查询结果生成列表
				 		createTable(codeList);
			  		}); 
        }
        
