/**
 * Project Name:zzj-db
 * File Name:MstPermissionInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.MstPermissionInfoMapper;
import com.zzj.db.dto.MstPermissionInfo;

/**
 * <p><strong>类名: </strong></p>MstPermissionInfoBlo <br/>
 * <p><strong>功能说明: </strong></p>Mst_Permission_Info表相关数据操作处理 . <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日上午10:03:11 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class MstPermissionInfoBlo {
	/**
	 * Mst_Permission_Info表操作Mapper
	 */
	@Autowired
	private MstPermissionInfoMapper mstPermissionInfoMapper;
	
	/**
	 * 根据角色查询信息
	 * @param  roleId 角色id
	 * @return List<MstPermissionInfo> 查询结果
	 */
	public List<MstPermissionInfo> selectByRoleId(String roleId) {
		List<MstPermissionInfo> mstPermissionInfos = mstPermissionInfoMapper.selectByRoleId(roleId);
		return mstPermissionInfos;
	}
	/**
	 * 统计页面权限表中共有多少Role_ID
	 * @param  无
	 * @return int 查询结果
	 */
	public int countRoleId() {
		int x = mstPermissionInfoMapper.countRoleId();
		return x;
	}
	/**
	 *页面权限保存：删除该用户Id下全部记录<br/>
	 * @param  roleId 角色id
	 * @return Integer 删除结果
	 */
	public Integer deleteByRoleId(String roleId) {
		// 调用Mapper删除
		return mstPermissionInfoMapper.deleteByPrimaryKey(roleId);
	}
	/**
	 *页面权限保存：添加新记录<br/>
	 * @param  MstPermissionInfo permissionInfo 记录
	 * @return Integer 插入结果
	 */
	public Integer insertPermission(MstPermissionInfo permissionInfo) {
		// 调用Mapper添加
		return mstPermissionInfoMapper.insert(permissionInfo);
	}

}

