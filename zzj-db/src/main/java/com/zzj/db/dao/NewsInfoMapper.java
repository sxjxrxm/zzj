/**
 * Project Name:zzj-db
 * File Name:NewsInfoMapper.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.dao;

import java.util.List;
import java.util.Map;

/**
 * <p><strong>类名: </strong></p>NewsInfoMapper表操作接口 <br/>
 * <p><strong>功能说明: </strong></p>定义NewsInfoMapper表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月29日上午9:40:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
import com.zzj.db.dto.NewsInfo;
import com.zzj.db.model.ExpertArticleCountVO;
import com.zzj.db.model.NewsInfoEditVO;
import com.zzj.db.model.NewsInfoListVO;
import com.zzj.db.model.SlideResultVO;

/**
 * <p><strong>类名: </strong></p>NewsInfo表操作接口<br/>
 * <p><strong>功能说明: </strong></p>NewsInfo表操作处理定义<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日下午6:36:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface NewsInfoMapper {
	
	/**
	 * 根据主键删除NewsInfo中指定记录<br/>
	 * @param  String newsCd 查询条件
	 * @return int 删除结果
	 */
    int deleteByPrimaryKey(String newsCd);

    /**
   	 * 插入指定记录<br/>
   	 * @param  record NewsInfo记录
   	 * @return int 插入结果
   	 */
    int insert(NewsInfo record);

    /**
	 * 有条件的插入指定记录<br/>
	 * @param  record NewsInfo记录
	 * @return int 插入结果
	 */
    int insertSelective(NewsInfo record);

    /**
   	 * 根据主键查询NewsInfo中指定记录<br/>
   	 * @param  String newsCd
   	 * @return NewsInfoEditVO 取得结果
   	 */
    NewsInfoEditVO selectByPrimaryKey(String newsCd);

    /**
   	 * 根据条件更新NewsInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKeySelective(NewsInfo record);

    /**
   	 * 根据主键更新NewsInfo中指定记录<br/>
   	 * @param  record 更新记录对应
   	 * @return int 更新结果
   	 */
    int updateByPrimaryKey(NewsInfo record);

    /**
	 * 根据专家id在表NewsInfo记录取得处理，只查询SOURCE_OWNER = expertId 而且（EXPERT_CD2、EXPERT_CD3、EXPERT_CD4、EXPERT_CD5）为空
	 * @param  expertId 专家id
	 * @return ExpertArticleCountVO 专家文章统计信息记录
	 */
	ExpertArticleCountVO findNewsCountByExpertId(String expertId);

	/**
	 * 根据专家id在表NewsInfo删除记录，仅当SOURCE_OWNER = expertId 而且（EXPERT_CD2、EXPERT_CD3、EXPERT_CD4、EXPERT_CD5）为空的时候才能删除
	 * @param  newsInfo 包含SOURCE_OWNER、UPDATE_ID、UPDATE_TIME
	 * @return 删除结果
	 */
	int deleteNewsBySourceOwner(NewsInfo newsInfo);

	/**
	 * 根据专家id在表NewsInfo记录取得处理，只查询SOURCE_OWNER = expertId 而且（EXPERT_CD2、EXPERT_CD3、EXPERT_CD4、EXPERT_CD5）为空
	 * @param  Map<String, Object> queryMap 专家id及分页信息
	 * @return List<NewsInfo> 专家文章信息
	 */
	List<NewsInfo> findNewsBySourceOwnerByPage(Map<String, Object> queryMap);

	/**
	 * 根据专家id在表NewsInfo记录取得记录总数，只查询SOURCE_OWNER = expertId 而且（EXPERT_CD2、EXPERT_CD3、EXPERT_CD4、EXPERT_CD5）为空
	 * @param  Map<String, Object> queryMap 专家id
	 * @return int 记录总数
	 */
	int findNewsCountBySourceOwner(Map<String, Object> queryMap);

	/**
	 * 根据资讯一览页面查询条件带分页查询NewsInfo表记录<br/>
	 * @param  Map<String, Object> map 查询条件及分页信息
	 * @return List<NewsInfoListVO> 查询结果
	 */
	List<NewsInfoListVO> selectSelectiveByPage(Map<String, Object> map);

	/**
	 * 根据资讯一览页面查询条件带分页查询NewsInfo表记录<br/>
	 * @param  Map<String, Object> map 查询条件及分页信息
	 * @return 查询结果
	 */
	int selectTotalCount(Map<String, Object> map);
	/**
	 * 根据研究领域和主题查询对应知识<br/>
	 * @param  slideResultVO 记录
	 * @return List<NewsInfo> 查询结果
	 */
	List<NewsInfo> slideEditSearch(SlideResultVO slideResultVO);

	/**
	 * 取得全部知识记录（根据知识一览页面的检索条件）
	 * @param Map<String, Object> map 查询参数
	 * @return List<NewsInfoListVO> 知识列表
	 */
	List<NewsInfoListVO> selectAll(Map<String, Object> map);
	
	/**
	 * 取得指定知识ID对应的详细内容
	 * @param  infoId 知识ID
	 * @return NewsInfoEditVO 知识详情
	 */
	NewsInfoEditVO selectNewInfo(String infoId);
}