/**
 * Project Name:zzj-db
 * File Name:SlideVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.util.List;

import com.zzj.db.dto.SlideShowInfo;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>SlideVO <br/>
 * <p><strong>功能说明: </strong></p>Slide_show_Info页面用VO <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月28日下午6:25:51 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class SlideVO extends SlideShowInfo{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3574517546702934426L;
	/**
	 * 领域编码
	 */
	private List<TopicFieldInfoKey> fieldCd;
	/**
	 * 设置者姓名
	 */
	private String updateName;
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
	 * 返回updateName的值
	 * @return String updateName的值
	 */
	public String getUpdateName() {
		return updateName;
	}
	/**
	 * 设置updateName的值
	 * @param  updateName updateName的值
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	/**
	 * 返回serialversionuid的值
	 * @return long serialversionuid的值
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

