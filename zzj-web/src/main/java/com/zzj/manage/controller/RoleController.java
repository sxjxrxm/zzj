/**
 * Project Name:zzj-web
 * File Name:RoleController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.zzj.core.web.controller.BaseController;
import com.zzj.db.model.PermissionVO;
import com.zzj.manage.service.RoleService;

/**
 * <p><strong>类名: </strong></p>RoleController <br/>
 * <p><strong>功能说明: </strong></p>用户权限管理模块Controller <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月27日下午3:33:09 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("role") 
public class RoleController extends BaseController{
	/**
	 *AppUser业务数据库操作类
	 */
	@Autowired
	private RoleService roleService;
	/**
	 * 
	 *模块访问方法：跳转到用户界面管理界面<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 */
	@RequestMapping("/edit.htm")
	public String index(HttpServletRequest request) {
		return "role/role_edit";
	}
	/**
	 * 页面权限管理查询<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  roleId 角色Id
	 * @return 返回页面名称
	 * @throws IOException 
	 */
	@RequestMapping("/editSearch.htm")
	public void editSearch(HttpServletRequest request, HttpServletResponse response, String roleId) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		// 调用service查询记录
		PermissionVO permissionVO = roleService.editSearch(roleId);
		// 转换成json
		Gson gson = new Gson() ;
		String result = gson.toJson(permissionVO) ;
		// 将查询结果返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(result) ;
	}
	/**
	 *页面权限保存：删除该用户Id下全部记录<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  roleId 角色Id
	 * @return 返回页面名称
	 * @throws IOException 
	 */
	@RequestMapping("/deleteRoles.htm")
	public void deleteRoles(HttpServletRequest request, HttpServletResponse response,String roleId) throws IOException{
		// 定义变量表示返回值
		Integer x = -1;
		// 调用Service保存
		x = roleService.deleteRoles(roleId);
		// 返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(x) ;
	}
	/**
	 * 页面权限保存：添加新记录<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  roleId 角色Id
	 * @param  screenId 画面Id
	 * @return 返回页面名称
	 * @throws IOException 
	 */
	@RequestMapping("/saveRoles.htm")
	public void saveRoles(HttpServletRequest request, HttpServletResponse response, String roleId, String screenId) throws IOException {
		request.setCharacterEncoding("utf-8");
		// 定义变量表示返回值
		Integer x = -1;
		// 调用Service保存
		x = roleService.saveRoles(roleId, screenId);
		// 返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(x) ;
	}
}

