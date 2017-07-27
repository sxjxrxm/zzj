/**
 * Project Name:zzj-db
 * File Name:CustomServiceBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.MstServiceInfoMapper;
import com.zzj.db.dto.MstServiceInfo;

/**
 * <p><strong>类名: </strong></p>CustomServiceBlo <br/>
 * <p><strong>功能说明: </strong></p>这个类是客服编辑模块，数据库查询操作. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午4:31:12 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class CustomServiceBlo {

	/**
	 * 客服编辑业务数据库操作类
	 */
	@Autowired
	MstServiceInfoMapper mstServiceInfoMapper;
	
	/**
	 * 查询原客服信息<br/>
	 * @param  无
	 * @return MstServiceInfo 查询结果
	 */
	public MstServiceInfo selectByDelFlag() {
    	
    	return mstServiceInfoMapper.selectByDelFlag();
	}
    
	/**
	 * 删除原客服信息<br/>
	 * @param  mstServiceInfo 检索条件
	 * @return 无
	 */
	public void delByDelFlag(MstServiceInfo mstServiceInfo) {
    	
		mstServiceInfoMapper.delByDelFlag(mstServiceInfo);
	}
	
	/**
	 * 插入新客服记录<br/>
	 * @param  mstServiceInfo 插入对象
	 * @return 无
	 */
	public void addSelective(MstServiceInfo mstServiceInfo){
		mstServiceInfoMapper.addSelective(mstServiceInfo);
	}

}

