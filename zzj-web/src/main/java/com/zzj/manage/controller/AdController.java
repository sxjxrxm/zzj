/**
 * Project Name:zzj-web
 * File Name:AppUserController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.zzj.core.web.controller.BaseController;
import com.zzj.db.model.CommercialVO;
import com.zzj.manage.service.AdService;
import com.zzj.util.StringUtil;
import com.zzj.util.UploadUtils;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>AppUserController <br/>
 * <p><strong>功能说明: </strong></p>前台用户管理Controller. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月23日下午7:53:09 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("ad") 
public class AdController extends BaseController{
	/**
	 * User业务操作Service
	 */
	@Autowired
	private AdService adService;

	/**
	 * 
	 *模块访问方法：跳转到广告编辑界面<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 */
	@RequestMapping("/edit.htm")
	public String index(HttpServletRequest request, HttpServletResponse response,String isAdd,String keyBusi,String keyTopic,String[] keyTechField){
		// 实例化VO返回页面
		CommercialVO commercialVO = new CommercialVO();
		commercialVO.setIsAdd(Integer.parseInt(isAdd));
		request.setAttribute("commercialVO", commercialVO);
		
		//将之前的查询条件 传到界面
		request.setAttribute("keyBusi", keyBusi);
		request.setAttribute("keyTopic", keyTopic);
		request.setAttribute("keyTechField", keyTechField);
		return "ad/ad_edit";
	}
	/**
	 * 图片异步请求<br/>
	 * @param  file 文件请求
	 * @param  request 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 */
	@ResponseBody
	@RequestMapping(value = "/imgUpload.htm")
	public Map<String, String> queryPic(MultipartRequest file, HttpServletRequest req) {
		String id = req.getParameter("id");// 用于区分列表图片（littleIcon）、详情图片（bigIcon）
		if (StringUtil.isBlank(id)) {
			return null;
		}
		// 获得请求图片
		MultipartFile multipartFile = file.getFile(id + "ImgData");
		String realPath = "";
		Map<String, String> resultMap = new HashMap<String, String>();
		
		try {
			// 执行图片上传
			realPath = UploadUtils.uploadImg(multipartFile, ZzjConstants.UPLOAD_TYPE_SLIDE, ZzjConstants.BUSI_TYPE_07);
			// web服务器存储路径
			resultMap.put("path", realPath);
			// 取得图片URL链接
			resultMap.put("url", StringUtil.getImageURL(realPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回图片地址
		return resultMap;
	}
	/**
	 * 
	 *广告编辑方法<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/editAd.htm")
	public void editAd(HttpServletRequest request, HttpServletResponse response,String name,String brief,String userId,String isAdd,String commercialCd) 
			throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		// 设变量表示受影响条目数
		Integer x = -1;
		// 调用Service进行编辑
		x = adService.editAd(name,brief,userId,isAdd,"",commercialCd);
		// 将查询结果返回页面
		PrintWriter  out = response.getWriter() ;
		if(x == 1){
			out.print(true) ;
		}
		else{
			out.print(false) ;
		}
	}
	/**
	 * 
	 *广告编辑查询方法<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/editSearch.htm")
	public String editSearch(HttpServletRequest request, HttpServletResponse response,String id,String isAdd,String keyBusi,String keyTopic,String[] keyTechField) 
			throws Exception{
		response.setContentType("application/json;charset=UTF-8");
		// 定义VO表示查询结果
		CommercialVO commercialVO = new CommercialVO();
		// 调用Service 查询
		commercialVO = adService.editSearch(id,isAdd);
		// 将查询结果返回界面
		request.setAttribute("commercialVO", commercialVO);
		//将之前的查询条件 传到界面
		request.setAttribute("keyBusi", keyBusi);
		request.setAttribute("keyTopic", keyTopic);
		request.setAttribute("keyTechField", keyTechField);
		// 跳转界面
		return "ad/ad_edit";
	}
	/**
	 * 
	 *广告编辑查询方法<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws IOException 
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteAd.htm")
	public void deleteAd(HttpServletRequest request, HttpServletResponse response,String commercialCd) throws IOException{
		// 定义变量表示受影响条目数
		Integer x = adService.deleteAd(commercialCd);
		PrintWriter  out = response.getWriter() ;
		if(x == 1){
			out.print(true) ;
		}
		else{
			out.print(false) ;
		}
	}
}

