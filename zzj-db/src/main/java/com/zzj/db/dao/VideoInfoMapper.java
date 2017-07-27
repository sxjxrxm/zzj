/**
 * Project Name:zzj-db
 * File Name:VideoInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.VideoInfo;
import com.zzj.db.model.SlideResultVO;
import com.zzj.db.model.VideoInfoVO;
/**
 * <p><strong>类名: </strong></p>Video_Info表操作接口<br/>
 * <p><strong>功能说明: </strong></p>Video_Info表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午11:35:30 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface VideoInfoMapper {
	/**
	 * 根据主键删除Video_Info中指定记录<br/>
	 * @param  videoCd 主键
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String videoCd);
    /**
	 * 插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insert(VideoInfo record);
    /**
   	 * 有条件的插入指定记录<br/>
   	 * @param  record 记录
   	 * @return int 插入结果
   	 */
    int insertSelective(VideoInfo record);
    /**
	 * 根据主键查询Video_Info中指定记录<br/>
	 * @param  videoCd 主键
	 * @return VideoInfoVO 取得结果
	 */
    VideoInfoVO selectByPrimaryKey(String videoCd);
    /**
	 * 根据条件更新Video_Info中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(VideoInfo record);
    /**
   	 * 根据主键更新Video_Info中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKey(VideoInfo record);
    /**
	 * 根据研究领域和主题查询对应视频<br/>
	 * @param  slideResultVO 记录
	 * @return List<VideoInfo> 查询结果
	 */
	List<VideoInfo> slideEditSearch(SlideResultVO slideResultVO);
	
	/**
	 * VideoInfoVO记录取得处理
	 * @param VideoInfoVO record 检索条件
	 * @author 任晓茂
	 * @return List<VideoInfoVO>记录
	 */
	List<VideoInfoVO> selectAll(VideoInfoVO record);
	
	/**
	 * VideoInfoVO记录分页取得处理
	 * @param  record 检索条件
	 * @author 任晓茂
	 * @return List<VideoInfoVO>记录
	 */
	List<VideoInfoVO> selectPagging(VideoInfoVO record);
	
	/**
	 * VideoInfoVO记录取得总件数
	 * @param VideoInfoVO record 检索条件
	 * @author 任晓茂
	 * @return Integer 总件数
	 */
	Integer selectTotalCount(VideoInfoVO record);
	
}