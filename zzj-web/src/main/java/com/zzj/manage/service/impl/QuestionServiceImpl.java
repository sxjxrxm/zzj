/**
 * Project Name:zzj-web
 * File Name:QuestionServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.core.exception.ValidateErrorException;
import com.zzj.db.blo.MstFieldInfoBlo;
import com.zzj.db.blo.QuestionAnswerBlo;
import com.zzj.db.blo.QuestionInfoBlo;
import com.zzj.db.blo.RecommendInfoBlo;
import com.zzj.db.blo.TopicFieldInfoBlo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.QuestionAnswer;
import com.zzj.db.dto.QuestionAnswerKey;
import com.zzj.db.dto.RecommendInfo;
import com.zzj.db.dto.RecommendInfoKey;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.model.KeyVO;
import com.zzj.db.model.PageResult;
import com.zzj.db.model.QuestionVO;
import com.zzj.manage.service.QuestionService;
import com.zzj.util.Base64;
import com.zzj.util.DateUtil;
import com.zzj.util.SerializUtils;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>QuestionServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装E问模块，E问一览显示，E问编辑.<br/>
 * <strong>创建日期: </strong><br/></p>2016年11月16日下午2:46:40 <br/>
 * 
 * @author 李善瑞
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class QuestionServiceImpl implements QuestionService {

	/**
	 * e问一览业务数据库操作类
	 */
	@Autowired
	private QuestionInfoBlo questionInfoBlo;
	/**
	 * 系统领域模块业务数据库操作类
	 */
	@Autowired
	private MstFieldInfoBlo mstFieldInfoBlo;
	/**
	 * 系统领域模块业务数据库操作类
	 */
	@Autowired
	private QuestionAnswerBlo questionAnswerBlo;
	/**
	 * 领域模块业务数据库操作类
	 */
	@Autowired
	private TopicFieldInfoBlo topicFieldInfoBlo;
	/**
	 * 业务数据库操作类
	 */
	@Autowired
	private RecommendInfoBlo recommendInfoBlo;
	
	/**
	 * 取得e问筛选记录
	 * @param info 筛选条件对象
	 * @return questionVOs 画面表示用数据对象
	 */
	@Override
	public List<QuestionVO> searchQuestionList(QuestionVO info) {		

		List<QuestionVO> list = questionInfoBlo.searchQuestionList(info);
		
		for(QuestionVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_05);
			map1.put("topicCd", infoVO.getQuestionCd());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			infoVO.setFieldCd(topicFieldInfo);
			
			// 获取对应的推荐置顶信息
			List<RecommendInfoKey> recommendInfo = recommendInfoBlo.selectByTypeAndCode(map1);
			if(recommendInfo == null) {
				recommendInfo = new ArrayList<RecommendInfoKey>();
			}
			infoVO.setRecommend(recommendInfo);			
		}
		
		return list;
	}
	
	/**
	 * 取得e问筛选记录分页显示
	 * @param record 筛选条件对象
	 * @return PageResult<QuestionVO> 画面表示用数据对象
	 */
	@Override
	public PageResult<QuestionVO> searchPagging(QuestionVO record) {		

		record.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
		if (record.getPageNo() == null || record.getPageNo() == 0)
		{
			record.setDbIndex(0);
			record.setPageNo(1);
		}
		else
		{
			record.setDbIndex((record.getPageNo() - 1) * record.getPageSize());
		}
		
		Integer totalCount = questionInfoBlo.selectTotalCount(record);
		// 没有检索到数据。
		if (totalCount == 0)
		{
			return new PageResult<QuestionVO>();
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
		
		List<QuestionVO> list = questionInfoBlo.selectPagging(record);
		for(QuestionVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_05);
			map1.put("topicCd", infoVO.getQuestionCd());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			infoVO.setFieldCd(topicFieldInfo);
			
			// 获取对应的推荐置顶信息
			List<RecommendInfoKey> recommendInfo = recommendInfoBlo.selectByTypeAndCode(map1);
			if(recommendInfo == null) {
				recommendInfo = new ArrayList<RecommendInfoKey>();
			}
			infoVO.setRecommend(recommendInfo);			
		}
		
		/**
		 * 构造分页结果集
		 */
		PageResult<QuestionVO> pageResult = new PageResult<QuestionVO>();
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
	 * @param List<QuestionVO> resultList E问一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	@Override
	public List<ArrayList<String>> getOutputContent(List<QuestionVO> resultList, List<MstCodeInfo> mstCodeInfos)  {
		// 数据做成
		List<ArrayList<String>> list= new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("E问一览");
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("E问主题");
		temp.add("回答状态");
		temp.add("领域");
		temp.add("E问时间");
		temp.add("审核状态");
		list.add(temp);
		for (QuestionVO q : resultList) {
			temp = new ArrayList<String>();
			temp.add(q.getQuestionName());
			temp.add(q.getAnswerStr());
			StringBuffer sb = new StringBuffer();
			for (TopicFieldInfoKey f : q.getFieldCd()) {
				sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.TECH_FIELD_TYPE, f.getTechFieldCd()).getCodeName());
				if (!ZzjConstants.BLANK.equals(f.getRschFieldCd()))
				{
					sb.append("->");
					sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.RSCH_FIELD_TYPE, f.getRschFieldCd()).getCodeName());
				}
				sb.append("|");
			}
			temp.add(sb.toString());
			temp.add(DateUtil.getDate(q.getCreateTime()));
			temp.add(q.getStatusStr());
			list.add(temp);
		}
		
		return list;
	}
	
	/**
	 * 获得导出内容
	 * @param QuestionVO questionVO E问情报
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	@Override
	public List<ArrayList<String>> getDetailOutputContent(QuestionVO questionVO, List<MstCodeInfo> mstCodeInfos)  {
		
		// 数据做成
		List<ArrayList<String>> list= new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("e问基本信息");
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("e问主题");
		temp.add(questionVO.getQuestionName());
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("领域");
		StringBuffer sb = new StringBuffer();
		for (TopicFieldInfoKey f : questionVO.getFieldCd()) {
			sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.TECH_FIELD_TYPE, f.getTechFieldCd()).getCodeName());
			if (!ZzjConstants.BLANK.equals(f.getRschFieldCd()))
			{
				sb.append("->");
				sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.RSCH_FIELD_TYPE, f.getRschFieldCd()).getCodeName());
			}
			sb.append("|");
		}
		temp.add(sb.toString());
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("e问内容");
		temp.add(questionVO.getQuestionContent());
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("审核结果");
		temp.add("通过");
		list.add(temp);
		if (questionVO.getRecommendKbn().contains(ZzjConstants.RECOMMEND_KBN_2)) {
			temp = new ArrayList<String>();
			temp.add("置顶");
			temp.add("置顶");
			list.add(temp);
		}
		if (questionVO.getRecommendKbn().contains(ZzjConstants.RECOMMEND_KBN_1)) {
			temp = new ArrayList<String>();
			temp.add("推荐");
			temp.add("推荐");
			temp.add(questionVO.getRecommendMsg());
			list.add(temp);
		}
		if (questionVO.getStatus() == ZzjConstants.STATUS_1) {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("questionCd", questionVO.getQuestionCd());
			queryMap.put("pageSize", 99999999);
			// 查询起始记录数
			queryMap.put("dbIndex", 0);
			// 取得回答一览
			List<QuestionAnswer> questionAnswerList = questionAnswerBlo.selectByQuestionCd(queryMap);
			temp = new ArrayList<String>();
			temp.add("回答一览");
			list.add(temp);
			temp = new ArrayList<String>();
			temp.add("回答内容");
			temp.add("回答者");
			temp.add("回答日");
			list.add(temp);
			for (QuestionAnswer q : questionAnswerList) {
				temp = new ArrayList<String>();
				temp.add(q.getAnswerContent());
				temp.add(q.getCreateName());
				temp.add(DateUtil.getDate(q.getCreateTime()));
				list.add(temp);
			}
		}
	
		return list;
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
	
	/**
	 * 刷新画面。
	 * @param HttpServletRequest request 请求实例
	 * @param String questionCd E问编码
	 */
	@Override
	public void load(HttpServletRequest request, String questionCd)  {
		// 数据查询
		QuestionVO questionVO = this.selectByPrimaryKey(questionCd);
		request.setAttribute("questionInfo", questionVO);
		// 已审核的需要显示回答列表
		if (questionVO.getStatus() == ZzjConstants.STATUS_1) {
			Integer pageNo = 1;
			String pageNoStr = request.getParameter("pageNo");
			String flag = request.getParameter("flag");
			if (!(StringUtil.isNotBlank(flag) && "2".equals(flag)) && StringUtil.isNotBlank(pageNoStr))
			{
				pageNo = Integer.valueOf(pageNoStr);
			}
			PageResult<QuestionAnswer> resultList = this.getQuestionAnswer(questionVO.getQuestionCd(), pageNo);
			request.setAttribute("resultList", resultList);
		}
	}
	
	/**
	 * 根据主键查询表记录。<br/>
	 * @param  questionCd 主键
	 * @return QuestionVO E问信息
	 */
	@Override
	public QuestionVO selectByPrimaryKey(String questionCd) {

		QuestionVO question = questionInfoBlo.selectByPrimaryKey(questionCd);
		// 完善推荐置顶信息
		Map<String, String> map = new HashMap<String, String>();
		map.put("busiType", ZzjConstants.BUSI_TYPE_05);
		map.put("topicCd", question.getQuestionCd());
		
		// 获取对应的推荐置顶信息
		List<RecommendInfoKey> recommendInfo = recommendInfoBlo.selectByTypeAndCode(map);
		if(recommendInfo == null) {
			recommendInfo = new ArrayList<RecommendInfoKey>();
		} else {
			StringBuffer sb = new StringBuffer();
			for (RecommendInfoKey key : recommendInfo) {
				sb.append(key.getRecommendKbn());
			}
			// 可能的结果： (1,2,)  (1,)  (2,)，方便前台使用判断
			question.setRecommendKbn(sb.toString());
			if (sb.toString().contains(ZzjConstants.RECOMMEND_KBN_1)) {
				// 改专家已被推荐，需要查询推荐语
				question.setRecommendMsg(recommendInfoBlo.findRecommendMsgByTypeAndCode(map));
			}
		}
		
		// 获取对应领域信息
		List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map);
		question.setFieldCd(topicFieldInfo);
		// 获得不属于该E问的领域
		question.setOtherFieldCd(this.getOtherTopicFieldInfo(topicFieldInfo));
		
		return question;
	}
	
	/**
	 * 获得不属于该E问的领域。
	 * @param List<TopicFieldInfoKey> 属于该E问的领域
	 * @return List<TopicFieldInfoKey> 获得不属于该E问的领域
	 */
	@Override
	public List<TopicFieldInfoKey> getOtherTopicFieldInfo(List<TopicFieldInfoKey> topicFieldInfo)  {
		// 取得全部领域
		List<TopicFieldInfoKey> mstFieldInfo = mstFieldInfoBlo.selectAll();
		if (topicFieldInfo == null)
		{
			return mstFieldInfo;
		}
	    // 获得不属于该E问的领域
		List<TopicFieldInfoKey> otherTopicFieldInfo = new ArrayList<TopicFieldInfoKey>();
		boolean isContains = false;
		for (TopicFieldInfoKey m :mstFieldInfo) {
			isContains = false;
			for (TopicFieldInfoKey t : topicFieldInfo) {
				if (m.getTechFieldCd().equals(t.getTechFieldCd()) && 
						(m.getRschFieldCd().equals(t.getRschFieldCd()) || ZzjConstants.BLANK.equals(t.getRschFieldCd()))) {
					isContains = true;
				}
			}
			if (!isContains) {
				otherTopicFieldInfo.add(m);
			}
		}
		return otherTopicFieldInfo;
	}
	
	/**
	 * 插入回答记录。
	 * @param QuestionAnswer record 记录
	 * @return int 执行结果
	 */
	@Override
	public int insertAnswer(QuestionAnswer record)  {
		// 作成日
		record.setCreateTime(new Date());
		record.setAnswerNo(questionAnswerBlo.selectMaxAnswerNo(record.getQuestionCd()) + 1);
		return questionAnswerBlo.insert(record);
	}
	
	/**
	 * 根据主键删除记录。
	 * @param QuestionAnswerKey key 主键
	 * @return int 执行结果
	 */
	@Override
	public int delAnswer(QuestionAnswerKey key)  {
		return questionAnswerBlo.delete(key);
	}
	
	/**
	 * 更新回答记录。
	 * @param QuestionAnswer record 记录
	 * @return int 执行结果
	 */
	@Override
	public int UpdateAnswer(QuestionAnswer record)  {
		return questionAnswerBlo.update(record);
	}
	
	/**
	 * E问修改service。
	 * @param questionCd E问编码
	 * @return 无
	 */
	@Override
	public void update(String questionCd) {
		QuestionVO info = new QuestionVO();
		info.setQuestionCd(questionCd);
		info.setDeleteFlag(1);
		questionInfoBlo.update(info);
	}
	
	/**
	 * 根据主键更新记录。
	 * @param  QuestionVO record E问信息
	 * @return int 更新结果条目数
	 */
	@Override
	public int save(QuestionVO record)  {
		
		int result = 0;
		// 1.保存信息
		result = questionInfoBlo.updateByPrimaryKeySelective(record);
		if (result != 1)
		{			
			return result;
		}
		// 3.保存推荐置顶信息
		// 3.1 先将该专家数据库中保存的推荐置顶全部改为逻辑删除状态，防止多次修改导致领域集合累加
		RecommendInfo recommend = new RecommendInfo();
		recommend.setBusiType(ZzjConstants.BUSI_TYPE_05);
		recommend.setTopicCd(record.getQuestionCd());
		recommend.setDeleteFlag(1);
		recommend.setUpdateId(record.getUpdateId());
		recommend.setUpdateTime(record.getUpdateTime());
		recommendInfoBlo.deleteRecommendInfoByExpertId(recommend);
		// 3.2.1置顶消息
		RecommendInfo toTopInfo = record.getToTopInfo();
		// 先查询后操作
		if (toTopInfo != null) {
			RecommendInfo temp = recommendInfoBlo.selectByPrimaryKey(toTopInfo);
			if (temp != null) {
				//如果存在执行更新操作
				result = recommendInfoBlo.saveRecommendInfo(toTopInfo);
			} else {
				//如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
				result = recommendInfoBlo.addRecommendInfo(toTopInfo);
			}
		}
		// 3.2.2推荐消息
		RecommendInfo recommendInfo = record.getRecommendInfo();
		// 先查询后操作
		if (recommendInfo != null) {
			RecommendInfo temp = recommendInfoBlo.selectByPrimaryKey(recommendInfo);
			if (temp != null) {
				// 如果存在执行更新操作
				result = recommendInfoBlo.saveRecommendInfo(recommendInfo);
			} else {
				// 如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
				result = recommendInfoBlo.addRecommendInfo(recommendInfo);
			}
		}
		
		return result;
	}
	
	/**
	 * 获得回答列表。
	 * @param String questionCd E问编码
	 * @param Integer pageNo 当前页
	 * @return PageResult<QuestionAnswer> 回答列表
	 */
	@Override
	public PageResult<QuestionAnswer> getQuestionAnswer(String questionCd, Integer pageNo)  {
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("questionCd", questionCd);
		// 取得总件数
		int totalCount = questionAnswerBlo.selectTotalCount(queryMap);
		if (totalCount == 0)
		{
			return new PageResult<QuestionAnswer>();
		}
		Integer pageSize = ZzjConstants.DEFAULT_PAGESIZE_S;
		Integer dbIndex = (pageNo - 1) * pageSize;
		if (dbIndex >= totalCount)
		{
			// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
			int flag = totalCount % pageSize;
			int num = totalCount / pageSize;
			pageNo = (flag == 0) ? num : num + 1;
			dbIndex = (pageNo - 1) * pageSize;
		}
		queryMap.put("pageSize", pageSize);
		// 查询起始记录数
		queryMap.put("dbIndex", dbIndex);
		// 取得回答一览
		List<QuestionAnswer> questionAnswerList = questionAnswerBlo.selectByQuestionCd(queryMap);
		
		/**
		 * 构造分页实体信息
		 */
		PageResult<QuestionAnswer> pageResult = new PageResult<QuestionAnswer>();
		pageResult.setPageNo(pageNo);
		pageResult.setPageSize(pageSize);
		int flag = totalCount % pageSize;// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / pageSize;
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(questionAnswerList);
		
		return pageResult;
	}
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 
	 * @param request 请求实例
	 * @return QuestionVO 用于数据库查询
	 * @throws Exception 
	 */
	@Override
	public QuestionVO getSaveQuestionInfo(HttpServletRequest request, String pagging) throws Exception {

		// E问编码
		String questionCd = request.getParameter("questionCd");		
		// 审核结果
		String status = request.getParameter("questionInfo.status");
		// 拒绝理由
		String refuseMemo = request.getParameter("questionInfo.refuseMemo");
		
		// 错误信息
		ValidateErrorException exception = null;
		// 获得更新人的id，并创建更新时间
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		String updateId = user.getUserId();
		Date updateTime = new Date();
		
		// 保存信息作成
		QuestionVO record = new QuestionVO();	
		record.setDeleteFlag(0);
		// 更新日
		record.setUpdateTime(new Date());		
		// 更新者
		record.setUpdateId(updateId);
		
		if (StringUtil.isNotBlank(questionCd)) {
			record.setQuestionCd(questionCd);
		}
		
		if (StringUtil.isNotBlank(status)) {
			record.setStatus(Integer.valueOf(status));
			if (record.getStatus() == ZzjConstants.STATUS_2) {
				if (StringUtil.isNotBlank(refuseMemo)) {
					record.setRefuseMemo(refuseMemo);
				}
				else
				{
					exception = this.rebuildException(exception, "E1000001", new Object[] {"拒绝理由"}, "question/question_edit", "sel");
				}
			}
			else {				
				record.setRefuseMemo("");
			}
			record.setAuditId(updateId);
			record.setAuditTime(updateTime);
		}
		else
		{
			record.setStatus(ZzjConstants.STATUS_0);
		}
		// 置顶
		String toTopCode = request.getParameter("toTopCode");
		// 推荐
		String recommend = request.getParameter("recommend");
		// 推荐语
		String recommendMsg = request.getParameter("questionInfo.recommendMsg");
		if (StringUtil.isNotBlank(recommend) || StringUtil.isNotBlank(toTopCode)) {
			record.setRecommendKbn(StringUtil.nullToBlank(recommend) + ", " + StringUtil.nullToBlank(toTopCode));
		}
		if (StringUtil.isNotBlank(recommend)) {
			if (StringUtil.isNotBlank(recommendMsg)) {
				record.setRecommendMsg(recommendMsg);
			}
		}

		if (StringUtil.isNotBlank(toTopCode)) {
			// 置顶信息
			RecommendInfo toTopInfo = new RecommendInfo();
			toTopInfo.setBusiType(ZzjConstants.BUSI_TYPE_05);
			toTopInfo.setTopicCd(record.getQuestionCd());
			toTopInfo.setUpdateId(updateId);
			toTopInfo.setUpdateTime(updateTime);
			toTopInfo.setDeleteFlag(0);
			toTopInfo.setRecommendKbn(Integer.parseInt(toTopCode));
			if (recommendInfoBlo.isToTopRecommendCount(toTopInfo, ZzjConstants.RECOMMEND_KBN_2))
			{
				exception = this.rebuildException(exception, "E1000027", new Object[] {ZzjConstants.TO_TOP_NUM}, "question/question_edit", "toTop");
			}
			record.setToTopInfo(toTopInfo);
			record.setTopFlag(ZzjConstants.TOP_FLG_1);
		}
		else
		{
			record.setTopFlag(ZzjConstants.TOP_FLG_0);
		}

		if (StringUtil.isNotBlank(recommend)) {
			// 推荐信息
			RecommendInfo recommendInfo = new RecommendInfo();
			recommendInfo.setBusiType(ZzjConstants.BUSI_TYPE_05);
			recommendInfo.setTopicCd(record.getQuestionCd());
			recommendInfo.setUpdateId(updateId);
			recommendInfo.setUpdateTime(updateTime);
			recommendInfo.setDeleteFlag(0);
			recommendInfo.setRecommendKbn(Integer.parseInt(recommend));
			recommendInfo.setRecommendMemo(record.getRecommendMsg());
			if (recommendInfoBlo.isToTopRecommendCount(recommendInfo, ZzjConstants.RECOMMEND_KBN_1))
			{
				exception = this.rebuildException(exception, "E1000028", new Object[] {ZzjConstants.RECOMMEND_NUM}, "question/question_edit", "recommend");
			}
			record.setRecommendInfo(recommendInfo);
		}
		
		// 回答列表翻页,或者,有异常发生
		if (exception != null || StringUtil.isNotBlank(pagging)) 
		{
			// 数据查询
			QuestionVO questionVO = questionInfoBlo.selectByPrimaryKey(questionCd);
			// 已审核的需要显示回答列表
			if (record.getStatus() == ZzjConstants.STATUS_1) {
				Integer no = 1;
				String pageNo = request.getParameter("pageNo");
				if (StringUtil.isNotBlank(pageNo) && Integer.valueOf(pageNo) != 0)
				{
					no = Integer.valueOf(request.getParameter("pageNo"));
				}
				PageResult<QuestionAnswer> resultList = this.getQuestionAnswer(record.getQuestionCd(), no);
				request.setAttribute("resultList", resultList);
			}
			record.setQuestionName(questionVO.getQuestionName());
			record.setQuestionContent(questionVO.getQuestionContent());	
			if (exception != null) 
			{
				if (record.getStatus() == ZzjConstants.STATUS_2) {
					record.setStatus(ZzjConstants.STATUS_0);
				}
				request.setAttribute("questionInfo", record);	
				throw exception;
			}
			request.setAttribute("questionInfo", record);						
		}
		
		return record;
	}
	
	/**
	 * 一览页面查询条件，用于回显数据<br/>
	 * @param request 请求实例
	 * @param String edit
	 * @return QuestionVO 回显数据
	 * @throws Exception 
	 */
	@Override
	public QuestionVO selectKeyMap(HttpServletRequest request, String edit) throws Exception {
		String flag = request.getParameter("flag");
		// 主题
		String questionName = null;
		// 回答状态
		String[] answerStuts = null;
		// 审核状态（0 待审核， 1 已审核）
		String[] statusList = null;
		// 领域（技术领域）
		String[] techFieldTypeList = null;
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbnlist = null;
		// 提出日期
		String sDate = null;
		// 提出日期
		String eDate = null;
		// 当前页
		String pageNo = null;
		String questionPageNo = null;
		//保存或者从编辑页返回列表页
		if(StringUtil.isNotBlank(flag) && "0".equals(flag)){
			String selectKey = request.getParameter("selectKey");
			byte[] b = Base64.decode(selectKey);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			if(null != keyVO){
				questionName = keyVO.getQuestionName();
				answerStuts = keyVO.getAnswerStatus();
				statusList = keyVO.getAuditStatus();
				techFieldTypeList = keyVO.getField();
				recommendKbnlist = keyVO.getRecommendKbn();
				sDate = keyVO.getsDate();
				eDate = keyVO.geteDate();
				pageNo = keyVO.getPageNo();
				questionPageNo = keyVO.getQuestionPageNo();
			}
		}else{
			questionName = request.getParameter("questionName");
			answerStuts = request.getParameterValues("answerStuts");
			statusList = request.getParameterValues("statusList");
			techFieldTypeList = request.getParameterValues("techFieldTypeList");
			recommendKbnlist = request.getParameterValues("recommendKbnlist");
			sDate = request.getParameter("sDate");
			eDate = request.getParameter("eDate");
			pageNo = request.getParameter("pageNo");
			questionPageNo = request.getParameter("questionPageNo");
		}

		/**
		 * 构造map
		 */
		Map<String, Object> showBackMap = new HashMap<String, Object>();
		// 错误信息
		ValidateErrorException exception = null;
		QuestionVO record = new QuestionVO();
		List<String> temp = null;
		if (StringUtil.isNotBlank(questionName)) {
			if (!"search".equals(edit)) {
				showBackMap.put("questionName", questionName);
			}
			record.setQuestionName(questionName);
		}
		if (StringUtil.isNotBlank(answerStuts) && StringUtil.isNotBlank(answerStuts[0])) {
			if (!"search".equals(edit)) {
				showBackMap.put("answerStuts", String.join(",", answerStuts));
			}
			if (StringUtil.isNotBlank(answerStuts[0]) && answerStuts[0].contains(",")) {
				record.setAnswerStuts(StringUtil.StringToStringList(answerStuts[0]));
			}
			else {
				temp = new ArrayList<String>();
				for (int i = 0; i < answerStuts.length; i++) {
					temp.add(answerStuts[i]);
				}
				record.setAnswerStuts(temp);
			}
		}
		if (StringUtil.isNotBlank(statusList) && StringUtil.isNotBlank(statusList[0])) {
			if (!"search".equals(edit)) {
				showBackMap.put("statusList", String.join(",", statusList));
			}
			if (StringUtil.isNotBlank(statusList[0]) && statusList[0].contains(",")) {
				record.setStatusList(StringUtil.StringToStringList(statusList[0]));
			}
			else {
				temp = new ArrayList<String>();
				for (int i = 0; i < statusList.length; i++) {
					temp.add(statusList[i]);
				}
				record.setStatusList(temp);
			}
		}
		if (StringUtil.isNotBlank(techFieldTypeList) && StringUtil.isNotBlank(techFieldTypeList[0])) {
			if (!"search".equals(edit)) {
				showBackMap.put("techFieldTypeList", String.join(",", techFieldTypeList));
			}
			if (StringUtil.isNotBlank(techFieldTypeList[0]) && techFieldTypeList[0].contains(",")) {
				record.setTechFieldTypeList(StringUtil.StringToStringList(techFieldTypeList[0]));
			}
			else {
				temp = new ArrayList<String>();
				for (int i = 0; i < techFieldTypeList.length; i++) {
					temp.add(techFieldTypeList[i]);
				}
				record.setTechFieldTypeList(temp);
			}
		}
		if (StringUtil.isNotBlank(recommendKbnlist) && StringUtil.isNotBlank(recommendKbnlist[0])) {
			if (!"search".equals(edit)) {
				showBackMap.put("recommendKbnlist", String.join(",", recommendKbnlist));
			}
			if (StringUtil.isNotBlank(recommendKbnlist[0]) && recommendKbnlist[0].contains(",")) {
				record.setRecommendKbnlist(StringUtil.StringToStringList(recommendKbnlist[0]));
			}
			else {
				temp = new ArrayList<String>();
				for (int i = 0; i < recommendKbnlist.length; i++) {
					temp.add(recommendKbnlist[i]);
				}
				record.setRecommendKbnlist(temp);
			}
		}
		if (!"search".equals(edit))
		{
			if (StringUtil.isNotBlank(sDate)) {
				showBackMap.put("sDate", sDate);
			}
			if (StringUtil.isNotBlank(eDate)) {
				showBackMap.put("eDate", eDate);
			}
		}
		else {
			SimpleDateFormat sdf = new SimpleDateFormat(ZzjConstants.datePattern);
			if (StringUtil.isNotBlank(sDate)) {
				try {
					record.setsDate(sdf.parse(sDate));
				} catch (ParseException e) {				
					exception = this.rebuildException(exception, "E1000004", new Object[] {"提出日期(开始时间)", ZzjConstants.datePattern}, "question/question_list", "sDate");			
				}
			}
			if (StringUtil.isNotBlank(eDate)) {
				try {
					record.seteDate(sdf.parse(eDate));
				} catch (ParseException e) {				
					exception = this.rebuildException(exception, "E1000004", new Object[] {"提出日期(结束时间)", ZzjConstants.datePattern}, "question/question_list", "eDate");			
				}
			}
		}
		// 查询起始记录数
		if ("edit".equals(edit)) {
			showBackMap.put("questionPageNo", pageNo);
		}
		if (StringUtil.isBlank(edit)) {
			showBackMap.put("questionPageNo", questionPageNo);
		}
		if ("search".equals(edit)) {
			if (StringUtil.isNotBlank(questionPageNo)) {
				showBackMap.put("pageNo", questionPageNo);
				record.setPageNo(Integer.valueOf(questionPageNo));
			}
			else if (StringUtil.isNotBlank(pageNo)) {
				showBackMap.put("pageNo", pageNo);
				record.setPageNo(Integer.valueOf(pageNo));
			}			
		}		
		request.setAttribute("info", showBackMap);
		
		// 回答列表翻页,或者,有异常发生
		if (exception != null) 
		{
			request.setAttribute("doSearch", "0");
			throw exception;
		}
		
		return record;
	}
	
	/**
	 * 一览页面查询条件，用于回显数据<br/>
	 * @param request 请求实例
	 * @param String edit
	 * @return QuestionVO 回显数据
	 * @throws Exception 
	 */
	@Override
	public void selectKeyString(HttpServletRequest request) throws Exception {
		String flag = request.getParameter("flag");
		// 主题
		String questionName = null;
		// 回答状态
		String[] answerStuts = null;
		// 审核状态（0 待审核， 1 已审核）
		String[] statusList = null;
		// 领域（技术领域）
		String[] techFieldTypeList = null;
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbnlist = null;
		// 提出日期
		String sDate = null;
		// 提出日期
		String eDate = null;
		// 当前页
		String pageNo = null;
		String questionPageNo = null;
		//保存或者从编辑页返回列表页
		if(StringUtil.isNotBlank(flag) && "2".equals(flag)){
			questionName = request.getParameter("questionName");
			answerStuts = request.getParameterValues("answerStuts");
			statusList = request.getParameterValues("statusList");
			techFieldTypeList = request.getParameterValues("techFieldTypeList");
			recommendKbnlist = request.getParameterValues("recommendKbnlist");
			sDate = request.getParameter("sDate");
			eDate = request.getParameter("eDate");
			pageNo = request.getParameter("pageNo");
			questionPageNo = request.getParameter("questionPageNo");
		}else{
			String selectKey = request.getParameter("selectKey");
			byte[] b = Base64.decode(selectKey);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			if(null != keyVO){
				questionName = keyVO.getQuestionName();
				answerStuts = keyVO.getAnswerStatus();
				statusList = keyVO.getAuditStatus();
				techFieldTypeList = keyVO.getField();
				recommendKbnlist = keyVO.getRecommendKbn();
				sDate = keyVO.getsDate();
				eDate = keyVO.geteDate();
				pageNo = keyVO.getPageNo();
				questionPageNo = keyVO.getQuestionPageNo();
			}
		}

		/**
		 * 构造model
		 */
		KeyVO vo = new KeyVO();
		
		if (StringUtil.isNotBlank(questionName)) {
			vo.setQuestionName(questionName);
		}
		if (StringUtil.isNotBlank(answerStuts) && StringUtil.isNotBlank(answerStuts[0])) {
			vo.setAnswerStatus(answerStuts);
		}
		if (StringUtil.isNotBlank(statusList) && StringUtil.isNotBlank(statusList[0])) {
			vo.setAuditStatus(statusList);
		}
		if (StringUtil.isNotBlank(techFieldTypeList) && StringUtil.isNotBlank(techFieldTypeList[0])) {
			vo.setField(techFieldTypeList);
		}
		if (StringUtil.isNotBlank(recommendKbnlist) && StringUtil.isNotBlank(recommendKbnlist[0])) {
			vo.setRecommendKbn(recommendKbnlist);
		}
		if (StringUtil.isNotBlank(sDate)) {
			vo.setsDate(sDate);
		}
		if (StringUtil.isNotBlank(eDate)) {
			vo.seteDate(eDate);
		}
		// 查询起始记录数
		if(StringUtil.isNotBlank(flag) && "2".equals(flag)){
			vo.setQuestionPageNo(pageNo);
		}else{
			vo.setQuestionPageNo(questionPageNo);
		}
		//序列化
		byte[] data = SerializUtils.serializ(vo);  
		String selectKey2 = Base64.encode(data);
		// 页面回显数据
		request.setAttribute("selectKey", selectKey2);
		request.setAttribute("questionPageNo", vo.getQuestionPageNo());
	}
	
	/**
	 * 获取问答信息。<br/>
	 * @param request 请求实例
	 * @return QuestionAnswer 问答信息
	 */
	@Override
	public QuestionAnswer getAnswerInfo(HttpServletRequest request) {
		
		// 参数保存
		QuestionAnswer record  = new QuestionAnswer();
		record.setQuestionCd(request.getParameter("questionCd"));
		record.setAnswerContent(request.getParameter("content"));
		String answerNo = request.getParameter("answerNo");
		if (StringUtil.isNotBlank(answerNo))
		{
			record.setAnswerNo(Integer.valueOf(answerNo));
		}
		else {
			record.setAnswerNo(0);			
		}		
		return record;
	}
	
	/**
	 * 重构ValidateErrorException实例。<br/>
	 * @param  exception ValidateErrorException
	 * @param  errorCode 异常消息代码
	 * @param  msgArgs 异常消息参数
	 * @param  errorPage 错误页面
	 * @param  errorItemKey 错误项目ID
	 */
	private ValidateErrorException rebuildException (ValidateErrorException exception, String errorCode, Object[] msgArgs, String errorPage, String errorItemKey) {
		if (exception == null) {
			exception = new ValidateErrorException(errorCode, msgArgs, errorPage, errorItemKey);
		} else {
			exception.addError(errorCode, msgArgs, errorItemKey);
		}
		return exception;
	}
}
