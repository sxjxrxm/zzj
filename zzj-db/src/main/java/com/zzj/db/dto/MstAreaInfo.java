/**
 * Project Name:zzj-db
 * File Name:MstAreaInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;

/**
 * <p><strong>类名: </strong></p>MstAreaInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义MstAreaInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月25日下午5:36:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MstAreaInfo implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8553573768593000723L;

	/**
    * ID
    */
    private Double regionId;

    /**
    * 区域码
    */
    private String regionCode;

    /**
    * 区域名
    */
    private String regionName;

    /**
    * 所属区域ID
    */
    private Double parentId;

    /**
    * 区域等级
    */
    private Double regionLevel;

    /**
    * 区域排序
    */
    private Double regionOrder;

    /**
    * 区域英文名
    */
    private String regionNameEn;

    /**
    * 区域英文名简写
    */
    private String regionShortnameEn;

    /**
    * 邮政编码
    */
    private String postcode;

    /**
    * 电话区域号码
    */
    private String telAreaCode;

    /**
    * 电话号码长度
    */
    private Integer phoneLength;

    public Double getRegionId() {
        return regionId;
    }

    public void setRegionId(Double regionId) {
        this.regionId = regionId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Double getParentId() {
        return parentId;
    }

    public void setParentId(Double parentId) {
        this.parentId = parentId;
    }

    public Double getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(Double regionLevel) {
        this.regionLevel = regionLevel;
    }

    public Double getRegionOrder() {
        return regionOrder;
    }

    public void setRegionOrder(Double regionOrder) {
        this.regionOrder = regionOrder;
    }

    public String getRegionNameEn() {
        return regionNameEn;
    }

    public void setRegionNameEn(String regionNameEn) {
        this.regionNameEn = regionNameEn;
    }

    public String getRegionShortnameEn() {
        return regionShortnameEn;
    }

    public void setRegionShortnameEn(String regionShortnameEn) {
        this.regionShortnameEn = regionShortnameEn;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelAreaCode() {
        return telAreaCode;
    }

    public void setTelAreaCode(String telAreaCode) {
        this.telAreaCode = telAreaCode;
    }

    public Integer getPhoneLength() {
        return phoneLength;
    }

    public void setPhoneLength(Integer phoneLength) {
        this.phoneLength = phoneLength;
    }
}