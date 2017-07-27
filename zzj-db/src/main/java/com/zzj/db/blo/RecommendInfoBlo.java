/**
 * Project Name:zzj-db
 * File Name:RecommendInfoBlo.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.blo;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.RecommendInfoMapper;
import com.zzj.db.dto.RecommendInfo;
import com.zzj.db.dto.RecommendInfoKey;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>RecommendInfo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>RecommendInfo业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:24:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class RecommendInfoBlo {

	/**
	 * RecommendInfo表操作Mapper
	 */
	@Autowired
	private RecommendInfoMapper mapper;

	/**
	 * RecommendInfo记录保存处理
	 * @param  RecommendInfo RecommendInfo记录
	 * @return int 保存结果
	 */
	public int saveRecommendInfo(RecommendInfo recommendInfo) {
		int result = 0;
		if(recommendInfo != null) {
			result = mapper.updateByPrimaryKeySelective(recommendInfo);
		}
		
		return result;
	}

	/**
	 * RecommendInfo记录追加处理
	 * @param  RecommendInfo RecommendInfo记录
	 * @return int 追加结果
	 */
	public int addRecommendInfo(RecommendInfo recommendInfo) {
		int result = 0;
		if(recommendInfo != null) {
			result = mapper.insertSelective(recommendInfo);
		}
		
		return result;
	}
	
	 /**
     * 根据业务分类和主题编号获得该编号对应的推荐置顶消息。<br/>
     * @param  map 业务分类和主题编号
     * @return List<RecommendInfoKey>  该编号对应的推荐置顶消息
     */
	public List<RecommendInfoKey> selectByTypeAndCode(Map<String, String> map) {
		return mapper.selectByTypeAndCode(map);
	}

	/**
     * 根据业务分类和主题编号获得该编号对应的推荐语。<br/>
     * @param  map 业务分类和主题编号
     * @return String  推荐语
     */
	public String findRecommendMsgByTypeAndCode(Map<String, String> map) {
		return mapper.findRecommendMsgByTypeAndCode(map);
	}
	
	/**
     * 推荐语。<br/>
	 * @param  RecommendInfo recommendInfo 记录
     * @return String  推荐语
     */
	public String selectRecommendMsg(RecommendInfo recommendInfo) {
		return mapper.selectRecommendMsg(recommendInfo);
	}

	/**
	 * 根据busiType和topicCd在表RecommendInfo删除记录
	 * @param  ids ids[0]:topicCd, ids[1]:updateId
	 * @param  busiType 业务分类
	 * @throws Exception
	 */
	public void deleteRecommendInfoByTypeAndIds(String[] ids, String busiType) {
		RecommendInfo recommendInfo = new RecommendInfo();
		recommendInfo.setBusiType(busiType);
		recommendInfo.setTopicCd(ids[0]);
		recommendInfo.setUpdateId(ids[1]);
		recommendInfo.setUpdateTime(new Date());
		mapper.updateByTypeAndCode(recommendInfo);
	}
	
	/**
	 * 表RecommendInfo删除记录
	 * @param  RecommendInfo recommendInfo 记录
	 * @throws Exception
	 */
	public void deleteRecommendInfoByExpertId(RecommendInfo recommendInfo) {
		mapper.updateByTypeAndCode(recommendInfo);
	}

	/**
	 * 根据主键查询RecommendInfo中指定记录<br/>
	 * @param  key 联合主键
	 * @return RecommendInfo 取得结果
	 * @throws Exception
	 */
	public RecommendInfo selectByPrimaryKey(RecommendInfoKey key) {
		return mapper.selectByPrimaryKey(key);
	}
	/**
	 * 根据机能和推荐置顶查询RecommendInfo中指定记录<br/>
	 * @param  recommendInfo 查询记录
	 * @return list 查询结果集
	 * @throws Exception
	 */
	public List<RecommendInfo> selectByRecommendKbnAndBusi(RecommendInfo recommendInfo) {
		List<RecommendInfo> recommendInfos = mapper.selectByRecommendKbnAndBusi(recommendInfo);
		return recommendInfos;
	}
	/**
	 * 
	 *批量取消方法：根据主键和推荐置顶状态更改相应记录删除状态<br/>
	 * @param  request http请求实例
	 * @author   刘研
	 * @throws IOException 
	 * @throws Exception
	 */
	public Integer updateByTopicAndKbn(RecommendInfo recommendInfo) {
		return mapper.updateByPrimaryKeySelective(recommendInfo);
	}
	
	/**
	 * 根据机能和推荐置顶查询RecommendInfo中指定记录<br/>
	 * @param  recommendInfo 查询记录
	 * @return Integer 查询件数
	 */
	public Integer selectCount(RecommendInfo recommendInfo) {
		return mapper.selectCount(recommendInfo);
	}
	
	/**
	 * 是否到达上限(true 是， false 否)
	 * @param  recommendInfo 推荐或置顶信息
	 * @param  recommendKbn 推荐置顶区分
	 * @return boolean 是否到达上限
	 */
	public boolean isToTopRecommendCount(RecommendInfo recommendInfo, String recommendKbn) {
		// 构造查询条件map
		Map<String, String> map = new HashMap<String, String>();
		map.put("busiType", recommendInfo.getBusiType());
		map.put("topicCd", recommendInfo.getTopicCd());
		List<RecommendInfoKey> info = mapper.selectByTypeAndCode(map);
		StringBuffer sb = new StringBuffer();
		for (RecommendInfoKey key : info) {
			sb.append(key.getRecommendKbn());
		}
		// 该信息以被推荐置顶
		if (sb.toString().contains(ZzjConstants.RECOMMEND_KBN_1) && 
				sb.toString().contains(ZzjConstants.RECOMMEND_KBN_2)) {
			return false;
		}
						
		int count = this.selectCount(recommendInfo);
		// 推荐
		if (!sb.toString().contains(ZzjConstants.RECOMMEND_KBN_1) &&
				ZzjConstants.RECOMMEND_KBN_1.equals(recommendKbn) && count >= ZzjConstants.RECOMMEND_NUM)
		{
			return true;
		}
		// 置顶
		if (!sb.toString().contains(ZzjConstants.RECOMMEND_KBN_2) &&
				ZzjConstants.RECOMMEND_KBN_2.equals(recommendKbn) && count >= ZzjConstants.TO_TOP_NUM)
		{
			return true;
		}
		return false;
	}
}

