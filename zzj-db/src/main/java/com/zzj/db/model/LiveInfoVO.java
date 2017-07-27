/**
 * Project Name:zzj-web
 * File Name:LiveInfoVO.java
 * Package Name:com.zzj.manage.modal
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.util.Date;
import java.util.List;

import com.zzj.db.dto.LiveInfo;
import com.zzj.db.dto.TopicFieldInfo;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>LiveInfoVO表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义LiveInfoVO表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日上午9:36:35 <br/>
 * @author 任晓茂
 * @version 1.0
 * @since JDK 1.8
 */
public class LiveInfoVO extends LiveInfo {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6859483467541003302L;

	/**
	 * 视频分类 1：视频，2：直播
	 */
	private String videoType;

	/**
	 * 费用分类
	 */
	private String paymentKbnDisp;

	/**
	 * 提出时间start
	 */
	private Date sDate;

	/**
	 * 提出时间end
	 */
	private Date eDate;

	/**
	 * 领域
	 */
	private String field;

	/**
	 * 领域主键
	 */
	private List<TopicFieldInfoKey> fieldCd;

	// /**
	// * 推荐区分 1：推荐，2：置顶
	// */
	// private List<RecommendInfoKey> recommend;
	//
	// /**
	// * 推荐区分 1：推荐，2：置顶
	// */
	// private String recommendKbn;
	//
	// /**
	// * 推荐语
	// */
	// private String recommendMsg;

	/**
	 * 领域编码（不属于该专家的领域）
	 */
	private List<TopicFieldInfoKey> otherFieldCd;

	// /**
	// * 置顶信息
	// */
	// private RecommendInfo toTopInfo;
	//
	// /**
	// * 推荐信息
	// */
	// private RecommendInfo recommendInfo;

	/**
	 * 领域列表信息
	 */
	private List<TopicFieldInfo> fieldInfo;

	/**
	 * 点赞数
	 */
	private Integer likeCount;

	/**
	 * 收藏数
	 */
	private Integer collectCount;

	/**
	 * 播放次数
	 */
	private Integer scanCount;

	/**
	 * startFlag : 1 直播开始， 0直播未开始
	 */
	private String startFlag;
	
	/**
	 * invalidFlag : 1 已过期， 0未过期
	 */
	private String invalidFlag;
	
	/**
	 * 详情图片地址
	 */
	private String bigIconUrl;

	/**
	 * 列表图片地址
	 */
	private String littleIconUrl;

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
	 * 聊天室ID
	 */
	private String roomId;

	/**
	 * 返回fieldCd的值
	 * 
	 * @return String fieldCd的值
	 */
	public List<TopicFieldInfoKey> getFieldCd() {
		return fieldCd;
	}

	/**
	 * 设置fieldCd的值
	 * 
	 * @param fieldCd
	 *            fieldCd的值
	 */
	public void setFieldCd(List<TopicFieldInfoKey> fieldCd) {
		this.fieldCd = fieldCd;
	}

	/**
	 * 返回field的值
	 * 
	 * @return String field的值
	 */
	public String getField() {
		return field;
	}

	/**
	 * 设置field的值
	 * 
	 * @param field
	 *            field的值
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * 返回sDate的值
	 * 
	 * @return Date sDate的值
	 */
	public Date getsDate() {
		return sDate;
	}

	/**
	 * 设置sDate的值
	 * 
	 * @param sDate
	 *            sDate的值
	 */
	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	/**
	 * 返回eDate的值
	 * 
	 * @return Date eDate的值
	 */
	public Date geteDate() {
		return eDate;
	}

	/**
	 * 设置eDate的值
	 * 
	 * @param eDate
	 *            eDate的值
	 */
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	/**
	 * 返回otherFieldCd的值
	 * 
	 * @return List<TopicFieldInfoKey> otherFieldCd的值
	 */
	public List<TopicFieldInfoKey> getOtherFieldCd() {
		return otherFieldCd;
	}

	/**
	 * 设置otherFieldCd的值
	 * 
	 * @param otherFieldCd
	 *            otherFieldCd的值
	 */
	public void setOtherFieldCd(List<TopicFieldInfoKey> otherFieldCd) {
		this.otherFieldCd = otherFieldCd;
	}

	/**
	 * 返回fieldInfo的值
	 * 
	 * @return List<TopicFieldInfo> fieldInfo的值
	 */
	public List<TopicFieldInfo> getFieldInfo() {
		return fieldInfo;
	}

	/**
	 * 设置fieldInfo的值
	 * 
	 * @param fieldInfo
	 *            fieldInfo的值
	 */
	public void setFieldInfo(List<TopicFieldInfo> fieldInfo) {
		this.fieldInfo = fieldInfo;
	}

	/**
	 * 返回videoType的值
	 * 
	 * @return String videoType的值
	 */
	public String getVideoType() {
		return videoType;
	}

	/**
	 * 设置videoType的值
	 * 
	 * @param videoType
	 *            videoType的值
	 */
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	/**
	 * 返回paymentKbnDisp的值
	 * 
	 * @return String paymentKbnDisp的值
	 */
	public String getPaymentKbnDisp() {
		return paymentKbnDisp;
	}

	/**
	 * 设置paymentKbnDisp的值
	 * 
	 * @param paymentKbnDisp
	 *            paymentKbnDisp的值
	 */
	public void setPaymentKbnDisp(String paymentKbnDisp) {
		this.paymentKbnDisp = paymentKbnDisp;
	}

	/**
	 * 返回pageNo的值
	 * 
	 * @return Integer pageNo的值
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * 设置pageNo的值
	 * 
	 * @param pageNo
	 *            pageNo的值
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 返回dbIndex的值
	 * 
	 * @return Integer dbIndex的值
	 */
	public Integer getDbIndex() {
		return dbIndex;
	}

	/**
	 * 设置dbIndex的值
	 * 
	 * @param dbIndex
	 *            dbIndex的值
	 */
	public void setDbIndex(Integer dbIndex) {
		this.dbIndex = dbIndex;
	}

	/**
	 * 返回pageSize的值
	 * 
	 * @return Integer pageSize的值
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * 设置pageSize的值
	 * 
	 * @param pageSize
	 *            pageSize的值
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 返回likeCount的值
	 * 
	 * @return Integer likeCount的值
	 */
	public Integer getLikeCount() {
		return likeCount;
	}

	/**
	 * 设置likeCount的值
	 * 
	 * @param likeCount
	 *            likeCount的值
	 */
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	/**
	 * 返回collectCount的值
	 * 
	 * @return Integer collectCount的值
	 */
	public Integer getCollectCount() {
		return collectCount;
	}

	/**
	 * 设置collectCount的值
	 * 
	 * @param collectCount
	 *            collectCount的值
	 */
	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	/**
	 * 返回scanCount的值
	 * 
	 * @return Integer scanCount的值
	 */
	public Integer getScanCount() {
		return scanCount;
	}

	/**
	 * 设置scanCount的值
	 * 
	 * @param scanCount
	 *            scanCount的值
	 */
	public void setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
	}

	/**
	 * 返回bigIconUrl的值
	 * 
	 * @return String bigIconUrl的值
	 */
	public String getBigIconUrl() {
		return bigIconUrl;
	}

	/**
	 * 设置bigIconUrl的值
	 * 
	 * @param bigIconUrl
	 *            bigIconUrl的值
	 */
	public void setBigIconUrl(String bigIconUrl) {
		this.bigIconUrl = bigIconUrl;
	}

	/**
	 * 返回littleIconUrl的值
	 * 
	 * @return String littleIconUrl的值
	 */
	public String getLittleIconUrl() {
		return littleIconUrl;
	}

	/**
	 * 设置littleIconUrl的值
	 * 
	 * @param littleIconUrl
	 *            littleIconUrl的值
	 */
	public void setLittleIconUrl(String littleIconUrl) {
		this.littleIconUrl = littleIconUrl;
	}

	/**
	 * 返回startFlag的值
	 * @return String startFlag的值
	 */
	public String getStartFlag() {
		return startFlag;
	}

	/**
	 * 设置startFlag的值
	 * @param  startFlag startFlag的值
	 */
	public void setStartFlag(String startFlag) {
		this.startFlag = startFlag;
	}

	/**
	 * 返回roomId的值
	 * @return String roomId的值
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * 设置roomId的值
	 * @param  roomId roomId的值
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * 返回invalidFlag的值
	 * @return String invalidFlag的值
	 */
	public String getInvalidFlag() {
		return invalidFlag;
	}

	/**
	 * 设置invalidFlag的值
	 * @param  invalidFlag invalidFlag的值
	 */
	public void setInvalidFlag(String invalidFlag) {
		this.invalidFlag = invalidFlag;
	}

}
