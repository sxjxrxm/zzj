/**
 * Project Name:zzj-web
 * File Name:AdService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.Map;

/**
 * <p><strong>类名: </strong></p>QcloudIMService <br/>
 * <p><strong>功能说明: </strong></p>腾讯云 云通信<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月18日下午5:02:55 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface QcloudIMService {
	
	
	/**
	 * 腾讯云 云通信 群消息发送前回调
	 * @param SdkAppid APP在云通讯申请的Appid
	 * @param CallbackCommand 固定为：Group.CallbackBeforeSendMsg。
	 * @param contenttype 固定为：json。
	 * @param ClientIP 客户端IP地址。
	 * @param OptPlatform 客户端平台。对应不同的平台类型，可能的取值有：
						  RESTAPI（使用REST API发送请求）、Web（使用Web SDK发送请求）、
						  Android、iOS、Windows、Mac、Unkown（使用未知类型的设备发送请求）。
	 * @param msgLogVO 聊天记录
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	Object callbackBefore(String SdkAppid, String CallbackCommand, String contenttype,
						String ClientIP, String OptPlatform, Map<String, Object> postMap);
	
	/**
	 * 下载语音并保存到本地
	 * @param URL 语音地址
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	void downloadSound(String url, String groupId, String uuid) throws Exception;
}

