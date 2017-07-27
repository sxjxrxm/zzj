/**
 * Project Name:zzj-db
 * File Name:AppUserBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.AppUsersInfoMapper;
import com.zzj.db.dto.AppUsersInfo;
import com.zzj.db.model.AppUserVO;

/**
 * <p><strong>类名: </strong></p>AppUser业务数据库操作类<br/>
 * <p><strong>功能说明: </strong></p>AppUser业务数据相关操作处理. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日下午7:46:55 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class AppUserBlo {
	/**
	 * App_User_Info表操作Mapper
	 */
	@Autowired
	private AppUsersInfoMapper appUsersInfoMapper;
	/**
	 * 根据条件查询APP_Users_Info对应<br/>
	 * @param  user 查询记录对应
	 * @return List<AppUsersInfo> 查询结果
	 */
	public List<AppUsersInfo> selectSelective(AppUsersInfo user) {
		List<AppUsersInfo> appuserList = appUsersInfoMapper.selectSelective(user);
		return appuserList;
	}
	/**
	 * 根据条件更新App_Users_Info中指定记录<br/>
	 * @param  user 更新记录对应
	 * @return int 更新结果
	 */
	public int updateByPrimaryKeySelective(AppUsersInfo user){
		int x = appUsersInfoMapper.updateByPrimaryKeySelective(user);
		return x;
	}
	/**
	 * 根据主键查询App_Users_Info中指定记录<br/>
	 * @param  userId 主键
	 * @return AppUsersInfo 查询结果
	 */
	public AppUsersInfo getAppUser(String userId){
		return appUsersInfoMapper.selectByPrimaryKey(userId);
	}
	/**
	 * AppUserInfoVO记录取得总件数
	 * @param record 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectTotalCount(AppUsersInfo record) {
		return appUsersInfoMapper.selectTotalCount(record);
	}
	/**
	 * AppUserInfoVO记录分页查询
	 * @param keyUser 检索条件
	 * @return List<AppUsersInfo> 查询结果
	 */
	public List<AppUsersInfo> fySelectSelective(AppUserVO keyUser) {
		return appUsersInfoMapper.fySelectSelective(keyUser);
	}
}

