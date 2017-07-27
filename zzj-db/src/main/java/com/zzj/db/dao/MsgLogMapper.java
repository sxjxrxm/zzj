/**
 * Project Name:zzj-db
 * File Name:MsgFaceMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;
import java.util.Map;

import com.zzj.db.dto.MsgLog;

/**
 * <p><strong>类名: </strong></p>MsgLog表操作接口<br/>
 * <p><strong>功能说明: </strong></p>MsgLog表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日下午11:35:30 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface MsgLogMapper {
	/**
	 * 根据主键删除Msg_Log中指定记录<br/>
	 * @param  id 查询条件
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(Long id);
    /**
	 * 插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insert(MsgLog record);
    /**
     * 插入消息记录主表
     * @param  record 记录
     * @return Integer 插入结果
     */
    Long insertSelective(MsgLog record);
    /**
	 * 根据主键查询Msg_Log中指定记录<br/>
	 * @param  id 查询结果
	 * @return MsgLog 取得结果
	 */
    MsgLog selectByPrimaryKey(Long id);
    
    /**
     * 根据条件查询记录
     * @param  record 封装条件
     * @return List<MsgLog> MsgLog集合
     */
    List<MsgLog> selectSelective(MsgLog record);
    /**
   	 * 根据条件更新Msg_Log中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKeySelective(MsgLog record);
    /**
   	 * 根据主键更新Msg_Log中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKey(MsgLog record);

    /**
     * 根据条件查询记录
     * @param  groupId groupId
     * @return int 总数
     */
	int selectCountByGroupId(String groupId);

	/**
     * 根据条件查询记录
     * @param  record 封装条件
     * @return List<MsgLog> MsgLog集合
     */
	List<MsgLog> selectByGroupIdAndPage(Map<String, Object> queryMap);
}