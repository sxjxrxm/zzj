/**
 * Project Name:zzj-db
 * File Name:TokenCacheInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.TokenCacheInfoMapper;
import com.zzj.db.dto.TokenCacheInfo;

/**
 * <p><strong>类名: </strong></p>TokenCacheInfoBlo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>TokenCacheInfoBlo业务数据相关操作处理.  <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月20日下午1:36:01 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class TokenCacheInfoBlo {
	/**
	 * TokenCacheInfoMapper表操作Mapper
	 */
	@Autowired
	private TokenCacheInfoMapper mapper;

	/**
	 * 根据主键查询对应课堂<br/>
	 * @param  key 主键
	 * @return TokenCacheInfo 查询结果
	 */
	public TokenCacheInfo selectByPrimaryKey(String key) {
		return mapper.selectByPrimaryKey(key);
	}	
	/**
	 * 根据条件更新指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
	public int updateByPrimaryKeySelective(TokenCacheInfo record) {
		return mapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 根据条件插入指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
	public int insertSelective(TokenCacheInfo record) {
		return mapper.insertSelective(record);
	}
	/**
	 * 根据主键查询对应课堂<br/>
	 * @param  record 记录
	 * @return TokenCacheInfo 查询结果
	 */
	public TokenCacheInfo selectUserSig(TokenCacheInfo record) {
		return mapper.selectUserSig(record);
	}
	/**
     * 获取usersig
     * @param  Map<String, Object> maps 查询条件
     * @return String 查询结果
     */
    public String selectCacheByUserIdAndType(Map<String, Object> maps){
    	return mapper.selectCacheByUserIdAndType(maps);
    }
}

