/**
 * Project Name:zzj-web
 * File Name:UserServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.io.IOException;
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
import com.zzj.db.blo.NeedsAnswerBlo;
import com.zzj.db.blo.NeedsInfoBlo;
import com.zzj.db.blo.RecommendInfoBlo;
import com.zzj.db.blo.TopicFieldInfoBlo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.NeedsAnswer;
import com.zzj.db.dto.NeedsAnswerKey;
import com.zzj.db.dto.NeedsInfo;
import com.zzj.db.dto.RecommendInfo;
import com.zzj.db.dto.RecommendInfoKey;
import com.zzj.db.dto.TopicFieldInfo;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.model.KeyVO;
import com.zzj.db.model.NeedsInfoVO;
import com.zzj.db.model.PageResult;
import com.zzj.manage.service.DemandService;
import com.zzj.util.Base64;
import com.zzj.util.DateUtil;
import com.zzj.util.SerializUtils;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>DemandServiceImpl业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>需求管理业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日下午1:24:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class DemandServiceImpl implements DemandService {

	/**
	 * NeedsInfoBlo业务数据库操作类
	 */
	@Autowired
	private NeedsInfoBlo needsInfoBlo;
	
	/**
	 * 领域模块业务数据库操作类
	 */
	@Autowired
	private TopicFieldInfoBlo topicFieldInfoBlo;
	
	/**
	 * 推荐置顶模块业务数据库操作类
	 */
	@Autowired
	private RecommendInfoBlo recommendInfoBlo;
	
	/**
	 * MstFieldInfoBlo系统领域模块业务数据库操作类
	 */
	@Autowired
	private MstFieldInfoBlo mstFieldInfoBlo;
	
	/**
	 * NeedsAnswerBlo系统领域模块业务数据库操作类
	 */
	@Autowired
	private NeedsAnswerBlo needsAnswerBlo;

	/**
	 * 取得全部需求记录
	 * @param NeedsInfoVO record 画面表示用需求记录
	 * @return List<NeedsInfoVO> 需求列表
	 */
	@Override
	public List<NeedsInfoVO> searchAll(NeedsInfoVO record)  {
		
		List<NeedsInfoVO> list = needsInfoBlo.selectAll(record);
		
		for(NeedsInfoVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_06);
			map1.put("topicCd", infoVO.getNeedsCd());
			
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
	 * 分页取得需求记录
	 * @param NeedsInfoVO record 画面表示用需求记录
	 * @return PageResult<NeedsInfoVO> 需求列表
	 */
	@Override
	public PageResult<NeedsInfoVO> searchPagging(NeedsInfoVO record)  {
		Integer totalCount = needsInfoBlo.selectTotalCount(record);
		// 没有检索到数据。
		if (totalCount == 0)
		{
			return new PageResult<NeedsInfoVO>();
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
		List<NeedsInfoVO> list = needsInfoBlo.selectPagging(record);
		
		for(NeedsInfoVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_06);
			map1.put("topicCd", infoVO.getNeedsCd());
			
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
		PageResult<NeedsInfoVO> pageResult = new PageResult<NeedsInfoVO>();
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
	 * 根据主键删除记录
	 * @param needsCd 主键
	 * @return int 更新结果条目数
	 */
	@Override
	public int delete(String needsCd)  {
		
		NeedsInfo record = new NeedsInfo() ;
		record.setNeedsCd(needsCd) ;
		record.setDeleteFlag(1);
		
		return needsInfoBlo.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 获得导出内容
	 * @param List<NeedsInfoVO> resultList 需求一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	@Override
	public List<ArrayList<String>> getOutputContent(List<NeedsInfoVO> resultList, List<MstCodeInfo> mstCodeInfos)  {
		
		// 数据做成
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("需求一览");
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("需求主题");
		temp.add("回答状态");
		temp.add("领域");
		temp.add("需求分类");
		temp.add("需求时间");
		temp.add("审核状态");
		list.add(temp);
		for (NeedsInfoVO n : resultList) {
			temp = new ArrayList<String>();
			temp.add(n.getNeedsName());
			temp.add(n.getAnswerDisp());
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
			temp.add(n.getNeedsTypeDisp());
			temp.add(DateUtil.getDate(n.getCreateTime()));
			temp.add(n.getStatusDisp());
			list.add(temp);
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
	 * 刷新画面
	 * @param HttpServletRequest request 请求实例
	 * @param String needsCd 需求编码
	 */
	@Override
	public void load(HttpServletRequest request, String needsCd)  {
		// 数据查询
		NeedsInfoVO needsInfoVO = this.selectByPrimaryKey(needsCd);
		request.setAttribute("needsInfo", needsInfoVO);
		// 已审核的需要显示回答列表
		if (needsInfoVO.getStatus() == ZzjConstants.STATUS_1) {
			Integer pageNo = 1;
			String flag = request.getParameter("flag");
			String pageNoStr = request.getParameter("pageNo");
			if (!(StringUtil.isNotBlank(flag) && "2".equals(flag)) && StringUtil.isNotBlank(pageNoStr))
			{
				pageNo = Integer.valueOf(pageNoStr);
			}
			PageResult<NeedsAnswer> resultList = this.getNeedsAnswer(needsInfoVO.getNeedsCd(), pageNo);
			request.setAttribute("resultList", resultList);
		}
	}
	
	/**
	 * 根据主键查询表记录<br/>
	 * @param  needsCd 主键
	 * @return NeedsInfoVO 需求信息
	 */
	@Override
	public NeedsInfoVO selectByPrimaryKey(String needsCd) {

		NeedsInfoVO needsInfo = needsInfoBlo.selectByPrimaryKey(needsCd);

		// 完善推荐置顶信息
		Map<String, String> map = new HashMap<String, String>();
		map.put("busiType", ZzjConstants.BUSI_TYPE_06);
		map.put("topicCd", needsInfo.getNeedsCd());
		
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
			needsInfo.setRecommendKbn(sb.toString());
			if (sb.toString().contains(ZzjConstants.RECOMMEND_KBN_1)) {
				// 改专家已被推荐，需要查询推荐语
				needsInfo.setRecommendMsg(recommendInfoBlo.findRecommendMsgByTypeAndCode(map));
			}
		}
		
		// 获取对应领域信息
		List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map);
		needsInfo.setFieldCd(topicFieldInfo);
		// 获得不属于该需求的领域
		needsInfo.setOtherFieldCd(this.getOtherTopicFieldInfo(topicFieldInfo));
		
		return needsInfo;
	}
	
	/**
	 * 获得不属于该需求的领域
	 * @param List<TopicFieldInfoKey> 属于该需求的领域
	 * @return List<TopicFieldInfoKey> 获得不属于该需求的领域
	 */
	@Override
	public List<TopicFieldInfoKey> getOtherTopicFieldInfo(List<TopicFieldInfoKey> topicFieldInfo)  {
		// 取得全部领域
		List<TopicFieldInfoKey> mstFieldInfo = mstFieldInfoBlo.selectAll();
		if (topicFieldInfo == null)
		{
			return mstFieldInfo;
		}
	    // 获得不属于该需求的领域
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
	 * 获得回答列表
	 * @param String needsCd 需求编码
	 * @param Integer pageNo 当前页
	 * @return PageResult<NeedsAnswer> 回答列表
	 */
	@Override
	public PageResult<NeedsAnswer> getNeedsAnswer(String needsCd, Integer pageNo)  {
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("needsCd", needsCd);
		// 取得总件数
		int totalCount = needsAnswerBlo.selectTotalCount(queryMap);
		if (totalCount == 0)
		{
			return new PageResult<NeedsAnswer>();
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
		List<NeedsAnswer> needsAnswerList = needsAnswerBlo.selectByNeedsCd(queryMap);
		
		/**
		 * 构造分页实体信息
		 */
		PageResult<NeedsAnswer> pageResult = new PageResult<NeedsAnswer>();
		pageResult.setPageNo(pageNo);
		pageResult.setPageSize(pageSize);
		int flag = totalCount % pageSize;// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / pageSize;
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(needsAnswerList);
		
		return pageResult;
	}
	
	/**
	 * 根据主键更新记录
	 * @param  NeedsInfo record 需求信息
	 * @return int 更新结果条目数
	 */
	@Override
	public int save(NeedsInfoVO record)  {
		int result = 0;
		// 1.保存需求信息
		result = needsInfoBlo.updateByPrimaryKeySelective(record);
		if (result != 1)
		{			
			return result;
		}
		// 2.保存领域
		TopicFieldInfo topicFieldInfo = new TopicFieldInfo();
		topicFieldInfo.setBusiType(ZzjConstants.BUSI_TYPE_06);
		topicFieldInfo.setTopicCd(record.getNeedsCd());
		topicFieldInfo.setDeleteFlag(1);
		topicFieldInfo.setUpdateId(record.getUpdateId());
		topicFieldInfo.setUpdateTime(record.getUpdateTime());
		topicFieldInfoBlo.delete(topicFieldInfo);
		List<TopicFieldInfo> fieldInfo = record.getFieldInfo();
		if (fieldInfo != null && fieldInfo.size() > 0) {
			for (TopicFieldInfo field : fieldInfo) {
				// 根据主键查询是否存在
				TopicFieldInfo temp = topicFieldInfoBlo.selectByPrimaryKey(field);
				if (temp != null) {
					// 如果存在执行更新操作
					result = topicFieldInfoBlo.saveTopicFieldInfo(field);
				} else {
					// 如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
					result = topicFieldInfoBlo.addTopicFieldInfo(field);
				}
			}
		}
		
		// 3.保存推荐置顶信息
		// 3.1 先将该专家数据库中保存的推荐置顶全部改为逻辑删除状态，防止多次修改导致领域集合累加
		RecommendInfo recommend = new RecommendInfo();
		recommend.setBusiType(ZzjConstants.BUSI_TYPE_06);
		recommend.setTopicCd(record.getNeedsCd());
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
	 * 插入回答记录
	 * @param NeedsAnswer record 记录
	 * @return int 执行结果
	 */
	@Override
	public int insertAnswer(NeedsAnswer record)  {
		// 作成日
		record.setCreateTime(new Date());
		record.setAnswerNo(needsAnswerBlo.selectMaxAnswerNo(record.getNeedsCd()) + 1);
		return needsAnswerBlo.insert(record);
	}
	
	/**
	 * 根据主键删除记录
	 * @param NeedsAnswerKey key 主键
	 * @return int 执行结果
	 */
	@Override
	public int delAnswer(NeedsAnswerKey key)  {
		return needsAnswerBlo.delete(key);
	}
	
	/**
	 * 更新回答记录
	 * @param NeedsAnswer record 记录
	 * @return int 执行结果
	 */
	@Override
	public int UpdateAnswer(NeedsAnswer record)  {
		return needsAnswerBlo.update(record);
	}
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 
	 * @param request
	 *            请求实例
	 * @return NeedsInfoVO 用于数据库查询
	 * @throws Exception
	 */
	@Override
	public NeedsInfoVO selectKeyMap(HttpServletRequest request, String edit) throws Exception {
		String backFlag = request.getParameter("flag");
		// 需求主题
		String needsName = null;
		// 回答状态
		String[] answerStatus = null;
		// 领域（技术领域）
		String[] field = null;
		// 需求分类
		String needsType = null;
		// 提出日期
		String sDate = null;
		// 提出日期
		String eDate = null;
		// 审核状态（0 待审核， 1 已审核）
		String[] auditStatus = null;
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbn = null;
		
		//从返回页面回到列表页
		if(StringUtil.isNotBlank(backFlag) && "0".equals(backFlag)){
			String selectKey = request.getParameter("selectKey");
			
			byte[] b = Base64.decode(selectKey);
			KeyVO needKeyVO = SerializUtils.deserialize(b, KeyVO.class);
			
			if(null != needKeyVO){
				needsName = needKeyVO.getNeedsName();
				answerStatus = needKeyVO.getAnswerStatus();
				field = needKeyVO.getField();
				needsType = needKeyVO.getNeedsType();
				sDate = needKeyVO.getsDate();
				eDate = needKeyVO.geteDate();
				auditStatus = needKeyVO.getAuditStatus();
				recommendKbn = needKeyVO.getRecommendKbn();
			}
		}else{
			// 需求主题
			needsName = request.getParameter("selectKey.needsName");
			// 回答状态
			answerStatus = request.getParameterValues("selectKey.answerStatus");
			// 领域（技术领域）
			field = request.getParameterValues("selectKey.field");
			// 需求分类
			needsType = request.getParameter("selectKey.needsType");
			// 提出日期
			sDate = request.getParameter("selectKey.sDate");
			// 提出日期
			eDate = request.getParameter("selectKey.eDate");
			// 审核状态（0 待审核， 1 已审核）
			auditStatus = request.getParameterValues("selectKey.auditStatus");
			// 推荐置顶 （1 推荐， 2 置顶）
			recommendKbn = request.getParameterValues("selectKey.recommendKbn");
		}
		
		NeedsInfoVO record = new NeedsInfoVO();
		Map<String, Object> showBackMap = new HashMap<String, Object>();
		// 错误信息
		ValidateErrorException exception = null;
		
		record.setDeleteFlag(0);
		if (StringUtil.isNotBlank(needsName)) {
			record.setNeedsName(needsName);
			showBackMap.put("needsName", needsName);
		}
		if (StringUtil.isNotBlank(answerStatus) && StringUtil.isNotBlank(answerStatus[0])) {
			record.setAnswerDisp("'" + String.join("','", answerStatus) + "'");
			showBackMap.put("answerStatus", String.join("','", answerStatus));
		}
		if (StringUtil.isNotBlank(field) && StringUtil.isNotBlank(field[0])) {
			record.setField("'" + String.join("','", field) + "'");
			showBackMap.put("field", String.join("','", field));
		}
		if (StringUtil.isNotBlank(needsType)) {
			record.setNeedsType(Integer.valueOf(needsType));
			showBackMap.put("needsType", needsType);
		}
		if (StringUtil.isNotBlank(edit))
		{
			if (StringUtil.isNotBlank(eDate)) {
				showBackMap.put("eDate", eDate);
			}
			if (StringUtil.isNotBlank(sDate)) {
				showBackMap.put("sDate", sDate);
			}
		}
		else {
			SimpleDateFormat sdf = new SimpleDateFormat(ZzjConstants.datePattern);
			if (StringUtil.isNotBlank(sDate)) {
				try {
					record.setsDate(sdf.parse(sDate));
					showBackMap.put("sDate", sdf.parse(sDate));
				} catch (ParseException e) {				
					exception = this.rebuildException(exception, "E1000004", new Object[] {"提出日期(开始时间)", ZzjConstants.datePattern}, "demand/demand_list", "sDate");				
				}
			}
			if (StringUtil.isNotBlank(eDate)) {
				try {
					record.seteDate(sdf.parse(eDate));
					showBackMap.put("eDate", sdf.parse(eDate));
				} catch (ParseException e) {				
					exception = this.rebuildException(exception, "E1000004", new Object[] {"提出日期(结束时间)", ZzjConstants.datePattern}, "demand/demand_list", "eDate");				
				}
			}
		}

		if (StringUtil.isNotBlank(auditStatus) && StringUtil.isNotBlank(auditStatus[0])) {
			record.setStatusDisp("'" + String.join("','", auditStatus) + "'");
			showBackMap.put("auditStatus", String.join("','", auditStatus));
		}
		if (StringUtil.isNotBlank(recommendKbn) && StringUtil.isNotBlank(recommendKbn[0])) {
			record.setRecommendKbn("'" + String.join("','", recommendKbn) + "'");
			showBackMap.put("recommendKbn", String.join("','", recommendKbn));
		}
		
		request.setAttribute("selectKey", showBackMap);
		
		// 查询当前页码
		String pageNo = request.getParameter("pageNo");
		if (!StringUtil.isNotBlank(request.getParameter("doSearch")) 
				&& StringUtil.isNotBlank(request.getParameter("needsPageNo"))) {
			pageNo = request.getParameter("needsPageNo");
		}
		if (StringUtil.isNotBlank(pageNo)) {
			record.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			record.setPageNo(Integer.valueOf(pageNo));
			record.setDbIndex((Integer.valueOf(pageNo) - 1) * record.getPageSize());
			if (StringUtil.isNotBlank(edit))
			{
				request.setAttribute("needsPageNo", pageNo);
			}
		}
		else {
			record.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			record.setDbIndex(0);
			record.setPageNo(1);
		}
		
		// 回答列表翻页,或者,有异常发生
		if (exception != null) 
		{
			request.setAttribute("doSearch", "0");
			throw exception;
		}
		
		return record;
	}
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 
	 * @param request
	 *            请求实例
	 * @return NeedsInfoVO 用于数据库查询
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws Exception
	 */
	@Override
	public void selectKeySave(HttpServletRequest request) throws Exception {
		String flag = request.getParameter("flag");
		// 需求主题
		String needsName = null;
		// 回答状态
		String[] answerStatus = null;
		// 领域（技术领域）
		String[] field = null;
		// 需求分类
		String needsType = null;
		// 提出日期
		String sDate = null;
		// 提出日期
		String eDate = null;
		// 审核状态（0 待审核， 1 已审核）
		String[] auditStatus = null;
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbn = null;
		//页码
		String pageNo = null;
		//从列表页进编辑页
		if(StringUtil.isNotBlank(flag) && "2".equals(flag)){
			needsName = request.getParameter("selectKey.needsName");
			answerStatus = request.getParameterValues("selectKey.answerStatus");
			field = request.getParameterValues("selectKey.field");
			needsType = request.getParameter("selectKey.needsType");
			sDate = request.getParameter("selectKey.sDate");
			eDate = request.getParameter("selectKey.eDate");
			auditStatus = request.getParameterValues("selectKey.auditStatus");
			recommendKbn = request.getParameterValues("selectKey.recommendKbn");
			// 查询当前页码
			pageNo = request.getParameter("pageNo");
			if (!StringUtil.isNotBlank(request.getParameter("doSearch")) 
					&& StringUtil.isNotBlank(request.getParameter("needsPageNo"))) {
				pageNo = request.getParameter("needsPageNo");
			}
		}else{
			String selectKey = request.getParameter("selectKey");
			byte[] b = Base64.decode(selectKey);
			KeyVO needKeyVO = SerializUtils.deserialize(b, KeyVO.class);
			if(null != needKeyVO){
				needsName = needKeyVO.getNeedsName();
				answerStatus = needKeyVO.getAnswerStatus();
				field = needKeyVO.getField();
				needsType = needKeyVO.getNeedsType();
				sDate = needKeyVO.getsDate();
				eDate = needKeyVO.geteDate();
				auditStatus = needKeyVO.getAuditStatus();
				recommendKbn = needKeyVO.getRecommendKbn();
				pageNo = request.getParameter("needsPageNo");
			}
		}
		
		KeyVO vo = new KeyVO();
		if (StringUtil.isNotBlank(needsName)) {
			vo.setNeedsName(needsName);
		}
		if (StringUtil.isNotBlank(answerStatus)) {
			vo.setAnswerStatus(answerStatus);
		}
		if (StringUtil.isNotBlank(field)) {
			vo.setField(field);
		}
		if (StringUtil.isNotBlank(needsType)) {
			vo.setNeedsType(needsType);
		}
		if (StringUtil.isNotBlank(eDate)) {
			vo.seteDate(eDate);
		}
		if (StringUtil.isNotBlank(sDate)) {
			vo.setsDate(sDate);
		}
		if (StringUtil.isNotBlank(auditStatus)) {
			vo.setAuditStatus(auditStatus);
		}
		if (StringUtil.isNotBlank(recommendKbn)) {
			vo.setRecommendKbn(recommendKbn);
		}
		//序列化
		byte[] data = SerializUtils.serializ(vo);  
		String selectKey2 = Base64.encode(data);
		// 页面回显数据
		request.setAttribute("selectKey", selectKey2);
		
		if (StringUtil.isNotBlank(pageNo)) {
			request.setAttribute("needsPageNo", pageNo);
		}
		
	}
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 
	 * @param request 请求实例 
	 * @param pagging 是否分页 
	 * @return NeedsInfoVO 用于数据库查询
	 * @throws Exception 
	 */
	@Override
	public NeedsInfoVO getSaveNeedsInfo(HttpServletRequest request, String pagging) throws Exception {

		// 需求编码
		String needsCd = request.getParameter("needsInfo.needsCd");		
		// 需求主题
		String needsName = request.getParameter("needsInfo.needsName");
		// 需求分类
		String needsType = request.getParameter("needsInfo.needsType");
		// 需求内容
		String needsContent = request.getParameter("needsInfo.needsContent");
		// 审核结果
		String status = request.getParameter("needsInfo.status");
		// 拒绝理由
		String refuseMemo = request.getParameter("needsInfo.refuseMemo");
		
		// 错误信息
		ValidateErrorException exception = null;
		// 获得更新人的id，并创建更新时间
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		String updateId = user.getUserId();
		Date updateTime = new Date();
		
		// 保存信息作成
		NeedsInfoVO record = new NeedsInfoVO();	
		record.setDeleteFlag(0);
		// 更新日
		record.setUpdateTime(new Date());		
		// 更新者
		record.setUpdateId(updateId);
		
		if (StringUtil.isNotBlank(needsCd)) {
			record.setNeedsCd(needsCd);
		}
		if (StringUtil.isNotBlank(needsName)) {
			record.setNeedsName(needsName);
		}
		else
		{
			exception = this.rebuildException(exception, "E1000001", new Object[] {"需求主题"}, "demand/demand_edit", "needsName");
		}
		if (StringUtil.isNotBlank(needsType)) {
			record.setNeedsType(Integer.valueOf(needsType));
		}
		else
		{
			exception = this.rebuildException(exception, "E1000001", new Object[] {"需求分类"}, "demand/demand_edit", "needsType");
		}

		// 领域
		String[] fieldCd = request.getParameterValues("fieldCd");
		if (StringUtil.isNotBlank(fieldCd)) {
			record.setField(String.join(",", fieldCd));
		}
		List<TopicFieldInfo> fieldInfo = null;
		List<TopicFieldInfoKey> fieldInfoKey = null;
		if (fieldCd != null && fieldCd.length > 0) {
			fieldInfo = new ArrayList<TopicFieldInfo>();
			fieldInfoKey = new ArrayList<TopicFieldInfoKey>();
			for (String code : fieldCd) {
				// 此时codes的length应该为2，如果不是2，则是垃圾数据，不予保存
				String[] codes = code.split("-");
				if (codes.length != 2) {
					continue;
				}
				TopicFieldInfo topicFieldInfo = new TopicFieldInfo();
				topicFieldInfo.setBusiType(ZzjConstants.BUSI_TYPE_06);
				topicFieldInfo.setUpdateId(updateId);
				topicFieldInfo.setUpdateTime(updateTime);
				topicFieldInfo.setDeleteFlag(0);
				topicFieldInfo.setTopicCd(record.getNeedsCd());
				topicFieldInfo.setTechFieldCd(codes[0]);
				topicFieldInfo.setRschFieldCd(codes[1]);
				fieldInfo.add(topicFieldInfo);
				fieldInfoKey.add(topicFieldInfo);
			}
		}
		else {
			exception = this.rebuildException(exception, "E1000001", new Object[] {"领域"}, "demand/demand_edit", "choose_sel");
		}
		record.setFieldInfo(fieldInfo);
		if (StringUtil.isNotBlank(needsContent)) {
			record.setNeedsContent(needsContent);
		}
		else
		{
			exception = this.rebuildException(exception, "E1000001", new Object[] {"需求内容"}, "demand/demand_edit", "needsContent");
		}
		
		if (StringUtil.isNotBlank(status)) {
			record.setStatus(Integer.valueOf(status));
			if (record.getStatus() == ZzjConstants.STATUS_2) {
				if (StringUtil.isNotBlank(refuseMemo)) {
					record.setRefuseMemo(refuseMemo);
				}
				else
				{
					exception = this.rebuildException(exception, "E1000001", new Object[] {"拒绝理由"}, "demand/demand_edit", "sel");
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
		String recommendMsg = request.getParameter("needsInfo.recommendMsg");
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
			toTopInfo.setBusiType(ZzjConstants.BUSI_TYPE_06);
			toTopInfo.setTopicCd(record.getNeedsCd());
			toTopInfo.setUpdateId(updateId);
			toTopInfo.setUpdateTime(updateTime);
			toTopInfo.setDeleteFlag(0);
			toTopInfo.setRecommendKbn(Integer.parseInt(toTopCode));			
			if (recommendInfoBlo.isToTopRecommendCount(toTopInfo, ZzjConstants.RECOMMEND_KBN_2))
			{
				exception = this.rebuildException(exception, "E1000027", new Object[] {ZzjConstants.TO_TOP_NUM}, "demand/demand_edit", "toTop");
			}			
			record.setToTopInfo(toTopInfo);
			record.setTopFlag(ZzjConstants.TOP_FLG_1);
		}
		else {
			record.setTopFlag(ZzjConstants.TOP_FLG_0);
		}

		if (StringUtil.isNotBlank(recommend)) {
			// 推荐信息
			RecommendInfo recommendInfo = new RecommendInfo();
			recommendInfo.setBusiType(ZzjConstants.BUSI_TYPE_06);
			recommendInfo.setTopicCd(record.getNeedsCd());
			recommendInfo.setUpdateId(updateId);
			recommendInfo.setUpdateTime(updateTime);
			recommendInfo.setDeleteFlag(0);
			recommendInfo.setRecommendKbn(Integer.parseInt(recommend));
			recommendInfo.setRecommendMemo(record.getRecommendMsg());
			if (recommendInfoBlo.isToTopRecommendCount(recommendInfo, ZzjConstants.RECOMMEND_KBN_1))
			{
				exception = this.rebuildException(exception, "E1000028", new Object[] {ZzjConstants.RECOMMEND_NUM}, "demand/demand_edit", "recommend");
			}
			record.setRecommendInfo(recommendInfo);
		}
		
		// 回答列表翻页,或者,有异常发生
		if (exception != null || StringUtil.isNotBlank(pagging)) 
		{
			record.setFieldCd(fieldInfoKey);
			// 获得不属于该需求的领域
			record.setOtherFieldCd(this.getOtherTopicFieldInfo(fieldInfoKey));			
			// 已审核的需要显示回答列表
			if (record.getStatus() == ZzjConstants.STATUS_1) {
				Integer no = 1;
				String pageNo = request.getParameter("pageNo");
				if (StringUtil.isNotBlank(pageNo) && Integer.valueOf(pageNo) != 0)
				{
					no = Integer.valueOf(request.getParameter("pageNo"));
				}				
				PageResult<NeedsAnswer> resultList = this.getNeedsAnswer(record.getNeedsCd(), no);
				request.setAttribute("resultList", resultList);
			}		
			if (exception != null) 
			{
				if (record.getStatus() == ZzjConstants.STATUS_2) {
					record.setStatus(ZzjConstants.STATUS_0);
				}
				request.setAttribute("needsInfo", record);
				throw exception;
			}
			request.setAttribute("needsInfo", record);
		}
		
		return record;
	}
	
	/**
	 * 获取问答信息。<br/>
	 * @param  request 请求实例
	 * @return NeedsAnswer 问答信息
	 */
	@Override
	public NeedsAnswer getAnswerInfo(HttpServletRequest request) {
		
		// 参数保存
		NeedsAnswer record  = new NeedsAnswer();
		record.setNeedsCd(request.getParameter("needsCd"));
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

