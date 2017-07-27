/**
 * Project Name:zzj-db
 * File Name:UserOrderInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>UserOrderInfo表对象定义类br/>
 * <p><strong>功能说明: </strong></p>定义UserOrderInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:56:30 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class UserOrderInfo implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3608849018119476140L;

	/**
	 * 序号
	 */
	private String orderNo;

	/**
	 * 状态
	 */
    private String status;

    /**
    * 内容分类
    */
    private String busiType;

    /**
    * 主题编号
    */
    private String topicCd;

    /**
    * 主题名称
    */
    private String topicName;

    /**
	 * 价格
	 */
    private BigDecimal priceSell;

    /**
	 * 删除标记
	 */
    private Boolean deleteFlag;

    /**
    * 用户ID
    */
    private String createId;

    /**
    * 行为时间
    */
    private Date createTime;

    /**
	 * 更新者
	 */
    private String updateId;

    /**
	 * 更新时间
	 */
    private Date updateTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getTopicCd() {
        return topicCd;
    }

    public void setTopicCd(String topicCd) {
        this.topicCd = topicCd;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public BigDecimal getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(BigDecimal priceSell) {
        this.priceSell = priceSell;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}