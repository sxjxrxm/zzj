/**
 * Project Name:zzj-db
 * File Name:VersionInfoMapper.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import com.zzj.db.dto.VersionInfo;

/**
 * <p><strong>类名: </strong></p>VersionInfo表操作接口 <br/>
 * <p><strong>功能说明: </strong></p>定义VersionInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2017年1月3日下午2:36:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface VersionInfoMapper {
	/**
	 * 根据主键删除Version_Info中指定记录<br/>
	 * @param  appType 主键
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(Integer appType);
    /**
	 * 插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insert(VersionInfo record);
    /**
   	 * 有条件的插入指定记录<br/>
   	 * @param  record 记录
   	 * @return int 插入结果
   	 */
    int insertSelective(VersionInfo record);
    /**
	 * 根据主键查询Version_Info中指定记录<br/>
	 * @param  appType 主键
	 * @return VersionInfo 取得结果
	 */
    VersionInfo selectByAppType(Integer appType);
    /**
	 * 根据条件更新Version_Info中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(VersionInfo record);
    /**
   	 * 根据主键更新Version_Info中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKey(VersionInfo record);
}