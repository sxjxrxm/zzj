/**
 * Project Name:zzj-web
 * File Name:DemandController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.NeedsAnswer;
import com.zzj.db.dto.NeedsAnswerKey;
import com.zzj.db.model.NeedsInfoVO;
import com.zzj.db.model.PageResult;
import com.zzj.manage.service.DemandService;
import com.zzj.util.CSVUtils;
import com.zzj.util.DateUtil;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>DemandController <br/>
 * <p><strong>功能说明: </strong></p>需求管理Controller <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日下午1:24:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("demand")
public class DemandController extends BaseController {
	
	/**
	 * 业务操作Service
	 */
	@Autowired
	private  DemandService demandService;
	
	/**
	 * 按条件查询需求一览<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping(value="/list.htm")
	public String list(HttpServletRequest request) throws Exception {
		
		String doSearch = request.getParameter("doSearch");
		String pageNo = request.getParameter("needsPageNo");
		if (StringUtil.isNotBlank(doSearch) || StringUtil.isNotBlank(pageNo)) {
			NeedsInfoVO needsInfo = demandService.selectKeyMap(request, "");
			PageResult<NeedsInfoVO> resultList = demandService.searchPagging(needsInfo);	
			request.setAttribute("resultList", resultList);
			if (resultList.getItems() == null || resultList.getItems().size() == 0)
			{
				request.setAttribute(ZzjConstants.SYS_WARNING_KEY, PropertyUtil.getMessageContent("E1000015", null));
			}
		}
		request.setAttribute("doSearch", "0");
		
		return "demand/demand_list";
	}
	
	/**
	 * 按条件查询需求一览CSV导出<br/>
	 * @param  request http请求实例
	 * @param  response http返回实例
	 * @throws Exception 
	 */
	@RequestMapping(value="/CSV.htm")
	public void outputCSV(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> mstCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_TECHCODEINFOS);
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> rishCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_RISHCODEINFOS);
		mstCodeInfos.addAll(rishCodeInfos);
		// 数据检索
		NeedsInfoVO needsInfo = demandService.selectKeyMap(request, "");
		List<NeedsInfoVO> resultList = demandService.searchAll(needsInfo);		
		// 数据做成
		List<ArrayList<String>> list = demandService.getOutputContent(resultList, mstCodeInfos);		
        // 文件生成 
        String title = ZzjConstants.FILE_OUTPUT_NAME_NEEDS + "_" + DateUtil.getDateTimeStr(new Date()) + ZzjConstants.FILE_OUTPUT_CSV;
		CSVUtils.createCSVFile(list, PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH), title);
		// 文件导出
		CSVUtils.exportToExcel(PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH) + File.separator + title, response, false);
	}
	
	/**
	 * 删除记录。<br/>
	 * @param  request 请求实例
	 * @param  needsCd 需求编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/del.htm")
	public String delete(HttpServletRequest request, String needsCd) throws Exception {
		
		// 删除记录
		demandService.delete(needsCd) ;
		NeedsInfoVO needsInfo = demandService.selectKeyMap(request, "");
		PageResult<NeedsInfoVO> resultList = demandService.searchPagging(needsInfo);	
		request.setAttribute("resultList", resultList);
		request.setAttribute("doSearch", "0");
		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000002", null));
		return "demand/demand_list";
	}
	
	/**
	 * 跳转到编辑页面。<br/>
	 * @param  request 请求实例
	 * @param  needsCd 需求编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/edit.htm")
	public String edit(HttpServletRequest request, String needsCd) throws Exception {
		
		// 参数保存
		demandService.selectKeySave(request);
		// 画面刷新
		demandService.load(request, needsCd);
		
		return "demand/demand_edit";
	}
	
	/**
	 * 保存（编辑页面）。<br/>
	 * @param  请求实例
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping(value="/save.htm")
	public String save(HttpServletRequest request) throws Exception {
		
		// 参数保存
		demandService.selectKeySave(request);		
		// 携带列表页面查询条件
		NeedsInfoVO record = demandService.getSaveNeedsInfo(request, "");				
		// 更新数据库
		int ret= demandService.save(record);
		
		if (ret != 1)
		{
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000008", null));
		}
		else
		{	
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));
		}
		// 画面刷新
		demandService.load(request, record.getNeedsCd());	
		
		return "demand/demand_edit";
	}
	
	/**
	 * 回答翻页（编辑页面）。<br/>
	 * @param  请求实例
	 * @param  needsCd 需求编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/answerPage.htm")
	public String answerPage(HttpServletRequest request, String needsCd) throws Exception {
		
		// 参数保存
		demandService.selectKeySave(request);		
		// 携带列表页面查询条件
		demandService.getSaveNeedsInfo(request, "pagging");
		
		if (StringUtil.isNotBlank(needsCd)) {
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000003", null));
		}
		return "demand/demand_edit";
	}
	
	/**
	 * 回答。<br/>
	 * @param  request 请求实例
	 * @return 执行结果
	 */
	@RequestMapping(value="/answer.htm")
	public @ResponseBody String answer(HttpServletRequest request) {
			
		NeedsAnswer record  = demandService.getAnswerInfo(request);
		if (StringUtil.isNotBlank(record.getAnswerContent())) {
			if (record.getAnswerNo() != 0) {
				demandService.UpdateAnswer(record);
			}
			else {
				// 作成者
				MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
				record.setCreateId(user.getUserId());
				demandService.insertAnswer(record);
			}
		}
		else
		{			
			// 错误信息
			return PropertyUtil.getMessageContent("E1000001", new Object[] {"回答内容"});
		}
		
		return ZzjConstants.SUCCESS;
	}
	
	/**
	 * 删除回答记录。<br/>
	 * @param  request 请求实例
	 * @param  needsCd 需求编码
	 * @param  answerNo 回答编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/delAnswer.htm")
	public String delAnswer(HttpServletRequest request, String needsCd, Integer answerNo) throws Exception {
		
		// 参数保存
		demandService.selectKeySave(request);			
		NeedsAnswerKey key = new NeedsAnswerKey();
		key.setNeedsCd(needsCd);
		key.setAnswerNo(answerNo);
		demandService.delAnswer(key);
		// 画面刷新
		demandService.load(request, needsCd);
		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000004", null));
		
		return "demand/demand_edit";
	}

}

