/**
 * Project Name:zzj-web
 * File Name:FieldController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.model.FieldResultVO;
import com.zzj.manage.service.FieldService;
import com.zzj.manage.service.IndexService;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>FieldController <br/>
 * <p><strong>功能说明: </strong></p>领域配置管理Controller. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月10日下午6:22:46 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("field") 
public class FieldController extends BaseController{
	
	/**
	 * 筛选业务操作Service
	 */
	@Autowired
	private FieldService fieldService;
	
	/**
	 * 操作Service
	 */
	@Autowired
	private IndexService indexService;
	
	/**
	 * 模块访问方法：跳转到领域配置管理一览界面<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 */
	@RequestMapping("/list.htm")
	public String index(HttpServletRequest request) {
		
		// 选择的技术领域
		String techFieldCd = request.getParameter("techFieldCd");
		
		if(StringUtil.isNotBlank(techFieldCd)) {
			// 取得剩余的研究领域和对应的研究领域
			FieldResultVO fieldInfo = fieldService.searchFieldList(techFieldCd);
			request.setAttribute("fieldInfo", fieldInfo);
			request.setAttribute("techFieldCd", techFieldCd);
		}

		// 设置编码
		setCode(request);
		return "field/field_list";
	}
	
	/**
	 * 设置编码。<br/>
	 * @param request http请求实例
	 */
	private void setCode(HttpServletRequest request) {
		
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> techCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_TECHCODEINFOS);
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> rishCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_RISHCODEINFOS);
		Integer code = 0;
		for (MstCodeInfo codeInfo : techCodeInfos) {
			if (code < Integer.valueOf(codeInfo.getCode())) 
			{
				code = Integer.valueOf(codeInfo.getCode());
			}
		}
		
		request.setAttribute("techNo", String.format("%02d", code + 1));		
		code = 0;
		for (MstCodeInfo codeInfo : rishCodeInfos) {
			if (code < Integer.valueOf(codeInfo.getCode())) 
			{
				code = Integer.valueOf(codeInfo.getCode());
			}
		}
		
		request.setAttribute("rschNo", String.format("%02d", code + 1));
	}
	
	/**
	 * 添加研究领域。<br/>
	 * @param  request http请求实例
	 * @return 执行结果
	 */
	@RequestMapping("/add.htm")
	public @ResponseBody String add(HttpServletRequest request) {
		
		Map<String, Object> map = fieldService.setCodeInfo(request);
		String message = map.get("message").toString();
		if (ZzjConstants.SUCCESS.equals(message))
		{
			MstCodeInfo codeInfo = (MstCodeInfo)map.get("data");
			int ret = fieldService.insert(codeInfo);
			if (ret == 1)
			{
				// 替换session数据
				indexService.saveMstCodeInfoToSessionSuccess(request);
				fieldService.insertTechField(codeInfo.getCode());
			}
		}
		else {
			return message;
		}
		
		return ZzjConstants.SUCCESS;
	}
	
	/**
	 * 保存研究领域。<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping("/save.htm")
	public String save(HttpServletRequest request) throws Exception {
		
		// 选择的技术领域
		String techFieldCd = request.getParameter("techFieldCd");
		request.setAttribute("techFieldCd", techFieldCd);
		
		// 更新研究领域
		fieldService.saveField(request);

		// 更新成功信息
		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));
		return this.index(request);
	}
}