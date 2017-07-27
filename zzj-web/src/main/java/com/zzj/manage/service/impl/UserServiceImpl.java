/**
 * Project Name:zzj-web
 * File Name:UserServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzj.core.exception.BusinessException;
import com.zzj.db.blo.MstUsersInfoBlo;
import com.zzj.db.dto.MstPermissionInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.model.PageResult;
import com.zzj.db.model.UserVO;
import com.zzj.manage.service.CommonService;
import com.zzj.manage.service.UserService;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;



/**
 * <p><strong>类名: </strong></p>UserServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>用户管理操作模块相关业务逻辑处理实现类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日上午10:08:43 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * User业务数据库操作类
	 */
	@Autowired
	private MstUsersInfoBlo mstUsersInfoBlo;
	
	/**
	 * 腾讯云服务
	 */
	@Autowired
	private CommonService common;
	/**
	 * 取得User记录
	 * @param userId 用户Id
	 * @return UserVO 画面表示用user记录
	 */
	@Override
	public UserVO searchUser(String userId)  {
		MstUsersInfo user = mstUsersInfoBlo.getUser(userId)  ;
		UserVO vo = null;
		if(user != null)  {
			vo = new UserVO() ;
			BeanUtils.copyProperties(user, vo) ;
		}
		return vo;
	}
	/**
	 * 取得全部permission记录
	 * @return List 画面表示用Permisssion记录集
	 */
	@Override
	public List<MstPermissionInfo> getAllPermission() {
		List<MstPermissionInfo> permissionList = mstUsersInfoBlo.getAllPermission() ;
		return permissionList;
	}
	/**
	 * 根据条件取得相应user记录
	 * @param roleId 角色Id
	 * @param phoneNumber 电话号码
	 * @param userName 用户名 
	 * @param userId 用户Id
	 * @param pageNumber 页码
	 * @return PageResult<MstUsersInfo> 画面表示用User记录集
	 */
	@Override
	public PageResult<MstUsersInfo> searchUsers(String roleId, String phoneNumber, String userName, String userId, String pageNumber)  {
		// 实例化一个User作为查询条件
		UserVO user = new UserVO();
		// 为user属性赋值
		if (!(StringUtil.isBlank(roleId))) {
			user.setRoleId(roleId)  ;
		}
		if (!(StringUtil.isBlank(userId))) {
			user.setUserId(userId);
		}
		if (!(StringUtil.isBlank(phoneNumber))) {
			user.setPhoneNumber(phoneNumber)  ;
		}
		if (!(StringUtil.isBlank(userName))) {
			user.setUserName(userName) ;
		}

		if(StringUtil.isBlank(pageNumber)){
			user.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			user.setDbIndex(0);
			user.setPageNo(1);
		}
		else{
			user.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			user.setPageNo(Integer.valueOf(pageNumber));
			user.setDbIndex((Integer.valueOf(pageNumber) - 1) * user.getPageSize());
		}
		// 查询结果并放入一个list
		PageResult<MstUsersInfo> usersInfoList = fyQuery(user) ;
		return usersInfoList;
	}
	/**
	 * 分页查询方法
	 */
	public  PageResult<MstUsersInfo> fyQuery(UserVO keyUser){
		// 定义返回结果
		PageResult<MstUsersInfo> pageResult = new PageResult<MstUsersInfo>();
		// 符合条件记录总数
		Integer totalCount = mstUsersInfoBlo.selectTotalCount(keyUser);
		// 没有检索到数据。
		if (totalCount == 0)
		{
			return new PageResult<MstUsersInfo>();
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
		List<MstUsersInfo> list = mstUsersInfoBlo.fySelectSelective(keyUser);
		
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
	 * 根据条件删除user记录
	 * @param userId 用户名Id
	 * @return Integer 更新结果条目数
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public Integer deleteUser(String userId)  {
		// 实例化一个user
		MstUsersInfo mstUsersInfo = new MstUsersInfo() ;
		// 将User 赋予属性
		mstUsersInfo.setUserId(userId) ;
		mstUsersInfo.setDeleteFlag(1);;
		// 调用blo更新User
		Integer x = mstUsersInfoBlo.updateByPrimaryKeySelective(mstUsersInfo) ;
		if(x == 1){
			return x;
		}
		else{
			throw new BusinessException("删除操作错误");
		}
		
	}
	/**
	 * 用户编辑：根据UserVO的isAdd字段判断更新user记录或新增user记录
	 * @param mstUsersInfo 用户信息
	 * @param isAdd 1:添加；0：编辑
	 * @return Integer 更新结果条目数
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public void edituser(MstUsersInfo mstUsersInfo, String isAdd) throws Exception {
		
		// 将user属性赋予一个MstUserInfo以便操作数据库
				/*MstUsersInfo mstUsersInfo=new MstUsersInfo();
				BeanUtils.copyProperties(user, mstUsersInfo);*/
				// 根据isAdd字段判断添加User或编辑User
				if("1".equals(isAdd)){

					mstUsersInfo.setCreateTime(new Date());
					mstUsersInfo.setErrorCount(0);
					mstUsersInfo.setDeleteFlag(0);
					mstUsersInfoBlo.insertSelective(mstUsersInfo);
					// 注册腾讯云账号
					common.accountImport(mstUsersInfo.getUserId(), mstUsersInfo.getUserName(), null);
					
				}
				else{
					mstUsersInfo.setUpdateTime(new Date());
					mstUsersInfoBlo.updateByPrimaryKeySelective(mstUsersInfo);
				}
		
	}
	/**
	 * 用户编辑：根据UserVO的isAdd字段判断更新user记录或新增user记录
	 * @param userId 用户Id
	 * @return Integer 更新结果条目数
	 */
	@Override
	public MstUsersInfo checkUserId(String userId) {
		// 调用blo查询主键为userId的user,并返回
		MstUsersInfo mstUsersInfo = mstUsersInfoBlo.getUser(userId);
		return mstUsersInfo;
		
	}
	
}

