/**
 * Project Name:zzj-web
 * File Name:InformationController.java
 * Package Name:com.zzj.manage
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.zzj.core.exception.BusinessException;
import com.zzj.core.exception.ValidateErrorException;
import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.model.KeyVO;
import com.zzj.db.model.NewsInfoEditVO;
import com.zzj.db.model.NewsInfoListVO;
import com.zzj.db.model.PageResult;
import com.zzj.manage.service.InformationService;
import com.zzj.util.Base64;
import com.zzj.util.CSVUtils;
import com.zzj.util.DateUtil;
import com.zzj.util.PropertyUtil;
import com.zzj.util.SerializUtils;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>InformationController <br/>
 * <p><strong>功能说明: </strong></p>知识列表及编辑用Controller <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月29日下午17:52:31 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("information")
public class InformationController extends BaseController {
	 
	/**
	 * 知识业务操作Service
	 */
	@Autowired
	private InformationService informationService;
	
	/**
	 * 跳转到专家列表页面。<br/>
	 * @param  request 请求实例
	 * @param  startDate 开始时间
	 * @param  endDate 结束时间
	 * @param  model 返回模型
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping(value="/list.htm")
	public String list(HttpServletRequest request, Date startDate, Date endDate, Model model) throws Exception {
		// 携带列表页面查询条件
		Map<String, Object> showBackMap = this.parseShowBackMap1(request);
		if (showBackMap == null) {
			showBackMap = new HashMap<String, Object>();
		}
		model.addAttribute("selectResource", showBackMap);
		String flag = request.getParameter("flag");
		//从编辑页返回列表页
		if(StringUtil.isNotBlank(flag) && "0".equals(flag)){
			startDate = (Date)showBackMap.get("startDate");
			endDate = (Date)showBackMap.get("endDate");
		}
		String[] doSearchArr = request.getParameterValues("doSearch");
		
		MstUsersInfo existUser = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		if (ZzjConstants.EXPERT_ID_IN_USERS_INFO.equals(existUser.getRoleId())) {
			doSearchArr = new String[1];
			doSearchArr[0] = "1";
		}
		
		if (doSearchArr != null && doSearchArr.length > 0) {
			String doSearch = String.join(",", doSearchArr);
			model.addAttribute("doSearch", doSearch);
			if ("1".equals(doSearch)) {
				// 查询操作
				PageResult<NewsInfoListVO> resultList = informationService.getResultList(request,startDate,endDate);
				model.addAttribute("resultList", resultList);
				if (resultList == null || resultList.getItems() == null || resultList.getItems().size() == 0)
				{
					request.setAttribute(ZzjConstants.SYS_WARNING_KEY, PropertyUtil.getMessageContent("E1000015", null));
				}
			}
		} 
		return "information/information_list";
	}

	/**
	 * 删除知识及知识相关信息（推荐置顶信息、领域信息）并跳转到专家列表页面。<br/>
	 * @param  request 请求实例
	 * @param  startDate 开始时间
	 * @param  endDate 结束时间
	 * @param  model 返回模型
	 * @return 返回页面名称
	 * @throws ValidateErrorException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@RequestMapping(value="/del.htm")
	public String delete(HttpServletRequest request, Date startDate, Date endDate,  Model model) throws ValidateErrorException, ClassNotFoundException, IOException {
		// 携带列表页面查询条件
		Map<String, Object> showBackMap = this.parseShowBackMap1(request);
		if (showBackMap == null) {
			showBackMap = new HashMap<String, Object>();
		}
		model.addAttribute("selectResource", showBackMap);
		/**
		 *  删除知识操作
		 */
		// 知识newsCd
		String[] ids = new String[2];
		ids[0] = request.getParameter("newsCd");
		// 当前登录用户的id
		ids[1] = request.getParameter("userId");
		informationService.deleteNewsById(ids);
		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000002", null));
		return "forward:list.htm";
	}
	
	/**
	 * 跳转到知识编辑页面。<br/>
	 * @param  request 请求实例
	 * @param  isAddNews 区分编辑与添加操作，1：添加，0：编辑
	 * @param  startDate 开始时间
	 * @param  endDate 结束时间
	 * @param  newsCd 知识id
	 * @param  model 返回模型
	 * @throws Exception
	 */
	@RequestMapping(value="/edit.htm")
	public String edit(HttpServletRequest request, Integer isAddNews, Date startDate, Date endDate, String newsCd, Model model) throws Exception {
		// 携带列表页面查询条件
//		Map<String, Object> showBackMap = new HashMap<String, Object>();
		
		String linkFrom = request.getParameter("linkFrom");
		model.addAttribute("linkFrom", linkFrom);
		if ("expertEdit".equals(linkFrom)) {
			// 专家一览的回显数据
//			showBackMap = this.parseShowBackMap(request);
			request.setAttribute("selectResource", this.parseShowBackString(request, "edit"));
		} else {
			// 知识一览的回显数据
//			showBackMap = this.parseShowBackMap1(request);
//			if (showBackMap == null) {
//				showBackMap = new HashMap<String, Object>();
//			}
			model.addAttribute("selectResourceNews", this.parseShowBackString1(request));
		}
		
		model.addAttribute("isAddNews", isAddNews);
		String doSearch = request.getParameter("doSearch");
		model.addAttribute("doSearch", doSearch);
		// 非法请求
		if (Objects.isNull(isAddNews) || (isAddNews != 1 && isAddNews != 0)) {
			throw new BusinessException("非法请求");
		}
		// 编辑知识
		if (isAddNews == 0) {
			NewsInfoEditVO newsInfoEditVO = informationService.selectByPrimaryKey(request, newsCd);
			model.addAttribute("newsInfo", newsInfoEditVO);
		}
		// 添加知识
		if (isAddNews == 1) {
			NewsInfoEditVO newsInfoEditVO = informationService.initPageResource();
            model.addAttribute("newsInfo", newsInfoEditVO);
		}
		
		// 专家操作时设置专家ID和名称，后台从专家编辑跳转时设置专家ID和名称
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
        if(user.getRoleId().equals(ZzjConstants.EXPERT_ID_IN_USERS_INFO)){
			model.addAttribute("expertId",user.getUserId() );
			model.addAttribute("expertName", user.getUserName());
        } else {
			model.addAttribute("expertId", request.getParameter("expertId"));// 当从专家编辑页面跳转过来，会有专家id，否则为空
			model.addAttribute("expertName", request.getParameter("expertName"));// 当从专家编辑页面跳转过来，会有专家姓名，否则为空
        }

		return "information/information_edit";
	}
	
	/**
	 * 保存知识。<br/>
	 * @param  newsInfo 接收的数据
	 * @param  startDate 开始时间
	 * @param  endDate 结束时间
	 * @param  request 请求实例
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping(value="/save.htm")
	public String save(NewsInfoEditVO newsInfo, Date startDate, Date endDate, HttpServletRequest request) throws Exception {
		// 携带列表页面查询条件
//		Map<String, Object> showBackMap = new HashMap<String, Object>();
		
		String linkFrom = request.getParameter("linkFrom");
		request.setAttribute("linkFrom", linkFrom);
		
		String expertId = request.getParameter("expertId");
		request.setAttribute("expertId", expertId);
		
		String expertName = request.getParameter("expertName");
		request.setAttribute("expertName", expertName);
		
		if ("expertEdit".equals(linkFrom)) {
			// 专家一览的回显数据
//			showBackMap = this.parseShowBackMap(request);
			request.setAttribute("selectResource", this.parseShowBackString(request, ""));
		} else {
			// 知识一览的回显数据
//			showBackMap = this.parseShowBackMap1(request);
//			request.setAttribute("selectResource", showBackMap);
			request.setAttribute("selectResourceNews", request.getParameter("selectResourceNews"));
		}
		
		/**
		 * 排除非法请求
		 */
		Integer isAddNews = Integer.parseInt(request.getParameter("isAddNews"));
		request.setAttribute("isAddNews", isAddNews);
		String doSearch = request.getParameter("doSearch");
		request.setAttribute("doSearch", doSearch);
		// 非法请求
		if (isAddNews != 1 && isAddNews != 0) {
			throw new BusinessException("非法请求");
		}
		// 保存页面数据
		request.setAttribute("newsInfo", newsInfo);
		boolean isSuccess = informationService.saveNews(newsInfo,request,isAddNews);
		if (isSuccess) {
			request.setAttribute("isAddNews", 0); // 当保存成功后，需要将添加标记给为0（即：编辑知识），防止插入重复数据
			if (!"expertEdit".equals(linkFrom) && isAddNews == 1) {// 从知识一览跳转，且是新增知识，一览页面的回显条件需要重构
//				showBackMap = this.rebuildShowBackMap(showBackMap,newsInfo.getNewsName());
				String selectResource = request.getParameter("selectResourceNews");
				byte[] b = Base64.decode(selectResource);
				KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
				String newsNameLike = keyVO.getNewsName();
				String[] paymentKbnLike = keyVO.getPaymentKbn();
				String[] techField = keyVO.getField();
				String newsTypeLike = keyVO.getNewsType();
				String expertNameLike = keyVO.getExpertName();
				String[] recommendKbnLike = keyVO.getRecommendKbn();
				if(StringUtil.isBlank(newsNameLike)
					&& (null == paymentKbnLike || paymentKbnLike.length == ZzjConstants.NUM_I_0)
					&& (null == techField || techField.length == ZzjConstants.NUM_I_0)
					&& StringUtil.isBlank(newsTypeLike) && StringUtil.isBlank(expertNameLike)
					&& (null == recommendKbnLike || recommendKbnLike.length == ZzjConstants.NUM_I_0)){
					keyVO.setNewsName(newsInfo.getNewsName());
					//重新序列化
					byte[] data = SerializUtils.serializ(keyVO);  
					String selectResource2 = Base64.encode(data);
					request.setAttribute("selectResourceNews", selectResource2);
				}
				request.setAttribute("doSearch", "1");
			}
			NewsInfoEditVO newsInfoEditVO = informationService.selectByPrimaryKey(request, newsInfo.getNewsCd());
			request.setAttribute("newsInfo", newsInfoEditVO);
			
			if ("save".equals(request.getParameter("flag"))) {
				request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));
			}
			if ("submit".equals(request.getParameter("flag"))) {
				request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000005", null));
			}
			
		}
		return "information/information_edit"; 
	}
	
	/**
	 * 知识编辑页面富文本编辑器中的图片请求<br/>
	 * @param  file 文件请求
	 * @return String 返回图片保存的url
	 * @throws Exception
	 */
	@RequestMapping(value = "/textEditorImg.htm")
	@ResponseBody
	public Map<String, String> textEditorImg(MultipartRequest file, HttpServletResponse response) throws Exception{		
		return informationService.textEditorImg(file);
	}
	
	/**
	 * 按条件查询知识一览CSV导出<br/>
	 * @param  request 请求实例
	 * @param  startDate 开始时间
	 * @param  endDate 结束时间
	 * @param  response 响应实例
	 * @return 无
	 * @throws Exception 
	 */
	@RequestMapping(value="/CSV.htm")
	public void outputCSV(HttpServletRequest request, Date startDate, Date endDate, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<MstCodeInfo> mstCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_MSTCODEINFOS);
		// 数据检索
		List<NewsInfoListVO> resultList = informationService.searchAll(request, startDate, endDate);		
		// 数据做成
		List<ArrayList<String>> list = informationService.getOutputContent(resultList, mstCodeInfos);		
        // 文件生成 
        String title = ZzjConstants.FILE_OUTPUT_NAME_NEWS + "_" + DateUtil.getDateTimeStr(new Date()) + ZzjConstants.FILE_OUTPUT_CSV;
		CSVUtils.createCSVFile(list, PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH), title);
		// 文件导出
		CSVUtils.exportToExcel(PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH) + File.separator + title, response, false);
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
	public Map<String, String> queryPic(MultipartRequest file, HttpServletRequest req) throws Exception {
		Map<String, String> resultMap = informationService.queryPic(file, req);
		return resultMap;
	}
	
	/**
	 * 知识编辑页面pdf异步请求<br/>
	 * @param  file 文件请求
	 * @param  req 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadPdf.htm")
	public Map<String, String> queryPdf(MultipartRequest file, HttpServletRequest req) throws Exception {
		Map<String, String> resultMap = informationService.queryPdf(file, req);
		return resultMap;
	}
	
	/**
	 * 知识编辑页面文件异步删除<br/>
	 * @param  name 文件名
	 * @param  path 文件路径
	 * @return boolean 返回删除是否成功
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/delFile.htm")
	public boolean delPic(String name, String path) throws Exception {
		boolean delFlag = informationService.delFile(name, path);
		return delFlag;
	}
	
	/**
	 * 封装知识一览页面查询条件，用于回显数据<br/>
	 * @param request 请求实例
	 * @return Map<String,Object> 用于回显数据
	 * @throws ValidateErrorException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private Map<String, Object> parseShowBackMap1(HttpServletRequest request) throws ValidateErrorException, ClassNotFoundException, IOException {
		String flag = request.getParameter("flag");
		/**
		 * 获取查询条件的值
		 */
		// 知识主题
		String newsName = null;
		// 费用分类  0：免费，1：付费
		String[] paymentKbn = null;
		// 领域（技术领域）
		String[] techField = null;
		// 知识分类  1：政策；2：案例；3：知识；4：文章
		String newsType = null;
		// 来源分类  1：为平台；2：为专家
		String[] sourceType = null;
		// 专家姓名
		String expertName = null;
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbn = null;
		// 当前页
		String pageNoStr = null;
		// 提出日期
		String startDate = null;
		// 提出日期
		String endDate = null;
		//从编辑返回列表
		if(StringUtil.isNotBlank(flag) && "0".equals(flag)){
			String selectResource = request.getParameter("selectResourceNews");
			byte[] b = Base64.decode(selectResource);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			if(null != keyVO){
				newsName = keyVO.getNewsName();
				paymentKbn = keyVO.getPaymentKbn();
				techField = keyVO.getField();
				newsType = keyVO.getNewsType();
				sourceType = keyVO.getSourceType();
				expertName = keyVO.getExpertName();
				recommendKbn = keyVO.getRecommendKbn();
				pageNoStr = keyVO.getPageNo();
				startDate = keyVO.getsDate();
				endDate = keyVO.geteDate();
			}
		}else{
			newsName = request.getParameter("newsNameLike");
			paymentKbn = request.getParameterValues("paymentKbnLike");
			techField = request.getParameterValues("techField");
			newsType = request.getParameter("newsTypeLike");
			sourceType = request.getParameterValues("sourceTypeLike");
			expertName = request.getParameter("expertNameLike");
			recommendKbn = request.getParameterValues("recommendKbnLike");
			pageNoStr = request.getParameter("pageNo");
			startDate = request.getParameter("startDate");
			endDate = request.getParameter("endDate");
		}
		// 页大小
		Integer pageSize = ZzjConstants.DEFAULT_PAGESIZE;
		/**
		 * 构造map
		 */
		Map<String, Object> showBackMap = new HashMap<String, Object>();
		
		if (StringUtil.isNotBlank(newsName)) {
			showBackMap.put("newsNameLike", newsName);
		}
		if (StringUtil.isNotBlank(paymentKbn)) {
			showBackMap.put("paymentKbnLike", String.join(",", paymentKbn));
		}
		if (StringUtil.isNotBlank(techField)) {
			showBackMap.put("techField", String.join(",", techField));
		}
		if (StringUtil.isNotBlank(newsType)) {
			showBackMap.put("newsTypeLike", String.join(",", newsType));
		}
		if (StringUtil.isNotBlank(sourceType)) {
			showBackMap.put("sourceTypeLike", String.join(",", sourceType));
		}
		if (StringUtil.isNotBlank(expertName)) {
			showBackMap.put("expertName", expertName);
		}
		if (StringUtil.isNotBlank(recommendKbn)) {
			showBackMap.put("recommendKbnLike", String.join(",", recommendKbn));
		}
		
		// 错误信息
		ValidateErrorException exception = null;
		
		if ("1".equals(request.getParameter("doSearch"))) {
			SimpleDateFormat sdf = new SimpleDateFormat(ZzjConstants.datePattern);
			if (StringUtil.isNotBlank(startDate)) {
				try {
					showBackMap.put("startDate", sdf.parse(startDate));
				} catch (ParseException e) {				
					if (exception == null)
					{
						exception = new ValidateErrorException("E1000004", new Object[] {"创建日期(开始时间)", ZzjConstants.datePattern}, "information/information_list", "startDate");
					}				
				}
			}
			if (StringUtil.isNotBlank(endDate)) {
				try {
					showBackMap.put("endDate", sdf.parse(endDate));
				} catch (ParseException e) {				
					if (exception == null)
					{
						exception = new ValidateErrorException("E1000004", new Object[] {"创建日期(结束时间)", ZzjConstants.datePattern}, "information/information_list", "endDate");
					}
					else 
					{
						exception.addError("E1000004", new Object[] {"创建日期(结束时间)", ZzjConstants.datePattern}, "endDate");
					}				
				}
			}
		}
		else {
			if (StringUtil.isNotBlank(startDate)) {
				showBackMap.put("startDate", startDate);
			}
			if (StringUtil.isNotBlank(endDate)) {
				showBackMap.put("endDate", endDate);
			}
		}
		// 查询起始记录数
		if (StringUtil.isNotBlank(pageNoStr)) {
			Integer pageNo = Integer.parseInt(pageNoStr);
			if (pageNo > 0) {
				showBackMap.put("pageNo", pageNo);
			}
		} else {
			showBackMap.put("pageNo", 1);
		}
		showBackMap.put("pageSize", pageSize);
		
		// 有异常发生
		if (exception != null) {
			request.setAttribute("selectResource", showBackMap);
			throw exception;
		}
		return showBackMap;
	}
	
	private String parseShowBackString1(HttpServletRequest request) throws ValidateErrorException, IOException, ClassNotFoundException {
		String flg = request.getParameter("flg");
		/**
		 * 获取查询条件的值
		 */
		// 知识主题
		String newsName = null;
		// 费用分类  0：免费，1：付费
		String[] paymentKbn = null;
		// 领域（技术领域）
		String[] techField = null;
		// 知识分类  1：政策；2：案例；3：知识；4：文章
		String newsType = null;
		// 来源分类  1：为平台；2：为专家
		String[] sourceType = null;
		// 专家姓名
		String expertName = null;
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbn = null;
		// 当前页
		String pageNoStr = null;
		// 提出日期
		String startDate = null;
		// 提出日期
		String endDate = null;
		
		//保存页面
		if(StringUtil.isNotBlank(flg) && "1".equals(flg)){
			String selectResource = request.getParameter("selectResourceNews");
			byte[] b = Base64.decode(selectResource);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			if(null != keyVO){
				newsName = keyVO.getNewsName();
				paymentKbn = keyVO.getPaymentKbn();
				techField = keyVO.getField();
				newsType = keyVO.getNewsType();
				sourceType = keyVO.getSourceType();
				expertName = keyVO.getExpertName();
				recommendKbn = keyVO.getRecommendKbn();
				pageNoStr = keyVO.getPageNo();
				startDate = keyVO.getsDate();
				endDate = keyVO.geteDate();
			}
		}else{
			newsName = request.getParameter("newsNameLike");
			paymentKbn = request.getParameterValues("paymentKbnLike");
			techField = request.getParameterValues("techField");
			newsType = request.getParameter("newsTypeLike");
			sourceType = request.getParameterValues("sourceTypeLike");
			expertName = request.getParameter("expertNameLike");
			recommendKbn = request.getParameterValues("recommendKbnLike");
			pageNoStr = request.getParameter("pageNo");
			startDate = request.getParameter("startDate");
			endDate = request.getParameter("endDate");
		}
		/**
		 * 构造mode
		 */
		KeyVO vo = new KeyVO();
		
		if (StringUtil.isNotBlank(newsName)) {
			vo.setNewsName(newsName);
		}
		if (StringUtil.isNotBlank(paymentKbn)) {
			vo.setPaymentKbn(paymentKbn);
		}
		if (StringUtil.isNotBlank(techField)) {
			vo.setField(techField);
		}
		if (StringUtil.isNotBlank(newsType)) {
			vo.setNewsType(newsType);
		}
		if (StringUtil.isNotBlank(sourceType)) {
			vo.setSourceType(sourceType);
		}
		if (StringUtil.isNotBlank(expertName)) {
			vo.setExpertName(expertName);
		}
		if (StringUtil.isNotBlank(recommendKbn)) {
			vo.setRecommendKbn(recommendKbn);
		}
		if (StringUtil.isNotBlank(startDate)) {
			vo.setsDate(startDate);
		}
		if (StringUtil.isNotBlank(endDate)) {
			vo.seteDate(endDate);
		}
		// 查询起始记录数
		if (StringUtil.isNotBlank(pageNoStr)) {
			Integer pageNo = Integer.parseInt(pageNoStr);
			if (pageNo > 0) {
				vo.setPageNo(pageNoStr);
			}
		} else {
			vo.setPageNo("1");
		}
		byte[] b = SerializUtils.serializ(vo);  
		String selectResource = Base64.encode(b);
		return selectResource;
	}
	
	/**
	 * 封装专家一览页面查询条件，用于回显数据<br/>
	 * @param request 请求实例
	 * @return Map<String,Object> 用于回显数据
	 */
	@SuppressWarnings("unused")
	private Map<String, Object> parseShowBackMap(HttpServletRequest request){
		/**
		 * 获取查询条件的值
		 */
		// 审核状态（0 待审核， 1 已审核）
		String[] auditStatus = request.getParameterValues("auditStatus");
		// 领域（技术领域）
		String[] techField = request.getParameterValues("techField");
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbn = request.getParameterValues("recommendKbn");
		/**
		 * 构造map
		 */
		Map<String, Object> showBackMap = new HashMap<String, Object>();
		
		if (StringUtil.isNotBlank(auditStatus)) {
			showBackMap.put("auditStatus", String.join(",", auditStatus));
		}
		if (StringUtil.isNotBlank(techField)) {
			showBackMap.put("techField", String.join(",", techField));
		}
		if (StringUtil.isNotBlank(recommendKbn)) {
			showBackMap.put("recommendKbn", String.join(",", recommendKbn));
		}
		
		showBackMap.put("expertName", request.getParameter("expertNameLike"));
		showBackMap.put("pageNo", request.getParameter("pageNo"));
		showBackMap.put("expertPageNo", request.getParameter("expertPageNo"));
		
		return showBackMap;
	}
	
	private String parseShowBackString(HttpServletRequest request, String edit) throws ClassNotFoundException, IOException{
		String selectResource = request.getParameter("selectResource");
		if(StringUtil.isNotBlank(selectResource)){
			byte[] b = Base64.decode(selectResource);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			//从编辑进入
			if(StringUtil.isNotBlank(edit) && "edit".equals(edit)){
				String pageNo = request.getParameter("pageNo");
				String expertPageNo = request.getParameter("expertPageNo");
				keyVO.setPageNo(pageNo);
				keyVO.setExpertPageNo(expertPageNo);
			}
			//序列化
			byte[] data = SerializUtils.serializ(keyVO);  
			String selectResource2 = Base64.encode(data);
			return selectResource2;
		}
		return "";
	}
	
	
	/**
	 * 封装知识一览页面查询条件，用于回显数据<br/>
	 * 如果是新增知识，一览页面的回显条件需要重构<br/>
	 * @param showBackMap 一览页面的回显数据
	 * @param newsName 新增知识的姓名
	 * @return Map<String,Object> 用于回显数据
	 */
	@SuppressWarnings("unused")
	private Map<String, Object> rebuildShowBackMap(Map<String, Object> showBackMap, String newsName) {
		if (StringUtil.isBlank((String)showBackMap.get("newsNameLike"))
				&& StringUtil.isBlank((String)showBackMap.get("paymentKbnLike"))
				&& StringUtil.isBlank((String)showBackMap.get("techField"))
				&& StringUtil.isBlank((String)showBackMap.get("newsTypeLike"))
				&& StringUtil.isBlank((String)showBackMap.get("expertNameLike"))
				&& StringUtil.isBlank((String)showBackMap.get("recommendKbnLike"))) {
			showBackMap.put("newsNameLike", newsName);
		}
		return showBackMap;
	}
}

