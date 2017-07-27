/**
 * Project Name:zzj-db
 * File Name:QuestionAnswerKey.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dto;

import java.io.Serializable;

/**
 * <p><strong>类名: </strong></p>QuestionAnswer表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义QuestionAnswer表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月05日上午9:36:35 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class QuestionAnswerKey implements Serializable {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8697090170943008449L;

	/**
    * e问编码
    */
    private String questionCd;

    /**
    * 回答编号
    */
    private Integer answerNo;

	/**
	 * 返回questionCd的值
	 * @return String questionCd的值
	 */
	public String getQuestionCd() {
		return questionCd;
	}

	/**
	 * 设置questionCd的值
	 * @param  questionCd questionCd的值
	 */
	public void setQuestionCd(String questionCd) {
		this.questionCd = questionCd;
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