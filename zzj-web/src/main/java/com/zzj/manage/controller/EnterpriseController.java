/**
 * Project Name:zzj-web
 * File Name:EnterpriseController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstAreaInfo;
import com.zzj.db.model.EnterpriseVO;
import com.zzj.manage.service.EnterpriseService;
import com.zzj.util.StringUtil;

/**
 * <p><strong>类名: </strong></p>EnterpriseController <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装政企管理模块. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月6日下午1:52:19 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping(value="enterprise")
public class EnterpriseController extends BaseController {
	
	/**
	 * 政企管理模块操作Service
	 */
	@Autowired
	private EnterpriseService enterpriseService;
	
	/**
	 * 政企一览页面。<br/>
	 * @param  request 请求实例
	 * @param  corpInfo 政企信息
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/list.htm")
	public String toLoginEnterprise (HttpServletRequest request, EnterpriseVO corpInfo) throws Exception {
		
		// 初期化一级省份信息
		corpInfo.setAreaInfosCityP(enterpriseService.selectByArea());
		// 检索数据封装
		enterpriseService.toEnterprise(request, corpInfo);
		
		return "enterprise/enterprise_list";
	}
	
	/**
	 * EXCEL导出<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  corpInfo 政企信息
	 * @throws Exception 
	 */
	@RequestMapping(value="/CSV.htm")
	public void outputCSV(HttpServletRequest request, HttpServletResponse response, EnterpriseVO corpInfo) throws Exception {
		// 初期化一级省份信息
		corpInfo.setAreaInfosCityP(enterpriseService.selectByArea());
		// 数据做成
		enterpriseService.outputCSV(request, response, corpInfo);
	}
	
	/**
	 * 编辑（跳转到政企审核页面）。<br/>
	 * @param  request 请求实例
	 * @param  corpInfoList对象类
	 * @param  userId用户ID
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/edit.htm")
	public String toLoginEnterpriseEdit (HttpServletRequest request, EnterpriseVO corpInfoList, String userId ) throws Exception {

		// 判断操作类型 【edit:审核预览、save：审核保存、back：返回】。
		String add = request.getParameter("add");
		
		// 设定页码
		String PageNoStr = request.getParameter("needsPageNo");		
		if (StringUtil.isNotBlank(PageNoStr)) {
			request.setAttribute("needsPageNo", PageNoStr);
		} else {
			request.setAttribute("needsPageNo", "1");
		}
		
		// 点击审核（携带一览查询条件）
		if (add.equals("edit")) {
			
			// 根据用户ID查询用户信息
			corpInfoList = enterpriseService.selectBykey(userId);
			// 显示用户信息
			request.setAttribute("corpInfoList", corpInfoList);
			// 获取一览查询条件
//			EnterpriseVO enterpriseVO = enterpriseService.savePara(request);
//			request.setAttribute("enterpriseVO", enterpriseVO);
			request.setAttribute("selectKey", enterpriseService.savePara(request, add));
		}
		
		// 返回政企一览
		if (add.equals("back")) {
			
			// 获取以保存的一览查询条件
			return this.toLoginEnterprise(request, 
								(EnterpriseVO)enterpriseService.savePara(request, add));
		}
		
		// 审核保存
		if(add.equals("save")) {
			
			// 获取审核页面的一览查询条件，并保存
//			EnterpriseVO enterpriseVO = enterpriseService.savePara(request);
			// 更新数据库
			enterpriseService.save(request, corpInfoList, userId);
			// 获取一览查询条件
//			request.setAttribute("enterpriseVO", enterpriseVO);
			request.setAttribute("selectKey", enterpriseService.savePara(request, add));
		}
		
		return "enterprise/enterprise_edit";
	}

	/**
	 * 城市联动菜单异步请求<br/>
	 * @param  request 请求实例
	 * @return List<MstAreaInfo> 返回根据省级code获得的对应的市级信息列表
	 */
	@RequestMapping(value="/getCityC.htm")
	public @ResponseBody List<MstAreaInfo> getCityC(HttpServletRequest request) {
		List<MstAreaInfo> cityCList = enterpriseService.getCityC(request);
		return cityCList;
	}
	
}

