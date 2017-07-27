/**
 * Project Name:zzj-web
 * File Name:KeyVO.java
 * Package Name:com.zzj.manage.modal
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.io.Serializable;

/**
 * <p><strong>类名: </strong></p>KeyVO <br/>
 * <p><strong>功能说明: </strong></p>KeyVO画面用VO. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年1月16日上午11:01:30 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class KeyVO implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1868787136833297725L;
	/**
	 * 需求主题
	 */
	private String needsName;
	/**
	 * 回答状态
	 */
	private String[] answerStatus;
	/**
	 * 领域（技术领域）
	 */
	private String[] field;
	/**
	 * 需求分类
	 */
	private String needsType;
	/**
	 * 提出日期
	 */
	private String sDate;
	/**
	 * 提出日期
	 */
	private String eDate;
	/**
	 * 审核状态（0 待审核， 1 已审核）
	 */
	private String[] auditStatus;
	/**
	 * 推荐置顶 （1 推荐， 2 置顶）
	 */
	private String[] recommendKbn;
	/**
	 * 专家名称
	 */
	private String expertName;
	
	private String pageNo;
	
	private String expertPageNo;
	/**
	 * 知识主题
	 */
	private String newsName;
	/**
	 * 费用分类  0：免费，1：付费
	 */
	private String[] paymentKbn;
	/**
	 * 知识分类  1：政策；2：案例；3：知识；4：文章
	 */
	private String newsType;
	/**
	 * 来源分类  1：为平台；2：为专家
	 */
	private String[] sourceType;
	/**
	 * 视频主题
	 */
	private String videoName;
	/**
	 * 视频类型 1:视频 2:直播
	 */
	private String videoType;
	/**
	 * 课堂主题
	 */
	private String courseName;
	/**
	 * 课堂分类
	 */
	private String[] courseType;
	/**
	 * e问主题
	 */
	private String questionName;
	
	private String questionPageNo;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 单位性质
	 */
	private String corpType;
	/**
	 * 常驻省
	 */
	private String cityP;
	/**
	 * 常驻市
	 */
	private String cityC;
	
	
	/**
	 * 返回needsName的值
	 * @return String needsName的值
	 */
	public String getNeedsName() {
		return needsName;
	}
	/**
	 * 设置needsName的值
	 * @param  needsName needsName的值
	 */
	public void setNeedsName(String needsName) {
		this.needsName = needsName;
	}
	/**
	 * 返回answerStatus的值
	 * @return String[] answerStatus的值
	 */
	public String[] getAnswerStatus() {
		return answerStatus;
	}
	/**
	 * 设置answerStatus的值
	 * @param  answerStatus answerStatus的值
	 */
	public void setAnswerStatus(String[] answerStatus) {
		this.answerStatus = answerStatus;
	}
	/**
	 * 返回field的值
	 * @return String[] field的值
	 */
	public String[] getField() {
		return field;
	}
	/**
	 * 设置field的值
	 * @param  field field的值
	 */
	public void setField(String[] field) {
		this.field = field;
	}
	/**
	 * 返回needsType的值
	 * @return String needsType的值
	 */
	public String getNeedsType() {
		return needsType;
	}
	/**
	 * 设置needsType的值
	 * @param  needsType needsType的值
	 */
	public void setNeedsType(String needsType) {
		this.needsType = needsType;
	}
	/**
	 * 返回sDate的值
	 * @return String sDate的值
	 */
	public String getsDate() {
		return sDate;
	}
	/**
	 * 设置sDate的值
	 * @param  sDate sDate的值
	 */
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	/**
	 * 返回eDate的值
	 * @return String eDate的值
	 */
	public String geteDate() {
		return eDate;
	}
	/**
	 * 设置eDate的值
	 * @param  eDate eDate的值
	 */
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	/**
	 * 返回auditStatus的值
	 * @return String[] auditStatus的值
	 */
	public String[] getAuditStatus() {
		return auditStatus;
	}
	/**
	 * 设置auditStatus的值
	 * @param  auditStatus auditStatus的值
	 */
	public void setAuditStatus(String[] auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * 返回recommendKbn的值
	 * @return String[] recommendKbn的值
	 */
	public String[] getRecommendKbn() {
		return recommendKbn;
	}
	/**
	 * 设置recommendKbn的值
	 * @param  recommendKbn recommendKbn的值
	 */
	public void setRecommendKbn(String[] recommendKbn) {
		this.recommendKbn = recommendKbn;
	}
	/**
	 * 返回expertName的值
	 * @return String expertName的值
	 */
	public String getExpertName() {
		return expertName;
	}
	/**
	 * 设置expertName的值
	 * @param  expertName expertName的值
	 */
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	/**
	 * 返回pageNo的值
	 * @return String pageNo的值
	 */
	public String getPageNo() {
		return pageNo;
	}
	/**
	 * 设置pageNo的值
	 * @param  pageNo pageNo的值
	 */
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	/**
	 * 返回expertPageNo的值
	 * @return String expertPageNo的值
	 */
	public String getExpertPageNo() {
		return expertPageNo;
	}
	/**
	 * 设置expertPageNo的值
	 * @param  expertPageNo expertPageNo的值
	 */
	public void setExpertPageNo(String expertPageNo) {
		this.expertPageNo = expertPageNo;
	}
	/**
	 * 返回newsName的值
	 * @return String newsName的值
	 */
	public String getNewsName() {
		return newsName;
	}
	/**
	 * 设置newsName的值
	 * @param  newsName newsName的值
	 */
	public void setNewsName(String newsName) {
		this.newsName = newsName;
	}
	/**
	 * 返回paymentKbn的值
	 * @return String[] paymentKbn的值
	 */
	public String[] getPaymentKbn() {
		return paymentKbn;
	}
	/**
	 * 设置paymentKbn的值
	 * @param  paymentKbn paymentKbn的值
	 */
	public void setPaymentKbn(String[] paymentKbn) {
		this.paymentKbn = paymentKbn;
	}
	/**
	 * 返回newsType的值
	 * @return String newsType的值
	 */
	public String getNewsType() {
		return newsType;
	}
	/**
	 * 设置newsType的值
	 * @param  newsType newsType的值
	 */
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	/**
	 * 返回sourceType的值
	 * @return String[] sourceType的值
	 */
	public String[] getSourceType() {
		return sourceType;
	}
	/**
	 * 设置sourceType的值
	 * @param  sourceType sourceType的值
	 */
	public void setSourceType(String[] sourceType) {
		this.sourceType = sourceType;
	}
	/**
	 * 返回videoName的值
	 * @return String videoName的值
	 */
	public String getVideoName() {
		return videoName;
	}
	/**
	 * 设置videoName的值
	 * @param  videoName videoName的值
	 */
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	/**
	 * 返回videoType的值
	 * @return String videoType的值
	 */
	public String getVideoType() {
		return videoType;
	}
	/**
	 * 设置videoType的值
	 * @param  videoType videoType的值
	 */
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}
	/**
	 * 返回courseName的值
	 * @return String courseName的值
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * 设置courseName的值
	 * @param  courseName courseName的值
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * 返回courseType的值
	 * @return String[] courseType的值
	 */
	public String[] getCourseType() {
		return courseType;
	}
	/**
	 * 设置courseType的值
	 * @param  courseType courseType的值
	 */
	public void setCourseType(String[] courseType) {
		this.courseType = courseType;
	}
	/**
	 * 返回questionName的值
	 * @return String questionName的值
	 */
	public String getQuestionName() {
		return questionName;
	}
	/**
	 * 设置questionName的值
	 * @param  questionName questionName的值
	 */
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	/**
	 * 返回questionPageNo的值
	 * @return String questionPageNo的值
	 */
	public String getQuestionPageNo() {
		return questionPageNo;
	}
	/**
	 * 设置questionPageNo的值
	 * @param  questionPageNo questionPageNo的值
	 */
	public void setQuestionPageNo(String questionPageNo) {
		this.questionPageNo = questionPageNo;
	}
	/**
	 * 返回userName的值
	 * @return String userName的值
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置userName的值
	 * @param  userName userName的值
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 返回corpType的值
	 * @return String corpType的值
	 */
	public String getCorpType() {
		return corpType;
	}
	/**
	 * 设置corpType的值
	 * @param  corpType corpType的值
	 */
	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}
	/**
	 * 返回cityP的值
	 * @return String cityP的值
	 */
	public String getCityP() {
		return cityP;
	}
	/**
	 * 设置cityP的值
	 * @param  cityP cityP的值
	 */
	public void setCityP(String cityP) {
		this.cityP = cityP;
	}
	/**
	 * 返回cityC的值
	 * @return String cityC的值
	 */
	public String getCityC() {
		return cityC;
	}
	/**
	 * 设置cityC的值
	 * @param  cityC cityC的值
	 */
	public void setCityC(String cityC) {
		this.cityC = cityC;
	}
	
}

