/**
 * Project Name:zzj-db
 * File Name:QuestionInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>QuestionInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义QuestionInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月13日下午9:24:30 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class QuestionInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5719078227846793420L;

	/**
    * e问编码
    */
    private String questionCd;

    /**
    * e问主题
    */
    private String questionName;

    /**
    * e问内容
    */
    private String questionContent;

    /**
    * 审核状态
    */
    private Integer status;

    /**
    * 审核人ID
    */
    private String auditId;

    /**
    * 审核时间
    */
    private Date auditTime;

    /**
     * 审核拒绝理由：refuseType 从系统配置表对应
     */
    private String refuseMemo;
    
    /**
     * 关键字
     */
    private String keyWord;
    
    /**
    * 置顶状态
    */
    private Integer topFlag;
    /**
    * 逻辑删除flag(0：可用，1：不可用)
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
    * 更新人ID(最后更新人ID)
    */
    private String updateId;

    /**
    * 更新时间(最后更新时间)
    */
    private Date updateTime;

	/**
	 * 返回questionCd的值
	 * @return String questionCd的值
	 */
	public String getQuestionCd() {
		return questionCd;
	}

	/**
	 * 设置questionCd的值
	 * @param  questionCd questionCd的值
	 */
	public void setQuestionCd(String questionCd) {
		this.questionCd = questionCd;
	}

	/**
	 * 返回questionName的值
	 * @return String questionName的值
	 */
	public String getQuestionName() {
		return questionName;
	}

	/**
	 * 设置questionName的值
	 * @param  questionName questionName的值
	 */
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	/**
	 * 返回questionContent的值
	 * @return String questionContent的值
	 */
	public String getQuestionContent() {
		return questionContent;
	}

	/**
	 * 设置questionContent的值
	 * @param  questionContent questionContent的值
	 */
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	/**
	 * 返回status的值
	 * @return Integer status的值
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置status的值
	 * @param  status status的值
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 返回auditId的值
	 * @return String auditId的值
	 */
	public String getAuditId() {
		return auditId;
	}

	/**
	 * 设置auditId的值
	 * @param  auditId auditId的值
	 */
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	/**
	 * 返回auditTime的值
	 * @return Date auditTime的值
	 */
	public Date getAuditTime() {
		return auditTime;
	}

	/**
	 * 设置auditTime的值
	 * @param  auditTime auditTime的值
	 */
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	/**
	 * 返回refuseMemo的值
	 * @return String refuseMemo的值
	 */
	public String getRefuseMemo() {
		return refuseMemo;
	}

	/**
	 * 设置refuseMemo的值
	 * @param  refuseMemo refuseMemo的值
	 */
	public void setRefuseMemo(String refuseMemo) {
		this.refuseMemo = refuseMemo;
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

	/**
	 * 返回keyWord的值
	 * @return String keyWord的值
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * 设置keyWord的值
	 * @param  keyWord keyWord的值
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/**
	 * 返回topFlag的值
	 * @return Integer topFlag的值
	 */
	public Integer getTopFlag() {
		return topFlag;
	}

	/**
	 * 设置topFlag的值
	 * @param  topFlag topFlag的值
	 */
	public void setTopFlag(Integer topFlag) {
		this.topFlag = topFlag;
	}
}