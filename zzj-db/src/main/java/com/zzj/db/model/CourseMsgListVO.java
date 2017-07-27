/**
 * Project Name:zzj-db
 * File Name:CourseMsgListVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p><strong>类名: </strong></p>CourseMsgListVO <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装在线课堂记录<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月21日下午12:49:50 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class CourseMsgListVO implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 92613238874850516L;

	/**
	 * 发送消息者
	 */
	private String msgOwner;
	
	/**
	 * 发送消息时间
	 */
	private Date msgDate;
	
	/**
	 * 发送的消息
	 */
	private List<CourseMsgVO> msgs;
	
	/**
	 * 腾讯云返回的MsgSeq
	 */
	private Integer msgSeq;

	/**
	 * 返回msgOwner的值
	 * @return String msgOwner的值
	 */
	public String getMsgOwner() {
		return msgOwner;
	}

	/**
	 * 设置msgOwner的值
	 * @param  msgOwner msgOwner的值
	 */
	public void setMsgOwner(String msgOwner) {
		this.msgOwner = msgOwner;
	}

	/**
	 * 返回msgDate的值
	 * @return Date msgDate的值
	 */
	public Date getMsgDate() {
		return msgDate;
	}

	/**
	 * 设置msgDate的值
	 * @param  msgDate msgDate的值
	 */
	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}

	/**
	 * 返回msgs的值
	 * @return List<CourseMsgVO> msgs的值
	 */
	public List<CourseMsgVO> getMsgs() {
		return msgs;
	}

	/**
	 * 设置msgs的值
	 * @param  msgs msgs的值
	 */
	public void setMsgs(List<CourseMsgVO> msgs) {
		this.msgs = msgs;
	}

	/**
	 * 返回msgSeq的值
	 * @return Integer msgSeq的值
	 */
	public Integer getMsgSeq() {
		return msgSeq;
	}

	/**
	 * 设置msgSeq的值
	 * @param  msgSeq msgSeq的值
	 */
	public void setMsgSeq(Integer msgSeq) {
		this.msgSeq = msgSeq;
	}

}

