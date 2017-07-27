/**
 * Project Name:zzj-db
 * File Name:VersionInfoKey.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>VersionInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义VersionInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2017年1月3日下午2:36:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class VersionInfoKey implements Serializable{
    /**
	 * serialVersionUID。
	 */
	private static final long serialVersionUID = 7356456945434846491L;

	/**
    * 类型
    */
    private Integer appType;

    /**
    * 创建时间
    */
    private Date createTime;

	/**
	 * 返回appType的值
	 * @return Integer appType的值
	 */
	public Integer getAppType() {
		return appType;
	}

	/**
	 * 设置appType的值
	 * @param  appType appType的值
	 */
	public void setAppType(Integer appType) {
		this.appType = appType;
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