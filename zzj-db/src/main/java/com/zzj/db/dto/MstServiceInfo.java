/**
 * Project Name:zzj-db
 * File Name:MstServiceInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>MstServiceInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义MstServiceInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午9:24:30 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MstServiceInfo implements Serializable{
	
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4801265666069207819L;

	/**
    * 客服电话
    */
    private String phoneNo;

    /**
    * 客服邮箱
    */
    private String email;

    /**
    * 客服微信
    */
    private String weChat;

    /**
    * 二维码地址
    */
    private String qrcodeAddress;

    /**
    * 二维码Url地址
    */
    private String qrcodeAddressUrl;

    /**
    * 备注
    */
    private String memo;

    /**
    * 逻辑删除flag
    */
    private Boolean deleteFlag;

    /**
    * 更新人ID
    */
    private String updateId;

    /**
    * 更新时间
    */
    private Date updateTime;

	/**
	 * 返回phoneNo的值
	 * @return String phoneNo的值
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * 设置phoneNo的值
	 * @param  phoneNo phoneNo的值
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
	 * 返回weChat的值
	 * @return String weChat的值
	 */
	public String getWeChat() {
		return weChat;
	}

	/**
	 * 设置weChat的值
	 * @param  weChat weChat的值
	 */
	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	/**
	 * 返回qrcodeAddress的值
	 * @return String qrcodeAddress的值
	 */
	public String getQrcodeAddress() {
		return qrcodeAddress;
	}

	/**
	 * 设置qrcodeAddress的值
	 * @param  qrcodeAddress qrcodeAddress的值
	 */
	public void setQrcodeAddress(String qrcodeAddress) {
		this.qrcodeAddress = qrcodeAddress;
	}

	/**
	 * 返回memo的值
	 * @return String memo的值
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置memo的值
	 * @param  memo memo的值
	 */
	public void setMemo(String memo) {
		this.memo = memo;
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

	/**
	 * 返回qrcodeAddressUrl的值
	 * @return String qrcodeAddressUrl的值
	 */
	public String getQrcodeAddressUrl() {
		return qrcodeAddressUrl;
	}

	/**
	 * 设置qrcodeAddressUrl的值
	 * @param  qrcodeAddressUrl qrcodeAddressUrl的值
	 */
	public void setQrcodeAddressUrl(String qrcodeAddressUrl) {
		this.qrcodeAddressUrl = qrcodeAddressUrl;
	}
}