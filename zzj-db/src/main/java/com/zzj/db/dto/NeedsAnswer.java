/**
 * Project Name:zzj-db
 * File Name:NeedsAnswer.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.util.Date;
/**
 * <p><strong>类名: </strong></p>NEEDS_ANSWER表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义NEEDS_ANSWER表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日上午9:36:35 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class NeedsAnswer extends NeedsAnswerKey {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5592725374498868248L;

	/**
    * 回答内容
    */
    private String answerContent;

    /**
    * 创建人ID
    */
    private String createId;
    
    /**
    * 创建人名称
    */
    private String createName;

    /**
    * 创建时间
    */
    private Date createTime;

	/**
	 * 返回answerContent的值
	 * @return String answerContent的值
	 */
	public String getAnswerContent() {
		return answerContent;
	}

	/**
	 * 设置answerContent的值
	 * @param  answerContent answerContent的值
	 */
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
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
	 * 返回createName的值
	 * @return String createName的值
	 */
	public String getCreateName() {
		return createName;
	}

	/**
	 * 设置createName的值
	 * @param  createName createName的值
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
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

}