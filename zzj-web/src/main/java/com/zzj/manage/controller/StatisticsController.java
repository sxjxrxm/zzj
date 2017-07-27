/**
 * Project Name:zzj-web
 * File Name:FilterController.java
 * Package Name:com.zzj.manage
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
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

import com.zzj.core.exception.ValidateErrorException;
import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.model.ActionJknVO;
import com.zzj.db.model.ActionResultVO;
import com.zzj.db.model.IncomeJknVO;
import com.zzj.db.model.IncomeResultVO;
import com.zzj.db.model.PageResult;
import com.zzj.manage.service.StatisticsService;
import com.zzj.util.CSVUtils;
import com.zzj.util.DateUtil;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>FilterController <br/>
 * <p><strong>功能说明: </strong></p>统计管理用Controller <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月9日上午10:52:31 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("statistics")
public class StatisticsController extends BaseController {

	/**
	 * 统计业务操作Service
	 */
	@Autowired
	private StatisticsService statisticsService;
	
	/**
	 * 用户收入一览。<br/>
	 * @param request 请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/incomeList.htm")
	public String incomeList(HttpServletRequest request) throws Exception {
			
		String doSearch = request.getParameter("doSearch");
		String pageNo = request.getParameter("listPageNo");
		if (StringUtil.isNotBlank(doSearch) || StringUtil.isNotBlank(pageNo)) {
			// 保存查询条件
			IncomeJknVO IncomeJknVO = statisticsService.selectKeyIncomeMap(request,"");
			
			// 统计日期为输入时，报错
			if(StringUtil.isBlank(request.getParameter("selectKey.startDate")) &&
			   StringUtil.isBlank(request.getParameter("selectKey.endDate"))){
				request.setAttribute("doSearch", "0");
			    // 请输入
				throw new ValidateErrorException("E1000001", new Object[] {"统计日期"}, "statistics/statistics_incomeList", "startDate");
			}
			
			// 分页查询
			PageResult<IncomeResultVO> resultList = statisticsService.incomeListPagging(IncomeJknVO);	
			request.setAttribute("resultList", resultList);
			if (resultList.getItems() == null || resultList.getItems().size() == 0)
			{
				request.setAttribute(ZzjConstants.SYS_WARNING_KEY, PropertyUtil.getMessageContent("E1000015", null));
			}
		}
		request.setAttribute("doSearch", "0");
		
		// 所选记录存在时，跳转到编辑画面
		return "statistics/statistics_incomeList";
	}
	
	/**
	 * 按条件查询需求一览CSV导出<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @throws Exception 
	 */
	@RequestMapping(value="/incomeListCSV.htm")
	public void incomeListCSV(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 设置查询条件
		IncomeJknVO incomeJknVO = statisticsService.selectKeyIncomeMap(request,"");
		
		// 统计日期为输入时，报错
		if(StringUtil.isBlank(request.getParameter("selectKey.startDate")) &&
		   StringUtil.isBlank(request.getParameter("selectKey.endDate"))){
			request.setAttribute("doSearch", "0");
		    // 请输入
			throw new ValidateErrorException("E1000001", new Object[] {"统计日期"}, "statistics/statistics_incomeList", "startDate");
		}
		
		List<IncomeResultVO> resultList = statisticsService.searchIncomeList(incomeJknVO);		
		// 数据做成
		List<ArrayList<String>> list = statisticsService.getIncomeListOutput(resultList);		
        // 文件生成 
        String title = ZzjConstants.FILE_OUTPUT_NAME_INCOME + "_" + DateUtil.getDateTimeStr(new Date()) + ZzjConstants.FILE_OUTPUT_CSV;
		CSVUtils.createCSVFile(list, PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH), title);
		// 文件导出
		CSVUtils.exportToExcel(PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH) + File.separator + title, response, false);
	}
	
	/**
	 * 显示收入详细。<br/>
	 * @param request 请求实例
	 * @param busiType 业务类别
	 * @param topicCd 主题编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/incomeDetail.htm")
	public String incomeDetail(HttpServletRequest request,String busiType,String topicCd) throws Exception {
		
		// 参数保持
		request.setAttribute("busiType", busiType);
		request.setAttribute("topicCd", topicCd);
		
		// 用户一览检索条件和页数保存
		IncomeJknVO incomeJknVO = statisticsService.selectKeyIncomeMap(request,"detail");
		
		// 分页检索收入详细
		incomeJknVO.setBusiType(busiType);
		incomeJknVO.setTopicCd(topicCd);		
		PageResult<IncomeResultVO> incomeDetail = statisticsService.incomeDetailPagging(incomeJknVO);
		request.setAttribute("resultList", incomeDetail);
		if (incomeDetail.getItems() == null || incomeDetail.getItems().size() == 0)
		{
			request.setAttribute(ZzjConstants.SYS_WARNING_KEY, PropertyUtil.getMessageContent("E1000015", null));
		}
		
		// 所选记录存在时，跳转到编辑画面
		return "statistics/statistics_incomeDetail";
	}
	
	/**
	 * 按条件查询收入详细CSV导出<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @throws Exception 
	 */
	@RequestMapping(value="/incomeDetailCSV.htm")
	public void incomeDetailCSV(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		// 设置查询条件
		IncomeJknVO incomeJknVO = statisticsService.selectKeyIncomeMap(request,"detail");
		// 数据检索
		List<IncomeResultVO> resultList = statisticsService.searchIncomeDetail(incomeJknVO);

		// 数据做成
		List<ArrayList<String>> list = statisticsService.getIncomeDetailOutput(resultList);		
        // 文件生成 
        String title = ZzjConstants.FILE_OUTPUT_NAME_INCOME_DETAIL + "_" + DateUtil.getDateTimeStr(new Date()) + ZzjConstants.FILE_OUTPUT_CSV;
		CSVUtils.createCSVFile(list, PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH), title);
		// 文件导出
		CSVUtils.exportToExcel(PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH) + File.separator + title, response, false);
	}
	
	/**
	 * 用户行为一览。<br/>
	 * @param request 请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/actionList.htm")
	public String actionList(HttpServletRequest request) throws Exception {
			
		String doSearch = request.getParameter("doSearch");
		String pageNo = request.getParameter("listPageNo");
		if (StringUtil.isNotBlank(doSearch) || StringUtil.isNotBlank(pageNo)) {
			// 保存查询条件
			ActionJknVO actionJknVO = statisticsService.selectKeyActionMap(request,"");
			
			// 分页查询
			PageResult<ActionResultVO> resultList = statisticsService.actionListPagging(actionJknVO);	
			request.setAttribute("resultList", resultList);
			if (resultList.getItems() == null || resultList.getItems().size() == 0)
			{
				request.setAttribute(ZzjConstants.SYS_WARNING_KEY, PropertyUtil.getMessageContent("E1000015", null));
			}
		}
		request.setAttribute("doSearch", "0");		
		
		// 所选记录存在时，跳转到编辑画面
		return "statistics/statistics_actionList";
	}
	
	/**
	 * 按条件查询需求一览CSV导出<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @throws Exception 
	 */
	@RequestMapping(value="/actionListCSV.htm")
	public void actionListCSV(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> mstCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_TECHCODEINFOS);
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> rishCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_RISHCODEINFOS);
		mstCodeInfos.addAll(rishCodeInfos);
		// 设置查询条件
		ActionJknVO actionJknVO = statisticsService.selectKeyActionMap(request,"");
		
		List<ActionResultVO> resultList = statisticsService.searchActionList(actionJknVO);		
		// 数据做成
		List<ArrayList<String>> list = statisticsService.getActionListOutput(resultList, mstCodeInfos);		
        // 文件生成 
        String title = ZzjConstants.FILE_OUTPUT_NAME_ACTION + "_" + DateUtil.getDateTimeStr(new Date()) + ZzjConstants.FILE_OUTPUT_CSV;
		CSVUtils.createCSVFile(list, PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH), title);
		// 文件导出
		CSVUtils.exportToExcel(PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH) + File.separator + title, response, false);
	}
	
	/**
	 * 显示行为详细。<br/>
	 * @param request 请求实例
	 * @param busiType 业务类别
	 * @param topicCd 主题编码
	 * @param handleType 操作类型
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/actionDetail.htm")
	public String actionDetail(HttpServletRequest request, String busiType, String topicCd, String handleType) throws Exception {
		
		// 参数保持
		request.setAttribute("handleType", handleType);
		request.setAttribute("topicCd", topicCd);
		
		// 行为名称
		switch (handleType)
		{
		    case "download":
				request.setAttribute("handleTypeName", "下载");
			break;
		    case "collect":
		    	request.setAttribute("handleTypeName", "收藏");
			break;
		    case "share":
		    	request.setAttribute("handleTypeName", "分享");
			break;
		    case "like":
		    	request.setAttribute("handleTypeName", "点赞");
			break;
		    case "scan":
		    	request.setAttribute("handleTypeName", "浏览");
			break;			
		}
		
		// 用户一览检索条件和页数保存
		ActionJknVO actionJknVO = statisticsService.selectKeyActionMap(request,"detail");
		
		// 分页检索行为详细
		actionJknVO.setTopicCd(topicCd);
		actionJknVO.setHandleType(handleType);
		PageResult<ActionResultVO> actionDetail = statisticsService.actionDetailPagging(actionJknVO);
		request.setAttribute("resultList", actionDetail);
		if (actionDetail.getItems() == null || actionDetail.getItems().size() == 0)
		{
			request.setAttribute(ZzjConstants.SYS_WARNING_KEY, PropertyUtil.getMessageContent("E1000015", null));
		}
		
		// 所选记录存在时，跳转到编辑画面
		return "statistics/statistics_actionDetail";
	}
	
	/**
	 * 按条件查询行为详细CSV导出<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @throws Exception 
	 */
	@RequestMapping(value="/actionDetailCSV.htm")
	public void actionDetailCSV(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> mstCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_TECHCODEINFOS);
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> rishCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_RISHCODEINFOS);
		mstCodeInfos.addAll(rishCodeInfos);
		// 设置查询条件
		ActionJknVO actionJknVO = statisticsService.selectKeyActionMap(request,"detail");
		actionJknVO.setHandleType(request.getParameter("handleType"));
		// 数据检索
		List<ActionResultVO> resultList = statisticsService.searchActionDetail(actionJknVO);
		
		// 行为名称
		String handleTypeName = request.getParameter("handleTypeName");
		// 数据做成
		List<ArrayList<String>> list = statisticsService.getActionDetailOutput(resultList, mstCodeInfos,handleTypeName);		
        // 文件生成  
        String title = ZzjConstants.FILE_OUTPUT_NAME_ACTION_DETAIL + "_" + DateUtil.getDateTimeStr(new Date()) + ZzjConstants.FILE_OUTPUT_CSV;
		CSVUtils.createCSVFile(list, PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH), title);
		// 文件导出
		CSVUtils.exportToExcel(PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH) + File.separator + title, response, false);
	}
}
