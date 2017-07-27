/**
 * Project Name:zzj-web
 * File Name:InformationServiceImpl.java
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
import com.zzj.db.blo.ExpertInfoBlo;
import com.zzj.db.blo.MstFieldInfoBlo;
import com.zzj.db.blo.MstSequenceInfoBlo;
import com.zzj.db.blo.NewsInfoBlo;
import com.zzj.db.blo.RecommendInfoBlo;
import com.zzj.db.blo.TopicFieldInfoBlo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.NewsInfo;
import com.zzj.db.dto.RecommendInfo;
import com.zzj.db.dto.RecommendInfoKey;
import com.zzj.db.dto.TopicFieldInfo;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.model.KeyVO;
import com.zzj.db.model.NewsInfoEditVO;
import com.zzj.db.model.NewsInfoListVO;
import com.zzj.db.model.PageResult;
import com.zzj.manage.service.HtmlService;
import com.zzj.manage.service.InformationService;
import com.zzj.util.Base64;
import com.zzj.util.DateUtil;
import com.zzj.util.SerializUtils;
import com.zzj.util.StringUtil;
import com.zzj.util.UploadUtils;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>InformationServiceImpl <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装知识模块，知识列表显示，知识编辑. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月29日下午5:20:07 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class InformationServiceImpl implements InformationService {

	/**
	 * 知识模块业务数据库操作类
	 */
	@Autowired
	private NewsInfoBlo newsInfoBlo;
	
	/**
	 * 专家模块业务数据库操作类
	 */
	@Autowired
	private ExpertInfoBlo expertInfoBlo;

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
	 *取得主键用blo
	 */
	@Autowired
	private MstSequenceInfoBlo  mstSequenceInfoBlo;
	
	/**
	 * H5页面生成服务
	 */
	@Autowired
	private HtmlService htmlService;
	
	// 常量
	private final Integer SOURCE_TYPE_EXPERT = 2;// 1:找专家平台， 2：专家
	
	/**
	 * news_info表查询处理<br/>
	 * @param  request 请求实例
	 * @param  startDate 开始时间
	 * @param  endDate 结束时间
	 * @return PageResult<NewsInfoListVO> 信息列表
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public PageResult<NewsInfoListVO> getResultList(HttpServletRequest request, Date startDate, Date endDate) throws Exception {
		/**
		 *  获取查询条件的map
		 */
		Map<String, Object> map = this.parseMap(request, startDate, endDate);
		Map<String, Object> queryMap = (map == null) ? new HashMap<String, Object>() : map;
		/**
		 *  获得记录总条数
		 */
		int totalCount = newsInfoBlo.selectTotalCount(queryMap);
		
		//根据总记录数重新校验当前页，当尾页只有一条记录，且执行删除操作时，此时保存的当前页不合法，需要进行减一操作
		int dbIndex = (int) map.get("dbIndex");
		if (dbIndex >= totalCount) {
			int pageNo = (int) map.get("pageNo");
			if (pageNo > 1) {
				queryMap.put("pageNo", pageNo - 1);
				queryMap.put("dbIndex", (pageNo - 2) * ZzjConstants.DEFAULT_PAGESIZE);
			}
		}
		/**
		 * 根据条件查询知识记录
		 */
		List<NewsInfoListVO> newsInfos = newsInfoBlo.selectSelectiveByPage(queryMap);
		
		if (newsInfos == null || newsInfos.size() < 1) {
			return null;// 查不到对应知识
		}
		
		/**
		 * 遍历查询结果，获取每个知识对应的推荐置顶信息、领域信息
		 */
		for(NewsInfoListVO nVo : newsInfos) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_02);
			map1.put("topicCd", nVo.getNewsCd());
			
			// 获取每个知识对应的推荐置顶信息
			List<RecommendInfoKey> recommendInfo = recommendInfoBlo.selectByTypeAndCode(map1);
			if(recommendInfo == null) {
				recommendInfo = new ArrayList<RecommendInfoKey>();
			}
			nVo.setRecommendKbn(recommendInfo);
			
			//获取每个知识对应领域信息
			List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map1);
			if(topicFieldInfo == null) {
				topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
			}
			nVo.setFieldCd(topicFieldInfo);
		}
		/**
		 * 构造分页结果集
		 */
		PageResult<NewsInfoListVO> pageResult = new PageResult<NewsInfoListVO>();
		pageResult.setPageNo(((int)queryMap.get("dbIndex") / (int)queryMap.get("pageSize")) + 1);
		pageResult.setPageSize((int)queryMap.get("pageSize"));
		int flag = totalCount % (int)queryMap.get("pageSize");// 0：记录数==页大小 ， 非0：有非完整页，总页数需加1
		int num = totalCount / (int)queryMap.get("pageSize");
		pageResult.setTotalPageCount((flag == 0) ? num : num + 1);
		pageResult.setTotalCount(totalCount);
		pageResult.setItems(newsInfos);
		return pageResult;
	}	

	/**
	 * 根据主键删除news_info表记录<br/>
	 * @param  ids newsCd、当前用户id
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteNewsById(String[] ids) {
		// 防止url篡改
		if (!newsInfoBlo.deleteNewsById(ids)) {
			throw new BusinessException("删除知识失败");
		};
//		// 删除知识领域信息
//		topicFieldInfoBlo.deleteTopicFieldInfoByTypeAndIds(ids,ZzjConstants.BUSI_TYPE_02);
//		// 删除知识推荐置顶信息
//		recommendInfoBlo.deleteRecommendInfoByTypeAndIds(ids,ZzjConstants.BUSI_TYPE_02);
	}


	/**
	 * 根据主键查询NewsInfo表记录<br/>
	 * @param  request 请求实例
	 * @param  newsCd 主键
	 * @return NewsInfoEditVO 信息详细
	 * @throws Exception
	 */
	@Override
	public NewsInfoEditVO selectByPrimaryKey(HttpServletRequest request, String newsCd) throws Exception {
		// 根据id查找知识
		NewsInfoEditVO newsInfoEditVO = newsInfoBlo.getNewsInfo(newsCd);
		if (newsInfoEditVO == null) {
			throw new BusinessException("查不到此知识");
		}
		if(newsInfoEditVO != null) {
			// 取得图片URL链接
			String littleImgPath = StringUtil.getImageURL(newsInfoEditVO.getLittleIcon());
			newsInfoEditVO.setLittleIconUrl(littleImgPath);
			String bigImgPath = StringUtil.getImageURL(newsInfoEditVO.getBigIcon());
			newsInfoEditVO.setBigIconUrl(bigImgPath);
			String pdfPath = StringUtil.getPdfURL(newsInfoEditVO.getNewsAddress());
			newsInfoEditVO.setNewsAddressUrl(pdfPath);
			
			// 获取知识作者的姓名
			newsInfoEditVO.setSourceOwnerName(this.getExpertName(newsInfoEditVO.getSourceOwner()));
			newsInfoEditVO.setExpertCd2Name(this.getExpertName(newsInfoEditVO.getExpertCd2()));
			newsInfoEditVO.setExpertCd3Name(this.getExpertName(newsInfoEditVO.getExpertCd3()));
			newsInfoEditVO.setExpertCd4Name(this.getExpertName(newsInfoEditVO.getExpertCd4()));
			newsInfoEditVO.setExpertCd5Name(this.getExpertName(newsInfoEditVO.getExpertCd5()));
		}
		// 完善推荐置顶信息
		Map<String, String> map = new HashMap<String, String>();
		map.put("busiType", ZzjConstants.BUSI_TYPE_02);
		map.put("topicCd", newsInfoEditVO.getNewsCd());
		List<RecommendInfoKey> recommendInfo = recommendInfoBlo.selectByTypeAndCode(map);
		if (recommendInfo == null) {
			recommendInfo = new ArrayList<RecommendInfoKey>();
		} else {
			StringBuffer sb = new StringBuffer();
			for (RecommendInfoKey key : recommendInfo) {
				sb.append(key.getRecommendKbn() + ",");
			}
			newsInfoEditVO.setRecommendKbn(sb.toString());// 可能的结果： (1,2,) (1,) (2,)，方便前台使用el判断
			if (sb.toString().contains("1")) {// 改知识已被推荐，需要查询推荐语
				newsInfoEditVO.setRecommendMsg(recommendInfoBlo.findRecommendMsgByTypeAndCode(map));
			}
		}
		// 完善领域信息
		List<TopicFieldInfoKey> topicFieldInfo = topicFieldInfoBlo.selectByTypeAndCode(map);
		if (topicFieldInfo == null) {
			topicFieldInfo = new ArrayList<TopicFieldInfoKey>();
		}
		newsInfoEditVO.setFieldCd(topicFieldInfo);
		// 获得不属于该知识的领域
		List<TopicFieldInfoKey> mstFieldInfo = mstFieldInfoBlo.selectAll();
		List<TopicFieldInfoKey> otherTopicFieldInfo = new ArrayList<TopicFieldInfoKey>();
		boolean isContains = false;
		for (TopicFieldInfoKey m : mstFieldInfo) {
			isContains = false;
			for (TopicFieldInfoKey t : topicFieldInfo) {
				if (m.getTechFieldCd().equals(t.getTechFieldCd()) && m.getRschFieldCd().equals(t.getRschFieldCd())) {
					isContains = true;
				}
			}
			if (!isContains) {
				otherTopicFieldInfo.add(m);
			}
		}
		newsInfoEditVO.setOtherFieldCd(otherTopicFieldInfo);
		return newsInfoEditVO;
	}

	/**
	 * 构造知识页面信息<br/>
	 * @param  无
	 * @return NewsInfoEditVO 信息详细
	 */
	@Override
	public NewsInfoEditVO initPageResource() {
		NewsInfoEditVO newsInfoEditVO = new NewsInfoEditVO();
		// 构造领域信息
		List<TopicFieldInfoKey> mstFieldInfo = mstFieldInfoBlo.selectAll();
		newsInfoEditVO.setOtherFieldCd(mstFieldInfo);
		return newsInfoEditVO;
	}
	
	/**
	 * NewsInfo表保存处理<br/>
	 * @param  vo 数据
	 * @param  request 请求实例
	 * @param  isAdd 区分编辑与添加操作，1：添加，0：编辑
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean saveNews(NewsInfoEditVO vo, HttpServletRequest request, Integer isAdd) throws Exception{
		// 根据前台传值解析操作需要的数据
		Map<String, Object> msg = this.parseEditPageRequest(vo, request, isAdd);
		if (msg == null) {
			return false;
		}

		// 1.保存知识
		NewsInfo newsRecord = (NewsInfo) msg.get("newsRecord");
		if (newsRecord == null) {
			return false;
		}
		int saveExpertCount = -1;
//		// 将临时保存的图片文件重命名并修改图片地址字段
//		if (StringUtil.isNotBlank(newsRecord.getLittleIcon())) {
//			String realPath = UploadUtils.getRealPath(newsRecord.getLittleIcon(), newsRecord.getNewsCd());
//			newsRecord.setLittleIcon(realPath);
//		}
//		if (StringUtil.isNotBlank(newsRecord.getBigIcon())) {
//			String realPath = UploadUtils.getRealPath(newsRecord.getBigIcon(), newsRecord.getNewsCd());
//			newsRecord.setBigIcon(realPath);
//		}
//		// 将临时保存的pdf文件重命名并修改pdf地址字段
//		if (StringUtil.isNotBlank(newsRecord.getNewsAddress())) {
//			String realPath = UploadUtils.getPdfRealPath(newsRecord.getNewsAddress(), newsRecord.getNewsCd());
//			newsRecord.setNewsAddress(realPath);
//		}
		if (isAdd == 0) {
			saveExpertCount = newsInfoBlo.saveNewsInfo(newsRecord);// 保存成功返回1
		} else {
			saveExpertCount = newsInfoBlo.addNewsInfo(newsRecord);// 保存成功返回1
		}
		if (saveExpertCount != 1) {// 保存失败，将不再进行后续操作
			return false;
		}
		// 2.保存知识领域
		// 2.1 先将该知识数据库中保存的领域全部改为逻辑删除状态，防止多次修改导致领域集合累加
		String[] ids = new String[2];
		ids[0] = newsRecord.getNewsCd();// 知识cd
		ids[1] = newsRecord.getUpdateId();// 当前登录用户的id
		topicFieldInfoBlo.deleteTopicFieldInfoByTypeAndIds(ids, ZzjConstants.BUSI_TYPE_02);
		// 2.2 根据新提交的领域信息执行更新或插入操作
		@SuppressWarnings("unchecked")
		List<TopicFieldInfo> fieldCd = (List<TopicFieldInfo>) msg.get("fieldCd");
		int saveFieldCdCount = -1;
		if (fieldCd != null && fieldCd.size() > 0) {
			for (TopicFieldInfo field : fieldCd) {
				// 根据主键查询是否存在
				TopicFieldInfo temp = topicFieldInfoBlo.selectByPrimaryKey(field);
				if (temp != null) {
					// 如果存在执行更新操作
					saveFieldCdCount = topicFieldInfoBlo.saveTopicFieldInfo(field);
				} else {
					// 如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
					saveFieldCdCount = topicFieldInfoBlo.addTopicFieldInfo(field);
				}
			}
		}
		// 3.保存推荐置顶信息
		// 3.1 先将该专家数据库中保存的推荐置顶全部改为逻辑删除状态，防止多次修改导致领域集合累加
		recommendInfoBlo.deleteRecommendInfoByTypeAndIds(ids, ZzjConstants.BUSI_TYPE_02);
		// 3.2 根据新提交的推荐置顶信息执行更新或插入操作
		int saveRecommendCount = -1;
		// 3.2.1置顶消息
		RecommendInfo toTopCode = (RecommendInfo) msg.get("toTopCode");
		// 先查询后操作
		if (toTopCode != null) {
			RecommendInfo temp = recommendInfoBlo.selectByPrimaryKey(toTopCode);
			if (temp != null) {
				// 如果存在执行更新操作
				saveRecommendCount = recommendInfoBlo.saveRecommendInfo(toTopCode);
			} else {
				// 如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
				saveRecommendCount = recommendInfoBlo.addRecommendInfo(toTopCode);
			}
		}
		// 3.2.2推荐消息
		RecommendInfo recommend = (RecommendInfo) msg.get("recommend");
		// 先查询后操作
		if (recommend != null) {
			RecommendInfo temp = recommendInfoBlo.selectByPrimaryKey(recommend);
			if (temp != null) {
				// 如果存在执行更新操作
				saveRecommendCount = recommendInfoBlo.saveRecommendInfo(recommend);
			} else {
				// 如果不存在执行插入操作，（添加创建人id和创建时间字段）（该表没有创建人id和创建时间字段，不需要处理该数据）
				saveRecommendCount = recommendInfoBlo.addRecommendInfo(recommend);
			}
		}
		// 生成静态页面
		htmlService.generateInfoHtml(request, newsRecord.getNewsCd());
		
		if (saveExpertCount != -1 || saveFieldCdCount != -1 || saveRecommendCount != -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 知识编辑页面富文本编辑器中的图片请求<br/>
	 * @param  file 文件请求
	 * @return String 返回图片保存的url
	 * @throws Exception
	 */
	@Override
	public Map<String, String> textEditorImg(MultipartRequest file) throws Exception {
		MultipartFile multipartFile = file.getFile("upfile");
		// 取得图片URL链接
		return UploadUtils.uploadEditImg(multipartFile, ZzjConstants.UPLOAD_TYPE_CONTENT, ZzjConstants.BUSI_TYPE_02);
	}

	/**
	 * 知识编辑页面图片异步请求<br/>
	 * @param  file 文件请求
	 * @param  req 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 */
	@Override
	public Map<String, String> queryPic(MultipartRequest file, HttpServletRequest req) {
		String id = req.getParameter("id");// 用于区分列表图片（littleIcon）、详情图片（bigIcon）
		if (StringUtil.isBlank(id)) {
			return null;
		}
		// 获得请求图片
		MultipartFile multipartFile = file.getFile(id + "ImgData");
		String realPath = "";
		Map<String, String> resultMap = new HashMap<String, String>();
		
		// 图片验证
		String message = UploadUtils.checkLittleIconImg(multipartFile);
		if (StringUtil.isNotBlank(message))
		{
			resultMap.put("message", message);
			return resultMap;
		}
		
		try {
			// 执行图片上传
			realPath = UploadUtils.uploadImg(multipartFile, ZzjConstants.UPLOAD_TYPE_CONTENT, ZzjConstants.BUSI_TYPE_02);
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
	 * 知识编辑页面文件异步删除<br/>
	 * @param  name 文件名
	 * @param  path 文件路径
	 * @return boolean 返回删除是否成功
	 * @throws Exception
	 */
	@Override
	public boolean delFile(String name, String path) throws Exception {
		return UploadUtils.delFile(name, path);
	}

	/**
	 * 知识编辑页面pdf异步请求<br/>
	 * @param  file 文件请求
	 * @param  req 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 */
	@Override
	public Map<String, String> queryPdf(MultipartRequest file, HttpServletRequest req) {
		// 获得请求pdf
		MultipartFile multipartFile = file.getFile("chapter");
		String realPath = "";
		Map<String, String> resultMap = new HashMap<String, String>();

		try {
			// 执行pdf上传
			realPath = UploadUtils.uploadImg(multipartFile, ZzjConstants.UPLOAD_TYPE_FILE, ZzjConstants.BUSI_TYPE_02);
			// web服务器存储路径
			resultMap.put("path", realPath);
			// 取得pdf URL链接
			resultMap.put("url", StringUtil.getPdfURL(realPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回图片地址
		return resultMap;
	}

	/**
	 * 取得全部知识记录（根据知识一览页面的检索条件）
	 * @param HttpServletRequest request 请求实例
	 * @param  startDate 开始时间
	 * @param  endDate 结束时间
	 * @return List<NewsInfoListVO> 知识列表
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public List<NewsInfoListVO> searchAll(HttpServletRequest request, Date startDate, Date endDate) throws Exception {
		/**
		 *  获取查询条件的map
		 */
		Map<String, Object> map = this.parseMap(request,startDate,endDate);
		Map<String, Object> queryMap = (map == null) ? new HashMap<String, Object>() : map;
		/**
		 * 根据条件查询知识记录
		 */
		List<NewsInfoListVO> newsInfos = newsInfoBlo.selectAll(queryMap);
		for(NewsInfoListVO infoVO : newsInfos) {
			// 构造查询条件map
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("busiType", ZzjConstants.BUSI_TYPE_02);
			map1.put("topicCd", infoVO.getNewsCd());
			
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
		return newsInfos;
	}

	/**
	 * 获得导出内容
	 * @param List<NewsInfoListVO> resultList 知识一览
	 * @param List<MstCodeInfo> mstCodeInfos 名称列表i
	 * @return List<ArrayList<String>> 导出内容
	 */
	@Override
	public List<ArrayList<String>> getOutputContent(List<NewsInfoListVO> resultList, List<MstCodeInfo> mstCodeInfos) {
		// 数据做成
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("知识一览");
		list.add(temp);
		temp = new ArrayList<String>();
		temp.add("知识主题");
		temp.add("费用");
		temp.add("领域");
		temp.add("知识分类");
		temp.add("创建时间");
		temp.add("收藏数");
		temp.add("浏览数");
		temp.add("下载数");
		list.add(temp);
		for (NewsInfoListVO n : resultList) {
			temp = new ArrayList<String>();
			temp.add(n.getNewsName());// 知识主题
			
			for (MstCodeInfo code : mstCodeInfos) {// 费用
				if (ZzjConstants.PAYMENT_KBN.equals(code.getCodeType()) && code.getCode().equals(n.getStatus() + "")) {
					temp.add(code.getCodeName());
				}
			}
			
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
			
			for (MstCodeInfo code : mstCodeInfos) {// 知识分类
				if (ZzjConstants.NEWS_TYPE.equals(code.getCodeType()) && code.getCode().equals(n.getStatus() + "")) {
					temp.add(code.getCodeName());
				}
			}
			temp.add(DateUtil.getYmdHmFormatString(n.getCreateTime()));
			if (Objects.isNull(n.getUserHandleInfo())) {
				temp.add("0");// 收藏数
				temp.add("0");// 浏览数
				temp.add("0");// 下载数
			} else {
				temp.add(n.getUserHandleInfo().getCollectCount() == null ? "0" : n.getUserHandleInfo().getCollectCount() + "");// 收藏数
				temp.add(n.getUserHandleInfo().getScanCount() == null ? "0" : n.getUserHandleInfo().getScanCount() + "");// 浏览数
				temp.add(n.getUserHandleInfo().getDownloadCount() == null ? "0" : n.getUserHandleInfo().getDownloadCount() + "");// 下载数
			}
			list.add(temp);
		}

		return list;
	}


	/**
	 * 根据专家id获得专家姓名，获取不到则直接返回
	 * @param  expertId 专家id
	 * @return String  专家姓名
	 */
	private String getExpertName(String expertId) {
		if (StringUtil.isBlank(expertId)) {
			return expertId;
		}
		String expertName = expertInfoBlo.selectNameByExpertId(expertId);
		if (StringUtil.isBlank(expertName)) {
			return expertId;
		}
		return expertName;
	}
	
	/**
	 * 根据知识信息获得分享链接
	 * @param  request HTTP请求
	 * @param  newsRecord 知识信息
	 * @return String  知识分享链接
	 * @throws Exception 
	 */
	private String getShareUrl(HttpServletRequest request,NewsInfo newsRecord) throws Exception {
		if (newsRecord == null) {
			return null;
		}
		String shareUrl = htmlService.generateInfoHtmlUrl(newsRecord.getNewsCd());
		return shareUrl;
	}
	
	/**
	 * 根据知识编辑页面请求实例，返回领域信息，推荐置顶信息。<br/>
	 * @param  newsInfo 前台接受的信息
	 * @param  request 请求实例
	 * @param  isAdd 1：专家添加， 0：专家编辑
	 * @return Map<String, Object>  返回操作用到的数据
	 * @throws Exception 
	 */
	private Map<String, Object> parseEditPageRequest(NewsInfoEditVO newsInfo, HttpServletRequest request, Integer isAdd) 
			throws Exception {
		// 当知识对象或者知识id为空时，停止解析
		if (newsInfo == null) {
			return null;
		}
		if (StringUtil.isBlank(newsInfo.getNewsCd())) {// 如果一次没有新增成功，此时已经有id号，不需要重新计算id
			String newCd = mstSequenceInfoBlo.selectSequenceInfo(ZzjConstants.NEWS_NO);
			newsInfo.setNewsCd(newCd);
		}
		// 当知识来源为专家时，专家名字单独提取，保存时需要靠前保存，去除空位
		String[] expertCdOrName = request.getParameterValues("expertCdOrName");
		String sourceName = "";
		for (int i = 0; i < expertCdOrName.length; i++) {
			sourceName += expertCdOrName[i];
		}
		// 输入信息的校验
		// 错误信息
		ValidateErrorException exception = null;
		if (StringUtil.isBlank(newsInfo.getNewsName())) {
			exception = new ValidateErrorException("E1000001", new Object[] {"知识主题"}, "information/information_edit", "newsName");
		} else if (newsInfo.getNewsName().length() > 100) {
			exception = new ValidateErrorException("E1000005", new Object[] {"知识主题","100个"}, "information/information_edit", "newsName");
		}
		if (Objects.isNull(newsInfo.getNewsType())) {
			exception = this.rebuildException(exception, "E1000001", new Object[] { "知识分类" }, "information/information_edit", "newsType");
		}
		if (StringUtil.isBlank(newsInfo.getNewsNick())) {
			exception = this.rebuildException(exception, "E1000001", new Object[] { "知识标签" }, "information/information_edit", "newsNick");
		} else if (newsInfo.getNewsNick().length() > 5) {
			exception = this.rebuildException(exception, "E1000005", new Object[] {"知识标签","5个"}, "information/information_edit", "newsNick");
		}
		// 获得更新人的id，并创建更新时间
		String updateId = newsInfo.getUpdateId();
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
				topicFieldInfo.setBusiType(ZzjConstants.BUSI_TYPE_02);
				topicFieldInfo.setUpdateId(updateId);
				topicFieldInfo.setUpdateTime(updateTime);
				topicFieldInfo.setDeleteFlag(0);
				topicFieldInfo.setTopicCd(newsInfo.getNewsCd());
				topicFieldInfo.setTechFieldCd(codes[0]);
				topicFieldInfo.setRschFieldCd(codes[1]);
				fieldCd.add(topicFieldInfo);
				fieldInfoKey.add(topicFieldInfo);
			}
		} else {
			exception = this.rebuildException(exception, "E1000001", new Object[] { "领域" }, "information/information_edit", "choose_sel");
		}
		
		msg.put("fieldCd", fieldCd);
		if (StringUtil.isBlank(newsInfo.getNewsBrief())) {
			exception = this.rebuildException(exception, "E1000001", new Object[] { "知识介绍" }, "information/information_edit", "newsBrief");
		}
		if (Objects.isNull(newsInfo.getSourceType())) {
			exception = this.rebuildException(exception, "E1000001", new Object[] { "知识来源" }, "information/information_edit", "sourceType");
		}
		
		if (StringUtil.isBlank(sourceName)) {
			if (newsInfo.getSourceType() == this.SOURCE_TYPE_EXPERT)  {// 当知识来源为专家时，专家名字必须输入
				exception = this.rebuildException(exception, "E1000001", new Object[] { "专家姓名" }, "information/information_edit", "sourceOwner");
			}
		}
		else {
			if (!StringUtil.arrRepeatCheck(expertCdOrName)) {
				request.getParameterValues("expertCdOrName");
				exception = this.rebuildException(exception, "E1000023", new Object[] { "专家姓名" }, "information/information_edit", "sourceOwner");
			}
		}
		
		// 价格校验
		if (ZzjConstants.PAYMENT_KBN_1.equals(request.getParameter("paymentKbn"))) {
			newsInfo.setPaymentKbn(Integer.parseInt(ZzjConstants.PAYMENT_KBN_1));
			if (Objects.isNull(newsInfo.getPrice())) {
				exception = this.rebuildException(exception, "E1000001", new Object[] { "价格" }, "information/information_edit", "chargeInput");
			}
			if (newsInfo.getPrice().doubleValue() == 0.00d) {
				exception = this.rebuildException(exception, "E1000017", new Object[] { "价格" }, "information/information_edit", "chargeInput");
			}
		} else {
			newsInfo.setPaymentKbn(Integer.parseInt(ZzjConstants.PAYMENT_KBN_0));
			newsInfo.setPrice(null);
		}
		
		/**
		 * 解析推荐置顶消息
		 */
		
		// 置顶信息
		String toTopCode = request.getParameter("toTopCode");
		if (StringUtil.isNotBlank(toTopCode)) {
			RecommendInfo recommendInfo = new RecommendInfo();
			recommendInfo.setBusiType(ZzjConstants.BUSI_TYPE_02);
			recommendInfo.setTopicCd(newsInfo.getNewsCd());
			recommendInfo.setUpdateId(updateId);
			recommendInfo.setUpdateTime(updateTime);
			recommendInfo.setDeleteFlag(0);
			Integer toTopNum = Integer.parseInt(toTopCode);
			recommendInfo.setRecommendKbn(toTopNum);
			msg.put("toTopCode", recommendInfo);
			
			newsInfo.setTopFlag(ZzjConstants.TOP_FLG_1);
			newsInfo.setStatus(ZzjConstants.STATUS_1);
			if (recommendInfoBlo.isToTopRecommendCount(recommendInfo, ZzjConstants.RECOMMEND_KBN_2))
			{
				exception = this.rebuildException(exception, "E1000027", new Object[] {ZzjConstants.TO_TOP_NUM}, "information/information_edit", "toTop");
			}
		} else {
			newsInfo.setTopFlag(ZzjConstants.TOP_FLG_0);
		}
		// 推荐信息
		String recommend = request.getParameter("recommend");
		if (StringUtil.isNotBlank(recommend)) {
			RecommendInfo recommendInfo = new RecommendInfo();
			recommendInfo.setBusiType(ZzjConstants.BUSI_TYPE_02);
			recommendInfo.setTopicCd(newsInfo.getNewsCd());
			recommendInfo.setUpdateId(updateId);
			recommendInfo.setUpdateTime(updateTime);
			recommendInfo.setDeleteFlag(0);
			Integer recommendNum = Integer.parseInt(recommend);
			recommendInfo.setRecommendKbn(recommendNum);
			recommendInfo.setRecommendMemo(newsInfo.getRecommendMsg());
			msg.put("recommend", recommendInfo);
			
			newsInfo.setStatus(ZzjConstants.STATUS_1);
			if (recommendInfoBlo.isToTopRecommendCount(recommendInfo, ZzjConstants.RECOMMEND_KBN_1))
			{
				exception = this.rebuildException(exception, "E1000028", new Object[] {ZzjConstants.RECOMMEND_NUM}, "information/information_edit", "recommend");
			}
		} else {
			newsInfo.setRecommendMsg(null);
		}

//		// 得到当前登录用户信息
//		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
//		// 当非专家用户登录时，必须输入审核结果
//		if (Objects.isNull(newsInfo.getStatus()) && user.getRoleId() != ZzjConstants.EXPERT_ID_IN_USERS_INFO) {
//			if (exception == null) {
//				exception = new ValidateErrorException("E1000001", new Object[] { "审核结果" }, "information/information_edit", "statusSelect");
//			} else {
//				exception.addError("E1000001", new Object[] { "审核结果" }, "statusSelect");
//			}
//		}
		// 审核状态为拒绝时，必须输入拒绝理由
		if (newsInfo.getStatus() == ZzjConstants.STATUS_2) {
			if (StringUtil.isBlank(newsInfo.getRefuseMemo())) {
				exception = this.rebuildException(exception, "E1000001", new Object[] { "拒绝理由" }, "information/information_edit", "sel");
			}
		}
		
		/**
		 * 构造关键词信息
		 * 由所有领域名称#知识主题（#作者）构成
		 * 如：电子信息安全领域智慧工程安全领域#大数据的应用之我见#中国最权威的大数据论述资料
		 */
		// 前台传来的领域信息格式为："智慧城市->基础设施及网络技术,智慧城市->安全领域,智慧城市->宏观领域,智慧城市->应用领域,电子政务->宏观领域"
		String fieldNameForKeyWord = request.getParameter("fieldNameForKeyWord");
		if (StringUtil.isNotBlank(fieldNameForKeyWord)) {
			fieldNameForKeyWord = fieldNameForKeyWord.replace(",", "").replace("->", "");
		}
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(fieldNameForKeyWord).append("#");// 领域信息
		sBuffer.append(newsInfo.getNewsName());// 知识名
		/**
		 * 构造知识记录信息
		 */
		newsInfo.setUpdateId(updateId);
		newsInfo.setUpdateTime(updateTime);
		newsInfo.setDeleteFlag(0);
		if (StringUtil.isNotBlank(newsInfo.getLittleIcon())) {
			newsInfo.setLittleIconUrl(StringUtil.getImageURL(newsInfo.getLittleIcon()));
		}
		if (StringUtil.isNotBlank(newsInfo.getBigIcon())) {
			newsInfo.setBigIconUrl(StringUtil.getImageURL(newsInfo.getBigIcon()));
		}
		if (StringUtil.isNotBlank(newsInfo.getNewsAddress())) {
			newsInfo.setNewsAddressUrl(StringUtil.getPdfURL(newsInfo.getNewsAddress()));
		}
		if (isAdd == 1) {// 如果是添加知识信息需要保存创建人id和创建时间
			newsInfo.setCreateId(updateId);
			newsInfo.setCreateTime(updateTime);
		}
		if (newsInfo.getStatus() != null && newsInfo.getStatus() == 1) {// 当知识的状态为审核通过时，应该将拒绝理由置为空
			newsInfo.setRefuseMemo(null);
		}
		// 根据知识来源判断，
		// 如果不论来源为平台还是专家，需要根据专家姓名查询专家id，并保存其id，如果查不到id，则保存姓名，（重名的问题先不考虑）
		for (int i = 0 , j = 0; i < expertCdOrName.length; i++) {
			if (StringUtil.isBlank(expertCdOrName[i])) {
				// 清空现在作者
				switch (i) {
				case 0: newsInfo.setSourceOwner("");
					break;
				case 1: newsInfo.setExpertCd2("");
					break;
				case 2: newsInfo.setExpertCd3("");
					break;
				case 3: newsInfo.setExpertCd4("");
					break;
				case 4: newsInfo.setExpertCd5("");
					break;
				default:
					break;
				}
				continue;
			}
			
			// 根据姓名查找专家id，能查到则保存id，查不到则保存姓名
			String temp = expertInfoBlo.selectIdByExpertName(expertCdOrName[i]);
			if (StringUtil.isBlank(temp)) {
				temp = expertCdOrName[i];
			}
			sBuffer.append("#").append(expertCdOrName[i]);// 作者名
			switch (j++) {
			case 0: newsInfo.setSourceOwner(temp);
			        newsInfo.setSourceOwnerName(expertCdOrName[i]);
				break;
			case 1: newsInfo.setExpertCd2(temp);
					newsInfo.setExpertCd2Name(expertCdOrName[i]);
				break;
			case 2: newsInfo.setExpertCd3(temp);
					newsInfo.setExpertCd3Name(expertCdOrName[i]);
				break;
			case 3: newsInfo.setExpertCd4(temp);
					newsInfo.setExpertCd4Name(expertCdOrName[i]);
				break;
			case 4: newsInfo.setExpertCd5(temp);
					newsInfo.setExpertCd5Name(expertCdOrName[i]);
				break;
			default:
				break;
			}
		}
		newsInfo.setKeyword(sBuffer.toString());
		// 获取用户信息
		MstUsersInfo existUser = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		if (ZzjConstants.EXPERT_ID_IN_USERS_INFO.equals(existUser.getRoleId())) {
			// 区分知识保存或提交flag=save（保存操作，更新审核状态），flag=submit（提交操作，不更新审核状态）
			String flag = request.getParameter("flag");
			if ("save".equals(flag)) {
				if (ZzjConstants.STATUS_2 == newsInfo.getStatus()) {// 保存时，如果审核状态为拒绝，则修改为待审核，并清空拒绝理由
					newsInfo.setStatus(ZzjConstants.STATUS_0);
					newsInfo.setRefuseMemo(null);
				}
			}
			if ("submit".equals(flag)) {
				newsInfo.setStatus(ZzjConstants.STATUS_0);
				newsInfo.setRefuseMemo(null);
			}
		} else{
			// 状态同意调整，如果状态为空，则改为1
			if (Objects.isNull(newsInfo.getStatus())) {
				newsInfo.setStatus(ZzjConstants.STATUS_1);
			}			
		}		
		
		// 分享链接处理
		newsInfo.setShareUrl(this.getShareUrl(request, newsInfo));
		msg.put("newsRecord", newsInfo);
		// 设置领域信息
		newsInfo.setFieldCd(fieldInfoKey);
		// 获得不属于该需求的领域
		newsInfo.setOtherFieldCd(this.getOtherTopicFieldInfo(fieldInfoKey));
		/**
		 * 校验失败，需要抛出异常信息
		 */
		if (exception != null) {
			throw exception;
		}	
		return msg;
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
	 * 根据请求参数构造blo调用的map参数<br/>
	 * 封装知识一览前台传来的查询条件<br/>
	 * @param request 请求实例
	 * @param  startDate 开始时间
	 * @param  endDate 结束时间
	 * @return Map<String,Object> 用于数据库查询
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private Map<String, Object> parseMap(HttpServletRequest request, Date startDate, Date endDate) throws ClassNotFoundException, IOException {
		String flag = request.getParameter("flag");
		/**
		 * 获取查询条件的值
		 */
		// 知识主题
		String newsName = null;
		// 费用分类 0：免费，1：付费
		String[] paymentKbn = null;
		// 领域（技术领域）
		String[] techField = null;
		// 知识分类 
		String newsTypeStr = null;
		// 来源分类 1：为平台；2：为专家
		String[] sourceTypeStr = null;
		// 专家姓名
		String expertName = null;
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbn = null;
		// 当前页
		String pageNoStr = null;
		//从编辑返回列表页
		if(StringUtil.isNotBlank(flag) && "0".equals(flag)){
			String selectResource = request.getParameter("selectResourceNews");
			byte[] b = Base64.decode(selectResource);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			if(null != keyVO){
				newsName = keyVO.getNewsName();
				paymentKbn = keyVO.getPaymentKbn();
				techField = keyVO.getField();
				newsTypeStr = keyVO.getNewsType();
				sourceTypeStr = keyVO.getSourceType();
				expertName = keyVO.getExpertName();
				recommendKbn = keyVO.getRecommendKbn();
				pageNoStr = keyVO.getPageNo();
			}
		}else{
			newsName = request.getParameter("newsNameLike");
			paymentKbn = request.getParameterValues("paymentKbnLike");
			techField = request.getParameterValues("techField");
			newsTypeStr = request.getParameter("newsTypeLike");
			sourceTypeStr = request.getParameterValues("sourceTypeLike");
			expertName = request.getParameter("expertNameLike");
			recommendKbn = request.getParameterValues("recommendKbnLike");
			pageNoStr = request.getParameter("pageNo");
		}
		
		// 页大小
		Integer pageSize = ZzjConstants.DEFAULT_PAGESIZE;
		/**
		 * 构造map
		 */
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(newsName)) {
			queryMap.put("newsName", newsName);
		}
		if (StringUtil.isNotBlank(paymentKbn)) {
			if (paymentKbn.length == 1) {
				/*
				 * 页面回显使用的el表达式使用contains函数
				 * 而且回显用的数据将数据转换为逗号分隔的字符串： <c:if test="${fn:contains(selectStatus, statusCode)}">checked</c:if> 
				 * 当从编辑页面或者新增页面跳转过来时，页面的hidden会传来逗号分隔的字符串或者空字符串，此时paymentKbn.length == 1
				 * 需要将其转为数组供以下转换使用 （下同）
				 */
				paymentKbn = paymentKbn[0].split(",");
			}
			if (StringUtil.isNotBlank(paymentKbn[0])) {
				/*
				 * 当列表查询时未选择该过滤条件，进入编辑/添加页面返回时，
				 * 该过滤条件hidden返回长度为1的字符串数组，但是这个元素的值是空字符串，
				 * 所以这里需要判断auditStatus[0]是否为空 （下同）
				 */
				queryMap.put("paymentKbn", Arrays.asList(paymentKbn));
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
		if (StringUtil.isNotBlank(newsTypeStr) && StringUtil.isNumber(newsTypeStr)) {
			Integer newsType = Integer.parseInt(newsTypeStr);
			queryMap.put("newsType", newsType);
		}
		if (StringUtil.isNotBlank(recommendKbn)) {
			if (recommendKbn.length == 1) {
				recommendKbn = recommendKbn[0].split(",");
			}
			if (StringUtil.isNotBlank(recommendKbn[0])) {
				queryMap.put("recommendKbn", Arrays.asList(recommendKbn));
			}
		}
		if (StringUtil.isNotBlank(sourceTypeStr)) {
			if (sourceTypeStr.length == 1) {
				sourceTypeStr = sourceTypeStr[0].split(",");
			}
			if (StringUtil.isNotBlank(sourceTypeStr[0])) {
				Integer[] sourceType = new Integer[sourceTypeStr.length];
				for (int i = 0; i < sourceTypeStr.length; i++) {
					sourceType[i] = Integer.parseInt(sourceTypeStr[i]);
					if (ZzjConstants.SOURCE_TYPE_1 == sourceType[i]) {
						queryMap.put("sourceType1", sourceType[i]);
					}
					if (ZzjConstants.SOURCE_TYPE_2 == sourceType[i]) {
						queryMap.put("sourceType2", sourceType[i]);
					}
				}
			}
		}
		if (StringUtil.isNotBlank(expertName)) {
			List<String> expertIds = expertInfoBlo.selectIdsByExpertName(expertName);
			expertIds.add(expertName);
			queryMap.put("expertIds", expertIds);
		}
		if (Objects.nonNull(startDate)) {
			queryMap.put("startDate", startDate);
		}
		if (Objects.nonNull(endDate)) {
			queryMap.put("endDate", endDate);
		}
		
		// 获取用户信息
		MstUsersInfo existUser = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		if (ZzjConstants.EXPERT_ID_IN_USERS_INFO.equals(existUser.getRoleId())) {
			List<String> expertIds = new ArrayList<String>();
			expertIds.add(existUser.getUserId());
			queryMap.put("expertIds", expertIds);
			queryMap.put("sourceType2", ZzjConstants.SOURCE_TYPE_2);
		}
		// 查询起始记录数
		if (StringUtil.isNotBlank(pageNoStr)) {
			Integer pageNo = Integer.parseInt(pageNoStr);
			if (pageNo > 0) {
				queryMap.put("dbIndex", (pageNo - 1) * pageSize);
				queryMap.put("pageNo", pageNo);
			}
		} else {
			queryMap.put("dbIndex", 0);
			queryMap.put("pageNo", 1);
		}
		queryMap.put("pageSize", pageSize);
		return queryMap;
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

