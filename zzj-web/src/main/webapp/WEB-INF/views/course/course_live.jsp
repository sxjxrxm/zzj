<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>找专家 e课堂</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="../styles/im/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/im/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/im/css/webim_demo.css"/>
    <link rel="stylesheet" type="text/css" href="../styles/im/css/bootstrap-table.css"/>

</head>
<body onunload="onClose()">

<div class="aio" id="webim_demo">
    <div class="titlebar">
        <img id="p_my_face"/>

        <p id="t_my_name"></p>
        <p style="font-size: 15px;color: #fff;position: relative;left: 100px;top: 15px;">当前课堂主题：${courseInfo.courseName}</p>
    </div>
    <div class="sesspart">
        <div class="accordion" id="accordionContact">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionContact" href="#collapseGroup">
                        我的群组
                    </a>
                </div>
                <div id="collapseGroup" class="accordion-body collapse">
                    <div class="sesslist">
                    </div>
                </div>
            </div> 
        </div>
    </div>

    <div class="chatpart">
        <div class="msgflow">
        </div>

        <span id="msg_end" style="overflow:hidden"></span>

        <div class="editbar">
            <a class="chat02_title_btn ctb01" title="选择表情" onclick="showEmotionDialog()"></a>
            <a class="chat02_title_btn ctb03" title="选择图片" onclick="selectPicClick()" href="#"> </a>
            <!-- <a class="chat02_title_btn ctb05" title="选择文件" onclick="selectFileClick()" href="#"> </a>
            <a class="chat02_title_btn ctb04" title="发送自定义消息" onclick="showEditCustomMsgDialog()" href="#"> </a>-->
            <a class="chat02_title_btn ctb06" title="听众禁言" onclick="forbidSendMsgDialog()" href="#"> </a>
            <span id="shutUpUntilInput"></span>

            <div id="wl_faces_box" class="wl_faces_box">
                <div class="wl_faces_content">
                    <div class="title">
                        <ul>
                            <li class="title_name">常用表情</li>
                            <li class="wl_faces_close"><span
                                    onclick='turnoffFaces_box()'>&nbsp;</span></li>
                        </ul>
                    </div>
                    <div id="wl_faces_main" class="wl_faces_main">
                        <ul id="emotionUL">
                        </ul>
                    </div>
                </div>
                <div class="wlf_icon"></div>
            </div>
        </div>
        <textarea class="msgedit" id="send_msg_text" onkeydown="onTextareaKeyDown()" cols="82" rows="5"></textarea>

        <div class="sendbar" id="sendbar">
            <button type="button" class="sendbtn" onclick="onSendMsg()">发送</button>
            <button type="button" class="closebtn" onclick="onClose()">关闭</button>
            <c:if test="${courseInfo.courseType eq 1}">
            	<button type="button" class="prePage" onclick="">上一页</button>
            	<button type="button" class="nextPage" onclick="">下一页</button>
            	Go
            	<select id="pageOption"></select>
            </c:if>
        </div>
    </div>
    <div class="bottom"></div>
</div>


<div class="modal fade" id="forbid_send_msg_dialog" tabindex="-1" role="dialog"
     aria-labelledby="forbid_send_msg_dialog_label" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="forbid_send_msg_dialog_label">
                    设置成员禁言时间
                </h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" role="form" id="fsm_form" name="fsm_form">
                    <input type="hidden" id="fsm_sel_row_index"/>
                    <input type="hidden" value="${courseInfo.roomId}" id="fsm_group_id"/>

                    <div class="form-group"  id="forbidFromGroupDiv">
                        <label for="fsm_account" class="col-sm-2 control-label">成员帐号</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="fsm_account"
                                   placeholder="请输入要修改的成员帐号" readonly="readonly">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="fsm_shut_up_time" class="col-sm-2 control-label">禁言时间(分钟)</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="fsm_shut_up_time"
                                   placeholder="请输入禁言时间，取消禁言请输0" maxlength="8" onkeydown="if(event.keyCode==13)return false;">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    关闭
                </button>
                <button type="button" class="btn btn-primary" id="forbidSubmit" onclick="forbidSendMsg()">
                    提交
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<div class="modal fade" id="send_group_msg_dialog" tabindex="-1" role="dialog"
     aria-labelledby="send_group_msg_dialog_label" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="send_group_msg_dialog_label">
                    发群消息
                </h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" role="form" id="sgm_form" name="sgm_form">
                    <input type="hidden" id="sgm_to_group_name">
                    <div class="form-group">
                        <label for="sgm_to_groupid" class="col-sm-2 control-label">群ID</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="sgm_to_groupid"
                                   placeholder="" readonly="readonly">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sgm_content" class="col-sm-2 control-label">内容</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="sgm_content"
                                   placeholder="请输入消息内容" onkeydown="if(event.keyCode==13)return false;">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="sendGroupMsg()">
                    提交
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->



<div class="modal fade" id="upload_pic_low_ie_dialog" tabindex="-1" role="dialog"
     aria-labelledby="upload_pic_low_ie_dialog_label" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="upload_pic_low_ie_dialog_label">
                    发送图片
                </h4>
            </div>
            <div class="modal-body">
                <form id="updli_form" enctype="multipart/form-data" class="form-horizontal" role="form"
                      onkeydown="if(event.keyCode==13)return false;">

                    <div class="form-group">
                        <label for="File" class="col-sm-2 control-label">选择</label>

                        <div class="col-sm-10">
                            <input type="file" id="updli_file"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="uploadPicLowIE()">
                    发送
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<div class="modal fade" id="upload_pic_dialog" tabindex="-1" role="dialog"
     aria-labelledby="upload_pic_dialog_label" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="upload_pic_dialog_label">
                    发送图片
                </h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" role="form" onkeydown="if(event.keyCode==13)return false;" id="upd_form"
                      name="upd_form">
                    <div class="form-group">
                        <label for="File" class="col-sm-2 control-label">选择</label>

                        <div class="col-sm-10">
                            <input type="file" id="upd_pic" onchange="fileOnChange(this)"/>
                            <!--<input type="button" value="停止" id="upd_abort" />-->
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="File" class="col-sm-2 control-label">预览</label>

                        <div class="col-sm-10">
                            <div id="previewPicDiv"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="upd_progress" class="col-sm-2 control-label">进度</label>

                        <div class="col-sm-10">

                            <progress id="upd_progress" value="0" max="100"></progress>
                            <!--<input type="text" id="upd_progress" value="0" name="upd_progress" />-->
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="uploadPic()">
                    发送
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->



<div class="modal fade" id="click_pic_dialog" tabindex="-1" role="dialog"
     aria-labelledby="click_pic_dialog_label" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="click_pic_dialog_label">
                    查看图片
                </h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" role="form">

                    <div class="form-group">

                        <div class="col-sm-12">
                            <div id="bigPicDiv"></div>
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">
                    关闭
                </button>
                <!--<button type="button" class="btn btn-primary" id="viewOriPicBt">
                    查看原图
                </button>-->
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<input type="hidden" value="${historyMsgSeq}" name="historyMsgSeq" id="historyMsgSeq"/><!-- 记录拉取历史信息的msgSeq -->
<input type="hidden" value="${haveMoreMsg}" name="haveMoreMsg" id="haveMoreMsg"/><!-- 记录是否还有更多的历史记录，空值说明还有，no说明没有 -->
<input type="hidden" value="${userInfo.userId}" name="userInfo.userId" id="userId"/>
<input type="hidden" value="${userSig}" name="userSig" id="userSig"/>
<input type="hidden" value="${resultList}" name="resultList" id="resultList"/>

<input type="hidden" value="${livePageNo}" name="livePageNo" id="livePageNo"/>
<input type="hidden" value="${totalPageCount}" name="totalPageCount" id="totalPageCount"/>
<input type="hidden" value="${courseInfo.courseCd}" name="courseInfo.courseCd" id="courseCd"/>
<input type="hidden" value="${courseInfo.courseType}" name="courseInfo.courseType" id="courseType"/>
<input type="hidden" value="${courseInfo.roomId}" name="courseInfo.roomId" id="roomId"/>


<script type="text/javascript" src="../scripts/im/js/lib/jquery/jquery.js"></script>
<script type="text/javascript" src="../scripts/im/js/lib/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../scripts/im/js/lib/lodash.min.js"></script>
<script type="text/javascript" src="../scripts/im/js/lib/bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="../scripts/im/js/lib/bootstrap/bootstrap-table.js"></script>
<script type="text/javascript" src="../scripts/im/js/lib/bootstrap/bootstrap-collapse.js"></script>
<script type="text/javascript" src="../scripts/im/js/lib/xss.js"></script>
<!--TLS web sdk(只用于托管模式，独立模式不用引入)-->
<script type="text/javascript" src="https://tls.qcloud.com/libs/api.min.js"></script>
<!--用于获取文件MD5 js api(发送图片时用到)-->
<script type="text/javascript" src="../scripts/im/js/lib/md5/spark-md5.js"></script>

<script type="text/javascript" src="../scripts/im/json2.js"></script>
<script type="text/javascript" src="../scripts/im/webim.min.js"></script>
<!--web im sdk 登录 示例代码-->
<script type="text/javascript" src="../scripts/im/js/login/login.js"></script>
<!--web im sdk 登出 示例代码-->
<script type="text/javascript" src="../scripts/im/js/logout/logout.js"></script>
<!--web im 解析一条消息 示例代码-->
<script type="text/javascript" src="../scripts/im/js/common/show_one_msg.js"></script>
<!--web im demo 基本逻辑-->
<script type="text/javascript" src="../scripts/im/js/base.js"></script>
<!--web im sdk 资料管理 api 示例代码-->
<script type="text/javascript" src="../scripts/im/js/profile/profile_manager.js"></script>
<!--web im sdk 好友管理 api 示例代码-->
<script type="text/javascript" src="../scripts/im/js/friend/friend_manager.js"></script>
<!--web im sdk 最近联系人 api 示例代码-->
<script type="text/javascript" src="../scripts/im/js/recentcontact/recent_contact_list_manager.js"></script>
<!--web im sdk 群组管理 api 示例代码-->
<script type="text/javascript" src="../scripts/im/js/group/group_manager.js"></script>
<!--web im sdk 群成员管理 api 示例代码-->
<script type="text/javascript" src="../scripts/im/js/group/group_member_manager.js"></script>
<!--web im 切换聊天好友或群组 示例代码-->
<script type="text/javascript" src="../scripts/im/js/switch_chat_obj.js"></script>
<!--web im sdk 获取c2c获取群组历史消息 示例代码-->
<script type="text/javascript" src="../scripts/im/js/msg/get_history_msg.js"></script>
<!--web im sdk 发送普通消息(文本和表情) api 示例代码-->
<script type="text/javascript" src="../scripts/im/js/msg/send_common_msg.js"></script>
<!--web im sdk 上传和发送图片消息 api 示例代码-->
<script type="text/javascript" src="../scripts/im/js/msg/upload_and_send_pic_msg.js"></script>
<!--web im sdk 上传和发送文件消息 api 示例代码-->
<script type="text/javascript" src="../scripts/im/js/msg/upload_and_send_file_msg.js"></script>
<!--web im sdk 切换播放语音消息 示例代码-->
<script type="text/javascript" src="../scripts/im/js/msg/switch_play_sound_msg.js"></script>
<!--web im sdk 发送自定义消息 api 示例代码-->
<script type="text/javascript" src="../scripts/im/js/msg/send_custom_msg.js"></script>
<!--web im sdk 发送群自定义通知 api 示例代码-->
<script type="text/javascript" src="../scripts/im/js/msg/send_custom_group_notify_msg.js"></script>
<!--web im 监听新消息(c2c或群) 示例代码-->
<script type="text/javascript" src="../scripts/im/js/msg/receive_new_msg.js"></script>
<!--web im 监听群系统通知消息 示例代码-->
<script type="text/javascript" src="../scripts/im/js/msg/receive_group_system_msg.js"></script>
<!--web im 监听好友系统通知消息 示例代码-->
<script type="text/javascript" src="../scripts/im/js/msg/receive_friend_system_msg.js"></script>
<!--web im 监听资料系统通知消息 示例代码-->
<script type="text/javascript" src="../scripts/im/js/msg/receive_profile_system_msg.js"></script>
<!-- cookie操作，退出时清除当前课堂在cookie中的记录 -->
<script type="text/javascript" src="../scripts/cookie.js"></script>

<script type="text/javascript">

    //帐号模式，0-表示独立模式，1-表示托管模式
    var accountMode = 0;

    //官方 demo appid,需要开发者自己修改（托管模式）
    var sdkAppID = 1400021399;
    var accountType = 9461;


    //当前用户身份
    var loginInfo = {
        'sdkAppID': sdkAppID, //用户所属应用id,必填
        'accountType': accountType, //用户所属应用帐号类型，必填
        'identifier': $("#userId").val(), //当前用户ID,必须是否字符串类型，必填
        'userSig': $("#userSig").val(), //当前用户身份凭证，必须是字符串类型，必填
        'identifierNick': null, //当前用户昵称，不用填写，登录接口会返回用户的昵称，如果没有设置，则返回用户的id
        'headurl': '../images/im/img/me.jpg'//当前用户默认头像，选填，如果设置过头像，则可以通过拉取个人资料接口来得到头像信息
    };

    var AdminAcount = 'admin';
    var selType = webim.SESSION_TYPE.GROUP;//当前聊天类型
    var selToID = $("#roomId").val();//当前选中聊天id（当聊天类型为私聊时，该值为好友帐号，否则为群号）
    var selSess = null;//当前聊天会话对象
    var recentSessMap={};//保存最近会话列表
    var reqRecentSessCount = 50;//每次请求的最近会话条数，业务可以自定义

    //默认好友头像
    var friendHeadUrl = '../images/im/img/friend.jpg';//仅demo使用，用于没有设置过头像的好友
    //默认群头像
    var groupHeadUrl = '../images/im/img/group.jpg';//仅demo使用，用于没有设置过群头像的情况
 	// 播放与暂停图标url
    var playIconUrl = "../images/im/img/play_icon.jpg";
    var pauseIconUrl = "../images/im/img/pause_icon.jpg";


    //存放c2c或者群信息（c2c用户：c2c用户id，昵称，头像；群：群id，群名称，群头像）
    var infoMap={};//初始化时，可以先拉取我的好友和我的群组信息
    var memberMap = {};


    var maxNameLen = 12;//我的好友或群组列表中名称显示最大长度，仅demo用得到
    var reqMsgCount = 15;//每次请求的历史消息(c2c获取群)条数，仅demo用得到

    var pageSize = 15;//表格的每页条数，bootstrap table 分页时用到
    var totalCount = 200;//每次接口请求的条数，bootstrap table 分页时用到

    var emotionFlag = false;//是否打开过表情选择框

    var curPlayAudio = null;//当前正在播放的audio对象

    var getPrePageC2CHistroyMsgInfoMap = {};//保留下一次拉取好友历史消息的信息
    var getPrePageGroupHistroyMsgInfoMap = {};//保留下一次拉取群历史消息的信息

    var defaultSelGroupId = null;//登录默认选中的群id，选填，仅demo用得到

    //监听（多终端同步）群系统消息方法，方法都定义在receive_group_system_msg.js文件中
    //注意每个数字代表的含义，比如，
    //1表示监听申请加群消息，2表示监听申请加群被同意消息，3表示监听申请加群被拒绝消息
    var onGroupSystemNotifys = {
        "1": onApplyJoinGroupRequestNotify,//申请加群请求（只有管理员会收到）
        "2": onApplyJoinGroupAcceptNotify,//申请加群被同意（只有申请人能够收到）
        "3": onApplyJoinGroupRefuseNotify,//申请加群被拒绝（只有申请人能够收到）
        "4": onKickedGroupNotify,//被管理员踢出群(只有被踢者接收到)
        "5": onDestoryGroupNotify,//群被解散(全员接收)
        "6": onCreateGroupNotify,//创建群(创建者接收)
        "7": onInvitedJoinGroupNotify,//邀请加群(被邀请者接收)
        "8": onQuitGroupNotify,//主动退群(主动退出者接收)
        "9": onSetedGroupAdminNotify,//设置管理员(被设置者接收)
        "10": onCanceledGroupAdminNotify,//取消管理员(被取消者接收)
        "11": onRevokeGroupNotify,//群已被回收(全员接收)
        "15": onReadedSyncGroupNotify,//群消息已读同步通知
        "255": onCustomGroupNotify//用户自定义通知(默认全员接收)
    };

    //监听好友系统通知函数对象，方法都定义在receive_friend_system_msg.js文件中
    var onFriendSystemNotifys = {
        "1": onFriendAddNotify,//好友表增加
        "2": onFriendDeleteNotify,//好友表删除
        "3": onPendencyAddNotify,//未决增加
        "4": onPendencyDeleteNotify,//未决删除
        "5": onBlackListAddNotify,//黑名单增加
        "6": onBlackListDeleteNotify//黑名单删除
    };

    var onC2cEventNotifys = {
        "92": onMsgReadedNotify,//消息已读通知
    };

    //监听资料系统通知函数对象，方法都定义在receive_profile_system_msg.js文件中
    var onProfileSystemNotifys = {
        "1": onProfileModifyNotify//资料修改
    };

    //监听连接状态回调变化事件
    var onConnNotify = function (resp) {
        var info;
        switch (resp.ErrorCode) {
            case webim.CONNECTION_STATUS.ON:
                webim.Log.warn('建立连接成功: ' + resp.ErrorInfo);
                break;
            case webim.CONNECTION_STATUS.OFF:
                info = '连接已断开，无法收到新消息，请检查下你的网络是否正常: ' + resp.ErrorInfo;
                // alert(info);
                webim.Log.warn(info);
                break;
            case webim.CONNECTION_STATUS.RECONNECT:
                info = '连接状态恢复正常: ' + resp.ErrorInfo;
                // alert(info);
                webim.Log.warn(info);
                break;
            default:
                webim.Log.error('未知连接状态: =' + resp.ErrorInfo);
                break;
        }
    };

    //IE9(含)以下浏览器用到的jsonp回调函数
    function jsonpCallback(rspData) {
        webim.setJsonpLastRspData(rspData);
    }

    //监听事件
    var listeners = {
        "onConnNotify": onConnNotify//监听连接状态回调变化事件,必填
        ,"jsonpCallback": jsonpCallback//IE9(含)以下浏览器用到的jsonp回调函数，
        ,"onMsgNotify": onMsgNotify//监听新消息(私聊，普通群(非直播聊天室)消息，全员推送消息)事件，必填
        ,"onBigGroupMsgNotify": onBigGroupMsgNotify//监听新消息(直播聊天室)事件，直播场景下必填
        ,"onGroupSystemNotifys": onGroupSystemNotifys//监听（多终端同步）群系统消息事件，如果不需要监听，可不填
        ,"onGroupInfoChangeNotify": onGroupInfoChangeNotify//监听群资料变化事件，选填
        ,"onFriendSystemNotifys": onFriendSystemNotifys//监听好友系统通知事件，选填
        ,"onProfileSystemNotifys": onProfileSystemNotifys//监听资料系统（自己或好友）通知事件，选填
        ,"onKickedEventCall" : onKickedEventCall//被其他登录实例踢下线
        ,"onC2cEventNotifys": onC2cEventNotifys//监听C2C系统消息通道
        ,"onAppliedDownloadUrl" : onAppliedDownloadUrl //申请文件/音频下载地址的回调
    };

    var isAccessFormalEnv = true;//是否访问正式环境

    if (webim.Tool.getQueryString("isAccessFormalEnv") == "false") {
        isAccessFormalEnv = false;//访问测试环境
    }

    var isLogOn = false;//是否开启sdk在控制台打印日志

    //初始化时，其他对象，选填
    var options = {
        'isAccessFormalEnv': isAccessFormalEnv,//是否访问正式环境，默认访问正式，选填
        'isLogOn': isLogOn //是否开启控制台打印日志,默认开启，选填
    }

    if (accountMode == 1) {//托管模式
        //判断是否已经拿到临时身份凭证
        if (webim.Tool.getQueryString('tmpsig')) {
            if (loginInfo.identifier == null) {
                webim.Log.info('start fetchUserSig');
                //获取正式身份凭证，成功后会回调tlsGetUserSig(res)函数
                TLSHelper.fetchUserSig();
            }
        } else {//未登录
            if (loginInfo.identifier == null) {
                //弹出选择应用类型对话框
                $('#select_app_dialog').modal('show');
                $("body").css("background-color", 'white');
            }
        }
    } else {//独立模式
        //$('#login_dialog').modal('show');
    	loginInfo.identifier = $('#userId').val();
    	loginInfo.userSig = $('#userSig').val();
    	var courseType = $('#courseType').val();
    	if (courseType != 0 && courseType != 1) {
    		alert("课堂不存在");
    	}
    	if (courseType == 0) {
    		webimLogin();
    		$("div #accordionContact").hide();
    		
    	}
    	if (courseType == 1) {
    		initHistoryCourseApp();
    	}
    }
    
//*********以往课堂js**********

function initHistoryCourseApp(){
	$("body").css("background-color", '#2f2f2f');
    document.getElementById("webim_demo").style.display = "block";//展开聊天界面
    document.getElementById("p_my_face").src = loginInfo.headurl;
    if (loginInfo.identifierNick) {
        document.getElementById("t_my_name").innerHTML = webim.Tool.formatText2Html(loginInfo.identifierNick);
    } else {
        document.getElementById("t_my_name").innerHTML = webim.Tool.formatText2Html(loginInfo.identifier);
    }
    // 禁用发送等按钮
    $("div .editbar").hide();
    $("div #accordionContact").hide();
    $("div .msgflow").css("height","505px");
    $("div .sendbtn").hide();
    $("#send_msg_text").hide();
    var courseCd = $("#courseCd").val();
    webim.login(
            loginInfo, listeners, options,
            function (resp) {
                loginInfo.identifierNick = resp.identifierNick;//设置当前用户昵称
                //initDemoApp();
            },
            function (err) {
                alert(err.ErrorInfo);
            }
        );
    getMsgByPage();
}

// 获得历史消息
function getMsgByPage(pageNo){
	var livePageNo,Url;
	var courseCd = $("#courseCd").val();
	if (!isNaN(pageNo)) {
		livePageNo = pageNo;
		$("#livePageNo").val(pageNo);
		Url = '${pageContext.request.contextPath}/course/showMoreMsg.htm?courseCd='+courseCd+'&livePageNo='+livePageNo;
	} else {
		Url = '${pageContext.request.contextPath}/course/showMoreMsg.htm?courseCd='+courseCd;
	}
	 $.post(Url,'', 
				function (data) { 
	    			data = eval("(" + data + ")");
	    			 $("div .msgflow").empty();
	    			var RspMsgList = data.RspMsgList;
	    			$("#livePageNo").val(data.livePageNo);
	    			createPageOptions(data.livePageNo, data.totalPageCount);
	    			for (var i = RspMsgList.length - 1 ; i >= 0; i--) {
	    				HaddMsg(RspMsgList[i]);
	    			}
				});
}

// 构建页码选取option
function createPageOptions(livePageNo,totalPageCount){
	var pageOptionSelect = $("#pageOption");
	pageOptionSelect.empty();
	for (var i = 1; i <= totalPageCount; i++) {
		if (i == livePageNo) {
			pageOptionSelect.append('<option value="'+i+'" onclick="getMsgByPage('+i+')" selected="selected" >'+i+'</option>');
		} else {
			pageOptionSelect.append('<option value="'+i+'" onclick="getMsgByPage('+i+')">'+i+'</option>');
		}
	}
	$(".prePage").removeAttr("disabled");
	$(".nextPage").removeAttr("disabled");
	if (totalPageCount == 1) {
		$(".prePage").attr("disabled","disabled");
		$(".nextPage").attr("disabled","disabled");
		pageOptionSelect.attr("disabled","disabled");
	} else if (livePageNo == totalPageCount) {
		$(".nextPage").attr("disabled","disabled");
		$(".prePage").attr("onclick","getMsgByPage("+(livePageNo-1)+")");
	} else if (livePageNo == 1) {
		$(".prePage").attr("disabled","disabled");
		$(".nextPage").attr("onclick","getMsgByPage("+(livePageNo-1+2)+")");
	} else {
		$(".prePage").attr("onclick","getMsgByPage("+(livePageNo-1)+")");
		$(".nextPage").attr("onclick","getMsgByPage("+(livePageNo-1+2)+")");
	}
}

//*******************

    var msgflow = document.getElementsByClassName("msgflow")[0];
    var bindScrollHistoryEvent  = {
        init : function(){
            msgflow.onscroll = function(){
                if(msgflow.scrollTop == 0){
                    msgflow.scrollTop = 10;
                    if(selType == webim.SESSION_TYPE.C2C){
                        getPrePageC2CHistoryMsgs();
                    }else{
                        getPrePageGroupHistoryMsgs();
                    }

                }
            }
        },
        reset : function(){
            msgflow.onscroll = null;
        }
    };
    //forbid_send_msg_dialog
    function forbidSendMsgDialog(){
    	if ($("#userId").val() == AdminAcount) {
    		getGroupMemberInfo();
        	forbidSendMsgAll();
    	} else {
    		alert("您没有权限执行禁言操作，请切换账号为："+AdminAcount);
    	}
    }

    // 听众禁言
    var memberAccountList = [];
    function forbidSendMsgAll(){
    	$("#forbidFromGroupDiv").hide();
    	$("#forbid_send_msg_dialog").modal('show');
    }
 	// 下载语音到本地服务器 show_one_msg.js 215行调用
 	var soundTimeStamp;
 	var joinCourseTimeStamp = (new Date()).getTime() / 1000;
 	//var joinCourseTimeStamp = 1482559176978 / 1000;
    function downloadSound(suuid,sdownUrl) {
    	sdownUrl = sdownUrl.replace(/&/g, "%26"); // 将url中的&符号转义
    	if (soundTimeStamp) {
    		if (soundTimeStamp >= joinCourseTimeStamp) { // 只下载新消息的声音
    			$.post('${pageContext.request.contextPath}/qcloudim/downloadSound.htm?groupId='+escape(selToID)+'&uuid='+suuid+'&url='+sdownUrl,
    	 				'',
    	 				function(data){
    	 					//alert(data);
    	 					}
    	 		);
    		}
    	}
 	}
    window.onunload = onClose;
</script>

</body>
</html>
