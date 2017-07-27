/**
 * Project Name:zzj-db
 * File Name:RecommendInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;
import java.util.Map;

import com.zzj.db.dto.RecommendInfo;
import com.zzj.db.dto.RecommendInfoKey;

/**
 * <p><strong>类名: </strong></p>RecommendInfoMapper表操作接口<br/>
 * <p><strong>功能说明: </strong></p>RecommendInfoMapper表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:36:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface RecommendInfoMapper {
	
	/**
	 * 根据主键删除RecommendInfo中指定记录<br/>
	 * @param  key 联合主键
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(RecommendInfoKey key);

    /**
	 * 插入指定记录<br/>
	 * @param  record RecommendInfo记录
	 * @return int 插入结果
	 */
    int insert(RecommendInfo record);

    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record RecommendInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(RecommendInfo record);

    /**
	 * 根据主键查询RecommendInfo中指定记录<br/>
	 * @param  key 联合主键
	 * @return RecommendInfo 取得结果
	 */
    RecommendInfo selectByPrimaryKey(RecommendInfoKey key);

    /**
	 * 根据条件更新RecommendInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(RecommendInfo record);

    /**
	 * 根据主键更新RecommendInfo中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(RecommendInfo record);
    
    /**
     * 根据业务分类和主题编号获得该编号对应的推荐置顶消息。<br/>
     * @param  map 业务分类和主题编号
     * @return List<RecommendInfoKey>  该编号对应的推荐置顶消息
     */
    List<RecommendInfoKey> selectByTypeAndCode(Map<String, String> map);

    /**
     * 根据业务分类和主题编号获得该编号对应的推荐语。<br/>
     * @param  map 业务分类和主题编号
     * @return String  推荐语
     */
	String findRecommendMsgByTypeAndCode(Map<String, String> map);

    /**
     * 推荐语。<br/>
     * @param  recommendInfo 查询条件
     * @return String  推荐语
     */
	String selectRecommendMsg(RecommendInfo recommendInfo);
	
	/**
	 * 根据专家id在表RecommendInfo删除记录
	 * @param  recommendInfo 查询条件
	 * @return 更新结果
	 */
	int updateByTypeAndCode(RecommendInfo recommendInfo);

	/**
	 * 根据机能和推荐置顶查询RecommendInfo中指定记录<br/>
	 * @param  recommendInfo 查询记录
	 * @return List<RecommendInfo> 查询结果集
	 */
	List<RecommendInfo> selectByRecommendKbnAndBusi(RecommendInfo recommendInfo);

	/**
	 * 根据机能和推荐置顶查询RecommendInfo中指定记录<br/>
	 * @param  recommendInfo 查询记录
	 * @return Integer 查询件数
	 */
	Integer selectCount(RecommendInfo recommendInfo);
}