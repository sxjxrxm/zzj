/**
 * Project Name:zzj-db
 * File Name:VideoInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.VideoInfoMapper;
import com.zzj.db.dto.VideoInfo;
import com.zzj.db.model.SlideResultVO;
import com.zzj.db.model.VideoInfoVO;

/**
 * <p><strong>类名: </strong></p>VideoInfor业务数据库操作类<br/>
 * <p><strong>功能说明: </strong></p>VideoInfor业务数据相关操作处理.  <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午1:19:21 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class VideoInfoBlo {
	/**
	 * Video_Infor表操作Mapper
	 */
	@Autowired
	private VideoInfoMapper videoInfoMapper;
	/**
	 * 根据研究领域和主题查询对应视频<br/>
	 * @param  slideResultVO 轮播记录
	 * @return List<VideoInfo> 查询结果集
	 */
	public List<VideoInfo> slideEditSearch(SlideResultVO slideResultVO) {
		List<VideoInfo> videoInfos = videoInfoMapper.slideEditSearch(slideResultVO);
		return videoInfos;
	}
	/**
	 * 根据主键查询对应视频<br/>
	 * @param  videoCd 视频记录
	 * @return VideoInfoVO 查询结果
	 */
	public VideoInfoVO selectByPrimaryKey(String videoCd) {
		return videoInfoMapper.selectByPrimaryKey(videoCd);
	}
	
	/**
	 * VideoInfoVO记录取得处理
	 * @param VideoInfoVO record 检索条件
	 * @return List<VideoInfoVO>记录
	 */
	public List<VideoInfoVO> selectAll(VideoInfoVO record) {
		return videoInfoMapper.selectAll(record);
	}
	
	/**
	 * VideoInfoVO记录分页取得处理
	 * @param  record 检索条件
	 * @return List<VideoInfoVO>记录
	 */
	public List<VideoInfoVO> selectPagging(VideoInfoVO record) {
		return videoInfoMapper.selectPagging(record);
	}
	
	/**
	 * VideoInfoVO记录取得总件数
	 * @param VideoInfoVO record 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectTotalCount(VideoInfoVO record) {
		return videoInfoMapper.selectTotalCount(record);
	}
	
	/**
	 * 根据主键删除记录
	 * @param record 查询条件
	 * @return int 更新结果条目数
	 */
	public int updateByPrimaryKeySelective(VideoInfo record) {
		return videoInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 根据条件插入指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
	public int insertSelective(VideoInfoVO record) {
		return videoInfoMapper.insertSelective(record);
	}

}

