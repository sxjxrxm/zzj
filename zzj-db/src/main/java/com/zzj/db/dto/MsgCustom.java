/**
 * Project Name:zzj-db
 * File Name:MsgCustom.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;

/**
 * <p><strong>类名: </strong></p>MsgCustom表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义MsgCustom表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日上午9:36:30 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MsgCustom implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = -3051709467819463838L;

	/**
	 * 主键
	 */
	private Long id;

    /**
    * 消息表主键
    */
    private Long mid;

    /**
    * 自定义消息数据
    */
    private String customData;

    /**
    * 自定义消息描述信息
    */
    private String customDesc;

    /**
    * 扩展字段;当接收方为iOS系统且应用处在后台时，此字段作为APNS请求包Payloads中的ext键值下发，Ext的协议格式由业务方确定，APNS只做透传
    */
    private String ext;

    /**
    * 自定义APNS推送铃音
    */
    private String sound;

    /**
    * 消息顺序
    */
    private Integer msgSeq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Integer getMsgSeq() {
        return msgSeq;
    }

    public void setMsgSeq(Integer msgSeq) {
        this.msgSeq = msgSeq;
    }

	/**
	 * 返回customDesc的值
	 * @return String customDesc的值
	 */
	public String getCustomDesc() {
		return customDesc;
	}

	/**
	 * 设置customDesc的值
	 * @param  customDesc customDesc的值
	 */
	public void setCustomDesc(String customDesc) {
		this.customDesc = customDesc;
	}
}