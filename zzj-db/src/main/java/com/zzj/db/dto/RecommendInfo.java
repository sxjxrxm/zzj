/**
 * Project Name:zzj-db
 * File Name:RecommendInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.util.Date;

/**
 * <p><strong>类名: </strong></p>RecommendInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义RecommendInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:56:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class RecommendInfo extends RecommendInfoKey {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6034162969344978498L;

	/**
    * 显示序号
    */
    private Byte sortNo;

    /**
    * 推荐语
    */
    private String recommendMemo;

    /**
    * 逻辑删除flag 0：可用，1：不可用
    */
    private Integer deleteFlag;

    /**
    * 最后更新人ID
    */
    private String updateId;

    /**
    * 最后更新时间
    */
    private Date updateTime;

    public Byte getSortNo() {
        return sortNo;
    }

    public void setSortNo(Byte sortNo) {
        this.sortNo = sortNo;
    }

    public String getRecommendMemo() {
        return recommendMemo;
    }

    public void setRecommendMemo(String recommendMemo) {
        this.recommendMemo = recommendMemo;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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