/**
 * Project Name:zzj-db
 * File Name:MsgCustomBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.MsgCustomMapper;
import com.zzj.db.dto.MsgCustom;

/**
 * <p><strong>类名: </strong></p>MsgCustomBlo <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装在线课堂自定义消息模块，数据库操作类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月19日下午3:49:14 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class MsgCustomBlo {
	
	/**
	 * MsgCustomBlo表操作Mapper
	 */
	@Autowired
	private MsgCustomMapper mapper;
	
	
	/**
     * 插入自定义消息
     * @param  record 记录
     * @return Integer 插入结果
     */
    public Integer insertSelective(MsgCustom record){
    	return mapper.insertSelective(record);
    }
}

