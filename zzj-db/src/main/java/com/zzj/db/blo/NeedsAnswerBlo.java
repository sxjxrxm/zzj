/**
 * Project Name:zzj-db
 * File Name:NeedsAnswerBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.NeedsAnswerMapper;
import com.zzj.db.dto.NeedsAnswer;
import com.zzj.db.dto.NeedsAnswerKey;

/**
 * <p><strong>类名: </strong></p>NeedsAnswerBlo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>NeedsAnswerBlo业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日下午1:24:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class NeedsAnswerBlo {

	/**
	 * NeedsAnswer表操作Mapper
	 */
	@Autowired
	private NeedsAnswerMapper mapper;
	
	/**
	 * NeedsAnswer记录追加处理
	 * @param  NeedsAnswer record记录
	 * @return int 追加结果
	 */
	public int insert(NeedsAnswer record) {	
		return mapper.insertSelective(record);
	}
	
	/**
	 * 最大号查询处理
	 * @param  String needsCd 需求编号
	 * @return Integer 最大号
	 */
	public Integer selectMaxAnswerNo(String needsCd) {	
		return mapper.selectMaxAnswerNo(needsCd);
	}
	
	/**
	 * 根据查询条件查询表记录<br/>
	 * @param  Map<String, Object> queryMap 查询条件
	 * @return List<NeedsAnswer> 记录
	 */
	public List<NeedsAnswer> selectByNeedsCd(Map<String, Object> queryMap) {	
		return mapper.selectByNeedsCd(queryMap);
	}
	
	/**
	 * NeedsAnswer根据主键查询记录总数
	 * @param  Map<String, Object> queryMap 查询条件
	 * @return Integer 记录总数
	 */
	public Integer selectTotalCount(Map<String, Object> queryMap) {	
		return mapper.selectTotalCount(queryMap);
	}
	
	/**
	 * NeedsAnswer记录查询处理
	 * @param  NeedsAnswerKey key 主键
	 * @return Integer 执行结果
	 */
	public Integer delete(NeedsAnswerKey key) {	
		return mapper.deleteByPrimaryKey(key);
	}
	
	/**
	 * NeedsAnswer根据主键更新记录
	 * @param  NeedsAnswer record记录
	 * @return int 更新结果
	 */
	public int update(NeedsAnswer record) {	
		return mapper.updateByPrimaryKeySelective(record);
	}
}
