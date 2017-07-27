/**
 * Project Name:zzj-db
 * File Name:UserHandleInfoBlo.java
 * Package Name:com.zzj.db.dao
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/
package com.zzj.db.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzj.db.dao.UserCollectInfoMapper;
import com.zzj.db.dao.UserDownloadInfoMapper;
import com.zzj.db.dao.UserHandleInfoMapper;
import com.zzj.db.dao.UserLikeInfoMapper;
import com.zzj.db.dao.UserScanInfoMapper;
import com.zzj.db.dao.UserShareInfoMapper;
import com.zzj.db.dto.UserHandleInfo;
import com.zzj.db.model.ActionJknVO;
import com.zzj.db.model.ActionResultVO;

/**
 * <p><strong>类名: </strong></p>UserHandleInfoBlo业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>UserHandleInfoBlo业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月18日下午1:24:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class UserHandleInfoBlo {

	/**
	 * UserHandleInfo表操作Mapper
	 */
	@Autowired
	private UserHandleInfoMapper mapper;
	
	/**
	 * UserCollectInfoMapper表操作Mapper
	 */
	@Autowired
	private UserCollectInfoMapper collectMapper;
	
	/**
	 * UserDownloadInfo表操作Mapper
	 */
	@Autowired
	private UserDownloadInfoMapper downloadMapper;
	
	/**
	 * UserLikeInfo表操作Mapper
	 */
	@Autowired
	private UserLikeInfoMapper likeMapper;
	
	/**
	 * UserScanInfo表操作Mapper
	 */
	@Autowired
	private UserScanInfoMapper scanMapper;
	
	/**
	 * UserShareInfo表操作Mapper
	 */
	@Autowired
	private UserShareInfoMapper shareMapper;

	/**
	 * UserHandleInfo记录取得处理
	 * @param  key 检索条件
	 * @return userHandleInfo userHandleInfo记录
	 */
	public UserHandleInfo getUserHandleInfo(String key) {
		UserHandleInfo userHandleInfo = mapper.selectByPrimaryKey(key);
		
		return userHandleInfo;
	}

	/**
	 * UserHandleInfo记录保存处理
	 * @param  userHandleInfo userHandleInfo记录
	 * @return int 保存结果
	 */
	public int saveUserHandleInfo(UserHandleInfo userHandleInfo) {
		int result = 0;
		if(userHandleInfo != null) {
			result = mapper.updateByPrimaryKey(userHandleInfo);
		}
		
		return result;
	}

	/**
	 * UserHandleInfo记录追加处理
	 * @param  userHandleInfo userHandleInfo记录
	 * @return int 追加结果
	 */
	public int addUserHandleInfo(UserHandleInfo userHandleInfo) {
		int result = 0;
		if(userHandleInfo != null) {
			result = mapper.insert(userHandleInfo);
		}
		
		return result;
	}
	
	/**
	 * 行为一览中ActionResultVO记录取得处理
	 * @param ActionJknVO record 检索条件
	 * @return List<ActionResultVO>记录
	 */
	public List<ActionResultVO> selectAll(ActionJknVO record) {
		return mapper.selectAll(record);
	}
	
	/**
	 * 行为一览中ActionResultVO记录分页取得处理
	 * @param  record 检索条件
	 * @return List<ActionResultVO>记录
	 */
	public List<ActionResultVO> selectByOption(ActionJknVO record) {
		return mapper.selectByOption(record);
	}
	
	/**
	 * 行为一览中ActionResultVO记录取得总件数
	 * @param ActionJknVO record 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectTotalCount(ActionJknVO record) {
		return mapper.selectTotalCount(record);
	}
	
	/**
	 * 收藏一览中ActionResultVO记录取得处理
	 * @param ActionJknVO record 检索条件
	 * @return List<ActionResultVO>记录
	 */
	public List<ActionResultVO> selectAllCollect(ActionJknVO record) {
		return collectMapper.selectAll(record);
	}
	
	/**
	 * 收藏一览中ActionResultVO记录分页取得处理
	 * @param  record 检索条件
	 * @return List<ActionResultVO>记录
	 */
	public List<ActionResultVO> selectByOptionCollect(ActionJknVO record) {
		return collectMapper.selectByOption(record);
	}
	
	/**
	 * 收藏一览中ActionResultVO记录取得总件数
	 * @param ActionJknVO record 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectTotalCountCollect(ActionJknVO record) {
		return collectMapper.selectTotalCount(record);
	}
	
	/**
	 * 下载一览中ActionResultVO记录取得处理
	 * @param ActionJknVO record 检索条件
	 * @return List<ActionResultVO>记录
	 */
	public List<ActionResultVO> selectAllDownload(ActionJknVO record) {
		return downloadMapper.selectAll(record);
	}
	
	/**
	 * 下载一览中ActionResultVO记录分页取得处理
	 * @param  record 检索条件
	 * @return List<ActionResultVO>记录
	 */
	public List<ActionResultVO> selectByOptionDownload(ActionJknVO record) {
		return downloadMapper.selectByOption(record);
	}
	
	/**
	 * 下载一览中ActionResultVO记录取得总件数
	 * @param ActionJknVO record 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectTotalCountDownload(ActionJknVO record) {
		return downloadMapper.selectTotalCount(record);
	}
	
	/**
	 * 点赞一览中ActionResultVO记录取得处理
	 * @param ActionJknVO record 检索条件
	 * @return List<ActionResultVO>记录
	 */
	public List<ActionResultVO> selectAllLike(ActionJknVO record) {
		return likeMapper.selectAll(record);
	}
	
	/**
	 * 点赞一览中ActionResultVO记录分页取得处理
	 * @param  record 检索条件
	 * @return List<ActionResultVO>记录
	 */
	public List<ActionResultVO> selectByOptionLike(ActionJknVO record) {
		return likeMapper.selectByOption(record);
	}
	
	/**
	 * 点赞一览中ActionResultVO记录取得总件数
	 * @param ActionJknVO record 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectTotalCountLike(ActionJknVO record) {
		return likeMapper.selectTotalCount(record);
	}
	
	/**
	 * 浏览一览中ActionResultVO记录取得处理
	 * @param ActionJknVO record 检索条件
	 * @return List<ActionResultVO>记录
	 */
	public List<ActionResultVO> selectAllScan(ActionJknVO record) {
		return scanMapper.selectAll(record);
	}
	
	/**
	 * 浏览一览中ActionResultVO记录分页取得处理
	 * @param  record 检索条件
	 * @return List<ActionResultVO>记录
	 */
	public List<ActionResultVO> selectByOptionScan(ActionJknVO record) {
		return scanMapper.selectByOption(record);
	}
	
	/**
	 * 浏览一览中ActionResultVO记录取得总件数
	 * @param ActionJknVO record 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectTotalCountScan(ActionJknVO record) {
		return scanMapper.selectTotalCount(record);
	}
	
	/**
	 * 分享一览中ActionResultVO记录取得处理
	 * @param ActionJknVO record 检索条件
	 * @return List<ActionResultVO>记录
	 */
	public List<ActionResultVO> selectAllShare(ActionJknVO record) {
		return shareMapper.selectAll(record);
	}
	
	/**
	 * 分享一览中ActionResultVO记录分页取得处理
	 * @param  record 检索条件
	 * @return List<ActionResultVO>记录
	 */
	public List<ActionResultVO> selectByOptionShare(ActionJknVO record) {
		return shareMapper.selectByOption(record);
	}
	
	/**
	 * 分享一览中ActionResultVO记录取得总件数
	 * @param ActionJknVO record 检索条件
	 * @return Integer 总件数
	 */
	public Integer selectTotalCountShare(ActionJknVO record) {
		return shareMapper.selectTotalCount(record);
	}
}

