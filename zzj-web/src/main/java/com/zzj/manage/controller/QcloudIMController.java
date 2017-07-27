/**
 * Project Name:zzj-web
 * File Name:EnterpriseController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzj.core.web.controller.BaseController;
import com.zzj.manage.service.QcloudIMService;

/**
 * <p><strong>类名: </strong></p>QcloudIMController <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装云通信 群消息收发管理模块. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月17日下午1:52:19 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping(value="qcloudim")
public class QcloudIMController extends BaseController {
	
	/**
	 * 腾讯云消息记录主表操作类
	 */
	@Autowired
	private QcloudIMService qcloudIMService;
	
	
	
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
	@RequestMapping(value="/callbackBefore.htm", method=RequestMethod.POST)
	public void callbackBefore(HttpServletRequest request, HttpServletResponse response,
			String SdkAppid, String CallbackCommand, String contenttype,
			String ClientIP, String OptPlatform, @RequestBody Map<String, Object> postMap) throws Exception{
		//设置回答头
		response.setContentType("application/json;charset=UTF-8");
		//保存数据
		Object object = qcloudIMService.callbackBefore(SdkAppid, CallbackCommand, contenttype, 
										ClientIP, OptPlatform, postMap);
		PrintWriter out = null;
		try{
			out = response.getWriter();
			out.println(object);
			out.flush();
		}catch(IOException e){
			e.printStackTrace();  
		}finally {
			 if (null != out) {  
				 out.close();  
			 } 
		}
		
	}
	
	/**
	 * 下载语音并保存到本地。
	 * @param request 请求实例
	 * @return 数据类型  代表业务逻辑意义，比如用户记录总条数
	 * @throws Exception
	 */
	@RequestMapping(value="/downloadSound.htm", method=RequestMethod.POST)
	public void downloadSound(HttpServletRequest request) throws Exception {
		//获取参数
		String url = request.getParameter("url");
		String groupId = request.getParameter("groupId");
		String uuid = request.getParameter("uuid");
		qcloudIMService.downloadSound(url, groupId, uuid);
	}
}

