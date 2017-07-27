/**
 * Project Name:zzj-db
 * File Name:MstUsersInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.MstPermissionInfoMapper;
import com.zzj.db.dao.MstUsersInfoMapper;
import com.zzj.db.dto.MstPermissionInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.model.UserVO;

/**
 * <p><strong>类名: </strong></p>MstUsersInfoBlo <br/>
 * <p><strong>功能说明: </strong></p>Mst_Users_Info表相关数据操作处理 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日上午9:36:35 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class MstUsersInfoBlo {
	/**
	 * Mst_Permission_Info表操作Mapper
	 */
	@Autowired
	private MstPermissionInfoMapper mstPermissionInfoMapper;
	/**
	 * Mst_Users_Info表操作Mapper
	 */
	@Autowired
	private MstUsersInfoMapper mstUsersInfoMapper;
	/**
	 * 根据主键在表Mst_User_Info记录取得处理
	 * @param  userId 检索条件
	 * @return MstUsersInfo 记录
	 */
	public MstUsersInfo getUser(String userId){
		MstUsersInfo mstUsersInfo=mstUsersInfoMapper.selectByPrimaryKey(userId);
		return mstUsersInfo;
	}
	/**
	 * 取得表Mst_Permission_Info中全部记录
	 * @param 无
	 * @return List<MstPermissionInfo> Permission记录集
	 */
	public List<MstPermissionInfo> getAllPermission(){
		List<MstPermissionInfo> mstPermissionInfolist=mstPermissionInfoMapper.selectAllPermissionSelective();
		return mstPermissionInfolist;
	}
	/**
	 * 根据条件更新Mst_Users_Info中指定记录<br/>
	 * @param  user 更新记录对应
	 * @return int 更新结果
	 */
	public int updateByPrimaryKeySelective(MstUsersInfo user) {
		int x = mstUsersInfoMapper.updateByPrimaryKeySelective(user);
		return x;
	}
	/**
	 * 根据条件查询Mst_Users_Info对应<br/>
	 * @param  user 查询记录对应
	 * @return List<MstUsersInfo> 查询结果
	 */
	public List<MstUsersInfo> selectSelective(MstUsersInfo user)
	{
		List<MstUsersInfo> list=mstUsersInfoMapper.selectSelective(user);
		return list;
	}
	/**
	 * 向Mst_Users_Info表添加纪录<br/>
	 * @param  user 查询记录对应
	 * @return int 查询结果
	 */
	public int  insertSelective(MstUsersInfo user)
	{
		int i=mstUsersInfoMapper.insert(user);
		return i;
	}
	/**
	 * 根据User_Id字段向表Mst_Users_Info查询UserName字段<br/>
	 * @param  userId 查询记录对应
	 * @return Stirng 查询结果
	 */
	public String  getUserName(String userId)
	{
		String userName = mstUsersInfoMapper.getUserName(userId);
		return userName;
	}
	/**
	 * MstUserInfoVO记录取得总件数
	 * @param keyUser 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectTotalCount(UserVO keyUser) {		
		return mstUsersInfoMapper.selectTotalCount(keyUser);
	}
	/**
	 * MstUserInfoVO分页查询方法
	 * @param UserVO keyUser 检索条件
	 * @return List<MstUsersInfo> 查询结果
	 */
	public List<MstUsersInfo> fySelectSelective(UserVO keyUser) {
		return mstUsersInfoMapper.fySelectSelective(keyUser);
	}
}

