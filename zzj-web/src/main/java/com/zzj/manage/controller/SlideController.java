/**
 * Project Name:zzj-web
 * File Name:SlideController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;
import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.SlideShowInfo;
import com.zzj.db.model.PageResult;
import com.zzj.db.model.SlideResultVO;
import com.zzj.db.model.SlideVO;
import com.zzj.manage.service.SlideService;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.UploadUtils;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>SlideController <br/>
 * <p><strong>功能说明: </strong></p>轮播管理模块Controller<br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月28日下午3:29:18 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("slide") 
public class SlideController extends BaseController{
	/**
	 * SlideShowInfo业务操作Service
	 */
	@Autowired
	private SlideService slideService;
	private String updateId = "";
	/**
	 * 模块访问方法：跳转到轮播一览界面<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 */
	@RequestMapping("/list.htm")
	public String index(HttpServletRequest request) {
		// 得到限制轮播条数
		Integer slideNum = ZzjConstants.SLIDE_NUM;		
		// 查询全部机能
		List<MstCodeInfo> codeInfos = new ArrayList<>();
		// 调用Service 查询
		codeInfos = slideService.searchBusis();
		// 定义结果集
		List<SlideVO> slideVOs= new ArrayList<>();
		// 调用Service查询当前轮播表中全部记录
		slideVOs = slideService.selectAllSlides();
		// 将查询结果返回页面
		request.setAttribute("slideNum", slideNum);
		request.setAttribute("codeInfos", codeInfos);
		request.setAttribute("slideVOs", slideVOs);
		return "slide/slide_list";
	}
	/**
	 * 保存排序方法<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  sortNo 显示顺
	 * @param  slideCd 轮播编码
	 * @throws IOException 
	 */
	@RequestMapping("/saveSortNo")
	public void saveSortNo(HttpServletRequest request, HttpServletResponse response,String sortNo, String slideCd) throws IOException{
		// 定义变量表示受影响条目数
		Integer xInteger = -1;
		// 调用Service进行更新
		xInteger = slideService.saveSortNo(sortNo,slideCd,this.updateId(request));
		// 将结果返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(xInteger) ;
		
	}
	/**
	 * 删除轮播方法<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  slideCd 轮播编码
	 * @throws IOException 
	 */
	@RequestMapping("/deleteSlides")
	public void deleteSlides(HttpServletRequest request, HttpServletResponse response, String slideCd) throws IOException{
		// 定义变量表示受影响条目数
		Integer xInteger = -1;
		// 调用Service进行更新
		xInteger = slideService.deleteSlides(slideCd,this.updateId(request));
		// 将结果返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(xInteger) ;
		
	}
	/**
	 * 模块跳转方法：跳转到轮播编辑界面<br/>
	 * @param  request http请求实例
	 * @param  slideCd 轮播编码
	 * @param  isZzj 平台区分
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping("/edit.htm")
	public String edit(HttpServletRequest request, String slideCd, String isZzj) throws Exception {
		SlideShowInfo slideShowInfo = new SlideShowInfo();
		if (StringUtil.isBlank(isZzj)) {
			if (StringUtil.isNotBlank(slideCd)) {
				// 调用service查询
				slideShowInfo = slideService.sreachSlide(slideCd);
			}
			// 将查询结果传到页面
			request.setAttribute("slideShowInfo", slideShowInfo);
			if (StringUtil.isNotBlank(slideShowInfo.getBusiType()))
			{
				request.setAttribute("isZzj", "1");
			}
			else
			{
				request.setAttribute("isZzj", "0");
			}
		}
		String[] busiType = {"null"};
		if(slideShowInfo.getTopicCd() != null && slideShowInfo.getBusiType() != null)
		{
			// 根据查询结果查询得到topicName
			String selectTopicName = "";
			String topicCd = slideShowInfo.getTopicCd();
			PageResult<SlideResultVO> resultList=new PageResult<>();
			Integer pageNumber = 1;
			do {
				resultList = slideService.selectBusis("", busiType, slideShowInfo.getBusiType(), ""+pageNumber+"");
				for (int i = 0; i < resultList.getItems().size(); i++) {
					if (topicCd.equals(resultList.getItems().get(i).getSlideResultId())) {
						selectTopicName = resultList.getItems().get(i).getSlideResultTopic();
						break;
					}
				}
				pageNumber++;
			} while (pageNumber <= resultList.getTotalPageCount());
			request.setAttribute("selectTopicName", selectTopicName);
		}
		slideService.setParamter(request);
		
		return "slide/slide_edit";
	}
	
	/**
	 * 图片异步请求。<br/>
	 * @param  multiPartRquest 文件请求
	 * @param  req 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 */
	@ResponseBody
	@RequestMapping(value = "/imgUpload.htm")
	public Map<String, String> queryPic (MultipartRequest multiPartRquest, HttpServletRequest req) {
		MultipartFile multipartFile = multiPartRquest.getFile("imgData");
		String realPath = "";
		Map<String, String> resultMap = new HashMap<String, String>();
		// 图片大小验证
		Boolean ret = UploadUtils.checkImgSize(multipartFile, ZzjConstants.IMG_SIZE);
		if (!ret)
		{
			String message = PropertyUtil.getMessageContent("E1000019", new Object[] {ZzjConstants.IMG_SIZE});
			resultMap.put("message", message);
			return resultMap;		
		}

		try {
			// web服务器存储路径
			realPath = UploadUtils.uploadImg(multipartFile, ZzjConstants.UPLOAD_TYPE_SLIDE, ZzjConstants.BUSI_TYPE_07);
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
	 * 轮播编辑方法<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  isAdd 1 添加；0编辑
	 * @param  slideCd 轮播编码
	 * @throws Exception 
	 */
	@RequestMapping("/editSlide")
	public void editSlide(HttpServletRequest request, HttpServletResponse response, String isAdd, String slideCd) throws Exception {
		// 定义变量表示受影响条目数
		Integer xInteger = -1;
		SlideShowInfo info = slideService.setSlideInfo(request, this.updateId(request));
		// 调用Service进行更新
		xInteger = slideService.editSlide(isAdd, slideCd, info);
		// 将结果返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(xInteger) ;
	}
	/**
	 * 轮播编辑查询：跳转轮播编辑选择zzj时的查询页面<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping("/slideSearch.htm")
	public String slideSearch(HttpServletRequest request) throws Exception {
		// 查询全部机能
		List<MstCodeInfo> codeInfos = new ArrayList<>();
		// 调用Service 查询
		codeInfos = slideService.searchBusis();
		request.setAttribute("codeInfos", codeInfos);
		slideService.setParamter(request);
		return "slide/slide_search";
	}
	
	/**
	 * 轮播编辑查询：根据技能到相应表根据条件查询记录<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  topic 主题
	 * @param  techFields 领域
	 * @param  busi 机能
	 * @param  pageNumber 页码
	 * @throws IOException 
	 */
	@RequestMapping("/selectBusis.htm")
	public void selectBusis(HttpServletRequest request, HttpServletResponse response,String topic,String[] techFields,String busi,String pageNumber) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		// 定义查询结果集
		PageResult<SlideResultVO> resultList = new PageResult<>();
		// 调用Service查询结果
		resultList = slideService.selectBusis(topic,techFields,busi,pageNumber);
		// 转换成json
		Gson gson = new Gson() ;
		String result = gson.toJson(resultList) ;
		// 将查询结果返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(result) ;
	}
	
	/**
	 * 获取用户ID<br/>
	 * @param  request http请求实例 
	 * @return updateId
	 */
	private String updateId(HttpServletRequest request) {
		if (StringUtil.isBlank(updateId))
		{
			// 获得更新人的id，并创建更新时间
			MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
			updateId = user.getUserId();
		}
		return updateId;
	}
	
}

