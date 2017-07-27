/**
 * Project Name:zzj-web
 * File Name:SlideServiceImpl.java
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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zzj.core.exception.BusinessException;
import com.zzj.db.blo.CommercialInfoBlo;
import com.zzj.db.blo.CourseInfoBlo;
import com.zzj.db.blo.ExpertInfoBlo;
import com.zzj.db.blo.LiveInfoBlo;
import com.zzj.db.blo.MstCodeInfoBlo;
import com.zzj.db.blo.MstUsersInfoBlo;
import com.zzj.db.blo.NeedsInfoBlo;
import com.zzj.db.blo.NewsInfoBlo;
import com.zzj.db.blo.QuestionInfoBlo;
import com.zzj.db.blo.SlideShowInfoBlo;
import com.zzj.db.blo.TopicFieldInfoBlo;
import com.zzj.db.blo.VideoInfoBlo;
import com.zzj.db.dto.CommercialInfo;
import com.zzj.db.dto.CourseInfo;
import com.zzj.db.dto.ExpertInfo;
import com.zzj.db.dto.LiveInfo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.NeedsInfo;
import com.zzj.db.dto.NewsInfo;
import com.zzj.db.dto.QuestionInfo;
import com.zzj.db.dto.SlideShowInfo;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.dto.VideoInfo;
import com.zzj.db.model.PageResult;
import com.zzj.db.model.SlideResultVO;
import com.zzj.db.model.SlideVO;
import com.zzj.manage.service.SlideService;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>SlideServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>轮播管理模块相关业务逻辑处理实现类. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月28日下午4:03:12 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class SlideServiceImpl implements SlideService {

	/**
	 *Slide业务数据库操作类
	 */
	@Autowired
	private SlideShowInfoBlo slideShowInfoBlo;
	/**
	 *User业务数据库操作类
	 */
	@Autowired
	private MstUsersInfoBlo mstUsersInfoBlo;
	/**
	 *TopicFieldInfo业务数据库操作类
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
	 *直播表业务数据库操作类
	 */
	@Autowired
	private LiveInfoBlo liveInfoBlo;

	/**
	 *e课堂表业务数据库操作类
	 */
	@Autowired
	private CourseInfoBlo courseInfo;
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
	 *广告表业务数据库操作类
	 */
	@Autowired
	private CommercialInfoBlo commercialInfoBlo;
	/**
	 *系统配置数据库操作类
	 */
	@Autowired
	private MstCodeInfoBlo mstCodeInfo;
	/**
	 * 根据条件查询Slide_Show_Info表中相应记录<br/>
	 * @return List<SlideVO>  Slide_Show_Info表记录集
	 */
	@Override
	public List<SlideVO> selectAllSlides() {
		// 定义结果集合
		List<SlideVO> slideVOs =new ArrayList<>();
		// 调用Blo查询数据
		List<SlideShowInfo> SlideShowInfos = slideShowInfoBlo.selectAllSlides();
		// 将查询结果集转为返回结果集
		for(SlideShowInfo slideShowInfo :  SlideShowInfos){
			if(slideShowInfo != null) {
				SlideVO slideVO = new SlideVO();
				BeanUtils.copyProperties(slideShowInfo, slideVO);
				slideVOs.add(slideVO);
			} 
		}
		// 便利slideVOs查询每条记录对应的需显示属性
		for(SlideVO slideVO : slideVOs){
			//  根据updateId属性向MstUsrInfo表查询UpdateName属性
			String updateName = mstUsersInfoBlo.getUserName(slideVO.getUpdateId()); 
			// 根据 busiType和 topicCd两个字段去topicFieldInfo里查询结果
			Map<String, String> map = new HashMap<String, String>();
			map.put("busiType", slideVO.getBusiType());
			map.put("topicCd", slideVO.getTopicCd());
			List<TopicFieldInfoKey> topicFieldInfoKeys  = topicFieldInfoBlo.selectByTypeAndCode(map);
			// 将查询结果赋予slideVO
			if(updateName != null){
				slideVO.setUpdateName(updateName);
			}
			else{
				slideVO.setUpdateName("System");
			}
			slideVO.setFieldCd(topicFieldInfoKeys);
			}
		// 返回结果集
		return slideVOs;
	}
	/**
	 * 保存排序：根据条件更新Slide_Show_Info表中相应记录<br/>
	 * @param  sortNo 表示顺
	 * @param  slideCd 轮播编码
	 * @param  updateId 更新者Id
	 * @return Integer  受更新影响条目数
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public Integer saveSortNo(String sortNo, String slideCd, String updateId) {
		// 定义变量表示受影响条目数
		Integer x = -1;
		// 实例化SlideShowInfo 表示更新条件
		SlideShowInfo slideShowInfo = new SlideShowInfo();
		// 为更新条件赋予属性
		slideShowInfo.setSortNo((byte) Integer.parseInt(sortNo));
		slideShowInfo.setSlideCd(Integer.parseInt(slideCd));
		slideShowInfo.setUpdateId(updateId);
		slideShowInfo.setUpdateTime(new Date());
		// 调用blo更新记录
		x = slideShowInfoBlo.saveSortNo(slideShowInfo);
		// 判断受影响条目数
		if(x == 1){
			return x;
		}
		else{
			throw new BusinessException("更新操作错误");
		}
	}
	/**
	 * 删除轮播记录<br/>
	 * @param  slideCd 轮播编码
	 * @param  updateId 更新者Id
	 * @return Integer  受更新影响条目数
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public Integer deleteSlides(String slideCd,String updateId) {
		// 定义变量表示受影响条目数
		Integer x = -1;
		// 实例化SlideShowInfo 表示更新条件
		SlideShowInfo slideShowInfo = new SlideShowInfo();
		// 为更新条件赋予属性
		slideShowInfo.setDeleteFlag(true);
		slideShowInfo.setSlideCd(Integer.parseInt(slideCd));
		slideShowInfo.setUpdateId(updateId);
		slideShowInfo.setUpdateTime(new Date());
		//slideShowInfo.setSortNo(null);
		// 调用blo更新记录
		x = slideShowInfoBlo.deleteSlides(slideShowInfo);
		// 判断受影响条目数
		if(x == 1){
			return x;
		}
		else{
			throw new BusinessException("删除操作错误");
		}
	}
	/**
	 * 查询系统配置表中全部机能<br/>
	 * @return List 查询结果集
	 */
	@Override
	public List<MstCodeInfo> searchBusis() {
		List<MstCodeInfo> codeInfos = mstCodeInfo.getAllBusis();
		return codeInfos;
	}
	/**
	 * 轮播编辑查询：根据busi到不同表查询对应记录<br/>
	 * @param  topic 主题编码
	 * @param  techFields 领域编码
	 * @param  busi 业务编码
	 * @param  pageNumber 页码
	 * @return List 查询结果集
	 */
	@Override
	public PageResult<SlideResultVO> selectBusis(String topic, String[] techFields, String busi,String pageNumber) {
		// 定义结果集合
		List<SlideResultVO> slideResultVOs = new ArrayList<>();
		// 将busi转化为int
		Integer tBusi = Integer.parseInt(busi);
		// 定义SlideResultVO作为查询条件
		SlideResultVO slideResultVO = new SlideResultVO();
		slideResultVO.setSlideResultBusi(busi);
		slideResultVO.setSlideResultTopic(topic);
		slideResultVO.setPageSize(ZzjConstants.DEFAULT_PAGESIZE);
		// 根据busi判断要查询的表
		// 业务分类：01专家,02知识,03E视频,04E课堂,05E问,06需求,07广告
		switch (tBusi) {
		case 1:
			// 定义List <ExpertInfoEditVO>作为查询结果
			List<ExpertInfo> expertInfos = new ArrayList<>();
			// 遍历查询条件
			for(int i = 0; i <techFields.length; i++){
				slideResultVO.setTechField(techFields[i]);
				// 调用对应Blo查询
				expertInfos = expertInfoBlo.slideEditSearch(slideResultVO);
				if(expertInfos != null){
					// 遍历查询结果
					for(int j = 0; j < expertInfos.size(); j++){
						SlideResultVO resultVO  = new SlideResultVO();
						resultVO.setSlideResultBusi(busi);
						if(expertInfos.get(j).getExpertName() != null){
							resultVO.setSlideResultTopic(expertInfos.get(j).getExpertName());
						}
						if(expertInfos.get(j).getExpertId() != null){
							resultVO.setSlideResultId(expertInfos.get(j).getExpertId());
						}
						resultVO.setSlideResultPayment(-1);
						// 根据 busiType和 topicCd两个字段去topicFieldInfo里查询结果
						Map<String, String> map = new HashMap<String, String>();
						map.put("busiType",busi );
						map.put("topicCd", expertInfos.get(j).getExpertId());
						List<TopicFieldInfoKey> topicFieldInfoKeys  = topicFieldInfoBlo.selectByTypeAndCode(map);
						resultVO.setSlideResultfields(topicFieldInfoKeys);
						slideResultVOs.add(resultVO);
					}
				}
			}
			break;
		case 2:
			// 定义List <NewsInfo>作为查询结果
			List<NewsInfo> newsInfos = new ArrayList<>();
			// 遍历查询条件
			for(int i = 0; i <techFields.length; i++){
				slideResultVO.setTechField(techFields[i]);
				// 调用对应Blo查询
				newsInfos = newsInfoBlo.slideEditSearch(slideResultVO);
				if(newsInfos != null){
					// 遍历查询结果
					for(int j = 0; j < newsInfos.size(); j++){
						SlideResultVO resultVO  = new SlideResultVO();
						resultVO.setSlideResultBusi(busi);
						if(newsInfos.get(j).getNewsName() != null){
							resultVO.setSlideResultTopic(newsInfos.get(j).getNewsName());
						}
						if(newsInfos.get(j).getNewsCd() != null){
							resultVO.setSlideResultId(newsInfos.get(j).getNewsCd());
						}
						if(newsInfos.get(j).getPaymentKbn() != null){
							resultVO.setSlideResultPayment(newsInfos.get(j).getPaymentKbn());
						}
						if(newsInfos.get(j).getLittleIcon() != null){
							resultVO.setSlideResultBigIcon(newsInfos.get(j).getLittleIcon());
						}
						
						// 根据 busiType和 topicCd两个字段去topicFieldInfo里查询结果
						Map<String, String> map = new HashMap<String, String>();
						map.put("busiType",busi );
						map.put("topicCd", newsInfos.get(j).getNewsCd());
						List<TopicFieldInfoKey> topicFieldInfoKeys  = topicFieldInfoBlo.selectByTypeAndCode(map);
						resultVO.setSlideResultfields(topicFieldInfoKeys);
						slideResultVOs.add(resultVO);
					}
				}
			}
			break;
		case 3:
			// 定义List <VideoInfo>作为查询结果
			List<VideoInfo> videoInfos = new ArrayList<>();
			// 遍历查询条件
			for(int i = 0; i <techFields.length; i++){
				slideResultVO.setTechField(techFields[i]);
				// 调用对应Blo查询
				videoInfos = videoInfoBlo.slideEditSearch(slideResultVO);
				if(videoInfos != null){
					// 遍历查询结果
					for(int j = 0; j < videoInfos.size(); j++){
						SlideResultVO resultVO  = new SlideResultVO();
						resultVO.setSlideResultBusi(busi);
						if(videoInfos.get(j).getVideoName() != null){
							resultVO.setSlideResultTopic(videoInfos.get(j).getVideoName());
						}
						if(videoInfos.get(j).getVideoCd() != null){
							resultVO.setSlideResultId(videoInfos.get(j).getVideoCd());
						}
						if(videoInfos.get(j).getLittleIcon() != null){
							resultVO.setSlideResultBigIcon(videoInfos.get(j).getLittleIcon());
						}
						if(videoInfos.get(j).getPaymentKbn() != null){
							resultVO.setSlideResultPayment(videoInfos.get(j).getPaymentKbn());
						}	
						// 根据 busiType和 topicCd两个字段去topicFieldInfo里查询结果
						Map<String, String> map = new HashMap<String, String>();
						map.put("busiType",busi );
						map.put("topicCd", videoInfos.get(j).getVideoCd());
						List<TopicFieldInfoKey> topicFieldInfoKeys  = topicFieldInfoBlo.selectByTypeAndCode(map);
						resultVO.setSlideResultfields(topicFieldInfoKeys);
						slideResultVOs.add(resultVO);
					}
				}
			}	
			
			
			List<LiveInfo> liveInfos = new ArrayList<>();
			// 遍历查询条件
			for(int i = 0; i <techFields.length; i++){
				slideResultVO.setTechField(techFields[i]);
				// 调用对应Blo查询
				liveInfos = liveInfoBlo.slideEditSearch(slideResultVO);
				if(videoInfos != null){
					// 遍历查询结果
					for(int j = 0; j < liveInfos.size(); j++){
						SlideResultVO resultVO  = new SlideResultVO();
						resultVO.setSlideResultBusi(busi);
						if(liveInfos.get(j).getLiveName() != null){
							resultVO.setSlideResultTopic(liveInfos.get(j).getLiveName());
						}
						if(liveInfos.get(j).getLiveCd() != null){
							resultVO.setSlideResultId(liveInfos.get(j).getLiveCd());
						}
						if(liveInfos.get(j).getLittleIcon() != null){
							resultVO.setSlideResultBigIcon(liveInfos.get(j).getLittleIcon());
						}
						if(liveInfos.get(j).getPaymentKbn() != null){
							resultVO.setSlideResultPayment(liveInfos.get(j).getPaymentKbn());
						}	
						// 根据 busiType和 topicCd两个字段去topicFieldInfo里查询结果
						Map<String, String> map = new HashMap<String, String>();
						map.put("busiType",busi );
						map.put("topicCd", liveInfos.get(j).getLiveCd());
						List<TopicFieldInfoKey> topicFieldInfoKeys  = topicFieldInfoBlo.selectByTypeAndCode(map);
						resultVO.setSlideResultfields(topicFieldInfoKeys);
						slideResultVOs.add(resultVO);
					}
				}
			}	
			break;
		case 4:
			// 定义List <CourseInfo>作为查询结果
			List<CourseInfo> courseInfos = new ArrayList<>();
			// 遍历查询条件
			for(int i = 0; i <techFields.length; i++){
				slideResultVO.setTechField(techFields[i]);
				// 调用对应Blo查询
				courseInfos = courseInfo.slideEditSearch(slideResultVO);
				if(courseInfos != null){
					// 遍历查询结果
					for(int j = 0; j < courseInfos.size(); j++){
						SlideResultVO resultVO  = new SlideResultVO();
						resultVO.setSlideResultBusi(busi);
						if(courseInfos.get(j).getCourseName() != null){
							resultVO.setSlideResultTopic(courseInfos.get(j).getCourseName());
						}	
						if(courseInfos.get(j).getCourseCd() != null){
							resultVO.setSlideResultId(courseInfos.get(j).getCourseCd());
						}	
						if(courseInfos.get(j).getLittleIcon() != null){
							resultVO.setSlideResultBigIcon(courseInfos.get(j).getLittleIcon());
						}	
						if(courseInfos.get(j).getPaymentKbn() != null){
							resultVO.setSlideResultPayment(courseInfos.get(j).getPaymentKbn());
						}							
						// 根据 busiType和 topicCd两个字段去topicFieldInfo里查询结果
						Map<String, String> map = new HashMap<String, String>();
						map.put("busiType",busi );
						map.put("topicCd", courseInfos.get(j).getCourseCd());
						List<TopicFieldInfoKey> topicFieldInfoKeys  = topicFieldInfoBlo.selectByTypeAndCode(map);
						resultVO.setSlideResultfields(topicFieldInfoKeys);
						slideResultVOs.add(resultVO);
					}
				}
			}	
			break;
		case 5:
			// 定义List <QuestionInfo>作为查询结果
			List<QuestionInfo> questionInfos = new ArrayList<>();
			// 遍历查询条件
			for(int i = 0; i <techFields.length; i++){
				slideResultVO.setTechField(techFields[i]);
				// 调用对应Blo查询
				questionInfos = questionInfoBlo.slideEditSearch(slideResultVO);
				if(questionInfos != null){
					// 遍历查询结果
					for(int j = 0; j < questionInfos.size(); j++){
						SlideResultVO resultVO  = new SlideResultVO();
						resultVO.setSlideResultBusi(busi);
						if(questionInfos.get(j).getQuestionName() != null){
							resultVO.setSlideResultTopic(questionInfos.get(j).getQuestionName());
						}
						if(questionInfos.get(j).getQuestionCd() != null){
							resultVO.setSlideResultId(questionInfos.get(j).getQuestionCd());
						}						
						resultVO.setSlideResultPayment(-1);
						// 根据 busiType和 topicCd两个字段去topicFieldInfo里查询结果
						Map<String, String> map = new HashMap<String, String>();
						map.put("busiType",busi );
						map.put("topicCd", questionInfos.get(j).getQuestionCd());
						List<TopicFieldInfoKey> topicFieldInfoKeys  = topicFieldInfoBlo.selectByTypeAndCode(map);
						resultVO.setSlideResultfields(topicFieldInfoKeys);
						slideResultVOs.add(resultVO);
					}
				}
			}
			break;
		case 6:
			// 定义List <NeedsInfo>作为查询结果
			List<NeedsInfo> needsInfos = new ArrayList<>();
			// 遍历查询条件
			for(int i = 0; i <techFields.length; i++){
				slideResultVO.setTechField(techFields[i]);
				// 调用对应Blo查询
				needsInfos = needsInfoBlo.slideEditSearch(slideResultVO);
				if(needsInfos != null) {
					// 遍历查询结果
					for(int j = 0; j < needsInfos.size(); j++) {
						SlideResultVO resultVO  = new SlideResultVO();
						resultVO.setSlideResultBusi(busi);
						if(needsInfos.get(j).getNeedsName() != null){
							resultVO.setSlideResultTopic(needsInfos.get(j).getNeedsName());
						}	
						if(needsInfos.get(j).getNeedsCd() != null){
							resultVO.setSlideResultId(needsInfos.get(j).getNeedsCd());
						}					
						resultVO.setSlideResultPayment(-1);
						// 根据 busiType和 topicCd两个字段去topicFieldInfo里查询结果
						Map<String, String> map = new HashMap<String, String>();
						map.put("busiType",busi );
						map.put("topicCd", needsInfos.get(j).getNeedsCd());
						List<TopicFieldInfoKey> topicFieldInfoKeys  = topicFieldInfoBlo.selectByTypeAndCode(map);
						resultVO.setSlideResultfields(topicFieldInfoKeys);
						slideResultVOs.add(resultVO);
					}
				}
			}
			break;
		case 7:
		    // 定义List <CommercialInfo>作为查询结果
			List<CommercialInfo> commercialInfos = new ArrayList<>();
			// 遍历查询条件
			for(int i = 0; i <techFields.length; i++) {
				slideResultVO.setTechField(techFields[i]);
				// 调用对应Blo查询
				commercialInfos = commercialInfoBlo.slideEditSearch(slideResultVO);
				if(commercialInfos != null){
					// 遍历查询结果
					for(int j = 0; j < commercialInfos.size(); j++){
						SlideResultVO resultVO  = new SlideResultVO();
						resultVO.setSlideResultBusi(busi);
						if(commercialInfos.get(j).getCommercialName() != null){
							resultVO.setSlideResultTopic(commercialInfos.get(j).getCommercialName());
						}
						if(commercialInfos.get(j).getCommercialCd() != null){
							resultVO.setSlideResultId(commercialInfos.get(j).getCommercialCd());
						}
						if(commercialInfos.get(j).getIconAddress() != null){
							resultVO.setSlideResultBigIcon(commercialInfos.get(j).getIconAddress());
						}						
						resultVO.setSlideResultPayment(-1);
						// 根据 busiType和 topicCd两个字段去topicFieldInfo里查询结果
						Map<String, String> map = new HashMap<String, String>();
						map.put("busiType",busi );
						map.put("topicCd", commercialInfos.get(j).getCommercialCd());
						List<TopicFieldInfoKey> topicFieldInfoKeys  = topicFieldInfoBlo.selectByTypeAndCode(map);
						resultVO.setSlideResultfields(topicFieldInfoKeys);
						slideResultVOs.add(resultVO);
					}
				}
			}
			break;
			
		}
		// 去重复
		List<SlideResultVO> slideResultVOList =new ArrayList<>();
		for(int j = 0; j < slideResultVOs.size(); j++){
			if(slideResultVOs.get(j).getSlideResultId() != null){
				boolean flag = true;
				for(int k = j+1;k<slideResultVOs.size();k++){
					if(slideResultVOs.get(k).getSlideResultId() != null){
						if(slideResultVOs.get(j).getSlideResultId().equals(slideResultVOs.get(k).getSlideResultId()))
						{flag = false;}
					}
				}
				// 判断当前记录是否已存在于轮播表
				String tId = slideResultVOs.get(j).getSlideResultId();
				String tbusi = slideResultVOs.get(j).getSlideResultBusi();
				Map<String, Object> key =new HashMap<>();
				key.put("topicCd",tId);
				key.put("busiType", tbusi);
				SlideShowInfo slideShowInfo = slideShowInfoBlo.select(key);
				if(slideShowInfo != null){
					flag = false;
				}
				if(flag){
					slideResultVOList.add(slideResultVOs.get(j));
				}
			}
		}
		// 分页
		PageResult<SlideResultVO> result = new PageResult<>();
		List<SlideResultVO> vos = new ArrayList<>();
		Integer minIndex = 0;
		Integer maxIndex = slideResultVO.getPageSize();
		if(pageNumber != null && pageNumber != ""){
			minIndex = (Integer.valueOf(pageNumber) - 1) * slideResultVO.getPageSize();
			maxIndex = Integer.valueOf(pageNumber) * slideResultVO.getPageSize();
		}
		for(int num = 0; num < slideResultVOs.size(); num++){
			if(num >= minIndex && num < maxIndex)
			{
				vos.add(slideResultVOs.get(num));
			}
		}
		result.setItems(vos);
		if(pageNumber != null && pageNumber != "" ){
			result.setPageNo(Integer.valueOf(pageNumber));
		}
		else{
			result.setPageNo(1);
		}
		result.setPageSize(slideResultVO.getPageSize());
		result.setTotalCount(slideResultVOs.size());
		Integer totalPageCount = 0;
		if(slideResultVOs.size() % slideResultVO.getPageSize() == 0){
			totalPageCount = slideResultVOs.size() / slideResultVO.getPageSize();
		}
		else{
			totalPageCount = (slideResultVOs.size() / slideResultVO.getPageSize()) + 1;
		}
		result.setTotalPageCount(totalPageCount);
		return result;
	}
	/**
	 * 添加轮播记录<br/>
	 * @param  topicCd 主题编码
	 * @param  topicName 主题名称
	 * @param  busiType 业务类别编码
	 * @param  bigIcon 图片地址
	 * @param  updateId 更新者Id
	 * @return boolean 添加是否成功结果
	 */
	@Override
	public boolean saveSlides(String topicCd, String topicName, String busiType, String bigIcon,String updateId) {
		// 定义变量表示返回结果
		boolean flag = false;
		// 定义表示添加记录
		SlideShowInfo slideShowInfo = new SlideShowInfo();
		// 为添加记录赋值
		slideShowInfo.setBigIcon(bigIcon);
		slideShowInfo.setBusiType(busiType);
		slideShowInfo.setDeleteFlag(false);
		slideShowInfo.setTopicCd(topicCd);
		slideShowInfo.setTopicName(topicName);
		slideShowInfo.setUpdateTime(new Date());
		slideShowInfo.setUpdateId(updateId);
		// 调用blo添加记录
		Integer x = slideShowInfoBlo.saveSlides(slideShowInfo);
		if(x == 1){
			flag = true;
		}
		return flag;
	}
	/**
	 * 设置信息<br/>
	 * @param request http请求实例
	 * @param updateId 更新着ID
	 * @return SlideShowInfo 信息
	 * @throws Exception 
	 */
	@Override
	public SlideShowInfo setSlideInfo(HttpServletRequest request, String updateId) throws Exception {
		// 定义SlideShowInfo作为编辑条件
		SlideShowInfo slideShowInfo = new SlideShowInfo();
		String isZzj = request.getParameter("isZzj");
		String topicName = request.getParameter("topicName");
		if (StringUtil.isNotBlank(topicName)) {
			slideShowInfo.setTopicName(topicName);
		}
		String bigIcon = request.getParameter("bigIcon");
		if(StringUtil.isNotBlank(bigIcon)){
			slideShowInfo.setBigIcon(bigIcon);
		}
		// 为编辑条件赋值
		if(Integer.parseInt(isZzj) == 0) {
			String url = request.getParameter("url");
			if (StringUtil.isNotBlank(url)) {			
					slideShowInfo.setUrl(url);
			}
		}
		if(Integer.parseInt(isZzj) == 1) {
			String busiType = request.getParameter("busiType");
			if (StringUtil.isNotBlank(busiType)) {
				slideShowInfo.setBusiType(busiType);
			}
			String topicCd = request.getParameter("topicCd");
			if (StringUtil.isNotBlank(topicCd)) {
				slideShowInfo.setTopicCd(topicCd);
			}
		}
		
		slideShowInfo.setUpdateId(updateId);
		slideShowInfo.setUpdateTime(new Date());
	
		return slideShowInfo;
	}
	
	/**
	 * 设置信息<br/>
	 * @param request http请求实例
	 * @throws Exception 
	 */
	@Override
	public void setParamter(HttpServletRequest request) throws Exception {
		
		// 将数据传到页面
		String isAdd = request.getParameter("isAdd");
		if (StringUtil.isNotBlank(isAdd)) {
			request.setAttribute("isAdd", isAdd);
		}
		
		String slideCd = request.getParameter("slideCd");
		if (StringUtil.isNotBlank(slideCd)) {
			request.setAttribute("slideCd", slideCd);
		}

		String topicName = request.getParameter("topicName");
		if (StringUtil.isNotBlank(topicName)) {
			request.setAttribute("topicName", topicName);
		}
		String bigIcon = request.getParameter("bigIcon");
		if (StringUtil.isNotBlank(bigIcon)) {
			request.setAttribute("bigIcon", bigIcon);
			request.setAttribute("bigIconUrl", StringUtil.getImageURL(bigIcon));
		}
		String isZzj = request.getParameter("isZzj");
		if (StringUtil.isNotBlank(isZzj)) {
			request.setAttribute("isZzj", isZzj);
		}
		String fZzj = request.getParameter("fZzj");
		if (StringUtil.isNotBlank(fZzj)) {
			request.setAttribute("fZzj", fZzj);
		}
		String busiType = request.getParameter("busiType");
		if (StringUtil.isNotBlank(busiType)) {
			request.setAttribute("busiType", busiType);
		}
		String topicCd = request.getParameter("topicCd");
		if (StringUtil.isNotBlank(topicCd)) {
			request.setAttribute("topicCd", topicCd);
		}
		String tZzj = request.getParameter("tZzj");
		if (StringUtil.isNotBlank(tZzj)) {
			request.setAttribute("tZzj", tZzj);
		}
		String zzjTopicName = request.getParameter("zzjTopicName");
		if (StringUtil.isNotBlank(zzjTopicName)) {
			request.setAttribute("selectTopicName", zzjTopicName);
		}
		String zzjTopicCd = request.getParameter("zzjTopicCd");
		if (StringUtil.isNotBlank(zzjTopicCd)) {
			request.setAttribute("topicCd", zzjTopicCd);	
		}
		String zzjBusyType = request.getParameter("zzjBusyType");
		if (StringUtil.isNotBlank(zzjBusyType)) {
			request.setAttribute("busiType", zzjBusyType);
		}
	}
	
	/**
	 * 编辑轮播记录：根据isZzj进行添加或更新轮播记录<br/>
	 * @param isAdd 1 添加；0编辑
	 * @param slideCd 轮播编码
	 * @param slideShowInfo 轮播信息
	 * @return Integer 添加是否成功结果
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) 
	public Integer editSlide(String isAdd, String slideCd, SlideShowInfo slideShowInfo) {
		// 定义变量作为返回值
		Integer x = -1;

		if(Integer.parseInt(isAdd) == 1) {
			slideShowInfo.setDeleteFlag(false);
			slideShowInfo.setSortNo((byte) 0);
			x = slideShowInfoBlo.saveSlides(slideShowInfo);
		}
		else if(Integer.parseInt(isAdd) == 0) {
			slideShowInfo.setSlideCd(Integer.parseInt(slideCd));
			x = slideShowInfoBlo.updateSlide(slideShowInfo);
		}
		// 为编辑条件赋值		
		return x;
	}
	/**
	 * 根据逐渐查询单条记录<br/>
	 * @param updateId 
	 * @return SlideShowInfo 查询结果
	 * @throws Exception 
	 */
	@Override
	public SlideShowInfo sreachSlide(String slideCd) throws Exception {
		Integer cdInteger = Integer.parseInt(slideCd);
		SlideShowInfo slideShowInfo = slideShowInfoBlo.sreachSlide(cdInteger);
		if(slideShowInfo != null){
			if (StringUtil.isNotBlank(slideShowInfo.getBigIcon()))
			{
				slideShowInfo.setBigIconUrl(StringUtil.getImageURL(slideShowInfo.getBigIcon()));
			}
			return slideShowInfo;
		}
		else{
			throw new BusinessException("查询操作错误");
		}		
	}	
}

