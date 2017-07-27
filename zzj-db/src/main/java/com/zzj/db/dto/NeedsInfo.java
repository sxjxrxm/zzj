/**
 * Project Name:zzj-db
 * File Name:NeedsInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * <p><strong>类名: </strong></p>NEEDS_INFO表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义NEEDS_INFO表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日上午9:36:35 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class NeedsInfo implements Serializable {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5460606463824603393L;

	/**
    * 需求编码
    */
    private String needsCd;

    /**
    * 需求主题
    */
    private String needsName;

    /**
    * 需求内容
    */
    private String needsContent;

    /**
    * 需求分类(1：政府需求，2：企业需求)
    */
    private Integer needsType;

    /**
    * 审核状态(0：待审核，1：已审核, 2:拒绝)
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
	 * 返回needsCd的值
	 * @return String needsCd的值
	 */
	public String getNeedsCd() {
		return needsCd;
	}

	/**
	 * 设置needsCd的值
	 * @param  needsCd needsCd的值
	 */
	public void setNeedsCd(String needsCd) {
		this.needsCd = needsCd;
	}

	/**
	 * 返回needsName的值
	 * @return String needsName的值
	 */
	public String getNeedsName() {
		return needsName;
	}

	/**
	 * 设置needsName的值
	 * @param  needsName needsName的值
	 */
	public void setNeedsName(String needsName) {
		this.needsName = needsName;
	}

	/**
	 * 返回needsContent的值
	 * @return String needsContent的值
	 */
	public String getNeedsContent() {
		return needsContent;
	}

	/**
	 * 设置needsContent的值
	 * @param  needsContent needsContent的值
	 */
	public void setNeedsContent(String needsContent) {
		this.needsContent = needsContent;
	}

	/**
	 * 返回needsType的值
	 * @return Boolean needsType的值
	 */
	public Integer getNeedsType() {
		return needsType;
	}

	/**
	 * 设置needsType的值
	 * @param  needsType needsType的值
	 */
	public void setNeedsType(Integer needsType) {
		this.needsType = needsType;
	}

	/**
	 * 返回status的值
	 * @return Boolean status的值
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
	 * 返回deleteFlag的值
	 * @return Boolean deleteFlag的值
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