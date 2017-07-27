/**
 * Project Name:zzj-web
 * File Name:RecommendController.java
 * Package Name:com.zzj.manage.controller
 * Copyright © 北京云物移大智信息技术有限公司  All Rights Reserved.
*/

package com.zzj.manage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.model.RecommendVO;
import com.zzj.manage.service.RecommendService;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>RecommendController <br/>
 * <p><strong>功能说明: </strong></p>这个类是封装推荐置顶模块代码. <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月28日下午3:33:14 <br/>
 * @author   任晓茂
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("recommend")
public class RecommendController extends BaseController {
	/**
	 * 推荐置顶业务操作Service
	 */
	@Autowired
	private RecommendService recommendService;
	/**
	 * 模块访问方法：跳转到推荐置顶一览界面<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @author 刘研
	 */
	@RequestMapping("/list.htm")
	public String index(HttpServletRequest request) {
		// 得到来访路径
		String requestURL = request.getHeader("referer");
		// 判断是否从首页来访
		boolean isIndex =true;
		if(requestURL != null && requestURL != ""){
			isIndex = requestURL.endsWith("management/index.htm");
		}
		// 将isIndex转到页面
		request.setAttribute("isIndex", isIndex);
		return "recommend/recommend_list";
	}
	/**
	 * 根据请求实例将消息置顶操作
	 * @param  request 请求实例，包含更新的信息及操作人的信息
	 * @param  code 操作分类： 1：置顶操作， 2：取消置顶操作， 3：推荐操作， 4：取消推荐操作
	 * @return String  执行操作的结果
	 * @author 任晓茂
	 */
	@RequestMapping(value="/recommendExecute.htm")
	public @ResponseBody String recommendExecute(HttpServletRequest request, Integer code) {
		String executeSuccess = "";
		executeSuccess = recommendService.recommendExecute(request, code);
		if (StringUtil.isBlank(executeSuccess)) {
			return ZzjConstants.SUCCESS;
		}		
		return executeSuccess;
	}
	/**
	 * 取得推荐语。
	 * @param  request http请求实例
	 * @return 推荐语
	 */
	@RequestMapping(value="/recommendMsg.htm")
	public @ResponseBody String recommendMsg(HttpServletRequest request) {
		return recommendService.selectRecommendMsg(request);
	}
	/**
	 * 模块访问方法：跳转到推荐置顶一览界面<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  recommendKbn 推荐置顶区分
	 * @return 返回页面名称
	 * @author 刘研
	 * @throws IOException 
	 */
	@RequestMapping(value="/selectRecommends.htm")
	public  void selectBusis(HttpServletRequest request, HttpServletResponse response, String busi, String recommendKbn) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		// 定义查询结果集
		List<RecommendVO> recommendVOs = new ArrayList<>();
		// 调用Service查询结果
		recommendVOs = recommendService.selectRecommends(recommendKbn,busi);
		// 转换成json
		Gson gson = new Gson() ;
		String result = gson.toJson(recommendVOs) ;
		// 将查询结果返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(result) ;
	}
	/**
	 * 批量取消方法：根据逐渐和推荐置顶状态更改相应记录删除状态<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  topicCd 主题编码
	 * @param  kbn 区分
	 * @param  busiType 业务区分
	 * @author   刘研
	 * @throws IOException 
	 */
	@RequestMapping(value="/delRecommends.htm")
	public  void delRecommends(HttpServletRequest request, HttpServletResponse response,String topicCd,String kbn,String busiType) throws IOException{
		// 定义变量作为返回值
		Integer x = -1;
		// 调用Service进行更改
		topicCd = URLDecoder.decode(topicCd,"utf-8"); 
		
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		String updateId = user.getUserId();
		x = recommendService.delRecommends(topicCd,kbn,updateId,busiType);
		// 将受影响条目数传回界面
		PrintWriter  out = response.getWriter() ;
		out.print(x) ;
	}
	/**
	 * 保存排序方法：根据逐渐和推荐置顶状态更改相应记录显示顺序<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  topicCd 主题编码
	 * @param  kbn 区分
	 * @param  busiType 业务区分
	 * @param  sortNo 表示顺
	 * @author   刘研
	 * @throws IOException 
	 */
	@RequestMapping(value="/saveSortNo.htm")
	public  void saveSortNo(HttpServletRequest request, HttpServletResponse response,String topicCd,String kbn,String busiType,String sortNo) throws IOException{
		// 定义变量作为返回值
		Integer x = -1;
		// 调用Service进行更改
		topicCd = URLDecoder.decode(topicCd,"utf-8");
		MstUsersInfo user = (MstUsersInfo) request.getSession().getAttribute(ZzjConstants.USER_SESSION_ADMIN_KEY);
		String updateId = user.getUserId();
		x = recommendService.saveSortNo(topicCd,kbn,updateId,busiType,sortNo);
		// 将受影响条目数传回界面
		PrintWriter  out = response.getWriter() ;
		out.print(x) ;
	}
}

