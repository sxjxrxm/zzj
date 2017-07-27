/**
 * Project Name:zzj-web
 * File Name:ExpertController.java
 * Package Name:com.zzj.manage
 * Copyright ? 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.zzj.core.exception.BusinessException;
import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstAreaInfo;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.NewsInfo;
import com.zzj.db.model.ExpertInfoEditVO;
import com.zzj.db.model.ExpertInfoListVO;
import com.zzj.db.model.KeyVO;
import com.zzj.db.model.PageResult;
import com.zzj.manage.service.ExpertService;
import com.zzj.util.Base64;
import com.zzj.util.CSVUtils;
import com.zzj.util.DateUtil;
import com.zzj.util.PropertyUtil;
import com.zzj.util.SerializUtils;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>ExpertController <br/>
 * <p><strong>功能说明: </strong></p>专家列表及编辑用Controller <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月17日上午10:52:31 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("expert")
public class ExpertController extends BaseController {
	
	/**
	 * 专家业务操作Service
	 */
	@Autowired
	private ExpertService expertService;
	
	/**
	 * 专家角色:2
	 */
	private final String EXPERT_ROLEID = "2";
	
	/**
	 * 跳转到专家列表页面。<br/>
	 * @param  request 请求实例
	 * @param  model 返回的模型
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/list.htm")
	public String list(HttpServletRequest request, Model model)  throws Exception {
		// 得到当前登录用户信息
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		if (user == null || StringUtil.isBlank(user.getUserId())) {
			throw new BusinessException("用户未登录");
		}
		if (user.getRoleId().equals(this.EXPERT_ROLEID)) {// 当前登录用户为专家
			request.setAttribute("expertId", user.getUserId());
			PageResult<ExpertInfoListVO> resultList = expertService.getResultList(request);
			model.addAttribute("resultList", resultList);
			return "expert/expert_list";
		}
		// 携带列表页面查询条件
		Map<String, Object> showBackMap = this.parseShowBackMap(request,request.getParameter("edit"));
		if (showBackMap == null) {
			showBackMap = new HashMap<String, Object>();
		}
		model.addAttribute("selectResource", showBackMap);
		String doSearch = request.getParameter("doSearch");
		model.addAttribute("doSearch", doSearch);
		if ("1".equals(doSearch)) {
			// 查询操作
			PageResult<ExpertInfoListVO> resultList = expertService.getResultList(request);
			model.addAttribute("resultList", resultList);
			if (resultList == null || resultList.getItems() == null || resultList.getItems().size() == 0)
			{
				request.setAttribute(ZzjConstants.SYS_WARNING_KEY, PropertyUtil.getMessageContent("E1000015", null));
			}
		}
		return "expert/expert_list";
	}
	
	/**
	 * 跳转到专家编辑页面。<br/>
	 * @param  request 请求实例
	 * @param  expertId 专家id
	 * @param  isAdd 区分编辑与添加操作，1：添加，0：编辑
	 * @param  model 返回的模型
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/edit.htm")
	public String edit(HttpServletRequest request, String expertId, Integer isAdd, Model model) throws Exception {
//		// 携带列表页面查询条件
//		Map<String, Object> showBackMap = this.parseShowBackMap(request,request.getParameter("edit"));
//		if (showBackMap == null) {
//			showBackMap = new HashMap<String, Object>();
//		}
		
		model.addAttribute("selectResource", this.parseShowBackString(request));
		
		model.addAttribute("isAdd", isAdd);
		String doSearch = request.getParameter("doSearch");
		model.addAttribute("doSearch", doSearch);
		// 非法请求
		if (isAdd != 1 && isAdd != 0) {
			throw new BusinessException("非法请求");
		}
		// 编辑专家
		if (isAdd == 0) {
			// 得到当前登录用户信息，当登录用户为专家时，专家信息禁止编辑，同时直接显示专家文章
			MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
			if (user == null || StringUtil.isBlank(user.getUserId())) {
				throw new BusinessException("用户未登录");
			}
			
			ExpertInfoEditVO expertInfoEditVO = expertService.selectByPrimaryKey(request, expertId);
			// 设置头像地址
			expertInfoEditVO.setAvatorAddressUrl(StringUtil.getImageURL(expertInfoEditVO.getAvatorAddress()));
			// 设置头像背景图地址
			expertInfoEditVO.setBackgroundAdressUrl(StringUtil.getImageURL(expertInfoEditVO.getBackgroundAdress()));
			// 传递到页面
			model.addAttribute("expertInfo", expertInfoEditVO);
			
			// 当专家信息已审核通过，进行翻页操作时，需要显示专家文章
			if (StringUtil.isNotBlank(request.getParameter("showNews")) 
					|| expertInfoEditVO.getStatus() == ZzjConstants.STATUS_1       // 当专家信息已审核通过
					|| user.getRoleId().equals(this.EXPERT_ROLEID) 	               // 当前登录用户为专家
					|| StringUtil.isNotBlank(request.getParameter("newsEdit")) ) { // 当从知识编辑页面返回时，需要直接显示专家文章
				PageResult<NewsInfo> resultList = expertService.findNewsByExpertIdAndPage(request, expertId);
				model.addAttribute("resultList", resultList);
				model.addAttribute("showNews", "showNews");
			}
		}
		// 添加专家
		if (isAdd == 1) {
			ExpertInfoEditVO expertInfoEditVO = expertService.initPageResource();
			model.addAttribute("expertInfo", expertInfoEditVO);
		}
		return "expert/expert_edit";
	}
	
	/**
	 * 保存专家。<br/>
	 * @param  expertInfo 接受的数据
	 * @param  request 请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/save.htm")
	public String save(ExpertInfoEditVO expertInfo, HttpServletRequest request) throws Exception {
		// 携带列表页面查询条件
		Map<String, Object> showBackMap = new HashMap<String, Object>();
		
		//0:从编辑页返回列表页，1:获取序列化重新保存序列化，2:获取表单序列化保存
		String flag = request.getParameter("flag");
		if(StringUtil.isNotBlank(flag) && "1".equals(flag)){
			if ("list".equals(request.getParameter("list"))) {
				request.setAttribute("expertPageNo", request.getParameter("pageNo"));
			} else {
				request.setAttribute("expertPageNo", request.getParameter("expertPageNo"));
			}
			request.setAttribute("selectResource", request.getParameter("selectResource"));
		}else{
			showBackMap = this.parseShowBackMap(request,"edit");
			request.setAttribute("selectResource", showBackMap);
		}
		
		String doSearch = request.getParameter("doSearch");
		request.setAttribute("doSearch", doSearch);
		/**
		 * 排除非法请求
		 */
		String isAddStr = request.getParameter("isAdd");
		Integer isAdd = Integer.parseInt(isAddStr);
		request.setAttribute("isAdd", isAdd);
		// 非法请求
		if (isAdd != 1 && isAdd != 0) {
			throw new BusinessException("非法请求");
		}
		// 保存页面数据
		boolean isSuccess = expertService.saveExpert(expertInfo,request,isAdd);
		request.setAttribute("expertInfo", expertInfo);
		
		// 画面刷新
		if (isSuccess) {
			// 如果是新增专家，一览页面的回显条件需要重构
			if (isAdd == 1) {
				if(StringUtil.isNotBlank(flag) && "1".equals(flag)){
					String selectResource = request.getParameter("selectResource");
					byte[] b = Base64.decode(selectResource);
					KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
					String expertName = keyVO.getExpertName();
					String[] auditStatus = keyVO.getAuditStatus();
					String[] field = keyVO.getField();
					String[] recommendKbn = keyVO.getRecommendKbn();
					if(StringUtil.isBlank(expertName) 
						&& (null == auditStatus || auditStatus.length == ZzjConstants.NUM_I_0)
						&& (null == field || field.length == ZzjConstants.NUM_I_0)
						&& (null == recommendKbn || recommendKbn.length == ZzjConstants.NUM_I_0)){
						keyVO.setExpertName(expertInfo.getExpertName());
						//重新序列化
						byte[] data = SerializUtils.serializ(keyVO);  
						String selectResource2 = Base64.encode(data);
						request.setAttribute("selectResource", selectResource2);
					}
				}else{
					showBackMap = this.rebuildShowBackMap(showBackMap,expertInfo.getExpertName());
					request.setAttribute("selectResource", showBackMap);
				}
				request.setAttribute("doSearch", "1");
			}
			request.setAttribute("isAdd", 0); // 当保存成功后，需要将添加标记给为0（即：编辑专家），防止插入重复数据
			ExpertInfoEditVO expertInfoEditVO = expertService.selectByPrimaryKey(request, expertInfo.getExpertId());
			PageResult<NewsInfo> resultList = expertService.findNewsByExpertIdAndPage(request, expertInfo.getExpertId());
			request.setAttribute("resultList", resultList);
			request.setAttribute("expertInfo", expertInfoEditVO);
			request.setAttribute("showNews", "showNews");
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));
		}
		else
		{
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000008", null));
		}
		return "expert/expert_edit";
	}
	
	/**
	 * 删除专家及专家相关信息（专家文章、推荐置顶信息、领域信息）并跳转到专家列表页面。<br/>
	 * @param  request 请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/del.htm")
	public String delete(HttpServletRequest request) throws Exception {
		// 携带列表页面查询条件
		Map<String, Object> showBackMap = this.parseShowBackMap(request,"");
		if (showBackMap == null) {
			showBackMap = new HashMap<String, Object>();
		}
		request.setAttribute("selectResource", showBackMap);
		/**
		 *  删除专家操作
		 */
		// 专家id
		String[] ids = new String[2];
		ids[0] = request.getParameter("expertId");
		// 当前登录用户的id
		ids[1] = request.getParameter("userId");
		expertService.deleteExpertById(ids);
		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000002", null));
		return "forward:list.htm";
	}
	
	/**
	 * 
	 * 专家编辑页面城市联动菜单异步请求<br/>
	 * @param  request 请求实例
	 * @return List<MstAreaInfo> 返回根据省级code获得的对应的市级信息列表
	 */
	@RequestMapping(value="/getCityC")
	public @ResponseBody List<MstAreaInfo> getCityC(HttpServletRequest request) {
		List<MstAreaInfo> cityCList = expertService.getCityC(request);
		return cityCList;
	}
	
	/**
	 * 专家编辑页面专家职称异步请求<br/>
	 * @return String 返回专家职称信息，以4个空格隔开的字符串
	 */
	@RequestMapping(value="/getRankName.htm")
	public @ResponseBody String getRankName() {
		String rankName = expertService.getRankName();
		return rankName;
	}
	
	/**
	 * 知识编辑页面专家姓名异步请求<br/>
	 * @return String 返回专家姓名信息，以4个空格隔开的字符串
	 */
	@RequestMapping(value="/getExpertName.htm")
	public @ResponseBody String getExpertName() {
		String expertName = expertService.getExpertName();
		return expertName;
	}
	
	/**
	 * 专家编辑页面专家头像异步请求<br/>
	 * @param  file 文件对象
	 * @param  request 请求实例
	 * @return String 返回专家头像地址
	 */
	@ResponseBody
	@RequestMapping(value = "/upload.htm")
	public Map<String, String> queryPic(MultipartRequest file, HttpServletRequest request){
		Map<String, String> resultMap = expertService.queryPic(file, request, "imgData");
		return resultMap;
	}
	
	/**
	 * 专家编辑页面专家头像异步请求<br/>
	 * @param  file 文件对象
	 * @param  request 请求实例
	 * @return String 返回专家头像地址
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadBack.htm")
	public Map<String, String> uploadBackImg(MultipartRequest file, HttpServletRequest request){
		Map<String, String> resultMap = expertService.queryPic(file, request, "backimgData");
		return resultMap;
	}
	
	/**
	 * 专家编辑页面专家头像异步删除<br/>
	 * @param  path 文件路径
	 * @return boolean 返回删除是否成功
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/delPic.htm")
	public boolean delPic(String path) throws Exception{
		boolean delFlag = expertService.delPic(path);
		return delFlag;
	}
	
	/**
	 * 按条件查询专家一览CSV导出<br/>
	 * @param  request http请求实例
	 * @param  response 响应
	 * @throws Exception 
	 */
	@RequestMapping(value="/CSV.htm")
	public void outputCSV(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 得到当前登录用户信息
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		if (user == null || StringUtil.isBlank(user.getUserId())) {
			throw new BusinessException("用户未登录");
		}
		if (user.getRoleId().equals(this.EXPERT_ROLEID)) {// 当前登录用户为专家
			request.setAttribute("expertId", user.getUserId());
		}		

		@SuppressWarnings("unchecked")
		List<MstCodeInfo> mstCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_MSTCODEINFOS);
		// 数据检索
		List<ExpertInfoListVO> resultList = expertService.searchAll(request);		
		// 数据做成
		List<ArrayList<String>> list = expertService.getOutputContent(resultList, mstCodeInfos);
		
		// 文件生成 
		String title = ZzjConstants.FILE_OUTPUT_NAME_EXPERT + "_" + DateUtil.getDateTimeStr(new Date())
				+ ZzjConstants.FILE_OUTPUT_CSV;
		CSVUtils.createCSVFile(list, PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH), title);
		// 文件导出
		CSVUtils.exportToExcel(PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH) + File.separator + title, response, false);
	}
	
	/**
	 * 进行专家文章添加<br/>
	 * @param  request http请求实例
	 * @return 返回知识添加页面
	 */
	@RequestMapping(value="/addNews.htm")
	public String addNews(HttpServletRequest request){
		return "forward: information/information_edit";
	}
	
	/**
	 * 进行专家文章删除<br/>
	 * @param  request http请求实例
	 * @param  expertId 专家id
	 * @param  newsCd 知识id
	 * @param  model 返回的模型
	 * @return 返回专家编辑页面
	 * @throws Exception 
	 */
	@RequestMapping(value="/delExpertArticle.htm")
	public String delExpertArticle(HttpServletRequest request, String expertId, String newsCd, Model model) throws Exception{
		// 携带列表页面查询条件
		Map<String, Object> showBackMap = this.parseShowBackMap(request, "edit");
		if (showBackMap == null) {
			showBackMap = new HashMap<String, Object>();
		}
		model.addAttribute("selectResource", showBackMap);
		model.addAttribute("isAdd", 0);
		String doSearch = request.getParameter("doSearch");
		model.addAttribute("doSearch", doSearch);
		// 删除文章级文章领域置顶信息
		int delNum = expertService.delExpertArticle(request, newsCd);
		
		if (delNum == 1) {
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000002", null));
		}
		
		// 获得文章列表
		PageResult<NewsInfo> resultList = expertService.findNewsByExpertIdAndPage(request, expertId);
		model.addAttribute("resultList", resultList);

		// 获得专家信息
		ExpertInfoEditVO expertInfoEditVO = expertService.selectByPrimaryKey(request, expertId);
		model.addAttribute("expertInfo", expertInfoEditVO);
		return "expert/expert_edit";
	}
	
	/**
	 * 封装专家一览页面查询条件，用于回显数据<br/>
	 * @param request 请求实例
	 * @param edit 判断是否从编辑页面跳过来，edit="edit"表示从编辑页面跳过来，pageNo取值是expertPageNo；其他情况pageNo取值是pageNo
	 * @return Map<String,Object> 用于回显数据
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private Map<String, Object> parseShowBackMap(HttpServletRequest request, String edit) throws ClassNotFoundException, IOException{
		String flag = request.getParameter("flag");
		/**
		 * 获取查询条件的值
		 */
		// 专家姓名
		String expertName = null;
		// 审核状态（0 待审核， 1 已审核）
		String[] auditStatus = null;
		// 领域（技术领域）
		String[] techField = null;
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbn = null;
		//从返回页面回到列表页
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
		// 当前页
		String pageNoStr = null;
		
		// list为标记从专家一览页面跳转过来
		// 能取到值为list说明是，则一览页面的翻页pageNo保存在expertPageNo中
		// 取不到说明不是，则一览页面的翻页已经保存在expertPageNo中，只需继续携带即可
		if ("list".equals(request.getParameter("list"))) {
			request.setAttribute("expertPageNo", request.getParameter("pageNo"));
		} else {
			request.setAttribute("expertPageNo", request.getParameter("expertPageNo"));
		}
		
		// edit标记是否从编辑页面跳转过来
		// 能取到值为edit说明是，则一览页面的翻页pageNo从expertPageNo中取值
		// 取不到说明不是，则一览页面的翻页从pageNo中取值
		if ("edit".equals(edit)) {
			pageNoStr = request.getParameter("expertPageNo");
			if ("news".equals(request.getParameter("news"))) {// news标记专家编辑页面进行文章翻页操作，此时文章的pageNo从pageNo中取值
				pageNoStr = request.getParameter("pageNo");
			}
		} else {
			pageNoStr = request.getParameter("pageNo");
		}
		
		// 页大小
		Integer pageSize = ZzjConstants.DEFAULT_PAGESIZE;
		
		/**
		 * 构造map
		 */
		Map<String, Object> showBackMap = new HashMap<String, Object>();
		
		if (StringUtil.isNotBlank(expertName)) {
			showBackMap.put("expertName", expertName);
		}
		if (StringUtil.isNotBlank(auditStatus)) {
			showBackMap.put("auditStatus", String.join(",", auditStatus));
		}
		if (StringUtil.isNotBlank(techField)) {
			showBackMap.put("techField", String.join(",", techField));
		}
		if (StringUtil.isNotBlank(recommendKbn)) {
			showBackMap.put("recommendKbn", String.join(",", recommendKbn));
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
		return showBackMap;
	}
	
	private String parseShowBackString(HttpServletRequest request) throws ClassNotFoundException, IOException{
		String newsEdit = request.getParameter("newsEdit");
		//编辑页翻页
		String news = request.getParameter("news");
		/**
		 * 获取查询条件的值
		 */
		// 专家姓名
		String expertName = null;
		// 审核状态（0 待审核， 1 已审核）
		String[] auditStatus = null;
		// 领域（技术领域）
		String[] techField = null;
		// 推荐置顶 （1 推荐， 2 置顶）
		String[] recommendKbn = null;
		
		String pageNo = null;
		String expertPageNo = null;
		//从返回页面回到列表页或者编辑页翻页
		if(StringUtil.isNotBlank(newsEdit) && "newsEdit".equals(newsEdit)){
			String selectResource = request.getParameter("selectResource");
			byte[] b = Base64.decode(selectResource);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			if(null != keyVO){
				expertName = keyVO.getExpertName();
				auditStatus = keyVO.getAuditStatus();
				techField = keyVO.getField();
				recommendKbn = keyVO.getRecommendKbn();
				pageNo = keyVO.getPageNo();
				expertPageNo = keyVO.getExpertPageNo();
			}
		}else if(StringUtil.isNotBlank(news) && "news".equals(news)){
			String selectResource = request.getParameter("selectResource");
			byte[] b = Base64.decode(selectResource);
			KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
			if(null != keyVO){
				expertName = keyVO.getExpertName();
				auditStatus = keyVO.getAuditStatus();
				techField = keyVO.getField();
				recommendKbn = keyVO.getRecommendKbn();
				pageNo = request.getParameter("pageNo");
				expertPageNo = request.getParameter("expertPageNo");
			}
		}else{
			expertName = request.getParameter("expertNameLike");
			auditStatus = request.getParameterValues("auditStatus");
			techField = request.getParameterValues("techField");
			recommendKbn = request.getParameterValues("recommendKbn");
			pageNo = request.getParameter("pageNo");
			expertPageNo = request.getParameter("expertPageNo");
		}
		// list为标记从专家一览页面跳转过来
		// 能取到值为list说明是，则一览页面的翻页pageNo保存在expertPageNo中
		// 取不到说明不是，则一览页面的翻页已经保存在expertPageNo中，只需继续携带即可
		if ("list".equals(request.getParameter("list"))) {
			request.setAttribute("expertPageNo", pageNo);
		} else {
			request.setAttribute("expertPageNo", expertPageNo);
		}
		
		KeyVO vo = new KeyVO();
		if (StringUtil.isNotBlank(expertName)) {
			vo.setExpertName(expertName);
		}
		if (StringUtil.isNotBlank(auditStatus)) {
			vo.setAuditStatus(auditStatus);
		}
		if (StringUtil.isNotBlank(techField)) {
			vo.setField(techField);
		}
		if (StringUtil.isNotBlank(recommendKbn)) {
			vo.setRecommendKbn(recommendKbn);
		}
		byte[] b = SerializUtils.serializ(vo);  
		String selectResource = Base64.encode(b);
		return selectResource;
	}
	
	/**
	 * 封装专家一览页面查询条件，用于回显数据<br/>
	 * 如果是新增专家，一览页面的回显条件需要重构<br/>
	 * @param showBackMap 一览页面的回显数据
	 * @param expertName 新增专家的姓名
	 * @return Map<String,Object> 用于回显数据
	 */
	private Map<String, Object> rebuildShowBackMap(Map<String, Object> showBackMap, String expertName) {
		if (StringUtil.isBlank((String)showBackMap.get("expertName"))
				&& StringUtil.isBlank((String)showBackMap.get("auditStatus"))
				&& StringUtil.isBlank((String)showBackMap.get("techField"))
				&& StringUtil.isBlank((String)showBackMap.get("recommendKbn"))) {
			showBackMap.put("expertName", expertName);
		}
		return showBackMap;
	}
}

