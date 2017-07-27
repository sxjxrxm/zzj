/**
 * Project Name:zzj-web
 * File Name:CustomServiceService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.manage.service;

import com.zzj.db.dto.MstServiceInfo;

/**
 * <p><strong>类名: </strong></p>CustomServiceService <br/>
 * <p><strong>功能说明: </strong></p>这个类是客服编辑模块，客服编辑Service接口. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午4:43:38 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface CustomServiceService {

	/**
	 * 查询原客服信息<br/>
	 * @param  无
	 * @return mstServiceInfo 查询结果
	 */
	public MstServiceInfo selcetOldMstService();

	/**
	 * 删除原客服信息<br/>
	 * @param  mstServiceInfo 服务信息
	 * @return 无
	 */
	void delOldMstService(MstServiceInfo mstServiceInfo);
	
	/**
	 * 插入新的客服编辑记录。
	 * @param mstServiceInfo 插入对象信息
	 */
	void addCustomService(MstServiceInfo mstServiceInfo);


}

