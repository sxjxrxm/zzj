/**
 * Project Name:zzj-web
 * File Name:VideoController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.model.KeyVO;
import com.zzj.db.model.LiveInfoVO;
import com.zzj.db.model.PageResult;
import com.zzj.db.model.VideoInfoVO;
import com.zzj.manage.service.CommonService;
import com.zzj.manage.service.VideoService;
import com.zzj.util.Base64;
import com.zzj.util.CSVUtils;
import com.zzj.util.DateUtil;
import com.zzj.util.PropertyUtil;
import com.zzj.util.SerializUtils;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>VideoController <br/>
 * <p><strong>功能说明: </strong></p>视频管理Controller <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月09日下午1:24:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("video")
public class VideoController extends BaseController {
	
	/**
	 * 业务操作Service
	 */
	@Autowired
	private  VideoService videoService;
	
	/**
	 * 共通业务操作Service
	 */
	@Autowired
	private CommonService commonService;
	
	/**
	 * 按条件查询视频一览<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping(value="/list.htm")
	public String list(HttpServletRequest request) throws Exception {
		
		String doSearch = request.getParameter("doSearch");
		String pageNo = request.getParameter("videoPageNo");
		String isAdd = request.getParameter("isAdd");
		String videoType = request.getParameter("selectKey.videoType");
		if (StringUtil.isNotBlank(doSearch) || StringUtil.isNotBlank(pageNo)
				|| StringUtil.isNotBlank(isAdd)) {
			request.setAttribute("doSearch", "0");
			if (ZzjConstants.VIDEO_TYPE_1.equals(videoType)) { // 视频
				VideoInfoVO videoInfo = videoService.selectKeyMapVideo(request, "");
				PageResult<VideoInfoVO> resultList = videoService.searchPaggingVideo(videoInfo);	
				request.setAttribute("resultList", resultList);
				if (resultList.getItems() == null || resultList.getItems().size() == 0)
				{
					request.setAttribute(ZzjConstants.SYS_WARNING_KEY, PropertyUtil.getMessageContent("E1000015", null));
				}
			}
			if (ZzjConstants.VIDEO_TYPE_2.equals(videoType)) { // 直播
				LiveInfoVO liveInfo = videoService.selectKeyMapLive(request, "");
				PageResult<LiveInfoVO> resultList = videoService.searchPaggingLive(liveInfo);	
				request.setAttribute("resultList", resultList);
				if (resultList.getItems() == null || resultList.getItems().size() == 0)
				{
					request.setAttribute(ZzjConstants.SYS_WARNING_KEY, PropertyUtil.getMessageContent("E1000015", null));
				}
			}
		}
		request.setAttribute("doSearch", "0");
		
		return "video/video_list";
	}
	
	

	/**
	 * 按条件查询视频一览CSV导出<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @throws Exception 
	 */
	@RequestMapping(value="/CSV.htm")
	public void outputCSV(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> mstCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_TECHCODEINFOS);
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> rishCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_RISHCODEINFOS);
		mstCodeInfos.addAll(rishCodeInfos);
		String videoType = request.getParameter("selectKey.videoType");
		List<ArrayList<String>> list = null;
		if (ZzjConstants.VIDEO_TYPE_1.equals(videoType)) { // 视频
			// 数据检索
			VideoInfoVO videoInfo = videoService.selectKeyMapVideo(request, "");
			List<VideoInfoVO> resultList = videoService.searchAllVideo(videoInfo);
			// 数据做成
			list = videoService.getOutputContentVideo(resultList, mstCodeInfos);	
		}
		if (ZzjConstants.VIDEO_TYPE_2.equals(videoType)) { // 直播
			// 数据检索
			LiveInfoVO LiveInfo = videoService.selectKeyMapLive(request, "");
			List<LiveInfoVO> resultList = videoService.searchAllLive(LiveInfo);
			// 数据做成
			list = videoService.getOutputContentLive(resultList, mstCodeInfos);	
		}
        // 文件生成 
        String title = ZzjConstants.FILE_OUTPUT_NAME_VIDEO + "_" + DateUtil.getDateTimeStr(new Date()) + ZzjConstants.FILE_OUTPUT_CSV;
		CSVUtils.createCSVFile(list, PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH), title);
		// 文件导出
		CSVUtils.exportToExcel(PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH) + File.separator + title, response, false);
	}
	
	/**
	 * 删除记录。<br/>
	 * @param  request 请求实例
	 * @param  videoCd 视频编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/del.htm")
	public String delete(HttpServletRequest request, String videoCd) throws Exception {
		String videoType = request.getParameter("selectKey.videoType");
		if (ZzjConstants.VIDEO_TYPE_1.equals(videoType)) { // 视频
			// 删除记录
			videoService.deleteVideo(videoCd) ;
			VideoInfoVO videoInfo = videoService.selectKeyMapVideo(request, "");
			PageResult<VideoInfoVO> resultList = videoService.searchPaggingVideo(videoInfo);	
			request.setAttribute("resultList", resultList);
		}
		if (ZzjConstants.VIDEO_TYPE_2.equals(videoType)) { // 直播
			// 删除记录
			videoService.deleteLive(videoCd);
			LiveInfoVO liveInfo = videoService.selectKeyMapLive(request, "");
			PageResult<LiveInfoVO> resultList = videoService.searchPaggingLive(liveInfo);
			request.setAttribute("resultList", resultList);
		}
		request.setAttribute("doSearch", "0");
		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000002", null));
		
		return "video/video_list";
	}
	
	/**
	 * 跳转到编辑页面。<br/>
	 * @param  请求实例
	 * @param  videoCd 视频编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/editVideo.htm")
	public String editVideo(HttpServletRequest request, String videoCd) throws Exception {
		// 参数保存
//		videoService.selectKeyMapVideo(request, "edit");
		videoService.selectKeySave(request);
		request.setAttribute("isAdd", request.getParameter("isAdd"));
		
		// 画面刷新
		videoService.loadVideo(request, videoCd);
		return "video/video_edit";
	}
	
	/**
	 * 跳转到编辑页面。<br/>
	 * @param  request 请求实例
	 * @param  videoCd 直播编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/editLive.htm")
	public String editLive(HttpServletRequest request, String liveCd) throws Exception {
		// 参数保存
//		videoService.selectKeyMapLive(request, "edit");
		videoService.selectKeySave(request);
		// 画面刷新
		videoService.loadLive(request, liveCd);
		return "live/live_edit";
	}
	
	/**
	 * 跳转到编辑页面。<br/>
	 * @param  request 请求实例
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping(value="/save.htm")
	public String save(HttpServletRequest request) throws Exception {
		
		// 参数保存
		videoService.selectKeySave(request);		
		// 携带列表页面查询条件
		VideoInfoVO record = videoService.getSaveVideoInfo(request, "");				
		// 更新数据库
		int isSuccess = videoService.save(record);
		if(ZzjConstants.NUM_I_1 == isSuccess){
			String isAdd = request.getParameter("isAdd");
			request.setAttribute("isAdd", isAdd);
			//添加
			if(StringUtil.isNotBlank(isAdd) && "1".equals(isAdd)){
				String selectKey = request.getParameter("selectKey");
				byte[] b = Base64.decode(selectKey);
				KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
				String videoName = keyVO.getVideoName();
				String[] paymentKbnDisp = keyVO.getPaymentKbn();
				String[] field = keyVO.getField();
				String sDate = keyVO.getsDate();
				String eDate = keyVO.geteDate();
				String[] recommendKbn = keyVO.getRecommendKbn();
				if(StringUtil.isBlank(videoName)
					&& (null == paymentKbnDisp || paymentKbnDisp.length == ZzjConstants.NUM_I_0)
					&& (null == field || field.length == ZzjConstants.NUM_I_0)
					&& StringUtil.isBlank(sDate) && StringUtil.isBlank(eDate)
					&& (null == recommendKbn || recommendKbn.length == ZzjConstants.NUM_I_0)){
					keyVO.setVideoName(record.getVideoName());
					//重新序列化
					byte[] data = SerializUtils.serializ(keyVO);  
					String selectKey2 = Base64.encode(data);
					request.setAttribute("selectKey", selectKey2);
				}
			}
		}
		// 画面刷新
		videoService.loadVideo(request, record.getVideoCd());		
		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));
		
		return "video/video_edit";
	}
	
	/**
	 * 知识编辑页面图片异步请求<br/>
	 * @param  file 文件请求
	 * @param  req 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/upload.htm")
	public Map<String, String> queryPic(MultipartRequest file, HttpServletRequest req) throws Exception{
		Map<String, String> resultMap = videoService.queryPic(file, req);
		return resultMap;
	}
	
	/**
	 * 视频编辑页面富文本编辑器中的图片请求<br/>
	 * @param  file 文件请求
	 * @return Map<String, String> 返回图片保存的url
	 * @throws Exception
	 */
	@RequestMapping(value = "/textEditorImg.htm")
	@ResponseBody
	public Map<String, String> textEditorImg(MultipartRequest file) throws Exception{
		return commonService.textEditorImg(file, ZzjConstants.UPLOAD_TYPE_CONTENT, ZzjConstants.BUSI_TYPE_03);
	}

}

