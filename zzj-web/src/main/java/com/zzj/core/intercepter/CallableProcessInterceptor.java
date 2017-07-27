/**
 * Project Name:zzj-web
 * File Name:CallableProcessInterceptor.java
 * Package Name:com.zzj.core.intercepter
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.core.intercepter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzj.core.vo.HttpResultMessage;
import com.zzj.util.StringUtil;


/**
 * <p><strong>类名: </strong></p>CallableProcessInterceptor <br/>
 * <p><strong>功能说明: </strong></p>异步请求拦截器，后期需要加入系统级日志处理。 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年4月7日下午1:17:41 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Component
public class CallableProcessInterceptor implements CallableProcessingInterceptor {
	private static final Logger fileLog = LoggerFactory.getLogger(CallableProcessInterceptor.class);
    private ObjectMapper jsonMapper = new ObjectMapper(); 

	public <T> void beforeConcurrentHandling(NativeWebRequest request,Callable<T> task) throws Exception {
	}
	public <T> void preProcess(NativeWebRequest request, Callable<T> task)
			throws Exception {
	}

	public <T> void postProcess(NativeWebRequest request, Callable<T> task,
			Object concurrentResult) throws Exception {
		HttpServletRequest httpServletRequest = request.getNativeRequest(HttpServletRequest.class);
		if(concurrentResult instanceof Exception){
			Exception e = (Exception)concurrentResult;
			String requestInfo = requestInfo(httpServletRequest);
			fileLog.error("\n系统运行异常:"+requestInfo+"\n"+e.getMessage(),e);
		}else
			System.out.println("返回的数据："+jsonMapper.writeValueAsString(concurrentResult));
	}
	public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task)
			throws Exception {
		HttpServletRequest httpServletRequest = request.getNativeRequest(HttpServletRequest.class);
		HttpResultMessage httpResultMessage = new HttpResultMessage();
		String timeOutStr = "请求超时！";
		String requestInfo = requestInfo(httpServletRequest);
		fileLog.error("系统请求超时："+requestInfo);
		httpResultMessage.setHttpCode(500);
		httpResultMessage.setSysExceptionMessage(timeOutStr);
		System.out.println("返回的数据："+jsonMapper.writeValueAsString(httpResultMessage));
		return httpResultMessage;
	}

	public <T> void afterCompletion(NativeWebRequest request, Callable<T> task)
			throws Exception {
	}
	
	private String requestInfo(HttpServletRequest httpServletRequest) throws JsonProcessingException{
		String url = httpServletRequest.getServletPath()
				+ (httpServletRequest.getPathInfo() != null ? httpServletRequest.getPathInfo() : "");
		Map<?,?> parameterMap = httpServletRequest.getParameterMap();
		List<String> parameterList = new ArrayList<String>(parameterMap.keySet().size());
		String ip = StringUtil.getRemoteIPAddress(httpServletRequest);
		for(Object key:parameterMap.keySet()){
			String[] parameters = httpServletRequest.getParameterValues(String.valueOf(key));
			if(StringUtil.isNotBlank(parameters)){
				if(parameters.length==1){
					parameterList.add(key+":"+parameters[0]);
				}else if(parameters.length>1){
					parameterList.add(key+":"+jsonMapper.writeValueAsString(parameters));
				}else
					parameterList.add(key+":null");
			}
		}
		String parameterStr = jsonMapper.writeValueAsString(parameterList);
		return "\nurl："+url+"\n请求IP："+ip+"\n请求参数："+parameterStr;
	}

}

