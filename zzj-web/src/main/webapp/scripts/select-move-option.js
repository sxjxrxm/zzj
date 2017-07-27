/* ==========================================================
 * onclick="javascript:rightMove();" 右移
 * onclick="javascript:leftMove();" 左移
 * onclick="javascript:clean();" 重置
 * 左侧select标签id为"brand_sel"
 * 右侧select标签id为"choose_sel"
 * 左移按钮id为"leftMoveBtn"
 * 右移按钮id为"rightMoveBtn"
 * ========================================================== */

function rightMove() {

    var brand_sel = document.getElementById("brand_sel");
    var choose_sel = document.getElementById("choose_sel");

    var brand_options = brand_sel.options;
    var s = choose_sel.options.length;
    for (var i = 0; i < brand_options.length; i++) {
        var is_selected = brand_options[i].selected;
        if (is_selected) {
            var option = new Option(brand_options[i].text, brand_options[i].value);
            if (!contains(choose_sel, option)) {
                choose_sel.options[s++] = new Option(brand_options[i].text, brand_options[i].value);
            }
        }
    }
    $("#brand_sel>option").each(function() {
        var option = $(this);
        if (option.attr("selected")) {
            option.remove();
        }
    });
    moveBtn.attr("disabled", true);
    sortMe(choose_sel);
}

function leftMove() {
    var brand_sel = document.getElementById("brand_sel");
    var choose_sel = document.getElementById("choose_sel");

    var choose_options = choose_sel.options;
    var s = brand_sel.options.length;
    for (var i = 0; i < choose_options.length; i++) {
        var is_selected = choose_options[i].selected;
        if (is_selected) {
            var option = new Option(choose_options[i].text, choose_options[i].value);
            if (!contains(brand_sel, option)) {
                brand_sel.options[s++] = new Option(choose_options[i].text, choose_options[i].value);
            }
        }
    }
    $("#choose_sel>option").each(function() {
        var option = $(this);
        if (option.attr("selected")) {
            option.remove();
        }
    });
    moveBtn.attr("disabled", true);
    sortMe(brand_sel);
}

function clean() {

    $("#choose_sel>option").each(function() {
        $(this).remove();
    });

}

function contains(obj_sel, option) {
    var options = obj_sel.options;
    for (var i = 0; i < options.length; i++) {
        if (options[i].value == option.value) {
            return true;
        }
    }

    return false;
}

//select排序
function sortMe(oSel) {
    var ln = oSel.options.length;
    var map = new Map();
    
    var arr = new Array(); // 这是关键部分
   // var arrValue = new Array();
    for (var i = 0; i < ln; i++) {
        arr[i] = oSel.options[i].text;
        //arrValue[i] = oSel.options[i].value;
        map.put(arr[i], oSel.options[i].value);
    }
    arr.sort(); // 开始排序
    while (ln--) {
        oSel.options[ln] = null;
    }
    for (i = 0; i < arr.length; i++) {
        oSel.add(new Option(arr[i], map.get(arr[i])));
    }
}

//添加未选中时按钮禁用功能
var moveBtn = null;
function selectChange(obj) {
    var selectedNum = 0;
    var selectedId = $(obj).attr("id");
    var moveBtnID = "#" + selectedId + "Btn";
    moveBtn = $(moveBtnID);
    for (var i = 0; i < obj.options.length; i++) {
        if (obj.options[i].selected) {
            selectedNum++;
        }
    }
    if (selectedNum != 0) {
        moveBtn.removeAttr("disabled");
    }
}

/* ==========================================================

 * ========================================================== */
//费用radio切换
function radioChange() {
    var chargeInput = $("#chargeInput");
    var timeInput = $("#timeInput");
    switch ($("input[name=paymentKbn]:checked").attr("value")) {
    case "0":
        chargeInput.attr("value", "");
        chargeInput.attr("disabled", "disabled");
        timeInput.attr("value", "");
        timeInput.attr("disabled", "disabled");
        break;
    case "1":
        chargeInput.removeAttr("disabled");
        timeInput.removeAttr("disabled");
        break;
    default:
        break;
    }
}
//资讯来源radio切换
function radioChange2() {
    var sourceOwnerInput = $(".expertCdInput");
    switch ($("input[name=sourceType]:checked").attr("value")) {
    case "1":
    	sourceOwnerInput.attr("value", "");
    	sourceOwnerInput.attr("disabled", "disabled");
        break;
    case "2":
    	sourceOwnerInput.removeAttr("disabled");
        break;
    default:
        break;
    }
}
//审核状态radio切换
function radioChange3() {
    var statusSelect = $("#sel");
    var toTopAndRecommend = $(".statusFlag");
    var recommendMsg = $(".statusFlagMsg");
    switch ($("input[name=status]:checked").attr("value")) {
    case "1":
        for (var i = 0; i < statusSelect[0].options.length; i++) {
            statusSelect[0].options[i].selected = false;
        }
        statusSelect.attr("disabled", "disabled");
        $("#refuseMemo2").attr("disabled", "disabled").val("");
        $("input[name=refuseMemoType]").removeAttr("checked").attr("disabled", "disabled");
        
        toTopAndRecommend.removeAttr("disabled");
        break;
    case "2":
    	// 默认选择拒绝理由，可用
        $("#refuseMemoType1").attr("checked", "checked").removeAttr("disabled");
        statusSelect.removeAttr("disabled");
        // 默认自定义拒绝理由，不可用
        $("#refuseMemo2").attr("disabled", "disabled");
        $("#refuseMemoType2").removeAttr("checked").removeAttr("disabled");
        // 推荐置顶
        toTopAndRecommend.attr("disabled", "disabled").removeAttr("checked");
        recommendMsg.attr("disabled", "disabled").val("");
        break;
    default:
        break;
    }
}

var recommendInputMsg = "请输入您的推荐语";
//推荐checkbox切换
function radioChange4() {
  var recommendInput = $("#recommendInput");
  var recommend = $("input[name=recommend]");
  if (recommend.attr("checked")) {
      recommendInput.removeAttr("disabled");
      if (recommendInput.attr("value") == "") {
          recommendInput.attr("value", recommendInputMsg).css("color", "#aaa");
      }
  } else {
  	if (recommendInput.attr("value") == recommendInputMsg) {
  		recommendInput.attr("value", "");
  	}
  	recommendInput.css("color", "#aaa");
      recommendInput.attr("disabled", "disabled");
  }
}

//推荐文本框获得焦点时进行文字输入
function recommendInputCheck() {
  var recommendInput = $("#recommendInput");
  if (recommendInput.attr("value") == recommendInputMsg) {
      recommendInput.attr("value", "");
  }
  recommendInput.css("color", "#000");
}

/* ==========================================================
		一览页面中推荐操作 置顶操作
 * ========================================================== */

//此部分操作应该是根据数据库查询得到结果，不能直接在前台修改
function changeRecommend(obj) {
    var func = obj.attr("href");
    if (obj.text() == "推荐") {
    	obj.text("取消推荐");//.css("background", "yellow");
        func = func.replace("cancleRecommend", "recommend");
        obj.attr("href", func);
    } else {
    	obj.text("推荐");//.css("background", "none");
        func = func.replace("recommend", "cancleRecommend");
        obj.attr("href", func);
    }
}

function changeToTop(obj) {
    var func = obj.attr("href");
    if (obj.text() == "取消置顶") {
    	obj.text("置顶");//.css("background", "yellow");
        func = func.replace("cancleToTop", "toTop");
        obj.attr("href", func);
    } else {
    	obj.text("取消置顶");//.css("background", "none");
        func = func.replace("toTop", "cancleToTop");
        obj.attr("href", func);
    }
}

//Code 操作分类： 1：置顶操作， 2：取消置顶操作， 3：推荐操作， 4：取消推荐操作
function recommend(userId,id) {
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
            	//$("#popupForm").submit();
            	alert($("#recommendMemo").text());
            	//recommendExecute(userId, id, 3);
            }
        }],
        cancel: true
        /*为true等价于function(){}*/
    });

    //alert("推荐");
}
function toTop(userId,id) {
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
    //alert("置顶");
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

function startCourse(id) {
   //待增加
}
/* ==========================================================
		输入价格和时长校验
 * ========================================================== */

function checkPrice() { //小数点后两位有效数字
    var chargeInput = $("#chargeInput");
    var reg = chargeInput.val().match(/\d{0,6}\.?\d{0,2}/);
    var txt = '';
    if (reg != null) {
        txt = reg[0];
    }
    chargeInput.val(txt);
}
function AngelMoney(s){
	   if(/[^0-9\.]/.test(s)) return "";
	   s=s.replace(/^(\d*)$/,"$1.");
	   s=(s+"00").replace(/(\d*\.\d\d)\d*/,"$1");
	   s=s.replace(".",",");
	   var re=/(\d)(\d{3},)/;
	   while(re.test(s))
	           s=s.replace(re,"$1,$2");
	   s=s.replace(/,(\d\d)$/,".$1");
	   return s.replace(/^\./,"0.")
}
	
function checkFreeTime() { //最长2位数的整数
    var timeInput = $("#timeInput");
    var reg = timeInput.val().match(/\d{0,2}/g);
    var txt = '';
    if (reg != null) {
        txt = reg[0];
    }
    timeInput.val(txt);
}

function checkPhoneNum() { //手机号校验
    var phoneInput = $("#phoneInput");
    var reg = phoneInput.val().match(/\d{0,11}/g);
    var txt = '';
    if (reg != null) {
        txt = reg[0];
    }
    phoneInput.val(txt);
}

/* ==========================================================
		图片预览功能
 * ========================================================== */
function preview(file,ImgView) {
	  //判断文件后缀名是否为图片格式
 		if (!/\.(gif|jpg|jpeg|bmp|png)$/i.test(file.value)) {
        del($(file).attr("id"), ImgView);
        alert("图片类型必须是：gif、jpeg、jpg、bmp、png中的一种");
        return false;
    }
    var prevDiv = document.getElementById(ImgView);
    if (file.files && file.files[0]) {
        var reader = new FileReader();
        reader.onload = function(evt) {
            prevDiv.innerHTML = '<img src="' + evt.target.result + '"/>';
        }
        reader.readAsDataURL(file.files[0]);
    } else {
        prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
    }
    $("#imgData").val(this.val());
}

/* ==========================================================
		上传文件名校验及显示
 * ========================================================== */
 
function changeFileName(file,fileType){
	//file为当前input[type=file]标签
	//fileType分为：pdf和video；pdf用于文档资料上传，video用于视频资料上传
	if(fileType != 'pdf' && fileType != 'video'){
		return false;
	}
	if(fileType == 'pdf'){
		//判断文件后缀名是否为pdf格式
 		if (!/\.(pdf)$/i.test(file.value)) {
        alert("文件类型必须是pdf格式");
        file.outerHTML = file.outerHTML;
        return false;
 		}
	}else{
		//判断文件后缀名是否为视频格式
 		if (!/\.(avi|mp4|rm|rmvb|flv)$/i.test(file.value)) {
 			alert("视频类型必须是：avi、mp4、rm、rmvb、flv中的一种");
 			file.outerHTML = file.outerHTML;
 			return false;
 		}
	}
	var name = file.value;
	var index = name.lastIndexOf("\\");
	name = name.substring(index + 1, name.length);
	$("#uploadFileName").attr("value",name);
}