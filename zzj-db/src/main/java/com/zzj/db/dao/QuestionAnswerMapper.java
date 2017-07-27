/**
 * Project Name:zzj-db
 * File Name:QuestionAnswerMapper.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;
import java.util.Map;

import com.zzj.db.dto.QuestionAnswer;
import com.zzj.db.dto.QuestionAnswerKey;
/**
 * <p><strong>类名: </strong></p>QuestionAnswer表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义QuestionAnswer表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月05日上午9:36:35 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface QuestionAnswerMapper {
	
	/**
	 * 根据主键 删除记录<br/>
	 * @param  QuestionAnswerKey key 主键
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(QuestionAnswerKey key);
	/**
	 * 插入记录<br/>
	 * @param QuestionAnswer record 记录
	 * @return int 插入结果
	 */
    int insert(QuestionAnswer record);
	/**
	 * 插入记录<br/>
	 * @param QuestionAnswer record 记录
	 * @return int 插入结果
	 */
    int insertSelective(QuestionAnswer record);
	/**
	 * 根据主键查询表记录（分页显示）<br/>
	 * @param  QuestionAnswerKey key 主键
	 * @return QuestionAnswer 记录
	 */
    QuestionAnswer selectByPrimaryKey(QuestionAnswerKey key);
	/**
	 * 根据查询条件查询表记录<br/>
	 * @param  Map<String, Object> queryMap 查询条件
	 * @return List<QuestionAnswer> 记录
	 */
    List<QuestionAnswer> selectByQuestionCd(Map<String, Object> queryMap);
	/**
	 * 最大号查询处理
	 * @param  String key E问编码
	 * @return Integer 最大号
	 */
    Integer selectMaxAnswerNo(String key);    
	/**
	 * 根据主键查询记录总数
	 * @param  Map<String, Object> queryMap 查询条件
	 * @return Integer 记录总数
	 */
    Integer selectTotalCount(Map<String, Object> queryMap);
	/**
	 * QuestionAnswer根据主键更新记录
	 * @param  QuestionAnswer record记录
	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(QuestionAnswer record);
	/**
	 * QuestionAnswer根据主键更新记录
	 * @param  QuestionAnswer record记录
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(QuestionAnswer record);
}