/**
 * Project Name:zzj-base
 * File Name:ValidateErrorException.java
 * Package Name:com.zzj.core.exception
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.core.exception;

import java.util.ArrayList;

import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>ValidateErrorException <br/>
 * <p><strong>功能说明: </strong></p>Service验证异常时，返回对应的验证错误信息<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年4月18日下午6:45:46 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class ValidateErrorException extends Exception {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
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
	 * 错误消息表示字段ID列表
	 */
	private ArrayList<String> errorItemKeyList = new ArrayList<String>();
	
	/**
	 * 错误消息列表
	 */
	private ArrayList<String> errorMsgList = new ArrayList<String>();

	/**
	 * 构造ServiceErrorException实例。<br/>
	 */
	public ValidateErrorException() {
		super();
	}
	
	/**
	 * 构造ServiceErrorException实例。<br/>
	 * @param  message 异常消息
	 */
	public ValidateErrorException(String message) {
		super(message);
		this.addError(message, null);
	}

	/**
	 * 构造ServiceErrorException实例。<br/>
	 * @param  errorCode 异常消息代码
	 * @param  msgArgs 异常消息参数
	 * @param  errorPage 错误页面
	 * @param  errorItemKey 错误项目ID
	 */
	public ValidateErrorException(String errorCode, Object[] msgArgs, String errorPage, String errorItemKey) {
		super(PropertyUtil.getMessageContent(errorCode, msgArgs));

		this.setErrorPage(errorPage);
		this.addError(errorCode, msgArgs, errorItemKey);
	}

	/**
	 * 构造ServiceErrorException实例。<br/>
	 * @param  message 异常消息
	 * @param  errorPage 错误页面
	 * @param  errorItemKey 错误项目ID
	 */
	public ValidateErrorException(String errorMsg, String errorPage, String errorItemKey) {
		super(errorMsg);
		
		this.setErrorPage(errorPage);
		this.addError(errorMsg, errorItemKey);
	}
	
	/**
	 * 追加异常消息处理<br/>
	 * 有复数错误输出的场合，需要往例外中追加新的异常消息<br/>
	 * 追加的内容包括错误消息，错误项目ID
	 * @param  errorCode 异常消息代码
	 * @param  msgArgs 异常消息参数
	 * @param  errorItemKey 错误项目ID
	 * @return 无
	 * @throws Exception
	 */
	public void addError(String errorCode, Object[] msgArgs, String errorItemKey){
		String message = PropertyUtil.getMessageContent(errorCode, msgArgs);
		this.addError(message, errorItemKey);
	}
	
	/**
	 * 追加异常消息处理<br/>
	 * 有复数错误输出的场合，需要往例外中追加新的异常消息<br/>
	 * 追加的内容包括错误消息，错误项目ID
	 * @param  msgArgs 异常消息参数
	 * @param  errorItemKey 错误项目ID
	 * @return 无
	 * @throws Exception
	 */
	public void addError(String message, String errorItemKey){
		// 追加错误消息内容
		if(!StringUtil.isNullOrEmpty(message)) {
			this.errorMsgList.add(message);
		}
		
		// 追加错误项目ID
		if(!StringUtil.isNullOrEmpty(errorItemKey)) {
			this.errorItemKeyList.add(errorItemKey);
		}
	}

	/**
	 * 构造ServiceErrorException实例。<br/>
	 * @param  cause 父异常实例
	 */
	public ValidateErrorException(Throwable cause) {
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
	 * 返回errorKeyList的值
	 * @return ArrayList<String> errorKeyList的值
	 */
	public ArrayList<String> getErrorKeyList() {
		return errorItemKeyList;
	}

	/**
	 * 设置errorKeyList的值
	 * @param  errorKeyList errorKeyList的值
	 */
	public void setErrorKeyList(ArrayList<String> errorKeyList) {
		this.errorItemKeyList = errorKeyList;
	}

	/**
	 * 返回errorMsgList的值
	 * @return ArrayList<String> errorMsgList的值
	 */
	public ArrayList<String> getErrorMsgList() {
		return errorMsgList;
	}

	/**
	 * 设置errorMsgList的值
	 * @param  errorMsgList errorMsgList的值
	 */
	public void setErrorMsgList(ArrayList<String> errorMsgList) {
		this.errorMsgList = errorMsgList;
	}
}

