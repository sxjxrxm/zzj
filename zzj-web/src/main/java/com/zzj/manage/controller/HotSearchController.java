/**
 * Project Name:zzj-web
 * File Name:HotSearchController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzj.core.exception.ValidateErrorException;
import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.HotSearchTerm;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.manage.service.HotSearchService;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>HotSearchController <br/>
 * <p><strong>功能说明: </strong></p>这个类是热搜词管理模块，热搜词管理模块的控制器. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月28日上午11:22:15 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping(value ="/hotsearch")
public class HotSearchController extends BaseController {
	
	/**
	 * 业务操作Service
	 */
	@Autowired
	private HotSearchService hotSearchService;
	
	/**
	 * 跳转进入热搜词编辑画面<br/>
	 * @param  model 返回模型
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit.htm")
	public String toLoginHotSearch (Model model ,HttpServletRequest request) throws Exception {
		
		HotSearchTerm oldHotSearchTerm = hotSearchService.selcetOldHotSearch();
		// 查询结果集
		model.addAttribute ("odlList", oldHotSearchTerm);
		
		return "hotsearch/hotsearch_edit";
	}
	
	/**
	 * 热搜词编辑.<br/>
	 * @param  request http请求实例
	 * @param  HotSearchTerm 修改对象类
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/newEdit.htm")
	public String toLoginNewHotSearch (HttpServletRequest request, HotSearchTerm newHotSearchTerm) throws Exception {

		// 错误信息
		ValidateErrorException exception = null;
		
		String termName01 = newHotSearchTerm.getTermName01();
		String termName02 = newHotSearchTerm.getTermName02();
		String termName03 = newHotSearchTerm.getTermName03();
		String termName04 = newHotSearchTerm.getTermName04();
		String termName05 = newHotSearchTerm.getTermName05();
		String termName06 = newHotSearchTerm.getTermName06();
		String termName07 = newHotSearchTerm.getTermName07();
		String termName08 = newHotSearchTerm.getTermName08();
		String termName09 = newHotSearchTerm.getTermName09();
		String termName10 = newHotSearchTerm.getTermName10();
		List<String> list = new ArrayList<String>();
		list.add(termName01);
		list.add(termName02);
		list.add(termName03);
		list.add(termName04);
		list.add(termName05);
		list.add(termName06);
		list.add(termName07);
		list.add(termName08);
		list.add(termName09);
		list.add(termName10);
		// 设定没有输入热搜词
		boolean flag = true;
		for (int i = 0; i < list.size(); i++) {
			// 不为空进入
			if (StringUtil.isBlank(list.get(i))) {
				// 当i为空时，循环后面的热搜词，判断i后面是否为空
				for (int j = i+1; j < list.size();j++) {
					if(StringUtil.isNotBlank(list.get(j))) {
						String flagStr = "termName0"+(i+1);
						// 没有连续输入热搜词
						if(exception == null) {
								exception = new ValidateErrorException("E1000021", new Object[] {"热搜词"}, "hotsearch/hotsearch_edit", flagStr);
						} else {
							exception.addError("E1000021", new Object[] {"热搜词"}, flagStr); 
						}
						break;
					}
				}
				
			} else {
				// 没有输入热搜词为假
				flag = false;
				for (int k = i+1; k < list.size(); k++) {
					String str1 = list.get(i).replaceAll(" ", "");
					String str2 = list.get(k).replaceAll(" ", "");
					String str3 = "termName0"+(k+1);
						// 判断是否有重复的
						if (exception == null) {
							if (str1.equals(str2)) {
								exception = new ValidateErrorException("E1000001", new Object[] {"不同的热搜词"}, "hotsearch/hotsearch_edit", str3);
							}
						} else {
							if (str1.equals(str2)) {
								exception.addError("E1000001", new Object[] {"不同的热搜词"}, str3);
							}
						}
				}
			}
		}
		
		// 热搜词_判断null
		if (exception == null) {
			if (flag) {
				exception = new ValidateErrorException("E1000001", new Object[] {"至少一个热搜词"}, "hotsearch/hotsearch_edit", "termName01");
			}
		} else {
			if (flag) {
				exception.addError("E1000001", new Object[] {"至少一个热搜词"}, "termName01");
			}
		}

		if (exception != null) {
			// 选项回显
			request.setAttribute("odlList", newHotSearchTerm);
			throw exception;
		} else {
			// 删除旧记录
			hotSearchService.delOldHotSearch();
			// 获取用户信息
			MstUsersInfo existUser = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
			newHotSearchTerm.setUpdateId(existUser.getUserId());
			newHotSearchTerm.setUpdateTime(new Date());
			// 插入新记录
			hotSearchService.newHotSearch(newHotSearchTerm);
			// 选项回显
			request.setAttribute("odlList", newHotSearchTerm);
		}

		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));
		return "hotsearch/hotsearch_edit";
	}
	
}

