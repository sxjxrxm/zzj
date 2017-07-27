/**
 * Project Name:zzj-db
 * File Name:PermissionVO.java
 * Package Name:com.zzj.db.model
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.model;

import java.util.List;

import com.zzj.db.dto.MstPermissionInfo;
import com.zzj.db.dto.MstScreenInfo;

/**
 * <p><strong>类名: </strong></p>PermissionVO <br/>
 * <p><strong>功能说明: </strong></p>角色页面权限管理用permissionVO. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月27日下午5:22:55 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class PermissionVO extends MstPermissionInfo{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5943108589691032483L;
	/**
	 * 表示角色的拥有权限的页面集合
	 */
	private List<MstPermissionInfo> trueScreens;
	/**
	 * 表示当前全部后台页面
	 */
	 private List<MstScreenInfo> allScreens;
	 /**
	  * 表示格式化的全部后台页面代码
	  */
	 private List<List<String>>  fmtAllScreens;
	/**
	 * 返回trueScreens的值
	 * @return List<MstPermissionInfo> trueScreens的值
	 */
	public List<MstPermissionInfo> getTrueScreens() {
		return trueScreens;
	}
	/**
	 * 设置trueScreens的值
	 * @param  trueScreens trueScreens的值
	 */
	public void setTrueScreens(List<MstPermissionInfo> trueScreens) {
		this.trueScreens = trueScreens;
	}
	/**
	 * 返回allScreens的值
	 * @return List<MstScreenInfo> allScreens的值
	 */
	public List<MstScreenInfo> getAllScreens() {
		return allScreens;
	}
	/**
	 * 设置allScreens的值
	 * @param  allScreens allScreens的值
	 */
	public void setAllScreens(List<MstScreenInfo> allScreens) {
		this.allScreens = allScreens;
	}
	/**
	 * 返回fmtAllScreens的值
	 * @return List<List<String>> fmtAllScreens的值
	 */
	public List<List<String>> getFmtAllScreens() {
		return fmtAllScreens;
	}
	/**
	 * 设置fmtAllScreens的值
	 * @param  fmtAllScreens fmtAllScreens的值
	 */
	public void setFmtAllScreens(List<List<String>> fmtAllScreens) {
		this.fmtAllScreens = fmtAllScreens;
	}
	/**
	 * 返回serialversionuid的值
	 * @return long serialversionuid的值
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	
}