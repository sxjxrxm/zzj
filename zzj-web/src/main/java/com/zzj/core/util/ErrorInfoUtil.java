/**
 * Project Name:zzj-web
 * File Name:ErrorInfoUtil.java
 * Package Name:com.zzj.core.util
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.core.util;

import com.zzj.core.vo.HttpResultMessage;
import com.zzj.util.PropertyUtil;
import com.zzj.core.exception.ValidateErrorException;

import org.springframework.util.StringUtils;

/**
 * <p><strong>类名: </strong></p>ErrorInfoUtil <br/>
 * <p><strong>功能说明: </strong></p>错误信息处理共通. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年4月26日下午1:45:23 <br/>
 * @author   马青山
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class ErrorInfoUtil {
	/**
	 * 错误信息处理共通
	 * @param  httpResultMessage 接口返回结果
	 * @param  errorInfo 错误信息
	 * @return HttpResultMessage 接口返回结果
	 * @throws Exception
	 */
	public static HttpResultMessage getErrorInfo(HttpResultMessage httpResultMessage,String errorInfo) throws Exception{
		httpResultMessage.setHttpMessage(errorInfo);
		httpResultMessage.setHttpCode(500);
		return httpResultMessage;
	}
	/**
	 * 错误信息处理共通
	 * @param  errorCode 错误代码，需要从配置文件中取对应的错误文本信息
	 * @param  httpResultMessage 接口返回结果实例
	 * @return HttpResultMessage 接口返回结果
	 * @throws Exception
	 */
	public static HttpResultMessage getErrorInfo(String errorCode,HttpResultMessage httpResultMessage) throws Exception{

		// 输入参数确认
		if(httpResultMessage == null) {
			httpResultMessage = new HttpResultMessage();
		}
		
		// 设置响应Code
		httpResultMessage.setHttpCode(500);
		
		// 错误消息取得
		String message = "";
		if(!StringUtils.isEmpty(errorCode) && !"result".equals(errorCode) && !"errorCode".equals(errorCode)) {
			// 如果错误消息代码不为空的场合，取得错误消息内容
			message = PropertyUtil.getMessageContent(errorCode, null);
			// 设置错误代码
			httpResultMessage.setErrorCode(errorCode);
		}
		
		// 设置返回错误消息
		httpResultMessage.setHttpMessage(message);
		
		return httpResultMessage;
	}
	
	/**
	 * 错误信息处理共通
	 * @param  HttpResultMessage 接口返回结果
	 * @param  ValidateErrorException 业务异常信息
	 * @return HttpResultMessage 接口返回结果
	 * @throws Exception 
	 */
	public static HttpResultMessage getErrorInfo(HttpResultMessage httpResultMessage, ValidateErrorException vee) throws Exception{
		
		// 输入参数确认
		if(httpResultMessage == null) {
			httpResultMessage = new HttpResultMessage();
		}
		
		// 设置响应Code
		httpResultMessage.setHttpCode(500);
		
		// 错误消息取得
		String message = vee.getMessage();
		String errorCode = vee.getErrorCode();
		if(!StringUtils.isEmpty(errorCode) && !"result".equals(errorCode) && !"errorCode".equals(errorCode)) {
			// 如果错误消息代码不为空的场合，取得错误消息内容
			message = PropertyUtil.getMessageContent(vee.getErrorCode(), vee.getMsgArgs());
			// 设置错误代码
			httpResultMessage.setErrorCode(vee.getErrorCode());
		}
		
		// 设置返回错误消息
		httpResultMessage.setHttpMessage(message);
		
		return httpResultMessage;
	}
}

