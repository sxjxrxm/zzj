/**
 * Project Name:zzj-db
 * File Name:LiveInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.LiveInfo;
import com.zzj.db.model.LiveInfoVO;
import com.zzj.db.model.SlideResultVO;

/**
 * <p><strong>类名: </strong></p>LiveInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>LiveInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日下午11:35:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface LiveInfoMapper {
	
	/**
	 * 根据主键删除Live_Info中指定记录<br/>
	 * @param  liveCd 主键
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String liveCd);
    
    /**
	 * 插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insert(LiveInfo record);
    
    /**
   	 * 有条件的插入指定记录<br/>
   	 * @param  record 记录
   	 * @return int 插入结果
   	 */
    int insertSelective(LiveInfo record);
    
    /**
	 * 根据主键查询Live_Info中指定记录<br/>
	 * @param  liveCd 主键
	 * @return LiveInfoVO 取得结果
	 */
    LiveInfoVO selectByPrimaryKey(String liveCd);
    
    /**
   	 * 根据条件更新Live_Info中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKeySelective(LiveInfo record);
    
    /**
   	 * 根据主键更新Live_Info中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKey(LiveInfo record);
    
    /**
	 * LiveInfoVO记录取得处理
	 * @param LiveInfoVO record 检索条件
	 * @author 任晓茂
	 * @return List<LiveInfoVO>记录
	 */
	List<LiveInfoVO> selectAll(LiveInfoVO record);
	
	/**
	 * LiveInfoVO记录分页取得处理
	 * @param  record 检索条件
	 * @author 任晓茂
	 * @return List<LiveInfoVO>记录
	 */
	List<LiveInfoVO> selectPagging(LiveInfoVO record);
	
	/**
	 * LiveInfoVO记录取得总件数
	 * @param LiveInfoVO record 检索条件
	 * @author 任晓茂
	 * @return Integer 总件数
	 */
	Integer selectTotalCount(LiveInfoVO record);
	/**
	 * 根据研究领域和主题查询对应直播<br/>
	 * @param  slideResultVO 记录
	 * @return List<LiveInfo> 查询结果
	 */
	List<LiveInfo> slideEditSearch(SlideResultVO slideResultVO);
}