/**
 * Project Name:zzj-db
 * File Name:HotSearchBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.HotSearchTermMapper;
import com.zzj.db.dto.HotSearchTerm;

/**
 * <p><strong>类名: </strong></p>HotSearchBlo <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装热搜词数据库操作类，调用热搜词数据库操作类的. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月28日下午3:41:13 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class HotSearchBlo {
	
	/**
	 * 热搜词业务数据库操作类
	 */
	@Autowired
	private HotSearchTermMapper hotSearchTermMapper;
	
	/**
	 * 查询原热搜词记录<br/>
	 * @author 李善瑞 
	 * @param  无
	 * @return HotSearchTerm 查询结果
	 */
	public HotSearchTerm selectByDelFlag() {
    	
    	return hotSearchTermMapper.selectByDelFlag();
	}
    
	/**
	 * 删除原热搜词记录<br/>
	 * @author 李善瑞 
	 * @param  无
	 * @return 无
	 */
	public void delByDelFlag() {
    	
    	hotSearchTermMapper.delByDelFlag();
	}
    
	/**
	 * 插入新热搜词记录<br/>
	 * @author 李善瑞 
	 * @param  HotSearchTerm hotSearchTerm记录
	 * @return 无
	 */
	public void setNewHotSearch(HotSearchTerm hotSearchTerm) {
    	
    	hotSearchTermMapper.addSelective(hotSearchTerm);
    	
	}
    
}

