/**
 * Project Name:zzj-web
 * File Name:MasterController.java
 * Package Name:com.zzj.manage
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.manage.service.IndexService;
import com.zzj.manage.service.MasterService;

/**
 * <p><strong>类名: </strong></p>MasterController <br/>
 * <p><strong>功能说明: </strong></p>基础信息管理用Controller <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月17日上午10:52:31 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("master")
public class MasterController extends BaseController {
	/**
	 * MstCodeInfo业务数据库操作类
	 */
	@Autowired
	private MasterService masterService;
	
	/**
	 * 操作Service
	 */
	@Autowired
	private IndexService indexService;
	
	/**
	 * 模块访问方法：跳转到前台用户一览界面<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @return 返回页面名称
	 */
	@RequestMapping("/list.htm")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "master/master_list";
	}
	/**
	 * 
	 * 按条件查询MstCodeInfo<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  codeType 编码类型
	 * @throws IOException 
	 */
	@RequestMapping("/searchCodes.htm")
	public void searchCodes(HttpServletRequest request, HttpServletResponse response, String codeType) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		// 定义一个List用来存放查询结果集
		List<MstCodeInfo> codeList = new ArrayList<>();
		// 调用Service查询
		codeList = masterService.searchCodes(codeType);
		// 转换成json
		Gson gson = new Gson() ;
		String result = gson.toJson(codeList) ;
		// 将查询结果返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(result) ;
	}

	/**
	 * 
	 * 删除项目：根据条件更新用户deleteFlag(删除状态)字段<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  codeType 编码类型
	 * @throws IOException 
	 */
	@RequestMapping(value = "/deleteCode.htm")
	public void deleteCode(HttpServletRequest request, HttpServletResponse response, String codeType, String code) throws IOException {
		request.setCharacterEncoding("utf-8");
		// 调用Service更新对应User
		Integer x = masterService.deleteCode(codeType, code) ;
		if (x == 1)
		{
			indexService.saveMstCodeInfoToSessionSuccess(request);
		}
		// 将更新结果返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(x) ;
	}
	/**
	 * 
	 * 编辑项目查询：根据主键查询出对应记录<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  codeType 编码类型
	 * @param  code 编码
	 * @throws IOException 
	 */
	@RequestMapping(value = "/editCodeSearch.htm")
	public void editCodeSearch(HttpServletRequest request, HttpServletResponse response, String codeType, String code) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		// 实例化一个mstcodeInfo作为查询结果
		MstCodeInfo codeInfo = new MstCodeInfo();
		// 调用Service查询
		codeInfo=masterService.editCodeSearch(codeType,code);
		// 转换成json
		Gson gson = new Gson() ;
		String result = gson.toJson(codeInfo) ;
		// 将查询结果返回页面
		PrintWriter out = response.getWriter() ;
		out.print(result) ;		
	}
	/**
	 * 项目编辑：根据isAdd判断对Mst_Code_Info表记录添加或update<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  isAdd 0 编辑；1 添加
	 * @throws Exception
	 */
	@RequestMapping(value = "/editCode.htm")
	public void editCode(HttpServletRequest request, HttpServletResponse response, String isAdd) throws Exception{
		
		String code = request.getParameter("code");
		String codeType = request.getParameter("codeType");
		String codeName = request.getParameter("codeName");
		String codeTypeName = request.getParameter("codeTypeName");
		// 实例化一个code作为编辑条件
		MstCodeInfo codeInfo = new MstCodeInfo();
		codeInfo.setCodeType(codeType);
		codeInfo.setCodeName(codeName);
		codeInfo.setCode(code);
		codeInfo.setCodeTypeName(codeTypeName);
		// 调用Service
		Integer x = masterService.editCode(isAdd, codeInfo);
		if (x == 1)
		{
			indexService.saveMstCodeInfoToSessionSuccess(request);
		}
		PrintWriter  out = response.getWriter() ;
		out.print(x) ;
		
	}
	/**
	 * 查询输入主键是否重复<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  code 编码
	 * @param  codeType 编码类型
	 * @throws IOException 
	 */
	@RequestMapping(value = "/checkKey.htm")
	public void chechKey(HttpServletRequest request, HttpServletResponse response, String code, String codeType) throws IOException {
		request.setCharacterEncoding("utf-8");
		MstCodeInfo codeInfo=masterService.editCodeSearch(codeType, code);
		PrintWriter  out = response.getWriter();
		if (codeInfo != null) {
			out.print(1) ;
		}
		else {
			out.print(0);
		}		
	}
}

