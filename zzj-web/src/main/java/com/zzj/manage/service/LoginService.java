/**
 * Project Name:zzj-web
 * File Name:LoginService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p><strong>类名: </strong></p>LoginService <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装登录模块. 有登录账户密码判断，验证码判断，更新验证码，登出<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月12日下午11:37:02 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface LoginService {
	
	/**
	 * 后台校验登录是否成功<br/>
	 * @param request 请求实例
	 * @return true 登录成功， false 登录失败
	 * @throws Exception
	 */
	public boolean isLoginSuccess(HttpServletRequest request) throws Exception;
	
	/**
	 * 后台校验验证码是否正确<br/>
	 * @param request 请求数据
	 * @return true 正确， false 错误
	 * @throws Exception
	 */
	public boolean isAuthCodeRight(HttpServletRequest request);
	
	/**
	 * 前台验证码请求。<br/>
	 * @param request 请求数据
	 * @param response 相应数据，返回验证码图片
	 * @param response session存放验证码信息
	 * @throws IOException
	 */
    public void getAuthCode(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException;

    /**
	 * 退出登录，清空session中存放的用户信息。<br/>
	 * @param request 请求实例
	 */
	public void logout(HttpServletRequest request);
	
}

