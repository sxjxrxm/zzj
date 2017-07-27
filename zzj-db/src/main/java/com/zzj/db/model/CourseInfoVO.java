/**
 * Project Name:zzj-web
 * File Name:CourseInfoVO.java
 * Package Name:com.zzj.manage.modal
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.util.List;

import com.zzj.db.dto.CourseInfo;
import com.zzj.db.dto.RecommendInfo;
import com.zzj.db.dto.RecommendInfoKey;
import com.zzj.db.dto.TopicFieldInfo;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>CourseInfoVO <br/>
 * <p><strong>功能说明: </strong></p>课堂一览页面使用<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日上午9:36:35 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class CourseInfoVO extends CourseInfo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3657906598445270543L;
    
	/**
	 * 费用匪类
	 */
    private String paymentType;
	/**
	 * 课程分类
	 */
    private String courseTypeDisp;
    
	/**
	 * 参与人数
	 */
    private Integer scanCount;
    
	/**
	 * 领域
	 */
    private String field;
    
	/**
	 * 领域主键
	 */
    private List<TopicFieldInfoKey> fieldCd;
    
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
	 * 领域编码（不属于该专家的领域）
	 */
	private List<TopicFieldInfoKey> otherFieldCd;
	
	/**
	 * 置顶信息
	 */
	private RecommendInfo toTopInfo;
	
	/**
	 * 推荐信息
	 */
	private RecommendInfo recommendInfo;
	
	/**
	 * 领域列表信息
	 */
    private List<TopicFieldInfo> fieldInfo;
	
	/**
	 * 页码
	 */
	private Integer pageNo;
	
	/**
	 * 起始位置
	 */
	private Integer dbIndex;
	
	/**
	 * 起始位置
	 */
	private Integer pageSize;

    /**
    * 详情图片地址URL
    */
    private String bigIconUrl;

    /**
    * 列表图片地址URL
    */
    private String littleIconUrl;
    
    /**
    * 主讲人
    */
    private String speakerOld;
    
	/**
	 * 返回scanCount的值
	 * @return Integer scanCount的值
	 */
	public Integer getScanCount() {
		return scanCount;
	}

	/**
	 * 设置scanCount的值
	 * @param  scanCount scanCount的值
	 */
	public void setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
	}

	/**
	 * 返回paymentType的值
	 * @return String paymentType的值
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * 设置paymentType的值
	 * @param  paymentType paymentType的值
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * 返回courseTypeDisp的值
	 * @return String courseTypeDisp的值
	 */
	public String getCourseTypeDisp() {
		return courseTypeDisp;
	}

	/**
	 * 设置courseTypeDisp的值
	 * @param  courseTypeDisp courseTypeDisp的值
	 */
	public void setCourseTypeDisp(String courseTypeDisp) {
		this.courseTypeDisp = courseTypeDisp;
	}

	/**
	 * 返回fieldCd的值
	 * @return String fieldCd的值
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
	 * 返回field的值
	 * @return String field的值
	 */
	public String getField() {
		return field;
	}

	/**
	 * 设置field的值
	 * @param  field field的值
	 */
	public void setField(String field) {
		this.field = field;
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
	 * 返回bigIconUrl的值
	 * @return String bigIconUrl的值
	 */
	public String getBigIconUrl() {
		return bigIconUrl;
	}

	/**
	 * 设置bigIconUrl的值
	 * @param  bigIconUrl bigIconUrl的值
	 */
	public void setBigIconUrl(String bigIconUrl) {
		this.bigIconUrl = bigIconUrl;
	}

	/**
	 * 返回littleIconUrl的值
	 * @return String littleIconUrl的值
	 */
	public String getLittleIconUrl() {
		return littleIconUrl;
	}

	/**
	 * 设置littleIconUrl的值
	 * @param  littleIconUrl littleIconUrl的值
	 */
	public void setLittleIconUrl(String littleIconUrl) {
		this.littleIconUrl = littleIconUrl;
	}

	/**
	 * 返回speakerOld的值
	 * @return String speakerOld的值
	 */
	public String getSpeakerOld() {
		return speakerOld;
	}

	/**
	 * 设置speakerOld的值
	 * @param  speakerOld speakerOld的值
	 */
	public void setSpeakerOld(String speakerOld) {
		this.speakerOld = speakerOld;
	}
	
}
