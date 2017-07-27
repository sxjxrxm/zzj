/**
 * Project Name:zzj-web
 * File Name:VersionController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.zzj.core.web.controller.BaseController;
import com.zzj.manage.service.VersionService;
import com.zzj.util.PropertyUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>VersionController <br/>
 * <p><strong>功能说明: </strong></p>版本控制模块 <br/>
 * <p><strong>创建日期: </strong><br/></p>2017年1月3日下午2:09:22 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("version")
public class VersionController extends BaseController {
	/**
	 * 业务操作Service
	 */
	@Autowired
	private  VersionService versionService;
	
	/**
	 * 跳转到版本编辑页面。<br/>
	 * @param  request 请求实例
	 * @return 编辑页面
	 */
	@RequestMapping(value="/edit.htm")
	public String edit(HttpServletRequest request) {
		
		versionService.load(request);
		
		return "version/version_edit";
	}
	
	/**
	 * 保存版本。<br/>
	 * @param  request 请求实例
	 * @return 编辑页面
	 * @throws Exception
	 */
	@RequestMapping(value="/save.htm")
	public String save(HttpServletRequest request) throws Exception{
		
		if (versionService.save(request)) {
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));
		} else {
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000008", null));
		}
		
		return "version/version_edit";
	}
	
	/**
	 * 页面Apk异步请求<br/>
	 * @param  file 文件请求
	 * @param  request 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadApk.htm")
	public Map<String, String> queryApk(MultipartRequest file, HttpServletRequest req) throws Exception{
		Map<String, String> resultMap = versionService.queryApk(file, req);
		return resultMap;
	}
}

