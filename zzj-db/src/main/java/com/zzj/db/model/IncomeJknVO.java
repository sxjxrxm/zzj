/**
 * Project Name:zzj-db
 * File Name:IncomeJknVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>IncomeJknVO <br/>
 * <p><strong>功能说明: </strong></p>收入画面查询条件用. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月14日下午3:56:23 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class IncomeJknVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -623446290217706639L;

	/**
	 * 机能分类
	 */
	private String busiType;
	
    /**
    * 来源分类  1：为平台；2：为专家
    */
	private String sourceType;
    
	/**
	 * 专家名称
	 */
	private String expertName;
	   
	/**
	 * 开始日期
	 */
	private Date startDate;
	
	/**
	 * 结束日期
	 */
	private Date endDate;
	
	/**
	 * 主题编码
	 */
	private String topicCd;
	
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
	 * 返回busiType的值
	 * @return String busiType的值
	 */
	public String getBusiType() {
		return busiType;
	}

	/**
	 * 设置busiType的值
	 * @param  busiType busiType的值
	 */
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	/**
	 * 返回startDate的值
	 * @return Date startDate的值
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置startDate的值
	 * @param  startDate startDate的值
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 返回endDate的值
	 * @return Date endDate的值
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置endDate的值
	 * @param  endDate endDate的值
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	/**
	 * 返回topicCd的值
	 * @return String topicCd的值
	 */
	public String getTopicCd() {
		return topicCd;
	}

	/**
	 * 设置topicCd的值
	 * @param  topicCd topicCd的值
	 */
	public void setTopicCd(String topicCd) {
		this.topicCd = topicCd;
	}

	/**
	 * 返回expertName的值
	 * @return String expertName的值
	 */
	public String getExpertName() {
		return expertName;
	}

	/**
	 * 设置expertName的值
	 * @param  expertName expertName的值
	 */
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	/**
	 * 返回sourceType的值
	 * @return String sourceType的值
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * 设置sourceType的值
	 * @param  sourceType sourceType的值
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}


}