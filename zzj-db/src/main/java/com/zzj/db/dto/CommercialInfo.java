/**
 * Project Name:zzj-db
 * File Name:CommercialInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * <p><strong>类名: </strong></p>CommercialInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义CommercialInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午13:42:30 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class CommercialInfo implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -533154445605804831L;

	/**
    * 广告编码
    */
    private String commercialCd;

    /**
    * 广告主题
    */
    private String commercialName;

    /**
    * 广告简介
    */
    private String commercialBrief;

    /**
    * 图片地址
    */
    private String iconAddress;

    /**
    * 逻辑删除flag  0：可用，1：不可用  default '0'
    */
    private Boolean deleteFlag;

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
	 * 返回commercialCd的值
	 * @return String commercialCd的值
	 */
	public String getCommercialCd() {
		return commercialCd;
	}

	/**
	 * 设置commercialCd的值
	 * @param  commercialCd commercialCd的值
	 */
	public void setCommercialCd(String commercialCd) {
		this.commercialCd = commercialCd;
	}

	/**
	 * 返回commercialName的值
	 * @return String commercialName的值
	 */
	public String getCommercialName() {
		return commercialName;
	}

	/**
	 * 设置commercialName的值
	 * @param  commercialName commercialName的值
	 */
	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}

	/**
	 * 返回commercialBrief的值
	 * @return String commercialBrief的值
	 */
	public String getCommercialBrief() {
		return commercialBrief;
	}

	/**
	 * 设置commercialBrief的值
	 * @param  commercialBrief commercialBrief的值
	 */
	public void setCommercialBrief(String commercialBrief) {
		this.commercialBrief = commercialBrief;
	}

	/**
	 * 返回iconAddress的值
	 * @return String iconAddress的值
	 */
	public String getIconAddress() {
		return iconAddress;
	}

	/**
	 * 设置iconAddress的值
	 * @param  iconAddress iconAddress的值
	 */
	public void setIconAddress(String iconAddress) {
		this.iconAddress = iconAddress;
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

    
}