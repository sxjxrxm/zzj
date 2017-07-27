/**
 * Project Name:zzj-web
 * File Name:IndexController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzj.core.exception.BusinessException;
import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstScreenInfo;
import com.zzj.manage.service.IndexService;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>IndexController <br/>
 * <p><strong>功能说明: </strong></p>首页表示处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月13日上午12:33:39 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
public class IndexController extends BaseController {

	/**
	 * 主页操作Service
	 */
	@Autowired
	private IndexService indexService;
	
	/**
	 * 跳转到系统主页页面。<br/>
	 * @param  request 请求实例
	 * @return 返回页面名称
	 */
	@RequestMapping(value="/index.htm")
	public String index(HttpServletRequest request) {

		// 画面菜单生成
		@SuppressWarnings("unchecked")
		List<MstScreenInfo> screenList = (List<MstScreenInfo>) request.getSession().getAttribute(ZzjConstants.SYS_SESSION_ROLE);
		request.setAttribute("screenList", screenList) ;
		
		// 将系统配置信息存入session，页面布局使用
		if(indexService.saveMstCodeInfoToSessionSuccess(request)) {
			// 存放成功，进入主页
			return "index";
		}else {
			// 存放失败，抛出异常系统异常
			throw new BusinessException("系统异常");
		}
	}
}

