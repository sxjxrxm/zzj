/**
 * Project Name:zzj-db
 * File Name:LiveInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.LiveInfoMapper;
import com.zzj.db.dto.LiveInfo;
import com.zzj.db.model.LiveInfoVO;
import com.zzj.db.model.SlideResultVO;

/**
 * <p><strong>类名: </strong></p>LiveInfo业务数据库操作类<br/>
 * <p><strong>功能说明: </strong></p>LiveInfo业务数据相关操作处理.  <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日下午1:19:21 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class LiveInfoBlo {
	/**
	 * LiveInfor表操作Mapper
	 */
	@Autowired
	private LiveInfoMapper liveInfoMapper;
	/**
	 * 根据主键查询对应视频<br/>
	 * @param  topicCd 主键
	 * @return LiveInfoVO 查询结果集
	 */
	public LiveInfoVO selectByPrimaryKey(String topicCd) {
		return liveInfoMapper.selectByPrimaryKey(topicCd);
	}
	
	/**
	 * LiveInfoVO记录取得处理
	 * @param LiveInfoVO record 检索条件
	 * @return List<LiveInfoVO>记录
	 */
	public List<LiveInfoVO> selectAll(LiveInfoVO record) {
		return liveInfoMapper.selectAll(record);
	}
	
	/**
	 * LiveInfoVO记录分页取得处理
	 * @param  record 检索条件
	 * @return List<LiveInfoVO>记录
	 */
	public List<LiveInfoVO> selectPagging(LiveInfoVO record) {
		return liveInfoMapper.selectPagging(record);
	}
	
	/**
	 * LiveInfoVO记录取得总件数
	 * @param LiveInfoVO record 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectTotalCount(LiveInfoVO record) {
		return liveInfoMapper.selectTotalCount(record);
	}

	/**
	 * 根据主键删除记录
	 * @param record 检索条件
	 * @return int 更新结果条目数
	 */
	public int updateByPrimaryKeySelective(LiveInfo record) {
		return liveInfoMapper.updateByPrimaryKeySelective(record);
	}

	 /**
   	 * 有条件的插入指定记录<br/>
   	 * @param  record 检索条件
   	 * @return int 插入结果
   	 */
	public int insertSelective(LiveInfoVO record) {
		return liveInfoMapper.insertSelective(record);
	}
	/**
	 * 根据研究领域和主题查询对应直播<br/>
	 * @param  slideResultVO 检索条件
	 * @return List<LiveInfo>记录
	 */
	public List<LiveInfo> slideEditSearch(SlideResultVO slideResultVO) {
		return liveInfoMapper.slideEditSearch(slideResultVO);
	}

}

