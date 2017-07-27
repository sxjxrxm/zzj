/**
 * Project Name:zzj-web
 * File Name:IndexController.java
 * Package Name:com.zzj.manage
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.manage.service.LoginService;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>LoginController <br/>
 * <p><strong>功能说明: </strong></p>登陆用Controller <br/>
 * <strong>创建日期: </strong></p>2016年11月12日下午11:32:31<br/>
 * 
 * @author 任晓茂
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class LoginController extends BaseController {

	/**
	 * 登录业务操作Service
	 */
	@Autowired
	private LoginService loginService;

	/**
	 * 跳转到登录页面。<br/>
	 * 
	 * @param request 请求实例
	 * @return 返回页面名称
	 */
	@RequestMapping(value = "/login")
	public String toLoginPage(HttpServletRequest request) {
		return "login";
	}

	/**
	 * 后台登录验证。<br/>
	 * 
	 * @param request 请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/login/check")
	public String login(HttpServletRequest request) throws Exception {
		/*
		 * 判断是否已经登录
		 */
		MstUsersInfo existUser = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		if (existUser != null) {
			return "redirect:/index.htm";
		}

		/*
		 * 验证码校验
		 */
		if (!loginService.isAuthCodeRight(request)) {
			return "login";
		}

		/*
		 * 请求成功：用户名密码正确 && 用户未被锁定 请求失败：用户名或密码不正确 || 用户被锁定
		 */
		return loginService.isLoginSuccess(request) ? "redirect:/index.htm" : "login";
	}

	/**
	 * 前台验证码请求。<br/>
	 * 
	 * @param request 请求实例
	 * @param response 相应数据，返回验证码图片
	 * @param session存放验证码信息
	 * @throws IOException
	 */
	@RequestMapping(value = "/login/authCode", method = RequestMethod.GET)
	@ResponseBody
	public void getAuthCode(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		loginService.getAuthCode(request, response, session);
	}

	/**
	 * 退出登录，跳转到登录页面。<br/>
	 * 
	 * @param request 请求实例
	 * @return 返回页面名称
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		loginService.logout(request);
		return "redirect:/login.htm";
	}

}
