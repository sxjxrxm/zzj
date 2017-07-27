/**
 * Project Name:zzj-web
 * File Name:DateMapper.java
 * Package Name:com.zzj.core.web.converter
 * Copyright © 北京云物移大智信息技术有限公司 All Rights Reserved.
*/

package com.zzj.core.web.converter;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p><strong>类名: </strong></p>ObjectMapper <br/>
 * <p><strong>功能说明: </strong></p>日期类型数据自动转换. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年3月30日下午8:07:10 <br/>
 * @author   康 冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Configuration
public class DateMapper {
	private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

        objectMapper.setDateFormat(dateTimeFormat);
        return objectMapper;
    }
}

