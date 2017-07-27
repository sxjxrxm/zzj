/**
 * Project Name:zzj-db
 * File Name:MstRankInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.MstRankInfo;

/**
 * <p><strong>类名: </strong></p>MstRankInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>MstRankInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月27日下午1:27:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface MstRankInfoMapper {
	/**
	 * 根据主键删除MstRankInfo中指定记录<br/>
	 * @param  rankCd 查询条件
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String rankCd);

    /**
	 * 插入指定记录<br/>
	 * @param  record MstRankInfo记录
	 * @return int 插入结果
	 */
    int insert(MstRankInfo record);

    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record MstRankInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(MstRankInfo record);

    /**
   	 * 根据主键查询MstRankInfo中指定记录<br/>
   	 * @param  rankCd 主键
   	 * @return MstRankInfo 取得结果
   	 */
    MstRankInfo selectByPrimaryKey(String rankCd);

    /**
	 * 根据条件更新MstRankInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(MstRankInfo record);

    /**
	 * 根据主键更新MstRankInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(MstRankInfo record);

    /**
	 * 专家编辑页面职称联想输入信息<br/>
	 * @param 无
	 * @return List<MstRankInfo> 查询结果
	 */
	List<MstRankInfo> selectAllRankName();

	/**
	 * 根据职称名称查找记录
	 * @param  rankName 职称名称
	 * @return int  记录数
	 */
	int selectByRankName(String rankName);

	/**
	 * 查找职称表中rankCd的最大值
	 * @param  无
	 * @return int rankCd的最大值
	 */
	int selectMaxRankCd();
}