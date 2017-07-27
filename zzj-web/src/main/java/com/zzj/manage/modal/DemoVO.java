/**
 * Project Name:zzj-web
 * File Name:DemoVO.java
 * Package Name:com.zzj.manage.modal
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.modal;

import com.zzj.db.dto.Demo;

/**
 * <p><strong>类名: </strong></p>DemoVO <br/>
 * <p><strong>功能说明: </strong></p>Demo画面用VO <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月13日下午8:44:58 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class DemoVO extends Demo {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1178737576165407585L;
	
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

