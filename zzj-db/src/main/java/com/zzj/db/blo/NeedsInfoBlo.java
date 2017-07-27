/**
 * Project Name:zzj-db
 * File Name:NeedsInfoBlo.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.NeedsInfoMapper;
import com.zzj.db.dto.NeedsInfo;
import com.zzj.db.model.NeedsInfoVO;
import com.zzj.db.model.SlideResultVO;

/**
 * <p><strong>类名: </strong></p>NeedsInfoBlo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>NeedsInfoBlo业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日下午1:24:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class NeedsInfoBlo {

	/**
	 * NeedsInfo表操作Mapper
	 */
	@Autowired
	private NeedsInfoMapper mapper;

	/**
	 * NeedsInfoVO记录取得总件数
	 * @param NeedsInfoVO record 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectTotalCount(NeedsInfoVO record) {
		return mapper.selectTotalCount(record);
	}
	
	/**
	 * NeedsInfoVO记录取得处理
	 * @param NeedsInfoVO record 检索条件
	 * @return List<NeedsInfoVO>记录
	 */
	public List<NeedsInfoVO> selectAll(NeedsInfoVO record) {
		return mapper.selectAll(record);
	}
	
	/**
	 * NeedsInfoVO记录分页取得处理
	 * @param  NeedsInfoVO record 检索条件
	 * @return List<NeedsInfoVO>记录
	 */
	public List<NeedsInfoVO> selectPagging(NeedsInfoVO record) {
		return mapper.selectPagging(record);
	}
	
	/**
	 * 根据条件更新中指定记录<br/>
	 * @param  NeedsInfo record 更新记录对应
	 * @return int 更新结果
	 */
	public int updateByPrimaryKeySelective(NeedsInfo record) {
		return mapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 根据主键查询表记录<br/>
	 * @param  String needsCd 主键
	 * @return NeedsInfoVO 记录
	 */
	public NeedsInfoVO selectByPrimaryKey(String needsCd) {
		return mapper.selectByPrimaryKey(needsCd);
	}
	/**
	 * 根据研究领域和主题查询对应需求<br/>
	 * @param  SlideResultVO slideResultVO 记录
	 * @return List<NeedsInfo>记录
	 */
	public List<NeedsInfo> slideEditSearch(SlideResultVO slideResultVO) {
		List<NeedsInfo> needsInfos = mapper.slideEditSearch(slideResultVO);
		return needsInfos;
	}
}

