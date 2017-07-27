/**
 * Project Name:zzj-db
 * File Name:SlideShowInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>SlideShowInfo表对象定义类br/>
 * <p><strong>功能说明: </strong></p>定义SlideShowInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:56:30 <br/>
 * @author   刘妍
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class SlideShowInfo implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 960243043813966569L;

	/**
     * 主键
     */
    private Integer slideCd;

    /**
    * 主题编号
    */
    private String topicCd;
    /**
     * 主题名称
     */
    private String topicName;
    /**
     * 机能
     */
    private String busiType;
    /**
     * 图片链接地址
     */
    private String url;
    /**
     * 主题图片地址
     */
    private String bigIcon;
    
    /**
     * 主题图片地址
     */
    private String bigIconUrl;

    /**
    * 显示序号
    */
    private Byte sortNo;

    /**
    * 逻辑删除flag  0：可用，1：不可用  default '0'
    */
    private Boolean deleteFlag;

    /**
    * 最后更新人ID
    */
    private String updateId;

    /**
    * 最后更新时间
    */
    private Date updateTime;

	/**
	 * 返回slideCd的值
	 * @return Integer slideCd的值
	 */
	public Integer getSlideCd() {
		return slideCd;
	}

	/**
	 * 设置slideCd的值
	 * @param  slideCd slideCd的值
	 */
	public void setSlideCd(Integer slideCd) {
		this.slideCd = slideCd;
	}

	/**
	 * 返回topicCd的值
	 * @return String topicCd的值
	 */
	public String getTopicCd() {
		return topicCd;
	}

	/**
	 * 设置topicCd的值
	 * @param  topicCd topicCd的值
	 */
	public void setTopicCd(String topicCd) {
		this.topicCd = topicCd;
	}

	/**
	 * 返回topicName的值
	 * @return String topicName的值
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * 设置topicName的值
	 * @param  topicName topicName的值
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	/**
	 * 返回busiType的值
	 * @return String busiType的值
	 */
	public String getBusiType() {
		return busiType;
	}

	/**
	 * 设置busiType的值
	 * @param  busiType busiType的值
	 */
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	/**
	 * 返回url的值
	 * @return String url的值
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置url的值
	 * @param  url url的值
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * 返回sortNo的值
	 * @return Byte sortNo的值
	 */
	public Byte getSortNo() {
		return sortNo;
	}

	/**
	 * 设置sortNo的值
	 * @param  sortNo sortNo的值
	 */
	public void setSortNo(Byte sortNo) {
		this.sortNo = sortNo;
	}

	/**
	 * 返回deleteFlag的值
	 * @return Boolean deleteFlag的值
	 */
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * 设置deleteFlag的值
	 * @param  deleteFlag deleteFlag的值
	 */
	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
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
    
}