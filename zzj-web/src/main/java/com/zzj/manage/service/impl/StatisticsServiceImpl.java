/**
 * Project Name:zzj-web
 * File Name:UserServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.core.exception.ValidateErrorException;
import com.zzj.db.blo.TopicFieldInfoBlo;
import com.zzj.db.blo.UserHandleInfoBlo;
import com.zzj.db.blo.UserOrderInfoBlo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.model.ActionJknVO;
import com.zzj.db.model.ActionResultVO;
import com.zzj.db.model.IncomeJknVO;
import com.zzj.db.model.IncomeResultVO;
import com.zzj.db.model.PageResult;
import com.zzj.manage.service.StatisticsService;
import com.zzj.util.DateUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>DemandServiceImpl业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>DemandServiceImpl业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月13日下午1:24:30 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

	/**
	 * 收入业务数据库操作类
	 */
	@Autowired
	private UserOrderInfoBlo userOrderInfoBlo;
	
	/**
	 * 用户行为业务数据库操作类
	 */
	@Autowired
	private UserHandleInfoBlo userHandleInfoBlo;
	
	/**
	 * 领域模块业务数据库操作类
	 */
	@Autowired
	private TopicFieldInfoBlo topicFieldInfoBlo;

	/**
	 * 取得全部收入记录
	 * @param IncomeJknVO 画面查询条件
	 * @return PageResult<IncomeResultVO> 收入列表
	 */
	@Override
	public List<IncomeResultVO> searchIncomeList(IncomeJknVO record)  {
		
		List<IncomeResultVO> list = userOrderInfoBlo.selectAllIncome(record);
		return list;
	}
	
	/**
	 * 分页取得收入记录
	 * @return List<NeedsInfo> 画面表示用收入记录
	 * @return PageResult<NeedsInfoVO> 收入列表
	 */
	@Override
	public PageResult<IncomeResultVO> incomeListPagging(IncomeJknVO record)  {

		Integer totalCount = userOrderInfoBlo.selectIncomeTotalCount(record);
		// 没有检索到数据。
		if (totalCount == 0)
		{
			return new PageResult<IncomeResultVO>();
		}
		if (record.getDbIndex() >= totalCount)
		{
			int flag = totalCount % record.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
			int num = totalCount / record.getPageSize();
			record.setPageNo((flag == 0) ? num : num + 1);
			record.setDbIndex((record.getPageNo() - 1) * record.getPageSize());
		}
		if (record.getDbIndex() < 0)
		{
			record.setDbIndex(0);
			record.setPageNo(1);
		}
		List<IncomeResultVO> list = userOrderInfoBlo.selectIncomeByOption(record);
		
		/**
		 * 构造分页结果集
		 */
		PageResult<IncomeResultVO> pageResult = new PageResult<IncomeResultVO>();
		pageResult.setPageNo(record.getDbIndex() / record.getPageSize() + 1);
		pageResult.setPageSize(record.getPageSize());
		int flag = totalCount % record.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / record.getPageSize();
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(list);
		
		return pageResult;
	}
	
	/**
	 * 获得导出内容
	 * @param List<IncomeResultVO> resultList 收入一览
	 * @return List<ArrayList<String>> 导出内容
	 */
	@Override
	public List<ArrayList<String>> getIncomeListOutput(List<IncomeResultVO> resultList)  {

		// 数据做成
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("收入一览");
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("机能分类");
		temp.add("内容分类");
		temp.add("资料主题");
		temp.add("资料来源");
		temp.add("收入金额(元)");
		list.add(temp);
		for (IncomeResultVO n : resultList) {
			temp = new ArrayList<String>();
			temp.add(n.getBusiTypeName());
			temp.add(StringUtil.nullToBlank(n.getContentName()));
			temp.add(n.getTopicName());
			if (StringUtil.isNotBlank(n.getSourceOwner())) {
				temp.add(n.getSourceOwner());
			}
			else {
				temp.add("找专家平台");
			}
			temp.add(n.getPriceSell().toString());
			list.add(temp);
		}
		
		return list;
	}
	
	@Override
	public List<IncomeResultVO> searchIncomeDetail(IncomeJknVO record) throws Exception {
		
		// 取得各行为的详细内容
		List<IncomeResultVO> list = userOrderInfoBlo.selectAllDetail(record);
		return list;
	}
	
	/**
	 * 分页取得收入记录
	 * @param IncomeJknVO 画面查询条件
	 * @return PageResult<IncomeResultVO> 收入列表
	 */
	@Override
	public PageResult<IncomeResultVO> incomeDetailPagging(IncomeJknVO record)  {

		// 取得各行为的详细总数
		Integer totalCount = 0;
		totalCount = userOrderInfoBlo.selectDetailTotalCount(record);
		
		// 没有检索到数据。
		if (totalCount == 0)
		{
			return new PageResult<IncomeResultVO>();
		}
		if (null == record.getDbIndex())
		{
			record.setDbIndex(0);
		}
		else if (record.getDbIndex() >= totalCount)
		{
			int flag = totalCount % record.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
			int num = totalCount / record.getPageSize();
			record.setPageNo((flag == 0) ? num : num + 1);
			record.setDbIndex((record.getPageNo() - 1) * record.getPageSize());
		}
		
		// 取得各行为的详细一览
		List<IncomeResultVO> list = new ArrayList<IncomeResultVO>();
		list = userOrderInfoBlo.selectDetailByOption(record);
	
		/**
		 * 构造分页结果集
		 */
		PageResult<IncomeResultVO> pageResult = new PageResult<IncomeResultVO>();
		pageResult.setPageNo(record.getDbIndex() / record.getPageSize() + 1);
		pageResult.setPageSize(record.getPageSize());
		int flag = totalCount % record.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / record.getPageSize();
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(list);
		
		return pageResult;
	}

	/**
	 * 获得收入详细一览导出内容
	 * @param List<IncomeVO> resultList 收入详细一览
	 * @return List<ArrayList<String>> 导出内容
	 */
	@Override
	public List<ArrayList<String>> getIncomeDetailOutput(List<IncomeResultVO> resultList) {
				
		// 数据做成
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("收入详细");
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("机能分类");
		temp.add("资料主题");
		temp.add("用户昵称");
		temp.add("购买金额(元)");
		temp.add("购买时间");
		list.add(temp);
		for (IncomeResultVO n : resultList) {
			temp = new ArrayList<String>();
			temp.add(n.getBusiTypeName());
			temp.add(n.getTopicName());
			temp.add(StringUtil.nullToBlank(n.getUserName()));
			temp.add(n.getPriceSell().toString());
			temp.add(DateUtil.getDateTimeFormatString(n.getUpdateTime()));
			list.add(temp);
		}
		
		return list;
	}
		
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 
	 * @param request     请求实例
	 * @param detail 收入详细画面区分
	 * @return IncomeJknVO 用于数据库查询
	 * @throws Exception
	 */
	@Override
	public IncomeJknVO selectKeyIncomeMap(HttpServletRequest request, String detail) throws Exception {
		// 错误信息
		ValidateErrorException exception = null;
		
		// 机能分类
		String busiType = request.getParameter("selectKey.busiType");
		// 开始日期
		String startDate = request.getParameter("selectKey.startDate");
		// 结束日期
		String endDate = request.getParameter("selectKey.endDate");
		// 资料来源
		String[] sourceType = request.getParameterValues("selectKey.sourceType");
		// 专家名称
		String expertName = request.getParameter("selectKey.expertName");
		// 资料主题CD
		String topicCd = request.getParameter("topicCd");
		
		IncomeJknVO record = new IncomeJknVO();
		// 将画面条件保存到Map和IncomeJknVO中
		Map<String, Object> showBackMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat(ZzjConstants.datePattern);
		if (StringUtil.isNotBlank(busiType)) {
			record.setBusiType(busiType);
			showBackMap.put("busiType", busiType);
		}
		if (StringUtil.isNotBlank(sourceType) && StringUtil.isNotBlank(sourceType[0])) {
			record.setSourceType("'" + String.join("','", sourceType) + "'");
			showBackMap.put("sourceType", String.join("','", sourceType));
		}
		if (StringUtil.isNotBlank(expertName)) {
			record.setExpertName(expertName);
			showBackMap.put("expertName", expertName);
		}	
		if (StringUtil.isNotBlank(detail))
		{
			if (StringUtil.isNotBlank(topicCd)) {
				record.setTopicCd(topicCd);
				record.setBusiType(request.getParameter("busiType"));
			}			
			if (StringUtil.isNotBlank(startDate)) {
				showBackMap.put("startDate", startDate);
				record.setStartDate(DateUtil.getDateFormat(startDate));
			} else {
				record.setStartDate(DateUtil.getDateFormat("2000-01-01"));
			}
			if (StringUtil.isNotBlank(endDate)) {
				showBackMap.put("endDate", endDate);
				record.setStartDate(DateUtil.getDateFormat(endDate));
			} else {
				record.setEndDate(DateUtil.getDateFormat("2099-12-31"));
			}
		}
		else {
			if (StringUtil.isNotBlank(startDate)) {
				try {
					showBackMap.put("startDate", sdf.parse(startDate));
					record.setStartDate(sdf.parse(startDate));
				} catch (ParseException e) {				
					exception = new ValidateErrorException("E1000004", new Object[] {"统计日期(开始时间)", ZzjConstants.datePattern}, "statistics/statistics_incomeList", "startDate");
				}	
			} else {
				record.setStartDate(DateUtil.getDateFormat("2000-01-01"));
			}
			if (StringUtil.isNotBlank(endDate)) {
				try {
					showBackMap.put("endDate", sdf.parse(endDate));
					record.setStartDate(sdf.parse(endDate));
				} catch (ParseException e) {				
					if (exception == null){
						exception = new ValidateErrorException("E1000004", new Object[] {"统计日期(结束时间)", ZzjConstants.datePattern}, "statistics/statistics_incomeList", "endDate");
					} else {
						exception.addError("E1000004", new Object[] {"统计日期(结束时间)", ZzjConstants.datePattern}, "endDate");
					}
				}	
			} else {
				record.setEndDate(DateUtil.getDateFormat("2099-12-31"));
			}
		}
		
		if (exception != null) 
		{
			request.setAttribute("doSearch", "0");
			throw exception;
		}
			
		// 页面回显数据
		request.setAttribute("selectKey", showBackMap);

		// 查询当前页码
		String pageNo = request.getParameter("pageNo");
		String doSearch = request.getParameter("doSearch");
		if (!StringUtil.isNotBlank(doSearch) && StringUtil.isBlank(detail)) {
			pageNo = request.getParameter("listPageNo");
		}
		String listPageNo = request.getParameter("listPageNo");
		if (StringUtil.isNotBlank(pageNo)) {
			record.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			record.setPageNo(Integer.valueOf(pageNo));
			record.setDbIndex((Integer.valueOf(pageNo) - 1) * record.getPageSize());
			if (StringUtil.isNotBlank(listPageNo))
			{
				request.setAttribute("listPageNo", listPageNo);
			}
			else {
				request.setAttribute("listPageNo", pageNo);
			}
		}
		else {
			record.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			record.setDbIndex(0);
			record.setPageNo(1);
		}
		
		return record;
	}	
	
	
	/**
	 * 取得全部用户行为记录
	 * @param ActionJknVO 画面查询条件
	 * @return PageResult<ActionResultVO> 用户行为列表
	 */
	@Override
	public List<ActionResultVO> searchActionList(ActionJknVO record)  {
		
		List<ActionResultVO> list = userHandleInfoBlo.selectAll(record);
		
		for(ActionResultVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", infoVO.getBusiType());
			map1.put("topicCd", infoVO.getTopicCd());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			infoVO.setFieldCd(topicFieldInfo);			
		}
		return list;
	}
	
	/**
	 * 分页取得用户行为记录
	 * @return List<NeedsInfo> 画面表示用用户行为记录
	 * @return PageResult<NeedsInfoVO> 用户行为列表
	 */
	@Override
	public PageResult<ActionResultVO> actionListPagging(ActionJknVO record)  {

		Integer totalCount = userHandleInfoBlo.selectTotalCount(record);
		// 没有检索到数据。
		if (totalCount == 0)
		{
			return new PageResult<ActionResultVO>();
		}
		if (record.getDbIndex() >= totalCount)
		{
			int flag = totalCount % record.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
			int num = totalCount / record.getPageSize();
			record.setPageNo((flag == 0) ? num : num + 1);
			record.setDbIndex((record.getPageNo() - 1) * record.getPageSize());
		}
		if (record.getDbIndex() < 0)
		{
			record.setDbIndex(0);
			record.setPageNo(1);
		}
		List<ActionResultVO> list = userHandleInfoBlo.selectByOption(record);
		
		for(ActionResultVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", infoVO.getBusiType());
			map1.put("topicCd", infoVO.getTopicCd());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			infoVO.setFieldCd(topicFieldInfo);		
		}
	
		/**
		 * 构造分页结果集
		 */
		PageResult<ActionResultVO> pageResult = new PageResult<ActionResultVO>();
		pageResult.setPageNo(record.getDbIndex() / record.getPageSize() + 1);
		pageResult.setPageSize(record.getPageSize());
		int flag = totalCount % record.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / record.getPageSize();
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(list);
		
		return pageResult;
	}
	
	/**
	 * 获得导出内容
	 * @param List<NeedsInfoVO> resultList 用户行为一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	@Override
	public List<ArrayList<String>> getActionListOutput(List<ActionResultVO> resultList, List<MstCodeInfo> mstCodeInfos)  {

		// 数据做成
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("用户行为一览");
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("机能分类");
		temp.add("资料主题");
		temp.add("领域");
		temp.add("下载数");
		temp.add("收藏数");
		temp.add("分享数");
		temp.add("点赞数");
		temp.add("浏览数");
		list.add(temp);
		for (ActionResultVO n : resultList) {
			temp = new ArrayList<String>();
			temp.add(n.getBusiTypeName());
			temp.add(n.getTopicName());
			StringBuffer sb = new StringBuffer();
			for (TopicFieldInfoKey f :n.getFieldCd()) {
				sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.TECH_FIELD_TYPE, f.getTechFieldCd()).getCodeName());
				if (!ZzjConstants.BLANK.equals(f.getRschFieldCd()))
				{
					sb.append("->");
					sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.RSCH_FIELD_TYPE, f.getRschFieldCd()).getCodeName());
				}
				sb.append("|");
			}
			temp.add(sb.toString());
			temp.add(n.getDownloadCount().toString());
			temp.add(n.getCollectCount().toString());
			temp.add(n.getShareCount().toString());
			temp.add(n.getLikeCount().toString());
			temp.add(n.getScanCount().toString());
			list.add(temp);
		}
		
		return list;
	}
	
	@Override
	public List<ActionResultVO> searchActionDetail(ActionJknVO record) throws Exception {
		
		// 取得各行为的详细内容
		List<ActionResultVO> list = new ArrayList<ActionResultVO>();
		switch (record.getHandleType())
		{
		    case "download":
		    	list = userHandleInfoBlo.selectAllDownload(record);
			break;
		    case "collect":
		    	list = userHandleInfoBlo.selectAllCollect(record);
			break;
		    case "share":
		    	list = userHandleInfoBlo.selectAllShare(record);
			break;
		    case "like":
		    	list = userHandleInfoBlo.selectAllLike(record);
			break;
		    case "scan":
		    	list = userHandleInfoBlo.selectAllScan(record);
			break;			
		}
		
		// 填充领域信息
		for(ActionResultVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", infoVO.getBusiType());
			map1.put("topicCd", infoVO.getTopicCd());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			infoVO.setFieldCd(topicFieldInfo);			
		}
		return list;
	}
	
	/**
	 * 分页取得用户行为记录
	 * @param ActionJknVO 画面查询条件
	 * @return PageResult<ActionResultVO> 用户行为列表
	 */
	@Override
	public PageResult<ActionResultVO> actionDetailPagging(ActionJknVO record)  {

		// 取得各行为的详细总数
		Integer totalCount = 0;
		switch (record.getHandleType())
		{
		    case "download":
				totalCount = userHandleInfoBlo.selectTotalCountDownload(record);
			break;
		    case "collect":
				totalCount = userHandleInfoBlo.selectTotalCountCollect(record);
			break;
		    case "share":
				totalCount = userHandleInfoBlo.selectTotalCountShare(record);
			break;
		    case "like":
				totalCount = userHandleInfoBlo.selectTotalCountLike(record);
			break;
		    case "scan":
				totalCount = userHandleInfoBlo.selectTotalCountScan(record);
			break;			
		}
		
		// 没有检索到数据。
		if (totalCount == 0)
		{
			return new PageResult<ActionResultVO>();
		}
		if (null == record.getDbIndex())
		{
			record.setDbIndex(0);
		}
		else if (record.getDbIndex() >= totalCount)
		{
			int flag = totalCount % record.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
			int num = totalCount / record.getPageSize();
			record.setPageNo((flag == 0) ? num : num + 1);
			record.setDbIndex((record.getPageNo() - 1) * record.getPageSize());
		}
		
		// 取得各行为的详细一览
		List<ActionResultVO> list = new ArrayList<ActionResultVO>();
		switch (record.getHandleType())
		{
		    case "download":
		    	list = userHandleInfoBlo.selectByOptionDownload(record);
			break;
		    case "collect":
		    	list = userHandleInfoBlo.selectByOptionCollect(record);
			break;
		    case "share":
		    	list = userHandleInfoBlo.selectByOptionShare(record);
			break;
		    case "like":
		    	list = userHandleInfoBlo.selectByOptionLike(record);
			break;
		    case "scan":
		    	list = userHandleInfoBlo.selectByOptionScan(record);
			break;			
		}
		
		// 填充领域内容
		for(ActionResultVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", infoVO.getBusiType());
			map1.put("topicCd", infoVO.getTopicCd());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			infoVO.setFieldCd(topicFieldInfo);		
		}
	
		/**
		 * 构造分页结果集
		 */
		PageResult<ActionResultVO> pageResult = new PageResult<ActionResultVO>();
		pageResult.setPageNo(record.getDbIndex() / record.getPageSize() + 1);
		pageResult.setPageSize(record.getPageSize());
		int flag = totalCount % record.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / record.getPageSize();
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(list);
		
		return pageResult;
	}

	@Override
	public List<ArrayList<String>> getActionDetailOutput(List<ActionResultVO> resultList, List<MstCodeInfo> mstCodeInfos,String handleTypeName) {
		
		// 数据做成
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(handleTypeName+"详细一览");
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("机能分类");
		temp.add("资料主题");
		temp.add("领域");
		temp.add(handleTypeName +"时间");
		temp.add("用户昵称");
		list.add(temp);
		for (ActionResultVO n : resultList) {
			temp = new ArrayList<String>();
			temp.add(n.getBusiTypeName());
			temp.add(n.getTopicName());
			StringBuffer sb = new StringBuffer();
			for (TopicFieldInfoKey f :n.getFieldCd()) {
				sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.TECH_FIELD_TYPE, f.getTechFieldCd()).getCodeName());
				if (!ZzjConstants.BLANK.equals(f.getRschFieldCd()))
				{
					sb.append("->");
					sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.RSCH_FIELD_TYPE, f.getRschFieldCd()).getCodeName());
				}
				sb.append("|");
			}
			temp.add(sb.toString());
			temp.add(DateUtil.getDateTimeFormatString(n.getHandleTime()));
			temp.add(n.getUserName());
			list.add(temp);
		}
		
		return list;
	}
		
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 
	 * @param request     请求实例
	 * @param detail 用户行为详细画面区分
	 * @return ActionJknVO 用于数据库查询
	 * @throws Exception
	 */
	@Override
	public ActionJknVO selectKeyActionMap(HttpServletRequest request, String detail) throws Exception {

		// 错误信息
		ValidateErrorException exception = null;
		
		// 机能分类
		String busiType = request.getParameter("selectKey.busiType");
		// 资料主题
		String topicName = request.getParameter("selectKey.topicName");
		// 领域（技术领域）
		String[] field = request.getParameterValues("selectKey.field");
		// 开始日期
		String startDate = request.getParameter("selectKey.startDate");
		// 结束日期
		String endDate = request.getParameter("selectKey.endDate");
		// 资料主题CD
		String topicCd = request.getParameter("topicCd");
		
		ActionJknVO record = new ActionJknVO();
		// 将画面条件保存到Map和ActionJknVO中
		Map<String, Object> showBackMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat(ZzjConstants.datePattern);
		if (StringUtil.isNotBlank(busiType)) {
			record.setBusiType(busiType);
			showBackMap.put("busiType", busiType);
		}
		if (StringUtil.isNotBlank(topicName)) {
			record.setTopicName(topicName);
			showBackMap.put("topicName", topicName);
		}
		if (StringUtil.isNotBlank(topicCd)) {
			record.setTopicCd(topicCd);
			showBackMap.put("topicCd", topicCd);
		}
		if (StringUtil.isNotBlank(field) && StringUtil.isNotBlank(field[0])) {
			record.setField("'" + String.join("','", field) + "'");
			showBackMap.put("field", String.join("','", field));
		}
		
		if (StringUtil.isNotBlank(detail))
		{
			if (StringUtil.isNotBlank(startDate)) {
				showBackMap.put("startDate", startDate);
				record.setStartDate(DateUtil.getDateFormat(startDate));
			} else {
				record.setStartDate(DateUtil.getDateFormat("2000-01-01"));
			}
			if (StringUtil.isNotBlank(endDate)) {
				showBackMap.put("endDate", endDate);
				record.setStartDate(DateUtil.getDateFormat(endDate));		
			} else {
				record.setEndDate(DateUtil.getDateFormat("2099-12-31"));
			}
		}
		else {
			if (StringUtil.isNotBlank(startDate)) {
				try {
					showBackMap.put("startDate", sdf.parse(startDate));
					record.setStartDate(sdf.parse(startDate));
				} catch (ParseException e) {				
					exception = new ValidateErrorException("E1000004", new Object[] {"统计日期(开始时间)", ZzjConstants.datePattern}, "statistics/statistics_actionList", "startDate");
				}
			} else {
				record.setStartDate(DateUtil.getDateFormat("2000-01-01"));
			}
			if (StringUtil.isNotBlank(endDate)) {
				try {
					showBackMap.put("endDate", sdf.parse(endDate));
					record.setStartDate(sdf.parse(endDate));
				} catch (ParseException e) {				
					if (exception == null){
						exception = new ValidateErrorException("E1000004", new Object[] {"统计日期(结束时间)", ZzjConstants.datePattern}, "statistics/statistics_actionList", "endDate");
					} else {
						exception.addError("E1000004", new Object[] {"统计日期(结束时间)", ZzjConstants.datePattern}, "endDate");
					}
				}
			} else {
				record.setEndDate(DateUtil.getDateFormat("2099-12-31"));
			}
		}
		
		if (exception != null) 
		{
			request.setAttribute("doSearch", "0");
			throw exception;
		}
		
		// 页面回显数据
		request.setAttribute("selectKey", showBackMap);

		// 查询当前页码
		String pageNo = request.getParameter("pageNo");
		String doSearch = request.getParameter("doSearch");
		if (!StringUtil.isNotBlank(doSearch) && StringUtil.isBlank(detail)) {
			pageNo = request.getParameter("listPageNo");
		}
		String listPageNo = request.getParameter("listPageNo");
		if (StringUtil.isNotBlank(pageNo)) {
			record.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			record.setPageNo(Integer.valueOf(pageNo));
			record.setDbIndex((Integer.valueOf(pageNo) - 1) * record.getPageSize());
			if (StringUtil.isNotBlank(listPageNo))
			{
				request.setAttribute("listPageNo", listPageNo);
			}
			else {
				request.setAttribute("listPageNo", pageNo);
			}
		}
		else {
			record.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			record.setDbIndex(0);
			record.setPageNo(1);
		}
		
		return record;
	}
	
	/**
	 * 根据CODE返回CODENAME<br/>
	 * @param List<MstCodeInfo> list 编码列表
	 * @param String codeType 类型
	 * @param String codeCd 编码
	 * @return MstCodeInfo 查询结果记录
	 */
	private MstCodeInfo getCodeName(List<MstCodeInfo> list, String codeType, String codeCd) {
		for (MstCodeInfo code : list) {
			if (codeType.equals(code.getCodeType()) && codeCd.equals(code.getCode()))
			{
				return code;
			}
		}
		return new MstCodeInfo();
	}
}