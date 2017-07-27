/**
 * Project Name:zzj-db
 * File Name:MsgImageMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.MsgImage;
import com.zzj.db.model.CourseMsgVO;

/**
 * <p><strong>类名: </strong></p>MsgFace表操作接口<br/>
 * <p><strong>功能说明: </strong></p>MsgFace表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日下午11:35:30 <br/>
 * @author   马玥
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface MsgImageMapper {
	/**
	 * 根据主键删除Msg_Image中指定记录<br/>
	 * @param  id 查询条件
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(Long id);
    /**
	 * 插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insert(MsgImage record);
    /**
     * 插入图片消息
     * @param  record 记录
     * @return Integer 插入结果
     */
    Integer insertSelective(MsgImage record);
    /**
   	 * 根据主键查询Msg_Image中指定记录<br/>
   	 * @param  id 查询结果
   	 * @return MsgImage 取得结果
   	 */
    MsgImage selectByPrimaryKey(Long id);
    /**
	 * 根据条件更新Msg_Image中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(MsgImage record);
    /**
   	 * 根据主键更新Msg_Image中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKey(MsgImage record);

    /**
     * 根据mid查询记录
     * @param  mid
     * @return List<CourseMsgVO> 记录集合
     */
	List<CourseMsgVO> selectByMid(Long mid);
}