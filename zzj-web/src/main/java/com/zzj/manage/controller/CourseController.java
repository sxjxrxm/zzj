/**
 * Project Name:zzj-web
 * File Name:courseController.java
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
import com.zzj.db.model.CourseInfoVO;
import com.zzj.db.model.KeyVO;
import com.zzj.db.model.PageResult;
import com.zzj.manage.service.CommonService;
import com.zzj.manage.service.CourseService;
import com.zzj.util.Base64;
import com.zzj.util.CSVUtils;
import com.zzj.util.DateUtil;
import com.zzj.util.PropertyUtil;
import com.zzj.util.SerializUtils;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>CourseController <br/>
 * <p><strong>功能说明: </strong></p>课堂管理Controller <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年12月12日下午1:24:30 <br/>
 * @author   周洁荣
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("course")
public class CourseController extends BaseController {
	
	/**
	 * 业务操作Service
	 */
	@Autowired
	private  CourseService courseService;
	
	/**
	 * 共通业务操作Service
	 */
	@Autowired
	private CommonService commonService;
	
	/**
	 * 按条件查询课堂一览<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping(value="/list.htm")
	public String list(HttpServletRequest request) throws Exception {
		
		String doSearch = request.getParameter("doSearch");
		if (StringUtil.isNotBlank(doSearch)) {
			Map<String, Object> map = courseService.selectKeyMap(request, "search");
			PageResult<CourseInfoVO> resultList = courseService.searchPagging(map);	
			request.setAttribute("resultList", resultList);
			if (resultList.getItems() == null || resultList.getItems().size() == 0)
			{
				request.setAttribute(ZzjConstants.SYS_WARNING_KEY, PropertyUtil.getMessageContent("E1000015", null));
			}
		}
		request.setAttribute("doSearch", "0");
		
		return "course/course_list";
	}
	
	/**
	 * 按条件查询课堂一览CSV导出<br/>
	 * @param  request http请求实例
	 * @param  response http返回实例
	 * @throws Exception 
	 */
	@RequestMapping(value="/CSV.htm")
	public void outputCSV(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> mstCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_TECHCODEINFOS);
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> rishCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_RISHCODEINFOS);
		mstCodeInfos.addAll(rishCodeInfos);
		// 数据检索 
		Map<String, Object> map = courseService.selectKeyMap(request, "");
		List<CourseInfoVO> resultList = courseService.searchAll(map);		
		// 数据做成
		List<ArrayList<String>> list = courseService.getOutputContent(resultList, mstCodeInfos);		
        // 文件生成 
        String title = ZzjConstants.FILE_OUTPUT_NAME_COURSE + "_" + DateUtil.getDateTimeStr(new Date()) + ZzjConstants.FILE_OUTPUT_CSV;
		CSVUtils.createCSVFile(list, PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH), title);
		// 文件导出
		CSVUtils.exportToExcel(PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH) + File.separator + title, response, false);
	}
	
	/**
	 * 删除记录。<br/>
	 * @param  request 请求实例
	 * @param  courseCd 课堂编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/del.htm")
	public String delete(HttpServletRequest request, String courseCd) throws Exception {		
		CourseInfoVO record = courseService.setUpdateInfo(request, courseCd);
		// 删除记录
		courseService.delete(record);
		Map<String, Object> map = courseService.selectKeyMap(request, "search");
		PageResult<CourseInfoVO> resultList = courseService.searchPagging(map);	
		request.setAttribute("resultList", resultList);
		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000002", null));
		
		return "course/course_list";
	}
	
	/**
	 * 关闭课堂<br/>
	 * @param  request 请求实例
	 * @param  courseCd 课堂编码
	 * @return String 执行结果
	 * @throws Exception 
	 */
	@RequestMapping(value = "/close.htm")
	public String closeCourse(HttpServletRequest request, String courseCd) throws Exception {
		// 设置信息
		CourseInfoVO record = courseService.setUpdateInfo(request, courseCd);
		record.setRoomId(request.getParameter(courseCd + "_roomId"));
		// 关闭课堂
		int ret = courseService.closeCourse(record);
		Map<String, Object> map = courseService.selectKeyMap(request, "");
		PageResult<CourseInfoVO> resultList = courseService.searchPagging(map);	
		request.setAttribute("resultList", resultList);
		if (ret == -1)
		{
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000008", null));
		}
		else
		{
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000007", null));
		}		
		return "course/course_list";
	}
	
	/**
	 * 跳转到编辑页面。<br/>
	 * @param  request 请求实例
	 * @param  courseCd 课堂编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/edit.htm")
	public String edit(HttpServletRequest request, String courseCd) throws Exception {
		
		// 参数保存
		courseService.selectKeyMap(request, "edit");
		request.setAttribute("isAdd", request.getParameter("isAdd"));
		// 画面刷新
		courseService.load(request, courseCd);
		
		return "course/course_edit";
	}
	
	/**
	 * 跳转到在线课堂页面。<br/>
	 * @param  request 请求实例
	 * @param  courseCd 课堂编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/enterCourse.htm")
	public String enterCourse(HttpServletRequest request, String courseCd) throws Exception {
		
		// 参数保存
		courseService.selectKeyMap(request, "");
		// 进入课堂
		courseService.loadLiveCourse(request, courseCd);
		
		return "course/course_live";
	}
	
	/**
	 * 在线课堂显示历史消息。<br/>
	 * @param  request 请求实例
	 * @param  response 响应实例
	 * @param  courseCd 课堂编码
	 * @throws Exception
	 */
	@RequestMapping(value="/showMoreMsg.htm")
	public void showMoreMsg(HttpServletRequest request, HttpServletResponse response, String courseCd) throws Exception {
		courseService.showMoreMsg(request, response, courseCd);
	}
	
	/**
	 * 图片异步请求<br/>
	 * @param  file 文件请求
	 * @param  req 请求实例
	 * @return Map<String, String> 返回文件保存地址
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/upload.htm")
	public Map<String, String> queryPic(MultipartRequest file, HttpServletRequest req) throws Exception {
		Map<String, String> resultMap = courseService.queryPic(file, req);
		return resultMap;
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
		courseService.selectKeyMap(request, "insert");		
		// 携带列表页面查询条件
		CourseInfoVO record = courseService.getSaveCourseInfo(request, "");				
		// 更新数据库
		int ret = courseService.save(record);
		if (ret == -1)
		{
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000008", null));
		}
		else
		{	
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));
		}
		// 画面刷新
		courseService.load(request, record.getCourseCd());
		if(ZzjConstants.NUM_I_1 == ret){
			String isAdd = request.getParameter("isAdd");
			request.setAttribute("isAdd", isAdd);
			//添加
			if(StringUtil.isNotBlank(isAdd) && "1".equals(isAdd)){
				String selectKey = request.getParameter("selectKey");
				byte[] b = Base64.decode(selectKey);
				KeyVO keyVO = SerializUtils.deserialize(b, KeyVO.class);
				String courseName = keyVO.getCourseName();
				String[] paymentKbn = keyVO.getPaymentKbn();
				String[] field = keyVO.getField();
				String sDate = keyVO.getsDate();
				String eDate = keyVO.geteDate();
				String[] recommendKbn = keyVO.getRecommendKbn();
				String[] courseType = keyVO.getCourseType();
				if(StringUtil.isBlank(courseName)
					&& (null == paymentKbn || paymentKbn.length == ZzjConstants.NUM_I_0)
					&& (null == field || field.length == ZzjConstants.NUM_I_0)
					&& StringUtil.isBlank(sDate) && StringUtil.isBlank(eDate)
					&& (null == recommendKbn || recommendKbn.length == ZzjConstants.NUM_I_0)
					&& (null == courseType || courseType.length == ZzjConstants.NUM_I_0)){
					keyVO.setCourseName(record.getCourseName());
					//重新序列化
					byte[] data = SerializUtils.serializ(keyVO);  
					String selectKey2 = Base64.encode(data);
					request.setAttribute("selectKey", selectKey2);
				}
			}
		}
		return "course/course_edit";
	}

	/**
	 * 富文本编辑器中的图片请求<br/>
	 * @param  file 文件请求
	 * @param  response 响应实例
	 * @return Map<String, String> 返回图片保存的url
	 * @throws Exception
	 */
	@RequestMapping(value = "/textEditorImg.htm")
	@ResponseBody
	public Map<String, String> textEditorImg(MultipartRequest file, HttpServletResponse response) throws Exception {
		return commonService.textEditorImg(file, ZzjConstants.UPLOAD_TYPE_CONTENT, ZzjConstants.BUSI_TYPE_04);
	}

}

