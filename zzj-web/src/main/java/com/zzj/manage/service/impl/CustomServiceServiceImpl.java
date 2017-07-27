/**
 * Project Name:zzj-web
 * File Name:CustomServiceServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.blo.CustomServiceBlo;
import com.zzj.db.dto.MstServiceInfo;
import com.zzj.manage.service.CustomServiceService;

/**
 * <p><strong>类名: </strong></p>CustomServiceServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装客服编辑，客服编辑模块的实体Service类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午4:45:32 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class CustomServiceServiceImpl implements CustomServiceService {

	/**
	 * 客服编辑业务数据库操作类
	 */
	@Autowired
	CustomServiceBlo customServiceBlo;
	
	/**
	 * 查询原客服信息<br/>
	 * @param  无
	 * @return mstServiceInfo 查询结果
	 */
	@Override
	public MstServiceInfo selcetOldMstService() {
		
		MstServiceInfo mstServiceInfo = customServiceBlo.selectByDelFlag();
		
		return mstServiceInfo;
	}
	
	/**
	 * 删除原客服信息<br/>
	 * @param  mstServiceInfo 服务信息
	 * @return 无
	 */
	@Override
	public void delOldMstService(MstServiceInfo mstServiceInfo) {
		
		customServiceBlo.delByDelFlag(mstServiceInfo);
	}
	
	/**
	 * 插入新的客服编辑记录。
	 * @param mstServiceInfo 插入对象信息
	 */
	@Override
	public void addCustomService(MstServiceInfo mstServiceInfo) {
		
		customServiceBlo.addSelective(mstServiceInfo);
		
	}
}

