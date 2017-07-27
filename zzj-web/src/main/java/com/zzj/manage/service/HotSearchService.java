/**
 * Project Name:zzj-web
 * File Name:HotSearchService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import com.zzj.db.dto.HotSearchTerm;

/**
 * <p><strong>类名: </strong></p>HotSearchService <br/>
 * <p><strong>功能说明: </strong></p>这个类是热搜词的Service接口，封装热搜词的Service实现类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月28日下午3:24:23 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface HotSearchService {
	/**
	 * hot_search_term表查询处理<br/>
	 * @param 无
	 * @return HotSearchTerm  hot_search_term表记录
	 */
	public HotSearchTerm selcetOldHotSearch();

	/**
	 * hot_search_term表逻辑删除处理<br/>
	 * @param 无
	 */
	void delOldHotSearch();

	/**
	 * hot_search_term表增加新记录处理<br/>
	 * @param hotSearchTerm 新增对象
	 */
	void newHotSearch(HotSearchTerm hotSearchTerm);
}

