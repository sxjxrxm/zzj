/**
 * Project Name:zzj-db
 * File Name:ExpertInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao; 
import java.util.List;
import java.util.Map;

import com.zzj.db.dto.ExpertInfo;
import com.zzj.db.model.ExpertInfoEditVO;
import com.zzj.db.model.ExpertInfoListVO;
import com.zzj.db.model.SlideResultVO;

/**
 * <p><strong>类名: </strong></p>ExpertInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>ExpertInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日下午6:36:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface ExpertInfoMapper {
	
	/**
	 * 根据主键删除ExpertInfo中指定记录<br/>
	 * @param  expertId 专家ID
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String expertId);

    /**
	 * 插入指定记录<br/>
	 * @param  record ExpertInfo记录
	 * @return int 插入结果
	 */
    int insert(ExpertInfo record);

    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record ExpertInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(ExpertInfo record);

    /**
	 * 根据主键查询ExpertInfo中指定记录<br/>
	 * 用于专家编辑/添加页面<br/>
	 * @param  expertId 专家ID
	 * @return ExpertInfoEditVO 取得结果
	 */
    ExpertInfoEditVO selectByPrimaryKey(String expertId);
    
    /**
	 * 根据条件查询ExpertInfo中指定记录<br/>
	 * @param  Map<String,Object> map 查询条件
	 * @return List<ExpertInfoListVO> 取得结果列表
	 */
    List<ExpertInfoListVO> selectSelectiveByPage(Map<String,Object> map);
    
    /**
     * 获取所有可用专家总数，分页使用<br/>
     * @param  Map<String,Object> map 查询条件
     * @return int  专家记录总条数
     */
    int selectTotalCount(Map<String,Object> map);
    
    /**
	 * 根据条件更新ExpertInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(ExpertInfo record);

    /**
	 * 根据主键更新ExpertInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(ExpertInfo record);
    
    /**
	 * 根据研究领域和主题查询对应专家<br/>
	 * @param  slideResultVO 记录
	 * @return List<ExpertInfo> 查询条件
	 */
    List<ExpertInfo> slideEditSearch(SlideResultVO slideResultVO);

    /**
	 * 根据专家姓名查找对应专家id<br/>
	 * @param  expertName 专家姓名
	 * @return List<String> 查询结果
	 */
	List<String> selectIdByExpertName(String expertName);

	/**
	 * 取得全部专家记录（根据专家一览页面的检索条件）
	 * @param Map<String, Object> map 查询参数
	 * @return List<ExpertInfoListVO> 专家列表
	 */
	List<ExpertInfoListVO> selectAll(Map<String, Object> map);

	/**
	 * 知识编辑页面专家姓名联想输入信息<br/>
	 * @param 无
	 * @return List<String> 查询结果
	 */
	List<String> selectAllExpertName();

	/**
	 * 根据专家id获得专家姓名，获取不到则直接返回
	 * @param  expertId 专家id
	 * @return String  专家姓名
	 */
	String selectNameByExpertId(String expertId);
	/**
	 * 查询是否是专家 是返回1 不是返回null
	 * @param  Map<String, Object> map 查询条件
	 * @return Integer 查询结果
	 */
	Integer selectIsExpertById(Map<String, Object> maps);
	
	/**
	 * 取得专家相关信息
	 * @param  expertId 专家ID
	 * @return ExpertInfoEditVO 专家信息
	 */
	ExpertInfoEditVO selectExpertInfo(String expertId);
}