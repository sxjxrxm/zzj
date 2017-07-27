/**
 * Project Name:zzj-db
 * File Name:CorpInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>CorpInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义CorpInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月05日下午14:19:30 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class CorpInfo implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8796370002122165331L;

	/**
    * 专家ID
    */
    private String userId;

    /**
    * 专家名
    */
    private String userName;

    /**
    * 工作单位
    */
    private String company;

    /**
    * 头衔职称
    */
    private String rank;

    /**
    * 单位性质
    */
    private String corpType;

    /**
    * 工作年限
    */
    private String experience;

    /**
    * 专家手机号
    */
    private String phone;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 省
    */
    private String cityP;

    /**
    * 市
    */
    private String cityC;

    /**
    * 区
    */
    private String cityD;

    /**
    * 政企简介
    */
    private String brief;

    /**
    * 审核状态  0：待审核，1：已审核
    */
    private Integer status;

    /**
    * 审核人ID
    */
    private String auditId;

    /**
    * 审核时间
    */
    private Date auditTime;

    /**
    * 拒绝理由
    */
    private String refuseMemo;

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
	 * 返回userId的值
	 * @return String userId的值
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 设置userId的值
	 * @param  userId userId的值
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * 返回company的值
	 * @return String company的值
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * 设置company的值
	 * @param  company company的值
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * 返回rank的值
	 * @return String rank的值
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * 设置rank的值
	 * @param  rank rank的值
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * 返回corpType的值
	 * @return Boolean corpType的值
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
	 * 返回experience的值
	 * @return Boolean experience的值
	 */
	public String getExperience() {
		return experience;
	}

	/**
	 * 设置experience的值
	 * @param  experience experience的值
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}

	/**
	 * 返回phone的值
	 * @return String phone的值
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置phone的值
	 * @param  phone phone的值
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 返回email的值
	 * @return String email的值
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置email的值
	 * @param  email email的值
	 */
	public void setEmail(String email) {
		this.email = email;
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

	/**
	 * 返回cityD的值
	 * @return String cityD的值
	 */
	public String getCityD() {
		return cityD;
	}

	/**
	 * 设置cityD的值
	 * @param  cityD cityD的值
	 */
	public void setCityD(String cityD) {
		this.cityD = cityD;
	}

	/**
	 * 返回brief的值
	 * @return String brief的值
	 */
	public String getBrief() {
		return brief;
	}

	/**
	 * 设置brief的值
	 * @param  brief brief的值
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

	/**
	 * 返回status的值
	 * @return Boolean status的值
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置status的值
	 * @param  status status的值
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 返回auditId的值
	 * @return String auditId的值
	 */
	public String getAuditId() {
		return auditId;
	}

	/**
	 * 设置auditId的值
	 * @param  auditId auditId的值
	 */
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	/**
	 * 返回auditTime的值
	 * @return Date auditTime的值
	 */
	public Date getAuditTime() {
		return auditTime;
	}

	/**
	 * 设置auditTime的值
	 * @param  auditTime auditTime的值
	 */
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	/**
	 * 返回refuseMemo的值
	 * @return String refuseMemo的值
	 */
	public String getRefuseMemo() {
		return refuseMemo;
	}

	/**
	 * 设置refuseMemo的值
	 * @param  refuseMemo refuseMemo的值
	 */
	public void setRefuseMemo(String refuseMemo) {
		this.refuseMemo = refuseMemo;
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
}