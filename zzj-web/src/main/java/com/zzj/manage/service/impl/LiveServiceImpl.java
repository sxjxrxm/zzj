/**
 * Project Name:zzj-web
 * File Name:LiveServiceImpl.java
 * Package Name:com.zzj.manage.service.impl
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.zzj.core.exception.ValidateErrorException;
import com.zzj.db.blo.LiveInfoBlo;
import com.zzj.db.blo.MstCodeInfoBlo;
import com.zzj.db.blo.MstFieldInfoBlo;
import com.zzj.db.blo.MstSequenceInfoBlo;
import com.zzj.db.blo.TopicFieldInfoBlo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.TopicFieldInfo;
import com.zzj.db.dto.TopicFieldInfoKey;
import com.zzj.db.model.LiveInfoVO;
import com.zzj.manage.service.CommonService;
import com.zzj.manage.service.HtmlService;
import com.zzj.manage.service.LiveService;
import com.zzj.util.DateUtil;
import com.zzj.util.HttpUtil;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>LiveServiceImpl业务数据库操作类 <br/>
 * <p><strong>功能说明: </strong></p>直播业务数据相关操作处理<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月14日下午1:24:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Service
public class LiveServiceImpl implements LiveService {
	
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
	 *取得系统配置用blo
	 */
	@Autowired
	private MstCodeInfoBlo  mstCodeInfoBlo;
	
	/**
	 * H5页面生成服务
	 */
	@Autowired
	private HtmlService htmlService;
	
	/**
	 * 共通业务处理
	 */
	@Autowired
	private CommonService commonService;
	
	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public void selectKeySave(HttpServletRequest request) throws Exception {
		String flag = request.getParameter("saveFlag");
		if(StringUtil.isNotBlank(flag) && "1".equals(flag)){
			String selectKey = request.getParameter("selectKey");
			request.setAttribute("videoPageNo", request.getParameter("videoPageNo"));
			request.setAttribute("selectKey", selectKey);
			return;
		}
		// 视频主题
		String videoName = request.getParameter("selectKey.videoName");
		// 费用
		String paymentKbnDisp = request.getParameter("selectKey.paymentKbnDisp");
		// 领域（技术领域）
		String field = request.getParameter("selectKey.field");
		// 视频分类
		String videoType = request.getParameter("selectKey.videoType");
		// 提出日期
		String sDate = request.getParameter("selectKey.sDate");
		// 结束日期
		String eDate = request.getParameter("selectKey.eDate");
		// 推荐置顶 （1 推荐， 2 置顶）
		String recommendKbn = request.getParameter("selectKey.recommendKbn");
		
		Map<String, Object> showBackMap = new HashMap<String, Object>();
		
		if (StringUtil.isNotBlank(videoName)) {
			showBackMap.put("videoName", videoName);
		}
		if (StringUtil.isNotBlank(paymentKbnDisp)) {
			showBackMap.put("answerStatus", paymentKbnDisp);
		}
		if (StringUtil.isNotBlank(field)) {
			showBackMap.put("field", field);
		}
		if (StringUtil.isNotBlank(videoType)) {
			showBackMap.put("videoType", videoType);
		}
		if (StringUtil.isNotBlank(eDate)) {
			showBackMap.put("eDate", eDate);
		}
		if (StringUtil.isNotBlank(sDate)) {
			showBackMap.put("sDate", eDate);
		}
		if (StringUtil.isNotBlank(recommendKbn)) {
			showBackMap.put("recommendKbn", recommendKbn);
		}
		
		// 页面回显数据
		request.setAttribute("selectKey", showBackMap);
		
		String pageNo = request.getParameter("videoPageNo");
		if (StringUtil.isNotBlank(pageNo)) {
			request.setAttribute("videoPageNo", pageNo);
		}
		
	}

	/**
	 * 根据请求参数构造blo调用的map参数<br/>
	 * @param request 请求实例
	 * @param pagging 分页
	 * @return LiveInfoVO 用于数据库查询
	 * @throws Exception 
	 */
	@Override
	public LiveInfoVO getSaveInfo(HttpServletRequest request, String pagging) throws Exception {

		// 直播编码
		String liveCd = request.getParameter("liveInfo.liveCd");
		// 直播主题
		String liveName = request.getParameter("liveInfo.liveName");
		// 直播介绍
		String liveBrief = request.getParameter("liveInfo.liveBrief");
		// 费用
		String price = request.getParameter("liveInfo.price");
		// 开始时间
		String startTime = request.getParameter("liveInfo.startTime");
		// 结束时间
		String endTime = request.getParameter("liveInfo.endTime");
		// 直播过期时间
		String invalidDate = request.getParameter("liveInfo.invalidDate");
		// 列表图片
		String littleIcon = request.getParameter("littleIcon");
		// 详情图片
		String bigIcon = request.getParameter("bigIcon");
		// 关键词
		String fieldNameForKeyWord = request.getParameter("fieldNameForKeyWord");
		

		// 错误信息
		ValidateErrorException exception = null;
		
		// 获得更新人的id，并创建更新时间
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		String updateId = user.getUserId();
		Date updateTime = new Date();

		// 保存信息作成
		LiveInfoVO record = new LiveInfoVO();
		// 更新日
		record.setUpdateTime(updateTime);
		// 更新者
		record.setUpdateId(updateId);

		if (StringUtil.isNotBlank(liveCd)) {
			record.setLiveCd(liveCd);
		}
		if (StringUtil.isNotBlank(liveName)) {
			if (liveName.length() > 30) {
				exception = new ValidateErrorException("E1000005", new Object[] { "直播主题","30个" }, "live/live_edit", "liveName");
			} else {
				record.setLiveName(liveName);
			}
		} else {
			exception = new ValidateErrorException("E1000001", new Object[] { "直播主题" }, "live/live_edit", "liveName");
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
				topicFieldInfo.setTopicCd(record.getLiveCd());
				topicFieldInfo.setTechFieldCd(codes[0]);
				topicFieldInfo.setRschFieldCd(codes[1]);
				fieldInfo.add(topicFieldInfo);
				fieldInfoKey.add(topicFieldInfo);
			}
		} else {
			exception = this.rebuildException(exception,"E1000001",new Object[] { "领域" }, "live/live_edit", "choose_sel");
		}
		record.setFieldInfo(fieldInfo);
		record.setFieldCd(fieldInfoKey);
		// 获得不属于该视频的领域
		record.setOtherFieldCd(this.getOtherTopicFieldInfo(fieldInfoKey));
		
		if (StringUtil.isNotBlank(liveBrief)) {
//			if (liveBrief.length() > 500) {
//				exception = this.rebuildException(exception, "E1000005", new Object[] {"直播介绍","500个"}, "live/live_edit", "liveBrief");
//			} else {
			record.setLiveBrief(liveBrief);
//			}
		} else {
			exception = this.rebuildException(exception,"E1000001",new Object[] { "直播介绍" }, "live/live_edit", "liveBrief");
		}
		
		if (StringUtil.isNotBlank(startTime)) {
			if (DateUtil.getDateFormatYMDhm(startTime).compareTo(new Date()) > 0) {
				record.setStartTime(DateUtil.getDateFormatYMDhm(startTime));
			} else {
				exception = this.rebuildException(exception,"E1000033",new Object[] { "直播时间(开始时间)","当前时间" }, "live/live_edit", "startTime");
			}
		} else {
			exception = this.rebuildException(exception,"E1000001",new Object[] { "直播开始时间" }, "live/live_edit", "startTime");
		}
		
		if (StringUtil.isNotBlank(endTime)) {
			if (DateUtil.getDateFormatYMDhm(endTime).compareTo(DateUtil.getDateFormatYMDhm(startTime)) > 0) {
				record.setEndTime(DateUtil.getDateFormatYMDhm(endTime));
			} else {
				exception = this.rebuildException(exception,"E1000033",new Object[] { "直播时间(结束时间)","直播时间(开始时间)" }, "live/live_edit", "endTime");
			}
		} else {
			exception = this.rebuildException(exception,"E1000001",new Object[] { "直播结束时间" }, "live/live_edit", "endTime");
		}
		
		if (StringUtil.isNotBlank(invalidDate)) {
			if (DateUtil.getDateFormatYMDhm(invalidDate).compareTo(DateUtil.getDateFormatYMDhm(endTime)) > 0) {
				record.setInvalidDate(DateUtil.getDateFormatYMDhm(invalidDate));
				
				// 生成推流地址
				getPullAddress(record);
				if (StringUtil.isBlank(record.getPushAddress()) || StringUtil.isBlank(record.getRtmpAddress()) 
						|| StringUtil.isBlank(record.getFlvAddress()) || StringUtil.isBlank(record.getHlsAddress())) {
					exception = this.rebuildException(exception, "E1000034", null, "live/live_edit", "save");
				}
				
			} else {
				exception = this.rebuildException(exception,"E1000033",new Object[] { "直播过期时间","直播时间(结束时间)" }, "live/live_edit", "invalidDate");
			}
			
		} else {
			exception = this.rebuildException(exception,"E1000001",new Object[] { "直播过期时间" }, "live/live_edit", "invalidDate");
		}
		
//		// 图片非空校验
//		if (StringUtil.isNotBlank(littleIcon)) {
//			record.setLittleIcon(littleIcon);
//			record.setLittleIconUrl(StringUtil.getImageURL(littleIcon));
//		} else {
//			exception = this.rebuildException(exception,"E1000001",new Object[] { "列表图片上传" }, "live/live_edit", "littleIconImg");
//		}
//		if (StringUtil.isNotBlank(bigIcon)) {
//			record.setBigIcon(bigIcon);
//			record.setBigIconUrl(StringUtil.getImageURL(bigIcon));
//		} else {
//			exception = this.rebuildException(exception,"E1000001",new Object[] { "详情图片上传" }, "live/live_edit", "bigIconImg");
//		}
		
		// 关键词
		if (StringUtil.isNotBlank(fieldNameForKeyWord) && StringUtil.isNotBlank(liveName)
				&& StringUtil.isNotBlank(liveBrief)) {
			fieldNameForKeyWord = fieldNameForKeyWord.replace(",", "").replace("->", "");
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append(fieldNameForKeyWord).append("#").append(liveName).append("#").append(liveBrief);
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
		
		// 执行保存操作时生成地址
//		String saveFlag = request.getParameter("save");
//		if ("1".equals(saveFlag)) {
			// 推流地址生成校验
//			String pushAddress = request.getParameter("liveInfo.pushAddress");
//			String rtmpAddress = request.getParameter("liveInfo.rtmpAddress");
//			String flvAddress = request.getParameter("liveInfo.flvAddress");
//			String hlsAddress = request.getParameter("liveInfo.hlsAddress");
////			String roomId = request.getParameter("liveInfo.roomId");
//			if (StringUtil.isBlank(pushAddress) || StringUtil.isBlank(rtmpAddress) || StringUtil.isBlank(flvAddress) || StringUtil.isBlank(hlsAddress)) {
//				exception = this.rebuildException(exception, "E1000034", null, "live/live_edit", "pushAddress");
//			} else {
//				record.setPushAddress(pushAddress);
//				record.setRtmpAddress(rtmpAddress);
//				record.setFlvAddress(flvAddress);
//				record.setHlsAddress(hlsAddress);
//			}
//			if (StringUtil.isBlank(roomId)) {
//				exception = this.rebuildException(exception,"E1000034",null, "live/live_edit", "getPullAddressBtn");
//			} else {
//				record.setRoomId(roomId);
//			}
//		}
		
		// 价格校验
		if (ZzjConstants.PAYMENT_KBN_1.equals(request.getParameter("paymentKbn"))) {
			record.setPaymentKbn(Integer.parseInt(ZzjConstants.PAYMENT_KBN_1));
			if (StringUtil.isNotBlank(price)) {
				if (Double.parseDouble(price) == 0.00d) {
					exception = this.rebuildException(exception,"E1000017",new Object[] { "价格" },"live/live_edit","chargeInput");
				} else {
					record.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
				}
			} else {
				exception = this.rebuildException(exception,"E1000001",new Object[] { "价格" },"live/live_edit","chargeInput");
			}
		} else {
			record.setPaymentKbn(Integer.parseInt(ZzjConstants.PAYMENT_KBN_0));
			record.setPrice(null);
		}
		
		// 有异常发生
		if (exception != null || StringUtil.isNotBlank(pagging)) {
			if (exception != null) {
				request.setAttribute("liveInfo", record);
				throw exception;
			}
			request.setAttribute("liveInfo", record);
		} else {
			record.setDeleteFlag(2);
		}

		return record;
	}

	/**
	 * 获得不属于该视频的领域
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
	 * 根据主键更新记录
	 * @param  LiveInfoVO record 视频信息
	 * @return int 更新结果条目数
	 * @throws Exception 
	 */
	@Override
	public int save(LiveInfoVO record) throws Exception {
		int result = 0;
		// 1.保存直播信息
		if (StringUtil.isBlank(record.getLiveCd())) {
			String liveCd = mstSequenceInfoBlo.selectSequenceInfo(ZzjConstants.LIVE_NO);
			record.setLiveCd(liveCd);
			record.setCreateId(record.getUpdateId());
			record.setCreateTime(record.getUpdateTime());
//			// 将临时保存的图片文件重命名并修改图片地址字段
//			if (StringUtil.isNotBlank(record.getLittleIcon())) {
//				String realPath = UploadUtils.getRealPath(record.getLittleIcon(), record.getLiveCd());
//				record.setLittleIcon(realPath);
//			}
//			if (StringUtil.isNotBlank(record.getBigIcon())) {
//				String realPath = UploadUtils.getRealPath(record.getBigIcon(), record.getLiveCd());
//				record.setBigIcon(realPath);
//			}
			
			// 创建聊天室
			String roomId = this.createOrDestroyRoom(record);
			if(StringUtil.isBlank(roomId)) {
				throw new ValidateErrorException("E1000034", null, "live/live_edit", "getPullAddressBtn");
			}
			record.setRoomId(roomId);
			// 分享链接处理
			record.setShareUrl(this.getShareUrl(null, record));
			result = liveInfoBlo.insertSelective(record);
		} else {
//			// 将临时保存的图片文件重命名并修改图片地址字段
//			if (StringUtil.isNotBlank(record.getLittleIcon())) {
//				String realPath = UploadUtils.getRealPath(record.getLittleIcon(), record.getLiveCd());
//				record.setLittleIcon(realPath);
//			}
//			if (StringUtil.isNotBlank(record.getBigIcon())) {
//				String realPath = UploadUtils.getRealPath(record.getBigIcon(), record.getLiveCd());
//				record.setBigIcon(realPath);
//			}
			// 分享链接处理
			record.setShareUrl(this.getShareUrl(null, record));
			result = liveInfoBlo.updateByPrimaryKeySelective(record);
		}
		
		if (result != 1) {
			return result;
		}
		// 2.保存领域
		TopicFieldInfo topicFieldInfo = new TopicFieldInfo();
		topicFieldInfo.setBusiType(ZzjConstants.BUSI_TYPE_03);
		topicFieldInfo.setTopicCd(record.getLiveCd());
		topicFieldInfo.setDeleteFlag(1);
		topicFieldInfo.setUpdateId(record.getUpdateId());
		topicFieldInfo.setUpdateTime(record.getUpdateTime());
		topicFieldInfoBlo.delete(topicFieldInfo);
		List<TopicFieldInfo> fieldInfo = record.getFieldInfo();
		if (fieldInfo != null && fieldInfo.size() > 0) {
			for (TopicFieldInfo field : fieldInfo) {
				// 根据主键查询是否存在
				field.setTopicCd(record.getLiveCd());
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
		
		// 生成静态页面
		htmlService.generateLiveHtml(null, record.getLiveCd());
		
		return result;
	}
	
	/**
	 * 发布直播信息。<br/>
	 * @param  请求实例
	 * @return 返回页面名称
	 */
	@Override
	public LiveInfoVO publish(HttpServletRequest request) {
		String liveCd = request.getParameter("liveInfo.liveCd");
		LiveInfoVO liveInfoVO = null;
		if (StringUtil.isNotBlank(liveCd)) {
			liveInfoVO = liveInfoBlo.selectByPrimaryKey(liveCd);
		}
		if (liveInfoVO != null) {
			liveInfoVO.setDeleteFlag(0);
			int flag = liveInfoBlo.updateByPrimaryKeySelective(liveInfoVO);
			if (flag == 1) {
				request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000011", null));
			}
		}
		return liveInfoVO;
	}

	/**
	 * 创建聊天室获取聊天室ID。<br/>
	 * 解散聊天室返回成功信息。
	 * @param  record 直播信息
	 * @return String 开启聊天室返回聊天室ID，解散聊天室返回成功信息。
	 * @throws Exception 
	 */
	@Override
	public String createOrDestroyRoom(LiveInfoVO record) throws Exception {
		// 请求URL
		String url = "";
		// userId
		String userId = "";
		// 参数设置
		JSONObject json = new JSONObject();
		if (StringUtil.isBlank(record.getRoomId())) 
		{	// 创建群组
//			userId = record.getUpdateId();
			userId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_USERID);
			url = ZzjConstants.HTTP_CREATE_COURSE;
			json.put("Owner_Account", userId);
			json.put("Type", "ChatRoom");
			json.put("Name", record.getLiveCd());
		}
		else
		{   // 解散群组
//			userId = record.getUpdateId();
			userId = PropertyUtil.getConfigValue(ZzjConstants.QCLOUDIM_USERID);
			url = ZzjConstants.HTTP_DESTROY_COURSE;
			json.put("GroupId", record.getRoomId());
		}
		
		// 取得签名
		String userSig = commonService.checkUserSig(userId);
			
		url = PropertyUtil.getHttpContent(url, new Object[] {userSig, userId, ZzjConstants.SDKAPPID_YUNTONGXUN});		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json; charset=UTF-8");
		String response = HttpUtil.post(url, json.toString(), headers);
		String roomId = "";
		if (response.indexOf("OK") > 0)
		{
			if (StringUtil.isBlank(record.getRoomId())) {
				// 获取聊天室ID
				roomId = response.substring(response.lastIndexOf("@"), response.lastIndexOf("@") + 14).trim();
			}
			else {				
				roomId = ZzjConstants.SUCCESS;
			}
		}		
		return roomId;
	}
	
	/**
	 * 生成推流地址及播放地址，刷新编辑页面。<br/>
	 * @param  record 直播信息
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@Override
	public LiveInfoVO getPullAddress(LiveInfoVO record) throws Exception {
		MstCodeInfo code = new MstCodeInfo();
		code.setCodeType(ZzjConstants.LIVEPUSH);
		
		List<MstCodeInfo> mstCodeInfos = mstCodeInfoBlo.selectSelective(code);
		
		// 准备数据
		String key = null;
		String userId = null; // 客户ID
		String safeUrl = null;
		String streamId = StringUtil.getLiveCode(); // 直播码
		long txTime = record.getInvalidDate().getTime()/1000;
		
		for (MstCodeInfo m : mstCodeInfos) {
			if ("1".equals(m.getCode())) {
				userId = m.getCodeName();
			}
			if ("3".equals(m.getCode())) {
				key = m.getCodeName();
			}
		}
		
		if (StringUtil.isNotBlank(key) && StringUtil.isNotBlank(userId)) {
			String liveCode = MessageFormat.format("{0}_{1}", new Object[]{userId, streamId});
			safeUrl = this.getSafeUrl(key, liveCode, txTime);
			// 地址生成                                                   userId                         userId_key            userId safeUrl
//			String pushAddress = null; // rtmp://5603.livepush.myqcloud.com/live/5603_01691d3819?bizid=5603&txSecret=0f089d47473cc&txTime=58501AFF
//			String rtmpAddress = null; // rtmp://5603.liveplay.myqcloud.com/live/5603_01691d3819
//			String flvAddress = null;  // http://5603.liveplay.myqcloud.com/live/5603_01691d3819.flv
//			String hlsAddress = null;  // http://5603.liveplay.myqcloud.com/live/5603_01691d3819.m3u8
			
//			rtmp://5603.livepush.myqcloud.com/live/5603_ce7b2623ab24e300ce794c0c831c3721?bizid=5603&txSecret=683d7cad8c3d69c6d5d3d7b605b11872&txTime=15950759400"
//			rtmp://5603.liveplay.myqcloud.com/live/5603_ce7b2623ab24e300ce794c0c831c3721
//			http://5603.liveplay.myqcloud.com/live/5603_ce7b2623ab24e300ce794c0c831c3721.flv
//			http://5603.liveplay.myqcloud.com/live/5603_ce7b2623ab24e300ce794c0c831c3721.m3u8
				
			String pushAddress = new StringBuilder().append("rtmp://").append(userId).append(".livepush.myqcloud.com/live/").append(userId)
					.append("_").append(streamId).append("?bizid=").append(userId).append("&").append(safeUrl).toString();
			String rtmpAddress = new StringBuilder().append("rtmp://").append(userId).append(".liveplay.myqcloud.com/live/").append(userId).append("_").append(streamId).toString();
			String flvAddress = new StringBuilder().append("http://").append(userId).append(".liveplay.myqcloud.com/live/").append(userId).append("_").append(streamId).append(".flv").toString();
			String hlsAddress = new StringBuilder().append("http://").append(userId).append(".liveplay.myqcloud.com/live/").append(userId).append("_").append(streamId).append(".m3u8").toString();
			record.setPushAddress(pushAddress);
			record.setRtmpAddress(rtmpAddress);
			record.setFlvAddress(flvAddress);
			record.setHlsAddress(hlsAddress);
//			record.setRoomId(userId+"_"+streamId);
		}
		
		if (StringUtil.isNotBlank(record.getLittleIcon())) {
			record.setLittleIconUrl(StringUtil.getImageURL(record.getLittleIcon()));
		}
		if (StringUtil.isNotBlank(record.getBigIcon())) {
			record.setBigIconUrl(StringUtil.getImageURL(record.getBigIcon()));
		}
		
		return record;
	}

	// 第三方方法如下：
	/*
	 * KEY+ stream_id + txTime （key，直播码，过期时间）
	 */
	private String getSafeUrl(String key, String streamId, long txTime) {
		String input = new StringBuilder().append(key).append(streamId).append(Long.toHexString(txTime).toUpperCase())
				.toString();

		String txSecret = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			txSecret = byteArrayToHexString(messageDigest.digest(input.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return txSecret == null ? ""
				: new StringBuilder().append("txSecret=").append(txSecret).append("&").append("txTime=")
						.append(Long.toHexString(txTime).toUpperCase()).toString();
	}

	private static String byteArrayToHexString(byte[] data) {
		char[] out = new char[data.length << 1];

		for (int i = 0, j = 0; i < data.length; i++) {
			out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS_LOWER[0x0F & data[i]];
		}
		return new String(out);
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
	 * 根据直播信息获得分享链接
	 * @param  liveInfo 直播信息
	 * @return String  直播分享链接
	 * @throws Exception
	 */
	private String getShareUrl(HttpServletRequest request, LiveInfoVO liveInfo) throws Exception {
		if (liveInfo == null) {
			return null;
		}
		String shareUrl = htmlService.generateLiveHtmlUrl(liveInfo.getLiveCd());
		return shareUrl;
	}

}

