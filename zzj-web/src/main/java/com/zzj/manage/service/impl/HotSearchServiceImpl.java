/**
 * Project Name:zzj-web
 * File Name:HotSearchServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.blo.HotSearchBlo;
import com.zzj.db.dto.HotSearchTerm;
import com.zzj.manage.service.HotSearchService;

/**
 * <p><strong>类名: </strong></p>HotSearchServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>封装热搜词管理模块service. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月28日下午3:28:49 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class HotSearchServiceImpl implements HotSearchService{

	/**
	 * 热搜词业务数据库操作类
	 */
	@Autowired
	private HotSearchBlo hotSearchBlo;
	
	/**
	 * 查询原热搜词记录。<br/>
	 * @return HotSearchTerm 查询结果
	 */
	@Override
	public HotSearchTerm selcetOldHotSearch() {
		
		HotSearchTerm hotSearchTerm = hotSearchBlo.selectByDelFlag();
		
		return hotSearchTerm;
	}
	
	/**
	 * 删除原热搜词记录<br/>
	 * @return 无
	 */
	@Override
	public void delOldHotSearch() {
		
		hotSearchBlo.delByDelFlag();
	}
	
	/**
	 * 增加新热搜词记录<br/>
	 * @param  HotSearchTerm record记录
	 * @return 无
	 */
	@Override
	public void newHotSearch(HotSearchTerm hotSearchTerm) {
		
		hotSearchBlo.setNewHotSearch(hotSearchTerm);
	}

}

