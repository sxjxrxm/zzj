/**
 * Project Name:zzj-web
 * File Name:VideoService.java
 * Package Name:com.zzj.manage.service
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartRequest;

import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.model.LiveInfoVO;
import com.zzj.db.model.PageResult;
import com.zzj.db.model.VideoInfoVO;

/**
 * <p><strong>类名: </strong></p>VideoService业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>VideoService业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月09日下午1:24:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
public interface VideoService {
	/**
	 * 分页取得视频记录。
	 * @param VideoInfoVO 画面表示用视频记录
	 * @return PageResult<VideoInfoVO> 视频列表
	 */
	PageResult<VideoInfoVO> searchPaggingVideo(VideoInfoVO record);
	
	/**
	 * 分页取得视频记录。
	 * @param LiveInfoVO 画面表示用视频记录
	 * @return PageResult<LiveInfoVO> 视频列表
	 */
	PageResult<LiveInfoVO> searchPaggingLive(LiveInfoVO record);
	
	/**
	 * 取得全部视频记录。
	 * @param VideoInfoVO 画面表示用视频记录
	 * @return List<VideoInfoVO> 视频列表
	 */
	List<VideoInfoVO> searchAllVideo(VideoInfoVO record);
	
	/**
	 * 取得全部视频记录。
	 * @param LiveInfoVO 画面表示用视频记录
	 * @return List<LiveInfoVO> 视频列表
	 */
	List<LiveInfoVO> searchAllLive(LiveInfoVO record);
	
	/**
	 * 根据主键删除记录
	 * @param videoCd 主键
	 * @return int 更新结果条目数
	 */
	int deleteVideo(String videoCd);
	
	/**
	 * 根据主键删除记录
	 * @param videoCd 主键
	 * @return int 更新结果条目数
	 * @throws Exception
	 */
	int deleteLive(String videoCd) throws Exception;
	
	/**
	 * 获得导出内容
	 * @param List<VideoInfoVO> resultList 视频一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	List<ArrayList<String>> getOutputContentVideo(List<VideoInfoVO> resultList, List<MstCodeInfo> mstCodeInfos);
	
	/**
	 * 获得导出内容
	 * @param List<LiveInfoVO> resultList 视频一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	List<ArrayList<String>> getOutputContentLive(List<LiveInfoVO> resultList, List<MstCodeInfo> mstCodeInfos);
	
	/**
	 * 刷新画面
	 * @param HttpServletRequest request 请求实例
	 * @param String videoCd 视频编码
	 * @throws Exception
	 */
	void loadVideo(HttpServletRequest request, String videoCd) throws Exception;
	
	/**
	 * 刷新画面
	 * @param HttpServletRequest request 请求实例
	 * @param String liveCd 直播编码
	 */
	void loadLive(HttpServletRequest request, String liveCd) throws Exception;
	
	/**
	 * 根据主键查询表记录<br/>
	 * @param  videoCd 主键
	 * @return VideoInfoVO 视频信息
	 * @throws Exception 
	 */
	VideoInfoVO selectVideoByPrimaryKey(String videoCd) throws Exception;
	
	/**
	 * 根据主键查询表记录<br/>
	 * @param  liveCd 主键
	 * @return LiveInfoVO 直播信息
	 * @throws Exception
	 */
	LiveInfoVO selectLiveByPrimaryKey(String liveCd) throws Exception;
	
	/**
	 * 根据主键更新记录
	 * @param  VideoInfoVO record 视频信息
	 * @return int 更新结果条目数
	 * @throws Exception 
	 */
	int save(VideoInfoVO record) throws Exception;
	
	/**
	 * 获得不属于该视频的领域
	 * @param List<TopicFieldInfoKey> 属于该视频的领域
	 * @return List<TopicFieldInfoKey> 获得不属于该视频的领域
	 */
	List<TopicFieldInfoKey> getOtherTopicFieldInfo(List<TopicFieldInfoKey> topicFieldInfo);

	/**
	 * 根据请求参数构造blo调用的map参数。<br/>
	 * @param request 请求实例
	 * @param edit 是否编辑
	 * @return videoInfoVO 用于数据库查询
	 * @throws Exception
	 */
	VideoInfoVO selectKeyMapVideo(HttpServletRequest request, String edit) throws Exception;
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 * @param edit 是否编辑
	 * @return LiveInfoVO 用于数据库查询
	 * @throws Exception
	 */
	LiveInfoVO selectKeyMapLive(HttpServletRequest request, String edit) throws Exception;
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 * @throws Exception 
	 */
	void selectKeySave(HttpServletRequest request) throws Exception;
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 * @param pagging 分页标识
	 * @return VideoInfoVO 用于数据库查询
	 * @throws Exception 
	 */
	VideoInfoVO getSaveVideoInfo(HttpServletRequest request, String pagging) throws Exception;

	/**
	 * 视频编辑页面图片异步请求<br/>
	 * @param  file 文件请求
	 * @param  request 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 */
	Map<String, String> queryPic(MultipartRequest file, HttpServletRequest request);

//	/**
//	 * 知识编辑页面视频异步请求<br/>
//	 * @param  file 文件请求
//	 * @param  request 请求实例
//	 * @return Map<String, String> 
//	 * 返回文件保存地址：https://qzonestyle.gtimg.cn/open/qcloud/video/flash/video_player.swf?swfv=beta1&auto_play=0&file_id=14651978969489883577&app_id=1253152384&version=1
//	 * file_id=14651978969489883577
//	 * @throws Exception
//	 */
//	Map<String, String> uploadVideo(MultipartRequest file, HttpServletRequest req);
}

