/**
 * Project Name:zzj-db
 * File Name:MstUsersInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * <p><strong>类名: </strong></p>Mst_Users_Info表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义Mst_Users_Info表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日上午9:36:35 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MstUsersInfo implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2380002122110954162L;

    /**
    * 用户ID
    */
    private String userId;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 登录密码
    */
    private String password;

    /**
    * 角色ID
    */
    private String roleId;

    /**
    * 手机号
    */
    private String phoneNumber;

    /**
    * 错误登录次数
    */
    private Integer errorCount;

    /**
    * 逻辑删除flag
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
    * 更新人ID
    */
    private String updateId;

    /**
    * 更新时间
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
	 * 返回password的值
	 * @return String password的值
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置password的值
	 * @param  password password的值
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 返回roleId的值
	 * @return String roleId的值
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 设置roleId的值
	 * @param  roleId roleId的值
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 返回phoneNumber的值
	 * @return String phoneNumber的值
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 设置phoneNumber的值
	 * @param  phoneNumber phoneNumber的值
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 返回errorCount的值
	 * @return Integer errorCount的值
	 */
	public Integer getErrorCount() {
		return errorCount;
	}

	/**
	 * 设置errorCount的值
	 * @param  errorCount errorCount的值
	 */
	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
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

  
}