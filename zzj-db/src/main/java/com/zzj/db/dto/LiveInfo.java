/**
 * Project Name:zzj-db
 * File Name:LiveInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>LiveInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义LiveInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日上午9:36:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class LiveInfo implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8315649763861969132L;

	/**
    * 直播编码
    */
    private String liveCd;

    /**
    * 直播主题
    */
    private String liveName;

    /**
    * 直播简介
    */
    private String liveBrief;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;
    
    /**
     * 过期时间
     */
    private Date invalidDate;

    /**
    * 费用分类  0：免费，1：付费  default '0'
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
    * 推流地址
    */
    private String pushAddress;

    /**
    * 播放地址(rtmp)
    */
    private String rtmpAddress;
    
    /**
     * 播放地址(flv)
     */
    private String flvAddress;
    
    /**
     * 播放地址(hls)
     */
    private String hlsAddress;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 分享地址
     */
    private String shareUrl;

    /**
    * 逻辑删除flag  0：可用（已发布），1：不可用  ，2：可用（已保存，未发布）default '2'
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
	 * 聊天室ID
	 */
	private String roomId;

    public String getLiveCd() {
        return liveCd;
    }

    public void setLiveCd(String liveCd) {
        this.liveCd = liveCd;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    public String getLiveBrief() {
        return liveBrief;
    }

    public void setLiveBrief(String liveBrief) {
        this.liveBrief = liveBrief;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPaymentKbn() {
        return paymentKbn;
    }

    public void setPaymentKbn(Integer paymentKbn) {
        this.paymentKbn = paymentKbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(BigDecimal priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getBigIcon() {
        return bigIcon;
    }

    public void setBigIcon(String bigIcon) {
        this.bigIcon = bigIcon;
    }

    public String getLittleIcon() {
        return littleIcon;
    }

    public void setLittleIcon(String littleIcon) {
        this.littleIcon = littleIcon;
    }

    public String getPushAddress() {
        return pushAddress;
    }

    public void setPushAddress(String pushAddress) {
        this.pushAddress = pushAddress;
    }

    /**
	 * 返回rtmpAddress的值
	 * @return String rtmpAddress的值
	 */
	public String getRtmpAddress() {
		return rtmpAddress;
	}

	/**
	 * 设置rtmpAddress的值
	 * @param  rtmpAddress rtmpAddress的值
	 */
	public void setRtmpAddress(String rtmpAddress) {
		this.rtmpAddress = rtmpAddress;
	}

	/**
	 * 返回flvAddress的值
	 * @return String flvAddress的值
	 */
	public String getFlvAddress() {
		return flvAddress;
	}

	/**
	 * 设置flvAddress的值
	 * @param  flvAddress flvAddress的值
	 */
	public void setFlvAddress(String flvAddress) {
		this.flvAddress = flvAddress;
	}

	/**
	 * 返回hlsAddress的值
	 * @return String hlsAddress的值
	 */
	public String getHlsAddress() {
		return hlsAddress;
	}

	/**
	 * 设置hlsAddress的值
	 * @param  hlsAddress hlsAddress的值
	 */
	public void setHlsAddress(String hlsAddress) {
		this.hlsAddress = hlsAddress;
	}

	public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	/**
	 * 返回invalidDate的值
	 * @return Date invalidDate的值
	 */
	public Date getInvalidDate() {
		return invalidDate;
	}

	/**
	 * 设置invalidDate的值
	 * @param  invalidDate invalidDate的值
	 */
	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
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
}