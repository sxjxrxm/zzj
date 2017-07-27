/**
 * Project Name:zzj-db
 * File Name:UserHandleInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

/**
 * <p><strong>类名: </strong></p>UserHandleInfoMapper表操作接口<br/>
 * <p><strong>功能说明: </strong></p>UserHandleInfoMapper表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:36:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
import com.zzj.db.dto.UserHandleInfo;
import com.zzj.db.model.ActionJknVO;
import com.zzj.db.model.ActionResultVO;
/**
 * <p><strong>类名: </strong></p>UserHandleInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>UserHandleInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月24日下午3:36:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface UserHandleInfoMapper {
	
	/**
	 * 根据主键删除UserHandleInfo中指定记录<br/>
	 * @param  String topicCd
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String topicCd);

    /**
   	 * 插入指定记录<br/>
   	 * @param  record UserHandleInfo记录
   	 * @return int 插入结果
   	 */
    int insert(UserHandleInfo record);

    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record UserHandleInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(UserHandleInfo record);

    /**
   	 * 根据主键查询UserHandleInfo中指定记录<br/>
   	 * @param  String topicCd
   	 * @return UserHandleInfo 取得结果
   	 */
    UserHandleInfo selectByPrimaryKey(String topicCd);

    /**
   	 * 根据条件更新UserHandleInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKeySelective(UserHandleInfo record);

    /**
   	 * 根据主键更新UserHandleInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKey(UserHandleInfo record);
    
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