/**
 * Project Name:zzj-db
 * File Name:ActionResultVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.util.Date;
import java.util.List;

import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.dto.UserHandleInfo;

/**
 * <p><strong>类名: </strong></p>ActionResultVO <br/>
 * <p><strong>功能说明: </strong></p>用户行为画面一览用. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月9日下午3:56:23 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class ActionResultVO extends UserHandleInfo{

	/**
	 * serialVersionUID:序列化。
	 */
	private static final long serialVersionUID = -2502453230426036660L;
	
	/**
	 * 机能分类名称
	 */
	private String busiTypeName;
	
	/**
	 * 领域主键
	 */
    private List<TopicFieldInfoKey> fieldCd;
	
	/**
	 * 操作时间
	 */
	private Date handleTime;
	
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
	 * 返回fieldCd的值
	 * @return List<TopicFieldInfoKey> fieldCd的值
	 */
	public List<TopicFieldInfoKey> getFieldCd() {
		return fieldCd;
	}

	/**
	 * 设置fieldCd的值
	 * @param  fieldCd fieldCd的值
	 */
	public void setFieldCd(List<TopicFieldInfoKey> fieldCd) {
		this.fieldCd = fieldCd;
	}

	/**
	 * 返回handleTime的值
	 * @return Date handleTime的值
	 */
	public Date getHandleTime() {
		return handleTime;
	}

	/**
	 * 设置handleTime的值
	 * @param  handleTime handleTime的值
	 */
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	/**
	 * 返回userName的值
	 * @return String userName的值
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置nickName的值
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