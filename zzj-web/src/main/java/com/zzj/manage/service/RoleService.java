/**
 * Project Name:zzj-web
 * File Name:RoleService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import com.zzj.db.model.PermissionVO;

/**
 * <p><strong>类名: </strong></p>RoleService <br/>
 * <p><strong>功能说明: </strong></p>用户页面权限管理模块相关业务逻辑处理.  <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月27日下午5:31:57 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface RoleService {
	/**
	 * 根据角色id查询该id对应的页面权限
	 * @param  roleId 角色Id
	 * @return PermissionVO 画面表示用权限记录集
	 */
	PermissionVO editSearch(String roleId);
	/**
	 * 页面权限保存：删除该用户Id下全部记录并添加新记录<br/>
	 * @param  roleId 角色Id
	 * @param  screenId 画面Id
	 * @return 执行结果
	 */
	Integer saveRoles(String roleId, String screenId);
	/**
	 * 
	 *页面权限保存：删除该用户Id下全部记录<br/>
	 * @param  roleId 角色Id
	 * @return 执行结果
	 */
	Integer deleteRoles(String roleId);

}

