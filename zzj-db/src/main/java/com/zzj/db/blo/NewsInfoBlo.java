/**
 * Project Name:zzj-db
 * File Name:NewsInfoBlo.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.blo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.NewsInfoMapper;
import com.zzj.db.dto.NewsInfo;
import com.zzj.db.model.ExpertArticleCountVO;
import com.zzj.db.model.NewsInfoEditVO;
import com.zzj.db.model.NewsInfoListVO;
import com.zzj.db.model.SlideResultVO;

/**
 * <p><strong>类名: </strong></p>NewsInfoBlo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>NewsInfoBlo业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月29日上午9:40:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class NewsInfoBlo {

	/**
	 * NewsInfo表操作Mapper
	 */
	@Autowired
	private NewsInfoMapper mapper;

	/**
	 * NewsInfo记录取得处理
	 * @param  key 检索条件
	 * @return NewsInfoEditVO记录
	 */
	public NewsInfoEditVO getNewsInfo(String key) {
		NewsInfoEditVO newsInfo = mapper.selectByPrimaryKey(key);
		
		return newsInfo;
	}

	/**
	 * NewsInfo记录保存处理
	 * @param  newsInfo NewsInfo记录
	 * @return int 保存结果
	 */
	public int saveNewsInfo(NewsInfo newsInfo) {
		int result = 0;
		if(newsInfo != null) {
			result = mapper.updateByPrimaryKeySelective(newsInfo);
		}
		
		return result;
	}

	/**
	 * NewsInfo记录追加处理
	 * @param  newsInfo NewsInfo记录
	 * @return int 追加结果
	 */
	public int addNewsInfo(NewsInfo newsInfo) {
		int result = 0;
		if(newsInfo != null) {
			result = mapper.insertSelective(newsInfo);
		}
		
		return result;
	}
	
	/**
	 * 根据专家id在表NewsInfo记录取得处理
	 * @param  expertId 专家id
	 * @return ExpertArticleCountVO 专家文章统计信息记录
	 */
	public ExpertArticleCountVO findNewsCountByExpertId(String expertId) {
		return mapper.findNewsCountByExpertId(expertId);
	}

	/**
	 * 根据专家id在表NewsInfo删除记录，仅当SOURCE_OWNER = expertId 而且（EXPERT_CD2、EXPERT_CD3、EXPERT_CD4、EXPERT_CD5）为空的时候才能逻辑删除
	 * @param  ids 专家id、当前用户id
	 * @return 删除结果
	 */
	public int deleteNewsBySourceOwner(String[] ids) {
		NewsInfo newsInfo = new NewsInfo();
		newsInfo.setSourceOwner(ids[0]);
		newsInfo.setUpdateId(ids[1]);
		newsInfo.setUpdateTime(new Date());
		return mapper.deleteNewsBySourceOwner(newsInfo);
	}

	/**
	 * 根据专家id在表NewsInfo记录取得处理
	 * @param  Map<String, Object> queryMap 专家id及分页信息
	 * @return List<NewsInfo> 文章信息
	 */
	public List<NewsInfo> findNewsBySourceOwnerByPage(Map<String, Object> queryMap) {
		return mapper.findNewsBySourceOwnerByPage(queryMap);
	}

	/**
	 * 根据专家id在表NewsInfo记录取得记录总数
	 * @param  Map<String, Object> queryMap 专家id
	 * @return int 记录总数
	 */
	public int findNewsCountBySourceOwner(Map<String, Object> queryMap) {
		return mapper.findNewsCountBySourceOwner(queryMap);
	}

	/**
	 * 根据资讯一览页面查询条件带分页查询NewsInfo表记录<br/>
	 * @param  map 查询条件及分页信息
	 * @return List<NewsInfoListVO> 查询结果
	 */
	public List<NewsInfoListVO> selectSelectiveByPage(Map<String, Object> map) {
		List<NewsInfoListVO> newsInfos = mapper.selectSelectiveByPage(map);
		return newsInfos;
	}

	/**
	 * 根据资讯一览页面查询条件带分页查询NewsInfo表记录<br/>
	 * @param  map 查询条件及分页信息
	 * @return 查询结果
	 */
	public int selectTotalCount(Map<String, Object> map) {
		return mapper.selectTotalCount(map);
	}

	/**
	 * 根据主键删除news_info表记录<br/>
	 * @param  ids newsCd、当前用户id
	 * @return 删除结果
	 */
	public boolean deleteNewsById(String[] ids) {
		NewsInfo newsInfo = new NewsInfo();
		newsInfo.setNewsCd(ids[0]);
		newsInfo.setUpdateId(ids[1]);
		newsInfo.setUpdateTime(new Date());
		newsInfo.setDeleteFlag(1);
		int deleteNum = mapper.updateByPrimaryKeySelective(newsInfo);
		return deleteNum == 1 ? true : false;
	}
	/**
	 * 根据研究领域和主题查询对应知识<br/>
	 * @param  slideResultVO 轮播记录
	 * @return List<NewsInfo> 查询结果
	 */
	public List<NewsInfo> slideEditSearch(SlideResultVO slideResultVO) {
		List<NewsInfo>  newsInfos = mapper.slideEditSearch(slideResultVO);
		return newsInfos;
	}

	/**
	 * 取得全部知识记录（根据知识一览页面的检索条件）
	 * @param Map<String, Object> map 查询参数
	 * @return List<NewsInfoListVO> 知识列表
	 */
	public List<NewsInfoListVO> selectAll(Map<String, Object> map) {
		List<NewsInfoListVO> newsInfos = mapper.selectAll(map);
		return newsInfos;
	}

	
	/**
	 * 取得指定知识ID对应的详细内容
	 * @param  infoId 知识ID
	 * @return NewsInfoEditVO 知识详情
	 */
	public NewsInfoEditVO selectNewInfo(String infoId){
		return mapper.selectNewInfo(infoId);
	}
}

