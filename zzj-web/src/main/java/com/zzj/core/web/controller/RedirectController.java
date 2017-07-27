/**
 * Project Name:zzj-web
 * File Name:RedirectController.java
 * Package Name:com.zzj.core.web.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.core.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p><strong>类名: </strong></p>RedirectController <br/>
 * <p><strong>功能说明: </strong></p>页面跳转控制器<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月25日下午2:05:44 <br/>
 * @author   康冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
public class RedirectController extends BaseController {

	/**
	 * 登录页面跳转处理
	 * @param  request Http请求
	 * @return String 返回跳转页面地址
	 * @throws Exception
	 */
	@RequestMapping(value="/goLogin")
	public String goLogin(HttpServletRequest request) {
		
		return "common/redirect";
	}
}

