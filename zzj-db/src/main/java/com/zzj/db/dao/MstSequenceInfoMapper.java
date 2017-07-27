/**
 * Project Name:zzj-db
 * File Name:MstSequenceInfoMapper.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import com.zzj.db.dto.MstSequenceInfo;
/**
 * <p><strong>类名: </strong></p>MstSequenceInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义MstSequenceInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月02日上午9:36:35 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface MstSequenceInfoMapper {
	/**
	 * 删除记录 
	 * @param  sequenceType 查询条件
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String sequenceType);
    /**
     * 插入记录 
     * @param  record 查询条件
     * @return int 插入结果
     */
    int insert(MstSequenceInfo record);
    /**
     * 插入记录 
     * @param  record 查询条件
     * @return int 插入结果
     */
    int insertSelective(MstSequenceInfo record);
    /**
     * 根据主键查找指定记录 
     * @param  sequenceType 查询条件
     * @return MstSequenceInfo 查询结果
     */
    MstSequenceInfo selectByPrimaryKey(String sequenceType);
    /**
     * 更新记录 
     * @param  record 查询条件
     * @return int 更新结果
     */
    int updateByPrimaryKeySelective(MstSequenceInfo record);
    /**
     * 更新记录 
     * @param  record 查询条件
     * @return int 更新结果
     */
    int updateByPrimaryKey(MstSequenceInfo record);
}