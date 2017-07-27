/**
 * Project Name:zzj-db
 * File Name:QuestionInfoMapper.java
 * Package Name:com.zzj.db.dto
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;

import com.zzj.db.dto.QuestionInfo;
import com.zzj.db.model.QuestionVO;
import com.zzj.db.model.SlideResultVO;
/**
 * <p><strong>类名: </strong></p>QuestionInfo表对象定义类 <br/>
 * <p><strong>功能说明: </strong></p>定义QuestionInfo表相关字段<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月05日上午9:36:35 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface QuestionInfoMapper {
	
	/**
	 * 根据主键删除E问表中指定记录<br/>
	 * @param  userId 用户ID
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String questionCd);

    /**
   	 * 插入指定记录<br/>
   	 * @param  record QuestionInfo记录
   	 * @return int 插入结果
   	 */
    int insert(QuestionInfo record);

    /**
	 * 插入指定记录<br/>
	 * @param  record QuestionInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(QuestionInfo record);

    /**
	 * 根据主键查询E问表指定记录<br/>
	 * @param  questionCd 用户ID
	 * @return QuestionVO 取得结果
	 */
    QuestionVO selectByPrimaryKey(String questionCd);

    /**
   	 * 根据条件更新E问表中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKeySelective(QuestionInfo record);

    /**
	 * 根据主键更新E问表中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
    int updateByPrimaryKey(QuestionInfo record);
    
    /**
     * E问筛选
     * @param  info 筛选条件
     * @return List<QuestionVO> 结果
     */
	List<QuestionVO> searchQuestionList(QuestionVO info);
    /**
     * 根据条件查询总件数
     * @param  record 筛选对象
     * @return Integer 总件数
     */
    Integer selectTotalCount(QuestionVO record);
    /**
     * E问筛选
     * @param  record 筛选条件
     * @return List<QuestionVO> 结果
     */
    List<QuestionVO> selectPagging(QuestionVO record);
	
    /**
     * E问修改
     * @param  info 筛修改对象
     * @return 无
     */
	void update(QuestionInfo info);
	
	/**
	 * 根据研究领域和主题查询对应e问<br/>
	 * @param  slideResultVO 记录
	 * @return List<QuestionInfo> 查询结果
	 */
	List<QuestionInfo> slideEditSearch(SlideResultVO slideResultVO);
}