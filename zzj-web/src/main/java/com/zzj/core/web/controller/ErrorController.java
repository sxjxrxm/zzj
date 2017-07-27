/**
 * Project Name:zzj-web
 * File Name:ErrorController.java
 * Package Name:com.zzj.core.web.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.core.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzj.util.PropertyUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>ErrorController <br/>
 * <p><strong>功能说明: </strong></p>错误处理控制器<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月20日下午6:49:37 <br/>
 * @author   康冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
public class ErrorController extends BaseController {

	/**
	 * 系统错误处理
	 * @param  无
	 * @return String 返回错误页面地址
	 * @throws Exception
	 */
	@RequestMapping(value="/error")
	public String goError(HttpServletRequest request) {
		
		if(request.getAttribute(ZzjConstants.SYS_ERROR_KEY) == null){
			request.setAttribute(ZzjConstants.SYS_ERROR_KEY, PropertyUtil.getMessageContent("E1000030", null));
		}
		
		return "error/error";
	}
	
	/**
	 * Session过期处理
	 * @param  无
	 * @return String 返回错误页面地址
	 * @throws Exception
	 */
	@RequestMapping(value="/sessionExpired")
	public String sessionExpired(HttpServletRequest request) {

		request.setAttribute(ZzjConstants.SYS_ERROR_KEY, PropertyUtil.getMessageContent("E1000031", null));
		
		return "error/error_expired";
	}
}

