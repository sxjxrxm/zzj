/**
 * Project Name:zzj-db
 * File Name:FieldResultVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.io.Serializable;
import java.util.List;

import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstFieldInfo;

/**
 * <p><strong>类名: </strong></p>FieldResultVO <br/>
 * <p><strong>功能说明: </strong></p>领域配置画面用. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月17日下午3:56:23 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class FieldResultVO implements Serializable{

	/**
	 * serialVersionUID:序列化。
	 */
	private static final long serialVersionUID = 845872971373081826L;
	
	/**
	 * 其它研究领域列表信息
	 */
    private List<MstCodeInfo> otherRschFieldInfo;
    
	/**
	 * 对应研究领域列表信息
	 */
    private List<MstFieldInfo> rschFieldInfo;

	/**
	 * 返回otherRschFieldInfo的值
	 * @return List<MstCodeInfo> otherRschFieldInfo的值
	 */
	public List<MstCodeInfo> getOtherRschFieldInfo() {
		return otherRschFieldInfo;
	}

	/**
	 * 设置otherRschFieldInfo的值
	 * @param  otherRschFieldInfo otherRschFieldInfo的值
	 */
	public void setOtherRschFieldInfo(List<MstCodeInfo> otherRschFieldInfo) {
		this.otherRschFieldInfo = otherRschFieldInfo;
	}

	/**
	 * 返回rschFieldInfo的值
	 * @return List<MstFieldInfo> rschFieldInfo的值
	 */
	public List<MstFieldInfo> getRschFieldInfo() {
		return rschFieldInfo;
	}

	/**
	 * 设置rschFieldInfo的值
	 * @param  rschFieldInfo rschFieldInfo的值
	 */
	public void setRschFieldInfo(List<MstFieldInfo> rschFieldInfo) {
		this.rschFieldInfo = rschFieldInfo;
	}
}