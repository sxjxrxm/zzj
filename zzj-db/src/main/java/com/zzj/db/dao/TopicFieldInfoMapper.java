/**
 * Project Name:zzj-db
 * File Name:TopicFieldInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright ? 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;
import java.util.Map;

import com.zzj.db.dto.TopicFieldInfo;
import com.zzj.db.dto.TopicFieldInfoKey;

/**
 * <p><strong>类名: </strong></p>TopicFieldInfoMapper表操作接口<br/>
 * <p><strong>功能说明: </strong></p>TopicFieldInfoMapper表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月24日下午3:36:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface TopicFieldInfoMapper {
	/**
	 * 根据主键删除TopicFieldInfo中指定记录<br/>
	 * @param  key TopicFieldInfoKey
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(TopicFieldInfoKey key);

    /**
	 * 插入指定记录<br/>
	 * @param  record TopicFieldInfo记录
	 * @return int 插入结果
	 */
    int insert(TopicFieldInfo record);

    /**
   	 * 有条件的插入指定记录<br/>
   	 * @param  record TopicFieldInfo记录
   	 * @return int 插入结果
   	 */
    int insertSelective(TopicFieldInfo record);

    /**
	 * 根据主键查询TopicFieldInfo中指定记录<br/>
	 * @param  key TopicFieldInfoKey
	 * @return TopicFieldInfo 取得结果
	 */
    TopicFieldInfo selectByPrimaryKey(TopicFieldInfoKey key);

    /**
	 * 根据条件更新TopicFieldInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
	 */
    int updateByPrimaryKeySelective(TopicFieldInfo record);

    /**
	 * 根据主键更新TopicFieldInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
	 */
    int updateByPrimaryKey(TopicFieldInfo record);

    /**
     * 根据业务分类和主题编号获得该编号对应的推荐置顶消息。<br/>
     * @param  map 业务分类和主题编号
     * @return List<TopicFieldInfoKey>  该编号对应的推荐置顶消息
     */
	List<TopicFieldInfoKey> selectByTypeAndCode(Map<String, String> map);

    /**
	 * 根据条件更新TopicFieldInfo记录
	 * @param  topicFieldInfo 置顶信息
	 * @return int 更新结果
	 */
	int updateByTypeAndCode(TopicFieldInfo topicFieldInfo);

    /**
     * 取得相关业务对应的领域信息<br/>
     * @param  map 搜索条件<br/>
     * codeType:编码类别(techFieldType 技术领域/rschFieldType 研究领域)
     * busiType:业务编码
     * topicCd:业务项目ID
     * @return List<TopicFieldInfo>  业务对应的领域信息
     */
	List<TopicFieldInfo> selectTopicInfo(Map<String, String> map);
}