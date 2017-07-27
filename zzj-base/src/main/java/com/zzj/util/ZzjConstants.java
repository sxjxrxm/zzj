/**
 * Project Name:zzj-base
 * File Name:Constants.java
 * Package Name:com.zzj.core.util
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.util;

/**
 * <p><strong>类名: </strong></p>Constants <br/>
 * <p><strong>功能说明: </strong></p>系统常量类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年3月30日下午6:52:11 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class ZzjConstants {
	/**
	 * 用户信息（Session保存用）
	 */
	public static final String USER_SESSION_ADMIN_KEY="userInfo";
	
	/**
	 * 系统配置信息（Session保存用）
	 */
	public static final String SYS_SESSION_MSTCODEINFOS="mstCodeInfos";
	
	/**
	 * 系统配置信息：技术领域
	 */
	public static final String SYS_SESSION_TECHCODEINFOS="techCodeInfos";
	
	/**
	 * 系统配置信息:研究领域
	 */
	public static final String SYS_SESSION_RISHCODEINFOS="rschCodeInfos";
	/**
	 * 用户权限信息（Session保存用）
	 */
	public static final String SYS_SESSION_ROLE="role_menu_url_list";
	
	/**
	 * 画面路径名
	 */
    public static final String SYSPATHNAME = "SYSPATHNAME";
    public static final String SESSION_SECURITY_CODE = "sessionSecCode";

	/**
	 * 默认错误页面
	 **/
	public static final String SYS_ERROR_PAGE = "error/error";

	/**
	 * 默认错误地址
	 **/
	public static final String SYS_ERROR_URL = "error.htm";

	/**
	 * 默认Session过期地址
	 **/
	public static final String SYS_SESSION_EXPIRED_URL = "sessionExpired.htm";

	
	/**
	 * 登录跳转页面
	 */
	public static final String SYS_GOLOGIN_URL = "goLogin.htm";

	/**
	 * 默认错误地址
	 **/
	public static final String INDEX_URL = "index.htm";
	
	/**
	 * 默认错误消息字段ID
	 **/
	public static final String SYS_ERROR_KEY = "errMsg";
	
	/**
	 * 默认提示消息字段ID
	 **/
	public static final String SYS_INFO_KEY = "infoMsg";
	
	/**
	 * 默认警告消息字段ID
	 **/
	public static final String SYS_WARNING_KEY = "warningMsg";
	
	/**
	 * 默认错误消息字段ID
	 **/
	public static final String SYS_ERROR_ITEM_KEY = "errItem";
	
	/**
	 * 标签:管理员
	 **/
	public static final String SIGN_ADMINISTRATOR = "管理员";
	
	/**
	 * 标签:专家
	 **/
	public static final String SIGN_EXPERT = "专家";
	
	/**
	 * 标签:主讲人
	 **/
	public static final String SIGN_SPEAKER = "主讲人";
	
	/**
	 * 配置文件KEY
	 * DES加密解密密钥
	 */
	public static final String C_S_CFG_DES_KEY = "des.key";
	
	/**
	 * 配置文件KEY
	 * 头像保存路径
	 */
	public static final String C_S_CFG_AVATAR_PATH = "avatar.path";
	
	/**
	 * 配置文件KEY
	 * 图片URL域名
	 */
	public static final String C_S_CFG_IMG_URL = "url.img";
	
	/**
	 * 配置文件KEY
	 * PDF URL域名
	 */
	public static final String C_S_CFG_PDF_URL = "url.pdf";
	
	/**
	 * 配置文件KEY
	 * APK URL域名
	 */
	public static final String C_S_CFG_APK_URL = "url.apk";
	
	/**
	 * 配置文件KEY
	 * H5URL域名
	 */
	public static final String C_S_CFG_H5_URL = "url.h5";
	
	/**
	 * 配置文件
	 * 用户默认昵称KEY
	 */
	public static final String C_S_CFG_DEFAULT_NICKNAME= "user.default.nickname";
	
	/**
	 * 配置文件
	 * 网站域名前缀KEY
	 */
	public static final String C_S_CFG_ZZJ_URL= "url.zzj";
	
	/**
	 * 分隔符（逗号）
	 */
	public static final String C_S_SEPERATOR_COMMA = ",";
	
	/**
	 * 哈希加密方式
	 */
	public static final String C_S_HASH_TYPE = "SHA-1";
	
	/**
	 * 验证码长度
	 */
	public static final Integer AUTHCODE_LENGTH = 4;
	
	/**
	 * 默认列表页面每页显示记录数
	 */
	public static final Integer DEFAULT_PAGESIZE = 10;
	
	/**
	 * 在线课堂每页显示记录数
	 */
	public static final Integer COURSE_PAGESIZE = 50;
	
	/**
	 * 默认列表页面每页显示记录数(小画面)
	 */
	public static final Integer DEFAULT_PAGESIZE_S = 5;
	
    /**
     * 审核状态：待审核
     */
	public static final Integer STATUS_0 = 0;
	
    /**
     * 审核状态：已审核   
     */
	public static final Integer STATUS_1 = 1;
	
    /**
     * 审核状态：已拒绝
     */
	public static final Integer STATUS_2 = 2;
	
    /**
     * 置顶状态：0
     */
	public static final Integer TOP_FLG_0 = 0;
	
    /**
     * 置顶状态：1
     */
	public static final Integer TOP_FLG_1 = 1;
	
    /**
     * 课堂分类：0：当前课堂，1：以往课堂
     */
	public static final Integer COURSE_TYPE_0 = 0;
	
    /**
     * 课堂分类：0：当前课堂，1：以往课堂
     */
	public static final Integer COURSE_TYPE_1 = 1;
	
    /**
     * 推荐置顶区分：1，推荐；2置顶；
     */
	public static final String RECOMMEND_KBN_1 = "1";
	
    /**
     * 推荐置顶区分：1，推荐；2置顶；
     */
	public static final String RECOMMEND_KBN_2 = "2";
	
	/**
	 * 前台显示默认推荐语
	 */
	public static final String DEFAULT_RECOMMEND_MSG = "请输入您的推荐语";
	
    /**
    * 业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告
    */
	public static final String BUSI_TYPE_01 = "01";
	
    /**
    * 业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告
    */
	public static final String BUSI_TYPE_02 = "02";
    /**
    * 业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告
    */
	public static final String BUSI_TYPE_03 = "03";
    /**
    * 业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告
    */
	public static final String BUSI_TYPE_04 = "04";
    /**
    * 业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告
    */
	public static final String BUSI_TYPE_05 = "05";	
    /**
    * 业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告
    */
	public static final String BUSI_TYPE_06 = "06";
    /**
    * 业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告
    */
	public static final String BUSI_TYPE_07 = "07";
    /**
    * 业务分类：99 其他
    */
	public static final String BUSI_TYPE_99 = "99";
	/**
	 * 视频分类：1视频,2直播
	 */
	public static final String VIDEO_TYPE_1 = "1";
	/**
	 * 视频分类：1视频,2直播
	 */
	public static final String VIDEO_TYPE_2 = "2";
	/**
	 * 知识来源：1 找专家平台, 2专家
	 */
	public static final Integer SOURCE_TYPE_1 = 1;
	/**
	 * 知识来源：1 找专家平台, 2专家
	 */
	public static final Integer SOURCE_TYPE_2 = 2;
    /**
    * apk上传路径
    */
	public static final String APK_PATH = "path.apk";
	/**
	 * apk上传文件名
	 */
	public static final String APK_NAME = "path.apkName";
	/**
	 * 资源上传路径
	 */
	public static final String RESOURCE_PATH = "path.resource";
    /**
    * 临时文件路径
    */
	public static final String TMP_PATH = "path.tmp";
    /**
    * 文件出力格式
    */
	public static final String FILE_OUTPUT_CSV = ".csv";	
    /**
    * 文件出力名
    */
	public static final String FILE_OUTPUT_NAME_NEEDS = "需求一览";
	/**
	 * 文件出力名
	 */
	public static final String FILE_OUTPUT_NAME_EXPERT = "专家一览";
	/**
	 * 文件出力名
	 */
	public static final String FILE_OUTPUT_NAME_NEWS = "知识一览";
	/**
	 * 文件出力名
	 */
	public static final String FILE_OUTPUT_NAME_VIDEO = "视频一览";
    /**
    * 文件出力名
    */
	public static final String FILE_OUTPUT_NAME_COURSE = "e课堂一览";
    /**
    * 文件出力名:E问一览
    */
	public static final String FILE_OUTPUT_NAME_QUESTION = "E问一览";
    /**
    * 文件出力名:E问回答一览
    */
	public static final String FILE_OUTPUT_NAME_QUESTIONANSWER = "E问回答一览";
    /**
    * 文件出力名:政企一览
    */
	public static final String FILE_OUTPUT_NAME_ENTERPRISE = "政企一览";
    /**
    * 文件出力名：行为一览
    */
	public static final String FILE_OUTPUT_NAME_ACTION = "行为一览";	
    /**
    * 文件出力名：行为详细一览
    */
	public static final String FILE_OUTPUT_NAME_ACTION_DETAIL = "行为详细一览";
    /**
    * 文件出力名：收入一览
    */
	public static final String FILE_OUTPUT_NAME_INCOME = "收入一览";	
    /**
    * 文件出力名：收入详细一览
    */
	public static final String FILE_OUTPUT_NAME_INCOME_DETAIL = "收入详细一览";
    /**
    * 技术领域
    */
	public static final String TECH_FIELD_TYPE = "techFieldType";
    /**
    * 研究领域
    */
	public static final String RSCH_FIELD_TYPE = "rschFieldType";
	/**
	 * 审核常态
	 */
	public static final String AUDIT_STATUS = "auditStatus";
	/**
	 * 费用
	 */
	public static final String PAYMENT_KBN = "paymentKbn";
	/**
	 * 费用: 1付费，0免费
	 */
	public static final String PAYMENT_KBN_0 = "0";
	/**
	 * 费用: 1付费，0免费
	 */
	public static final String PAYMENT_KBN_1 = "1";
	/**
	 * 知识分类
	 */
	public static final String NEWS_TYPE = "newsType";
	/**
	 * 单位分类
	 * @author ctdw 李善瑞
	 */
	public static final String COEP_TYPE = "corpType";
	/**
	 * 用户专家编码
	 */
	public static final String USER_NO = "USER_NO";
	/**
	 * 知识编码
	 */
	public static final String NEWS_NO = "NEWS_NO";
	/**
	 * 视频编码
	 */
	public static final String VIDEO_NO = "VIDEO_NO";
	/**
	 * 直播编码
	 */
	public static final String LIVE_NO = "LIVE_NO";
	/**
	 * 专家用户在appUsersInfo表和mstUsersInfo表中保存的密码
	 */
	public static final String EXPERT_PSW_IN_USERS_INFO = "expert";
	/**
	 * 专家用户在appUsersInfo表和mstUsersInfo表中保存的用户角色id
	 */
	public static final String EXPERT_ID_IN_USERS_INFO = "2";
	/**
	 * appUsersInfo表的政企用户角色id
	 */
	public static final String CORP_ROLE_IN_APP_INFO = "3";
//	/**
//	 * 专家头像在icon目录下的子文件夹名
//	 */
//	public static final String EXPERT_AVATOR_FOLDER = "avator";
//	/**
//	 * 知识图片在icon目录下的子文件夹名
//	 */
//	public static final String NEWS_IMG_FOLDER = "newsImg";
//	/**
//	 * 视频图片在icon目录下的子文件夹名
//	 */
//	public static final String VIDEO_IMG_FOLDER = "videoImg";
//	/**
//	 * 课堂图片在icon目录下的子文件夹名
//	 */
//	public static final String COURSE_IMG_FOLDER = "courseImg";
	/**
	 * 课堂编码
	 */
	public static final String COURSE_NO = "COURSE_NO";
//	/**
//	 * 专家头像保存时的命名后缀 eg: USR00000007-avatorAddress.png
//	 */
//	public static final String EXPERT_AVATOR_SUFFIX = "avatorAddress";
//	/**
//	 * 知识插图保存时的命名后缀 eg: xxx-avatorAddress.png
//	 */
//	public static final String CHAPTER_IMG_SUFFIX = "chapterIcon";
//	/**
//	 * 列表图片保存时的命名后缀 eg: NEW00000007-littleIcon.png
//	 */
//	public static final String LITTLE_IMG_SUFFIX = "littleIcon";
//	/**
//	 * 详情图片保存时的命名后缀 eg: NEW00000007-bigIcon.png
//	 */
//	public static final String BIG_IMG_SUFFIX = "bigIcon";
//	/**
//	 * 腾讯云视频上传参数
//	 */
//	public static final String QCLOUD_SECRET_ID = "qcloud.SecretId";
//	public static final String QCLOUD_SECRET_KEY = "qcloud.SecretKey";
//	public static final String QCLOUD_REQUEST_METHOD = "qcloud.RequestMethod";
//	public static final String QCLOUD_DEFAULT_REGION = "qcloud.DefaultRegion";
	/**
	 * 腾讯云 云通信 appid
	 */
	public static final String QCLOUDIM_SDKAPPID = "qcloudim.sdkappid";
	/**
	 * 腾讯云 云通信 管理员用户
	 */
	public static final String QCLOUDIM_USERID = "qcloudim.identifier";
	/**
	 * 腾讯云 云通信 私匙
	 */
	public static final String QCLOUDIM_PRIVSTR = "qcloudim.privstr";
	/**
	 * 腾讯云 云通信 公匙
	 */
	public static final String QCLOUDIM_PUBSTR = "qcloudim.pubstr";
	/**
	 * 直播参数在系统配置表中的codeType
	 */
	public static final String LIVEPUSH = "livepush";
	
	/**
	 * 轮播控制条数
	 */
	public static final Integer SLIDE_NUM = 10;
	
	/**
	 * 推荐控制条数
	 */
	public static final Integer RECOMMEND_NUM = 10;
	
	/**
	 * 置顶控制条数
	 */
	public static final Integer TO_TOP_NUM = 10;
	/**
	 * 请求处理的结果，OK表示处理成功
	 */
	public static final String ACTION_STATUS_OK = "OK";
	/**
	 * 请求处理的结果，FAIL表示失败。
	 */
	public static final String ACTION_STATUS_FAIL = "FAIL";
	/**
	 *  0为允许发言
	 */
	public static final int ERROR_COED_0 = 0;
	/**
	 *   1为拒绝发言
	 */
	public static final int ERROR_COED_1 = 1;
	/**
	 *    2为静默丢弃
	 */
	public static final int ERROR_COED_2 = 2;
	/**
	 * 可用
	 */
	public static final byte DELETE_FLAG_0 = 0;
	/**
	 * 不可用
	 */
	public static final byte DELETE_FLAG_1 = 1;
	/**
	 * 常数0
	 */
	public static final int NUM_I_0 = 0;
	/**
	 * 常数1
	 */
	public static final int NUM_I_1 = 1;
	/**
	 * 常数2
	 */
	public static final int NUM_I_2 = 2;
	/**
	 * 常数3
	 */
	public static final int NUM_I_3 = 3;
	/**
	 * 常数180
	 */
	public static final int NUM_I_180 = 180;
	/**
	 * 上传类型：content: 内容详情（包括简介，列表图片，详情图片等）
	 */
	public static final String UPLOAD_TYPE_CONTENT= "content";
	
	/**
	 * 上传类型：avatar: 头像
	 */
	public static final String UPLOAD_TYPE_AVATAR= "avatar";
	
	/**
	 * 上传类型：file: 文件
	 */
	public static final String UPLOAD_TYPE_FILE= "file";
	
	/**
	 * 上传类型：slide: 轮播
	 */
	public static final String UPLOAD_TYPE_SLIDE= "slide";
	
	/**
	 * 上传类型：chat_img: 聊天图片
	 */
	public static final String UPLOAD_TYPE_CHAT_IMG= "chat_img";
	
	/**
	 * 上传类型：chat_audio: 聊天语音
	 */
	public static final String UPLOAD_TYPE_CHAT_AUDIO= "chat_audio";
	
	/**
	 * 上传类型：html: 静态页面文件
	 */
	public static final String UPLOAD_TYPE_HTML= "html";
	
	/**
	 * 上传类型：other: 其他功能图片（如客服图片等）
	 */
	public static final String UPLOAD_TYPE_OTHER= "other";
	
	/**
	 * 模板文件：知识
	 */
	public static final String H5_TEMPLATE_INFO = "html.template.info";
	
	/**
	 * 模板文件：专家
	 */
	public static final String H5_TEMPLATE_EXPERT = "html.template.expert";
	
	/**
	 * 模板文件：视频
	 */
	public static final String H5_TEMPLATE_VIDEO = "html.template.video";
	
	/**
	 * 模板文件：直播
	 */
	public static final String H5_TEMPLATE_LIVE = "html.template.live";
	
	/**
	 * 模板文件：课堂
	 */
	public static final String H5_TEMPLATE_COURSE = "html.template.course";
	
	/**
	 * 编码格式：UTF-8
	 */
	public static final String ENCODE_TYPE_UTF8 = "UTF-8";
	
	/**
	 * http:创建群组
	 */
	public static final String HTTP_CREATE_COURSE = "https://console.tim.qq.com/v4/group_open_http_svc/create_group?usersig={0}&apn=1&identifier={1}&sdkappid={2}&contenttype=json";
	
	/**
	 * http:解散群组
	 */
	public static final String HTTP_DESTROY_COURSE = "https://console.tim.qq.com/v4/group_open_http_svc/destroy_group?usersig={0}&apn=1&identifier={1}&sdkappid={2}&contenttype=json";
	
	/**
	 * http:获得历史消息
	 */
	public static final String HTTP_GET_HISTORY_MSG = "https://console.tim.qq.com/v4/group_open_http_svc/group_msg_get_simple?usersig={0}&apn=1&identifier={1}&sdkappid={2}&contenttype=json";
	
	/**
	 * http:获得群组消息
	 */
	public static final String HTTP_GET_GROUP_INFO = "https://console.tim.qq.com/v4/group_open_http_svc/get_group_info?usersig={0}&apn=1&identifier={1}&sdkappid={2}&contenttype=json";
	
	/**
	 * http:获得群组消息
	 */
	public static final String HTTP_ADD_GROUP_MEMBER = "https://console.tim.qq.com/v4/group_open_http_svc/add_group_member?usersig={0}&apn=1&identifier={1}&sdkappid={2}&contenttype=json";
	
	/**
	 * 云通讯sdkappid:1400021399
	 */
	public static final String SDKAPPID_YUNTONGXUN  = "1400021399";
	
	/**
	 * usersig 失效
	 */
	public static final Integer USER_SIG_DAY  = 180;
	
	/**
	 * 成功的返回值：success
	 */
	public static final String SUCCESS  = "success";
	
//	/**
//	 * 	课堂消息类型
//	 *  0:text,1:image,2:face,3:sound
//	 */
//	public final static Integer MSG_TYPE_TEXT = 0;
//	public static final Integer MSG_TYPE_IMAGE = 1;
//	public static final Integer MSG_TYPE_FACE = 2;
//	public static final Integer MSG_TYPE_SOUND = 3;
	
	/**
	 * 在线课堂每次拉取历史记录数
	 */
	public static final Integer REQ_MSG_NUMBER = 10;
	
	/**
	 * 空白
	 */
	public static final String BLANK = " ";

	/**
	 * yyyy-MM-dd
	 */
	public static final String datePattern = "yyyy-MM-dd";
	
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String dateTimePattern = "yyyy-MM-dd HH:mm";
	
	/**
	 * 图片大小:200K
	 */
	public static final Integer IMG_SIZE = 200;
	
	/**
	 * 图片宽：200
	 */
	public static final Integer IMG_WIDTH_200 = 200;

	/**
	 * 图片宽：140
	 */
	public static final Integer IMG_WIDTH_140 = 140;
	
	/**
	 * 图片宽：720
	 */
	public static final Integer IMG_WIDTH_720 = 720;
	
	/**
	 * 图片宽：192
	 */
	public static final Integer IMG_WIDTH_192 = 192;
	
	/**
	 * 图片高：140
	 */
	public static final Integer IMG_HEIGHT_140 = 140;
	
	/**
	 * 图片高：412
	 */
	public static final Integer IMG_HEIGHT_412 = 412;
	
	/**
	 * 图片高：148
	 */
	public static final Integer IMG_HEIGHT_148 = 148;
	
	/**
	 * APP_TYPE
	 */
	public static final String APP_TYPE = "appType";
	
	/**
	 * APP_TYPE 1：Android，2：IOS
	 */
	public static final Integer APP_TYPE_ANDROID = 1;
	
	/**
	 * APP_TYPE 1：Android，2：IOS
	 */
	public static final Integer APP_TYPE_IOS = 2;
	
	/**
	 * 专家模块拒绝理由的类型： 1，系统配置表code；2，自定义拒绝理由
	 */
	public static final Integer REFUSE_MEMO_TYPE_1 = 1;
	
	/**
	 * 专家模块拒绝理由的类型： 1，系统配置表code；2，自定义拒绝理由
	 */
	public static final Integer REFUSE_MEMO_TYPE_2 = 2;
}

