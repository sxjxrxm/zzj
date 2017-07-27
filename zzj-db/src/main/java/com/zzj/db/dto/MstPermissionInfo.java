/**
 * Project Name:zzj-db
 * File Name:MstPermissionInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * <p><strong>类名: </strong></p>Mst_Permission_Info表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义Mst_Permission_Info表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日上午9:36:35 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class MstPermissionInfo implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -401359391060637112L;

	/**
    * 角色编码
    */
    private String roleId;

    /**
    * 页面编码
    */
    private String screenId;

    /**
    * 最后更新时间
    */
    private Date updateTime;

    /**
    * 角色名称
    */
    private String roleName;

	/**
	 * 返回roleId的值
	 * @return String roleId的值
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 设置roleId的值
	 * @param  roleId roleId的值
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 返回screenId的值
	 * @return String screenId的值
	 */
	public String getScreenId() {
		return screenId;
	}

	/**
	 * 设置screenId的值
	 * @param  screenId screenId的值
	 */
	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	/**
	 * 返回updateTime的值
	 * @return Date updateTime的值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置updateTime的值
	 * @param  updateTime updateTime的值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 返回roleName的值
	 * @return String roleName的值
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 设置roleName的值
	 * @param  roleName roleName的值
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

    
}