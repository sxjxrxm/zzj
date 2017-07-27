/**
 * Project Name:zzj-web
 * File Name:UserController.java
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

import com.google.gson.Gson;
import com.zzj.core.web.controller.BaseController;
import com.zzj.db.dto.MstUsersInfo;
import com.zzj.db.model.PageResult;
import com.zzj.db.model.UserVO;
import com.zzj.manage.service.UserService;
import com.zzj.util.PropertyUtil;
import com.zzj.util.StringUtil;
import com.zzj.util.ZzjConstants;

/**
 * <p><strong>类名: </strong></p>UserController <br/>
 * <p><strong>功能说明: </strong></p>用户一览，用户编辑页面跳转 <br/>
 * <p><strong>创建日期: </strong><br/></p>2016年11月16日上午10:15:27 <br/>
 * @author   刘研
 * @version  1.0
 * @since    JDK 1.8	 
 */
@Controller
@RequestMapping("mstuser") 
public class UserController extends BaseController{
	/**
	 * User业务操作Service
	 */
	@Autowired
	private  UserService userService;
	/**
	 * 
	 *模块访问方法：跳转到用户一览界面<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/list.htm") 
	public String index(HttpServletRequest request,String isAdd,String userId,String keyUserId,String keyRoleId,String keyUserName,String keyPhoneNumber,String keypageNumber) {
		 // 根据isAdd判断页面是由用户添加返回还是由用户编辑返回
		// 定义查询结果
		PageResult<MstUsersInfo> mstUsersInfolist=new PageResult<>();
		if(isAdd == null){
			return "user/user_list";
		}
		else if("0".equals(isAdd)){
			// 如果由用户编辑返回：根据之前查询条件查询出对应记录并返回list页面
			mstUsersInfolist = userService.searchUsers(keyRoleId,keyPhoneNumber,keyUserName,keyUserId,keypageNumber) ;
			request.setAttribute("keyUserId", keyUserId);
			request.setAttribute("keyRoleId", keyRoleId);
			request.setAttribute("keyUserName", keyUserName);
			request.setAttribute("keyPhoneNumber", keyPhoneNumber);
			request.setAttribute("keypageNumber", keypageNumber);}
		else {
			mstUsersInfolist = userService.searchUsers("","","",userId,keypageNumber) ;
			request.setAttribute("newUserId", userId);
		}
		// 实例化一个User作为查询条件保存
		MstUsersInfo userSearchKey=new MstUsersInfo();
		userSearchKey.setRoleId(keyRoleId);
		userSearchKey.setPhoneNumber(keyPhoneNumber);
		userSearchKey.setUserName(keyUserName);
		userSearchKey.setUserId(keyUserId);
		// 用一个Map表示要返回的结果
		Map<String,Object> resultMap=new HashMap<>();
		// 将查询条件和查询结果集放入map
		resultMap.put("userSearchKey", userSearchKey);
		resultMap.put("mstUsersInfolist", mstUsersInfolist);
				
		// 将查询结果返回一览界面
		Gson gson = new Gson() ;
		String result = gson.toJson(resultMap) ;
		request.setAttribute("keyUserList", result);
		request.setAttribute("isAdd",isAdd);
		request.setAttribute("userSearchKey", userSearchKey);
		// 跳转页面
		return "user/user_list";
	}
	/**
	 * 
	 * 按条件查询user<br/>
	 * @param  request http请求实例
	 * @return 返回页面名称
	 * @throws IOException 
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchUsers.htm") 
	public void serachUsers(HttpServletRequest request, HttpServletResponse response,String pageNumber)  throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		// 取得页面传值
		String roleId = request.getParameter("MstUsersInfo.roleId") ;
		String phoneNumber = request.getParameter("MstUsersInfo.phoneNumber") ;
		String userName = request.getParameter("MstUsersInfo.userName") ;
		String userId=request.getParameter("MstUsersInfo.userId");
		// 根据这个user查询符合条件的user
		PageResult<MstUsersInfo>  mstUsersInfolist = userService.searchUsers(roleId,phoneNumber, userName, userId, pageNumber) ;
		// 实例化一个User作为查询条件保存
		MstUsersInfo userSearchKey=new MstUsersInfo();
		userSearchKey.setRoleId(roleId);
		userSearchKey.setPhoneNumber(phoneNumber);
		userSearchKey.setUserName(userName);
		userSearchKey.setUserId(userId);
		// 用一个Map表示要返回的结果
		Map<String,Object> resultMap=new HashMap<>();
		// 将查询条件和查询结果集放入map
		resultMap.put("userSearchKey", userSearchKey);
		resultMap.put("mstUsersInfolist", mstUsersInfolist);
		// 转换成json
		Gson gson = new Gson() ;
		String result = gson.toJson(resultMap) ;
		// 将查询结果返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(result) ;
	}
	/**
	 * 
	 * 删除用户：根据条件更新用户deleteFlag(删除状态)字段<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  userId 用户Id
	 * @return 返回页面名称
	 * @throws IOException 
	 */
	@RequestMapping(value = "/deleteUser.htm") 
	public void unlockUser(HttpServletRequest request, HttpServletResponse response,String userId)  throws IOException{
		// 调用Service更新对应User
		Integer x=userService.deleteUser(userId) ;
		// 将更新结果返回页面
		PrintWriter  out = response.getWriter() ;
		out.print(x) ;
	}
	/**
	 * 
	 * 编辑用户：根据条件跳转到用户编辑或用户添加界面<br/>
	 * @param  request http请求实例
	 * @param  userId 用户Id
	 * @param  isAdd 添加/更新
	 * @param  keyUserId, keyRoleId, keyUserName, keyPhoneNumber, pageNumber 一览页面查询条件
	 * @return 返回页面名称
	 */
	@RequestMapping(value = "/edit.htm") 
	public String edit(HttpServletRequest request, String userId,Integer isAdd, String keyUserId, String keyRoleId, String keyUserName, String keyPhoneNumber, String pageNumber) {
		// 得到一个user
		UserVO userVO=new UserVO();
		userVO=userService.searchUser(userId);
		if(isAdd == 0){
			request.setAttribute("keyUserId", keyUserId);
			request.setAttribute("keyRoleId", keyRoleId);
			request.setAttribute("keyUserName", keyUserName);
			request.setAttribute("keyPhoneNumber", keyPhoneNumber);
			request.setAttribute("keypageNumber", pageNumber);
		}
		if(userVO != null){
			userVO.setIsAdd(isAdd);
			request.setAttribute("user", userVO);
		}
		return "user/user_edit";
	}
	/**
	 * 编辑用户：根据条件进行User编辑或新增<br/>
	 * @param  request http请求实例
	 * @param  request http响应实例
	 * @return 返回页面名称
	 * @throws Exception
	 */
	@RequestMapping(value = "/editUser.htm")
	@ResponseBody
	public String editUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		MstUsersInfo user=new MstUsersInfo();
		// 接受页面传值
		String isAdd=request.getParameter("isAdd");
		user.setUserId(request.getParameter("userId"));
		user.setRoleId(request.getParameter("roleId"));
		user.setCreateId(request.getParameter("createId"));
		user.setUpdateId(request.getParameter("updateId"));
		if(Integer.parseInt(isAdd) == 1){
			user.setPassword(StringUtil.stringToMD5(request.getParameter("password")));
		}
		
		user.setUserName(request.getParameter("userName"));
		user.setPhoneNumber(request.getParameter("phoneNumber"));
		user.setDeleteFlag(Integer.getInteger(request.getParameter("deleteFlag")));
		// 调用service进行User的编辑
		userService.edituser(user,isAdd);
		return "1";
	}
	/**
	 * check用户Id是否已经存在：根据主键查询用户<br/>
	 * @param  request http请求实例
	 * @param  response http响应实例
	 * @param  userId 用户Id
	 * @throws IOException 
	 */
	@RequestMapping(value = "/checkUserId.htm") 
	public void checkUserId(HttpServletRequest request, HttpServletResponse response,String userId)  throws IOException{
		// 调用Service查询对应User
		MstUsersInfo mstUsersInfo=userService.checkUserId(userId) ;
		// 将更新结果返回页面
		PrintWriter  out = response.getWriter() ;
		// 判断查询结果是否为空，是则返回1，否则返回0
		if(mstUsersInfo != null){
			out.print(0) ;
		}
		else{
			out.print(1);
		}
	}

	/**
	 * 变更密码<br/>
	 * @param  request 请求实例
	 * @param  userId 用户Id
	 * @return 返回操作结果
	 * @throws Exception 系统配置信息未能加载异常
	 */
	@RequestMapping(value="/editPassword.htm")
	public @ResponseBody String editPassword(HttpServletRequest request, String userId) throws Exception {
		// 英文和数字的正则表达式
		String regex = "^[a-z0-9A-Z]+$";
		
		// 新密码
		String password = request.getParameter("newPassword1");
		
		// 旧密码
		String oldPassword = request.getParameter("oldPassword");
		
		// 新密码
		String newPassword = request.getParameter("newPassword2");
		StringBuilder msg = new StringBuilder();
		// 调用Service查询对应User
		UserVO userVo = userService.searchUser(userId) ;
		if (StringUtil.isBlank(oldPassword)) {
			msg.append(PropertyUtil.getMessageContent("E1000001", new Object[] {"旧密码"})).append("<br/>");
		}
		if (StringUtil.isBlank(password)) {
			msg.append(PropertyUtil.getMessageContent("E1000001", new Object[] {"新密码"})).append("<br/>");
		}
		if (StringUtil.isBlank(newPassword)) {
			msg.append(PropertyUtil.getMessageContent("E1000001", new Object[] {"新密码确认"})).append("<br/>");
		}
		if (msg.length() > 0) 
		{
			return msg.toString();
		}
		
		// 加密后的旧密码
		oldPassword = StringUtil.stringToMD5(request.getParameter("oldPassword"));		
		// 加密后的新密码
		newPassword = StringUtil.stringToMD5(request.getParameter("newPassword1"));
		
		if (!oldPassword.equals(userVo.getPassword())){
			// 旧密码不正确
			msg.append(PropertyUtil.getMessageContent("E1000022", null)).append("<br/>");
		}
		else if (!request.getParameter("newPassword1").equals(request.getParameter("newPassword2"))) {
			// 新密码与确认密码不一致
			msg.append(PropertyUtil.getMessageContent("E1000011", null)).append("<br/>");
		}
		else if (request.getParameter("newPassword1").length()<6) {
			// 新密码不足6位
			msg.append(PropertyUtil.getMessageContent("E1000013", null)).append("<br/>");
		}
		else if (!password.matches(regex)) {
			// 密码必须并且只能由数字和字母组成			
			msg.append(PropertyUtil.getMessageContent("E1000014", null)).append("<br/>");
		}
		else if (oldPassword.equals(newPassword)) {
			// 新密码与旧密码不能相同
			msg.append(PropertyUtil.getMessageContent("E1000012", null)).append("<br/>");
		}
		
		if (msg.length() > 0) 
		{
			return msg.toString();
		}
		else {
			// 更新新密码
			userVo = new UserVO();
			userVo.setUserId(userId);
			userVo.setPassword(newPassword);
			userVo.setUpdateId(userId);
			userService.edituser(userVo,"2");
		}
		return ZzjConstants.SUCCESS;
	}
}

