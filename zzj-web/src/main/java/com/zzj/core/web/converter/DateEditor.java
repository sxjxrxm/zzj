/**
 * Project Name:zzj-web
 * File Name:DateEditor.java
 * Package Name:com.zzj.core.web.converter
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.core.web.converter;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>DateEditor <br/>
 * <p><strong>功能说明: </strong></p>日期数据自动转换类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年3月30日下午7:06:45 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
public class DateEditor extends PropertyEditorSupport {
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	public SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	public void setAsText(String text)
    {
        setValue(parseString(text));
    }
    private Object parseString(String text)
    {
		Date date = null;
		if (null != text && !"".equals(text)) {
			try {
				dateTimeFormat.setLenient(false);
				date = dateTimeFormat.parse(text);
			} catch (ParseException e) {
				//e.printStackTrace();
			}
			if (date == null) {
				try {
					dateFormat.setLenient(false);
					date = dateFormat.parse(text);
				} catch (ParseException e) {
					//e.printStackTrace();
				}
			}
		}
		return date;
    }
}

