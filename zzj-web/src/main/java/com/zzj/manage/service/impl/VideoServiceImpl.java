/**
 * Project Name:zzj-web
 * File Name:UserServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.zzj.core.exception.ValidateErrorException;
import com.zzj.db.blo.LiveInfoBlo;
import com.zzj.db.blo.MstFieldInfoBlo;
import com.zzj.db.blo.MstSequenceInfoBlo;
import com.zzj.db.blo.RecommendInfoBlo;
import com.zzj.db.blo.TopicFieldInfoBlo;
import com.zzj.db.blo.VideoInfoBlo;
import com.zzj.db.dto.LiveInfo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.RecommendInfo;
import com.zzj.db.dto.RecommendInfoKey;
import com.zzj.db.dto.TopicFieldInfo;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.dto.VideoInfo;
import com.zzj.db.model.KeyVO;
import com.zzj.db.model.LiveInfoVO;
import com.zzj.db.model.PageResult;
import com.zzj.db.model.VideoInfoVO;
import com.zzj.manage.service.LiveService;
import com.zzj.manage.service.VideoService;
import com.zzj.util.Base64;
import com.zzj.util.SerializUtils;
import com.zzj.util.StringUtil;
import com.zzj.util.UploadUtils;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>VideoServiceImpl业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>VideoServiceImpl业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月09日下午1:24:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class VideoServiceImpl implements VideoService {

	/**
	 * User业务数据库操作类
	 */
	@Autowired
	private VideoInfoBlo videoInfoBlo;
	
	/**
	 * User业务数据库操作类
	 */
	@Autowired
	private LiveInfoBlo liveInfoBlo;
	
	/**
	 * 领域模块业务数据库操作类
	 */
	@Autowired
	private TopicFieldInfoBlo topicFieldInfoBlo;
	
	/**
	 * 推荐置顶模块业务数据库操作类
	 */
	@Autowired
	private RecommendInfoBlo recommendInfoBlo;
	
	/**
	 * 系统领域模块业务数据库操作类
	 */
	@Autowired
	private MstFieldInfoBlo mstFieldInfoBlo;
	
	/**
	 *取得主键用blo
	 */
	@Autowired
	private MstSequenceInfoBlo  mstSequenceInfoBlo;
	
	/**
	 * 共通业务处理
	 */
	@Autowired
	private LiveService liveService;
	
	/**
	 * 取得全部视频记录
	 * @param VideoInfoVO 画面表示用视频记录
	 * @return List<VideoInfoVO> 视频列表
	 */
	@Override
	public List<VideoInfoVO> searchAllVideo(VideoInfoVO record)  {
		
		List<VideoInfoVO> list = videoInfoBlo.selectAll(record);
		
		for(VideoInfoVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_03);
			map1.put("topicCd", infoVO.getVideoCd());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			infoVO.setFieldCd(topicFieldInfo);
			
			// 获取对应的推荐置顶信息
			List<RecommendInfoKey> recommendInfo = recommendInfoBlo.selectByTypeAndCode(map1);
			if(recommendInfo == null) {
				recommendInfo = new ArrayList<RecommendInfoKey>();
			}
			infoVO.setRecommend(recommendInfo);
			
		}
		return list;
	}
	
	/**
	 * 取得全部视频记录。
	 * @param LiveInfoVO 画面表示用视频记录
	 * @return List<LiveInfoVO> 视频列表
	 */
	@Override
	public List<LiveInfoVO> searchAllLive (LiveInfoVO record) {
		
		List<LiveInfoVO> list = liveInfoBlo.selectAll(record);
		
		for(LiveInfoVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_03);
			map1.put("topicCd", infoVO.getLiveCd());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			infoVO.setFieldCd(topicFieldInfo);			
		}
		return list;
	}
	/**
	 * 分页取得视频记录
	 * @return List<videoInfo> 画面表示用视频记录
	 * @return PageResult<videoInfoVO> 视频列表
	 */
	@Override
	public PageResult<VideoInfoVO> searchPaggingVideo(VideoInfoVO record)  {
		Integer totalCount = videoInfoBlo.selectTotalCount(record);
		// 没有检索到数据。
		if (totalCount == 0)
		{
			return new PageResult<VideoInfoVO>();
		}
		if (record.getDbIndex() >= totalCount)
		{
			int flag = totalCount % record.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
			int num = totalCount / record.getPageSize();
			record.setPageNo((flag == 0) ? num : num + 1);
			record.setDbIndex((record.getPageNo() - 1) * record.getPageSize());
		}
		if (record.getDbIndex() < 0)
		{
			record.setDbIndex(0);
			record.setPageNo(1);
		}
		List<VideoInfoVO> list = videoInfoBlo.selectPagging(record);
		
		for(VideoInfoVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_03);
			map1.put("topicCd", infoVO.getVideoCd());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			infoVO.setFieldCd(topicFieldInfo);
			
			// 获取对应的推荐置顶信息
			List<RecommendInfoKey> recommendInfo = recommendInfoBlo.selectByTypeAndCode(map1);
			if(recommendInfo == null) {
				recommendInfo = new ArrayList<RecommendInfoKey>();
			}
			infoVO.setRecommend(recommendInfo);			
		}
	
		/**
		 * 构造分页结果集
		 */
		PageResult<VideoInfoVO> pageResult = new PageResult<VideoInfoVO>();
		pageResult.setPageNo(record.getDbIndex() / record.getPageSize() + 1);
		pageResult.setPageSize(record.getPageSize());
		int flag = totalCount % record.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / record.getPageSize();
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(list);
		
		return pageResult;
	}
	
	/**
	 * 分页取得视频记录
	 * @return List<LiveInfoVO> 画面表示用视频记录
	 * @return PageResult<LiveInfoVO> 视频列表
	 */
	@Override
	public PageResult<LiveInfoVO> searchPaggingLive(LiveInfoVO record)  {
		Integer totalCount = liveInfoBlo.selectTotalCount(record);
		// 没有检索到数据。
		if (totalCount == 0)
		{
			return new PageResult<LiveInfoVO>();
		}
		if (record.getDbIndex() >= totalCount)
		{
			int flag = totalCount % record.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
			int num = totalCount / record.getPageSize();
			record.setPageNo((flag == 0) ? num : num + 1);
			record.setDbIndex((record.getPageNo() - 1) * record.getPageSize());
		}
		if (record.getDbIndex() < 0)
		{
			record.setDbIndex(0);
			record.setPageNo(1);
		}
		List<LiveInfoVO> list = liveInfoBlo.selectPagging(record);
		
		for(LiveInfoVO infoVO : list) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_03);
			map1.put("topicCd", infoVO.getLiveCd());
			
			// 获取对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			infoVO.setFieldCd(topicFieldInfo);		
		}
		
		/**
		 * 构造分页结果集
		 */
		PageResult<LiveInfoVO> pageResult = new PageResult<LiveInfoVO>();
		pageResult.setPageNo(record.getDbIndex() / record.getPageSize() + 1);
		pageResult.setPageSize(record.getPageSize());
		int flag = totalCount % record.getPageSize();// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / record.getPageSize();
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(list);
		
		return pageResult;
	}
	
	/**
	 * 根据主键删除记录
	 * @param videoCd 主键
	 * @return int 更新结果条目数
	 */
	@Override
	public int deleteVideo(String videoCd)  {
		VideoInfo record = new VideoInfo() ;
		record.setVideoCd(videoCd) ;
		record.setDeleteFlag(1);		
		return videoInfoBlo.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 根据主键删除记录
	 * @param videoCd 主键
	 * @return int 更新结果条目数
	 * @throws Exception 
	 */
	@Override
	public int deleteLive(String videoCd) throws Exception {
		LiveInfoVO record1 = liveInfoBlo.selectByPrimaryKey(videoCd);
		// 删除聊天室
		String ret = liveService.createOrDestroyRoom(record1);
		if (ZzjConstants.SUCCESS.equals(ret)){
			LiveInfo record = new LiveInfo();
			record.setLiveCd(videoCd);
			record.setDeleteFlag(1);
			
			return liveInfoBlo.updateByPrimaryKeySelective(record);
		}
		return -1;
	}
	
	/**
	 * 获得导出内容
	 * @param List<VideoInfoVO> resultList 视频一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	@Override
	public List<ArrayList<String>> getOutputContentVideo(List<VideoInfoVO> resultList, List<MstCodeInfo> mstCodeInfos)  {
		
		// 数据做成
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("视频一览");
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("视频主题");
		temp.add("费用");
		temp.add("领域");
		temp.add("收藏数");
		temp.add("播放次数");
		temp.add("点赞数");
		list.add(temp);
		
		for (VideoInfoVO n : resultList) {
			temp = new ArrayList<String>();
			temp.add(n.getVideoName());
			temp.add(n.getPaymentKbnDisp());
			StringBuffer sb = new StringBuffer();
			for (TopicFieldInfoKey f :n.getFieldCd()) {
				sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.TECH_FIELD_TYPE, f.getTechFieldCd()).getCodeName());
				if (!ZzjConstants.BLANK.equals(f.getRschFieldCd()))
				{
					sb.append("->");
					sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.RSCH_FIELD_TYPE, f.getRschFieldCd()).getCodeName());
				}
				sb.append("|");
			}
			temp.add(sb.toString());
			temp.add(n.getCollectCount() == null ? "0" : n.getCollectCount() + "");
			temp.add(n.getScanCount() == null ? "0" : n.getScanCount() + "");
			temp.add(n.getLikeCount() == null ? "0" : n.getLikeCount() + "");
			list.add(temp);
		}
		
		return list;
	}
	
	/**
	 * 获得导出内容。
	 * @param List<LiveInfoVO> resultList 视频一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	@Override
	public List<ArrayList<String>> getOutputContentLive(List<LiveInfoVO> resultList, List<MstCodeInfo> mstCodeInfos)  {
		
		// 数据做成
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("视频一览");
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("视频主题");
		temp.add("费用");
		temp.add("领域");
		temp.add("收藏数");
		temp.add("播放次数");
		temp.add("点赞数");
		list.add(temp);
		
		for (LiveInfoVO n : resultList) {
			temp = new ArrayList<String>();
			temp.add(n.getLiveName());
			temp.add(n.getPaymentKbnDisp());
			StringBuffer sb = new StringBuffer();
			for (TopicFieldInfoKey f :n.getFieldCd()) {
				sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.TECH_FIELD_TYPE, f.getTechFieldCd()).getCodeName());
				if (!ZzjConstants.BLANK.equals(f.getRschFieldCd()))
				{
					sb.append("->");
					sb.append(this.getCodeName(mstCodeInfos, ZzjConstants.RSCH_FIELD_TYPE, f.getRschFieldCd()).getCodeName());
				}
				sb.append("|");
			}
			temp.add(sb.toString());
			temp.add(n.getCollectCount() == null ? "0" : n.getCollectCount() + "");
			temp.add(n.getScanCount() == null ? "0" : n.getScanCount() + "");
			temp.add(n.getLikeCount() == null ? "0" : n.getLikeCount() + "");
			list.add(temp);
		}
		
		return list;
	}
	
	/**
	 * 根据CODE返回CODENAME<br/>
	 * @param List<MstCodeInfo> list 编码列表
	 * @param String codeType 类型
	 * @param String codeCd 编码
	 * @return MstCodeInfo 查询结果记录
	 */
	private MstCodeInfo getCodeName(List<MstCodeInfo> list, String codeType, String codeCd) {
		for (MstCodeInfo code : list) {
			if (codeType.equals(code.getCodeType()) && codeCd.equals(code.getCode()))
			{
				return code;
			}
		}
		return new MstCodeInfo();
	}
	
	/**
	 * 刷新画面。
	 * @param HttpServletRequest request 请求实例
	 * @param String videoCd 视频编码
	 * @throws Exception 
	 */
	@Override
	public void loadVideo(HttpServletRequest request, String videoCd) throws Exception {
		VideoInfoVO videoInfo = null;
		if (StringUtil.isNotBlank(videoCd)) {
			// 数据查询
			videoInfo = this.selectVideoByPrimaryKey(videoCd);
		}
		else {
			videoInfo = new VideoInfoVO();
			videoInfo.setOtherFieldCd(this.getOtherTopicFieldInfo(null));
		}
		request.setAttribute("videoInfo", videoInfo);
	}
	
	/**
	 * 根据主键查询表记录。<br/>
	 * @param  videoCd 主键
	 * @return videoInfoVO 视频信息
	 * @throws Exception 
	 */
	@Override
	public VideoInfoVO selectVideoByPrimaryKey(String videoCd) throws Exception {

		VideoInfoVO videoInfo = videoInfoBlo.selectByPrimaryKey(videoCd);
		if (StringUtil.isNotBlank(videoInfo.getLittleIcon())) {
			videoInfo.setLittleIconUrl(StringUtil.getImageURL(videoInfo.getLittleIcon()));
		}
		if (StringUtil.isNotBlank(videoInfo.getBigIcon())) {
			videoInfo.setBigIconUrl(StringUtil.getImageURL(videoInfo.getBigIcon()));
		}

		// 完善推荐置顶信息
		Map<String, String> map = new HashMap<String, String>();
		map.put("busiType", ZzjConstants.BUSI_TYPE_03);
		map.put("topicCd", videoInfo.getVideoCd());
		
		// 获取对应的推荐置顶信息
		List<RecommendInfoKey> recommendInfo = recommendInfoBlo.selectByTypeAndCode(map);
		if(recommendInfo == null) {
			recommendInfo = new ArrayList<RecommendInfoKey>();
		} else {
			StringBuffer sb = new StringBuffer();
			for (RecommendInfoKey key : recommendInfo) {
				sb.append(key.getRecommendKbn());
			}
			// 可能的结果： (1,2,)  (1,)  (2,)，方便前台使用判断
			videoInfo.setRecommendKbn(sb.toString());
			if (sb.toString().contains(ZzjConstants.RECOMMEND_KBN_1)) {
				// 已被推荐，需要查询推荐语
				videoInfo.setRecommendMsg(recommendInfoBlo.findRecommendMsgByTypeAndCode(map));
			}
		}
		
		// 获取对应领域信息
		List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map);
		videoInfo.setFieldCd(topicFieldInfo);
		// 获得不属于该视频的领域
		videoInfo.setOtherFieldCd(this.getOtherTopicFieldInfo(topicFieldInfo));
		
		return videoInfo;
	}
	
	/**
	 * 刷新画面。
	 * @param HttpServletRequest request 请求实例
	 * @param String liveCd 视频编码
	 * @throws Exception 
	 */
	@Override
	public void loadLive(HttpServletRequest request, String liveCd) throws Exception {
		LiveInfoVO liveInfo = null;
		if (StringUtil.isNotBlank(liveCd)) {
			// 数据查询
			liveInfo = this.selectLiveByPrimaryKey(liveCd);
			if (StringUtil.isNotBlank(liveInfo.getLittleIcon())) {
				liveInfo.setLittleIconUrl(StringUtil.getImageURL(liveInfo.getLittleIcon()));
			}
			if (StringUtil.isNotBlank(liveInfo.getBigIcon())) {
				liveInfo.setBigIconUrl(StringUtil.getImageURL(liveInfo.getBigIcon()));
			}
		}
		else {
			liveInfo = new LiveInfoVO();
			liveInfo.setOtherFieldCd(this.getOtherTopicFieldInfo(null));
		}
		request.setAttribute("liveInfo", liveInfo);
	}
	
	/**
	 * 根据主键查询表记录。<br/>
	 * @param  videoCd 主键
	 * @return videoInfoVO 视频信息
	 */
	@Override
	public LiveInfoVO selectLiveByPrimaryKey(String liveCd) {
		
		LiveInfoVO liveInfo = liveInfoBlo.selectByPrimaryKey(liveCd);
		
		// 完善推荐置顶信息
		Map<String, String> map = new HashMap<String, String>();
		map.put("busiType", ZzjConstants.BUSI_TYPE_03);
		map.put("topicCd", liveInfo.getLiveCd());
		
		// 获取对应领域信息
		List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map);
		liveInfo.setFieldCd(topicFieldInfo);
		// 获得不属于该视频的领域
		liveInfo.setOtherFieldCd(this.getOtherTopicFieldInfo(topicFieldInfo));
		
		return liveInfo;
	}
	
	/**
	 * 获得不属于该视频的领域。
	 * @param List<TopicFieldInfoKey> 属于该视频的领域
	 * @return List<TopicFieldInfoKey> 获得不属于该视频的领域
	 */
	@Override
	public List<TopicFieldInfoKey> getOtherTopicFieldInfo(List<TopicFieldInfoKey> topicFieldInfo)  {
		// 取得全部领域
		List<TopicFieldInfoKey> mstFieldInfo = mstFieldInfoBlo.selectAll();
		if (topicFieldInfo == null)
		{
			return mstFieldInfo;
		}
	    // 获得不属于该视频的领域
		List<TopicFieldInfoKey> otherTopicFieldInfo = new ArrayList<TopicFieldInfoKey>();
		boolean isContains = false;
		for (TopicFieldInfoKey m :mstFieldInfo) {
			isContains = false;
			for (TopicFieldInfoKey t : topicFieldInfo) {
				if (m.getTechFieldCd().equals(t.getTechFieldCd()) && 
						(m.getRschFieldCd().equals(t.getRschFieldCd()) || ZzjConstants.BLANK.equals(t.getRschFieldCd()))) {
					isContains = true;
				}
			}
			if (!isContains) {
				otherTopicFieldInfo.add(m);
			}
		}
		return otherTopicFieldInfo;
	}

	/**
	 * 根据主键更新记录。
	 * @param  VideoInfoVO record 视频信息
	 * @return int 更新结果条目数
	 * @throws Exception 
	 */
	@Override
	public int save(VideoInfoVO record) throws Exception {
		int result = 0;
		// 1.保存视频信息
		if (StringUtil.isBlank(record.getVideoCd())) {
			String videoCd = mstSequenceInfoBlo.selectSequenceInfo(ZzjConstants.VIDEO_NO);
			record.setVideoCd(videoCd);
			record.setCreateId(record.getUpdateId());
			record.setCreateTime(record.getUpdateTime());
//			// 将临时保存的图片文件重命名并修改图片地址字段
//			if (StringUtil.isNotBlank(record.getLittleIcon())) {
//				String realPath = UploadUtils.getRealPath(record.getLittleIcon(), record.getVideoCd());
//				record.setLittleIcon(realPath);
//			}
//			if (StringUtil.isNotBlank(record.getBigIcon())) {
//				String realPath = UploadUtils.getRealPath(record.getBigIcon(), record.getVideoCd());
//				record.setBigIcon(realPath);
//			}
			result = videoInfoBlo.insertSelective(record);
		} else {
//			// 将临时保存的图片文件重命名并修改图片地址字段
//			if (StringUtil.isNotBlank(record.getLittleIcon())) {
//				String realPath = UploadUtils.getRealPath(record.getLittleIcon(), record.getVideoCd());
//				record.setLittleIcon(realPath);
//			}
//			if (StringUtil.isNotBlank(record.getBigIcon())) {
//				String realPath = UploadUtils.getRealPath(record.getBigIcon(), record.getVideoCd());
//				record.setBigIcon(realPath);
//			}
			result = videoInfoBlo.updateByPrimaryKeySelective(record);
		}
		
		if (result != 1) {
			return result;
		}
		// 2.保存领域
		TopicFieldInfo topicFieldInfo = new TopicFieldInfo();
		topicFieldInfo.setBusiType(ZzjConstants.BUSI_TYPE_03);
		topicFieldInfo.setTopicCd(record.getVideoCd());
		topicFieldInfo.setDeleteFlag(1);
		topicFieldInfo.setUpdateId(record.getUpdateId());
		topicFieldInfo.setUpdateTime(record.getUpdateTime());
		topicFieldInfoBlo.delete(topicFieldInfo);
		List<TopicFieldInfo> fieldInfo = record.getFieldInfo();
		if (fieldInfo != null && fieldInfo.size() > 0) {
			for (TopicFieldInfo field : fieldInfo) {
				// 根据主键查询是否存在
				field.setTopicCd(record.getVideoCd());
				TopicFieldInfo temp = topicFieldInfoBlo.selectByPrimaryKey(field);
				if (temp != null) {
					// 如果存在执行更新操作
					result = topicFieldInfoBlo.saveTopicFieldInfo(field);
				} else {
					// 如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
					result = topicFieldInfoBlo.addTopicFieldInfo(field);
				}
			}
		}
		
		// 3.保存推荐置顶信息
		// 3.1 先将该专家数据库中保存的推荐置顶全部改为逻辑删除状态，防止多次修改导致领域集合累加
		RecommendInfo recommend = new RecommendInfo();
		recommend.setBusiType(ZzjConstants.BUSI_TYPE_03);
		recommend.setTopicCd(record.getVideoCd());
		recommend.setDeleteFlag(1);
		recommend.setUpdateId(record.getUpdateId());
		recommend.setUpdateTime(record.getUpdateTime());
		recommendInfoBlo.deleteRecommendInfoByExpertId(recommend);
		// 3.2.1置顶消息
		RecommendInfo toTopInfo = record.getToTopInfo();
		
		// 先查询后操作
		if (toTopInfo != null) {
			toTopInfo.setTopicCd(record.getVideoCd());
			RecommendInfo temp = recommendInfoBlo.selectByPrimaryKey(toTopInfo);
			if (temp != null) {
				//如果存在执行更新操作
				result = recommendInfoBlo.saveRecommendInfo(toTopInfo);
			} else {
				//如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
				result = recommendInfoBlo.addRecommendInfo(toTopInfo);
			}
		}
		// 3.2.2推荐消息
		RecommendInfo recommendInfo = record.getRecommendInfo();
		
		// 先查询后操作
		if (recommendInfo != null) {
			recommendInfo.setTopicCd(record.getVideoCd());
			RecommendInfo temp = recommendInfoBlo.selectByPrimaryKey(recommendInfo);
			if (temp != null) {
				// 如果存在执行更新操作
				result = recommendInfoBlo.saveRecommendInfo(recommendInfo);
			} else {
				// 如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
				result = recommendInfoBlo.addRecommendInfo(recommendInfo);
			}
		}
		
		return result;
	}
	
	/**
	 * 视频编辑页面图片异步请求。<br/>
	 * @param  file 文件请求
	 * @param  request 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 */
	@Override
	public Map<String, String> queryPic(MultipartRequest file, HttpServletRequest request) {
		String id = request.getParameter("id");// 用于区分列表图片（littleIcon）、详情图片（bigIcon）
		if (StringUtil.isBlank(id)) {
			return null;
		}
		// 获得请求图片
		MultipartFile multipartFile = file.getFile(id + "ImgData");
		String realPath = "";
		Map<String, String> resultMap = new HashMap<String, String>();
		// 图片验证
		String message = "";
		if ("littleIcon".equals(id))
		{
			message = UploadUtils.checkLittleIconImg(multipartFile);
		}
		else {			
			message = UploadUtils.checkBigIconImg(multipartFile);
		}
		if (StringUtil.isNotBlank(message))
		{
			resultMap.put("message", message);
			return resultMap;
		}
		
		try {
			// 执行图片上传
			realPath = UploadUtils.uploadImg(multipartFile, ZzjConstants.UPLOAD_TYPE_CONTENT, ZzjConstants.BUSI_TYPE_03);
			// web服务器存储路径
			resultMap.put("path", realPath);
			// 取得图片URL链接
			resultMap.put("url", StringUtil.getImageURL(realPath));
			resultMap.put("message", ZzjConstants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回图片地址
		return resultMap;
	}

	/**
	 * 根据请求参数构造blo调用的map参数。<br/>
	 * @param request 请求实例
	 * @param edit 是否编辑
	 * @return videoInfoVO 用于数据库查询
	 * @throws Exception
	 */
	@Override
	public VideoInfoVO selectKeyMapVideo(HttpServletRequest request, String edit) throws Exception {
		String flag = request.getParameter("flag");
		// 视频主题
		String videoName = null;
		// 费用分类
		String[] paymentKbn = null;
		// 领域（技术领域）
		String[] field = null;
		// 提出日期
		String sDate = null;
		// 提出日期
		String eDate = null;
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbn = null;
		//从返回页面回到列表页
		if(StringUtil.isNotBlank(flag) && "0".equals(flag)){
			String selectKey = request.getParameter("selectKey");
			
			byte[] b = Base64.decode(selectKey);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			
			if(null != keyVO){
				videoName = keyVO.getVideoName();
				paymentKbn = keyVO.getPaymentKbn();
				field = keyVO.getField();
				sDate = keyVO.getsDate();
				eDate = keyVO.geteDate();
				recommendKbn = keyVO.getRecommendKbn();
			}
		}else{
			videoName = request.getParameter("selectKey.videoName");
			paymentKbn = request.getParameterValues("selectKey.paymentKbn");
			field = request.getParameterValues("selectKey.field");
			sDate = request.getParameter("selectKey.sDate");
			eDate = request.getParameter("selectKey.eDate");
			recommendKbn = request.getParameterValues("selectKey.recommendKbn");
		}
		
		VideoInfoVO record = new VideoInfoVO();
		Map<String, Object> showBackMap = new HashMap<String, Object>();
		
		record.setDeleteFlag(0);
		if (StringUtil.isNotBlank(videoName)) {
			record.setVideoName(videoName);
			showBackMap.put("videoName", videoName);
		}
		if (StringUtil.isNotBlank(paymentKbn) && StringUtil.isNotBlank(paymentKbn[0])) {
			record.setPaymentKbnDisp("'" + String.join("','", paymentKbn) + "'");
			showBackMap.put("paymentKbnDisp", String.join("','", paymentKbn));
		}
		if (StringUtil.isNotBlank(field) && StringUtil.isNotBlank(field[0])) {
			record.setField("'" + String.join("','", field) + "'");
			showBackMap.put("field", String.join("','", field));
		}

		showBackMap.put("videoType", ZzjConstants.VIDEO_TYPE_1);
		
		// 错误信息
		ValidateErrorException exception = null;
		SimpleDateFormat sdf = new SimpleDateFormat(ZzjConstants.datePattern);
		if (StringUtil.isNotBlank(edit)) {
			if (StringUtil.isNotBlank(eDate)) {
				showBackMap.put("eDate", eDate);
			}
			if (StringUtil.isNotBlank(sDate)) {
				showBackMap.put("sDate", sDate);
			}
		} else {
			if (StringUtil.isNotBlank(sDate)) {
				try {
					showBackMap.put("sDate", sdf.parse(sDate));
				} catch (ParseException e) {
					if (exception == null) {
						exception = new ValidateErrorException("E1000004", new Object[] { "创建日期(开始时间)", ZzjConstants.datePattern},
								"video/video_list", "sDate");
					}
				}
			}
			if (StringUtil.isNotBlank(eDate)) {
				try {
					showBackMap.put("eDate", sdf.parse(eDate));
				} catch (ParseException e) {
					if (exception == null) {
						exception = new ValidateErrorException("E1000004", new Object[] { "创建日期(结束时间)", ZzjConstants.datePattern},
								"video/video_list", "eDate");
					} else {
						exception.addError("E1000004", new Object[] { "创建日期(开始时间)", ZzjConstants.datePattern}, "eDate");
					}
				}
			}
		}
		if (StringUtil.isNotBlank(recommendKbn) && StringUtil.isNotBlank(recommendKbn[0])) {
			record.setRecommendKbn("'" + String.join("','", recommendKbn) + "'");
			showBackMap.put("recommendKbn", String.join("','", recommendKbn));
		}
		
		// 页面回显数据
		request.setAttribute("selectKey", showBackMap);

		// 查询当前页码
		String pageNo = request.getParameter("pageNo");
		String doSearch = request.getParameter("doSearch");
		if (!StringUtil.isNotBlank(doSearch)) {
			pageNo = request.getParameter("videoPageNo");
		}
		if (StringUtil.isNotBlank(pageNo)) {
			record.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			record.setPageNo(Integer.valueOf(pageNo));
			record.setDbIndex((Integer.valueOf(pageNo) - 1) * record.getPageSize());
			if (StringUtil.isNotBlank(edit))
			{
				request.setAttribute("videoPageNo", pageNo);
			}
		}
		else {
			record.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			record.setDbIndex(0);
			record.setPageNo(1);
		}
		// 有异常发生
		if (exception != null) {
			throw exception;
		} else {
			if (StringUtil.isNotBlank(sDate)) {
				record.setsDate(sdf.parse(sDate));
			}
			if (StringUtil.isNotBlank(eDate)) {
				record.seteDate(sdf.parse(eDate));
			}
		}
		return record;
	}
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 * @param edit 是否编辑
	 * @return LiveInfoVO 用于数据库查询
	 * @throws Exception
	 */
	@Override
	public LiveInfoVO selectKeyMapLive(HttpServletRequest request, String edit) throws Exception {
		String flag = request.getParameter("backFlag");
		// 视频主题
		String videoName = null;
		// 费用分类
		String[] paymentKbn = null;
		// 领域（技术领域）
		String[] field = null;
		// 提出日期
		String sDate = null;
		// 提出日期
		String eDate = null;
		//从返回页面回到列表页
		if(StringUtil.isNotBlank(flag) && "0".equals(flag)){
			String selectKey = request.getParameter("selectKey");
			
			byte[] b = Base64.decode(selectKey);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			
			if(null != keyVO){
				videoName = keyVO.getVideoName();
				paymentKbn = keyVO.getPaymentKbn();
				field = keyVO.getField();
				sDate = keyVO.getsDate();
				eDate = keyVO.geteDate();
			}
		}else{
			videoName = request.getParameter("selectKey.videoName");
			paymentKbn = request.getParameterValues("selectKey.paymentKbn");
			field = request.getParameterValues("selectKey.field");
			sDate = request.getParameter("selectKey.sDate");
			eDate = request.getParameter("selectKey.eDate");
		}
		
		LiveInfoVO record = new LiveInfoVO();
		Map<String, Object> showBackMap = new HashMap<String, Object>();
		
		record.setDeleteFlag(0);
		if (StringUtil.isNotBlank(videoName)) {
			record.setLiveName(videoName);
			showBackMap.put("videoName", videoName);
		}
		if (StringUtil.isNotBlank(paymentKbn) && StringUtil.isNotBlank(paymentKbn[0])) {
			record.setPaymentKbnDisp("'" + String.join("','", paymentKbn) + "'");
			showBackMap.put("paymentKbnDisp", String.join("','", paymentKbn));
		}
		if (StringUtil.isNotBlank(field) && StringUtil.isNotBlank(field[0])) {
			record.setField("'" + String.join("','", field) + "'");
			showBackMap.put("field", String.join("','", field));
		}
		
		showBackMap.put("videoType", ZzjConstants.VIDEO_TYPE_2);
		
		// 错误信息
		ValidateErrorException exception = null;
		SimpleDateFormat sdf = new SimpleDateFormat(ZzjConstants.datePattern);
		if (StringUtil.isNotBlank(edit)) {
			if (StringUtil.isNotBlank(eDate)) {
				showBackMap.put("eDate", eDate);
			}
			if (StringUtil.isNotBlank(sDate)) {
				showBackMap.put("sDate", sDate);
			}
		} else {
			if (StringUtil.isNotBlank(sDate)) {
				try {
					showBackMap.put("sDate", sdf.parse(sDate));
				} catch (ParseException e) {
					if (exception == null) {
						exception = new ValidateErrorException("E1000004", new Object[] { "创建日期(开始时间)", ZzjConstants.datePattern },
								"video/video_list", "sDate");
					}
				}
			}
			if (StringUtil.isNotBlank(eDate)) {
				try {
					showBackMap.put("eDate", sdf.parse(eDate));
				} catch (ParseException e) {
					if (exception == null) {
						exception = new ValidateErrorException("E1000004", new Object[] { "创建日期(结束时间)", ZzjConstants.datePattern },
								"video/video_list", "eDate");
					} else {
						exception.addError("E1000004", new Object[] { "创建日期(开始时间)", ZzjConstants.datePattern }, "sDate");
					}
				}
			}
		}
		
		// 页面回显数据
		request.setAttribute("selectKey", showBackMap);
		
		// 查询当前页码
		String pageNo = request.getParameter("pageNo");
		String doSearch = request.getParameter("doSearch");
		if (!StringUtil.isNotBlank(doSearch)) {
			pageNo = request.getParameter("videoPageNo");
		}
		if (StringUtil.isNotBlank(pageNo)) {
			record.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			record.setPageNo(Integer.valueOf(pageNo));
			record.setDbIndex((Integer.valueOf(pageNo) - 1) * record.getPageSize());
			if (StringUtil.isNotBlank(edit))
			{
				request.setAttribute("videoPageNo", pageNo);
			}
		}
		else {
			record.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
			record.setDbIndex(0);
			record.setPageNo(1);
		}
		// 有异常发生
		if (exception != null) {
			throw exception;
		} else {
			if (StringUtil.isNotBlank(sDate)) {
				record.setsDate(sdf.parse(sDate));
			}
			if (StringUtil.isNotBlank(eDate)) {
				record.seteDate(sdf.parse(eDate));
			}
		}
		return record;
	}
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public void selectKeySave(HttpServletRequest request) throws Exception {
		String flag = request.getParameter("flag");
		String live = request.getParameter("live");
		// 视频主题
		String videoName = null;
		// 费用
		String[] paymentKbnDisp = null;
		// 领域（技术领域）
		String[] field = null;
		// 视频分类
		String videoType = null;
		// 提出日期
		String sDate = null;
		// 结束日期
		String eDate = null;
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbn = null;
		// 查询当前页码
		String pageNo = null;
		//保存
		if(StringUtil.isNotBlank(flag) && "1".equals(flag)){
			String selectKey = request.getParameter("selectKey");
			byte[] b = Base64.decode(selectKey);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			if(null != keyVO){
				videoName = keyVO.getVideoName();
				paymentKbnDisp = keyVO.getPaymentKbn();
				field = keyVO.getField();
				videoType = keyVO.getVideoType();
				sDate = keyVO.getsDate();
				eDate = keyVO.geteDate();
				recommendKbn = keyVO.getRecommendKbn();
				pageNo = request.getParameter("videoPageNo");
			}
		}else{
			videoName = request.getParameter("selectKey.videoName");
			paymentKbnDisp = request.getParameterValues("selectKey.paymentKbn");
			field = request.getParameterValues("selectKey.field");
			if(StringUtil.isNotBlank(live) && "live".equals(live)){
				videoType = ZzjConstants.VIDEO_TYPE_2;
			}else{
				videoType = ZzjConstants.VIDEO_TYPE_1;
			}
			sDate = request.getParameter("selectKey.sDate");
			eDate = request.getParameter("selectKey.eDate");
			recommendKbn = request.getParameterValues("selectKey.recommendKbn");
			pageNo = request.getParameter("pageNo");
			String doSearch = request.getParameter("doSearch");
			if (!StringUtil.isNotBlank(doSearch)) {
				pageNo = request.getParameter("videoPageNo");
			}
		}
		
//		Map<String, Object> showBackMap = new HashMap<String, Object>();
		KeyVO vo = new KeyVO();
		
		if (StringUtil.isNotBlank(videoName)) {
//			showBackMap.put("videoName", videoName);
			vo.setVideoName(videoName);
		}
		if (StringUtil.isNotBlank(paymentKbnDisp)) {
//			showBackMap.put("answerStatus", paymentKbnDisp);
			vo.setPaymentKbn(paymentKbnDisp);
		}
		if (StringUtil.isNotBlank(field)) {
//			showBackMap.put("field", field);
			vo.setField(field);
		}
		if (StringUtil.isNotBlank(videoType)) {
//			showBackMap.put("videoType", videoType);
			vo.setVideoType(videoType);
		}
		if (StringUtil.isNotBlank(eDate)) {
//			showBackMap.put("eDate", eDate);
			vo.seteDate(eDate);
		}
		if (StringUtil.isNotBlank(sDate)) {
//			showBackMap.put("sDate", eDate);
			vo.setsDate(sDate);
		}
		if (StringUtil.isNotBlank(recommendKbn)) {
//			showBackMap.put("recommendKbn", recommendKbn);
			vo.setRecommendKbn(recommendKbn);
		}
		//序列化
		byte[] b = SerializUtils.serializ(vo);  
		String selectKey = Base64.encode(b);
		// 页面回显数据
		request.setAttribute("selectKey", selectKey);
		
		if (StringUtil.isNotBlank(pageNo)) {
			request.setAttribute("videoPageNo", pageNo);
		}
		
	}
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 * @param pagging 分页标识
	 * @return VideoInfoVO 用于数据库查询
	 * @throws Exception 
	 */
	@Override
	public VideoInfoVO getSaveVideoInfo(HttpServletRequest request, String pagging) throws Exception {

		// 视频编码
		String videoCd = request.getParameter("videoInfo.videoCd");
		// 视频主题
		String videoName = request.getParameter("videoInfo.videoName");
		// 视频介绍
		String videoBrief = request.getParameter("videoInfo.videoBrief");
		// 关键词
		String fieldNameForKeyWord = request.getParameter("fieldNameForKeyWord");
		// 视频地址
		String videoAddress = request.getParameter("videoInfo.videoAddress");
		// 价格
		String price = request.getParameter("videoInfo.price");
		// 免费视频地址
		String freeAddress = request.getParameter("videoInfo.freeAddress");
		// 列表图片
		String littleIcon = request.getParameter("littleIcon");
		// 详情图片
		String bigIcon = request.getParameter("bigIcon");

		// 错误信息
		ValidateErrorException exception = null;
		// 获得更新人的id，并创建更新时间
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		String updateId = user.getUserId();
		Date updateTime = new Date();

		// 保存信息作成
		VideoInfoVO record = new VideoInfoVO();
		record.setDeleteFlag(0);
		// 更新日
		record.setUpdateTime(new Date());
		// 更新者
		record.setUpdateId(updateId);

		if (StringUtil.isNotBlank(videoCd)) {
			record.setVideoCd(videoCd);
		}
		if (StringUtil.isNotBlank(videoName)) {
			if (videoName.length() > 100) {
				exception = new ValidateErrorException("E1000005", new Object[] { "视频主题","100个" }, "video/video_edit", "videoName");
			} else {
				record.setVideoName(videoName);
			}
		} else {
			exception = new ValidateErrorException("E1000001", new Object[] { "视频主题" }, "video/video_edit",
					"videoName");
		}
		// 领域
		String[] fieldCd = request.getParameterValues("fieldCd");
		if (StringUtil.isNotBlank(fieldCd)) {
			record.setField(String.join(",", fieldCd));
		}
		List<TopicFieldInfo> fieldInfo = null;
		List<TopicFieldInfoKey> fieldInfoKey = null;
		if (fieldCd != null && fieldCd.length > 0) {
			fieldInfo = new ArrayList<TopicFieldInfo>();
			fieldInfoKey = new ArrayList<TopicFieldInfoKey>();
			for (String code : fieldCd) {
				// 此时codes的length应该为2，如果不是2，则是垃圾数据，不予保存
				String[] codes = code.split("-");
				if (codes.length != 2) {
					continue;
				}
				TopicFieldInfo topicFieldInfo = new TopicFieldInfo();
				topicFieldInfo.setBusiType(ZzjConstants.BUSI_TYPE_03);
				topicFieldInfo.setUpdateId(updateId);
				topicFieldInfo.setUpdateTime(updateTime);
				topicFieldInfo.setDeleteFlag(0);
				topicFieldInfo.setTopicCd(record.getVideoCd());
				topicFieldInfo.setTechFieldCd(codes[0]);
				topicFieldInfo.setRschFieldCd(codes[1]);
				fieldInfo.add(topicFieldInfo);
				fieldInfoKey.add(topicFieldInfo);
			}
		} else {
			exception = this.rebuildException(exception,"E1000001",new Object[] { "领域" }, "video/video_edit", "choose_sel");
		}
		record.setFieldInfo(fieldInfo);
		if (StringUtil.isNotBlank(videoBrief)) {
			record.setVideoBrief(videoBrief);
		} else {
			exception = this.rebuildException(exception,"E1000001",new Object[] { "视频介绍" }, "video/video_edit", "videoBrief");
		}
		// 关键词
		if (StringUtil.isNotBlank(fieldNameForKeyWord) && StringUtil.isNotBlank(videoName)
				&& StringUtil.isNotBlank(videoBrief)) {
			fieldNameForKeyWord = fieldNameForKeyWord.replace(",", "").replace("->", "");
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append(fieldNameForKeyWord).append("#").append(videoName);
			record.setKeyword(sBuffer.toString());
		}

		if (StringUtil.isNotBlank(littleIcon)) {
			record.setLittleIcon(littleIcon);
			record.setLittleIconUrl(StringUtil.getImageURL(littleIcon));
		}
		if (StringUtil.isNotBlank(bigIcon)) {
			record.setBigIcon(bigIcon);
			record.setBigIconUrl(StringUtil.getImageURL(bigIcon));
		}
		
		if (StringUtil.isNotBlank(videoAddress)) {
			record.setVideoAddress(videoAddress);
		} else {
			exception = this.rebuildException(exception,"E1000001",new Object[] { "e视频地址" }, "video/video_edit", "videoAddress");
		}
		
		// 免费时长及价格校验
		if (ZzjConstants.PAYMENT_KBN_1.equals(request.getParameter("paymentKbn"))) {
			record.setPaymentKbn(Integer.parseInt(ZzjConstants.PAYMENT_KBN_1));
			if (StringUtil.isNotBlank(price)) {
				record.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
			} else {
				exception = this.rebuildException(exception,"E1000001",new Object[] { "价格" }, "video/video_edit", "chargeInput");
			}
			if (StringUtil.isNotBlank(freeAddress)) {
				record.setFreeAddress(freeAddress);
			} else {
				exception = this.rebuildException(exception,"E1000001",new Object[] { "免费视频地址" }, "video/video_edit", "timeInput");
			}
		} else {
			record.setPaymentKbn(Integer.parseInt(ZzjConstants.PAYMENT_KBN_0));
			record.setPrice(null);
			record.setFreeTime(null);
		}
		// 置顶
		String toTopCode = request.getParameter("toTopCode");
		// 推荐
		String recommend = request.getParameter("recommend");
		// 推荐语
		String recommendMsg = request.getParameter("videoInfo.recommendMsg");
		if (StringUtil.isNotBlank(recommend) || StringUtil.isNotBlank(toTopCode)) {
			record.setRecommendKbn(recommend + "," + toTopCode);
		}
		if (StringUtil.isNotBlank(recommend)) {
			if (StringUtil.isNotBlank(recommendMsg) && !ZzjConstants.DEFAULT_RECOMMEND_MSG.equals(recommendMsg)) {
				record.setRecommendMsg(recommendMsg);
			}
		}
		if (StringUtil.isNotBlank(toTopCode)) {
			// 置顶信息
			RecommendInfo toTopInfo = new RecommendInfo();
			toTopInfo.setBusiType(ZzjConstants.BUSI_TYPE_03);
			toTopInfo.setTopicCd(record.getVideoCd());
			toTopInfo.setUpdateId(updateId);
			toTopInfo.setUpdateTime(updateTime);
			toTopInfo.setDeleteFlag(0);
			toTopInfo.setRecommendKbn(Integer.parseInt(toTopCode));
			record.setToTopInfo(toTopInfo);
			record.setTopFlag(ZzjConstants.TOP_FLG_1);			
			if (recommendInfoBlo.isToTopRecommendCount(toTopInfo, ZzjConstants.RECOMMEND_KBN_2))
			{
				exception = this.rebuildException(exception, "E1000027", new Object[] {ZzjConstants.TO_TOP_NUM}, "video/video_edit", "toTop");
			}
		} else {
			record.setTopFlag(ZzjConstants.TOP_FLG_0);
		}

		if (StringUtil.isNotBlank(recommend)) {
			// 推荐信息
			RecommendInfo recommendInfo = new RecommendInfo();
			recommendInfo.setBusiType(ZzjConstants.BUSI_TYPE_03);
			recommendInfo.setTopicCd(record.getVideoCd());
			recommendInfo.setUpdateId(updateId);
			recommendInfo.setUpdateTime(updateTime);
			recommendInfo.setDeleteFlag(0);
			recommendInfo.setRecommendKbn(Integer.parseInt(recommend));
			recommendInfo.setRecommendMemo(record.getRecommendMsg());
			record.setRecommendInfo(recommendInfo);
			if (recommendInfoBlo.isToTopRecommendCount(recommendInfo, ZzjConstants.RECOMMEND_KBN_1))
			{
				exception = this.rebuildException(exception, "E1000028", new Object[] {ZzjConstants.RECOMMEND_NUM}, "video/video_edit", "recommend");
			}
		}

		// 回答列表翻页,或者,有异常发生
		if (exception != null || StringUtil.isNotBlank(pagging)) {
			record.setFieldCd(fieldInfoKey);
			// 获得不属于该视频的领域
			record.setOtherFieldCd(this.getOtherTopicFieldInfo(fieldInfoKey));
			request.setAttribute("videoInfo", record);
			if (exception != null) {
				throw exception;
			}
		}

		return record;
	}
	
	/**
	 * 重构ValidateErrorException实例。<br/>
	 * @param  exception ValidateErrorException
	 * @param  errorCode 异常消息代码
	 * @param  msgArgs 异常消息参数
	 * @param  errorPage 错误页面
	 * @param  errorItemKey 错误项目ID
	 */
	private ValidateErrorException rebuildException (ValidateErrorException exception, String errorCode, Object[] msgArgs, String errorPage, String errorItemKey) {
		if (exception == null) {
			exception = new ValidateErrorException(errorCode, msgArgs, errorPage, errorItemKey);
		} else {
			exception.addError(errorCode, msgArgs, errorItemKey);
		}
		return exception;
	}
	
}

