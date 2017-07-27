/**
 * Project Name:zzj-web
 * File Name:AppUserController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.AppUsersInfo;
import com.zzj.db.model.PageResult;
import com.zzj.manage.service.AppUserService;

/**
 * <p><strong>类名: </strong></p>AppUserController <br/>
 * <p><strong>功能说明: </strong></p>前台用户管理Controller. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日下午7:53:09 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("appuser") 
public class AppUserController extends BaseController{
	/**
	 * AppUser业务操作Service
	 */
	@Autowired
	private AppUserService appUserservice;
	/**
	 * 
	 *模块访问方法：跳转到前台用户一览界面<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 */
	@RequestMapping("/list.htm")
	public String index(HttpServletRequest request) {
		return "user/appuser_list";
	}
	/**
	 * 
	 * 按条件查询user<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  pageNumber 页码
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchUsers.htm")
	public void serachUsers(HttpServletRequest request, HttpServletResponse response, String pageNumber)  throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		// 取得页面传值
		String roleId = request.getParameter("roleId") ;
		String phoneNumber = request.getParameter("phoneNumber") ;
		String userName = request.getParameter("userName") ;
		// 根据这个user查询符合条件的user
		PageResult<AppUsersInfo> appUsersInfolist = appUserservice.searchUsers(roleId,phoneNumber,userName,pageNumber) ;
		// 转换成json
		Gson gson = new Gson() ;
		String result = gson.toJson(appUsersInfolist) ;
		// 将查询结果返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(result) ;
	}
	/**
	 * 删除用户：根据条件更新用户deleteFlag(删除状态)字段<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  userId 用户Id
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteUser.htm") 
	public void unlockUser(HttpServletRequest request, HttpServletResponse response,String userId)  throws Exception {
		// 调用Service更新对应User
		Integer x=appUserservice.deleteUser(userId) ;
		// 将更新结果返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(x) ;
	}
}

