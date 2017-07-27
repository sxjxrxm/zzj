/**
 * Project Name:zzj-db
 * File Name:ScreeningInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京常通多维软件技术开发有限公司  All Rights Reserved.
*/

package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.ScreeningInfo;

/**
 * <p><strong>类名: </strong></p>ScreeningInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>ScreeningInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月22日下午1:56:30 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */

public interface ScreeningInfoMapper {
	
	/**
	 * 根据主键删除ScreeningInfo中指定记录<br/>
	 * @param  key busiType
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String busiType);

    /**
	 * 插入指定记录<br/>
	 * @param  record ScreeningInfo记录
	 * @return int 插入结果
	 */
    int insert(ScreeningInfo record);

    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record ScreeningInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(ScreeningInfo record);

	/**
	 * 根据主键查询ScreeningInfo中指定记录<br/>
	 * @param  key busiType
	 * @return ScreeningInfo 取得结果
	 */
    ScreeningInfo selectByPrimaryKey(String busiType);

	/**
	 * 根据条件更新ScreeningInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(ScreeningInfo record);

	/**
	 * 根据主键更新ScreeningInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
	 */
    int updateByPrimaryKey(ScreeningInfo record);
    
	/**
	 * 选择全部机能的筛选条件<br/>
	 * @param 无
   	 * @return ScreeningInfo 全部机能的筛选条件
	 */
    List<ScreeningInfo> selectAllScreeningInfo();
    
}