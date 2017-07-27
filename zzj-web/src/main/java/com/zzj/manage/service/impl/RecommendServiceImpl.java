/**
 * Project Name:zzj-web
 * File Name:RecommendService.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzj.db.blo.CourseInfoBlo;
import com.zzj.db.blo.ExpertInfoBlo;
import com.zzj.db.blo.MstUsersInfoBlo;
import com.zzj.db.blo.NeedsInfoBlo;
import com.zzj.db.blo.NewsInfoBlo;
import com.zzj.db.blo.QuestionInfoBlo;
import com.zzj.db.blo.RecommendInfoBlo;
import com.zzj.db.blo.TopicFieldInfoBlo;
import com.zzj.db.blo.VideoInfoBlo;
import com.zzj.db.dto.CourseInfo;
import com.zzj.db.dto.ExpertInfo;
import com.zzj.db.dto.NeedsInfo;
import com.zzj.db.dto.NewsInfo;
import com.zzj.db.dto.QuestionInfo;
import com.zzj.db.dto.RecommendInfo;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.dto.VideoInfo;
import com.zzj.db.model.RecommendVO;
import com.zzj.manage.service.RecommendService;
import com.zzj.util.DateUtil;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>RecommendService <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装那个哪个模块，起什么用的，需要手动填写. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月28日下午3:36:54 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class RecommendServiceImpl implements RecommendService {

	/**
	 * 推荐置顶模块业务数据库操作类
	 */
	@Autowired
	private RecommendInfoBlo recommendInfoBlo;
	/**
	 * 主题领域表数据库操作类
	 */
	@Autowired
	private TopicFieldInfoBlo topicFieldInfoBlo;
	/**
	 *专家表业务数据库操作类
	 */
	@Autowired
	private ExpertInfoBlo expertInfoBlo;
	/**
	 *知识表业务数据库操作类
	 */
	@Autowired
	private NewsInfoBlo newsInfoBlo;
	/**
	 *e视频表业务数据库操作类
	 */
	@Autowired
	private VideoInfoBlo videoInfoBlo;
	/**
	 *e课堂表业务数据库操作类
	 */
	@Autowired
	private CourseInfoBlo courseInfoBlo;
	/**
	 *e问表业务数据库操作类
	 */
	@Autowired
	private QuestionInfoBlo questionInfoBlo;
	/**
	 *需求表业务数据库操作类
	 */
	@Autowired
	private NeedsInfoBlo needsInfoBlo;
	/**
	 *后台用户数据库操作类
	 */
	@Autowired
	private MstUsersInfoBlo mstUsersInfoBlo;
	
	/**
	 * 根据请求实例将消息置顶操作
	 * @param  request 请求实例，包含更新的信息及操作人的信息
	 * @param  code 操作分类： 1：置顶操作， 2：取消置顶操作， 3：推荐操作， 4：取消推荐操作
	 * @return String  执行操作的结果
	 * @author 任晓茂
	 */
	@Override
	public String recommendExecute(HttpServletRequest request, Integer code) {
		// 非法请求
		if (code < 1 || code > 4) {
			return "error";
		}
		//解析request，得到操作需要的参数
		RecommendInfo recommendInfo = this.parseRequestForRecommendExecute(request, code);
		if (recommendInfo == null)
		{
			return PropertyUtil.getMessageContent("E1000001", new Object[] {"推荐语"});
		}
		// 1：置顶操作
		if (code == 1)
		{
			if (recommendInfoBlo.isToTopRecommendCount(recommendInfo, ZzjConstants.RECOMMEND_KBN_2))
			{
				return PropertyUtil.getMessageContent("E1000027", new Object[] {ZzjConstants.TO_TOP_NUM});
			}
		}
		// 3：推荐操作
		if (code == 3)
		{
			if (recommendInfoBlo.isToTopRecommendCount(recommendInfo, ZzjConstants.RECOMMEND_KBN_1))
			{
				return PropertyUtil.getMessageContent("E1000028", new Object[] {ZzjConstants.RECOMMEND_NUM});
			}
		}
		// 判断是够存在，若果存在执行更新操作，如果不存在执行插入操作
		RecommendInfo record = recommendInfoBlo.selectByPrimaryKey(recommendInfo);
		int count = 0;
		if (record == null) {
			// 插入操作
			count = recommendInfoBlo.addRecommendInfo(recommendInfo);
		} else {
			// 更新操作
			count = recommendInfoBlo.saveRecommendInfo(recommendInfo);
		}
		
		if (code == 1 || code == 2)
		{
			switch (Integer.parseInt(recommendInfo.getBusiType())) {
				case 1:
					// 专家
					ExpertInfo expertInfo = new ExpertInfo();
					expertInfo.setExpertId(recommendInfo.getTopicCd());
					if (code == 1)
					{
						expertInfo.setTopFlag(ZzjConstants.TOP_FLG_1);
					}
					else {						
						expertInfo.setTopFlag(ZzjConstants.TOP_FLG_0);
					}
					expertInfo.setUpdateId(recommendInfo.getUpdateId());
					expertInfo.setUpdateTime(recommendInfo.getUpdateTime());
					count = expertInfoBlo.updateByPrimaryKeySelective(expertInfo);			
					break;
				case 2:
					// 资讯
					NewsInfo newsInfo = new NewsInfo();
					newsInfo.setNewsCd(recommendInfo.getTopicCd());
					if (code == 1)
					{
						newsInfo.setTopFlag(ZzjConstants.TOP_FLG_1);
					}
					else {						
						newsInfo.setTopFlag(ZzjConstants.TOP_FLG_0);
					}
					newsInfo.setUpdateId(recommendInfo.getUpdateId());
					newsInfo.setUpdateTime(recommendInfo.getUpdateTime());
					count = newsInfoBlo.saveNewsInfo(newsInfo);
					break;
				case 3:
					// 视频
					String videoType = request.getParameter("videoType");
					if (ZzjConstants.VIDEO_TYPE_1.equals(videoType)) {
						VideoInfo videoInfo = new VideoInfo();
						videoInfo.setVideoCd(recommendInfo.getTopicCd());
						if (code == 1)
						{
							videoInfo.setTopFlag(ZzjConstants.TOP_FLG_1);
						}
						else {						
							videoInfo.setTopFlag(ZzjConstants.TOP_FLG_0);
						}
						videoInfo.setUpdateId(recommendInfo.getUpdateId());
						videoInfo.setUpdateTime(recommendInfo.getUpdateTime());
						count = videoInfoBlo.updateByPrimaryKeySelective(videoInfo);
					}
					if (ZzjConstants.VIDEO_TYPE_2.equals(videoType)) {
						count = 1;// 直播表没有topFlag，所以不需更新
					}
					break;
				case 4:
					// 课堂
					CourseInfo courseInfo = new CourseInfo();
					courseInfo.setCourseCd(recommendInfo.getTopicCd());
					if (code == 1)
					{
						courseInfo.setTopFlag(ZzjConstants.TOP_FLG_1);
					}
					else {						
						courseInfo.setTopFlag(ZzjConstants.TOP_FLG_0);
					}
					courseInfo.setUpdateId(recommendInfo.getUpdateId());
					courseInfo.setUpdateTime(recommendInfo.getUpdateTime());
					count = courseInfoBlo.updateByPrimaryKeySelective(courseInfo);			
					break;
				case 5:
					// E问
					QuestionInfo questionInfo = new QuestionInfo();
					questionInfo.setQuestionCd(recommendInfo.getTopicCd());
					if (code == 1)
					{
						questionInfo.setTopFlag(ZzjConstants.TOP_FLG_1);
					}
					else {						
						questionInfo.setTopFlag(ZzjConstants.TOP_FLG_0);
					}
					questionInfo.setUpdateId(recommendInfo.getUpdateId());
					questionInfo.setUpdateTime(recommendInfo.getUpdateTime());
					count = questionInfoBlo.updateByPrimaryKeySelective(questionInfo);
					break;
				case 6:
					// 需求
					NeedsInfo needsInfo = new NeedsInfo();
					needsInfo.setNeedsCd(recommendInfo.getTopicCd());
					if (code == 1)
					{
						needsInfo.setTopFlag(ZzjConstants.TOP_FLG_1);
					}
					else {						
						needsInfo.setTopFlag(ZzjConstants.TOP_FLG_0);
					}
					needsInfo.setUpdateId(recommendInfo.getUpdateId());
					needsInfo.setUpdateTime(recommendInfo.getUpdateTime());
					count = needsInfoBlo.updateByPrimaryKeySelective(needsInfo);
					break;
	
				default:
					break;
			}
		}
		return (count > 0) ? "": "error";
	}
	
	/**
	 * 解析request，得到操作需要的参数。
	 * 必须有的参数：RecommendInfoKey(busiType,recommendKbn,topicCd)。<br/>
	 * 其他可能的参数信息：recommendMemo,updateId,updateTime。<br/>
	 * @param  request 请求实例，包含更新的信息及操作人的信息
	 * @param  code 操作分类： 1：置顶操作， 2：取消置顶操作， 3：推荐操作， 4：取消推荐操作 
	 * @return Map<String, Object>  操作需要的参数
	 */
	private RecommendInfo parseRequestForRecommendExecute(HttpServletRequest request, Integer code) {
		if (request == null) {
			return null;
		}
		RecommendInfo recommendInfo = new RecommendInfo();
		String updateId = request.getParameter("updateId");
		String busiType = request.getParameter("busiType");
		String topicCd = request.getParameter("topicCd");
		String recommendMemo = request.getParameter("recommendMemo");
		// 专家推荐必须输入推荐语
		if (code == 3 && StringUtil.isBlank(recommendMemo) && ZzjConstants.BUSI_TYPE_01.equals(busiType)) {
			return null;
		}
		Date updateTime = new Date();
		Integer recommendKbn = 1; // 推荐区分 1：推荐，2：置顶
		if (code < 3) {// code 操作分类： 1：置顶操作， 2：取消置顶操作， 3：推荐操作， 4：取消推荐操作
			recommendKbn = 2;
		}
		Integer deleteFlag = 0;
		if (code == 2 || code == 4) {
			deleteFlag = 1;
		}
		recommendInfo.setBusiType(busiType);
		recommendInfo.setDeleteFlag(deleteFlag);
		recommendInfo.setRecommendKbn(recommendKbn);
		recommendInfo.setRecommendMemo(recommendMemo);
		recommendInfo.setTopicCd(topicCd);
		recommendInfo.setUpdateId(updateId);
		recommendInfo.setUpdateTime(updateTime);
		return recommendInfo;
	}
	/**
	 * 根据busi机能字段和recommendKbn推荐置顶查询对应记录
	 * @param recommendKbn 推荐或置顶 
	 * @param busi 机能
	 * @return List<RecommendVO> 查询结果集
	 */
	@Override
	public List<RecommendVO> selectRecommends(String recommendKbn, String busi) {
		// 定义List<RecommendVO 作为查询结果
		List<RecommendVO> recommendVOs = new ArrayList<>();
		// 定义RecommendInfo作为`查询条件
		RecommendInfo recommendInfo = new RecommendInfo();
		// 为查询条件赋属性
		recommendInfo.setBusiType(busi);
		recommendInfo.setRecommendKbn(Integer.parseInt(recommendKbn));
		// 根据查询条件查询对应
		List<RecommendInfo> recommendInfos = recommendInfoBlo.selectByRecommendKbnAndBusi(recommendInfo);
		// 遍历recommendInfos得到每条记录对应领域
		for(int i = 0; i < recommendInfos.size(); i++){
			RecommendVO 	resultVO = new RecommendVO();
			// 根据 busiType和 topicCd两个字段去topicFieldInfo里查询领域
			Map<String, String> map = new HashMap<String, String>();
			map.put("busiType",recommendInfos.get(i).getBusiType() );
			map.put("topicCd", recommendInfos.get(i).getTopicCd());
			List<TopicFieldInfoKey> topicFieldInfoKeys  = topicFieldInfoBlo.selectByTypeAndCode(map);
			// 根据updateId得到updateName
			String updateName = mstUsersInfoBlo.getUserName(recommendInfos.get(i).getUpdateId());
			resultVO.setRecommendInfoResultfields(topicFieldInfoKeys);
			resultVO.setBusiType(busi);
			resultVO.setResultUpdateName(updateName);
			String updateTime = DateUtil.getDate(recommendInfos.get(i).getUpdateTime());
			resultVO.setResultUpdateTime(updateTime);
			recommendVOs.add(resultVO);
		}
		// 通过busi判断去对应表取得每条记录对应内容和收费
		// 业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告
		switch (Integer.parseInt(busi)) {
		case 1:
			for(int i = 0; i < recommendInfos.size(); i++){
				// 定义ExpertInfo作为查询结果
				ExpertInfo expertInfo = new ExpertInfo();
				// 调用blo查询
				expertInfo = expertInfoBlo.selectByPrimaryKey(recommendInfos.get(i).getTopicCd());
				if(expertInfo != null){
					recommendVOs.get(i).setTopicCd(expertInfo.getExpertId());
					recommendVOs.get(i).setRecommendInfoTopic(expertInfo.getExpertName());
					recommendVOs.get(i).setRecommendInfoPayment(-1);
				}
				
				
			}
	
			break;
		case 2:
			for(int i = 0; i < recommendInfos.size(); i++){
				// 定义NewsInfo作为查询结果
				NewsInfo newsInfo = new NewsInfo();
				// 调用blo查询
				newsInfo = newsInfoBlo.getNewsInfo(recommendInfos.get(i).getTopicCd());
				if(newsInfo != null){
					recommendVOs.get(i).setTopicCd(newsInfo.getNewsCd());
					recommendVOs.get(i).setRecommendInfoTopic(newsInfo.getNewsName());
					recommendVOs.get(i).setRecommendInfoPayment(newsInfo.getPaymentKbn());
				}
				
				
			}
			break;
		case 3:
			for(int i = 0; i < recommendInfos.size(); i++){
				// 定义VideoInfo作为查询结果
				VideoInfo videoInfo = new VideoInfo();
				// 调用blo查询
				videoInfo = videoInfoBlo.selectByPrimaryKey(recommendInfos.get(i).getTopicCd());
				if(videoInfo != null){
					recommendVOs.get(i).setTopicCd(videoInfo.getVideoCd());
					recommendVOs.get(i).setRecommendInfoTopic(videoInfo.getVideoName());
					recommendVOs.get(i).setRecommendInfoPayment(videoInfo.getPaymentKbn());
				}
				
			}
			break;
		case 4:
			for(int i = 0; i < recommendInfos.size(); i++){
				// 定义CourseInfo作为查询结果
				CourseInfo courseInfo = new CourseInfo();
				// 调用blo查询
				courseInfo = courseInfoBlo.selectByPrimaryKey(recommendInfos.get(i).getTopicCd());
				if(courseInfo != null){
					recommendVOs.get(i).setTopicCd(courseInfo.getCourseCd());
					recommendVOs.get(i).setRecommendInfoTopic(courseInfo.getCourseName());
					recommendVOs.get(i).setRecommendInfoPayment(courseInfo.getPaymentKbn());
				}
				
			}
	
			break;
		case 5:
			for(int i = 0; i < recommendInfos.size(); i++){
				// 定义QuestionInfo作为查询结果
				QuestionInfo questionInfo = new QuestionInfo();
				// 调用blo查询
				questionInfo = questionInfoBlo.selectByPrimaryKey(recommendInfos.get(i).getTopicCd());
				if(questionInfo != null){
					recommendVOs.get(i).setTopicCd(questionInfo.getQuestionCd());
					recommendVOs.get(i).setRecommendInfoTopic(questionInfo.getQuestionName());
					recommendVOs.get(i).setRecommendInfoPayment(-1);
				}
				
				}
			break;
		case 6:
			for(int i = 0; i < recommendInfos.size(); i++){
				// 定义NeedsInfo作为查询结果
				NeedsInfo needsInfo = new NeedsInfo();
				// 调用blo查询
				needsInfo = needsInfoBlo.selectByPrimaryKey(recommendInfos.get(i).getTopicCd());
				if(needsInfo != null){
					recommendVOs.get(i).setTopicCd(needsInfo.getNeedsCd());
					recommendVOs.get(i).setRecommendInfoTopic(needsInfo.getNeedsName());
					recommendVOs.get(i).setRecommendInfoPayment(-1);
				}
				
				}
			break;

		default:
			break;
		}
		// 去重复
		List<RecommendVO> RecommendVOList =new ArrayList<>();
		for(int j = 0; j < recommendVOs.size(); j++){
			if(recommendVOs.get(j).getTopicCd() !=  null){
				boolean flag = true;
				for(int k = j+1;k<recommendVOs.size();k++){
					if(recommendVOs.get(k).getTopicCd() !=null){
						if(recommendVOs.get(j).getTopicCd().equals(recommendVOs.get(k).getTopicCd()))
						{flag = false;}
					}
					
				}
				if(flag){
					RecommendVOList.add(recommendVOs.get(j));
				}
			}
			
		}
		return RecommendVOList;
	}
	
	/**
	 * 取得推荐语。
	 * @param  request http请求实例
	 * @return 推荐语
	 */
	@Override
	public String selectRecommendMsg(HttpServletRequest request) {
		
		// 根据查询条件查询对应
		RecommendInfo recommendInfo = new RecommendInfo();
		String busiType = request.getParameter("busiType");
		String topicCd = request.getParameter("topicCd");
		if (StringUtil.isNotBlank(busiType))
		{
			recommendInfo.setBusiType(busiType);
		}
		if (StringUtil.isNotBlank(topicCd))
		{
			recommendInfo.setTopicCd(topicCd);
		}
		String recommendMsg = recommendInfoBlo.selectRecommendMsg(recommendInfo);
		return StringUtil.nullToBlank(recommendMsg);
	}
	
	/**
	 * 批量取消方法：根据主键和推荐置顶状态更改相应记录删除状态<br/>
	 * @param  request http请求实例
	 * @param  topicCd 主题编码
	 * @param  kbn 区分
	 * @param  updateId 更新着ID
	 * @param  busiType 业务类型
	 * @return Integer 执行结果
	 * @author 刘研
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public Integer delRecommends(String topicCd, String kbn,String updateId,String busiType) {
		// 实例化 RecommendInfo作为修改条件
		RecommendInfo recommendInfo = new RecommendInfo();
		// 为修改条件赋值
		recommendInfo.setRecommendKbn(Integer.parseInt(kbn));
		recommendInfo.setTopicCd(topicCd);
		recommendInfo.setUpdateId(updateId);
		recommendInfo.setUpdateTime(new Date());
		recommendInfo.setBusiType(busiType);
		recommendInfo.setDeleteFlag(1);
		// 调用Blo进行修改
		Integer x = recommendInfoBlo.updateByTopicAndKbn(recommendInfo);
		return x;
	}
	/**
	 * 保存排序方法：根据逐渐和推荐置顶状态更改相应记录显示顺序<br/>
	 * @param  request http请求实例
	 * @param  topicCd 主题编码
	 * @param  kbn 区分
	 * @param  busiType 业务类型
	 * @param  sortNo 表示顺
	 * @return Integer 执行结果
	 * @author   刘研
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public Integer saveSortNo(String topicCd, String kbn, String updateId, String busiType, String sortNo) {
		// 实例化 RecommendInfo作为修改条件
		RecommendInfo recommendInfo = new RecommendInfo();
		// 为修改条件赋值
		recommendInfo.setRecommendKbn(Integer.parseInt(kbn));
		recommendInfo.setTopicCd(topicCd);
		recommendInfo.setUpdateId(updateId);
		recommendInfo.setUpdateTime(new Date());
		recommendInfo.setBusiType(busiType);
		recommendInfo.setSortNo((byte) Integer.parseInt(sortNo));
		// 调用Blo进行修改
		Integer x = recommendInfoBlo.updateByTopicAndKbn(recommendInfo);
		return x;
	}

}

