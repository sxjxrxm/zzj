/**
 * Project Name:zzj-db
 * File Name:UserOrderInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright ? 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.UserOrderInfo;
import com.zzj.db.model.IncomeJknVO;
import com.zzj.db.model.IncomeResultVO;
/**
 * <p><strong>类名: </strong></p>UserOrderInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>UserOrderInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月24日下午3:36:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface UserOrderInfoMapper {
	/**
	 * 根据主键删除UserOrderInfo中指定记录<br/>
	 * @param  String key
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(UserOrderInfo key);

    /**
   	 * 插入指定记录<br/>
   	 * @param  record UserOrderInfo记录
   	 * @return int 插入结果
   	 */
    int insert(UserOrderInfo record);

    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record UserOrderInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(UserOrderInfo record);

    /**
   	 * 根据主键查询UserOrderInfo中指定记录<br/>
   	 * @param  String key
   	 * @return UserOrderInfo 取得结果
   	 */
    UserOrderInfo selectByPrimaryKey(UserOrderInfo key);

    /**
   	 * 根据条件更新UserOrderInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKeySelective(UserOrderInfo record);

    /**
   	 * 根据主键更新UserOrderInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKey(UserOrderInfo record);
 
    /**
   	 * 根据条件取得当前页的数据<br/>
   	 * @param  IncomeJknVO record
   	 * @return List<IncomeResultVO> 全部数据
   	 */
    List<IncomeResultVO> selectIncomeByOption(IncomeJknVO record);
    
    /**
   	 * 根据条件取得总件数<br/>
   	 * @param  IncomeJknVO record
   	 * @return int 总件数
   	 */
    int selectIncomeTotalCount(IncomeJknVO record);
    
    /**
   	 * 根据条件取得全部数据<br/>
   	 * @param  IncomeJknVO record
   	 * @return List<IncomeResultVO> 全部数据
   	 */
    List<IncomeResultVO> selectAllIncome(IncomeJknVO record);
    
    /**
   	 * 根据条件取得当前页的数据<br/>
   	 * @param  IncomeJknVO record
   	 * @return List<IncomeResultVO> 全部数据
   	 */
    List<IncomeResultVO> selectDetailByOption(IncomeJknVO record);
    
    /**
   	 * 根据条件取得总件数<br/>
   	 * @param  IncomeJknVO record
   	 * @return int 总件数
   	 */
    int selectDetailTotalCount(IncomeJknVO record);
    
    /**
   	 * 根据条件取得全部数据<br/>
   	 * @param  IncomeJknVO record
   	 * @return List<IncomeResultVO> 全部数据
   	 */
    List<IncomeResultVO> selectAllDetail(IncomeJknVO record);
}