/**
 * Project Name:zzj-web
 * File Name:BusinessException.java
 * Package Name:com.zzj.core.exception
 * Copyright ©   All Rights Reserved.
*/
package com.zzj.core.exception;

import com.zzj.util.PropertyUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>BusinessException <br/>
 * <p><strong>功能说明: </strong></p>业务异常类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2015-4-22下午13:47:47 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class BusinessException extends RuntimeException{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -445939667484246706L;

	/**
	 * 异常代码
	 */
	private String errorCode;
	
	/**
	 * 消息参数数组
	 */
	private Object[] msgArgs;
	
	/**
	 * 错误消息路径
	 */
	private String errorPage = ZzjConstants.SYS_ERROR_PAGE;
	
	/**
	 * 错误消息表示字段ID
	 */
	private String errorMsgKey = ZzjConstants.SYS_ERROR_KEY;

	/**
	 * 构造ServiceErrorException实例。<br/>
	 */
	public BusinessException() {
		super();
	}
	
	/**
	 * 构造ServiceErrorException实例。<br/>
	 * @param  message 异常消息
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * 构造ServiceErrorException实例。<br/>
	 * @param  errorCode 异常消息代码
	 * @param  msgArgs 异常消息参数
	 * @param  errorPage 错误页面
	 * @param  errorMsgKey 错误消息字段ID
	 */
	public BusinessException(String errorCode, Object[] msgArgs, String errorPage) {
		super(PropertyUtil.getMessageContent(errorCode, msgArgs));
		
		this.setErrorCode(errorCode);
		this.setMsgArgs(msgArgs);
		this.setErrorPage(errorPage);
	}

	/**
	 * 构造ServiceErrorException实例。<br/>
	 * @param  errorCode 异常消息代码
	 * @param  msgArgs 异常消息参数
	 * @param  errorPage 错误页面
	 * @param  errorMsgKey 错误消息字段ID
	 */
	public BusinessException(String message, String errorPage) {
		super(message);
		
		this.setErrorPage(errorPage);
	}

	/**
	 * 构造ServiceErrorException实例。<br/>
	 * @param  cause 父异常实例
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}

	/**
	 * 取得该异常对应的错误代码
	 * @return String 错误代码
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 设置该异常对应的错误代码
	 * @param  String 错误代码
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 取得消息内容参数
	 * @return Object[] 消息内容参数
	 */
	public Object[] getMsgArgs() {
		return msgArgs;
	}

	/**
	 * 设置消息内容参数
	 * @param msgArgs 消息内容参数
	 */
	public void setMsgArgs(Object[] msgArgs) {
		this.msgArgs = msgArgs;
	}

	/**
	 * 返回errorPage的值
	 * @return String errorPage的值
	 */
	public String getErrorPage() {
		return errorPage;
	}

	/**
	 * 设置errorPage的值
	 * @param  errorPage errorPage的值
	 */
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	/**
	 * 返回errorMsgKey的值
	 * @return String errorMsgKey的值
	 */
	public String getErrorMsgKey() {
		return errorMsgKey;
	}

	/**
	 * 设置errorMsgKey的值
	 * @param  errorMsgKey errorMsgKey的值
	 */
	public void setErrorMsgKey(String errorMsgKey) {
		this.errorMsgKey = errorMsgKey;
	}
}

