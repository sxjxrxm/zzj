/**
 * Project Name:zzj-db
 * File Name:AppUsersInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.AppUsersInfo;
import com.zzj.db.model.AppUserVO;
/**
 * <p><strong>类名: </strong></p>App_User_Info表操作接口<br/>
 * <p><strong>功能说明: </strong></p>App_User_Info表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日下午19:24:30 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface AppUsersInfoMapper {
	/**
	 * 根据主键删除App_User_Info中指定记录<br/>
	 * @param  userId 查询条件
	 * @return Integer 删除结果
	 */
	
    int deleteByPrimaryKey(String userId);
    /**
	 * 插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insert(AppUsersInfo record);
    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insertSelective(AppUsersInfo record);
    /**
	 * 根据主键查询App_User_Info中指定记录<br/>
	 * @param  userId 查询结果
	 * @return AppUsersInfo 取得结果
	 */
    AppUsersInfo selectByPrimaryKey(String userId);
    /**
	 * 根据条件更新App_User_Info中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(AppUsersInfo record);
    /**
	 * 根据主键更新App_User_Info中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(AppUsersInfo record);
    /**
  	 * 根据条件查询对用AppUser记录，前台用户一览界面使用
  	 * @param  record User记录
  	 * @return List<AppUsersInfo> 查询到的user集
  	 * @author 刘研
  	 */
    List<AppUsersInfo> selectSelective(AppUsersInfo record);
    /**
  	 * 根据条件查询对用AppUser记录数量，前台用户一览界面使用
  	 * @param  record User记录
  	 * @return Integer 查询结果
  	 * @author 刘研
  	 */
	Integer selectTotalCount(AppUsersInfo record);
	/**
	 * AppUserInfoVO记录分页查询
	 * @param AppUserVO keyUser 检索条件
	 * @return List<AppUsersInfo> 查询结果
	 */
	List<AppUsersInfo> fySelectSelective(AppUserVO keyUser);
}