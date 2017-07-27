 		// 设置为同步的请求
        $.ajaxSetup({
		    async : false  
		});
	    // Code 操作分类： 1：置顶操作， 2：取消置顶操作， 3：推荐操作， 4：取消推荐操作
	    function recommend(userId, id) {
	    	// 清空错误信息
			$("#message").text("");
			$("#message").removeAttr("style");
			$("#message").attr("style", "display: none;");
 	         $.dialog({
	            title: '添加推荐语',
	            content: $('#popupForm').html(),
	            lock: true,
	            width: 630,
	            height: 230,
	            min: false,
	            max: false,
	            cancelVal: '关闭',
	            button: [{
	                id: 'chur',
	                name: '保存',
	                callback: function() {
	                	recommendExecute(userId, id, 3);
	                	if ($('#message', parent.document).text() != "")
	                	{
	                		return false;	                		
	                	}	                	
	                }
	            }],
	            cancel: true
	        }); 
	    }
	    function toTop(userId, id) {
	        $('body').alert({
	            type: 'top',
	            buttons: [{
	                id: 'yes',
	                name: '确定',
	                callback: function() {recommendExecute(userId, id, 1);}
	            },
	            {
	                id: 'no',
	                name: '取消',
	                callback: function() {}
	            }]
	        })
	    }
	    function cancleRecommend(userId,id) {
	        $('body').alert({
	            type: 'cancle-recommend',
	            buttons: [{
	                id: 'yes',
	                name: '确定',
	                callback: function() {recommendExecute(userId, id, 4);}
	            },
	            {
	                id: 'no',
	                name: '取消',
	                callback: function() {}
	            }]
	        })

	    }
	    function cancleToTop(userId,id) {
	        $('body').alert({
	            type: 'cancle-top',
	            buttons: [{
	                id: 'yes',
	                name: '确定',
	                callback: function() {recommendExecute(userId, id, 2);}
	            },
	            {
	                id: 'no',
	                name: '取消',
	                callback: function() {}
	            }]
	        })
	    }
        // 推荐状态变更
        function changeRecommend(code, obj) {
            var func = obj.attr("href");
            if (code == 3) {
            	obj.text("取消推荐");//.css("background", "yellow");
                func = func.replace("recommend", "cancleRecommend");
                obj.attr("href", func);
            }
            if (code == 4) {
            	obj.text("推荐");//.css("background", "none");
                func = func.replace("cancleRecommend", "recommend");
                obj.attr("href", func);
            }
        }
        // 置顶状态变更
        function changeToTop(code, obj) {
            var func = obj.attr("href");
            if (code == 1) 
            {
            	obj.text("取消置顶");//.css("background", "none");
                func = func.replace("toTop", "cancleToTop");
                obj.attr("href", func);
            }
            if (code == 2) 
            {
            	obj.text("置顶");//.css("background", "yellow");
                func = func.replace("cancleToTop", "toTop");
                obj.attr("href", func);
            } 
        }