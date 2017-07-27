/**
 * Project Name:zzj-db
 * File Name:CommercialVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import com.zzj.db.dto.CommercialInfo;

/**
 * <p><strong>类名: </strong></p>CommercialVO <br/>
 * <p><strong>功能说明: </strong></p>Commercial画面用. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月5日上午9:56:23 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class CommercialVO extends CommercialInfo{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 510785687451826831L;
	/**
	 * isAdd:表示添加或修改的状态。
	 */
	private int isAdd;
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

