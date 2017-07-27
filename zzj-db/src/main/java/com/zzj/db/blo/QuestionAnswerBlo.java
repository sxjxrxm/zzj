/**
 * Project Name:zzj-db
 * File Name:QuestionAnswerBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.QuestionAnswerMapper;
import com.zzj.db.dto.QuestionAnswer;
import com.zzj.db.dto.QuestionAnswerKey;

/**
 * <p><strong>类名: </strong></p>QuestionAnswerBlo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>QuestionAnswerBlo业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年15月05日下午1:24:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class QuestionAnswerBlo {

	/**
	 * QuestionAnswer表操作Mapper
	 */
	@Autowired
	private QuestionAnswerMapper mapper;
	
	/**
	 * 插入记录<br/>
	 * @param QuestionAnswer record 记录
	 * @return int 插入结果
	 */
	public int insert(QuestionAnswer record) {	
		return mapper.insertSelective(record);
	}
	
	/**
	 * 最大号查询处理
	 * @param  String QuestionCd E问编码
	 * @return int 最大号
	 */
	public Integer selectMaxAnswerNo(String QuestionCd) {	
		return mapper.selectMaxAnswerNo(QuestionCd);
	}
	
	/**
	 * 根据查询条件查询表记录<br/>
	 * @param  Map<String, Object> queryMap 查询条件
	 * @return List<QuestionAnswer> 记录
	 */
	public List<QuestionAnswer> selectByQuestionCd(Map<String, Object> queryMap) {	
		return mapper.selectByQuestionCd(queryMap);
	}
	
	/**
	 * 根据主键查询记录总数
	 * @param  Map<String, Object> queryMap 查询条件
	 * @return Integer 记录总数
	 */
	public Integer selectTotalCount(Map<String, Object> queryMap) {	
		return mapper.selectTotalCount(queryMap);
	}
	
	/**
	 * 根据主键 删除记录<br/>
	 * @param  QuestionAnswerKey key 主键
	 * @return int 删除结果
	 */
	public Integer delete(QuestionAnswerKey key) {	
		return mapper.deleteByPrimaryKey(key);
	}
	
	/**
	 * QuestionAnswer根据主键更新记录
	 * @param  QuestionAnswer record记录
	 * @return int 更新结果
	 */
	public int update(QuestionAnswer record) {	
		return mapper.updateByPrimaryKeySelective(record);
	}
}
