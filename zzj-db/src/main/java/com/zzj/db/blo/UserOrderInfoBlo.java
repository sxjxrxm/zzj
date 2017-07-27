/**
 * Project Name:zzj-db
 * File Name:UserOrderInfoBlo.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.UserOrderInfoMapper;
import com.zzj.db.model.IncomeJknVO;
import com.zzj.db.model.IncomeResultVO;

/**
 * <p><strong>类名: </strong></p>UserOrderInfoBlo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>UserOrderInfoBlo业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月14日下午1:24:30 <br/>
 * @author   孙宗宁
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class UserOrderInfoBlo {

	/**
	 * UserOrderInfo表操作Mapper
	 */
	@Autowired
	private UserOrderInfoMapper mapper;

	/**
	 * 行为一览中IncomeResultVO记录取得处理
	 * @param IncomeJknVO record 检索条件
	 * @return List<IncomeResultVO>记录
	 */
	public List<IncomeResultVO> selectAllIncome(IncomeJknVO record) {
		return mapper.selectAllIncome(record);
	}
	
	/**
	 * 行为一览中IncomeResultVO记录分页取得处理
	 * @param  record 检索条件
	 * @return List<IncomeResultVO>记录
	 */
	public List<IncomeResultVO> selectIncomeByOption(IncomeJknVO record) {
		return mapper.selectIncomeByOption(record);
	}
	
	/**
	 * 行为一览中IncomeResultVO记录取得总件数
	 * @param IncomeJknVO record 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectIncomeTotalCount(IncomeJknVO record) {
		return mapper.selectIncomeTotalCount(record);
	}
	
	/**
	 * 行为一览中IncomeResultVO记录取得处理
	 * @param IncomeJknVO record 检索条件
	 * @return List<IncomeResultVO>记录
	 */
	public List<IncomeResultVO> selectAllDetail(IncomeJknVO record) {
		return mapper.selectAllDetail(record);
	}
	
	/**
	 * 行为一览中IncomeResultVO记录分页取得处理
	 * @param  record 检索条件
	 * @return List<IncomeResultVO>记录
	 */
	public List<IncomeResultVO> selectDetailByOption(IncomeJknVO record) {
		return mapper.selectDetailByOption(record);
	}
	
	/**
	 * 行为一览中IncomeResultVO记录取得总件数
	 * @param IncomeJknVO record 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectDetailTotalCount(IncomeJknVO record) {
		return mapper.selectDetailTotalCount(record);
	}
}