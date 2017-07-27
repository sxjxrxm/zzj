/**
 * Project Name:zzj-db
 * File Name:MsgCustomMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import com.zzj.db.dto.MsgCustom;

/**
 * <p><strong>类名: </strong></p>MsgCustom表操作接口<br/>
 * <p><strong>功能说明: </strong></p>MsgCustom表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日下午11:35:30 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface MsgCustomMapper {
	/**
	 * 根据主键删除Msg_Custom中指定记录<br/>
	 * @param  id 查询条件
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(Long id);
    /**
	 * 插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insert(MsgCustom record);
    /**
     * 插入自定义消息
     * @param  record 记录
     * @return Integer 插入结果
     */
    Integer insertSelective(MsgCustom record);
    /**
	 * 根据主键查询Msg_Custom中指定记录<br/>
	 * @param  id 查询结果
	 * @return MsgCustom 取得结果
	 */
    MsgCustom selectByPrimaryKey(Long id);
    /**
	 * 根据条件更新Msg_Custom中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(MsgCustom record);
    /**
	 * 根据主键更新Msg_Custom中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(MsgCustom record);
}