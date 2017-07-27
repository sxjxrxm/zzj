/**
 * Project Name:zzj-web
 * File Name:IndexService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p><strong>类名: </strong></p>IndexService <br/>
 * <p><strong>功能说明: </strong></p>这个类是系统主页模块的代码. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月17日下午4:00:42 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface IndexService {

	/**
	 * 将系统配置信息存入session，页面显示调用<br/>
	 * @param request http请求实例
	 * @return true 保存成功， false 保存失败
	 */
	public boolean saveMstCodeInfoToSessionSuccess(HttpServletRequest request);
	
}

