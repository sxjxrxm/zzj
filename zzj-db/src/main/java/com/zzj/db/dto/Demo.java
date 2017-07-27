/**
 * Project Name:zzj-db
 * File Name:Demo.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p><strong>类名: </strong></p>Demo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义Demo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月13日下午9:24:30 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class Demo implements Serializable{
	
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5608779190595923846L;

	/**
    * 字段1
    */
    private Integer a;

    /**
    * 字段2
    */
    private Byte b;

    /**
    * 字段3
    */
    private BigDecimal c;

    /**
    * 字段4
    */
    private Short d;

    /**
    * 字段5
    */
    private Double e;

    /**
    * 字段6
    */
    private String f;

	/**
	 * 返回a的值
	 * @return Integer a的值
	 */
	public Integer getA() {
		return a;
	}

	/**
	 * 设置a的值
	 * @param  a a的值
	 */
	public void setA(Integer a) {
		this.a = a;
	}

	/**
	 * 返回b的值
	 * @return Byte b的值
	 */
	public Byte getB() {
		return b;
	}

	/**
	 * 设置b的值
	 * @param  b b的值
	 */
	public void setB(Byte b) {
		this.b = b;
	}

	/**
	 * 返回c的值
	 * @return BigDecimal c的值
	 */
	public BigDecimal getC() {
		return c;
	}

	/**
	 * 设置c的值
	 * @param  c c的值
	 */
	public void setC(BigDecimal c) {
		this.c = c;
	}

	/**
	 * 返回d的值
	 * @return Short d的值
	 */
	public Short getD() {
		return d;
	}

	/**
	 * 设置d的值
	 * @param  d d的值
	 */
	public void setD(Short d) {
		this.d = d;
	}

	/**
	 * 返回e的值
	 * @return Double e的值
	 */
	public Double getE() {
		return e;
	}

	/**
	 * 设置e的值
	 * @param  e e的值
	 */
	public void setE(Double e) {
		this.e = e;
	}

	/**
	 * 返回f的值
	 * @return String f的值
	 */
	public String getF() {
		return f;
	}

	/**
	 * 设置f的值
	 * @param  f f的值
	 */
	public void setF(String f) {
		this.f = f;
	}
}