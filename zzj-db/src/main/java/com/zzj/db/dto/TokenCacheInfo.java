/**
 * Project Name:zzj-db
 * File Name:TokenCacheInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>TokenCacheInfo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>TokenCacheInfo业务数据相关操作处理.  <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月20日下午1:36:01 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class TokenCacheInfo implements Serializable {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4279362945704475117L;

    /**
     * TOKEN_ID或签名
     */
	private String tokenId;

    /**
     * 1：TOKEN，2：签名
     */
    private Integer cacheType;

    /**
     * 手机号码
     */
    private String phoneNo;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 有效期限
     */
    private Integer dayNum;
    
	/**
	 * 返回tokenId的值
	 * @return String tokenId的值
	 */
	public String getTokenId() {
		return tokenId;
	}

	/**
	 * 设置tokenId的值
	 * @param  tokenId tokenId的值
	 */
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	/**
	 * 返回cacheType的值
	 * @return Integer cacheType的值
	 */
	public Integer getCacheType() {
		return cacheType;
	}

	/**
	 * 设置cacheType的值
	 * @param  cacheType cacheType的值
	 */
	public void setCacheType(Integer cacheType) {
		this.cacheType = cacheType;
	}

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
	 * 返回dayNum的值
	 * @return Integer dayNum的值
	 */
	public Integer getDayNum() {
		return dayNum;
	}

	/**
	 * 设置dayNum的值
	 * @param  dayNum dayNum的值
	 */
	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}
}