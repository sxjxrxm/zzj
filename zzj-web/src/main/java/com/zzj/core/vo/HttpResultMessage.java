/**
 * Project Name:zzj-web
 * File Name:HttpResultMessage.java
 * Package Name:com.zzj.core.vo
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.core.vo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * <p><strong>类名: </strong></p>HttpResultMessage <br/>
 * <p><strong>功能说明: </strong></p>前端请求结果JSON数据通用JAVABEAN. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年3月30日下午6:53:33 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@JsonInclude(value = Include.NON_EMPTY)
public class HttpResultMessage {
	public HttpResultMessage(){
	}
	
	@SuppressWarnings("unchecked")
	public HttpResultMessage(Object requestSign){
		this.requestSignMap = ((Map<String, Object>)requestSign);
	}

	/**
	 * http请求状态码
	 */
	private Integer httpCode = 200;
	/**
	 * 错误代码
	 */
	private String  errorCode = null;
	/**
	 * http请求返回的消息，可以用于业务自定义信息
	 * **/
	private String httpMessage;
	
	/**
	 * 系统架构捕获到的异常信息，测试机时期可以考虑在前端App显示出来，
	 * 考虑到该信息只能帮助修改程序错误，对于正式用户来讲不友好，正式上线后则关闭该信息显示，改成其他友好信息，如“系统繁忙，请稍后。”。
	 * **/
	private String sysExceptionMessage;
	/**
	 * http请求返回的业务数据
	 * **/
	private Object businessData;
	/**
	 * 新创建的实体ID值
	 * **/
	private Long entityId;
	/**
	 * 请求标识唯一码值
	 * **/
	private Map<String,Object> requestSignMap;
	
	/**
	 * 消息代码
	 */
	private String msgCode;
	
	/**
	 * 返回msgCode的值
	 * @return String msgCode的值
	 */
	public String getMsgCode() {
		return msgCode;
	}

	/**
	 * 设置msgCode的值
	 * @param  msgCode msgCode的值
	 */
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	/**
	 * 返回httpCode的值
	 * @return Integer httpCode的值
	 */
	public Integer getHttpCode() {
		return httpCode;
	}
	/**
	 * 返回httpMessage的值
	 * @return String httpMessage的值
	 */
	public String getHttpMessage() {
		return httpMessage;
	}
	/**
	 * 返回businessData的值
	 * @return Object businessData的值
	 */
	public Object getBusinessData() {
		return businessData;
	}
	/**
	 * 设置httpCode的值
	 * @param  httpCode httpCode的值
	 */
	public void setHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
	}
	/**
	 * 设置httpMessage的值
	 * @param  httpMessage httpMessage的值
	 */
	public void setHttpMessage(String httpMessage) {
		this.httpMessage = httpMessage;
	}
	/**
	 * 设置businessData的值
	 * @param  businessData businessData的值
	 */
	public void setBusinessData(Object businessData) {
		this.businessData = businessData;
	}
	/**
	 * 返回entityId的值
	 * @return Long entityId的值
	 */
	public Long getEntityId() {
		return entityId;
	}
	/**
	 * 设置entityId的值
	 * @param  entityId entityId的值
	 */
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	/**
	 * 返回requestSignMap的值
	 * @return Map<String,Object> requestSignMap的值
	 */
	public Map<String, Object> getRequestSignMap() {
		return requestSignMap;
	}

	/**
	 * 设置requestSignMap的值
	 * @param  requestSignMap requestSignMap的值
	 */
	public void setRequestSignMap(Map<String, Object> requestSignMap) {
		this.requestSignMap = requestSignMap;
	}

	/**
	 * 返回sysExceptionMessage的值
	 * @return String sysExceptionMessage的值
	 */
	public String getSysExceptionMessage() {
		return sysExceptionMessage;
	}

	/**
	 * 设置sysExceptionMessage的值
	 * @param  sysExceptionMessage sysExceptionMessage的值
	 */
	public void setSysExceptionMessage(String sysExceptionMessage) {
		this.sysExceptionMessage = sysExceptionMessage;
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

