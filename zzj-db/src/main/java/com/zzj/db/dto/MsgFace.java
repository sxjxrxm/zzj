/**
 * Project Name:zzj-db
 * File Name:MsgFace.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
/**
 * <p><strong>类名: </strong></p>MsgFace表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义MsgFace表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日上午9:36:30 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MsgFace implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = -6496525031303149750L;

	/**
	 * 主键
	 */
	private Long id;

    /**
    * 消息表主键
    */
    private Long mid;

    /**
    * 表情索引，用户自定义
    */
    private Integer faceIndex;

    /**
    * 额外数据
    */
    private String faceData;

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

    public Integer getMsgSeq() {
        return msgSeq;
    }

    public void setMsgSeq(Integer msgSeq) {
        this.msgSeq = msgSeq;
    }

	/**
	 * 返回faceIndex的值
	 * @return Integer faceIndex的值
	 */
	public Integer getFaceIndex() {
		return faceIndex;
	}

	/**
	 * 设置faceIndex的值
	 * @param  faceIndex faceIndex的值
	 */
	public void setFaceIndex(Integer faceIndex) {
		this.faceIndex = faceIndex;
	}

	/**
	 * 返回faceData的值
	 * @return String faceData的值
	 */
	public String getFaceData() {
		return faceData;
	}

	/**
	 * 设置faceData的值
	 * @param  faceData faceData的值
	 */
	public void setFaceData(String faceData) {
		this.faceData = faceData;
	}
}