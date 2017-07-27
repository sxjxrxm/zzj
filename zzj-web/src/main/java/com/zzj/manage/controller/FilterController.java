/**
 * Project Name:zzj-web
 * File Name:FilterController.java
 * Package Name:com.zzj.manage
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzj.core.exception.ValidateErrorException;
import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.model.FilterVO;
import com.zzj.manage.service.FilterService;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>FilterController <br/>
 * <p><strong>功能说明: </strong></p>筛选条件管理用Controller <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月22日上午10:52:31 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("filter")
public class FilterController extends BaseController {

	/**
	 * 筛选业务操作Service
	 */
	@Autowired
	private FilterService filterService;
	
	/**
	 * 各机能筛选条件表示。<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 */
	@RequestMapping(value="/list.htm")
	public String list(HttpServletRequest request) {

		// 查询各机能筛选条件
		List<FilterVO> voList = filterService.searchFilterList();
		if(voList != null) {
			request.setAttribute("filterList", voList);
		}
		
		return "filter/filter_list";
	}
	
	/**
	 * 跳转到编辑页面。<br/>
	 * @param request 请求实例
	 * @param busiType 业务类型编码
	 * @param busiType 业务类型名称
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request, String busiType, String busiTypeName) throws Exception {
		
		// 取得所选记录
		FilterVO vo = filterService.searchFilter(request,busiType);
		
		// 所选机能的筛选条件不存在时，默认追加
		if(StringUtil.isBlank(vo.getBusiType())) {
            // 业务机能ID
			vo.setBusiType(busiType);	
            // 删除Flag
			vo.setDeleteFlag(0);
		}
		
        // 业务机能名称
		vo.setBusiTypeName(busiTypeName);	
		
		// 更新者
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		vo.setUpdateId(user.getUserId());
		request.setAttribute("filterInfo", vo);
		
		// 所选记录存在时，跳转到编辑画面
		return "filter/filter_edit";
	}
	
	/**
	 * 保存筛选条件。<br/>
	 * 
	 * @param request 请求实例
	 * @param vo 筛选条件信息
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, FilterVO vo) throws Exception {

		request.setAttribute("filterInfo", vo);
		
		// 错误信息
		ValidateErrorException exception = null;
		
		// 将筛选条件放在List中
		List<String> list = new ArrayList<String>();
		list.add(vo.getScreening01());
		list.add(vo.getScreening02());
		list.add(vo.getScreening03());
		list.add(vo.getScreening04());
		
		for (int i=1; i<list.size(); i++)
		{
			String behindStr = null;
			if(StringUtil.isBlank(list.get(i-1)))
			{
				if(i == 1)
				{
				    // 请输入
					exception = new ValidateErrorException("E1000001", new Object[] {"筛选条件"}, "filter/filter_edit", "screening01");
				}
			}
			else{
				for (int j=list.size(); j>i; j--)
				{
					if(StringUtil.isNotBlank(list.get(j-1))){
						behindStr = "have";
						
						if(list.get(j-1).equals(list.get(i-1))){
							if(null == exception){
							    // 重复否
								exception = new ValidateErrorException("E1000023", new Object[] {"筛选条件"}, "filter/filter_edit", "screening0"+j);
							}
							else{
								exception.addError("E1000023", new Object[] {"筛选条件"}, "screening0"+j);
							}
						}
					}
					else if(StringUtil.isNotBlank(behindStr)){
						if(null == exception){
						    // 连续
							exception = new ValidateErrorException("E1000021", new Object[] {"筛选条件"}, "filter/filter_edit", "screening0"+j);
						}
						else{
							exception.addError("E1000021", new Object[] {"筛选条件"}, "screening0"+j);
						}
					}
				}
			}
			
			if (exception != null) 
			{
				throw exception;
			}	
		}
	
		// 保存筛选条件
		filterService.saveFilter(request, vo);
		
		// 更新成功信息
		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));

		return "filter/filter_edit";
	}
	
	/**
	 * 返回到一览页面。<br/>
	 * 
	 * @param 请求实例
	 * @return 返回页面名称
	 */
	@RequestMapping(value = "/back")
	public String back(HttpServletRequest request) {
		// 跳转到一览画面
		return "redirect:/filter/list";
	}
}
