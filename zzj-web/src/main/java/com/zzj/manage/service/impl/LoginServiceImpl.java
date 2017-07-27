/**
 * Project Name:zzj-web
 * File Name:LoginServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzj.core.util.VerificationCodeUtil;
import com.zzj.db.blo.MstScreenInfoBlo;
import com.zzj.db.blo.MstUsersInfoBlo;
import com.zzj.db.dto.MstScreenInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.manage.service.LoginService;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>LoginServiceImpl业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装登录模块. 有登录账户密码判断，验证码判断，更新验证码，登出相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月12日下午11:36:27 <br/>
 * 
 * @author 任晓茂
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class LoginServiceImpl implements LoginService {

	/**
	 * 用户业务数据库操作类
	 */
	@Autowired
	private MstUsersInfoBlo blo;
	
	/**
	 * 用户权限页面操作类
	 */
	@Autowired
	private MstScreenInfoBlo screenInfoBlo;

	/**
	 * 后台校验登录是否成功，将错误次数信息lockMsg存入请求<br/>
	 * @param request 请求实例
	 * @return true 登录成功， false 登录失败
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean isLoginSuccess(HttpServletRequest request) throws Exception {

		// 获得登录用户ID及密码
		String userid = request.getParameter("userid").trim();
		String password = StringUtil.stringToMD5(request.getParameter("password").trim());
		// 将用户输入信息放入request，用于登录失败页面回显
		request.setAttribute("userid", userid);
		request.setAttribute("password", password);

		// 获得空值返回
		if (StringUtil.isBlank(userid) || StringUtil.isBlank(password)) {
			return false;
		}

		// 用户ID及密码非空时的操作
		MstUsersInfo user = blo.getUser(userid);
		// 当前用户ID不存在
		if (user == null) {
			request.setAttribute("lockMsg", PropertyUtil.getMessageContent("E1000008", null));
			return false;
		}
		// 当前用户已被删除
		if (user.getDeleteFlag() == 1) {
			request.setAttribute("lockMsg", PropertyUtil.getMessageContent("E1000008", null));
			return false;
		}
		// 用户已被锁定
		if (user.getErrorCount() != null && user.getErrorCount() >= 6) {
			request.setAttribute("lockMsg", PropertyUtil.getMessageContent("E1000010", null));
			return false;
		}

		// 密码输入不正确（错误次数大于等于6次会对用户锁定）
		if (!password.equals(user.getPassword())) {
			if (user.getErrorCount() < 6) {
				user.setErrorCount(user.getErrorCount() + 1);
				blo.updateByPrimaryKeySelective(user);
				request.setAttribute("lockMsg", "密码错误累计" + user.getErrorCount() + "次");
			} else {
				request.setAttribute("lockMsg", PropertyUtil.getMessageContent("E1000010", null));
			}
			return false;
		}
		
		// 取得用户权限信息
		List<MstScreenInfo> screenList = screenInfoBlo.selectRolePermission(user.getRoleId());
		// 保存到session中
		request.getSession().setAttribute(ZzjConstants.SYS_SESSION_ROLE, screenList);

		// 其他情况下为用户登录成功，将错误次数归零，将用户信息存入session中
		user.setErrorCount(0);
		blo.updateByPrimaryKeySelective(user);
		request.getSession().setAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY, user);
		return true;
	}

	/**
	 * 前台验证码请求。<br/>
	 * 
	 * @param request 请求数据
	 * @param response 相应数据，返回验证码图片
	 * @param response session存放验证码信息
	 * @throws IOException
	 */
	@Override
	public void getAuthCode(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {
		VerificationCodeUtil.getAuthCode(request, response, session);
	}

	/**
	 * 后台校验验证码是否正确<br/>
	 * @param request 请求数据
	 * @return true 正确， false 错误
	 * @throws Exception
	 */
	@Override
	public boolean isAuthCodeRight(HttpServletRequest request) {
		// 用户输入的验证码
		String authCode = request.getParameter("authCode");
		// session中存放的正确的验证码
		String realCode = (String) request.getSession().getAttribute("strCode");
		// 默认验证码提示信息是：验证码输入不正确
		request.setAttribute("authCodeMsg", "验证码输入不正确");
		// 验证码为空
		if (StringUtil.isBlank(authCode)) {
			return false;
		}
		// 验证码长度不正确
		if (authCode.length() != ZzjConstants.AUTHCODE_LENGTH) {
			return false;
		}
		if (!realCode.equals(authCode)) {
			return false;
		}
		// 登录成功需要移除验证码错误提示信息
		request.removeAttribute("authCodeMsg");
		return true;
	}

	/**
	 * 退出登录，清空session。<br/>
	 * 
	 * @param request 请求实例
	 */
	@Override
	public void logout(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Enumeration<String> em = request.getSession().getAttributeNames();
		while (em.hasMoreElements()) {
			request.getSession().removeAttribute(em.nextElement().toString());
		}
	}

}
