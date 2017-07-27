/**
 * Project Name:zzj-web
 * File Name:AppUserService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;


import com.zzj.db.dto.AppUsersInfo;
import com.zzj.db.model.PageResult;

/**
 * <p><strong>类名: </strong></p>UserService <br/>
 * <p><strong>功能说明: </strong></p>前台用户管理操作模块相关业务逻辑处理. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日下午7:59:16 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface AppUserService {
	/**
	 * 根据条件取得相应appuser记录
	 * @param  roleId 角色Id
	 * @param  phoneNumber 电话号码
	 * @param  userName 用户名
	 * @param  pageNumber 页码
	 * @return List 画面表示用AppUser记录集
	 */
	PageResult<AppUsersInfo> searchUsers(String roleId, String phoneNumber, String userName, String pageNumber);
	/**
	 * 根据条件删除user记录。
	 * @param  userId 用户Id
	 * @return Integer 更新结果条目数
	 */
	Integer deleteUser(String userId);
}

