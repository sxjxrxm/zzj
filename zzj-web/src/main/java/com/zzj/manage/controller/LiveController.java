/**
 * Project Name:zzj-web
 * File Name:LiveController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.zzj.core.web.controller.BaseController;
import com.zzj.db.model.LiveInfoVO;
import com.zzj.manage.service.LiveService;
import com.zzj.manage.service.VideoService;
import com.zzj.util.PropertyUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>LiveController <br/>
 * <p><strong>功能说明: </strong></p>视频管理Controller <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月14日下午1:24:30 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("live")
public class LiveController extends BaseController {
	
	/**
	 * 业务操作Service
	 */
	@Autowired
	private  LiveService liveService;
	
	/**
	 * 业务操作Service
	 */
	@Autowired
	private  VideoService videoService;
	
	/**
	 * 跳转到编辑页面。<br/>
	 * @param  请求实例
	 * @param  liveCd 直播编码
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping(value="/edit.htm")
	public String edit (HttpServletRequest request, String liveCd) throws Exception {
		videoService.loadLive(request, liveCd);
		return "live/live_edit";
	}
	
	/**
	 * 保存直播信息。<br/>
	 * @param  请求实例
	 * @param  liveCd 直播编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/save.htm")
	public String save (HttpServletRequest request) throws Exception {
		// 参数保存
		liveService.selectKeySave(request);
		// 携带页面查询条件
		LiveInfoVO record = liveService.getSaveInfo(request, "");
		// 更新数据库
		//record = liveService.getPullAddress(record);
		
		liveService.save(record);
		// 画面刷新
		videoService.loadLive(request, record.getLiveCd());		
		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));
		
		return "live/live_edit";
		
	}
	
	/**
	 * 发布直播信息。<br/>
	 * 直播编辑页面图片异步请求<br/>
	 * @param  file 文件请求
	 * @param  req 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/upload.htm")
	public Map<String, String> queryPic(MultipartRequest file, HttpServletRequest req) throws Exception {
		Map<String, String> resultMap = videoService.queryPic(file, req);
		return resultMap;
	}
	
	/**
	 * 跳转到编辑页面。<br/>
	 * @param  request 请求实例
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping(value="/publish.htm")
	public String publish (HttpServletRequest request) throws Exception {
		// 参数保存
		liveService.selectKeySave(request);
		// 发布直播
		LiveInfoVO record = liveService.publish(request);
		
		// 画面刷新
		videoService.loadLive(request, record.getLiveCd());		
		
		return "live/live_edit";
		
	}
	
//	/**
//	 * 跳转到编辑页面。<br/>
//	 * @param  请求实例
//	 * @return 返回页面名称
//	 * @throws Exception 
//	 */
//	@RequestMapping(value="/save.htm")
//	public String save(HttpServletRequest request) throws Exception {
//		
//		// 参数保存
//		liveService.selectKeySave(request);
//		// 携带页面查询条件
//		LiveInfoVO record = liveService.getSaveInfo(request, "");				
//		// 更新数据库
//		liveService.save(record);
//		// 画面刷新
//		videoService.loadLive(request, record.getLiveCd());		
//		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));
//		
//		return "live/live_edit";
//	}
	
}

