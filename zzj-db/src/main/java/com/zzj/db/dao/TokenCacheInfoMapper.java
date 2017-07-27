/**
 * Project Name:zzj-db
 * File Name:ExpertInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.Map;

import com.zzj.db.dto.TokenCacheInfo;

/**
 * <p><strong>类名: </strong></p>TokenCacheInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>TokenCacheInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月14日下午11:34:30 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface TokenCacheInfoMapper {
	/**
	 * 根据主键 删除记录<br/>
	 * @param  String tokenId 主键
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String tokenId);
	/**
	 * 插入记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insert(TokenCacheInfo record);
	/**
	 * 插入记录<br/>
	 * @param  record 记录
	 * @return int 插入结果
	 */
    int insertSelective(TokenCacheInfo record);
	/**
	 * 根据主键查询表记录<br/>
	 * @param String tokenId 主键
	 * @return TokenCacheInfo 记录
	 */
    TokenCacheInfo selectByPrimaryKey(String tokenId);
	/**
	 * 根据条件查询表记录<br/>
	 * @param TokenCacheInfo record 条件
	 * @return TokenCacheInfo 记录
	 */
    TokenCacheInfo selectUserSig(TokenCacheInfo record);
	/**
	 * 根据条件更新中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(TokenCacheInfo record);
	/**
	 * 根据条件更新中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(TokenCacheInfo record);
    /**
     * 获取usersig
     * @param  Map<String, Object> maps 查询条件
     * @return String 查询结果
     */
    String selectCacheByUserIdAndType(Map<String, Object> maps);
}