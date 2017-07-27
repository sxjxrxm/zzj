/**
 * Project Name:zzj-db
 * File Name:HotSearchTermMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import com.zzj.db.dto.HotSearchTerm;

/**
 * <p><strong>类名: </strong></p>HotSearchTerm表操作接口<br/>
 * <p><strong>功能说明: </strong></p>HotSearchTerm表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月28日下午14:19:30 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface HotSearchTermMapper {
	
	/**
	 * 插入指定记录<br/>
	 * @param  HotSearchTerm record记录
	 * @return int 插入结果
	 */
    int insert(HotSearchTerm record);

	/**
	 * 有条件的插入指定记录<br/>
	 * @param  HotSearchTerm record记录
	 * @return int 插入结果
	 */
    int insertSelective(HotSearchTerm record);
    
	/**
	 * 查询原热搜词记录<br/>
	 * @author 李善瑞 
	 * @param  无
	 * @return HotSearchTerm 查询结果
	 */
    HotSearchTerm selectByDelFlag();
    
	/**
	 * 逻辑删除原热搜词记录<br/>
	 * @author 李善瑞 
	 * @param  无
	 * @return 无
	 */
    void delByDelFlag();
    
	/**
	 * 有条件的插入指定记录<br/>
	 * @author 李善瑞 
	 * @param  HotSearchTerm record记录
	 * @return 无
	 */
    void addSelective(HotSearchTerm record);
}