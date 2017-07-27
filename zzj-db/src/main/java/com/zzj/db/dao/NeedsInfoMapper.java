/**
 * Project Name:zzj-db
 * File Name:NeedsInfoMapper.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.NeedsInfo;
import com.zzj.db.model.NeedsInfoVO;
import com.zzj.db.model.SlideResultVO;
/**
 * <p><strong>类名: </strong></p>NeedsInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义NeedsInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日上午9:36:35 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface NeedsInfoMapper {
	/**
	 * 根据主键 删除记录<br/>
	 * @param  String needsCd 主键
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String needsCd);
	/**
	 * 插入记录<br/>
	 * @param  NeedsInfo record 记录
	 * @return int 插入结果
	 */
    int insert(NeedsInfo record);
	/**
	 * 插入记录<br/>
	 * @param  NeedsInfo record 记录
	 * @return int 插入结果
	 */
    int insertSelective(NeedsInfo record);
	/**
	 * 根据主键查询表记录<br/>
	 * @param  needsCd 主键
	 * @return NeedsInfoVO 记录
	 */
    NeedsInfoVO selectByPrimaryKey(String needsCd);
	/**
	 * NeedsInfoVO记录取得总件数
	 * @param NeedsInfoVO record 检索条件
	 * @return Integer 总件数
	 */
    Integer selectTotalCount(NeedsInfoVO record);
	/**
	 * NeedsInfoVO记录取得处理
	 * @param NeedsInfoVO record 检索条件
	 * @return List<NeedsInfoVO>记录
	 */
    List<NeedsInfoVO> selectAll(NeedsInfoVO record);
	/**
	 * NeedsInfoVO记录分页取得处理
	 * @param  record 检索条件
	 * @return List<NeedsInfoVO>记录
	 */
    List<NeedsInfoVO> selectPagging(NeedsInfoVO record);
	/**
	 * 根据条件更新中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(NeedsInfo record);
	/**
	 * 根据条件更新中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(NeedsInfo record);
    /**
	 * 根据研究领域和主题查询对应需求<br/>
	 * @param  slideResultVO 记录
	 * @return List<NeedsInfo>记录
	 */
	List<NeedsInfo> slideEditSearch(SlideResultVO slideResultVO);
}