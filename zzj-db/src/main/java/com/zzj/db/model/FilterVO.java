/**
 * Project Name:zzj-web
 * File Name:FilterVO.java
 * Package Name:com.zzj.manage.modal
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import com.zzj.db.dto.ScreeningInfo;

/**
 * <p><strong>类名: </strong></p>FilterVO <br/>
 * <p><strong>功能说明: </strong></p>筛选条件画面用VO <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月22日下午8:44:58 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class FilterVO extends ScreeningInfo {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8538823735144184804L;
	
	/**
	 * 添加标识
	 * 0:编辑 1:添加
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