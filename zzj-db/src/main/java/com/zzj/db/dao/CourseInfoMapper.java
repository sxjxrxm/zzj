/**
 * Project Name:zzj-db
 * File Name:CourseInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;
import java.util.Map;

import com.zzj.db.dto.CourseInfo;
import com.zzj.db.model.CourseInfoVO;
import com.zzj.db.model.SlideResultVO;
/**
 * <p><strong>类名: </strong></p>Course_Info表操作接口<br/>
 * <p><strong>功能说明: </strong></p>Course_Info表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午13:33:30 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface CourseInfoMapper {
	/**
	 * 根据主键删除Course_Info中指定记录<br/>
	 * @param  courseCd 主键
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String courseCd);
    /**
   	 * 插入指定记录<br/>
   	 * @param  record 记录
   	 * @return int 插入结果
   	 */
    int insert(CourseInfo record);
    /**
   	 * 有条件的插入指定记录<br/>
   	 * @param  record 记录
   	 * @return int 插入结果
   	 */
    int insertSelective(CourseInfo record);
    /**
	 * 根据条件更新Course_Info中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(CourseInfo record);
    /**
   	 * 根据主键更新Course_Info中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKey(CourseInfo record);
    /**
	 * 根据研究领域和主题查询对应课堂<br/>
	 * @param  slideResultVO 记录
	 * @return List<CourseInfo> 查询结果
	 */
	List<CourseInfo> slideEditSearch(SlideResultVO slideResultVO);
	/**
	 * 记录取得总件数
	 * @param Map<String, Object> record 检索条件
	 * @return Integer 总件数
	 */
    Integer selectTotalCount(Map<String, Object> record);
	/**
	 * 记录取得处理
	 * @param Map<String, Object> record 检索条件
	 * @return List<CourseInfoVO>记录
	 */
    List<CourseInfoVO> selectAll(Map<String, Object> record);
	/**
	 * 记录分页取得处理
	 * @param Map<String, Object>  record 检索条件
	 * @return List<CourseInfoVO>记录
	 */
    List<CourseInfoVO> selectPagging(Map<String, Object> record);
    /**
	 * 根据主键查询Course_Info中指定记录<br/>
	 * @param  courseCd 主键
	 * @return CourseInfoVO 取得结果
	 */
    CourseInfoVO selectByPrimaryKey(String courseCd);
    /**
     * 根据用户id和聊天室id查找是否是主讲人  是返回1  不是返回null
     * @param  Map<String, Object> maps 查询条件
     * @return Integer 查询结果
     */
    Integer selectSpeakerByUserIdAndRoomId(Map<String, Object> maps);
}