/**
 * Project Name:zzj-db
 * File Name:CourseInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.CourseInfoMapper;
import com.zzj.db.dto.CourseInfo;
import com.zzj.db.model.CourseInfoVO;
import com.zzj.db.model.SlideResultVO;

/**
 * <p><strong>类名: </strong></p>CourseInfo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>CourseInfo业务数据相关操作处理.  <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午1:36:01 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class CourseInfoBlo {
	/**
	 * CourseInfo表操作Mapper
	 */
	@Autowired
	private CourseInfoMapper mapper;
	/**
	 * 根据研究领域和主题查询对应课堂<br/>
	 * @param  slideResultVO 轮播记录
	 * @return List<CourseInfo> 查询结果集
	 */
	public List<CourseInfo> slideEditSearch(SlideResultVO slideResultVO) {
		List<CourseInfo> courseInfos = mapper.slideEditSearch(slideResultVO);
		return courseInfos;
	}
	/**
	 * 根据主键查询对应课堂<br/>
	 * @param  key 主键
	 * @return CourseInfoVO 查询结果
	 */
	public CourseInfoVO selectByPrimaryKey(String key) {
		return mapper.selectByPrimaryKey(key);
	}	
	/**
	 * 记录取得总件数
	 * @param Map<String, Object> record 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectTotalCount(Map<String, Object> record) {
		return mapper.selectTotalCount(record);
	}
	
	/**
	 * 记录取得处理
	 * @param Map<String, Object> record 检索条件
	 * @return List<CourseInfoVO>记录
	 */
	public List<CourseInfoVO> selectAll(Map<String, Object> record) {
		return mapper.selectAll(record);
	}
	
	/**
	 * 记录分页取得处理
	 * @param  Map<String, Object> record 检索条件
	 * @return List<CourseInfoVO>记录
	 */
	public List<CourseInfoVO> selectPagging(Map<String, Object> record) {
		return mapper.selectPagging(record);
	}
	/**
	 * 根据条件更新指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
	public int updateByPrimaryKeySelective(CourseInfo record) {
		return mapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 根据条件插入指定记录<br/>
	 * @param  record 插入记录对应
	 * @return int 插入结果
	 */
	public int insertSelective(CourseInfo record) {
		return mapper.insertSelective(record);
	}
	/**
     * 根据用户id和聊天室id查找是否是主讲人  是返回1  不是返回null
     * @param  Map<String, Object> maps 检索条件
     * @return Integer 检索结果
     */
    public Integer selectSpeakerByUserIdAndRoomId(Map<String, Object> maps){
    	return mapper.selectSpeakerByUserIdAndRoomId(maps);
    }
}

