/**
 * Project Name:zzj-db
 * File Name:IncomeResultVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import com.zzj.db.dto.UserOrderInfo;

/**
 * <p><strong>类名: </strong></p>IncomeResultVO <br/>
 * <p><strong>功能说明: </strong></p>收入画面一览用. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月14日下午3:56:23 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class IncomeResultVO extends UserOrderInfo{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7049147736709579832L;

	/**
	 * 机能分类名称
	 */
	private String busiTypeName;
	
	/**
	 * 内容分类名称
	 */
	private String contentName;
	
    /**
    * 资料来源
    */
    private String sourceOwner;
	
	/**
	 * 用户昵称
	 */
	private String userName;
	
	/**
	 * 页码
	 */
	private Integer pageNo;
	
	/**
	 * 起始位置
	 */
	private Integer dbIndex;
	
	/**
	 * 页数
	 */
	private Integer pageSize;

	/**
	 * isAdd:表示添加或修改的状态。
	 */
	private int isAdd;

	/**
	 * 返回busiTypeName的值
	 * @return String busiTypeName的值
	 */
	public String getBusiTypeName() {
		return busiTypeName;
	}

	/**
	 * 设置busiTypeName的值
	 * @param  busiTypeName busiTypeName的值
	 */
	public void setBusiTypeName(String busiTypeName) {
		this.busiTypeName = busiTypeName;
	}

	/**
	 * 返回contentName的值
	 * @return String contentName的值
	 */
	public String getContentName() {
		return contentName;
	}

	/**
	 * 设置contentName的值
	 * @param  contentName contentName的值
	 */
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	/**
	 * 返回sourceOwner的值
	 * @return String sourceOwner的值
	 */
	public String getSourceOwner() {
		return sourceOwner;
	}

	/**
	 * 设置sourceOwner的值
	 * @param  sourceOwner sourceOwner的值
	 */
	public void setSourceOwner(String sourceOwner) {
		this.sourceOwner = sourceOwner;
	}

	/**
	 * 返回userName的值
	 * @return String userName的值
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置userName的值
	 * @param  userName userName的值
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	/**
	 * 返回isAdd的值
	 * @return int isAdd的值
	 */
	public int getIsAdd() {
		return isAdd;
	}

	/**
	 * 设置isAdd的值
	 * @param  isAdd isAdd的值
	 */
	public void setIsAdd(int isAdd) {
		this.isAdd = isAdd;
	}
}