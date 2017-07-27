/**
 * Project Name:zzj-web
 * File Name:AppUserServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.zzj.core.exception.BusinessException;
import com.zzj.db.blo.AppUserBlo;
import com.zzj.db.dto.AppUsersInfo;
import com.zzj.db.model.AppUserVO;
import com.zzj.db.model.PageResult;
import com.zzj.manage.service.AppUserService;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;
/**
 * <p><strong>类名: </strong></p>AppUserServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>前台用户管理操作模块相关业务逻辑处理实现类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日下午8:00:57  <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class AppUserServiceImpl implements AppUserService {
	/**
	 *AppUser业务数据库操作类
	 */
	@Autowired
	private  AppUserBlo appUserBlo;
	/**
	 * 根据条件取得相应appuser记录
	 * @param  roleId 角色Id
	 * @param  phoneNumber 电话号码
	 * @param  userName 用户名
	 * @param  pageNumber 页码
	 * @return List 画面表示用AppUser记录集
	 */
	@Override
	public PageResult<AppUsersInfo> searchUsers(String roleId, String phoneNumber, String userName, String pageNumber) {
		// 实例化一个AppUserVO作为查询条件
		AppUserVO keyUser = new AppUserVO();
		// 为user属性赋值
		if (!(StringUtil.isBlank(roleId))) {
			keyUser.setRoleId(roleId); 
		}
		if (!(StringUtil.isBlank(phoneNumber))) {
			keyUser.setPhoneNumber(phoneNumber)  ;
		}
		if (!(StringUtil.isBlank(userName))) {
			keyUser.setUserName(userName) ;
		}
		if(StringUtil.isBlank(pageNumber)){
			keyUser.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			keyUser.setDbIndex(0);
			keyUser.setPageNo(1);
		}
		else{
			keyUser.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			keyUser.setPageNo(Integer.valueOf(pageNumber));
			keyUser.setDbIndex((Integer.valueOf(pageNumber) - 1) * keyUser.getPageSize());
		}
		
		// 查询结果并放入一个list
		PageResult<AppUsersInfo> appusersInfoList = fyQuery(keyUser) ;
		return appusersInfoList;
	}
	/**
	 * 分页处理。
	 * @param  keyUser 检索条件
	 * @return PageResult<AppUsersInfo> 画面表示用AppUser记录集
	 */
	private PageResult<AppUsersInfo> fyQuery(AppUserVO keyUser) {
		// 定义返回结果
		PageResult<AppUsersInfo> pageResult = new PageResult<AppUsersInfo>();
		// 符合条件记录总数
		Integer totalCount = appUserBlo.selectTotalCount(keyUser);
		// 没有检索到数据。
		if (totalCount == 0)
		{
			return new PageResult<AppUsersInfo>();
		}
		if (keyUser.getDbIndex() >= totalCount)
		{
			int flag = totalCount % keyUser.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
			int num = totalCount / keyUser.getPageSize();
			keyUser.setPageNo((flag == 0) ? num : num + 1);
			keyUser.setDbIndex((keyUser.getPageNo() - 1) * keyUser.getPageSize());
		}
		if (keyUser.getDbIndex() < 0)
		{
			keyUser.setDbIndex(0);
			keyUser.setPageNo(1);
		}
		List<AppUsersInfo> list = appUserBlo.fySelectSelective(keyUser);
		
		/**
		 * 构造分页结果集
		 */
		
		pageResult.setPageNo(keyUser.getDbIndex() / keyUser.getPageSize() + 1);
		pageResult.setPageSize(keyUser.getPageSize());
		int flag = totalCount % keyUser.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / keyUser.getPageSize();
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(list);
		
		return pageResult;
	}
	/**
	 * 根据条件删除user记录。
	 * @param  userId 用户Id
	 * @return Integer 更新结果条目数
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public Integer deleteUser(String userId) {
		// 得到一个appuser作为更新记录
		AppUsersInfo appUsersInfo=new AppUsersInfo();		
		// 赋予属性
		appUsersInfo.setUserId(userId);
		appUsersInfo.setDeleteFlag(1);
		// 调用blo对appuser记录更新
		Integer x = appUserBlo.updateByPrimaryKeySelective(appUsersInfo);
		if (x == 1) {
			// 返回值表示更新成功
			return x;
		}
		else {
			throw new BusinessException("删除操作错误");
		}
	}
}

