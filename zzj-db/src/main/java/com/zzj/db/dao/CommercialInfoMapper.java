/**
 * Project Name:zzj-db
 * File Name:CommercialInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.CommercialInfo;
import com.zzj.db.model.SlideResultVO;
/**
 * <p><strong>类名: </strong></p>CommercialInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>CommercialInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月30日下午13:44:30 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface CommercialInfoMapper {
	/**
	 * 根据主键删除CommercialInfo中指定记录<br/>
	 * @param  commercialCd 查询条件
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String commercialCd);
    /**
	 * 插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insert(CommercialInfo record);
    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insertSelective(CommercialInfo record);
    /**
	 * 根据主键查询CommercialInfo中指定记录<br/>
	 * @param  commercialCd 查询条件
	 * @return CommercialInfo 取得结果
	 */
    CommercialInfo selectByPrimaryKey(String commercialCd);
    /**
	 * 根据条件更新CommercialInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(CommercialInfo record);
    /**
	 * 根据主键更新CommercialInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(CommercialInfo record);
    /**
	 * 根据研究领域和主题查询对应广告<br/>
	 * @param  slideResultVO 记录
	 * @return List<CommercialInfo> 查询结果
	 */
	List<CommercialInfo> slideEditSearch(SlideResultVO slideResultVO);
}