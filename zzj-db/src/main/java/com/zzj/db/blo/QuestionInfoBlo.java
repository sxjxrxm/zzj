/**
 * Project Name:zzj-db
 * File Name:QuestionInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.QuestionInfoMapper;
import com.zzj.db.dto.QuestionInfo;
import com.zzj.db.model.QuestionVO;
import com.zzj.db.model.SlideResultVO;

/**
 * <p><strong>类名: </strong></p>QuestionInfoBlo <br/>
 * <p><strong>功能说明: </strong></p>QUESTION_INFO表相关数据操作处理  <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日下午2:11:04 <br/>
 * @author   康冠雄
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class QuestionInfoBlo {

	/**
	 * question_info表操作Mapper
	 */
	@Autowired
	private QuestionInfoMapper questionInfoMapper;

	/**
	 * e问筛选
	 * @param  info 筛选条件
	 * @return List<QuestionVO>记录
	 */
	public List<QuestionVO> searchQuestionList(QuestionVO info) {
		
		return questionInfoMapper.searchQuestionList(info);
	}
	
	/**
	 * 记录取得总件数
	 * @param  record 检索条件
	 * @return Integer 查询结果
	 */
	public Integer selectTotalCount(QuestionVO record) {
		return questionInfoMapper.selectTotalCount(record);
	}
	
	/**
	 * 记录分页取得处理
	 * @param  record 检索条件
	 * @return List<QuestionVO>记录
	 */
	public List<QuestionVO> selectPagging(QuestionVO record) {
		return questionInfoMapper.selectPagging(record);
	}
	
	/**
	 * e问修改
	 * @param  info 修改对象
	 * @return 无
	 */
	public void update(QuestionInfo info) {
		questionInfoMapper.update(info);
	}
	
    /**
	 * 根据主键查询E问表指定记录<br/>
	 * @param  questionCd 用户ID
	 * @return QuestionVO 取得结果
	 */
	public QuestionVO selectByPrimaryKey(String questionCd) {		
		return questionInfoMapper.selectByPrimaryKey(questionCd);
	}

	/**
	 * 根据条件更新中指定记录<br/>
	 * @param  record 更新记录对应
	 * @return int 更新结果
	 */
	public int updateByPrimaryKeySelective(QuestionInfo record) {
		return questionInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 根据研究领域和主题查询对应e问<br/>
	 * @param  slideResultVO 轮播记录
	 * @return List<QuestionInfo> 结果
	 */
	public List<QuestionInfo> slideEditSearch(SlideResultVO slideResultVO) {
		List<QuestionInfo> questionInfos = questionInfoMapper.slideEditSearch(slideResultVO);
		return questionInfos;
	}
}

