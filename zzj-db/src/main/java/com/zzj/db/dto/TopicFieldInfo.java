/**
 * Project Name:zzj-db
 * File Name:TopicFieldInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.util.Date;

/**
 * <p><strong>类名: </strong></p>TopicFieldInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义TopicFieldInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月24日下午3:56:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class TopicFieldInfo extends TopicFieldInfoKey {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9056699576507378411L;

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

    /**
    * 技术领域名称
    */
    private String techFieldName;

    /**
    * 研究领域名称
    */
    private String rschFieldName;

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
	 * 返回techFieldName的值
	 * @return String techFieldName的值
	 */
	public String getTechFieldName() {
		return techFieldName;
	}

	/**
	 * 设置techFieldName的值
	 * @param  techFieldName techFieldName的值
	 */
	public void setTechFieldName(String techFieldName) {
		this.techFieldName = techFieldName;
	}

	/**
	 * 返回rschFieldName的值
	 * @return String rschFieldName的值
	 */
	public String getRschFieldName() {
		return rschFieldName;
	}

	/**
	 * 设置rschFieldName的值
	 * @param  rschFieldName rschFieldName的值
	 */
	public void setRschFieldName(String rschFieldName) {
		this.rschFieldName = rschFieldName;
	}

}