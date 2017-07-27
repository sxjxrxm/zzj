/**
 * Project Name:zzj-db
 * File Name:MstUsersInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.model.UserVO;
/**
 * <p><strong>类名: </strong></p>Mst_Users_Info表操作接口<br/>
 * <p><strong>功能说明: </strong></p>Mst_Users_Info表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日上午9:36:35 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface MstUsersInfoMapper {
	/**
	 * 根据主键删除Mst_Users_Info中指定记录<br/>
	 * @param  userId 用户ID
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String userId);
    /**
	 * 插入指定记录<br/>
	 * @param  record MstUsersInfo记录
	 * @return int 插入结果
	 */
    int insert(MstUsersInfo record);
    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record MstUsersInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(MstUsersInfo record);
    /**
	 * 根据主键查询Demo中指定记录<br/>
	 * @param  userId 用户ID
	 * @return MstUsersInfo 取得结果
	 */
    MstUsersInfo selectByPrimaryKey(String userId);
    /**
	 * 根据条件更新Mst_Users_Info中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(MstUsersInfo record);
    /**
	 * 根据主键更新Mst_Users_Info中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(MstUsersInfo record);
    /**
  	 * 根据条件查询对用User记录，用户一览界面使用
  	 * @param  record User记录
  	 * @return List<MstUsersInfo> 查询到的user集
  	 * @author 刘研
  	 */
    List<MstUsersInfo> selectSelective(MstUsersInfo record);
    /**
  	 * 根据userid查询userName，用户一览界面使用
  	 * @param  userId User属性
  	 * @return String 查询到的userName
  	 * @author 刘研
  	 */
    String getUserName(String userId);
    /**
  	 * 统计符合条件的所有记录条目数
  	 * @param  keyUser User记录
  	 * @return Integer 查询到的结果条目数
  	 * @author 刘研
  	 */
	Integer selectTotalCount(UserVO keyUser);
	/**
  	 * 分页查询方法
  	 * @param  keyUser User记录
  	 * @return List<MstUsersInfo> 查询到的结果条目数
  	 * @author 刘研
  	 */
	List<MstUsersInfo> fySelectSelective(UserVO keyUser);
	
}