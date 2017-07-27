/**
 * Project Name:zzj-web
 * File Name:DemoController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzj.core.exception.BusinessException;
import com.zzj.core.exception.ValidateErrorException;
import com.zzj.core.web.controller.BaseController;
import com.zzj.manage.modal.DemoVO;
import com.zzj.manage.service.HtmlService;
import com.zzj.manage.service.impl.DemoServiceImpl;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>DemoController <br/>
 * <p><strong>功能说明: </strong></p>Demo处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月13日上午12:33:39 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("demo")
public class DemoController extends BaseController {

	/**
	 * Demo业务操作Service
	 */
	@Autowired
	private DemoServiceImpl demoService;
	
	/**
	 * 
	 * Demo表记录表示<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String index(HttpServletRequest request, String key) throws Exception{

		int iKey = StringUtil.isNullOrEmpty(key) || !StringUtil.isNumber(key) ? 1 : Integer.valueOf(key).intValue();
		
		DemoVO vo = demoService.searchDemo(request, iKey);
		if(vo != null) {
			request.setAttribute("demo", vo);
		}
		
		return "demo/demo";
	}
	
	/**
	 * 
	 * Demo表记录表示<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public String saveDemo(HttpServletRequest request, DemoVO vo) throws Exception{

		if(vo != null) {
			
			if(vo.getIsAdd() == 1){
				demoService.addDemo(request,vo);
			} else{
				demoService.saveDemo(request,vo);
			}
			
			request.setAttribute("demo", vo);
		}

		return "demo/demo_edit";
	}
	
	/**
	 * 
	 * Demo表记录编辑<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public String editDemo(HttpServletRequest request) throws Exception{
		
		DemoVO vo = demoService.searchDemo(request,1);
		if(vo != null) {
			vo.setIsAdd(0);
			request.setAttribute("demo", vo);
		}
		
		return "demo/demo_edit";
	}
	
	/**
	 * 
	 * Demo表记录追加<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/add")
	public String addDemo(HttpServletRequest request){

		DemoVO vo = new DemoVO();
		vo.setIsAdd(1);
		request.setAttribute("demo", vo);
		
		return "demo/demo_edit";
	}

	@RequestMapping(value="/testValidate1")
	public String testValidationError1() throws Exception {
		throw new ValidateErrorException("错误测试,迁移到共通错误页面");
	}

	@RequestMapping(value="/testValidate2")
	public String testValidationError2() throws Exception {
		throw new ValidateErrorException("错误测试，留在指定页面", "demo/demo_edit", "c");
	}

	@RequestMapping(value="/testValidate3")
	public String testValidationError3() throws Exception {
		throw new BusinessException("业务错误：已经有该记录，不能重复插入，留在指定页面", "demo/demo_edit");
	}
	
	@RequestMapping(value="/qcloudim/callbackBefore")
	@ResponseBody
	public Map<String, Object> callback(HttpServletRequest request,
			String SdkAppid, String CallbackCommand, String contenttype, 
			String ClientIP, String OptPlatform,
			@RequestBody Map<String, Object> postMap) {
		return postMap;
	}
	
	@Autowired
	private HtmlService htmlService;
	
	@RequestMapping(value="info_preview")
	public String info_preview(HttpServletRequest request, String id) throws Exception{
		String baseUrl = PropertyUtil.getConfigValue(ZzjConstants.C_S_CFG_H5_URL);
		return "redirect:" + baseUrl + htmlService.generateInfoHtml(request, id);
	}

	@ResponseBody
	@RequestMapping(value="html_generate")
	public String htmlGenerate(HttpServletRequest request, String id) throws Exception{
		return htmlService.generateAllHtml();
	}
	
	@RequestMapping(value="expert_preview")
	public String expert_preview(HttpServletRequest request, String id) throws Exception{
		String baseUrl = PropertyUtil.getConfigValue(ZzjConstants.C_S_CFG_H5_URL);
		return "redirect:" + baseUrl + htmlService.generateExpertHtml(request, id);
	}
	
	@RequestMapping(value="class_preview")
	public String class_preview(HttpServletRequest request, String id) throws Exception{
		String baseUrl = PropertyUtil.getConfigValue(ZzjConstants.C_S_CFG_H5_URL);
		return "redirect:" + baseUrl + htmlService.generateCourseHtml(request, id);
	}
	
	@RequestMapping(value="live_preview")
	public String live_preview(HttpServletRequest request, String id) throws Exception{
		String baseUrl = PropertyUtil.getConfigValue(ZzjConstants.C_S_CFG_H5_URL);
		return "redirect:" + baseUrl + htmlService.generateLiveHtml(request, id);
	}
}
