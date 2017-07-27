/**
 * Project Name:zzj-db
 * File Name:MstCodeInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstCodeInfoKey;

/**
 * <p><strong>类名: </strong></p>MstCodeInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>MstCodeInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月17日下午1:56:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface MstCodeInfoMapper {
	
	/**
	 * 根据主键删除MstCodeInfo中指定记录<br/>
	 * @param  key 联合主键
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(MstCodeInfoKey key);

    /**
	 * 插入指定记录<br/>
	 * @param  record MstCodeInfo记录
	 * @return int 插入结果
	 */
    int insert(MstCodeInfo record);

    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record MstCodeInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(MstCodeInfo record);

    /**
	 * 查询MstCodeInfo中所有可用记录<br/>
	 * @param 无
	 * @return MstCodeInfo 取得结果
	 */
    List<MstCodeInfo> selectAll();
    
    /**
	 * 根据主键查询MstCodeInfo中指定记录<br/>
	 * @param  key 联合主键
	 * @return MstCodeInfo 取得结果
	 */
    MstCodeInfo selectByPrimaryKey(MstCodeInfoKey key);

    /**
	 * 根据条件更新MstCodeInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(MstCodeInfo record);

    /**
	 * 根据主键更新MstCodeInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(MstCodeInfo record);
    /**
  	 * 根据条件查询对用Mst_Code_Info记录，基础信息一览界面使用
  	 * @param  code Mst_Code_Info记录
  	 * @return List<MstCodeInfo> 查询到的Mst_Code_Info集
  	 * @author 刘研
  	 */
    List<MstCodeInfo> selectSelective(MstCodeInfo code);
    /**
  	 * 查询全部机能
  	 * @param 无
  	 * @return List<MstCodeInfo> 查询到的Mst_Code_Info集
  	 * @author 刘研
  	 */
	List<MstCodeInfo> getAllBusis();
	/**
	 * 查询系统配置表中拒绝理由对应code值的最大值<br/>
	 * @param  无
	 * @return int 更新结果
	 */
	int selectMaxRefuseCode();
}