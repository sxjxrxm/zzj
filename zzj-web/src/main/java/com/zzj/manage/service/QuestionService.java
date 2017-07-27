/**
 * Project Name:zzj-web
 * File Name:QuestionService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.QuestionAnswer;
import com.zzj.db.dto.QuestionAnswerKey;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.model.PageResult;
import com.zzj.db.model.QuestionVO;



/**
 * <p><strong>类名: </strong></p>QuestionService <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装E问模块，E问一览显示，E问编辑.  <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日下午2:43:04 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface QuestionService {

	/**
	 * e问条件查询service接口<br/>
	 * @param  info，所查询的对象
	 */
	public List<QuestionVO> searchQuestionList(QuestionVO info);
	/**
	 * 取得e问筛选记录分页显示
	 * @param record 筛选条件对象
	 * @return questionVOs 画面表示用数据对象
	 */
	PageResult<QuestionVO> searchPagging(QuestionVO record);
	/**
	 * 获得导出内容
	 * @param List<QuestionVO> resultList E问一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	List<ArrayList<String>> getOutputContent(List<QuestionVO> resultList, List<MstCodeInfo> mstCodeInfos);
	/**
	 * 更新回答记录。
	 * @param QuestionAnswer record 记录
	 * @return int 执行结果
	 */
	void update(String questionCd);
	
	/**
	 * 根据主键更新记录
	 * @param  QuestionVO record E问信息
	 * @return int 更新结果条目数
	 */
	int save(QuestionVO record);
	/**
	 * 插入回答记录
	 * @param QuestionAnswer record 记录
	 * @return int 执行结果
	 */
	int insertAnswer(QuestionAnswer record);
	/**
	 * 根据主键删除记录
	 * @param QuestionAnswerKey key 主键
	 * @return int 执行结果
	 */
	int delAnswer(QuestionAnswerKey key);
	/**
	 * 更新回答记录。
	 * @param QuestionAnswer record 记录
	 * @return int 执行结果
	 */
	int UpdateAnswer(QuestionAnswer record);	
	/**
	 * 获得不属于该需求的领域
	 * @param List<TopicFieldInfoKey> 属于该需求的领域
	 * @return List<TopicFieldInfoKey> 获得不属于该需求的领域
	 */
	List<TopicFieldInfoKey> getOtherTopicFieldInfo(List<TopicFieldInfoKey> topicFieldInfo);
	/**
	 * 刷新画面。
	 * @param HttpServletRequest request 请求实例
	 * @param String questionCd E问编码
	 */
	void load(HttpServletRequest request, String questionCd);
	/**
	 * 根据主键查询表记录<br/>
	 * @param  questionCd 主键
	 * @return QuestionVO E问信息
	 */
	QuestionVO selectByPrimaryKey(String questionCd);
	/**
	 * 获得回答列表
	 * @param String questionCd E问编码
	 * @param Integer pageNo 当前页
	 * @return PageResult<QuestionAnswer> 回答列表
	 */
	PageResult<QuestionAnswer> getQuestionAnswer(String questionCd, Integer pageNo);
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 
	 * @param request
	 *            请求实例
	 * @return QuestionVO 用于数据库查询
	 * @throws Exception 
	 */
	QuestionVO getSaveQuestionInfo(HttpServletRequest request, String pagging) throws Exception;
	/**
	 * 一览页面查询条件，用于回显数据<br/>
	 * @param request 请求实例
	 * @param String edit
	 * @return QuestionVO 回显数据
	 * @throws Exception 
	 */
	QuestionVO selectKeyMap(HttpServletRequest request, String edit) throws Exception;
	/**
	 * 一览页面查询条件，用于回显数据<br/>
	 * @param request 请求实例
	 * @param String edit
	 * @return QuestionVO 回显数据
	 * @throws Exception 
	 */
	public void selectKeyString(HttpServletRequest request) throws Exception;
	/**
	 * 获取问答信息。<br/>
	 * @param  请求实例
	 * @return QuestionAnswer 问答信息
	 */
	QuestionAnswer getAnswerInfo(HttpServletRequest request);
	/**
	 * 获得导出内容
	 * @param QuestionVO questionVO E问情报
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	List<ArrayList<String>> getDetailOutputContent(QuestionVO questionVO, List<MstCodeInfo> mstCodeInfos);	
}

