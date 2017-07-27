/**
 * Project Name:zzj-db
 * File Name:MstFieldInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.MstFieldInfo;
import com.zzj.db.dto.MstFieldInfoKey;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>MstFieldInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>MstFieldInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:56:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface MstFieldInfoMapper {
	
	/**
	 * 根据主键删除MstFieldInfo中指定记录<br/>
	 * @param  key 联合主键
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(MstFieldInfoKey key);

    /**
	 * 插入指定记录<br/>
	 * @param  record MstFieldInfo记录
	 * @return int 插入结果
	 */
    int insert(MstFieldInfo record);

    /**
   	 * 有条件的插入指定记录<br/>
   	 * @param  record MstFieldInfo记录
   	 * @return int 插入结果
   	 */
    int insertSelective(MstFieldInfo record);

    /**
	 * 根据主键查询MstFieldInfo中指定记录<br/>
	 * @param  key 联合主键
	 * @return MstFieldInfo 取得结果
	 */
    MstFieldInfo selectByPrimaryKey(MstFieldInfoKey key);

    /**
	 * 根据条件更新MstFieldInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(MstFieldInfo record);

    /**
   	 * 根据主键更新MstFieldInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKey(MstFieldInfo record);
    
    /**
   	 * 根据主键更新MstFieldInfo中指定记录<br/>
   	 * @param  techField 选择的技术领域
   	 * @return int 更新结果
   	 */
    int updateByTechField(String techField);

    /**
	 * 获取所有MstFieldInfo可用记录
	 * @param 无
	 * @return List<MstFieldInfo> 所有MstFieldInfo可用记录
	 */
	List<TopicFieldInfoKey> selectAll();
	
    /**
	 * 根据技术领域查询MstFieldInfo中指定记录<br/>
	 * @param  techFieldCd 技术领域
	 * @return List<MstFieldInfo> 取得结果
	 */
	List<MstFieldInfo> selectByField(String techFieldCd);
}