/**
 * Project Name:zzj-db
 * File Name:UserLikeInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright ? 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.UserLikeInfo;
import com.zzj.db.dto.UserLikeInfoKey;
import com.zzj.db.model.ActionJknVO;
import com.zzj.db.model.ActionResultVO;
/**
 * <p><strong>类名: </strong></p>UserLikeInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>UserLikeInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月24日下午3:36:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface UserLikeInfoMapper {
	
	/**
	 * 根据主键删除UserLikeInfo中指定记录<br/>
	 * @param  String key
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(UserLikeInfoKey key);

    /**
   	 * 插入指定记录<br/>
   	 * @param  record UserLikeInfo记录
   	 * @return int 插入结果
   	 */
    int insert(UserLikeInfo record);

    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record UserLikeInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(UserLikeInfo record);

    /**
   	 * 根据主键查询UserLikeInfo中指定记录<br/>
   	 * @param  String key
   	 * @return UserLikeInfo 取得结果
   	 */
    UserLikeInfo selectByPrimaryKey(UserLikeInfoKey key);

    /**
   	 * 根据条件更新UserLikeInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKeySelective(UserLikeInfo record);

    /**
   	 * 根据主键更新UserLikeInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKey(UserLikeInfo record);
 
    /**
   	 * 根据条件取得当前页的数据<br/>
   	 * @param  ActionJknVO record
   	 * @return List<ActionResultVO> 全部数据
   	 */
    List<ActionResultVO> selectByOption(ActionJknVO record);
    
    /**
   	 * 根据条件取得总件数<br/>
   	 * @param  ActionJknVO record
   	 * @return int 总件数
   	 */
    int selectTotalCount(ActionJknVO record);
    
    /**
   	 * 根据条件取得全部数据<br/>
   	 * @param  ActionJknVO record
   	 * @return List<ActionResultVO> 全部数据
   	 */
    List<ActionResultVO> selectAll(ActionJknVO record);
}