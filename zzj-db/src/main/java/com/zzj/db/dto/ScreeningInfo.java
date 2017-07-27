/**
 * Project Name:zzj-db
 * File Name:ScreeningInfo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/

package com.zzj.db.dto;

import java.util.Date;
import java.io.Serializable;

/**
 * <p><strong>类名: </strong></p>ScreeningInfo表对象定义类br/>
 * <p><strong>功能说明: </strong></p>定义ScreeningInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:56:30 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class ScreeningInfo implements Serializable{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1710995580775464148L;

	/**
    * 业务分类
    */
    private String busiType;

    /**
    * 筛选条件1
    */
    private String screening01;

    /**
    * 筛选条件2
    */
    private String screening02;

    /**
    * 筛选条件3
    */
    private String screening03;

    /**
    * 筛选条件4
    */
    private String screening04;

    /**
    * 逻辑删除flag
    */
    private Integer deleteFlag;

    /**
    * 最后更新人ID
    */
    private String updateId;

    /**
    * 最后更新时间
    */
    private Date updateTime;
    
	/**
     * 业务分类名称
     */
     private String busiTypeName;

     /**
     * 筛选条件1名称
     */
     private String name1;

     /**
     * 筛选条件2名称
     */
     private String name2;

     /**
     * 筛选条件3名称
     */
     private String name3;

     /**
     * 筛选条件4名称
     */
     private String name4;

	/**
	 * 返回busiType的值
	 * @return String busiType的值
	 */
	public String getBusiType() {
		return busiType;
	}

	/**
	 * 设置busiType的值
	 * @param  busiType busiType的值
	 */
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	/**
	 * 返回screening01的值
	 * @return String screening01的值
	 */
	public String getScreening01() {
		return screening01;
	}

	/**
	 * 设置screening01的值
	 * @param  screening01 screening01的值
	 */
	public void setScreening01(String screening01) {
		this.screening01 = screening01;
	}

	/**
	 * 返回screening02的值
	 * @return String screening02的值
	 */
	public String getScreening02() {
		return screening02;
	}

	/**
	 * 设置screening02的值
	 * @param  screening02 screening02的值
	 */
	public void setScreening02(String screening02) {
		this.screening02 = screening02;
	}

	/**
	 * 返回screening03的值
	 * @return String screening03的值
	 */
	public String getScreening03() {
		return screening03;
	}

	/**
	 * 设置screening03的值
	 * @param  screening03 screening03的值
	 */
	public void setScreening03(String screening03) {
		this.screening03 = screening03;
	}

	/**
	 * 返回screening04的值
	 * @return String screening04的值
	 */
	public String getScreening04() {
		return screening04;
	}

	/**
	 * 设置screening04的值
	 * @param  screening04 screening04的值
	 */
	public void setScreening04(String screening04) {
		this.screening04 = screening04;
	}

	/**
	 * 返回deleteFlag的值
	 * @return Boolean deleteFlag的值
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * 设置deleteFlag的值
	 * @param  deleteFlag deleteFlag的值
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * 返回updateId的值
	 * @return String updateId的值
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * 设置updateId的值
	 * @param  updateId updateId的值
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
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
	 * 返回busiTypeName的值
	 * @return String busiTypeName的值
	 */
	public String getBusiTypeName() {
		return busiTypeName;
	}

	/**
	 * 设置busiTypeName的值
	 * @param  busiTypeName busiTypeName的值
	 */
	public void setBusiTypeName(String busiTypeName) {
		this.busiTypeName = busiTypeName;
	}

	/**
	 * 返回name1的值
	 * @return String name1的值
	 */
	public String getName1() {
		return name1;
	}

	/**
	 * 设置name1的值
	 * @param  name1 name1的值
	 */
	public void setName1(String name1) {
		this.name1 = name1;
	}

	/**
	 * 返回name2的值
	 * @return String name2的值
	 */
	public String getName2() {
		return name2;
	}

	/**
	 * 设置name2的值
	 * @param  name2 name2的值
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}

	/**
	 * 返回name3的值
	 * @return String name3的值
	 */
	public String getName3() {
		return name3;
	}

	/**
	 * 设置name3的值
	 * @param  name3 name3的值
	 */
	public void setName3(String name3) {
		this.name3 = name3;
	}

	/**
	 * 返回name4的值
	 * @return String name4的值
	 */
	public String getName4() {
		return name4;
	}

	/**
	 * 设置name4的值
	 * @param  name4 name4的值
	 */
	public void setName4(String name4) {
		this.name4 = name4;
	}

	/**
	 * 返回serialversionuid的值
	 * @return long serialversionuid的值
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}