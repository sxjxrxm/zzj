/**
 * Project Name:zzj-web
 * File Name:BusinessJsonException.java
 * Package Name:com.zzj.core.exception
 * Copyright © 北京雅泰联合集团  All Rights Reserved.
*/

package com.zzj.core.exception;
/**
 * <p><strong>类名: </strong></p>BusinessJsonException <br/>
 * <p><strong>功能说明: </strong></p>系统框架遇到该异常，返回对应的异常信息，格式是JSON. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年3月31日上午9:13:54 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class BusinessJsonException extends Exception {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 6472412530265365676L;
	/**
	 * 构造BusinessJsonException <br>
	 * @param message 异常信息
	 */
	public BusinessJsonException(String message) {
		super(message);
	}
	/**
	 * 构造BusinessJsonException <br>
	 * @param message 异常信息
	 * @param e 父异常实例
	 */
	public BusinessJsonException(String message,Exception e){
		super(message,e);
	}

}

