/**
 * Project Name:zzj-db
 * File Name:MsgLogBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.MsgLogMapper;
import com.zzj.db.dto.MsgLog;

/**
 * <p><strong>类名: </strong></p>MsgLogBlo <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装在线课堂消息日志模块，数据库操作类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月18日下午3:49:14 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class MsgLogBlo {
	
	/**
	 * MsgLogBlo表操作Mapper
	 */
	@Autowired
	private MsgLogMapper mapper;
	
	/**
     * 插入消息记录主表
     * @param  record 记录
     * @return Long 返回主键
     */
    public Long insertSelective(MsgLog record){
    	mapper.insertSelective(record);
    	return record.getId();
    }

    /**
     * 根据条件查询记录
     * @param  record 封装条件
     * @return List<MsgLog> MsgLog集合
     */
	public List<MsgLog> selectSelective(MsgLog msgLog) {
		return mapper.selectSelective(msgLog);
	}

	/**
     * 根据条件查询记录
     * @param  groupId groupId
     * @return int 总数
     */
	public int selectCountByGroupId(String groupId) {
		return mapper.selectCountByGroupId(groupId);
	}

	/**
     * 根据条件查询记录
     * @param  Map<String, Object> queryMap 封装条件
     * @return List<MsgLog> MsgLog集合
     */
	public List<MsgLog> selectByGroupIdAndPage(Map<String, Object> queryMap) {
		return mapper.selectByGroupIdAndPage(queryMap);
	}
}

