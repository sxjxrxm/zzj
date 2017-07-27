/**
 * Project Name:zzj-web
 * File Name:TokenException.java
 * Package Name:com.zzj.core.exception
 * Copyright © 北京雅泰联合集团  All Rights Reserved.
*/

package com.zzj.core.exception;
/**
 * <p><strong>类名: </strong></p>TokenException <br/>
 * <p><strong>功能说明: </strong></p>登录TOKEN异常类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年3月30日下午7:01:56 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class TokenException extends Exception {
	/**
	 * 序列化ID。
	 */
	private static final long serialVersionUID = 4914529448042154460L;
	/**
	 * 错误代码，第一种是token为空，第二种是token码有误
	 */
	private String errorCode;
	/**
	 * 构造TokenException <br>
	 * @param message 异常信息
	 */
	public TokenException(String message) {
		super(message);
	}
	/**
	 * 构造TokenException <br>
	 * @param errorCode 错误码
	 * @param message 异常信息
	 */
	public TokenException(String errorCode,String message) {
		super(message);
		this.errorCode = errorCode;
	}
	/**
	 * 构造TokenException <br>
	 * @param message 异常信息
	 * @param e 父异常实例
	 */
	public TokenException(String message,Exception e){
		super(message,e);
	}
	/**
	 * 返回errorCode的值
	 * @return String errorCode的值
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * 设置errorCode的值
	 * @param  errorCode errorCode的值
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}

