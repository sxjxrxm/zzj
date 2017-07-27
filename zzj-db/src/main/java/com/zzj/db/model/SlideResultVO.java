/**
 * Project Name:zzj-db
 * File Name:SlideResultVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;


import java.util.List;

import com.zzj.db.dto.SlideShowInfo;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>SlideResultVO <br/>
 * <p><strong>功能说明: </strong></p>轮播编辑模块查询结果页面用VO <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日上午10:30:34 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class SlideResultVO extends SlideShowInfo{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1507286569850794985L;
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
	 * 查询结果主键
	 */
	private String  SlideResultId;
	/**
	 * 查询结果机能
	 */
	private String SlideResultBusi;
	/**
	 * 查询结果主题
	 */
	private String SlideResultTopic;
	/**
	 * 查询结果领域
	 */
	private List<TopicFieldInfoKey> SlideResultfields;
	/**
	 * 查询条件技术领域
	 */
	private String techField; 
	/**
	 * 查询结果费用:0免费1付费
	 */
	private int SlideResultPayment;
	/**
	 * 查询结果图片地址
	 */
	private String SlideResultBigIcon;	
	/**
	 * 返回slideResultBusi的值
	 * @return String slideResultBusi的值
	 */
	public String getSlideResultBusi() {
		return SlideResultBusi;
	}
	/**
	 * 设置slideResultBusi的值
	 * @param  slideResultBusi slideResultBusi的值
	 */
	public void setSlideResultBusi(String slideResultBusi) {
		SlideResultBusi = slideResultBusi;
	}
	/**
	 * 返回slideResultTopic的值
	 * @return String slideResultTopic的值
	 */
	public String getSlideResultTopic() {
		return SlideResultTopic;
	}
	/**
	 * 设置slideResultTopic的值
	 * @param  slideResultTopic slideResultTopic的值
	 */
	public void setSlideResultTopic(String slideResultTopic) {
		SlideResultTopic = slideResultTopic;
	}
	/**
	 * 返回slideResultfields的值
	 * @return List<TopicFieldInfoKey> slideResultfields的值
	 */
	public List<TopicFieldInfoKey> getSlideResultfields() {
		return SlideResultfields;
	}
	/**
	 * 设置slideResultfields的值
	 * @param  slideResultfields slideResultfields的值
	 */
	public void setSlideResultfields(List<TopicFieldInfoKey> slideResultfields) {
		SlideResultfields = slideResultfields;
	}
	/**
	 * 返回slideResultPayment的值
	 * @return int slideResultPayment的值
	 */
	public int getSlideResultPayment() {
		return SlideResultPayment;
	}
	/**
	 * 设置slideResultPayment的值
	 * @param  slideResultPayment slideResultPayment的值
	 */
	public void setSlideResultPayment(int slideResultPayment) {
		SlideResultPayment = slideResultPayment;
	}
	
	/**
	 * 返回techField的值
	 * @return String techField的值
	 */
	public String getTechField() {
		return techField;
	}
	/**
	 * 设置techField的值
	 * @param  techField techField的值
	 */
	public void setTechField(String techField) {
		this.techField = techField;
	}
	/**
	 * 返回serialversionuid的值
	 * @return long serialversionuid的值
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 返回slideResultId的值
	 * @return String slideResultId的值
	 */
	public String getSlideResultId() {
		return SlideResultId;
	}
	/**
	 * 设置slideResultId的值
	 * @param  slideResultId slideResultId的值
	 */
	public void setSlideResultId(String slideResultId) {
		SlideResultId = slideResultId;
	}
	/**
	 * 返回slideResultBigIcon的值
	 * @return String slideResultBigIcon的值
	 */
	public String getSlideResultBigIcon() {
		return SlideResultBigIcon;
	}
	/**
	 * 设置slideResultBigIcon的值
	 * @param  slideResultBigIcon slideResultBigIcon的值
	 */
	public void setSlideResultBigIcon(String slideResultBigIcon) {
		SlideResultBigIcon = slideResultBigIcon;
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
	
	

}

