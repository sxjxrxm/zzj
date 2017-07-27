/**
 * Project Name:zzj-db
 * File Name:NeedsAnswerKey.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;
/**
 * <p><strong>类名: </strong></p>NEEDS_ANSWER表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义NEEDS_ANSWER表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日上午9:36:35 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class NeedsAnswerKey implements Serializable {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6226847450191752049L;

	/**
    * 需求编码
    */
    private String needsCd;

    /**
    * 回答编号
    */
    private Integer answerNo;

    public String getNeedsCd() {
        return needsCd;
    }

    public void setNeedsCd(String needsCd) {
        this.needsCd = needsCd;
    }

	/**
	 * 返回answerNo的值
	 * @return Integer answerNo的值
	 */
	public Integer getAnswerNo() {
		return answerNo;
	}

	/**
	 * 设置answerNo的值
	 * @param  answerNo answerNo的值
	 */
	public void setAnswerNo(Integer answerNo) {
		this.answerNo = answerNo;
	}

}