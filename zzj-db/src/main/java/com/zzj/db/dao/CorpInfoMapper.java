/**
 * Project Name:zzj-db
 * File Name:CorpInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.CorpInfo;
import com.zzj.db.model.EnterpriseVO;

/**
 * <p><strong>类名: </strong></p>CorpInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>CorpInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月05日下午14:19:30 <br/>
 * @author   李善瑞
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface CorpInfoMapper {
	
    /**
	 * 根据条件查询CorpInfo中指定记录<br/>
	 * @author ctdw 李善瑞
	 * @param  enterpriseVO 查询条件
	 * @return List<EnterpriseVO> 取得结果
	 */
	List<EnterpriseVO> selectByParament(EnterpriseVO enterpriseVO);
	
    /**
	 * 根据条件查询CorpInfo中指定记录的总数<br/>
	 * @author ctdw 李善瑞
	 * @param  enterpriseVO 查询条件
	 * @return Integer 取得结果
	 */
	Integer selectTotalCount(EnterpriseVO enterpriseVO);
    /**
	 * 根据条件查询CorpInfo中指定分页记录<br/>
	 * @author ctdw 李善瑞
	 * @param  enterpriseVO 查询条件
	 * @return List<EnterpriseVO> 取得结果
	 */
	List<EnterpriseVO> selectPagging(EnterpriseVO enterpriseVO);
    
	/**
	 * 根据主键删除CorpInfo中指定记录<br/>
	 * @param  userId 用户ID
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String userId);

	/**
	 * 插入指定记录<br/>
	 * @param  CorpInfo record记录
	 * @return int 插入结果
	 */
    int insert(CorpInfo record);

    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record CorpInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(CorpInfo record);

    /**
	 * 根据主键查询CorpInfo中指定记录<br/>
	 * @param  userId 用户ID
	 * @return EnterpriseVO 取得结果
	 */
    EnterpriseVO selectByPrimaryKey(String userId);

    /**
	 * 根据条件更新CorpInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(CorpInfo record);

    /**
	 * 根据主键更新CorpInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(CorpInfo record);
}