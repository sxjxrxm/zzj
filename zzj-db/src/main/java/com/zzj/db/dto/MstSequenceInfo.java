/**
 * Project Name:zzj-db
 * File Name:MstSequenceInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * <p><strong>类名: </strong></p>页面配置表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义页面配置表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月02日下午1:56:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MstSequenceInfo implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2282799633713083515L;
    /**
	 * 自动编码类别
	 */
    private String sequenceType;
    /**
	 * 自动编码类别名
	 */
    private String sequenceTypeName;
    /**
	 * 自动编码类别名
	 */
    private Integer minNo;
    /**
	 * 最小值
	 */
    private Integer maxNo;
    /**
	 * 当前值
	 */
    private Integer nowNo;
    /**
	 * 下一个值
	 */
    private Integer nextNo;
    /**
	 * 自动编号
	 */
    private String keySq;
    /**
	 * 前缀字符
	 */
    private String headerStr;
    /**
	 * 更新时间
	 */
    private Date updateTime;
	/**
	 * 返回sequenceType的值
	 * @return String sequenceType的值
	 */
	public String getSequenceType() {
		return sequenceType;
	}
	/**
	 * 设置sequenceType的值
	 * @param  sequenceType sequenceType的值
	 */
	public void setSequenceType(String sequenceType) {
		this.sequenceType = sequenceType;
	}
	/**
	 * 返回sequenceTypeName的值
	 * @return String sequenceTypeName的值
	 */
	public String getSequenceTypeName() {
		return sequenceTypeName;
	}
	/**
	 * 设置sequenceTypeName的值
	 * @param  sequenceTypeName sequenceTypeName的值
	 */
	public void setSequenceTypeName(String sequenceTypeName) {
		this.sequenceTypeName = sequenceTypeName;
	}
	/**
	 * 返回minNo的值
	 * @return Integer minNo的值
	 */
	public Integer getMinNo() {
		return minNo;
	}
	/**
	 * 设置minNo的值
	 * @param  minNo minNo的值
	 */
	public void setMinNo(Integer minNo) {
		this.minNo = minNo;
	}
	/**
	 * 返回maxNo的值
	 * @return Integer maxNo的值
	 */
	public Integer getMaxNo() {
		return maxNo;
	}
	/**
	 * 设置maxNo的值
	 * @param  maxNo maxNo的值
	 */
	public void setMaxNo(Integer maxNo) {
		this.maxNo = maxNo;
	}
	/**
	 * 返回nowNo的值
	 * @return Integer nowNo的值
	 */
	public Integer getNowNo() {
		return nowNo;
	}
	/**
	 * 设置nowNo的值
	 * @param  nowNo nowNo的值
	 */
	public void setNowNo(Integer nowNo) {
		this.nowNo = nowNo;
	}
	/**
	 * 返回headerStr的值
	 * @return String headerStr的值
	 */
	public String getHeaderStr() {
		return headerStr;
	}
	/**
	 * 设置headerStr的值
	 * @param  headerStr headerStr的值
	 */
	public void setHeaderStr(String headerStr) {
		this.headerStr = headerStr;
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
	 * 返回nextNo的值
	 * @return Integer nextNo的值
	 */
	public Integer getNextNo() {
		return nextNo;
	}
	/**
	 * 设置nextNo的值
	 * @param  nextNo nextNo的值
	 */
	public void setNextNo(Integer nextNo) {
		this.nextNo = nextNo;
	}
	/**
	 * 返回keySq的值
	 * @return String keySq的值
	 */
	public String getKeySq() {
		return keySq;
	}
	/**
	 * 设置keySq的值
	 * @param  keySq keySq的值
	 */
	public void setKeySq(String keySq) {
		this.keySq = keySq;
	}
}