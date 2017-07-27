/**
 * Project Name:zzj-web
 * File Name:VersionService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartRequest;

/**
 * <p><strong>类名: </strong></p>VersionService <br/>
 * <p><strong>功能说明: </strong></p>版本控制模块 <br/>
 * <p><strong>创建日期: </strong><br/></p>2017年1月3日下午2:09:22 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface VersionService {
	/**
	 * 页面Apk异步请求<br/>
	 * @param  file 文件请求
	 * @param  req 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 */
	Map<String, String> queryApk(MultipartRequest file, HttpServletRequest req);

	/**
	 * 加载页面数据。<br/>
	 * @param  request 请求实例
	 */
	void load(HttpServletRequest request);

	/**
	 * 保存apk
	 * @param  request 请求实例
	 * @return 保存结果 true 成功 false 失败
	 * @throws Exception
	 */
	boolean save(HttpServletRequest request) throws Exception;
}

