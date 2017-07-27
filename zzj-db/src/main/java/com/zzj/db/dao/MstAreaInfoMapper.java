/**
 * Project Name:zzj-db
 * File Name:MstAreaInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.MstAreaInfo;

/**
 * <p><strong>类名: </strong></p>MstAreaInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>MstAreaInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月25日下午5:56:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface MstAreaInfoMapper {
	/**
	 * 根据主键删除MstAreaInfo中指定记录<br/>
	 * @param  regionId 主键
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(Double regionId);

    /**
	 * 插入指定记录<br/>
	 * @param  record MstAreaInfo记录
	 * @return int 插入结果
	 */
    int insert(MstAreaInfo record);

    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record MstAreaInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(MstAreaInfo record);

    /**
   	 * 根据主键查询MstAreaInfo中指定记录<br/>
   	 * @param  regionId 主键
   	 * @return MstAreaInfo 取得结果
   	 */
    MstAreaInfo selectByPrimaryKey(Double regionId);

    /**
	 * 根据条件更新MstAreaInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(MstAreaInfo record);

    /**
	 * 根据主键更新MstAreaInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(MstAreaInfo record);

    /**
	 * 根据专家编辑页面城市显示市级城市信息<br/>
	 * @param 无
	 * @return List<MstAreaInfo> 查询结果
	 */
	List<MstAreaInfo> selectAllCity();

	/**
	 * 根据专家编辑页面城市显示信息<br/>
	 * 根据父级id获得相应的下级信息<br/>
	 * @param parentId 查询条件
	 * @return List<MstAreaInfo> 查询结果
	 */
	List<MstAreaInfo> selectAllByParentId(Double parentId);

	/**
	 * 根据区域代码获得区域id，用户后续查询操作<br/>
	 * @param  regionCode 区域代码
	 * @return Double  区域id
	 */
	Double selectRegionIdByRegionCode(String regionCode);
}