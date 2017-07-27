/**
 * Project Name:zzj-db
 * File Name:MstScreenInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.util.Date;

/**
 * <p><strong>类名: </strong></p>页面配置表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义页面配置表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:56:30 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */

public class MstScreenInfo extends MstScreenInfoKey {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6167781414601358757L;

	/**
    * 页面名称
    */
    private String screenName;

    /**
    * 指向代码
    */
    private String linkUrl;

    /**
    * 是否显示 0：显示，1：不显示
    */
    private Boolean menuShowFlag;

    /**
    * 显示顺序
    */
    private Byte displayOrder;

    /**
    * 逻辑删除flag 0：可用，1：不可用
    */
    private Boolean deleteFlag;

    /**
    * 更新时间
    */
    private Date updateTime;

	/**
	 * 返回screenName的值
	 * @return String screenName的值
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * 设置screenName的值
	 * @param  screenName screenName的值
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * 返回linkUrl的值
	 * @return String linkUrl的值
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * 设置linkUrl的值
	 * @param  linkUrl linkUrl的值
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/**
	 * 返回menuShowFlag的值
	 * @return Boolean menuShowFlag的值
	 */
	public Boolean getMenuShowFlag() {
		return menuShowFlag;
	}

	/**
	 * 设置menuShowFlag的值
	 * @param  menuShowFlag menuShowFlag的值
	 */
	public void setMenuShowFlag(Boolean menuShowFlag) {
		this.menuShowFlag = menuShowFlag;
	}

	/**
	 * 返回displayOrder的值
	 * @return Byte displayOrder的值
	 */
	public Byte getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * 设置displayOrder的值
	 * @param  displayOrder displayOrder的值
	 */
	public void setDisplayOrder(Byte displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 * 返回deleteFlag的值
	 * @return Boolean deleteFlag的值
	 */
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * 设置deleteFlag的值
	 * @param  deleteFlag deleteFlag的值
	 */
	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
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
}