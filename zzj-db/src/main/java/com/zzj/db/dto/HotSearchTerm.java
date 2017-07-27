/**
 * Project Name:zzj-db
 * File Name:HotSearchTerm.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>HotSearchTerm表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义HotSearchTerm表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月28日下午14:19:30 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class HotSearchTerm implements Serializable {
	
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4976963593494755195L;

	/**
    * 主题编号
    */
    private String termName01;

    /**
    * 主题编号
    */
    private String termName02;

    /**
    * 主题编号
    */
    private String termName03;

    /**
    * 主题编号
    */
    private String termName04;

    /**
    * 主题编号
    */
    private String termName05;

    /**
    * 主题编号
    */
    private String termName06;

    /**
    * 主题编号
    */
    private String termName07;

    /**
    * 主题编号
    */
    private String termName08;

    /**
    * 主题编号
    */
    private String termName09;

    /**
    * 主题编号
    */
    private String termName10;

    /**
    * 逻辑删除flag
    */
    private Boolean deleteFlag;

    /**
    * 更新人ID
    */
    private String updateId;

    /**
    * 更新时间
    */
    private Date updateTime;

	/**
	 * 返回termName01的值
	 * @return String termName01的值
	 */
	public String getTermName01() {
		return termName01;
	}

	/**
	 * 设置termName01的值
	 * @param  termName01 termName01的值
	 */
	public void setTermName01(String termName01) {
		this.termName01 = termName01;
	}

	/**
	 * 返回termName02的值
	 * @return String termName02的值
	 */
	public String getTermName02() {
		return termName02;
	}

	/**
	 * 设置termName02的值
	 * @param  termName02 termName02的值
	 */
	public void setTermName02(String termName02) {
		this.termName02 = termName02;
	}

	/**
	 * 返回termName03的值
	 * @return String termName03的值
	 */
	public String getTermName03() {
		return termName03;
	}

	/**
	 * 设置termName03的值
	 * @param  termName03 termName03的值
	 */
	public void setTermName03(String termName03) {
		this.termName03 = termName03;
	}

	/**
	 * 返回termName04的值
	 * @return String termName04的值
	 */
	public String getTermName04() {
		return termName04;
	}

	/**
	 * 设置termName04的值
	 * @param  termName04 termName04的值
	 */
	public void setTermName04(String termName04) {
		this.termName04 = termName04;
	}

	/**
	 * 返回termName05的值
	 * @return String termName05的值
	 */
	public String getTermName05() {
		return termName05;
	}

	/**
	 * 设置termName05的值
	 * @param  termName05 termName05的值
	 */
	public void setTermName05(String termName05) {
		this.termName05 = termName05;
	}

	/**
	 * 返回termName06的值
	 * @return String termName06的值
	 */
	public String getTermName06() {
		return termName06;
	}

	/**
	 * 设置termName06的值
	 * @param  termName06 termName06的值
	 */
	public void setTermName06(String termName06) {
		this.termName06 = termName06;
	}

	/**
	 * 返回termName07的值
	 * @return String termName07的值
	 */
	public String getTermName07() {
		return termName07;
	}

	/**
	 * 设置termName07的值
	 * @param  termName07 termName07的值
	 */
	public void setTermName07(String termName07) {
		this.termName07 = termName07;
	}

	/**
	 * 返回termName08的值
	 * @return String termName08的值
	 */
	public String getTermName08() {
		return termName08;
	}

	/**
	 * 设置termName08的值
	 * @param  termName08 termName08的值
	 */
	public void setTermName08(String termName08) {
		this.termName08 = termName08;
	}

	/**
	 * 返回termName09的值
	 * @return String termName09的值
	 */
	public String getTermName09() {
		return termName09;
	}

	/**
	 * 设置termName09的值
	 * @param  termName09 termName09的值
	 */
	public void setTermName09(String termName09) {
		this.termName09 = termName09;
	}

	/**
	 * 返回termName10的值
	 * @return String termName10的值
	 */
	public String getTermName10() {
		return termName10;
	}

	/**
	 * 设置termName10的值
	 * @param  termName10 termName10的值
	 */
	public void setTermName10(String termName10) {
		this.termName10 = termName10;
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
}