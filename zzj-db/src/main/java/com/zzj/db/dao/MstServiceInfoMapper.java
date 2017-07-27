/**
 * Project Name:zzj-db
 * File Name:MstServiceInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import com.zzj.db.dto.MstServiceInfo;

/**
 * <p><strong>类名: </strong></p>MstServiceInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>MstServiceInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午9:24:30 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface MstServiceInfoMapper {
	
	/**
	 * 插入指定记录<br/>
	 * @param  record  记录
	 * @return int 插入结果
	 */
    int insert(MstServiceInfo record);

	/**
	 * 有条件的插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insertSelective(MstServiceInfo record);
    
	/**
	 * 有条件的插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int addSelective(MstServiceInfo record);
    
	/**
	 * 查询原客服编辑信息<br/>
	 * @param  无
	 * @return MstServiceInfo 查询结果
	 */
    MstServiceInfo selectByDelFlag();
    
	/**
	 * 逻辑删除原客服编辑信息<br/>
	 * @param  mstServiceInfo 查询条件
	 * @return 无
	 */
    void delByDelFlag(MstServiceInfo mstServiceInfo);
}