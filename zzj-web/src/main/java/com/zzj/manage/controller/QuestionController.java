/**
 * Project Name:zzj-web
 * File Name:QuestionController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstCodeInfo;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.dto.QuestionAnswer;
import com.zzj.db.model.PageResult;
import com.zzj.db.model.QuestionVO;
import com.zzj.manage.service.QuestionService;
import com.zzj.util.CSVUtils;
import com.zzj.util.DateUtil;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>QuestionController <br/>
 * <p><strong>功能说明: </strong></p>e问一览、e问编辑共通控制类 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月15日下午1:41:14 <br/>
 * @author   李善瑞
 * @version  1.0 
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping(value = "/question")
public class QuestionController extends BaseController {

	/**
	 * e问管理模块操作Service
	 */
	@Autowired
	private QuestionService questionService;
	
	/**
	 * 跳转到E问一览页面。<br/>
	 * @param  request 请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/list.htm")
	public String toLoginQuestionListPage(HttpServletRequest request) throws Exception {
		
		String isShow = request.getParameter("isShow");
		String pageNo = request.getParameter("questionPageNo");
		if(StringUtils.isNotBlank(isShow) && isShow.equals("true") || StringUtils.isNotBlank(pageNo)) {
			// 保存查询条件
			QuestionVO info = questionService.selectKeyMap(request, "search");
			PageResult<QuestionVO> questionInfos = questionService.searchPagging(info);
			// 传给页面检索结果
			request.setAttribute("resultList", questionInfos);
			// 选项回显
			request.setAttribute("info", info);
		}
		
		return "question/question_list";
	}
	
	/**
	 * E问一览页面删除。<br/>
	 * @param  request 请求实例
     * @param  questionCd e问编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/update.htm")
	public String delete(HttpServletRequest request, String questionCd) throws Exception {

		// 删除
		questionService.update(questionCd);
		// 保存查询条件
		QuestionVO info = questionService.selectKeyMap(request, "search");
		// 刷新画面
		PageResult<QuestionVO> questionInfos = questionService.searchPagging(info);
		// 传给页面检索结果
		request.setAttribute("resultList", questionInfos);
		// 选项回显
		request.setAttribute("info", info);
		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000002", null));

		return "question/question_list";
	}	
	
	/**
	 * EXCEL导出<br/>
	 * @param  request http请求实例
	 * @param  response http返回实例
	 * @param  info E问信息
	 * @throws Exception 
	 */
	@RequestMapping(value="/CSV.htm")
	public void outputCSV(HttpServletRequest request, HttpServletResponse response, QuestionVO info) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> mstCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_TECHCODEINFOS);
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> rishCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_RISHCODEINFOS);
		mstCodeInfos.addAll(rishCodeInfos);
		// 数据检索
		List<QuestionVO> questionList = questionService.searchQuestionList(info);		
		// 数据做成
		List<ArrayList<String>> list = questionService.getOutputContent(questionList, mstCodeInfos);		
        // 文件生成 
        String title = ZzjConstants.FILE_OUTPUT_NAME_QUESTION + "_" + DateUtil.getDateTimeStr(new Date()) + ZzjConstants.FILE_OUTPUT_CSV;
		CSVUtils.createCSVFile(list, PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH), title);
		// 文件导出
		CSVUtils.exportToExcel(PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH) + File.separator + title, response, false);

	}

	/**
	 * 跳转到E问编辑页面。<br/>
	 * @param  request 请求实例
	 * @param  questionCd e问编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/edit.htm")
	public String questionEdit(HttpServletRequest request, String questionCd) throws Exception {
		
		// 保存查询条件
//		questionService.selectKeyMap(request, "edit");
		questionService.selectKeyString(request);
		// 画面刷新
		questionService.load(request, questionCd);
		
		return "question/question_edit";
	}
	
	/**
	 * 保存。<br/>
	 * @param  request 请求实例
	 * @return 返回页面名称
	 * @throws Exception 
	 */
	@RequestMapping(value="/save.htm")
	public String save(HttpServletRequest request) throws Exception {
		
		// 保存查询条件
		questionService.selectKeyString(request);
		// 携带列表页面查询条件
		QuestionVO record = questionService.getSaveQuestionInfo(request, "");				
		// 更新数据库
		int ret = questionService.save(record);
		if (ret != 1)
		{
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000008", null));
		}
		else
		{		
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000001", null));		
		}
		// 画面刷新
		questionService.load(request, record.getQuestionCd());
		return "question/question_edit";
	}
	
	/**
	 * 回答翻页。<br/>
	 * @param  request 请求实例
	 * @param  questionCd e问编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/answerPage.htm")
	public String answerPage(HttpServletRequest request, String questionCd) throws Exception {
				
		// 保存查询条件
//		questionService.selectKeyMap(request, "");
		questionService.selectKeyString(request);
		// 携带列表页面查询条件
		questionService.getSaveQuestionInfo(request, "pagging");
		
		if (StringUtil.isNotBlank(questionCd)) {
			request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000003", null));
		}
		return "question/question_edit";
	}
	
	/**
	 * 跳转到编辑页面。<br/>
	 * @param  request 请求实例
	 * @return 返回页面名称
	 */
	@RequestMapping(value="/answer.htm")
	public @ResponseBody String answer(HttpServletRequest request) {
			
		QuestionAnswer record  = questionService.getAnswerInfo(request);
		if (StringUtil.isNotBlank(record.getAnswerContent())) {
			if (record.getAnswerNo() != 0) {
				questionService.UpdateAnswer(record);
			}
			else {
				// 作成者
				MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
				record.setCreateId(user.getUserId());
				questionService.insertAnswer(record);
			}
		}
		else
		{			
			// 错误信息
			return PropertyUtil.getMessageContent("E1000001", new Object[] {"回答内容"});
		}
		
		return ZzjConstants.SUCCESS;
	}
	
	/**
	 * 删除记录。<br/>
	 * @param  request 请求实例
	 * @param  questionCd e问编码
	 * @param  answerNo 回答编码
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value="/delAnswer.htm")
	public String delAnswer(HttpServletRequest request, String questionCd, Integer answerNo) throws Exception {
		
		// 保存查询条件
//		questionService.selectKeyMap(request, "");
		questionService.selectKeyString(request);
		questionCd = request.getParameter("questionCd");
		QuestionAnswer key = new QuestionAnswer();
		key.setQuestionCd(questionCd);
		key.setAnswerNo(answerNo);
		questionService.delAnswer(key);
		// 画面刷新
		questionService.load(request, questionCd);
		request.setAttribute(ZzjConstants.SYS_INFO_KEY, PropertyUtil.getMessageContent("I1000004", null));
		
		return "question/question_edit";
	}
	
	/**
	 * EXCEL导出。<br/>
	 * @param  request http请求实例
	 * @param  response http返回实例
	 * @throws Exception 
	 */
	@RequestMapping(value="/detailCSV.htm")
	public void outputDetailCSV(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> mstCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_TECHCODEINFOS);
		@SuppressWarnings("unchecked")
		List<MstCodeInfo> rishCodeInfos = (List<MstCodeInfo>)request.getSession().getAttribute(ZzjConstants.SYS_SESSION_RISHCODEINFOS);
		mstCodeInfos.addAll(rishCodeInfos);
		// 数据检索
		String questionCd = request.getParameter("questionCd");
		QuestionVO questionVO = questionService.selectByPrimaryKey(questionCd);	
		// 数据做成
		List<ArrayList<String>> list = questionService.getDetailOutputContent(questionVO, mstCodeInfos);		
        // 文件生成 
        String title = ZzjConstants.FILE_OUTPUT_NAME_QUESTIONANSWER + "_" + DateUtil.getDateTimeStr(new Date()) + ZzjConstants.FILE_OUTPUT_CSV;
		CSVUtils.createCSVFile(list, PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH), title);
		// 文件导出
		CSVUtils.exportToExcel(PropertyUtil.getConfigValue(ZzjConstants.TMP_PATH) + File.separator + title, response, false);
	}

}

