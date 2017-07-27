/**
 * Project Name:zzj-web
 * File Name:BaseController.java
 * Package Name:com.zzj.core.web.controller
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.core.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.zzj.core.exception.BusinessException;
import com.zzj.core.exception.ValidateErrorException;
import com.zzj.core.web.converter.DateEditor;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>BaseController <br/>
 * <p><strong>功能说明: </strong></p>控制器基类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年3月30日下午7:00:39 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class BaseController {
	
	/**
	 * 日志对象
	 * **/
	Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	/**
	 * 请求路径
	 **/
	public String requestURL = null;

	/**
	 * 附件跟文件夹路径
	 **/
	public static final String BASE_DIR = "resources";

	/**
	* 初始化绑定<br/>
	* @param request http请求实例
	* @param binder 绑定实例
	* @throws Exception
	* */
	@InitBinder  
	public void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {   
	    binder.registerCustomEditor(Date.class, new DateEditor());       
	}
	
	/**
	* 异常处理，返回异常提示信息<br/>
	* @param ex 异常实例
	* @return String 错误消息地址
	* */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.OK)//INTERNAL_SERVER_ERROR
	public String handleException(Exception ex) {
		ArrayList<String> errorMessage = new ArrayList<String>();
		String errorPage = ZzjConstants.SYS_ERROR_PAGE;
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		try {
			if(ex instanceof ValidateErrorException){
				// 验证错误的场合
				ValidateErrorException ve = (ValidateErrorException)ex;
				// 设置跳转页面
				errorPage = StringUtil.isNullOrEmpty(ve.getErrorPage()) ? errorPage : ve.getErrorPage();
				// 设置错误项目ID 
				request.setAttribute(ZzjConstants.SYS_ERROR_ITEM_KEY, ve.getErrorKeyList());
				// 设置错误消息
				errorMessage.addAll(ve.getErrorMsgList());
				// 返回错误页面
				return errorPage;
				
			} else if(ex instanceof BusinessException){
				// 业务错误的场合
				BusinessException be = (BusinessException)ex;
				// 设置跳转页面
				errorPage = StringUtil.isNullOrEmpty(be.getErrorPage()) ? errorPage : be.getErrorPage();
				// 设置错误消息
				errorMessage.add(be.getMessage());
				// 返回错误页面
				return errorPage;
				
			} else if(ex instanceof MaxUploadSizeExceededException){
				// 设置错误消息
				errorMessage.add("上传的文件超过文件大小的最大值："+ ex.getMessage());
			} else
				// 设置错误消息
				errorMessage.add(ex.getMessage());
			errorMessage.add(0, "系统运行异常：");
		} catch (Exception e) {
			errorMessage.add("系统运行异常：");
			errorMessage.add(ex.getMessage());
		} finally{
			logger.error(errorMessage.toString(), ex);
			
			// 设置页面返回错误信息
			request.setAttribute(ZzjConstants.SYS_ERROR_KEY, errorMessage);
		}
		
		return errorPage;
	}
	
//	/**
//	 * 根据验证异常设置显示的错误消息
//	 * @param  ve 验证异常对象
//	 * @return String 错误消息
//	 * @throws Exception
//	 */
//	private final String getErrorMessage(ValidateErrorException ve) {
//		StringBuffer errorMessage = new StringBuffer();
//		
//		ArrayList<String> messageList = ve.getErrorMsgList();
//		for (String msg : messageList) {
//			errorMessage.append(msg).append("<br/>");
//		}
//		
//		return errorMessage.toString();
//	}
	
	/**
	 * 获取基于应用程序的url绝对路径<br/>
	 * @param request http请求对象
	 * @param url 以"/"打头的URL地址
	 * @return 基于应用程序的url绝对路径
	 */
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		Assert.hasLength(url, "url不能为空");
		Assert.isTrue(url.startsWith("/"), "必须以/打头");
		return request.getContextPath() + url;
	}
	
	/**
	 * 将对应对应保存在SESSION中<br/>
	 * @param key 要保存的数据对象的键值
	 * @param obj 保存的实际数据对象
	 * **/
	public void putObjInSession(String key,Object obj){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().setAttribute(key, obj);
	}
	
	/**
	 * 通过键值获取保存在SESSION中的对应的数据对象<br/>
	 * @param key 保存的数据对象的键值
	 * @return Object 保存在SESSION中的对应的数据对象
	 * **/
	public Object getObjFromSession(String key){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession().getAttribute(key);
	}
	
	/**
	 * 通过键值清除保存在SESSION中的对应的数据对象<br/>
	 * @param key 保存的数据对象的键值
	 * **/
	public void clearObjFromSession(String key){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().removeAttribute(key);
	}
	
	/**
	 * 清除所有保存在SESSION中的对应的数据对象<br/>
	 * **/
	public void clearAllObjFromSession(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().invalidate();
	}

	/**
	 * 获取请求路径
	 **/
	public String getrequestURL() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		requestURL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		return requestURL;
	}
	
	/**
	 * 获取web服务的跟文件夹路径
	 **/
	public String getWebDirectoryPath() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String savePath = request.getSession().getServletContext().getRealPath("/");
		if(!savePath.endsWith(File.separator)) {
			savePath = savePath + File.separator;
	    }
		logger.info("web服务的跟文件夹路径:"+savePath);
		return savePath;
	}

	/**
	 * 请求之间交互的消息
	 * */
	private String message = null;
	/**
	 * 回调类型
	 * */
	private String callbackType = null;
	/**
	 * 跳转URL
	 * */
	private String forwardUrl = null;
	/**
	 * 返回message的值
	 * @return String message的值
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 返回callbackType的值
	 * @return String callbackType的值
	 */
	public String getCallbackType() {
		return callbackType;
	}
	/**
	 * 返回forwardUrl的值
	 * @return String forwardUrl的值
	 */
	public String getForwardUrl() {
		return forwardUrl;
	}
	/**
	 * 设置requestURL的值
	 * @param  requestURL requestURL的值
	 */
	public void setrequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	/**
	 * 设置message的值
	 * @param  message message的值
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 设置callbackType的值
	 * @param  callbackType callbackType的值
	 */
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}
	/**
	 * 设置forwardUrl的值
	 * @param  forwardUrl forwardUrl的值
	 */
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	} 
}

