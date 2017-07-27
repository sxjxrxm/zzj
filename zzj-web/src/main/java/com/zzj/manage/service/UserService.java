/**
 * Project Name:zzj-web
 * File Name:UserService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.List;

import com.zzj.db.dto.MstPermissionInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.model.PageResult;
import com.zzj.db.model.UserVO;

/**
 * <p><strong>类名: </strong></p>UserService <br/>
 * <p><strong>功能说明: </strong></p>用户管理操作模块相关业务逻辑处理. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日上午10:06:12 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface UserService {
	/**
	 * 取得User记录
	 * @param userId 用户Id
	 * @return UserVO 画面表示用user记录
	 */
	UserVO searchUser(String userId);
	/**
	 * 查询Mst_Permission_Info表中全部记录<br/>
	 * @return List  Mst_Permission_Info表记录集
	 */
	List<MstPermissionInfo> getAllPermission();
	/**
	 * 根据条件取得相应user记录
	 * @param roleId 角色Id
	 * @param phoneNumber 电话号码
	 * @param userName 用户名 
	 * @param userId 用户Id
	 * @param pageNumber 页码
	 * @return PageResult<MstUsersInfo> 画面表示用User记录集
	 */
	PageResult<MstUsersInfo> searchUsers(String roleId, String phoneNumber,
			String userName,String userId, String pageNumber);
	/**
	 * 删除用户:根据条件更新Mst_User_Info表中相应记录的deleteflag字段<br/>
	 * @param userId 用户名Id
	 * @return Integer 更新结果条目数
	 */
	Integer deleteUser(String userId);
	/**
	 * 用户编辑：根据UserVO的isAdd字段判断更新user记录或新增user记录
	 * @param mstUsersInfo 用户信息
	 * @param isAdd 1:添加；0：编辑
	 * @return Integer 更新结果条目数
	 * @throws Exception
	 */
	void edituser(MstUsersInfo user, String isAdd)  throws Exception;
	/**
	 * 用户编辑：根据UserVO的isAdd字段判断更新user记录或新增user记录
	 * @param userId 用户Id
	 * @return Integer 更新结果条目数
	 */
	MstUsersInfo checkUserId(String userId);
	

}

