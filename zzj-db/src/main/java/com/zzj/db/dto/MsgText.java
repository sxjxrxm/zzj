/**
 * Project Name:zzj-db
 * File Name:MsgText.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;

/**
 * <p><strong>类名: </strong></p>MsgText表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义MsgText表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日上午9:36:30 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MsgText implements Serializable{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 517885386915562506L;

	/**
	 * 主键
	 */
	private Long id;

    /**
    * 消息表主键
    */
    private Long mid;

    /**
	 * 文本
	 */
    private String text;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getMsgSeq() {
        return msgSeq;
    }

    public void setMsgSeq(Integer msgSeq) {
        this.msgSeq = msgSeq;
    }
}