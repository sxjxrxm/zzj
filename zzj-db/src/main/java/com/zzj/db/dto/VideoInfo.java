/**
 * Project Name:zzj-db
 * File Name:VideoInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * <p><strong>类名: </strong></p>Video_Info表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义Video_Info表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午13:11:30 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class VideoInfo implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6478567080558498435L;

	/**
    * 视频编码
    */
    private String videoCd;

    /**
    * 视频主题
    */
    private String videoName;

    /**
    * 视频简介
    */
    private String videoBrief;

    /**
    * 文件名称
    */
    private String videoFile;

    /**
    * 文件地址
    */
    private String videoAddress;

    /**
    * 费用分类  0：免费，1：付费 default '0'
    */
    private Integer paymentKbn;
    
    /**
     * 置顶标记 0：置顶，1：非置顶
     */
    private Integer topFlag;

    /**
    * 价格
    */
    private BigDecimal price;

    /**
    * 优惠价格
    */
    private BigDecimal priceDiscount;

    /**
    * 免费观看时长
    */
    private Integer freeTime;

    /**
    * 详情图片地址
    */
    private String bigIcon;

    /**
    * 列表图片地址
    */
    private String littleIcon;

    /**
    * 试看文件地址
    */
    private String freeAddress;

    /**
     * 关键词
     */
    private String keyword;

    /**
    * 逻辑删除flag  0：可用，1：不可用 default '0'
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
    * 最后更新人ID
    */
    private String updateId;

    /**
    * 最后更新时间
    */
    private Date updateTime;

	/**
	 * 返回videoCd的值
	 * @return String videoCd的值
	 */
	public String getVideoCd() {
		return videoCd;
	}

	/**
	 * 设置videoCd的值
	 * @param  videoCd videoCd的值
	 */
	public void setVideoCd(String videoCd) {
		this.videoCd = videoCd;
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
	 * 返回videoBrief的值
	 * @return String videoBrief的值
	 */
	public String getVideoBrief() {
		return videoBrief;
	}

	/**
	 * 设置videoBrief的值
	 * @param  videoBrief videoBrief的值
	 */
	public void setVideoBrief(String videoBrief) {
		this.videoBrief = videoBrief;
	}

	/**
	 * 返回videoFile的值
	 * @return String videoFile的值
	 */
	public String getVideoFile() {
		return videoFile;
	}

	/**
	 * 设置videoFile的值
	 * @param  videoFile videoFile的值
	 */
	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}

	/**
	 * 返回videoAddress的值
	 * @return String videoAddress的值
	 */
	public String getVideoAddress() {
		return videoAddress;
	}

	/**
	 * 设置videoAddress的值
	 * @param  videoAddress videoAddress的值
	 */
	public void setVideoAddress(String videoAddress) {
		this.videoAddress = videoAddress;
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
	 * 返回freeTime的值
	 * @return Integer freeTime的值
	 */
	public Integer getFreeTime() {
		return freeTime;
	}

	/**
	 * 设置freeTime的值
	 * @param  freeTime freeTime的值
	 */
	public void setFreeTime(Integer freeTime) {
		this.freeTime = freeTime;
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
	 * 返回freeAddress的值
	 * @return String freeAddress的值
	 */
	public String getFreeAddress() {
		return freeAddress;
	}

	/**
	 * 设置freeAddress的值
	 * @param  freeAddress freeAddress的值
	 */
	public void setFreeAddress(String freeAddress) {
		this.freeAddress = freeAddress;
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
}