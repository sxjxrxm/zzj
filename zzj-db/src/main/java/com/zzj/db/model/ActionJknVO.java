/**
 * Project Name:zzj-db
 * File Name:ActionJknVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>ActionJknVO <br/>
 * <p><strong>功能说明: </strong></p>用户行为画面查询条件用. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月9日下午3:56:23 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class ActionJknVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1798915219304776386L;

	/**
	 * 机能分类
	 */
	private String busiType;
	
	/**
	 * 资料主题名称
	 */
	private String topicName;
	
	/**
	 * 资料主题CD
	 */
	private String topicCd;
	
	/**
	 * 领域
	 */
    private String field;
    
    /**
	 * 领域主键
	 */
    private String[] fieldCd;
    
	/**
	 * 开始日期
	 */
	private Date startDate;
	
	/**
	 * 结束日期
	 */
	private Date endDate;
	
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
	 * 行为类别
	 */
	private String handleType;
	
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
	 * 返回topicName的值
	 * @return String topicName的值
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * 设置topicName的值
	 * @param  topicName topicName的值
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
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
	 * 返回fieldCd的值
	 * @return String[] fieldCd的值
	 */
	public String[] getFieldCd() {
		return fieldCd;
	}

	/**
	 * 设置fieldCd的值
	 * @param  fieldCd fieldCd的值
	 */
	public void setFieldCd(String[] fieldCd) {
		this.fieldCd = fieldCd;
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
	 * 返回field的值
	 * @return String field的值
	 */
	public String getField() {
		return field;
	}

	/**
	 * 设置field的值
	 * @param  field field的值
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * 返回handleType的值
	 * @return String handleType的值
	 */
	public String getHandleType() {
		return handleType;
	}

	/**
	 * 设置handleType的值
	 * @param  handleType handleType的值
	 */
	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}
}