/**
 * Project Name:zzj-web
 * File Name:FilterService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.model.ActionJknVO;
import com.zzj.db.model.ActionResultVO;
import com.zzj.db.model.IncomeJknVO;
import com.zzj.db.model.IncomeResultVO;
import com.zzj.db.model.PageResult;

/**
 * <p><strong>类名: </strong></p>StatisticsService <br/>
 * <p><strong>功能说明: </strong></p>统计管理业务实现类 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月9日下午5:37:02 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface StatisticsService {

	/**
	 * 取得收入一览<br/>
	 * @param IncomeJknVO 画面查询条件
	 * @return List<IncomeResultVO>  行为一览列表
	 * @throws Exception
	 */
	public List<IncomeResultVO> searchIncomeList(IncomeJknVO record) throws Exception;

	/**
	 * 取得收入详细一览<br/>
	 * @param IncomeJknVO 画面查询条件
	 * @return List<IncomeResultVO>  行为详细列表
	 * @throws Exception
	 */
	public List<IncomeResultVO> searchIncomeDetail(IncomeJknVO record) throws Exception;
	
	/**
	 * 分页取得收入记录
	 * @param IncomeJknVO 画面查询条件
	 * @return PageResult<IncomeResultVO> 行为列表
	 */
	PageResult<IncomeResultVO> incomeListPagging(IncomeJknVO record);
	
	/**
	 * 分页取得收入记录
	 * @param IncomeJknVO 画面查询条件
	 * @return PageResult<IncomeResultVO> 行为详细列表
	 */
	PageResult<IncomeResultVO> incomeDetailPagging(IncomeJknVO record);
	
	/**
	 * 获得收入一览导出内容
	 * @param List<IncomeVO> resultList 收入一览
	 * @return List<ArrayList<String>> 导出内容
	 */
	List<ArrayList<String>> getIncomeListOutput(List<IncomeResultVO> resultList);
	
	/**
	 * 获得收入详细一览导出内容
	 * @param List<IncomeVO> resultList 收入详细一览
	 * @return List<ArrayList<String>> 导出内容
	 */
	List<ArrayList<String>> getIncomeDetailOutput(List<IncomeResultVO> resultList);
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 * @param detail 收入详细画面区分
	 * @return IncomeVO 用于数据库查询
	 * @throws Exception
	 */
	IncomeJknVO selectKeyIncomeMap(HttpServletRequest request,String detail) throws Exception;	
	
	/**
	 * 取得用户行为一览<br/>
	 * @param ActionJknVO 画面查询条件
	 * @return List<ActionResultVO>  行为一览列表
	 * @throws Exception
	 */
	public List<ActionResultVO> searchActionList(ActionJknVO record) throws Exception;

	/**
	 * 取得用户行为详细一览<br/>
	 * @param ActionJknVO 画面查询条件
	 * @return List<ActionResultVO>  行为详细列表
	 * @throws Exception
	 */
	public List<ActionResultVO> searchActionDetail(ActionJknVO record) throws Exception;
	
	/**
	 * 分页取得用户行为记录
	 * @param ActionJknVO 画面查询条件
	 * @return PageResult<ActionResultVO> 行为列表
	 */
	PageResult<ActionResultVO> actionListPagging(ActionJknVO record);
	
	/**
	 * 分页取得用户行为记录
	 * @param ActionJknVO 画面查询条件
	 * @return PageResult<ActionResultVO> 行为详细列表
	 */
	PageResult<ActionResultVO> actionDetailPagging(ActionJknVO record);
	
	/**
	 * 获得用户行为一览导出内容
	 * @param List<ActionVO> resultList 用户行为一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	List<ArrayList<String>> getActionListOutput(List<ActionResultVO> resultList, List<MstCodeInfo> mstCodeInfos);
	
	/**
	 * 获得用户行为详细一览导出内容
	 * @param List<ActionVO> resultList 用户行为详细一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表
	 * @param String handleTypeName 行为名称
	 * @return List<ArrayList<String>> 导出内容
	 */
	List<ArrayList<String>> getActionDetailOutput(List<ActionResultVO> resultList, List<MstCodeInfo> mstCodeInfos,String handleTypeName);
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 * @param detail 用户行为详细画面区分
	 * @return ActionVO 用于数据库查询
	 * @throws Exception
	 */
	ActionJknVO selectKeyActionMap(HttpServletRequest request,String detail) throws Exception;	
}

