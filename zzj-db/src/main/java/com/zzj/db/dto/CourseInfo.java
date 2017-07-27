/**
 * Project Name:zzj-db
 * File Name:CourseInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * <p><strong>类名: </strong></p>Course_Info表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义Course_Info表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午13:31:30 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class CourseInfo implements Serializable {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2535848593385882512L;

	/**
    * 课堂编码
    */
    private String courseCd;

    /**
    * 课堂主题
    */
    private String courseName;

    /**
    * 课堂简介
    */
    private String courseBrief;

    /**
    * 主讲人
    */
    private String speaker;

    /**
    * 聊天室ID
    */
    private String roomId;
    
    /**
    * 课堂分类
    */
    private Integer courseType;
    
    /**
    * 课堂开始时间
    */
    private Date startTime;

    /**
    * 课堂结束时间
    */
    private Date endTime;

    /**
    * 费用分类(0：免费，1：付费)
    */
    private Integer paymentKbn;

    /**
    * 价格
    */
    private BigDecimal price;

    /**
    * 优惠价格
    */
    private BigDecimal priceDiscount;

    /**
    * 详情图片地址
    */
    private String bigIcon;

    /**
    * 列表图片地址
    */
    private String littleIcon;
    
    /**
     * 关键字
     */
    private String keyword;
    
    /**
    * 置顶状态
    */
    private Integer topFlag;

    /**
     * 分享链接
     */
    private String shareUrl;

    /**
    * 逻辑删除flag(0：可用，1：不可用) 
    */
    private Integer deleteFlag;

    /**
    * 创建人ID
    */
    private String createId;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新人ID(最后更新人ID)
    */
    private String updateId;

    /**
    * 更新时间(最后更新时间)
    */
    private Date updateTime;

	/**
	 * 返回courseCd的值
	 * @return String courseCd的值
	 */
	public String getCourseCd() {
		return courseCd;
	}

	/**
	 * 设置courseCd的值
	 * @param  courseCd courseCd的值
	 */
	public void setCourseCd(String courseCd) {
		this.courseCd = courseCd;
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
	 * 返回courseBrief的值
	 * @return String courseBrief的值
	 */
	public String getCourseBrief() {
		return courseBrief;
	}

	/**
	 * 设置courseBrief的值
	 * @param  courseBrief courseBrief的值
	 */
	public void setCourseBrief(String courseBrief) {
		this.courseBrief = courseBrief;
	}

	/**
	 * 返回speaker的值
	 * @return String speaker的值
	 */
	public String getSpeaker() {
		return speaker;
	}

	/**
	 * 设置speaker的值
	 * @param  speaker speaker的值
	 */
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	/**
	 * 返回startTime的值
	 * @return Date startTime的值
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 设置startTime的值
	 * @param  startTime startTime的值
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 返回endTime的值
	 * @return Date endTime的值
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置endTime的值
	 * @param  endTime endTime的值
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 返回price的值
	 * @return BigDecimal price的值
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 设置price的值
	 * @param  price price的值
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 返回priceDiscount的值
	 * @return BigDecimal priceDiscount的值
	 */
	public BigDecimal getPriceDiscount() {
		return priceDiscount;
	}

	/**
	 * 设置priceDiscount的值
	 * @param  priceDiscount priceDiscount的值
	 */
	public void setPriceDiscount(BigDecimal priceDiscount) {
		this.priceDiscount = priceDiscount;
	}

	/**
	 * 返回bigIcon的值
	 * @return String bigIcon的值
	 */
	public String getBigIcon() {
		return bigIcon;
	}

	/**
	 * 设置bigIcon的值
	 * @param  bigIcon bigIcon的值
	 */
	public void setBigIcon(String bigIcon) {
		this.bigIcon = bigIcon;
	}

	/**
	 * 返回littleIcon的值
	 * @return String littleIcon的值
	 */
	public String getLittleIcon() {
		return littleIcon;
	}

	/**
	 * 设置littleIcon的值
	 * @param  littleIcon littleIcon的值
	 */
	public void setLittleIcon(String littleIcon) {
		this.littleIcon = littleIcon;
	}

	/**
	 * 返回keyword的值
	 * @return String keyword的值
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 设置keyword的值
	 * @param  keyword keyword的值
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 返回topFlag的值
	 * @return Integer topFlag的值
	 */
	public Integer getTopFlag() {
		return topFlag;
	}

	/**
	 * 设置topFlag的值
	 * @param  topFlag topFlag的值
	 */
	public void setTopFlag(Integer topFlag) {
		this.topFlag = topFlag;
	}

	/**
	 * 返回shareUrl的值
	 * @return String shareUrl的值
	 */
	public String getShareUrl() {
		return shareUrl;
	}

	/**
	 * 设置shareUrl的值
	 * @param  shareUrl shareUrl的值
	 */
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	/**
	 * 返回paymentKbn的值
	 * @return Integer paymentKbn的值
	 */
	public Integer getPaymentKbn() {
		return paymentKbn;
	}

	/**
	 * 设置paymentKbn的值
	 * @param  paymentKbn paymentKbn的值
	 */
	public void setPaymentKbn(Integer paymentKbn) {
		this.paymentKbn = paymentKbn;
	}

	/**
	 * 返回deleteFlag的值
	 * @return Integer deleteFlag的值
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * 设置deleteFlag的值
	 * @param  deleteFlag deleteFlag的值
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * 返回createId的值
	 * @return String createId的值
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * 设置createId的值
	 * @param  createId createId的值
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * 返回createTime的值
	 * @return Date createTime的值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置createTime的值
	 * @param  createTime createTime的值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 返回updateId的值
	 * @return String updateId的值
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * 设置updateId的值
	 * @param  updateId updateId的值
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	/**
	 * 返回updateTime的值
	 * @return Date updateTime的值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置updateTime的值
	 * @param  updateTime updateTime的值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 返回serialversionuid的值
	 * @return long serialversionuid的值
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * 返回courseType的值
	 * @return Integer courseType的值
	 */
	public Integer getCourseType() {
		return courseType;
	}

	/**
	 * 设置courseType的值
	 * @param  courseType courseType的值
	 */
	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}
}