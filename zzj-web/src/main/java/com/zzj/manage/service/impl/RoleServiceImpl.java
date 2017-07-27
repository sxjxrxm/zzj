/**
 * Project Name:zzj-web
 * File Name:RoleServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzj.db.blo.MstPermissionInfoBlo;
import com.zzj.db.blo.MstScreenInfoBlo;
import com.zzj.db.dto.MstPermissionInfo;
import com.zzj.db.dto.MstScreenInfo;
import com.zzj.db.model.PermissionVO;
import com.zzj.manage.service.RoleService;

/**
 * <p><strong>类名: </strong></p>RoleServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>用户页面权限管理模块相关业务逻辑处理. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月27日下午5:33:29 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class RoleServiceImpl implements RoleService {
    /**
	 * Mst_Permission_Info表业务数据库操作类
	 */
	@Autowired
    private	MstPermissionInfoBlo mstPermissionInfoBlo;
    /**
 	 * Mst_Screen_Info表业务数据库操作类
 	 */
	@Autowired
    private	MstScreenInfoBlo mstScreenInfoBlo;
	/**
	 * 根据角色id查询该id对应的页面权限
	 * @param  roleId 角色Id
	 * @return PermissionVO 画面表示用权限记录集
	 */
	@Override
	public PermissionVO editSearch(String roleId) {
		// 调用blo分别在Mst_Permission_Info表和Mst_Role_Info表查询出相应记录
		List<MstPermissionInfo> mstPermissionInfoList = mstPermissionInfoBlo.selectByRoleId(roleId);
		List<MstScreenInfo> mstScreenInfoList = mstScreenInfoBlo.selectAllScreen();
		// 将所有父页面取出
		// 定义List表示全部父页面集合
		List<String> allParentScreen = new ArrayList<>();
		for(int x = 0; x < mstScreenInfoList.size(); x++){
			// 标识位表示是否重复
			boolean flag = true;
			for(int y = x + 1;y < mstScreenInfoList.size();  y++){
				// 得到要对比的父页面
				String xParent = mstScreenInfoList.get(x).getParentScreenId();
				String yParent = mstScreenInfoList.get(y).getParentScreenId();
				if(xParent.equals(yParent)){
					flag = false;
				}
			}
			if(flag){
				allParentScreen.add(mstScreenInfoList.get(x).getParentScreenId());
			}
		}
		
		
		// 将所有页面按页面所需格式：父页面.子页面,子页面,子页面...格式组合
		// 定义结果集
		List<List<String>> allScreens = new ArrayList<>();
		// 遍历全部父界面
		for(int i = 0; i< allParentScreen.size(); i++){
			List<String> parentScreen =new ArrayList<>();
			parentScreen.add(allParentScreen.get(i));
			allScreens.add(parentScreen);
			// 得到当前父页面对应得所有子页面
			List< String> childrens = getChildrenScreens(allParentScreen.get(i),mstScreenInfoList);
			// 便利所有子界面
			for(int j = 0; j < childrens.size(); j++){
				// 将当前子界面加入allScreens
				allScreens.get(i).add(childrens.get(j));
			}
		}
		
		
		// 将该用户拥有权限的页面的父页面取出
		// 定义List表示全部父页面集合
		List<String> trueParentScreen = new ArrayList<>();
		for(int i = 0; i < mstPermissionInfoList.size(); i++){
			boolean flag = true;
			for(int j = i + 1;j < mstPermissionInfoList.size();  j++){
				// 得到要对比的父页面
				String iParent = getParentScreen(mstPermissionInfoList.get(i).getScreenId(),mstScreenInfoList);
				String jParent = getParentScreen(mstPermissionInfoList.get(j).getScreenId(),mstScreenInfoList);
				if(iParent.equals(jParent)){
					flag = false;
				}
			}
			if(flag){
				trueParentScreen.add(getParentScreen(mstPermissionInfoList.get(i).getScreenId(),mstScreenInfoList));
			}
		}
		
		// 定义PermissionVO表示返回结果
		PermissionVO permissionVO = new PermissionVO();
		// 为返回结果赋值
		permissionVO.setRoleId(roleId);
		permissionVO.setAllScreens(mstScreenInfoList);
		permissionVO.setFmtAllScreens(allScreens);
		permissionVO.setTrueScreens(mstPermissionInfoList);
		return permissionVO;
	}
	//获得页面的父页面方法
	public static String getParentScreen(String screenId, List<MstScreenInfo> mstScreenInfoList)
	{
		// 定义返回值
		String parentScreen = "";
		// 遍历全部界面
		for(int i = 0; i < mstScreenInfoList.size(); i++){
			if(screenId.equals(mstScreenInfoList.get(i).getScreenId()))
			{
				parentScreen =  mstScreenInfoList.get(i).getParentScreenId();
			}
		}
		return parentScreen;
	}
	//获得父页面对应所有子页面方法
	public static List<String> getChildrenScreens(String screenId, List<MstScreenInfo> mstScreenInfoList)
	{
		// 定义返回值
		List<String> childrens = new ArrayList<>();
		// 遍历全部界面
		for(int i = 0; i < mstScreenInfoList.size(); i++){
			if(screenId.equals(mstScreenInfoList.get(i).getParentScreenId()))
			{
				childrens.add(mstScreenInfoList.get(i).getScreenId());
			}
		}
		return childrens;
	}
	/**
	 * 页面权限保存：删除该用户Id下全部记录并添加新记录<br/>
	 * @param  roleId 角色Id
	 * @param  screenId 画面Id
	 * @return 执行结果
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public Integer saveRoles (String roleId, String screenId) {
		// 定义变量表示返回值
		Integer x = -1;
		// 定义
		MstPermissionInfo permissionInfo = new MstPermissionInfo();
		// 赋属
		permissionInfo.setRoleId(roleId);
		permissionInfo.setScreenId(screenId);
		permissionInfo.setUpdateTime(new Date());
		// 调用service插入记录
		x = mstPermissionInfoBlo.insertPermission(permissionInfo);
		return x;
	}
	/**
	 * 页面权限保存：删除该用户Id下全部记录<br/>
	 * @param  roleId 角色Id
	 * @return 执行结果
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public Integer deleteRoles(String roleId) {
		// 调用Blo删除所有记录
		Integer x = mstPermissionInfoBlo.deleteByRoleId(roleId);
		return x;
	}
}

