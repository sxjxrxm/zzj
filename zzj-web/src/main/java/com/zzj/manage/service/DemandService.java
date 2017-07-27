/**
 * Project Name:zzj-web
 * File Name:UserService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.NeedsAnswer;
import com.zzj.db.dto.NeedsAnswerKey;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.model.NeedsInfoVO;
import com.zzj.db.model.PageResult;

/**
 * <p><strong>类名: </strong></p>DemandService业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>需求管了业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日下午1:24:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface DemandService {
	/**
	 * 分页取得课堂记录
	 * @param NeedsInfoVO 画面表示用需求记录
	 * @return PageResult<NeedsInfoVO> 需求列表
	 */
	PageResult<NeedsInfoVO> searchPagging(NeedsInfoVO record);
	/**
	 * 取得全部课堂记录
	 * @param NeedsInfoVO 画面表示用需求记录
	 * @return List<NeedsInfoVO> 需求列表
	 */
	List<NeedsInfoVO> searchAll(NeedsInfoVO record);
	/**
	 * 根据主键删除记录
	 * @param needsCd 主键
	 * @return int 更新结果条目数
	 */
	int delete(String needsCd);
	/**
	 * 获得导出内容
	 * @param List<NeedsInfoVO> resultList 需求一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	List<ArrayList<String>> getOutputContent(List<NeedsInfoVO> resultList, List<MstCodeInfo> mstCodeInfos);
	/**
	 * 刷新画面
	 * @param HttpServletRequest request 请求实例
	 * @param String needsCd 需求编码
	 */
	void load(HttpServletRequest request, String needsCd);
	/**
	 * 根据主键查询表记录<br/>
	 * @param  needsCd 主键
	 * @return NeedsInfoVO 需求信息
	 */
	NeedsInfoVO selectByPrimaryKey(String needsCd);
	/**
	 * 根据主键更新记录
	 * @param  NeedsInfoVO record 需求信息
	 * @return int 更新结果条目数
	 */
	int save(NeedsInfoVO record);
	/**
	 * 插入回答记录
	 * @param NeedsAnswer record 记录
	 * @return int 执行结果
	 */
	int insertAnswer(NeedsAnswer record);
	/**
	 * 根据主键删除记录
	 * @param NeedsAnswerKey key 主键
	 * @return int 执行结果
	 */
	int delAnswer(NeedsAnswerKey key);
	/**
	 * 更新回答记录
	 * @param NeedsAnswer record 记录
	 * @return int 执行结果
	 */
	int UpdateAnswer(NeedsAnswer record);	
	/**
	 * 获得不属于该需求的领域
	 * @param List<TopicFieldInfoKey> 属于该需求的领域
	 * @return List<TopicFieldInfoKey> 获得不属于该需求的领域
	 */
	List<TopicFieldInfoKey> getOtherTopicFieldInfo(List<TopicFieldInfoKey> topicFieldInfo);
	/**
	 * 获得回答列表
	 * @param String needsCd 需求编码
	 * @param Integer pageNo 当前页
	 * @return PageResult<NeedsAnswer> 回答列表
	 */
	PageResult<NeedsAnswer> getNeedsAnswer(String needsCd, Integer pageNo);
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 
	 * @param request
	 *            请求实例
	 * @return NeedsInfoVO 用于数据库查询
	 * @throws Exception
	 */
	NeedsInfoVO selectKeyMap(HttpServletRequest request, String edit) throws Exception;
//	/**
//	 * 根据请求参数构造blo调用的map参数<br/>
//	 * 
//	 * @param request
//	 *            请求实例
//	 * @return NeedsInfoVO 用于数据库查询
//	 * @throws Exception
//	 */
//	void selectKeyMode(HttpServletRequest request) throws Exception;
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 
	 * @param request 请求实例
	 * @return NeedsInfoVO 用于数据库查询
	 * @throws Exception
	 */
	void selectKeySave(HttpServletRequest request) throws Exception;
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 
	 * @param request 请求实例 
	 * @param pagging 是否分页 
	 * @return NeedsInfoVO 用于数据库查询
	 * @throws Exception 
	 */
	NeedsInfoVO getSaveNeedsInfo(HttpServletRequest request, String pagging) throws Exception;
	/**
	 * 获取问答信息。<br/>
	 * @param  request 请求实例
	 * @return NeedsAnswer 问答信息
	 */
	NeedsAnswer getAnswerInfo(HttpServletRequest request);
}

