/**
 * Project Name:zzj-web
 * File Name:GmtDateTime.java
 * Package Name:com.zzj.core.web.converter
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/
package com.zzj.core.web.converter;

import java.text.FieldPosition;
import java.util.Date;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

/**
 * <p><strong>类名: </strong></p>GmtDateTime <br/>
 * <p><strong>功能说明: </strong></p>将时间转换成格林尼治时间格式。<br/>
 * <p><strong>创建日期: </strong><br/></p>2015年10月16日下午6:00:46 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class GmtDateTime extends ISO8601DateFormat {
	/**
	 * 序列化ID。
	 */
	private static final long serialVersionUID = -6412221393617191441L;

	@Override
	public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition)
	{
	    String value = ISO8601Utils.format(date, true);
	    toAppendTo.append(value);
	    return toAppendTo;
	}
}

