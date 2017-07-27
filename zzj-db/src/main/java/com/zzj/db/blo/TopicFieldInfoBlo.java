/**
 * Project Name:zzj-db
 * File Name:TopicFieldInfoBlo.java
 * Package Name:com.zzj.db.blo
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.db.blo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.TopicFieldInfoMapper;
import com.zzj.db.dto.TopicFieldInfo;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>TopicFieldInfo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>TopicFieldInfo业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月24日下午3:44:33 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class TopicFieldInfoBlo {

	/**
	 * TopicFieldInfo表操作Mapper
	 */
	@Autowired
	private TopicFieldInfoMapper mapper;

	/**
     * 根据业务分类和主题编号获得该编号对应的推荐置顶消息。<br/>
     * @param  map 业务分类和主题编号
     * @return List<TopicFieldInfoKey>  该编号对应的推荐置顶消息
     */
	public List<TopicFieldInfoKey> selectByTypeAndCode(Map<String, String> map) {
		return mapper.selectByTypeAndCode(map);
	}
	
	/**
	 * TopicFieldInfo记录追加处理
	 * @param  topicFieldInfo TopicFieldInfo记录
	 * @return int 追加结果
	 */
	public int addTopicFieldInfo(TopicFieldInfo topicFieldInfo) {
		int result = 0;
		if(topicFieldInfo != null) {
			result = mapper.insert(topicFieldInfo);
		}
		return result;
	}

	/**
	 * 表TopicFieldInfo删除记录
	 * @param  TopicFieldInfo topicFieldInfo 信息
	 * @return 无
	 */
	public void delete(TopicFieldInfo topicFieldInfo) {
		mapper.updateByTypeAndCode(topicFieldInfo);
	}
	
	/**
	 * 根据busiType和topicCd在表TopicFieldInfo删除记录
	 * @param  ids ids[0]:topicCd, ids[1]:updateId
	 * @param  busiType 业务分类
	 * @return 无
	 */
	public void deleteTopicFieldInfoByTypeAndIds(String[] ids, String busiType) {
		TopicFieldInfo topicFieldInfo = new TopicFieldInfo();
		topicFieldInfo.setBusiType(busiType);
		topicFieldInfo.setTopicCd(ids[0]);
		topicFieldInfo.setUpdateId(ids[1]);
		topicFieldInfo.setUpdateTime(new Date());
		mapper.updateByTypeAndCode(topicFieldInfo);
	}

	/**
	 * 根据主键查找TopicFieldInfo表中记录
	 * @param  key 主键
	 * @return TopicFieldInfo 查询结果
	 */
	public TopicFieldInfo selectByPrimaryKey(TopicFieldInfoKey key) {
		return mapper.selectByPrimaryKey(key);
	}

	/**
	 * TopicFieldInfo记录更新处理
	 * @param  topicFieldInfo TopicFieldInfo记录
	 * @return int 更新结果
	 */
	public int saveTopicFieldInfo(TopicFieldInfo topicFieldInfo) {
		int result = 0;
		if(topicFieldInfo != null) {
			result = mapper.updateByPrimaryKeySelective(topicFieldInfo);
		}
		return result;
	}

    /**
     * 取得相关业务对应的领域信息<br/>
     * @param  map 搜索条件<br/>
     * codeType:编码类别(techFieldType 技术领域/rschFieldType 研究领域)
     * busiType:业务编码
     * topicCd:业务项目ID
     * @return List<TopicFieldInfo>  业务对应的领域信息
     */
	public List<TopicFieldInfo> selectTopicInfo(Map<String, String> map){
		return mapper.selectTopicInfo(map);
	}
}
