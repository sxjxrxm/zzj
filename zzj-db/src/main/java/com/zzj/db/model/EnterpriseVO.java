/**
 * Project Name:zzj-db
 * File Name:EnterpriseVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.util.List;

import com.zzj.db.dto.CorpInfo;
import com.zzj.db.dto.MstAreaInfo;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>EnterpriseVO <br/>
 * <p><strong>功能说明: </strong></p>政企模块使用 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月6日下午3:14:25 <br/>
 * @author   康冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class EnterpriseVO extends CorpInfo{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1360067361296466693L;
	
	/**
	 * 审核状态
	 */
	private List<String> statusList;
	/**
	 * 领域
	 */
	private List<String> techFieldTypeList;
	/**
	 * 领域主键
	 */
    private List<TopicFieldInfoKey> fieldCd;	
    /**
	 * 所有省级信息
	 */
	private List<MstAreaInfo> areaInfosCityP;		
	
	/**
	 * 专家所在省对应的所有市级信息
	 */
	private List<MstAreaInfo> areaInfosCityC;
	
	/**
	 * 页码
	 */
	private Integer pageNo;
	
	/**
	 * 起始位置
	 */
	private Integer dbIndex;
	
	/**
	 * 起始位置
	 */
	private Integer pageSize;
	
	/**
	 * 拒绝消息类型，1：系统配置表code 2：自定义拒绝理由
	 */
	private Integer refuseMemoType;
	
	/**
	 * 返回statusList的值
	 * @return List<String> statusList的值
	 */
	public List<String> getStatusList() {
		return statusList;
	}	
	/**
	 * 设置statusList的值
	 * @param  statusList statusList的值
	 */
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	/**
	 * 返回techFieldTypeList的值
	 * @return List<String> techFieldTypeList的值
	 */
	public List<String> getTechFieldTypeList() {
		return techFieldTypeList;
	}

	/**
	 * 设置techFieldTypeList的值
	 * @param  techFieldTypeList techFieldTypeList的值
	 */
	public void setTechFieldTypeList(List<String> techFieldTypeList) {
		this.techFieldTypeList = techFieldTypeList;
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
	 * 返回areaInfosCityP的值
	 * @return List<MstAreaInfo> areaInfosCityP的值
	 */
	public List<MstAreaInfo> getAreaInfosCityP() {
		return areaInfosCityP;
	}

	/**
	 * 设置areaInfosCityP的值
	 * @param  areaInfosCityP areaInfosCityP的值
	 */
	public void setAreaInfosCityP(List<MstAreaInfo> areaInfosCityP) {
		this.areaInfosCityP = areaInfosCityP;
	}
	/**
	 * 返回areaInfosCityC的值
	 * @return List<MstAreaInfo> areaInfosCityC的值
	 */
	public List<MstAreaInfo> getAreaInfosCityC() {
		return areaInfosCityC;
	}
	/**
	 * 设置areaInfosCityC的值
	 * @param  areaInfosCityC areaInfosCityC的值
	 */
	public void setAreaInfosCityC(List<MstAreaInfo> areaInfosCityC) {
		this.areaInfosCityC = areaInfosCityC;
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
	 * 返回refuseMemoType的值
	 * @return Integer refuseMemoType的值
	 */
	public Integer getRefuseMemoType() {
		return refuseMemoType;
	}
	/**
	 * 设置refuseMemoType的值
	 * @param  refuseMemoType refuseMemoType的值
	 */
	public void setRefuseMemoType(Integer refuseMemoType) {
		this.refuseMemoType = refuseMemoType;
	}

}

