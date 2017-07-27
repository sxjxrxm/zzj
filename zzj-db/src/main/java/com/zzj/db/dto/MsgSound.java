/**
 * Project Name:zzj-db
 * File Name:MsgSound.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
/**
 * <p><strong>类名: </strong></p>MsgSound表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义MsgSound表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日上午9:36:30 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MsgSound implements Serializable{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -7087341323372540775L;

	/**
	 * 主键
	 */
	private Long id;

    /**
    * 消息表主键
    */
    private Long mid;

    /**
    * 语音序列号
    */
    private String uuid;

    /**
    * 语音数据大小
    */
    private Integer size;

    /**
    * 语音时长，单位:秒
    */
    private Integer second;

    /**
    * 语音地址
    */
    private String address;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMsgSeq() {
        return msgSeq;
    }

    public void setMsgSeq(Integer msgSeq) {
        this.msgSeq = msgSeq;
    }
}