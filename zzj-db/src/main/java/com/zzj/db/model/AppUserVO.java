/**
 * Project Name:zzj-db
 * File Name:AppUserVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import com.zzj.db.dto.AppUsersInfo;

/**
 * <p><strong>类名: </strong></p>AppUserVO <br/>
 * <p><strong>功能说明: </strong></p>画面用UserVO<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月13日下午3:23:25 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class AppUserVO extends AppUsersInfo{

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = -8845801815276289235L;
	/**
	 * 页码
	 */
	private Integer pageNo;
	
	/**
	 * 起始位置
	 */
	private Integer dbIndex;
	
	/**
	 * 页码大小
	 */
	private Integer pageSize;

	/**
	 * 返回pageNo的值
	 * @return Integer pageNo的值
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * 设置pageNo的值
	 * @param  pageNo pageNo的值
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 返回dbIndex的值
	 * @return Integer dbIndex的值
	 */
	public Integer getDbIndex() {
		return dbIndex;
	}

	/**
	 * 设置dbIndex的值
	 * @param  dbIndex dbIndex的值
	 */
	public void setDbIndex(Integer dbIndex) {
		this.dbIndex = dbIndex;
	}

	/**
	 * 返回pageSize的值
	 * @return Integer pageSize的值
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * 设置pageSize的值
	 * @param  pageSize pageSize的值
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}

