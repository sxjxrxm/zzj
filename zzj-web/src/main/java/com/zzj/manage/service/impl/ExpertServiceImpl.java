/**
 * Project Name:zzj-web
 * File Name:ExpertServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.zzj.core.exception.BusinessException;
import com.zzj.core.exception.ValidateErrorException;
import com.zzj.db.blo.AppUserBlo;
import com.zzj.db.blo.ExpertInfoBlo;
import com.zzj.db.blo.MstAreaInfoBlo;
import com.zzj.db.blo.MstFieldInfoBlo;
import com.zzj.db.blo.MstRankInfoBlo;
import com.zzj.db.blo.MstSequenceInfoBlo;
import com.zzj.db.blo.NewsInfoBlo;
import com.zzj.db.blo.RecommendInfoBlo;
import com.zzj.db.blo.TopicFieldInfoBlo;
import com.zzj.db.dto.AppUsersInfo;
import com.zzj.db.dto.ExpertInfo;
import com.zzj.db.dto.MstAreaInfo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstRankInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.NewsInfo;
import com.zzj.db.dto.RecommendInfo;
import com.zzj.db.dto.RecommendInfoKey;
import com.zzj.db.dto.TopicFieldInfo;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.model.ExpertArticleCountVO;
import com.zzj.db.model.ExpertInfoEditVO;
import com.zzj.db.model.ExpertInfoListVO;
import com.zzj.db.model.KeyVO;
import com.zzj.db.model.PageResult;
import com.zzj.manage.service.CommonService;
import com.zzj.manage.service.ExpertService;
import com.zzj.manage.service.HtmlService;
import com.zzj.util.Base64;
import com.zzj.util.SerializUtils;
import com.zzj.util.StringUtil;
import com.zzj.util.UploadUtils;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>ExpertService <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装专家模块，专家列表显示，专家编辑. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月17日上午10:54:07 <br/>
 * 
 * @author 任晓茂
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class ExpertServiceImpl implements ExpertService {

	/**
	 * 专家模块业务数据库操作类
	 */
	@Autowired
	private ExpertInfoBlo expertInfoBlo;
	
	/**
	 * 资讯模块业务数据库操作类
	 */
	@Autowired
	private NewsInfoBlo newsInfoBlo;
	
	/**
	 * 推荐置顶模块业务数据库操作类
	 */
	@Autowired
	private RecommendInfoBlo recommendInfoBlo;
	
	/**
	 * 领域模块业务数据库操作类
	 */
	@Autowired
	private TopicFieldInfoBlo topicFieldInfoBlo;
	
	/**
	 * 系统领域模块业务数据库操作类
	 */
	@Autowired
	private MstFieldInfoBlo mstFieldInfoBlo;

	/**
	 * 系统城市配置模块业务数据库操作类
	 */
	@Autowired
	private MstAreaInfoBlo mstAreaInfoBlo;
	
	/**
	 * 系统职称配置模块业务数据库操作类
	 */
	@Autowired
	private MstRankInfoBlo mstRankInfoBlo;
	
	/**
	 * 取得主键用blo
	 */
	@Autowired
	private MstSequenceInfoBlo  mstSequenceInfoBlo;
	
	/**
	 * AppUser业务数据库操作类
	 */
	@Autowired
	private AppUserBlo appUserBlo;
	
	/**
	 * H5页面生成服务
	 */
	@Autowired
	private HtmlService htmlService;
	
	/**
	 * 腾讯云服务
	 */
	@Autowired
	private CommonService common;
	
	/**
	 * Expert表保存处理<br/>
	 * @param  vo ExpertInfo数据
	 * @param  request 请求实例
	 * @param  isAdd 区分编辑与添加操作，1：添加，0：编辑
	 * @return boolean 是否成功
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean saveExpert(ExpertInfoEditVO vo, HttpServletRequest request, Integer isAdd) throws Exception {
		// 根据前台传值解析操作需要的数据
		Map<String, Object> msg = this.parseEditPageRequest(vo, request, isAdd);
		if (msg == null) {
			return false;
		}
		String createId = (String) msg.get("createId");
		Date createTime = (Date) msg.get("createTime");
		
		// 1.保存专家
		ExpertInfo expertRecord = (ExpertInfo) msg.get("expertRecord");
		AppUsersInfo appUsersInfo = (AppUsersInfo) msg.get("appUsersInfo");
		MstUsersInfo mstUsersInfo = (MstUsersInfo) msg.get("mstUsersInfo");
		int saveExpertCount = -1;
		if (expertRecord != null) {
			// 将临时保存的头像文件重命名并修改头像地址字段
//			if (StringUtil.isNotBlank(appUsersInfo.getAvator())) {
//				String realPath = UploadUtils.getRealPath(appUsersInfo.getAvator(), appUsersInfo.getUserId());
//				appUsersInfo.setAvator(realPath);
//			}
			AppUsersInfo temp1 = null;
			String avator = "";
			String nick = "";
			if (isAdd == 0) {
				temp1 = appUserBlo.getAppUser(appUsersInfo.getUserId());
				if (temp1 != null)
				{
					avator = temp1.getAvator();
					nick = temp1.getUserName();
				}
				saveExpertCount = expertInfoBlo.saveExpert(expertRecord, appUsersInfo, temp1, mstUsersInfo);// 保存成功返回1
			} else {
				saveExpertCount = expertInfoBlo.addExpert(expertRecord, appUsersInfo, mstUsersInfo);// 保存成功返回1
			}
			if (saveExpertCount != 1) {// 保存失败，将不再进行后续操作
				return false;
			}
			this.accountImport(isAdd, expertRecord, appUsersInfo, temp1, avator, nick);
			
		}
		// 2.保存专家领域
		// 2.1 先将该专家数据库中保存的领域全部改为逻辑删除状态，防止多次修改导致领域集合累加
		String[] ids = new String[2];
		ids[0] = expertRecord.getExpertId();// 专家id
		ids[1] = expertRecord.getUpdateId();// 当前登录用户的id
		topicFieldInfoBlo.deleteTopicFieldInfoByTypeAndIds(ids,ZzjConstants.BUSI_TYPE_01);
		// 2.2 根据新提交的领域信息执行更新或插入操作
		@SuppressWarnings("unchecked")
		List<TopicFieldInfo> fieldCd = (List<TopicFieldInfo>) msg.get("fieldCd");
		int saveFieldCdCount = -1;
		if (fieldCd != null && fieldCd.size() > 0) {
			for (TopicFieldInfo field : fieldCd) {
				// 根据主键查询是否存在
				TopicFieldInfo temp = topicFieldInfoBlo.selectByPrimaryKey(field);
				if (temp != null) {
					//如果存在执行更新操作
					saveFieldCdCount = topicFieldInfoBlo.saveTopicFieldInfo(field);
				} else {
					//如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
					saveFieldCdCount = topicFieldInfoBlo.addTopicFieldInfo(field);
				}
			}
		}
		// 3.保存推荐置顶信息
		// 3.1 先将该专家数据库中保存的推荐置顶全部改为逻辑删除状态，防止多次修改导致领域集合累加
		recommendInfoBlo.deleteRecommendInfoByTypeAndIds(ids,ZzjConstants.BUSI_TYPE_01);
		// 3.2 根据新提交的推荐置顶信息执行更新或插入操作
		int saveRecommendCount = -1;
		// 3.2.1置顶消息
		RecommendInfo toTopCode = (RecommendInfo) msg.get("toTopCode");
		// 先查询后操作
		if (toTopCode != null) {
			RecommendInfo temp = recommendInfoBlo.selectByPrimaryKey(toTopCode);
			if (temp != null) {
				//如果存在执行更新操作
				saveRecommendCount = recommendInfoBlo.saveRecommendInfo(toTopCode);
			} else {
				//如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
				saveRecommendCount = recommendInfoBlo.addRecommendInfo(toTopCode);
			}
		}
		// 3.2.2推荐消息
		RecommendInfo recommend = (RecommendInfo) msg.get("recommend");
		// 先查询后操作
		if (recommend != null) {
			RecommendInfo temp = recommendInfoBlo.selectByPrimaryKey(recommend);
			if (temp != null) {
				//如果存在执行更新操作
				saveRecommendCount = recommendInfoBlo.saveRecommendInfo(recommend);
			} else {
				//如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
				saveRecommendCount = recommendInfoBlo.addRecommendInfo(recommend);
			}
		}
		// 4.保存专家职称/头衔信息
		this.updateRankInfo(expertRecord.getRank(), createId, createTime);
		this.updateRankInfo(expertRecord.getRank2(), createId, createTime);
		this.updateRankInfo(expertRecord.getRank3(), createId, createTime);
		this.updateRankInfo(expertRecord.getRank4(), createId, createTime);
		
		// 生成静态页面
		htmlService.generateExpertHtml(request, expertRecord.getExpertId());
		
		if (saveExpertCount != -1 || saveFieldCdCount != -1 ||saveRecommendCount != -1) {
			return true;
		}
		return false;
	}

	/**
	 * Expert表查询处理<br/>
	 * @param  request 请求实例
	 * @return PageResult<ExpertInfoListVO> 分页处理结果
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public PageResult<ExpertInfoListVO> getResultList(HttpServletRequest request) throws Exception {
		/**
		 *  获取查询条件的map
		 */
		Map<String, Object> map = this.parseMap(request);
		Map<String, Object> queryMap = (map == null) ? new HashMap<String, Object>() : map;
		/**
		 *  获得记录总条数
		 */
		int totalCount = expertInfoBlo.selectTotalCount(queryMap);
		//根据总记录数重新校验当前页，当尾页只有一条记录，且执行删除操作时，此时保存的当前页不合法，需要进行减一操作
		int dbIndex = (int) map.get("dbIndex");
		if (dbIndex >= totalCount) {
			int pageNo = (int) map.get("pageNo");
			if (pageNo > 1) {
				queryMap.put("pageNo", pageNo-1);
				queryMap.put("dbIndex", (pageNo - 2) * ZzjConstants.DEFAULT_PAGESIZE);
			}
		}
		
		/**
		 * 根据条件查询专家记录
		 */
		List<ExpertInfoListVO> expertInfos = expertInfoBlo.selectSelectiveByPage(queryMap);
		
		if (expertInfos == null || expertInfos.size() < 1) {
			return null;// 查不到对应专家
		}
		
		/**
		 * 遍历查询结果，获取每个专家对应的文章、推荐置顶信息、领域信息
		 */
		for(ExpertInfoListVO eVo : expertInfos) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_01);
			map1.put("topicCd", eVo.getExpertId());
			
			// 获取每个专家对应的文章数量及文章待审数量
			ExpertArticleCountVO eaVO = newsInfoBlo.findNewsCountByExpertId(eVo.getExpertId());
			if(eaVO == null) {
				eaVO = new ExpertArticleCountVO();
			}
			eVo.setArticleCount(eaVO.getArticleCount());
			eVo.setPendingReviewArticleCount(eaVO.getPendingReviewArticleCount());
			
			// 获取每个专家对应的推荐置顶信息
			List<RecommendInfoKey> recommendInfo = recommendInfoBlo.selectByTypeAndCode(map1);
			if(recommendInfo == null) {
				recommendInfo = new ArrayList<RecommendInfoKey>();
			}
			eVo.setRecommendKbn(recommendInfo);
			
			//获取每个专家对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			eVo.setFieldCd(topicFieldInfo);
		}
		/**
		 * 构造分页结果集
		 */
		PageResult<ExpertInfoListVO> pageResult = new PageResult<ExpertInfoListVO>();
		pageResult.setPageNo(((int)queryMap.get("dbIndex") / (int)queryMap.get("pageSize")) + 1);
		pageResult.setPageSize((int)queryMap.get("pageSize"));
		int flag = totalCount % (int)queryMap.get("pageSize");// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / (int)queryMap.get("pageSize");
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(expertInfos);
		return pageResult;
	}

	/**
	 * 根据主键查询Expert表记录<br/>
	 * @param  request 请求实例
	 * @param  expertId 主键
	 * @return ExpertInfoEditVO 专家信息
	 * @throws Exception
	 */
	@Override
	public ExpertInfoEditVO selectByPrimaryKey(HttpServletRequest request, String expertId) throws Exception {
		// 根据id查找专家
		ExpertInfoEditVO expertInfoEditVO = expertInfoBlo.selectByPrimaryKey(expertId);
		if (expertInfoEditVO == null) {
			throw new BusinessException("查不到此专家");
		}
		if(expertInfoEditVO != null) {
			// 取得图片URL链接
			String imgPath = StringUtil.getImageURL(expertInfoEditVO.getAvatorAddress());
			expertInfoEditVO.setAvatorAddressUrl(imgPath);
			
			// 取得背景图片URL链接
			String backImgPath = StringUtil.getImageURL(expertInfoEditVO.getBackgroundAdress());
			expertInfoEditVO.setBackgroundAdressUrl(backImgPath);
		}
		// 完善推荐置顶信息
		Map<String, String> map = new HashMap<String, String>();
		map.put("busiType", ZzjConstants.BUSI_TYPE_01);
		map.put("topicCd", expertInfoEditVO.getExpertId());
		List<RecommendInfoKey> recommendInfo = recommendInfoBlo.selectByTypeAndCode(map);
		if(recommendInfo == null) {
			recommendInfo = new ArrayList<RecommendInfoKey>();
		} else {
			StringBuffer sb = new StringBuffer();
			for (RecommendInfoKey key : recommendInfo) {
				sb.append(key.getRecommendKbn() + ",");
			}
			expertInfoEditVO.setRecommendKbn(sb.toString());// 可能的结果： (1,2,)  (1,)  (2,)，方便前台使用el判断
			if (sb.toString().contains(ZzjConstants.RECOMMEND_KBN_1)) {// 改专家已被推荐，需要查询推荐语
				expertInfoEditVO.setRecommendMsg(recommendInfoBlo.findRecommendMsgByTypeAndCode(map));
			}
		}
		// 完善领域信息
		List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map);
		if(topicFieldInfo == null) {
			topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
		}
		expertInfoEditVO.setFieldCd(topicFieldInfo);
		// 获得不属于该专家的领域
		expertInfoEditVO.setOtherFieldCd(this.getOtherTopicFieldInfo(topicFieldInfo));
		// 构造城市信息
		Double parentId = (double) 1;// 省级记录的parentId为1
		List<MstAreaInfo> areaInfosCityP = mstAreaInfoBlo.selectAllByParentId(parentId);
		expertInfoEditVO.setAreaInfosCityP(areaInfosCityP);
		
		String cityPRegionCode = expertInfoEditVO.getCityP();
		if (StringUtil.isNotBlank(cityPRegionCode)) {
			Double regionId = mstAreaInfoBlo.selectRegionIdByRegionCode(expertInfoEditVO.getCityP());// 获得该专家所在的省信息，查询对应的市级信息
			List<MstAreaInfo> areaInfosCityC = mstAreaInfoBlo.selectAllByParentId(regionId);
			expertInfoEditVO.setAreaInfosCityC(areaInfosCityC);
		}
		return expertInfoEditVO;
	}
	
	/**
	 * 构造专家页面信息<br/>
	 * @param  无
	 * @return ExpertInfoEditVO 专家信息
	 */
	@Override
	public ExpertInfoEditVO initPageResource() {
		ExpertInfoEditVO expertInfoEditVO = new ExpertInfoEditVO();
		// 构造领域信息
		expertInfoEditVO.setOtherFieldCd(this.getOtherTopicFieldInfo(null));
		// 构造城市信息
		Double parentId = (double) 1;// 省级记录的parentId为1
		List<MstAreaInfo> areaInfosCityP = mstAreaInfoBlo.selectAllByParentId(parentId);
		expertInfoEditVO.setAreaInfosCityP(areaInfosCityP);
		return expertInfoEditVO;
	}

	/**
	 * 根据主键删除Expert表记录<br/>
	 * @param  ids 专家id、当前用户id
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteExpertById(String[] ids) {
		// 防止url篡改
		if (!expertInfoBlo.deleteExpertById(ids)) {
			throw new BusinessException("删除专家失败");
		};
//		// 删除专家文章
//		newsInfoBlo.deleteNewsBySourceOwner(ids);
//		// 删除专家领域信息
//		topicFieldInfoBlo.deleteTopicFieldInfoByTypeAndIds(ids,ZzjConstants.BUSI_TYPE_01);
//		// 删除专家推荐置顶信息
//		recommendInfoBlo.deleteRecommendInfoByTypeAndIds(ids,ZzjConstants.BUSI_TYPE_01);
	}
	
	/**
	 * 专家编辑页面城市联动菜单异步请求<br/>
	 * @param  request 请求实例
	 * @return List<MstAreaInfo> 返回根据省级code获得的对应的市级信息列表
	 */
	@Override
	public List<MstAreaInfo> getCityC(HttpServletRequest request) {
		String cityPCode = request.getParameter("cityPCode");
		if (StringUtil.isBlank(cityPCode)) {
			return null;
		}
		Double regionId = mstAreaInfoBlo.selectRegionIdByRegionCode(cityPCode);
		List<MstAreaInfo> areaInfosCityC = mstAreaInfoBlo.selectAllByParentId(regionId);
		return areaInfosCityC;
	}
	
	/** 
	 * 专家编辑页面专家职称异步请求<br/>
	 * @return String 返回专家职称信息，以空格隔开的字符串
	 */
	@Override
	public String getRankName() {
		// 构造专家职称/头衔联想输入信息
		return mstRankInfoBlo.selectAllRankName();
	}
	
	/**
	 * 知识编辑页面专家姓名异步请求<br/>
	 * @return String 返回专家姓名信息，以4个空格隔开的字符串
	 */
	@Override
	public String getExpertName() {
		// 构造专家姓名联想输入信息
		return expertInfoBlo.selectAllExpertName();
	}

	/**
	 * 专家编辑页面专家头像异步请求。<br/>
	 * @param  file 文件对象
	 * @param  req 请求实例
	 * @param  itemName 图片空间区分
	 * @return Map<String, String> 返回专家头像地址
	 */
	@Override
	public Map<String, String> queryPic(MultipartRequest file, HttpServletRequest req, String itemName) {
		// 获得请求图片
		MultipartFile multipartFile = file.getFile(itemName);
		String realPath = "";
		
		Map<String, String> resultMap = new HashMap<String, String>();
		// 图片验证
		String message = "";
		if ("imgData".equals(itemName))
		{
			message = UploadUtils.checkAvatorImg(multipartFile);
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
			realPath = UploadUtils.uploadImg(multipartFile, ZzjConstants.UPLOAD_TYPE_AVATAR , ZzjConstants.BUSI_TYPE_01);
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
	 * 专家编辑页面专家头像异步删除<br/>
	 * @param  path 文件路径
	 * @return boolean 返回删除是否成功
	 * @throws Exception
	 */
	@Override
	public boolean delPic(String path) throws Exception {
		return UploadUtils.delFile("img",path);
	}

	/**
	 * 刷新画面
	 * @param ExpertInfoEditVO expertInfo 专家信息
	 * @param HttpServletRequest request 请求实例
	 * @throws Exception 
	 */
	@Override
	public void load(ExpertInfoEditVO expertInfo, HttpServletRequest request) throws Exception {
		if (expertInfo != null) {
			request.setAttribute("expertInfo", expertInfo);
		}
		// 已审核的需要显示文章列表
		if (expertInfo.getStatus() == ZzjConstants.STATUS_1) {
			PageResult<NewsInfo> resultList = this.findNewsByExpertIdAndPage(request, expertInfo.getExpertId());
			request.setAttribute("resultList", resultList);
		}
	}

	/**
	 * 取得全部专家记录（根据专家一览页面的检索条件）
	 * @param HttpServletRequest request 请求实例
	 * @return List<ExpertInfoListVO> 专家列表
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public List<ExpertInfoListVO> searchAll(HttpServletRequest request) throws Exception {
		/**
		 *  获取查询条件的map
		 */
		Map<String, Object> map = this.parseMap(request);
		Map<String, Object> queryMap = (map == null) ? new HashMap<String, Object>() : map;
		/**
		 * 根据条件查询专家记录
		 */
		List<ExpertInfoListVO> expertInfos = expertInfoBlo.selectAll(queryMap);
		for(ExpertInfoListVO infoVO : expertInfos) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_01);
			map1.put("topicCd", infoVO.getExpertId());
			
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
			infoVO.setRecommendKbn(recommendInfo);
			
		}
		return expertInfos;
	}


	/**
	 * 获得导出内容
	 * @param List<ExpertInfoListVO> resultList 专家一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	@Override
	public List<ArrayList<String>> getOutputContent(List<ExpertInfoListVO> resultList, List<MstCodeInfo> mstCodeInfos) {
		// 数据做成
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("专家一览");
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("专家名称");
		temp.add("领域");
		temp.add("审核状态");
		temp.add("文章数");
		temp.add("待审数");
		temp.add("点赞数");
		temp.add("收藏数");
		temp.add("浏览数");
		temp.add("下载数");
		temp.add("专家简介");
		list.add(temp);
		for (ExpertInfoListVO n : resultList) {
			temp = new ArrayList<String>();
			temp.add(n.getExpertName());// 专家名称
			StringBuffer sb = new StringBuffer();// 领域
			for (TopicFieldInfoKey f : n.getFieldCd()) {
				for (MstCodeInfo code : mstCodeInfos) {
					if (ZzjConstants.TECH_FIELD_TYPE.equals(code.getCodeType())
							&& f.getTechFieldCd().equals(code.getCode())) {
						sb.append(code.getCodeName());
					}
				}
				if (!ZzjConstants.BLANK.equals(f.getRschFieldCd()))
				{
					sb.append("->");
					for (MstCodeInfo code : mstCodeInfos) {
						if (ZzjConstants.RSCH_FIELD_TYPE.equals(code.getCodeType())
								&& f.getRschFieldCd().equals(code.getCode())) {
							sb.append(code.getCodeName());
						}
					}
				}
				sb.append("|");
			}
			temp.add(sb.toString());
			for (MstCodeInfo code : mstCodeInfos) {// 审核状态
				if (ZzjConstants.AUDIT_STATUS.equals(code.getCodeType())
						&& code.getCode().equals(n.getStatus()+"")) {
					temp.add(code.getCodeName());
				}
			}
			temp.add(n.getArticleCount()==null ? "0" : n.getArticleCount() + "");// 文章数
			temp.add(n.getPendingReviewArticleCount()==null ? "0" : n.getPendingReviewArticleCount() + "");// 待审数
			if (Objects.isNull(n.getUserHandleInfo())) {
				temp.add("0");// 点赞数
				temp.add("0");// 收藏数
				temp.add("0");// 浏览数
				temp.add("0");// 下载数
			} else {
				temp.add(n.getUserHandleInfo().getLikeCount()==null ? "0" : n.getUserHandleInfo().getLikeCount() + "");// 点赞数
				temp.add(n.getUserHandleInfo().getCollectCount()==null ? "0" : n.getUserHandleInfo().getCollectCount() + "");// 收藏数
				temp.add(n.getUserHandleInfo().getScanCount()==null ? "0" : n.getUserHandleInfo().getScanCount() + "");// 浏览数
				temp.add(n.getUserHandleInfo().getDownloadCount()==null ? "0" : n.getUserHandleInfo().getDownloadCount() + "");// 下载数
			}
			// 专家简介
			String brief = n.getExpertBrief();
			try {
				if (StringUtil.isNotBlank(brief))
				{
					brief = StringUtil.delHTMLTag(brief);
				}
				if (StringUtil.isNotBlank(brief))
				{
					brief = StringUtil.StringFilter(brief);
				}				
			} catch (Exception e) {
				e.printStackTrace();				
			}
			temp.add(brief);
			list.add(temp);
		}
		return list;
	}

	/**
	 * 进行专家文章删除。<br/>
	 * @param  request http请求实例
	 * @param  newsCd 知识id
	 * @return 删除文章数量
	 */
	@Override
	public int delExpertArticle(HttpServletRequest request, String newsCd) {
		MstUsersInfo user  = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		String[] ids = new String[2];
		ids[0] = newsCd;
		ids[1] = user.getUserId();
		if (newsInfoBlo.deleteNewsById(ids)) {
			// 删除知识领域信息
			topicFieldInfoBlo.deleteTopicFieldInfoByTypeAndIds(ids,ZzjConstants.BUSI_TYPE_02);
			// 删除知识推荐置顶信息
			recommendInfoBlo.deleteRecommendInfoByTypeAndIds(ids,ZzjConstants.BUSI_TYPE_02);
			return 1;
		}
		return 0;
	}


	/**
	 * 获得不属于该需求的领域
	 * @param List<TopicFieldInfoKey> 属于该需求的领域
	 * @return List<TopicFieldInfoKey> 获得不属于该需求的领域
	 */
	private List<TopicFieldInfoKey> getOtherTopicFieldInfo(List<TopicFieldInfoKey> topicFieldInfo)  {
		// 取得全部领域
		List<TopicFieldInfoKey> mstFieldInfo = mstFieldInfoBlo.selectAll();
		if (topicFieldInfo == null)
		{
			return mstFieldInfo;
		}
	    // 获得不属于该需求的领域
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
	 * 
	 * 根据专家编辑页面请求实例，返回领域信息，推荐置顶信息，专家记录信息。<br/>
	 * @param  expertInfo 前台接受的信息
	 * @param  request 请求实例
	 * @param  isAdd 1：专家添加， 0：专家编辑
	 * @return Map<String, Object>  返回操作用到的数据
	 * @throws Exception
	 */
	private Map<String, Object> parseEditPageRequest(ExpertInfoEditVO expertInfo, HttpServletRequest request, Integer isAdd) 
			throws Exception {
		// 当专家对象为空时，停止解析
		if (expertInfo == null) {
			return null;
		}
		if (isAdd == 1) {// 如果是添加专家信息需要保存创建人id和创建时间
			if (StringUtil.isBlank(expertInfo.getExpertId())) {// 如果一次没有新增成功，此时已经有id号，不需要重新计算id
				String expertId = mstSequenceInfoBlo.selectSequenceInfo(ZzjConstants.USER_NO);
				expertInfo.setExpertId(expertId);
			}
		}
		// 输入信息的校验
		// 错误信息
		ValidateErrorException exception = null;
		if (StringUtil.isBlank(expertInfo.getExpertName())) {
			exception = new ValidateErrorException("E1000001", new Object[] {"专家姓名"}, "expert/expert_edit", "expertName");
		} else if (expertInfo.getExpertName().length() > 20) {
			exception = new ValidateErrorException("E1000005", new Object[] {"专家姓名","20个"}, "expert/expert_edit", "expertName");
		}
		if (StringUtil.isBlank(expertInfo.getRank())) {
			exception = this.rebuildException(exception, "E1000001", new Object[] { "头衔/职称1" }, "expert/expert_edit", "rank");
		} else if (expertInfo.getRank().length() > 50) {
			exception = this.rebuildException(exception, "E1000005", new Object[] {"头衔/职称1","50个"}, "expert/expert_edit", "rank");
		}
		if (StringUtil.isNotBlank(expertInfo.getRank2()) && expertInfo.getRank2().length() > 50) {
			exception = this.rebuildException(exception, "E1000005", new Object[] {"头衔/职称2","50个"}, "expert/expert_edit", "rank2");
		}
		if (StringUtil.isNotBlank(expertInfo.getRank3()) && expertInfo.getRank3().length() > 50) {
			exception = this.rebuildException(exception, "E1000005", new Object[] {"头衔/职称3","50个"}, "expert/expert_edit", "rank3");
		}
		if (StringUtil.isNotBlank(expertInfo.getRank4()) && expertInfo.getRank4().length() > 50) {
			exception = this.rebuildException(exception, "E1000005", new Object[] {"头衔/职称4","50个"}, "expert/expert_edit", "rank4");
		}
		// 获得更新人的id，并创建更新时间
		String updateId = expertInfo.getUpdateId();
		Date updateTime = new Date();
		
		Map<String, Object> msg = new HashMap<String, Object>();
		/**
		 * 保存更新人id和更新时间为创建人id和创建时间，当记录为插入操作时需要添加创建人id和创建时间
		 */
		msg.put("createId", updateId);
		msg.put("createTime", updateTime);
		/**
		 * 解析领域信息，前台传值示例：{01-02, 02-02}  ->  {技术领域-研究领域, 技术领域-研究领域}
		 */
		String[] fieldCdStr = request.getParameterValues("fieldCdStr");
		List<TopicFieldInfo> fieldCd = null;
		List<TopicFieldInfoKey> fieldInfoKey = null;
		if (fieldCdStr != null && fieldCdStr.length > 0) {
			fieldCd = new ArrayList<TopicFieldInfo>();
			fieldInfoKey = new ArrayList<TopicFieldInfoKey>();
			for (String code : fieldCdStr) {
				String[] codes = code.split("-");// 此时codes的length应该为2，如果不是2，则是垃圾数据，不予保存
				if (codes.length != 2) {
					continue;
				}
				TopicFieldInfo topicFieldInfo = new TopicFieldInfo();
				topicFieldInfo.setBusiType(ZzjConstants.BUSI_TYPE_01);
				topicFieldInfo.setUpdateId(updateId);
				topicFieldInfo.setUpdateTime(updateTime);
				topicFieldInfo.setDeleteFlag(0);
				topicFieldInfo.setTopicCd(expertInfo.getExpertId());
				topicFieldInfo.setTechFieldCd(codes[0]);
				topicFieldInfo.setRschFieldCd(codes[1]);
				fieldCd.add(topicFieldInfo);
				fieldInfoKey.add(topicFieldInfo);
			}
		} else {
			exception = this.rebuildException(exception, "E1000001", new Object[] { "领域" }, "expert/expert_edit", "choose_sel");
		}
			
		msg.put("fieldCd", fieldCd);
		
		
//		if (StringUtil.isBlank(expertInfo.getExperience())) {
//			exception = this.rebuildException(exception, "E1000001", new Object[] { "工作年限" }, "expert/expert_edit", "experience");
//		}
		if (StringUtil.isBlank(expertInfo.getCompany())) {
//			exception = this.rebuildException(exception, "E1000001", new Object[] { "工作单位" }, "expert/expert_edit", "company");
		} else if (expertInfo.getCompany().length() > 50) {
			exception = this.rebuildException(exception, "E1000005", new Object[] {"工作单位","50个"}, "expert/expert_edit", "company");
		}
//		if (StringUtil.isBlank(expertInfo.getCityP())) {
//			exception = this.rebuildException(exception, "E1000001", new Object[] { "常驻城市" }, "expert/expert_edit", "cityP");
//		}
//		if (StringUtil.isBlank(expertInfo.getCityC())) {
//			exception = this.rebuildException(exception, "E1000001", new Object[] { "常驻城市" }, "expert/expert_edit", "cityC");
//		}
		if (!StringUtil.isMobile(expertInfo.getPhone())) {
			exception = this.rebuildException(exception, "E1000001", new Object[] { "手机" }, "expert/expert_edit", "phoneInput");
		}
		if (StringUtil.isNotBlank(expertInfo.getEmail()) && expertInfo.getEmail().length() > 50) {
			exception = this.rebuildException(exception, "E1000005", new Object[] {"邮箱","50个"}, "expert/expert_edit", "email");
		}
		if (StringUtil.isBlank(expertInfo.getExpertBrief())) {
			exception = this.rebuildException(exception, "E1000001", new Object[] { "专家介绍" }, "expert/expert_edit", "expertBrief");
		}
//		if (expertInfo.getExpertBrief().length() > 500) {
//			exception = this.rebuildException(exception, "E1000005", new Object[] {"专家介绍","500个"}, "expert/expert_edit", "expertBrief");
//		}
		
		/**
		 * 解析推荐置顶消息
		 */
		
		// 置顶信息
		String toTopCode = request.getParameter("toTopCode");
		if (StringUtil.isNotBlank(toTopCode)) {
			RecommendInfo recommendInfo = new RecommendInfo();
			recommendInfo.setBusiType(ZzjConstants.BUSI_TYPE_01);
			recommendInfo.setTopicCd(expertInfo.getExpertId());
			recommendInfo.setUpdateId(updateId);
			recommendInfo.setUpdateTime(updateTime);
			recommendInfo.setDeleteFlag(0);
			Integer toTopNum = Integer.parseInt(toTopCode);
			recommendInfo.setRecommendKbn(toTopNum);
			msg.put("toTopCode", recommendInfo);
			
			expertInfo.setTopFlag(ZzjConstants.TOP_FLG_1);
			expertInfo.setStatus(ZzjConstants.STATUS_1);
			if (recommendInfoBlo.isToTopRecommendCount(recommendInfo, ZzjConstants.RECOMMEND_KBN_2))
			{
				exception = this.rebuildException(exception, "E1000027", new Object[] {ZzjConstants.TO_TOP_NUM}, "expert/expert_edit", "toTop");
			}
		} else {
			expertInfo.setTopFlag(ZzjConstants.TOP_FLG_0);
		}
		// 推荐信息
		String recommend = request.getParameter("recommend");
		if (StringUtil.isNotBlank(recommend)) {
			if (StringUtil.isBlank(expertInfo.getRecommendMsg()) || ZzjConstants.DEFAULT_RECOMMEND_MSG.equals(expertInfo.getRecommendMsg())) {
				exception = this.rebuildException(exception, "E1000001", new Object[] { "推荐语" }, "expert/expert_edit", "recommendInput");
			}
			RecommendInfo recommendInfo = new RecommendInfo();
			recommendInfo.setBusiType(ZzjConstants.BUSI_TYPE_01);
			recommendInfo.setTopicCd(expertInfo.getExpertId());
			recommendInfo.setUpdateId(updateId);
			recommendInfo.setUpdateTime(updateTime);
			recommendInfo.setDeleteFlag(0);
			Integer recommendNum = Integer.parseInt(recommend);
			recommendInfo.setRecommendKbn(recommendNum);
			recommendInfo.setRecommendMemo(expertInfo.getRecommendMsg());
			msg.put("recommend", recommendInfo);

			expertInfo.setStatus(ZzjConstants.STATUS_1);
			
			if (recommendInfoBlo.isToTopRecommendCount(recommendInfo, ZzjConstants.RECOMMEND_KBN_1))
			{
				exception = this.rebuildException(exception, "E1000028", new Object[] {ZzjConstants.RECOMMEND_NUM}, "expert/expert_edit", "recommend");
			}
			
		} else {
			expertInfo.setRecommendMsg(null);
		}
		
//		// 得到当前登录用户信息
//		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
//		// 当非专家用户登录时，必须输入审核结果
//		if (Objects.isNull(expertInfo.getStatus()) && user.getRoleId() != ZzjConstants.EXPERT_ID_IN_USERS_INFO) {
//			if (exception == null) {
//				exception = new ValidateErrorException("E1000001", new Object[] {"审核结果"}, "expert/expert_edit", "statusSelect");
//			} else {
//				exception.addError("E1000001", new Object[] {"审核结果"}, "statusSelect");
//			}
//		}
		// 审核状态为拒绝时，必须输入拒绝理由
		if (expertInfo.getStatus() == ZzjConstants.STATUS_2) {
			if (StringUtil.isBlank(expertInfo.getRefuseMemo())) {
				String id = null;
				if (expertInfo.getRefuseMemoType() == ZzjConstants.REFUSE_MEMO_TYPE_1) {
					id = "sel";
				} else {
					id = "refuseMemo2";
				}
				exception = this.rebuildException(exception, "E1000001", new Object[] {"拒绝理由"}, "expert/expert_edit", id);
			}
		}
		
		/**
		 * 构造关键词信息
		 * 由地域名称#所有领域名称#专家名构成
		 * 如：北京市朝阳区#电子信息安全领域智慧工程安全领域#刘博士
		 */
		// 前台传来的城市信息
		String cityName = request.getParameter("cityNameForKeyWord");
		// 前台传来的领域信息格式为："智慧城市->基础设施及网络技术,智慧城市->安全领域,智慧城市->宏观领域,智慧城市->应用领域,电子政务->宏观领域"
		String fieldNameForKeyWord = request.getParameter("fieldNameForKeyWord");
		if (StringUtil.isNotBlank(fieldNameForKeyWord)) {
			fieldNameForKeyWord = fieldNameForKeyWord.replace(",", "").replace("->", "");
		}
		StringBuffer sBuffer = new StringBuffer();
		if (StringUtil.isNotBlank(cityName) && StringUtil.isNotBlank(cityName.replaceAll("==请选择==", ""))) {
			sBuffer.append(cityName).append("#");// 地域名称
		}
		sBuffer.append(fieldNameForKeyWord).append("#");// 领域信息
		sBuffer.append(expertInfo.getExpertName());// 专家名
		/**
		 * 构造专家记录信息/app用户信息/系统用户信息
		 */
		AppUsersInfo appUsersInfo = new AppUsersInfo();
		appUsersInfo.setRoleId(ZzjConstants.EXPERT_ID_IN_USERS_INFO);
		appUsersInfo.setPassword(StringUtil.stringToMD5(ZzjConstants.EXPERT_PSW_IN_USERS_INFO));
		appUsersInfo.setUpdateId(updateId);
		appUsersInfo.setUpdateTime(updateTime);
		appUsersInfo.setUserName(expertInfo.getExpertName());
		appUsersInfo.setPhoneNumber(expertInfo.getPhone());
		appUsersInfo.setDeleteFlag(0);
		appUsersInfo.setAvator(expertInfo.getAvatorAddress());
		appUsersInfo.setUserId(expertInfo.getExpertId());
		
		MstUsersInfo mstUsersInfo = new MstUsersInfo();
		mstUsersInfo.setRoleId(ZzjConstants.EXPERT_ID_IN_USERS_INFO);
		mstUsersInfo.setPassword(StringUtil.stringToMD5(ZzjConstants.EXPERT_PSW_IN_USERS_INFO));
		mstUsersInfo.setUpdateId(updateId);
		mstUsersInfo.setUpdateTime(updateTime);
		mstUsersInfo.setUserName(expertInfo.getExpertName());
		mstUsersInfo.setPhoneNumber(expertInfo.getPhone());
		mstUsersInfo.setDeleteFlag(0);
		mstUsersInfo.setUserId(expertInfo.getExpertId());
		
		expertInfo.setUpdateId(updateId);
		expertInfo.setUpdateTime(updateTime);
		expertInfo.setKeyword(sBuffer.toString());
		expertInfo.setDeleteFlag(0);
		if (StringUtil.isNotBlank(expertInfo.getAvatorAddress())) {
			expertInfo.setAvatorAddressUrl(StringUtil.getImageURL(expertInfo.getAvatorAddress()));
		}
		if (StringUtil.isNotBlank(expertInfo.getBackgroundAdress())) {
			expertInfo.setBackgroundAdressUrl(StringUtil.getImageURL(expertInfo.getBackgroundAdress()));
		}
		if (isAdd == 1) {// 如果是添加专家信息需要保存创建人id和创建时间
			expertInfo.setCreateId(updateId);
			expertInfo.setCreateTime(updateTime);
			
			appUsersInfo.setCreateId(updateId);
			appUsersInfo.setCreateTime(updateTime);
			appUsersInfo.setUserId(expertInfo.getExpertId());
			
			mstUsersInfo.setCreateId(updateId);
			mstUsersInfo.setCreateTime(updateTime);
			mstUsersInfo.setUserId(expertInfo.getExpertId());
		}
		if (expertInfo.getStatus() != null && expertInfo.getStatus() == ZzjConstants.STATUS_1) {// 当专家的状态为审核通过时，应该将拒绝理由置为空
			expertInfo.setRefuseMemo(null);
		}
		// 分享链接处理
		expertInfo.setShareUrl(this.getShareUrl(request, expertInfo));
		msg.put("expertRecord", expertInfo);
		msg.put("appUsersInfo", appUsersInfo);
		msg.put("mstUsersInfo", mstUsersInfo);
		// 设置领域信息
		expertInfo.setFieldCd(fieldInfoKey);
		// 获得不属于该需求的领域
		expertInfo.setOtherFieldCd(this.getOtherTopicFieldInfo(fieldInfoKey));
		// 构造城市信息
		Double parentId = (double) 1;// 省级记录的parentId为1
		List<MstAreaInfo> areaInfosCityP = mstAreaInfoBlo.selectAllByParentId(parentId);
		expertInfo.setAreaInfosCityP(areaInfosCityP);
		
		String cityPRegionCode = expertInfo.getCityP();
		if (StringUtil.isNotBlank(cityPRegionCode)) {
			Double regionId = mstAreaInfoBlo.selectRegionIdByRegionCode(expertInfo.getCityP());// 获得该专家所在的省信息，查询对应的市级信息
			List<MstAreaInfo> areaInfosCityC = mstAreaInfoBlo.selectAllByParentId(regionId);
			expertInfo.setAreaInfosCityC(areaInfosCityC);
		}
		request.setAttribute("expertInfo", expertInfo);
		request.setAttribute("appUsersInfo", appUsersInfo);
		/**
		 * 校验失败，需要抛出异常信息
		 */
		// 文章列表翻页,或者,有异常发生
		if (exception != null /* || StringUtil.isNotBlank(pagging) */) {
			// 已审核的需要显示文章列表
			if (expertInfo.getStatus() == ZzjConstants.STATUS_1) {
				PageResult<NewsInfo> resultList = this.findNewsByExpertIdAndPage(request, expertInfo.getExpertId());
				request.setAttribute("resultList", resultList);
			}
			//request.setAttribute("expertInfo", expertInfo);
			if (exception != null) {
				throw exception;
			}
		}
		return msg;
	}
	
	/**
	 * 根据专家信息获得分享链接
	 * @param  request http请求实例
	 * @param  expertInfo 专家信息
	 * @return String  专家分享链接
	 * @throws Exception
	 */
	private String getShareUrl(HttpServletRequest request, ExpertInfoEditVO expertInfo) throws Exception {
		if (expertInfo == null) {
			return null;
		}
		String shareUrl = htmlService.generateExpertHtmlUrl(expertInfo.getExpertId());
		return shareUrl;
	}
	
	/**
	 * 根据专家id和分页信息查询专家文章列表.<br/>
	 * @param request 请求实例
	 * @param expertId 专家id
	 * @return Map<String,Object> 用于数据库查询
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public PageResult<NewsInfo> findNewsByExpertIdAndPage (HttpServletRequest request, String expertId) throws Exception {
		String newsEdit = request.getParameter("newsEdit");
		/**
		 * 构造map
		 */
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("expertId", expertId);
		Integer pageSize = ZzjConstants.DEFAULT_PAGESIZE_S;
		queryMap.put("pageSize", pageSize);
		// 查询起始记录数
		String pageNoStr = null;
		if(StringUtil.isNotBlank(newsEdit) && "newsEdit".equals(newsEdit)){
			String selectResource = request.getParameter("selectResource");
			byte[] b = Base64.decode(selectResource);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			if(null != keyVO){
				pageNoStr = keyVO.getPageNo();
			}
		}else{
			pageNoStr = request.getParameter("pageNo");
		}
		
		String from = request.getParameter("from"); // 记录是否是从一览页面跳转
		Integer pageNo = 1;
		if (StringUtil.isNotBlank(pageNoStr)) {
			if (StringUtil.isBlank(from)) { // from为空，说明并非从一览页面跳转，而是进行文章翻页操作
				pageNo = Integer.parseInt(pageNoStr);
			}
			if (pageNo > 0) {
				queryMap.put("dbIndex", (pageNo - 1) * pageSize);
			}
		} else {
			queryMap.put("dbIndex", 0);
		}
		/**
		 * 根据map查询专家文章信息
		 */
		List<NewsInfo> articleList = newsInfoBlo.findNewsBySourceOwnerByPage(queryMap);
		int totalCount = newsInfoBlo.findNewsCountBySourceOwner(queryMap);
		/**
		 * 构造分页实体信息
		 */
		PageResult<NewsInfo> pageResult = new PageResult<NewsInfo>();
		pageResult.setPageNo(pageNo);
		pageResult.setPageSize(pageSize);
		int flag = totalCount % pageSize;// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / pageSize;
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(articleList);
		return pageResult;
	}
	
	
	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 封装专家一览前台传来的查询条件<br/>
	 * @param request 请求实例
	 * @return Map<String,Object> 用于数据库查询
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private Map<String, Object> parseMap(HttpServletRequest request) throws ClassNotFoundException, IOException {
		String flag = request.getParameter("flag");
		/**
		 * 获取查询条件的值
		 */
		// 专家姓名
		String expertName = null;;
		// 审核状态（0 待审核， 1 已审核）
		String[] auditStatus = null;
		// 领域（技术领域）
		String[] techField = null;
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbn = null;
		//从编辑返回列表页
		if(StringUtil.isNotBlank(flag) && "0".equals(flag)){
			String selectResource = request.getParameter("selectResource");
			byte[] b = Base64.decode(selectResource);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			if(null != keyVO){
				expertName = keyVO.getExpertName();
				auditStatus = keyVO.getAuditStatus();
				techField = keyVO.getField();
				recommendKbn = keyVO.getRecommendKbn();
			}
		}else{
			expertName = request.getParameter("expertNameLike");
			auditStatus = request.getParameterValues("auditStatus");
			techField = request.getParameterValues("techField");
			recommendKbn = request.getParameterValues("recommendKbn");
		}
		
		// 专家id （仅当登录用户为专家用户时使用该查询条件）
		String expertId = (String) request.getAttribute("expertId");
		
		/**
		 * 构造map
		 */
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(expertName)) {
			queryMap.put("expertName", expertName);
		}
		if (StringUtil.isNotBlank(expertId)) {
			queryMap.put("expertId", expertId);
		}
		if (StringUtil.isNotBlank(auditStatus)) {
			if (auditStatus.length == 1) {
				/*
				 * 页面回显使用的el表达式使用contains函数
				 * 而且回显用的数据将数据转换为逗号分隔的字符串： <c:if test="${fn:contains(selectStatus, statusCode)}">checked</c:if> 
				 * 当从编辑页面或者新增页面跳转过来时，页面的hidden会传来逗号分隔的字符串或者空字符串，此时auditStatus.length == 1
				 * 需要将其转为数组供以下转换使用 （下同）
				 */
				auditStatus = auditStatus[0].split(",");
			}
			if (StringUtil.isNotBlank(auditStatus[0])) {
				/*
				 * 当列表查询时未选择该过滤条件，进入编辑/添加页面返回时，
				 * 该过滤条件hidden返回长度为1的字符串数组，但是这个元素的值是空字符串，
				 * 所以这里需要判断auditStatus[0]是否为空 （下同）
				 */
				queryMap.put("auditStatus", Arrays.asList(auditStatus));
			}
		}
		if (StringUtil.isNotBlank(techField)) {
			if (techField.length == 1) {
				techField = techField[0].split(",");
			}
			if (StringUtil.isNotBlank(techField[0])) {
				queryMap.put("techField", Arrays.asList(techField));
			}
		} 
		if (StringUtil.isNotBlank(recommendKbn)) {
			if (recommendKbn.length == 1) {
				recommendKbn = recommendKbn[0].split(",");
			}
			if (StringUtil.isNotBlank(recommendKbn[0])) {
				queryMap.put("recommendKbn", Arrays.asList(recommendKbn));
			}
		}
		// 查询起始记录数
		// 当前页
		String pageNoStr = null;
		if ("edit".equals(request.getParameter("edit"))) {
			pageNoStr = request.getParameter("expertPageNo");
		} else {
			pageNoStr = request.getParameter("pageNo");
		}
		// 页大小
		Integer pageSize = ZzjConstants.DEFAULT_PAGESIZE;
		if (StringUtil.isNotBlank(pageNoStr)) {
			Integer pageNo = Integer.parseInt(pageNoStr);
			if (pageNo > 0) {
				queryMap.put("dbIndex", (pageNo - 1) * pageSize);
				queryMap.put("pageNo", pageNo);
			}
			request.setAttribute("expertPageNo", pageNo);
		} else {
			queryMap.put("dbIndex", 0);
			queryMap.put("pageNo", 1);
		}
		queryMap.put("pageSize", pageSize);
		return queryMap;
	}
		
	/**
	 * 判断当前专家职称是否在数据库中存在，如果存在，不执行操作，如果不存在，则插入新的记录。<br/>
	 * @param  rankName 当前专家职称
	 * @param  createId 创建id
	 * @param  createTime 创建时间
	 * @return 无
	 */
	private void updateRankInfo(String rankName, String createId, Date createTime) {
		if (StringUtil.isBlank(rankName)) {
			return;
		}
		//  根据专家职称查询数据库是否已经存在
		int isExistCount = mstRankInfoBlo.selectByRankName(rankName);
		// 1 如果不存在则需要插入新的职称信息，供前台输入联想使用
		if (isExistCount == 0) {
			// 1.1 获取职称表最大rankCd，加1后供插入使用
			int maxRankCd = mstRankInfoBlo.selectMaxRankCd();
			// 1.2 进行插入操作
			MstRankInfo record = new MstRankInfo();
			record.setRankCd(String.valueOf(maxRankCd + 1));
			record.setRankName(rankName);
			if (StringUtil.isNotBlank(createId)) {
				record.setCreateId(createId);
			}
			if (createTime != null) {
				record.setCreateTime(createTime);
			}
			int insertCount = mstRankInfoBlo.addMstRankInfo(record);
			if (insertCount != 1) {// 插入失败
				throw new BusinessException("职称插入失败");
			}
		} else {
			// 2 如果已经存在则不进行插入操作
			return;
		}
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

	/**
	 * 注册腾讯云账号<br/>
	 * 在执行expertInfoBlo.saveExpert(expertRecord, appUsersInfo, temp1, mstUsersInfo)代码时temp1的头像和昵称已经更新
	 * @param  isAdd 区分编辑与添加操作，1：添加，0：编辑
	 * @param  expertRecord 专家信息
	 * @param  appUsersInfo APP用户信息
	 * @param  temp1 DBAPP用户信息
	 * @param  avator 原始的的头像地址  
	 * @param  nick 原始的用户名
	 * @throws Exception 
	 */
	private void accountImport(Integer isAdd, ExpertInfo expertRecord, AppUsersInfo appUsersInfo, AppUsersInfo temp1, String avator, String nick) throws Exception {
		if (expertRecord.getStatus() == ZzjConstants.STATUS_1) {
			if (isAdd == 0) {
				if (temp1 == null) {
					// 注册腾讯云账号
					common.accountImport(appUsersInfo.getUserId(), appUsersInfo.getUserName(), appUsersInfo.getAvator());
				} else if (StringUtil.isNotBlank(appUsersInfo.getAvator()) // 用户名已经做了非空校验，再次只做头像非空校验
						&& (!appUsersInfo.getUserName().equals(nick) || !appUsersInfo.getAvator().equals(avator))) {
					// 修改腾讯云头像或昵称
					common.accountImport(appUsersInfo.getUserId(), appUsersInfo.getUserName(), appUsersInfo.getAvator());
				}
			} else {
				// 注册腾讯云账号
				common.accountImport(appUsersInfo.getUserId(), appUsersInfo.getUserName(), appUsersInfo.getAvator());
				// 根据前台要求，设置专家用户在腾讯云的自定义标签为“专家”
				common.SetAccountInfo(appUsersInfo.getUserId(), null, null, ZzjConstants.SIGN_EXPERT);
			}
		}
	}
}
