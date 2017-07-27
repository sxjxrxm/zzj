/**
 * Project Name:zzj-web
 * File Name:QuestionVO.java
 * Package Name:com.zzj.manage.modal
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.util.Date;
import java.util.List;

import com.zzj.db.dto.QuestionInfo;
import com.zzj.db.dto.RecommendInfo;
import com.zzj.db.dto.RecommendInfoKey;
import com.zzj.db.dto.TopicFieldInfo;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>QuestionVO <br/>
 * <p><strong>功能说明: </strong></p>这个类是用于封装E问一览页面列表显示数据.  <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日下午2:34:17 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class QuestionVO extends QuestionInfo {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4377797954950510053L;
	
	/**
	 * 回答状态
	 */
	private List<String> answerStuts;
	/**
	 * 回答状态
	 */
	private String answerStr;
	/**
	 * 审核状态
	 */
	private String statusStr;
	/**
	 * 审核状态
	 */
	private List<String> statusList;
	/**
	 * 领域
	 */
	private List<String> techFieldTypeList;
	/**
	 * 领域列表信息
	 */
    private List<TopicFieldInfo> fieldInfo;
	/**
	 * 领域主键
	 */
    private List<TopicFieldInfoKey> fieldCd;
	/**
	 * 领域编码（不属于该专家的领域）
	 */
	private List<TopicFieldInfoKey> otherFieldCd;
	/**
	 * 提出时间：开始时间
	 */
	private Date sDate;
	/**
	 * 提出时间：结束时间
	 */
	private Date eDate;
    
	/**
	 * 推荐区分 1：推荐，2：置顶
	 */
	private List<RecommendInfoKey> recommend;
	
	/**
	 * 推荐区分 1：推荐，2：置顶
	 */
	private String recommendKbn;
	
	/**
	 * 推荐语
	 */
	private String recommendMsg;
	
	/**
	 * 推荐置顶状态
	 */
	private List<String> recommendKbnlist;
	
	/**
	 * 置顶信息
	 */
	private RecommendInfo toTopInfo;
	
	/**
	 * 推荐信息
	 */
	private RecommendInfo recommendInfo;	
	/**
	 * 页码
	 */
	private Integer pageNo;
	
	/**
	 * 页码
	 */
	private Integer questionPageNo;
	
	/**
	 * 起始位置
	 */
	private Integer dbIndex;
	
	/**
	 * 起始位置
	 */
	private Integer pageSize;
	/**
	 * 返回answerStuts的值
	 * @return List<String> answerStuts的值
	 */
	public List<String> getAnswerStuts() {
		return answerStuts;
	}
	/**
	 * 设置answerStuts的值
	 * @param  answerStuts answerStuts的值
	 */
	public void setAnswerStuts(List<String> answerStuts) {
		this.answerStuts = answerStuts;
	}
	/**
	 * 返回answerStr的值
	 * @return String answerStr的值
	 */
	public String getAnswerStr() {
		return answerStr;
	}
	/**
	 * 设置answerStr的值
	 * @param  answerStr answerStr的值
	 */
	public void setAnswerStr(String answerStr) {
		this.answerStr = answerStr;
	}
	/**
	 * 返回techFieldTypeList的值
	 * @return List<String> techFieldTypeList的值
	 */
	public List<String> getTechFieldTypeList() {
		return techFieldTypeList;
	}
	/**
	 * 设置techFieldTypeList的值
	 * @param  techFieldTypeList techFieldTypeList的值
	 */
	public void setTechFieldTypeList(List<String> techFieldTypeList) {
		this.techFieldTypeList = techFieldTypeList;
	}
	/**
	 * 返回statusList的值
	 * @return List<String> statusList的值
	 */
	public List<String> getStatusList() {
		return statusList;
	}
	/**
	 * 设置statusList的值
	 * @param  statusList statusList的值
	 */
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
	/**
	 * 返回statusStr的值
	 * @return String statusStr的值
	 */
	public String getStatusStr() {
		return statusStr;
	}
	/**
	 * 设置statusStr的值
	 * @param  statusStr statusStr的值
	 */
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	/**
	 * 返回sDate的值
	 * @return Date sDate的值
	 */
	public Date getsDate() {
		return sDate;
	}
	/**
	 * 设置sDate的值
	 * @param  sDate sDate的值
	 */
	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}
	/**
	 * 返回eDate的值
	 * @return Date eDate的值
	 */
	public Date geteDate() {
		return eDate;
	}
	/**
	 * 设置eDate的值
	 * @param  eDate eDate的值
	 */
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	/**
	 * 返回fieldCd的值
	 * @return List<TopicFieldInfoKey> fieldCd的值
	 */
	public List<TopicFieldInfoKey> getFieldCd() {
		return fieldCd;
	}
	/**
	 * 设置fieldCd的值
	 * @param  fieldCd fieldCd的值
	 */
	public void setFieldCd(List<TopicFieldInfoKey> fieldCd) {
		this.fieldCd = fieldCd;
	}
	/**
	 * 返回recommend的值
	 * @return List<RecommendInfoKey> recommend的值
	 */
	public List<RecommendInfoKey> getRecommend() {
		return recommend;
	}
	/**
	 * 设置recommend的值
	 * @param  recommend recommend的值
	 */
	public void setRecommend(List<RecommendInfoKey> recommend) {
		this.recommend = recommend;
	}
	/**
	 * 返回recommendKbn的值
	 * @return String recommendKbn的值
	 */
	public String getRecommendKbn() {
		return recommendKbn;
	}
	/**
	 * 设置recommendKbn的值
	 * @param  recommendKbn recommendKbn的值
	 */
	public void setRecommendKbn(String recommendKbn) {
		this.recommendKbn = recommendKbn;
	}
	/**
	 * 返回recommendMsg的值
	 * @return String recommendMsg的值
	 */
	public String getRecommendMsg() {
		return recommendMsg;
	}
	/**
	 * 设置recommendMsg的值
	 * @param  recommendMsg recommendMsg的值
	 */
	public void setRecommendMsg(String recommendMsg) {
		this.recommendMsg = recommendMsg;
	}
	/**
	 * 返回otherFieldCd的值
	 * @return List<TopicFieldInfoKey> otherFieldCd的值
	 */
	public List<TopicFieldInfoKey> getOtherFieldCd() {
		return otherFieldCd;
	}
	/**
	 * 设置otherFieldCd的值
	 * @param  otherFieldCd otherFieldCd的值
	 */
	public void setOtherFieldCd(List<TopicFieldInfoKey> otherFieldCd) {
		this.otherFieldCd = otherFieldCd;
	}
	/**
	 * 返回toTopInfo的值
	 * @return RecommendInfo toTopInfo的值
	 */
	public RecommendInfo getToTopInfo() {
		return toTopInfo;
	}
	/**
	 * 设置toTopInfo的值
	 * @param  toTopInfo toTopInfo的值
	 */
	public void setToTopInfo(RecommendInfo toTopInfo) {
		this.toTopInfo = toTopInfo;
	}
	/**
	 * 返回recommendInfo的值
	 * @return RecommendInfo recommendInfo的值
	 */
	public RecommendInfo getRecommendInfo() {
		return recommendInfo;
	}
	/**
	 * 设置recommendInfo的值
	 * @param  recommendInfo recommendInfo的值
	 */
	public void setRecommendInfo(RecommendInfo recommendInfo) {
		this.recommendInfo = recommendInfo;
	}
	/**
	 * 返回fieldInfo的值
	 * @return List<TopicFieldInfo> fieldInfo的值
	 */
	public List<TopicFieldInfo> getFieldInfo() {
		return fieldInfo;
	}
	/**
	 * 设置fieldInfo的值
	 * @param  fieldInfo fieldInfo的值
	 */
	public void setFieldInfo(List<TopicFieldInfo> fieldInfo) {
		this.fieldInfo = fieldInfo;
	}
	/**
	 * 返回pageNo的值
	 * @return Integer pageNo的值
	 */
	public Integer getPageNo() {
		return pageNo;
	}
	/**
	 * 设置pageNo的值
	 * @param  pageNo pageNo的值
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	/**
	 * 返回dbIndex的值
	 * @return Integer dbIndex的值
	 */
	public Integer getDbIndex() {
		return dbIndex;
	}
	/**
	 * 设置dbIndex的值
	 * @param  dbIndex dbIndex的值
	 */
	public void setDbIndex(Integer dbIndex) {
		this.dbIndex = dbIndex;
	}
	/**
	 * 返回pageSize的值
	 * @return Integer pageSize的值
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * 设置pageSize的值
	 * @param  pageSize pageSize的值
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 返回recommendKbnlist的值
	 * @return List<String> recommendKbnlist的值
	 */
	public List<String> getRecommendKbnlist() {
		return recommendKbnlist;
	}
	/**
	 * 设置recommendKbnlist的值
	 * @param  recommendKbnlist recommendKbnlist的值
	 */
	public void setRecommendKbnlist(List<String> recommendKbnlist) {
		this.recommendKbnlist = recommendKbnlist;
	}
	/**
	 * 返回questionPageNo的值
	 * @return Integer questionPageNo的值
	 */
	public Integer getQuestionPageNo() {
		return questionPageNo;
	}
	/**
	 * 设置questionPageNo的值
	 * @param  questionPageNo questionPageNo的值
	 */
	public void setQuestionPageNo(Integer questionPageNo) {
		this.questionPageNo = questionPageNo;
	}
	
}

