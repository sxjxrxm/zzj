/**
 * Project Name:zzj-web
 * File Name:AuthService.java
 * Package Name:com.zzj.core.web.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.core.web.service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p><strong>类名: </strong></p>AuthService <br/>
 * <p><strong>功能说明: </strong></p>页面访问权限Service<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午6:52:03 <br/>
 * @author   康冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface AuthService {

	/**
	 * 验证是否有权限访问该页面
	 * @param  uri 访问路径
	 * @param  request http请求对象
	 * @return boolean 是否有权限（true:有;false:无）
	 * @throws Exception
	 */
	public boolean isAuth(HttpServletRequest request, String uri) throws Exception;
}

