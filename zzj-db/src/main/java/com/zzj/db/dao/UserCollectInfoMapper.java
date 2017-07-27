/**
 * Project Name:zzj-db
 * File Name:UserCollectInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright ? 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

/**
 * <p><strong>类名: </strong></p>UserCollectInfoMapper表操作接口<br/>
 * <p><strong>功能说明: </strong></p>UserHandleInfoMapper表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日下午5:30:30 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
import com.zzj.db.dto.UserCollectInfo;
import com.zzj.db.dto.UserCollectInfoKey;
import com.zzj.db.model.ActionJknVO;
import com.zzj.db.model.ActionResultVO;
/**
 * <p><strong>类名: </strong></p>UserCollectInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>UserCollectInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月24日下午3:36:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface UserCollectInfoMapper {
	
	/**
	 * 根据主键删除UserCollectInfo中指定记录<br/>
	 * @param  key 查询条件
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(UserCollectInfoKey key);

    /**
   	 * 插入指定记录<br/>
   	 * @param  record UserCollectInfo记录
   	 * @return int 插入结果
   	 */
    int insert(UserCollectInfo record);

    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record UserCollectInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(UserCollectInfo record);

    /**
   	 * 根据主键查询UserCollectInfo中指定记录<br/>
   	 * @param  String key
   	 * @return UserCollectInfo 取得结果
   	 */
    UserCollectInfo selectByPrimaryKey(UserCollectInfoKey key);

    /**
   	 * 根据条件更新UserCollectInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKeySelective(UserCollectInfo record);

    /**
   	 * 根据主键更新UserCollectInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKey(UserCollectInfo record);
 
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