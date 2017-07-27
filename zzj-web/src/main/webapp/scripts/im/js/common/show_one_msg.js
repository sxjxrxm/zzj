//聊天页面增加一条消息
function addMsg(msg, prepend) {
    var isSelfSend, fromAccount, fromAccountNick, fromAccountImage, sessType, subType;

    //获取会话类型，目前只支持群聊
    //webim.SESSION_TYPE.GROUP-群聊，
    //webim.SESSION_TYPE.C2C-私聊，
    sessType = msg.getSession().type();

    isSelfSend = msg.getIsSend(); //消息是否为自己发的
    fromAccount = msg.getFromAccount();
    if (!fromAccount) {
        return;
    }
    if (isSelfSend) { //如果是自己发的消息
        if (loginInfo.identifierNick) {
            fromAccountNick = loginInfo.identifierNick;
        } else {
            fromAccountNick = fromAccount;
        }
        
        //获取头像
        /*if(loginInfo.headurl){
            fromAccountImage=loginInfo.headurl;
        }else{
            fromAccountImage= friendHeadUrl;
        }*/
    } else { //如果别人发的消息
        var info = memberMap[fromAccount];
        if (info) {
            fromAccountNick = info;
        } else {
        	// 新增的成员需要重新获取用户名
        	//发送请求获取用户昵称
    	    var tag_list = ["Tag_Profile_IM_Nick" //昵称
    	    ];
    	    var options1 = {
    	        'To_Account': [fromAccount],
    	        'TagList': tag_list
    	    };
    	    var nick;

    	    webim.getProfilePortrait(options1,
    	    function(resp) {
    	    	for (var k in resp.UserProfileItem) {
    	    		nick = resp.UserProfileItem[k].ProfileItem[0].Value;
    	    		if (nick) {
    	    			memberMap[fromAccount]=nick;
    	    			fromAccountNick = nick;
    	    		} else {
    	    			fromAccountNick = fromAccount;
    	    		}
    	    		
    	    	}
    	    },
    	    function(err) {
    	        alert(err.ErrorInfo);
    	    });
        }

        //获取头像
        //if(info && info.image){
        //    fromAccountImage=info.image;
        //}else{
        //    fromAccountImage= friendHeadUrl;
        //}
    }
    addMsgHtml(msg, isSelfSend, fromAccountNick, prepend);
}

function addMsgHtml(msg, isSelfSend, fromAccountNick, prepend) {
    var onemsg = document.createElement("div");
    if (msg.sending) {
        onemsg.id = "id_" + msg.random;
        //发送中
        var spinner = document.createElement("div");
        spinner.className = "spinner";
        spinner.innerHTML = '<div class="bounce1"></div> <div class="bounce2"></div> <div class="bounce3"></div>';
        onemsg.appendChild(spinner);
    } else {
        $("#id_" + msg.random).remove();
    }

    onemsg.className = "onemsg";
    var msghead = document.createElement("p");
    var msgbody = document.createElement("p");
    var msgPre = document.createElement("pre");
    msghead.className = "msghead";
    msgbody.className = "msgbody";

    //如果是发给自己的消息
    if (!isSelfSend) msghead.style.color = "blue";
    //昵称  消息时间
    soundTimeStamp = msg.getTime();
    msghead.innerHTML = webim.Tool.formatText2Html(fromAccountNick + "&nbsp;&nbsp;" + webim.Tool.formatTimeStamp(msg.getTime()));

    //解析消息
    //获取消息子类型
    //会话类型为群聊时，子类型为：webim.GROUP_MSG_SUB_TYPE
    //会话类型为私聊时，子类型为：webim.C2C_MSG_SUB_TYPE
    var subType = msg.getSubType();

    switch (subType) {

    case webim.GROUP_MSG_SUB_TYPE.COMMON:
        //群普通消息
        msgPre.innerHTML = convertMsgtoHtml(msg);
        break;
    case webim.GROUP_MSG_SUB_TYPE.REDPACKET:
        //群红包消息
        msgPre.innerHTML = "[群红包消息]" + convertMsgtoHtml(msg);
        break;
    case webim.GROUP_MSG_SUB_TYPE.LOVEMSG:
        //群点赞消息
        //业务自己可以增加逻辑，比如展示点赞动画效果
        msgPre.innerHTML = "[群点赞消息]" + convertMsgtoHtml(msg);
        //展示点赞动画
        //showLoveMsgAnimation();
        break;
    case webim.GROUP_MSG_SUB_TYPE.TIP:
        //群提示消息
        msgPre.innerHTML = "[群提示消息]" + convertMsgtoHtml(msg);
        break;
    }

    msgbody.appendChild(msgPre);

    onemsg.appendChild(msghead);
    onemsg.appendChild(msgbody);
    //消息列表
    var msgflow = document.getElementsByClassName("msgflow")[0];
    if (prepend) {
        //300ms后,等待图片加载完，滚动条自动滚动到底部
        msgflow.insertBefore(onemsg, msgflow.firstChild);
        if (msgflow.scrollTop == 0) {
            setTimeout(function() {
                msgflow.scrollTop = 0;
            },
            300);
        }
    } else {
        msgflow.appendChild(onemsg);
        //300ms后,等待图片加载完，滚动条自动滚动到底部
        setTimeout(function() {
            msgflow.scrollTop = msgflow.scrollHeight;
        },
        300);
    }
}



//聊天页面增加一条消息***********
function HaddMsg(msg) {
	var isSelfSend, fromAccount, fromAccountNick;
	
	isSelfSend = false;//消息是否为自己发的
	
	fromAccount = msg.From_Account;
	if (!fromAccount) {
		return;
	}
	
	var userId = $("#userId").val();
	if (userId == fromAccount) {
		isSelfSend = true;
	}
	
//	//发送请求获取用户昵称
//    var tag_list = ["Tag_Profile_IM_Nick", //昵称
//    //             "Tag_Profile_IM_Image"//头像
//    ];
//    var options = {
//        'To_Account': [fromAccount],
//        'TagList': tag_list
//    };
//    var nick;
//    webim.getProfilePortrait(options,
//    function(resp) {
//        nick = resp.UserProfileItem[0].ProfileItem[0].Value;
	// 获取昵称工程交给后台完成
        if (msg.From_Account_Nick) {
            fromAccountNick = msg.From_Account_Nick;
        } else {
            fromAccountNick = fromAccount;
        }
        HaddMsgHtml(msg, isSelfSend, fromAccountNick);
//    },
//    function(err) {
//        alert(err.ErrorInfo);
//    });
}

function HaddMsgHtml(msg, isSelfSend, fromAccountNick) {
	var onemsg = document.createElement("div");
	
	onemsg.className = "onemsg";
	var msghead = document.createElement("p");
	var msgbody = document.createElement("p");
	var msgPre = document.createElement("pre");
	msghead.className = "msghead";
	msgbody.className = "msgbody";
	
	
	//如果是发给自己的消息
	if (!isSelfSend)
		msghead.style.color = "blue";
	//昵称  消息时间
	soundTimeStamp = msg.MsgTimeStamp;
	msghead.innerHTML = webim.Tool.formatText2Html(fromAccountNick + "&nbsp;&nbsp;" + webim.Tool.formatTimeStamp(msg.MsgTimeStamp));
	
	//解析消息
	//群普通消息
	msgPre.innerHTML = HconvertMsgtoHtml(msg);
	//msgPre.appendChild(HconvertMsgtoHtml(msg));
		
	
	msgbody.appendChild(msgPre);
	
	onemsg.appendChild(msghead);
	onemsg.appendChild(msgbody);
	//消息列表
	var msgflow = document.getElementsByClassName("msgflow")[0];

	//300ms后,等待图片加载完，滚动条自动滚动到底部
	msgflow.insertBefore(onemsg, msgflow.firstChild);
	if(msgflow.scrollTop == 0 ){
		setTimeout(function () {
			msgflow.scrollTop = 0;
		}, 300);
	}
}


//把消息转换成Html
function convertMsgtoHtml(msg) {
    var html = "", elems, elem, type, content;
    elems = msg.getElems();//获取消息包含的元素数组
    var count = elems.length;
    for (var i = 0; i < count; i++) {
        elem = elems[i];
        type = elem.getType();//获取元素类型
        content = elem.getContent();//获取元素对象
        switch (type) {
            case webim.MSG_ELEMENT_TYPE.TEXT:
                html += convertTextMsgToHtml(content);
                //转义，防XSS
                //html = webim.Tool.formatText2Html(html);
                break;
            case webim.MSG_ELEMENT_TYPE.FACE:
                html += convertFaceMsgToHtml(content);
                break;
            case webim.MSG_ELEMENT_TYPE.IMAGE:
               /* if (i <= count - 2) {
                    var customMsgElem = elems[i + 1];//获取保存图片名称的自定义消息elem
                    var imgName = customMsgElem.getContent().getData();//业务可以自定义保存字段，demo中采用data字段保存图片文件名
                    html += convertImageMsgToHtml(content, imgName);
                    i++;//下标向后移一位
                } else {*/
                    html += convertImageMsgToHtml(content);
                //}
                break;
            case webim.MSG_ELEMENT_TYPE.SOUND:
                html += convertSoundMsgToHtml(content);
                break;
            /*case webim.MSG_ELEMENT_TYPE.FILE:
                html += convertFileMsgToHtml(content);
                break;
            case webim.MSG_ELEMENT_TYPE.LOCATION:
                html += convertLocationMsgToHtml(content);
                break;
            case webim.MSG_ELEMENT_TYPE.CUSTOM:
                html += convertCustomMsgToHtml(content);
                //转义，防XSS
                html = webim.Tool.formatText2Html(html);
                break;
            case webim.MSG_ELEMENT_TYPE.GROUP_TIP:
                html += convertGroupTipMsgToHtml(content);
                //转义，防XSS
                html = webim.Tool.formatText2Html(html);
                break;*/
            default:
                webim.Log.error('未知消息元素类型: elemType=' + type);
                break;
        }
    }
    return html;
}
//把消息转换成Html************
function HconvertMsgtoHtml(msg) {
	var html = "", elems, elem, type, content;
	elems = msg.MsgBody;//获取消息包含的元素数组
	var count = elems.length;
	for (var i = 0; i < count; i++) {
		elem = elems[i];
		type = elem.MsgType;//获取元素类型
		content = elem.MsgContent;//获取元素对象
		switch (type) {
		case 'TIMTextElem':
			html += HconvertTextMsgToHtml(content);
			break;
		case 'TIMFaceElem':
			html += HconvertFaceMsgToHtml(content);
			break;
		case 'TIMImageElem':
			html += HconvertImageMsgToHtml(content);
			break;
		case 'TIMSoundElem':
			html += HconvertSoundMsgToHtml(content);
			break;
		default:
			webim.Log.error('未知消息元素类型: elemType=' + type);
		break;
		}
	}
	return html;
}

//解析文本消息元素
function convertTextMsgToHtml(content) {
    return content.getText();
}

//解析文本消息元素*********
function HconvertTextMsgToHtml(MsgContent) {
	return MsgContent.Text;
}
//解析表情消息元素
function convertFaceMsgToHtml(content) {
    var faceUrl = null;
    var data = content.getData();
    var index = webim.EmotionDataIndexs[data];

    var emotion = webim.Emotions[index];
    if (emotion && emotion[1]) {
        faceUrl = emotion[1];
    }
    if (faceUrl) {
        return "<img src='" + faceUrl + "'/>";
    } else {
        return data;
    }
}
//解析表情消息元素*********
function HconvertFaceMsgToHtml(MsgContent) {
	var faceUrl = null;
	//var data = content.getData();
	var index = MsgContent.Index;
	
	var emotion = webim.Emotions[index];
	if (emotion && emotion[1]) {
		faceUrl = emotion[1];
	}
	if (faceUrl) {
		return "<img src='" + faceUrl + "'/>";
	} else {
		return index;
	}
}
//解析图片消息元素
function convertImageMsgToHtml(content, imageName) {
    var smallImage = content.getImage(webim.IMAGE_TYPE.SMALL);//小图
    var bigImage = content.getImage(webim.IMAGE_TYPE.LARGE);//大图
    var oriImage = content.getImage(webim.IMAGE_TYPE.ORIGIN);//原图
    if (!bigImage) {
        bigImage = smallImage;
    }
    if (!oriImage) {
        oriImage = smallImage;
    }
    return "<img name='" + imageName + "' src='" + smallImage.getUrl() + "#" + bigImage.getUrl() + "#" + oriImage.getUrl() + "' style='CURSOR: hand' id='" + content.getImageId() + "' bigImgUrl='" + bigImage.getUrl() + "' onclick='imageClick(this)' />";
}
//解析图片消息元素*********
function HconvertImageMsgToHtml(MsgContent) {
	var smallImage = MsgContent.URL;//小图
	return "<img src='" + smallImage + "'/>";
}
//解析语音消息元素
function convertSoundMsgToHtml(content) {
    var second = content.getSecond();//获取语音时长
    var downUrl = content.getDownUrl();
    var uuid = content.uuid;
    downloadSound(uuid,downUrl);// 下载语音到本地服务器
    if (webim.BROWSER_INFO.type == 'ie' && parseInt(webim.BROWSER_INFO.ver) <= 8) {
        return '[这是一条语音消息]demo暂不支持ie8(含)以下浏览器播放语音,语音URL:' + downUrl;
    }
    //return '<a href="javascript:void(0);" onclick="playOrPaused(this);">播放</a><audio id="uuid_'+content.uuid+'" src="' + downUrl + '" onended="playEndEvent(this);"></audio>';
    return '<img onclick="playOrPaused(this);" style="width:24px; height:24px;" src="'+playIconUrl+'">&nbsp;'+second+'s</img><audio id="uuid_'+content.uuid+'" src="' + downUrl + '" onended="playEndEvent(this);"></audio>';
}
// 播放与暂停
function playOrPaused(obj){
	var audio = $(obj).next()[0];
	if(audio.paused){
		audio.currentTime = 0;
		audio.play();
		//obj.innerHTML='暂停';
		$(obj).attr("src",pauseIconUrl);
		return;
	}
	audio.pause();
	//obj.innerHTML='播放';
	$(obj).attr("src",playIconUrl);
}
// 播放结束触发事件
function playEndEvent(obj){
	$(obj).prev().attr("src",playIconUrl);
}
//解析语音消息元素*********
function HconvertSoundMsgToHtml(MsgContent) {
	var second = MsgContent.Second;//获取语音时长
	var downUrl = MsgContent.Address;
	var uuid = MsgContent.UUID;
	if (webim.BROWSER_INFO.type == 'ie' && parseInt(webim.BROWSER_INFO.ver) <= 8) {
		return '[这是一条语音消息]demo暂不支持ie8(含)以下浏览器播放语音,语音URL:' + downUrl;
	}
	return '<img onclick="playOrPaused(this);" style="width:24px; height:24px;" src="'+playIconUrl+'">&nbsp;'+second+'s</img><audio id="uuid_'+uuid+'" src="' + downUrl + '" onended="playEndEvent(this);"></audio>';
}
//解析文件消息元素
function convertFileMsgToHtml(content) {
    var fileSize, unitStr;
    fileSize = content.getSize();
    unitStr = "Byte";
    if (fileSize >= 1024) {
        fileSize = Math.round(fileSize / 1024);
        unitStr = "KB";
    }
    // return '<a href="' + content.getDownUrl() + '" title="点击下载文件" ><i class="glyphicon glyphicon-file">&nbsp;' + content.getName() + '(' + fileSize + unitStr + ')</i></a>';

    return '<a href="javascript:;" onclick=\'webim.onDownFile("'+content.uuid+'")\' title="点击下载文件" ><i class="glyphicon glyphicon-file">&nbsp;' + content.name + '(' + fileSize + unitStr + ')</i></a>';
}
//解析位置消息元素
function convertLocationMsgToHtml(content) {
    return '经度=' + content.getLongitude() + ',纬度=' + content.getLatitude() + ',描述=' + content.getDesc();
}
//解析自定义消息元素
function convertCustomMsgToHtml(content) {
    var data = content.getData();//自定义数据
    var desc = content.getDesc();//描述信息
    var ext = content.getExt();//扩展信息
    return "data=" + data + ", desc=" + desc + ", ext=" + ext;
}
//解析群提示消息元素
function convertGroupTipMsgToHtml(content) {
    var WEB_IM_GROUP_TIP_MAX_USER_COUNT = 10;
    var text = "";
    var maxIndex = WEB_IM_GROUP_TIP_MAX_USER_COUNT - 1;
    var opType, opUserId, userIdList;
    var groupMemberNum;
    opType = content.getOpType();//群提示消息类型（操作类型）
    opUserId = content.getOpUserId();//操作人id
    switch (opType) {
        case webim.GROUP_TIP_TYPE.JOIN://加入群
            userIdList = content.getUserIdList();
            //text += opUserId + "邀请了";
            for (var m in userIdList) {
                text += userIdList[m] + ",";
                if (userIdList.length > WEB_IM_GROUP_TIP_MAX_USER_COUNT && m == maxIndex) {
                    text += "等" + userIdList.length + "人";
                    break;
                }
            }
            text = text.substring(0, text.length - 1);
            text += "加入该群，当前群成员数：" + content.getGroupMemberNum();
            break;
        case webim.GROUP_TIP_TYPE.QUIT://退出群
            text += opUserId + "离开该群，当前群成员数：" + content.getGroupMemberNum();
            break;
        case webim.GROUP_TIP_TYPE.KICK://踢出群
            text += opUserId + "将";
            userIdList = content.getUserIdList();
            for (var m in userIdList) {
                text += userIdList[m] + ",";
                if (userIdList.length > WEB_IM_GROUP_TIP_MAX_USER_COUNT && m == maxIndex) {
                    text += "等" + userIdList.length + "人";
                    break;
                }
            }
            text += "踢出该群";
            break;
        case webim.GROUP_TIP_TYPE.SET_ADMIN://设置管理员
            text += opUserId + "将";
            userIdList = content.getUserIdList();
            for (var m in userIdList) {
                text += userIdList[m] + ",";
                if (userIdList.length > WEB_IM_GROUP_TIP_MAX_USER_COUNT && m == maxIndex) {
                    text += "等" + userIdList.length + "人";
                    break;
                }
            }
            text += "设为管理员";
            break;
        case webim.GROUP_TIP_TYPE.CANCEL_ADMIN://取消管理员
            text += opUserId + "取消";
            userIdList = content.getUserIdList();
            for (var m in userIdList) {
                text += userIdList[m] + ",";
                if (userIdList.length > WEB_IM_GROUP_TIP_MAX_USER_COUNT && m == maxIndex) {
                    text += "等" + userIdList.length + "人";
                    break;
                }
            }
            text += "的管理员资格";
            break;

        case webim.GROUP_TIP_TYPE.MODIFY_GROUP_INFO://群资料变更
            text += opUserId + "修改了群资料：";
            var groupInfoList = content.getGroupInfoList();
            var type, value;
            for (var m in groupInfoList) {
                type = groupInfoList[m].getType();
                value = groupInfoList[m].getValue();
                switch (type) {
                    case webim.GROUP_TIP_MODIFY_GROUP_INFO_TYPE.FACE_URL:
                        text += "群头像为" + value + "; ";
                        break;
                    case webim.GROUP_TIP_MODIFY_GROUP_INFO_TYPE.NAME:
                        text += "群名称为" + value + "; ";
                        break;
                    case webim.GROUP_TIP_MODIFY_GROUP_INFO_TYPE.OWNER:
                        text += "群主为" + value + "; ";
                        break;
                    case webim.GROUP_TIP_MODIFY_GROUP_INFO_TYPE.NOTIFICATION:
                        text += "群公告为" + value + "; ";
                        break;
                    case webim.GROUP_TIP_MODIFY_GROUP_INFO_TYPE.INTRODUCTION:
                        text += "群简介为" + value + "; ";
                        break;
                    default:
                        text += "未知信息为:type=" + type + ",value=" + value + "; ";
                        break;
                }
            }
            break;

        case webim.GROUP_TIP_TYPE.MODIFY_MEMBER_INFO://群成员资料变更(禁言时间)
            text += opUserId + "修改了群成员资料:";
            var memberInfoList = content.getMemberInfoList();
            var userId, shutupTime;
            for (var m in memberInfoList) {
                userId = memberInfoList[m].getUserId();
                shutupTime = memberInfoList[m].getShutupTime();
                text += userId + ": ";
                if (shutupTime != null && shutupTime !== undefined) {
                    if (shutupTime == 0) {
                        text += "取消禁言; ";
                    } else {
                        text += "禁言" + shutupTime + "秒; ";
                    }
                } else {
                    text += " shutupTime为空";
                }
                if (memberInfoList.length > WEB_IM_GROUP_TIP_MAX_USER_COUNT && m == maxIndex) {
                    text += "等" + memberInfoList.length + "人";
                    break;
                }
            }
            break;
        default:
            text += "未知群提示消息类型：type=" + opType;
            break;
    }
    return text;
}