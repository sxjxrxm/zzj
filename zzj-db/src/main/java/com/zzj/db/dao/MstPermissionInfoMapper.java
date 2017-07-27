/**
 * Project Name:zzj-db
 * File Name:MstPermissionInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.MstPermissionInfo;
/**
 * <p><strong>类名: </strong></p>Mst_Permission_Info表操作接口<br/>
 * <p><strong>功能说明: </strong></p>Mst_Permission_Info表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日上午9:36:35 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface MstPermissionInfoMapper {
	/**
	 * 根据主键删除Mst_Permission_Info中指定记录<br/>
	 * @param  roleId 角色编码
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String roleId);
    /**
	 * 插入指定记录<br/>
	 * @param  record MstPermissionInfo记录
	 * @return int 插入结果
	 */
    int insert(MstPermissionInfo record);
    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record MstPermissionInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(MstPermissionInfo record);
    /**
	 * 根据主键查询Mst_Permission_Info中指定记录<br/>
	 * @param  roleId 角色编码
	 * @return MstPermissionInfo 取得结果
	 */
    MstPermissionInfo selectByPrimaryKey(String roleId);
    /**
   	 * 根据roleId查询Mst_Permission_Info中对应<br/>
   	 * @param  roleId 角色编码
   	 * @return List<MstPermissionInfo> 取得结果集
   	 */
     List<MstPermissionInfo> selectByRoleId(String roleId);
    /**
   	 * 查询Mst_Permission_Info中全部记录<br/>
   	 * @param  无
   	 * @return List<MstPermissionInfo> 取得结果
   	 */
     List<MstPermissionInfo> selectAllPermission();

	/**
	 * 查询Mst_Permission_Info中全部记录(对role_id字段去重)<br/>
	 * @param 无
	 * @return List<MstPermissionInfo> 取得结果
	 */
     List<MstPermissionInfo> selectAllPermissionSelective();
    /**
	 * 根据条件更新Mst_Permission_Info中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(MstPermissionInfo record);
    /**
	 * 根据主键更新Mst_Permission_Info中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(MstPermissionInfo record);
    /**
	 * 统计Mst_Permission_Info中共有多少RoleId<br/>
	 * @param 无
	 * @return int 查询结果
	 */
    int countRoleId();
}