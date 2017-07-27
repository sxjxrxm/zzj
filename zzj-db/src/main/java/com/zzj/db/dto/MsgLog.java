/**
 * Project Name:zzj-db
 * File Name:MsgLog.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * <p><strong>类名: </strong></p>MsgLog表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义MsgLog表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日上午9:36:30 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MsgLog implements Serializable{
	
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1424037707492837830L;

	/**
	 * 主键
	 */
	private Long id;

    /**
    * userId
    */
    private String fromAccount;
    
    /**
     * userName
     */
    private String fromAccountName;

    /**
    * 课堂id
    */
    private String groupId;

    /**
    * 客户端IP地址
    */
    private String clientIp;

    /**
    * 客户端平台。对应不同的平台类型，可能的取值有：
    */
    private String optPlatform;

    /**
    * 按时间正序排列
    */
    private Date msgTimestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getOptPlatform() {
        return optPlatform;
    }

    public void setOptPlatform(String optPlatform) {
        this.optPlatform = optPlatform;
    }

    public Date getMsgTimestamp() {
        return msgTimestamp;
    }

    public void setMsgTimestamp(Date msgTimestamp) {
        this.msgTimestamp = msgTimestamp;
    }

	/**
	 * 返回fromAccountName的值
	 * @return String fromAccountName的值
	 */
	public String getFromAccountName() {
		return fromAccountName;
	}

	/**
	 * 设置fromAccountName的值
	 * @param  fromAccountName fromAccountName的值
	 */
	public void setFromAccountName(String fromAccountName) {
		this.fromAccountName = fromAccountName;
	}
    
}