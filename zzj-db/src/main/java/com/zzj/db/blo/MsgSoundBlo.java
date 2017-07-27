/**
 * Project Name:zzj-db
 * File Name:MsgSoundBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.MsgSoundMapper;
import com.zzj.db.dto.MsgSound;
import com.zzj.db.model.CourseMsgVO;

/**
 * <p><strong>类名: </strong></p>MsgSoundBlo <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装在线课堂语音模块，数据库操作类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月18日下午3:49:14 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class MsgSoundBlo {
	
	/**
	 * MsgSoundBlo表操作Mapper
	 */
	@Autowired
	private MsgSoundMapper mapper;
	
	
	/**
     * 插入语音
     * @param  record 记录
     * @return Integer 插入结果
     */
    public Integer insertSelective(MsgSound record){
    	return mapper.insertSelective(record);
    }
    
    /**
     * 修改语音
     * @param  record 记录
     * @return Integer 更新结果
     */
    public Integer updateByPrimaryKeySelective(MsgSound record){
    	return mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据mid查询记录
     * @param  mid
     * @return List<CourseMsgVO> 记录集合
     */
	public List<CourseMsgVO> selectByMid(Long mid) {
		return mapper.selectByMid(mid);
	}
	
	/**
	 * 通过uuid和聊天室id查找语音
	 * @param  Map<String, Object> maps 查询条件
	 * @return MsgSound 查询结果
	 */
	public MsgSound selectByUuidAndGroupId(Map<String, Object> maps){
		return mapper.selectByUuidAndGroupId(maps);
	}
}

